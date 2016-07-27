package com.example.maxim.p1181customwidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ConfigActivity extends Activity {

    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;

    final String LOG_TAG = "myLogs";

    public final static String WIDGET_PREF = "widget_pref";
    public final static String WIDGET_TEXT = "widget_text_";
    public final static String WIDGET_COLOR = "widget_color_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate config");

        // извлекаем ID конфигурируемого виджета
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        // и проверяем его корректность
        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        // формируем intent ответа
        resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID);

        // отрицательный ответ
        setResult(RESULT_CANCELED, resultValue);

        setContentView(R.layout.config);
    }


    public void onClick(View v) {
        int selRBColor = ((RadioGroup) findViewById(R.id.rgColor))
                .getCheckedRadioButtonId();
        int color = Color.RED;
        switch (selRBColor) {
            case R.id.radioRed:
                color = Color.parseColor("#66ff0000");
                break;
            case R.id.radioGreen:
                color = Color.parseColor("#6600ff00");
                break;
            case R.id.radioBlue:
                color = Color.parseColor("#660000ff");
                break;
        }
        EditText etText = (EditText) findViewById(R.id.etText);

        // Записываем значения с экрана в Preferences
        SharedPreferences sp = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(WIDGET_TEXT + widgetID, etText.getText()
                .toString());
        editor.putInt(WIDGET_COLOR + widgetID, color);
        editor.commit();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        MyWidget.updateWidget(this, appWidgetManager, sp, widgetID);

        // положительный ответ
        setResult(RESULT_OK, resultValue);

        Log.d(LOG_TAG, "finish config " + widgetID);
        finish();
    }
}