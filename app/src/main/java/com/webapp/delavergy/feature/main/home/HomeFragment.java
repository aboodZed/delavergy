package com.webapp.delavergy.feature.main.home;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.webapp.delavergy.R;
import com.webapp.delavergy.feature.main.MainActivity;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.dialog.WaitDialogFragment;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.NavigationView;
import com.webapp.delavergy.utils.location.LocationManager;
import com.webapp.delavergy.utils.location.locationHelper.FetchURL;
import com.webapp.delavergy.utils.location.locationHelper.TaskLoadedCallback;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements OnMapReadyCallback
        , LocationManager.Listener, TaskLoadedCallback, DialogView<Order> {

    public static final int page = 201;

    @BindView(R.id.map_view) MapView mapView;

    @BindView(R.id.ll_order_recive) LinearLayout llOrderRecive;
    @BindView(R.id.tv_receiver_address) TextView tvReceiverAddress;
    @BindView(R.id.tv_receiver_city) TextView tvReceiverCity;
    @BindView(R.id.tv_receiver_distance) TextView tvReceiverDistance;
    @BindView(R.id.btn_accept_order) Button btnAcceptOrder;
    @BindView(R.id.btn_reject_order) Button btnRejectOrder;

    private GoogleMap googleMap;
    private LocationManager locationManager;
    private boolean denialLock;
    private Polyline currentPolyLine;
    private HomePresenter homePresenter;
    private long order_id;

    public static HomeFragment newInstance(long id) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putLong(AppContent.ORDER_Id, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        //argument
        if (getArguments() != null)
            order_id = getArguments().getLong(AppContent.ORDER_Id);
        //map
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        //location
        locationManager = new LocationManager(this, getActivity(), this);
        homePresenter = new HomePresenter(getActivity(), this);
        homePresenter.tracking();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
    }

    @Override
    public void onServicesOrPermissionChoice() {
        denialLock = false;
    }

    @Override
    public void onLocationFound(double latitude, double longitude) {
        if (order_id != 0) {
            homePresenter.getOrderData(order_id);
        } else {
            zoomToLocation(new LatLng(latitude, longitude));
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if (requestCode != LocationManager.LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        // No need to check if the location permission has been granted because of the onResume() block
        if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            denialLock = true;
            locationManager.showLocationPermissionDialog();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LocationManager.LOCATION_SERVICES_CODE) {
            if (resultCode == RESULT_OK) {
                locationManager.fetchAutomaticLocation();
            } else {
                denialLock = true;
                locationManager.showLocationDenialDialog();
            }
        }
    }

    public void routeBetweenTwoPoints(LatLng latLng1, LatLng latLng2) {
        //parameter
        LatLngBounds.Builder builder;
        CameraUpdate cu;
        MarkerOptions place1, place2;
        //clear google map
        googleMap.clear();
        //create points
        place1 = new MarkerOptions().position(latLng1).title(getString(R.string.send_address))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_truck));
        place2 = new MarkerOptions().position(latLng2).title(getString(R.string.receive_address))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        //add points
        googleMap.addMarker(place1);
        googleMap.addMarker(place2);
        //zoom
        builder = new LatLngBounds.Builder();
        builder.include(place1.getPosition());
        builder.include(place2.getPosition());
        //url get route
        String url = locationManager.getUrl(place1.getPosition(), place2.getPosition());
        new FetchURL(this).execute(url, "driving");
        //zoom
        cu = CameraUpdateFactory.newLatLngBounds(builder.build(), 450);
        googleMap.animateCamera(cu);
    }

    public void zoomToLocation(LatLng latLng) {
        googleMap.clear();
        googleMap.addMarker(new MarkerOptions().position(latLng).title("أنا"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)
                .zoom(16)
                .bearing(0)
                .tilt(0)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

        // Run this here instead of onCreate() to cover the case where they return from turning on location
        if (!denialLock) {
            locationManager.fetchCurrentLocation();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        locationManager.cleanUp();
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyLine != null) {
            currentPolyLine.remove();
        } else {
            currentPolyLine = googleMap.addPolyline((PolylineOptions) values[0]);
        }
    }

    @Override
    public void setDistance(String o) {
        tvReceiverDistance.setText(o);
    }

    @OnClick(R.id.btn_accept_order)
    public void acceptOrder() {
        homePresenter.pickOrder(order_id);
    }

    @OnClick(R.id.btn_reject_order)
    public void rejectOrder() {
        NavigateUtils.activityIntent(getActivity(), MainActivity.class, false);
    }

    @Override
    public void setData(Order order) {
        Log.e("order_data", order.toString());

        tvReceiverAddress.setText(order.getSender_address());
        tvReceiverCity.setText(order.getFrom_state().getName());
        routeBetweenTwoPoints(new LatLng(order.getFrom_state().getLat(), order.getFrom_state().getLng())
                , new LatLng(order.getTo_state().getLat(), order.getTo_state().getLng()));

        //routeBetweenTwoPoints(new LatLng(31.5198972, 34.4457472), new LatLng(31.5242647, 34.4432764));
        llOrderRecive.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDialog(String s) {
        WaitDialogFragment.newInstance(s).show(getChildFragmentManager(), "");
    }

    @Override
    public void hideDialog() {
        WaitDialogFragment.newInstance("").dismiss();
    }
}