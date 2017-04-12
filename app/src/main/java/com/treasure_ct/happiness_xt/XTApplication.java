package com.treasure_ct.happiness_xt;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;

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
        Bmob.initialize(this, "17f1e9675e74b57f488a963655a8ff99");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);

        //百度地图初始化
        SDKInitializer.initialize(getApplicationContext());

        //fresco的初始化
        Fresco.initialize(this);

        //Mob的初始化
        SMSSDK.initSDK(this, "1cc26b90803f5", "78a3e40107b81c515b56737efe2cfee8");

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
