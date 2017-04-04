package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeNewsNeihanListBean;

import java.util.List;

/**
 * Created by treasure on 2017.04.04.
 */

public class HomeNewsNeiHanListAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeNewsNeihanListBean.DataBeanX.DataBean.GroupBean> list;
    private LayoutInflater mInflater;

    public HomeNewsNeiHanListAdapter(Context context, List<HomeNewsNeihanListBean.DataBeanX.DataBean.GroupBean> list) {
        mContext = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        HomeNewsNeihanListBean.DataBeanX.DataBean.GroupBean neihanListBean = list.get(position);
        if (neihanListBean.getMedia_type() == 0) {
            ret = 0;
        } else if (neihanListBean.getMedia_type() == 1) {
            ret = 1;
        } else if (neihanListBean.getMedia_type() == 2) {
            ret = 2;
        } else if (neihanListBean.getMedia_type() == 3) {
            ret = 3;
        }
        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView!= null){
            ret = convertView;
        }else {
            if (getItemViewType(position) == 0){
                mInflater.inflate(R.layout.home_news_neihan_listview_text_item,parent,false);
            }
        }
        return ret;
    }
}
