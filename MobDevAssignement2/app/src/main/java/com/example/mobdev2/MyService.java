package com.example.mobdev2;

import android.Manifest;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.sql.Timestamp;

public class MyService extends Service {
    LocationManager locationManager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Service started!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Service stopped!", Toast.LENGTH_LONG).show();
        //android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //scheduleSendLocation();
        showLocation();
        return Service.START_REDELIVER_INTENT;
    }

    public void showLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ContentResolver resolver = this.getContentResolver();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.println(Log.INFO, "Info message", "accessing");
            return;
        }
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 5000, 10, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.println(Log.INFO, "Database info", " hreerrer");
                ContentValues values = new ContentValues();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                values.put("latitude", location.getLatitude());
                values.put("longitude", location.getLongitude());
                values.put("timestamp", timestamp.toString());
                Uri result = resolver.insert(Uri.parse(LocationContentProvider.CONTENT_URI + "/locations"), values);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("update", "ok");
                Toast.makeText(getApplicationContext(), "location saved", Toast.LENGTH_SHORT).show();
            }
        });
    }


}