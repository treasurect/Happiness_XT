package com.treasure_ct.happiness_xt.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.HomeFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initFindId(view);

        tabLayout.addOnTabSelectedListener(this);
        initTabLayout();
        initViewPager();
        //联动
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(0,false);
        return view;
    }
    private void initFindId(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.home_tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.home_viewPager);
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("头条"));
        tabLayout.addTab(tabLayout.newTab().setText("微信精选"));
        tabLayout.addTab(tabLayout.newTab().setText("FUN"));
        tabLayout.addTab(tabLayout.newTab().setText("科技"));
        tabLayout.addTab(tabLayout.newTab().setText("时尚"));
        tabLayout.addTab(tabLayout.newTab().setText("电影"));
        tabLayout.addTab(tabLayout.newTab().setText("体育"));
    }

    private void initViewPager() {
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeNewsTopFragment());
        list.add(new HomeWeChatSelectFragment());
        list.add(new HomeNewsFunFragment());
        list.add(new HomeNewsTechFragment());
        list.add(new HomeNewsFashionFragment());
        list.add(new HomeNewsMoviesFragment());
        list.add(new HomeNewsSportsFragment());
        HomeFragmentPagerAdapter pagerAdapter = new HomeFragmentPagerAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition(),false);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
