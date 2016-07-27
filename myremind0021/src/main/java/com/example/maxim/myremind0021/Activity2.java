package com.example.maxim.myremind0021;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by maxim on 27.01.16.
 */
public class Activity2 extends Activity {

    Button btn2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        btn2 = (Button) findViewById(R.id.btn2);
    }

    public void onClick (View view) {
        finish();
    }
}
