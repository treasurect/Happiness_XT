package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.AssistantRobotBean;

import java.util.List;

/**
 * Created by treasure on 2016.09.18.
 */
public class AssistantRobotListAdapter extends BaseAdapter {
    private Context context;
    private List<AssistantRobotBean> list;

    public AssistantRobotListAdapter(Context context, List<AssistantRobotBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list!= null){
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
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        AssistantRobotBean assistantRobotBean = list.get(position);
        if (assistantRobotBean.isReceived()) {
            ret = 0;
        }else {
            ret = 1;
        }
        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                ret = getLeftView(position,convertView,parent);
                break;
            case 1:
                ret = getRightView(position,convertView,parent);
                break;
        }
        return ret;
    }
    private View getLeftView(int position, View convertView, ViewGroup parent){
        View ret = null;
        if (convertView != null){
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.assistant_robot_left_item,parent,false);
        }
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (holder == null){
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }
        holder.bindView(position,list.get(position));
        return ret;
    }
    private View getRightView(int position, View convertView, ViewGroup parent){
        View ret = null;
        if (convertView != null){
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.assistant_robot_right_item,parent,false);
        }
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (holder == null){
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }
        holder.bindView(position,list.get(position));
        return ret;
    }
    private static class ViewHolder{
        private TextView name,text;
        private ImageView icon;
        public ViewHolder(View view) {
             name = (TextView) view.findViewById(R.id.userName);
             text = (TextView) view.findViewById(R.id.userText);
             icon = (ImageView) view.findViewById(R.id.userIcon);
        }
        public void bindView(int position,AssistantRobotBean assistantRobotBean){
            name.setText(assistantRobotBean.getUserName());
            text.setText(assistantRobotBean.getText());
        }
    }
}
