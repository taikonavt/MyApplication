package com.example.maxim.my0031valentinsday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    String TAG = "myLogs";
    Button roseBtn;
    Intent roseIntent;
    Button proposeBtn;
    Intent proposeIntent;
    Button chocolateBtn;
    Intent chocolateIntent;
    Button teddyBtn;
    Intent teddyIntent;
    Button promiseBtn;
    Intent promiseIntent;
    Button hugBtn;
    Intent hugIntent;
    Button kissBtn;
    Intent kissIntent;
    Button valentineBtn;
    Intent valentineIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roseBtn = (Button) findViewById(R.id.roseBtn);
        proposeBtn = (Button) findViewById(R.id.proposeBtn);
        chocolateBtn = (Button) findViewById(R.id.chocolateBtn);
        teddyBtn = (Button) findViewById(R.id.teddyBtn);
        promiseBtn = (Button) findViewById(R.id.promiseBtn);
        hugBtn = (Button) findViewById(R.id.hugBtn);
        kissBtn = (Button) findViewById(R.id.kissBtn);
        valentineBtn = (Button) findViewById(R.id.valentineBtn);

        setBtnsText();
    }

    void setBtnsText () {

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int dayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);

        int roseDay = 38;
        int proposeDay = 39;
        int chocolateDay = 40;
        int teddyDay = 41;
        int promiseDay = 42;
        int hugDay = 43;
        int kissDay = 44;
        int valentineDay = 45;

        int remainRoseDays = (dayOfYear > roseDay ?
                0 : (roseDay - dayOfYear));
        int remainProposeDays = (dayOfYear > proposeDay ?
                0 : (proposeDay - dayOfYear));
        int remainChocolateDays = (dayOfYear > chocolateDay ?
                0 : (chocolateDay - dayOfYear));
        int remainTeddyDays = (dayOfYear > teddyDay ?
                0 : (teddyDay - dayOfYear));
        int remainpromiseDays = (dayOfYear > promiseDay ?
                0 : (promiseDay - dayOfYear));
        int remainHugDays = (dayOfYear > hugDay ?
                0 : (hugDay - dayOfYear));
        int remainKissDays = (dayOfYear > kissDay ?
                0 : (kissDay - dayOfYear));
        int remainValentineDays = (dayOfYear > valentineDay ?
                0 : (valentineDay - dayOfYear));

        String roseStr = "Rose Day SMS \n" + remainRoseDays + " Days To Go";
        String proposeStr = "Propose Day SMS \n" + remainProposeDays + " Days To Go";
        String chocolateStr = "Chocolate Day SMS \n" + remainChocolateDays + " Days To Go";
        String teddyStr = "Teddy Day SMS \n" + remainTeddyDays + " Days To Go";
        String promiseStr = "Promise Day SMS \n" + remainpromiseDays + " Days To Go";
        String hugStr = "Hug Day SMS \n" + remainHugDays + " Days To Go";
        String kissStr = "Kiss Day SMS \n" + remainKissDays + " Days To Go";
        String valentineStr = "Valentine Day SMS \n" + remainValentineDays + " Days To Go";

        roseBtn.setText(roseStr);
        proposeBtn.setText(proposeStr);
        chocolateBtn.setText(chocolateStr);
        teddyBtn.setText(teddyStr);
        promiseBtn.setText(promiseStr);
        hugBtn.setText(hugStr);
        kissBtn.setText(kissStr);
        valentineBtn.setText(valentineStr);
    }

    public void onRoseClick (View view) {

        roseIntent = new Intent(this, Rose.class);
        startActivity(roseIntent);
    }

    public void onProposeClick (View view) {

        proposeIntent = new Intent(this, Propose.class);
        startActivity(proposeIntent);
    }

    public void onChocolateClick (View view) {

        chocolateIntent = new Intent(this, Chocolate.class);
        startActivity(chocolateIntent);
    }

    public void onTeddyClick (View view) {

        teddyIntent = new Intent(this, Teddy.class);
        startActivity(teddyIntent);
    }

    public void onPromiseClick (View view) {

        promiseIntent = new Intent(this, Promise.class);
        startActivity(promiseIntent);
    }

    public void onHugClick (View view) {

        hugIntent = new Intent(this, Hug.class);
        startActivity(hugIntent);
    }

    public void onKissClick (View view) {

        kissIntent = new Intent(this, Kiss.class);
        startActivity(kissIntent);
    }

    public void onValentineClick (View view) {

        valentineIntent = new Intent(this, Valentine.class);
        startActivity(valentineIntent);
    }
}
