package com.treasure_ct.happiness_xt.activity.life;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.MapMarkerInfoBean;
import com.treasure_ct.happiness_xt.listener.MapOrientationListener;
import com.treasure_ct.happiness_xt.utils.BikingRouteOverlay;
import com.treasure_ct.happiness_xt.utils.DrivingRouteOverlay;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.Tools;
import com.treasure_ct.happiness_xt.utils.TransitRouteOverlay;
import com.treasure_ct.happiness_xt.utils.WalkingRouteOverlay;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LifeMapActivity extends BaseActivity implements BDLocationListener, View.OnClickListener, BaiduMap.OnMapLoadedCallback, TextWatcher, CompoundButton.OnCheckedChangeListener, RadioGroup.OnCheckedChangeListener, OnGetRoutePlanResultListener, OnGetPoiSearchResultListener, BaiduMap.OnMarkerClickListener, BaiduMap.OnMapClickListener {

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
    private boolean isPageDestroy;
    private EditText input;
    private ImageView more;
    private PopupWindow mPopupWindow;
    private Switch map_sate, map_2D, map_hot, map_traffic;
    private RadioGroup line_layout;
    private RoutePlanSearch mSearch;
    private String city;
    private String addr;
    private Switch mode_normal, mode_follow, mode_compass;
    private MyLocationConfiguration.LocationMode mode;
    private ImageView input_close;
    private PoiSearch mPoiSearch;
    private EditText poi_name, poi_city;
    private TextView poi_query;
    private BitmapDescriptor marker_bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.icon_marker);
    private PoiResult poiResult;
    private InfoWindow mInfoWindow;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    MyLocationData data = new MyLocationData.Builder()
                            .accuracy(radius)
                            .direction(mCurrentX)
                            .latitude(user_latitude)
                            .longitude(user_longitude)
                            .build();
                    map.setMyLocationData(data);
                    MyLocationConfiguration config = new MyLocationConfiguration(mode, true, null);
                    map.setMyLocationConfigeration(config);
                    break;
                case 201:
                    map.clear();
                    for (int i = 0; i < poiResult.getTotalPoiNum(); i++) {
                        if (i < 10) {
                            MapMarkerInfoBean infoBean = new MapMarkerInfoBean();
                            infoBean.setAddress(poiResult.getAllPoi().get(i).address);
                            infoBean.setLatLng(poiResult.getAllPoi().get(i).location);
                            infoBean.setName(poiResult.getAllPoi().get(i).name);
                            addOverlay(infoBean);
                        }
                    }
                    Toast.makeText(LifeMapActivity.this, "获取数量：" + poiResult.getTotalPoiNum(), Toast.LENGTH_SHORT).show();
                    mPopupWindow.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_map);
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
        map.setIndoorEnable(true);//开启室内图
        mode = MyLocationConfiguration.LocationMode.NORMAL;
        initLocation();
        initClick();
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
    }

    private void initFindId() {
        mapView = (MapView) findViewById(R.id.assistant_mapView);
        map_location = (ImageView) findViewById(R.id.assistant_mapView_location);
        input = (EditText) findViewById(R.id.assistant_mapView_input);
        more = (ImageView) findViewById(R.id.assistant_mapView_more);
        line_layout = (RadioGroup) findViewById(R.id.assistant_mapView_line_layout);
        input_close = (ImageView) findViewById(R.id.assistant_mapView_input_close);
    }

    private void initClick() {
        map_location.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        input.addTextChangedListener(this);
        more.setOnClickListener(this);
        line_layout.setOnCheckedChangeListener(this);
        input_close.setOnClickListener(this);
        map.setOnMarkerClickListener(this);
        map.setOnMapClickListener(this);
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
            case R.id.assistant_mapView_more:
                showPopupWindow();
                break;
            case R.id.assistant_mapView_input_close:
                input.setText("");
                break;
            case R.id.map_poi_query:
                String name = poi_name.getText().toString();
                String city = poi_city.getText().toString();
                if (!Tools.isNull(name) && !Tools.isNull(city)) {
                    mPoiSearch.searchInCity((new PoiCitySearchOption())
                            .city(city)
                            .keyword(name)
                            .pageNum(10));
                }
                break;
        }
    }

    /**
     * 常规逻辑
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!Tools.isNull(s.toString().trim())) {
            line_layout.setVisibility(View.VISIBLE);
            input_close.setVisibility(View.VISIBLE);
        } else {
            line_layout.setVisibility(View.GONE);
            input_close.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void showPopupWindow() {
        View convertView = LayoutInflater.from(this).inflate(R.layout.life_map_pop_layout, null);
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setAnimationStyle(R.style.loginPopupWindow);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        map_sate = (Switch) convertView.findViewById(R.id.map_satellite_switch);
        map_2D = (Switch) convertView.findViewById(R.id.map_2D_switch);
        map_hot = (Switch) convertView.findViewById(R.id.map_hot_switch);
        map_traffic = (Switch) convertView.findViewById(R.id.map_traffic_switch);
        mode_normal = ((Switch) convertView.findViewById(R.id.map_mode_normal_switch));
        mode_follow = ((Switch) convertView.findViewById(R.id.map_mode_follow_switch));
        mode_compass = ((Switch) convertView.findViewById(R.id.map_mode_compass_switch));
        poi_name = ((EditText) convertView.findViewById(R.id.map_poi_name));
        poi_city = ((EditText) convertView.findViewById(R.id.map_poi_city));
        poi_query = ((TextView) convertView.findViewById(R.id.map_poi_query));

        if (map.getMapType() == BaiduMap.MAP_TYPE_SATELLITE) {
            map_sate.setChecked(true);
        } else {
            map_2D.setChecked(true);
        }
        if (map.getLocationConfigeration() != null) {
            if (map.getLocationConfigeration().locationMode.equals(MyLocationConfiguration.LocationMode.NORMAL)) {
                mode_normal.setChecked(true);
            } else if (map.getLocationConfigeration().locationMode.equals(MyLocationConfiguration.LocationMode.FOLLOWING)) {
                mode_follow.setChecked(true);
            } else {
                mode_compass.setChecked(true);
            }
        } else {
            mode_normal.setChecked(true);
        }
        if (map.isBaiduHeatMapEnabled()) {
            map_hot.setChecked(true);
        }
        if (map.isTrafficEnabled()) {
            map_traffic.setChecked(true);
        }

        map_sate.setOnCheckedChangeListener(this);
        map_2D.setOnCheckedChangeListener(this);
        map_hot.setOnCheckedChangeListener(this);
        map_traffic.setOnCheckedChangeListener(this);
        mode_normal.setOnCheckedChangeListener(this);
        mode_follow.setOnCheckedChangeListener(this);
        mode_compass.setOnCheckedChangeListener(this);
        poi_query.setOnClickListener(this);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_life_map, null);
        mPopupWindow.showAtLocation(rootView, Gravity.RIGHT, 0, 0);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.map_satellite_switch:
                if (isChecked) {
                    map_2D.setChecked(false);
                    map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                }
                break;
            case R.id.map_2D_switch:
                if (isChecked) {
                    map_sate.setChecked(false);
                    map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                }
                break;
            case R.id.map_hot_switch:
                if (isChecked) {
                    map.setBaiduHeatMapEnabled(true);
                } else {
                    map.setBaiduHeatMapEnabled(false);
                }
                break;
            case R.id.map_traffic_switch:
                if (isChecked) {
                    map.setTrafficEnabled(true);
                } else {
                    map.setTrafficEnabled(false);
                }
                break;
            case R.id.map_mode_normal_switch:
                if (isChecked) {
                    mode_follow.setChecked(false);
                    mode_compass.setChecked(false);
                    mode = MyLocationConfiguration.LocationMode.NORMAL;
                }
                break;
            case R.id.map_mode_follow_switch:
                if (isChecked) {
                    mode_normal.setChecked(false);
                    mode_compass.setChecked(false);
                    mode = MyLocationConfiguration.LocationMode.FOLLOWING;
                }
                break;
            case R.id.map_mode_compass_switch:
                if (isChecked) {
                    mode_follow.setChecked(false);
                    mode_normal.setChecked(false);
                    mode = MyLocationConfiguration.LocationMode.COMPASS;
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        PlanNode stNode = PlanNode.withCityNameAndPlaceName(city, addr);
        PlanNode enNode = PlanNode.withCityNameAndPlaceName(city, input.getText().toString().trim());
        RadioButton radioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        if (radioButton.getText().equals("公交")) {
            mSearch.transitSearch((new TransitRoutePlanOption())
                    .from(stNode)
                    .city(city)
                    .to(enNode));
        } else if (radioButton.getText().equals("驾车")) {
            mSearch.drivingSearch((new DrivingRoutePlanOption())
                    .from(stNode)
                    .to(enNode));
        } else if (radioButton.getText().equals("步行")) {
            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(stNode)
                    .to(enNode));
        } else if (radioButton.getText().equals("骑行")) {
            mSearch.bikingSearch((new BikingRoutePlanOption())
                    .from(stNode)
                    .to(enNode));
        }
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        MapMarkerInfoBean markerInfo = (MapMarkerInfoBean) marker.getExtraInfo().get("marker");
        View inflate = LayoutInflater.from(LifeMapActivity.this).inflate(R.layout.map_marker_info_layout, null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView name = (TextView) inflate.findViewById(R.id.map_marker_info_name);
        TextView address = (TextView) inflate.findViewById(R.id.map_marker_info_address);
        name.setText(markerInfo.getName()+"");
        address.setText(markerInfo.getAddress()+"");
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
         mInfoWindow = new InfoWindow(inflate, markerInfo.getLatLng(), -100);
        //显示InfoWindow
        map.showInfoWindow(mInfoWindow);
        return false;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        map.hideInfoWindow();
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }
    /**
     * 地图设计
     */
    //百度地图定位初始化
    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());     //定位初始化
        mLocationClient.registerLocationListener(this);//定位注册

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(2000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
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
        map.setOnMapLoadedCallback(this);
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

    //定位到用户当前位置
    private void showUserLocation() {
        LatLng latLng = new LatLng(user_latitude, user_longitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        map.animateMapStatus(msu);
    }

    //添加标注
    public void addOverlay(MapMarkerInfoBean Info) {
        OverlayOptions overlayoptions = null;
        Marker marker = null;
        overlayoptions = new MarkerOptions()//
                .position(Info.getLatLng())// 设置marker的位置
                .icon(marker_bitmap)// 设置marker的图标
                .animateType(MarkerOptions.MarkerAnimateType.drop)
                .zIndex(9);// 設置marker的所在層級
        marker = (Marker) map.addOverlay(overlayoptions);

        Bundle bundle = new Bundle();
        bundle.putSerializable("marker", Info);
        marker.setExtraInfo(bundle);
    }

    @Override
    public void onMapLoaded() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    if (!isPageDestroy) {
                        LatLng latLng = new LatLng(user_latitude, user_longitude);
                        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(19);// 设置地图放大比例
                        map.setMapStatus(msu);
                        msu = MapStatusUpdateFactory.newLatLng(latLng);
                        map.animateMapStatus(msu);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //定位回调
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
                city = bdLocation.getCity().substring(0, bdLocation.getCity().length() - 1);
                addr = bdLocation.getAddrStr().replace(bdLocation.getCountry(), "").replace(bdLocation.getCity(), "");
                mHandler.sendMessage(mHandler.obtainMessage(200));

                break;
            default:
                String s = bdLocation.getLocTypeDescription();
                break;
        }
    }

    //兴趣点回调
    @Override
    public void onGetPoiResult(PoiResult poiResult1) {
        poiResult = poiResult1;
        mHandler.sendMessage(mHandler.obtainMessage(201));
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~11~~~~~~~~~", poiDetailResult.toString());
    }

    //路线回调
    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
        map.clear();
        if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "未查找到结果", Toast.LENGTH_SHORT).show();
            return;
        }
        if (walkingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            Toast.makeText(this, "起终点或途经点地址有岐义", Toast.LENGTH_SHORT).show();
            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            //result.getSuggestAddrInfo()
            return;
        }
        if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {

            WalkingRouteLine routeLine = walkingRouteResult.getRouteLines().get(0);
            //创建公交路线规划线路覆盖物
            WalkingRouteOverlay overlay = new WalkingRouteOverlay(map);
            //设置公交路线规划数据
            overlay.setData(routeLine);
            //将公交路线规划覆盖物添加到地图中
            overlay.addToMap();
            overlay.zoomToSpan();
        }
    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
        map.clear();
        if (transitRouteResult == null || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "未查找到结果", Toast.LENGTH_SHORT).show();
            return;
        }
        if (transitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            Toast.makeText(this, "起终点或途经点地址有岐义", Toast.LENGTH_SHORT).show();
            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            //result.getSuggestAddrInfo()
            return;
        }
        if (transitRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {

            TransitRouteLine routeLine = transitRouteResult.getRouteLines().get(0);
            //创建公交路线规划线路覆盖物
            TransitRouteOverlay overlay = new TransitRouteOverlay(map);
            //设置公交路线规划数据
            overlay.setData(routeLine);
            //将公交路线规划覆盖物添加到地图中
            overlay.addToMap();
            overlay.zoomToSpan();
        }
    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
        LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~massTransitRouteResult", massTransitRouteResult.getTotal() + "///////");
    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
        map.clear();
        if (drivingRouteResult == null || drivingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "未查找到结果", Toast.LENGTH_SHORT).show();
            return;
        }
        if (drivingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            Toast.makeText(this, "起终点或途经点地址有岐义", Toast.LENGTH_SHORT).show();
            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            //result.getSuggestAddrInfo()
            return;
        }
        if (drivingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
            DrivingRouteLine routeLine = drivingRouteResult.getRouteLines().get(0);
            //创建公交路线规划线路覆盖物
            DrivingRouteOverlay overlay = new DrivingRouteOverlay(map);
            //设置公交路线规划数据
            overlay.setData(routeLine);
            //将公交路线规划覆盖物添加到地图中
            overlay.addToMap();
            overlay.zoomToSpan();
        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {
        map.clear();
        if (bikingRouteResult == null || bikingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "未查找到结果", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bikingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            Toast.makeText(this, "起终点或途经点地址有岐义", Toast.LENGTH_SHORT).show();
            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            //result.getSuggestAddrInfo()
            return;
        }
        if (bikingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {

            BikingRouteLine routeLine = bikingRouteResult.getRouteLines().get(0);
            //创建公交路线规划线路覆盖物
            BikingRouteOverlay overlay = new BikingRouteOverlay(map);
            //设置公交路线规划数据
            overlay.setData(routeLine);
            //将公交路线规划覆盖物添加到地图中
            overlay.addToMap();
            overlay.zoomToSpan();
        }
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
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        mLocationClient.unRegisterLocationListener(this);
        orientationListener.stop();
        isPageDestroy = true;
        mSearch.destroy();
        mPoiSearch.destroy();
        super.onDestroy();
    }
}
