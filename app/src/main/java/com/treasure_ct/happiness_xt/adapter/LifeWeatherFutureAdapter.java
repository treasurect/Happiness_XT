package com.treasure_ct.happiness_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.LifeWeatherFutureBean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public class LifeWeatherFutureAdapter extends BaseAdapter{
    private Context mContext;
    private List<LifeWeatherFutureBean> list;
    private LayoutInflater mInflater;

    public LifeWeatherFutureAdapter(Context context, List<LifeWeatherFutureBean> list) {
        mContext = context;
        this.list = list;
        mInflater =LayoutInflater.from(context);
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
        View ret =null;
        if (view != null){
            ret = view;
        }else {
            ret = mInflater.inflate(R.layout.life_weather_future_item,viewGroup,false);
        }
        ret.setTag(new ViewHolder(ret));
        LifeWeatherFutureBean futureBean = list.get(position);
        ViewHolder holder = (ViewHolder) ret.getTag();
        holder.future_date.setText(futureBean.getDate());
        holder.future_weather.setText(futureBean.getWeather());
        holder.future_range.setText(futureBean.getTemp_range());
        return ret;
    }
    public static class ViewHolder{
        private final TextView future_date;
        private final TextView future_weather;
        private final TextView future_range;

        public ViewHolder(View view) {
            future_date = (TextView) view.findViewById(R.id.assistant_weather_future_date);
            future_weather = ((TextView) view.findViewById(R.id.assistant_weather_future_weather));
            future_range = (TextView) view.findViewById(R.id.assistant_weather_future_range);
        }
    }
}
