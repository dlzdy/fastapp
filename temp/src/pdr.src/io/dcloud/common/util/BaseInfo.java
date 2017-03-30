/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.content.pm.PackageManager.NameNotFoundException;
/*     */ import android.content.pm.Signature;
/*     */ import android.net.ConnectivityManager;
/*     */ import android.net.NetworkInfo;
/*     */ import android.net.Uri;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Environment;
/*     */ import android.text.TextUtils;
/*     */ import android.util.Log;
/*     */ import android.webkit.URLUtil;
/*     */ import io.dcloud.a.a;
/*     */ import io.dcloud.application.DCloudApplication;
/*     */ import io.dcloud.common.DHInterface.ICore;
/*     */ import io.dcloud.common.DHInterface.ICore.ICoreStatusListener;
/*     */ import io.dcloud.common.adapter.io.DHFile;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.constant.StringConst;
/*     */ import io.dcloud.feature.internal.sdk.SDK.IntegratedMode;
/*     */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.CertificateFactory;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Set;
/*     */ import javax.security.auth.x500.X500Principal;
/*     */ 
/*     */ public final class BaseInfo
/*     */ {
/*  62 */   public static String sGlobalUserAgent = null;
/*     */ 
/*  64 */   public static String sChannel = "default";
/*     */ 
/*  66 */   public static String sFontScale = "none";
/*     */ 
/*  70 */   public static boolean isPostChcekShortCut = false;
/*     */ 
/*  80 */   public static boolean sDoingAnimation = false;
/*     */ 
/*  82 */   public static int sOpenedCount = 0;
/*     */ 
/*  84 */   public static boolean sFullScreenChanged = false;
/*     */ 
/*  86 */   public static boolean sAnimationCaptureC = true;
/*     */ 
/*  88 */   public static boolean sAnimationCaptureB = true;
/*     */ 
/*  90 */   public static int sTimeoutCapture = 350;
/*     */ 
/*  92 */   public static int sTimeOutMax = 3;
/*     */ 
/*  94 */   public static int sTimeOutCount = 0;
/*     */ 
/*  96 */   public static int mDeStatusBarBackground = -111111;
/*     */ 
/*  99 */   public static HashMap<String, CmtInfo> mLaunchers = new HashMap();
/*     */ 
/* 101 */   public static String sSplashExitCondition = "loaded";
/*     */ 
/* 103 */   public static boolean sGlobalFullScreen = false;
/*     */ 
/* 105 */   public static String sBaseControlPath = DeviceInfo.sBaseResRootPathName + "data/dcloud_control.xml";
/* 106 */   public static String sBaseWap2AppTemplatePath = null;
/* 107 */   public static String sBaseConfigTemplatePath = DeviceInfo.sBaseResRootPathName + "data/wap2app/__template.json";
/* 108 */   public static String sBaseWap2AppFilePath = DeviceInfo.sBaseResRootPathName + "data/wap2app/__wap2app.js";
/* 109 */   public static String sRuntimeJsPath = "io/dcloud/all.js";
/* 110 */   public static String sApiConfigPath = DeviceInfo.sBaseResRootPathName + "data/api.json";
/*     */ 
/* 112 */   public static boolean ISDEBUG = false;
/*     */ 
/* 114 */   public static boolean USE_ACTIVITY_HANDLE_KEYEVENT = 0x1 | (Build.VERSION.SDK_INT < 19 ? 1 : 0);
/*     */ 
/* 116 */   public static boolean sSupportAddByHand = true;
/*     */ 
/* 118 */   public static SDK.IntegratedMode sRuntimeMode = null;
/* 119 */   public static String PDR = "pdr";
/*     */ 
/* 123 */   public static String WGTU_UPDATE_XML = "update.xml";
/* 124 */   public static String APP_WEB_CHACHE = "webcache/";
/* 125 */   public static String APP_JSDATA = "jsdata/";
/* 126 */   public static String APP_DB_DATA = "dbdata/";
/*     */ 
/* 128 */   private static String APPS_NAME = "apps/";
/*     */   public static String sDefaultBootApp;
/*     */   public static String sLastRunApp;
/*     */   public static String sBaseVersion;
/*     */   public static String sWap2AppTemplateVersion;
/* 138 */   public static boolean ISAMU = false;
/*     */ 
/* 140 */   public static boolean s_Is_DCloud_Packaged = false;
/*     */ 
/* 142 */   public static int s_Webview_Count = 0;
/* 143 */   public static int s_Runing_App_Count = 0;
/* 144 */   public static int s_Runing_App_Count_Max = 3;
/* 145 */   public static int s_Runing_App_Count_Trim = 0;
/* 146 */   public static ArrayList<String> sRunningApp = null;
/*     */ 
/* 148 */   public static String sBaseResAppsFullPath = null;
/*     */ 
/* 154 */   public static String sBaseResAppsPath = null;
/*     */ 
/* 157 */   public static String sBaseFsAppsPath = null;
/*     */ 
/* 160 */   public static String sDownloadFullPath = null;
/*     */ 
/* 163 */   public static String sDocumentFullPath = "";
/*     */ 
/* 166 */   public static boolean sCoverApkRuning = false;
/*     */ 
/* 169 */   public static String s_properties = "/data/dcloud_properties.xml";
/*     */ 
/* 171 */   public static String sConfigXML = "manifest.json";
/*     */ 
/* 174 */   public static String APP_WWW_FS_DIR = "www/";
/*     */   public static final String REL_PRIVATE_WWW_DIR = "_www";
/*     */   public static final String REL_PRIVATE_DOC_DIR = "_doc";
/*     */   public static final String REL_PUBLIC_DOCUMENTS_DIR = "_documents";
/* 185 */   public static String sBaseNotificationPath = null;
/*     */   public static final String REL_PUBLIC_DOWNLOADS_DIR = "_downloads";
/* 190 */   public static String REAL_PRIVATE_WWW_DIR = "www/";
/*     */ 
/* 192 */   public static String REAL_PRIVATE_DOC_DIR = "doc/";
/*     */ 
/* 194 */   public static String REAL_PUBLIC_DOCUMENTS_DIR = "documents/";
/*     */ 
/* 196 */   public static String REAL_PUBLIC_DOWNLOADS_DIR = "downloads/";
/*     */ 
/* 198 */   public static HashMap<String, String> sAppIsTests = new HashMap();
/*     */ 
/* 202 */   public static boolean isStreamAppRuning = false;
/*     */ 
/* 204 */   public static boolean isDefaultAim = Build.VERSION.SDK_INT >= 21;
/*     */ 
/* 206 */   public static long run5appEndTime = 0L;
/*     */ 
/* 208 */   public static LinkedHashMap<String, Intent> sAppStateMap = new LinkedHashMap();
/*     */ 
/* 264 */   private static boolean sParsedControl = false;
/*     */ 
/* 443 */   public static HashMap<String, BaseAppInfo> mUnInstalledAppInfoSet = new HashMap();
/*     */ 
/* 446 */   public static HashMap<String, BaseAppInfo> mInstalledAppInfoSet = new HashMap();
/*     */ 
/* 448 */   public static HashMap<String, BaseAppInfo> mBaseAppInfoSet = new HashMap();
/*     */ 
/* 558 */   public static boolean isStreamSDK = false;
/*     */ 
/* 794 */   private static final X500Principal DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");
/*     */ 
/*     */   public static synchronized boolean isLoadingLaunchePage()
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public static synchronized void setLoadingLaunchePage(boolean paramBoolean, String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public static String getCrashLogsPath(Context paramContext)
/*     */   {
/* 210 */     if (isForQihooBrowser(paramContext))
/* 211 */       return DeviceInfo.sBaseFsRootPath + "logs/browser_qihoo/";
/* 212 */     if (isForQihooHelper(paramContext))
/* 213 */       return DeviceInfo.sBaseFsRootPath + "logs/appsotre_qihoo/";
/* 214 */     if (isStreamApp(paramContext)) {
/* 215 */       return DeviceInfo.sBaseFsRootPath + "logs/streamapps/";
/*     */     }
/* 217 */     return DeviceInfo.sBaseFsRootPath + "/log/";
/*     */   }
/*     */ 
/*     */   public static void updateBaseInfo()
/*     */   {
/* 226 */     if (!APPS_NAME.equals("/")) {
/* 227 */       sBaseResAppsFullPath = DeviceInfo.sBaseResRootFullPath + APPS_NAME;
/*     */ 
/* 229 */       sBaseFsAppsPath = DeviceInfo.sBaseFsRootPath + APPS_NAME;
/* 230 */       sBaseWap2AppTemplatePath = sBaseFsAppsPath;
/* 231 */       sBaseResAppsPath = DeviceInfo.sBaseResRootPathName + APPS_NAME;
/* 232 */       DHFile.createNewFile(sBaseFsAppsPath);
/*     */     }
/* 234 */     if ((sDownloadFullPath != null) && (sDownloadFullPath.indexOf("sdcard/") > -1))
/* 235 */       sDownloadFullPath = PdrUtil.appendByDeviceRootDir(sDownloadFullPath);
/*     */     else {
/* 237 */       sDownloadFullPath = DeviceInfo.sBaseFsCachePath + REAL_PUBLIC_DOWNLOADS_DIR;
/*     */     }
/*     */ 
/* 240 */     if ((sDocumentFullPath != null) && (sDocumentFullPath.indexOf("sdcard/") > -1))
/* 241 */       sDocumentFullPath = PdrUtil.appendByDeviceRootDir(sDocumentFullPath);
/*     */     else
/* 243 */       sDocumentFullPath = DeviceInfo.sBaseFsRootPath + REAL_PUBLIC_DOCUMENTS_DIR;
/*     */   }
/*     */ 
/*     */   public static void updateBaseInfoByApp(String paramString1, String paramString2)
/*     */   {
/* 255 */     if (paramString1 != null) {
/* 256 */       PDR = paramString1;
/*     */     }
/*     */ 
/* 259 */     if (paramString2 != null) {
/* 260 */       DeviceInfo.sBaseFsRootPath = paramString2;
/*     */     }
/* 262 */     DeviceInfo.initBaseFsRootPath();
/*     */   }
/*     */ 
/*     */   public static String[] parseControl(ICore paramICore, ICore.ICoreStatusListener paramICoreStatusListener)
/*     */   {
/* 272 */     HashMap localHashMap = null;
/* 273 */     String[] arrayOfString = null;
/* 274 */     int i = 0;
/*     */ 
/* 276 */     if (!sParsedControl) {
/*     */       try {
/* 278 */         InputStream localInputStream = PlatformUtil.getResInputStream(sBaseControlPath);
/* 279 */         if (localInputStream != null) {
/* 280 */           XmlUtil.DHNode localDHNode1 = XmlUtil.XML_Parser(localInputStream);
/* 281 */           IOUtil.close(localInputStream);
/* 282 */           if (localDHNode1 != null) {
/* 283 */             ISDEBUG = Boolean.parseBoolean(XmlUtil.getAttributeValue(localDHNode1, "debug", "false"));
/* 284 */             isStreamSDK = !PdrUtil.isEmpty(XmlUtil.getAttributeValue(localDHNode1, "streamapp", null));
/* 285 */             ISAMU = Boolean.parseBoolean(XmlUtil.getAttributeValue(localDHNode1, "amu", "false"));
/* 286 */             String str1 = AndroidResources.getMetaValue("DCLOUD_STREAMAPP_CHANNEL");
/* 287 */             sChannel = PdrUtil.isEmpty(str1) ? XmlUtil.getAttributeValue(localDHNode1, "channel", sChannel) : str1;
/* 288 */             sSplashExitCondition = XmlUtil.getAttributeValue(localDHNode1, "back", sSplashExitCondition);
/* 289 */             s_Is_DCloud_Packaged = Boolean.parseBoolean(XmlUtil.getAttributeValue(localDHNode1, "ns", "false"));
/* 290 */             String str2 = XmlUtil.getAttributeValue(localDHNode1, "fontscale", sFontScale);
/* 291 */             if (!TextUtils.isEmpty(str2)) {
/* 292 */               sFontScale = str2;
/*     */             }
/* 294 */             sSupportAddByHand &= ISDEBUG;
/* 295 */             if (TextUtils.isEmpty(sBaseVersion)) {
/* 296 */               sBaseVersion = XmlUtil.getAttributeValue(localDHNode1, "version");
/*     */             }
/*     */ 
/* 301 */             XmlUtil.DHNode localDHNode2 = XmlUtil.getElement(localDHNode1, "apps");
/*     */ 
/* 303 */             Object localObject = XmlUtil.getAttributeValue(localDHNode2, "max");
/* 304 */             s_Runing_App_Count_Max = PdrUtil.parseInt((String)localObject, s_Runing_App_Count_Max);
/* 305 */             if (s_Runing_App_Count_Max <= 0) {
/* 306 */               s_Runing_App_Count_Max = 2147483647;
/*     */             }
/*     */ 
/* 310 */             localObject = XmlUtil.getAttributeValue(localDHNode2, "trim");
/* 311 */             s_Runing_App_Count_Trim = PdrUtil.parseInt((String)localObject, s_Runing_App_Count_Trim);
/* 312 */             if (s_Runing_App_Count_Trim <= 0) {
/* 313 */               s_Runing_App_Count_Trim = 0;
/*     */             }
/*     */ 
/* 316 */             localObject = XmlUtil.getElements(localDHNode2, "app");
/* 317 */             if (localObject != null) {
/* 318 */               boolean bool = true;
/* 319 */               int j = ((ArrayList)localObject).size();
/* 320 */               for (int k = 0; k < j; k++) {
/* 321 */                 XmlUtil.DHNode localDHNode3 = (XmlUtil.DHNode)((ArrayList)localObject).get(k);
/* 322 */                 String str3 = XmlUtil.getAttributeValue(localDHNode3, "appid");
/* 323 */                 if (k == 0) {
/* 324 */                   arrayOfString = new String[1];
/* 325 */                   arrayOfString[0] = str3;
/* 326 */                   sDefaultBootApp = str3;
/* 327 */                   updateBaseInfoByApp(str3, null);
/* 328 */                   bool = BaseInfo.sCoverApkRuning = DeviceInfo.checkCoverInstallApk();
/* 329 */                   if (isBase(DCloudApplication.getInstance())) {
/* 330 */                     bool &= !ISDEBUG;
/*     */                   }
/* 332 */                   loadInstalledAppInfo(paramICore);
/* 333 */                   localHashMap = mInstalledAppInfoSet;
/*     */                 }
/* 335 */                 String str4 = XmlUtil.getAttributeValue(localDHNode3, "appver");
/* 336 */                 BaseAppInfo localBaseAppInfo1 = new BaseAppInfo(str3, str4);
/*     */ 
/* 338 */                 mBaseAppInfoSet.put(str3, localBaseAppInfo1);
/*     */ 
/* 341 */                 if ((bool) && (localHashMap.containsKey(str3))) {
/* 342 */                   BaseAppInfo localBaseAppInfo2 = (BaseAppInfo)localHashMap.get(str3);
/* 343 */                   if ((sCoverApkRuning) || (localBaseAppInfo1.high(localBaseAppInfo2)))
/*     */                   {
/* 346 */                     Log.i("Main_Path", str3 + " App has new version! it is " + localBaseAppInfo1.mAppVer);
/* 347 */                     i = 1;
/* 348 */                     localBaseAppInfo1.clearBundleData();
/* 349 */                     localHashMap.remove(str3);
/*     */                   } else {
/* 351 */                     localBaseAppInfo1.mMoreRecent = false;
/* 352 */                     mBaseAppInfoSet.remove(str3);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         } else {
/* 359 */           DeviceInfo.initBaseFsRootPath();
/*     */         }
/*     */       } catch (Exception localException) {
/* 362 */         localException.printStackTrace();
/*     */       }
/* 364 */       sParsedControl = true;
/* 365 */       if (ISDEBUG) {
/* 366 */         Logger.setOpen(true);
/*     */       }
/*     */ 
/* 369 */       if (i != 0) {
/* 370 */         PlatformUtil.setBundleData(PDR, "apps", installAppMapToString());
/*     */       }
/*     */     }
/* 373 */     if (paramICoreStatusListener != null) {
/* 374 */       Log.i("Main_Path", "will exc coreListener.onCoreReady");
/* 375 */       paramICoreStatusListener.onCoreReady(paramICore);
/*     */     }
/* 377 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public static void loadInstalledAppInfo(ICore paramICore)
/*     */   {
/* 386 */     String str1 = PlatformUtil.getBundleData(PDR, "apps");
/*     */     String[] arrayOfString1;
/*     */     String str2;
/*     */     Object localObject;
/* 387 */     if (str1 != null) {
/* 388 */       arrayOfString1 = str1.split("\\|");
/* 389 */       for (str2 : arrayOfString1) {
/* 390 */         localObject = PlatformUtil.getBundleData(PDR, str2 + "_" + "appver");
/* 391 */         BaseAppInfo localBaseAppInfo = new BaseAppInfo(str2, (String)localObject);
/* 392 */         boolean bool = Boolean.parseBoolean(PlatformUtil.getBundleData(PDR, str2 + "_" + "deleted"));
/* 393 */         localBaseAppInfo.mDeleted = bool;
/* 394 */         if (bool)
/* 395 */           mUnInstalledAppInfoSet.put(str2, localBaseAppInfo);
/* 396 */         else if (!PdrUtil.isEmpty(str2)) {
/* 397 */           mInstalledAppInfoSet.put(str2, localBaseAppInfo);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 402 */     if ((paramICore != null) && (!paramICore.isStreamAppMode()) && (sSupportAddByHand)) {
/* 403 */       arrayOfString1 = PlatformUtil.listFsAppsFiles(sBaseFsAppsPath);
/* 404 */       if (arrayOfString1 != null)
/* 405 */         for (str2 : arrayOfString1)
/* 406 */           if (!mInstalledAppInfoSet.containsKey(str2)) {
/* 407 */             localObject = new BaseAppInfo(str2, "0");
/* 408 */             mInstalledAppInfoSet.put(str2, localObject);
/*     */           }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String installAppMapToString()
/*     */   {
/* 421 */     StringBuffer localStringBuffer = new StringBuffer();
/* 422 */     Set localSet = mInstalledAppInfoSet.keySet();
/* 423 */     int i = localSet.size();
/* 424 */     for (String str : localSet) {
/* 425 */       localStringBuffer.append(str).append("|");
/*     */     }
/* 427 */     if (i > 1) {
/* 428 */       localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
/*     */     }
/* 430 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static void saveInstalledAppInfo()
/*     */   {
/* 438 */     String str = installAppMapToString();
/* 439 */     PlatformUtil.setBundleData(PDR, "apps", str);
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public static void loadCustomPath(String paramString)
/*     */   {
/* 456 */     String[] arrayOfString1 = paramString.split("\\||\r\n|\n");
/* 457 */     int i = arrayOfString1.length;
/* 458 */     if (i > 0) {
/* 459 */       int j = 1;
/* 460 */       for (int k = 0; k < i; k++) {
/* 461 */         String[] arrayOfString2 = arrayOfString1[k].split("=");
/* 462 */         String str1 = arrayOfString2[0];
/* 463 */         String str2 = arrayOfString2[1];
/* 464 */         if (str1.equals("control_xml_path")) {
/* 465 */           sBaseControlPath = str2;
/* 466 */         } else if (str1.equals("fs_root_path")) {
/* 467 */           str2 = str2.substring(str2.indexOf("sdcard/") + "sdcard/".length());
/* 468 */           DeviceInfo.sBaseFsRootPath = DeviceInfo.sDeviceRootDir + "/" + str2;
/* 469 */         } else if (str1.equals("downloads")) {
/* 470 */           REAL_PUBLIC_DOWNLOADS_DIR = str2;
/* 471 */         } else if (str1.equals("documents")) {
/* 472 */           REAL_PUBLIC_DOCUMENTS_DIR = str2;
/* 473 */         } else if (str1.equals("doc")) {
/* 474 */           REAL_PRIVATE_DOC_DIR = str2;
/* 475 */         } else if (str1.equals("runtime_apps")) {
/* 476 */           APPS_NAME = str2;
/* 477 */         } else if (str1.equals("www")) {
/* 478 */           REAL_PRIVATE_WWW_DIR = BaseInfo.APP_WWW_FS_DIR = str2;
/*     */         }
/*     */       }
/* 481 */       if (j != 0)
/* 482 */         DeviceInfo.updatePath();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isBase(Context paramContext)
/*     */   {
/* 490 */     return paramContext.getPackageName().equals("io.dcloud.HBuilder");
/*     */   }
/*     */ 
/*     */   public static boolean existsBase() {
/*     */     try {
/* 495 */       return new File(Environment.getExternalStorageDirectory() + "/Android/data/io.dcloud.HBuilder").exists();
/*     */     } catch (Exception localException) {
/* 497 */       localException.printStackTrace();
/*     */     }
/* 499 */     return false;
/*     */   }
/*     */   public static boolean isQihooLifeHelper(Context paramContext) {
/* 502 */     return paramContext.getPackageName().equals("com.qihoo.life");
/*     */   }
/*     */ 
/*     */   public static boolean useStreamAppStatistic(Context paramContext) {
/* 506 */     boolean bool = false;
/*     */     try
/*     */     {
/* 510 */       bool |= paramContext.getPackageName().equals("io.dcloud.streamapp");
/* 511 */       bool |= paramContext.getPackageName().equals("com.qihoo.appstore");
/* 512 */       bool |= paramContext.getPackageName().equals("io.dcloud.streamapps");
/* 513 */       bool |= paramContext.getPackageName().equals("io.dcloud.HBuilder");
/* 514 */       bool |= paramContext.getPackageName().equals("com.qihoo.life");
/* 515 */       bool |= paramContext.getPackageName().equals("com.qihoo.browser");
/*     */     }
/*     */     catch (Throwable localThrowable)
/*     */     {
/* 529 */       localThrowable.printStackTrace();
/*     */     }
/* 531 */     return bool;
/*     */   }
/*     */ 
/*     */   public static boolean isTrafficFree()
/*     */   {
/*     */     try
/*     */     {
/* 540 */       Context localContext1 = DCloudApplication.getInstance();
/* 541 */       if (localContext1 != null) {
/* 542 */         Context localContext2 = localContext1.getApplicationContext();
/* 543 */         return (localContext2.getPackageName().equals("com.qihoo.life")) && (!isWifi(localContext2));
/*     */       }
/*     */     } catch (Exception localException) {
/* 546 */       localException.printStackTrace();
/*     */     }
/* 548 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isStreamApp(Context paramContext)
/*     */   {
/* 556 */     return paramContext.getPackageName().equals("io.dcloud.streamapps");
/*     */   }
/*     */ 
/*     */   public static boolean isShowTitleBarForStreamAppSDK()
/*     */   {
/* 567 */     boolean bool = false;
/* 568 */     String str = AndroidResources.getMetaValue("DCLOUD_STREAMAPP_ACTIONBAR");
/* 569 */     if (PdrUtil.isEquals("standard", str)) {
/* 570 */       bool = true;
/*     */     }
/* 572 */     Logger.e("StreamSdk", "isshowtitlebar==" + bool);
/* 573 */     return bool;
/*     */   }
/*     */ 
/*     */   public static boolean isForQihooHelper(Context paramContext)
/*     */   {
/* 581 */     return (paramContext.getPackageName().equals("io.dcloud.streamapp")) || (paramContext.getPackageName().equals("com.qihoo.appstore"));
/*     */   }
/*     */ 
/*     */   public static boolean isShowTitleBar(Context paramContext)
/*     */   {
/* 592 */     return (isForQihooBrowser(paramContext)) || (isShowTitleBarForStreamAppSDK());
/*     */   }
/*     */ 
/*     */   public static boolean isForQihooBrowser(Context paramContext)
/*     */   {
/* 602 */     return (paramContext.getPackageName().equals("com.qihoo.browser")) || (paramContext.getPackageName().equals("com.qihoo.bclplugintest")) || (paramContext.getPackageName().equals("io.dcloud.streamapp"));
/*     */   }
/*     */ 
/*     */   public static boolean isForQihooHelper5_0(Context paramContext) {
/* 606 */     return paramContext.getPackageName().equals("io.dcloud.streamapp");
/*     */   }
/*     */ 
/*     */   public static String getShortCutActivity(Context paramContext) {
/* 610 */     String str = null;
/* 611 */     return str;
/*     */   }
/*     */   public static String getRunningActivity(Context paramContext) {
/* 614 */     String str = "io.dcloud.appstream.StreamAppMainActivity";
/* 615 */     if (isForQihooHelper(paramContext)) {
/* 616 */       if (DCloudAdapterUtil.isPlugin())
/* 617 */         str = "io.src.dcloud.adapter.StreamAppActivity";
/*     */       else {
/* 619 */         str = "io.dcloud.appstream.StreamAppListActivity";
/*     */       }
/*     */     }
/* 622 */     return str;
/*     */   }
/*     */   public static boolean isGlobal(Context paramContext) {
/* 625 */     return (isForQihooBrowser(paramContext)) || (isForQihooHelper(paramContext)) || (isStreamApp(paramContext)) || (TextUtils.equals("io.dcloud.html5pframework", paramContext.getPackageName())) || (isStreamSDK);
/*     */   }
/*     */ 
/*     */   public static boolean isWifi(Context paramContext)
/*     */   {
/* 633 */     ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
/*     */ 
/* 635 */     NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
/* 636 */     if ((localNetworkInfo != null) && (localNetworkInfo.getType() == 1))
/*     */     {
/* 638 */       return true;
/*     */     }
/* 640 */     return false;
/*     */   }
/*     */ 
/*     */   public static void putLauncherData(String paramString1, String paramString2)
/*     */   {
/* 729 */     CmtInfo localCmtInfo = getCmitInfo(paramString1);
/* 730 */     localCmtInfo.plusLauncher = paramString2;
/*     */   }
/*     */ 
/*     */   public static String getLauncherData(String paramString) {
/* 734 */     CmtInfo localCmtInfo = (CmtInfo)mLaunchers.get(paramString);
/* 735 */     if (localCmtInfo == null) {
/* 736 */       return "default";
/*     */     }
/* 738 */     return TextUtils.isEmpty(localCmtInfo.plusLauncher) ? "default" : localCmtInfo.plusLauncher;
/*     */   }
/*     */ 
/*     */   public static String getLaunchType(Intent paramIntent)
/*     */   {
/* 743 */     if (paramIntent == null) return "default";
/* 744 */     Uri localUri = paramIntent.getData();
/* 745 */     Object localObject = "default";
/* 746 */     if (paramIntent.hasExtra("plus.runtime.launcher")) {
/* 747 */       String str = paramIntent.getStringExtra("plus.runtime.launcher");
/* 748 */       localObject = str;
/*     */     }
/* 750 */     if ((localUri != null) && (!URLUtil.isNetworkUrl(localUri.toString()))) {
/* 751 */       if (paramIntent.getBooleanExtra("from_barcode", false))
/* 752 */         localObject = "barcode";
/*     */       else
/* 754 */         localObject = "scheme";
/*     */     }
/* 756 */     else if (paramIntent.getExtras() != null) {
/* 757 */       if (!TextUtils.isEmpty(paramIntent.getStringExtra("__launcher__")))
/* 758 */         localObject = paramIntent.getStringExtra("__launcher__");
/* 759 */       else if (paramIntent.getBooleanExtra("from_short_cut_start", false))
/* 760 */         localObject = "shortcut";
/* 761 */       else if (paramIntent.getBooleanExtra("from_barcode", false))
/* 762 */         localObject = "barcode";
/* 763 */       else if (paramIntent.getIntExtra("__start_from__", -1) == 3)
/* 764 */         localObject = "push";
/* 765 */       else if (paramIntent.getIntExtra("__start_from__", -1) == 5) {
/* 766 */         localObject = "myapp";
/*     */       }
/*     */     }
/* 769 */     return localObject;
/*     */   }
/*     */ 
/*     */   public static String getLastKey(LinkedHashMap<String, Intent> paramLinkedHashMap) {
/* 773 */     String str = null;
/* 774 */     if (paramLinkedHashMap != null) {
/* 775 */       Iterator localIterator = paramLinkedHashMap.keySet().iterator();
/* 776 */       while (localIterator.hasNext()) {
/* 777 */         str = (String)localIterator.next();
/*     */       }
/*     */     }
/* 780 */     return str;
/*     */   }
/*     */   public static void clearData() {
/* 783 */     sParsedControl = false;
/* 784 */     sAppStateMap.clear();
/*     */ 
/* 786 */     sGlobalFullScreen = false;
/* 787 */     io.dcloud.common.adapter.util.UEH.sInited = false;
/* 788 */     sLastRunApp = null;
/* 789 */     sRunningApp = null;
/* 790 */     s_Webview_Count = 0;
/* 791 */     s_Runing_App_Count = 0;
/*     */   }
/*     */ 
/*     */   private static boolean isDebugSignature(Context paramContext)
/*     */   {
/* 797 */     boolean bool = false;
/*     */     try {
/* 799 */       PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64);
/*     */ 
/* 801 */       Signature[] arrayOfSignature = localPackageInfo.signatures;
/* 802 */       for (int i = 0; i < arrayOfSignature.length; i++) {
/* 803 */         CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
/* 804 */         ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfSignature[i].toByteArray());
/*     */ 
/* 806 */         X509Certificate localX509Certificate = (X509Certificate)localCertificateFactory.generateCertificate(localByteArrayInputStream);
/*     */ 
/* 808 */         bool = localX509Certificate.getSubjectX500Principal().equals(DEBUG_DN);
/* 809 */         if (bool) break;
/*     */       }
/*     */     } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
/*     */     }
/*     */     catch (CertificateException localCertificateException) {
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/* 817 */     return bool;
/*     */   }
/*     */ 
/*     */   public static boolean isWap2AppCompleted(String paramString)
/*     */   {
/* 826 */     return (isWap2AppAppid(paramString)) && (InvokeExecutorHelper.AppStreamUtils.invoke("hasCompletedFile", paramString, false));
/*     */   }
/*     */ 
/*     */   public static boolean isWap2AppAppid(String paramString)
/*     */   {
/* 831 */     if (paramString != null) {
/* 832 */       paramString = paramString.toLowerCase();
/* 833 */       return (paramString.startsWith("__w2a__")) || ("H52588A9C".equalsIgnoreCase(paramString)) || ("H5B5EEFBB".equalsIgnoreCase(paramString)) || ("H5A0B1958".equalsIgnoreCase(paramString)) || ("H5EA885FD".equalsIgnoreCase(paramString)) || ("H592E7F63".equalsIgnoreCase(paramString)) || ("H5BCD03E4".equalsIgnoreCase(paramString));
/*     */     }
/*     */ 
/* 838 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isTest(String paramString)
/*     */   {
/* 847 */     if (sAppIsTests.containsKey(paramString)) {
/* 848 */       return true;
/*     */     }
/* 850 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean checkAppIsTest(String paramString)
/*     */   {
/* 860 */     String str = StringConst.STREAMAPP_KEY_ROOTPATH + "apps/" + paramString + "/.test";
/* 861 */     File localFile = new File(str);
/* 862 */     return localFile.exists();
/*     */   }
/*     */ 
/*     */   public static boolean checkTestOpenFile() {
/* 866 */     String str = DeviceInfo.sDeviceRootDir + "/.system/d85a37c6-afdc-11e6-80f5-76304dec7eb7";
/* 867 */     File localFile = new File(str);
/* 868 */     return localFile.exists();
/*     */   }
/*     */ 
/*     */   public static void removeTestFile(String paramString)
/*     */   {
/* 876 */     String str = StringConst.STREAMAPP_KEY_ROOTPATH + "apps/" + paramString + "/.test";
/* 877 */     File localFile = new File(str);
/* 878 */     if (localFile.exists())
/* 879 */       localFile.delete();
/*     */   }
/*     */ 
/*     */   public static void createAppTestFile(String paramString)
/*     */   {
/* 888 */     String str = StringConst.STREAMAPP_KEY_ROOTPATH + "apps/" + paramString + "/.test";
/* 889 */     File localFile = new File(str);
/* 890 */     if (!localFile.exists())
/*     */       try {
/* 892 */         localFile.mkdirs();
/* 893 */         localFile.createNewFile();
/*     */       }
/*     */       catch (IOException localIOException) {
/* 896 */         localIOException.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static String getkey(Context paramContext)
/*     */   {
/* 907 */     return a.a(paramContext);
/*     */   }
/*     */ 
/*     */   public static String getiv() {
/* 911 */     return a.a();
/*     */   }
/*     */ 
/*     */   public static CmtInfo getCmitInfo(String paramString) {
/* 915 */     CmtInfo localCmtInfo = (CmtInfo)mLaunchers.get(paramString);
/* 916 */     if (localCmtInfo == null) {
/* 917 */       localCmtInfo = new CmtInfo();
/* 918 */       mLaunchers.put(paramString, localCmtInfo);
/*     */     }
/* 920 */     return localCmtInfo;
/*     */   }
/*     */ 
/*     */   public static class CmtInfo
/*     */   {
/*     */     public String templateVersion;
/*     */     public String plusLauncher;
/*     */     public String sfd;
/* 932 */     public boolean rptCrs = true;
/*     */ 
/* 934 */     public boolean rptJse = true;
/*     */ 
/* 936 */     public boolean needUpdate = true;
/*     */   }
/*     */ 
/*     */   public static class BaseAppInfo
/*     */   {
/* 655 */     public boolean mMoreRecent = true;
/* 656 */     public boolean mDeleted = false;
/* 657 */     String mAppid = null;
/* 658 */     public String mAppVer = null;
/*     */ 
/* 660 */     public BaseAppInfo(String paramString1, String paramString2) { this.mAppid = paramString1;
/* 661 */       this.mAppVer = paramString2; }
/*     */ 
/*     */     boolean high(BaseAppInfo paramBaseAppInfo)
/*     */     {
/* 665 */       return compareVersion(this.mAppVer, paramBaseAppInfo.mAppVer);
/*     */     }
/*     */ 
/*     */     public static final boolean compareVersion(String paramString1, String paramString2)
/*     */     {
/* 675 */       boolean bool = false;
/*     */       try {
/* 677 */         String[] arrayOfString1 = paramString1.split("\\.");
/* 678 */         String[] arrayOfString2 = paramString2.split("\\.");
/* 679 */         int i = arrayOfString1.length;
/* 680 */         int j = arrayOfString2.length;
/* 681 */         for (int k = 0; k < i; k++) {
/* 682 */           if (k >= j) {
/* 683 */             bool = true;
/* 684 */             break;
/*     */           }
/* 686 */           int m = Integer.parseInt(arrayOfString1[k]);
/* 687 */           int n = Integer.parseInt(arrayOfString2[k]);
/* 688 */           if (m > n) {
/* 689 */             bool = true;
/* 690 */             break;
/* 691 */           }if (m < n) {
/* 692 */             bool = false;
/* 693 */             break;
/*     */           }
/*     */         }
/*     */       } catch (NumberFormatException localNumberFormatException) {
/* 697 */         localNumberFormatException.printStackTrace();
/*     */       }
/* 699 */       return bool;
/*     */     }
/*     */ 
/*     */     public void saveToBundleData()
/*     */     {
/* 708 */       PlatformUtil.setBundleData(BaseInfo.PDR, this.mAppid + "_" + "appver", this.mAppVer);
/* 709 */       PlatformUtil.setBundleData(BaseInfo.PDR, this.mAppid + "_" + "deleted", String.valueOf(this.mDeleted));
/* 710 */       String str = BaseInfo.access$000();
/* 711 */       StringBuffer localStringBuffer = new StringBuffer();
/* 712 */       if (!PdrUtil.isEmpty(str)) {
/* 713 */         localStringBuffer.append(str).append("|");
/*     */       }
/* 715 */       localStringBuffer.append(this.mAppid);
/* 716 */       PlatformUtil.setBundleData(BaseInfo.PDR, "apps", localStringBuffer.toString());
/*     */     }
/*     */ 
/*     */     public void clearBundleData()
/*     */     {
/* 724 */       PlatformUtil.removeBundleData(BaseInfo.PDR, this.mAppid + "_" + "appver");
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.BaseInfo
 * JD-Core Version:    0.6.2
 */