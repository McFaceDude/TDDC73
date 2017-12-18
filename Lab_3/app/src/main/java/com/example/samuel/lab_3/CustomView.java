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
    Paint paint = new Paint();



    String text;
    public CustomView(Context context, String text) {
        super(context);
        this.text = text;
    }
    public void setName(String name){
        text = name;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("onDraw, text" + text);
        canvas.drawColor(Color.BLACK);
        canvas.drawText(text,1,1,paint);

        //Does not work, check custom on draw with paint and canvas

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
