package com.treasure_ct.happiness_xt.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.life.LifeWeatherCityListActivity;
import com.treasure_ct.happiness_xt.adapter.LifeTrafficTrainAdapter;
import com.treasure_ct.happiness_xt.bean.LifeTrafficTrainBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeTrafficTrainFragment extends Fragment implements View.OnClickListener {
    private TextView station_start, station_end, station_query;
    private ImageView station_exchange;
    private ListView station_result;
    private List<LifeTrafficTrainBean.ResultBean> list;
    private LifeTrafficTrainAdapter adapter;
    private LifeTrafficTrainBean trainBean;
    private String error;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    list.addAll(trainBean.getResult());
                    adapter.notifyDataSetChanged();
                    result_progress.setVisibility(View.GONE);
                    break;
                case 201:
                    Toast.makeText(getContext(), "未查询到火车信息", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    result_progress.setVisibility(View.GONE);
                    break;
                case 202:
                    String start1 = station_start.getText().toString().trim();
                    String end1 = station_end.getText().toString().trim();
                    station_start.setText(end1);
                    station_end.setText(start1);
                    break;
                case 400:
                    Toast.makeText(getContext(), "原因：" + error, Toast.LENGTH_SHORT).show();
                    result_progress.setVisibility(View.GONE);
                    break;
            }
        }
    };
    private int screen_width;
    private int screen_height;
    private ProgressBar result_progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traffic_train, container, false);
        initFindId(view);
        initClick();
        initListView();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screen_width = metrics.widthPixels;
        screen_height = metrics.heightPixels;
        return view;
    }

    private void initFindId(View view) {
        station_start = (TextView) view.findViewById(R.id.train_station_start);
        station_end = (TextView) view.findViewById(R.id.train_station_end);
        station_exchange = (ImageView) view.findViewById(R.id.train_station_exchange);
        station_query = (TextView) view.findViewById(R.id.train_station_query);
        station_result = (ListView) view.findViewById(R.id.train_station_result);
        result_progress = (ProgressBar) view.findViewById(R.id.train_station_result_progress);
    }

    private void initClick() {
        station_exchange.setOnClickListener(this);
        station_query.setOnClickListener(this);
        station_start.setOnClickListener(this);
        station_end.setOnClickListener(this);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new LifeTrafficTrainAdapter(getContext(), list);
        station_result.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.train_station_start:
                Intent intent = new Intent(getContext(), LifeWeatherCityListActivity.class);
                startActivityForResult(intent, 201);
                break;
            case R.id.train_station_end:
                Intent intent2 = new Intent(getContext(), LifeWeatherCityListActivity.class);
                startActivityForResult(intent2, 202);
                break;
            case R.id.train_station_exchange:
                TranslateAnimation translateAnimation = new TranslateAnimation(0, screen_width / 2, 0, 0);
                translateAnimation.setDuration(1500);
                translateAnimation.setRepeatMode(Animation.REVERSE);
                station_start.startAnimation(translateAnimation);

                TranslateAnimation translateAnimation2 = new TranslateAnimation(0, -screen_width / 2, 0, 0);
                translateAnimation2.setDuration(1500);
                translateAnimation2.setRepeatMode(Animation.INFINITE);
                translateAnimation2.setRepeatCount(0);
                station_end.startAnimation(translateAnimation2);

                RotateAnimation rotateAnimation = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setFillAfter(false);
                station_exchange.startAnimation(rotateAnimation);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            handler.sendMessage(handler.obtainMessage(202));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.train_station_query:
                String start = station_start.getText().toString().trim();
                String end = station_end.getText().toString();
                getTrainInfo(start, end);
                result_progress.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void getTrainInfo(String start, String end) {
        String url = "http://apicloud.mob.com/train/tickets/queryByStationToStation?key="+ StringContents.MobAPI_APPKEY +"&start=" + start + "&end=" + end;
        HttpHelper.doGetCall(url, getContext(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                error = e.getMessage();
                handler.sendMessage(handler.obtainMessage(400));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String resp = response.body().string();
                trainBean = ModelParseHelper.parsetrainResult(resp);
                list.clear();
                if (trainBean != null) {
                    if (trainBean.getResult() != null) {
                        handler.sendMessage(handler.obtainMessage(200));
                    } else {
                        handler.sendMessage(handler.obtainMessage(201));
                    }
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 201:
                if (resultCode == 201) {
                    String district = data.getExtras().getString("district");
                    if (!Tools.isNull(district)) {
                        station_start.setText(district);
                    }
                }
                break;
            case 202:
                if (resultCode == 201) {
                    String district = data.getExtras().getString("district");
                    if (!Tools.isNull(district)) {
                        station_end.setText(district);
                    }
                }
                break;
        }
    }
}
