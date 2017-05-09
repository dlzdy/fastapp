package com.jeenms.app;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.jeenms.app.commons.AbstractActivity;
import com.jeenms.app.commons.constant.AbsoluteConst;
import com.jeenms.app.ui.WebViewImpl;
import com.jeenms.app.util.RuningAcitvityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WebAppActivity extends AbstractActivity{

    //private Stack<WebViewImpl> stackWebView  = new Stack();
    private WebViewImpl webView;
    //
    private static List<WebViewImpl> webViewList = new ArrayList<>();

    private FrameLayout viewGroup;

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
        //TODO 根据配置加载首页
        String index = "http://www.baidu.com";
        index = "file:///android_asset/apps/hello-mui/index.html";
        //index = "file:///android_asset/apps/hello-mui/examples/dialog.html";
        //index = "file:///android_asset/apps/hello-mui/examples/mytest.html";
        webView = new WebViewImpl(this);
        webView.loadUrl(index);

        viewGroup = new FrameLayout ( this );    // 变量layout是该Activity的成员变量（private LinearLayout viewGroup）
        setContentView(viewGroup);
        RuningAcitvityUtils.put(AbsoluteConst.ID_INDEX, this);

        addWebView(webView);
        setWebView(webView);

    }
    public FrameLayout getViewGroup() {
        return viewGroup;
    }
    /**
     * 返回按键处理
     */
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            //关闭自己，显示前一个webview
            showLastWebView();
            //finish();
        }
    }

    public void showLastWebView() {
        if (webViewList.size() < 1) {
            return;
        }
        WebViewImpl currentWebView = webViewList.get(webViewList.size()-1);
        webViewList.remove(webViewList.size()-1);//删除当前的
        currentWebView.stopLoading();
        currentWebView = null;
        if (webViewList.size() < 1) {
            return;
        }
        WebViewImpl lastWebView = webViewList.get(webViewList.size()-1);//显示上一个
        addWebView(lastWebView);
        setWebView(lastWebView);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    public WebView getWebView() {
//        return webView;
//    }
//
    public void setWebView(WebViewImpl webView) {
        this.webView = webView;
    }

    public void addWebView(WebViewImpl webView) {
        if (!webViewList.contains(webView)){
            webViewList.add(webView);//放缓存
        }
        viewGroup.removeView(webView);
        viewGroup.addView(webView);//添加即将显示的webview
    }

    public void removeWebView(WebViewImpl webView) {
        webView.stopLoading();
        webViewList.remove(webView);//删缓存
        webView = null;
        viewGroup.removeView(this.webView);
    }
//    public void pushWebView(WebView webView){
//        stackWebView.push(webView);
//        viewGroup.addView(webView);
//    }
//
//    public WebView popWebView(){
//        WebView popWebView = stackWebView.pop();
//        popWebView.stopLoading();
//        viewGroup.removeView(popWebView);
//        popWebView = null;
//
//    }

}
