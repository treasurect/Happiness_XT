package com.treasure_ct.happiness_xt.activity.user;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.UserPushListAdapter;
import com.treasure_ct.happiness_xt.bean.PushBean;
import com.treasure_ct.happiness_xt.receiver.JPushReceiver;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.jpush.android.api.JPushInterface;

public class UserPushActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private ListView noti_listView;
    private ListView other_listView;
    private List<PushBean> list;
    private List<PushBean> list2;
    private UserPushListAdapter adapter;
    private UserPushListAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_push);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        push_RG.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);

        initFindId();
        initClick();
        initListView();

        noti_RB.setChecked(true);
        noti_RB.setTextColor(getResources().getColor(R.color.colorBlock));
        other_RB.setTextColor(getResources().getColor(R.color.colorWhite));
        noti_listView.setVisibility(View.VISIBLE);
        other_listView.setVisibility(View.GONE);
        requestNotiList();
    }

    private void initFindId() {
        noti_listView = (ListView) findViewById(R.id.push_notification_listView);
        other_listView = (ListView) findViewById(R.id.push_other_listView);
    }

    private void initClick() {
        push_RG.setOnCheckedChangeListener(this);
        btn_back.setOnClickListener(this);
    }

    private void initListView() {
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        adapter = new UserPushListAdapter(this,list);
        adapter2 = new UserPushListAdapter(this,list2);
        noti_listView.setAdapter(adapter);
        other_listView.setAdapter(adapter2);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        if (radioButton.getText().equals("通知")){
            noti_RB.setTextColor(getResources().getColor(R.color.colorBlock));
            other_RB.setTextColor(getResources().getColor(R.color.colorWhite));
            noti_listView.setVisibility(View.VISIBLE);
            other_listView.setVisibility(View.GONE);
            requestNotiList();
        }else {
            noti_RB.setTextColor(getResources().getColor(R.color.colorWhite));
            other_RB.setTextColor(getResources().getColor(R.color.colorBlock));
            noti_listView.setVisibility(View.GONE);
            other_listView.setVisibility(View.VISIBLE);
            requestOtherList();
        }
    }

    private void requestNotiList() {
        BmobQuery<PushBean> query = new BmobQuery<>();
        query.addWhereEqualTo("label", "normal");
        query.setLimit(10);
        query.findObjects(new FindListener<PushBean>() {
            @Override
            public void done(List<PushBean> data_list, BmobException e) {
                if(e==null){
                    if (data_list != null){
                        list.clear();
                        Collections.reverse(data_list);
                        list.addAll(data_list);
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(UserPushActivity.this, "失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestOtherList() {
        BmobQuery<PushBean> query = new BmobQuery<>();
        query.addWhereEqualTo("label", "other");
        query.setLimit(10);
        query.findObjects(new FindListener<PushBean>() {
            @Override
            public void done(List<PushBean> data_list, BmobException e) {
                if(e==null){
                    if (data_list != null){
                        list2.clear();
                        Collections.reverse(data_list);
                        list2.addAll(data_list);
                        adapter2.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(UserPushActivity.this, "失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
