package com.treasure_ct.happiness_xt.activity.assistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.utils.Tools;

public class LifeAssistantWeatherCityListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_assistant_weather_city_list);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        btn_back.setImageResource(R.mipmap.icon_return);
        btn_back.setVisibility(View.VISIBLE);
        title.setText("城市列表");
    }
}
