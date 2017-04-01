package com.treasure_ct.happiness_xt.utils;

import com.google.gson.Gson;
import com.treasure_ct.happiness_xt.bean.MobAPIWeatherResultBean;

/**
 * 使用Gson解析接口数据帮助类
 * Created by Administrator on 2016/12/8.
 */

public class ModelParseHelper {

//    /**
//     * 示例----单个对象的转化
//     *
//     * @param str：传入的json格式字符串
//     * @return 单个对象
//     */
//    public static PictrueBean parsePictrueBean(String str) {
//        if (str == null || str.equals("")) return null;
//        Gson gson = new Gson();
//        return gson.fromJson(str, PictrueBean.class);
//
//    }
//
//    /**
//     * 示例----集合对象转化
//     *
//     * @param str 传入的json格式字符串
//     * @return 对象集合
//     */
//    public static List<PictrueBean> parsePictureBeanList(String str) {
//        if (str == null || str.equals("")) return null;
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<PictrueBean>>() {
//        }.getType();
//        return gson.fromJson(str, type);
//    }

    public static MobAPIWeatherResultBean parseWeatherResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, MobAPIWeatherResultBean.class);

    }
}
