package com.example.maxim.p0891asynctaskcancel;

import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    MyTask mt;
    TextView tvInfo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mt = new MyTask();
                mt.execute();
                break;
            case R.id.btnCancel:
                cancelTask();
                break;
            default:
                break;
        }
    }

    private void cancelTask() {
        if (mt == null) return;
        Log.d(LOG_TAG, "cancel result: " + mt.cancel(true));
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
            Log.d(LOG_TAG, "Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    //if (isCancelled()) return null;
                    Log.d(LOG_TAG, "isCancelled: " + isCancelled());
                }
            } catch (InterruptedException e) {
                Log.d(LOG_TAG, "Interrupted");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("End");
            Log.d(LOG_TAG, "End");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            tvInfo.setText("Cancel");
            Log.d(LOG_TAG, "Cancel");

        }
    }
}