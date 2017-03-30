package com.treasure_ct.happiness_xt.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treasure_ct.happiness_xt.R;

public class HomeNewsFragment extends BaseFragment {
    //标志位，标志已经初始化完成
    private boolean isPrepared;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_news, container, false);
        //初始化view各控件
        isPrepared = true;
        lazyLoad();
        return view;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible)
            return;
        //填充数据
    }
}
