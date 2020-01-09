package com.hamdy.infrastructurebase.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.hamdy.infrastructurebase.interfaces.OnLocationSuccessListener;

public class FusedLocation {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 999;

    private static String tag = "FusedLocation";
    private final Activity activity;

    private OnLocationSuccessListener onLocationSuccessListener;

    private  OnSuccessListener<Location> onSuccessListener = new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            if(onLocationSuccessListener != null)
                onLocationSuccessListener.onLocationSuccess(location);
        }
    };
    private FusedLocationProviderClient fusedLocationClient;

    public FusedLocation(Activity activity ) {
        this.activity = activity;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.activity);
    }

    public void getLocation(OnSuccessListener<Location> onSuccessListener) {

        Log.e(tag, "getLocation");

        this.onSuccessListener = onSuccessListener;

        if(activity == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkLocationPermission()) {
                    getLastKnownLocation();
            } else
                requestRuntimeLocationPermission();
        }
        else
            getLastKnownLocation();
    }

    public void getLocation(OnLocationSuccessListener onLocationSuccessListener) {

        Log.e(tag, "getLocation");

        this.onLocationSuccessListener = onLocationSuccessListener;

        if(activity == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkLocationPermission()) {
                    getLastKnownLocation();
            } else
                requestRuntimeLocationPermission();
        }
        else
            getLastKnownLocation();
    }

    private void getLastKnownLocation() {
        if(onSuccessListener == null) return;
        fusedLocationClient.getLastLocation().addOnSuccessListener(activity, onSuccessListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestRuntimeLocationPermission() {
        activity.requestPermissions(
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if(requestCode == MY_PERMISSIONS_REQUEST_LOCATION){

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                getLastKnownLocation();
            }
        }
    }

}
