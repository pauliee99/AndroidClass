package com.example.brclassexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    private String test;

    public MyService() {
        test = "Hello";
    }

    @Override
    public IBinder onBind(Intent intent) {
        MyBinder binder = new MyBinder();
        return binder;
    }

    public String doSomething(){
        test+=test;
        return test;
    }

    public class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }

}