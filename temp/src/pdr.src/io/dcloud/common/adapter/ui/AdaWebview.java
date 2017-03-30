/*      */ package io.dcloud.common.adapter.ui;
/*      */ 
/*      */ import android.content.Context;
/*      */ import android.os.Build.VERSION;
/*      */ import android.text.TextUtils;
/*      */ import android.util.Log;
/*      */ import android.view.View;
/*      */ import android.webkit.CookieManager;
/*      */ import android.webkit.WebSettings;
/*      */ import android.webkit.WebView;
/*      */ import android.webkit.WebView.HitTestResult;
/*      */ import io.dcloud.common.DHInterface.IApp;
/*      */ import io.dcloud.common.DHInterface.IApp.ConfigProperty.ThridInfo;
/*      */ import io.dcloud.common.DHInterface.IFrameView;
/*      */ import io.dcloud.common.DHInterface.IJsInterface;
/*      */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*      */ import io.dcloud.common.DHInterface.IWebview;
/*      */ import io.dcloud.common.DHInterface.IWebviewStateListener;
/*      */ import io.dcloud.common.adapter.util.DeviceInfo;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.MessageHandler;
/*      */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*      */ import io.dcloud.common.adapter.util.PlatformUtil;
/*      */ import io.dcloud.common.adapter.util.ViewOptions;
/*      */ import io.dcloud.common.util.BaseInfo;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.lang.reflect.Field;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Locale;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ public abstract class AdaWebview extends AdaContainerFrameItem
/*      */   implements IWebview
/*      */ {
/*   86 */   public static boolean ScreemOrientationChangedNeedLayout = false;
/*   87 */   public static boolean setedWebViewData = false;
/*   88 */   boolean unReceiveTitle = true;
/*   89 */   AdaWebViewParent mWebViewParent = null;
/*      */ 
/*   91 */   String mRecordLastUrl = null;
/*   92 */   WebViewImpl mWebViewImpl = null;
/*   93 */   protected AdaFrameView mFrameView = null;
/*      */ 
/*   95 */   boolean mLoaded = false;
/*      */ 
/*   97 */   boolean mLoadCompleted = false;
/*      */ 
/*   99 */   boolean mPreloadJsLoaded = false;
/*  100 */   boolean mPreloadJsLoading = false;
/*      */ 
/*  102 */   boolean mPlusLoaded = false;
/*      */   String mInjectPlusWidthJs;
/*  105 */   String mPlusInjectTag = "page_finished";
/*  106 */   boolean mIsAdvanceCss = false;
/*      */ 
/*  108 */   public boolean mNeedInjection = true;
/*      */ 
/*  110 */   String mEncoding = null;
/*      */ 
/*  112 */   private String mWebviewUUID = null;
/*      */ 
/*  114 */   private String mFrameId = null;
/*  115 */   IJsInterface mJsInterfaces = null;
/*  116 */   boolean hasErrorPage = false;
/*  117 */   String errorPageUrl = null;
/*  118 */   String originalUrl = null;
/*      */   private int mFixBottomHeight;
/*  120 */   private String mVideoFullscreen = "auto";
/*  121 */   private String needTouchEvent = "";
/*      */ 
/*  123 */   private int mCacheMode = -1;
/*      */ 
/*  128 */   public String mPlusrequire = "normal";
/*  129 */   public String mInjectGEO = "none";
/*  130 */   public boolean mInjectGeoLoaded = false;
/*      */ 
/*  201 */   private Object mFlag = null;
/*      */ 
/*  331 */   String mInjectPlusLoadedUrl = null;
/*      */ 
/*  386 */   String[] mEvalJsOptionStack = null;
/*      */ 
/*  513 */   ArrayList<String> mPreloadJsFile = new ArrayList(2);
/*      */ 
/*  539 */   String mCssString = "";
/*      */ 
/*  713 */   public MessageHandler.IMessages executeScriptListener = new MessageHandler.IMessages()
/*      */   {
/*      */     public void execute(Object paramAnonymousObject) {
/*  716 */       String str = (String)paramAnonymousObject;
/*      */ 
/*  720 */       AdaWebview.this.mWebViewImpl.loadUrl("javascript:" + str);
/*      */     }
/*  713 */   };
/*      */ 
/*  829 */   public MessageHandler.IMessages mMesssageListener = new MessageHandler.IMessages()
/*      */   {
/*      */     public void execute(Object paramAnonymousObject) {
/*  832 */       Object[] arrayOfObject = (Object[])paramAnonymousObject;
/*  833 */       AdaWebview.this.mJsInterfaces.exec(String.valueOf(arrayOfObject[0]), String.valueOf(arrayOfObject[1]), (JSONArray)arrayOfObject[2]);
/*      */     }
/*  829 */   };
/*      */ 
/*  885 */   private ArrayList<IWebviewStateListener> mStateListeners = null;
/*      */ 
/*  899 */   public int mProgress = 0;
/*      */ 
/*  938 */   boolean justClearOption = false;
/*      */ 
/* 1084 */   JSONArray mOverrideResourceRequestOptions = null;
/*      */ 
/* 1138 */   JSONObject mOverrideUrlLoadingDataOptions = null;
/*      */ 
/* 1148 */   JSONObject mListenResourceLoadingOptions = null;
/*      */ 
/*      */   public int getCacheMode()
/*      */   {
/*  126 */     return this.mCacheMode;
/*      */   }
/*      */ 
/*      */   protected AdaWebview(Context paramContext, AdaFrameView paramAdaFrameView)
/*      */   {
/*  133 */     super(paramContext);
/*  134 */     this.mFrameView = paramAdaFrameView;
/*  135 */     Logger.d("AdaWebview");
/*      */     try {
/*  137 */       this.mWebViewImpl = new WebViewImpl(getActivity(), this);
/*      */     } catch (Exception localException) {
/*  139 */       localException.printStackTrace();
/*  140 */       this.mWebViewImpl = new WebViewImpl(getActivity(), this);
/*      */     }
/*  142 */     setWebView(this.mWebViewImpl);
/*  143 */     setScrollIndicator("none");
/*  144 */     this.mWebViewParent = new AdaWebViewParent(paramContext);
/*  145 */     this.mWebViewParent.fillsWithWebview(this);
/*      */     Object localObject;
/*      */     String str1;
/*  146 */     if (paramAdaFrameView.getFrameType() == 2) {
/*  147 */       this.mOverrideUrlLoadingDataOptions = this.mFrameView.obtainApp().obtainThridInfo(IApp.ConfigProperty.ThridInfo.OverrideUrlJsonData);
/*  148 */       localObject = this.mFrameView.obtainApp().obtainThridInfo(IApp.ConfigProperty.ThridInfo.OverrideResourceJsonData);
/*  149 */       if (localObject != null) {
/*  150 */         this.mOverrideResourceRequestOptions = ((JSONObject)localObject).optJSONArray("0");
/*      */       }
/*  152 */       this.mNeedInjection = PdrUtil.parseBoolean(this.mFrameView.obtainApp().obtainConfigProperty("injection"), this.mNeedInjection, false);
/*  153 */       str1 = this.mFrameView.obtainApp().obtainConfigProperty("L_plusrequire");
/*  154 */       if (!TextUtils.isEmpty(str1)) {
/*  155 */         this.mPlusrequire = str1;
/*      */       }
/*  157 */       String str2 = this.mFrameView.obtainApp().obtainConfigProperty("L_geolocation");
/*  158 */       if (!TextUtils.isEmpty(str2)) {
/*  159 */         this.mInjectGEO = str2;
/*      */       }
/*      */ 
/*      */     }
/*  163 */     else if (paramAdaFrameView.getFrameType() == 4) {
/*  164 */       localObject = this.mFrameView.obtainApp().obtainConfigProperty("S_pluserquire");
/*  165 */       if (!TextUtils.isEmpty((CharSequence)localObject)) {
/*  166 */         this.mPlusrequire = ((String)localObject);
/*      */       }
/*  168 */       str1 = this.mFrameView.obtainApp().obtainConfigProperty("S_geolocation");
/*  169 */       if (!TextUtils.isEmpty(str1))
/*  170 */         this.mInjectGEO = str1;
/*      */     }
/*      */   }
/*      */ 
/*      */   String getAppName()
/*      */   {
/*  176 */     String str = this.mFrameView.obtainApp().obtainAppName();
/*  177 */     if ((TextUtils.isEmpty(str)) && (this.mFrameView.getFrameType() == 3)) {
/*  178 */       str = "流应用";
/*      */     }
/*  180 */     return str;
/*      */   }
/*      */   protected AdaWebview(Context paramContext, AdaFrameView paramAdaFrameView, WebLoadEvent.OnPageFinishedCallack paramOnPageFinishedCallack) {
/*  183 */     super(paramContext);
/*  184 */     this.mFrameView = paramAdaFrameView;
/*  185 */     Logger.d("AdaWebview");
/*      */     try {
/*  187 */       this.mWebViewImpl = new WebViewImpl(getActivity(), this, paramOnPageFinishedCallack);
/*      */     } catch (Exception localException) {
/*  189 */       localException.printStackTrace();
/*  190 */       this.mWebViewImpl = new WebViewImpl(getActivity(), this, paramOnPageFinishedCallack);
/*      */     }
/*  192 */     setWebView(this.mWebViewImpl);
/*  193 */     setScrollIndicator("none");
/*  194 */     this.mWebViewParent = new AdaWebViewParent(paramContext);
/*  195 */     this.mWebViewParent.fillsWithWebview(this);
/*      */   }
/*      */ 
/*      */   public void init() {
/*  199 */     this.mWebViewImpl.init();
/*      */   }
/*      */ 
/*      */   public void setFlag(Object paramObject)
/*      */   {
/*  204 */     this.mFlag = paramObject;
/*      */   }
/*      */ 
/*      */   public Object getFlag()
/*      */   {
/*  209 */     return this.mFlag;
/*      */   }
/*      */ 
/*      */   public void loadContentData(String paramString1, String paramString2, String paramString3) {
/*  213 */     this.mWebViewImpl.loadDataWithBaseURL("", paramString1, paramString2, paramString3, "");
/*      */   }
/*      */ 
/*      */   public void setFrameId(String paramString)
/*      */   {
/*  218 */     this.mFrameId = paramString;
/*      */   }
/*      */ 
/*      */   public String obtainFrameId() {
/*  222 */     return this.mFrameId;
/*      */   }
/*      */ 
/*      */   public final void initWebviewUUID(String paramString) {
/*  226 */     this.mWebviewUUID = paramString;
/*      */   }
/*      */ 
/*      */   public final String getWebviewUUID() {
/*  230 */     return this.mWebviewUUID;
/*      */   }
/*      */ 
/*      */   public WebView obtainWebview() {
/*  234 */     return this.mWebViewImpl;
/*      */   }
/*      */ 
/*      */   public void reload() {
/*  238 */     if (!PdrUtil.isEmpty(this.mWebViewImpl.mUrl)) {
/*  239 */       removeAllFrameItem();
/*      */ 
/*  245 */       this.mWebViewImpl.reload();
/*  246 */       Logger.d("webview", "reload url=" + this.mWebViewImpl.convertRelPath(this.mWebViewImpl.mUrl));
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setFixBottom(int paramInt) {
/*  251 */     this.mFixBottomHeight = paramInt;
/*      */   }
/*      */ 
/*      */   public int getFixBottom()
/*      */   {
/*  256 */     return this.mFixBottomHeight;
/*      */   }
/*      */ 
/*      */   public void reload(String paramString) {
/*  260 */     removeAllFrameItem();
/*  261 */     Logger.d("webview", "reload loadUrl url=" + paramString);
/*  262 */     this.mLoaded = false;
/*  263 */     this.mWebViewImpl.mUrl = paramString;
/*  264 */     this.mWebViewImpl.loadUrl(paramString);
/*      */   }
/*      */ 
/*      */   public void loadUrl(String paramString) {
/*  268 */     if ((PdrUtil.isEmpty(this.mWebViewImpl.mUrl)) && (paramString != null) && (!paramString.startsWith("javascript:"))) {
/*  269 */       this.mWebViewImpl.mUrl = paramString;
/*      */     }
/*  271 */     this.mWebViewImpl.loadUrl(paramString);
/*      */   }
/*      */ 
/*      */   public String obtainUrl()
/*      */   {
/*  276 */     if (this.mWebViewImpl.mUrl != null) {
/*  277 */       int i = this.mWebViewImpl.mUrl.indexOf(this.mWebViewImpl.mBaseUrl);
/*  278 */       String str = this.mWebViewImpl.mUrl;
/*  279 */       if (i >= 0) {
/*  280 */         str = str.substring(this.mWebViewImpl.mBaseUrl.length());
/*      */       }
/*  282 */       return str;
/*      */     }
/*  284 */     return "";
/*      */   }
/*      */ 
/*      */   public String syncUpdateWebViewData(String paramString)
/*      */   {
/*  289 */     if (Build.VERSION.SDK_INT <= 19) return "";
/*      */ 
/*  291 */     StringBuffer localStringBuffer = new StringBuffer();
/*      */ 
/*  293 */     String str = getWebviewUUID();
/*  294 */     if (PdrUtil.isEmpty(str)) {
/*  295 */       str = String.valueOf(this.mFrameView.hashCode());
/*      */     }
/*  297 */     localStringBuffer.append("window.__HtMl_Id__= '" + str + "';");
/*  298 */     if (PdrUtil.isEmpty(obtainFrameId()))
/*  299 */       localStringBuffer.append("window.__WebVieW_Id__= undefined;");
/*      */     else {
/*  301 */       localStringBuffer.append("window.__WebVieW_Id__= '" + obtainFrameId() + "';");
/*      */     }
/*      */ 
/*  304 */     Logger.e("WebViewData", "syncUpdateWebViewData url=" + this.mRecordLastUrl);
/*  305 */     localStringBuffer.append("try{window.plus.__tag__='" + this.mPlusInjectTag + "';location.__plusready__=true;/*console.log(location);window.plus.__url__='" + paramString + "';*/}catch(e){console.log(e)}");
/*  306 */     return "javascript:" + localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   void resetPlusLoadSaveData() {
/*  310 */     Logger.e("WebViewData", "resetPlusLoadSaveData url=" + this.mWebViewImpl.getUrl());
/*  311 */     this.mPlusLoaded = false;
/*  312 */     this.mPreloadJsLoaded = false;
/*  313 */     this.mPreloadJsLoading = false;
/*  314 */     this.mInjectPlusWidthJs = null;
/*  315 */     this.mLoaded = false;
/*  316 */     this.mIsAdvanceCss = false;
/*  317 */     this.mInjectGeoLoaded = false;
/*      */   }
/*      */ 
/*      */   public void saveWebViewData(String paramString) {
/*  321 */     this.mWebViewImpl.mUrl = paramString;
/*  322 */     Logger.i("WebViewData", "saveWebViewData url=" + paramString);
/*  323 */     this.mWebViewImpl.mScale = this.mWebViewImpl.getScale();
/*  324 */     this.mPlusLoaded = true;
/*  325 */     this.mInjectPlusLoadedUrl = paramString;
/*  326 */     this.mPreloadJsLoaded = this.mPreloadJsLoading;
/*  327 */     this.mWebViewImpl.mWebLoadEvent.onUpdatePlusData(this.mWebViewImpl, paramString, "saveWebViewData");
/*  328 */     this.mWebViewImpl.mWebLoadEvent.listenPageFinishTimeout(this.mWebViewImpl, paramString, "saveWebViewData");
/*      */   }
/*      */ 
/*      */   boolean isRealInject(String paramString)
/*      */   {
/*  333 */     String str = this.mInjectPlusLoadedUrl;
/*  334 */     paramString = PdrUtil.getUrlPathName(paramString);
/*  335 */     str = PdrUtil.getUrlPathName(str);
/*  336 */     return (this.mPlusLoaded) && (TextUtils.equals(paramString, str));
/*      */   }
/*      */ 
/*      */   String getScreenAndDisplayJson(IWebview paramIWebview) {
/*  340 */     String str = "(function(p){p.screen.scale=%f;p.screen.resolutionHeight=%d;p.screen.resolutionWidth=%d;p.screen.dpiX=%f;p.screen.dpiY=%f;p.display.resolutionHeight=%d;p.display.resolutionWidth=%d;})(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus));";
/*      */ 
/*  349 */     float f = paramIWebview.getScale();
/*  350 */     IApp localIApp = paramIWebview.obtainApp();
/*  351 */     int i = localIApp.getInt(2);
/*  352 */     int j = localIApp.getInt(0);
/*  353 */     int k = localIApp.getInt(1);
/*  354 */     return String.format(Locale.ENGLISH, str, new Object[] { Float.valueOf(f), Integer.valueOf((int)(i / f)), Integer.valueOf((int)(j / f)), Float.valueOf(DeviceInfo.dpiX), Float.valueOf(DeviceInfo.dpiY), Integer.valueOf((int)(k / f)), Integer.valueOf((int)(j / f)) });
/*      */   }
/*      */ 
/*      */   public String obtainFullUrl()
/*      */   {
/*  360 */     if ((Build.VERSION.SDK_INT >= 19) && (!TextUtils.isEmpty(this.mWebViewImpl.mUrl))) {
/*  361 */       return this.mWebViewImpl.mUrl;
/*      */     }
/*  363 */     return this.mWebViewImpl.getUrl();
/*      */   }
/*      */ 
/*      */   public IFrameView obtainFrameView() {
/*  367 */     return this.mFrameView;
/*      */   }
/*      */ 
/*      */   public void evalJS(String paramString) {
/*  371 */     if (this.mPlusLoaded)
/*      */     {
/*  373 */       executeScript(paramString);
/*      */     }
/*  375 */     else pushEvalJsOption(paramString);
/*      */   }
/*      */ 
/*      */   public void evalJS(String paramString, ReceiveJSValue.ReceiveJSValueCallback paramReceiveJSValueCallback)
/*      */   {
/*  380 */     if (paramReceiveJSValueCallback != null) {
/*  381 */       paramString = ReceiveJSValue.registerCallback(paramString, paramReceiveJSValueCallback);
/*      */     }
/*  383 */     evalJS(paramString);
/*      */   }
/*      */ 
/*      */   private void pushEvalJsOption(String paramString)
/*      */   {
/*  393 */     if (this.mEvalJsOptionStack == null) {
/*  394 */       this.mEvalJsOptionStack = new String[1];
/*      */     } else {
/*  396 */       String[] arrayOfString = this.mEvalJsOptionStack;
/*  397 */       this.mEvalJsOptionStack = new String[arrayOfString.length + 1];
/*  398 */       System.arraycopy(arrayOfString, 0, this.mEvalJsOptionStack, 0, arrayOfString.length);
/*      */     }
/*  400 */     this.mEvalJsOptionStack[(this.mEvalJsOptionStack.length - 1)] = paramString;
/*  401 */     Logger.d("adawebview", "webviewimp=(" + this.mWebViewImpl + ");pushEvalJs=" + paramString);
/*      */   }
/*      */ 
/*      */   void execute_eval_js_stack()
/*      */   {
/*  409 */     if (this.mEvalJsOptionStack != null) {
/*  410 */       Logger.d("adawebview", "webviewimp=" + this.mWebViewImpl + ";execute_eval_js_stack count=" + this.mEvalJsOptionStack.length);
/*  411 */       for (int i = 0; i < this.mEvalJsOptionStack.length; i++) {
/*  412 */         executeScript(this.mEvalJsOptionStack[i]);
/*      */       }
/*  414 */       this.mEvalJsOptionStack = null;
/*      */     }
/*      */   }
/*      */ 
/*      */   String get_eval_js_stack()
/*      */   {
/*  423 */     StringBuffer localStringBuffer = new StringBuffer();
/*  424 */     if (this.mEvalJsOptionStack != null) {
/*  425 */       for (int i = 0; i < this.mEvalJsOptionStack.length; i++) {
/*  426 */         String str = this.mEvalJsOptionStack[i];
/*  427 */         if (str.endsWith(";"))
/*  428 */           localStringBuffer.append(str);
/*      */         else {
/*  430 */           localStringBuffer.append(str).append(";");
/*      */         }
/*      */       }
/*  433 */       this.mEvalJsOptionStack = null;
/*      */     }
/*  435 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   public void dispose() {
/*  439 */     super.dispose();
/*  440 */     BaseInfo.s_Webview_Count -= 1;
/*      */     try {
/*  442 */       this.mWebViewImpl.stopLoading();
/*      */     } catch (Exception localException) {
/*      */     }
/*  445 */     MessageHandler.sendMessage(new MessageHandler.IMessages()
/*      */     {
/*      */       public void execute(Object paramAnonymousObject) {
/*      */         try {
/*  449 */           AdaWebview.this.mWebViewImpl.clearCache(false);
/*      */         } catch (Exception localException1) {
/*      */         }
/*  452 */         AdaWebview.this.mWebViewImpl.setVisibility(8);
/*      */         try
/*      */         {
/*  455 */           AdaWebview.this.mWebViewImpl.destroy();
/*      */         }
/*      */         catch (Exception localException2) {
/*      */         }
/*  459 */         AdaWebview.this.releaseConfigCallback();
/*      */         try {
/*  461 */           AdaWebview.this.mWebViewImpl.destroyDrawingCache();
/*  462 */           AdaWebview.this.mWebViewImpl.clearDisappearingChildren();
/*      */         } catch (Exception localException3) {
/*  464 */           localException3.printStackTrace();
/*      */         }
/*  466 */         System.gc();
/*      */       }
/*      */     }
/*      */     , null);
/*      */   }
/*      */ 
/*      */   private void releaseConfigCallback()
/*      */   {
/*  472 */     if (Build.VERSION.SDK_INT < 16)
/*      */       try {
/*  474 */         Field localField1 = WebView.class.getDeclaredField("mWebViewCore");
/*  475 */         localField1 = localField1.getType().getDeclaredField("mBrowserFrame");
/*  476 */         localField1 = localField1.getType().getDeclaredField("sConfigCallback");
/*  477 */         localField1.setAccessible(true);
/*  478 */         localField1.set(null, null);
/*      */       }
/*      */       catch (NoSuchFieldException localNoSuchFieldException1) {
/*      */       }
/*      */       catch (IllegalAccessException localIllegalAccessException1) {
/*      */       }
/*      */     else
/*      */       try {
/*  486 */         Field localField2 = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
/*  487 */         if (localField2 != null) {
/*  488 */           localField2.setAccessible(true);
/*  489 */           localField2.set(null, null);
/*      */         }
/*      */       }
/*      */       catch (NoSuchFieldException localNoSuchFieldException2) {
/*      */       }
/*      */       catch (ClassNotFoundException localClassNotFoundException) {
/*      */       }
/*      */       catch (IllegalAccessException localIllegalAccessException2) {
/*      */       }
/*      */   }
/*      */ 
/*      */   public boolean onDispose() {
/*  501 */     boolean bool = super.onDispose();
/*      */ 
/*  503 */     return bool;
/*      */   }
/*      */ 
/*      */   public void executeScript(String paramString) {
/*  507 */     if (paramString != null)
/*      */     {
/*  509 */       MessageHandler.sendMessage(this.executeScriptListener, paramString);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void appendPreloadJsFile(String paramString)
/*      */   {
/*  516 */     this.mPreloadJsFile.add(paramString);
/*  517 */     Logger.d("AdaWebview", "appendPreloadJsFile mPreloadJsFile=" + this.mPreloadJsFile);
/*  518 */     if (this.mPlusLoaded) {
/*  519 */       Log.d("AdaWebview", "appendPreloadJsFile---=" + paramString);
/*  520 */       int i = this.mFrameView.obtainApp().obtainRunningAppMode() == 1 ? 0 : 2;
/*  521 */       String str = loadPreLoadJsFile(paramString, i);
/*  522 */       if (!TextUtils.isEmpty(str))
/*  523 */         loadUrl("javascript:" + str + ";");
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setPreloadJsFile(String paramString)
/*      */   {
/*  530 */     this.mPreloadJsFile.clear();
/*  531 */     this.mPreloadJsFile.add(paramString);
/*  532 */     Logger.d("AdaWebview", "setPreloadJsFile mPreloadJsFile=" + this.mPreloadJsFile);
/*  533 */     if (this.mPlusLoaded) {
/*  534 */       Log.d("AdaWebview", "setPreloadJsFile---=" + paramString);
/*  535 */       loadPreLoadJsFile(null);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setCssFile(String paramString1, String paramString2)
/*      */   {
/*  542 */     if (!PdrUtil.isEmpty(paramString1)) {
/*  543 */       IApp localIApp = this.mFrameView.obtainApp();
/*  544 */       int i = localIApp.obtainRunningAppMode() == 1 ? 0 : 2;
/*  545 */       this.mCssString = loadPreLoadJsFile(paramString1, i);
/*      */     } else {
/*  547 */       this.mCssString = paramString2;
/*      */     }
/*      */   }
/*      */ 
/*      */   public String getCssString() {
/*  552 */     return this.mCssString;
/*      */   }
/*      */ 
/*      */   void directLoadPreLoadJsFile()
/*      */   {
/*  560 */     if ((BaseInfo.isQihooLifeHelper(getContext())) && 
/*  561 */       (!PdrUtil.isEmpty(this.mPreloadJsFile)))
/*      */       try {
/*  563 */         IApp localIApp = this.mFrameView.obtainApp();
/*  564 */         if (localIApp != null) {
/*  565 */           i = localIApp.obtainRunningAppMode() == 1 ? 0 : 2;
/*  566 */           for (String str1 : this.mPreloadJsFile)
/*  567 */             if (str1.endsWith("www/js/cp.min.js")) {
/*  568 */               String str2 = loadPreLoadJsFile(str1, i);
/*  569 */               if (!TextUtils.isEmpty(str2))
/*  570 */                 loadUrl("javascript:" + str2 + ";");
/*      */             }
/*      */         }
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */         int i;
/*  576 */         localException.printStackTrace();
/*      */       }
/*      */   }
/*      */ 
/*      */   void loadPreLoadJsFile(IFExecutePreloadJSContentCallBack paramIFExecutePreloadJSContentCallBack)
/*      */   {
/*  585 */     if (!PdrUtil.isEmpty(this.mPreloadJsFile))
/*      */       try {
/*  587 */         IApp localIApp = this.mFrameView.obtainApp();
/*  588 */         if (localIApp != null) {
/*  589 */           i = localIApp.obtainRunningAppMode() == 1 ? 0 : 2;
/*  590 */           for (String str1 : this.mPreloadJsFile) {
/*  591 */             String str2 = loadPreLoadJsFile(str1, i);
/*  592 */             if (!TextUtils.isEmpty(str2)) {
/*  593 */               String str3 = "javascript:" + str2 + ";";
/*  594 */               if (paramIFExecutePreloadJSContentCallBack == null)
/*  595 */                 loadUrl(str3);
/*      */               else
/*  597 */                 paramIFExecutePreloadJSContentCallBack.callback(this.mWebViewImpl.convertRelPath(str1), str3);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */         int i;
/*  603 */         localException.printStackTrace();
/*      */       }
/*      */   }
/*      */ 
/*      */   boolean loadCssFile()
/*      */   {
/*  610 */     if (!PdrUtil.isEmpty(this.mCssString))
/*      */     {
/*  612 */       this.mCssString = this.mCssString.replaceAll("\"", "'");
/*  613 */       String str1 = checkRedCssline(this.mCssString);
/*  614 */       String str2 = "javascript:var container = document.getElementsByTagName('head')[0];\nvar addStyle = document.createElement('style');\naddStyle.rel = 'stylesheet';\naddStyle.type = 'text/css';\naddStyle.innerHTML = " + str1 + ";\n" + "container.appendChild(addStyle);\n" + "firstNode = container.children[0];\n" + "    container.appendChild(addStyle);\n";
/*      */ 
/*  628 */       loadUrl(str2);
/*  629 */       return true;
/*      */     }
/*  631 */     return false;
/*      */   }
/*      */ 
/*      */   void loadPlusApiFile() {
/*  635 */     String str = "javascript:(function(){var container = document.getElementsByTagName('head')[0];\nvar script = document.createElement('script');\nscript.type = 'text/javascript';\nscript.src = 'html5plus://ready';\ncontainer.appendChild(script);\nfirstNode = container.children[0];\nif(firstNode == null || firstNode==undefined)\n{    container.appendChild(script);}\nelse{\n\tcontainer.insertBefore(script,container.children[0]);\n}})();";
/*      */ 
/*  646 */     loadUrl(str);
/*      */   }
/*      */ 
/*      */   private String checkRedCssline(String paramString)
/*      */   {
/*  655 */     ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramString.getBytes());
/*  656 */     StringBuffer localStringBuffer = new StringBuffer();
/*  657 */     String str1 = null;
/*  658 */     if (localByteArrayInputStream != null) {
/*  659 */       BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localByteArrayInputStream));
/*  660 */       String str2 = "";
/*      */       try {
/*  662 */         while ((str2 = localBufferedReader.readLine()) != null) {
/*  663 */           str2 = "\"" + str2 + "\"\n+";
/*  664 */           localStringBuffer.append(str2);
/*      */         }
/*  666 */         str1 = localStringBuffer.substring(0, localStringBuffer.length() - 1);
/*      */       } catch (IOException localIOException) {
/*  668 */         localIOException.printStackTrace();
/*      */       }
/*      */     }
/*  671 */     return str1;
/*      */   }
/*      */ 
/*      */   String getPreLoadJsString()
/*      */   {
/*  679 */     String str1 = "";
/*  680 */     if (!PdrUtil.isEmpty(this.mPreloadJsFile)) {
/*  681 */       IApp localIApp = this.mFrameView.obtainApp();
/*  682 */       if ((localIApp != null) && (this.mPreloadJsFile.size() > 0)) {
/*  683 */         str1 = ";";
/*  684 */         int i = localIApp.obtainRunningAppMode() == 1 ? 0 : 2;
/*  685 */         for (String str2 : this.mPreloadJsFile)
/*      */         {
/*  687 */           if ((!this.mPlusrequire.equals("none")) || ((!str2.contains("__wap2app.js")) && (!str2.contains("__wap2appconfig.js"))))
/*      */           {
/*  690 */             String str3 = loadPreLoadJsFile(str2, i);
/*  691 */             if (!TextUtils.isEmpty(str3))
/*  692 */               str1 = str1 + str3 + "\n";
/*      */           }
/*      */         }
/*  695 */         str1 = str1 + "\n";
/*      */       }
/*      */     }
/*  698 */     return str1;
/*      */   }
/*      */ 
/*      */   boolean hasPreLoadJsFile() {
/*  702 */     return this.mPreloadJsFile.size() > 0;
/*      */   }
/*      */ 
/*      */   private String loadPreLoadJsFile(String paramString, int paramInt) {
/*  706 */     StringBuffer localStringBuffer = new StringBuffer();
/*  707 */     byte[] arrayOfByte = PlatformUtil.getFileContent(paramString, paramInt);
/*  708 */     if (arrayOfByte != null) {
/*  709 */       localStringBuffer.append(new String(arrayOfByte));
/*      */     }
/*  711 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   public void addFrameItem(AdaFrameItem paramAdaFrameItem)
/*      */   {
/*  727 */     super.addFrameItem(paramAdaFrameItem);
/*      */   }
/*      */ 
/*      */   public void addJsInterface(String paramString1, String paramString2) {
/*  731 */     this.mWebViewImpl.addJavascriptInterface(paramString2, paramString1);
/*      */   }
/*      */ 
/*      */   public void addJsInterface(String paramString, Object paramObject) {
/*  735 */     this.mWebViewImpl.addJavascriptInterface(paramObject, paramString);
/*      */   }
/*      */ 
/*      */   public static IJsInterface[] combineObj2Array(IJsInterface[] paramArrayOfIJsInterface, IJsInterface paramIJsInterface) {
/*  739 */     if (paramArrayOfIJsInterface == null) {
/*  740 */       paramArrayOfIJsInterface = new IJsInterface[1];
/*      */     } else {
/*  742 */       int i = paramArrayOfIJsInterface.length;
/*  743 */       IJsInterface[] arrayOfIJsInterface = new IJsInterface[i + 1];
/*  744 */       System.arraycopy(paramArrayOfIJsInterface, 0, arrayOfIJsInterface, 0, i);
/*  745 */       paramArrayOfIJsInterface = arrayOfIJsInterface;
/*      */     }
/*  747 */     paramArrayOfIJsInterface[paramArrayOfIJsInterface.length] = paramIJsInterface;
/*  748 */     return paramArrayOfIJsInterface;
/*      */   }
/*      */ 
/*      */   public void addJsInterface(String paramString, IJsInterface paramIJsInterface)
/*      */   {
/*  753 */     if (Build.VERSION.SDK_INT > 17) {
/*  754 */       this.mWebViewImpl.addJavascriptInterface(paramIJsInterface, paramString);
/*      */     }
/*  756 */     setJsInterface(paramIJsInterface);
/*      */   }
/*      */ 
/*      */   public void setJsInterface(IJsInterface paramIJsInterface) {
/*  760 */     if (this.mJsInterfaces == null) this.mJsInterfaces = paramIJsInterface;
/*      */   }
/*      */ 
/*      */   public String execScript(String paramString1, String paramString2, JSONArray paramJSONArray, boolean paramBoolean)
/*      */   {
/*  782 */     if (paramBoolean)
/*      */     {
/*  790 */       MessageHandler.sendMessage(this.mMesssageListener, new Object[] { paramString1, paramString2, paramJSONArray });
/*      */ 
/*  792 */       return null;
/*      */     }
/*  794 */     return this.mJsInterfaces.exec(paramString1, paramString2, paramJSONArray);
/*      */   }
/*      */ 
/*      */   public boolean backOrForward(int paramInt)
/*      */   {
/*  839 */     return this.mWebViewImpl.canGoBackOrForward(paramInt);
/*      */   }
/*      */   public String toString() {
/*      */     try {
/*  843 */       return "<UUID=" + this.mWebviewUUID + ">;" + (obtainMainView() != null ? obtainMainView().toString() : "view = null");
/*      */     } catch (Exception localException) {
/*  845 */       localException.printStackTrace();
/*      */     }
/*  847 */     return super.toString();
/*      */   }
/*      */ 
/*      */   public void setWebViewEvent(String paramString, Object paramObject)
/*      */   {
/*      */     JSONObject localJSONObject;
/*  852 */     if (PdrUtil.isEquals(paramString, "pull_down_refresh"))
/*      */     {
/*  854 */       localJSONObject = (JSONObject)paramObject;
/*  855 */       this.mWebViewParent.parsePullToReFresh(localJSONObject);
/*  856 */     } else if (PdrUtil.isEquals(paramString, "pull_down_refresh_begin")) {
/*  857 */       this.mWebViewParent.beginPullRefresh();
/*  858 */     } else if (PdrUtil.isEquals(paramString, "bounce_register")) {
/*  859 */       localJSONObject = (JSONObject)paramObject;
/*  860 */       this.mWebViewParent.parseBounce(localJSONObject);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void endWebViewEvent(String paramString)
/*      */   {
/*  866 */     if (PdrUtil.isEquals(paramString, "pull_down_refresh"))
/*  867 */       this.mWebViewParent.endPullRefresh();
/*  868 */     else if (PdrUtil.isEquals(paramString, "bounce_register"))
/*  869 */       this.mWebViewParent.resetBounce();
/*      */   }
/*      */ 
/*      */   protected AdaWebViewParent getWebviewParent()
/*      */   {
/*  874 */     return this.mWebViewParent;
/*      */   }
/*      */ 
/*      */   public float getScale()
/*      */   {
/*  879 */     if ((this.mWebViewImpl.mScale > 0.0F) && (Build.VERSION.SDK_INT >= 19)) {
/*  880 */       return this.mWebViewImpl.mScale;
/*      */     }
/*  882 */     return this.mWebViewImpl.getScale();
/*      */   }
/*      */ 
/*      */   public void addStateListener(IWebviewStateListener paramIWebviewStateListener)
/*      */   {
/*  888 */     if (this.mStateListeners == null) this.mStateListeners = new ArrayList();
/*  889 */     if (paramIWebviewStateListener != null) this.mStateListeners.add(paramIWebviewStateListener);
/*      */   }
/*      */ 
/*      */   public void removeStateListener(IWebviewStateListener paramIWebviewStateListener)
/*      */   {
/*  894 */     if (this.mStateListeners != null)
/*  895 */       this.mStateListeners.remove(paramIWebviewStateListener);
/*      */   }
/*      */ 
/*      */   protected void dispatchWebviewStateEvent(int paramInt, Object paramObject)
/*      */   {
/*  903 */     if (3 == paramInt) {
/*  904 */       this.mProgress = Integer.parseInt(String.valueOf(paramObject));
/*      */     }
/*  906 */     if (this.mStateListeners != null)
/*  907 */       for (int i = this.mStateListeners.size() - 1; i >= 0; i--) {
/*  908 */         IWebviewStateListener localIWebviewStateListener = (IWebviewStateListener)this.mStateListeners.get(i);
/*  909 */         localIWebviewStateListener.onCallBack(paramInt, paramObject);
/*      */       }
/*      */   }
/*      */ 
/*      */   public boolean canGoBack()
/*      */   {
/*  918 */     boolean bool = (!this.justClearOption) && (this.mWebViewImpl.canGoBack());
/*  919 */     Logger.d("AdaFrameItem", "canGoBack" + this.mWebViewImpl.mUrl + ";" + this.justClearOption + ";" + bool);
/*  920 */     return bool;
/*      */   }
/*      */ 
/*      */   public void goBackOrForward(int paramInt) {
/*  924 */     this.mWebViewImpl.goBackOrForward(paramInt);
/*      */   }
/*      */ 
/*      */   public boolean canGoForward()
/*      */   {
/*  930 */     return (!this.justClearOption) && (this.mWebViewImpl.canGoForward());
/*      */   }
/*      */ 
/*      */   public void stopLoading()
/*      */   {
/*  935 */     this.mWebViewImpl.stopLoading();
/*      */   }
/*      */ 
/*      */   public void clearHistory()
/*      */   {
/*  941 */     Logger.d("AdaFrameItem", "clearHistory url=" + this.mWebViewImpl.mUrl);
/*  942 */     this.justClearOption = true;
/*  943 */     this.mWebViewImpl.loadData("<html><head><meta charset=\"utf-8\"></head><body></body><html>", "text/html", "utf-8");
/*  944 */     this.mWebViewImpl.mUrl = "";
/*      */   }
/*      */ 
/*      */   boolean hadClearHistory(String paramString)
/*      */   {
/*  952 */     return (this.justClearOption) && (PdrUtil.isEquals(paramString, "data:text/html,<html><head><meta charset=\"utf-8\"></head><body></body><html>"));
/*      */   }
/*      */ 
/*      */   void onPageStarted() {
/*  956 */     if (this.mWebViewImpl != null)
/*  957 */       this.mWebViewImpl.onPageStarted();
/*      */   }
/*      */ 
/*      */   public boolean checkWhite(String paramString)
/*      */   {
/*  963 */     if (this.mWebViewImpl != null) {
/*  964 */       return this.mWebViewImpl.checkWhite(paramString);
/*      */     }
/*  966 */     return false;
/*      */   }
/*      */ 
/*      */   public void reload(boolean paramBoolean)
/*      */   {
/*  971 */     if (paramBoolean)
/*  972 */       this.mWebViewImpl.webSettings.setCacheMode(2);
/*      */     else {
/*  974 */       this.mWebViewImpl.webSettings.setCacheMode(this.mCacheMode);
/*      */     }
/*  976 */     reload();
/*      */   }
/*      */ 
/*      */   public String obtainPageTitle()
/*      */   {
/*  981 */     String str = this.mWebViewImpl.getTitle();
/*  982 */     return TextUtils.isEmpty(str) ? this.mWebViewImpl.mPageTitle : str;
/*      */   }
/*      */ 
/*      */   public String getWebviewProperty(String paramString)
/*      */   {
/*  987 */     if ("needTouchEvent".equals(paramString)) {
/*  988 */       return String.valueOf(this.needTouchEvent);
/*      */     }
/*  990 */     if ("User-Agent".equals(paramString))
/*  991 */       return this.mWebViewImpl.webSettings.getUserAgentString();
/*  992 */     if ("videoFullscreen".equals(paramString))
/*  993 */       return this.mVideoFullscreen;
/*  994 */     if ("plusrequire".equals(paramString)) {
/*  995 */       return this.mPlusrequire;
/*      */     }
/*  997 */     if (this.mWebViewImpl.cm != null) {
/*  998 */       return this.mWebViewImpl.cm.getCookie(paramString);
/*      */     }
/*      */ 
/* 1001 */     return null;
/*      */   }
/*      */ 
/*      */   public void setWebviewProperty(String paramString1, String paramString2) {
/* 1005 */     if ("needTouchEvent".equals(paramString1)) {
/* 1006 */       if (!TextUtils.isEmpty(paramString2))
/* 1007 */         this.needTouchEvent = paramString2;
/*      */       else
/* 1009 */         this.needTouchEvent = "";
/*      */     }
/*      */     else
/*      */     {
/*      */       boolean bool;
/* 1012 */       if ("scalable".equals(paramString1)) {
/* 1013 */         bool = PdrUtil.parseBoolean(paramString2, this.mFrameView.obtainFrameOptions().scalable, false);
/* 1014 */         this.mWebViewImpl.webSettings.supportZoom();
/* 1015 */         this.mWebViewImpl.webSettings.setBuiltInZoomControls(bool);
/* 1016 */         this.mWebViewImpl.webSettings.setSupportZoom(bool);
/* 1017 */         this.mWebViewImpl.webSettings.setUseWideViewPort(bool);
/* 1018 */       } else if ("User-Agent".equals(paramString1)) {
/* 1019 */         bool = Boolean.parseBoolean(this.mFrameView.obtainApp().obtainConfigProperty("h5plus"));
/* 1020 */         if ((bool) && (paramString2.indexOf(" Html5Plus/") < 0)) {
/* 1021 */           paramString2 = paramString2 + " Html5Plus/1.0";
/*      */         }
/* 1023 */         WebViewImpl.sCustomUserAgent = paramString2;
/* 1024 */         this.mWebViewImpl.webSettings.setUserAgentString(paramString2);
/* 1025 */       } else if ("blockNetworkImage".equals(paramString1)) {
/* 1026 */         bool = PdrUtil.parseBoolean(paramString2, false, false);
/* 1027 */         this.mWebViewImpl.webSettings.setBlockNetworkImage(bool);
/* 1028 */       } else if ("injection".equals(paramString1)) {
/* 1029 */         this.mNeedInjection = PdrUtil.parseBoolean(paramString2, true, false);
/* 1030 */       } else if ("bounce".equals(paramString1)) {
/* 1031 */         if (DeviceInfo.sDeviceSdkVer >= 9) {
/* 1032 */           if (("vertical".equalsIgnoreCase(paramString2)) || ("horizontal".equalsIgnoreCase(paramString2)) || ("all".equalsIgnoreCase(paramString2)))
/* 1033 */             this.mWebViewImpl.setOverScrollMode(0);
/*      */           else
/* 1035 */             this.mWebViewImpl.setOverScrollMode(2);
/*      */         }
/*      */       }
/* 1038 */       else if ("videoFullscreen".equals(paramString1)) {
/* 1039 */         if (!TextUtils.isEmpty(paramString2))
/* 1040 */           this.mVideoFullscreen = paramString2;
/*      */       }
/* 1042 */       else if ("plusrequire".equals(paramString1)) {
/* 1043 */         if (!TextUtils.isEmpty(paramString2))
/* 1044 */           this.mPlusrequire = paramString2;
/*      */       }
/* 1046 */       else if ("geolocation".equals(paramString1)) {
/* 1047 */         if (!TextUtils.isEmpty(paramString2)) {
/* 1048 */           this.mInjectGEO = paramString2;
/*      */         }
/*      */       }
/* 1051 */       else if (this.mWebViewImpl.cm != null) {
/* 1052 */         this.mWebViewImpl.cm.setCookie(paramString1, paramString2);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setScrollIndicator(String paramString) {
/* 1058 */     if (PdrUtil.isEquals(paramString, "none")) {
/* 1059 */       this.mWebViewImpl.setHorizontalScrollBarEnabled(false);
/* 1060 */       this.mWebViewImpl.setVerticalScrollBarEnabled(false);
/* 1061 */     } else if (PdrUtil.isEquals(paramString, "vertical")) {
/* 1062 */       this.mWebViewImpl.setHorizontalScrollBarEnabled(false);
/* 1063 */       this.mWebViewImpl.setVerticalScrollBarEnabled(true);
/* 1064 */     } else if (PdrUtil.isEquals(paramString, "horizontal")) {
/* 1065 */       this.mWebViewImpl.setHorizontalScrollBarEnabled(true);
/* 1066 */       this.mWebViewImpl.setVerticalScrollBarEnabled(false);
/*      */     } else {
/* 1068 */       this.mWebViewImpl.setHorizontalScrollBarEnabled(true);
/* 1069 */       this.mWebViewImpl.setVerticalScrollBarEnabled(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onRootViewGlobalLayout(View paramView)
/*      */   {
/*      */     try {
/* 1076 */       IApp localIApp = obtainApp();
/* 1077 */       if (localIApp != null)
/* 1078 */         localIApp.obtainWebAppRootView().onRootViewGlobalLayout(paramView);
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setOverrideResourceRequest(JSONArray paramJSONArray)
/*      */   {
/* 1087 */     this.mOverrideResourceRequestOptions = paramJSONArray;
/*      */   }
/*      */ 
/*      */   OverrideResourceRequestItem checkResourceRequestUrl(String paramString)
/*      */   {
/* 1096 */     if ((this.mOverrideResourceRequestOptions != null) && (Build.VERSION.SDK_INT >= 16)) {
/* 1097 */       JSONObject localJSONObject1 = null;
/* 1098 */       String str1 = null; String str2 = null; String str3 = null; String str4 = null;
/* 1099 */       Object localObject = null;
/*      */       try {
/* 1101 */         for (int i = 0; i < this.mOverrideResourceRequestOptions.length(); i++) {
/* 1102 */           localJSONObject1 = this.mOverrideResourceRequestOptions.optJSONObject(i);
/* 1103 */           str1 = localJSONObject1.optString("match", "");
/* 1104 */           if (!TextUtils.isEmpty(str1)) {
/* 1105 */             Pattern localPattern = Pattern.compile(str1);
/* 1106 */             Matcher localMatcher = localPattern.matcher(paramString);
/* 1107 */             if (localMatcher.matches()) {
/* 1108 */               str2 = localJSONObject1.optString("redirect");
/* 1109 */               str2 = obtainApp().convert2AbsFullPath(str2);
/* 1110 */               str3 = localJSONObject1.optString("mime", PdrUtil.getMimeType(str2));
/* 1111 */               str4 = localJSONObject1.optString("encoding", "utf-8");
/* 1112 */               JSONObject localJSONObject2 = localJSONObject1.optJSONObject("header");
/* 1113 */               OverrideResourceRequestItem localOverrideResourceRequestItem = new OverrideResourceRequestItem();
/* 1114 */               localOverrideResourceRequestItem.redirect = str2;
/* 1115 */               localOverrideResourceRequestItem.encoding = str4;
/* 1116 */               localOverrideResourceRequestItem.mime = str3;
/* 1117 */               localOverrideResourceRequestItem.headerJson = localJSONObject2;
/*      */               try {
/* 1119 */                 return localOverrideResourceRequestItem;
/*      */               } catch (Exception localException2) {
/* 1121 */                 localException2.printStackTrace();
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       } catch (Exception localException1) {
/* 1127 */         localException1.printStackTrace();
/*      */       }
/*      */     }
/* 1130 */     return null;
/*      */   }
/*      */ 
/*      */   public void setOverrideUrlLoadingData(JSONObject paramJSONObject)
/*      */   {
/* 1141 */     this.mOverrideUrlLoadingDataOptions = paramJSONObject;
/* 1142 */     Logger.d("AdaFrameItem", "setOverrideUrlLoadingData=" + paramJSONObject);
/* 1143 */     if (this.mFrameView.getFrameType() == 2) {
/* 1144 */       this.mFrameView.obtainApp().setConfigProperty("wap2app_running_mode", "false");
/* 1145 */       this.mWebViewImpl.mWebLoadEvent.closeWap2AppBlockDialog(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setListenResourceLoading(JSONObject paramJSONObject)
/*      */   {
/* 1151 */     this.mListenResourceLoadingOptions = paramJSONObject;
/*      */   }
/*      */ 
/*      */   boolean checkResourceLoading(String paramString)
/*      */   {
/* 1160 */     boolean bool = true;
/* 1161 */     if ((this.mListenResourceLoadingOptions != null) && (this.mListenResourceLoadingOptions.has("match"))) {
/* 1162 */       bool = false;
/*      */       try {
/* 1164 */         String str = this.mListenResourceLoadingOptions.optString("match");
/* 1165 */         Pattern localPattern = Pattern.compile(str);
/* 1166 */         Matcher localMatcher = localPattern.matcher(paramString);
/* 1167 */         bool = localMatcher.matches();
/*      */       } catch (Exception localException) {
/* 1169 */         localException.printStackTrace();
/*      */       }
/*      */     }
/* 1172 */     return bool;
/*      */   }
/*      */ 
/*      */   boolean checkOverrideUrl(String paramString)
/*      */   {
/* 1181 */     boolean bool1 = false;
/*      */     try {
/* 1183 */       if (this.mOverrideUrlLoadingDataOptions != null) {
/* 1184 */         WebView.HitTestResult localHitTestResult = this.mWebViewImpl.getHitTestResult();
/* 1185 */         int i = localHitTestResult.getType();
/* 1186 */         String str1 = this.mOverrideUrlLoadingDataOptions.optString("exclude");
/* 1187 */         if (("redirect".equalsIgnoreCase(str1)) && (i == 0)) {
/* 1188 */           return false;
/*      */         }
/*      */ 
/* 1198 */         String str2 = this.mOverrideUrlLoadingDataOptions.optString("mode");
/*      */ 
/* 1202 */         boolean bool2 = false;
/* 1203 */         if (this.mOverrideUrlLoadingDataOptions.has("match")) {
/* 1204 */           String str3 = this.mOverrideUrlLoadingDataOptions.optString("match");
/* 1205 */           Pattern localPattern = Pattern.compile(str3);
/* 1206 */           Matcher localMatcher = localPattern.matcher(paramString);
/* 1207 */           bool2 = localMatcher.matches();
/*      */         } else {
/* 1209 */           bool2 = true;
/*      */         }
/* 1211 */         if ("allow".equals(str2)) {
/* 1212 */           if (bool2)
/* 1213 */             bool1 = false;
/*      */           else {
/* 1215 */             bool1 = true;
/*      */           }
/*      */         }
/* 1218 */         else if (bool2)
/* 1219 */           bool1 = true;
/*      */         else
/* 1221 */           bool1 = false;
/*      */       }
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/* 1226 */       localException.printStackTrace();
/*      */     }
/* 1228 */     return bool1;
/*      */   }
/*      */ 
/*      */   public boolean isLoaded() {
/* 1232 */     return this.mLoaded;
/*      */   }
/*      */ 
/*      */   public void setOriginalUrl(String paramString)
/*      */   {
/* 1237 */     this.originalUrl = paramString;
/*      */   }
/*      */ 
/*      */   public String getOriginalUrl()
/*      */   {
/* 1242 */     return this.originalUrl;
/*      */   }
/*      */ 
/*      */   public void setWebViewCacheMode(String paramString)
/*      */   {
/* 1247 */     if (paramString.equals("default"))
/* 1248 */       this.mCacheMode = -1;
/* 1249 */     else if (paramString.equals("cacheElseNetwork"))
/* 1250 */       this.mCacheMode = 1;
/* 1251 */     else if (paramString.equals("noCache"))
/* 1252 */       this.mCacheMode = 2;
/* 1253 */     else if (paramString.equals("cacheOnly")) {
/* 1254 */       this.mCacheMode = 3;
/*      */     }
/* 1256 */     if (this.mWebViewImpl != null)
/* 1257 */       this.mWebViewImpl.webSettings.setCacheMode(this.mCacheMode);
/*      */   }
/*      */ 
/*      */   public static void clearData()
/*      */   {
/* 1262 */     setedWebViewData = false;
/* 1263 */     WebViewImpl.sCustomUserAgent = null;
/* 1264 */     WebViewImpl.sDefalutUserAgent = null;
/*      */   }
/*      */ 
/*      */   public boolean unReceiveTitle()
/*      */   {
/* 1269 */     return this.unReceiveTitle;
/*      */   }
/*      */ 
/*      */   class OverrideResourceRequestItem
/*      */   {
/* 1134 */     public String redirect = null; public String mime = null; public String encoding = null;
/* 1135 */     JSONObject headerJson = null;
/*      */ 
/*      */     OverrideResourceRequestItem()
/*      */     {
/*      */     }
/*      */   }
/*      */ 
/*      */   public static abstract interface IFExecutePreloadJSContentCallBack
/*      */   {
/*      */     public abstract void callback(String paramString1, String paramString2);
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.AdaWebview
 * JD-Core Version:    0.6.2
 */