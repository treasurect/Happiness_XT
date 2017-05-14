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
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.List;

/**
 * Created by treasure on 2017.04.03.
 */

public class HomeJokerListAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeJokerListBean.DataBeanXX.DataBeanX> list;
    private LayoutInflater mInflater;

    public HomeJokerListAdapter(Context context, List<HomeJokerListBean.DataBeanXX.DataBeanX> list) {
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
        final HomeJokerListBean.DataBeanXX.DataBeanX dataBeanX = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (dataBeanX.getGroup() != null){
            if (dataBeanX.getGroup().getUser() != null){
                if (!Tools.isNull(dataBeanX.getGroup().getUser().getAvatar_url())) {
                    holder.userIcon.setImageURI(Uri.parse(dataBeanX.getGroup().getUser().getAvatar_url()));
                }
                if (!Tools.isNull(dataBeanX.getGroup().getUser().getName())) {
                    holder.userName.setText(dataBeanX.getGroup().getUser().getName());
                }
            }
            if (!Tools.isNull(dataBeanX.getGroup().getContent())) {
                holder.content.setText(dataBeanX.getGroup().getContent());
            }
            if (!Tools.isNull(String.valueOf(dataBeanX.getGroup().getDigg_count()))) {
                holder.top.setText("" + dataBeanX.getGroup().getDigg_count());
            }
            if (!Tools.isNull(String.valueOf(dataBeanX.getGroup().getRepin_count()))) {
                holder.low.setText("" + dataBeanX.getGroup().getRepin_count());
            }
            if (!Tools.isNull(String.valueOf(dataBeanX.getGroup().getHas_comments()))) {
                holder.comments.setText("" + dataBeanX.getGroup().getHas_comments());
            }
            if (!Tools.isNull(String.valueOf(dataBeanX.getGroup().getShare_count()))) {
                holder.transPond.setText("" + dataBeanX.getGroup().getShare_count());
            }
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBeanX.getGroup() != null){
                    mJokerItemInterface.isClickItem(dataBeanX.getGroup());
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
        void isClickItem(HomeJokerListBean.DataBeanXX.DataBeanX.GroupBean groupBean);
    }

    private jokerItemInterface mJokerItemInterface;

    public void setJokerItemInterface(jokerItemInterface jokerItemInterface) {
        mJokerItemInterface = jokerItemInterface;
    }
}
