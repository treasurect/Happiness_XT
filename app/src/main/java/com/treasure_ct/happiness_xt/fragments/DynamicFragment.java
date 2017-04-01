package com.treasure_ct.happiness_xt.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.DynamicListAdapter;
import com.treasure_ct.happiness_xt.bean.DynamicListViewBean;

import java.util.ArrayList;
import java.util.List;

public class DynamicFragment extends Fragment implements DynamicListAdapter.OnClickPublishAttention {
    private ListView listView;
    private List<DynamicListViewBean> list;
    private DynamicListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        initFindId(view);
        initListView();
        adapter.setOnClickPublishAttention(this);
        return view;
    }
    private void initFindId(View view) {
        listView = (ListView) view.findViewById(R.id.dynamic_listView);
    }

    private void initListView() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DynamicListViewBean dynamicListViewBean = new DynamicListViewBean();
            dynamicListViewBean.setPublisher_name("小王");
            dynamicListViewBean.setPublish_title("这些日用品");
            dynamicListViewBean.setPublish_text("你到底想要什么品质的日用品呢。");
            list.add(dynamicListViewBean);
        }
        adapter = new DynamicListAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }

    @Override
    public void clickAttention() {
        Toast.makeText(getContext(), "已经关注了", Toast.LENGTH_SHORT).show();
    }
}
