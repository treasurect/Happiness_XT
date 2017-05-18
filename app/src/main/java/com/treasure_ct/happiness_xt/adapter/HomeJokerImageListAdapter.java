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
import com.treasure_ct.happiness_xt.bean.HomeJokerImageListBean;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by treasure on 2017.04.03.
 */

public class HomeJokerImageListAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeJokerImageListBean.DataBeanX.DataBean> list;
    private LayoutInflater mInflater;

    public HomeJokerImageListAdapter(Context context, List<HomeJokerImageListBean.DataBeanX.DataBean> list) {
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
            ret = mInflater.inflate(R.layout.home_joker_list_image_item, parent, false);
        }
        ret.setTag(new ViewHolder(ret));
        final HomeJokerImageListBean.DataBeanX.DataBean dataBeanX = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (dataBeanX.getGroup() != null) {
            if (dataBeanX.getGroup().getUser() != null) {
                if (!Tools.isNull(dataBeanX.getGroup().getUser().getAvatar_url())) {
                    holder.userIcon.setImageURI(Uri.parse(dataBeanX.getGroup().getUser().getAvatar_url()));
                }
                if (!Tools.isNull(dataBeanX.getGroup().getUser().getName())) {
                    holder.userName.setText(dataBeanX.getGroup().getUser().getName());
                }
            }
            if (dataBeanX.getGroup().getLarge_image() != null) {
                if (dataBeanX.getGroup().getLarge_image().getUrl_list() != null) {
                    if (dataBeanX.getGroup().getLarge_image().getUrl_list().get(0).getUrl() != null) {
                        holder.image.setImageURI(Uri.parse(dataBeanX.getGroup().getLarge_image().getUrl_list().get(0).getUrl()));
                    }
                }
            }else if (dataBeanX.getGroup().getLarge_image_list() != null){
                if (!Tools.isNull(dataBeanX.getGroup().getLarge_image_list().get(0).getUrl())){
                    DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                            .setAutoPlayAnimations(true)
                            .setUri(Uri.parse(dataBeanX.getGroup().getLarge_image_list().get(0).getUrl()))//设置uri
                            .build();
                    holder.image.setController(mDraweeController);
                }
            }
            if (!Tools.isNull(dataBeanX.getGroup().getContent())) {
                holder.content.setText(dataBeanX.getGroup().getContent());
            }
            if (!Tools.isNull(String.valueOf(dataBeanX.getGroup().getDigg_count()))) {
                holder.top.setText("顶：" + dataBeanX.getGroup().getDigg_count());
            }
            if (!Tools.isNull(String.valueOf(dataBeanX.getGroup().getRepin_count()))) {
                holder.low.setText("踩：" + dataBeanX.getGroup().getRepin_count());
            }
            if (!Tools.isNull(String.valueOf(dataBeanX.getGroup().getHas_comments()))) {
                holder.comments.setText("留言：" + dataBeanX.getGroup().getHas_comments());
            }
            if (!Tools.isNull(String.valueOf(dataBeanX.getGroup().getShare_count()))) {
                holder.transPond.setText("转发：" + dataBeanX.getGroup().getShare_count());
            }
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Tools.isNull(dataBeanX.getGroup().getShare_url())) {
                    mIsClickItemInterface.isClickItem(dataBeanX.getGroup().getShare_url());
                }
            }
        });
        return ret;
    }

    public static class ViewHolder {
        private LinearLayout layout;
        private TextView userName;
        private SimpleDraweeView userIcon;
        private TextView content;
        private TextView top;
        private TextView low;
        private TextView comments;
        private TextView transPond;
        private SimpleDraweeView image;

        public ViewHolder(View view) {
            userIcon = ((SimpleDraweeView) view.findViewById(R.id.home_joker_item_userIcon));
            userName = (TextView) view.findViewById(R.id.home_joker_item_userName);
            content = (TextView) view.findViewById(R.id.home_joker_item_content);
            image = (SimpleDraweeView) view.findViewById(R.id.home_joker_item_image);
            top = (TextView) view.findViewById(R.id.home_joker_item_top);
            low = (TextView) view.findViewById(R.id.home_joker_item_low);
            comments = (TextView) view.findViewById(R.id.home_joker_item_comments);
            transPond = (TextView) view.findViewById(R.id.home_joker_item_transPond);
            layout = (LinearLayout) view.findViewById(R.id.home_joker_item_layout);
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
