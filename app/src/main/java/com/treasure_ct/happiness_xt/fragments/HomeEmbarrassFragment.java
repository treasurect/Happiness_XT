package com.treasure_ct.happiness_xt.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.entertainment.HomeEmbarrassDetailActivity;
import com.treasure_ct.happiness_xt.activity.entertainment.HomeJokerDetailActivity;
import com.treasure_ct.happiness_xt.adapter.HomeEmbarrassListAdapter;
import com.treasure_ct.happiness_xt.adapter.HomeJokerListAdapter;
import com.treasure_ct.happiness_xt.bean.HomeEmbarrassListBean;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.widget.CustomRefreshListView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeEmbarrassFragment extends BaseFragment implements CustomRefreshListView.OnRefreshListener, HomeEmbarrassListAdapter.jokerItemInterface {
    //标志位，标志已经初始化完成
    private boolean isPrepared;
    private CustomRefreshListView listView;
    private HomeEmbarrassListBean embarrassResult;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    List<HomeEmbarrassListBean.ItemsBean> itemList = embarrassResult.getItems();
                    for (int i = 0; i < itemList.size(); i++) {
                        HomeEmbarrassListBean.ItemsBean itemBean = itemList.get(i);
                        list.add(itemBean);
                    }
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    break;
                case 300:
                    listView.completeRefresh();
                    break;
                case 400:
                    Toast.makeText(getContext(), "原因：" + error, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    };
    private List<HomeEmbarrassListBean.ItemsBean> list;
    private HomeEmbarrassListAdapter adapter;
    private int page = 1;
    private String error;
    private ProgressBar progressBar;

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

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible)
            return;
        //填充数据
        initListView();
        listView.setOnRefreshListener(this);
        getJokerInfo(page);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void initFindId(View view) {
        listView = (CustomRefreshListView) view.findViewById(R.id.home_news_listView);
        progressBar = (ProgressBar) view.findViewById(R.id.home_news_loading);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new HomeEmbarrassListAdapter(getContext(), list);
        listView.setAdapter(adapter);
        adapter.setJokerItemInterface(this);

    }

    private void getJokerInfo(int page) {
        String url = "http://m2.qiushibaike.com/article/list/text?page=" + page + "&count=30&rqcnt=3";
        HttpHelper.doGetCall(url, getContext(), new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                error = e.getMessage();
                mHandler.sendMessage(mHandler.obtainMessage(400));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                embarrassResult = ModelParseHelper.parseembarrassResult(result);
                if (embarrassResult != null) {
                    if (embarrassResult.getItems() != null) {
                        mHandler.sendMessage(mHandler.obtainMessage(200));
                    }
                }
            }
        });
    }

    @Override
    public void onPullRefresh() {
        list.clear();
        getJokerInfo(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    mHandler.sendMessage(mHandler.obtainMessage(300));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onLoadingMore() {
        page++;
        getJokerInfo(page);
        progressBar.setVisibility(View.VISIBLE);
        listView.completeRefresh();
    }

    @Override
    public void isClickItem(HomeEmbarrassListBean.ItemsBean itemsBean) {
        Intent intent = new Intent(getContext(), HomeEmbarrassDetailActivity.class);
        intent.putExtra("itemsBean", (Serializable) itemsBean);
        startActivity(intent);
    }
}
