package com.zhangyihao.cloudmusic;

import android.app.MediaRouteButton;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.media.MediaRouter;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.libraries.cast.companionlibrary.cast.VideoCastManager;
import com.google.android.libraries.cast.companionlibrary.cast.callbacks.VideoCastConsumerImpl;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.zhangyihao.cloudmusic.util.LogHelper;
import com.zhangyihao.cloudmusic.util.PrefUtils;

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
        public void onFailed(int resourceId, int statusCode) {
            LogHelper.d(TAG, "onFailed ", resourceId, " status ", statusCode);
        }

        @Override
        public void onConnectionSuspended(int cause) {
            LogHelper.d(TAG, "onConnectionSuspended() was called with cause: ", cause);
        }

        @Override
        public void onConnectivityRecovered() {
        }

        @Override
        public void onCastDeviceDetected(final MediaRouter.RouteInfo info) {
            // FTU stands for First Time Use:
            if (!PrefUtils.isFtuShown(ActionBarCastActivity.this)) {
                // If user is seeing the cast button for the first time, we will
                // show an overlay that explains what that button means.
                PrefUtils.setFtuShown(ActionBarCastActivity.this, true);

                LogHelper.d(TAG, "Route is visible: ", info);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (mMediaRouteMenuItem.isVisible()) {
                            LogHelper.d(TAG, "Cast Icon is visible: ", info.getName());
                            showFtu();
                        }
                    }
                }, DELAY_MILLIS);
            }
        }
    };

    /**
     * Shows the Cast First Time User experience to the user (an overlay that explains what is the Cast icon)、
     * 第一次加载时向用户展示转换按钮
     */
    private void showFtu() {
        Menu menu = mToolbar.getMenu();
        View view = menu.findItem(R.id.media_route_menu_item).getActionView();
        if(view !=null && view instanceof MediaRouteButton) {
            new ShowcaseView.Builder(this)
                    .setTarget(new ViewTarget(view))
                    .setContentTitle("Touch to cast")
                    .hideOnTouchOutside()
                    .build();

        }
    }

}