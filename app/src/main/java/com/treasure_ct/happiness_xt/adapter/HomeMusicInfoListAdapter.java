package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeMusicInfoListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public class HomeMusicInfoListAdapter extends BaseAdapter{
    private Context context;
    private List<HomeMusicInfoListBean> list;
    private LayoutInflater inflater;

    public HomeMusicInfoListAdapter(Context context, List<HomeMusicInfoListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list!= null){
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        View ret = null;
        if (view != null){
            ret = view;
        }else {
            ret = inflater.inflate(R.layout.home_music_list_item,viewGroup,false);
        }
        ret.setTag(new ViewHolder(ret));
        HomeMusicInfoListBean listBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        holder.image.setImageBitmap(listBean.getImage());
        holder.title.setText(listBean.getTitle());
        holder.singer.setText(listBean.getArtist());
        holder.duration.setText(listBean.getTime());
        return ret;
    }
    public static class ViewHolder{
        private ImageView image;
        private TextView title;
        private TextView singer;
        private TextView duration;
        public ViewHolder(View view) {
            image = (ImageView)view.findViewById(R.id.home_music_item_image);
            title = (TextView) view.findViewById(R.id.home_music_item_title);
            singer = (TextView) view.findViewById(R.id.home_music_item_singer);
            duration = (TextView) view.findViewById(R.id.home_music_item_duration);
        }
    }
}
