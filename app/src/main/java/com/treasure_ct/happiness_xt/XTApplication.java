package com.treasure_ct.happiness_xt;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.treasure_ct.happiness_xt.utils.StringContents;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;
import cn.smssdk.SMSSDK;

/**
 * Created by treasure on 2017.03.30.
 */

public class XTApplication extends Application{
    private boolean isNight;

    @Override
    public void onCreate() {
        super.onCreate();
        //Bmob云初始化
        Bmob.initialize(this, StringContents.Bmob_APPKEY);

        //百度地图初始化
        SDKInitializer.initialize(getApplicationContext());

        //fresco的初始化
        Fresco.initialize(this);

        //Mob的初始化
        SMSSDK.initSDK(this, StringContents.SMSSDK_APPKEY, StringContents.SMSSDK_APPSECRET);

        //JPush  初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        setNight(false);
    }
    public boolean isNight() {
        return isNight;
    }

    public void setNight(boolean night) {
        isNight = night;
    }
}
