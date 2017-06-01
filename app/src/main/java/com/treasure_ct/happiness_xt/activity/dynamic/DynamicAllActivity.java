package com.treasure_ct.happiness_xt.activity.dynamic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.utils.Tools;

public class DynamicAllActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_all);
        Tools.setTranslucentStatus(this);
        btn_back.setVisibility(View.VISIBLE);
        title.setText("全部动态");
    }
}
