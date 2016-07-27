package com.example.maxim.p0532myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    Button btnSecAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSecAct = (Button) findViewById (R.id.secact);
        btnSecAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.secact:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.zero:
                btnSecAct.setAlpha(0);
                break;
            case R.id.quarter:
                btnSecAct.setAlpha(0.25F);
                break;
            case R.id.three_fourths:
                btnSecAct.setAlpha(0.75F);
                break;
            case R.id.half:
                btnSecAct.setAlpha(0.5F);
                break;
            default:
                btnSecAct.setAlpha(1);
        }

        return super.onOptionsItemSelected(item);
    }
}
