package com.example.mobdev2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Runnable runnable;
    Handler handler = new Handler();
    private final int FIVE_SECONDS = 5000;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView locations = findViewById(R.id.locations);
        locations.setMovementMethod(new ScrollingMovementMethod());
        ContentResolver resolver = this.getContentResolver();

        registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int plugged = intent.getIntExtra("plugged", -1);
                intent = new Intent(MainActivity.this, MyService.class);
                if (plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB) {
                    Log.println(Log.INFO, "Info message", "charging......");
                    if (isFinishing()){
                        unregisterReceiver(this);
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                    stopService(intent);
                }else{
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        Log.println(Log.INFO, "Info message", "accessing");
                        return;
                    }
                    startService(intent);
                    Log.println(Log.INFO, "Info message", "NOT charging..............");
                }
            }

        }, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        scheduleSendLocation(locations, resolver);

        findViewById(R.id.stopLocationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locations.setText(null);
                Cursor cursor = resolver.query(Uri.parse(LocationContentProvider.CONTENT_URI+"/locations"),new String[]{"rowid"},null,null,null);
                if (cursor.moveToFirst()){
                    do{
                        locations.append(cursor.getString(1) + " | " +  cursor.getString(2) + " | " + cursor.getString(3) + String.format("%n", ""));
                        //Toast.makeText(MainActivity.this, cursor.getString(1), Toast.LENGTH_SHORT).show();
                    }while (cursor.moveToNext());
                }
                else locations.append("nothing to show");
            }
        });
    }

    public void scheduleSendLocation(TextView locations, ContentResolver resolver) {
        handler.postDelayed(new Runnable() {
            public void run() {
                handler.postDelayed(this, FIVE_SECONDS);
                locations.setText(null);
                Cursor cursor = resolver.query(Uri.parse(LocationContentProvider.CONTENT_URI+"/locations"),new String[]{"rowid"},null,null,null);
                if (cursor.moveToFirst()){
                    do{
                        locations.append(cursor.getString(1) + " | " +  cursor.getString(2) + " | " + cursor.getString(3) + String.format("%n", ""));
                        //Toast.makeText(MainActivity.this, cursor.getString(1), Toast.LENGTH_SHORT).show();
                    }while (cursor.moveToNext());
                }
                else locations.append("nothing to show");
            }
        }, FIVE_SECONDS);
    }


}