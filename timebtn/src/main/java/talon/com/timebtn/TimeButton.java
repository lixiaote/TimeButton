package talon.com.timebtn;

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
    private long lenght = 60 * 1000;// Countdown length, default 60 seconds
    private String textafter = "Seconds to re-obtain";
    private String textbefore = "Click Get Verification Code";

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
     * Sets the text to be displayed at the time of the timer
     */
    public TimeButton setTextAfter(String after) {
        this.textafter = after;
        return this;
    }

    /**
     * Sets the text before clicking
     */
    public TimeButton setTextBefore(String before) {
        this.textbefore = before;
        this.setText(textbefore);
        return this;
    }

    /**
     * Set to the length of the timer
     *
     * @param lenght Time defaults to milliseconds
     * @return
     */
    public TimeButton setLenght(long lenght) {
        this.lenght = lenght;
        return this;
    }
}
