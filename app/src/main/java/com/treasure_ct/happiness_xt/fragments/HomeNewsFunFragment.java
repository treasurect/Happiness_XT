package com.treasure_ct.happiness_xt.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.home.HomeNewsWebActivity;
import com.treasure_ct.happiness_xt.adapter.HomeNewsTopListAdapter;
import com.treasure_ct.happiness_xt.bean.HomeNewsTopListBean;
import com.treasure_ct.happiness_xt.widget.CustomRefreshListView;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeNewsFunFragment extends BaseFragment implements HomeNewsTopListAdapter.isClickItemInterface, CustomRefreshListView.OnRefreshListener {
    //标志位，标志已经初始化完成
    private boolean isPrepared;
    private CustomRefreshListView listView;
    private HomeNewsTopListBean newsResult;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    List<HomeNewsTopListBean.ItemBean> beanList = newsResult.getItem();
                    for (int i = 0; i < beanList.size(); i++) {
                        HomeNewsTopListBean.ItemBean itemBean = beanList.get(i);
                        list.add(itemBean);
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case 300:
                    listView.completeRefresh();
                    break;
            }
        }
    };
    private List<HomeNewsTopListBean.ItemBean> list;
    private HomeNewsTopListAdapter adapter;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_news, container, false);
        //初始化view各控件
        initFindId(view);
        isPrepared = true;
        lazyLoad();
        return view;
    }

    private void initFindId(View view) {
        listView = (CustomRefreshListView) view.findViewById(R.id.home_news_listView);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible)
            return;
        //填充数据
        initListView();
        listView.setOnRefreshListener(this);
        getNewsInfo(page);
        adapter.setIsClickItemInterface(this);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new HomeNewsTopListAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }

    private void getNewsInfo(int page) {
        String url = "http://api.iclient.ifeng.com/ClientNews?id=DZPD&page=" + page + "&uid=860797039338439";
        HttpHelper.doGetCall(url, getContext(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~onFailure~", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                newsResult = ModelParseHelper.parseNewsTopResult(result.substring(1, result.length() - 1));
//                LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~`",response.body().string());
                if (newsResult.getItem().size() > 0) {
                    Message message = new Message();
                    message.what = 200;
                    mHandler.sendMessage(message);
                }
            }
        });
    }

    @Override
    public void isClickItem(String url) {
        Intent intent = new Intent(getContext(), HomeNewsWebActivity.class);
        intent.putExtra("webUrl", url);
        startActivity(intent);
    }

    @Override
    public void onPullRefresh() {
        list.clear();
        getNewsInfo(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Message message = new Message();
                    message.what = 300;
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onLoadingMore() {
        page++;
        getNewsInfo(page);
        listView.completeRefresh();
    }
}
