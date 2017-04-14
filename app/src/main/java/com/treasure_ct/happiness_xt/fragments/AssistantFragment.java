package com.treasure_ct.happiness_xt.fragments;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAllAssistantActivity;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAssistantMapActivity;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAssistantRecordActivity;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAssistantRobotActivity;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAssistantWeatherActivity;
import com.treasure_ct.happiness_xt.adapter.DynamicListAdapter;
import com.treasure_ct.happiness_xt.adapter.LifeAssistantGridAdapter;
import com.treasure_ct.happiness_xt.bean.AssistantPhoneBelongBean;
import com.treasure_ct.happiness_xt.bean.AssistantGridBean;
import com.treasure_ct.happiness_xt.bean.AssistantWeatherResultBean;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.receiver.CommonDataReceiver;
import com.treasure_ct.happiness_xt.custom.CustomScrollListView;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AssistantFragment extends Fragment implements LifeAssistantGridAdapter.LifeAssistantClickItem, View.OnClickListener, BDLocationListener {
    private GridView gridView;
    private String[] assistant_list_text = {"天气预报", "地图", "手机归属地", "空气质量", "健康知识", "汽车信息", "驾考题库", "全部"};
    private int[] assistant_list_image = {R.mipmap.icon_weather, R.mipmap.icon_location, R.mipmap.icon_phone, R.mipmap.icon_air,
            R.mipmap.icon_healthy, R.mipmap.icon_car, R.mipmap.icon_driving, R.mipmap.icon_all};
    private PopupWindow mPopupWindow;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    phoneBelong_city.setText(resultBean.getResult().getCity());
                    phoneBelong_cityCode.setText(resultBean.getResult().getCityCode());
                    phoneBelong_operator.setText(resultBean.getResult().getOperator());
                    phoneBelong_province.setText(resultBean.getResult().getProvince());
                    phoneBelong_zipCode.setText(resultBean.getResult().getZipCode());
                    break;
                case 400:
                    Toast.makeText(getContext(), resultBean.getMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 301:
                    String weather = resultBean2.getResult().get(0).getWeather();
                    if (weather.equals("多云") || weather.equals("少云")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + getContext().getPackageName() + "/" + R.mipmap.gif_cloud))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("晴")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + getContext().getPackageName() + "/" + R.mipmap.gif_fine))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("阴")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + getContext().getPackageName() + "/" + R.mipmap.gif_bad))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("小雨") || weather.equals("雨") || weather.equals("中雨") || weather.equals("阵雨") || weather.equals("零散阵雨") || weather.equals("零散雷雨") || weather.equals("暴雨") || weather.equals("大雨")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + getContext().getPackageName() + "/" + R.mipmap.gif_rain))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("小雪") || weather.equals("雨夹雪") || weather.equals("阵雪") || weather.equals("大雪") || weather.equals("中雪")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + getContext().getPackageName() + "/" + R.mipmap.gif_snow))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("霾")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + getContext().getPackageName() + "/" + R.mipmap.gif_bad))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("雷阵雨")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + getContext().getPackageName() + "/" + R.mipmap.gif_thunder))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    }
                    break;
                case 302:
                    String addr = msg.getData().getString("addr");
                    title.setText(addr);
                    break;
            }
        }
    };
    private AssistantPhoneBelongBean resultBean;
    private AssistantWeatherResultBean resultBean2;
    private TextView phoneBelong_city, phoneBelong_cityCode, phoneBelong_operator, phoneBelong_province, phoneBelong_zipCode;
    private CustomScrollListView listView;
    private List<DynamicBean> list;
    private DynamicListAdapter adapter;
    private NestedScrollView scrollView;
    private TextView record;
    private ImageView camera, robot;
    private IntentFilter filter;
    private CommonDataReceiver commonDataReceiver;
    private FloatingActionButton refresh;
    public LocationClient mLocationClient = null;
    private SimpleDraweeView weather_show;
    private TextView title;
    private String province,city,district;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_assistant, container, false);
        initFindId(view);
        receiveBoardCast();
        initGridView();
        initListView();
        initScrollView();
        initClick();
        if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))) {
            requestDynamicList();
        }
        initiLocation();
        return view;
    }

    private void receiveBoardCast() {
        filter = new IntentFilter();
        commonDataReceiver = new CommonDataReceiver();
        commonDataReceiver.setDoUiReceiver(new CommonDataReceiver.DoUiReceiver() {
            @Override
            public void doUi(Context context, Intent intent) {
                if (intent.getExtras().getString("label").equals("dynamic")) {
                    requestDynamicList();
                }
            }
        });
        getContext().registerReceiver(commonDataReceiver, filter);
    }

    private void initFindId(View view) {
        robot = (ImageView) view.findViewById(R.id.assistant_robot);
        gridView = (GridView) view.findViewById(R.id.assistant_gridView);
        listView = (CustomScrollListView) view.findViewById(R.id.assistant_listView);
        scrollView = (NestedScrollView) view.findViewById(R.id.assistant_scrollView);
        record = (TextView) view.findViewById(R.id.assistant_record);
        camera = (ImageView) view.findViewById(R.id.assistant_camera);
        refresh = ((FloatingActionButton) view.findViewById(R.id.assistant_listView_refresh));
        weather_show = (SimpleDraweeView) view.findViewById(R.id.assistant_weather_show);
        title = (TextView) view.findViewById(R.id.assistant_title);
    }

    private void initGridView() {
        List<AssistantGridBean> list = new ArrayList<>();
        for (int i = 0; i < assistant_list_text.length; i++) {
            AssistantGridBean assistantGridBean = new AssistantGridBean();
            assistantGridBean.setText(assistant_list_text[i]);
            assistantGridBean.setImage(assistant_list_image[i]);
            list.add(assistantGridBean);
        }
        LifeAssistantGridAdapter adapter = new LifeAssistantGridAdapter(getContext(), list);
        gridView.setAdapter(adapter);
        adapter.setLifeAssistantClickItem(this);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new DynamicListAdapter(getContext(), list);
        listView.setAdapter(adapter);
    }

    private void initScrollView() {
        scrollView.smoothScrollTo(0, 20);
        listView.setFocusable(false);
    }

    private void initClick() {
        robot.setOnClickListener(this);
        record.setOnClickListener(this);
        camera.setOnClickListener(this);
        refresh.setOnClickListener(this);
        weather_show.setOnClickListener(this);
    }

    private void initiLocation() {
        mLocationClient = new LocationClient(getContext().getApplicationContext());     //定位初始化
        mLocationClient.registerLocationListener(this);//定位注册

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
//        option.setScanSpan(1000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setNeedDeviceDirect(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        int locType = bdLocation.getLocType();
        switch (locType) {
            case BDLocation.TypeNetWorkLocation:
            case BDLocation.TypeGpsLocation:
            case BDLocation.TypeOffLineLocation:
                 String addrStr = bdLocation.getAddrStr().replace(bdLocation.getCountry(),"").replace(bdLocation.getCity(),"");
                 province = bdLocation.getProvince().substring(0, bdLocation.getProvince().length() - 1);
                 city = bdLocation.getCity().substring(0, bdLocation.getCity().length() - 1);
                 district = bdLocation.getDistrict().substring(0, bdLocation.getDistrict().length() - 1);
                if (province.equals("北京市") || province.equals("上海市") || province.equals("天津市") || province.equals("重庆市")) {
                    getWeatherDetail(StringContents.MobAPI_APPKEY, district, province);
                } else {
                    getWeatherDetail(StringContents.MobAPI_APPKEY, city, province);
                }
                Bundle bundle = new Bundle();
                bundle.putString("addr",addrStr);
                Message message = new Message();
                message.setData(bundle);
                message.what = 302;
                mHandler.sendMessage(message);
                break;
            default:
                LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~error::" + bdLocation.getLocType());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assistant_robot:
                startActivity(new Intent(getContext(), LifeAssistantRobotActivity.class));
                break;
            case R.id.assistant_record:
            case R.id.assistant_camera:
                showCameraSelectDialog();
                break;
            case R.id.assistant_listView_refresh:
                if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))) {
                    requestDynamicList();
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(refresh, "Rotation", -360, 0);
                    // 设置持续时间
                    objectAnimator.setDuration(1000);
                    // 设置循环播放
                    objectAnimator.setRepeatCount(1);
                    objectAnimator.start();
                }
                break;
            case R.id.assistant_weather_show:
                Intent intent = new Intent(getContext(), LifeAssistantWeatherActivity.class);
                if (!Tools.isNull(province)){
                    intent.putExtra("province",province);
                    intent.putExtra("city",city);
                    intent.putExtra("district",district);
                }
                startActivity(intent);
        }
    }

    private void requestDynamicList() {
        BmobQuery<DynamicBean> query = new BmobQuery<DynamicBean>();
        String user_name = ((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getUser_name();
        query.addWhereEqualTo("user_name", user_name);
        query.setLimit(10);
        query.findObjects(new FindListener<DynamicBean>() {
            @Override
            public void done(List<DynamicBean> data_list, BmobException e) {
                if (e == null) {
                    if (data_list != null) {
                        list.clear();
                        Collections.reverse(data_list);
                        list.addAll(data_list);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~",e.getMessage());
                }
            }
        });
    }

    private void getWeatherDetail(String key, String city, String province) {
        String url = StringContents.MobAPI_BaseUrl + "weather/query?key=" + key + "&city=" + city + "&province=" + province;
        HttpHelper.doGetCall(url, getContext(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.d("~~~~~~~~~~~~~~~~onFailure", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    String string = response.body().string();
                    resultBean2 = ModelParseHelper.parseWeatherResult(string);
                    if (resultBean2.getResult() != null) {
                        Message message = new Message();
                        message.what = 301;
                        mHandler.sendMessage(message);
                    }
                }
            }
        });
    }

    private void showCameraSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] item_list = {"记录生活", "拍照", "从相册上中选取"};
        builder.setSingleChoiceItems(item_list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    startActivity(new Intent(getContext(), LifeAssistantRecordActivity.class));
                } else if (which == 1) {
                    startActivity(new Intent(getContext(), LifeAssistantRecordActivity.class));
                } else if (which == 2) {
                    startActivity(new Intent(getContext(), LifeAssistantRecordActivity.class));
                }
                dialog.dismiss();
            }
        }).create();
        builder.show();
    }

    @Override
    public void assistantClickItem(String name) {
        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
        switch (name) {
            case "天气预报":
                Intent intent = new Intent(getContext(), LifeAssistantWeatherActivity.class);
                if (!Tools.isNull(province)){
                    intent.putExtra("province",province);
                    intent.putExtra("city",city);
                    intent.putExtra("district",district);
                }
                startActivity(intent);
                break;
            case "地图":
                startActivity(new Intent(getContext(), LifeAssistantMapActivity.class));
                break;
            case "手机归属地":
                showPhoneBelongWindow();
                break;
            case "空气质量":
                break;
            case "健康知识":
                break;
            case "汽车信息":
                break;
            case "驾考题库":
                break;
            case "全部":
                startActivity(new Intent(getContext(), LifeAllAssistantActivity.class));
                break;
        }
    }

    /**
     * 显示 手机归属地 popupWindow
     */
    public void showPhoneBelongWindow() {
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_assistant_phonebelong, null);
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setAnimationStyle(R.style.loginPopupWindow);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        ImageView phoneBelong_close = (ImageView) convertView.findViewById(R.id.assistant_phoneBelong_close);
        final EditText phoneBelong_input = (EditText) convertView.findViewById(R.id.assistant_phoneBelong_input);
        phoneBelong_city = (TextView) convertView.findViewById(R.id.assistant_phoneBelong_city);
        phoneBelong_cityCode = (TextView) convertView.findViewById(R.id.assistant_phoneBelong_cityCode);
        phoneBelong_operator = (TextView) convertView.findViewById(R.id.assistant_phoneBelong_operator);
        phoneBelong_province = (TextView) convertView.findViewById(R.id.assistant_phoneBelong_province);
        phoneBelong_zipCode = (TextView) convertView.findViewById(R.id.assistant_phoneBelong_zipCode);
        TextView phoneBelong_query = (TextView) convertView.findViewById(R.id.assistant_phoneBelong_query);
        phoneBelong_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        phoneBelong_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Tools.isNull(phoneBelong_input.getText().toString().trim())) {
                    String url = "http://apicloud.mob.com/v1/mobile/address/query?key=" + StringContents.MobAPI_APPKEY + "&phone=" + phoneBelong_input.getText().toString().trim();
                    HttpHelper.doGetCall(url, getContext(), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            LogUtil.d("~~~~~~~~~~~~~~~~~~onFailure~~~~~", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            resultBean = ModelParseHelper.parsePhoneBelongResult(response.body().string());
                            if (resultBean.getRetCode().equals("200")) {
                                Message message = new Message();
                                message.what = 200;
                                mHandler.sendMessage(message);
                            } else {
                                Message message = new Message();
                                message.what = 400;
                                mHandler.sendMessage(message);
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_assistant, null);
        mPopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        mLocationClient.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationClient.stop();
    }

    @Override
    public void onDestroyView() {
        getContext().unregisterReceiver(commonDataReceiver);
        mLocationClient.unRegisterLocationListener(this);
        super.onDestroyView();
    }
}

