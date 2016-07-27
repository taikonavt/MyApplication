package com.example.maxim.my0031valentinsday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by maxim on 05.02.16.
 */
public class Rose extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView roseLV;
    Intent roseTextIntent;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rose_activity);

        roseLV = (ListView) findViewById(R.id.roseLV);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.roseList,
                R.layout.bluetext_list_item
        );

        roseLV.setAdapter(adapter);

        roseLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

        roseTextIntent = new Intent(this, RoseText.class);

        roseTextIntent.putExtra("pos", position);

        startActivity(roseTextIntent);
    }
}
