package com.treasure_ct.happiness_xt.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.LifeAssistantGridAdapter;
import com.treasure_ct.happiness_xt.bean.LifeAssistantGridBean;

import java.util.ArrayList;
import java.util.List;

public class AssistantFragment extends Fragment implements LifeAssistantGridAdapter.LifeAssistantClickItem {
    private GridView gridView;
    private String[] assistant_list_text = {"天气预报", "空气质量", "万年历", "微信精选", "健康知识", "汽车信息", "驾考题库", "全部"};
    private int[] assistant_list_image = {R.mipmap.icon_weather,R.mipmap.icon_air,R.mipmap.icon_calendar,R.mipmap.icon_wechat_2,
            R.mipmap.icon_healthy,R.mipmap.icon_car,R.mipmap.icon_driving,R.mipmap.icon_all};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_assistant, container, false);
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
        switch (name){
            case "天气预报":
                break;
            case "空气质量":
                break;
            case "万年历":
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
                break;
        }
    }
}
