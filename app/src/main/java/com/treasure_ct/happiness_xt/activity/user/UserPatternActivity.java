package com.treasure_ct.happiness_xt.activity.user;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.XTApplication;
import com.treasure_ct.happiness_xt.utils.Tools;

public class UserPatternActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout normal_layout, cartoon_layout;
    private ImageView normal_image, cartoon_image;
    private TextView pattern_complete;
    private XTApplication mApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pattern);
        Tools.setTranslucentStatus(this);
        initTitle();
        btn_back.setVisibility(View.VISIBLE);
        title.setText("模式选择");
        initFindId();
        mApplication = (XTApplication) getApplication();
        if (mApplication.getPattern().equals("normal")) {
            normal_image.setVisibility(View.VISIBLE);
        } else if (mApplication.getPattern().equals("cartoon")) {
            cartoon_image.setVisibility(View.VISIBLE);
        }
        initClick();
    }

    private void initFindId() {
        normal_layout = (FrameLayout) findViewById(R.id.mine_pattern_normal_layout);
        cartoon_layout = (FrameLayout) findViewById(R.id.mine_pattern_cartoon_layout);
        normal_image = (ImageView) findViewById(R.id.mine_pattern_normal_image);
        cartoon_image = (ImageView) findViewById(R.id.mine_pattern_cartoon_image);
        pattern_complete = (TextView) findViewById(R.id.mine_pattern_complete);
    }

    private void initClick() {
        btn_back.setOnClickListener(this);
        normal_layout.setOnClickListener(this);
        cartoon_layout.setOnClickListener(this);
        pattern_complete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                UserPatternActivity.this.finish();
                break;
            case R.id.mine_pattern_normal_layout:
                normal_image.setVisibility(View.VISIBLE);
                cartoon_image.setVisibility(View.GONE);
                mApplication.setPattern("normal");
                break;
            case R.id.mine_pattern_cartoon_layout:
                normal_image.setVisibility(View.GONE);
                cartoon_image.setVisibility(View.VISIBLE);
                mApplication.setPattern("cartoon");
                break;
            case R.id.mine_pattern_complete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("确认");
                builder.setMessage("是否将默认模式改为"+mApplication.getPattern()+"?");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                        editor.putString("user_pattern", mApplication.getPattern());
                        editor.apply();
                        UserPatternActivity.this.finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
        }
    }
}
