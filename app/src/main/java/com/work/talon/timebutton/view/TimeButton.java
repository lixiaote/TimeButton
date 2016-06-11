package com.work.talon.timebutton.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by talon on 2016/5/30.
 */
public class TimeButton extends Button {
    private long lenght = 60 * 1000;// 倒计时长度，默认60秒
    private String textafter = "秒后重新获取";
    private String textbefore = "点击获取验证码";

    private Timer t;
    private TimerTask tt;
    private long time;

    public TimeButton(Context context) {
        super(context);
    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void start() {
        initTimer();
        this.setText(time / 1000 + textafter);
        this.setEnabled(false);
        t.schedule(tt, 0, 1000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TimeButton.this.setText(time / 1000 + textafter);
            time -= 1000;
            if (time < 0) {
                TimeButton.this.setEnabled(true);
                TimeButton.this.setText(textbefore);
                clearTimer();
            }
        }
    };

    private void clearTimer() {
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null) {
            t.cancel();
            t = null;
        }
    }

    private void initTimer() {
        time = lenght;
        t = new Timer();
        tt = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x01);
            }
        };
    }

    /**
     * 设置计时时候显示的文本
     */
    public TimeButton setTextAfter(String after) {
        this.textafter = after;
        return this;
    }

    /**
     * 设置点击之前的文本
     */
    public TimeButton setTextBefore(String before) {
        this.textbefore = before;
        this.setText(textbefore);
        return this;
    }

    /**
     * 设置到计时长度
     *
     * @param lenght 时间 默认毫秒
     * @return
     */
    public TimeButton setLenght(long lenght) {
        this.lenght = lenght;
        return this;
    }
}
