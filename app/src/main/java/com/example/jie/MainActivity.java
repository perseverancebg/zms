package com.example.jie;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.jie.Adapter.FragmentAdapter;
import com.example.jie.Fragment.Fragment_Campus;
import com.example.jie.Fragment.Fragment_Find;
import com.example.jie.Fragment.Fragment_Home;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragments;
    private BottomNavigationView bottom_Navigation;
    private ViewPager Fragment_ViewPage;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment_Home());
        fragments.add(new Fragment_Campus());
        fragments.add(new Fragment_Find());

        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        Fragment_ViewPage.setAdapter(fragmentAdapter);
    }

    private void initView() {
        Fragment_ViewPage = findViewById(R.id.Fragment_ViewPage);
        bottom_Navigation = findViewById(R.id.bottom_Navigation);
        drawerLayout = findViewById(R.id.drawerLayout);
        //设置将头部的ActionBar设置为Toolbar
        toolbar = findViewById(R.id.navigation_toolbar);
        setSupportActionBar(toolbar);
         // 设置阴影部分颜色为透明
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //和侧滑抽屉相关联
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                View content = drawerLayout.getChildAt(0); //获得主界面View
                if (drawerView.getTag().equals("left")) {  //判断是否是左菜单
                    int offset = (int) (drawerView.getWidth() * slideOffset);
                    content.setTranslationX(offset);
                }
            }
        };
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);



        //底部菜单导航的点击事件
        bottom_Navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                switch (menuId) {
                    case R.id.bottom_Navigation_home:
                        Fragment_ViewPage.setCurrentItem(0);
                        break;

                    case R.id.bottom_Navigation_campus:
                        Fragment_ViewPage.setCurrentItem(1);
                        break;

                    case R.id.bottom_Navigation_find:
                        Fragment_ViewPage.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        Fragment_ViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bottom_Navigation.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


}
