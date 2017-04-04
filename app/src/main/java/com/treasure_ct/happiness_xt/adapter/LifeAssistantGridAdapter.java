package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.AssistantGridBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class LifeAssistantGridAdapter extends BaseAdapter {
    private Context context;
    private List<AssistantGridBean> list;
    private LayoutInflater inflater;

    public LifeAssistantGridAdapter(Context context, List<AssistantGridBean> list) {
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        View ret = null;
        if (view != null) {
            ret = view;
        } else {
            ret = inflater.inflate(R.layout.assistant_grid_item, viewGroup, false);
        }
        ret.setTag(new ViewHolder(ret));
        final AssistantGridBean gridBean = list.get(position);
        final ViewHolder holder = (ViewHolder) ret.getTag();
        holder.textView.setText(gridBean.getText());
        holder.imageView.setImageResource(gridBean.getImage());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeAssistantClickItem.assistantClickItem(gridBean.getText());
            }
        });
        return ret;
    }

    private static class ViewHolder {
        private LinearLayout layout;
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.grid_list_icon);
            textView = (TextView) view.findViewById(R.id.grid_list_name);
            layout = ((LinearLayout) view.findViewById(R.id.grid_list_layout));
        }
    }
    public interface LifeAssistantClickItem{
        void assistantClickItem(String name);
    }
    public LifeAssistantClickItem lifeAssistantClickItem;

    public void setLifeAssistantClickItem(LifeAssistantClickItem lifeAssistantClickItem) {
        this.lifeAssistantClickItem = lifeAssistantClickItem;
    }
}
