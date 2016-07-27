package com.example.maxim.p1481canvasclip;

import android.graphics.Region;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
        Rect rect1;
        Rect rect2;
        Region.Op op = Region.Op.UNION;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(3);
            rect1 = new Rect(180, 220, 340, 380);
            rect2 = new Rect(280, 320, 440, 480);

            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            // сетка
            p.setColor(Color.BLUE);
            drawGrid(canvas);

            // красные прямоугольники
            p.setColor(Color.RED);
            canvas.drawRect(rect1, p);
            canvas.drawRect(rect2, p);

            // смещение
            canvas.translate(600, 0);

            // задание clip-области
            canvas.clipRect(rect1);
            canvas.clipRect(rect2, op);

            // сетка
            p.setColor(Color.BLUE);
            drawGrid(canvas);

        }

        private void drawGrid(Canvas canvas) {
            for (int i = 25; i < 400; i += 25) {
                canvas.drawLine(100 + i, 100, 100 + i, 600, p);
            }
            for (int i = 25; i < 500; i += 25) {
                canvas.drawLine(100, 100 + i, 500, 100 + i, p);
            }
        }

    }

}