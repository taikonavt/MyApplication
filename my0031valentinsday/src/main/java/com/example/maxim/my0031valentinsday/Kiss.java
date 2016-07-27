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
public class Kiss extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView kissLV;
    Intent kissTextIntent;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kiss_activity);

        kissLV = (ListView) findViewById(R.id.kissLV);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.kissList,
                R.layout.bluetext_list_item
        );

        kissLV.setAdapter(adapter);

        kissLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

        kissTextIntent = new Intent(this, KissText.class);

        kissTextIntent.putExtra("pos", position);

        startActivity(kissTextIntent);
    }
}
