package com.treasure_ct.happiness_xt.activity.life;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.adapter.LifeDeliciousDetailAdapter;
import com.treasure_ct.happiness_xt.bean.LifeDeliciousDetailBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.widget.CustomScrollListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LifeDeliciousDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private JCVideoPlayerStandard videoView;
    private ImageView prepare1;
    private TextView prepare2;
    private ImageView later1;
    private TextView later2;
    private TextView title;
    private TextView label01,label02,label03,label04,label05;
    private TextView content;
    private TextView difficult;
    private TextView time;
    private TextView taste;
    private CustomScrollListView listView;
    private List<LifeDeliciousDetailBean.DataBean.StepBean> list;
    private LifeDeliciousDetailAdapter adapter;
    private String dishes_id;
    private LifeDeliciousDetailBean detailBean;
    private String error;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    LifeDeliciousDetailBean.DataBean dataBean = detailBean.getData();
                    title.setText(dataBean.getDishes_title()+"");
                    content.setText(dataBean.getMaterial_desc()+"");
                    difficult.setText("难度："+dataBean.getHard_level());
                    time.setText("时间："+dataBean.getCooke_time());
                    taste.setText("味道："+dataBean.getTaste());
                    List<LifeDeliciousDetailBean.DataBean.TagsInfoBean> tags_info = dataBean.getTags_info();
                    if (tags_info.size() > 0){
                        label01.setText(tags_info.get(0).getText()+"");
                    }if (tags_info.size() > 1){
                        label01.setText(tags_info.get(1).getText()+"");
                    }if (tags_info.size() > 2){
                        label01.setText(tags_info.get(2).getText()+"");
                    }if (tags_info.size() > 3){
                        label01.setText(tags_info.get(3).getText()+"");
                    }if (tags_info.size() > 4){
                        label01.setText(tags_info.get(4).getText()+"");
                    }
                    List<LifeDeliciousDetailBean.DataBean.StepBean> stepBeen = dataBean.getStep();
                    list.addAll(stepBeen);
                    adapter.notifyDataSetChanged();
                    videoView.setUp(dataBean.getProcess_video()+"", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, dataBean.getDishes_name()+"");
                    videoView.thumbImageView.setImageURI(Uri.parse(dataBean.getImage()+""));
                    break;
                case 400:
                    Toast.makeText(LifeDeliciousDetailActivity.this, "原因："+error, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_delicious_detail);
        initReceiveData();
        initFindId();
        initListView();
        getDeliciousDetailData();
        initClick();
    }

    private void initReceiveData() {
        Intent intent = getIntent();
        dishes_id = intent.getStringExtra("dishes_id");
    }

    private void initFindId() {
        videoView = (JCVideoPlayerStandard) findViewById(R.id.life_delicious_detail_videoView);
        prepare1 = (ImageView) findViewById(R.id.life_delicious_detail_prepare1);
        prepare2 = (TextView) findViewById(R.id.life_delicious_detail_prepare2);
        later1 = (ImageView) findViewById(R.id.life_delicious_detail_later1);
        later2 = (TextView) findViewById(R.id.life_delicious_detail_later2);
        title = (TextView) findViewById(R.id.life_delicious_detail_title);
        label01 = (TextView) findViewById(R.id.life_delicious_detail_label01);
        label02 = (TextView) findViewById(R.id.life_delicious_detail_label02);
        label03 = (TextView) findViewById(R.id.life_delicious_detail_label03);
        label04 = (TextView) findViewById(R.id.life_delicious_detail_label04);
        label05 = (TextView) findViewById(R.id.life_delicious_detail_label05);
        content = (TextView) findViewById(R.id.life_delicious_detail_content);
        difficult = (TextView) findViewById(R.id.life_delicious_detail_difficult);
        time = (TextView) findViewById(R.id.life_delicious_detail_time);
        taste = (TextView) findViewById(R.id.life_delicious_detail_taste);
        listView = (CustomScrollListView) findViewById(R.id.life_delicious_detail_listView);
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new LifeDeliciousDetailAdapter(this,list);
        listView.setAdapter(adapter);
    }

    private void getDeliciousDetailData() {
        HttpHelper.doGetCall("http://api.izhangchu.com/?methodName=DishesView&dishes_id=" + dishes_id,
                this,
                new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        error = e.getMessage();
                        mHandler.sendMessage(mHandler.obtainMessage(400));
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        detailBean = ModelParseHelper.parseDeliciousDetailResult(string);
                        mHandler.sendMessage(mHandler.obtainMessage(200));
                    }
                });
    }

    private void initClick() {
    prepare1.setOnClickListener(this);
    prepare2.setOnClickListener(this);
    later1.setOnClickListener(this);
    later2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.life_delicious_detail_prepare1:
            case R.id.life_delicious_detail_prepare2:
                videoView.setUp(detailBean.getData().getMaterial_video()+"", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, detailBean.getData().getDishes_name()+"");
                videoView.thumbImageView.setImageURI(Uri.parse(detailBean.getData().getImage()+""));
                break;
            case R.id.life_delicious_detail_later1:
            case R.id.life_delicious_detail_later2:
                videoView.setUp(detailBean.getData().getProcess_video()+"", JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, detailBean.getData().getDishes_name()+"");
                videoView.thumbImageView.setImageURI(Uri.parse(detailBean.getData().getImage()+""));
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
