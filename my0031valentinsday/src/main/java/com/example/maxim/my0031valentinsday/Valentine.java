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
public class Valentine extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView valentineLV;
    Intent valentineTextIntent;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valentine_activity);

        valentineLV = (ListView) findViewById(R.id.valentineLV);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.valentineList,
                R.layout.bluetext_list_item
        );

        valentineLV.setAdapter(adapter);

        valentineLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

        valentineTextIntent = new Intent(this, ValentineText.class);

        valentineTextIntent.putExtra("pos", position);

        startActivity(valentineTextIntent);
    }
}
