package com.treasure_ct.happiness_xt;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.activity.dynamic.DynamicActivity;
import com.treasure_ct.happiness_xt.activity.entertainment.EntertainmentActivity;
import com.treasure_ct.happiness_xt.activity.life.LifeActivity;
import com.treasure_ct.happiness_xt.activity.user.UserActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {

    private ImageView idle_anim;
    private ImageView talk_anim;
    private ImageView walk_left_anim;
    private ImageView walk_right_anim;
    private ImageView click_anim;
    private AnimationDrawable idleAnimationDrawable;
    private AnimationDrawable talkAnimationDrawable;
    private AnimationDrawable walkLeftAnimationDrawable;
    private AnimationDrawable walkRightAnimationDrawable;
    private AnimationDrawable clickAnimationDrawable;
    private int screenWidth, screenHeight;
    private FrameLayout main_layout;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    walk_right_anim.setVisibility(View.GONE);
                    talk_anim.setVisibility(View.VISIBLE);
                    click_anim.setVisibility(View.VISIBLE);
                    talkAnimationDrawable.start();
                    main_layout.setClickable(true);
                    hint_text.setText("传统的页面让人疲倦，而我喜欢另辟蹊径\n\n随着社会飞速进步，我们的压力与日俱增\n" +
                            "难得一寸净土，让我们尽情享受吧\n试着点击屏幕，进行下一步吧");
                    break;
                case 201:
                    walk_left_anim.setVisibility(View.GONE);
                    idle_anim.setVisibility(View.VISIBLE);
                    main_layout.setClickable(true);
                    hint_text.setText("试着从下面的选项中寻找到你想要的吧\n\n点击即可进入，长按可以查看缩略图");
                    main_selectPage.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    private boolean isPageStroy;
    private int click_flag = 0;
    private TextView hint_text;
    private TextView main_entertainment;
    private TextView main_life;
    private TextView main_dynamic;
    private TextView main_settings;
    private LinearLayout main_selectPage;
    private PopupWindow popupWindow;
    private Bitmap bitmap;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFindId();
        initFrameAnim();
        getWindowPiexl();
        initClick();
        idle_anim.setVisibility(View.VISIBLE);
        click_anim.setVisibility(View.VISIBLE);
        clickAnimationDrawable.start();
        idleAnimationDrawable.start();
        hint_text.setText("点击屏幕进行下一步吧");
    }

    private void initFindId() {
        main_layout = (FrameLayout) findViewById(R.id.activity_main);
        idle_anim = (ImageView) findViewById(R.id.main_idle_animList);
        talk_anim = (ImageView) findViewById(R.id.main_talk_animList);
        walk_left_anim = (ImageView) findViewById(R.id.main_walk_left_animList);
        walk_right_anim = (ImageView) findViewById(R.id.main_walk_right_animList);
        click_anim = (ImageView) findViewById(R.id.main_click_animList);
        hint_text = (TextView) findViewById(R.id.main_hint_text);
        main_selectPage = (LinearLayout) findViewById(R.id.main_selectPage);
        main_entertainment = (TextView) findViewById(R.id.main_entertainment);
        main_life = (TextView) findViewById(R.id.main_life);
        main_dynamic = (TextView) findViewById(R.id.main_dynamic);
        main_settings = (TextView) findViewById(R.id.main_settings);
    }

    private void initFrameAnim() {
        idleAnimationDrawable = ((AnimationDrawable) idle_anim.getDrawable());
        talkAnimationDrawable = ((AnimationDrawable) talk_anim.getDrawable());
        walkLeftAnimationDrawable = ((AnimationDrawable) walk_left_anim.getDrawable());
        walkRightAnimationDrawable = ((AnimationDrawable) walk_right_anim.getDrawable());
        clickAnimationDrawable = ((AnimationDrawable) click_anim.getDrawable());
    }

    private void getWindowPiexl() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }

    //位移动画
    public void setTransAnim(ImageView image, float fromX, float fromY, float toX, float toY, int duration) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(image, "translationX", fromX, toX, toX);
        animatorX.setDuration(duration);
        animatorX.start();

        ObjectAnimator animatorY = ObjectAnimator.ofFloat(image, "translationY", fromY, toY, toY);
        animatorY.setDuration(duration);
        animatorY.start();
    }

    private void initClick() {
        main_layout.setOnClickListener(this);
        main_entertainment.setOnClickListener(this);
        main_life.setOnClickListener(this);
        main_dynamic.setOnClickListener(this);
        main_settings.setOnClickListener(this);

        main_entertainment.setOnLongClickListener(this);
        main_life.setOnLongClickListener(this);
        main_dynamic.setOnLongClickListener(this);
        main_settings.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main:
                click_flag++;
                if (click_flag == 1) {
                    main_layout.setClickable(false);
                    idle_anim.setVisibility(View.GONE);
                    click_anim.setVisibility(View.GONE);
                    walk_right_anim.setVisibility(View.VISIBLE);
                    setTransAnim(walk_right_anim, 0, 0, screenWidth - 300, 0, 4000);
                    walkRightAnimationDrawable.start();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                if (!isPageStroy) {
                                    mHandler.sendMessage(mHandler.obtainMessage(200));
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                if (click_flag == 2) {
                    main_layout.setClickable(false);
                    talk_anim.setVisibility(View.GONE);
                    click_anim.setVisibility(View.GONE);
                    walk_left_anim.setVisibility(View.VISIBLE);
                    setTransAnim(walk_left_anim, 0, 0, 300-screenWidth, 0, 4000);
                    walkLeftAnimationDrawable.start();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                if (!isPageStroy) {
                                    mHandler.sendMessage(mHandler.obtainMessage(201));
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                break;
            case R.id.main_entertainment:
                 intent = new Intent(this, EntertainmentActivity.class);
                if (Build.VERSION.SDK_INT >= 21) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, main_entertainment, "entertainment").toBundle());
                }else {
                    startActivity(intent);
                }
                break;
            case R.id.main_life:
                intent = new Intent(this, LifeActivity.class);
                if (Build.VERSION.SDK_INT >= 21) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, main_life, "life").toBundle());
                }else {
                    startActivity(intent);
                }
                break;
            case R.id.main_dynamic:
                intent = new Intent(this, DynamicActivity.class);
                if (Build.VERSION.SDK_INT >= 21) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, main_dynamic, "dynamic").toBundle());
                }else {
                    startActivity(intent);
                }
                break;
            case R.id.main_settings:
                intent = new Intent(this, UserActivity.class);
                if (Build.VERSION.SDK_INT >= 21) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, main_settings, "settings").toBundle());
                }else {
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.main_entertainment:
                 bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.main_enter_screenshot);
                showPopupWindow(bitmap);
                break;
            case R.id.main_life:
                 bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.main_life_screenshot);
                showPopupWindow(bitmap);
                break;
            case R.id.main_dynamic:
                 bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.main_dynamic_screenshot);
                showPopupWindow(bitmap);
                break;
            case R.id.main_settings:
                 bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.main_settings_screenshot);
                showPopupWindow(bitmap);
                break;
        }
        return false;
    }
    //显示  游戏线索
    private void showPopupWindow(Bitmap bitmap) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.main_page_long_click, null);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(inflate);
        popupWindow.setAnimationStyle(R.style.alpha_popupWindow_style);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        ImageView image = ((ImageView) inflate.findViewById(R.id.main_page_long_click_image));
        image.setImageBitmap(bitmap);
        popupWindow.showAsDropDown(idle_anim);
    }
    @Override
    protected void onDestroy() {
        isPageStroy = true;
        super.onDestroy();
    }
}
