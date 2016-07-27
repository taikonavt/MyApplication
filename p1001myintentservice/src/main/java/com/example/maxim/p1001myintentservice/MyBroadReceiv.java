package com.example.maxim.p1001myintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by maxim on 16.11.15.
 */
public class MyBroadReceiv extends BroadcastReceiver {

    final String LOG_TAG = "myLogs";

    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive " + intent.getAction());
        context.startService(new Intent(context, MyService.class));
    }

}
