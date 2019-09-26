package com.androidwind.demo.customizedview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.androidwind.demo.customizedview.R;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class DragView extends LinearLayout {

    private static final String TAG = "MyLinearLayout";

    private float X;
    private float Y;
    private View mDragView;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setBackgroundColor(0xFFFFB6C1);
        mDragView = new View(context);
        mDragView.setLayoutParams(new LayoutParams(120, 120));
        mDragView.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_android));
        mDragView.setVisibility(View.INVISIBLE);
        addView(mDragView);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        X = ev.getX();
        Y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent ACTION_DOWN");
                mDragView.layout((int)X - 60, (int)Y - 60, (int)X + 60, (int)Y + 60);
                mDragView.setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent ACTION_MOVE x:" + X + " Y:" + Y);
                mDragView.layout((int)X - 60, (int)Y - 60, (int)X + 60, (int)Y + 60);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent ACTION_UP");
                mDragView.setVisibility(View.INVISIBLE);
                break;
        }
        return true;
    }

}
