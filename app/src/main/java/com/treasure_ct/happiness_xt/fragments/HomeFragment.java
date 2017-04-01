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
        viewPager.setCurrentItem(0);
        //联动
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        return view;
    }
    private void initFindId(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.home_tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.home_viewPager);
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("新闻"));
        tabLayout.addTab(tabLayout.newTab().setText("段子"));
        tabLayout.addTab(tabLayout.newTab().setText("视频"));
        tabLayout.addTab(tabLayout.newTab().setText("美食"));
        tabLayout.addTab(tabLayout.newTab().setText("音乐"));
    }

    private void initViewPager() {
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeNewsFragment());
        list.add(new HomeJokerFragment());
        list.add(new HomeVideoFragment());
        list.add(new HomeCateFragment());
        list.add(new HomeMusicFragment());
        HomeFragmentPagerAdapter pagerAdapter = new HomeFragmentPagerAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
