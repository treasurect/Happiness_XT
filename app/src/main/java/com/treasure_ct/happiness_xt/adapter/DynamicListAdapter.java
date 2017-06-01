package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
import com.treasure_ct.happiness_xt.utils.LogUtil;

import java.util.List;

/**
 * Created by treasure on 2017.04.09.
 */

public class DynamicListAdapter extends BaseAdapter {
    private Context context;
    private List<DynamicBean> list;
    private LayoutInflater inflater;

    public DynamicListAdapter(Context context, List<DynamicBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
        DynamicBean listBean = list.get(position);
        if (listBean.getImage().get(0).equals("0")) {
            ret = 0;
        } else {
            ret = 1;
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
            if (getItemViewType(position) == 0) {
                ret = inflater.inflate(R.layout.dynamic_text_list_item, parent, false);
            } else {
                ret = inflater.inflate(R.layout.dynamic_image_list_item, parent, false);
            }
        }
        ret.setTag(new ViewHolder(ret));
        final DynamicBean listBean = list.get(position);
        final ViewHolder holder = (ViewHolder) ret.getTag();
        if (listBean.getUser_icon().equals("暂无头像")) {

        } else {
            holder.user_icon.setImageURI(Uri.parse(listBean.getUser_icon()));
        }
        LogUtil.d("~~~~~~~~~~~~~~~",listBean.getUser_icon()+"........................");
        holder.user_nick.setText(listBean.getUser_nick());
        holder.publish_time.setText(listBean.getPublish_time());
        holder.content.setText(listBean.getContent());
        if (getItemViewType(position) == 1) {
//                holder.image1.setImageURI(Uri.parse());
//                holder.image2.setImageURI(Uri.parse());
//                holder.image3.setImageURI(Uri.parse());
            if (listBean.getImage().size() > 3) {
                holder.image_num.setText(listBean.getImage().size() - 3);
                holder.image_num.setVisibility(View.VISIBLE);
            }
        }
        holder.sendTop.setText(listBean.getSendTop() + "");
        holder.sendComments.setText(listBean.getComments() + "");
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClick.sendMore(listBean.getUser_nick(), listBean.getContent());
            }
        });
        holder.top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClick.sendTop(listBean.getUser_nick(), listBean.getContent());
            }
        });
        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClick.sendComments(listBean.getUser_nick(), listBean.getContent(), listBean.getPublish_time(), listBean.getSendTop(), listBean.getComments());
            }
        });
        return ret;
    }

    private static class ViewHolder {
        private SimpleDraweeView user_icon;
        private TextView user_nick;
        private TextView publish_time;
        private FrameLayout more;
        private TextView content;
        private SimpleDraweeView image1;
        private SimpleDraweeView image2;
        private SimpleDraweeView image3;
        private TextView image_num;
        private TextView sendTop;
        private ImageView sendTopImage;
        private TextView sendComments;
        private LinearLayout top;
        private LinearLayout comments;

        public ViewHolder(View view) {
            user_icon = ((SimpleDraweeView) view.findViewById(R.id.assistant_dynamic_item_icon));
            user_nick = ((TextView) view.findViewById(R.id.assistant_dynamic_item_nick));
            publish_time = ((TextView) view.findViewById(R.id.assistant_dynamic_item_time));
            more = ((FrameLayout) view.findViewById(R.id.assistant_dynamic_item_more));
            content = ((TextView) view.findViewById(R.id.assistant_dynamic_item_content));
            image1 = ((SimpleDraweeView) view.findViewById(R.id.assistant_dynamic_item_image1));
            image2 = ((SimpleDraweeView) view.findViewById(R.id.assistant_dynamic_item_image2));
            image3 = ((SimpleDraweeView) view.findViewById(R.id.assistant_dynamic_item_image3));
            image_num = ((TextView) view.findViewById(R.id.assistant_record_image_num));
            sendTop = ((TextView) view.findViewById(R.id.assistant_dynamic_item_good_num));
            sendTopImage = ((ImageView) view.findViewById(R.id.assistant_dynamic_item_good_image));
            sendComments = ((TextView) view.findViewById(R.id.assistant_dynamic_item_comments_num));
            top = ((LinearLayout) view.findViewById(R.id.assistant_dynamic_item_good));
            comments = ((LinearLayout) view.findViewById(R.id.assistant_dynamic_item_comments));
        }
    }

    public interface ItemClick {
        void sendMore(String nick, String contents);

        void sendTop(String nick, String contents);

        void sendComments(String nick, String contents, String content, int top_num, int comments_num);
    }

    private ItemClick mItemClick;

    public void setItemClick(ItemClick itemClick) {
        mItemClick = itemClick;
    }
}
