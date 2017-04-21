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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
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
import com.treasure_ct.happiness_xt.activity.life.LifeAllActivity;
import com.treasure_ct.happiness_xt.activity.life.LifeDynamicItemActivity;
import com.treasure_ct.happiness_xt.activity.life.LifeMapActivity;
import com.treasure_ct.happiness_xt.activity.life.LifeMusicActivity;
import com.treasure_ct.happiness_xt.activity.life.LifeRecordActivity;
import com.treasure_ct.happiness_xt.activity.life.LifeRobotActivity;
import com.treasure_ct.happiness_xt.activity.life.LifeWeatherActivity;
import com.treasure_ct.happiness_xt.adapter.DynamicListAdapter;
import com.treasure_ct.happiness_xt.adapter.LifeGridAdapter;
import com.treasure_ct.happiness_xt.bean.LifePhoneBelongBean;
import com.treasure_ct.happiness_xt.bean.LifeGridBean;
import com.treasure_ct.happiness_xt.bean.LifePostCode2Bean;
import com.treasure_ct.happiness_xt.bean.LifePostCodeBean;
import com.treasure_ct.happiness_xt.bean.LifeWeatherResultBean;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.receiver.CommonDataReceiver;
import com.treasure_ct.happiness_xt.widget.CustomScrollListView;
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
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LifeFragment extends Fragment implements LifeGridAdapter.LifeAssistantClickItem, View.OnClickListener, BDLocationListener, DynamicListAdapter.ItemClick, AdapterView.OnItemClickListener {
    private GridView gridView;
    private String[] assistant_list_text = {"智能机器人", "天气预报", "地图", "手机归属地", "邮编查询", "聆听好声音", "驾考题库", "全部"};
    private int[] assistant_list_image = {R.mipmap.icon_robot, R.mipmap.icon_weather, R.mipmap.icon_location, R.mipmap.icon_phone,
            R.mipmap.icon_postcode, R.mipmap.icon_music, R.mipmap.icon_driving, R.mipmap.icon_all};
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
                                .setUri(Uri.parse("res://" + package_name + "/" + R.mipmap.gif_cloud))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("晴")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + package_name + "/" + R.mipmap.gif_fine))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("阴")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + package_name + "/" + R.mipmap.gif_bad))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("小雨") || weather.equals("雨") || weather.equals("中雨") || weather.equals("阵雨") || weather.equals("零散阵雨") || weather.equals("零散雷雨") || weather.equals("暴雨") || weather.equals("大雨")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + package_name + "/" + R.mipmap.gif_rain))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("小雪") || weather.equals("雨夹雪") || weather.equals("阵雪") || weather.equals("大雪") || weather.equals("中雪")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + package_name + "/" + R.mipmap.gif_snow))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("霾")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + package_name + "/" + R.mipmap.gif_bad))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    } else if (weather.equals("雷阵雨")) {
                        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                                .setAutoPlayAnimations(true)
                                .setUri(Uri.parse("res://" + package_name + "/" + R.mipmap.gif_thunder))//设置uri
                                .build();
                        weather_show.setController(mDraweeController);
                    }
                    break;
                case 302:
                    String addr = msg.getData().getString("addr");
                    title.setText(addr);
                    break;
                case 201:
                    postCode_province.setText(postCodeResult.getResult().getProvince());
                    postCode_city.setText(postCodeResult.getResult().getCity());
                    postCode_district.setText(postCodeResult.getResult().getDistrict());
                    postCode_post.setText(postCodeResult.getResult().getPostNumber());
                    List<String> address = postCodeResult.getResult().getAddress();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, address);
                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    postCode_address.setAdapter(adapter);
                    postCode_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    postCode_address.setVisibility(View.VISIBLE);
                    break;
                case 401:
                    Toast.makeText(getContext(), postCodeResult.getMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 202:
                    postCode_province2.setText(postCodeResult2.getResult().get(0).getProvince());
                    postCode_city2.setText(postCodeResult2.getResult().get(0).getCity());
                    postCode_district2.setText(postCodeResult2.getResult().get(0).getDistrict());
                    postCode_post2.setText(postCodeResult2.getResult().get(0).getPostNumber());
                    postCode_pid.setText(postCodeResult2.getResult().get(0).getPId());
                    postCode_cid.setText(postCodeResult2.getResult().get(0).getCId());
                    postCode_did.setText(postCodeResult2.getResult().get(0).getDId());
                    List<String> address2 = postCodeResult2.getResult().get(0).getAddress();
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, address2);
                    adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    postCode_address2.setAdapter(adapter2);
                    postCode_address2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    postCode_address2.setVisibility(View.VISIBLE);
                    break;
                case 402:
                    Toast.makeText(getContext(), postCodeResult2.getMsg(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private LifePhoneBelongBean resultBean;
    private LifeWeatherResultBean resultBean2;
    private TextView phoneBelong_city, phoneBelong_cityCode, phoneBelong_operator, phoneBelong_province, phoneBelong_zipCode;
    private CustomScrollListView listView;
    private List<DynamicBean> list;
    private DynamicListAdapter listAdapter;
    private NestedScrollView scrollView;
    private TextView record;
    private ImageView camera;
    private IntentFilter filter;
    private CommonDataReceiver commonDataReceiver;
    private FloatingActionButton refresh;
    public LocationClient mLocationClient = null;
    private SimpleDraweeView weather_show;
    private TextView title;
    private String province, city, district;
    private String package_name = "com.treasure_ct.happiness_xt";
    //邮编查城市
    private PopupWindow mPopupWindow2;
    private LifePostCodeBean postCodeResult;
    private TextView postCode_city, postCode_district, postCode_post, postCode_province;
    private Spinner postCode_address;
    //城市查邮编
    private PopupWindow mPopupWindow3;
    private LifePostCode2Bean postCodeResult2;
    private TextView postCode_city2, postCode_district2, postCode_post2, postCode_province2, postCode_pid, postCode_cid, postCode_did;
    private Spinner postCode_address2;

    private int sendTopNum;
    private PopupWindow mPopupWindow1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_life, container, false);
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
        List<LifeGridBean> list = new ArrayList<>();
        for (int i = 0; i < assistant_list_text.length; i++) {
            LifeGridBean lifeGridBean = new LifeGridBean();
            lifeGridBean.setText(assistant_list_text[i]);
            lifeGridBean.setImage(assistant_list_image[i]);
            list.add(lifeGridBean);
        }
        LifeGridAdapter adapter = new LifeGridAdapter(getContext(), list);
        gridView.setAdapter(adapter);
        adapter.setLifeAssistantClickItem(this);
    }

    private void initListView() {
        list = new ArrayList<>();
        listAdapter = new DynamicListAdapter(getContext(), list);
        listView.setAdapter(listAdapter);
        listAdapter.setItemClick(this);
    }

    private void initScrollView() {
        scrollView.smoothScrollTo(0, 20);
        listView.setFocusable(false);
    }

    private void initClick() {
        record.setOnClickListener(this);
        camera.setOnClickListener(this);
        refresh.setOnClickListener(this);
        weather_show.setOnClickListener(this);
        listView.setOnItemClickListener(this);
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
                String addrStr = bdLocation.getAddrStr().replace(bdLocation.getCountry(), "").replace(bdLocation.getCity(), "");
                province = bdLocation.getProvince().substring(0, bdLocation.getProvince().length() - 1);
                city = bdLocation.getCity().substring(0, bdLocation.getCity().length() - 1);
                district = bdLocation.getDistrict().substring(0, bdLocation.getDistrict().length() - 1);
                if (province.equals("北京市") || province.equals("上海市") || province.equals("天津市") || province.equals("重庆市")) {
                    getWeatherDetail(StringContents.MobAPI_APPKEY, district, province);
                } else {
                    getWeatherDetail(StringContents.MobAPI_APPKEY, city, province);
                }
                Bundle bundle = new Bundle();
                bundle.putString("addr", addrStr);
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
                Intent intent = new Intent(getContext(), LifeWeatherActivity.class);
                if (!Tools.isNull(province)) {
                    intent.putExtra("province", province);
                    intent.putExtra("city", city);
                    intent.putExtra("district", district);
                }
                startActivity(intent);
                break;
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
                        listAdapter.notifyDataSetChanged();
                    }
                } else {
                    LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", e.getMessage());
                }
            }
        });
    }

    private void getWeatherDetail(String key, String city, String province) {
        String url = StringContents.MobAPI_BaseUrl + "/v1/weather/query?key=" + key + "&city=" + city + "&province=" + province;
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
                    startActivity(new Intent(getContext(), LifeRecordActivity.class));
                } else if (which == 1) {
                    startActivity(new Intent(getContext(), LifeRecordActivity.class));
                } else if (which == 2) {
                    startActivity(new Intent(getContext(), LifeRecordActivity.class));
                }
                dialog.dismiss();
            }
        }).create();
        builder.show();
    }

    @Override
    public void assistantClickItem(String name) {
        switch (name) {
            case "智能机器人":
                startActivity(new Intent(getContext(), LifeRobotActivity.class));
                break;
            case "天气预报":
                Intent intent = new Intent(getContext(), LifeWeatherActivity.class);
                if (!Tools.isNull(province)) {
                    intent.putExtra("province", province);
                    intent.putExtra("city", city);
                    intent.putExtra("district", district);
                }
                startActivity(intent);
                break;
            case "地图":
                startActivity(new Intent(getContext(), LifeMapActivity.class));
                break;
            case "手机归属地":
                showPhoneBelongWindow();
                break;
            case "邮编查询":
                showPostCodeItemDialog();
                break;
            case "聆听好声音":
                startActivity(new Intent(getContext(), LifeMusicActivity.class));
                break;
            case "驾考题库":
                break;
            case "全部":
                startActivity(new Intent(getContext(), LifeAllActivity.class));
                break;
        }
    }

    /**
     * 显示 手机归属地 popupWindow
     */
    public void showPhoneBelongWindow() {
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_life_phonebelong, null);
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
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_life, null);
        mPopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    /**
     * 显示 邮编  选项
     */
    private void showPostCodeItemDialog() {
        String[] items = {"邮编查询地址", "地址查询邮编"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
        builder.setIcon(R.mipmap.icon);
        builder.setTitle("请选择查询选项");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    dialog.dismiss();
                    showPostCode1Window();
                } else if (which == 1) {
                    dialog.dismiss();
                    showPostCode2Window();
                }
            }
        });
        builder.create();
        builder.show();
    }

    /**
     * 显示 邮编查询城市 popupWindow
     */
    public void showPostCode1Window() {
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_life_postcode, null);
        mPopupWindow2 = new PopupWindow(convertView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow2.setAnimationStyle(R.style.loginPopupWindow);
        mPopupWindow2.setOutsideTouchable(true);
        mPopupWindow2.setBackgroundDrawable(new ColorDrawable(0x00000000));
        ImageView postCode_close = (ImageView) convertView.findViewById(R.id.assistant_postCode_close);
        final EditText postCode_input = (EditText) convertView.findViewById(R.id.assistant_postCode_input);
        postCode_province = (TextView) convertView.findViewById(R.id.assistant_postCode_province);
        postCode_city = (TextView) convertView.findViewById(R.id.assistant_postCode_city);
        postCode_district = (TextView) convertView.findViewById(R.id.assistant_postCode_district);
        postCode_post = (TextView) convertView.findViewById(R.id.assistant_postCode_postCode);
        postCode_address = (Spinner) convertView.findViewById(R.id.assistant_postCode_address);
        TextView postCode_query = (TextView) convertView.findViewById(R.id.assistant_postCode_query);
        postCode_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow2.dismiss();
            }
        });
        postCode_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Tools.isNull(postCode_input.getText().toString().trim())) {
                    String url = "http://apicloud.mob.com/v1/postcode/query?key=" + StringContents.MobAPI_APPKEY + "&code=" + postCode_input.getText().toString().trim();
                    HttpHelper.doGetCall(url, getContext(), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            LogUtil.d("~~~~~~~~~~~~~~~~~~~onFailure~`", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            postCodeResult = ModelParseHelper.parsePostCodeResult(response.body().string());
                            if (postCodeResult.getRetCode().equals("200")) {
                                if (postCodeResult.getResult() != null) {
                                    Message message = new Message();
                                    message.what = 201;
                                    mHandler.sendMessage(message);
                                }
                            } else {
                                Message message = new Message();
                                message.what = 401;
                                mHandler.sendMessage(message);
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "请输入邮编号", Toast.LENGTH_SHORT).show();
                }
            }
        });
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.activity_life_all_assistant, null);
        mPopupWindow2.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    /**
     * 显示 城市查询邮编 popupWindow
     */
    public void showPostCode2Window() {
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_life_postcode2, null);
        mPopupWindow3 = new PopupWindow(convertView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow3.setAnimationStyle(R.style.loginPopupWindow);
        mPopupWindow3.setOutsideTouchable(true);
        mPopupWindow3.setBackgroundDrawable(new ColorDrawable(0x00000000));
        ImageView postCode_close2 = (ImageView) convertView.findViewById(R.id.assistant_postCode_close2);
        final EditText postCode_input2 = (EditText) convertView.findViewById(R.id.assistant_postCode_input2);
        final EditText postCode_input3 = (EditText) convertView.findViewById(R.id.assistant_postCode_input3);
        final EditText postCode_input4 = (EditText) convertView.findViewById(R.id.assistant_postCode_input4);
        final EditText postCode_input5 = (EditText) convertView.findViewById(R.id.assistant_postCode_input5);
        postCode_province2 = (TextView) convertView.findViewById(R.id.assistant_postCode_province2);
        postCode_city2 = (TextView) convertView.findViewById(R.id.assistant_postCode_city2);
        postCode_district2 = (TextView) convertView.findViewById(R.id.assistant_postCode_district2);
        postCode_post2 = (TextView) convertView.findViewById(R.id.assistant_postCode_postCode2);
        postCode_pid = (TextView) convertView.findViewById(R.id.assistant_postCode_provinceID2);
        postCode_cid = (TextView) convertView.findViewById(R.id.assistant_postCode_cityID2);
        postCode_did = (TextView) convertView.findViewById(R.id.assistant_postCode_districtID2);
        postCode_address2 = (Spinner) convertView.findViewById(R.id.assistant_postCode_address2);
        TextView postCode_query2 = (TextView) convertView.findViewById(R.id.assistant_postCode_query2);
        postCode_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow3.dismiss();
            }
        });
        postCode_query2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code1 = postCode_input2.getText().toString().trim();
                String code2 = postCode_input3.getText().toString().trim();
                String code3 = postCode_input4.getText().toString().trim();
                String code4 = postCode_input5.getText().toString().trim();
                if (!Tools.isNull(code1) && !Tools.isNull(code2) && !Tools.isNull(code3) && !Tools.isNull(code4)) {
                    String url = "http://apicloud.mob.com/v1/postcode/search?key=" + StringContents.MobAPI_APPKEY + "&pid=" + code1 + "&cid=" + code2 + "&did=" + code3 + "&word=" + code4;
                    HttpHelper.doGetCall(url, getContext(), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            LogUtil.d("~~~~~~~~~~~~~~~~~~~onFailure~`", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            postCodeResult2 = ModelParseHelper.parsePostCode2Result(response.body().string());
                            if (postCodeResult2.getRetCode().equals("200")) {
                                if (postCodeResult2.getResult() != null) {
                                    Message message = new Message();
                                    message.what = 202;
                                    mHandler.sendMessage(message);
                                }
                            } else {
                                Message message = new Message();
                                message.what = 402;
                                mHandler.sendMessage(message);
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "请填写完整", Toast.LENGTH_SHORT).show();
                }
            }
        });
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.activity_life_all_assistant, null);
        mPopupWindow3.showAtLocation(rootView, Gravity.CENTER, 0, 0);
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

    @Override
    public void sendMore(final String nick, final String contents) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.life_dynamic_item_more_layout, null);
        mPopupWindow1 = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow1.setOutsideTouchable(true);
        mPopupWindow1.setBackgroundDrawable(new ColorDrawable(0x88000000));
        LinearLayout delete = (LinearLayout) inflate.findViewById(R.id.life_dynamic_item_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<DynamicBean> query = new BmobQuery<>();
                query.addWhereEqualTo("user_nick", nick);
                query.addWhereEqualTo("content", contents);
                query.findObjects(new FindListener<DynamicBean>() {
                    @Override
                    public void done(List<DynamicBean> list, BmobException e) {
                        if (e == null) {
                            for (DynamicBean dynamicBean : list) {
                                toDelete(dynamicBean.getObjectId());
                            }
                        }
                    }
                });
            }
        });
        mPopupWindow1.showAsDropDown(listView);
    }

    private void toDelete(String objectId) {
        DynamicBean dynamicBean = new DynamicBean();
        dynamicBean.setObjectId(objectId);
        dynamicBean.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    mPopupWindow1.dismiss();
                    requestDynamicList();
                } else {

                }
            }
        });
    }

    @Override
    public void sendTop(String nick, String contents) {
        BmobQuery<DynamicBean> query = new BmobQuery<>();
        query.addWhereEqualTo("user_nick", nick);
        query.addWhereEqualTo("content", contents);
        query.findObjects(new FindListener<DynamicBean>() {
            @Override
            public void done(List<DynamicBean> list, BmobException e) {
                if (e == null) {
                    for (DynamicBean dynamicBean : list) {
                        getTop(dynamicBean.getObjectId());
                    }
                }
            }
        });
    }

    private void getTop(final String objectId) {
        BmobQuery<DynamicBean> query = new BmobQuery<>();
        query.getObject(objectId, new QueryListener<DynamicBean>() {

            @Override
            public void done(DynamicBean dynamicBean, BmobException e) {
                if (e == null) {
                    sendTopNum = dynamicBean.getSendTop();
                    toUpdateTop(objectId, sendTopNum);
                }
            }
        });
    }

    private void toUpdateTop(String objectId, int sendTopNum) {
        DynamicBean dynamicBean = new DynamicBean();
        dynamicBean.setSendTop(sendTopNum + 1);
        dynamicBean.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                requestDynamicList();
            }
        });
    }

    @Override
    public void sendComments(String nick, String contents, String publish_time, int top_num, int comments_num) {
        Intent intent = new Intent(getContext(), LifeDynamicItemActivity.class);
        intent.putExtra("user_nick", nick);
        intent.putExtra("publish_time", publish_time);
        intent.putExtra("content", contents);
        intent.putExtra("top", top_num + "");
        intent.putExtra("comments", comments_num + "");
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), LifeDynamicItemActivity.class);
        intent.putExtra("user_nick", list.get(position).getUser_nick());
        intent.putExtra("publish_time", list.get(position).getPublish_time());
        intent.putExtra("content", list.get(position).getContent());
        intent.putExtra("top", list.get(position).getSendTop() + "");
        intent.putExtra("comments", list.get(position).getComments() + "");
        startActivity(intent);
    }
}

