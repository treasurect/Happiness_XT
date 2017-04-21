package com.treasure_ct.happiness_xt.activity.life;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.utils.Tools;
import com.treasure_ct.happiness_xt.widget.CustomScrollListView;

public class LifeDynamicItemActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private ScrollView scrollView;
    private CustomScrollListView listView;
    private SimpleDraweeView user_icon;
    private TextView user_nick;
    private TextView publish_time;
    private TextView content;
    private TextView top_num;
    private TextView comments_num;
    private EditText input;
    private TextView send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_dynamic_item);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        title.setText("详情");
        btn_back.setVisibility(View.VISIBLE);
        initFinId();
        receiveIntent();
        initListView();
        initScrollView();
        initClick();
    }

    private void initFinId() {
        scrollView = (ScrollView) findViewById(R.id.life_dynamic_item_detail_scrollView);
        listView = (CustomScrollListView) findViewById(R.id.life_dynamic_item_detail_listView);
        user_icon = (SimpleDraweeView) findViewById(R.id.life_dynamic_item_detail_icon);
        user_nick = (TextView) findViewById(R.id.assistant_dynamic_item_detail_nick);
        publish_time = (TextView) findViewById(R.id.assistant_dynamic_item_detail_time);
        content = (TextView) findViewById(R.id.assistant_dynamic_item_detail_content);
        top_num = (TextView) findViewById(R.id.assistant_dynamic_item_detail_good_num);
        comments_num = (TextView) findViewById(R.id.assistant_dynamic_item_detail_comments_num);
        input = (EditText) findViewById(R.id.life_dynamic_item_detail_input);
        send = (TextView) findViewById(R.id.life_dynamic_item_detail_send);
    }

    private void receiveIntent() {
        Intent intent = getIntent();
        if (!Tools.isNull(intent.getStringExtra("user_nick"))) {
            user_nick.setText(intent.getStringExtra("user_nick"));
        }
        if (!Tools.isNull(intent.getStringExtra("publish_time"))) {
            publish_time.setText(intent.getStringExtra("publish_time"));
        }
        if (!Tools.isNull(intent.getStringExtra("content"))) {
            content.setText(intent.getStringExtra("content"));
        }
        if (!Tools.isNull(intent.getStringExtra("top"))) {
            top_num.setText(intent.getStringExtra("top"));
        }
        if (!Tools.isNull(intent.getStringExtra("comments"))) {
            comments_num.setText(intent.getStringExtra("comments"));
        }
    }

    private void initClick() {
        btn_back.setOnClickListener(this);
        input.addTextChangedListener(this);
    }

    private void initListView() {
    }

    private void initScrollView() {
        scrollView.smoothScrollTo(0, 20);
        listView.setFocusable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!Tools.isNull(s.toString().trim())) {
            send.setClickable(true);
            send.setBackgroundResource(R.drawable.bottom_gold_yellow_radius5);
        } else {
            send.setClickable(false);
            send.setBackgroundResource(R.drawable.bottom_gray_radius5);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
