package com.treasure_ct.happiness_xt.activity.assistant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.AssistantWeatherCityListBean;
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

public class LifeAssistantWeatherCityListActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private List<String> pro_list;
    private List<String> city_list;
    private List<String> dis_list;
    private ArrayAdapter<String> pro_adapter;
    private ArrayAdapter<String> city_adapter;
    private ArrayAdapter<String> dis_adapter;
    private ListView pro_listView;
    private ListView city_listView;
    private ListView dis_listView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    pro_list.clear();
                    for (int i = 0; i < mResult.size(); i++) {
                        pro_list.add(mResult.get(i).getProvince());
                    }
                    pro_adapter.notifyDataSetChanged();
                    break;
                case 201:
                    city_list.clear();
                     province = msg.getData().getString("province");
                    for (int i = 0; i < mResult.size(); i++) {
                        if (mResult.get(i).getProvince().equals(province)){
                            List<AssistantWeatherCityListBean.ResultBean.CityBean> cityBeanList = mResult.get(i).getCity();
                            for (int j = 0; j < cityBeanList.size(); j++) {
                                city_list.add(cityBeanList.get(j).getCity());
                            }
                        }
                    }
                    city_adapter.notifyDataSetChanged();
                    break;
                case 202:
                    dis_list.clear();
                     city = msg.getData().getString("city");
                    for (int i = 0; i < mResult.size(); i++) {
                        if (mResult.get(i).getProvince().equals(province)){
                            List<AssistantWeatherCityListBean.ResultBean.CityBean> cityBeanList = mResult.get(i).getCity();
                            for (int j = 0; j < cityBeanList.size(); j++) {
                                if (cityBeanList.get(j).getCity().equals(city)){
                                    List<AssistantWeatherCityListBean.ResultBean.CityBean.DistrictBean> districtBeen = cityBeanList.get(j).getDistrict();
                                    for (int k = 0; k < districtBeen.size(); k++) {
                                        dis_list.add(districtBeen.get(k).getDistrict());
                                    }
                                }
                            }
                        }
                    }
                    dis_adapter.notifyDataSetChanged();
                    break;
                case 400:
                    String error = msg.getData().getString("error");
                    Toast.makeText(LifeAssistantWeatherCityListActivity.this, "原因：" + error, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private List<AssistantWeatherCityListBean.ResultBean> mResult;
    private AssistantWeatherCityListBean cityListBean;
    private String province;
    private String city;
    private int item_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_assistant_weather_city_list);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        btn_back.setImageResource(R.mipmap.icon_return);
        btn_back.setVisibility(View.VISIBLE);
        title.setText("城市列表");

        initFindId();
        initListView();
        initClick();
        getCityList();
    }

    private void initFindId() {
        pro_listView = (ListView) findViewById(R.id.weather_cityList_province_listView);
        city_listView = (ListView) findViewById(R.id.weather_cityList_city_listView);
        dis_listView = (ListView) findViewById(R.id.weather_cityList_district_listView);
    }

    private void initListView() {
        pro_list = new ArrayList<>();
        pro_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pro_list);
        pro_listView.setAdapter(pro_adapter);
        city_list = new ArrayList<>();
        city_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, city_list);
        city_listView.setAdapter(city_adapter);
        dis_list = new ArrayList<>();
        dis_adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dis_list);
        dis_listView.setAdapter(dis_adapter);

    }

    private void initClick() {
        btn_back.setOnClickListener(this);
        pro_listView.setOnItemClickListener(this);
        city_listView.setOnItemClickListener(this);
        dis_listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void getCityList() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(StringContents.MobAPI_BaseUrl + "weather/citys?key=" + StringContents.MobAPI_APPKEY)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Bundle bundle = new Bundle();
                bundle.putString("error", e.getMessage());
                Message message = new Message();
                message.setData(bundle);
                message.what = 400;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                cityListBean = ModelParseHelper.parseCityListResult(string);
                mResult = cityListBean.getResult();

                mHandler.sendMessage(mHandler.obtainMessage(200));
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~~",view.getId()+"");
        switch (parent.getId()) {
            case R.id.weather_cityList_province_listView:
                pro_listView.setVisibility(View.GONE);
                city_listView.setVisibility(View.VISIBLE);
                dis_listView.setVisibility(View.GONE);
                Bundle bundle = new Bundle();
                bundle.putString("province",pro_list.get(position));
                Message message = new Message();
                message.what = 201;
                message.setData(bundle);
                mHandler.sendMessage(message);
                item_num++;
                break;
            case R.id.weather_cityList_city_listView:
                pro_listView.setVisibility(View.GONE);
                city_listView.setVisibility(View.GONE);
                dis_listView.setVisibility(View.VISIBLE);
                Bundle bundle2 = new Bundle();
                bundle2.putString("city",city_list.get(position));
                Message message2 = new Message();
                message2.what = 202;
                message2.setData(bundle2);
                mHandler.sendMessage(message2);
                item_num++;
                break;
            case R.id.weather_cityList_district_listView:
                String district = dis_list.get(position);
                Intent intent = new Intent();
                intent.putExtra("province",province);
                intent.putExtra("city",city);
                intent.putExtra("district",district);
                setResult(201,intent);
                LifeAssistantWeatherCityListActivity.this.finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (item_num == 2){
            pro_listView.setVisibility(View.GONE);
            city_listView.setVisibility(View.VISIBLE);
            dis_listView.setVisibility(View.GONE);
            item_num--;
        }else if (item_num == 1){
            pro_listView.setVisibility(View.VISIBLE);
            city_listView.setVisibility(View.GONE);
            dis_listView.setVisibility(View.GONE);
            item_num--;
        }else if (item_num == 0){
            super.onBackPressed();
        }
    }
}
