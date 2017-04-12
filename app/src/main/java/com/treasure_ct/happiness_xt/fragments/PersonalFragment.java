package com.treasure_ct.happiness_xt.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.XTApplication;
import com.treasure_ct.happiness_xt.activity.user.UserEditUserInfoActivity;
import com.treasure_ct.happiness_xt.activity.user.UserRegisterActivity;
import com.treasure_ct.happiness_xt.activity.user.UserSettingsActivity;
import com.treasure_ct.happiness_xt.bean.UserInfoBean;
import com.treasure_ct.happiness_xt.boradcastreceiver.CommonDataReceiver;
import com.treasure_ct.happiness_xt.utils.ACache;
import com.treasure_ct.happiness_xt.utils.LogUtil;
import com.treasure_ct.happiness_xt.utils.StringContents;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class PersonalFragment extends Fragment implements View.OnClickListener {
    private PopupWindow mPopupWindow;
    private ImageView imageNight, mine_login_icon, imageHistory, imageSettings;
    private TextView mine_login_username, register;
    private String user_name;
    private ImageView qqLogin, weChatLogin, sinaLogin, qqweiboLogin;
    private EditText editPwd, editPhone;
    private FrameLayout messagePush_layout, offLine_layout, active_layout, feedBack_layout, shop_layout;
    private XTApplication application;
    private IntentFilter filter;
    private CommonDataReceiver commonDataReceiver;
    private LinearLayout layoutNight, layoutHistory,layoutSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        application = (XTApplication) getActivity().getApplication();

        receiveBoradCast();
        initFindId(view);
        initView();
        initClick();
        return view;
    }

    private void receiveBoradCast() {
        filter = new IntentFilter();
        filter.addAction(StringContents.ACTION_COMMENTDATA);
        commonDataReceiver = new CommonDataReceiver();
        commonDataReceiver.setDoUiReceiver(new CommonDataReceiver.DoUiReceiver() {
            @Override
            public void doUi(Context context, Intent intent) {
                if (intent.getExtras().getString("label").equals("login")){
                    if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))){
                        if (BaseActivity.aCache.getAsString("token").equals("login")){
                            mine_login_icon.setImageResource(R.mipmap.icon);
                            mine_login_username.setText("用户：" + ((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getNick_name());
                        }
                    }else {
                        mine_login_icon.setImageResource(R.mipmap.icon_login);
                        mine_login_username.setText("登录让内容更精彩");
                    }
                }
            }
        });
        getContext().registerReceiver(commonDataReceiver, filter);
    }

    private void initFindId(View view) {
        mine_login_icon = (ImageView) view.findViewById(R.id.mine_login_icon);
        mine_login_username = (TextView) view.findViewById(R.id.mine_login_username);
        imageNight = (ImageView) view.findViewById(R.id.mine_night_icon);
        layoutNight = (LinearLayout) view.findViewById(R.id.mine_night_layout);
        imageHistory = (ImageView) view.findViewById(R.id.mine_history_icon);
        layoutHistory = (LinearLayout) view.findViewById(R.id.mine_history_layout);
        imageSettings = (ImageView) view.findViewById(R.id.mine_settings_icon);
        layoutSettings = (LinearLayout) view.findViewById(R.id.mine_settings_layout);
        messagePush_layout = (FrameLayout) view.findViewById(R.id.mine_messagePush_layout);
        offLine_layout = (FrameLayout) view.findViewById(R.id.mine_offLine_layout);
        active_layout = (FrameLayout) view.findViewById(R.id.mine_active_layout);
        feedBack_layout = (FrameLayout) view.findViewById(R.id.mine_feedBack_layout);
        shop_layout = (FrameLayout) view.findViewById(R.id.mine_shop_layout);
    }

    private void initView() {
        //用户名 头像
        if (!Tools.isNull(BaseActivity.aCache.getAsString("token"))){
            if (BaseActivity.aCache.getAsString("token").equals("login")){
                mine_login_icon.setImageResource(R.mipmap.icon);
                mine_login_username.setText("用户：" + ((UserInfoBean) BaseActivity.aCache.getAsObject("UserInfo")).getNick_name());
            }
        }else {
            mine_login_icon.setImageResource(R.mipmap.icon_login);
            mine_login_username.setText("登录让内容更精彩");
        }
        //夜间模式
        if (application.isNight()) {
            imageNight.setImageResource(R.mipmap.icon_night);
            Window window = getActivity().getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = 0.001f;
            window.setAttributes(layoutParams);
        } else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
            Window window = getActivity().getWindow();
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
        messagePush_layout.setOnClickListener(this);
        offLine_layout.setOnClickListener(this);
        active_layout.setOnClickListener(this);
        feedBack_layout.setOnClickListener(this);
        shop_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_login_icon:
                if (Tools.isNull(BaseActivity.aCache.getAsString("token"))){
                    showPopupWindow();
                }else {
                    if (BaseActivity.aCache.getAsString("token").equals("login")){
                        Intent intent = new Intent(getContext(), UserEditUserInfoActivity.class);
                        intent.putExtra("edit_type","normal");
                        startActivity(intent);
                    }else {
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
                startActivity(new Intent(getContext(), UserSettingsActivity.class ));
                break;
            case R.id.mine_messagePush_layout:
                break;
            case R.id.mine_offLine_layout:
                break;
            case R.id.mine_active_layout:
                break;
            case R.id.mine_shop_layout:
                break;
            case R.id.mine_feedBack_layout:
                break;
            case R.id.mine_popup_quit:
                quitpopupWindow();
                break;
            case R.id.mine_popup_loginin:
                Loginin();
                break;
            case R.id.mine_popup_register:
                mPopupWindow.dismiss();
                startActivity(new Intent(getContext(), UserRegisterActivity.class));
                break;
            case R.id.image_qqlogin:
                break;
            case R.id.image_wechatlogin:
                break;
            case R.id.image_sinalogin:
                break;
            case R.id.image_qqweibologin:
                break;
        }
    }

    /**
     * 登录操作
     */
    private void Loginin() {
        if (Tools.isNull(editPhone.getText().toString().trim())){
            Toast.makeText(getContext(), "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Tools.isNull(editPwd.getText().toString().trim())){
            Toast.makeText(getContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        BmobQuery<UserInfoBean> query = new BmobQuery<>();
        query.addWhereEqualTo("user_name",editPhone.getText().toString().trim());
        query.findObjects(new FindListener<UserInfoBean>() {
            @Override
            public void done(List<UserInfoBean> list, BmobException e) {
                if (e== null){
                    if (list.size() == 0){
                        Toast.makeText(getContext(), "手机号错误", Toast.LENGTH_SHORT).show();
                    }else {
                        for (int i = 0; i < list.size(); i++) {
                            LogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~",list.get(i).getUser_name());
                        }
                        if (editPwd.getText().toString().trim().equals(list.get(0).getUser_pwd())){
                            Toast.makeText(getContext(), "恭喜你，登陆成功", Toast.LENGTH_SHORT).show();
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
                            BaseActivity.aCache.put("token","login");
                            //发送登录成功 广播
                            Intent intent = new Intent();
                            intent.setAction(StringContents.ACTION_COMMENTDATA);
                            intent.putExtra("label","login");
                            getContext().sendBroadcast(intent);
                            mPopupWindow.dismiss();
                        }else {
                            Toast.makeText(getContext(), "密码错误", Toast.LENGTH_SHORT).show();
                            editPwd.setText("");
                        }
                    }
                }else {
                    Toast.makeText(getContext(), "原因："+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 显示 关闭 popupWindow
     */
    public void showPopupWindow() {
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_mine_login, null);
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setAnimationStyle(R.style.loginPopupWindow);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x66000000));

        ImageView quit = (ImageView) convertView.findViewById(R.id.mine_popup_quit);
        editPhone = (EditText) convertView.findViewById(R.id.mine_login_phone);
        editPwd = (EditText) convertView.findViewById(R.id.mine_login_password);
        TextView login = (TextView) convertView.findViewById(R.id.mine_popup_loginin);
        register = (TextView) convertView.findViewById(R.id.mine_popup_register);
        qqLogin = ((ImageView) convertView.findViewById(R.id.image_qqlogin));
        weChatLogin = ((ImageView) convertView.findViewById(R.id.image_wechatlogin));
        sinaLogin = ((ImageView) convertView.findViewById(R.id.image_sinalogin));
        qqweiboLogin = ((ImageView) convertView.findViewById(R.id.image_qqweibologin));

        quit.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        qqLogin.setOnClickListener(this);
        weChatLogin.setOnClickListener(this);
        sinaLogin.setOnClickListener(this);
        qqweiboLogin.setOnClickListener(this);

        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_personal, null);
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    private void quitpopupWindow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

    /**
     * 夜间模式的切换
     */
    private void nightSwitch() {
        if (!application.isNight()) {
            imageNight.setImageResource(R.mipmap.icon_night);
            application.setNight(true);
            Window window = getActivity().getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = 0.001f;
            window.setAttributes(layoutParams);
            application.setNight(true);
        } else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
            application.setNight(false);
            Window window = getActivity().getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = -1;
            window.setAttributes(layoutParams);
            application.setNight(false);
        }
    }

    @Override
    public void onDestroyView() {
        getContext().unregisterReceiver(commonDataReceiver);
        super.onDestroyView();
    }
}
