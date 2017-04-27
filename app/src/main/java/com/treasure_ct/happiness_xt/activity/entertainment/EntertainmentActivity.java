package com.treasure_ct.happiness_xt.activity.entertainment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.HomeFragmentPagerAdapter;
import com.treasure_ct.happiness_xt.fragments.HomeNewsFashionFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsFunFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsMoviesFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsSportsFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsTechFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsTopFragment;
import com.treasure_ct.happiness_xt.fragments.HomeWeChatSelectFragment;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        initFindId();

        tabLayout.addOnTabSelectedListener(this);
        initTabLayout();
        initViewPager();
        //联动
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(0,false);
    }
    private void initFindId() {
        tabLayout = (TabLayout) findViewById(R.id.home_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.home_viewPager);
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
        HomeFragmentPagerAdapter pagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), list);
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
