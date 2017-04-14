package com.treasure_ct.happiness_xt.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.dynatmic.DynamicVrWholeActivity;
import com.treasure_ct.happiness_xt.adapter.DynamicListAdapter;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
import com.treasure_ct.happiness_xt.receiver.CommonDataReceiver;
import com.treasure_ct.happiness_xt.custom.CustomScrollListView;
import com.treasure_ct.happiness_xt.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class DynamicFragment extends Fragment implements View.OnClickListener {
    private CustomScrollListView listView;
    private List<DynamicBean> list;
    private DynamicListAdapter adapter;
    private NestedScrollView scrollView;
    private IntentFilter filter;
    private CommonDataReceiver commonDataReceiver;
    private ImageView vr_whole;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        initFindId(view);
        receiveBroadCast();
        initListView();
        initScrollView();
        initClick();
        requestDynamicList();
        return view;
    }

    private void receiveBroadCast() {
        filter = new IntentFilter();
        commonDataReceiver = new CommonDataReceiver();
        commonDataReceiver.setDoUiReceiver(new CommonDataReceiver.DoUiReceiver() {
            @Override
            public void doUi(Context context, Intent intent) {
              if (intent.getExtras().getString("label").equals("dynamic")){
                  requestDynamicList();
              }
            }
        });
        getContext().registerReceiver(commonDataReceiver,filter);
    }

    private void initFindId(View view) {
        scrollView = (NestedScrollView) view.findViewById(R.id.dynamic_scrollView);
        listView = (CustomScrollListView) view.findViewById(R.id.dynamic_listView);
        vr_whole = (ImageView) view.findViewById(R.id.dynamic_vr_whole);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new DynamicListAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }

    private void initScrollView() {
        scrollView.smoothScrollTo(0,20);
        listView.setFocusable(false);
    }

    private void initClick() {
        vr_whole.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dynamic_vr_whole:
                startActivity(new Intent(getContext(), DynamicVrWholeActivity.class));
                break;
        }
    }

    private void requestDynamicList() {
        BmobQuery<DynamicBean> query = new BmobQuery<>();
        query.setLimit(10);
        query.findObjects(new FindListener<DynamicBean>() {
            @Override
            public void done(List<DynamicBean> data_list, BmobException e) {
                if(e==null){
                    if (data_list != null){
                        list.clear();
                        Collections.reverse(data_list);
                        list.addAll(data_list);
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~",e.getMessage());
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        getContext().unregisterReceiver(commonDataReceiver);
        super.onDestroyView();
    }
}
