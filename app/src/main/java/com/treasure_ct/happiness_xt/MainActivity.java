package com.treasure_ct.happiness_xt;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.fragments.AssistantFragment;
import com.treasure_ct.happiness_xt.fragments.DynamicFragment;
import com.treasure_ct.happiness_xt.fragments.HomeFragment;
import com.treasure_ct.happiness_xt.fragments.PersonalFragment;
import com.treasure_ct.happiness_xt.utils.Tools;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout tab_home,tab_assistant,tab_collection,tab_personal;
    private ImageView home_image,assistant_image,collection_image,personal_image, tab_plus;
    private TextView home_text,assistant_text,collection_text,personal_text;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式

        transaction = getSupportFragmentManager().beginTransaction();
        initFindId();
        transaction.replace(R.id.replace_fragment,new HomeFragment());
        transaction.commit();
        initClick();
    }

    private void initView() {
        home_image.setImageResource(R.mipmap.btn_home2);
        assistant_image.setImageResource(R.mipmap.btn_assistant2);
        collection_image.setImageResource(R.mipmap.btn_collection2);
        personal_image.setImageResource(R.mipmap.btn_personal2);
        home_text.setTextColor(getResources().getColor(R.color.colorBlock));
        home_text.getPaint().setFakeBoldText(false);
        assistant_text.setTextColor(getResources().getColor(R.color.colorBlock));
        assistant_text.getPaint().setFakeBoldText(false);
        collection_text.setTextColor(getResources().getColor(R.color.colorBlock));
        collection_text.getPaint().setFakeBoldText(false);
        personal_text.setTextColor(getResources().getColor(R.color.colorBlock));
        personal_text.getPaint().setFakeBoldText(false);
    }

    private void initFindId() {
        tab_home = (LinearLayout) findViewById(R.id.main_tab_home);
        tab_assistant = (LinearLayout) findViewById(R.id.main_tab_assistant);
        tab_collection = (LinearLayout) findViewById(R.id.main_tab_collection);
        tab_personal = (LinearLayout) findViewById(R.id.main_tab_personal);
        home_image = (ImageView) findViewById(R.id.main_tab_home_image);
        assistant_image = (ImageView) findViewById(R.id.main_tab_assistant_image);
        collection_image = (ImageView) findViewById(R.id.main_tab_collection_image);
        personal_image = (ImageView) findViewById(R.id.main_tab_personal_image);
        tab_plus = (ImageView) findViewById(R.id.main_tab_plus);
        home_text = (TextView) findViewById(R.id.main_tab_home_text);
        assistant_text = (TextView) findViewById(R.id.main_tab_assistant_text);
        collection_text = (TextView) findViewById(R.id.main_tab_collection_text);
        personal_text = (TextView) findViewById(R.id.main_tab_personal_text);
    }

    private void initClick() {
        tab_home.setOnClickListener(this);
        tab_assistant.setOnClickListener(this);
        tab_collection.setOnClickListener(this);
        tab_personal.setOnClickListener(this);
        tab_plus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        initView();
        transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.main_tab_home:
                home_image.setImageResource(R.mipmap.btn_home);
                home_text.setTextColor(getResources().getColor(R.color.colorRed));
                home_text.getPaint().setFakeBoldText(true);
                transaction.replace(R.id.replace_fragment,new HomeFragment());
                break;
            case R.id.main_tab_assistant:
                assistant_image.setImageResource(R.mipmap.btn_assistant);
                assistant_text.setTextColor(getResources().getColor(R.color.colorRed));
                assistant_text.getPaint().setFakeBoldText(true);
                transaction.replace(R.id.replace_fragment,new AssistantFragment());
                break;
            case R.id.main_tab_collection:
                collection_image.setImageResource(R.mipmap.btn_collection);
                collection_text.setTextColor(getResources().getColor(R.color.colorRed));
                collection_text.getPaint().setFakeBoldText(true);
                transaction.replace(R.id.replace_fragment,new DynamicFragment());
                break;
            case R.id.main_tab_personal:
                personal_image.setImageResource(R.mipmap.btn_personal);
                personal_text.setTextColor(getResources().getColor(R.color.colorRed));
                personal_text.getPaint().setFakeBoldText(true);
                transaction.replace(R.id.replace_fragment,new PersonalFragment());
                break;
            case R.id.main_tab_plus:
                break;
        }
        transaction.commit();
    }
}
