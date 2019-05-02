package com.androidwind.demo.customizedview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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

    private Button mBtnBack;
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
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_left) {
            if (mClickCallBack != null) {
                mClickCallBack.onBack();
            }
        }
    }

    public void setClickCallBack(ClickCallBack clickCallBack) {
        mClickCallBack = clickCallBack;
    }

    public interface ClickCallBack {
        void onBack();
    }
}
