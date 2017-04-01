package com.treasure_ct.happiness_xt.activity.assistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.LifeAssistantGridAdapter;
import com.treasure_ct.happiness_xt.bean.LifeAssistantGridBean;

import java.util.ArrayList;
import java.util.List;

public class LifeAllAssistantActivity extends AppCompatActivity implements LifeAssistantGridAdapter.LifeAssistantClickItem {
    private String[] assistant_text1 = {"地图", "天气预报", "手机号码归属地", " 邮编查询", "菜谱查询", "基站定位查询",
            "空气质量", "身份证查询", "IP地址", "万年历", "中国彩票开奖", "微信精选"};
    private int[] assistant_image1 = {R.mipmap.icon_location, R.mipmap.icon_weather, R.mipmap.icon_phone, R.mipmap.icon_postcode,
            R.mipmap.icon_greens, R.mipmap.icon_location, R.mipmap.icon_air, R.mipmap.icon_idcard,
            R.mipmap.icon_ip, R.mipmap.icon_calendar, R.mipmap.icon_lottery, R.mipmap.icon_wechat_2};

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_all_assistant);
        initFindId();
        initGridView();
    }

    private void initGridView() {
        //填充gridView1
        List<LifeAssistantGridBean> list = new ArrayList<>();
        for (int i = 0; i < assistant_image1.length; i++) {
            LifeAssistantGridBean gridBean = new LifeAssistantGridBean();
            gridBean.setText(assistant_text1[i]);
            gridBean.setImage(assistant_image1[i]);
            list.add(gridBean);
        }
        LifeAssistantGridAdapter adapter1 = new LifeAssistantGridAdapter(this, list);
        gridView1.setAdapter(adapter1);
        adapter1.setLifeAssistantClickItem(this);
        //填充GridView2
        List<LifeAssistantGridBean> list2 = new ArrayList<>();
        for (int i = 0; i < assistant_image2.length; i++) {
            LifeAssistantGridBean gridBean = new LifeAssistantGridBean();
            gridBean.setText(assistant_text2[i]);
            gridBean.setImage(assistant_image2[i]);
            list2.add(gridBean);
        }
        LifeAssistantGridAdapter adapter2 = new LifeAssistantGridAdapter(this, list2);
        gridView2.setAdapter(adapter2);
        adapter2.setLifeAssistantClickItem(this);
        //填充GridView3
        List<LifeAssistantGridBean> list3 = new ArrayList<>();
        for (int i = 0; i < assistant_image3.length; i++) {
            LifeAssistantGridBean gridBean = new LifeAssistantGridBean();
            gridBean.setText(assistant_text3[i]);
            gridBean.setImage(assistant_image3[i]);
            list3.add(gridBean);
        }
        LifeAssistantGridAdapter adapter3 = new LifeAssistantGridAdapter(this, list3);
        gridView3.setAdapter(adapter3);
        adapter3.setLifeAssistantClickItem(this);
        //填充GridView4
        List<LifeAssistantGridBean> list4 = new ArrayList<>();
        for (int i = 0; i < assistant_image4.length; i++) {
            LifeAssistantGridBean gridBean = new LifeAssistantGridBean();
            gridBean.setText(assistant_text4[i]);
            gridBean.setImage(assistant_image4[i]);
            list4.add(gridBean);
        }
        LifeAssistantGridAdapter adapter4 = new LifeAssistantGridAdapter(this, list4);
        gridView4.setAdapter(adapter4);
        adapter4.setLifeAssistantClickItem(this);
    }

    private void initFindId() {
        gridView1 = (GridView) findViewById(R.id.assistant_all_gridView1);
        gridView2 = (GridView) findViewById(R.id.assistant_all_gridView2);
        gridView3 = (GridView) findViewById(R.id.assistant_all_gridView3);
        gridView4 = (GridView) findViewById(R.id.assistant_all_gridView4);
    }

    @Override
    public void assistantClickItem(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        switch (name){
            case "地图":
                break;
            case "天气预报":
                break;
            case "手机号码归属地":
                break;
            case "邮编查询":
                break;
            case "菜谱查询":
                break;
            case "基站定位查询":
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
            case "微信精选":
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
}
