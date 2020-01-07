package com.citytransport.android7;

import android.app.Activity;
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

public class GetLoation extends Activity implements LocationListener{

    private LocationListener listener;
    private LocationManager locationManager;


 public void getloc(){

     locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
     locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,this);
 }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
