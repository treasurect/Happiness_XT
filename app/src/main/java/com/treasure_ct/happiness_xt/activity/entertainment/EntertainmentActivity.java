package com.treasure_ct.happiness_xt.activity.entertainment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.HomeFragmentPagerAdapter;
import com.treasure_ct.happiness_xt.fragments.BaseFragment;
import com.treasure_ct.happiness_xt.fragments.HomeJokerFragment;
import com.treasure_ct.happiness_xt.fragments.HomeJokerImageFragment;
import com.treasure_ct.happiness_xt.fragments.HomeJokerVideoFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsFashionFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsFunFragment;
import com.treasure_ct.happiness_xt.fragments.HomeNewsTopFragment;
import com.treasure_ct.happiness_xt.fragments.HomeWeChatSelectFragment;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);
        Tools.setTranslucentStatus(this);
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
        tabLayout.addTab(tabLayout.newTab().setText("段子"));
        tabLayout.addTab(tabLayout.newTab().setText("微信精选"));
        tabLayout.addTab(tabLayout.newTab().setText("搞笑图片"));
        tabLayout.addTab(tabLayout.newTab().setText("头条"));
        tabLayout.addTab(tabLayout.newTab().setText("FUN"));
    }

    private void initViewPager() {
        List<BaseFragment> list = new ArrayList<>();
        list.add(new HomeJokerFragment());
        list.add(new HomeWeChatSelectFragment());
        list.add(new HomeJokerImageFragment());
        list.add(new HomeNewsTopFragment());
        list.add(new HomeNewsFunFragment());
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
