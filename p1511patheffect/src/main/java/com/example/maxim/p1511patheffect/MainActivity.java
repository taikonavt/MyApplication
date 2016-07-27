package com.example.maxim.p1511patheffect;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
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

        Path path;
        Paint p1;
        Paint p2;
        Paint p3;

        public DrawView(Context context) {
            super(context);
            path = new Path();
            path.rLineTo(100, 300);
            path.rLineTo(100, -100);
            path.rLineTo(100, 300);

            p1 = new Paint(Paint.ANTI_ALIAS_FLAG);
            p1.setStyle(Paint.Style.STROKE);
            p1.setStrokeWidth(3);

            p2 = new Paint(p1);
            p2.setColor(Color.GREEN);
            p2.setPathEffect(new CornerPathEffect(25));

            p3 = new Paint(p1);
            p3.setColor(Color.BLUE);
            p3.setPathEffect(new CornerPathEffect(50));

        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(80, 102, 204, 255);

            canvas.translate(100, 100);
            canvas.drawPath(path, p1);

            canvas.translate(250, 0);
            canvas.drawPath(path, p2);

            canvas.translate(250, 0);
            canvas.drawPath(path, p3);

        }

    }

}