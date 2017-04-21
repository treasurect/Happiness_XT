package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeWeChatSelectListBean;

import java.util.List;

/**
 * Created by treasure on 2017.04.22.
 */

public class HomeWeChatSelectListAdapter extends BaseAdapter{
    private Context context;
    private List<HomeWeChatSelectListBean.ResultBean> list;
    private LayoutInflater inflater;

    public HomeWeChatSelectListAdapter(Context context, List<HomeWeChatSelectListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null){
            ret = convertView;
        }else {
            ret = inflater.inflate(R.layout.home_wechat_select_list_item,parent,false);
        }
        ret.setTag(new ViewHolder(ret));
        HomeWeChatSelectListBean.ResultBean resultBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        holder.name.setText(resultBean.getName());
        return ret;
    }
    private static class ViewHolder{
        private TextView name;
        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.home_wechat_list_item_name);
        }
    }
}
