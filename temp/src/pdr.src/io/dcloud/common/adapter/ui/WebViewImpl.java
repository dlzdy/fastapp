/*      */ package io.dcloud.common.adapter.ui;
/*      */ 
/*      */ import android.annotation.TargetApi;
/*      */ import android.app.Activity;
/*      */ import android.content.Context;
/*      */ import android.content.Intent;
/*      */ import android.content.IntentFilter;
/*      */ import android.content.SharedPreferences;
/*      */ import android.content.res.Configuration;
/*      */ import android.content.res.Resources;
/*      */ import android.graphics.Bitmap;
/*      */ import android.graphics.Bitmap.Config;
/*      */ import android.graphics.Canvas;
/*      */ import android.graphics.Picture;
/*      */ import android.graphics.Rect;
/*      */ import android.net.Uri;
/*      */ import android.os.Build;
/*      */ import android.os.Build.VERSION;
/*      */ import android.util.DisplayMetrics;
/*      */ import android.view.ActionMode;
/*      */ import android.view.ActionMode.Callback;
/*      */ import android.view.KeyEvent;
/*      */ import android.view.Menu;
/*      */ import android.view.MenuItem;
/*      */ import android.view.MotionEvent;
/*      */ import android.view.View;
/*      */ import android.view.ViewConfiguration;
/*      */ import android.webkit.CookieManager;
/*      */ import android.webkit.CookieSyncManager;
/*      */ import android.webkit.DownloadListener;
/*      */ import android.webkit.JavascriptInterface;
/*      */ import android.webkit.WebSettings;
/*      */ import android.webkit.WebSettings.LayoutAlgorithm;
/*      */ import android.webkit.WebView;
/*      */ import android.webkit.WebViewClient;
/*      */ import com.dcloud.android.v4.widget.IRefreshAble;
/*      */ import com.dcloud.android.v4.widget.IRefreshAble.OnRefreshListener;
/*      */ import io.dcloud.application.DCloudApplication;
/*      */ import io.dcloud.common.DHInterface.IApp;
/*      */ import io.dcloud.common.DHInterface.ICallBack;
/*      */ import io.dcloud.common.DHInterface.IFrameView;
/*      */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*      */ import io.dcloud.common.adapter.io.DHFile;
/*      */ import io.dcloud.common.adapter.io.DownloadReceiver;
/*      */ import io.dcloud.common.adapter.util.AndroidResources;
/*      */ import io.dcloud.common.adapter.util.DeviceInfo;
/*      */ import io.dcloud.common.adapter.util.DownloadUtil;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.PlatformUtil;
/*      */ import io.dcloud.common.adapter.util.ViewOptions;
/*      */ import io.dcloud.common.util.BaseInfo;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import io.dcloud.d;
/*      */ import io.dcloud.feature.internal.sdk.SDK.IntegratedMode;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ 
/*      */ class WebViewImpl extends WebView
/*      */   implements DownloadListener, IRefreshAble.OnRefreshListener
/*      */ {
/* 1283 */   static String sCustomUserAgent = null;
/*      */ 
/* 1285 */   static String sDefalutUserAgent = null;
/*      */ 
/* 1287 */   String mUserAgent = null;
/*      */   static final String TAG = "webview";
/* 1289 */   ReceiveJSValue mReceiveJSValue_android42 = null;
/*      */   static final String UserAgentExtInfo = " Html5Plus/1.0";
/*      */   static final String UserAgentExtInfoForHBuilder = " StreamApp/1.0";
/*      */   static final String UserAgentStreamApp = " StreamApp/1.0";
/*      */   static final String UserAgentQihoo = " qihoo";
/*      */   static final String UserAgentQihoBrowser = " qihoobrowser";
/* 1295 */   AdaWebview mAdaWebview = null;
/* 1296 */   WebLoadEvent mWebLoadEvent = null;
/* 1297 */   String mUrl = null;
/* 1298 */   float mScale = 0.0F;
/* 1299 */   Context mContext = null;
/* 1300 */   String mBaseUrl = null;
/* 1301 */   WebSettings webSettings = getSettings();
/* 1302 */   CookieManager cm = null;
/* 1303 */   private int mLastScrollY = 0;
/* 1304 */   private int mContentHeight = 0;
/* 1305 */   private int mThreshold = 2;
/* 1306 */   private int mThresholdTime = 15;
/* 1307 */   private long mLastScrollTimestamp = 0L;
/* 1308 */   String mPageTitle = null;
/* 1309 */   int mDeafaltOverScrollMode = 0;
/*      */   private WebLoadEvent.OnPageFinishedCallack mPageFinishedCallack;
/* 1332 */   boolean isToInvalidate = false;
/*      */   static final String PLUSSCROLLBOTTOM_JS_TEMPLATE = "(function(){var e = document.createEvent('HTMLEvents');var evt = 'plusscrollbottom';e.initEvent(evt, false, true);document.dispatchEvent(e);})();";
/* 1857 */   boolean didTouch = false;
/* 1858 */   private int mEventY = 0;
/* 1859 */   private int mEventX = 0;
/* 1860 */   private int mTouchSlop = -1;
/*      */ 
/*      */   public WebViewImpl(Context paramContext, AdaWebview paramAdaWebview)
/*      */   {
/* 1313 */     super(paramContext);
/* 1314 */     Logger.d("WebViewImpl");
/* 1315 */     BaseInfo.s_Webview_Count += 1;
/* 1316 */     this.mContext = paramContext;
/* 1317 */     this.mAdaWebview = paramAdaWebview;
/*      */ 
/* 1319 */     if ((BaseInfo.isTrafficFree()) && (DCloudApplication.self().isQihooTrafficFreeValidate)) {
/* 1320 */       Object localObject = InvokeExecutorHelper.TrafficProxy.invoke("getInstance", new Class[0], new Object[0]);
/* 1321 */       InvokeExecutorHelper.TrafficProxy.setInstance(localObject).invoke("start");
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onPageStarted()
/*      */   {
/* 1330 */     this.isToInvalidate = false;
/*      */   }
/*      */ 
/*      */   public void invalidate()
/*      */   {
/* 1335 */     super.invalidate();
/*      */     try {
/* 1337 */       float f = getContentHeight() * getScale();
/* 1338 */       if ((f > 0.0F) && ((f > getHeight()) || ((this.mAdaWebview.mProgress > 60) && (f >= getHeight()))) && (!this.isToInvalidate)) {
/* 1339 */         this.mAdaWebview.dispatchWebviewStateEvent(6, Integer.valueOf(getContentHeight()));
/* 1340 */         this.mAdaWebview.mFrameView.dispatchFrameViewEvents("rendering", Integer.valueOf(getContentHeight()));
/* 1341 */         this.isToInvalidate = true;
/*      */       }
/*      */     } catch (Exception localException) {
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean checkWhite(String paramString) {
/* 1348 */     long l = System.currentTimeMillis();
/* 1349 */     if (getWidth() <= 0) {
/* 1350 */       return true;
/*      */     }
/* 1352 */     Rect localRect = null;
/*      */     int i;
/* 1353 */     if (paramString.equals("center")) {
/* 1354 */       i = getHeight() / 2;
/* 1355 */       localRect = new Rect(0, i, getWidth(), i + 1);
/* 1356 */     } else if (paramString.equals("top")) {
/* 1357 */       i = DeviceInfo.getDeivceSuitablePixel(this.mAdaWebview.getActivity(), 20);
/* 1358 */       localRect = new Rect(0, i, getWidth(), i + 1);
/* 1359 */     } else if (paramString.equals("bottom")) {
/* 1360 */       i = DeviceInfo.getDeivceSuitablePixel(this.mAdaWebview.getActivity(), 25);
/* 1361 */       localRect = new Rect(0, getHeight() - i + 1, getWidth(), getHeight() - i);
/*      */     } else {
/* 1363 */       i = getWidth() / 2;
/* 1364 */       localRect = new Rect(i, 0, i + 5, getHeight());
/*      */     }
/* 1366 */     Bitmap localBitmap = captureWebView(this, localRect);
/* 1367 */     if (localBitmap == null) {
/* 1368 */       return false;
/*      */     }
/* 1370 */     boolean bool = paramString.equals("auto") ? PlatformUtil.isWhiteBitmap(localBitmap) : PlatformUtil.isLineWhiteBitmap(localBitmap, !this.mAdaWebview.isLoaded());
/*      */ 
/* 1372 */     localBitmap.recycle();
/*      */ 
/* 1377 */     return bool;
/*      */   }
/*      */ 
/*      */   private Bitmap captureWebView(WebView paramWebView, Rect paramRect)
/*      */   {
/* 1400 */     Picture localPicture = paramWebView.capturePicture();
/* 1401 */     Bitmap localBitmap = Bitmap.createBitmap(paramRect.width(), paramRect.height(), Bitmap.Config.RGB_565);
/* 1402 */     Canvas localCanvas = new Canvas(localBitmap);
/* 1403 */     localCanvas.translate(-paramRect.left, -paramRect.top);
/* 1404 */     localPicture.draw(localCanvas);
/* 1405 */     return localBitmap;
/*      */   }
/*      */ 
/*      */   public WebViewImpl(Context paramContext, AdaWebview paramAdaWebview, WebLoadEvent.OnPageFinishedCallack paramOnPageFinishedCallack)
/*      */   {
/* 1416 */     super(paramContext);
/* 1417 */     Logger.d("WebViewImpl");
/* 1418 */     this.mContext = paramContext;
/* 1419 */     this.mAdaWebview = paramAdaWebview;
/* 1420 */     this.mPageFinishedCallack = paramOnPageFinishedCallack;
/*      */   }
/*      */   protected boolean isReadyForPullUp(int paramInt) {
/* 1423 */     float f = (float)Math.floor(getContentHeight() * getScale());
/* 1424 */     boolean bool = false;
/* 1425 */     int i = (int)f - getHeight();
/* 1426 */     long l = System.currentTimeMillis();
/* 1427 */     if (((paramInt >= i) || ((paramInt >= i - this.mThreshold) && (l - this.mLastScrollTimestamp > this.mThresholdTime))) && (this.mLastScrollY < this.mContentHeight))
/*      */     {
/* 1430 */       bool = true;
/*      */     }
/* 1432 */     else bool = false;
/*      */ 
/* 1435 */     this.mLastScrollY = paramInt;
/* 1436 */     this.mContentHeight = i;
/* 1437 */     if (l - this.mLastScrollTimestamp <= 500L) {
/* 1438 */       this.mLastScrollTimestamp = (l - this.mLastScrollTimestamp);
/*      */     }
/* 1440 */     return bool;
/*      */   }
/*      */ 
/*      */   protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/* 1447 */     super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */ 
/* 1449 */     if ((paramInt1 == paramInt3) && (paramInt2 == paramInt4)) return;
/* 1450 */     if ((!AndroidResources.sIMEAlive) && (isReadyForPullUp(paramInt2))) {
/* 1451 */       Logger.d("onPlusScrollBottom", "上拉事件  url=" + this.mAdaWebview.obtainUrl());
/* 1452 */       this.mAdaWebview.executeScript("(function(){var e = document.createEvent('HTMLEvents');var evt = 'plusscrollbottom';e.initEvent(evt, false, true);document.dispatchEvent(e);})();");
/*      */     }
/*      */   }
/*      */ 
/*      */   public void loadUrl(String paramString)
/*      */   {
/* 1458 */     if ((paramString.startsWith("javascript:")) && 
/* 1459 */       (Build.VERSION.SDK_INT >= 19)) {
/* 1460 */       evaluateJavascript(paramString, null);
/* 1461 */       return;
/*      */     }
/*      */ 
/* 1465 */     super.loadUrl(paramString);
/*      */   }
/*      */ 
/*      */   protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 1469 */     super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */   void initUserAgent(IApp paramIApp)
/*      */   {
/* 1477 */     if ((BaseInfo.isQihooLifeHelper(paramIApp.getActivity())) && (!PdrUtil.isEquals(paramIApp.obtainAppId(), BaseInfo.sDefaultBootApp)) && (!PdrUtil.isEmpty(BaseInfo.sGlobalUserAgent)))
/*      */     {
/* 1482 */       this.webSettings.setUserAgentString(BaseInfo.sGlobalUserAgent);
/* 1483 */       return;
/*      */     }
/* 1485 */     String str1 = paramIApp.obtainConfigProperty("useragent");
/* 1486 */     boolean bool1 = Boolean.parseBoolean(paramIApp.obtainConfigProperty("concatenate"));
/* 1487 */     boolean bool2 = Boolean.parseBoolean(paramIApp.obtainConfigProperty("h5plus"));
/* 1488 */     if (PdrUtil.isEmpty(sDefalutUserAgent)) {
/* 1489 */       sDefalutUserAgent = this.webSettings.getUserAgentString();
/*      */     }
/*      */ 
/* 1492 */     this.mUserAgent = sDefalutUserAgent;
/* 1493 */     if (bool1) {
/* 1494 */       this.mUserAgent = str1;
/* 1495 */     } else if (!PdrUtil.isEmpty(str1)) {
/* 1496 */       str1 = str1.trim();
/* 1497 */       this.mUserAgent = (this.mUserAgent + " " + str1);
/*      */     }
/* 1499 */     String str2 = "";
/*      */ 
/* 1501 */     if (AndroidResources.checkImmersedStatusBar(this.mAdaWebview.getActivity())) {
/* 1502 */       str2 = " (Immersed/" + DeviceInfo.sStatusBarHeight / getScale() + ")";
/*      */     }
/* 1504 */     if ((bool2) && (this.mUserAgent.indexOf(" Html5Plus/1.0") < 0)) {
/* 1505 */       if (BaseInfo.isForQihooBrowser(getContext()))
/* 1506 */         this.mUserAgent = (this.mUserAgent + " Html5Plus/1.0" + str2 + " StreamApp/1.0" + " qihoobrowser");
/* 1507 */       else if ((BaseInfo.isForQihooHelper(getContext())) || (BaseInfo.isQihooLifeHelper(getContext())))
/* 1508 */         this.mUserAgent = (this.mUserAgent + " Html5Plus/1.0" + str2 + " StreamApp/1.0" + " qihoo");
/* 1509 */       else if ((paramIApp.isStreamApp()) || (BaseInfo.isStreamApp(getContext()))) {
/* 1510 */         this.mUserAgent = (this.mUserAgent + " Html5Plus/1.0" + str2 + " StreamApp/1.0");
/*      */       }
/* 1512 */       else if ((BaseInfo.ISAMU) && (BaseInfo.isBase(getContext())))
/* 1513 */         this.mUserAgent = (this.mUserAgent + " Html5Plus/1.0 StreamApp/1.0" + str2);
/*      */       else {
/* 1515 */         this.mUserAgent = (this.mUserAgent + " Html5Plus/1.0" + str2);
/*      */       }
/*      */     }
/*      */ 
/* 1519 */     Logger.d("webview", "userAgent=" + this.mUserAgent);
/* 1520 */     this.webSettings.setUserAgentString(this.mUserAgent);
/*      */ 
/* 1522 */     if ((BaseInfo.isQihooLifeHelper(paramIApp.getActivity())) && (PdrUtil.isEquals(paramIApp.obtainAppId(), BaseInfo.sDefaultBootApp)) && (PdrUtil.isEmpty(BaseInfo.sGlobalUserAgent)))
/*      */     {
/* 1525 */       BaseInfo.sGlobalUserAgent = this.mUserAgent;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void init()
/*      */   {
/* 1539 */     if (!AdaWebview.setedWebViewData) {
/* 1540 */       boolean bool1 = false;
/* 1541 */       bool1 = DHFile.hasFile();
/* 1542 */       String str = this.mAdaWebview.obtainApp().obtainConfigProperty("use_encryption");
/* 1543 */       boolean bool3 = Boolean.parseBoolean(str);
/* 1544 */       if (((BaseInfo.ISDEBUG) || (bool1)) && (!bool3)) {
/* 1545 */         if (Build.VERSION.SDK_INT >= 19)
/* 1546 */           PlatformUtil.invokeMethod("android.webkit.WebView", "setWebContentsDebuggingEnabled", null, new Class[] { Boolean.TYPE }, new Object[] { Boolean.valueOf(true) });
/*      */       }
/*      */       else {
/* 1549 */         setWebViewData();
/*      */       }
/* 1551 */       AdaWebview.setedWebViewData = true;
/*      */     }
/* 1553 */     setDownloadListener(this);
/* 1554 */     if (DeviceInfo.sDeviceSdkVer >= 9) {
/* 1555 */       this.mDeafaltOverScrollMode = getOverScrollMode();
/*      */     }
/* 1557 */     this.mScale = getContext().getResources().getDisplayMetrics().density;
/*      */     try
/*      */     {
/* 1560 */       CookieSyncManager.createInstance(this.mContext);
/* 1561 */       this.cm = CookieManager.getInstance();
/* 1562 */       if (this.cm != null) {
/* 1563 */         PlatformUtil.invokeMethod(CookieManager.class.getName(), "setAcceptThirdPartyCookies", this.cm, new Class[] { WebView.class, Boolean.TYPE }, new Object[] { this, Boolean.valueOf(true) });
/* 1564 */         this.cm.setAcceptCookie(true);
/* 1565 */         this.cm.removeExpiredCookie();
/* 1566 */         CookieSyncManager.getInstance().sync();
/*      */       }
/*      */     } catch (Throwable localThrowable) {
/* 1569 */       Logger.e("WebViewImpl CookieManager.getInstance Exception =" + localThrowable);
/*      */     }
/*      */ 
/* 1572 */     this.mAdaWebview.obtainFrameView().onInit();
/*      */ 
/* 1580 */     IApp localIApp = this.mAdaWebview.obtainFrameView().obtainApp();
/* 1581 */     this.mBaseUrl = localIApp.obtainWebviewBaseUrl();
/*      */ 
/* 1587 */     setScrollBarStyle(33554432);
/* 1588 */     if (sCustomUserAgent != null)
/* 1589 */       this.webSettings.setUserAgentString(sCustomUserAgent);
/*      */     else {
/* 1591 */       initUserAgent(localIApp);
/*      */     }
/* 1593 */     if (DeviceInfo.sDeviceSdkVer > 16) {
/* 1594 */       this.webSettings.setAllowUniversalAccessFromFileURLs(true);
/* 1595 */       this.webSettings.setAllowFileAccessFromFileURLs(true);
/*      */     }
/* 1597 */     this.webSettings.setAllowFileAccess(true);
/* 1598 */     this.webSettings.setDefaultTextEncodingName("GB2312");
/*      */ 
/* 1602 */     this.webSettings.setCacheMode(this.mAdaWebview.getCacheMode());
/*      */ 
/* 1607 */     this.webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
/*      */ 
/* 1609 */     this.webSettings.setSaveFormData(false);
/* 1610 */     this.webSettings.setJavaScriptEnabled(true);
/*      */ 
/* 1612 */     this.webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
/* 1613 */     boolean bool2 = this.mAdaWebview.mFrameView.mViewOptions.scalable;
/* 1614 */     this.webSettings.supportZoom();
/* 1615 */     this.webSettings.setBuiltInZoomControls(bool2);
/* 1616 */     this.webSettings.setSupportZoom(bool2);
/* 1617 */     this.webSettings.setUseWideViewPort(bool2);
/*      */ 
/* 1621 */     this.webSettings.setDatabasePath(this.mAdaWebview.obtainFrameView().obtainApp().obtainAppWebCachePath());
/* 1622 */     this.webSettings.setAppCacheEnabled(true);
/* 1623 */     this.webSettings.setAppCachePath(this.mAdaWebview.obtainFrameView().obtainApp().obtainAppWebCachePath());
/* 1624 */     this.webSettings.setDatabaseEnabled(true);
/* 1625 */     if (DeviceInfo.sDeviceSdkVer >= 7)
/*      */     {
/* 1629 */       localObject = this.mAdaWebview.obtainFrameView().obtainApp().obtainAppId();
/* 1630 */       SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences((String)localObject, 0);
/* 1631 */       long l = localSharedPreferences.getLong("maxSize", 0L);
/* 1632 */       this.webSettings.setDomStorageEnabled(true);
/* 1633 */       if (l != 0L) {
/* 1634 */         this.webSettings.setAppCacheMaxSize(l);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1639 */     this.webSettings.setAllowFileAccess(true);
/* 1640 */     this.webSettings.setGeolocationEnabled(true);
/*      */ 
/* 1642 */     this.webSettings.setGeolocationDatabasePath(this.mAdaWebview.obtainFrameView().obtainApp().obtainAppWebCachePath());
/* 1643 */     if (Build.VERSION.SDK_INT >= 21)
/*      */     {
/* 1646 */       PlatformUtil.invokeMethod("android.webkit.WebSettings", "setMixedContentMode", this.webSettings, new Class[] { Integer.TYPE }, new Object[] { Integer.valueOf(0) });
/*      */     }
/* 1648 */     setWebChromeClient(new WebJsEvent(this.mAdaWebview));
/* 1649 */     Object localObject = new WebLoadEvent(this.mAdaWebview);
/* 1650 */     this.mWebLoadEvent = ((WebLoadEvent)localObject);
/* 1651 */     this.mScale = getScale();
/* 1652 */     ((WebLoadEvent)localObject).setPageFinishedCallack(this.mPageFinishedCallack);
/* 1653 */     setWebViewClient((WebViewClient)localObject);
/* 1654 */     ReceiveJSValue.addJavascriptInterface(this.mAdaWebview);
/*      */ 
/* 1658 */     requestFocus();
/*      */ 
/* 1660 */     setClickable(true);
/* 1661 */     removeUnSafeJavascriptInterface();
/*      */   }
/*      */ 
/*      */   private void removeUnSafeJavascriptInterface() {
/*      */     try {
/* 1666 */       if ((Build.VERSION.SDK_INT >= 11) && (Build.VERSION.SDK_INT < 17))
/*      */       {
/* 1668 */         Class localClass = getClass();
/* 1669 */         Class[] arrayOfClass = new Class[1];
/* 1670 */         arrayOfClass[0] = String.class;
/* 1671 */         Method localMethod = localClass.getMethod("removeJavascriptInterface", arrayOfClass);
/* 1672 */         WebViewImpl localWebViewImpl = this;
/* 1673 */         String[] arrayOfString1 = { "searchBoxJavaBridge_", "accessibility", "ccessibilityaversal" };
/* 1674 */         for (String str : arrayOfString1)
/* 1675 */           localMethod.invoke(localWebViewImpl, new Object[] { str });
/*      */       }
/*      */     }
/*      */     catch (Exception localException) {
/* 1679 */       localException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
/*      */   {
/* 1685 */     Logger.i("webview", "onDownloadStart " + paramString1 + " " + paramString2 + " " + paramString3 + " " + paramString4 + " " + paramLong);
/*      */     try
/*      */     {
/*      */       Object localObject1;
/* 1687 */       if (DeviceInfo.sDeviceSdkVer > 8) {
/* 1688 */         localObject1 = this.mAdaWebview.obtainFrameView().obtainApp();
/* 1689 */         localObject2 = null;
/* 1690 */         localObject2 = ((IApp)localObject1).convert2AbsFullPath("_downloads/");
/* 1691 */         final String str = PdrUtil.getDownloadFilename(paramString3, paramString4, paramString1);
/* 1692 */         final Context localContext = getContext();
/* 1693 */         DownloadUtil.startRequest(localContext, paramString1, paramString4, (String)localObject2, str, new ICallBack()
/*      */         {
/*      */           public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject) {
/* 1696 */             SDK.IntegratedMode localIntegratedMode = BaseInfo.sRuntimeMode;
/*      */             Object localObject;
/* 1698 */             if (localIntegratedMode == null)
/*      */             {
/* 1700 */               localObject = new IntentFilter();
/* 1701 */               ((IntentFilter)localObject).addAction(DownloadReceiver.ACTION_OPEN_FILE);
/* 1702 */               localContext.registerReceiver(new DownloadReceiver(), (IntentFilter)localObject);
/*      */ 
/* 1704 */               Intent localIntent = new Intent();
/* 1705 */               localIntent.setAction(DownloadReceiver.ACTION_OPEN_FILE);
/* 1706 */               localIntent.putExtra(DownloadReceiver.KEY_FILEURI, String.valueOf(paramAnonymousObject));
/*      */ 
/* 1708 */               PlatformUtil.showNotification(WebViewImpl.this.getContext(), this.val$app.obtainAppName(), str + " 下载完成", localIntent, -1, 1);
/*      */             }
/*      */             else {
/* 1711 */               localObject = new Intent();
/* 1712 */               ((Intent)localObject).putExtra(DownloadReceiver.KEY_FILEURI, String.valueOf(paramAnonymousObject));
/* 1713 */               ((Intent)localObject).setAction(DownloadReceiver.ACTION_DOWNLOAD_COMPLETED);
/* 1714 */               localContext.sendBroadcast((Intent)localObject);
/*      */             }
/* 1716 */             return null;
/*      */           } } );
/*      */       }
/*      */       else {
/* 1720 */         localObject1 = new Intent("android.intent.action.VIEW");
/* 1721 */         ((Intent)localObject1).setData(Uri.parse(paramString1));
/* 1722 */         this.mAdaWebview.getActivity().startActivity((Intent)localObject1);
/*      */       }
/*      */     }
/*      */     catch (Exception localException1)
/*      */     {
/*      */       Object localObject2;
/* 1725 */       Logger.w("webview onDownloadStart", localException1);
/* 1726 */       Logger.e("webview", "browser will download url=" + paramString1);
/*      */       try {
/* 1728 */         localObject2 = new Intent("android.intent.action.VIEW");
/* 1729 */         ((Intent)localObject2).setData(Uri.parse(paramString1));
/* 1730 */         this.mAdaWebview.getActivity().startActivity((Intent)localObject2);
/*      */       } catch (Exception localException2) {
/* 1732 */         localException2.printStackTrace();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void setWebViewData()
/*      */   {
/* 1739 */     Class[] arrayOfClass1 = new Class[0];
/* 1740 */     Class[] arrayOfClass2 = { Boolean.TYPE };
/*      */     try {
/* 1742 */       Method localMethod1 = WebView.class.getDeclaredMethod("getFactory", arrayOfClass1);
/* 1743 */       if (localMethod1 != null) {
/* 1744 */         localMethod1.setAccessible(true);
/* 1745 */         Object[] arrayOfObject1 = new Object[0];
/* 1746 */         Object[] arrayOfObject2 = { Boolean.valueOf(false) };
/*      */         try {
/* 1748 */           Object localObject = localMethod1.invoke(null, arrayOfObject1);
/* 1749 */           Class localClass = localObject.getClass();
/*      */ 
/* 1751 */           Method localMethod2 = localClass.getDeclaredMethod("setWebContentsDebuggingEnabled", arrayOfClass2);
/*      */ 
/* 1755 */           if (localMethod2 != null) {
/* 1756 */             localMethod2.setAccessible(true);
/* 1757 */             localMethod2.invoke(localObject, arrayOfObject2);
/*      */           }
/*      */         }
/*      */         catch (IllegalAccessException localIllegalAccessException)
/*      */         {
/* 1762 */           localIllegalAccessException.printStackTrace();
/*      */         }
/*      */         catch (IllegalArgumentException localIllegalArgumentException) {
/* 1765 */           localIllegalArgumentException.printStackTrace();
/*      */         }
/*      */         catch (InvocationTargetException localInvocationTargetException) {
/* 1768 */           localInvocationTargetException.printStackTrace();
/*      */         }
/*      */         catch (Exception localException) {
/* 1771 */           localException.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (NoSuchMethodException localNoSuchMethodException) {
/* 1776 */       localNoSuchMethodException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void onConfigurationChanged(Configuration paramConfiguration)
/*      */   {
/* 1785 */     super.onConfigurationChanged(paramConfiguration);
/* 1786 */     Logger.i("webview", "onConfigurationChanged");
/*      */ 
/* 1788 */     this.mAdaWebview.executeScript(String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "plusorientationchange" }));
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1799 */     if (this.mUrl != null) {
/* 1800 */       int i = this.mUrl.indexOf(this.mBaseUrl);
/* 1801 */       String str = this.mUrl;
/* 1802 */       if (i >= 0) {
/* 1803 */         str = str.substring(this.mBaseUrl.length());
/*      */       }
/* 1805 */       return "<url=" + str + ">;<hashcode=" + hashCode() + ">";
/*      */     }
/* 1807 */     return super.toString();
/*      */   }
/*      */ 
/*      */   public void removeAllViews()
/*      */   {
/* 1813 */     super.removeAllViews();
/* 1814 */     if (((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView != null)
/* 1815 */       addView((View)((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView, -1, -1);
/*      */   }
/*      */ 
/*      */   String convertRelPath(String paramString)
/*      */   {
/* 1820 */     int i = paramString.indexOf(this.mBaseUrl);
/* 1821 */     String str1 = paramString;
/* 1822 */     if (i >= 0) {
/* 1823 */       str1 = str1.substring(this.mBaseUrl.length());
/*      */     } else {
/* 1825 */       String str2 = this.mBaseUrl.substring("file://".length());
/* 1826 */       i = paramString.indexOf(str2);
/* 1827 */       if (i >= 0) {
/* 1828 */         str1 = str1.substring(str2.length());
/*      */       }
/*      */     }
/* 1831 */     return str1;
/*      */   }
/*      */ 
/*      */   public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
/* 1835 */     if (BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) {
/* 1836 */       return super.onKeyDown(paramInt, paramKeyEvent);
/*      */     }
/* 1838 */     boolean bool = false;
/* 1839 */     d locald = (d)this.mAdaWebview.getActivity();
/* 1840 */     if (paramKeyEvent.getRepeatCount() == 0)
/* 1841 */       bool = locald.onKeyEventExecute(ISysEventListener.SysEventType.onKeyDown, paramInt, paramKeyEvent);
/*      */     else {
/* 1843 */       bool = locald.onKeyEventExecute(ISysEventListener.SysEventType.onKeyDown, paramInt, paramKeyEvent);
/*      */     }
/* 1845 */     return bool ? bool : super.onKeyDown(paramInt, paramKeyEvent);
/*      */   }
/*      */ 
/*      */   public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
/* 1849 */     if (BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) {
/* 1850 */       return super.onKeyUp(paramInt, paramKeyEvent);
/*      */     }
/* 1852 */     boolean bool = false;
/* 1853 */     d locald = (d)this.mAdaWebview.getActivity();
/* 1854 */     bool = locald.onKeyEventExecute(ISysEventListener.SysEventType.onKeyUp, paramInt, paramKeyEvent);
/* 1855 */     return bool ? bool : super.onKeyUp(paramInt, paramKeyEvent);
/*      */   }
/*      */ 
/*      */   protected void onDraw(Canvas paramCanvas)
/*      */   {
/* 1865 */     super.onDraw(paramCanvas);
/* 1866 */     if ((((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView != null) && (((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView.isRefreshEnable()))
/* 1867 */       ((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView.onSelfDraw(paramCanvas);
/*      */   }
/*      */ 
/*      */   public void onRefresh(int paramInt)
/*      */   {
/* 1873 */     this.mAdaWebview.mFrameView.dispatchFrameViewEvents("pulldownrefreshevent", Integer.valueOf(paramInt));
/*      */   }
/*      */ 
/*      */   public boolean onTouchEvent(MotionEvent paramMotionEvent)
/*      */   {
/* 1878 */     this.didTouch = true;
/* 1879 */     if ((Build.VERSION.SDK_INT == 16) && (!PdrUtil.isEquals(Build.BRAND, "samsung")) && 
/* 1880 */       (paramMotionEvent.getAction() == 0)) {
/* 1881 */       int i = getScrollY();
/* 1882 */       scrollTo(getScrollX(), i + 1);
/* 1883 */       scrollTo(getScrollX(), i);
/*      */     }
/*      */ 
/* 1886 */     if (paramMotionEvent.getAction() == 0) {
/* 1887 */       this.mAdaWebview.mFrameView.dispatchFrameViewEvents("touchstart", Integer.valueOf(getContentHeight()));
/*      */     }
/*      */ 
/* 1894 */     if ((BaseInfo.isShowTitleBar(this.mContext)) && ((this.mContext instanceof Activity))) {
/* 1895 */       Activity localActivity = (Activity)this.mContext;
/* 1896 */       switch (paramMotionEvent.getAction()) {
/*      */       case 0:
/* 1898 */         this.mEventY = ((int)paramMotionEvent.getRawY());
/* 1899 */         this.mEventX = ((int)paramMotionEvent.getRawX());
/* 1900 */         break;
/*      */       case 2:
/* 1902 */         break;
/*      */       case 1:
/* 1904 */         int k = (int)paramMotionEvent.getRawY() - this.mEventY;
/* 1905 */         int m = (int)paramMotionEvent.getRawX() - this.mEventX;
/*      */ 
/* 1907 */         if (Math.abs(k) > Math.abs(m)) {
/* 1908 */           if (-1 == this.mTouchSlop) {
/* 1909 */             this.mTouchSlop = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
/*      */           }
/*      */ 
/* 1912 */           if (Math.abs(k) > this.mTouchSlop)
/*      */           {
/* 1914 */             if (k > 0)
/*      */             {
/* 1916 */               PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "showTitleView", null, new Class[] { Activity.class }, new Object[] { localActivity });
/*      */             }
/*      */             else
/*      */             {
/* 1922 */               PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "hideTitleView", null, new Class[] { Activity.class }, new Object[] { localActivity });
/*      */             }
/*      */           }
/*      */         }
/*      */         break;
/*      */       }
/*      */     }
/*      */ 
/* 1930 */     int j = 0;
/* 1931 */     if ((((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView != null) && (((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView.isRefreshEnable()) && (this.mAdaWebview.mWebViewImpl.getScrollY() <= 0))
/*      */     {
/* 1934 */       if (BaseInfo.isShowTitleBar(this.mContext)) {
/* 1935 */         Object localObject = PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "isTitlebarVisible", null, new Class[] { Activity.class, String.class }, new Object[] { (Activity)this.mContext, this.mAdaWebview.obtainApp().obtainAppId() });
/*      */ 
/* 1937 */         if ((null != localObject) && ((localObject instanceof Boolean)) && (((Boolean)localObject).booleanValue()))
/* 1938 */           j = 1;
/*      */       }
/*      */       else
/*      */       {
/* 1942 */         j = 1;
/*      */       }
/*      */     }
/* 1945 */     if ((j != 0) && (((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView.onSelfTouchEvent(paramMotionEvent))) {
/* 1946 */       return true;
/*      */     }
/* 1948 */     return super.onTouchEvent(paramMotionEvent);
/*      */   }
/*      */ 
/*      */   @JavascriptInterface
/*      */   public void addJavascriptInterface(Object paramObject, String paramString)
/*      */   {
/* 1954 */     super.addJavascriptInterface(paramObject, paramString);
/*      */   }
/*      */ 
/*      */   public ActionMode startActionMode(ActionMode.Callback paramCallback)
/*      */   {
/* 1960 */     if (Build.VERSION.SDK_INT >= 11) {
/* 1961 */       CustomizedSelectActionModeCallback localCustomizedSelectActionModeCallback = new CustomizedSelectActionModeCallback(paramCallback);
/*      */ 
/* 1963 */       return super.startActionMode(localCustomizedSelectActionModeCallback);
/*      */     }
/* 1965 */     return super.startActionMode(paramCallback);
/*      */   }
/*      */ 
/*      */   @TargetApi(11)
/*      */   public class CustomizedSelectActionModeCallback
/*      */     implements ActionMode.Callback
/*      */   {
/*      */     ActionMode.Callback callback;
/*      */ 
/*      */     public CustomizedSelectActionModeCallback(ActionMode.Callback arg2)
/*      */     {
/*      */       Object localObject;
/* 1984 */       this.callback = localObject;
/*      */     }
/*      */ 
/*      */     public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
/*      */     {
/* 1990 */       this.callback.onCreateActionMode(paramActionMode, paramMenu);
/* 1991 */       int i = paramMenu.size();
/* 1992 */       for (int j = 0; j < i; j++) {
/* 1993 */         MenuItem localMenuItem = paramMenu.getItem(j);
/* 1994 */         String str = localMenuItem.getTitle().toString();
/* 1995 */         if ((str.contains("搜索")) || (str.toLowerCase().contains("search"))) {
/* 1996 */           paramMenu.removeItem(localMenuItem.getItemId());
/* 1997 */           break;
/*      */         }
/*      */       }
/* 2000 */       return true;
/*      */     }
/*      */ 
/*      */     public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
/*      */     {
/* 2006 */       return this.callback.onPrepareActionMode(paramActionMode, paramMenu);
/*      */     }
/*      */ 
/*      */     public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
/*      */     {
/* 2012 */       return this.callback.onActionItemClicked(paramActionMode, paramMenuItem);
/*      */     }
/*      */ 
/*      */     public void onDestroyActionMode(ActionMode paramActionMode)
/*      */     {
/* 2018 */       this.callback.onDestroyActionMode(paramActionMode);
/*      */     }
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.WebViewImpl
 * JD-Core Version:    0.6.2
 */