package com.example.maxim.p0491simpleadaptercustom1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    //имена атрибутов для Map
    final String ATTRBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_VALUE = "value";
    final String ATTRIBUT_NAME_IMAGE = "image";

    //картинки для отображения динамики
    final int positive = android.R.drawable.arrow_up_float;
    final int negative = android.R.drawable.arrow_down_float;

    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //массив данных
        int[] values = {8, 4,-3, 2, -5, 0, 3, -6, 1, -1};

        //упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                values.length);
        Map<String, Object> m;
        int img = 0;
        for (int i = 0; i < values.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRBUTE_NAME_TEXT, "Day " + (i + 1));
            m.put(ATTRIBUTE_NAME_VALUE, values[i]);
            if (values[i] == 0) img = 0;
            else
                img = (values[i] > 0) ? positive : negative;
            m.put(ATTRIBUT_NAME_IMAGE, img);
            data.add(m);
        }

        //массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE, ATTRIBUT_NAME_IMAGE};

        //массив ID View компонентов, в которые будут вставлять данные
        int[] to = {R.id.tvText, R.id.tvValue, R.id.ivImg};

        //создаем адаптер
        MySimpleAdapter sAdapter = new MySimpleAdapter(this, data, R.layout.item, from, to);

        //определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }


    class MySimpleAdapter extends SimpleAdapter {

        public MySimpleAdapter (Context context,
                                List<? extends Map<String, ?>> data, int resourse,
                                String[] from, int[] to) {
            super(context, data, resourse, from, to);
        }

        @Override
        public void setViewText (TextView v, String text) {
            //метод супер-класса, который вставляет текст
            super.setViewText(v, text);
            //если нужный нам TexView то разрисовываем
            if (v.getId() == R.id.tvValue) {
                int i = Integer.parseInt(text);
                if (i < 0) v.setTextColor(Color.RED);
                else
                    if (i > 0) v.setTextColor(Color.GREEN);
            }
        }

        @Override
        public void setViewImage (ImageView v, int value) {
            //метод суперкласса
            super.setViewImage(v, value);
            //разрисовываем ImageView
            if (value == negative) v.setBackgroundColor(Color.BLUE);
            else
                if (value == positive) v.setBackgroundColor(Color.GREEN);
        }
    }


}
