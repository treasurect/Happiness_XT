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
import com.treasure_ct.happiness_xt.activity.entertainment.HomeJokerDetailActivity;
import com.treasure_ct.happiness_xt.activity.entertainment.HomeNewsWebActivity;
import com.treasure_ct.happiness_xt.adapter.HomeJokerListAdapter;
import com.treasure_ct.happiness_xt.adapter.HomeNewsTopListAdapter;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.widget.CustomRefreshListView;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeJokerFragment extends BaseFragment implements CustomRefreshListView.OnRefreshListener, HomeJokerListAdapter.jokerItemInterface {
    //标志位，标志已经初始化完成
    private boolean isPrepared;
    private CustomRefreshListView listView;
    private HomeJokerListBean jokerResult;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    List<HomeJokerListBean.DataBeanXX.DataBeanX> beanList = jokerResult.getData().getData();
                    for (int i = 0; i < beanList.size(); i++) {
                        HomeJokerListBean.DataBeanXX.DataBeanX itemBean = beanList.get(i);
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
                    break;
            }
        }
    };
    private List<HomeJokerListBean.DataBeanXX.DataBeanX> list;
    private HomeJokerListAdapter adapter;
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
    }

    private void initFindId(View view) {
        listView = (CustomRefreshListView) view.findViewById(R.id.home_news_listView);
        progressBar = (ProgressBar) view.findViewById(R.id.home_news_loading);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new HomeJokerListAdapter(getContext(), list);
        listView.setAdapter(adapter);
        adapter.setJokerItemInterface(this);

    }

    private void getJokerInfo(int page) {
        String url = "http://iu.snssdk.com/neihan/stream/mix/v1/?mpic=" + page + "&webp=1&essence=1&content_type=-102&message_cursor=-1&double_col_mode=0&local_request_tag=1494865297827&iid=10236690215&device_id=33766398200";
        HttpHelper.doGetCall(url, getContext(), new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                error = e.getMessage();
                mHandler.sendMessage(mHandler.obtainMessage(400));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                jokerResult = ModelParseHelper.parseJokerResult(result);

                if (jokerResult.getData() != null) {
                    mHandler.sendMessage(mHandler.obtainMessage(200));
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
        listView.completeRefresh();
    }

    @Override
    public void isClickItem(HomeJokerListBean.DataBeanXX.DataBeanX.GroupBean groupBean) {
        Intent intent = new Intent(getContext(), HomeJokerDetailActivity.class);
        intent.putExtra("groupBean", (Serializable) groupBean);
        startActivity(intent);
    }
}
