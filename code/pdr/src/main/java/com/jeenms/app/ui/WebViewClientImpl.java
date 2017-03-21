package com.jeenms.app.ui;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zhangdy on 2017/3/21.
 */

public class WebViewClientImpl extends WebViewClient {

    //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
        view.loadUrl(url);
        return true;
    }
}
