package com.treasure_ct.happiness_xt.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class UserEditUserInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView editPhone;
    private EditText editNick;
    private EditText editPwd;
    private EditText editAge;
    private EditText editDesc;
    private RadioButton sex_man;
    private RadioButton sex_woman;
    private TextView btnEnter;
    private int sex;
    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    private Pattern p = Pattern.compile("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
    private String edit_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_user_info);
        initTitle();//基础activity里初始化标题栏
        Tools.setTranslucentStatus(this);//沉浸模式
        title.setText("信息修改");
        initFindId();
        receiveIntentData();
        initClick();
    }

    private void initFindId() {
        editPhone = (TextView) findViewById(R.id.mine_edit_phone);
        editNick = (EditText) findViewById(R.id.mine_edit_nickname);
        editPwd = (EditText) findViewById(R.id.mine_edit_password);
        editAge = (EditText) findViewById(R.id.mine_edit_age);
        editDesc = (EditText) findViewById(R.id.mine_edit_userDesc);
        sex_man = (RadioButton) findViewById(R.id.mine_edit_sex_man);
        sex_woman = (RadioButton) findViewById(R.id.mine_edit_sex_woman);
        btnEnter = (TextView) findViewById(R.id.btn_user_edit_enter);
    }

    private void receiveIntentData() {
        Intent intent = getIntent();
        if (!Tools.isNull(intent.getStringExtra("UserPhone"))) {
            String userPhone = intent.getStringExtra("UserPhone");
            editPhone.setText(userPhone);
            editNick.setText(userPhone);
        } else {
            editNick.setText("小王");
        }
        if (!Tools.isNull(intent.getStringExtra("edit_type"))) {
            edit_type = intent.getStringExtra("edit_type");
            if (edit_type.equals("normal")) {
                btn_back.setVisibility(View.VISIBLE);
                btn_back.setOnClickListener(this);
                btnEnter.setText("修改资料");
            }
        }
        editAge.setText("0");
        sex_man.setChecked(true);
    }

    private void initClick() {
        btnEnter.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user_edit_enter:
                if (Tools.isNull(editPwd.getText().toString().trim())) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Tools.isNull(editNick.getText().toString().trim())) {
                    Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Tools.isNull(editAge.getText().toString().trim())) {
                    Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Tools.isNull(editDesc.getText().toString().trim())) {
                    Toast.makeText(this, "让更多人认识你", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Tools.isNull(edit_type)){
                    if (edit_type.equals("normal")){
                        editAccount();
                    }else {
                        editRegister();
                    }
                }
                break;
            case R.id.btn_back:
                UserEditUserInfoActivity.this.finish();
                break;
        }
    }

    private void editAccount() {
        BmobQuery<UserInfoBean> query = new BmobQuery<UserInfoBean>();
        query.addWhereEqualTo("user_name", editPhone.getText().toString());
        query.findObjects(new FindListener<UserInfoBean>() {
            @Override
            public void done(List<UserInfoBean> object, BmobException e) {
                if(e==null){
                    for (UserInfoBean gameScore : object) {
                        toEdit(gameScore.getObjectId());
                    }
                }
            }
        });
    }

    private void toEdit(String objectId) {
        if (sex_man.isChecked()) {
            sex = 0;
        } else if (sex_woman.isChecked()) {
            sex = 1;
        }
        final UserInfoBean infoBean = new UserInfoBean();
        infoBean.setUser_name(editPhone.getText().toString().trim());
        infoBean.setNick_name(editNick.getText().toString().trim() + "");
        infoBean.setUser_pwd(editPwd.getText().toString().trim());
        infoBean.setAge(Integer.parseInt(editAge.getText().toString().trim()));
        infoBean.setSex(sex);
        infoBean.setUser_desc(editDesc.getText().toString().trim() + "");
        infoBean.setUser_icon("暂无头像");
        infoBean.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    Toast.makeText(UserEditUserInfoActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                    //缓存
                    BaseActivity.aCache.put("UserInfo", (Serializable) infoBean);
                    BaseActivity.aCache.put("token", "login");
                    //发送登录成功 广播
                    Intent intent = new Intent();
                    intent.setAction(StringContents.ACTION_COMMENTDATA);
                    intent.putExtra("label", "login");
                    sendBroadcast(intent);
                    UserEditUserInfoActivity.this.finish();
                }else {
                    Toast.makeText(UserEditUserInfoActivity.this, "更新失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void editRegister() {
        /**
         * 查询是否重复
         */
        BmobQuery<UserInfoBean> query = new BmobQuery<>();
        query.addWhereEqualTo("user_name", editPhone.getText().toString().trim());
        query.count(UserInfoBean.class, new CountListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    if (integer > 0) {
                        Toast.makeText(UserEditUserInfoActivity.this, "手机号已被注册", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        toRegister();
                    }
                } else {
                    Toast.makeText(UserEditUserInfoActivity.this, "原因：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void toRegister() {
        /**
         * 注册
         */
        if (sex_man.isChecked()) {
            sex = 0;
        } else if (sex_woman.isChecked()) {
            sex = 1;
        }
        final UserInfoBean infoBean = new UserInfoBean();
        infoBean.setUser_name(editPhone.getText().toString().trim());
        infoBean.setNick_name(editNick.getText().toString().trim() + "");
        infoBean.setUser_pwd(editPwd.getText().toString().trim());
        infoBean.setAge(Integer.parseInt(editAge.getText().toString().trim()));
        infoBean.setSex(sex);
        infoBean.setUser_desc(editDesc.getText().toString().trim() + "");
        infoBean.setUser_icon("暂无头像");

        infoBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(UserEditUserInfoActivity.this, "恭喜你，注册成功", Toast.LENGTH_SHORT).show();
                    //缓存
                    BaseActivity.aCache.put("UserInfo", (Serializable) infoBean);
                    BaseActivity.aCache.put("token", "login");
                    //发送登录成功 广播
                    Intent intent = new Intent();
                    intent.setAction(StringContents.ACTION_COMMENTDATA);
                    intent.putExtra("label", "login");
                    sendBroadcast(intent);
                    UserEditUserInfoActivity.this.finish();
                } else {
                    Toast.makeText(UserEditUserInfoActivity.this, "很遗憾，注册失败\n原因：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!Tools.isNull(edit_type)){
            if (edit_type.equals("normal")) {
                super.onBackPressed();
            } else if (edit_type.equals("register")) {
                Toast.makeText(this, "请提交自己的信息", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
