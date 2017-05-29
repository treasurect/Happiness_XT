package com.treasure_ct.happiness_xt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.utils.Tools;

import java.io.IOException;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    private SurfaceView videoView;
    private MediaPlayer mPlayer;
    private SurfaceHolder mHolder;
    private TextView page_normal;
    private TextView page_cartoon;
    private XTApplication mApplication;
    private CheckBox pattern_moren;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        mApplication = (XTApplication) getApplication();
        initFindId();
        String pattern = getSharedPreferences("user", MODE_PRIVATE).getString("user_pattern", "");
        if (!Tools.isNull(pattern)){
            mApplication.setPattern(pattern);
            if (pattern.equals("normal")){
                page_cartoon.setVisibility(View.GONE);
                page_normal.setVisibility(View.GONE);
                pattern_moren.setVisibility(View.GONE);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(WelcomeActivity.this,Main2Activity.class));
                        WelcomeActivity.this.finish();
                    }
                },3000);
            }else if (pattern.equals("cartoon")){
                page_cartoon.setVisibility(View.GONE);
                page_normal.setVisibility(View.GONE);
                pattern_moren.setVisibility(View.GONE);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                        WelcomeActivity.this.finish();
                    }
                },3000);
            }
        }
        initClick();
        setMediaPlayer();
    }

    private void initFindId() {
        videoView = (SurfaceView) findViewById(R.id.welcome_video);
        page_normal = (TextView) findViewById(R.id.page_normal);
        page_cartoon = (TextView) findViewById(R.id.page_cartoon);
        pattern_moren = (CheckBox) findViewById(R.id.pattern_moren);
    }

    private void initClick() {
        page_normal.setOnClickListener(this);
        page_cartoon.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.page_normal:
                mApplication.setPattern("normal");
                if (pattern_moren.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                    editor.putString("user_pattern","normal");
                    editor.apply();
                }
                startActivity(new Intent(WelcomeActivity.this,Main2Activity.class));
                WelcomeActivity.this.finish();
                break;
            case R.id.page_cartoon:
                mApplication.setPattern("cartoon");
                if (pattern_moren.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                    editor.putString("user_pattern","cartoon");
                    editor.apply();
                }
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                WelcomeActivity.this.finish();
                break;
        }
    }
}
