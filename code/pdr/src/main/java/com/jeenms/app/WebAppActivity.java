package com.jeenms.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.jeenms.app.utils.WebViewUtils;

public class WebAppActivity extends AppCompatActivity {

    private WebView webView;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        initView();
        //
        initData();

    }
    private void initData() {

    }

    private void initView() {
        webView = new WebView(this);
        WebViewUtils.initWebViewSettings(webView);

        layout = new LinearLayout( this );    // 变量layout是该Activity的成员变量（private LinearLayout layout）
        layout.addView(webView);
        setContentView(layout);
        webView.loadUrl("http://www.baidu.com");
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
 }
