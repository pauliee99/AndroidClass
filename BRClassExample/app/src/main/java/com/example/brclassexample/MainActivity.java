package com.example.brclassexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver receiver;
    private MyService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver();
        Intent intent = new Intent(this, MyService.class);
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                MyService.MyBinder nBinder = (MyService.MyBinder) binder;
                service = nBinder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, conn, Context.BIND_AUTO_CREATE);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(service.doSomething());
            }
        });
    }


    public void registerReceiver(){
        receiver = new BroadcastReceiver(){
            @RequiresApi(api= Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onReceive(Context context, Intent intent){
                try {
                    String result;
                    int a = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON);
                    if (a==0){
                        result="OFF";
                    }else
                        result="ON";
                    Toast.makeText(context, "airplane mode:" + result, Toast.LENGTH_SHORT).show();
                } catch (Settings.SettingNotFoundException e) {
                    e.printStackTrace();
                }

            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(receiver, filter);
    }


}