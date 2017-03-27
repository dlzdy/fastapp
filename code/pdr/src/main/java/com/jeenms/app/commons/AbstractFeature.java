package com.jeenms.app.commons;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by zhangdy on 2017/3/22.
 */

public class AbstractFeature implements IFeature {
    protected Context context;
    protected WebView webView;
    public AbstractFeature(WebView webView) {
        this.webView = webView;
        this.context = webView.getContext();
    }

    public WebView getWebView() {
        return webView;
    }

    public Context getContext() {
        return context;
    }

    @JavascriptInterface
    @Override
    public void test() {
        Toast.makeText(context, this.getClass().getSimpleName()  +  " test" , Toast.LENGTH_SHORT).show();
    }
}
