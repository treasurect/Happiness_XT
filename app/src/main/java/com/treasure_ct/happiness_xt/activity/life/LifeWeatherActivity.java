package com.treasure_ct.happiness_xt.activity.life;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.LifeWeatherFutureAdapter;
import com.treasure_ct.happiness_xt.bean.LifeWeatherFutureBean;
import com.treasure_ct.happiness_xt.bean.LifeWeatherResultBean;
import com.treasure_ct.happiness_xt.widget.CustomScrollListView;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LifeWeatherActivity extends BaseActivity implements View.OnClickListener {

    private TextView now_temp, now_fine, now_range, now_air;
    private TextView publish_time, publish_humidity, publish_pollution, publish_wind, publish_cold, publish_dress, publish_exercise;
    private CustomScrollListView listView;
    private List<LifeWeatherFutureBean> list;
    private LifeWeatherFutureAdapter adapter;
    private ImageView btn_cityList;
    private TextView now_city;
    private ImageView btn_refresh;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    now_temp.setText(resultBean.getResult().get(0).getTemperature());
                    now_fine.setText(resultBean.getResult().get(0).getWeather());
                    now_range.setText(resultBean.getResult().get(0).getFuture().get(0).getTemperature());
                    now_air.setText("空气" + resultBean.getResult().get(0).getAirCondition());
                    publish_time.setText("更新时间：" + resultBean.getResult().get(0).getTime());
                    publish_humidity.setText(resultBean.getResult().get(0).getHumidity().substring(3));
                    publish_pollution.setText(resultBean.getResult().get(0).getPollutionIndex());
                    publish_wind.setText(resultBean.getResult().get(0).getWind());
                    publish_cold.setText(resultBean.getResult().get(0).getColdIndex());
                    publish_dress.setText(resultBean.getResult().get(0).getDressingIndex());
                    publish_exercise.setText(resultBean.getResult().get(0).getExerciseIndex());
                    List<LifeWeatherResultBean.ResultBean.FutureBean> futureList = resultBean.getResult().get(0).getFuture();
                    list.clear();
                    for (int i = 1; i < futureList.size(); i++) {
                        LifeWeatherFutureBean futureBean = new LifeWeatherFutureBean();
                        futureBean.setDate(futureList.get(i).getDate());
                        futureBean.setWeather(futureList.get(i).getDayTime());
                        futureBean.setTemp_range(futureList.get(i).getTemperature());
                        list.add(futureBean);
                    }
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);
                    break;
                case 400:
                    Toast.makeText(LifeWeatherActivity.this, "原因："+error, Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                    break;
            }
        }
    };
    private LifeWeatherResultBean resultBean;
    private NestedScrollView scrollView;
    private String province;
    private String city;
    private String district;
    private String error;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_weather);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        initFindId();
        initListView();
        initClick();
        initScrollView();
        receiveIntent();
    }

    private void initFindId() {
        now_temp = (TextView) findViewById(R.id.assistant_weather_now_temperature);
        now_fine = (TextView) findViewById(R.id.assistant_weather_now_fine);
        now_range = (TextView) findViewById(R.id.assistant_weather_now_range);
        now_air = (TextView) findViewById(R.id.assistant_weather_now_air);
        publish_time = (TextView) findViewById(R.id.assistant_weather_publish_time);
        publish_humidity = (TextView) findViewById(R.id.assistant_weather_publish_humidity);
        publish_pollution = (TextView) findViewById(R.id.assistant_weather_publish_pollution);
        publish_wind = (TextView) findViewById(R.id.assistant_weather_publish_wind);
        publish_cold = (TextView) findViewById(R.id.assistant_weather_publish_cold);
        publish_dress = (TextView) findViewById(R.id.assistant_weather_publish_dress);
        publish_exercise = (TextView) findViewById(R.id.assistant_weather_publish_exercise);
        listView = (CustomScrollListView) findViewById(R.id.assistant_weather_now_listView);
        btn_cityList = (ImageView) findViewById(R.id.assistant_weather_cityList);
        now_city = (TextView) findViewById(R.id.assistant_weather_now_city);
        btn_refresh = (ImageView) findViewById(R.id.assistant_weather_refresh);
        scrollView = (NestedScrollView) findViewById(R.id.assistant_weather_now_scrollView);
        progress = (ProgressBar) findViewById(R.id.loading_progress);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new LifeWeatherFutureAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void initClick() {
        btn_cityList.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
    }

    private void getWeatherDetail(String key, String city, String province) {
        progress.setVisibility(View.VISIBLE);
        String url = StringContents.MobAPI_BaseUrl + "/v1/weather/query?key=" + key + "&city=" + city + "&province=" + province;
        HttpHelper.doGetCall(url, this, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                error = e.getMessage();
                mHandler.sendMessage(mHandler.obtainMessage(400));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    resultBean = ModelParseHelper.parseWeatherResult(response.body().string());
                    if (resultBean != null){
                        if (resultBean.getResult() != null){
                            mHandler.sendMessage(mHandler.obtainMessage(200));
                        }
                    }
                }
            }
        });
    }

    private void initScrollView() {
        scrollView.smoothScrollTo(0, 20);
        listView.setFocusable(false);
    }

    private void receiveIntent() {
        Intent intent = getIntent();
        if (!Tools.isNull(intent.getStringExtra("province"))) {
             province = intent.getStringExtra("province");
             city = intent.getStringExtra("city");
             district = intent.getStringExtra("district");
            if (province.equals("北京") || province.equals("天津") || province.equals("上海") || province.equals("重庆")) {
                getWeatherDetail(StringContents.MobAPI_APPKEY, district, city);
                now_city.setText(district);
            } else {
                getWeatherDetail(StringContents.MobAPI_APPKEY, city, province);
                now_city.setText(city);
            }
        }else {
            getWeatherDetail(StringContents.MobAPI_APPKEY, "石景山", "北京");
            now_city.setText("石景山");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assistant_weather_cityList:
                startActivityForResult(new Intent(LifeWeatherActivity.this, LifeWeatherCityListActivity.class),200);
                break;
            case R.id.assistant_weather_refresh:
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn_refresh, "Rotation", -360, 0);
                // 设置持续时间
                objectAnimator.setDuration(1000);
                // 设置循环播放
                objectAnimator.setRepeatCount(1);
                objectAnimator.start();
                if (!Tools.isNull(province)){
                    if (province.equals("北京")||province.equals("上海")||province.equals("天津")||province.equals("重庆")){
                        getWeatherDetail(StringContents.MobAPI_APPKEY,district,province);
                        now_city.setText(district);
                    }else {
                        getWeatherDetail(StringContents.MobAPI_APPKEY,city,province);
                        now_city.setText(district);
                    }
                }else {
                    getWeatherDetail(StringContents.MobAPI_APPKEY,"石景山","北京");
                    now_city.setText("石景山");
                }
                Toast.makeText(this, "最新数据已更新", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 201:
                 province = data.getStringExtra("province");
                 city = data.getStringExtra("city");
                 district = data.getStringExtra("district");
                if (province.equals("北京")||province.equals("上海")||province.equals("天津")||province.equals("重庆")){
                    getWeatherDetail(StringContents.MobAPI_APPKEY,district,province);
                    now_city.setText(district);
                }else {
                    getWeatherDetail(StringContents.MobAPI_APPKEY,city,province);
                    now_city.setText(district);
                }
                break;
        }
    }
}
