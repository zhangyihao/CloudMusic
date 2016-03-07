package com.zhangyihao.cloudmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zhangyihao.cloudmusic.MainActivity;
import com.zhangyihao.cloudmusic.R;
import com.zhangyihao.cloudmusic.util.PrefUtils;


public class SplashActivity extends BaseActivity {

    private final static int SWITCH_MAIN_ACTIVITY = 1000;
    private final static int SWITCH_GUIDE_ACTIVITY = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean isGuid = PrefUtils.activityIsGuid(getApplicationContext(), this.getClass().getName());

        if(isGuid) {
            mHandler.sendEmptyMessage(SWITCH_MAIN_ACTIVITY);
        } else {
            mHandler.sendEmptyMessage(SWITCH_GUIDE_ACTIVITY);
        }
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SWITCH_MAIN_ACTIVITY:
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                    break;
                case SWITCH_GUIDE_ACTIVITY:
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
