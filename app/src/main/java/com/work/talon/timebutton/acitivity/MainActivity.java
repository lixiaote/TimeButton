package com.work.talon.timebutton.acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.work.talon.timebutton.R;

import talon.com.timebtn.TimeButton;


public class MainActivity extends AppCompatActivity {
    private TimeButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (TimeButton) findViewById(R.id.timeButton);
        btn.setTextBefore("点击获取验证码").setTextAfter("秒后重新获取").setLenght(60 * 1000);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.start();
            }
        });
    }
}
