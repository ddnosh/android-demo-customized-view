package com.androidwind.demo.customizedview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidwind.demo.customizedview.view.MyLinearLayout;

public class MainActivity extends AppCompatActivity {

    private MyLinearLayout ml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ml = findViewById(R.id.ml);
        ml.setClickCallBack(new MyLinearLayout.ClickCallBack() {
            @Override
            public void onBack() {
                MainActivity.this.finish();
            }
        });
    }
}
