package com.treasure_ct.happiness_xt.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.HomeFragmentPagerAdapter;
import com.treasure_ct.happiness_xt.fragments.BaseFragment;
import com.treasure_ct.happiness_xt.fragments.HomeEmbarrassFragment;
import com.treasure_ct.happiness_xt.fragments.HomeGossipFragment;
import com.treasure_ct.happiness_xt.fragments.HomeJokerFragment;
import com.treasure_ct.happiness_xt.fragments.HomeJokerImageFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsFunFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsTopFragment;
import com.treasure_ct.happiness_xt.fragments.HomeWeChatSelectFragment;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_entertainment,container,false);
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
        tabLayout.addTab(tabLayout.newTab().setText("段子"));
        tabLayout.addTab(tabLayout.newTab().setText("搞笑图片"));
        tabLayout.addTab(tabLayout.newTab().setText("糗事"));
        tabLayout.addTab(tabLayout.newTab().setText("八卦"));
        tabLayout.addTab(tabLayout.newTab().setText("精选"));
        tabLayout.addTab(tabLayout.newTab().setText("头条"));
        tabLayout.addTab(tabLayout.newTab().setText("FUN"));
    }

    private void initViewPager() {
        List<BaseFragment> list = new ArrayList<>();
        list.add(new HomeJokerFragment());
        list.add(new HomeJokerImageFragment());
        list.add(new HomeEmbarrassFragment());
        list.add(new HomeGossipFragment());
        list.add(new HomeWeChatSelectFragment());
        list.add(new HomeNewsTopFragment());
        list.add(new HomeNewsFunFragment());
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

    @Override
    protected void lazyLoad() {

    }
}
