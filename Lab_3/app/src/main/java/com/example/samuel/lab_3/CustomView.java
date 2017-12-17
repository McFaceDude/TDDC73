package com.example.samuel.lab_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import org.json.JSONArray;

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

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(text,1,1,paint);
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
