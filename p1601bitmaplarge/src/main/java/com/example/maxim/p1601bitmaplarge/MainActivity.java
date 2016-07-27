package com.example.maxim.p1601bitmaplarge;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends Activity {

    ImageView mImageView;
    String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.imageView);
        logMemory();
        readImage();
        logMemory();
    }

    private void readImage() {
        int px = getResources().getDimensionPixelSize(R.dimen.image_size);
        File file = new File(Environment.
                getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"map.jpg");
        Bitmap bitmap = decodeSampledBitmapFromResource(file.getAbsolutePath(), px, px);
        Log.d(TAG, String.format("Required size = %s, bitmap size = %sx%s, byteCount = %s",
                px, bitmap.getWidth(), bitmap.getHeight(), bitmap.getByteCount()));
        mImageView.setImageBitmap(bitmap);
    }

    private void logMemory() {
        Log.i(TAG, String.format("Total memory = %s",
                (int) (Runtime.getRuntime().totalMemory() / 1024)));
    }

    public static Bitmap decodeSampledBitmapFromResource(String path,
                                                         int reqWidth, int reqHeight) {

        // Читаем с inJustDecodeBounds=true для определения размеров
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Вычисляем inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Читаем с использованием inSampleSize коэффициента
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(path, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Реальные размеры изображения
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Вычисляем наибольший inSampleSize, который будет кратным двум
            // и оставит полученные размеры больше, чем требуемые
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}