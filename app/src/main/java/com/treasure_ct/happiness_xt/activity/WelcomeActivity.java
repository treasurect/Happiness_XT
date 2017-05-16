package com.treasure_ct.happiness_xt.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.MainActivity;
import com.treasure_ct.happiness_xt.R;

import java.io.IOException;

public class WelcomeActivity extends BaseActivity {
    private Handler mHandler = new Handler();
    private SurfaceView videoView;
    private MediaPlayer mPlayer;
    private SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
         videoView = (SurfaceView) findViewById(R.id.welcome_video);
        setMediaPlayer();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        },3000);
    }

    private void setMediaPlayer() {
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome);
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(this,uri);
            mHolder = videoView.getHolder();
            mHolder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    mPlayer.setDisplay(mHolder);
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
            mPlayer.prepare();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mPlayer.start();
                    mPlayer.setLooping(true);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
