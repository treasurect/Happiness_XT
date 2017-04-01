package com.treasure_ct.happiness_xt.activity.assistant;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.AssistantWeatherFutureAdapter;
import com.treasure_ct.happiness_xt.bean.AssistantWeatherFutureBean;
import com.treasure_ct.happiness_xt.bean.MobAPIWeatherResultBean;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LifeAssistantWeatherActivity extends BaseActivity implements View.OnClickListener {

    private TextView now_temp, now_fine, now_range, now_air;
    private TextView publish_time, publish_humidity, publish_pollution, publish_wind, publish_cold, publish_dress, publish_exercise;
    private ListView listView;
    private List<AssistantWeatherFutureBean> list;
    private AssistantWeatherFutureAdapter adapter;
    private ImageView btn_cityList;
    private TextView now_city;
    private ImageView btn_refresh;
    private Handler mHandler = new Handler(){
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
                    List<MobAPIWeatherResultBean.ResultBean.FutureBean> futureList = resultBean.getResult().get(0).getFuture();
                    list.clear();
                    for (int i = 1; i < futureList.size(); i++) {
                        AssistantWeatherFutureBean futureBean = new AssistantWeatherFutureBean();
                        futureBean.setDate(futureList.get(i).getDate());
                        futureBean.setWeather(futureList.get(i).getDayTime());
                        futureBean.setTemp_range(futureList.get(i).getTemperature());
                        list.add(futureBean);
                    }
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private MobAPIWeatherResultBean resultBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_assistant_weather);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        initFindId();
        initListView();
        initClick();
        getWeatherDetail(StringContents.MobAPI_APPKEY, "石景山", "北京");
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
        listView = (ListView) findViewById(R.id.assistant_weather_now_listView);
        btn_cityList = (ImageView) findViewById(R.id.assistant_weather_cityList);
        now_city = (TextView) findViewById(R.id.assistant_weather_now_city);
        btn_refresh = (ImageView) findViewById(R.id.assistant_weather_refresh);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new AssistantWeatherFutureAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void initClick() {
        btn_cityList.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);
    }

    private void getWeatherDetail(String key, String city, String province) {
        final Request request = new Request.Builder()
                .url(StringContents.MobAPI_BaseUrl + "weather/query?key=" + key + "&city=" + city + "&province=" + province)
                .get()
                .tag(this)
                .build();
        new OkHttpClient().newCall(request)
                .enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.d("~~~~~~~~~~~~~~~~onFailure", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                     resultBean = ModelParseHelper.parseWeatherResult(response.body().string());
                    Message message = new Message();
                    message.what = 200;
                    mHandler.sendMessage(message);
                }
            }
        });
//        OkHttpUtils.get()
//                .url(StringContents.MobAPI_BaseUrl + "weather/query")
//                .addParams("key", key)
//                .addParams("city", city)
//                .addParams("province", province)
//                .tag(this)
//                .build().execute(new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//                LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~data:", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                MobAPIWeatherResultBean resultBean = ModelParseHelper.parseWeatherResult(response);
//                if (resultBean.getRetCode().equals("200")) {

//                }
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assistant_weather_cityList:
                break;
            case R.id.assistant_weather_refresh:
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn_refresh, "Rotation", -360, 0);
                // 设置持续时间
                objectAnimator.setDuration(1000);
                // 设置循环播放
                objectAnimator.setRepeatCount(1);

                objectAnimator.start();
                getWeatherDetail(StringContents.MobAPI_APPKEY, "石景山", "北京");
                Toast.makeText(this, "最新数据已更新", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
