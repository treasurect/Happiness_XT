package com.treasure_ct.happiness_xt.activity.assistant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
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

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.LifeAssistantGridAdapter;
import com.treasure_ct.happiness_xt.bean.AssistantBaseLocationQueryBean;
import com.treasure_ct.happiness_xt.bean.AssistantPhoneBelongBean;
import com.treasure_ct.happiness_xt.bean.AssistantPostCode2Bean;
import com.treasure_ct.happiness_xt.bean.AssistantPostCodeBean;
import com.treasure_ct.happiness_xt.bean.AssistantGridBean;
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

public class LifeAllAssistantActivity extends BaseActivity implements LifeAssistantGridAdapter.LifeAssistantClickItem, View.OnClickListener {
    private String[] assistant_text1 = {"地图", "天气预报", "手机号码归属地", "邮编查询", "基站定位查询",
            "空气质量", "身份证查询", "IP地址", "万年历", "中国彩票开奖"};
    private int[] assistant_image1 = {R.mipmap.icon_map, R.mipmap.icon_weather, R.mipmap.icon_phone,
            R.mipmap.icon_postcode, R.mipmap.icon_location, R.mipmap.icon_air, R.mipmap.icon_idcard,
            R.mipmap.icon_ip, R.mipmap.icon_calendar, R.mipmap.icon_lottery};

    private String[] assistant_text2 = {"银行卡信息", "黄金数据", "货币汇率", "白银数据", "现货交易贵金属", "全球股指查询"};
    private int[] assistant_image2 = {R.mipmap.icon_bankcard, R.mipmap.icon_gold, R.mipmap.icon_exchange, R.mipmap.icon_silver, R.mipmap.icon_metal, R.mipmap.icon_stock};

    private String[] assistant_text3 = {"周公解梦", "婚姻匹配", "手机号码查吉凶", "八字算命", "老黄历",
            "电影票房", "火车票查询", "航班信息查询", "足球五大联赛"};
    private int[] assistant_image3 = {R.mipmap.icon_zhou, R.mipmap.icon_marry, R.mipmap.icon_lucky, R.mipmap.icon_furture,
            R.mipmap.icon_calendar, R.mipmap.icon_movie, R.mipmap.icon_train, R.mipmap.icon_airport, R.mipmap.icon_football};

