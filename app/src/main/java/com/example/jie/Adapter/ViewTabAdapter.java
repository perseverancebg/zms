package com.example.jie.Adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewTabAdapter extends PagerAdapter {
    private List<View> viewList;
    private List<String> TitleList;
    public ViewTabAdapter(List<View> viewList,List<String> TitleList) {
        this.TitleList = TitleList;
        this.viewList = viewList;
        if (TitleList == null){
            TitleList = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       container.addView(viewList.get(position));
       return viewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TitleList.size() > 0 ? TitleList.get(position):"";
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
