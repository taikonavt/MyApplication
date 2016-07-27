package com.example.maxim.p0701saveinstancestate;

import android.util.Log;

/**
 * Created by maxim on 22.10.15.
 */
public class MyObj {

    int i;
    String s;

    MyObj(int _i, String _s) {
        i = _i;
        s = _s;
    }

    public void showme() {
        Log.d("myLogs", "MyObj: i=" + i + "s= " + s);
    }
}
