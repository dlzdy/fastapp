/*     */ package io.dcloud.common.constant;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.res.Resources;
/*     */ import android.os.Build;
/*     */ import android.os.Build.VERSION;
/*     */ import android.text.TextUtils;
/*     */ import android.util.DisplayMetrics;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.NetworkTypeUtil;
/*     */ import io.dcloud.common.util.TelephonyUtil;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLEncoder;
/*     */ 
/*     */ public class DataInterface
/*     */ {
/*     */   public static String getBaseUrl()
/*     */   {
/*  26 */     return StringConst.STREAMAPP_KEY_BASESERVICEURL();
/*     */   }
/*     */ 
/*     */   public static String getUrlBaseData(Context paramContext, String paramString1, String paramString2, String paramString3)
/*     */   {
/*  39 */     String str1 = TelephonyUtil.getIMEI(paramContext);
/*  40 */     int i = NetworkTypeUtil.getNetworkType(paramContext);
/*  41 */     String str2 = null;
/*     */     try {
/*  43 */       str2 = URLEncoder.encode(Build.MODEL, "utf-8");
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*  45 */       localUnsupportedEncodingException.printStackTrace();
/*     */     }
/*  47 */     int j = Build.VERSION.SDK_INT;
/*  48 */     String str3 = BaseInfo.sBaseVersion;
/*  49 */     return String.format(StringConst.T_URL_BASE_DATA, new Object[] { paramString1, str1, Integer.valueOf(i), str2, Integer.valueOf(j), str3, Integer.valueOf(StringConst.getIntSF(paramString2)), Long.valueOf(System.currentTimeMillis()), paramString3, Build.MANUFACTURER }) + getTestParam(paramString1) + "&mc=" + BaseInfo.sChannel;
/*     */   }
/*     */ 
/*     */   public static String getTestParam(String paramString)
/*     */   {
/*  60 */     String str = "r";
/*  61 */     if ((!TextUtils.isEmpty(paramString)) && (BaseInfo.isTest(paramString))) {
/*  62 */       str = "t";
/*     */     }
/*  64 */     return "&__am=" + str;
/*     */   }
/*     */ 
/*     */   public static String getStatisticsUrl(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4)
/*     */   {
/*  80 */     return String.format(StringConst.STREAMAPP_KEY_STATISTICURL, new Object[] { paramString1, paramString2, Integer.valueOf(paramInt), paramString3, paramString4, Integer.valueOf(Build.VERSION.SDK_INT), Build.MANUFACTURER }) + "&p=a" + getTestParam(paramString1) + "&mc=" + BaseInfo.sChannel;
/*     */   }
/*     */ 
/*     */   public static String getIconImageUrl(String paramString1, String paramString2)
/*     */   {
/*  93 */     return StringConst.STREAMAPP_KEY_BASESERVICEURL() + "resource/icon?appid=" + paramString1 + "&width=" + paramString2 + getTestParam(paramString1) + (DeviceInfo.sApplicationContext == null ? "" : new StringBuilder().append("&imei=").append(TelephonyUtil.getIMEI(DeviceInfo.sApplicationContext)).toString());
/*     */   }
/*     */ 
/*     */   public static String getWGTUrl(Context paramContext, String paramString1, String paramString2, String paramString3)
/*     */   {
/*  99 */     return StringConst.STREAMAPP_KEY_BASESERVICEURL() + "resource/wgt?" + getUrlBaseData(paramContext, paramString1, paramString2, paramString3);
/*     */   }
/*     */ 
/*     */   public static String getCheckUpdateUrl(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 106 */     return StringConst.STREAMAPP_KEY_BASESERVICEURL() + "check/update?appid=" + paramString1 + "&version=" + paramString2 + "&type=native" + "&imei=" + paramString3 + getTestParam(paramString1);
/*     */   }
/*     */ 
/*     */   public static String getDatailUrl(String paramString)
/*     */   {
/* 115 */     return StringConst.STREAMAPP_KEY_BASESERVICEURL() + "apps/detail?appid=" + paramString + getTestParam(paramString);
/*     */   }
/*     */ 
/*     */   public static String getSplashUrl(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 121 */     return StringConst.STREAMAPP_KEY_BASESERVICEURL() + "resource/splash?appid=" + paramString1 + "&width=" + paramString2 + "&height=" + paramString3 + getTestParam(paramString1);
/*     */   }
/*     */ 
/*     */   public static String getAppStreamUpdateUrl(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
/*     */   {
/* 128 */     StringBuilder localStringBuilder = new StringBuilder();
/* 129 */     localStringBuilder.append(paramString1).append("check/update?").append(getUrlBaseData(paramContext, paramString2, paramString4, paramString5)).append("&version=").append(paramString3).append("&plus_version=").append(BaseInfo.sBaseVersion).append("&width=").append(paramContext.getResources().getDisplayMetrics().widthPixels).append("&height=").append(paramContext.getResources().getDisplayMetrics().heightPixels);
/*     */ 
/* 141 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static String getJsonUrl(String paramString1, String paramString2, String paramString3, Context paramContext, String paramString4) {
/* 145 */     return paramString1 + "resource/stream?" + getUrlBaseData(paramContext, paramString2, paramString3, paramString4);
/*     */   }
/*     */ 
/*     */   public static String getAppListUrl(String paramString)
/*     */   {
/* 150 */     return StringConst.STREAMAPP_KEY_BASESERVICEURL() + "apps/list?t=array" + getTestParam(paramString);
/*     */   }
/*     */ 
/*     */   public static String getRomVersion()
/*     */   {
/* 157 */     if (Build.MANUFACTURER.equals(MobilePhoneModel.XIAOMI)) {
/* 158 */       String str = DeviceInfo.getBuildValue("ro.miui.ui.version.name");
/* 159 */       return Build.VERSION.INCREMENTAL + (TextUtils.isEmpty(str) ? "" : new StringBuilder().append("-").append(str).toString());
/*     */     }
/* 161 */     return Build.VERSION.INCREMENTAL;
/*     */   }
/*     */ 
/*     */   public static String getStreamappFrom(Intent paramIntent) {
/* 165 */     String str1 = null;
/* 166 */     if (paramIntent == null) {
/* 167 */       return str1;
/*     */     }
/*     */ 
/* 170 */     if (paramIntent.hasExtra("plus.runtime.launcher")) {
/* 171 */       String str2 = paramIntent.getStringExtra("plus.runtime.launcher");
/* 172 */       if (str2.indexOf("third:") == 0)
/* 173 */         str1 = str2.substring(6, str2.length());
/*     */     }
/* 175 */     else if (paramIntent.hasExtra("from")) {
/* 176 */       str1 = paramIntent.getStringExtra("from");
/*     */     }
/*     */ 
/* 179 */     return str1;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.constant.DataInterface
 * JD-Core Version:    0.6.2
 */