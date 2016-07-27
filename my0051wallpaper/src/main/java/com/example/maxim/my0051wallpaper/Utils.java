package com.example.maxim.my0051wallpaper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by maxim on 05.05.16.
 */
public class Utils {

    public static String TAG = "myLogs";


    //получаем картинки нужного нам размера
    public static Bitmap decodeSampledBitmapFromResource(String path, int reqHeight, int reqWidth) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateInSampleSize(options, reqHeight, reqWidth);

        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        return BitmapFactory.decodeFile(path, options);
    }


    //вычисляем коэффициент для сокращения размера
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqHeight, int reqWidth) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height/2;
            final int halfWidth = width/2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
