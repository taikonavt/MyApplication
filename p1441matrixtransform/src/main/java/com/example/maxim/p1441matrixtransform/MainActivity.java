package com.example.maxim.p1441matrixtransform;

import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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
        RectF rectfDst;
        Path path;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);
            p.setStyle(Paint.Style.STROKE);
            rectf = new RectF(100, 100, 200, 200);
            rectfDst = new RectF();
            matrix = new Matrix();
            path = new Path();

            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            // прямоугольник
            path.reset();
            path.addRect(rectf, Path.Direction.CW);
            p.setColor(Color.BLACK);
            canvas.drawPath(path, p);

            // поворот, размер, пермещение
            matrix.setRotate(45, 150, 150);
            matrix.postScale(1.2f, 0.8f, 150, 150);
            matrix.postTranslate(200, 0);
            path.transform(matrix);

            // итоговая фигура зеленым цветом
            p.setColor(Color.GREEN);
            canvas.drawPath(path, p);

            // границы, полученные от изначального прямоугольника
            matrix.mapRect(rectfDst, rectf);
            p.setColor(Color.BLUE);
            canvas.drawRect(rectfDst, p);

        }
    }
}