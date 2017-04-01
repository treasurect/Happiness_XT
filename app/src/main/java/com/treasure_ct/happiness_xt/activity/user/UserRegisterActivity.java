package com.treasure_ct.happiness_xt.activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.utils.Tools;

public class UserRegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText editPhone;
    private EditText editCode;
    private TextView btnSendMes;
    private TextView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        btn_back.setImageResource(R.mipmap.icon_return);
        btn_back.setVisibility(View.VISIBLE);
        title.setText("注册");
        initFindId();
        initClick();
    }

    private void initClick() {
        btnLogin.setOnClickListener(this);
        btnSendMes.setOnClickListener(this);
    }

    private void initFindId() {
        editPhone = (EditText) findViewById(R.id.mine_register_phone);
        editCode = (EditText) findViewById(R.id.mine_register_verification_code);
        btnSendMes = (TextView) findViewById(R.id.user_send_message);
        btnLogin = (TextView) findViewById(R.id.btn_user_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user_login:
                Intent intent = new Intent(UserRegisterActivity.this, UserEditUserInfoActivity.class);
                if (!Tools.isNull(editPhone.getText().toString().trim())){
                    intent.putExtra("UserPhone",editPhone.getText().toString().trim());
                }
                startActivity(intent);
                break;
            case R.id.user_send_message:
                break;
        }
    }
}
