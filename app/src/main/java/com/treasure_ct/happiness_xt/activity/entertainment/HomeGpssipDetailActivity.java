package com.treasure_ct.happiness_xt.activity.entertainment;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.bean.HomeGossipDetailBean;
import com.treasure_ct.happiness_xt.utils.HttpHelper;
import com.treasure_ct.happiness_xt.utils.ModelParseHelper;
import com.treasure_ct.happiness_xt.utils.Tools;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeGpssipDetailActivity extends BaseActivity implements View.OnClickListener {

    private SimpleDraweeView topImage;
    private TextView title1;
    private TextView userName;
    private TextView userTime;
    private TextView topContent;
    private String id;
    private String error;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    if (detailBean.getData().getInfo() != null) {
                        if (!Tools.isNull(detailBean.getData().getInfo().getImg())) {
                            topImage.setImageURI(Uri.parse(detailBean.getData().getInfo().getImg()));
                        }
                        if (!Tools.isNull(detailBean.getData().getInfo().getTitle())) {
                            title1.setText(detailBean.getData().getInfo().getTitle());
                        }
                        if (detailBean.getData().getInfo().getAuthor() != null) {
                            if (!Tools.isNull(detailBean.getData().getInfo().getAuthor().getUsername())) {
                                userName.setText(detailBean.getData().getInfo().getAuthor().getUsername());
                            }
                        }
                        if (!Tools.isNull(detailBean.getData().getInfo().getPublish_time())) {
                            userTime.setText(detailBean.getData().getInfo().getPublish_time());
                        }
                        if (!Tools.isNull(detailBean.getData().getInfo().getTxtlead())) {
                            topContent.setText(detailBean.getData().getInfo().getTxtlead());
                        }
                        if (!Tools.isNull(detailBean.getData().getInfo().getShareurl())) {
                            setWebView();
                        }
                    }
                    break;
                case 400:
                    Toast.makeText(HomeGpssipDetailActivity.this, "原因" + error, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private HomeGossipDetailBean detailBean;
    private WebView webUrl;
    private ProgressBar progress;
    private NestedScrollView scrollView;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_gpssip_detail);
        getIntentData();
        initFindId();
        initClick();
        scrollView.smoothScrollTo(0, 20);
        getGossipDetail();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (!Tools.isNull(intent.getStringExtra("id"))) {
            id = intent.getStringExtra("id");
        }
    }

    private void initFindId() {
        topImage = (SimpleDraweeView) findViewById(R.id.home_gossip_detail_topImage);
        title1 = (TextView) findViewById(R.id.home_gossip_detail_title);
        userName = (TextView) findViewById(R.id.home_gossip_detail_userName);
        userTime = (TextView) findViewById(R.id.home_gossip_detail_userTime);
        topContent = (TextView) findViewById(R.id.home_gossip_detail_topContent);
        webUrl = (WebView) findViewById(R.id.home_gossip_detail_url);
        progress = (ProgressBar) findViewById(R.id.home_gossip_detail_progress);
        scrollView = (NestedScrollView) findViewById(R.id.home_gossip_detail_scroll);
        back = (ImageView) findViewById(R.id.home_gossip_detail_return);
    }

    private void initClick() {
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_gossip_detail_return:
                finish();
                break;
        }
    }

    private void getGossipDetail() {
        String url = "http://api.app.happyjuzi.com/article/detail?id=" + id + "&fromapp=juzi&mac=02-00-00-00-00-00&uid=4108532685623026&pf=android&net=wifi&accesstoken=13cf0c286c8e88eac3c540eff8a73e74&channel=yingyongbao&ver=3.7.8&res=1080x1830";
        HttpHelper.doGetCall(url, this, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                error = e.getMessage();
                mHandler.sendMessage(mHandler.obtainMessage(400));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                detailBean = ModelParseHelper.parsegossipDetailResult(string);
                if (detailBean != null) {
                    if (detailBean.getData() != null) {
                        mHandler.sendMessage(mHandler.obtainMessage(200));
                    }
                }
            }
        });
    }

    private void setWebView() {
        progress.setVisibility(View.VISIBLE);
        WebSettings settings = webUrl.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        settings.setSupportZoom(true); // 支持缩放
        webUrl.setWebViewClient(new WebViewClient()
//        {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//        }
        );
        webUrl.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webUrl.canGoBack()) {
                        webUrl.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        webUrl.setWebChromeClient(new WebChromeClient() {
            //当WebView进度改变时更新窗口进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //Activity的进度范围在0到10000之间,所以这里要乘以100
                HomeGpssipDetailActivity.this.setProgress(newProgress * 100);
                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);
                }
            }
        });
        webUrl.loadUrl(detailBean.getData().getInfo().getShareurl());
    }
}
