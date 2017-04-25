package com.treasure_ct.happiness_xt.utils;

import com.google.gson.Gson;
import com.treasure_ct.happiness_xt.bean.HomeWeChatSelectItemBean;
import com.treasure_ct.happiness_xt.bean.HomeWeChatSelectListBean;
import com.treasure_ct.happiness_xt.bean.LifeBaseLocationQueryBean;
import com.treasure_ct.happiness_xt.bean.LifeDeliciousDetailBean;
import com.treasure_ct.happiness_xt.bean.LifeDeliciousListBean;
import com.treasure_ct.happiness_xt.bean.LifePhoneBelongBean;
import com.treasure_ct.happiness_xt.bean.LifePostCode2Bean;
import com.treasure_ct.happiness_xt.bean.LifePostCodeBean;
import com.treasure_ct.happiness_xt.bean.LifeWeatherCityListBean;
import com.treasure_ct.happiness_xt.bean.LifeWeatherResultBean;
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
     *
     * @param str
     * @return
     */
    public static LifeWeatherResultBean parseWeatherResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, LifeWeatherResultBean.class);

    }

    /**
     * 解析 助手-手机归属地
     *
     * @param str
     * @return
     */
    public static LifePhoneBelongBean parsePhoneBelongResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, LifePhoneBelongBean.class);

    }

    /**
     * 解析 助手-邮编查城市
     *
     * @param str
     * @return
     */
    public static LifePostCodeBean parsePostCodeResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, LifePostCodeBean.class);

    }

    /**
     * 解析 助手-城市查邮编
     *
     * @param str
     * @return
     */
    public static LifePostCode2Bean parsePostCode2Result(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, LifePostCode2Bean.class);

    }

    /**
     * 解析 助手-基站查询
     *
     * @param str
     * @return
     */
    public static LifeBaseLocationQueryBean parseBaseQueryResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, LifeBaseLocationQueryBean.class);

    }

    /**
     * 解析 home-新闻头条
     *
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
     *
     * @param str
     * @return
     */
    public static HomeJokerListBean parseJokerResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, HomeJokerListBean.class);

    }

    /**
     * 解析 assistant-天气-城市列表
     *
     * @param str
     * @return
     */
    public static LifeWeatherCityListBean parseCityListResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, LifeWeatherCityListBean.class);

    }
    /**
     * 解析 home-微信精选列表
     *
     * @param str
     * @return
     */
    public static HomeWeChatSelectListBean parseWeChatListResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, HomeWeChatSelectListBean.class);

    }
    /**
     * 解析 home-微信精选列表-内容
     *
     * @param str
     * @return
     */
    public static HomeWeChatSelectItemBean parseWeChatItemResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, HomeWeChatSelectItemBean.class);

    }
    /**
     * 解析 life-美食列表
     *
     * @param str
     * @return
     */
    public static LifeDeliciousListBean parseDeliciousListResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, LifeDeliciousListBean.class);
    }
    /**
     * 解析 life-美食-详情
     *
     * @param str
     * @return
     */
    public static LifeDeliciousDetailBean parseDeliciousDetailResult(String str) {
        if (str == null || str.equals("")) return null;
        Gson gson = new Gson();
        return gson.fromJson(str, LifeDeliciousDetailBean.class);
    }
}
