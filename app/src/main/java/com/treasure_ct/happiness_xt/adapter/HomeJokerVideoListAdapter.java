package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.bean.HomeJokerVideoListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by treasure on 2017.04.03.
 */

public class HomeJokerVideoListAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeJokerVideoListBean.DataBeanX.DataBean> list;
    private LayoutInflater mInflater;
    private Bitmap mBitmap;
    private ViewHolder holder;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    holder.videoView.thumbImageView.setImageBitmap(mBitmap);
                    break;
            }
        }
    };

    public HomeJokerVideoListAdapter(Context context, List<HomeJokerVideoListBean.DataBeanX.DataBean> list) {
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
            ret = mInflater.inflate(R.layout.home_joker_list_video_item, parent, false);
        }
        ret.setTag(new ViewHolder(ret));
        final HomeJokerVideoListBean.DataBeanX.DataBean dataBeanX = list.get(position);
         holder = (ViewHolder) ret.getTag();

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
        if (!Tools.isNull(dataBeanX.getGroup().getMp4_url())){
            holder.videoView.setUp(dataBeanX.getGroup().getMp4_url(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"");
            if (dataBeanX.getGroup().getMedium_cover() != null){
                if (dataBeanX.getGroup().getMedium_cover().getUrl_list() != null){
                    if (dataBeanX.getGroup().getMedium_cover().getUrl_list().get(0).getUrl() != null){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mBitmap = Tools.getBitmap(dataBeanX.getGroup().getMedium_cover().getUrl_list().get(0).getUrl());
                                mHandler.sendMessage(mHandler.obtainMessage(200));
                            }
                        }).start();
                    }
                }
            }
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Tools.isNull(dataBeanX.getGroup().getShare_url())){
                    mIsClickItemInterface.isClickItem(dataBeanX.getGroup().getShare_url());
                }
            }
        });
        return ret;
    }

    public static class ViewHolder {
        private JCVideoPlayerStandard videoView;
        private LinearLayout layout;
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
            videoView = (JCVideoPlayerStandard) view.findViewById(R.id.home_joker_item_video);
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
