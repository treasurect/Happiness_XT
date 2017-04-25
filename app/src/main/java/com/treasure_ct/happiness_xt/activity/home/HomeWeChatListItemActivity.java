package com.treasure_ct.happiness_xt.activity.home;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.HomeWeChatSelectItemAdapter;
import com.treasure_ct.happiness_xt.bean.HomeWeChatSelectItemBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeWeChatListItemActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private String cid;
    private String name;
    private List<HomeWeChatSelectItemBean.ResultBean.ListBean> list;
    private HomeWeChatSelectItemAdapter adapter;
    private ListView listView;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    if (itemBean.getResult() != null) {
                        List<HomeWeChatSelectItemBean.ResultBean.ListBean> listBean = itemBean.getResult().getList();
                        list.addAll(listBean);
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case 400:
                    String obj = (String) msg.obj;
                    Toast.makeText(HomeWeChatListItemActivity.this, "原因："+obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private HomeWeChatSelectItemBean itemBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_we_chat_list_item);
        initTitle();
        Tools.setTranslucentStatus(this);
        btn_back.setImageResource(R.mipmap.icon_return);
        btn_back.setVisibility(View.VISIBLE);
        getIntentData();
        initFinId();
        initListView();
        initClick();
        getListItem();
    }

    private void initClick() {
        btn_back.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    private void initFinId() {
        listView = (ListView) findViewById(R.id.home_wechat_item_listView);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        cid = intent.getStringExtra("cid");
        name = intent.getStringExtra("name");
        title.setText(name);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new HomeWeChatSelectItemAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void getListItem() {
        HttpHelper.doGetCall(StringContents.MobAPI_BaseUrl + "/wx/article/search?key=" + StringContents.MobAPI_APPKEY + "&cid=" + cid,
                this, new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message message = mHandler.obtainMessage();
                        message.obj = e.getMessage();
                        message.what = 400;
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        itemBean = ModelParseHelper.parseWeChatItemResult(string);
                        mHandler.sendMessage(mHandler.obtainMessage(200));
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,HomeNewsWebActivity.class);
        intent.putExtra("webUrl",list.get(position).getSourceUrl());
        startActivity(intent);
    }
}