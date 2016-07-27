package com.example.maxim.p1001myintentservice;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends IntentService {

    final String LOG_TAG = "myLogs";

    public MyService() {
        super("myname");
    }
/*
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");
        return START_NOT_STICKY;
    }
*/
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int tm = intent.getIntExtra("time", 0);
        String label = intent.getStringExtra("label");
        Log.d(LOG_TAG, "onHandleIntent start " + label);
        try {
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, "onHandleIntent end " + label);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
/*
    public IBinder onBind (Intent arg0) {
        return null;
    }
*/
}