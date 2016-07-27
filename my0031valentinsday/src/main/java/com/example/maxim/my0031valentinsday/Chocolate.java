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
public class Chocolate extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView chocolateLV;
    Intent chocolateTextIntent;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chocolate_activity);

        chocolateLV = (ListView) findViewById(R.id.chocolateLV);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.chocolateList,
                R.layout.whitetext_list_item
        );

        chocolateLV.setAdapter(adapter);

        chocolateLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

        chocolateTextIntent = new Intent(this, ChocolateText.class);

        chocolateTextIntent.putExtra("pos", position);

        startActivity(chocolateTextIntent);
    }
}
