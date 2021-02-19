package com.example.mobdev2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.ImageView;
import android.widget.TextView;

public class BatteryMonitor extends Activity {

    @Override
    public void onStart() {
        super.onStart();

        registerReceiver(onBatteryChanged, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onStop() {
        super.onStop();

        unregisterReceiver(onBatteryChanged);
    }

    BroadcastReceiver onBatteryChanged=new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int pct=100*intent.getIntExtra("level", 1)/intent.getIntExtra("scale", 1);

            //bar.setProgress(pct);
            //level.setText(String.valueOf(pct));

            int plugged=intent.getIntExtra("plugged", -1);

            if (plugged==BatteryManager.BATTERY_PLUGGED_AC || plugged==BatteryManager.BATTERY_PLUGGED_USB) {
                Log.println(Log.INFO, "Info message", "charging......");
            }else{
                Log.println(Log.INFO, "Info message", "NOT charging..............");
            }

//            switch(intent.getIntExtra("status", -1)) {
//                case BatteryManager.BATTERY_STATUS_CHARGING:
//                case BatteryManager.BATTERY_STATUS_FULL:
//            }

        }
    };
}
