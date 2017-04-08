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
     * @param js
     */
    public static void exeJavaScript(final WebView webView, final String js) {
        //Fixed: all WebView methods must be called on the same thread
        webView.post(new Runnable() {
            @Override
            public void run() {
                String tmpJs = "javascript:" + js;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    webView.evaluateJavascript(tmpJs, null);
                } else {
                    webView.loadUrl(tmpJs);
                }
            }
        });

    }

}
