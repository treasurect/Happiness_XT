package com.treasure_ct.happiness_xt.activity.assistant;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.listener.MapOrientationListener;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LifeAssistantMapActivity extends BaseActivity implements BDLocationListener, View.OnClickListener {

    private MapView mapView;
    private BaiduMap map;
    private double user_latitude = 39.915168, user_longitude = 116.403875;
    private double destination_latitude, destination_longitude;
    private float radius = 0;
    public LocationClient mLocationClient = null;
    private float mCurrentX;
    private LatLng p1, p2;
    private List<LatLng> point_list;
    private OverlayOptions ooPolyline;
    private final static double DEF_PI = 3.14159265359; // PI
    private final static double DEF_2PI = 6.28318530712; // 2*PI
    private final static double DEF_PI180 = 0.01745329252; // PI/180.0
    private final static double DEF_R = 6370693.5; // 地球半径，m
    private MapOrientationListener orientationListener;
    private ImageView map_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_assistant_map);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        btn_back.setImageResource(R.mipmap.icon_return);
        btn_back.setVisibility(View.VISIBLE);
        title.setText("地图");

        useLocationOrientationListener();

        initFindId();
        //隐藏百度地图部分图标
        mapView.removeViewAt(2);
        View childAt = mapView.getChildAt(1);
        if (childAt instanceof ImageView || childAt instanceof ZoomControls) {
            childAt.setVisibility(View.INVISIBLE);
        }
        map = mapView.getMap();
        initLocation();
        initClick();
    }

    private void initFindId() {
        mapView = (MapView) findViewById(R.id.assistant_mapView);
        map_location = (ImageView) findViewById(R.id.assistant_mapView_location);
    }

    private void initClick() {
        map_location.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assistant_mapView_location:
                showUserLocation();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    //百度地图定位初始化
    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());     //定位初始化
        mLocationClient.registerLocationListener(this);//定位注册

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(1000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
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

//        useLocationOrientationListener();
    }

    //定位结合方向传感器，从而可以实时监测到X轴坐标的变化，从而就可以检测到
    private void useLocationOrientationListener() {
        orientationListener = new MapOrientationListener(this);
        orientationListener.start();
        orientationListener.setMapOrientationListener(new MapOrientationListener.onOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {//监听方向的改变，方向改变时，需要得到地图上方向图标的位置
                mCurrentX = x;
            }
        });
    }

    //线段绘制
    public void drawLines() {
        point_list = new ArrayList<>();
        p1 = new LatLng(user_latitude, user_longitude);
        p2 = new LatLng(destination_latitude, destination_longitude);
        point_list.add(p1);
        point_list.add(p2);
        ooPolyline = new PolylineOptions().dottedLine(true).width(8).color(0xAA666666).points(point_list).visible(true);
        map.addOverlay(ooPolyline);
    }

    //根据球面距离计算两点直接的距离
    public String getLongDistance(double lat1, double lon1, double lat2, double lon2) {
        double ew1, ns1, ew2, ns2;
        double distance;
        // 角度转换为弧度
        ew1 = lon1 * DEF_PI180;
        ns1 = lat1 * DEF_PI180;
        ew2 = lon2 * DEF_PI180;
        ns2 = lat2 * DEF_PI180;
        // 求大圆劣弧与球心所夹的角(弧度)
        distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
        // 调整到[-1..1]范围内，避免溢出
        if (distance > 1.0) {
            distance = 1.0;
        } else if (distance < -1.0) {
            distance = -1.0;
        }
        // 求大圆劣弧长度
        distance = DEF_R * Math.acos(distance);
        if (distance > 1000.0) {
            DecimalFormat df = new DecimalFormat("0.000");
            String dis = df.format(distance / 1000);
            return dis + "km";
        } else {
            DecimalFormat df = new DecimalFormat("0.000");
            String dis = df.format(distance);
            return dis + "m";
        }
    }
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        // 定位接口可能返回错误码,要根据结果错误码,来判断是否是正确的地址;
        int locType = bdLocation.getLocType();
        switch (locType) {
            case BDLocation.TypeCacheLocation:
            case BDLocation.TypeOffLineLocation:
            case BDLocation.TypeGpsLocation:
            case BDLocation.TypeNetWorkLocation:

                radius = bdLocation.getRadius();
                user_latitude = bdLocation.getLatitude();
                user_longitude = bdLocation.getLongitude();
                MyLocationData data = new MyLocationData.Builder()
                        .accuracy(radius)
                        .direction(mCurrentX)
                        .latitude(user_latitude)
                        .longitude(user_longitude)
                        .build();
                map.setMyLocationData(data);
                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, null);
                map.setMyLocationConfigeration(config);

                break;
            default:
                String s = bdLocation.getLocTypeDescription();
                break;
        }
    }
    //定位到用户当前位置
    private void showUserLocation() {
        LatLng latLng = new LatLng(user_latitude, user_longitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        map.animateMapStatus(msu);
    }

    /**
     * 生命周期的处理
     */
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
        mLocationClient.start();
        map.setMyLocationEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
        mLocationClient.stop();
        map.setMyLocationEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        mLocationClient.unRegisterLocationListener(this);
        orientationListener.stop();
    }
}
