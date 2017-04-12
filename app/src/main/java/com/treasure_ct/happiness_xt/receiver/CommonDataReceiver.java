package com.treasure_ct.happiness_xt.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/1/5.
 */

public class CommonDataReceiver extends BroadcastReceiver{

    private DoUiReceiver doUiReceiver = null;

    public void setDoUiReceiver(DoUiReceiver doUiReceiver) {
        this.doUiReceiver = doUiReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        doUiReceiver.doUi(context,intent);
    }

    public interface DoUiReceiver{
        void doUi(Context context, Intent intent);
    }

}