    private String[] assistant_text4 = {"健康知识", "历史上的今天", "成语大全", "新华字典", "今日油价", "汽车信息查询", "驾考题库"};
    private int[] assistant_image4 = {R.mipmap.icon_healthy, R.mipmap.icon_history, R.mipmap.icon_language,
            R.mipmap.icon_dictionary, R.mipmap.icon_oil, R.mipmap.icon_car, R.mipmap.icon_driving};
    private GridView gridView1, gridView2, gridView3, gridView4;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    phoneBelong_city.setText(phoneBelongBean.getResult().getCity());
                    phoneBelong_cityCode.setText(phoneBelongBean.getResult().getCityCode());
                    phoneBelong_operator.setText(phoneBelongBean.getResult().getOperator());
                    phoneBelong_province.setText(phoneBelongBean.getResult().getProvince());
                    phoneBelong_zipCode.setText(phoneBelongBean.getResult().getZipCode());
                    break;
                case 400:
                    Toast.makeText(LifeAllAssistantActivity.this, phoneBelongBean.getMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 201:
                    postCode_province.setText(postCodeResult.getResult().getProvince());
                    postCode_city.setText(postCodeResult.getResult().getCity());
                    postCode_district.setText(postCodeResult.getResult().getDistrict());
                    postCode_post.setText(postCodeResult.getResult().getPostNumber());
                    List<String> address = postCodeResult.getResult().getAddress();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(LifeAllAssistantActivity.this, android.R.layout.simple_spinner_item, address);
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
                    Toast.makeText(LifeAllAssistantActivity.this, postCodeResult.getMsg(), Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(LifeAllAssistantActivity.this, android.R.layout.simple_spinner_item, address2);
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
                    Toast.makeText(LifeAllAssistantActivity.this, postCodeResult2.getMsg(), Toast.LENGTH_SHORT).show();
                    break;
                case 203:
                    baseLocation_lat.setText(queryBean.getResult().getLat()+"");
                    baseLocation_lon.setText(queryBean.getResult().getLng()+"");
                    baseLocation_g_lat.setText(queryBean.getResult().getGoogleLat()+"");
                    baseLocation_g_lon.setText(queryBean.getResult().getGoogleLng()+"");
                    baseLocation_range.setText(queryBean.getResult().getPrecision()+"");
                    baseLocation_laocation.setText(queryBean.getResult().getAddr());
                    break;
                case 403:
                    Toast.makeText(LifeAllAssistantActivity.this, queryBean.getMsg(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    //手机归属地
    private PopupWindow mPopupWindow;
    private AssistantPhoneBelongBean phoneBelongBean;
    private TextView phoneBelong_city, phoneBelong_cityCode, phoneBelong_operator, phoneBelong_province, phoneBelong_zipCode;
    //邮编查城市
    private PopupWindow mPopupWindow2;
    private AssistantPostCodeBean postCodeResult;
    private TextView postCode_city, postCode_district, postCode_post, postCode_province;
    private Spinner postCode_address;
    //城市查邮编
    private PopupWindow mPopupWindow3;
    private AssistantPostCode2Bean postCodeResult2;
    private TextView postCode_city2, postCode_district2, postCode_post2, postCode_province2, postCode_pid, postCode_cid, postCode_did;
    private Spinner postCode_address2;
    //基站定位查询
    private PopupWindow mPopupWindow4;
    private AssistantBaseLocationQueryBean queryBean;
    private TextView baseLocation_lat, baseLocation_lon, baseLocation_g_lat, baseLocation_g_lon, baseLocation_range, baseLocation_laocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_all_assistant);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        btn_back.setImageResource(R.mipmap.icon_return);
        btn_back.setVisibility(View.VISIBLE);
        title.setText("全部应用");
        initFindId();
        initGridView();
        initClick();
    }

    private void initFindId() {
        gridView1 = (GridView) findViewById(R.id.assistant_all_gridView1);
        gridView2 = (GridView) findViewById(R.id.assistant_all_gridView2);
        gridView3 = (GridView) findViewById(R.id.assistant_all_gridView3);
        gridView4 = (GridView) findViewById(R.id.assistant_all_gridView4);
    }

    private void initGridView() {
        //填充gridView1
        List<AssistantGridBean> list = new ArrayList<>();
        for (int i = 0; i < assistant_image1.length; i++) {
            AssistantGridBean gridBean = new AssistantGridBean();
            gridBean.setText(assistant_text1[i]);
            gridBean.setImage(assistant_image1[i]);
            list.add(gridBean);
        }
        LifeAssistantGridAdapter adapter1 = new LifeAssistantGridAdapter(this, list);
        gridView1.setAdapter(adapter1);
        adapter1.setLifeAssistantClickItem(this);
        //填充GridView2
        List<AssistantGridBean> list2 = new ArrayList<>();
        for (int i = 0; i < assistant_image2.length; i++) {
            AssistantGridBean gridBean = new AssistantGridBean();
            gridBean.setText(assistant_text2[i]);
            gridBean.setImage(assistant_image2[i]);
            list2.add(gridBean);
        }
        LifeAssistantGridAdapter adapter2 = new LifeAssistantGridAdapter(this, list2);
        gridView2.setAdapter(adapter2);
        adapter2.setLifeAssistantClickItem(this);
        //填充GridView3
        List<AssistantGridBean> list3 = new ArrayList<>();
        for (int i = 0; i < assistant_image3.length; i++) {
            AssistantGridBean gridBean = new AssistantGridBean();
            gridBean.setText(assistant_text3[i]);
            gridBean.setImage(assistant_image3[i]);
            list3.add(gridBean);
        }
        LifeAssistantGridAdapter adapter3 = new LifeAssistantGridAdapter(this, list3);
        gridView3.setAdapter(adapter3);
        adapter3.setLifeAssistantClickItem(this);
        //填充GridView4
        List<AssistantGridBean> list4 = new ArrayList<>();
        for (int i = 0; i < assistant_image4.length; i++) {
            AssistantGridBean gridBean = new AssistantGridBean();
            gridBean.setText(assistant_text4[i]);
            gridBean.setImage(assistant_image4[i]);
            list4.add(gridBean);
        }
        LifeAssistantGridAdapter adapter4 = new LifeAssistantGridAdapter(this, list4);
        gridView4.setAdapter(adapter4);
        adapter4.setLifeAssistantClickItem(this);
    }

    private void initClick() {
        btn_back.setOnClickListener(this);
    }

    @Override
    public void assistantClickItem(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        switch (name) {
            case "地图":
                startActivity(new Intent(this, LifeAssistantMapActivity.class));
                break;
            case "天气预报":
                startActivity(new Intent(this, LifeAssistantWeatherActivity.class));
                break;
            case "手机号码归属地":
                showPhoneBelongWindow();
                break;
            case "邮编查询":
                showPostCodeItemDialog();
                break;
            case "基站定位查询":
                showBaseLocationWindow();
                break;
            case "空气质量":
                break;
            case "身份证查询":
                break;
            case "IP地址":
                break;
            case "万年历":
                break;
            case "中国彩票开奖":
                break;
            case "银行卡信息":
                break;
            case "黄金数据":
                break;
            case "货币汇率":
                break;
            case "白银数据":
                break;
            case "现货交易贵金属":
                break;
            case "全球股指查询":
                break;
            case "周公解梦":
                break;
            case "婚姻匹配":
                break;
            case "手机号码查吉凶":
                break;
            case "八字算命":
                break;
            case "老黄历":
                break;
            case "电影票房":
                break;
            case "火车票查询":
                break;
            case "航班信息查询":
                break;
            case "足球五大联赛":
                break;
            case "健康知识":
                break;
            case "历史上的今天":
                break;
            case "成语大全":
                break;
            case "新华字典":
                break;
            case "今日油价":
                break;
            case "汽车信息查询":
                break;
            case "驾考题库":
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    /**
     * 显示 手机归属地 popupWindow
     */
    public void showPhoneBelongWindow() {
        View convertView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.popupwindow_assistant_phonebelong, null);
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
                    HttpHelper.doGetCall(url, LifeAllAssistantActivity.this, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            LogUtil.d("~~~~~~~~~~~~~~~~~~onFailure~~~~~", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            phoneBelongBean = ModelParseHelper.parsePhoneBelongResult(response.body().string());
                            if (phoneBelongBean.getRetCode().equals("200")) {
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
                    Toast.makeText(LifeAllAssistantActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
            }
        });
        View rootView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.activity_life_all_assistant, null);
        mPopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    /**
     * 显示 邮编  选项
     */
    private void showPostCodeItemDialog() {
        String[] items = {"邮编查询地址", "地址查询邮编"};
        AlertDialog.Builder builder = new AlertDialog.Builder(LifeAllAssistantActivity.this);
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
        View convertView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.popupwindow_assistant_postcode, null);
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
                    HttpHelper.doGetCall(url, LifeAllAssistantActivity.this, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            LogUtil.d("~~~~~~~~~~~~~~~~~~~onFailure~`", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            postCodeResult = ModelParseHelper.parsePostCodeResult(response.body().string());
                            if (postCodeResult.getRetCode().equals("200")) {
                                Message message = new Message();
                                message.what = 201;
                                mHandler.sendMessage(message);
                            } else {
                                Message message = new Message();
                                message.what = 401;
                                mHandler.sendMessage(message);
                            }
                        }
                    });
                } else {
                    Toast.makeText(LifeAllAssistantActivity.this, "请输入邮编号", Toast.LENGTH_SHORT).show();
                }
            }
        });
        View rootView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.activity_life_all_assistant, null);
        mPopupWindow2.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    /**
     * 显示 城市查询邮编 popupWindow
     */
    public void showPostCode2Window() {
        View convertView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.popupwindow_assistant_postcode2, null);
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
                    HttpHelper.doGetCall(url, LifeAllAssistantActivity.this, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            LogUtil.d("~~~~~~~~~~~~~~~~~~~onFailure~`", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            postCodeResult2 = ModelParseHelper.parsePostCode2Result(response.body().string());
                            if (postCodeResult2.getRetCode().equals("200")) {
                                Message message = new Message();
                                message.what = 202;
                                mHandler.sendMessage(message);
                            } else {
                                Message message = new Message();
                                message.what = 402;
                                mHandler.sendMessage(message);
                            }
                        }
                    });
                } else {
                    Toast.makeText(LifeAllAssistantActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                }
            }
        });
        View rootView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.activity_life_all_assistant, null);
        mPopupWindow3.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    /**
     * 显示 基站定位查询 popupWindow
     */
    public void showBaseLocationWindow() {
        View convertView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.popupwindow_assistant_baselocation_query, null);
        mPopupWindow4 = new PopupWindow(convertView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow4.setAnimationStyle(R.style.loginPopupWindow);
        mPopupWindow4.setOutsideTouchable(true);
        mPopupWindow4.setBackgroundDrawable(new ColorDrawable(0x00000000));
        ImageView baseLoaction_close = (ImageView) convertView.findViewById(R.id.assistant_baseLocation_close);
        final LinearLayout query_layout = (LinearLayout) convertView.findViewById(R.id.assistant_baseLocation_query_layout);
        final LinearLayout result_layout = (LinearLayout) convertView.findViewById(R.id.assistant_baseLocation_result_layout);
        final EditText mcc_input = (EditText) convertView.findViewById(R.id.assistant_mcc_input);
        final EditText mnc_input = (EditText) convertView.findViewById(R.id.assistant_mnc_input);
        final EditText lac_input = (EditText) convertView.findViewById(R.id.assistant_lac_input);
        final EditText cell_input = (EditText) convertView.findViewById(R.id.assistant_cell_input);
        TextView baseLocation_query = (TextView) convertView.findViewById(R.id.assistant_baseLocation_query);
        baseLocation_lat = (TextView) convertView.findViewById(R.id.assistant_baseLocation_lat);
        baseLocation_lon = (TextView) convertView.findViewById(R.id.assistant_baseLocation_lon);
        baseLocation_g_lat = (TextView) convertView.findViewById(R.id.assistant_baseLocation_google_lat);
        baseLocation_g_lon = (TextView) convertView.findViewById(R.id.assistant_baseLocation_google_lon);
        baseLocation_range = (TextView) convertView.findViewById(R.id.assistant_baseLocation_range);
        baseLocation_laocation = (TextView) convertView.findViewById(R.id.assistant_baseLocation_location);
        baseLoaction_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow4.dismiss();
            }
        });
        baseLocation_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query_layout.setVisibility(View.GONE);
                result_layout.setVisibility(View.VISIBLE);
                final String mcc = mcc_input.getText().toString().trim();
                final String mnc = mnc_input.getText().toString().trim();
                final String lac = lac_input.getText().toString().trim();
                final String cell = cell_input.getText().toString().trim();
                String url = "http://apicloud.mob.com/station/query?key=" + StringContents.MobAPI_APPKEY + "&mcc=" + mcc + "&mnc=" + mnc + "&lac=" + lac + "&cell=" + cell;
                HttpHelper.doGetCall(url, LifeAllAssistantActivity.this, new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~onFailure~~",e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        queryBean = ModelParseHelper.parseBaseQueryResult(response.body().string());
                        if (!Tools.isNull(mcc) &&!Tools.isNull(mnc)&&!Tools.isNull(lac)&&!Tools.isNull(cell)){
                            if (queryBean.getRetCode().equals("200")){
                                Message message = new Message();
                                message.what = 203;
                                mHandler.sendMessage(message);
                            }else {
                                Message message = new Message();
                                message.what = 403;
                                mHandler.sendMessage(message);
                            }
                        }else {
                            Toast.makeText(LifeAllAssistantActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        View rootView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.activity_life_all_assistant, null);
        mPopupWindow4.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }
    /**
     * 显示 基站定位查询 popupWindow
     */
    public void showAirQulityWindow() {
        View convertView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.popupwindow_assistant_baselocation_query, null);
        mPopupWindow4 = new PopupWindow(convertView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow4.setAnimationStyle(R.style.loginPopupWindow);
        mPopupWindow4.setOutsideTouchable(true);
        mPopupWindow4.setBackgroundDrawable(new ColorDrawable(0x00000000));
        ImageView baseLoaction_close = (ImageView) convertView.findViewById(R.id.assistant_baseLocation_close);
        final LinearLayout query_layout = (LinearLayout) convertView.findViewById(R.id.assistant_baseLocation_query_layout);
        final LinearLayout result_layout = (LinearLayout) convertView.findViewById(R.id.assistant_baseLocation_result_layout);
        final EditText mcc_input = (EditText) convertView.findViewById(R.id.assistant_mcc_input);
        final EditText mnc_input = (EditText) convertView.findViewById(R.id.assistant_mnc_input);
        final EditText lac_input = (EditText) convertView.findViewById(R.id.assistant_lac_input);
        final EditText cell_input = (EditText) convertView.findViewById(R.id.assistant_cell_input);
        TextView baseLocation_query = (TextView) convertView.findViewById(R.id.assistant_baseLocation_query);
        baseLocation_lat = (TextView) convertView.findViewById(R.id.assistant_baseLocation_lat);
        baseLocation_lon = (TextView) convertView.findViewById(R.id.assistant_baseLocation_lon);
        baseLocation_g_lat = (TextView) convertView.findViewById(R.id.assistant_baseLocation_google_lat);
        baseLocation_g_lon = (TextView) convertView.findViewById(R.id.assistant_baseLocation_google_lon);
        baseLocation_range = (TextView) convertView.findViewById(R.id.assistant_baseLocation_range);
        baseLocation_laocation = (TextView) convertView.findViewById(R.id.assistant_baseLocation_location);
        baseLoaction_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow4.dismiss();
            }
        });
        baseLocation_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query_layout.setVisibility(View.GONE);
                result_layout.setVisibility(View.VISIBLE);
                final String mcc = mcc_input.getText().toString().trim();
                final String mnc = mnc_input.getText().toString().trim();
                final String lac = lac_input.getText().toString().trim();
                final String cell = cell_input.getText().toString().trim();
                String url = "http://apicloud.mob.com/station/query?key=" + StringContents.MobAPI_APPKEY + "&mcc=" + mcc + "&mnc=" + mnc + "&lac=" + lac + "&cell=" + cell;
                HttpHelper.doGetCall(url, LifeAllAssistantActivity.this, new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~onFailure~~",e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        queryBean = ModelParseHelper.parseBaseQueryResult(response.body().string());
                        if (!Tools.isNull(mcc) &&!Tools.isNull(mnc)&&!Tools.isNull(lac)&&!Tools.isNull(cell)){
                            if (queryBean.getRetCode().equals("200")){
                                Message message = new Message();
                                message.what = 203;
                                mHandler.sendMessage(message);
                            }else {
                                Message message = new Message();
                                message.what = 403;
                                mHandler.sendMessage(message);
                            }
                        }else {
                            Toast.makeText(LifeAllAssistantActivity.this, "请填写完整", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        View rootView = LayoutInflater.from(LifeAllAssistantActivity.this).inflate(R.layout.activity_life_all_assistant, null);
        mPopupWindow4.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }
}
