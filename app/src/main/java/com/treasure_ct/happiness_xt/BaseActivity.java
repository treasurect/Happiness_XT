package com.treasure_ct.happiness_xt;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.treasure_ct.happiness_xt.utils.ACache;

public class BaseActivity extends FragmentActivity {

    public ImageView btn_back;
    public TextView title;
    public TextView btn_send;

    public static ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化缓存
        if (aCache == null) {
            aCache = ACache.get(this);
        }
    }

    public void initTitle() {
        title = (TextView) findViewById(R.id.text_title);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_send = (TextView) findViewById(R.id.btn_send);
    }

}
