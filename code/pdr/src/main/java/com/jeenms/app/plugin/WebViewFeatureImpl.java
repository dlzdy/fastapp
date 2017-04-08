package com.jeenms.app.plugin;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.jeenms.app.commons.AbstractFeature;
import com.jeenms.app.util.JSONUtils;
import com.jeenms.app.util.JSUtils;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by zhangdy on 2017/3/22.
 */


public class WebViewFeatureImpl extends AbstractFeature {
    private static String TAG = "WebViewFeatureImpl";

    public WebViewFeatureImpl(WebView webView) {
        super(webView);
    }

    /**
     * 获取所有WebView窗口
     * @return
     */
    @JavascriptInterface
    public String all(){
        //TODO
        WebViewObject[] webViewObjects = new WebViewObject[2];
        webViewObjects[0] = new WebViewObject();
        webViewObjects[1] = new WebViewObject();
        return JSONUtils.toJSON(webViewObjects);
    }

    /**
     * 获取当前窗口的WebviewObject对象
     *
     * @return
     */
    @JavascriptInterface
    public String currentWebview(){
        //TODO
        WebViewObject webViewObject = new WebViewObject();
        return JSONUtils.toJSON(webViewObject);
    }

    /**
     * 查找指定标识的WebviewObject窗口
     *
     * @param id
     * @return
     */
    @JavascriptInterface
    public String getWebviewById(String id){
        //TODO
        WebViewObject webViewObject = new WebViewObject();
        return JSONUtils.toJSON(webViewObject);
    }
    /**
     * 创建新的Webview窗口
     * http://www.html5plus.org/doc/zh_cn/webview.html#plus.webview.create
     * @param url
     * @param id
     * @param styles
     * @param extra
     * return WebViewObject
     */
    @JavascriptInterface
    public String create(String url, String id, String styles, String extra){
        WebViewObject webViewObject = new WebViewObject();
        webViewObject.setUrl(url);
        webViewObject.setId(id);
        webViewObject.setStyles(styles);
        webViewObject.setExtra(extra);
        return JSONUtils.toJSON(webViewObject);
    }

    /**
     * 获取Webview窗口加载HTML页面的标题
     * @return
     */
    @JavascriptInterface
    public String  getTitle(){
        //TODO
        return "test title";
    }

    @JavascriptInterface
    public void  evalJS(String js){
        JSUtils.exeJavaScript(getWebView(), js);
    }

    @JavascriptInterface
    public void addEventListener(String event, String listener, Boolean capture){
        //TODO
    };
    @JavascriptInterface
    public void function(String event, String listener){
       //TODO
    };
}
