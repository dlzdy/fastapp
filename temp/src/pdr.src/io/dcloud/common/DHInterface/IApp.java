/*     */ package io.dcloud.common.DHInterface;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Intent;
/*     */ import java.io.InputStream;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public abstract interface IApp extends IAppInfo
/*     */ {
/*     */   public static final byte FS_RUNNING_MODE = 0;
/*     */   public static final byte APP_RUNNING_MODE = 1;
/*     */   public static final byte STATUS_UN_RUNNING = 1;
/*     */   public static final byte STATUS_UN_ACTIVIE = 2;
/*     */   public static final byte STATUS_ACTIVE = 3;
/*     */   public static final byte ABS_PRIVATE_WWW_DIR_APP_MODE = -1;
/*     */   public static final byte ABS_PRIVATE_WWW_DIR = 0;
/*     */   public static final byte ABS_PRIVATE_DOC_DIR = 1;
/*     */   public static final byte ABS_PUBLIC_DOCUMENTS_DIR = 2;
/*     */   public static final byte ABS_PUBLIC_DOWNLOADS_DIR = 3;
/*     */ 
/*     */   public abstract void setStatus(byte paramByte);
/*     */ 
/*     */   public abstract void applySmartUpdate();
/*     */ 
/*     */   public abstract ISmartUpdate startSmartUpdate();
/*     */ 
/*     */   public abstract void smartUpdate();
/*     */ 
/*     */   public abstract boolean isJustDownload();
/*     */ 
/*     */   public abstract void stopSmartUpdate();
/*     */ 
/*     */   public abstract void setIAppStatusListener(IAppStatusListener paramIAppStatusListener);
/*     */ 
/*     */   public abstract IAppStatusListener getIAppStatusListener();
/*     */ 
/*     */   public abstract String getPathByType(byte paramByte);
/*     */ 
/*     */   public abstract String obtainConfigProperty(String paramString);
/*     */ 
/*     */   public abstract void setConfigProperty(String paramString1, String paramString2);
/*     */ 
/*     */   public abstract void setRuntimeArgs(String paramString);
/*     */ 
/*     */   public abstract String obtainRuntimeArgs();
/*     */ 
/*     */   public abstract String obtainWebviewBaseUrl();
/*     */ 
/*     */   public abstract String obtainAdaptationJs();
/*     */ 
/*     */   public abstract String checkPrivateDirAndCopy2Temp(String paramString);
/*     */ 
/*     */   public abstract Object obtainMgrData(IMgr.MgrType paramMgrType, int paramInt, Object[] paramArrayOfObject);
/*     */ 
/*     */   public abstract boolean startFromShortCut();
/*     */ 
/*     */   public abstract String forceShortCut();
/*     */ 
/*     */   public abstract boolean isStreamApp();
/*     */ 
/*     */   public abstract boolean isCompetentStreamApp();
/*     */ 
/*     */   public abstract void addFeaturePermission(String paramString);
/*     */ 
/*     */   public abstract void addAllFeaturePermission();
/*     */ 
/*     */   public abstract boolean checkPrivateDir(String paramString);
/*     */ 
/*     */   public abstract String convert2WebviewFullPath(String paramString1, String paramString2);
/*     */ 
/*     */   public abstract String convert2RelPath(String paramString);
/*     */ 
/*     */   /** @deprecated */
/*     */   public abstract String convert2AbsFullPath(String paramString);
/*     */ 
/*     */   public abstract String convert2AbsFullPath(String paramString1, String paramString2);
/*     */ 
/*     */   public abstract String convert2LocalFullPath(String paramString1, String paramString2);
/*     */ 
/*     */   public abstract String obtainAppWebCachePath();
/*     */ 
/*     */   public abstract String obtainAppTempPath();
/*     */ 
/*     */   public abstract String obtainAppDataPath();
/*     */ 
/*     */   public abstract String obtainAppDocPath();
/*     */ 
/*     */   public abstract void setAppDocPath(String paramString);
/*     */ 
/*     */   public abstract void setAppDataPath(String paramString);
/*     */ 
/*     */   public abstract byte obtainRunningAppMode();
/*     */ 
/*     */   public abstract boolean isOnAppRunningMode();
/*     */ 
/*     */   public abstract byte obtainAppStatus();
/*     */ 
/*     */   public abstract String obtainAppId();
/*     */ 
/*     */   public abstract String obtainOriginalAppId();
/*     */ 
/*     */   public abstract InputStream obtainResInStream(String paramString);
/*     */ 
/*     */   public abstract InputStream obtainResInStream(String paramString1, String paramString2);
/*     */ 
/*     */   public abstract String obtainAppVersionName();
/*     */ 
/*     */   public abstract String obtainAppName();
/*     */ 
/*     */   public abstract String obtainAppLog();
/*     */ 
/*     */   public abstract void registerSysEventListener(ISysEventListener paramISysEventListener, ISysEventListener.SysEventType paramSysEventType);
/*     */ 
/*     */   public abstract void unregisterSysEventListener(ISysEventListener paramISysEventListener, ISysEventListener.SysEventType paramSysEventType);
/*     */ 
/*     */   public abstract void setWebAppIntent(Intent paramIntent);
/*     */ 
/*     */   public abstract void setWebAppActivity(Activity paramActivity);
/*     */ 
/*     */   public abstract Intent obtainWebAppIntent();
/*     */ 
/*     */   public abstract void showSplash();
/*     */ 
/*     */   public abstract IWebviewStateListener obtainLaunchPageStateListener();
/*     */ 
/*     */   public abstract void setLaunchPageStateListener(IWebviewStateListener paramIWebviewStateListener);
/*     */ 
/*     */   public abstract JSONObject obtainThridInfo(IApp.ConfigProperty.ThridInfo paramThridInfo);
/*     */ 
/*     */   public abstract int checkSelfPermission(String paramString);
/*     */ 
/*     */   public abstract void requestPermissions(String[] paramArrayOfString, int paramInt);
/*     */ 
/*     */   public abstract String getPopGesture();
/*     */ 
/*     */   public static abstract interface IAppStatusListener
/*     */   {
/*     */     public abstract void onStart();
/*     */ 
/*     */     public abstract void onPause(IApp paramIApp1, IApp paramIApp2);
/*     */ 
/*     */     public abstract boolean onStop();
/*     */ 
/*     */     public abstract String onStoped(boolean paramBoolean, String paramString);
/*     */   }
/*     */ 
/*     */   public static abstract interface ConfigProperty
/*     */   {
/*     */     public static final String CONFIG_VERSION = "version";
/*     */     public static final String CONFIG_VERSION_NAME = "name";
/*     */     public static final String CONFIG_VERSION_CODE = "code";
/*     */     public static final String CONFIG_RUNMODE = "runmode";
/*     */     public static final String CONFIG_RUNMODE_NORMAL = "normal";
/*     */     public static final String CONFIG_RUNMODE_LIBERATE = "liberate";
/*     */     public static final String CONFIG_ID = "id";
/*     */     public static final String CONFIG_NAME = "name";
/*     */     public static final String CONFIG_VALUE = "value";
/*     */     public static final String CONFIG_DESCRIPTION = "description";
/*     */     public static final String CONFIG_ADAPTATION = "adaptation";
/*     */     public static final String CONFIG_DEVELOPER = "developer";
/*     */     public static final String CONFIG_DEVELOPER_NAME = "name";
/*     */     public static final String CONFIG_DEVELOPER_EMAIL = "email";
/*     */     public static final String CONFIG_DEVELOPER_URL = "url";
/*     */     public static final String CONFIG_LICENSE = "license";
/*     */     public static final String CONFIG_LICENSE_URL = "url";
/*     */     public static final String CONFIG_LICENSE_DESCRIPTION = "description";
/*     */     public static final String CONFIG_LAUNCH_PATH = "launch_path";
/*     */     public static final String CONFIG_LAUNCH_PATH_W2A = "launch_path_w2a";
/*     */     public static final String CONFIG_SRC = "src";
/*     */     public static final String CONFIG_BASEURL = "baseUrl";
/*     */     public static final String CONFIG_SPLASHSCREEN = "splashscreen";
/*     */     public static final String CONFIG_WAITING = "waiting";
/*     */     public static final String CONFIG_PLUS = "plus";
/*     */     public static final String CONFIG_PERMISSIONS = "permissions";
/*     */     public static final String CONFIG_FEATURE = "feature";
/*     */     public static final String CONFIG_MODULE = "module";
/*     */     public static final String CONFIG_FEATURES = "features";
/*     */     public static final String CONFIG_SERVICES = "services";
/*     */     public static final String CONFIG_SERVICE = "service";
/*     */     public static final String CONFIG_COVER = "cover";
/*     */     public static final String CONFIG_AUTOCLOSE_W2A = "autoclose_w2a";
/*     */     public static final String CONFIG_DELAY_W2A = "delay_w2a";
/*     */     public static final String CONFIG_AUTOCLOSE = "autoclose";
/*     */     public static final String CONFIG_EVENT = "event";
/*     */     public static final String CONFIG_TARGET = "target";
/*     */     public static final String CONFIG_DELAY = "delay";
/*     */     public static final String CONFIG_TIMEOUT = "timeout";
/*     */     public static final String CONFIG_FULLSCREEN = "fullscreen";
/*     */     public static final String CONFIG_USER_AGENT = "useragent";
/*     */     public static final String CONFIG_CONCATENATE = "concatenate";
/*     */     public static final String CONFIG_H5PLUS = "h5plus";
/*     */     public static final String CONFIG_ERROR_PAGE = "error";
/*     */     public static final String CONFIG_RAM_CACHE_MODE = "ramcachemode";
/*     */     public static final String CONFIG_USE_ENCRYPTION = "use_encryption";
/*     */     public static final String CONFIG_CONFUSION = "confusion";
/*     */     public static final String CONFIG_RESOURCES = "resources";
/*     */     public static final String CONFIG_ALGORITHM = "algorithm";
/*     */     public static final String CONFIG_KEY = "key";
/*     */     public static final String CONFIG_STREAM = "stream";
/*     */     public static final String CONFIG_COMPETENT = "competent";
/*     */     public static final String CONFIG_SHORTCUT = "shortcut";
/*     */     public static final String CONFIG_CERS = "cers";
/*     */     public static final String CONFIG_CRASH = "crash";
/*     */     public static final String CONFIG_JSERROR = "jserror";
/*     */     public static final String CONFIG_CACHE = "cache";
/*     */     public static final String CONFIG_LAUNCHWEBVIEW = "launchwebview";
/*     */     public static final String CONFIG_SECONDWEBVIEW = "secondwebview";
/*     */     public static final String CONFIG_NAVIGATIONBAR = "navigationbar";
/*     */     public static final String CONFIG_SECONDWEBVIEW_MODE = "mode";
/*     */     public static final String CONFIG_OVERRIDEURL = "overrideurl";
/*     */     public static final String CONFIG_OVERRIDE_RESOURCE = "overrideresource";
/*     */     public static final String CONFIG_INJECTION = "injection";
/*     */     public static final String CONFIG_PLUSREQUIRE = "plusrequire";
/*     */     public static final String CONFIG_LPLUSERQUIRE = "L_plusrequire";
/*     */     public static final String CONFIG_SPLUSERQUIRE = "S_pluserquire";
/*     */     public static final String CONFIG_GEOLOCATION = "geolocation";
/*     */     public static final String CONFIG_LGEOLOCATION = "L_geolocation";
/*     */     public static final String CONFIG_SGEOLOCATION = "S_geolocation";
/*     */     public static final String CONFIG_UNTRUSTEDCA = "untrustedca";
/*     */     public static final String CONFIG_SSL = "ssl";
/*     */     public static final String CONFIG_ERROR_PAGE_URL = "url";
/*     */     public static final String CONFIG_QIHOO_360 = "qihoo";
/*     */     public static final String CONFIG_LOADED_TIME = "loadedTime";
/*     */ 
/*     */     public static enum ThridInfo
/*     */     {
/* 421 */       QIHOO, 
/* 422 */       OverrideUrlJsonData, 
/* 423 */       OverrideResourceJsonData, 
/* 424 */       SecondWebviewJsonData, 
/* 425 */       NavigationbarJsonData;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract interface Name
/*     */   {
/*     */     public static final int APP = 1;
/*     */     public static final int STORAGE = 2;
/*     */     public static final int STREAM = 3;
/*     */     public static final int UPDATE = 4;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IApp
 * JD-Core Version:    0.6.2
 */