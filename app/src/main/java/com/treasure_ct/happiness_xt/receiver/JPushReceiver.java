package com.treasure_ct.happiness_xt.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.activity.user.UserPushActivity;
import com.treasure_ct.happiness_xt.bean.PushBean;
import com.treasure_ct.happiness_xt.utils.LogUtil;

import org.json.JSONObject;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/4/10.
 */

public class JPushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPushReceiver";
    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        Bundle bundle = intent.getExtras();


        /**
         * t通知
         */
        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            receivingNotification(context,bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            openNotification(context,bundle);

        } else {
            LogUtil.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }

    private void receivingNotification(final Context context, Bundle bundle){
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String message = bundle.getString(JPushInterface.EXTRA_ALERT);
        PushBean pushBean = new PushBean();
        pushBean.setTitle(title);
        pushBean.setMessage(message);
        pushBean.setLabel("normal");
        pushBean.setImage_url("");
        pushBean.setTop_title("");
        pushBean.setUrl("");
        pushBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    Toast.makeText(context, "收到了一条通知", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openNotification(Context context, Bundle bundle){
        context.startActivity(new Intent(context, UserPushActivity.class));
    }
}
