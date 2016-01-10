package com.zhangyihao.cloudmusic.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhangyihao.cloudmusic.R;
import com.zhangyihao.cloudmusic.adapter.GuidePageAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuidActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewpager;
    private GuidePageAdapter mViewpagerAdapter;
    private List<View> mPageViews;

    private static final int[] pics = {R.mipmap.start_i0, R.mipmap.start_i1};
    private ImageView[] mDotsImageViews;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mPageViews = new ArrayList<View>();

        for (int i = 0; i < this.pics.length; i++) {
            ImageView vi = new ImageView(GuidActivity.this);
            vi.setLayoutParams(layoutParams);
            vi.setImageResource(pics[i]);
            mPageViews.add(vi);
        }
        mViewpager = (ViewPager) findViewById(R.id.activity_guid_viewpager);
        mViewpagerAdapter = new GuidePageAdapter(mPageViews);
        mViewpager.setAdapter(mViewpagerAdapter);
        mViewpager.setOnPageChangeListener(this);

        initDots();
    }

    private void initDots() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_guid_dots);
        mDotsImageViews = new ImageView[pics.length];
        for(int i=0; i<pics.length; i++) {
            mDotsImageViews[i] = (ImageView) linearLayout.getChildAt(i);
            mDotsImageViews[i].setEnabled(true);
            mDotsImageViews[i].setOnClickListener(this);
            mDotsImageViews[i].setTag(i);
        }

        mCurrentIndex = 0;
        mDotsImageViews[mCurrentIndex].setEnabled(false);
    }

    private void setCurrentView(int position) {
        if(position<0 || position>pics.length) {
            return;
        }
        mViewpager.setCurrentItem(position);

    }

    private void setCurrentDot(int position) {
        if(position<0 || position>pics.length-1 || mCurrentIndex==position) {
            return;
        }
        mDotsImageViews[position].setEnabled(false);
        mDotsImageViews[mCurrentIndex].setEnabled(true);
        mCurrentIndex = position;
    }

    @Override
    public void onPageSelected(int position) {
        setCurrentDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        setCurrentView(position);
        setCurrentDot(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
}
