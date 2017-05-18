package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeGossipListBean;
import com.treasure_ct.happiness_xt.bean.HomeJokerImageListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.List;

/**
 * Created by treasure on 2017.04.03.
 */

public class HomeGossipListAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeGossipListBean.DataBean.ListBean> list;
    private LayoutInflater mInflater;

    public HomeGossipListAdapter(Context context, List<HomeGossipListBean.DataBean.ListBean> list) {
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
            ret = mInflater.inflate(R.layout.home_gossip_list_item, parent, false);
        }
        ret.setTag(new ViewHolder(ret));
        final HomeGossipListBean.DataBean.ListBean listBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (listBean != null) {

            if (!Tools.isNull(listBean.getPic())) {
                holder.image.setImageURI(Uri.parse(listBean.getPic()));
            }
            if (!Tools.isNull(listBean.getTitle())) {
                holder.title.setText(listBean.getTitle());
            }
            if (!Tools.isNull(String.valueOf(listBean.getReadnum()))) {
                holder.scan.setText(listBean.getReadnum()+"");
            }
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBean != null) {
                    mIsClickItemInterface.isClickItem(String.valueOf(listBean.getId()));
                }
            }
        });
        return ret;
    }

    public static class ViewHolder {
        private LinearLayout layout;
        private SimpleDraweeView image;
        private TextView title;
        private TextView scan;

        public ViewHolder(View view) {
            image = ((SimpleDraweeView) view.findViewById(R.id.home_gossip_list_image));
            title = (TextView) view.findViewById(R.id.home_gossip_list_title);
            scan = (TextView) view.findViewById(R.id.home_gossip_list_scan);
            layout = (LinearLayout) view.findViewById(R.id.home_gossip_list_layout);
        }
    }

    public interface isClickItemInterface {
        void isClickItem(String id);
    }

    private isClickItemInterface mIsClickItemInterface;

    public void setIsClickItemInterface(isClickItemInterface isClickItemInterface) {
        mIsClickItemInterface = isClickItemInterface;
    }
}
