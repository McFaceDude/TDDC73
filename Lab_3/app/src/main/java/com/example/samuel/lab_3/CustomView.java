package com.example.samuel.lab_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by samuel on 12/17/17.
 */

public class CustomView extends View {
    String text;
    Paint paint;

    public CustomView(Context context) {
        super(context);
        setWillNotDraw(false);
        paint = new Paint();

    }
    public void setName(String name){
        text = name;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        paint.setTextSize(35);
        canvas.drawText(text,2,50,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(800,80);
    }
}
