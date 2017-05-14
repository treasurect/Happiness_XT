package com.treasure_ct.happiness_xt.activity.user;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.XTApplication;
import com.treasure_ct.happiness_xt.activity.life.LifeRobotActivity;
import com.treasure_ct.happiness_xt.activity.life.LifeVrWholeActivity;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.receiver.CommonDataReceiver;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class UserActivity extends BaseActivity implements View.OnClickListener {
    private PopupWindow mPopupWindow;
    private ImageView imageNight, mine_login_icon, imageHistory, imageSettings;
    private TextView mine_login_username;
    private String user_name;
    private ImageView qqLogin, weChatLogin, sinaLogin, qqweiboLogin;
    private EditText editPwd, editPhone;
    private FrameLayout signIn_layout,messagePush_layout, feedBack_layout, turing_layout, vrWhole_layout;
    private XTApplication application;
    private IntentFilter filter;
    private CommonDataReceiver commonDataReceiver;
    private LinearLayout layoutNight, layoutHistory, layoutSettings;
    private Tencent mTencent;
    private IUiListener loginListener; //授权登录监听器
    private IUiListener userInfoListener; //获取用户信息监听器
    private UserInfo userInfo; //qq用户信息
    private ImageView pass_visible;
    private boolean isHind = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        application = (XTApplication) getApplication();
        Tools.setTranslucentStatus(this);
        mTencent = Tencent.createInstance(StringContents.QQ_Login_APPID, getApplicationContext());

        receiveBoradCast();
        initFindId();
        initView();
        initClick();
    }
    private void receiveBoradCast() {
        filter = new IntentFilter();
        filter.addAction(StringContents.ACTION_COMMENTDATA);
        commonDataReceiver = new CommonDataReceiver();
        commonDataReceiver.setDoUiReceiver(new CommonDataReceiver.DoUiReceiver() {
            @Override
            public void doUi(Context context, Intent intent) {
                if (intent.getExtras().getString("label").equals("login")) {
                    if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))) {
                        if (BaseActivity.aCache.getAsString("token").equals("login")) {
                            mine_login_icon.setImageResource(R.mipmap.icon);
                            mine_login_username.setText("用户：" + ((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getNick_name());
                        }
                    } else {
                        mine_login_icon.setImageResource(R.mipmap.icon_login);
                        mine_login_username.setText("登录让内容更精彩");
                    }
                }
            }
        });
        registerReceiver(commonDataReceiver, filter);
    }

    private void initFindId() {
        mine_login_icon = (ImageView) findViewById(R.id.mine_login_icon);
        mine_login_username = (TextView) findViewById(R.id.mine_login_username);
        imageNight = (ImageView) findViewById(R.id.mine_night_icon);
        layoutNight = (LinearLayout) findViewById(R.id.mine_night_layout);
        imageHistory = (ImageView) findViewById(R.id.mine_history_icon);
        layoutHistory = (LinearLayout) findViewById(R.id.mine_history_layout);
        imageSettings = (ImageView) findViewById(R.id.mine_settings_icon);
        layoutSettings = (LinearLayout) findViewById(R.id.mine_settings_layout);
        signIn_layout = (FrameLayout) findViewById(R.id.mine_signIn_layout);
        messagePush_layout = (FrameLayout) findViewById(R.id.mine_messagePush_layout);
        feedBack_layout = (FrameLayout) findViewById(R.id.mine_feedBack_layout);
        turing_layout = (FrameLayout) findViewById(R.id.mine_turing_layout);
        vrWhole_layout = (FrameLayout) findViewById(R.id.mine_vr_whole_scene_layout);
    }

    private void initView() {
        //用户名 头像
        if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))) {
            if (BaseActivity.aCache.getAsString("token").equals("login")) {
                mine_login_icon.setImageResource(R.mipmap.icon);
                mine_login_username.setText("用户：" + ((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getNick_name());
            }
        } else {
            mine_login_icon.setImageResource(R.mipmap.icon_login);
            mine_login_username.setText("登录让内容更精彩");
        }
        //夜间模式
        if (application.isNight()) {
            imageNight.setImageResource(R.mipmap.icon_night);
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = 0.001f;
            window.setAttributes(layoutParams);
        } else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = -1;
            window.setAttributes(layoutParams);
        }
    }

    private void initClick() {
        mine_login_icon.setOnClickListener(this);
        layoutNight.setOnClickListener(this);
        layoutHistory.setOnClickListener(this);
        layoutSettings.setOnClickListener(this);
        signIn_layout.setOnClickListener(this);
        messagePush_layout.setOnClickListener(this);
        feedBack_layout.setOnClickListener(this);
        turing_layout.setOnClickListener(this);
        vrWhole_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_login_icon:
                if (Tools.isNull(BaseActivity.aCache.getAsString("token"))) {
                    showPopupWindow();
                } else {
                    if (BaseActivity.aCache.getAsString("token").equals("login")) {
                        Intent intent = new Intent(UserActivity.this, UserEditUserInfoActivity.class);
                        intent.putExtra("edit_type", "normal");
                        intent.putExtra("UserPhone", ((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getUser_name());
                        startActivity(intent);
                    } else {
                        showPopupWindow();
                    }
                }
                break;
            case R.id.mine_history_layout:
                break;
            case R.id.mine_night_layout:
                nightSwitch();
                break;
            case R.id.mine_settings_layout:
                startActivity(new Intent(UserActivity.this, UserSettingsActivity.class));
                break;
            case R.id.mine_signIn_layout:
                startActivity(new Intent(UserActivity.this, UserSignInActivity.class));
                break;
            case R.id.mine_messagePush_layout:
                startActivity(new Intent(UserActivity.this, UserPushActivity.class));
                break;
            case R.id.mine_feedBack_layout:
                startActivity(new Intent(UserActivity.this, UserFeedBackActivity.class));
                break;
            case R.id.mine_turing_layout:
                startActivity(new Intent(UserActivity.this, LifeRobotActivity.class));
                break;
            case R.id.mine_vr_whole_scene_layout:
                startActivity(new Intent(UserActivity.this, LifeVrWholeActivity.class));
                break;
            case R.id.mine_popup_quit:
                quitpopupWindow();
                break;
            case R.id.mine_popup_loginin:
                Loginin();
                break;
            case R.id.mine_popup_register:
                mPopupWindow.dismiss();
                startActivity(new Intent(UserActivity.this, UserRegisterActivity.class));
                break;
            case R.id.mine_popup_forget_password:
                mPopupWindow.dismiss();
                startActivity(new Intent(UserActivity.this, UserForgetPassActivity.class));
                break;
            case R.id.mine_login_password_visible:
                if (isHind){
                    initNoHindPassInput();
                    isHind = false;
                }else {
                    initHindPassInput();
                    isHind = true;
                }
                break;
            case R.id.image_qqlogin:
                mPopupWindow.dismiss();
                initQQLogin();
                requestQQLogin();
                break;
            case R.id.image_wechatlogin:
                mPopupWindow.dismiss();
                Toast.makeText(UserActivity.this, "正在开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_sinalogin:
                mPopupWindow.dismiss();
                Toast.makeText(UserActivity.this, "正在开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_qqweibologin:
                mPopupWindow.dismiss();
                Toast.makeText(UserActivity.this, "正在开发中", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 夜间模式的切换
     */
    private void nightSwitch() {
        if (!application.isNight()) {
            imageNight.setImageResource(R.mipmap.icon_night);
            application.setNight(true);
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = 0.001f;
            window.setAttributes(layoutParams);
            application.setNight(true);
        } else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
            application.setNight(false);
            Window window = getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = -1;
            window.setAttributes(layoutParams);
            application.setNight(false);
        }
    }

    /**
     * 显示 关闭 popupWindow
     */
    public void showPopupWindow() {
        View convertView = LayoutInflater.from(this).inflate(R.layout.popupwindow_mine_login, null);
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setAnimationStyle(R.style.loginPopupWindow);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x66000000));

        ImageView quit = (ImageView) convertView.findViewById(R.id.mine_popup_quit);
        editPhone = (EditText) convertView.findViewById(R.id.mine_login_phone);
        editPwd = (EditText) convertView.findViewById(R.id.mine_login_password);
        TextView login = (TextView) convertView.findViewById(R.id.mine_popup_loginin);
        TextView register = (TextView) convertView.findViewById(R.id.mine_popup_register);
        TextView forget = (TextView) convertView.findViewById(R.id.mine_popup_forget_password);
        pass_visible = (ImageView) convertView.findViewById(R.id.mine_login_password_visible);
        qqLogin = ((ImageView) convertView.findViewById(R.id.image_qqlogin));
        weChatLogin = ((ImageView) convertView.findViewById(R.id.image_wechatlogin));
        sinaLogin = ((ImageView) convertView.findViewById(R.id.image_sinalogin));
        qqweiboLogin = ((ImageView) convertView.findViewById(R.id.image_qqweibologin));

        quit.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forget.setOnClickListener(this);
        pass_visible.setOnClickListener(this);
        qqLogin.setOnClickListener(this);
        weChatLogin.setOnClickListener(this);
        sinaLogin.setOnClickListener(this);
        qqweiboLogin.setOnClickListener(this);

        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_user, null);
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    private void quitpopupWindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("您确认放弃登录吗？");
        builder.setPositiveButton("放弃", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPopupWindow.dismiss();
            }
        });
        builder.setNegativeButton("继续登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }
    //不隐藏密码
    private void initNoHindPassInput() {
        pass_visible.setImageResource(R.mipmap.icon_eye_open);
        editPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        editPwd.setSelection(editPwd.getText().length());
    }

    //隐藏密码
    private void initHindPassInput() {
        pass_visible.setImageResource(R.mipmap.icon_eye_close);
        editPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editPwd.setSelection(editPwd.getText().length());
    }
    /**
     * 登录操作
     */
    private void Loginin() {
        if (Tools.isNull(editPhone.getText().toString().trim())) {
            Toast.makeText(UserActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Tools.isNull(editPwd.getText().toString().trim())) {
            Toast.makeText(UserActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        BmobQuery<UserInfoBean> query = new BmobQuery<>();
        query.addWhereEqualTo("user_name", editPhone.getText().toString().trim());
        query.findObjects(new FindListener<UserInfoBean>() {
            @Override
            public void done(List<UserInfoBean> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        Toast.makeText(UserActivity.this, "手机号错误", Toast.LENGTH_SHORT).show();
                    } else {
                        if (editPwd.getText().toString().trim().equals(list.get(0).getUser_pwd())) {
                            Toast.makeText(UserActivity.this, "恭喜你，登陆成功", Toast.LENGTH_SHORT).show();
                            //存入缓存
                            UserInfoBean userInfoBean = new UserInfoBean();
                            userInfoBean.setUser_icon("暂无头像");
                            userInfoBean.setUser_name(list.get(0).getUser_name());
                            userInfoBean.setNick_name(list.get(0).getNick_name());
                            userInfoBean.setUser_pwd(list.get(0).getUser_pwd());
                            userInfoBean.setAge(list.get(0).getAge());
                            userInfoBean.setSex(list.get(0).getSex());
                            userInfoBean.setUser_desc(list.get(0).getUser_desc());
                            BaseActivity.aCache.put("UserInfo", ((Serializable) userInfoBean));
                            BaseActivity.aCache.put("token", "login");
                            //发送登录成功 广播
                            Intent intent = new Intent();
                            intent.setAction(StringContents.ACTION_COMMENTDATA);
                            intent.putExtra("label", "login");
                            sendBroadcast(intent);
                            mPopupWindow.dismiss();
                        } else {
                            Toast.makeText(UserActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                            editPwd.setText("");
                        }
                    }
                } else {
                    Toast.makeText(UserActivity.this, "原因：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * QQ Login
     */
    private void initQQLogin() {
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object value) {
                LogUtil.d("~~~~~~~~~~~~~~~~~~loginListener::", value.toString());
                if (value == null) {
                    return;
                }

                try {
                    JSONObject jo = (JSONObject) value;

                    int ret = jo.getInt("ret");

                    if (ret == 0) {
                        Toast.makeText(UserActivity.this, "登录成功", Toast.LENGTH_LONG).show();

                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");
                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };
        userInfoListener = new IUiListener() {
            @Override
            public void onComplete(Object arg0) {
                LogUtil.d("~~~~~~~~~~~~~~~~~~userInfoListener::", arg0.toString());
                if (arg0 == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) arg0;
                    int ret = jo.getInt("ret");
                    System.out.println("json=" + String.valueOf(jo));
                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");

                    Toast.makeText(UserActivity.this, "你好，" + nickName, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };
    }

    private void requestQQLogin() {
        //如果session无效，就开始登录
        if (!mTencent.isSessionValid()) {
            //开始qq授权登录
            mTencent.login(this, "all", loginListener);
        }
        userInfo = new UserInfo(this, mTencent.getQQToken());
        userInfo.getUserInfo(userInfoListener);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(commonDataReceiver);
        if (mTencent != null) {
            //注销登录
            mTencent.logout(this);
        }
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, loginListener);
                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
