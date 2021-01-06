package com.webapp.delavergy.feature.main.home;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.webapp.delavergy.R;
import com.webapp.delavergy.models.Order;
import com.webapp.delavergy.utils.NavigateUtils;
import com.webapp.delavergy.utils.dialog.order.NewOrderDialogFragment;
import com.webapp.delavergy.utils.listener.DialogView;
import com.webapp.delavergy.utils.listener.NavigationView;
import com.webapp.delavergy.utils.location.LocationManager;
import com.webapp.delavergy.utils.location.locationHelper.FetchURL;
import com.webapp.delavergy.utils.location.locationHelper.TaskLoadedCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements OnMapReadyCallback
        , LocationManager.Listener, TaskLoadedCallback, DialogView<Order> {

    public static final int page = 201;

    @BindView(R.id.map_view) MapView mapView;
    @BindView(R.id.ll_order_recive) LinearLayout llOrderRecive;
    @BindView(R.id.iv_way_line) ImageView ivWayLine;
    @BindView(R.id.tv_receiver_address) TextView tvReceiverAddress;
    @BindView(R.id.tv_receiver_city) TextView tvReceiverCity;
    @BindView(R.id.tv_receiver_far) TextView tvReceiverFar;
    @BindView(R.id.tv_receiver_distance) TextView tvReceiverDistance;
    @BindView(R.id.tv_receiver_address_2) TextView tvReceiverAddress2;
    @BindView(R.id.tv_receiver_city_2) TextView tvReceiverCity2;
    @BindView(R.id.tv_receiver_far_2) TextView tvReceiverFar2;
    @BindView(R.id.tv_receiver_distance_2) TextView tvReceiverDistance2;
    @BindView(R.id.btn_accept_order) Button btnAcceptOrder;
    @BindView(R.id.btn_reject_order) Button btnRejectOrder;

    private GoogleMap googleMap;
    private LocationManager locationManager;
    private boolean denialLock, orderFound;
    private Polyline currentPolyLine;
    private NavigationView navigationView;
    private HomePresenter homePresenter;

    public static HomeFragment newInstance(NavigationView navigationView) {
        HomeFragment fragment = new HomeFragment(navigationView);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private HomeFragment(NavigationView navigationView) {
        this.navigationView = navigationView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        //map
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        //location
        locationManager = new LocationManager(this, getActivity(), this);
        orderFound = false;
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
        if (orderFound){
            homePresenter.getOrderData();
        }else {
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
        place1 = new MarkerOptions().position(latLng1).title("مكان الأستلام")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_truck));
        place2 = new MarkerOptions().position(latLng2).title("مكان التسليم")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        //add points
        googleMap.addMarker(place1);
        googleMap.addMarker(place2);
        //zoom
        builder = new LatLngBounds.Builder();
        builder.include(place1.getPosition());
        builder.include(place2.getPosition());
        //url get route
        String url = getUrl(place1.getPosition(), place2.getPosition(), "driving");
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

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters
                + "&key=" + getString(R.string.google_maps_key);
        return url;
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

    @OnClick(R.id.btn_accept_order)
    public void acceptOrder(){
        NavigateUtils.openOrder(getContext(), 1, true);
    }

    @OnClick(R.id.btn_reject_order)
    public void rejectOrder() {
        navigationView.navigate(page);
    }

    @Override
    public void setData(Order order) {
        llOrderRecive.setVisibility(View.VISIBLE);
        routeBetweenTwoPoints(new LatLng(31.5198972, 34.4457472), new LatLng(31.5242647, 34.4432764));
    }

    @Override
    public void showDialog(String s) {

    }

    @Override
    public void hideDialog() {

    }
}