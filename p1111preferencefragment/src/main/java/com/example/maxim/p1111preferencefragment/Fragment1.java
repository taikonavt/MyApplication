package com.example.maxim.p1111preferencefragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

public class Fragment1 extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("myLogs", "Fragment1 onCreate");
        addPreferencesFromResource(R.xml.pref1);
    }
}