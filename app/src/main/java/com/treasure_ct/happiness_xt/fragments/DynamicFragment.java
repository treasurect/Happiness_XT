package com.treasure_ct.happiness_xt.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.bbssdk.API;
import com.mob.bbssdk.APICallback;
import com.mob.bbssdk.BBSSDK;
import com.mob.bbssdk.api.ForumAPI;
import com.mob.bbssdk.model.ForumForum;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.dynamic.DynamicAllActivity;
import com.treasure_ct.happiness_xt.activity.life.LifeDynamicItemActivity;
import com.treasure_ct.happiness_xt.adapter.DynamicListAdapter;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
import com.treasure_ct.happiness_xt.fragments.BaseFragment;
import com.treasure_ct.happiness_xt.receiver.CommonDataReceiver;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.Tools;
import com.treasure_ct.happiness_xt.widget.CustomScrollListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class DynamicFragment extends BaseFragment implements View.OnClickListener {
    private FrameLayout all_layout;
    private FrameLayout friend_layout;
    private CustomScrollListView listView;
    private TextView dynamic_title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);

        initFindId(view);
        dynamic_title.setText("消息");
        initListView();
        initClick();
        return view;
    }

    private void initFindId(View view) {
        all_layout = ((FrameLayout) view.findViewById(R.id.dynamic_all_layout));
        friend_layout = (FrameLayout) view.findViewById(R.id.dynamic_friend_layout);
       listView = (CustomScrollListView) view.findViewById(R.id.dynamic_listView);
        dynamic_title = (TextView) view.findViewById(R.id.dynamic_title);
    }

    private void initListView() {

    }

    private void initClick() {
        all_layout.setOnClickListener(this);
        friend_layout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dynamic_all_layout:
                startActivity(new Intent(getContext(), DynamicAllActivity.class));
                break;
            case R.id.dynamic_friend_layout:

                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }
}
