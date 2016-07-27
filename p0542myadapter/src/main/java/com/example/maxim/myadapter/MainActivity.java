package com.example.maxim.myadapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends Activity {

    ListView list1;

    String str1 = "Box";
    String str2 = "1000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(2);

        Map<String, Object> m1, m2;

        m1 = new HashMap<String, Object>();
        m2 = new HashMap<String, Object>();

        m1.put("1", str1);
        m1.put("2", str2);
        m2.put("1", str1 + "x");
        m2.put("2", str2 + "0");

        data.add(m1);
        data.add(m2);


        list1 = (ListView) findViewById(R.id.list1);

        String[] from = {"1", "2"};
        int[] to = {R.id.tv1, R.id.tv2};

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        list1.setAdapter(sAdapter);
    }
}
