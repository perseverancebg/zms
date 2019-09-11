package com.example.jie.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;;

import com.example.jie.Adapter.ViewTabAdapter;
import com.example.jie.Adapter.tab_home_one_Adapter;
import com.example.jie.Adapter.tab_home_second_Adapter;
import com.example.jie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Fragment_Home extends Fragment {
    //实现Tab标签所需
    private TabLayout tabLayout;
    private ViewPager tab_viewPager;
    private List<String> TitleList;
    //加载Tab所需要的布局
    private List<View> viewList;

    //添加Tab子项所需数据所需
    private RecyclerView linear, Grid;
    private SwipeRefreshLayout linear_refresh;

    private static final String TAG = "Fragment_Home";

    //添加LinearLayout的数据
    int[] pics = {
            R.drawable.apple,
            R.drawable.banana,
            R.drawable.cherry,
            R.drawable.grape,
            R.drawable.mango,
            R.drawable.orange,
            R.drawable.pear,
            R.drawable.pineapple,
            R.drawable.strawberry,
            R.drawable.watermelon,
            R.drawable.nav_icon,

    };
    String[] names = {
            "北极熊",
            "犀牛",
            "花豹",
            "白马",
            "小鹦鹉",
            "袋鼠",
            "狐狸",
            "小猫咪",
            "哈巴狗",
            "蜥蜴",
            "大熊猫",
            "蚂蚁",
    };
    List<Map<String, Object>> linearData = new ArrayList<>();
    List<Map<String, Object>> GridData = new ArrayList<>();
    List<Map<String, Object>> staggeredData = new ArrayList<>();

    public void addData() {
        Map<String, Object> map = null;
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int n = random.nextInt(pics.length);
            map = new HashMap<>();
            map.put("pic", pics[n]);
            map.put("name", names[n]);
            map.put("desc", "这就是：" + names[n]);
            linearData.add(map);
        }
    }

    public void addGridData() {
        Map<String, Object> map = null;
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int n = random.nextInt(pics.length);
            map = new HashMap<>();
            map.put("pic", pics[n]);
            map.put("name", names[n]);
            GridData.add(map);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //使用TabLayout和viewPager实现tab，且找出id
        tabLayout = view.findViewById(R.id.tabLayout);
        tab_viewPager = view.findViewById(R.id.tab_viewPager);

        //加载tab标签布局
        View viewOne = LayoutInflater.from(view.getContext()).inflate(R.layout.tab_home_one, null);
        View viewTwo = LayoutInflater.from(view.getContext()).inflate(R.layout.tab_home_second, null);
        View viewThree = LayoutInflater.from(view.getContext()).inflate(R.layout.tab_home_three, null);

        //找出第一个Tab标签，且要实现数据显示
        linear = viewOne.findViewById(R.id.linear_Recycler);
        Grid = viewTwo.findViewById(R.id.Grid_recycler);
        //找出刷新组件
        linear_refresh = viewOne.findViewById(R.id.linear_refresh);

        //记得加载数据，不然数据无法显示
        addData();
        addGridData();
        //Accessing hidden field Landroid/view/View;->mAccessibilityDelegate:
        //Landroid/view/View$AccessibilityDelegate; (light greylist, reflection)


        //添加三个tab标签
        viewList = new ArrayList<>();
        viewList.add(viewOne);
        viewList.add(viewTwo);
        viewList.add(viewThree);
        //添加tab标签的名字
        TitleList = new ArrayList<>();
        TitleList.add("氢气");
        TitleList.add("石油");
        TitleList.add("花旗");
        //实现tab标签的适配器
        ViewTabAdapter tabAdapter = new ViewTabAdapter(viewList, TitleList);
        //实现tab标签的名字
        for (String title : TitleList) {
            tabLayout.addTab(tabLayout.newTab().setText(title));
        }
        //将TabLayout和ViewPager连接
        tabLayout.setupWithViewPager(tab_viewPager);
        tab_viewPager.setAdapter(tabAdapter);

        //线性布局的设置和适配器
        final tab_home_one_Adapter tab_home_one_adapter = new tab_home_one_Adapter(getContext(), linearData);
        linear.setLayoutManager(new LinearLayoutManager(getContext()));
        linear.setAdapter(tab_home_one_adapter);
        //网络布局的设置和适配器
        Grid.setLayoutManager(new GridLayoutManager(getContext(), 2));
        Grid.setAdapter(new tab_home_second_Adapter(getContext(), GridData));

        //设置刷新监听
        linear_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //清除数据
                linearData.clear();
                //加载数据
                addData();
                //适配器来设置要完成的
                tab_home_one_adapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (linear_refresh.isRefreshing()) {
                            linear_refresh.setRefreshing(false);
                        }
                    }
                }, 3000);
            }
        });
        //设置刷新图标的颜色
        linear_refresh.setColorSchemeColors(Color.BLUE, Color.GRAY, Color.RED);
        //设置触发图标距离
        linear_refresh.setDistanceToTriggerSync(400);
        //设置刷新图标的下拉距离
        linear_refresh.setSlingshotDistance(500);


    }
}
