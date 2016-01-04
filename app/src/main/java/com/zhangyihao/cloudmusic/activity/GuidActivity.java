package com.zhangyihao.cloudmusic.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhangyihao.cloudmusic.R;

import java.util.List;

public class GuidActivity extends BaseActivity {

    private ViewPager mViewpager;
    private List<View> mPageViews;
    private ImageView mImageView;
    private ImageView[] mImageViews;
    private ViewGroup mViewPicGroup;
    private ViewGroup mViewPointGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);

        LayoutInflater inflater = getLayoutInflater();
        this.mPageViews.add(inflater.inflate(R.layout.activity_guid_viewpage1, null));
        this.mPageViews.add(inflater.inflate(R.layout.activity_guid_viewpage2, null));

        this.mImageViews = new ImageView[this.mPageViews.size()];
        this.mViewPicGroup = (ViewGroup) inflater.inflate(R.layout.activity_guid, null);

        this.mViewPointGroup = (ViewGroup) this.mViewPicGroup.findViewById(R.id.activity_guid_viewgroup);
        this.mViewpager = (ViewPager) this.mViewPicGroup.findViewById(R.id.activity_guid_viewpager);

        for(int i=0; i<this.mPageViews.size(); i++) {
            mImageView = new ImageView(GuidActivity.this);
            mImageView.setLayoutParams(new LayoutParams(20, 20));
        }


    }

}
