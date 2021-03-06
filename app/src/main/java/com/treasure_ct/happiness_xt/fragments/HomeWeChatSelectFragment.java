package com.treasure_ct.happiness_xt.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.entertainment.HomeWeChatListItemActivity;
import com.treasure_ct.happiness_xt.adapter.HomeWeChatSelectListAdapter;
import com.treasure_ct.happiness_xt.bean.HomeWeChatSelectListBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.StringContents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeWeChatSelectFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private boolean isPrepared;
    private GridView gridView;
    private List<HomeWeChatSelectListBean.ResultBean> list;
    private HomeWeChatSelectListAdapter adapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    list.clear();
                    List<HomeWeChatSelectListBean.ResultBean> result = weChatSelectListBean.getResult();
                    for (int i = 0; i < result.size(); i++) {
                        HomeWeChatSelectListBean.ResultBean listBean = new HomeWeChatSelectListBean.ResultBean();
                        listBean.setName(result.get(i).getName());
                        listBean.setCid(result.get(i).getCid());
                        list.add(listBean);
                    }
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);
                    break;
                case 400:
                    Toast.makeText(getContext(), "原因：" + error, Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                    break;
            }
        }
    };
    private HomeWeChatSelectListBean weChatSelectListBean;
    private ProgressBar progress;
    private String error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_we_chat_select, container, false);
        initFindId(view);
        isPrepared = true;
//        lazyLoad();
        initGridView();
        getWeChatSelectList();
        gridView.setOnItemClickListener(this);
        return view;
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible)
            return;
        //填充数据
        initGridView();
        getWeChatSelectList();
        gridView.setOnItemClickListener(this);
    }

    private void initFindId(View view) {
        gridView = (GridView) view.findViewById(R.id.home_wechat_grid);
        progress = (ProgressBar) view.findViewById(R.id.home_wechat_grid_progress);
    }

    private void initGridView() {
        list = new ArrayList<>();
        adapter = new HomeWeChatSelectListAdapter(getContext(),list);
        gridView.setAdapter(adapter);
    }

    private void getWeChatSelectList() {
        progress.setVisibility(View.VISIBLE);
        String url = StringContents.MobAPI_BaseUrl + "/wx/article/category/query?key=" + StringContents.MobAPI_APPKEY;
        HttpHelper.doGetCall(url, getContext(), new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        error = e.getMessage();
                        mHandler.sendMessage(mHandler.obtainMessage(400));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        weChatSelectListBean = ModelParseHelper.parseWeChatListResult(string);
                        if (weChatSelectListBean != null){
                            if (weChatSelectListBean.getResult() != null){
                                mHandler.sendMessage(mHandler.obtainMessage(200));
                            }
                        }
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), HomeWeChatListItemActivity.class);
        intent.putExtra("cid",list.get(position).getCid());
        intent.putExtra("name",list.get(position).getName());
        startActivity(intent);
    }
}
