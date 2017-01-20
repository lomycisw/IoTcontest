package com.example.f_masa.proximityareadetection;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import android.os.Handler;

import com.example.f_masa.proximityareadetection.utils.JsonUtils;
import com.example.f_masa.proximityareadetection.utils.NetworkUtils;

import java.net.HttpURLConnection;

/**
 * Created by f-masa on 2016/12/15.
 */

public class LocationService extends Service {
    private double latitude = 0;
    private double longtitude = 0;
    private LocationManager locationManager;
    private int count = 0 ;
    private boolean isRunning = false;
    String msg;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean networkFlg = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            System.out.println("permission error");
        }
        if (networkFlg) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    1000,
                    locationListener
            );

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Post();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    private void Post(){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(isRunning) {
                        //request();
                        System.out.println(msg);
                        Post();
                    }
                }
            },1000);

    }
    private void request(){
        AsyncHttp asyncHttp = new AsyncHttp(new AsyncCallback() {
            @Override
            public void onPreExecute() {
            }
            @Override
            public void onPostExecute(AsyncHttp.HttpResponse result) {
                if(result.status == -1 || result.status == HttpURLConnection.HTTP_UNAUTHORIZED){
                    //TODO : If reject Http post
                }
            }
            @Override
            public void onProgressUpdate(int progress) {
            }
            @Override
            public void onCancelled() {

            }
        });
        asyncHttp.execute("POST", NetworkUtils.sendDataUrl, JsonUtils.sendDataJson());
    }




    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            msg = "Lat=" + location.getLatitude()
                    + "\nLng=" + location.getLongitude();

            latitude = location.getLatitude();
            longtitude = location.getLongitude();
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



}
