package com.zhangyihao.cloudmusic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zhangyihao.cloudmusic.R;
import com.zhangyihao.cloudmusic.util.MyPreferences;

public class BaseActivity extends Activity {

    private int guidResourceId = 0; //引导页图片id

    @Override
    protected void onStart() {
        super.onStart();
        addGuidImage(); //添加引导页
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addGuidImage() {
        View view = getWindow().getDecorView().findViewById(R.id.guid_content_view);
        if (view == null) {
            return;
        }

        if (MyPreferences.activityIsGuid(this, this.getClass().getName())) {
            return;
        }
        ViewParent viewParent = view.getParent();
        if (viewParent instanceof FrameLayout) {
            final FrameLayout frameLayout = (FrameLayout) viewParent;
            if (guidResourceId != 0) {
                final ImageView guidImage = new ImageView(this);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                guidImage.setLayoutParams(layoutParams);
                guidImage.setScaleType(ImageView.ScaleType.FIT_XY);
                guidImage.setImageResource(guidResourceId);
                guidImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        frameLayout.removeView(guidImage);
                        MyPreferences.setIsGuid(getApplicationContext(), BaseActivity.this.getClass().getName());
                    }
                });
                frameLayout.addView(guidImage);
            }
        }

        return;
    }

    protected void setGuidResourceId(int resourceId) {
        this.guidResourceId = resourceId;
    }
}
