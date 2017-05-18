package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeEmbarrassListBean;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.List;

/**
 * Created by treasure on 2017.04.03.
 */

public class HomeEmbarrassListAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeEmbarrassListBean.ItemsBean> list;
    private LayoutInflater mInflater;

    public HomeEmbarrassListAdapter(Context context, List<HomeEmbarrassListBean.ItemsBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        } else {
            ret = mInflater.inflate(R.layout.home_joker_list_text_item, parent, false);
        }
        ret.setTag(new ViewHolder(ret));
        final HomeEmbarrassListBean.ItemsBean itemsBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (itemsBean != null){
            if (itemsBean.getUser() != null){
                if (!Tools.isNull(itemsBean.getUser().getThumb())) {
                    holder.userIcon.setImageURI(Uri.parse("http:"+itemsBean.getUser().getThumb()));
                }
                if (!Tools.isNull(itemsBean.getUser().getLogin())) {
                    holder.userName.setText(itemsBean.getUser().getLogin());
                }
            }
            if (!Tools.isNull(itemsBean.getContent())) {
                holder.content.setText(itemsBean.getContent());
            }
            if (itemsBean.getVotes() != null){
                if (!Tools.isNull(String.valueOf(itemsBean.getVotes().getUp()))){
                    holder.top.setText(String.valueOf(itemsBean.getVotes().getUp()));
                }
                if (!Tools.isNull(String.valueOf(itemsBean.getVotes().getDown()))){
                    if (String.valueOf(itemsBean.getVotes().getDown()).length() > 0){
                        holder.low.setText(String.valueOf(itemsBean.getVotes().getDown()).substring(1));
                    }
                }
            }

            if (!Tools.isNull(String.valueOf(itemsBean.getComments_count()))) {
                holder.comments.setText(String.valueOf(itemsBean.getComments_count()));
            }
            if (!Tools.isNull(String.valueOf(itemsBean.getShare_count()))) {
                holder.transPond.setText(String.valueOf(itemsBean.getShare_count()));
            }
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemsBean != null){
                    mJokerItemInterface.isClickItem(itemsBean);
                }
            }
        });
        return ret;
    }

    public static class ViewHolder {
        private  LinearLayout layout;
        private TextView userName;
        private SimpleDraweeView userIcon;
        private TextView content;
        private TextView top;
        private TextView low;
        private TextView comments;
        private TextView transPond;

        public ViewHolder(View view) {
            userIcon = ((SimpleDraweeView) view.findViewById(R.id.home_joker_item_userIcon));
            userName = (TextView) view.findViewById(R.id.home_joker_item_userName);
            content = (TextView) view.findViewById(R.id.home_joker_item_content);
            top = (TextView) view.findViewById(R.id.home_joker_item_top);
            low = (TextView) view.findViewById(R.id.home_joker_item_low);
            comments = (TextView) view.findViewById(R.id.home_joker_item_comments);
            transPond = (TextView) view.findViewById(R.id.home_joker_item_transPond);
            layout = (LinearLayout) view.findViewById(R.id.home_joker_item_layout);
        }
    }

    public interface jokerItemInterface {
        void isClickItem(HomeEmbarrassListBean.ItemsBean itemsBean);
    }

    private jokerItemInterface mJokerItemInterface;

    public void setJokerItemInterface(jokerItemInterface jokerItemInterface) {
        mJokerItemInterface = jokerItemInterface;
    }
}
