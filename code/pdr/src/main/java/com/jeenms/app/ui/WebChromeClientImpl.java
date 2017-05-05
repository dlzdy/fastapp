package com.jeenms.app.ui;

import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by zhangdy on 2017/3/21.
 */

public class WebChromeClientImpl extends WebChromeClient {
    private static String TAG = "WebChromeClientImpl";

//    @Override
//    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//        builder.setTitle("Alert");
//        builder.setMessage(message);
//        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                result.confirm();
//            }
//        });
//        builder.setCancelable(false);
//        builder.create().show();
//        return true;
//    }
    //设置响应js 的Confirm()函数
//    @Override
//    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
//        AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
//        b.setTitle("Confirm");
//        b.setMessage(message);
//        b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                result.confirm();
//            }
//        });
//        b.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                result.cancel();
//            }
//        });
//        b.create().show();
//        return true;
//    }

    public void onReceivedTitle(WebView view, String title) {
//        System.out.println("title" + title);
//        this.mAdaWebview.unReceiveTitle = false;
//        this.mAdaWebview.dispatchWebviewStateEvent(4, paramString);
//        this.mAdaWebview.mFrameView.dispatchFrameViewEvents("titleUpdate", paramString);
//        this.mAdaWebview.mWebViewImpl.mPageTitle = paramString;
//        this.mAdaWebview.mLoadCompleted = true;
        Log.i(TAG, "onReceivedTitle title=" + title);
        super.onReceivedTitle(view, title);
    }
}
