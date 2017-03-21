package com.jeenms.app.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;

public class WebViewUtils {
	private static final String URL_PREFIX = "file:///android_asset/";

	public static void initWebViewSettings(WebView webView) {
		enableCrossDomain(webView);
		enableRemoteDebugging();

		webView.setInitialScale(0);
		webView.setVerticalScrollBarEnabled(false);

		// Enable JavaScript
		final WebSettings settings = webView.getSettings();

		settings.setJavaScriptEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
		// settings.setUserAgentString(
		// "User-Agent:Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us)
		// AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50");

		// We don't save any form data in the application
		settings.setSaveFormData(false);
		settings.setSavePassword(false);

		String databasePath = webView.getContext().getApplicationContext().getDir("database", Context.MODE_PRIVATE)
				.getPath();
		settings.setDatabaseEnabled(true);
		settings.setDatabasePath(databasePath);

		settings.setGeolocationDatabasePath(databasePath);
		settings.setDomStorageEnabled(true);
		settings.setGeolocationEnabled(true);
		settings.setAppCacheMaxSize(5 * 1048576);
		settings.setAppCachePath(databasePath);
		settings.setAppCacheEnabled(true);

		//覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});
	}

	@TargetApi(VERSION_CODES.KITKAT)
	private static void enableRemoteDebugging() {
		if (Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
	}

	@TargetApi(VERSION_CODES.JELLY_BEAN)
	private static void enableCrossDomain(WebView webView) {
		if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
			webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
		}
	}


	private class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onCloseWindow(WebView window) {
			super.onCloseWindow(window);
		}

		@Override
		public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {

			WebView childView = new WebView(view.getContext());
			final WebSettings settings = childView.getSettings();
			settings.setJavaScriptEnabled(true);
			childView.setWebChromeClient(this);
			WebViewTransport transport = (WebViewTransport) resultMsg.obj;
			transport.setWebView(childView);
			resultMsg.sendToTarget();
			return true;

		}

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
		}

		@Override
		public void onReceivedIcon(WebView view, Bitmap icon) {
			super.onReceivedIcon(view, icon);
		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			super.onReceivedTitle(view, title);
		}

		@Override
		public void onRequestFocus(WebView view) {
			super.onRequestFocus(view);
		}
	}
}
