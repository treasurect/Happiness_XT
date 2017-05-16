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
import com.treasure_ct.happiness_xt.bean.HomeJokerCommentsBean;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by treasure on 2017.04.03.
 */

public class HomeJokerTopCommentsAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeJokerCommentsBean.DataBean.TopCommentsBean> list;
    private LayoutInflater mInflater;

    public HomeJokerTopCommentsAdapter(Context context, List<HomeJokerCommentsBean.DataBean.TopCommentsBean> list) {
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
            ret = mInflater.inflate(R.layout.home_joker_top_commtemts_item, parent, false);
        }
        ret.setTag(new ViewHolder(ret));
        HomeJokerCommentsBean.DataBean.TopCommentsBean topCommentsBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (topCommentsBean != null){
            if (!Tools.isNull(topCommentsBean.getAvatar_url())){
                holder.userIcon.setImageURI(Uri.parse(topCommentsBean.getAvatar_url()));
            }
            if (!Tools.isNull(topCommentsBean.getUser_name())){
                holder.userName.setText(topCommentsBean.getUser_name());
            }
            if (!Tools.isNull(String.valueOf(topCommentsBean.getCreate_time()))){
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(topCommentsBean.getCreate_time());
                holder.time.setText(time.substring(5, 7) + "月" + time.substring(8, 10) + "日" + time.substring(11, 16));
            }
            if (!Tools.isNull(topCommentsBean.getText())){
                holder.content.setText(topCommentsBean.getText());
            }
            if (!Tools.isNull(String.valueOf(topCommentsBean.getBury_count()))){
                holder.top.setText(topCommentsBean.getBury_count()+"");
            }
        }
        return ret;
    }

    public static class ViewHolder {
        private TextView userName;
        private SimpleDraweeView userIcon;
        private TextView content;
        private TextView top;
        private TextView time;

        public ViewHolder(View view) {
            userIcon = ((SimpleDraweeView) view.findViewById(R.id.home_joker_top_item_userIcon));
            userName = (TextView) view.findViewById(R.id.home_joker_top_item_userName);
            content = (TextView) view.findViewById(R.id.home_joker_top_item_content);
            top = (TextView) view.findViewById(R.id.home_joker_top_item_num);
            time = (TextView) view.findViewById(R.id.home_joker_top_item_userTime);

        }
    }

    public interface jokerItemInterface {
        void isClickItem(HomeJokerListBean.DataBeanXX.DataBeanX.GroupBean groupBean);
    }

    private jokerItemInterface mJokerItemInterface;

    public void setJokerItemInterface(jokerItemInterface jokerItemInterface) {
        mJokerItemInterface = jokerItemInterface;
    }
}