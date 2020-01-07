package com.citytransport.android7;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

public class GpsService extends Service {

    private LocationListener listener;
    private LocationManager locationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }


    @Override
    public void onCreate() {
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent("location_update");
                i.putExtra("coordinates_long",location.getLongitude());
                i.putExtra("coordinates_lat",location.getLatitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,listener);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (locationManager != null){
            locationManager.removeUpdates(listener);
        }
    }


}
