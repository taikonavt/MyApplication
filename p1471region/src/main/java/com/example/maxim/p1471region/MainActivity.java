package com.example.maxim.p1471region;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.os.Bundle;
import android.util.Log;
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
        Rect rect3;
        Region region;
        Region region1;
        RegionIterator iterator;
        Path path;
        String TAG = "myLogs";

        Region.Op op = Region.Op.UNION;


        public DrawView(Context context) {
            super(context);
            p = new Paint();
            p.setStrokeWidth(3);

            // прямоугольники
            rect1 = new Rect(200,200,400,400);
            rect2 = new Rect(300,300,500,500);

            // создание региона
            region = new Region();
            region.set(rect1);
            region.op(rect2, op);

            region1 = new Region();
            region1.set(rect1);

            // создание path из региона
            path = region.getBoundaryPath();

            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            // контуры прямоугольников
            p.setStyle(Paint.Style.STROKE);
            p.setColor(Color.BLACK);
            canvas.drawRect(rect1, p);
            canvas.drawRect(rect2, p);

            // path
            p.setStyle(Paint.Style.FILL);
            p.setColor(Color.BLUE);
            canvas.drawPath(path, p);

            iterator = new RegionIterator(region);
            rect3 = new Rect();

            while (iterator.next(rect3)) {

                Log.d(TAG, rect3.toShortString());

            }


        }
    }
}