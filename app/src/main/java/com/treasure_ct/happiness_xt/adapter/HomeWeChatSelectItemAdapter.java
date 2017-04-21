package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeWeChatSelectItemBean;

import java.util.List;

/**
 * Created by treasure on 2017.04.22.
 */

public class HomeWeChatSelectItemAdapter extends BaseAdapter{
    private Context context;
    private List<HomeWeChatSelectItemBean.ResultBean.ListBean> list;
    private LayoutInflater inflater;

    public HomeWeChatSelectItemAdapter(Context context, List<HomeWeChatSelectItemBean.ResultBean.ListBean> list) {
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
            ret = inflater.inflate(R.layout.home_wechat_item_list_item,parent,false);
        }
        ret.setTag(new ViewHolder(ret));
        HomeWeChatSelectItemBean.ResultBean.ListBean resultBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        holder.title.setText(resultBean.getTitle());
        holder.time.setText(resultBean.getPubTime());
        return ret;
    }
    private static class ViewHolder{
        private TextView title;
        private TextView time;
        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.home_wechat_item_title);
            time = (TextView) view.findViewById(R.id.home_wechat_item_time);
        }
    }
}
