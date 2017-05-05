package com.jeenms.app.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.jeenms.app.WebAppActivity;
import com.jeenms.app.WebViewActivity;
import com.jeenms.app.commons.AbstractFeature;
import com.jeenms.app.commons.constant.AbsoluteConst;
import com.jeenms.app.util.JSONUtils;
import com.jeenms.app.util.JSUtils;
import com.jeenms.app.util.RuningAcitvityUtils;
import com.jeenms.app.util.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by zhangdy on 2017/3/22.
 */


public class WebViewFeatureImpl extends AbstractFeature {

    private static String TAG = "WebViewFeatureImpl";
    //<id,WebViewObject>
    private static Map<String, WebViewObject> webViewObjectMap = new HashMap();
    private static WebViewObject currentWebView;
    public WebViewFeatureImpl(WebView webView) {
        super(webView);
    }


    /**
     * 获取所有WebView窗口
     *
     * @return
     */
    @JavascriptInterface
    public String all() {
        Collection<WebViewObject> webViewObjects = webViewObjectMap.values();
        return JSONUtils.toJSON(webViewObjects);
    }

    /**
     * 获取当前窗口的WebviewObject对象
     *
     * @return
     */
    @JavascriptInterface
    public String currentWebview() {
        return JSONUtils.toJSON(currentWebView);
    }

    /**
     * 查找指定标识的WebviewObject窗口
     *
     * @param id
     * @return
     */
    @JavascriptInterface
    public String getWebviewById(String id) {
        WebViewObject webViewObject = webViewObjectMap.get(id);
        return JSONUtils.toJSON(webViewObject);
    }

    /**
     * 创建新的Webview窗口
     * http://www.html5plus.org/doc/zh_cn/webview.html#plus.webview.create
     *
     * @param url
     * @param id
     * @param styles
     * @param extras  return WebViewObject
     */
    @JavascriptInterface
    public String create(String url, String id, String styles, String extras) {
        WebViewObject webViewObject = new WebViewObject();
        webViewObject.setUrl(url);
        webViewObject.setId(id);
        webViewObject.setStyles(styles);
        webViewObject.setExtra(extras);
        webViewObjectMap.put(id, webViewObject);//缓存起来
        show(id, null,0,null,null);
        return JSONUtils.toJSON(webViewObject);
    }

    /**
     * 获取Webview窗口加载HTML页面的标题
     *
     * @return
     */
    @JavascriptInterface
    public String getTitle() {
        //TODO
        return "TODO";
    }

    @JavascriptInterface
    public void evalJS(String js) {
        JSUtils.exeJavaScript(getWebView(), js);
    }

    @JavascriptInterface
    public void addEventListener(String event, String listener, Boolean capture) {
        //TODO
        if (StringUtils.equals(AbsoluteConst.EVENTS_TITLE_UPDATE, event) ){//titleUpdate
            //
        }
    }

    /**
     * http://www.html5plus.org/doc/zh_cn/webview.html#plus.webview.show
     *
     * @param id
     * @param aniShow
     * @param duration
     * @param showedCB
     * @param extras
     */
    @JavascriptInterface
    public void show(String id, String aniShow, int duration, String showedCB, String extras) {
        Log.i(TAG, id);
        currentWebView = webViewObjectMap.get(id);//设置当前webview
        //设置参数
        Bundle bundle = new Bundle();
        bundle.putString("url", currentWebView.getUrl());
        bundle.putString("id", currentWebView.getId());
        bundle.putString("extras", currentWebView.getExtra());
        bundle.putString("styltes", currentWebView.getStyles());
        //切换页面
        Activity mainActivity = RuningAcitvityUtils.getIndexActivity();
        Intent webviewIntent = new Intent(mainActivity, WebViewActivity.class);
        webviewIntent.putExtras(bundle);
        mainActivity.startActivity(webviewIntent);
    }

    @JavascriptInterface
    public void function(String event, String listener) {
        //TODO
    }

    ;
}
