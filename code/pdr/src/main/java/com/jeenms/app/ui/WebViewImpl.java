package com.jeenms.app.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by zhangdy on 2017/3/21.
 */
public class WebViewImpl extends WebView{
    public WebViewImpl(Context context) {
        super(context);
        initSettings();
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        this.setWebViewClient(new WebViewClientImpl());
        this.setWebChromeClient(new WebChromeClientImpl());
    }
    public void initSettings() {
        //允许跨域
        enableCrossDomain();
        //TODO 允许调试
        enableRemoteDebugging();

        setInitialScale(0);
        setVerticalScrollBarEnabled(false);

        // Enable JavaScript
        final WebSettings settings = getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        // settings.setUserAgentString(
        // "User-Agent:Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us)
        // AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");

        // We don't save any form data in the application
        settings.setSaveFormData(false);
        settings.setSavePassword(false);

        String databasePath = getContext().getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(databasePath);

        settings.setGeolocationDatabasePath(databasePath);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(5 * 1048576);
        settings.setAppCachePath(databasePath);
        settings.setAppCacheEnabled(true);


    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void enableRemoteDebugging() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setWebContentsDebuggingEnabled(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void enableCrossDomain() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
    }

}
