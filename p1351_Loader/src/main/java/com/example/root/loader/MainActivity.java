package com.example.root.loader;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.ContentObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements LoaderCallbacks<String> {

    final String LOG_TAG = "myLogs";
    static final int LOADER_TIME_ID = 1;

    TextView tvTime;
    RadioGroup rgTimeFormat;
    static int lastCheckedId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime = (TextView) findViewById(R.id.tvTime);
        rgTimeFormat = (RadioGroup) findViewById(R.id.rgTimeFormat);

        Bundle bndl = new Bundle();
        bndl.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
        // При помощи getLoaderManager() получаем объект LoaderManager.
        // При помощи initLoader(int id, Bundle args, LoaderCallbacks<D> callback) создаем и получаем
        // объект Loader. На вход передаем id для идентификации Loader'а (можем работать с несколькими),
        // Bundle - передаем информацию в Loader, callback - объект, реализующий колбэк-интерфейс
        // LoaderCallbacks. Он будет использоваться для взаимодействия с лоадером.
        getLoaderManager().initLoader(LOADER_TIME_ID, bndl, this);
        lastCheckedId = rgTimeFormat.getCheckedRadioButtonId();
    }

    // реализуем интерфейс LoaderCallbacks. метод вызывается, когда требуется создать новый лоадер,
    // например, в тот момент, когда мы выше вызываем метод initLoader. На вход получает ID
    // требуемого лоадера и Bundle с данными. Т.е. те самые объекты, что мы передавали в initLoader.
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;
        if (id == LOADER_TIME_ID) {
            loader = new TimeLoader(this, args);
            Log.d(LOG_TAG, "onCreateLoader: " + loader.hashCode());
        }
        return loader;
    }

    // реализуем интерфейс LoaderCallbacks. Метод срабатывает, когда лоадер закончил работу и вернул
    // результат. На вход приходит сам лоадер и результат его работы.
    @Override
    public void onLoadFinished(Loader<String> loader, String result) {
        Log.d(LOG_TAG, "onLoadFinished for loader " + loader.hashCode()
                + ", result = " + result);
        tvTime.setText(result);
    }

    // реализуем интерфейс LoaderCallbacks. Метод срабатывает, когда LoaderManager собрался
    // уничтожать лоадер. На вход получает лоадер.
    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(LOG_TAG, "onLoaderReset for loader " + loader.hashCode());
    }

    public void getTimeClick(View v) {
        Loader<String> loader;

        int id = rgTimeFormat.getCheckedRadioButtonId();
        if (id == lastCheckedId) {
            loader = getLoaderManager().getLoader(LOADER_TIME_ID);
        } else {
            Bundle bndl = new Bundle();
            bndl.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
            loader = getLoaderManager().restartLoader(LOADER_TIME_ID, bndl,
                    this);
            lastCheckedId = id;
        }
        loader.forceLoad();
    }

    String getTimeFormat() {
        String result = TimeLoader.TIME_FORMAT_SHORT;
        switch (rgTimeFormat.getCheckedRadioButtonId()) {
            case R.id.rdShort:
                result = TimeLoader.TIME_FORMAT_SHORT;
                break;
            case R.id.rdLong:
                result = TimeLoader.TIME_FORMAT_LONG;
                break;
        }
        return result;
    }

    // нажатие на кнопку создает объект ContentObserver который управляет оповещением о том что данные
    // изменились и нужно их обновить
    public void observerClick(View v) {
        Log.d(LOG_TAG, "observerClick");
        Loader<String> loader = getLoaderManager().getLoader(LOADER_TIME_ID);
        final ContentObserver observer = loader.new ForceLoadContentObserver(); // создаем объект
        v.postDelayed(new Runnable() {
            @Override
            public void run() {
                observer.dispatchChange(false, null); // оповещаем о изменении
            }
        }, 5000);
    }

}