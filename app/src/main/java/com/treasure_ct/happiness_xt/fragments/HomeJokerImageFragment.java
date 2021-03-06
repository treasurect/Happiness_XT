package com.treasure_ct.happiness_xt.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.entertainment.HomeJokerImageDetailActivity;
import com.treasure_ct.happiness_xt.activity.entertainment.HomeNewsWebActivity;
import com.treasure_ct.happiness_xt.adapter.HomeJokerImageListAdapter;
import com.treasure_ct.happiness_xt.bean.HomeJokerImageListBean;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeJokerImageFragment extends BaseFragment implements CustomRefreshListView.OnRefreshListener, HomeJokerImageListAdapter.isClickItemInterface {
    private boolean isPrepare;
    private CustomRefreshListView listView;
    private ProgressBar progressBar;
    private List<HomeJokerImageListBean.DataBeanX.DataBean> list;
    private HomeJokerImageListAdapter adapter;
    private int page = 1;
    private HomeJokerImageListBean listBean;
    private String error;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    List<HomeJokerImageListBean.DataBeanX.DataBean> dataBeen = listBean.getData().getData();
                    list.addAll(dataBeen);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    break;
                case 201:
                    listView.completeRefresh();
                    break;
                case 400:
                    Toast.makeText(getContext(), "原因：" + error, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_news, container, false);
        initFindId(view);
        isPrepare = true;
        lazyLoad();
        return view;
    }

    private void initFindId(View view) {
        listView = (CustomRefreshListView) view.findViewById(R.id.home_news_listView);
        progressBar = (ProgressBar) view.findViewById(R.id.home_news_loading);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepare || !isVisible)
            return;
        initListView();
        getJokerImageList(page);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new HomeJokerImageListAdapter(getContext(), list);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(this);
        adapter.setIsClickItemInterface(this);
    }

    private void getJokerImageList(int page) {
        String url = "http://iu.snssdk.com/neihan/stream/mix/v1/?mpic="+page+"&webp=1&essence=1&content_type=-103&message_cursor=-1&double_col_mode=0&local_request_tag=1494865429905&iid=10236690215&device_id=33766398200";
        HttpHelper.doGetCall(url, getContext(), new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        error = e.getMessage();
                        mHandler.sendMessage(mHandler.obtainMessage(400));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        listBean = ModelParseHelper.parseJokerImageResult(string);
                        if (listBean != null){
                            if (listBean.getData() != null){
                                if (listBean.getData().getData() != null){
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
        getJokerImageList(1);
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
        getJokerImageList(page);
        progressBar.setVisibility(View.VISIBLE);
        listView.completeRefresh();
    }

    @Override
    public void isClickItem(HomeJokerImageListBean.DataBeanX.DataBean.GroupBean groupBean) {
        Intent intent = new Intent(getContext(), HomeJokerImageDetailActivity.class);
        intent.putExtra("groupBean", (Serializable) groupBean);
        startActivity(intent);
    }
}
