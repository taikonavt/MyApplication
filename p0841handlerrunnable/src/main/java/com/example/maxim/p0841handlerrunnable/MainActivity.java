package com.example.maxim.p0841handlerrunnable;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    ProgressBar pbCount;
    TextView tvInfo;
    CheckBox chbInfo;
    int cnt;

    final String LOG_TAG = "myLogs";
    final int max = 100;

    Handler h;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h = new Handler();

        pbCount = (ProgressBar) findViewById(R.id.pbCount);
        pbCount.setMax(max);
        pbCount.setProgress(0);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        chbInfo = (CheckBox) findViewById(R.id.chbInfo);
        chbInfo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    tvInfo.setVisibility(View.VISIBLE);
                    // показываем информацию
                    h.post(showInfo);
                } else {
                    tvInfo.setVisibility(View.GONE);
                    // отменяем показ информации
                    h.removeCallbacks(showInfo);
                }
            }
        });

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    for (cnt = 1; cnt < max; cnt++) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        // обновляем ProgressBar
                        h.post(updateProgress);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }

    // обновление ProgressBar
    Runnable updateProgress = new Runnable() {
        public void run() {
            pbCount.setProgress(cnt);
        }
    };

    // показ информации
    Runnable showInfo = new Runnable() {
        public void run() {
            Log.d(LOG_TAG, "showInfo");
            tvInfo.setText("Count = " + cnt);
            // планирует сам себя через 1000 мсек
            h.postDelayed(showInfo, 1000);
        }
    };
}