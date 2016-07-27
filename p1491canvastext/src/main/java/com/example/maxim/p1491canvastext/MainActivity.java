package com.example.maxim.p1491canvastext;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
        String text = "Test width text";
        int fontSize = 60;
        float y = 80;

        public DrawView(Context context) {
            super(context);
            p = new Paint(Paint.ANTI_ALIAS_FLAG);
            p.setTextSize(fontSize);
            p.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            // обычный текст
            canvas.translate(50, y);
            canvas.drawText(text, 0, 0, p);

            // моноширинный
            canvas.translate(0, y);
            p.setTypeface(Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL));
            canvas.drawText(text, 0, 0, p);

            // с засечками
            canvas.translate(0, y);
            p.setTypeface(Typeface.create(Typeface.SERIF, Typeface.NORMAL));
            canvas.drawText(text, 0, 0, p);

            // обычный жирный
            canvas.translate(0, y);
            p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText(text, 0, 0, p);

            // обычный жирный курсивный
            canvas.translate(0, y);
            p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));
            canvas.drawText(text, 0, 0, p);

            // обычный курсивный
            canvas.translate(0, y);
            p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
            canvas.drawText(text, 0, 0, p);

        }
    }
}