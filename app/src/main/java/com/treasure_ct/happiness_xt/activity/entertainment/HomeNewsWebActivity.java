package com.treasure_ct.happiness_xt.activity.entertainment;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.treasure_ct.happiness_xt.BaseActivity;
import com.treasure_ct.happiness_xt.R;
import com.treasure_ct.happiness_xt.utils.Tools;

public class HomeNewsWebActivity extends BaseActivity implements View.OnClickListener {

    private WebView webView;
    private String url;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_news_web);
        initTitle();
        Tools.setTranslucentStatus(this);
        btn_back.setImageResource(R.mipmap.icon_return);
        btn_back.setVisibility(View.VISIBLE);
        receiveIntent();
        initFindId();
        if (!Tools.isNull(url)) {
            LoadUrl();
        }
        initClick();
    }

    private void receiveIntent() {
        Intent intent = getIntent();
        if (!Tools.isNull(intent.getStringExtra("webUrl"))) {
            url = intent.getStringExtra("webUrl");
        }
    }

    private void initFindId() {
        webView = (WebView) findViewById(R.id.news_webView);
        progress = (ProgressBar) findViewById(R.id.news_webView_progress);
    }

    private void LoadUrl() {
        progress.setVisibility(View.VISIBLE);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        settings.setSupportZoom(true); // 支持缩放
        webView.setWebViewClient(new WebViewClient()
//        {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//        }
        );
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            //当WebView进度改变时更新窗口进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //Activity的进度范围在0到10000之间,所以这里要乘以100
                HomeNewsWebActivity.this.setProgress(newProgress * 100);
                if (newProgress == 100){
                    progress.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl(url);
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
