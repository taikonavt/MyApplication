package com.example.maxim.p1161mngtasks1;

import android.content.Intent;
import android.view.View;

public class ActivityC extends MainActivity {
    public void onClick(View v) {
        startActivity(new Intent(this, ActivityD.class));
    }
}