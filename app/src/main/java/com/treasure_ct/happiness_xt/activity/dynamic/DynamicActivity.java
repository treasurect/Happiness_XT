package com.treasure_ct.happiness_xt.activity.dynamic;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mob.bbssdk.API;
import com.mob.bbssdk.APICallback;
import com.mob.bbssdk.BBSSDK;
import com.mob.bbssdk.api.ForumAPI;
import com.mob.bbssdk.model.ForumForum;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.life.LifeDynamicItemActivity;
import com.treasure_ct.happiness_xt.adapter.DynamicListAdapter;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
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

public class DynamicActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, DynamicListAdapter.ItemClick, AdapterView.OnItemClickListener {
    private CustomScrollListView dynamic_listView;
    private List<DynamicBean> dynamic_list;
    private DynamicListAdapter dynamic_adapter;
    private NestedScrollView scrollView;
    private IntentFilter filter;
    private CommonDataReceiver commonDataReceiver;
    private RadioButton dynamic_RB,community_RB;
    private RadioGroup dynamic_RG;
    private LinearLayout community_layout;
    private ForumAPI bbsApi;
    private int sendTopNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        Tools.setTranslucentStatus(this);
        initFindId();
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
        registerReceiver(commonDataReceiver,filter);
    }

    private void initFindId() {
        scrollView = (NestedScrollView) findViewById(R.id.dynamic_scrollView);
        dynamic_listView = (CustomScrollListView) findViewById(R.id.dynamic_listView);
        dynamic_RB = (RadioButton) findViewById(R.id.dynamic_dynamic);
        community_RB = (RadioButton) findViewById(R.id.dynamic_community);
        dynamic_RG = (RadioGroup) findViewById(R.id.dynamic_radioGroup);
        community_layout = (LinearLayout) findViewById(R.id.dynamic_community_layout);
    }

    private void initListView() {
        dynamic_list = new ArrayList<>();
        dynamic_adapter = new DynamicListAdapter(this, dynamic_list);
        dynamic_listView.setAdapter(dynamic_adapter);
    }

    private void initScrollView() {
        scrollView.smoothScrollTo(0,20);
        dynamic_listView.setFocusable(false);
    }

    private void initClick() {
        dynamic_RG.setOnCheckedChangeListener(this);
        dynamic_adapter.setItemClick(this);
        dynamic_listView.setOnItemClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        if (radioButton.getText().equals("动态")){
            dynamic_RB.setTextColor(getResources().getColor(R.color.colorBlock));
            community_RB.setTextColor(getResources().getColor(R.color.colorWhite));
//            dynamic_listView.setVisibility(View.VISIBLE);
//            community_layout.setVisibility(View.GONE);
        }else {
            dynamic_RB.setTextColor(getResources().getColor(R.color.colorWhite));
            community_RB.setTextColor(getResources().getColor(R.color.colorBlock));
//            dynamic_listView.setVisibility(View.GONE);
//            community_layout.setVisibility(View.VISIBLE);
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
    public void onDestroy() {
        unregisterReceiver(commonDataReceiver);
        super.onDestroy();
    }

    @Override
    public void sendMore( String nick,  String contents) {

    }

    @Override
    public void sendTop(String nick, String contents) {
        BmobQuery<DynamicBean> query = new BmobQuery<>();
        query.addWhereEqualTo("user_nick", nick);
        query.addWhereEqualTo("content", contents);
        query.findObjects(new FindListener<DynamicBean>() {
            @Override
            public void done(List<DynamicBean> list, BmobException e) {
                if (e == null) {
                    for (DynamicBean dynamicBean : list) {
                        getTop(dynamicBean.getObjectId());
                    }
                }
            }
        });
    }

    private void getTop(final String objectId) {
        BmobQuery<DynamicBean> query = new BmobQuery<>();
        query.getObject(objectId, new QueryListener<DynamicBean>() {

            @Override
            public void done(DynamicBean dynamicBean, BmobException e) {
                if (e == null) {
                    sendTopNum = dynamicBean.getSendTop();
                    toUpdateTop(objectId, sendTopNum);
                }
            }
        });
    }

    private void toUpdateTop(String objectId, int sendTopNum) {
        DynamicBean dynamicBean = new DynamicBean();
        dynamicBean.setSendTop(sendTopNum + 1);
        dynamicBean.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                requestDynamicList();
            }
        });
    }

    @Override
    public void sendComments(String nick, String contents, String publish_time, int top_num, int comments_num) {
        Intent intent = new Intent(this, LifeDynamicItemActivity.class);
        intent.putExtra("user_nick", nick);
        intent.putExtra("publish_time", publish_time);
        intent.putExtra("content", contents);
        intent.putExtra("top", top_num + "");
        intent.putExtra("comments", comments_num + "");
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, LifeDynamicItemActivity.class);
        intent.putExtra("user_nick", dynamic_list.get(position).getUser_nick());
        intent.putExtra("publish_time", dynamic_list.get(position).getPublish_time());
        intent.putExtra("content", dynamic_list.get(position).getContent());
        intent.putExtra("top", dynamic_list.get(position).getSendTop() + "");
        intent.putExtra("comments", dynamic_list.get(position).getComments() + "");
        startActivity(intent);
    }
}
