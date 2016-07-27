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
public class Hug extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView hugLV;
    Intent hugTextIntent;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hug_activity);

        hugLV = (ListView) findViewById(R.id.hugLV);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.hugList,
                R.layout.bluetext_list_item
        );

        hugLV.setAdapter(adapter);

        hugLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

        hugTextIntent = new Intent(this, HugText.class);

        hugTextIntent.putExtra("pos", position);

        startActivity(hugTextIntent);
    }
}
