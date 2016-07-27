package com.example.maxim.my0032semple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Button roseBtn;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roseBtn = (Button) findViewById(R.id.roseBtn);

        String roseStr = "Rose Day SMS \n" + getDays() + " Days To Go";
        roseBtn.setText(roseStr);
    }

    int getDays() {

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int dayOfYear = localCalendar.get(Calendar.DAY_OF_YEAR);

        int roseDay = 38;

        int remainDays = (dayOfYear > roseDay ?
                0 : (roseDay - dayOfYear));

        return remainDays;
    }
}
