/*      */ package io.dcloud.common.adapter.ui;
/*      */ 
/*      */ import android.app.Activity;
/*      */ import android.app.AlertDialog;
/*      */ import android.app.AlertDialog.Builder;
/*      */ import android.content.ActivityNotFoundException;
/*      */ import android.content.Context;
/*      */ import android.content.DialogInterface;
/*      */ import android.content.DialogInterface.OnClickListener;
/*      */ import android.content.DialogInterface.OnKeyListener;
/*      */ import android.content.Intent;
/*      */ import android.content.res.Resources;
/*      */ import android.graphics.Bitmap;
/*      */ import android.net.Uri;
/*      */ import android.net.http.SslCertificate;
/*      */ import android.net.http.SslError;
/*      */ import android.os.Build;
/*      */ import android.os.Build.VERSION;
/*      */ import android.text.TextUtils;
/*      */ import android.util.DisplayMetrics;
/*      */ import android.util.Log;
/*      */ import android.view.KeyEvent;
/*      */ import android.view.ViewGroup;
/*      */ import android.webkit.CookieSyncManager;
/*      */ import android.webkit.MimeTypeMap;
/*      */ import android.webkit.SslErrorHandler;
/*      */ import android.webkit.URLUtil;
/*      */ import android.webkit.WebResourceResponse;
/*      */ import android.webkit.WebSettings;
/*      */ import android.webkit.WebView;
/*      */ import android.webkit.WebViewClient;
/*      */ import android.widget.AbsoluteLayout.LayoutParams;
/*      */ import android.widget.ProgressBar;
/*      */ import io.dcloud.a.a;
/*      */ import io.dcloud.a.b;
/*      */ import io.dcloud.common.DHInterface.IActivityHandler;
/*      */ import io.dcloud.common.DHInterface.IApp;
/*      */ import io.dcloud.common.DHInterface.IFrameView;
/*      */ import io.dcloud.common.DHInterface.ISysEventListener;
/*      */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*      */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*      */ import io.dcloud.common.DHInterface.IWebview;
/*      */ import io.dcloud.common.adapter.io.DHFile;
/*      */ import io.dcloud.common.adapter.util.AndroidResources;
/*      */ import io.dcloud.common.adapter.util.DCloudTrustManager;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.MessageHandler;
/*      */ import io.dcloud.common.adapter.util.ViewOptions;
/*      */ import io.dcloud.common.constant.DataInterface;
/*      */ import io.dcloud.common.util.BaseInfo;
/*      */ import io.dcloud.common.util.DLGeolocation;
/*      */ import io.dcloud.common.util.IOUtil;
/*      */ import io.dcloud.common.util.Md5Utils;
/*      */ import io.dcloud.common.util.NetTool;
/*      */ import io.dcloud.common.util.NetworkTypeUtil;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import io.dcloud.common.util.TelephonyUtil;
/*      */ import io.dcloud.common.util.TestUtil.PointTime;
/*      */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.net.HttpURLConnection;
/*      */ import java.net.URI;
/*      */ import java.net.URL;
/*      */ import java.net.URLEncoder;
/*      */ import java.security.SecureRandom;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import javax.net.ssl.HostnameVerifier;
/*      */ import javax.net.ssl.HttpsURLConnection;
/*      */ import javax.net.ssl.SSLContext;
/*      */ import javax.net.ssl.SSLSession;
/*      */ import javax.net.ssl.TrustManager;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ public final class WebLoadEvent extends WebViewClient
/*      */ {
/*      */   public static final String ENABLE = "enable";
/*      */   public static final String PLUSREADY = "html5plus://ready";
/*   92 */   AdaWebview mAdaWebview = null;
/*      */   static final String TAG = "webview";
/*   94 */   boolean printLog = false;
/*   95 */   private OnPageFinishedCallack mPageFinishedCallack = null;
/*      */ 
/*   97 */   private boolean mClearCache = false;
/*      */ 
/*  101 */   private String mLastPageUrl = "";
/*  102 */   String mAppid = null;
/*  103 */   boolean mIsStreamApp = false;
/*  104 */   InvokeExecutorHelper.InvokeExecutor mWap2AppBlockDialog = null;
/*  105 */   ISysEventListener mWap2AppBlockDialogSysEventListener = null;
/*  106 */   String mPlusJS = null;
/*      */ 
/*  108 */   String TYPE_JS = "type_js";
/*  109 */   String TYPE_CSS = "type_css";
/*      */ 
/*  111 */   boolean isInitAmapGEO = false;
/*      */ 
/*  723 */   ProgressBar mWaitingForWapPage = null;
/*      */   private static final String DIFFERENT_VERSION_JS = "window.plus && (plus.android.import=plus.android.importClass);";
/*      */   private static final String IF_PLUSREADY_TEMPLATE = "(function(){/*console.log('all.js loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(!location.__plusready__){location.__plusready__=true;return 1}else{return 2}return 0})();";
/*      */   private static final String IF_PLUSREADY_EVENT_TEMPLATE = "(function(){/*console.log('plusready event loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(location.__plusready__||window.__html5plus__){if(!location.__plusready__event__){location.__plusready__event__=true;return 1}else{return 2}}return 0})();";
/*      */   private static final String IF_PRELOAD_TEMPLATE = "(function(){/*console.log( 'preload js loading href=' + location.href);*/if(location.__page__load__over__){return 2}var jsfile='%s';if(location.__plusready__||window.__html5plus__){location.__preload__=location.__preload__||[];if(location.__preload__.indexOf(jsfile)<0){location.__preload__.push(jsfile);return 1}else{return 2}}return 0})();";
/*      */   private static final String IF_LOAD_TEMPLATE = "(function(){/*console.log('eval js loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(location.__plusready__||window.__html5plus__){return 1}return 0})();";
/*      */   private static final String ERROR_TEMPLATE = "javascript:(function(){var b=document.createEvent('HTMLEvents');var a='%s';b.url='%s';b.href='%s';b.initEvent(a,false,true);console.error(a);document.dispatchEvent(b);})();";
/*      */   private static final int NOLOAD = 0;
/*      */   private static final int LOADABLE = 1;
/*      */   private static final int LOADED = 2;
/*      */   static final int Timeout_Plus_Inject = 3000;
/*  784 */   Runnable Timeout_Plus_Inject_Runnable = null;
/*      */   static final int Timeout_Page_Finish = 6000;
/*  808 */   Runnable Timeout_Page_Finish_Runnable = null;
/*      */ 
/*  939 */   public static String PAGE_FINISHED_FLAG = "javascript:setTimeout(function(){location.__page__load__over__ = true;},2000);";
/*      */   long mShowLoadingTime;
/*      */ 
/*      */   WebLoadEvent(AdaWebview paramAdaWebview)
/*      */   {
/*  113 */     this.mAdaWebview = paramAdaWebview;
/*  114 */     this.mAppid = this.mAdaWebview.obtainApp().obtainAppId();
/*  115 */     this.printLog = (!BaseInfo.isQihooLifeHelper(paramAdaWebview.getContext()));
/*      */ 
/*  118 */     String str = paramAdaWebview.obtainApp().obtainConfigProperty("ramcachemode");
/*      */ 
/*  120 */     this.mIsStreamApp = paramAdaWebview.obtainApp().isStreamApp();
/*      */ 
/*  122 */     if ((BaseInfo.isBase(paramAdaWebview.getContext())) && (!"enable".equalsIgnoreCase(str)))
/*      */     {
/*  124 */       this.mClearCache = true;
/*      */     }
/*  126 */     this.mPlusJS = ("(function(){/*console.log('all.js loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(!location.__plusready__){location.__plusready__=true;return 1}else{return 2}return 0})();\n" + this.mAdaWebview.mFrameView.obtainPrePlusreadyJs() + "\n" + "window.plus && (plus.android.import=plus.android.importClass);");
/*  127 */     this.isInitAmapGEO = DLGeolocation.checkAMapGeo();
/*      */   }
/*      */ 
/*      */   public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
/*      */   {
/*  169 */     WebResourceResponse localWebResourceResponse = super.shouldInterceptRequest(paramWebView, paramString);
/*      */ 
/*  171 */     AdaWebview.OverrideResourceRequestItem localOverrideResourceRequestItem = this.mAdaWebview.checkResourceRequestUrl(paramString);
/*  172 */     String str1 = "application/x-javascript";
/*  173 */     String str2 = this.mAdaWebview.mEncoding;
/*  174 */     if (localOverrideResourceRequestItem != null) {
/*  175 */       paramString = localOverrideResourceRequestItem.redirect;
/*  176 */       str2 = localOverrideResourceRequestItem.encoding;
/*  177 */       str1 = localOverrideResourceRequestItem.mime;
/*      */     }
/*      */     try
/*      */     {
/*      */       Object localObject1;
/*      */       Object localObject2;
/*      */       String str3;
/*  180 */       if (this.mIsStreamApp) {
/*  181 */         localObject1 = getOriginalUrl(paramString);
/*      */ 
/*  183 */         if (((String)localObject1).startsWith(this.mAdaWebview.obtainApp().obtainAppDataPath())) {
/*  184 */           localObject2 = new File(PdrUtil.stripQuery(PdrUtil.stripAnchor((String)localObject1)));
/*  185 */           if ((!((File)localObject2).exists()) || (((File)localObject2).length() == 0L)) {
/*  186 */             str3 = getUrlByFilePath(paramString);
/*  187 */             sendStatistics(this.mAdaWebview.mFrameView.obtainApp().obtainAppId(), paramWebView.getContext(), paramString, this.mAdaWebview.obtainFullUrl(), this.mAdaWebview.mFrameView.obtainApp());
/*      */ 
/*  191 */             return downloadResponse(paramWebView, paramString, str3, localWebResourceResponse, (File)localObject2, true);
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  196 */       Logger.i("webview", "shouldInterceptRequest url=" + paramString + ";withJs=" + this.mAdaWebview.mInjectPlusWidthJs);
/*      */ 
/*  198 */       localWebResourceResponse = handleDecode(paramString, localWebResourceResponse);
/*      */ 
/*  202 */       if (localWebResourceResponse == null)
/*      */       {
/*  204 */         if ((this.mAdaWebview.mPlusrequire.equals("ahead")) && (this.mAdaWebview.hasPreLoadJsFile()) && ((this.mAdaWebview.mInjectPlusWidthJs == null) || (TextUtils.equals(this.mAdaWebview.mInjectPlusWidthJs, paramString))) && (PdrUtil.isNetPath(paramString)) && (checkJsFile(paramString)))
/*      */         {
/*  210 */           localWebResourceResponse = downloadResponseInjection(localWebResourceResponse, paramString, str1, str2, this.TYPE_JS);
/*  211 */           if (localWebResourceResponse != null)
/*  212 */             this.mAdaWebview.mInjectPlusWidthJs = paramString;
/*      */         }
/*  214 */         else if ((!TextUtils.isEmpty(this.mAdaWebview.getCssString())) && (!this.mAdaWebview.mIsAdvanceCss) && (PdrUtil.isNetPath(paramString)) && (checkCssFile(paramString))) {
/*  215 */           str1 = "text/css";
/*  216 */           localWebResourceResponse = downloadResponseInjection(localWebResourceResponse, paramString, str1, str2, this.TYPE_CSS);
/*  217 */         } else if ((this.isInitAmapGEO) && (!this.mAdaWebview.mInjectGeoLoaded) && (DLGeolocation.checkInjectGeo(this.mAdaWebview.mInjectGEO))) {
/*  218 */           localWebResourceResponse = downloadResponseInjection(localWebResourceResponse, paramString, str1, str2, this.TYPE_JS);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  223 */       if ((localWebResourceResponse == null) && (!BaseInfo.isWap2AppAppid(this.mAppid)))
/*      */       {
/*  225 */         if (("html5plus://ready".equals(paramString)) && 
/*  226 */           (!this.mAdaWebview.mPlusLoaded)) {
/*  227 */           localWebResourceResponse = downloadResponseInjection(localWebResourceResponse, paramString, str1, str2, this.TYPE_JS);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  232 */       if ((localWebResourceResponse == null) && (localOverrideResourceRequestItem != null)) {
/*      */         try {
/*  234 */           localObject1 = new FileInputStream(paramString);
/*  235 */           localWebResourceResponse = new WebResourceResponse(str1, str2, (InputStream)localObject1);
/*      */         } catch (FileNotFoundException localFileNotFoundException) {
/*  237 */           localFileNotFoundException.printStackTrace();
/*      */         }
/*      */       }
/*  240 */       if ((localWebResourceResponse != null) && (localOverrideResourceRequestItem != null) && (localOverrideResourceRequestItem.headerJson != null) && (Build.VERSION.SDK_INT >= 21)) {
/*  241 */         Iterator localIterator = localOverrideResourceRequestItem.headerJson.keys();
/*  242 */         if (localOverrideResourceRequestItem.headerJson.length() > 0) {
/*  243 */           localObject2 = localWebResourceResponse.getResponseHeaders();
/*  244 */           if (localObject2 == null) {
/*  245 */             localObject2 = new HashMap();
/*      */           }
/*  247 */           ((Map)localObject2).put("Access-Control-Allow-Credentials", "true");
/*  248 */           ((Map)localObject2).put("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
/*  249 */           ((Map)localObject2).put("Access-Control-Allow-Origin", "*");
/*  250 */           while (localIterator.hasNext()) {
/*  251 */             str3 = (String)localIterator.next();
/*  252 */             String str4 = localOverrideResourceRequestItem.headerJson.opt(str3).toString();
/*  253 */             ((Map)localObject2).put(str3, str4);
/*      */           }
/*  255 */           localWebResourceResponse.setResponseHeaders((Map)localObject2);
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Exception localException) {
/*  260 */       localException.printStackTrace();
/*  261 */       Logger.e(this.mAppid + ";url=" + paramString);
/*      */     }
/*      */ 
/*  284 */     return localWebResourceResponse;
/*      */   }
/*      */ 
/*      */   private WebResourceResponse downloadResponse(WebView paramWebView, String paramString1, String paramString2, WebResourceResponse paramWebResourceResponse, File paramFile, boolean paramBoolean)
/*      */   {
/*  299 */     if ((!URLUtil.isNetworkUrl(paramString2)) || (paramFile == null)) {
/*  300 */       return paramWebResourceResponse;
/*      */     }
/*  302 */     showLoading();
/*  303 */     HttpURLConnection localHttpURLConnection = null;
/*      */     try {
/*  305 */       URL localURL = new URL(paramString2);
/*  306 */       localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
/*  307 */       localHttpURLConnection.setConnectTimeout(5000);
/*  308 */       localHttpURLConnection.setReadTimeout(5000);
/*  309 */       localHttpURLConnection.setRequestMethod("GET");
/*  310 */       localHttpURLConnection.setDoInput(true);
/*  311 */       int i = localHttpURLConnection.getResponseCode();
/*      */ 
/*  313 */       if ((i == 200) || (i == 206))
/*      */       {
/*  315 */         InputStream localInputStream = localHttpURLConnection.getInputStream();
/*  316 */         boolean bool = DHFile.writeFile(localInputStream, paramFile.getAbsolutePath());
/*  317 */         if (bool) {
/*  318 */           InvokeExecutorHelper.createDownloadTaskListManager().invoke("removeTask", paramString2, true);
/*  319 */           hideLoading();
/*      */         }
/*  321 */         else if (paramBoolean) {
/*  322 */           downloadResponse(paramWebView, paramString1, paramString2, paramWebResourceResponse, paramFile, false);
/*      */         } else {
/*  324 */           hideLoading();
/*      */         }
/*      */ 
/*      */       }
/*  328 */       else if (((i < 400) || (i >= 500)) && (paramBoolean)) {
/*  329 */         downloadResponse(paramWebView, paramString1, paramString2, paramWebResourceResponse, paramFile, false);
/*      */       } else {
/*  331 */         hideLoading();
/*      */       }
/*      */     }
/*      */     catch (Exception localException) {
/*  335 */       localException.printStackTrace();
/*      */     } finally {
/*  337 */       if (localHttpURLConnection != null) {
/*  338 */         localHttpURLConnection.disconnect();
/*      */       }
/*  340 */       hideLoading();
/*      */     }
/*  342 */     return handleDecode(paramString1, paramWebResourceResponse);
/*      */   }
/*      */ 
/*      */   private WebResourceResponse handleDecode(String paramString, WebResourceResponse paramWebResourceResponse)
/*      */   {
/*  354 */     if (TextUtils.isEmpty(paramString)) {
/*  355 */       return paramWebResourceResponse;
/*      */     }
/*      */ 
/*  358 */     if (needDecode(paramString, this.mAdaWebview.mFrameView.obtainApp())) {
/*  359 */       String str1 = getAESKey(paramString, this.mAdaWebview.mFrameView.obtainApp());
/*      */       try
/*      */       {
/*  362 */         File localFile = new File(URI.create(paramString));
/*  363 */         FileInputStream localFileInputStream = new FileInputStream(localFile);
/*  364 */         DataInputStream localDataInputStream = new DataInputStream(localFileInputStream);
/*  365 */         byte[] arrayOfByte = IOUtil.getBytes(localDataInputStream);
/*  366 */         String str2 = (String)InvokeExecutorHelper.AesEncryptionUtil.invoke("byte2hex", new Class[] { [B.class }, new Object[] { arrayOfByte });
/*  367 */         String str3 = (String)InvokeExecutorHelper.AesEncryptionUtil.invoke("decrypt", new Class[] { String.class, String.class, String.class }, new Object[] { str2, str1, a.a() });
/*      */ 
/*  372 */         if (str3 != null) {
/*  373 */           ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(str3.getBytes());
/*      */ 
/*  375 */           paramWebResourceResponse = new WebResourceResponse(getMimeType(paramString), "UTF-8", localByteArrayInputStream);
/*      */         }
/*      */       }
/*      */       catch (FileNotFoundException localFileNotFoundException) {
/*  379 */         localFileNotFoundException.printStackTrace();
/*      */       } catch (Exception localException) {
/*  381 */         localException.printStackTrace();
/*      */       }
/*      */     }
/*  384 */     return paramWebResourceResponse;
/*      */   }
/*      */   private boolean shouldRuntimeHandle(String paramString) {
/*  387 */     return (PdrUtil.isDeviceRootDir(paramString)) || (PdrUtil.isNetPath(paramString)) || (paramString.startsWith("file://"));
/*      */   }
/*      */ 
/*      */   void closeWap2AppBlockDialog(boolean paramBoolean) {
/*  391 */     if (this.mWap2AppBlockDialog != null) {
/*  392 */       this.mWap2AppBlockDialog.invoke("close");
/*  393 */       this.mAdaWebview.obtainApp().unregisterSysEventListener(this.mWap2AppBlockDialogSysEventListener, ISysEventListener.SysEventType.onKeyUp);
/*  394 */       this.mWap2AppBlockDialog = null;
/*  395 */       this.mWap2AppBlockDialogSysEventListener = null;
/*  396 */       if (paramBoolean)
/*  397 */         this.mAdaWebview.loadUrl(this.mAdaWebview.mRecordLastUrl);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
/*      */   {
/*  405 */     Logger.i("webview", "shouldOverrideUrlLoading url=" + paramString);
/*      */ 
/*  410 */     this.mAdaWebview.mRecordLastUrl = paramString;
/*      */ 
/*  414 */     if (this.mAdaWebview.checkOverrideUrl(paramString)) {
/*  415 */       Logger.i("shutao", "检测是否执行拦截回调shouldOverrideUrlLoading url=" + paramString);
/*  416 */       this.mAdaWebview.mFrameView.dispatchFrameViewEvents("overrideUrlLoading", "{url:'" + paramString + "'}");
/*  417 */       return true;
/*  418 */     }if (!shouldRuntimeHandle(paramString)) {
/*      */       try {
/*  420 */         if (paramString.startsWith("sms:"))
/*      */         {
/*  423 */           int i = paramString.indexOf("sms:");
/*  424 */           int j = paramString.indexOf("?");
/*  425 */           if (j == -1) {
/*  426 */             localObject = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
/*  427 */             this.mAdaWebview.getActivity().startActivity((Intent)localObject);
/*  428 */             return true;
/*      */           }
/*  430 */           Object localObject = paramString.substring(i + "sms:".length(), j);
/*  431 */           String str = paramString.substring(j + 1);
/*  432 */           Uri localUri = Uri.parse("sms:" + (String)localObject);
/*  433 */           Intent localIntent2 = new Intent("android.intent.action.VIEW", localUri);
/*  434 */           localIntent2.putExtra("address", (String)localObject);
/*  435 */           localIntent2.putExtra("sms_body", str);
/*  436 */           this.mAdaWebview.getActivity().startActivity(localIntent2);
/*      */         } else {
/*  438 */           Intent localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
/*  439 */           this.mAdaWebview.getActivity().startActivity(localIntent1);
/*      */         }
/*      */       } catch (ActivityNotFoundException localActivityNotFoundException) {
/*  442 */         Logger.e("webview", "ActivityNotFoundException url=" + paramString);
/*      */       }
/*  444 */       return true;
/*  445 */     }if ((null != this.mAdaWebview) && (null != this.mAdaWebview.obtainApp()) && ("true".equals(this.mAdaWebview.obtainApp().obtainConfigProperty("wap2app_running_mode"))) && (this.mAdaWebview.mLoadCompleted) && (this.mAdaWebview.mWebViewImpl.didTouch) && (this.mAdaWebview.obtainApp().obtainWebAppRootView().didCloseSplash()) && (BaseInfo.isWap2AppAppid(this.mAppid)) && (this.mAdaWebview.obtainFrameView().getFrameType() == 2))
/*      */     {
/*  455 */       if (this.mWap2AppBlockDialog == null) {
/*  456 */         InvokeExecutorHelper.InvokeExecutor localInvokeExecutor = InvokeExecutorHelper.createInvokeExecutor("io.dcloud.base.ui.WaitingView", new Class[] { IWebview.class }, new Object[] { this.mAdaWebview });
/*  457 */         Logger.e("webview", "shouldOverrideUrlLoading block url =" + paramString);
/*  458 */         if (localInvokeExecutor.hasObject()) {
/*  459 */           this.mWap2AppBlockDialogSysEventListener = new ISysEventListener()
/*      */           {
/*      */             public boolean onExecute(ISysEventListener.SysEventType paramAnonymousSysEventType, Object paramAnonymousObject) {
/*  462 */               Object[] arrayOfObject = (Object[])paramAnonymousObject;
/*  463 */               int i = ((Integer)arrayOfObject[0]).intValue();
/*  464 */               if (i == 4) {
/*  465 */                 WebLoadEvent.this.closeWap2AppBlockDialog(false);
/*      */               }
/*  467 */               return false;
/*      */             }
/*      */           };
/*  470 */           this.mAdaWebview.obtainApp().registerSysEventListener(this.mWap2AppBlockDialogSysEventListener, ISysEventListener.SysEventType.onKeyUp);
/*  471 */           this.mWap2AppBlockDialog = localInvokeExecutor;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  483 */       return true;
/*      */     }
/*      */ 
/*  497 */     this.mAdaWebview.resetPlusLoadSaveData();
/*  498 */     return false;
/*      */   }
/*      */ 
/*      */   public void onReceivedSslError(WebView paramWebView, final SslErrorHandler paramSslErrorHandler, final SslError paramSslError)
/*      */   {
/*  504 */     String str1 = this.mAdaWebview.obtainApp().obtainConfigProperty("untrustedca");
/*  505 */     if (PdrUtil.isEquals(str1, "refuse")) {
/*  506 */       paramSslErrorHandler.cancel();
/*  507 */     } else if (PdrUtil.isEquals(str1, "warning")) {
/*  508 */       Context localContext = paramWebView.getContext();
/*  509 */       final AlertDialog localAlertDialog = new AlertDialog.Builder(localContext).create();
/*  510 */       localAlertDialog.setIcon(17301601);
/*  511 */       localAlertDialog.setTitle("安全警告");
/*  512 */       localAlertDialog.setCanceledOnTouchOutside(false);
/*  513 */       String str2 = null;
/*  514 */       if (Build.VERSION.SDK_INT >= 14) {
/*  515 */         str2 = paramSslError.getUrl();
/*      */       }
/*  517 */       String str3 = "此站点安全证书存在问题,是否继续?";
/*  518 */       if (!TextUtils.isEmpty(str2)) {
/*  519 */         str3 = str2 + "\n" + str3;
/*      */       }
/*  521 */       localAlertDialog.setMessage(str3);
/*  522 */       DialogInterface.OnClickListener local2 = new DialogInterface.OnClickListener()
/*      */       {
/*      */         public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/*  525 */           if (paramAnonymousInt == -2) {
/*  526 */             localAlertDialog.cancel();
/*  527 */             localAlertDialog.dismiss();
/*  528 */           } else if (paramAnonymousInt == -3) {
/*  529 */             paramSslError.getCertificate().getIssuedBy();
/*  530 */           } else if (paramAnonymousInt == -1) {
/*  531 */             paramSslErrorHandler.proceed();
/*  532 */             localAlertDialog.dismiss();
/*      */           }
/*      */         }
/*      */       };
/*  536 */       localAlertDialog.setButton(-2, localContext.getResources().getString(17039360), local2);
/*      */ 
/*  538 */       localAlertDialog.setButton(-1, localContext.getResources().getString(17039370), local2);
/*  539 */       localAlertDialog.show();
/*      */     } else {
/*  541 */       paramSslErrorHandler.proceed();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
/*      */   {
/*  548 */     Logger.e("webview", "onReceivedError description=" + paramString1 + ";failingUrl=" + paramString2 + ";errorCode=" + paramInt);
/*  549 */     this.mAdaWebview.dispatchWebviewStateEvent(5, paramString1);
/*  550 */     this.mAdaWebview.mFrameView.dispatchFrameViewEvents("failed", this.mAdaWebview);
/*  551 */     this.mAdaWebview.hasErrorPage = true;
/*  552 */     this.mAdaWebview.errorPageUrl = paramString2;
/*  553 */     final IApp localIApp = this.mAdaWebview.mFrameView.obtainApp();
/*  554 */     if (localIApp != null)
/*      */       try
/*      */       {
/*      */         Object localObject;
/*  557 */         if ((BaseInfo.isWap2AppAppid(localIApp.obtainAppId())) && (this.mAdaWebview.mFrameView.getFrameType() == 2)) {
/*  558 */           localObject = this.mAdaWebview.getContext();
/*  559 */           final AlertDialog localAlertDialog = new AlertDialog.Builder((Context)localObject).create();
/*  560 */           final String str1 = paramString2;
/*  561 */           localAlertDialog.setTitle("提示");
/*  562 */           localAlertDialog.setCanceledOnTouchOutside(false);
/*  563 */           String str2 = "无法连接服务器，请检查网络设置";
/*  564 */           localAlertDialog.setMessage(str2);
/*  565 */           DialogInterface.OnClickListener local3 = new DialogInterface.OnClickListener()
/*      */           {
/*      */             public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*      */             {
/*      */               Object localObject;
/*  568 */               if (paramAnonymousInt == -2)
/*      */               {
/*  570 */                 localObject = new Intent("android.settings.SETTINGS");
/*  571 */                 WebLoadEvent.this.mAdaWebview.getActivity().startActivity((Intent)localObject);
/*  572 */               } else if (paramAnonymousInt == -3) {
/*  573 */                 Logger.e("webview", "onReceivedError try again");
/*  574 */                 localObject = DCloudAdapterUtil.getIActivityHandler(WebLoadEvent.this.mAdaWebview.getActivity());
/*  575 */                 if ((localObject != null) && (BaseInfo.isShowTitleBar(WebLoadEvent.this.mAdaWebview.getActivity()))) {
/*  576 */                   ((IActivityHandler)localObject).updateParam("setProgressView", null);
/*      */                 }
/*  578 */                 WebLoadEvent.this.mAdaWebview.loadUrl(str1);
/*  579 */               } else if (paramAnonymousInt == -1) {
/*  580 */                 localObject = WebLoadEvent.this.mAdaWebview.getActivity();
/*  581 */                 IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler((Activity)localObject);
/*  582 */                 localIActivityHandler.updateParam("closewebapp", localObject);
/*      */               }
/*  584 */               localAlertDialog.dismiss();
/*      */             }
/*      */           };
/*  587 */           localAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
/*      */           {
/*      */             public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent) {
/*  590 */               if (paramAnonymousInt == 4) {
/*  591 */                 localAlertDialog.dismiss();
/*  592 */                 Activity localActivity = WebLoadEvent.this.mAdaWebview.getActivity();
/*  593 */                 IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(localActivity);
/*  594 */                 localIActivityHandler.updateParam("closewebapp", localActivity);
/*      */               }
/*  596 */               return false;
/*      */             }
/*      */           });
/*  599 */           localAlertDialog.setButton(-2, "设置网络", local3);
/*  600 */           localAlertDialog.setButton(-3, "重试", local3);
/*  601 */           localAlertDialog.setButton(-1, "退出", local3);
/*  602 */           localAlertDialog.show();
/*  603 */           localIApp.registerSysEventListener(new ISysEventListener()
/*      */           {
/*      */             public boolean onExecute(ISysEventListener.SysEventType paramAnonymousSysEventType, Object paramAnonymousObject) {
/*  606 */               if (ISysEventListener.SysEventType.onResume == paramAnonymousSysEventType) {
/*  607 */                 WebLoadEvent.this.mAdaWebview.mWebViewImpl.postDelayed(new Runnable()
/*      */                 {
/*      */                   public void run() {
/*  610 */                     Logger.e("webview", "onReceivedError 500ms retry after the onResume");
/*  611 */                     IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(WebLoadEvent.this.mAdaWebview.getActivity());
/*  612 */                     if ((localIActivityHandler != null) && (BaseInfo.isShowTitleBar(WebLoadEvent.this.mAdaWebview.getActivity()))) {
/*  613 */                       localIActivityHandler.updateParam("setProgressView", null);
/*      */                     }
/*  615 */                     WebLoadEvent.this.mAdaWebview.loadUrl(WebLoadEvent.5.this.val$f_url);
/*      */                   }
/*      */                 }
/*      */                 , 500L);
/*      */ 
/*  618 */                 localIApp.unregisterSysEventListener(this, paramAnonymousSysEventType);
/*      */               }
/*  620 */               return false;
/*      */             }
/*      */           }
/*      */           , ISysEventListener.SysEventType.onResume);
/*      */ 
/*  623 */           Logger.e("webview", "onReceivedError do clearHistory");
/*  624 */           this.mAdaWebview.clearHistory();
/*      */         } else {
/*  626 */           localObject = getErrorPage();
/*  627 */           if (!"none".equals(localObject)) {
/*  628 */             Logger.e("webview", "onReceivedError  load errorPage " + (String)localObject);
/*  629 */             this.mAdaWebview.loadUrl((String)localObject);
/*      */           }
/*      */           else
/*      */           {
/*  652 */             this.mAdaWebview.hasErrorPage = false;
/*      */           }
/*      */         }
/*      */       } catch (Exception localException) {
/*  656 */         localException.printStackTrace();
/*      */       }
/*      */   }
/*      */ 
/*      */   String getErrorPage()
/*      */   {
/*  664 */     String str = this.mAdaWebview.mFrameView.obtainFrameOptions().errorPage;
/*  665 */     str = ViewOptions.checkOptionsErrorPage(this.mAdaWebview, str);
/*  666 */     return str;
/*      */   }
/*      */ 
/*      */   public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {
/*  670 */     Logger.i("webview", "onPageStarted url=" + paramString);
/*      */ 
/*  673 */     this.mAdaWebview.onPageStarted();
/*  674 */     printOpenLog(paramWebView, paramString);
/*  675 */     if (this.mAdaWebview.hadClearHistory(paramString))
/*      */     {
/*  677 */       return;
/*      */     }
/*  679 */     this.mAdaWebview.directLoadPreLoadJsFile();
/*  680 */     listenPlusInjectTimeout(paramWebView, paramString, "onPageStarted");
/*  681 */     if (!paramString.startsWith("data:")) {
/*  682 */       this.mAdaWebview.mWebViewImpl.mUrl = paramString;
/*      */     }
/*      */ 
/*  685 */     this.mAdaWebview.resetPlusLoadSaveData();
/*      */ 
/*  687 */     if (!PdrUtil.isEmpty(this.mAdaWebview.mWebViewImpl.mUrl)) {
/*  688 */       this.mAdaWebview.mFrameView.dispatchFrameViewEvents("window_close", this.mAdaWebview);
/*      */     }
/*  690 */     this.mAdaWebview.dispatchWebviewStateEvent(0, paramString);
/*      */ 
/*  701 */     AdaFrameView localAdaFrameView = this.mAdaWebview.mFrameView;
/*  702 */     this.mAdaWebview.mFrameView.dispatchFrameViewEvents("loading", this.mAdaWebview);
/*  703 */     if (localAdaFrameView.obtainStatus() != 3) {
/*  704 */       localAdaFrameView.onPreLoading();
/*      */     }
/*  706 */     super.onPageStarted(paramWebView, paramString, paramBitmap);
/*  707 */     if (this.mAdaWebview.mFrameView.getFrameType() == 3)
/*      */       try {
/*  709 */         if (this.mWaitingForWapPage == null) {
/*  710 */           this.mWaitingForWapPage = new ProgressBar(this.mAdaWebview.getContext());
/*  711 */           int i = AndroidResources.mResources.getDisplayMetrics().widthPixels;
/*  712 */           int j = AndroidResources.mResources.getDisplayMetrics().heightPixels;
/*  713 */           int k = PdrUtil.parseInt("7%", i, -1);
/*  714 */           int m = k;
/*      */ 
/*  716 */           ((ViewGroup)this.mAdaWebview.obtainFrameView().obtainMainView()).addView(this.mWaitingForWapPage, new AbsoluteLayout.LayoutParams(k, m, (i - k) / 2, (j - m) / 2));
/*      */         }
/*      */       } catch (Exception localException) {
/*  719 */         localException.printStackTrace();
/*      */       }
/*      */   }
/*      */ 
/*      */   private void printOpenLog(WebView paramWebView, String paramString)
/*      */   {
/*  728 */     String str = paramWebView.getUrl();
/*  729 */     if ((BaseInfo.isBase(paramWebView.getContext())) && (!TextUtils.isEmpty(paramString)) && (!TextUtils.isEmpty(str))) {
/*  730 */       IApp localIApp = this.mAdaWebview.mFrameView.obtainApp();
/*      */ 
/*  732 */       if ((localIApp == null) || (paramString.startsWith("http://")) || (str.startsWith("http://")) || (paramString.startsWith("https://")) || (str.startsWith("https://")))
/*      */       {
/*  736 */         return;
/*      */       }
/*      */ 
/*  739 */       str = getOriginalUrl(str);
/*  740 */       paramString = getOriginalUrl(paramString);
/*  741 */       Log.i(".stream_json", String.format("{\"open\": {\"opener\":\"%s\",\"opened\":\"%s\"}}", new Object[] { getHBuilderPrintUrl(localIApp.convert2RelPath(str)), getHBuilderPrintUrl(localIApp.convert2RelPath(paramString)) }));
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean onLoadPlusJSContent(final WebView paramWebView, final String paramString1, final String paramString2)
/*      */   {
/*  757 */     if (this.mAdaWebview.mPlusrequire.equals("none")) {
/*  758 */       return false;
/*      */     }
/*  760 */     if (this.mAdaWebview.isRealInject(paramString1)) {
/*  761 */       Logger.i("shutao", "all.js已经提前注入JS完成。不需要再注入了" + this.mAdaWebview.getOriginalUrl());
/*  762 */       return true;
/*      */     }
/*  764 */     Logger.i("shutao", "plus提前注入JS" + this.mAdaWebview.getOriginalUrl());
/*  765 */     if ((this.mAdaWebview.mPlusrequire.equals("later")) && (paramString2.equals("onPageFinished"))) {
/*  766 */       paramWebView.postDelayed(new Runnable()
/*      */       {
/*      */         public void run() {
/*  769 */           WebLoadEvent.this.mAdaWebview.mPlusInjectTag = paramString2;
/*  770 */           WebLoadEvent.this.completeLoadJs(paramWebView, paramString1, paramString2, new String[] { WebLoadEvent.this.mPlusJS, "window.plus && (plus.android.import=plus.android.importClass);" }, "(function(){/*console.log('all.js loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(!location.__plusready__){location.__plusready__=true;return 1}else{return 2}return 0})();", new Object[] { paramString1 });
/*      */         }
/*      */       }
/*      */       , 2000L);
/*      */     }
/*      */     else
/*      */     {
/*  774 */       this.mAdaWebview.mPlusInjectTag = paramString2;
/*  775 */       completeLoadJs(paramWebView, paramString1, paramString2, new String[] { this.mPlusJS, "window.plus && (plus.android.import=plus.android.importClass);" }, "(function(){/*console.log('all.js loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(!location.__plusready__){location.__plusready__=true;return 1}else{return 2}return 0})();", new Object[] { paramString1 });
/*      */     }
/*      */ 
/*  779 */     return false;
/*      */   }
/*      */ 
/*      */   private void listenPlusInjectTimeout(final WebView paramWebView, final String paramString1, final String paramString2)
/*      */   {
/*  787 */     if (this.mAdaWebview.mPlusrequire.equals("none")) {
/*  788 */       return;
/*      */     }
/*  790 */     if (this.Timeout_Plus_Inject_Runnable != null) {
/*  791 */       MessageHandler.removeCallbacks(this.Timeout_Plus_Inject_Runnable);
/*      */     }
/*  793 */     this.Timeout_Plus_Inject_Runnable = new Runnable()
/*      */     {
/*      */       public void run() {
/*  796 */         if (!WebLoadEvent.this.mAdaWebview.isRealInject(paramString1)) {
/*  797 */           Logger.i("WebViewData", "listenPlusInjectTimeout url=" + paramString1);
/*  798 */           WebLoadEvent.this.onLoadPlusJSContent(paramWebView, paramString1, "plus_inject_timeout_" + paramString2);
/*  799 */           WebLoadEvent.this.mAdaWebview.mPreloadJsLoading = false;
/*  800 */           WebLoadEvent.this.Timeout_Plus_Inject_Runnable = null;
/*      */         }
/*      */       }
/*      */     };
/*  804 */     MessageHandler.postDelayed(this.Timeout_Plus_Inject_Runnable, 3000L);
/*      */   }
/*      */ 
/*      */   void listenPageFinishTimeout(final WebView paramWebView, final String paramString1, final String paramString2)
/*      */   {
/*  810 */     if ((this.mAdaWebview.mLoaded) && (this.mAdaWebview.isRealInject(paramString1))) {
/*  811 */       injectScript(paramWebView, paramString1, paramString2);
/*      */     } else {
/*  813 */       if (this.Timeout_Page_Finish_Runnable != null) {
/*  814 */         MessageHandler.removeCallbacks(this.Timeout_Page_Finish_Runnable);
/*      */       }
/*  816 */       this.Timeout_Page_Finish_Runnable = new Runnable()
/*      */       {
/*      */         public void run() {
/*  819 */           if ((!WebLoadEvent.this.mAdaWebview.mLoaded) && (WebLoadEvent.this.mAdaWebview.isRealInject(paramString1)))
/*      */           {
/*  821 */             WebLoadEvent.this.injectScript(paramWebView, paramString1, "page_finished_timeout_" + paramString2);
/*  822 */             WebLoadEvent.this.Timeout_Page_Finish_Runnable = null;
/*      */           }
/*      */         }
/*      */       };
/*  826 */       MessageHandler.postDelayed(this.Timeout_Page_Finish_Runnable, 6000L);
/*      */     }
/*      */   }
/*      */ 
/*  830 */   private void onPlusreadyEvent(WebView paramWebView, String paramString1, String paramString2) { StringBuffer localStringBuffer1 = new StringBuffer();
/*  831 */     localStringBuffer1.append(String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "plusready" }));
/*  832 */     StringBuffer localStringBuffer2 = new StringBuffer();
/*  833 */     localStringBuffer2.append(String.format("!function(){try{var a,b,c,d,e=document.getElementsByTagName(\"iframe\");if(e&&e.length)for(a=0;a<e.length;a++)b=e[a],c=b.contentWindow.document.createEvent(\"HTMLEvents\"),d=\"%s\",c.initEvent(d,!1,!0),b.contentWindow.plus=((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus),b.contentWindow.document.dispatchEvent(c)}catch(d){}}();", new Object[] { "plusready" }));
/*  834 */     String str = "plus.webview.currentWebview().__needTouchEvent__()";
/*  835 */     completeLoadJs(paramWebView, paramString1, paramString2, new String[] { localStringBuffer1.toString(), localStringBuffer2.toString(), str }, "(function(){/*console.log('plusready event loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(location.__plusready__||window.__html5plus__){if(!location.__plusready__event__){location.__plusready__event__=true;return 1}else{return 2}}return 0})();", new Object[] { paramString1 }); }
/*      */ 
/*      */   private final void completeLoadJs(final WebView paramWebView, final String paramString1, final String paramString2, final String[] paramArrayOfString, final String paramString3, final Object[] paramArrayOfObject)
/*      */   {
/*  839 */     String str = ReceiveJSValue.registerCallback(this.mAdaWebview, String.format(paramString3, paramArrayOfObject), new ReceiveJSValue.ReceiveJSValueCallback()
/*      */     {
/*      */       public String callback(JSONArray paramAnonymousJSONArray) {
/*      */         try {
/*  843 */           int i = paramAnonymousJSONArray.getInt(1);
/*      */ 
/*  845 */           if ((0 == i) && (!PdrUtil.isEquals(paramString2, "onPageFinished")))
/*  846 */             WebLoadEvent.this.completeLoadJs(paramWebView, paramString1, paramString2, paramArrayOfString, paramString3, paramArrayOfObject);
/*  847 */           else if (1 == i) {
/*  848 */             for (String str : paramArrayOfString)
/*  849 */               WebLoadEvent.this.mAdaWebview.executeScript(str);
/*      */           }
/*  851 */           else if (2 != i);
/*      */         }
/*      */         catch (JSONException localJSONException) {
/*  854 */           localJSONException.printStackTrace();
/*      */         }
/*  856 */         return null;
/*      */       }
/*      */     });
/*  859 */     this.mAdaWebview.executeScript(str);
/*      */   }
/*      */   void onUpdatePlusData(WebView paramWebView, String paramString1, String paramString2) {
/*  862 */     this.mAdaWebview.executeScript(this.mAdaWebview.getScreenAndDisplayJson(this.mAdaWebview));
/*      */ 
/*  864 */     onExecuteEvalJSStatck(paramWebView, paramString1, paramString2);
/*      */   }
/*      */ 
/*      */   private void onPreloadJSContent(final WebView paramWebView, final String paramString1, final String paramString2) {
/*  868 */     if (this.mAdaWebview.mPreloadJsLoaded) {
/*  869 */       Logger.i("shutao", "mPreloadJs 已经提前注入JS完成。不需要再注入了" + this.mAdaWebview.getOriginalUrl());
/*  870 */       return;
/*      */     }
/*  872 */     this.mAdaWebview.loadPreLoadJsFile(new AdaWebview.IFExecutePreloadJSContentCallBack()
/*      */     {
/*      */       public void callback(String paramAnonymousString1, String paramAnonymousString2) {
/*  875 */         Logger.i("shutao", "注入mPreloadJs ;" + WebLoadEvent.this.mAdaWebview.getOriginalUrl() + ";" + paramAnonymousString1);
/*  876 */         if (!PdrUtil.isEmpty(paramAnonymousString2)) {
/*  877 */           WebLoadEvent.this.mAdaWebview.mPreloadJsLoaded = true;
/*  878 */           WebLoadEvent.this.completeLoadJs(paramWebView, paramString1, paramString2, new String[] { paramAnonymousString2 }, "(function(){/*console.log( 'preload js loading href=' + location.href);*/if(location.__page__load__over__){return 2}var jsfile='%s';if(location.__plusready__||window.__html5plus__){location.__preload__=location.__preload__||[];if(location.__preload__.indexOf(jsfile)<0){location.__preload__.push(jsfile);return 1}else{return 2}}return 0})();", new Object[] { paramAnonymousString1 });
/*      */         }
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void onLoadCssContent() {
/*  885 */     if (this.mAdaWebview.mIsAdvanceCss) {
/*  886 */       Logger.i("shutao", "已经提前注入CSS完成。不需要再注入了" + this.mAdaWebview.getOriginalUrl());
/*  887 */       return;
/*      */     }
/*  889 */     if (this.mAdaWebview.loadCssFile())
/*  890 */       Logger.i("shutao", "提前注入CSS完成" + this.mAdaWebview.getOriginalUrl());
/*      */   }
/*      */ 
/*      */   private void onExecuteEvalJSStatck(WebView paramWebView, String paramString1, String paramString2)
/*      */   {
/*  895 */     String str = this.mAdaWebview.get_eval_js_stack();
/*  896 */     if (!PdrUtil.isEmpty(str))
/*  897 */       completeLoadJs(paramWebView, paramString1, paramString2, new String[] { str }, "(function(){/*console.log('eval js loading href=' + location.href);*/if(location.__page__load__over__){return 2}if(location.__plusready__||window.__html5plus__){return 1}return 0})();", new Object[] { paramString1 });
/*      */   }
/*      */ 
/*      */   private void startTryLoadAllJSContent(WebView paramWebView, String paramString1, String paramString2) {
/*  901 */     loadAllJSContent(paramWebView, paramString1, paramString2);
/*      */   }
/*      */ 
/*      */   private void loadAllJSContent(WebView paramWebView, String paramString1, String paramString2)
/*      */   {
/*  907 */     if (onLoadPlusJSContent(paramWebView, paramString1, paramString2))
/*      */     {
/*  909 */       injectScript(paramWebView, paramString1, paramString2);
/*      */     }
/*      */   }
/*      */ 
/*      */   void injectScript(final WebView paramWebView, final String paramString1, final String paramString2)
/*      */   {
/*  916 */     if ((paramString2.equals("onPageFinished")) && (this.mAdaWebview.mPlusrequire.equals("later"))) {
/*  917 */       paramWebView.postDelayed(new Runnable()
/*      */       {
/*      */         public void run()
/*      */         {
/*  921 */           WebLoadEvent.this.onPreloadJSContent(paramWebView, paramString1, paramString2);
/*      */ 
/*  923 */           WebLoadEvent.this.onPlusreadyEvent(paramWebView, paramString1, paramString2);
/*      */         }
/*      */       }
/*      */       , 2000L);
/*      */     }
/*      */     else
/*      */     {
/*  928 */       onPreloadJSContent(paramWebView, paramString1, paramString2);
/*      */ 
/*  930 */       onPlusreadyEvent(paramWebView, paramString1, paramString2);
/*      */     }
/*      */ 
/*  935 */     onLoadCssContent();
/*      */   }
/*      */ 
/*      */   public void onPageFinished(WebView paramWebView, String paramString)
/*      */   {
/*  943 */     Logger.d("webview", "onPageFinished=" + paramString);
/*  944 */     if (this.mAdaWebview.hadClearHistory(paramString)) {
/*  945 */       this.mAdaWebview.hasErrorPage = false;
/*  946 */       return;
/*      */     }
/*  948 */     this.mAdaWebview.mWebViewImpl.mScale = paramWebView.getScale();
/*  949 */     int i = 0;
/*  950 */     if (this.mAdaWebview.hasErrorPage) {
/*  951 */       localObject = getErrorPage();
/*  952 */       if ((PdrUtil.isEquals(paramString, (String)localObject)) || (("data:text/html,chromewebdata".equals(paramString)) && ("none".equals(localObject))))
/*      */       {
/*  961 */         i = 1;
/*      */       }
/*      */       else {
/*  964 */         return;
/*      */       }
/*      */     }
/*  967 */     if (this.mAdaWebview.unReceiveTitle) {
/*  968 */       Logger.i("webview", "onPageFinished will exe titleUpdate =" + paramString);
/*  969 */       this.mAdaWebview.mFrameView.dispatchFrameViewEvents("titleUpdate", this.mAdaWebview.mWebViewImpl.getTitle());
/*  970 */       this.mAdaWebview.unReceiveTitle = false;
/*      */     }
/*  972 */     CookieSyncManager.getInstance().sync();
/*      */ 
/*  974 */     Logger.i("shutao", "onPageFinished" + this.mAdaWebview.getOriginalUrl());
/*  975 */     this.mAdaWebview.dispatchWebviewStateEvent(1, paramString);
/*      */ 
/*  977 */     onLoadPlusJSContent(paramWebView, paramString, "onPageFinished");
/*  978 */     if (this.mAdaWebview.isRealInject(paramString)) {
/*  979 */       injectScript(paramWebView, paramString, "onPageFinished");
/*      */     }
/*      */ 
/*  983 */     this.mAdaWebview.mFrameView.dispatchFrameViewEvents("loaded", this.mAdaWebview);
/*  984 */     if (i != 0) {
/*  985 */       localObject = String.format("javascript:(function(){var b=document.createEvent('HTMLEvents');var a='%s';b.url='%s';b.href='%s';b.initEvent(a,false,true);console.error(a);document.dispatchEvent(b);})();", new Object[] { "error", this.mAdaWebview.getOriginalUrl(), this.mAdaWebview.errorPageUrl });
/*  986 */       this.mAdaWebview.executeScript((String)localObject);
/*  987 */       this.mAdaWebview.errorPageUrl = null;
/*  988 */       this.mAdaWebview.hasErrorPage = false;
/*      */     }
/*  990 */     Object localObject = this.mAdaWebview.mFrameView;
/*      */ 
/*  992 */     if (((AdaFrameItem)localObject).mViewOptions.hasBackground()) {
/*  993 */       ((AdaFrameItem)localObject).mViewOptions.mWebviewScale = paramWebView.getScale();
/*      */     }
/*      */ 
/* 1001 */     if (((IFrameView)localObject).obtainStatus() != 3) {
/* 1002 */       ((IFrameView)localObject).onPreShow(null);
/*      */     }
/* 1004 */     if (!this.mAdaWebview.mLoaded) {
/* 1005 */       this.mAdaWebview.mLoaded = true;
/* 1006 */       this.mAdaWebview.mPlusLoaded = true;
/*      */     }
/* 1008 */     super.onPageFinished(paramWebView, paramString);
/*      */ 
/* 1010 */     if ((this.mAdaWebview.justClearOption) && (!paramString.startsWith("data:"))) {
/* 1011 */       Logger.d("webview", "onPageFinished mWebViewImpl.clearHistory url=" + paramString);
/* 1012 */       this.mAdaWebview.mWebViewImpl.clearHistory();
/* 1013 */       this.mAdaWebview.justClearOption = false;
/*      */     }
/* 1015 */     this.mAdaWebview.mWebViewImpl.webSettings.setCacheMode(this.mAdaWebview.getCacheMode());
/*      */ 
/* 1017 */     if (this.mPageFinishedCallack != null) {
/* 1018 */       this.mPageFinishedCallack.onLoad();
/*      */     }
/* 1020 */     if (this.mWaitingForWapPage != null)
/*      */       try {
/* 1022 */         ((ViewGroup)this.mAdaWebview.obtainFrameView().obtainMainView()).removeView(this.mWaitingForWapPage);
/* 1023 */         this.mWaitingForWapPage = null;
/*      */       } catch (Exception localException) {
/* 1025 */         localException.printStackTrace();
/*      */       }
/*      */   }
/*      */ 
/*      */   public void onLoadResource(WebView paramWebView, String paramString)
/*      */   {
/* 1032 */     if (this.printLog) {
/* 1033 */       Logger.i("webview", "onLoadResource url=" + paramString);
/*      */     }
/*      */ 
/* 1036 */     printResourceLog(paramWebView, this.mAdaWebview.mFrameView.obtainApp(), paramWebView.getUrl(), paramString);
/*      */ 
/* 1038 */     IFrameView localIFrameView = this.mAdaWebview.obtainFrameView();
/* 1039 */     if (localIFrameView.obtainStatus() != 3) {
/* 1040 */       localIFrameView.onLoading();
/*      */     }
/* 1042 */     if (this.mAdaWebview.checkResourceLoading(paramString)) {
/* 1043 */       this.mAdaWebview.mFrameView.dispatchFrameViewEvents("listenResourceLoading", "{url:'" + paramString + "'}");
/*      */     }
/* 1045 */     this.mAdaWebview.dispatchWebviewStateEvent(2, paramString);
/*      */ 
/* 1047 */     super.onLoadResource(paramWebView, paramString);
/*      */   }
/*      */ 
/*      */   public void setPageFinishedCallack(OnPageFinishedCallack paramOnPageFinishedCallack)
/*      */   {
/* 1070 */     this.mPageFinishedCallack = paramOnPageFinishedCallack;
/*      */   }
/*      */ 
/*      */   private String getUrlByFilePath(String paramString)
/*      */   {
/*      */     try
/*      */     {
/* 1085 */       Activity localActivity = this.mAdaWebview.obtainApp().getActivity();
/* 1086 */       if (localActivity != null) {
/* 1087 */         IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(localActivity);
/* 1088 */         return localIActivityHandler.getUrlByFilePath(this.mAppid, paramString);
/*      */       }
/*      */     } catch (Exception localException) {
/* 1091 */       localException.printStackTrace();
/*      */     }
/* 1093 */     return paramString;
/*      */   }
/*      */ 
/*      */   private void showLoading()
/*      */   {
/* 1098 */     this.mAdaWebview.mWebViewImpl.post(new Runnable()
/*      */     {
/*      */       public void run() {
/* 1101 */         WebLoadEvent.this.mAdaWebview.mFrameView.dispatchFrameViewEvents("show_loading", WebLoadEvent.this.mAdaWebview.mFrameView);
/*      */       }
/*      */     });
/* 1104 */     this.mShowLoadingTime = System.currentTimeMillis();
/*      */   }
/*      */ 
/*      */   private void hideLoading() {
/* 1108 */     this.mAdaWebview.mWebViewImpl.post(new Runnable()
/*      */     {
/*      */       public void run() {
/* 1111 */         long l = System.currentTimeMillis();
/* 1112 */         if (l - WebLoadEvent.this.mShowLoadingTime < 1000L) {
/* 1113 */           WebLoadEvent.this.mAdaWebview.mWebViewImpl.postDelayed(this, l - WebLoadEvent.this.mShowLoadingTime);
/* 1114 */           return;
/*      */         }
/* 1116 */         WebLoadEvent.this.mAdaWebview.mFrameView.dispatchFrameViewEvents("hide_loading", WebLoadEvent.this.mAdaWebview.mFrameView);
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void sendStatistics(String paramString1, Context paramContext, String paramString2, String paramString3, IApp paramIApp)
/*      */   {
/* 1124 */     String str1 = "";
/*      */     try {
/* 1126 */       str1 = URLEncoder.encode(Build.MODEL, "utf-8");
/*      */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/* 1128 */       localUnsupportedEncodingException.printStackTrace();
/*      */     }
/*      */ 
/* 1131 */     String str2 = DataInterface.getStatisticsUrl(paramString1, TelephonyUtil.getIMEI(paramContext), 9, TestUtil.PointTime.getBaseVer(paramContext), str1);
/*      */ 
/* 1138 */     paramString3 = getOriginalUrl(paramString3);
/*      */ 
/* 1140 */     if ((paramString2.startsWith("http://")) || (paramString2.startsWith("https://")))
/*      */     {
/* 1142 */       return;
/*      */     }
/*      */ 
/* 1145 */     paramString2 = getOriginalUrl(paramString2);
/*      */ 
/* 1147 */     String str3 = getHBuilderPrintUrl(paramIApp.convert2RelPath(paramString3));
/* 1148 */     String str4 = getHBuilderPrintUrl(paramIApp.convert2RelPath(paramString2));
/*      */ 
/* 1150 */     str2 = str2 + "&net=" + NetworkTypeUtil.getNetworkType(paramContext) + "&sr=" + PdrUtil.encodeURL(str4) + "&sh=" + PdrUtil.encodeURL(str3);
/*      */ 
/* 1152 */     NetTool.httpGet(str2);
/*      */   }
/*      */ 
/*      */   private void printResourceLog(WebView paramWebView, IApp paramIApp, String paramString1, String paramString2)
/*      */   {
/* 1161 */     if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1)) || (paramWebView == null) || (paramIApp == null)) {
/* 1162 */       return;
/*      */     }
/* 1164 */     if ((BaseInfo.isBase(paramWebView.getContext())) && 
/* 1165 */       (!paramString1.equalsIgnoreCase(paramString2)) && (!TextUtils.isEmpty(paramString2)))
/*      */     {
/* 1167 */       if ((this.mClearCache) && (!this.mLastPageUrl.equalsIgnoreCase(paramString1)))
/*      */       {
/* 1170 */         paramWebView.clearCache(true);
/*      */       }
/*      */ 
/* 1173 */       this.mLastPageUrl = paramString1;
/*      */ 
/* 1175 */       paramString1 = getOriginalUrl(paramString1);
/*      */ 
/* 1177 */       if ((paramString2.startsWith("http://")) || (paramString2.startsWith("https://")))
/*      */       {
/* 1179 */         return;
/*      */       }
/*      */ 
/* 1182 */       paramString2 = getOriginalUrl(paramString2);
/* 1183 */       Log.i(".stream_json", String.format("{\"resource\": {\"%s\":\"%s\"}}", new Object[] { getHBuilderPrintUrl(paramIApp.convert2RelPath(paramString1)), getHBuilderPrintUrl(paramIApp.convert2RelPath(paramString2)) }));
/*      */     }
/*      */   }
/*      */ 
/*      */   public static String getOriginalUrl(String paramString)
/*      */   {
/* 1198 */     if (TextUtils.isEmpty(paramString)) {
/* 1199 */       return "";
/*      */     }
/* 1201 */     if (paramString.startsWith("file://")) {
/* 1202 */       paramString = paramString.substring("file://".length());
/*      */     }
/* 1204 */     return paramString;
/*      */   }
/*      */ 
/*      */   public static String getHBuilderPrintUrl(String paramString)
/*      */   {
/* 1213 */     if (TextUtils.isEmpty(paramString)) {
/* 1214 */       return "";
/*      */     }
/* 1216 */     String str = "_www" + File.separator;
/* 1217 */     if (paramString.startsWith(str)) {
/* 1218 */       paramString = paramString.substring(str.length());
/*      */     }
/* 1220 */     return paramString;
/*      */   }
/*      */ 
/*      */   private static boolean needDecode(String paramString, IApp paramIApp)
/*      */   {
/* 1225 */     if ((TextUtils.isEmpty(paramString)) || (paramIApp == null)) {
/* 1226 */       return false;
/*      */     }
/* 1228 */     Map localMap = b.a().a(paramIApp.obtainAppId());
/* 1229 */     if ((localMap == null) || (localMap.size() == 0)) {
/* 1230 */       return false;
/*      */     }
/* 1232 */     paramString = getOriginalUrl(paramString);
/* 1233 */     String str = getHBuilderPrintUrl(paramIApp.convert2RelPath(paramString));
/* 1234 */     if (localMap.containsKey(str)) {
/* 1235 */       return true;
/*      */     }
/* 1237 */     return false;
/*      */   }
/*      */ 
/*      */   private static String getAESKey(String paramString, IApp paramIApp)
/*      */   {
/* 1242 */     if ((TextUtils.isEmpty(paramString)) || (paramIApp == null)) {
/* 1243 */       return "";
/*      */     }
/* 1245 */     Map localMap = b.a().a(paramIApp.obtainAppId());
/* 1246 */     if ((localMap == null) || (localMap.size() == 0)) {
/* 1247 */       return "";
/*      */     }
/* 1249 */     paramString = getOriginalUrl(paramString);
/* 1250 */     String str = getHBuilderPrintUrl(paramIApp.convert2RelPath(paramString));
/* 1251 */     if (localMap.containsKey(str)) {
/* 1252 */       return (String)localMap.get(str);
/*      */     }
/* 1254 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getMimeType(String paramString) {
/* 1258 */     String str1 = null;
/* 1259 */     String str2 = MimeTypeMap.getFileExtensionFromUrl(paramString);
/* 1260 */     if (str2 != null) {
/* 1261 */       MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
/* 1262 */       str1 = localMimeTypeMap.getMimeTypeFromExtension(str2);
/*      */     }
/* 1264 */     if (TextUtils.isEmpty(str1)) {
/* 1265 */       str1 = "text/plain";
/*      */     }
/* 1267 */     return str1;
/*      */   }
/*      */ 
/*      */   private boolean checkJsFile(String paramString)
/*      */   {
/* 1276 */     if ((!TextUtils.isEmpty(paramString)) && 
/* 1277 */       (paramString.contains(".js")) && (!paramString.contains(".jsp"))) {
/* 1278 */       return true;
/*      */     }
/*      */ 
/* 1281 */     return false;
/*      */   }
/*      */ 
/*      */   private boolean checkCssFile(String paramString) {
/* 1285 */     if ((!TextUtils.isEmpty(paramString)) && 
/* 1286 */       (paramString.contains(".css"))) {
/* 1287 */       return true;
/*      */     }
/*      */ 
/* 1290 */     return false;
/*      */   }
/*      */ 
/*      */   private String getCacheLocalFilePath(String paramString1, String paramString2)
/*      */   {
/* 1301 */     if (this.TYPE_JS.equals(paramString2)) {
/* 1302 */       return this.mAdaWebview.obtainApp().obtainAppTempPath() + "__plus__cache__/" + Md5Utils.md5(paramString1) + ".js";
/*      */     }
/* 1304 */     return this.mAdaWebview.obtainApp().obtainAppTempPath() + "__plus__cache__/" + Md5Utils.md5(paramString1) + ".css";
/*      */   }
/*      */ 
/*      */   private CatchFile getUrlFile(String paramString1, String paramString2) throws Exception {
/* 1308 */     String str1 = getCacheLocalFilePath(paramString1, paramString2);
/*      */     try {
/* 1310 */       if (DHFile.isExist(str1)) {
/* 1311 */         CatchFile localCatchFile = new CatchFile();
/* 1312 */         localCatchFile.mFile = new File(str1);
/* 1313 */         localCatchFile.mExist = localCatchFile.mFile.exists();
/* 1314 */         return localCatchFile;
/*      */       }
/*      */     } catch (IOException localIOException1) {
/* 1317 */       localIOException1.printStackTrace();
/*      */     }
/*      */     try {
/* 1320 */       HttpURLConnection localHttpURLConnection = null;
/*      */ 
/* 1322 */       URL localURL = new URL(paramString1);
/* 1323 */       localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
/* 1324 */       if ((localHttpURLConnection instanceof HttpsURLConnection))
/*      */       {
/*      */         try
/*      */         {
/* 1328 */           SSLContext localSSLContext = SSLContext.getInstance("TLSv1");
/*      */ 
/* 1330 */           localSSLContext.init(null, new TrustManager[] { new DCloudTrustManager() }, new SecureRandom());
/*      */ 
/* 1333 */           ((HttpsURLConnection)localHttpURLConnection).setSSLSocketFactory(localSSLContext.getSocketFactory());
/* 1334 */           ((HttpsURLConnection)localHttpURLConnection).setHostnameVerifier(new HostnameVerifier() {
/*      */             public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession) {
/* 1336 */               return true;
/*      */             } } );
/*      */         }
/*      */         catch (Exception localException) {
/* 1340 */           throw new RuntimeException(localException);
/*      */         }
/*      */       }
/* 1343 */       localHttpURLConnection.setConnectTimeout(5000);
/* 1344 */       localHttpURLConnection.setReadTimeout(5000);
/* 1345 */       localHttpURLConnection.setRequestMethod("GET");
/* 1346 */       localHttpURLConnection.setDoInput(true);
/*      */ 
/* 1348 */       int i = localHttpURLConnection.getResponseCode();
/* 1349 */       String str2 = localHttpURLConnection.getContentType();
/*      */ 
/* 1352 */       if ((!TextUtils.isEmpty(str2)) && (((paramString2.equals(this.TYPE_JS)) && (str2.contains("javascript"))) || ((paramString2.equals(this.TYPE_CSS)) && ((str2.contains("text/css")) || (localURL.getPath().endsWith(".css"))))))
/*      */       {
/* 1354 */         if ((i == 200) || (i == 206))
/*      */         {
/* 1356 */           InputStream localInputStream = localHttpURLConnection.getInputStream();
/*      */ 
/* 1358 */           boolean bool = DHFile.writeFile(localInputStream, str1);
/* 1359 */           IOUtil.close(localInputStream);
/* 1360 */           if (bool) {
/* 1361 */             localObject = new CatchFile();
/* 1362 */             ((CatchFile)localObject).mFile = new File(str1);
/* 1363 */             ((CatchFile)localObject).mExist = ((CatchFile)localObject).mFile.exists();
/* 1364 */             ((CatchFile)localObject).mEncoding = localHttpURLConnection.getContentEncoding();
/* 1365 */             ((CatchFile)localObject).mContentType = str2;
/* 1366 */             return localObject;
/*      */           }
/* 1368 */           Object localObject = new File(str1);
/* 1369 */           if (((File)localObject).exists()) {
/* 1370 */             ((File)localObject).delete();
/*      */           }
/* 1372 */           return null;
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException2) {
/* 1377 */       localIOException2.printStackTrace();
/* 1378 */       return null;
/*      */     }
/* 1380 */     return null;
/*      */   }
/*      */ 
/*      */   private WebResourceResponse downloadResponseInjection(WebResourceResponse paramWebResourceResponse, String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/* 1389 */     long l = System.currentTimeMillis();
/*      */     String str;
/*      */     Object localObject2;
/*      */     Object localObject3;
/*      */     byte[] arrayOfByte;
/*      */     int i;
/* 1390 */     if (paramString4.equals(this.TYPE_JS))
/*      */     {
/* 1392 */       str = this.mAdaWebview.getPreLoadJsString();
/* 1393 */       if ((TextUtils.isEmpty(str)) && (TextUtils.isEmpty(this.mPlusJS)))
/* 1394 */         return null;
/*      */       try
/*      */       {
/* 1397 */         Object localObject1 = null;
/* 1398 */         if ((URLUtil.isNetworkUrl(paramString1)) && (this.mAdaWebview.mNeedInjection)) {
/* 1399 */           localObject2 = getUrlFile(paramString1, paramString4);
/* 1400 */           if (localObject2 != null) {
/* 1401 */             localObject3 = new ByteArrayOutputStream();
/* 1402 */             if ((!TextUtils.isEmpty(this.mPlusJS)) && (this.mAdaWebview.mPlusrequire.equals("ahead"))) {
/* 1403 */               this.mAdaWebview.mPlusInjectTag = paramString1;
/* 1404 */               ((ByteArrayOutputStream)localObject3).write(this.mPlusJS.getBytes());
/*      */             }
/* 1406 */             if ((!TextUtils.isEmpty(str)) && (this.mAdaWebview.mPlusrequire.equals("ahead"))) {
/* 1407 */               this.mAdaWebview.mPreloadJsLoading = true;
/* 1408 */               ((ByteArrayOutputStream)localObject3).write(str.getBytes());
/*      */             }
/* 1410 */             if ((this.isInitAmapGEO) && (DLGeolocation.checkInjectGeo(this.mAdaWebview.mInjectGEO)) && (!this.mAdaWebview.mPlusrequire.equals("ahead"))) {
/* 1411 */               Logger.i("shutao", "提前注入GEOJS" + this.mAdaWebview.getOriginalUrl());
/* 1412 */               this.mAdaWebview.mInjectGeoLoaded = true;
/* 1413 */               ((ByteArrayOutputStream)localObject3).write(DLGeolocation.getGEOJS().getBytes());
/*      */             }
/* 1415 */             if (((CatchFile)localObject2).mExist) {
/*      */               try {
/* 1417 */                 FileInputStream localFileInputStream1 = new FileInputStream(((CatchFile)localObject2).mFile);
/* 1418 */                 arrayOfByte = new byte[4096];
/*      */ 
/* 1420 */                 while ((i = localFileInputStream1.read(arrayOfByte)) > 0) {
/* 1421 */                   ((ByteArrayOutputStream)localObject3).write(arrayOfByte, 0, i);
/*      */                 }
/* 1423 */                 localFileInputStream1.close();
/*      */               } catch (IOException localIOException) {
/* 1425 */                 localIOException.printStackTrace();
/*      */               }
/*      */             }
/* 1428 */             if (!TextUtils.isEmpty(((CatchFile)localObject2).mEncoding)) {
/* 1429 */               paramString3 = ((CatchFile)localObject2).mEncoding;
/*      */             }
/* 1431 */             if (!TextUtils.isEmpty(((CatchFile)localObject2).mContentType)) {
/* 1432 */               paramString2 = ((CatchFile)localObject2).mContentType;
/*      */             }
/* 1434 */             localObject1 = new ByteArrayInputStream(((ByteArrayOutputStream)localObject3).toByteArray());
/* 1435 */             if (this.mAdaWebview.mPlusrequire.equals("ahead"))
/* 1436 */               listenPlusInjectTimeout(this.mAdaWebview.mWebViewImpl, this.mAdaWebview.obtainFullUrl(), "inject_with_js");
/*      */           }
/*      */         }
/* 1439 */         else if ("html5plus://ready".equals(paramString1)) {
/* 1440 */           if (!TextUtils.isEmpty(this.mPlusJS))
/*      */           {
/* 1442 */             Logger.i("PLUSREADY", this.mAdaWebview.getOriginalUrl() + ";url=" + paramString1);
/* 1443 */             localObject1 = new ByteArrayInputStream(this.mPlusJS.getBytes());
/*      */           }
/* 1445 */         } else if (PdrUtil.isDeviceRootDir(paramString1)) {
/* 1446 */           localObject1 = new FileInputStream(paramString1);
/*      */         }
/* 1448 */         if (localObject1 != null) {
/* 1449 */           paramWebResourceResponse = new WebResourceResponse(paramString2, paramString3, (InputStream)localObject1);
/*      */         }
/* 1451 */         if (paramWebResourceResponse != null) {
/* 1452 */           this.mAdaWebview.mEncoding = paramString3;
/* 1453 */           Logger.i("shutao", "提前注入JS成功耗时：" + (System.currentTimeMillis() - l) + this.mAdaWebview.getOriginalUrl() + ";url=" + paramString1);
/*      */         }
/*      */       } catch (Exception localException1) {
/* 1456 */         localException1.printStackTrace();
/*      */       }
/* 1458 */     } else if (this.TYPE_CSS.equals(paramString4)) {
/* 1459 */       str = this.mAdaWebview.getCssString();
/* 1460 */       if ((!this.mAdaWebview.mIsAdvanceCss) && (!TextUtils.isEmpty(str))) {
/*      */         try {
/* 1462 */           ByteArrayInputStream localByteArrayInputStream = null;
/* 1463 */           localObject2 = new ByteArrayOutputStream();
/* 1464 */           localObject3 = getUrlFile(paramString1, paramString4);
/* 1465 */           if (localObject3 != null) {
/* 1466 */             if (((CatchFile)localObject3).mExist) {
/* 1467 */               FileInputStream localFileInputStream2 = new FileInputStream(((CatchFile)localObject3).mFile);
/* 1468 */               arrayOfByte = new byte[4096];
/*      */ 
/* 1470 */               while ((i = localFileInputStream2.read(arrayOfByte)) > 0) {
/* 1471 */                 ((ByteArrayOutputStream)localObject2).write(arrayOfByte, 0, i);
/*      */               }
/* 1473 */               localFileInputStream2.close();
/*      */             }
/* 1475 */             ((ByteArrayOutputStream)localObject2).write(str.getBytes());
/*      */           }
/* 1477 */           if (!TextUtils.isEmpty(((CatchFile)localObject3).mEncoding)) {
/* 1478 */             paramString3 = ((CatchFile)localObject3).mEncoding;
/*      */           }
/* 1480 */           paramString2 = "text/css";
/* 1481 */           localByteArrayInputStream = new ByteArrayInputStream(((ByteArrayOutputStream)localObject2).toByteArray());
/* 1482 */           if (localByteArrayInputStream != null) {
/* 1483 */             paramWebResourceResponse = new WebResourceResponse(paramString2, paramString3, localByteArrayInputStream);
/*      */           }
/* 1485 */           if (paramWebResourceResponse != null)
/*      */           {
/* 1487 */             this.mAdaWebview.mIsAdvanceCss = true;
/* 1488 */             this.mAdaWebview.mEncoding = paramString3;
/* 1489 */             Logger.i("shutao", "提前注入CSS成功耗时：" + (System.currentTimeMillis() - l) + this.mAdaWebview.getOriginalUrl() + ";url=" + paramString1);
/*      */           }
/*      */         } catch (Exception localException2) {
/* 1492 */           this.mAdaWebview.mIsAdvanceCss = false;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1497 */     return paramWebResourceResponse;
/*      */   }
/*      */ 
/*      */   class CatchFile
/*      */   {
/* 1294 */     File mFile = null;
/* 1295 */     String mEncoding = null;
/* 1296 */     String mContentType = null;
/* 1297 */     boolean mExist = false;
/*      */ 
/*      */     CatchFile()
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   public static abstract interface OnPageFinishedCallack
/*      */   {
/*      */     public abstract void onLoad();
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.WebLoadEvent
 * JD-Core Version:    0.6.2
 */