package com.androidwind.demo.customizedview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidwind.demo.customizedview.R;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyView extends View implements View.OnClickListener {

    private Rect mBounds;
    private Paint mPaint;
    private String mText;
    private int mTextBackgroundColor;
    private int mTextColor;
    private int mTextSize;
    private int count;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyView, 0, 0);
        mText = a.getString(R.styleable.MyView_iText);
        mTextBackgroundColor = a.getColor(R.styleable.MyView_iTextBackgroundColor, Color.BLACK);
        mTextColor = a.getColor(R.styleable.MyView_iTextColor, Color.BLUE);
        mTextSize = (int) a.getDimension(R.styleable.MyView_iTextSize, 16);
        a.recycle();  //回收

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mBounds = new Rect();

        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(mTextBackgroundColor);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mText, 0, mText.length(), mBounds); //获取文字的宽和高
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(mText, getWidth() / 2 - textWidth / 2, getHeight() / 2 + textHeight / 2, mPaint);
    }

    @Override
    public void onClick(View v) {
        mText = String.format("点击了%d次", ++count);
        invalidate();
    }
}
