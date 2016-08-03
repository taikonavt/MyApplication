package com.example.root.loader;

import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

// создаем класс Loader объект которого получаем в MainActivity
// <String> - указывает, что будет возвращить Loader после своей работы
public class TimeLoader extends Loader<String> {

    final String LOG_TAG = "myLogs";
    final int PAUSE = 10;

    public final static String ARGS_TIME_FORMAT = "time_format";
    public final static String TIME_FORMAT_SHORT = "h:mm:ss a";
    public final static String TIME_FORMAT_LONG = "yyyy.MM.dd G 'at' HH:mm:ss";

    GetTimeTask getTimeTask;
    String format;

    public TimeLoader(Context context, Bundle args) {
        super(context);
        Log.d(LOG_TAG, hashCode() + " create TimeLoader");
        if (args != null)
            format = args.getString(ARGS_TIME_FORMAT);
        if (TextUtils.isEmpty(format))
            format = TIME_FORMAT_SHORT;
    }

    // вызывается при старте (onStart) Activity или фрагмента, к которому будет привязан Loader.
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(LOG_TAG, hashCode() + " onStartLoading");
        if (takeContentChanged()) // мы читаем (и одновременно сбрасываем) метку методом
                // takeContentChanged. Если метка говорит, что данные были изменены (true), то запускаем работу.
            forceLoad();
    }

    // вызывается при остановке (onStop) Activity или фрагмента, к которому будет привязан Loader.
    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d(LOG_TAG, hashCode() + " onStopLoading");
    }

    // в этом методе кодим работу лоадера.
    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.d(LOG_TAG, hashCode() + " onForceLoad");
        if (getTimeTask != null)
            getTimeTask.cancel(true);
        getTimeTask = new GetTimeTask();
        getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, format);
    }

    // метод означающий, что лоадер становится неактивным.
    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.d(LOG_TAG, hashCode() + " onAbandon");
    }

    // означает уничтожение лоадера, вызывается при закрытии (onDestroy) Activity или фрагмента,
    // к которому будет привязан Loader. Не вызывается, если onDestroy был вызван, например при смене ориентации.
    @Override
    protected void onReset() {
        super.onReset();
        Log.d(LOG_TAG, hashCode() + " onReset");
    }

    void getResultFromTask(String result) {
        // метод Loader'a который оповещает слушателя, подключенного к лоадеру, что работа окончена
        // и передает ему данные.
        deliverResult(result);
    }

    class GetTimeTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Log.d(LOG_TAG, TimeLoader.this.hashCode() + " doInBackground");
            try {
                TimeUnit.SECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                return null;
            }

            SimpleDateFormat sdf = new SimpleDateFormat(params[0],
                    Locale.getDefault());
            return sdf.format(new Date());
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(LOG_TAG, TimeLoader.this.hashCode() + " onPostExecute "
                    + result);
            getResultFromTask(result);
        }

    }
}