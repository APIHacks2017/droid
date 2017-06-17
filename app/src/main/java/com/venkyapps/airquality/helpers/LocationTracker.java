package com.venkyapps.airquality.helpers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by venkatesh on 17-Jun-17.
 */

public class LocationTracker implements LocationListener {

    private LocationManager locationManager;
    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private Criteria criteria;
    private String provider;
    Context context;
    boolean isLocationManagerCreated = true;


    public LocationTracker(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            isLocationManagerCreated = false;
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, this);
        setMostRecentLocation(locationManager.getLastKnownLocation(provider));
        isLocationManagerCreated = true;
    }

    private void setMostRecentLocation(Location lastKnownLocation) {

    }

    public boolean isLocationManagerCreated() {
        return isLocationManagerCreated;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = (double) (location.getLongitude());
        latitude = (double) (location.getLatitude());
        //  Toast.makeText(context, "lat " + latitude + " long " + longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String arg0) {
        //  Toast.makeText(context, "onProviderDisabled " , Toast.LENGTH_SHORT).show();
        latitude = 0.0;
        longitude = 0.0;
    }

    @Override
    public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub
        // Toast.makeText(context, "onProviderEnabled " , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub

    }

    public boolean isGpsAvailable() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}