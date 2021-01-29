package com.webapp.delavergy.utils.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.webapp.delavergy.R;
import com.webapp.delavergy.models.MyLocation;
import com.webapp.delavergy.utils.AppContent;
import com.webapp.delavergy.utils.AppController;

public class GPSTracking {

    private Context context;
    private LocationManager lm;
    private LocationListener locationListener;

    private static GPSTracking myGpsTracking;

    public GPSTracking(Context context) {
        lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.context = context;
    }

    public static GPSTracking getInstance(Context context) {
        if (myGpsTracking == null) {
            myGpsTracking = new GPSTracking(context);
        }
        return myGpsTracking;
    }

    public void startMyGPSTracking() {
        MyLocation myLocation = new MyLocation();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference(AppContent.DELIVERY_APP_TRACKING)
                .child(AppController.getInstance().getAppLocal().getUser().getUser().getId() + "");

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(context, context.getString(R.string.un_able_get_location), Toast.LENGTH_SHORT).show();
        } else {
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        myLocation.setLat(location.getLatitude());
                        myLocation.setLng(location.getLongitude());
                        myLocation.setName(AppController.getInstance().getAppLocal().getUser().getUser().getName());
                        myLocation.setMobile(AppController.getInstance().getAppLocal().getUser().getUser().getMobile());
                        myLocation.setDriver_id(AppController.getInstance().getAppLocal().getUser().getUser().getId());
                        myLocation.setStatus(AppController.getInstance().getAppLocal().getUserStatus());
                        db.setValue(myLocation);
                    } else {
                        lm.removeUpdates(locationListener);
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
        }
    }

    public void removeMyUpdates() {
        if (this.locationListener != null)
            this.lm.removeUpdates(this.locationListener);
        myGpsTracking = null;
    }
}
