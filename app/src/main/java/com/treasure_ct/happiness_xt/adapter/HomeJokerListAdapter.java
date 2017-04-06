package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.List;

/**
 * Created by treasure on 2017.04.03.
 */

public class HomeJokerListAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeJokerListBean.BodyBean> list;
    private LayoutInflater mInflater;

    public HomeJokerListAdapter(Context context, List<HomeJokerListBean.BodyBean> list) {
        mContext = context;
        this.list = list;
        mInflater = LayoutInflater.from(mContext);
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
    public int getItemViewType(int position) {
        int ret = 0;
        HomeJokerListBean.BodyBean bodyBean = list.get(position);
        if (bodyBean.getThumbnail().equals("")) {
            ret = 1;
        } else {
            ret = 2;
        }
        return ret;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        } else {
            if (getItemViewType(position) == 1) {
                ret = mInflater.inflate(R.layout.home_news_listview_single_item, parent, false);
            } else if (getItemViewType(position) == 2) {
                ret = mInflater.inflate(R.layout.home_news_listview_single_big_item, parent, false);
            }
        }
        ret.setTag(new ViewHolder(ret));
        HomeJokerListBean.BodyBean bodyBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (!Tools.isNull(bodyBean.getContent())){
            holder.title.setText(bodyBean.getContent());
        }
        if (getItemViewType(position)==2){
//            holder.image.setImageURI(Uri.parse(bodyBean.getThumbnail()));
        }
        return ret;
    }

    public static class ViewHolder {
        private TextView title;
        private SimpleDraweeView image;
        private TextView top;
        private TextView low;
        private TextView liuyan;
        private TextView collection;
        private TextView transPond;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.home_joker_item_content);
            image = (SimpleDraweeView) view.findViewById(R.id.home_joker_item_image);
            top = (TextView) view.findViewById(R.id.home_joker_item_top);
            low = (TextView) view.findViewById(R.id.home_joker_item_low);
            liuyan = (TextView) view.findViewById(R.id.home_joker_item_liuyan);
            collection = (TextView) view.findViewById(R.id.home_joker_item_collection);
            transPond = (TextView) view.findViewById(R.id.home_joker_item_transPond);
        }
    }

    public interface isClickItemInterface {
        void isClickItem(String url);
    }

    private isClickItemInterface mIsClickItemInterface;

    public void setIsClickItemInterface(isClickItemInterface isClickItemInterface) {
        mIsClickItemInterface = isClickItemInterface;
    }
}
