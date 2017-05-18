package com.treasure_ct.happiness_xt.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.entertainment.HomeGpssipDetailActivity;
import com.treasure_ct.happiness_xt.adapter.HomeGossipListAdapter;
import com.treasure_ct.happiness_xt.bean.HomeGossipListBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.Tools;
import com.treasure_ct.happiness_xt.widget.CustomRefreshListView;
import com.treasure_ct.happiness_xt.widget.CustomScrollListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeGossipFragment extends BaseFragment implements CustomRefreshListView.OnRefreshListener, HomeGossipListAdapter.isClickItemInterface, View.OnClickListener {
    //标志位，标志已经初始化完成
    private boolean isPrepared;
    private CustomRefreshListView listView;
    private int page = 1;
    private List<HomeGossipListBean.DataBean.ListBean> list;
    private HomeGossipListAdapter adapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    if (listBean.getData().getInfo() != null) {
                        if (!Tools.isNull(listBean.getData().getInfo().get(0).getPic())) {
                            info_image.setImageURI(Uri.parse(listBean.getData().getInfo().get(0).getPic()));
                        }
                        if (!Tools.isNull(listBean.getData().getInfo().get(0).getTitle())) {
                            info_title.setText(listBean.getData().getInfo().get(0).getTitle());
                        }
                        if (!Tools.isNull(String.valueOf(listBean.getData().getInfo().get(0).getId()))) {
                            id1 = String.valueOf(listBean.getData().getInfo().get(0).getId());
                        }
                    }
                    if (listBean.getData().getList() != null) {
                        list.addAll(listBean.getData().getList());
                        adapter.notifyDataSetChanged();
                    }
                    progress.setVisibility(View.GONE);
                    break;
                case 201:
                    listView.completeRefresh();
                    break;
                case 400:
                    Toast.makeText(getContext(), "原因：" + error, Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                    break;
            }
        }
    };
    private String error;
    private HomeGossipListBean listBean;
    private ProgressBar progress;
    private SimpleDraweeView info_image;
    private TextView info_title;
    private String id1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_gossip, container, false);
        initFindId(view);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    private void initFindId(View view) {
        listView = (CustomRefreshListView) view.findViewById(R.id.home_gossip_listView);
        progress = (ProgressBar) view.findViewById(R.id.home_gossip_progress);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible)
            return;
        //填充数据
        initListView();
        getGossipList(page);
        progress.setVisibility(View.VISIBLE);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new HomeGossipListAdapter(getContext(), list);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(this);
        adapter.setIsClickItemInterface(this);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.home_gossip_listview_header, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Tools.dip2px(getContext(),310));
        inflate.setLayoutParams(layoutParams);
        info_image = (SimpleDraweeView) inflate.findViewById(R.id.home_gossip_info_image);
        info_title = (TextView) inflate.findViewById(R.id.home_gossip_info_title);
        info_image.setOnClickListener(this);
        listView.addHeaderView(inflate);
    }

    private void getGossipList(int page) {
        String url = "http://api.app.happyjuzi.com/article/list/channel?id=27&page=" + page + "&ts=0&startup=0&fromapp=juzi&mac=02-00-00-00-00-00&uid=4108532685623026&pf=android&net=wifi&accesstoken=13cf0c286c8e88eac3c540eff8a73e74&channel=yingyongbao&ver=3.7.8&res=1080x1830";
        HttpHelper.doGetCall(url, getContext(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                error = e.getMessage();
                mHandler.sendMessage(mHandler.obtainMessage(400));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", string + ".....");
                listBean = ModelParseHelper.parsegossipResult(string);
                if (listBean != null) {
                    if (listBean.getData() != null) {
                        mHandler.sendMessage(mHandler.obtainMessage(200));
                    }
                }
            }
        });
    }

    @Override
    public void onPullRefresh() {
        list.clear();
        page = 1;
        getGossipList(page);
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
        page++;
        getGossipList(page);
        progress.setVisibility(View.VISIBLE);
        listView.completeRefresh();
    }

    @Override
    public void isClickItem(String id) {
        Intent intent = new Intent(getContext(), HomeGpssipDetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_gossip_info_image:
                Intent intent = new Intent(getContext(), HomeGpssipDetailActivity.class);
                intent.putExtra("id",id1);
                startActivity(intent);
                break;
        }
    }
}
