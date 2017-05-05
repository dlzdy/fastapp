package com.jeenms.app;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.jeenms.app.commons.AbstractActivity;
import com.jeenms.app.ui.WebViewImpl;
import com.jeenms.app.util.RuningAcitvityUtils;

public class WebViewActivity extends AbstractActivity{

    private WebView webView;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        String url = bundle.getString("url");
        String id = bundle.getString("id");
        RuningAcitvityUtils.put(id, this);
        webView.loadUrl(url);
    }

    @Override
    public void initEvent() {

    }

    public void initView() {
        webView = new WebViewImpl(this);
        layout = new LinearLayout( this );    // 变量layout是该Activity的成员变量（private LinearLayout layout）
        layout.addView(webView);
        setContentView(layout);
       // setVisibility(View.GONE);  //影藏 view
    }
//    public void loadUrl(String url) {
//        webView.loadUrl(url);
//    }
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

}
