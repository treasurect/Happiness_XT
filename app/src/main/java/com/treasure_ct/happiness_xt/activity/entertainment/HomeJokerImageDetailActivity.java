package com.treasure_ct.happiness_xt.activity.entertainment;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.HomeJokerRecentCommentsAdapter;
import com.treasure_ct.happiness_xt.adapter.HomeJokerTopCommentsAdapter;
import com.treasure_ct.happiness_xt.bean.HomeJokerCommentsBean;
import com.treasure_ct.happiness_xt.bean.HomeJokerImageListBean;
import com.treasure_ct.happiness_xt.bean.HomeJokerListBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.Tools;
import com.treasure_ct.happiness_xt.widget.CustomScrollListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeJokerImageDetailActivity extends BaseActivity implements View.OnClickListener {

    private SimpleDraweeView userIcon;
    private TextView userName;
    private TextView userFans;
    private TextView content;
    private TextView top;
    private TextView low;
    private TextView comments;
    private TextView transPond;
    private CustomScrollListView topComments_listView;
    private CustomScrollListView recentConmentsListView;
    private String group_id;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    progress.setVisibility(View.GONE);
                    if (commentsBean.getData().getTop_comments() != null){
                        top_num.setText("热门评论（"+commentsBean.getData().getTop_comments().size()+"）");
                        top_list.addAll(commentsBean.getData().getTop_comments());
                        top_adapter.notifyDataSetChanged();
                    }
                    if (commentsBean.getData().getRecent_comments() != null){
                        recent_num.setText("新鲜评论（"+commentsBean.getData().getRecent_comments().size()+"）");
                        recent_list.addAll(commentsBean.getData().getRecent_comments());
                        recent_adapter.notifyDataSetChanged();
                    }
                    break;
                case 400:
                    Toast.makeText(HomeJokerImageDetailActivity.this, "原因："+error, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private String error;
    private TextView top_num;
    private TextView recent_num;
    private HomeJokerCommentsBean commentsBean;
    private ProgressBar progress;
    private List<HomeJokerCommentsBean.DataBean.TopCommentsBean> top_list;
    private List<HomeJokerCommentsBean.DataBean.RecentCommentsBean> recent_list;
    private HomeJokerTopCommentsAdapter top_adapter;
    private HomeJokerRecentCommentsAdapter recent_adapter;
    private ScrollView scrollView;
    private SimpleDraweeView content_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_joker_image_detail);
        Tools.setTranslucentStatus(this);
        initTitle();
        title.setText("详情");
        btn_back.setVisibility(View.VISIBLE);
        initFindId();
        getIntentData();
        initClick();
        initListView();
        initScrollView();
        getComments();
    }
    private void initFindId() {
        userIcon = (SimpleDraweeView) findViewById(R.id.home_joker_detail_userIcon);
        userName = (TextView) findViewById(R.id.home_joker_detail_userName);
        userFans = (TextView) findViewById(R.id.home_joker_detail_userFans);
        content = (TextView) findViewById(R.id.home_joker_detail_content);
        top = (TextView) findViewById(R.id.home_joker_detail_top);
        low = (TextView) findViewById(R.id.home_joker_detail_low);
        comments = (TextView) findViewById(R.id.home_joker_detail_comments);
        transPond = (TextView) findViewById(R.id.home_joker_detail_transPond);
        topComments_listView = (CustomScrollListView) findViewById(R.id.home_joker_detail_top_comments);
        recentConmentsListView = (CustomScrollListView) findViewById(R.id.home_joker_detail_recent_comments);
        top_num = (TextView) findViewById(R.id.home_joker_detail_top_num);
        recent_num = (TextView) findViewById(R.id.home_joker_detail_recent_num);
        progress = (ProgressBar) findViewById(R.id.home_joker_detail_progress);
        scrollView = (ScrollView) findViewById(R.id.activity_home_joker_detail);
        content_image = (SimpleDraweeView) findViewById(R.id.home_joker_detail_image);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent.getExtras().get("groupBean") != null) {
            HomeJokerImageListBean.DataBeanX.DataBean.GroupBean groupBean = (HomeJokerImageListBean.DataBeanX.DataBean.GroupBean) intent.getExtras().get("groupBean");
            if (groupBean.getUser() != null) {
                if (!Tools.isNull(groupBean.getUser().getAvatar_url())) {
                    userIcon.setImageURI(Uri.parse(groupBean.getUser().getAvatar_url()));
                }
                if (!Tools.isNull(groupBean.getUser().getName())) {
                    userName.setText(groupBean.getUser().getName());
                }
                if (!Tools.isNull(String.valueOf(groupBean.getUser().getFollowings()))) {
                    userFans.setText(groupBean.getUser().getUgc_count() + "个作品      " + groupBean.getUser().getFollowings() + "个粉丝");
                }
            }
            if (!Tools.isNull(groupBean.getContent())) {
                content.setText(groupBean.getContent());
            } else if (!Tools.isNull(groupBean.getText())) {
                content.setText(groupBean.getText());
            }
            if (groupBean.getLarge_image() != null) {
                if (groupBean.getLarge_image().getUrl_list() != null) {
                    if (groupBean.getLarge_image().getUrl_list().get(0).getUrl() != null) {
                        content_image.setImageURI(Uri.parse(groupBean.getLarge_image().getUrl_list().get(0).getUrl()));
                    }
                }
            }else if (groupBean.getLarge_image_list() != null){
                if (!Tools.isNull(groupBean.getLarge_image_list().get(0).getUrl())){
                    DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                            .setAutoPlayAnimations(true)
                            .setUri(Uri.parse(groupBean.getLarge_image_list().get(0).getUrl()))//设置uri
                            .build();
                    content_image.setController(mDraweeController);
                }
            }
            if (!Tools.isNull(String.valueOf(groupBean.getDigg_count()))) {
                top.setText(groupBean.getDigg_count() + "");
            }
            if (!Tools.isNull(String.valueOf(groupBean.getRepin_count()))) {
                low.setText(groupBean.getRepin_count() + "");
            }
            if (!Tools.isNull(String.valueOf(groupBean.getHas_comments()))) {
                comments.setText(groupBean.getHas_comments() + "");
            }
            if (!Tools.isNull(String.valueOf(groupBean.getShare_count()))) {
                transPond.setText(groupBean.getShare_count() + "");
            }
            if (!Tools.isNull(String.valueOf(groupBean.getId()))) {
                group_id = String.valueOf(groupBean.getId());
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

    private void initListView() {
        top_list = new ArrayList<>();
        top_adapter = new HomeJokerTopCommentsAdapter(this,top_list);
        topComments_listView.setAdapter(top_adapter);

        recent_list = new ArrayList<>();
        recent_adapter = new HomeJokerRecentCommentsAdapter(this,recent_list);
        recentConmentsListView.setAdapter(recent_adapter);
    }
    private void initScrollView() {
        scrollView.smoothScrollTo(0,20);
        topComments_listView.setFocusable(false);
        recentConmentsListView.setFocusable(false);
    }
    private void getComments() {
        if (!Tools.isNull(group_id)){
            String url = "http://iu.snssdk.com/neihan/comments/?group_id="+group_id+"&item_id="+group_id+"&count=20&offset=0&iid=10236690215&device_id=33766398200&ac=wifi&channel=huawei&aid=7&app_name=joke_essay&version_code=625&version_name=6.2.5&device_platform=android&ssmix=a&device_type=HUAWEI+NXT-AL10&device_brand=HUAWEI&os_api=24&os_version=7.0&uuid=860797039338439&openudid=cd9eeccf957d57d2&manifest_version_code=625&resolution=1080*1830&dpi=400&update_version_code=6253";
            HttpHelper.doGetCall(url, this, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    error = e.getMessage();
                    mHandler.sendMessage(mHandler.obtainMessage(400));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String resp = response.body().string();
                    commentsBean = ModelParseHelper.parseJokerCommentsResult(resp);
                    if (commentsBean != null){
                        mHandler.sendMessage(mHandler.obtainMessage(200));
                    }
                }
            });
        }
    }
}
