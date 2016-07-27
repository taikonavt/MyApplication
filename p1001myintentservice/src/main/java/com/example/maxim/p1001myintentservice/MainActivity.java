package com.example.maxim.p1001myintentservice;

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

    public void onclick(View V) {
        startService(intent.putExtra("time", 3).putExtra("label", "Call 1"));
        startService(intent.putExtra("time", 1).putExtra("label", "Call 2"));
        startService(intent.putExtra("time", 4).putExtra("label", "Call 3"));

    }

}
