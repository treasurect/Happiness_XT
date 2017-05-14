package com.treasure_ct.happiness_xt.activity.entertainment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

public class HomeJokerDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_joker_detail);
        Tools.setTranslucentStatus(this);
        initTitle();
        title.setText("详情");
        btn_back.setVisibility(View.VISIBLE);
        initFindId();
        getIntentData();
        initClick();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent.getExtras().get("groupBean")!=null){
            HomeJokerListBean.DataBeanXX.DataBeanX.GroupBean groupBean = (HomeJokerListBean.DataBeanXX.DataBeanX.GroupBean) intent.getExtras().get("groupBean");
            Toast.makeText(this, groupBean.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initFindId() {

    }

    private void initClick() {
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
