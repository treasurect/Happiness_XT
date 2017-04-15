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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mob.bbssdk.API;
import com.mob.bbssdk.APICallback;
import com.mob.bbssdk.BBSSDK;
import com.mob.bbssdk.api.ForumAPI;
import com.mob.bbssdk.model.ForumForum;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.dynatmic.DynamicVrWholeActivity;
import com.treasure_ct.happiness_xt.adapter.DynamicListAdapter;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
import com.treasure_ct.happiness_xt.receiver.CommonDataReceiver;
import com.treasure_ct.happiness_xt.widget.CustomScrollListView;
import com.treasure_ct.happiness_xt.utils.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class DynamicFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private CustomScrollListView dynamic_listView;
    private List<DynamicBean> dynamic_list;
    private DynamicListAdapter dynamic_adapter;
    private NestedScrollView scrollView;
    private IntentFilter filter;
    private CommonDataReceiver commonDataReceiver;
    private ImageView vr_whole;
    private RadioButton dynamic_RB,community_RB;
    private RadioGroup dynamic_RG;
    private View view;
    private LinearLayout community_layout;
    private ForumAPI bbsApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_dynamic, container, false);

        initFindId(view);
        receiveBroadCast();
        initListView();
        initScrollView();
        initClick();

        requestDynamicList();

        bbsApi = BBSSDK.getApi(ForumAPI.class);
        requestCommunityData();

        dynamic_RB.setChecked(true);
        dynamic_RB.setTextColor(getResources().getColor(R.color.colorBlock));
        community_RB.setTextColor(getResources().getColor(R.color.colorWhite));
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
        dynamic_listView = (CustomScrollListView) view.findViewById(R.id.dynamic_listView);
        vr_whole = (ImageView) view.findViewById(R.id.dynamic_vr_whole);
        dynamic_RB = (RadioButton) view.findViewById(R.id.dynamic_dynamic);
        community_RB = (RadioButton) view.findViewById(R.id.dynamic_community);
        dynamic_RG = (RadioGroup) view.findViewById(R.id.dynamic_radioGroup);
        community_layout = (LinearLayout) view.findViewById(R.id.dynamic_community_layout);
    }

    private void initListView() {
        dynamic_list = new ArrayList<>();
        dynamic_adapter = new DynamicListAdapter(getContext(), dynamic_list);
        dynamic_listView.setAdapter(dynamic_adapter);
    }

    private void initScrollView() {
        scrollView.smoothScrollTo(0,20);
        dynamic_listView.setFocusable(false);
    }

    private void initClick() {
        vr_whole.setOnClickListener(this);
        dynamic_RG.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dynamic_vr_whole:
                startActivity(new Intent(getContext(), DynamicVrWholeActivity.class));
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton) view.findViewById(group.getCheckedRadioButtonId());
        if (radioButton.getText().equals("动态")){
            dynamic_RB.setTextColor(getResources().getColor(R.color.colorBlock));
            community_RB.setTextColor(getResources().getColor(R.color.colorWhite));
            dynamic_listView.setVisibility(View.VISIBLE);
            community_layout.setVisibility(View.GONE);
        }else {
            dynamic_RB.setTextColor(getResources().getColor(R.color.colorWhite));
            community_RB.setTextColor(getResources().getColor(R.color.colorBlock));
            dynamic_listView.setVisibility(View.GONE);
            community_layout.setVisibility(View.VISIBLE);
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
                        dynamic_list.clear();
                        Collections.reverse(data_list);
                        dynamic_list.addAll(data_list);
                        dynamic_adapter.notifyDataSetChanged();
                    }
                }else{
                    LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~",e.getMessage());
                }
            }
        });
    }

    private void requestCommunityData() {
        bbsApi.getForumList(false, new APICallback<ArrayList<ForumForum>>() {
            public void onSuccess(API api, int action, ArrayList<ForumForum> result) {
                for (int i = 0; i < result.size(); i++) {
                    LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~",result.get(i).name+"........");
                }
            }
            public void onError(API api, int action, int errorCode, Throwable details) {
                //TODO 获取失败
                LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~~","失败");
            }
        });
    }

    @Override
    public void onDestroyView() {
        getContext().unregisterReceiver(commonDataReceiver);
        super.onDestroyView();
    }
}
