package com.random.customdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.random.customdemo.Coustom.TaiJi;

public class TaiJiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TaiJi taiJi = new TaiJi(this);
        setContentView(taiJi);
        final Handler handler = new Handler() {
            private float degrees = 0;

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (degrees >= 360) {
                    degrees = 0;
                }
                taiJi.setRotate(degrees += 2);
                this.sendEmptyMessageDelayed(0, 1);
            }
        };

        handler.sendEmptyMessageDelayed(0, 100);
    }
}
