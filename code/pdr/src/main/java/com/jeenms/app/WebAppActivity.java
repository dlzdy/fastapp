package com.jeenms.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jeenms.app.commons.AbstractActivity;
import com.jeenms.app.ui.WebViewImpl;

public class WebAppActivity extends AbstractActivity{

//    private WebView webView;
//    private LinearLayout layout;

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
        //WebViewActivity activity = new WebViewActivity();
        //TODO 根据配置加载首页
        //webView.loadUrl("http://www.baidu.com");
        String index = "http://www.baidu.com";
        index = "file:///android_asset/apps/hello-mui/index.html";
        //index = "file:///android_asset/apps/hello-mui/examples/dialog.html";
        //index = "file:///android_asset/apps/hello-mui/examples/mytest.html";
        //activity.loadUrl(index);
        Intent webviewIntent = new Intent(this, WebViewActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("url", index);
        bundle.putString("id", "index");
        webviewIntent.putExtras(bundle);
        startActivity(webviewIntent);
    }
 }
