package com.jeenms.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jeenms.app.commons.AbstractActivity;
import com.jeenms.app.commons.constant.AbsoluteConst;
import com.jeenms.app.plugin.WebViewObject;
import com.jeenms.app.ui.WebViewImpl;
import com.jeenms.app.util.RuningAcitvityUtils;

public class WebAppActivity extends AbstractActivity{

    private WebView webView;
    private FrameLayout layout;

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
        layout = new FrameLayout ( this );    // 变量layout是该Activity的成员变量（private LinearLayout layout）
        layout.addView(webView);
        setContentView(layout);
        RuningAcitvityUtils.put(AbsoluteConst.ID_INDEX, this);
        //TODO 根据配置加载首页
        //webView.loadUrl("http://www.baidu.com");
        String index = "http://www.baidu.com";
        index = "file:///android_asset/apps/hello-mui/index.html";
        //index = "file:///android_asset/apps/hello-mui/examples/dialog.html";
        //index = "file:///android_asset/apps/hello-mui/examples/mytest.html";
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

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }
}
