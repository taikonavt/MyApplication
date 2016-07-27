package com.example.maxim.p0911asynctaskrotate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    MyTask mt;
    TextView tv;
    static String TAG = "myLogs";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "create MainActivity: " + this.hashCode());

        tv = (TextView) findViewById(R.id.tv);

        mt = (MyTask) getLastNonConfigurationInstance();
        if (mt == null) {
            mt = new MyTask();
            mt.execute();
        }
        //передаём в MyTask ссылку на текущее MainActivity
        mt.link(this);

        Log.d(TAG, "create MyTask: " + mt.hashCode());
    }

    static class MyTask extends AsyncTask<String, Integer, Void> {

        MainActivity activity;

        //получаем ссылку на MainActivity
        void link(MainActivity act) {
            activity = act;
        }

        //обнуляем ссылку
        void unLink() {
            activity = null;
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                for (int i = 1; i <= 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d(TAG, "i = " + i
                            + ", MyTask: " + this.hashCode()
                            + ", MainActivity: " + activity.hashCode());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            do {
                if (activity != null)
                    activity.tv.setText("i = " + values[0]);
            } while (activity == null);

        }
    }

    public Object onRetainNonConfigurationInstance() {
        mt.unLink();
        return mt;
    }
}