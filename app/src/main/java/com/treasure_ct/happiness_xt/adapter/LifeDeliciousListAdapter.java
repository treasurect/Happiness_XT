package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.LifeDeliciousListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.List;

/**
 * Created by treasure on 2016.11.12.
 */

public class LifeDeliciousListAdapter extends BaseAdapter{
    private Context context;
    private List<LifeDeliciousListBean.DataBeanX.DataBean> list;
    private LayoutInflater mInflater;

    public LifeDeliciousListAdapter(Context context, List<LifeDeliciousListBean.DataBeanX.DataBean> list) {
        this.context = context;
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
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View ret = null;
        if (view != null) {
            ret = view;
        } else {
            ret = mInflater.inflate(R.layout.life_delicious_list_item, viewGroup, false);
        }
        ret.setTag(new ViewHolder(ret));
        final LifeDeliciousListBean.DataBeanX.DataBean bean2 = list.get(i);
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (!Tools.isNull(bean2.getDishes_name())) {
            holder.title.setText(bean2.getDishes_name());
        }
        if (!Tools.isNull(bean2.getDishes_desc())) {
            holder.content.setText(bean2.getDishes_desc());
        }
        if (!Tools.isNull(bean2.getImage())){
            holder.icon.setImageURI(Uri.parse(bean2.getImage()));
        }
        if (bean2.getTags_info().size() > 0){
            holder.label01.setVisibility(View.VISIBLE);
            holder.label01.setText(bean2.getTags_info().get(0).getText());
        }
        if (bean2.getTags_info().size() > 1){
            holder.label02.setVisibility(View.VISIBLE);
            holder.label02.setText(bean2.getTags_info().get(1).getText());
        }
        if (bean2.getTags_info().size() > 2){
            holder.label03.setVisibility(View.VISIBLE);
            holder.label03.setText(bean2.getTags_info().get(2).getText());
        }
        if (bean2.getTags_info().size() > 3){
            holder.label04.setVisibility(View.VISIBLE);
            holder.label04.setText(bean2.getTags_info().get(3).getText());
        }

        if (bean2.getTags_info().size() > 4){
            holder.label05.setVisibility(View.VISIBLE);
            holder.label05.setText(bean2.getTags_info().get(4).getText());
        }
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeliciousItemClick.itemClick(bean2.getDishes_id()+"");
            }
        });
        return ret;
    }

    public static class ViewHolder {
        private SimpleDraweeView icon;
        private TextView title;
        private TextView content;
        private TextView label01;
        private TextView label02;
        private TextView label03;
        private TextView label04;
        private TextView label05;
        private FrameLayout play;

        public ViewHolder(View view) {
            icon = (SimpleDraweeView) view.findViewById(R.id.life_delicious_list_image);
            title = (TextView)view.findViewById(R.id.life_delicious_list_title);
            content = (TextView)view.findViewById(R.id.life_delicious_list_content);
            label01 = (TextView)view.findViewById(R.id.life_delicious_list_label01);
            label02 = (TextView)view.findViewById(R.id.life_delicious_list_label02);
            label03 = (TextView)view.findViewById(R.id.life_delicious_list_label03);
            label04 = (TextView)view.findViewById(R.id.life_delicious_list_label04);
            label05 = (TextView)view.findViewById(R.id.life_delicious_list_label05);
            play = (FrameLayout) view.findViewById(R.id.life_delicious_list_play);
        }
    }
    public interface DeliciousItemClick{
        void itemClick(String dishes_id);
    }
    private DeliciousItemClick mDeliciousItemClick;

    public void setDeliciousItemClick(DeliciousItemClick deliciousItemClick) {
        mDeliciousItemClick = deliciousItemClick;
    }
}
