package com.example.maxim.my0031valentinsday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by maxim on 17.03.16.
 */
public class Teddy extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView teddyLV;
    Intent teddyTextIntent;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teddy_activity);

        teddyLV = (ListView) findViewById(R.id.teddyLV);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.teddyList,
                R.layout.bluetext_list_item
        );

        teddyLV.setAdapter(adapter);

        teddyLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

        teddyTextIntent = new Intent(this, TeddyText.class);

        teddyTextIntent.putExtra("pos", position);

        startActivity(teddyTextIntent);
    }
}
