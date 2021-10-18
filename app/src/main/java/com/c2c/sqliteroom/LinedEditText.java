package com.c2c.sqliteroom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class LinedEditText extends AppCompatEditText {

    private Rect mRect;
    private Paint mPaint;

    public LinedEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = ((View)this.getParent()).getHeight();
        int lineHeight = getLineHeight();
        int numOfLines = height/lineHeight;

        Rect rect = mRect;
        Paint paint = mPaint;

        int baseline = getLineBounds(0, rect);

        for(int i=0;i<numOfLines; i++){
             
        }

        super.onDraw(canvas);
    }
}
