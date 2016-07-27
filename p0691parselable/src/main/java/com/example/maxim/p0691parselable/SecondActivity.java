package com.example.maxim.p0691parselable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends Activity {

    final String LOG_TAG = "myLogs";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Log.d(LOG_TAG, "getParcelableExtra");
        MyObject myObj = (MyObject) getIntent().getParcelableExtra(
                MyObject.class.getCanonicalName());
        Log.d(LOG_TAG, "myObj: " + myObj.s + ", " + myObj.i);
    }
}