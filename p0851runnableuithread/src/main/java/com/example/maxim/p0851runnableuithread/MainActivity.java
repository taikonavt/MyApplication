package com.example.maxim.p0851runnableuithread;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    TextView tvInfo;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    tvInfo.postDelayed(runn3, 2000);
                    tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    Runnable runn1 = new Runnable() {
        public void run() {
            tvInfo.setText("runn1");
        }
    };

    Runnable runn2 = new Runnable() {
        public void run() {
            tvInfo.setText("runn2");
        }
    };

    Runnable runn3 = new Runnable() {
        public void run() {
            tvInfo.setText("runn3");
        }
    };
}