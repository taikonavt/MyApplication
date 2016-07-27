package com.example.maxim.p0532myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by maxim on 24.09.15.
 */
public class SecondActivity extends Activity implements View.OnClickListener{

    Button btnMainAct;
    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        btnMainAct = (Button) findViewById (R.id.mainact);
        btnMainAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mainact:
                finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, 1, 1, "0%");
        menu.add(0, 2, 2, "25%");
        menu.add(0, 3, 3, "50%");
        menu.add(0, 4, 4, "75%");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case 1:
                btnMainAct.setAlpha(0);
                break;
            case 2:
                btnMainAct.setAlpha(0.25F);
                break;
            case 3:
                btnMainAct.setAlpha(0.5F);
                break;
            case 4:
                btnMainAct.setAlpha(0.75F);
                break;
            default:
                btnMainAct.setAlpha(1);
        }

        return super.onOptionsItemSelected(item);
    }
}
