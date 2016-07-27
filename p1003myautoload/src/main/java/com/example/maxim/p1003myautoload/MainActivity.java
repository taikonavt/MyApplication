package com.example.maxim.p1003myautoload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MyService.class);
    }

    public void onclickStart(View v) {
        startService(intent);
    }

    public void onclickStop(View v) {
        stopService(intent);
    }

}
