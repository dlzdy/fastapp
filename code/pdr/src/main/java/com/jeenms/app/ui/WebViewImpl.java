package com.jeenms.app.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jeenms.app.commons.IWebview;
import com.jeenms.app.plugin.NativeUIFeatureImpl;
import com.jeenms.app.plugin.RuntimeFeatureImpl;
import com.jeenms.app.plugin.WebViewFeatureImpl;

/**
 * Created by zhangdy on 2017/3/21.
 */
public class WebViewImpl extends WebView  implements IWebview {
    String mUserAgent = null;
    static final String UserAgentExtInfo = " Html5Plus/1.0";

    public WebViewImpl(Context context) {
        super(context);
        initSettings();
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        this.setWebViewClient(new WebViewClientImpl());
        this.setWebChromeClient(new WebChromeClientImpl());
        //TODO js 接口,使用反射机制，读取配置文件
        addJavascriptInterface(new WebViewFeatureImpl(this), "webview");
        addJavascriptInterface(new RuntimeFeatureImpl(this), "runtime");
        addJavascriptInterface(new NativeUIFeatureImpl(this), "nativeUI");
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

        mUserAgent = settings.getUserAgentString();
        settings.setUserAgentString(mUserAgent + " Html5Plus/1.0");

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

    @Override
    public Activity getActivity() {
        return null;
    }
}
