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
import com.treasure_ct.happiness_xt.bean.HomeNewsTopListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.List;

/**
 * Created by treasure on 2017.04.03.
 */

public class HomeNewsTopListAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeNewsTopListBean.ItemBean> list;
    private LayoutInflater mInflater;

    public HomeNewsTopListAdapter(Context context, List<HomeNewsTopListBean.ItemBean> list) {
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
        HomeNewsTopListBean.ItemBean bean = list.get(position);
        if (bean.getStyle().getView().equals("titleimg")) {
            ret = 1;
        } else if (bean.getStyle().getView().equals("bigimg")||bean.getStyle().getView().equals("bigtopic")) {
            ret = 2;
        } else if (bean.getStyle().getView().equals("slideimg")) {
            ret = 3;
        } else if (bean.getStyle().getView().equals("singletitle")) {
            ret = 4;
        }
        return ret;
    }

    @Override
    public int getViewTypeCount() {
        return 5;
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
            } else if (getItemViewType(position) == 3) {
                ret = mInflater.inflate(R.layout.home_news_listview_nultiple_item, parent, false);
            } else if (getItemViewType(position) == 4) {
                ret = mInflater.inflate(R.layout.home_news_listview_noneimage_item, parent, false);
            } else {
                ret = mInflater.inflate(R.layout.home_news_listview_nultiple_item, parent, false);
            }
        }
        ret.setTag(new ViewHolder(ret));
        final HomeNewsTopListBean.ItemBean bean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (!Tools.isNull(bean.getTitle())) {
            holder.title.setText(bean.getTitle());
        }
        if (!Tools.isNull(bean.getSource())) {
            holder.from.setText(bean.getSource());
        }
        if (!Tools.isNull(bean.getUpdateTime())) {
            holder.time.setText(bean.getUpdateTime().substring(11, 16));
        }
        if (!Tools.isNull(bean.getCommentsall())) {
            holder.liuyan.setText(bean.getCommentsall());
        }

        if (getItemViewType(position) == 3) {
            if (!Tools.isNull(bean.getStyle().getImages().get(0))) {
                holder.image.setImageURI(Uri.parse(bean.getStyle().getImages().get(0)));
            }
            if (!Tools.isNull(bean.getStyle().getImages().get(1))) {
                holder.image2.setImageURI(Uri.parse(bean.getStyle().getImages().get(1)));
            }
            if (!Tools.isNull(bean.getStyle().getImages().get(2))) {
                holder.image3.setImageURI(Uri.parse(bean.getStyle().getImages().get(2)));
            }
        } else if (getItemViewType(position) == 4) {

        } else {
            if (!Tools.isNull(bean.getThumbnail())) {
                holder.image.setImageURI(Uri.parse(bean.getThumbnail()));
            }
        }
        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTopItemInterface.isClickItem(bean.getLink().getWeburl());
            }
        });
        return ret;
    }

    public static class ViewHolder {
        private TextView title;
        private SimpleDraweeView image;
        private SimpleDraweeView image2;
        private SimpleDraweeView image3;
        private TextView from;
        private TextView time;
        private TextView liuyan;
        private LinearLayout item_layout;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.home_news_item_title);
            from = (TextView) view.findViewById(R.id.home_news_item_from);
            time = (TextView) view.findViewById(R.id.home_news_item_time);
            liuyan = (TextView) view.findViewById(R.id.home_news_item_liuyan);
            image = (SimpleDraweeView) view.findViewById(R.id.home_news_item_image);
            image2 = (SimpleDraweeView) view.findViewById(R.id.home_news_item_image2);
            image3 = (SimpleDraweeView) view.findViewById(R.id.home_news_item_image3);
            item_layout = (LinearLayout) view.findViewById(R.id.home_news_item_layout);
        }
    }

    public interface topItemInterface {
        void isClickItem(String url);
    }

    private topItemInterface mTopItemInterface;

    public void setTopItemInterface(topItemInterface topItemInterface) {
        mTopItemInterface = topItemInterface;
    }
}
