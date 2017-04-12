package com.treasure_ct.happiness_xt.fragments;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAllAssistantActivity;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAssistantMapActivity;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAssistantRecordActivity;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAssistantWeatherActivity;
import com.treasure_ct.happiness_xt.adapter.DynamicListAdapter;
import com.treasure_ct.happiness_xt.adapter.LifeAssistantGridAdapter;
import com.treasure_ct.happiness_xt.bean.AssistantPhoneBelongBean;
import com.treasure_ct.happiness_xt.bean.AssistantGridBean;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.boradcastreceiver.CommonDataReceiver;
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

public class AssistantFragment extends Fragment implements LifeAssistantGridAdapter.LifeAssistantClickItem, View.OnClickListener {
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
            }
        }
    };
    private AssistantPhoneBelongBean resultBean;
    private TextView  phoneBelong_city, phoneBelong_cityCode, phoneBelong_operator, phoneBelong_province, phoneBelong_zipCode;
    private CustomScrollListView listView;
    private List<DynamicBean>list;
    private DynamicListAdapter adapter;
    private NestedScrollView scrollView;
    private TextView record;
    private ImageView camera;
    private IntentFilter filter;
    private CommonDataReceiver commonDataReceiver;
    private FloatingActionButton refresh;

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
        if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))){
            requestDynamicList();
        }
        return view;
    }

    private void receiveBoardCast() {
        filter = new IntentFilter();
        commonDataReceiver = new CommonDataReceiver();
        commonDataReceiver.setDoUiReceiver(new CommonDataReceiver.DoUiReceiver() {
            @Override
            public void doUi(Context context, Intent intent) {
                if (intent.getExtras().getString("label").equals("dynamic")){
                    requestDynamicList();
                }
            }
        });
        getContext().registerReceiver(commonDataReceiver,filter);
    }

    private void initFindId(View view) {
        gridView = (GridView) view.findViewById(R.id.assistant_gridView);
        listView = (CustomScrollListView) view.findViewById(R.id.assistant_listView);
        scrollView = (NestedScrollView) view.findViewById(R.id.assistant_scrollView);
        record = (TextView) view.findViewById(R.id.assistant_record);
        camera = (ImageView) view.findViewById(R.id.assistant_camera);
        refresh = ((FloatingActionButton) view.findViewById(R.id.assistant_listView_refresh));
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
        adapter = new DynamicListAdapter(getContext(),list);
        listView.setAdapter(adapter);
    }

    private void initScrollView() {
        scrollView.smoothScrollTo(0,20);
        listView.setFocusable(false);
    }

    private void initClick() {
        record.setOnClickListener(this);
        camera.setOnClickListener(this);
        refresh.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.assistant_record:
            case R.id.assistant_camera:
                showCameraSelectDialog();
                break;
            case R.id.assistant_listView_refresh:
                if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))){
                    requestDynamicList();
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(refresh, "Rotation", -360, 0);
                    // 设置持续时间
                    objectAnimator.setDuration(1000);
                    // 设置循环播放
                    objectAnimator.setRepeatCount(1);
                    objectAnimator.start();
                }
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
                if(e==null){
                    if (data_list != null){
                        LogUtil.d("~~~~~~~~~~~~~~~~~~~~","lllllllllllllllll");
                        list.clear();
                        Collections.reverse(data_list);
                        list.addAll(data_list);
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(getContext(), "失败："+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showCameraSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] item_list = {"记录生活","拍照","从相册上中选取"};
        builder.setSingleChoiceItems(item_list, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    startActivity(new Intent(getContext(), LifeAssistantRecordActivity.class));
                }else if (which == 1){
                    startActivity(new Intent(getContext(), LifeAssistantRecordActivity.class));
                }else if (which == 2){
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
                startActivity(new Intent(getContext(), LifeAssistantWeatherActivity.class));
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
    public void onDestroyView() {
        getContext().unregisterReceiver(commonDataReceiver);
        super.onDestroyView();
    }
}

