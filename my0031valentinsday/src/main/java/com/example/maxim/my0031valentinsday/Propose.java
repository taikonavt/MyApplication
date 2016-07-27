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
public class Propose extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView proposeLV;
    Intent proposeTextIntent;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propose_activity);

        proposeLV = (ListView) findViewById(R.id.proposeLV);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.proposeList,
                R.layout.bluetext_list_item
        );

        proposeLV.setAdapter(adapter);

        proposeLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

        proposeTextIntent = new Intent(this, ProposeText.class);

        proposeTextIntent.putExtra("pos", position);

        startActivity(proposeTextIntent);
    }
}
