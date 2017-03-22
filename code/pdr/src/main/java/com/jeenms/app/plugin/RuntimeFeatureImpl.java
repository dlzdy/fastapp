package com.jeenms.app.plugin;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.jeenms.app.commons.AbstractFeature;

/**
 * Created by zhangdy on 2017/3/22.
 */

public class RuntimeFeatureImpl extends AbstractFeature {

    public RuntimeFeatureImpl(WebView webView) {
        this.webView = webView;
        this.context = webView.getContext();
    }

}
