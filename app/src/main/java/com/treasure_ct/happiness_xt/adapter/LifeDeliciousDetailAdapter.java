package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.LifeDeliciousDetailBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.List;

/**
 * Created by treasure on 2016.11.13.
 */

public class LifeDeliciousDetailAdapter extends BaseAdapter{
    private Context context;
    private List<LifeDeliciousDetailBean.DataBean.StepBean>list;
    private LayoutInflater mInflater;

    public LifeDeliciousDetailAdapter(Context context, List<LifeDeliciousDetailBean.DataBean.StepBean> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
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
        if (view != null){
            ret = view;
        }else {
            ret = mInflater.inflate(R.layout.life_delicious_detail_item,viewGroup,false);
        }
        ret.setTag(new ViewHolder(ret));
        LifeDeliciousDetailBean.DataBean.StepBean bean = list.get(i);
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (!Tools.isNull(bean.getDishes_step_desc())) {
            holder.text.setText(bean.getDishes_step_desc());
        }
        if (!Tools.isNull(bean.getDishes_step_image())) {
            holder.image.setImageURI(Uri.parse(bean.getDishes_step_image()));
        }
        return ret;
    }
    public static class ViewHolder{
        private SimpleDraweeView image;
        private TextView text;
        public ViewHolder(View view) {
            image = ((SimpleDraweeView) view.findViewById(R.id.delicious_detail_list_item_image));
            text = (TextView) view.findViewById(R.id.delicious_detail_list_item_text);
        }
    }
}
