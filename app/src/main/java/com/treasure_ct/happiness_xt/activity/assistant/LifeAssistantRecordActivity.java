package com.treasure_ct.happiness_xt.activity.assistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.DynamicBean;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LifeAssistantRecordActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private TextView user_name,image_num;
    private ImageView user_icon;
    private EditText editDesc;
    private ImageView image1,image2,image3,camera,album;
    private String textDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_assistant_record);
        initView();
        initFindId();
        initClick();
        editDesc.addTextChangedListener(this);
        btn_send.setClickable(false);
        btn_send.setTextColor(getResources().getColor(R.color.colorGray2));
    }

    private void initView() {
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        btn_back.setVisibility(View.VISIBLE);
        title.setText("记录生活");
        btn_send.setVisibility(View.VISIBLE);
    }

    private void initFindId() {
        user_icon = (ImageView) findViewById(R.id.assistant_record_user_icon);
        user_name = (TextView) findViewById(R.id.assistant_record_user_name);
        editDesc = (EditText) findViewById(R.id.assistant_record_desc);
        image1 = (ImageView) findViewById(R.id.assistant_record_image1);
        image2 = (ImageView) findViewById(R.id.assistant_record_image2);
        image3 = (ImageView) findViewById(R.id.assistant_record_image3);
        image_num = (TextView) findViewById(R.id.assistant_record_image_num);
        camera = (ImageView) findViewById(R.id.assistant_record_camera);
        album = (ImageView) findViewById(R.id.assistant_record_album);
    }

    private void initClick() {
        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                LifeAssistantRecordActivity.this.finish();
                break;
            case R.id.btn_send:
                sendDynamic();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!Tools.isNull(s.toString().trim())){
            btn_send.setClickable(true);
            btn_send.setTextColor(getResources().getColor(R.color.colorWhite));
        }else {
            btn_send.setClickable(false);
            btn_send.setTextColor(getResources().getColor(R.color.colorGray2));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void sendDynamic() {
        textDesc = editDesc.getText().toString().trim();
        DynamicBean dynamicBean = new DynamicBean();
        dynamicBean.setUser_name(((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getUser_name());
        dynamicBean.setUser_nick(((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getNick_name());
        dynamicBean.setUser_icon("");
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Long(System.currentTimeMillis()));
        dynamicBean.setPublish_time(time.substring(5,7)+"月"+time.substring(8,10)+"日"+time.substring(11,16));
        dynamicBean.setContent(textDesc);
        List<String> image_list = new ArrayList<>();
        image_list.add("0");
        dynamicBean.setImage(image_list);
        dynamicBean.setSendTop(0);
        dynamicBean.setComments(0);
        dynamicBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(LifeAssistantRecordActivity.this, "恭喜你，发表成功", Toast.LENGTH_SHORT).show();
                    //发送登录成功 广播
                    Intent intent = new Intent();
                    intent.setAction(StringContents.ACTION_COMMENTDATA);
                    intent.putExtra("label","dynamic");
                    sendBroadcast(intent);
                    LifeAssistantRecordActivity.this.finish();
                } else {
                    Toast.makeText(LifeAssistantRecordActivity.this, "很遗憾，发表失败\n原因：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
