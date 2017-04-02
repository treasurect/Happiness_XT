package com.treasure_ct.happiness_xt.fragments;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
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

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAllAssistantActivity;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAssistantMapActivity;
import com.treasure_ct.happiness_xt.activity.assistant.LifeAssistantWeatherActivity;
import com.treasure_ct.happiness_xt.adapter.LifeAssistantGridAdapter;
import com.treasure_ct.happiness_xt.bean.AssistantPhoneBelongBean;
import com.treasure_ct.happiness_xt.bean.LifeAssistantGridBean;
import com.treasure_ct.happiness_xt.bean.MobAPIWeatherResultBean;
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

public class AssistantFragment extends Fragment implements LifeAssistantGridAdapter.LifeAssistantClickItem {
    private GridView gridView;
    private String[] assistant_list_text = {"天气预报", "地图", "手机归属地", "微信精选", "健康知识", "汽车信息", "驾考题库", "全部"};
    private int[] assistant_list_image = {R.mipmap.icon_weather, R.mipmap.icon_location, R.mipmap.icon_phone, R.mipmap.icon_wechat_2,
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_assistant, container, false);
        initFindId(view);
        initGridView();
        return view;
    }

    private void initFindId(View view) {
        gridView = (GridView) view.findViewById(R.id.assistant_gridView);
    }

    private void initGridView() {
        List<LifeAssistantGridBean> list = new ArrayList<>();
        for (int i = 0; i < assistant_list_text.length; i++) {
            LifeAssistantGridBean lifeAssistantGridBean = new LifeAssistantGridBean();
            lifeAssistantGridBean.setText(assistant_list_text[i]);
            lifeAssistantGridBean.setImage(assistant_list_image[i]);
            list.add(lifeAssistantGridBean);
        }
        LifeAssistantGridAdapter adapter = new LifeAssistantGridAdapter(getContext(), list);
        gridView.setAdapter(adapter);
        adapter.setLifeAssistantClickItem(this);
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
            case "微信精选":
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
}

