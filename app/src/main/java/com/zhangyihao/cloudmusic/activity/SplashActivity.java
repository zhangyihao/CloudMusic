package com.zhangyihao.cloudmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zhangyihao.cloudmusic.MainActivity;
import com.zhangyihao.cloudmusic.R;
import com.zhangyihao.cloudmusic.util.MyPreferences;



public class SplashActivity extends BaseActivity {

    private final static int SWITCH_MAINACTIVITY = 1000;
    private final static int SWITCH_GUIDACTIITY = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean isGuid = MyPreferences.activityIsGuid(getApplicationContext(), this.getClass().getName());

        if(isGuid) {
            mHandler.sendEmptyMessage(SWITCH_MAINACTIVITY);
        } else {
            mHandler.sendEmptyMessage(SWITCH_GUIDACTIITY);
        }
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SWITCH_MAINACTIVITY:
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                    break;
                case SWITCH_GUIDACTIITY:
                    Intent guidIntent = new Intent();
                    guidIntent.setClass(SplashActivity.this, GuidActivity.class);
                    SplashActivity.this.startActivity(guidIntent);
                    SplashActivity.this.finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
