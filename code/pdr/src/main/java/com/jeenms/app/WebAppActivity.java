package com.jeenms.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jeenms.app.commons.AbstractActivity;
import com.jeenms.app.ui.WebViewImpl;

public class WebAppActivity extends AbstractActivity{

    private WebView webView;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    public void initView() {
        webView = new WebViewImpl(this);
        layout = new LinearLayout( this );    // 变量layout是该Activity的成员变量（private LinearLayout layout）
        layout.addView(webView);
        setContentView(layout);
        //TODO 根据配置加载首页
        //webView.loadUrl("http://www.baidu.com");
        //String index = "http://www.baidu.com";
        //String index = "file:///android_asset/apps/hello-mui/index.html";
        String index = "file:///android_asset/apps/hello-mui/examples/dialog.html";
        //String index = "file:///android_asset/apps/hello-mui/examples/mytest.html";
        webView.loadUrl(index);
    }

    /**
     * 返回按键处理
     */
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {//关闭
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

 }
