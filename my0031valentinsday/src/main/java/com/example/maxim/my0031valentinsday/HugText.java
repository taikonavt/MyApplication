package com.example.maxim.my0031valentinsday;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by maxim on 17.03.16.
 */
public class HugText extends AppCompatActivity {

    TextView hugTV;
    String[] sms;
    int pos;
    String TAG = "myLogs";

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hugtext_activity);

        hugTV = (TextView) findViewById(R.id.hugTV);

        Intent intent = getIntent();

        pos = intent.getIntExtra("pos", 20);

        sms = getResources().getStringArray(R.array.hugList);

        hugTV.setText(sms[pos]);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case (R.id.share):

                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, sms[pos]);

                startActivity(Intent.createChooser(intent, getString(R.string.share_title)));
                break;
            case (R.id.copy):

                ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.GINGERBREAD_MR1) {

                    try {
                        cm.setText(sms[pos]);

                        Toast.makeText(this, "Text was copied", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    try {
                        ClipData clip = ClipData.newPlainText("simple text", sms[pos]);

                        cm.setPrimaryClip(clip);

                        Toast.makeText(this, "Text was copied", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
