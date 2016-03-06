package com.zhangyihao.cloudmusic;

import android.support.v7.app.AppCompatActivity;

import com.google.android.libraries.cast.companionlibrary.cast.VideoCastManager;
import com.zhangyihao.cloudmusic.util.LogHelper;

/**
 * Created by zhangyihao on 2016-3-3.
 */
public abstract class ActionBarCastActivity extends AppCompatActivity {
    private final static String TAG = LogHelper.makeLogTag(ActionBarCastActivity.class);

    private static final int DELAY_MILLIS = 1000;
    private VideoCastManager mVideoCastManager;
}