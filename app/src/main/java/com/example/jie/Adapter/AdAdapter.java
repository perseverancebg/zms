package com.example.jie.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

public class AdAdapter extends ViewPager {
    private List<Image> images;

    public AdAdapter(@NonNull Context context) {
        super(context);
    }
}
