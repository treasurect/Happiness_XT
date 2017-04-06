package com.treasure_ct.happiness_xt.utils;

import com.google.gson.Gson;
import com.treasure_ct.happiness_xt.bean.AssistantBaseLocationQueryBean;
import com.treasure_ct.happiness_xt.bean.AssistantPhoneBelongBean;
import com.treasure_ct.happiness_xt.bean.AssistantPostCode2Bean;
import com.treasure_ct.happiness_xt.bean.AssistantPostCodeBean;
import com.treasure_ct.happiness_xt.bean.AssistantWeatherResultBean;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.bean.HomeNewsTopListBean;

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

    /**
     * 解析 助手-天气-未来几天
     * @param str
     * @return
     */
    public static AssistantWeatherResultBean parseWeatherResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, AssistantWeatherResultBean.class);

    }
    /**
     * 解析 助手-手机归属地
     * @param str
     * @return
     */
    public static AssistantPhoneBelongBean parsePhoneBelongResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, AssistantPhoneBelongBean.class);

    }
    /**
     * 解析 助手-邮编查城市
     * @param str
     * @return
     */
    public static AssistantPostCodeBean parsePostCodeResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, AssistantPostCodeBean.class);

    }
    /**
     * 解析 助手-城市查邮编
     * @param str
     * @return
     */
    public static AssistantPostCode2Bean parsePostCode2Result(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, AssistantPostCode2Bean.class);

    }
    /**
     * 解析 助手-基站查询
     * @param str
     * @return
     */
    public static AssistantBaseLocationQueryBean parseBaseQueryResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, AssistantBaseLocationQueryBean.class);

    }
    /**
     * 解析 home-新闻头条
     * @param str
     * @return
     */
    public static HomeNewsTopListBean parseNewsTopResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, HomeNewsTopListBean.class);

    }
    /**
     * 解析 home-段子
     * @param str
     * @return
     */
    public static HomeJokerListBean parseJokerResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, HomeJokerListBean.class);

    }
}
