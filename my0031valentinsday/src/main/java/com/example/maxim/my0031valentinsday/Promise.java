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
public class Promise extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView promiseLV;
    Intent promiseTextIntent;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promise_activity);

        promiseLV = (ListView) findViewById(R.id.promiseLV);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.promiseList,
                R.layout.bluetext_list_item
        );

        promiseLV.setAdapter(adapter);

        promiseLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

        promiseTextIntent = new Intent(this, PromiseText.class);

        promiseTextIntent.putExtra("pos", position);

        startActivity(promiseTextIntent);
    }
}
