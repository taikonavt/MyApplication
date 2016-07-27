package com.example.maxim.my0051wallpaper;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button wpBtn;
    ImageView mainImg;
    LinearLayout hllayout;
    ImageButton imageBtn;
    HorizontalScrollView hSV;

    String TAG = "myLogs";
    String[] fileNames; // список файлов в папке
    String wpFullFileName; //полное имя файла для wallpaper
    String folderPath = "/mnt/sdcard/Pictures/";
    int mSize; //размер imageBtn и imageSample;
    int screenWidth, screenHeight; //размер экрана телефона


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wpBtn = (Button) findViewById(R.id.wpBtn);
        mainImg = (ImageView) findViewById(R.id.wpView);
        hllayout = (LinearLayout) findViewById(R.id.hllayout);
        hSV = (HorizontalScrollView) findViewById(R.id.hSV);
        mSize = this.getResources().getDimensionPixelSize(R.dimen.image_button_size);
        getScreenWidth(); //получаем размеры экрана
    }


    @Override
    protected void onStart() {
        super.onStart();
        //получаем список файлов в папке и из них создаем ImageButtons
        fileNames = getFilesNames(folderPath);
        createImageButtons(fileNames);
    }


    //обрабатываем нажатие на ImageButton
    public void onClick(View v) {

        //получаем путь к выбраному файлу
        int i = v.getId();
        wpFullFileName = fileNames[i];

        //отображаем выбраную картинку на большом экране
        Bitmap bitmap = Utils.
                decodeSampledBitmapFromResource(wpFullFileName, screenHeight, screenWidth);

        mainImg.setImageBitmap(bitmap);

        //получаем ширину выбраного imageButton
        int viewWidth = v.getWidth();

        //совмещаем середину выбранного imageButton и середину дисплея
        hSV.smoothScrollTo(v.getLeft() + (viewWidth / 2) - (screenWidth / 2), 0);
    }


    //обрабатываетм нажатие на Set Wallpaper Button - устанавливаем обои на рабочий стол
    public void onSWBtnClick(View v) {

        WallpaperManager wm = WallpaperManager.getInstance(getApplicationContext());
        try {

            Bitmap bitmap = BitmapFactory.decodeFile(wpFullFileName);

            wm.setBitmap(bitmap);

            //если картинка мала для экрана, то выводим сообщение об этом
            if (bitmap.getWidth() < wm.getDesiredMinimumWidth()
                    || bitmap.getHeight() < wm.getDesiredMinimumHeight()) {
                Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    //обрабатываем нажатие на кнопку Open Folder - запарашиваем папку из которой прочитать картинки
    public void onOpenFolderBtnClick(View v) {

        Intent intent = new Intent(this, Browser.class);

        startActivityForResult(intent, 1);
    }


    //обрабатываем результат выбора папки
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            folderPath = data.getStringExtra("path");
        }
        else
            folderPath = "/mnt/sdcard/Pictures/";


    }


    //получаем список картинок из папки
    protected String[] getFilesNames (String path) {

        //получаем список файлов с расширениями .jpg и .png
        File folder = new File(path);
        String[] fileList = folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".jpg") || filename.endsWith(".png");
            }
        });

        //добавляем полный путь к списку
        for (int i = 0; i < fileList.length; i++) {
            fileList[i] = path + fileList[i];
        }

        return fileList;
    }


    //создаем образцы картинок внизу экрана
    protected void createImageButtons (String[] fileList) {

        hllayout.removeAllViews();

        if (fileList.length > 0) {
            //создаем пустой ImageView вначале, чтобы ImageButton'ы
            // могли сдвигаться вправо до середины
            ImageView imageView = new ImageView(this);
            imageView.setAdjustViewBounds(true);
            imageView.setMaxHeight(mSize);
            imageView.setMaxWidth(screenWidth / 2 - 50);
            imageView.setImageBitmap(
                    Bitmap.createBitmap(screenWidth / 2 - 50, mSize, Bitmap.Config.ALPHA_8));
            imageView.setImageAlpha(0);
            hllayout.addView(imageView);
        }

        //создаем ImageButton'ы присваиваем им картинку и обработчик
        for (int i = 0; i < fileList.length; i++) {

            imageBtn= new ImageButton(this);
            imageBtn.setOnClickListener(this);
            imageBtn.setId(i);
            imageBtn.setPadding(2, 2, 2, 2);

            Bitmap bitmap;
            bitmap = Utils.decodeSampledBitmapFromResource(fileList[i], mSize, mSize);

            imageBtn.setImageBitmap(bitmap);
            imageBtn.setAdjustViewBounds(true); //без вкл параметра картинка появляется в реальном размере
            imageBtn.setMaxHeight(mSize);
            imageBtn.setMaxWidth(mSize);
            imageBtn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            hllayout.addView(imageBtn);
        }

        if (fileList.length > 0) {
            //создаем пустой ImageView вконце,
            // чтобы ImageButton'ы могли сдвигаться влево до середины
            ImageView imageView = new ImageView(this);
            imageView.setAdjustViewBounds(true);
            imageView.setMaxHeight(mSize);
            imageView.setMaxWidth(screenWidth / 2 - 50);
            imageView.setImageBitmap(
                    Bitmap.createBitmap(screenWidth / 2 - 50, mSize, Bitmap.Config.ALPHA_8));
            imageView.setImageAlpha(0);
            hllayout.addView(imageView);
        }
    }


    void getScreenWidth() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth =  size.x;
        screenHeight = size.y;
    }
}
