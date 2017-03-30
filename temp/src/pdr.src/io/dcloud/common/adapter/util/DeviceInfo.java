/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.ActivityManager;
/*     */ import android.app.ActivityManager.MemoryInfo;
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Rect;
/*     */ import android.net.ConnectivityManager;
/*     */ import android.net.NetworkInfo;
/*     */ import android.os.Build;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Environment;
/*     */ import android.os.IBinder;
/*     */ import android.provider.Settings.System;
/*     */ import android.telephony.TelephonyManager;
/*     */ import android.telephony.gsm.GsmCellLocation;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.Display;
/*     */ import android.view.View;
/*     */ import android.view.Window;
/*     */ import android.view.WindowManager;
/*     */ import android.view.inputmethod.InputMethodManager;
/*     */ import io.dcloud.application.DCloudApplication;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.NetworkTypeUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.TelephonyUtil;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FileReader;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class DeviceInfo
/*     */ {
/*     */   private static final String TAG = "DeviceInfo";
/*     */   public static float sDensity;
/*  54 */   public static int sDeviceSdkVer = Build.VERSION.SDK_INT;
/*     */   public static String sCacheRootDir;
/*     */   public static String sBaseFsCachePath;
/*     */   public static String sDeviceRootDir;
/*     */   public static String sBaseFsRootPath;
/*     */   public static String sBaseFsRootFullPath;
/*     */   public static String sBaseResRootFullPath;
/*     */   public static final String FILE_PROTOCOL = "file://";
/*     */   public static final String HTTP_PROTOCOL = "http://";
/*     */   public static final String HTTPS_PROTOCOL = "https://";
/*  74 */   public static String sModel = Build.MODEL;
/*     */ 
/*  76 */   public static String sBrand = Build.BRAND;
/*     */ 
/*  78 */   public static long sTotalMem = -1L;
/*  79 */   public static int sCoreNums = -1;
/*     */   public static String sIMEI;
/*     */   public static String sIMSI;
/*  85 */   public static String sVendor = Build.MANUFACTURER;
/*     */ 
/*  87 */   public static String sVersion_release = Build.VERSION.RELEASE;
/*     */ 
/*  89 */   public static String sLanguage = Locale.getDefault().getLanguage();
/*     */   public static float dpiX;
/*     */   public static float dpiY;
/*     */   public static String sWifiAddr;
/*     */   public static String sBSSID;
/*  98 */   public static boolean sNetWorkInited = false;
/*  99 */   private static GsmCellLocation sCellLocation = null;
/*     */ 
/* 101 */   public static String sDeftDataNetwork = "gsm";
/*     */ 
/* 103 */   public static String sSimOperator = null;
/*     */ 
/* 105 */   public static char sSeparatorChar = File.separatorChar;
/*     */ 
/* 108 */   public static String sBaseResRootPathName = String.valueOf(sSeparatorChar);
/*     */ 
/* 110 */   public static String sPackageName = null;
/*     */   private static final String DEFAULT_DATA_NETWORK = "default_data_network";
/*     */   private static final String SAVED_DATA_NETWORK = "saved_data_network";
/*     */   private static final String CDMA_DATA_NETWORK = "cdma";
/*     */   private static final String GSM_DATA_NETWORK = "gsm";
/*     */   private static final String NONE_DATA_NETWORK = "none";
/* 122 */   public static String DEVICESTATUS_JS = null;
/*     */ 
/* 124 */   public static Paint sPaint = new Paint();
/*     */ 
/* 126 */   public static float DEFAULT_FONT_SIZE = sPaint.getTextSize();
/*     */   public static final int OSTYPE_ANDROID = 0;
/*     */   public static final int OSTYPE_OMS20 = 1;
/*     */   public static final int OSTYPE_OMS15 = 2;
/*     */   public static final int OSTYPE_OMS10 = 3;
/*     */   public static final int OSTYPE_LEOS10 = 4;
/* 133 */   public static int osType = 0;
/*     */ 
/* 137 */   public static Context sApplicationContext = null;
/* 138 */   static ConnectivityManager sConnectMgr = null;
/*     */   public static final String SWITCH_DIRECTORY = "io.dcloud.streamapp";
/* 141 */   public static boolean isSwitchDirectory = false;
/*     */ 
/* 320 */   private static String CONNECTION_UNKNOW = "0";
/* 321 */   private static String NETTYPE_NONE = "1";
/* 322 */   private static String CONNECTION_ETHERNET = "2";
/* 323 */   private static String NETTYPE_WIFI = "3";
/* 324 */   private static String CONNECTION_CELL2G = "4";
/* 325 */   private static String CONNECTION_CELL3G = "5";
/* 326 */   private static String CONNECTION_CELL4G = "6";
/*     */ 
/* 600 */   public static int sStatusBarHeight = 0;
/*     */ 
/* 634 */   public static int HARDWAREACCELERATED_WINDOW = 0;
/* 635 */   public static int HARDWAREACCELERATED_VIEW = 1;
/*     */ 
/*     */   public static void init(Context paramContext)
/*     */   {
/* 156 */     sDeviceSdkVer = Build.VERSION.SDK_INT;
/* 157 */     sModel = Build.MODEL;
/* 158 */     if ("OMAP_SS".equals(sModel)) {
/* 159 */       osType = 1;
/*     */     }
/* 161 */     else if ("OMS1_5".equals(sModel)) {
/* 162 */       osType = 2;
/*     */     }
/* 164 */     else if ("generic".equals(sModel)) {
/* 165 */       osType = 3;
/*     */     }
/* 167 */     sBrand = Build.BRAND;
/* 168 */     sVendor = Build.MANUFACTURER;
/* 169 */     sLanguage = Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
/* 170 */     DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
/* 171 */     sDensity = localDisplayMetrics.density;
/* 172 */     dpiX = localDisplayMetrics.xdpi;
/* 173 */     dpiY = localDisplayMetrics.ydpi;
/*     */ 
/* 176 */     Logger.i("DeviceInfo", "init() sWifiAddr=" + sWifiAddr + ";sDeviceSdkVer=" + sDeviceSdkVer + ";sModel=" + sModel + ";sBrand=" + sBrand + ";sVendor=" + sVendor + ";sLanguage=" + sLanguage + ";dpiX=" + dpiX + ";dpiY=" + dpiY + ";package=" + paramContext.getPackageName());
/*     */ 
/* 180 */     sConnectMgr = (ConnectivityManager)paramContext.getSystemService("connectivity");
/*     */   }
/*     */ 
/*     */   public static void initBaseFsRootPath()
/*     */   {
/* 186 */     initPath(sApplicationContext);
/*     */   }
/*     */ 
/*     */   public static void initPath(Context paramContext) {
/* 190 */     AndroidResources.initAndroidResources(paramContext);
/* 191 */     sApplicationContext = paramContext;
/* 192 */     sPackageName = paramContext.getPackageName();
/* 193 */     if (isSwitchDirectory) {
/* 194 */       sPackageName = "io.dcloud.streamapp";
/*     */     }
/* 196 */     if (sDeviceRootDir != null) return;
/* 197 */     boolean bool1 = BaseInfo.isBase(sApplicationContext);
/* 198 */     bool1 = sPackageName.equals("io.dcloud.HBuilder");
/* 199 */     String str1 = "";
/* 200 */     boolean bool2 = isSDcardExists();
/* 201 */     int i = !BaseInfo.ISDEBUG ? 1 : 0;
/*     */ 
/* 203 */     i = 0;
/*     */ 
/* 205 */     if (bool1)
/* 206 */       str1 = ".HBuilder/";
/* 207 */     else if (!BaseInfo.isForQihooHelper(sApplicationContext));
/* 210 */     String str2 = sBaseFsRootPath;
/* 211 */     if (bool2)
/*     */     {
/* 213 */       if (str2 == null) {
/* 214 */         if (sDeviceSdkVer >= 8) {
/* 215 */           sDeviceRootDir = Environment.getExternalStorageDirectory().getPath();
/*     */ 
/* 219 */           File localFile = null;
/*     */ 
/* 221 */           localFile = paramContext.getExternalFilesDir(Environment.DIRECTORY_DCIM);
/*     */ 
/* 225 */           if (localFile != null) {
/* 226 */             String str3 = localFile.getAbsolutePath();
/* 227 */             int j = str3.indexOf(paramContext.getPackageName());
/*     */ 
/* 229 */             str2 = str3.substring(0, j) + sPackageName + sSeparatorChar + str1;
/* 230 */             if (!str2.startsWith(sDeviceRootDir))
/*     */             {
/* 232 */               sDeviceRootDir = str2.substring(0, str2.indexOf("Android") - 1);
/*     */             }
/*     */           } else {
/* 235 */             str2 = "/sdcard/android/data/" + sPackageName + sSeparatorChar + str1;
/*     */           }
/*     */         }
/*     */         else {
/* 239 */           str2 = "/sdcard/android/data/" + sPackageName + sSeparatorChar + str1;
/*     */         }
/*     */       }
/* 242 */       if ((!isSwitchDirectory) && (BaseInfo.isGlobal(sApplicationContext))) {
/* 243 */         str2 = sDeviceRootDir + "/.system/streamapp/";
/*     */       }
/* 245 */       sCacheRootDir = sDeviceRootDir;
/* 246 */       sBaseFsRootPath = DeviceInfo.sBaseFsCachePath = str2;
/* 247 */       if (i != 0) {
/* 248 */         sDeviceRootDir = paramContext.getFilesDir().getParent() + sSeparatorChar;
/* 249 */         sBaseFsRootPath = sDeviceRootDir + str1;
/*     */       }
/*     */     }
/*     */     else {
/* 253 */       BaseInfo.ISDEBUG = false;
/*     */ 
/* 255 */       sDeviceRootDir = paramContext.getFilesDir().getParent() + sSeparatorChar;
/* 256 */       sCacheRootDir = sDeviceRootDir;
/* 257 */       sBaseFsCachePath = DeviceInfo.sBaseFsRootPath = sDeviceRootDir + str1;
/*     */     }
/* 259 */     updatePath();
/*     */   }
/*     */ 
/*     */   public static void updatePath()
/*     */   {
/* 266 */     sBaseFsRootFullPath = "file://" + sBaseFsRootPath;
/* 267 */     sBaseResRootFullPath = "file:///android_asset/";
/* 268 */     StringBuffer localStringBuffer = new StringBuffer();
/* 269 */     localStringBuffer.append("sPackageName=" + sPackageName).append(";\n");
/* 270 */     localStringBuffer.append("sDeviceRootDir=" + sDeviceRootDir).append(";\n");
/* 271 */     localStringBuffer.append("sBaseFsRootPath=" + sBaseFsRootPath).append(";\n");
/* 272 */     localStringBuffer.append("sBaseFsRootFullPath=" + sBaseFsRootFullPath).append(";\n");
/* 273 */     localStringBuffer.append("sBaseResRootFullPath=" + sBaseResRootFullPath).append(";\n");
/* 274 */     Logger.d("DeviceInfo", localStringBuffer.toString());
/* 275 */     BaseInfo.updateBaseInfo();
/*     */   }
/*     */ 
/*     */   private static String intToIp(int paramInt)
/*     */   {
/* 280 */     return (paramInt & 0xFF) + "." + (paramInt >> 8 & 0xFF) + "." + (paramInt >> 16 & 0xFF) + "." + (paramInt >> 24 & 0xFF);
/*     */   }
/*     */ 
/*     */   public static boolean isOMS()
/*     */   {
/* 289 */     if ((osType == 1) || (osType == 2) || (osType == 3))
/*     */     {
/* 291 */       return true;
/*     */     }
/* 293 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean wifiEnabled()
/*     */   {
/* 298 */     if ((PdrUtil.isEmpty(sWifiAddr)) || (PdrUtil.isEmpty(sBSSID))) {
/* 299 */       return false;
/*     */     }
/* 301 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean isSDcardExists()
/*     */   {
/* 313 */     String str = Environment.getExternalStorageState();
/* 314 */     return "mounted".equals(str);
/*     */   }
/*     */ 
/*     */   public static String getNetWorkType()
/*     */   {
/* 337 */     String str = NETTYPE_NONE;
/* 338 */     if (sConnectMgr != null)
/*     */     {
/* 340 */       if (sConnectMgr.getActiveNetworkInfo() != null) {
/* 341 */         str = CONNECTION_UNKNOW;
/* 342 */         if (sConnectMgr.getActiveNetworkInfo().getType() == 1) {
/* 343 */           str = NETTYPE_WIFI;
/* 344 */         } else if (sConnectMgr.getActiveNetworkInfo().getType() == 0) {
/* 345 */           int i = sConnectMgr.getActiveNetworkInfo().getSubtype();
/* 346 */           switch (i) {
/*     */           case 1:
/*     */           case 2:
/*     */           case 4:
/*     */           case 7:
/* 351 */             str = CONNECTION_CELL2G;
/* 352 */             break;
/*     */           case 5:
/*     */           case 6:
/*     */           case 12:
/*     */           case 14:
/* 358 */             str = CONNECTION_CELL3G;
/* 359 */             break;
/*     */           case 3:
/*     */           case 8:
/* 363 */             str = CONNECTION_CELL3G;
/* 364 */             break;
/*     */           case 17:
/*     */           case 18:
/* 368 */             str = CONNECTION_CELL3G;
/* 369 */             break;
/*     */           case 9:
/*     */           case 10:
/*     */           case 11:
/*     */           case 13:
/*     */           case 15:
/* 375 */             str = CONNECTION_CELL4G;
/* 376 */             break;
/*     */           case 16:
/*     */           default:
/* 378 */             str = "" + i;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 384 */     return str;
/*     */   }
/*     */ 
/*     */   public static String getCurrentAPN() {
/* 388 */     return NetworkTypeUtil.getCurrentAPN(DCloudApplication.getInstance().getApplicationContext());
/*     */   }
/*     */ 
/*     */   public static boolean startsWithSdcard(String paramString)
/*     */   {
/* 398 */     return (paramString.startsWith(sDeviceRootDir)) || (paramString.startsWith("/sdcard/")) || (paramString.startsWith("mnt/sdcard/")) || (paramString.startsWith(sCacheRootDir));
/*     */   }
/*     */ 
/*     */   public static String getUpdateIMSI() {
/*     */     try {
/* 403 */       TelephonyManager localTelephonyManager = (TelephonyManager)sApplicationContext.getSystemService("phone");
/* 404 */       sIMSI = localTelephonyManager.getSubscriberId();
/*     */     } catch (Exception localException) {
/* 406 */       localException.printStackTrace();
/*     */     }
/* 408 */     return sIMSI;
/*     */   }
/*     */ 
/*     */   public static void initGsmCdmaCell()
/*     */   {
/* 415 */     if (!sNetWorkInited) {
/* 416 */       sDeftDataNetwork = Settings.System.getString(sApplicationContext.getContentResolver(), "default_data_network");
/* 417 */       if (sDeftDataNetwork == null) {
/* 418 */         sDeftDataNetwork = "gsm";
/*     */       }
/* 420 */       Logger.i("DefaultDataNetwork：", sDeftDataNetwork);
/* 421 */       TelephonyManager localTelephonyManager = (TelephonyManager)sApplicationContext.getSystemService("phone");
/* 422 */       int i = localTelephonyManager.getPhoneType();
/*     */ 
/* 440 */       sIMEI = TelephonyUtil.getIMEI(sApplicationContext, false);
/* 441 */       sIMSI = TelephonyUtil.getIMSI(sApplicationContext);
/* 442 */       sSimOperator = localTelephonyManager.getSimOperator();
/* 443 */       if ("none".equals(sDeftDataNetwork)) {
/* 444 */         sDeftDataNetwork = "gsm";
/* 445 */         if (i == 1) {
/* 446 */           sCellLocation = (GsmCellLocation)localTelephonyManager.getCellLocation();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 453 */       if (sIMEI == null) {
/* 454 */         sIMEI = "";
/*     */       }
/* 456 */       if (sIMSI == null) {
/* 457 */         sIMSI = "";
/*     */       }
/* 459 */       Logger.d("DeviceInfo", "IMEI=" + sIMEI);
/* 460 */       Logger.d("DeviceInfo", "IMSI=" + sIMSI);
/* 461 */       sNetWorkInited = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getNumCores()
/*     */   {
/* 472 */     if (sCoreNums != -1) return sCoreNums;
/*     */ 
/*     */     try
/*     */     {
/* 487 */       File localFile = new File("/sys/devices/system/cpu/");
/*     */ 
/* 489 */       File[] arrayOfFile = localFile.listFiles(new FileFilter()
/*     */       {
/*     */         public boolean accept(File paramAnonymousFile)
/*     */         {
/* 478 */           if (Pattern.matches("cpu[0-9]", paramAnonymousFile.getName())) {
/* 479 */             return true;
/*     */           }
/* 481 */           return false;
/*     */         }
/*     */       });
/* 491 */       sCoreNums = arrayOfFile.length;
/* 492 */       return arrayOfFile.length;
/*     */     } catch (Exception localException) {
/*     */     }
/* 495 */     return 1;
/*     */   }
/*     */ 
/*     */   public static long getAvailMemory()
/*     */   {
/* 503 */     if (sApplicationContext != null) {
/* 504 */       ActivityManager localActivityManager = (ActivityManager)sApplicationContext.getSystemService("activity");
/* 505 */       ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
/* 506 */       localActivityManager.getMemoryInfo(localMemoryInfo);
/*     */ 
/* 508 */       return localMemoryInfo.availMem;
/*     */     }
/* 510 */     return 0L;
/*     */   }
/*     */ 
/*     */   public static long getTotalMemory()
/*     */   {
/* 517 */     if (sTotalMem != -1L) return sTotalMem;
/* 518 */     String str1 = "/proc/meminfo";
/*     */ 
/* 521 */     long l = 0L;
/*     */     try
/*     */     {
/* 524 */       FileReader localFileReader = new FileReader(str1);
/* 525 */       BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
/*     */ 
/* 527 */       String str2 = localBufferedReader.readLine();
/*     */ 
/* 529 */       String[] arrayOfString = str2.split("\\s+");
/*     */ 
/* 534 */       l = Integer.valueOf(arrayOfString[1]).intValue();
/* 535 */       localBufferedReader.close();
/* 536 */       sTotalMem = l;
/*     */     } catch (Exception localException) {
/*     */     }
/* 539 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getDevicestatus_js()
/*     */   {
/* 551 */     DEVICESTATUS_JS = "(function(p){p.device.imei='%s';p.device.uuid='%s';p.device.imsi=['%s'];p.device.model='%s';p.device.vendor='%s';p.os.language='%s';p.os.version='%s';p.os.name='%s';p.os.vendor='%s';})(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus));";
/*     */ 
/* 562 */     DEVICESTATUS_JS = String.format(DEVICESTATUS_JS, new Object[] { sIMEI, sIMEI, sIMSI, sModel, sVendor, sLanguage, sVersion_release, "Android", "Google" });
/*     */ 
/* 564 */     return DEVICESTATUS_JS;
/*     */   }
/*     */ 
/*     */   public static String getPlusCache()
/*     */   {
/* 577 */     String str = "window.plus.cache = navigator.plus.cache = (function(mkey){return {clear : function(clearCB){var callbackid = mkey.helper.callbackid( function(args){if ( clearCB ) {clearCB()};}, null);mkey.exec('Cache', 'clear', [callbackId]);},calculate : function(calculateCB){var callbackid = mkey.helper.callbackid( function(args){if ( calculateCB ) {calculateCB(args)};}, null);mkey.exec('Cache', 'calculate', [callbackid]);},setMaxSize : function (size) {mkey.exec('Cache', 'setMaxSize', [size]);}};})(window.__Mkey__);";
/*     */ 
/* 596 */     return str;
/*     */   }
/*     */ 
/*     */   public static void updateStatusBarHeight(Activity paramActivity)
/*     */   {
/* 603 */     if (sStatusBarHeight == 0) {
/* 604 */       Rect localRect = new Rect();
/* 605 */       paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
/* 606 */       sStatusBarHeight = localRect.top;
/* 607 */       if (sStatusBarHeight <= 0)
/* 608 */         sStatusBarHeight = getStatusHeight(paramActivity);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean checkCoverInstallApk()
/*     */   {
/* 619 */     String str1 = PlatformUtil.getBundleData(BaseInfo.PDR, "last_apk_modify_date");
/* 620 */     String str2 = sApplicationContext.getPackageCodePath();
/* 621 */     File localFile = new File(str2);
/* 622 */     long l = localFile.lastModified();
/* 623 */     boolean bool = false;
/* 624 */     Logger.d("DeviceInfo", "old_apk_modify_date=" + str1);
/* 625 */     if (!PdrUtil.isEquals(str1, String.valueOf(l))) {
/* 626 */       PlatformUtil.setBundleData(BaseInfo.PDR, "last_apk_modify_date", String.valueOf(l));
/* 627 */       str1 = Logger.generateTimeStamp("yyyyMMdd HH:mm:ss.SSS", new Date(l));
/* 628 */       Logger.d("DeviceInfo", "new_apk_modify_date=" + l);
/* 629 */       bool = true;
/*     */     }
/* 631 */     Logger.d("DeviceInfo", "Apk Modify Date=" + str1 + ";_ret=" + bool);
/* 632 */     return bool;
/*     */   }
/*     */ 
/*     */   public static void openHardwareAccelerated(Activity paramActivity, int paramInt, Object paramObject)
/*     */   {
/* 637 */     if (paramInt == HARDWAREACCELERATED_WINDOW) {
/* 638 */       Window localWindow = (Window)paramObject;
/* 639 */       if (localWindow == null) localWindow = paramActivity != null ? paramActivity.getWindow() : null;
/* 640 */       if (localWindow != null) {
/* 641 */         localWindow.setFlags(16777216, 16777216);
/*     */       }
/*     */ 
/*     */     }
/* 646 */     else if ((paramInt == HARDWAREACCELERATED_VIEW) && 
/* 647 */       (sDeviceSdkVer >= 11)) {
/* 648 */       ((View)paramObject).setLayerType(2, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void closeHardwareAccelerated(Activity paramActivity, int paramInt, Object paramObject)
/*     */   {
/* 654 */     if (paramInt == HARDWAREACCELERATED_WINDOW) {
/* 655 */       Window localWindow = (Window)paramObject;
/* 656 */       if (localWindow == null) localWindow = paramActivity != null ? paramActivity.getWindow() : null;
/* 657 */       if (localWindow != null) {
/* 658 */         localWindow.clearFlags(16777216);
/*     */       }
/*     */     }
/* 661 */     else if ((paramInt == HARDWAREACCELERATED_VIEW) && 
/* 662 */       (sDeviceSdkVer >= 11)) {
/* 663 */       ((View)paramObject).setLayerType(1, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void hideIME(View paramView)
/*     */   {
/* 672 */     if (sApplicationContext != null) {
/* 673 */       InputMethodManager localInputMethodManager = (InputMethodManager)sApplicationContext.getSystemService("input_method");
/* 674 */       if (paramView != null) {
/* 675 */         IBinder localIBinder = paramView.getWindowToken();
/* 676 */         if (localIBinder != null)
/* 677 */           localInputMethodManager.hideSoftInputFromWindow(localIBinder, 2);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void showIME(View paramView)
/*     */   {
/* 689 */     Timer localTimer = new Timer();
/* 690 */     localTimer.schedule(new TimerTask() {
/*     */       public void run() {
/* 692 */         if (DeviceInfo.sApplicationContext != null) {
/* 693 */           InputMethodManager localInputMethodManager = (InputMethodManager)DeviceInfo.sApplicationContext.getSystemService("input_method");
/* 694 */           if (this.val$view != null)
/*     */           {
/* 698 */             localInputMethodManager.toggleSoftInput(0, 2);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     , 250L);
/*     */   }
/*     */ 
/*     */   public static int getStatusHeight(Context paramContext)
/*     */   {
/* 712 */     int i = -1;
/*     */     try {
/* 714 */       Class localClass = Class.forName("com.android.internal.R$dimen");
/* 715 */       Object localObject = localClass.newInstance();
/* 716 */       int j = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
/*     */ 
/* 718 */       i = paramContext.getResources().getDimensionPixelSize(j);
/*     */     } catch (Exception localException) {
/* 720 */       localException.printStackTrace();
/*     */     }
/* 722 */     return i;
/*     */   }
/*     */ 
/*     */   public static int getDeivceSuitablePixel(Activity paramActivity, int paramInt)
/*     */   {
/* 727 */     DisplayMetrics localDisplayMetrics = new DisplayMetrics();
/* 728 */     paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
/* 729 */     float f1 = localDisplayMetrics.density;
/* 730 */     float f2 = paramInt * f1;
/*     */ 
/* 732 */     return (int)f2;
/*     */   }
/*     */ 
/*     */   public static String getBuildValue(String paramString)
/*     */   {
/* 741 */     String str = null;
/*     */     try {
/* 743 */       Class localClass = Class.forName("android.os.SystemProperties");
/* 744 */       Method localMethod = localClass.getDeclaredMethod("get", new Class[] { String.class });
/*     */ 
/* 746 */       str = (String)localMethod.invoke(localClass, new Object[] { paramString }); } catch (Exception localException) {
/*     */     }
/* 748 */     return str;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.DeviceInfo
 * JD-Core Version:    0.6.2
 */