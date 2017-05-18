package com.treasure_ct.happiness_xt.activity.entertainment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeEmbarrassListBean;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.text.SimpleDateFormat;

public class  HomeEmbarrassDetailActivity extends BaseActivity implements View.OnClickListener {
    private SimpleDraweeView userIcon;
    private TextView userName;
    private TextView userFans;
    private TextView content;
    private TextView top;
    private TextView low;
    private TextView comments;
    private TextView transPond;
    private SimpleDraweeView comments_userIcon;
    private TextView comments_userName;
    private TextView comments_userTime;
    private TextView comments_top;
    private TextView comments_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_embarrass_detail);
        Tools.setTranslucentStatus(this);
        initTitle();
        title.setText("详情");
        btn_back.setVisibility(View.VISIBLE);
        initFindId();
        initClick();
        getIntentData();
    }

    private void initFindId() {
        userIcon = (SimpleDraweeView) findViewById(R.id.home_embarrass_detail_userIcon);
        userName = (TextView) findViewById(R.id.home_embarrass_detail_userName);
        userFans = (TextView) findViewById(R.id.home_embarrass_detail_userFans);
        content = (TextView) findViewById(R.id.home_embarrass_detail_content);
        top = (TextView) findViewById(R.id.home_embarrass_detail_top);
        low = (TextView) findViewById(R.id.home_embarrass_detail_low);
        comments = (TextView) findViewById(R.id.home_embarrass_detail_comments);
        transPond = (TextView) findViewById(R.id.home_embarrass_detail_transPond);
        comments_userIcon = (SimpleDraweeView) findViewById(R.id.home_embarrass_detail_comments_userIcon);
        comments_userName = (TextView) findViewById(R.id.home_embarrass_detail_comments_userName);
        comments_userTime = (TextView) findViewById(R.id.home_embarrass_detail_comments_userTime);
        comments_top = (TextView) findViewById(R.id.home_embarrass_detail_comments_top);
        comments_content = (TextView) findViewById(R.id.home_embarrass_detail_comments_content);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent.getExtras().get("itemsBean") != null) {
            HomeEmbarrassListBean.ItemsBean itemsBean = (HomeEmbarrassListBean.ItemsBean) intent.getExtras().get("itemsBean");
            if (itemsBean.getUser() != null) {
                if (!Tools.isNull(itemsBean.getUser().getThumb())) {
                    userIcon.setImageURI(Uri.parse("http:"+itemsBean.getUser().getThumb()));
                }
                if (!Tools.isNull(itemsBean.getUser().getLogin())) {
                    userName.setText(itemsBean.getUser().getLogin());
                }
                if (!Tools.isNull(String.valueOf(itemsBean.getPublished_at()))) {
                    String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(itemsBean.getPublished_at());
                    userFans.setText(time.substring(5,7)+"月"+time.substring(8,10)+"日  "+time.substring(11,16));
                }
            }
            if (!Tools.isNull(itemsBean.getContent())) {
                content.setText(itemsBean.getContent());
            }
            if (itemsBean.getVotes() != null){
                if (!Tools.isNull(String.valueOf(itemsBean.getVotes().getUp()))) {
                    top.setText(String.valueOf(itemsBean.getVotes().getUp()));
                }
                if (!Tools.isNull(String.valueOf(itemsBean.getVotes().getDown()))) {
                    if (String.valueOf(itemsBean.getVotes().getDown()).length() > 1){
                        low.setText(String.valueOf(itemsBean.getVotes().getDown()).substring(1));
                    }
                }
            }
            if (!Tools.isNull(String.valueOf(itemsBean.getComments_count()))) {
                comments.setText(String.valueOf(itemsBean.getComments_count()));
            }
            if (!Tools.isNull(String.valueOf(itemsBean.getShare_count()))) {
                transPond.setText(String.valueOf(itemsBean.getShare_count()));
            }
            if (itemsBean.getHot_comment() != null){
                if (itemsBean.getHot_comment().getUser()!= null){
                    if (!Tools.isNull(itemsBean.getHot_comment().getUser().getThumb())){
                        comments_userIcon.setImageURI(Uri.parse("http:"+itemsBean.getHot_comment().getUser().getThumb()));
                    }
                    if (!Tools.isNull(itemsBean.getHot_comment().getUser().getLogin())){
                        comments_userName.setText(itemsBean.getHot_comment().getUser().getLogin());
                    }
                }
                if (!Tools.isNull(itemsBean.getHot_comment().getCreated_at())){
                    comments_userTime.setText(itemsBean.getHot_comment().getCreated_at());
                }
                if (!Tools.isNull(String.valueOf(itemsBean.getHot_comment().getLike_count()))){
                    comments_top.setText(String.valueOf(itemsBean.getHot_comment().getLike_count()));
                }
                if (!Tools.isNull(itemsBean.getHot_comment().getContent())){
                    comments_content.setText(itemsBean.getHot_comment().getContent());
                }
            }
        }
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
