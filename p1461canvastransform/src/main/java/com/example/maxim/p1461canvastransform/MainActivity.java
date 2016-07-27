package com.example.maxim.p1461canvastransform;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
        Matrix matrix;
        RectF rectf;
        Path path;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);
            rectf = new RectF(100, 100, 200, 200);
            matrix = new Matrix();
            path = new Path();

            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            // квадрат
            p.setColor(Color.BLACK);
            canvas.drawRect(rectf, p);

            // квадрат на канве с преобразованиями
            canvas.rotate(30);
            canvas.translate(500, 000);
            p.setColor(Color.GREEN);
            canvas.drawRect(rectf, p);

        }
    }
}