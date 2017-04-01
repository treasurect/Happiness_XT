package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.DynamicListViewBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class DynamicListAdapter extends BaseAdapter {
    private Context context;
    private List<DynamicListViewBean> list;
    private LayoutInflater inflater;

    public DynamicListAdapter(Context context, List<DynamicListViewBean> list) {
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        View ret = null;
        if (view != null){
            ret = view;
        }else {
            ret = inflater.inflate(R.layout.dynamic_listview_item,viewGroup,false);
        }
        ret.setTag(new ViewHolder(ret));
        DynamicListViewBean listViewBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        holder.publisher_icon.setImageResource(R.mipmap.icon);
        holder.publisher_name.setText(listViewBean.getPublisher_name());
        holder.publish_image.setImageResource(R.mipmap.pic_fengjing);
        holder.publish_title.setText(listViewBean.getPublish_title());
        holder.publish_text.setText(listViewBean.getPublish_text());
        holder.publisher_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickPublishAttention.clickAttention();
            }
        });
        return ret;
    }
    public class ViewHolder{
        private ImageView publisher_icon;
        private TextView publisher_name;
        private TextView publisher_attention;
        private ImageView publish_image;
        private TextView publish_title;
        private TextView publish_text;
        public ViewHolder(View view) {
            publisher_icon = (ImageView) view.findViewById(R.id.dynamic_publisher_icon);
            publisher_name = (TextView) view.findViewById(R.id.dynamic_publisher_name);
            publisher_attention = (TextView) view.findViewById(R.id.dynamic_publisher_attention);
            publish_image = (ImageView) view.findViewById(R.id.dynamic_publish_image);
            publish_title = (TextView) view.findViewById(R.id.dynamic_publish_title);
            publish_text = (TextView) view.findViewById(R.id.dynamic_publish_text);
        }
    }
    public interface OnClickPublishAttention {
        void clickAttention();
    }
    public OnClickPublishAttention onClickPublishAttention;

    public void setOnClickPublishAttention(OnClickPublishAttention onClickPublishAttention) {
        this.onClickPublishAttention = onClickPublishAttention;
    }
}
