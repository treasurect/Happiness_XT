package com.treasure_ct.happiness_xt.activity.life;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.fragments.LifeTrafficAirFragment;
import com.treasure_ct.happiness_xt.fragments.LifeTrafficTrainFragment;
import com.treasure_ct.happiness_xt.utils.Tools;

public class LifeTrafficActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_traffic);
        Tools.setTranslucentStatus(this);
        initTitle();
        push_RG.setVisibility(View.VISIBLE);
        noti_RB.setText("火车票");
        other_RB.setText("机票");
        noti_RB.setChecked(true);
        noti_RB.setTextColor(getResources().getColor(R.color.colorOrange));
        btn_back.setVisibility(View.VISIBLE);
        initFindId();
        initClick();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.train_content_layout,new LifeTrafficTrainFragment());
        transaction.commit();
    }

    private void initFindId() {

    }

    private void initClick() {
        btn_back.setOnClickListener(this);
        push_RG.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RadioButton radioButton = (RadioButton) findViewById(group.getCheckedRadioButtonId());
        if (radioButton.getText().equals("火车票")){
            noti_RB.setTextColor(getResources().getColor(R.color.colorOrange));
            other_RB.setTextColor(getResources().getColor(R.color.colorWhite));
            transaction.replace(R.id.train_content_layout,new LifeTrafficTrainFragment());
        }else if (radioButton.getText().equals("机票")){
            noti_RB.setTextColor(getResources().getColor(R.color.colorWhite));
            other_RB.setTextColor(getResources().getColor(R.color.colorOrange));
            transaction.replace(R.id.train_content_layout,new LifeTrafficAirFragment());
        }
        transaction.commit();
    }
}
