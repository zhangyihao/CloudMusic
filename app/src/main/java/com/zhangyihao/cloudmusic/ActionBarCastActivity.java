package com.zhangyihao.cloudmusic;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.libraries.cast.companionlibrary.cast.VideoCastManager;
import com.google.android.libraries.cast.companionlibrary.cast.callbacks.VideoCastConsumerImpl;
import com.zhangyihao.cloudmusic.util.LogHelper;

/**
 * Created by zhangyihao on 2016-3-3.
 */
public abstract class ActionBarCastActivity extends AppCompatActivity {
    private final static String TAG = LogHelper.makeLogTag(ActionBarCastActivity.class);

    private static final int DELAY_MILLIS = 1000;

    private VideoCastManager mVideoCastManager;
    private MenuItem mMediaRouteMenuItem;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mToolbarInitalized;

    private int mItemToOpenWhenDrawerCloses = -1;

    private final VideoCastConsumerImpl mCastConsumer = new VideoCastConsumerImpl() {
        @Override
        public void onConnected() {
            super.onConnected();
        }

        @Override
        public void onDisconnected() {
            super.onDisconnected();
        }

        @Override
        public void onConnectionSuspended(int cause) {
            super.onConnectionSuspended(cause);
        }
    };

}