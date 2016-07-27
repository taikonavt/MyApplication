package com.example.maxim.p1611bitmapcache;

import android.support.v7.app.AppCompatActivity;
import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

public class MainActivity extends Activity {

    ListView mLvImages;
    public static String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLvImages = (ListView) findViewById(R.id.lvImages);


        File dir = new File(Environment.getExternalStorageDirectory(), "Download/L0161");

        File[] filesArray = dir.listFiles();

        if (filesArray != null) {
            ImageAapter adapter = new ImageAapter(this, filesArray);
            mLvImages.setAdapter(adapter);
        }

    }


    static class ImageAapter extends ArrayAdapter<File> {

        LayoutInflater mInflater;
        Picasso mPicasso;

        public ImageAapter(Context context, File[] objects) {
            super(context, R.layout.list_item, objects);
            mInflater = LayoutInflater.from(context);
            mPicasso = Picasso.with(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = mInflater.inflate(R.layout.list_item, parent, false);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            mPicasso.load(getItem(position)).resizeDimen(R.dimen.image_size, R.dimen.image_size). centerInside().into(imageView);
            return view;
        }

    }
}