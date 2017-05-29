package com.treasure_ct.happiness_xt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.fragments.DynamicFragment;
import com.treasure_ct.happiness_xt.fragments.EntertainmentFragment;
import com.treasure_ct.happiness_xt.fragments.LifeFragment;
import com.treasure_ct.happiness_xt.fragments.UserFragment;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout main_layout;
    private LinearLayout happy_layout,assistant_layout,dynamic_layout,settings_layout;
    private ImageView happy_image,assistant_image,dynamic_image,settings_image;
    private TextView happy_text,assistant_text,dynamic_text,settings_text;
    private Fragment entertainment,life,dynamic,user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initFindId();
        initClick();
        initView();
    }

    private void initView() {
        entertainment = new EntertainmentFragment();
        life = new LifeFragment();
        dynamic = new DynamicFragment();
        user = new UserFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame,entertainment);
        transaction.commit();
        initBottom();
        happy_image.setImageResource(R.mipmap.icon_happy);
        happy_text.setTextColor(getResources().getColor(R.color.colorOrange));
        happy_text.setTextSize(14.0f);
    }

    private void initFindId() {
        main_layout = (FrameLayout) findViewById(R.id.main_frame);
        happy_layout = (LinearLayout)findViewById(R.id.main_happy_layout);
        happy_image = (ImageView) findViewById(R.id.main_happy_image);
        happy_text = (TextView) findViewById(R.id.main_happy_text);
        assistant_layout = (LinearLayout)findViewById(R.id.main_assistant_layout);
        assistant_image = (ImageView) findViewById(R.id.main_assistant_image);
        assistant_text = (TextView) findViewById(R.id.main_assistant_text);
        dynamic_layout = (LinearLayout)findViewById(R.id.main_dynamic_layout);
        dynamic_image = (ImageView) findViewById(R.id.main_dynamic_image);
        dynamic_text = (TextView) findViewById(R.id.main_dynamic_text);
        settings_layout = (LinearLayout)findViewById(R.id.main_settings_layout);
        settings_image = (ImageView) findViewById(R.id.main_settings_image);
        settings_text = (TextView) findViewById(R.id.main_settings_text);
    }

    private void initClick() {
        happy_layout.setOnClickListener(this);
        assistant_layout.setOnClickListener(this);
        dynamic_layout.setOnClickListener(this);
        settings_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.main_happy_layout:
                initBottom();
                happy_image.setImageResource(R.mipmap.icon_happy);
                happy_text.setTextColor(getResources().getColor(R.color.colorOrange));
                happy_text.setTextSize(14.0f);
                transaction.replace(R.id.main_frame,entertainment);
                break;
            case R.id.main_assistant_layout:
                initBottom();
                assistant_image.setImageResource(R.mipmap.icon_assistant);
                assistant_text.setTextColor(getResources().getColor(R.color.colorOrange));
                assistant_text.setTextSize(14.0f);
                transaction.replace(R.id.main_frame,life);
                break;
            case R.id.main_dynamic_layout:
                initBottom();
                dynamic_image.setImageResource(R.mipmap.icon_dynamic);
                dynamic_text.setTextColor(getResources().getColor(R.color.colorOrange));
                dynamic_text.setTextSize(14.0f);
                transaction.replace(R.id.main_frame,dynamic);
                break;
            case R.id.main_settings_layout:
                initBottom();
                settings_image.setImageResource(R.mipmap.icon_settings);
                settings_text.setTextColor(getResources().getColor(R.color.colorOrange));
                settings_text.setTextSize(14.0f);
                transaction.replace(R.id.main_frame,user);
                break;
        }
        transaction.commit();
    }
    private void  initBottom(){
        happy_image.setImageResource(R.mipmap.icon_happy2);
        happy_text.setTextColor(getResources().getColor(R.color.colorBlock));
        happy_text.setTextSize(12.0f);
        assistant_image.setImageResource(R.mipmap.icon_assistant2);
        assistant_text.setTextColor(getResources().getColor(R.color.colorBlock));
        assistant_text.setTextSize(12.0f);
        dynamic_image.setImageResource(R.mipmap.icon_dynamic2);
        dynamic_text.setTextColor(getResources().getColor(R.color.colorBlock));
        dynamic_text.setTextSize(12.0f);
        settings_image.setImageResource(R.mipmap.icon_settings2);
        settings_text.setTextColor(getResources().getColor(R.color.colorBlock));
        settings_text.setTextSize(12.0f);
    }
}
