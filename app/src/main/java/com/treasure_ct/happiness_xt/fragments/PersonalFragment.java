package com.treasure_ct.happiness_xt.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.R;

public class PersonalFragment extends Fragment implements View.OnClickListener {
    private PopupWindow mPopupWindow;
    private boolean isNight, isLogin;
    private ImageView imageNight, mine_login_icon, imageCollection, imageSettings;
    private TextView mine_login_username, register;
    private String user_name;
    private ImageView qqLogin, weChatLogin, sinaLogin, qqweiboLogin;
    private EditText editCode, editPhone;
    private FrameLayout messagePush_layout, offLine_layout, active_layout, feedBack_layout, shop_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        initFindId(view);
        initView();
        initClick();
        return view;
    }

    private void initFindId(View view) {
        mine_login_icon = (ImageView) view.findViewById(R.id.mine_login_icon);
        mine_login_username = (TextView) view.findViewById(R.id.mine_login_username);
        imageNight = (ImageView) view.findViewById(R.id.mine_night_icon);
        imageCollection = (ImageView) view.findViewById(R.id.mine_collection_icon);
        imageSettings = (ImageView) view.findViewById(R.id.mine_settings_icon);
        messagePush_layout = (FrameLayout) view.findViewById(R.id.mine_messagePush_layout);
        offLine_layout = (FrameLayout) view.findViewById(R.id.mine_offLine_layout);
        active_layout = (FrameLayout) view.findViewById(R.id.mine_active_layout);
        feedBack_layout = (FrameLayout) view.findViewById(R.id.mine_feedBack_layout);
        shop_layout = (FrameLayout) view.findViewById(R.id.mine_shop_layout);
    }

    private void initView() {
        if (isNight) {
            imageNight.setImageResource(R.mipmap.icon_night);
        } else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
        }
        if (isLogin) {
            user_name = "小米";
            mine_login_icon.setImageResource(R.mipmap.ic_launcher);
            mine_login_username.setText("用户：" + user_name);
        } else {
            mine_login_icon.setImageResource(R.mipmap.icon_login);
            mine_login_username.setText("登录让内容更精彩");
        }
    }

    private void initClick() {
        mine_login_icon.setOnClickListener(this);
        imageNight.setOnClickListener(this);
        imageCollection.setOnClickListener(this);
        imageSettings.setOnClickListener(this);
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
                if (!isLogin) {
                    showPopupWindow();
                } else {

                }
                break;
            case R.id.mine_collection_icon:
                break;
            case R.id.mine_night_icon:
                nightSwitch();
                break;
            case R.id.mine_settings_icon:
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
     * 显示 关闭 popupWindow
     */
    public void showPopupWindow() {
        View convertView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_mine_login, null);
        mPopupWindow = new PopupWindow(convertView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopupWindow.setAnimationStyle(R.style.loginPopupWindow);
        mPopupWindow.setOutsideTouchable(false);

        ImageView quit = (ImageView) convertView.findViewById(R.id.mine_popup_quit);
        TextView send_message = (TextView) convertView.findViewById(R.id.popup_send_message);
        editPhone = (EditText) convertView.findViewById(R.id.mine_login_phone);
        editCode = (EditText) convertView.findViewById(R.id.mine_login_verification_code);
        Button login = (Button) convertView.findViewById(R.id.mine_popup_loginin);
        register = (TextView) convertView.findViewById(R.id.mine_popup_register);
        qqLogin = ((ImageView) convertView.findViewById(R.id.image_qqlogin));
        weChatLogin = ((ImageView) convertView.findViewById(R.id.image_wechatlogin));
        sinaLogin = ((ImageView) convertView.findViewById(R.id.image_sinalogin));
        qqweiboLogin = ((ImageView) convertView.findViewById(R.id.image_qqweibologin));

        send_message.setOnClickListener(this);
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
        if (!isNight) {
            imageNight.setImageResource(R.mipmap.icon_night);
            isNight = true;
            Window window = getActivity().getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = 0.001f;
            window.setAttributes(layoutParams);

        } else {
            imageNight.setImageResource(R.mipmap.icon_daytime);
            isNight = false;
            Window window = getActivity().getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.screenBrightness = -1;
            window.setAttributes(layoutParams);
        }
    }
}
