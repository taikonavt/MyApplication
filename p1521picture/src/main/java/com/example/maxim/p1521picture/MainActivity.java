package com.example.maxim.p1521picture;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {

        Paint p;
        Path path;
        Picture picture;

        public DrawView(Context context) {
            super(context);


            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            picture = new Picture();

            Canvas canvas = picture.beginRecording(300, 300);

            p.setColor(Color.GREEN);
            canvas.drawCircle(150, 100, 80, p);

            p.setColor(Color.BLUE);
            canvas.drawRect(20, 70, 150, 200, p);

            p.setColor(Color.RED);
            path = new Path();
            path.moveTo(170,80);
            path.lineTo(240, 210);
            path.lineTo(100, 210);
            path.close();
            canvas.drawPath(path, p);

            picture.endRecording();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            canvas.drawPicture(picture);

        }

    }

}