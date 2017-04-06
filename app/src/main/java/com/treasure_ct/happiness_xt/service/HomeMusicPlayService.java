package com.treasure_ct.happiness_xt.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/5.
 */

public class HomeMusicPlayService extends Service{
    private String url;
    private MediaPlayer mediaPlayer;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){

            try {
                url = intent.getStringExtra("url");
                mediaPlayer.setDataSource(url);
                mediaPlayer.setLooping(true);//单曲循环
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
