package com.example.maxim.my0051wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * Created by maxim on 05.05.16.
 */
public class Browser extends AppCompatActivity {

    ListView listView;
    String[] fileList;
    File folder;
    Button btnOK;
    Button btnCancel;
    String TAG = "myLogs";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        listView = (ListView) findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        btnOK = (Button) findViewById(R.id.buttonOK);
        btnCancel = (Button) findViewById(R.id.buttonCancel);

        folder = new File("/");
        //folder = Environment.getExternalStorageDirectory();

        folderList(folder);

        //обрабатываем нажатие на пункт списка
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CharSequence charSequence = ((TextView) view).getText();
                Log.d(TAG,charSequence.toString());

                String absoluteFolderName = folder.getAbsolutePath() + "/" + fileList[position];
                Log.d(TAG, absoluteFolderName);
                folder = new File(absoluteFolderName);

                folderList(folder);
            }
        });

        //обработка кнопки ОК - возвращаем путь к папке и результат ОК
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("path", folder.getAbsolutePath() + "/");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //обработка кнопки CANCEL - возвращаем путь к папке и результат отмены
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("path", folder.getAbsolutePath());
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }


    //получаем список папок в папке и выводим в список
    public void folderList(File directory) {

        String[] fileListTemp = null;

        while(fileListTemp == null) {

            fileListTemp = directory.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    String listItem = dir.getAbsolutePath() + "/" + filename;
                    File fileListItem = new File(listItem);
                    return fileListItem.isDirectory();
                }
            });

            if (fileListTemp == null) {
                Toast.makeText(this, R.string.toast_browser_message, Toast.LENGTH_LONG).show();
                folder = new File("/");
                directory = new File("/");
            }
            else {

                fileList = new String[fileListTemp.length + 1];
                fileList[0] = "..";

                for (int i = 0; i < fileListTemp.length; i++) {

                    fileList[i + 1] = fileListTemp[i];
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, fileList);

        listView.setAdapter(adapter);
    }
}
