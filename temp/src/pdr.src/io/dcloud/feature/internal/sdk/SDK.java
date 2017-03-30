/*     */ package io.dcloud.feature.internal.sdk;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.ICore;
/*     */ import io.dcloud.common.DHInterface.IEventCallback;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IOnCreateSplashView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.DHInterface.IWebviewStateListener;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*     */ import io.dcloud.common.b.a.a;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class SDK
/*     */ {
/*     */   public static final String ANDROID_ASSET = "file:///android_asset/";
/*     */   public static final String DEFAULT_APPID = "Default_Appid";
/*  46 */   static ICore sCore = null;
/*     */ 
/*     */   public static void initSDK(ICore paramICore)
/*     */   {
/*  54 */     sCore = paramICore;
/*     */   }
/*     */ 
/*     */   public static Object dispatchEvent(IMgr.MgrType paramMgrType, int paramInt, Object paramObject)
/*     */   {
/*  64 */     return sCore.dispatchEvent(paramMgrType, paramInt, paramObject);
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public static final void loadCustomPath(String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public static void setGlobalDocumentsPath(String paramString)
/*     */   {
/*  76 */     BaseInfo.sDocumentFullPath = PdrUtil.appendByDeviceRootDir(paramString);
/*     */   }
/*     */ 
/*     */   public static void setGlobalDownloadsPath(String paramString) {
/*  80 */     BaseInfo.sDownloadFullPath = PdrUtil.appendByDeviceRootDir(paramString);
/*     */   }
/*     */ 
/*     */   private static IApp createUnstrictWebApp(String paramString1, String paramString2, String paramString3, byte paramByte)
/*     */   {
/*  94 */     return (IApp)sCore.dispatchEvent(IMgr.MgrType.AppMgr, 8, new String[] { paramString1, paramString2, paramString3, String.valueOf(paramByte) });
/*     */   }
/*     */ 
/*     */   public static void attach(ViewGroup paramViewGroup, IApp paramIApp, IWebview paramIWebview)
/*     */   {
/* 104 */     sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 16, new Object[] { paramViewGroup, paramIApp, paramIWebview, new ViewGroup.LayoutParams(-1, -1) });
/*     */   }
/*     */ 
/*     */   public static void attach(ViewGroup paramViewGroup, IApp paramIApp, IWebview paramIWebview, ViewGroup.LayoutParams paramLayoutParams)
/*     */   {
/* 113 */     sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 16, new Object[] { paramViewGroup, paramIApp, paramIWebview, paramLayoutParams });
/*     */   }
/*     */ 
/*     */   public static void attach(ViewGroup paramViewGroup, IWebview paramIWebview)
/*     */   {
/* 121 */     attach(paramViewGroup, paramIWebview.obtainFrameView().obtainApp(), paramIWebview);
/*     */   }
/*     */ 
/*     */   public static IWebview obatinFirstPage(IApp paramIApp)
/*     */   {
/* 131 */     return ((IFrameView)sCore.dispatchEvent(IMgr.MgrType.AppMgr, 9, new Object[] { paramIApp, null })).obtainWebView();
/*     */   }
/*     */ 
/*     */   public static IWebview createWebview(Activity paramActivity, String paramString, IWebviewStateListener paramIWebviewStateListener)
/*     */   {
/* 140 */     return createWebview(paramActivity, paramString, "Default_Appid", paramIWebviewStateListener);
/*     */   }
/*     */ 
/*     */   public static IWebview createWebview(Activity paramActivity, String paramString1, String paramString2, IWebviewStateListener paramIWebviewStateListener)
/*     */   {
/* 149 */     String str1 = PdrUtil.isEmpty(paramString2) ? "Default_Appid" : paramString2;
/* 150 */     String str2 = BaseInfo.sBaseFsAppsPath + str1 + File.separatorChar + BaseInfo.REAL_PRIVATE_DOC_DIR;
/* 151 */     return createWebview(paramActivity, paramString1, null, str2, str1, null, paramIWebviewStateListener);
/*     */   }
/*     */ 
/*     */   public static IWebview createWebview(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, IWebviewStateListener paramIWebviewStateListener)
/*     */   {
/* 164 */     byte b = 0;
/* 165 */     if (paramString1.startsWith("file:///android_asset/")) {
/* 166 */       b = 1;
/*     */     }
/* 168 */     IApp localIApp = createUnstrictWebApp(paramString4, paramString2, paramString1, b);
/* 169 */     a.a(paramString4);
/* 170 */     localIApp.setLaunchPageStateListener(paramIWebviewStateListener);
/* 171 */     localIApp.setAppDocPath(paramString3);
/* 172 */     localIApp.setConfigProperty("name", paramString5);
/* 173 */     localIApp.addAllFeaturePermission();
/* 174 */     localIApp.setWebAppActivity(paramActivity);
/* 175 */     localIApp.setWebAppIntent(paramActivity.getIntent());
/* 176 */     return obatinFirstPage(localIApp, paramIWebviewStateListener);
/*     */   }
/*     */ 
/*     */   public static IWebview obatinFirstPage(IApp paramIApp, IWebviewStateListener paramIWebviewStateListener)
/*     */   {
/* 185 */     return ((IFrameView)sCore.dispatchEvent(IMgr.MgrType.AppMgr, 9, new Object[] { paramIApp, paramIWebviewStateListener })).obtainWebView();
/*     */   }
/*     */ 
/*     */   public static IApp startWebApp(Activity paramActivity, String paramString1, String paramString2, IWebviewStateListener paramIWebviewStateListener, IOnCreateSplashView paramIOnCreateSplashView)
/*     */   {
/* 194 */     IApp localIApp = (IApp)sCore.dispatchEvent(IMgr.MgrType.AppMgr, 14, paramString1);
/* 195 */     if (localIApp != null) {
/* 196 */       localIApp.setLaunchPageStateListener(paramIWebviewStateListener);
/* 197 */       sCore.dispatchEvent(null, 2, new Object[] { paramActivity, localIApp.obtainAppId(), paramString2, paramIOnCreateSplashView });
/*     */     }
/* 199 */     return localIApp;
/*     */   }
/*     */ 
/*     */   public static void stopWebApp(IApp paramIApp) {
/* 203 */     sCore.dispatchEvent(IMgr.MgrType.AppMgr, 10, paramIApp.obtainAppId());
/*     */   }
/*     */ 
/*     */   public static void requestFeature(String paramString1, String paramString2, boolean paramBoolean)
/*     */   {
/* 212 */     sCore.dispatchEvent(IMgr.MgrType.FeatureMgr, 6, new String[] { paramString1, paramString2, String.valueOf(paramBoolean) });
/*     */   }
/*     */ 
/*     */   public static void requestAllFeature()
/*     */   {
/* 218 */     sCore.dispatchEvent(IMgr.MgrType.FeatureMgr, 7, null);
/*     */   }
/*     */ 
/*     */   public static void registerJsApi(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 228 */     sCore.dispatchEvent(IMgr.MgrType.FeatureMgr, 5, new String[] { paramString1, paramString2, paramString3 });
/*     */   }
/*     */ 
/*     */   private static IWebview createWebView(IApp paramIApp, String paramString, JSONObject paramJSONObject, IFrameView paramIFrameView, IEventCallback paramIEventCallback) {
/* 232 */     Object[] arrayOfObject = { Integer.valueOf(0), paramIApp, { paramString, paramJSONObject }, paramIFrameView, paramIEventCallback };
/* 233 */     IFrameView localIFrameView = (IFrameView)sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 3, arrayOfObject);
/*     */ 
/* 235 */     IWebview localIWebview = localIFrameView.obtainWebView();
/* 236 */     localIWebview.loadUrl(paramString);
/* 237 */     return localIWebview;
/*     */   }
/*     */ 
/*     */   public static void closeWebView(IWebview paramIWebview)
/*     */   {
/* 245 */     ((AdaFrameItem)paramIWebview.obtainFrameView()).getAnimOptions().mOption = 1;
/* 246 */     sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 2, paramIWebview.obtainFrameView());
/*     */   }
/*     */ 
/*     */   public static void popWebView(IWebview paramIWebview)
/*     */   {
/* 254 */     ((AdaFrameItem)paramIWebview.obtainFrameView()).getAnimOptions().mOption = 1;
/* 255 */     sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 21, paramIWebview.obtainFrameView());
/*     */   }
/*     */ 
/*     */   public static ArrayList<IWebview> obtainAllIWebview(String paramString)
/*     */   {
/* 264 */     ArrayList localArrayList1 = null;
/* 265 */     ArrayList localArrayList2 = (ArrayList)sCore.dispatchEvent(IMgr.MgrType.WindowMgr, 6, paramString);
/* 266 */     if ((localArrayList2 != null) && (localArrayList2.size() > 0)) {
/* 267 */       localArrayList1 = new ArrayList();
/* 268 */       for (IFrameView localIFrameView : localArrayList2) {
/* 269 */         localArrayList1.add(localIFrameView.obtainWebView());
/*     */       }
/*     */     }
/* 272 */     return localArrayList1;
/*     */   }
/*     */ 
/*     */   public static String obtainCurrentRunnbingAppId()
/*     */   {
/* 280 */     return String.valueOf(sCore.dispatchEvent(IMgr.MgrType.AppMgr, 11, null));
/*     */   }
/*     */ 
/*     */   public static IWebview obtainWebview(String paramString1, String paramString2) {
/* 284 */     ArrayList localArrayList = obtainAllIWebview(paramString1);
/* 285 */     for (IWebview localIWebview : localArrayList) {
/* 286 */       if (PdrUtil.isEquals(paramString2, localIWebview.getWebviewUUID())) {
/* 287 */         return localIWebview;
/*     */       }
/*     */     }
/* 290 */     return null;
/*     */   }
/*     */ 
/*     */   public static ArrayList<IWebview> obtainAllIWebview()
/*     */   {
/* 297 */     String str = obtainCurrentRunnbingAppId();
/* 298 */     return obtainAllIWebview(str);
/*     */   }
/*     */ 
/*     */   public static IApp obtainCurrentApp()
/*     */   {
/* 305 */     return (IApp)sCore.dispatchEvent(IMgr.MgrType.AppMgr, 6, obtainCurrentRunnbingAppId());
/*     */   }
/*     */ 
/*     */   public static enum IntegratedMode
/*     */   {
/*  42 */     WEBVIEW, 
/*  43 */     WEBAPP, 
/*  44 */     RUNTIME;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.feature.internal.sdk.SDK
 * JD-Core Version:    0.6.2
 */