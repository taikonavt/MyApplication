package com.example.maxim.p1161mngtasks1;

import android.content.Intent;
import android.view.View;

public class ActivityA extends MainActivity {
    public void onClick(View v) {
        startActivity(new Intent(this, ActivityB.class));
    }
}