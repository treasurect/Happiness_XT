package com.treasure_ct.happiness_xt.activity.dynamic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.life.LifeDynamicItemActivity;
import com.treasure_ct.happiness_xt.adapter.DynamicListAdapter;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class DynamicAllActivity extends BaseActivity implements View.OnClickListener, DynamicListAdapter.ItemClick, AdapterView.OnItemClickListener {
    private ListView dynamic_listView;
    private List<DynamicBean> dynamic_list;
    private DynamicListAdapter dynamic_adapter;
    private int sendTopNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_all);
        Tools.setTranslucentStatus(this);
        initTitle();
        btn_back.setVisibility(View.VISIBLE);
        title.setText("全部动态");
        initFindId();
        initListView();
        initClick();
        requestDynamicList();
    }

    private void initFindId() {
        dynamic_listView = (ListView) findViewById(R.id.dynamic_listView);
    }

    private void initListView() {
        dynamic_list = new ArrayList<>();
        dynamic_adapter = new DynamicListAdapter(this, dynamic_list);
        dynamic_listView.setAdapter(dynamic_adapter);
    }

    private void initClick() {
        btn_back.setOnClickListener(this);
        dynamic_adapter.setItemClick(this);
        dynamic_listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                DynamicAllActivity.this.finish();
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
        Intent intent = new Intent(DynamicAllActivity.this, LifeDynamicItemActivity.class);
        intent.putExtra("user_nick", nick);
        intent.putExtra("publish_time", publish_time);
        intent.putExtra("content", contents);
        intent.putExtra("top", top_num + "");
        intent.putExtra("comments", comments_num + "");
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(DynamicAllActivity.this, LifeDynamicItemActivity.class);
        intent.putExtra("user_nick", dynamic_list.get(position).getUser_nick());
        intent.putExtra("publish_time", dynamic_list.get(position).getPublish_time());
        intent.putExtra("content", dynamic_list.get(position).getContent());
        intent.putExtra("top", dynamic_list.get(position).getSendTop() + "");
        intent.putExtra("comments", dynamic_list.get(position).getComments() + "");
        startActivity(intent);
    }
}
