package com.zhangyihao.cloudmusic.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyihao on 16/1/10.
 */
public class GuidePageAdapter extends PagerAdapter {

    private List<View> views;

    public GuidePageAdapter(List<View> views) {
        this.views = views;
        if(this.views==null) {
            this.views = new ArrayList<View>();
        }
    }

    @Override
    public int getCount() {
        return this.views.size();
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position), 0);
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
