package com.example.maxim.my0011firsttable;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    DBHelper dbHelper;
    EditText editText;
    TextView textView;
    int pos;
    String LOG_TAG = "myLogs";
    final int DB_VERSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "------- New execution -------");

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        pos = 0;

        dbHelper = new DBHelper(this);
    }

    public void onclick (View view) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        switch (view.getId()) {
            case (R.id.btnIn):
                ContentValues cv = new ContentValues();
                String data = editText.getText().toString();
                cv.put("data", data);
                cv.put("num", Integer.parseInt(data));
                database.insert("mytable", null, cv);
                break;
            case (R.id.btnNext):
                pos++;
                setTextView(database);
                break;
            case (R.id.btnPrev):
                pos--;
                if (pos < 1) pos = 1;
                setTextView(database);
                break;
            case R.id.btnSort:
                sortList(database);
                break;
        }

        dbHelper.close();
    }

    void setTextView (SQLiteDatabase database) {

        Cursor c = database.query("mytable", null, "id = " + String.valueOf(pos), null, null, null, null);

        if (c.getCount() == 0) {
            textView.setText("Table is ended");
            c.close();
            pos--;
            return;
        }
        else {
            c.moveToFirst();
            String str = c.getString(c.getColumnIndex("data"));
            textView.setText(str);
            c.close();
        }
    }

    void sortList(SQLiteDatabase database) {

        String [] strArr = {"40000"};
        String [] strColumns = {"data"};
        Cursor c = database.query("mytable", null, null, null, null, null, "data", null);

        if (c != null) {
            c.moveToFirst();
            String str;

            do {
                str = "";
                for (String cn : c.getColumnNames()) {
                    str = str.concat(c.getString(c.getColumnIndex(cn)) + "; ");
                }
                Log.d(LOG_TAG, str);
            } while (c.moveToNext());
            c.close();
        }
        else
            Log.d(LOG_TAG, "Cursor is null");
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "myDB", null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {

            database.execSQL("create table mytable (" +
            "id integer primary key autoincrement, data text, num integer);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

            database.beginTransaction();
            try {

                if (oldVersion == 1 && newVersion == 2) {

                    database.execSQL("create table tableTmp (" +
                            "id integer primary key autoincrement, data text, num integer);");

                    Cursor c = database.query("mytable", null, null, null, null, null, null, null);

                    c.moveToFirst();

                    do {
                        int idTmp = c.getInt(c.getColumnIndex("id"));
                        String dataTmp = c.getString(c.getColumnIndex("data"));

                        ContentValues cv = new ContentValues();
                        cv.put("id", idTmp);
                        cv.put("data", dataTmp);

                        cv.put("num", Integer.parseInt(dataTmp));

                        database.insert("tableTmp", null, cv);
                    } while (c.moveToNext());

                    database.execSQL("drop table mytable");

                    database.execSQL("create table mytable (" +
                            "id integer primary key autoincrement, data text, num integer);");

                    c = database.query("tableTmp", null, null, null, null, null, null, null);

                    c.moveToFirst();

                    do {
                        int idTmp = c.getInt(c.getColumnIndex("id"));
                        String dataTmp = c.getString(c.getColumnIndex("data"));
                        int numTmp = c.getInt(c.getColumnIndex("num"));

                        ContentValues cv = new ContentValues();
                        cv.put("id", idTmp);
                        cv.put("data", dataTmp);
                        cv.put("num", numTmp);

                        database.insert("mytable", null, cv);
                    } while (c.moveToNext());

                    database.execSQL("drop table tableTmp");

                    c.close();
                }
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
        }
    }
}
