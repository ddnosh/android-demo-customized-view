package com.androidwind.demo.customizedview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidwind.demo.customizedview.R;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class MyLinearLayout extends LinearLayout implements View.OnClickListener {

    private Button mBtnBack, mBtnFunction;
    private ClickCallBack mClickCallBack;

    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_my, this);
        mBtnBack = view.findViewById(R.id.btn_left);
        mBtnBack.setOnClickListener(this);
        mBtnFunction = view.findViewById(R.id.btn_right);
        mBtnFunction.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_left) {
            if (mClickCallBack != null) {
                mClickCallBack.onBack();
            }
        } else if (v.getId() == R.id.btn_right) {
            if (mClickCallBack != null) {
                mClickCallBack.onFunction();
            }
        }
    }

    public void setClickCallBack(ClickCallBack clickCallBack) {
        mClickCallBack = clickCallBack;
    }

    public interface ClickCallBack {
        void onBack();
        void onFunction();
    }

    private int x, y;
    private int down_x, down_y;
    private int left, top;
    private int childIndex = -1;
    private View child;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (childIndex != -1) {
            View v = getChildAt(childIndex);
            if (v != null)
                v.layout(x, y, x + v.getMeasuredWidth(), y + v.getMeasuredHeight());
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View v = getChildAt(i);
            if (v != null) {
                if (i == 0) {
                    v.layout(x, y, x + v.getMeasuredWidth(), y + v.getMeasuredHeight());
                } else if (i == 1) {
                    v.layout(x, y + 350, x + v.getMeasuredWidth(), y + v.getMeasuredHeight() + 350);
                }
            }
        }
        // super.onLayout(changed, l, t, r, b);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_x = (int) event.getX();
                down_y = (int) event.getY();
                if (childIndex == -1) {
                    for (int i = 0; i < getChildCount(); i++) {
                        child = getChildAt(i);
                        if ((child.getLeft() < down_x && down_x < child.getRight())
                                && (child.getTop() < down_y && down_y < child
                                .getBottom())) {
                            childIndex = i;
                            break;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (childIndex != -1) {
                    x = left + (int) event.getX() - down_x;
                    y = top + (int) event.getY() - down_y;

                    this.requestLayout();
                }
                break;
            case MotionEvent.ACTION_UP:
                childIndex = -1;
                left = child.getLeft();
                top = child.getTop();
                break;

        }

        return true;
    }
}
