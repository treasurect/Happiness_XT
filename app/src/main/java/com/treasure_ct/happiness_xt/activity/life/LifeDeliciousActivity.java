package com.treasure_ct.happiness_xt.activity.life;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.LifeDeliciousListAdapter;
import com.treasure_ct.happiness_xt.bean.LifeDeliciousListBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.Tools;
import com.treasure_ct.happiness_xt.widget.CustomRefreshListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LifeDeliciousActivity extends BaseActivity implements CustomRefreshListView.OnRefreshListener, View.OnClickListener, LifeDeliciousListAdapter.DeliciousItemClick {
    private List<LifeDeliciousListBean.DataBeanX.DataBean> list;
    private LifeDeliciousListAdapter adapter;
    private CustomRefreshListView listView;
    private int id = 1;
    private LifeDeliciousListBean deliciousListBean;
    private String error;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    List<LifeDeliciousListBean.DataBeanX.DataBean> dataBean = deliciousListBean.getData().getData();
                    list.addAll(dataBean);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    break;
                case 201:
                    listView.completeRefresh();
                    break;
                case 400:
                    Toast.makeText(LifeDeliciousActivity.this, "原因：" + error, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    };
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_delicious);
        initTitle();
        Tools.setTranslucentStatus(this);
        btn_back.setVisibility(View.VISIBLE);
        title.setText("美食菜谱");
        initFindId();
        initListView();
        initClick();
        getDeliciousList(id);
        progressBar.setVisibility(View.VISIBLE);
        listView.setOnRefreshListener(this);
    }

    private void initFindId() {
        listView = (CustomRefreshListView) findViewById(R.id.life_delicious_listView);
        progressBar = (ProgressBar) findViewById(R.id.life_delicious_progress);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new LifeDeliciousListAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void initClick() {
        btn_back.setOnClickListener(this);
        adapter.setDeliciousItemClick(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void getDeliciousList(int id) {
        String url = "http://api.izhangchu.com/?scene_id=51&page=" + id + "&methodName=SceneDishes&size=20&version=4.40";
        HttpHelper.doGetCall(url, this, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                error = e.getMessage();
                mHandler.sendMessage(mHandler.obtainMessage(400));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                deliciousListBean = ModelParseHelper.parseDeliciousListResult(string);
                if (deliciousListBean != null){
                    if (deliciousListBean.getData() != null){
                        if (deliciousListBean.getData().getData() != null){
                            mHandler.sendMessage(mHandler.obtainMessage(200));
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onPullRefresh() {
        list.clear();
        getDeliciousList(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    mHandler.sendMessage(mHandler.obtainMessage(201));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onLoadingMore() {
        id++;
        getDeliciousList(id);
        progressBar.setVisibility(View.VISIBLE);
        listView.completeRefresh();
    }

    @Override
    public void itemClick(String dishes_id) {
        Intent intent = new Intent(this, LifeDeliciousDetailActivity.class);
        intent.putExtra("dishes_id", String.valueOf(dishes_id));
        startActivity(intent);
    }
}
