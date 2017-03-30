package com.jeenms.app.util;

import android.os.Build;
import android.webkit.WebView;

/**
 * Created by zhangdy on 2017/3/27.
 */

public class JSUtils {
    /**
     * StringUtils.isEmpty() + isUndefined
     * @param cs
     * @return
     */
    public static boolean isEmpty(final CharSequence cs) {
        return StringUtils.isEmpty(cs) || isUndefined(cs);
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }
    /**
     * isUndefined
     * @param cs
     * @return
     */
    public static  boolean isUndefined(final CharSequence cs){
        return ("undefined".equals(cs));
    }

    /**
     * 执行js
     * @param webView
     * @param jsContent
     */
    public static void exeJavaScript(final WebView webView, final String jsContent) {
        //Fixed: all WebView methods must be called on the same thread
        webView.post(new Runnable() {
            @Override
            public void run() {
                String tmpJsContent = "javascript:" + jsContent;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript(tmpJsContent, null);
                } else {
                    webView.loadUrl(tmpJsContent);
                }
            }
        });

    }

}
