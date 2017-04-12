package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.PushBean;

import java.util.List;

/**
 * Created by treasure on 2017.04.13.
 */

public class UserPushListAdapter extends BaseAdapter {
    private Context context;
    private List<PushBean>list;
    private LayoutInflater mInflater;

    public UserPushListAdapter(Context context, List<PushBean> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list!=null){
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
        View ret =null;
        if (convertView != null){
            ret = convertView;
        }else {
            ret = mInflater.inflate(R.layout.user_push_list_item,parent,false);
        }
        ret.setTag(new ViewHolder(ret));
        PushBean pushBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        holder.title.setText(pushBean.getTitle());
        holder.message.setText(pushBean.getMessage());
        return ret;
    }
    public static class ViewHolder{
        private TextView title;
        private TextView message;
        public ViewHolder(View view) {
            title=((TextView) view.findViewById(R.id.push_list_item_title));
            message=((TextView) view.findViewById(R.id.push_list_item_mes));
        }
    }
}
