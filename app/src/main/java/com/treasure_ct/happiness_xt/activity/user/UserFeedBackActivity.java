package com.treasure_ct.happiness_xt.activity.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.FeedBackBean;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class UserFeedBackActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private EditText edit_Content;
    private EditText edit_Contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed_back);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        btn_back.setVisibility(View.VISIBLE);
        btn_send.setVisibility(View.VISIBLE);

        initFindId();
        initClick();

        btn_send.setClickable(false);
        btn_send.setTextColor(getResources().getColor(R.color.colorGray2));
    }

    private void initFindId() {
        edit_Content = (EditText) findViewById(R.id.feedBack_content);
        edit_Contacts = (EditText) findViewById(R.id.feedBack_contacts);
    }

    private void initClick() {
        btn_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        edit_Content.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_send:
                sendFeedBack();
                break;
        }
    }

    private void sendFeedBack() {
        FeedBackBean feedBackBean = new FeedBackBean();
        if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))){
            UserInfoBean userInfo = (UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo");
            feedBackBean.setUser_name(userInfo.getUser_name());
            feedBackBean.setUser_nick(userInfo.getNick_name());
        }else {
            feedBackBean.setUser_name("匿名");
            feedBackBean.setUser_nick("匿名");
        }
        feedBackBean.setContent(edit_Content.getText().toString().trim());
        if (!Tools.isNull(edit_Contacts.getText().toString().trim())){
            feedBackBean.setContacts(edit_Contacts.getText().toString().trim());
        }else {
            feedBackBean.setContacts("无");
        }
        feedBackBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    Toast.makeText(UserFeedBackActivity.this, "我们已收到你的反馈，谢谢支持！", Toast.LENGTH_SHORT).show();
                    UserFeedBackActivity.this.finish();
                }else {
                    Toast.makeText(UserFeedBackActivity.this, "提交失败，原因："+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}
