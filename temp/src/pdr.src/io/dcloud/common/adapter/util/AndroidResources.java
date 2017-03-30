/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.pm.ApplicationInfo;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.content.pm.PackageManager.NameNotFoundException;
/*     */ import android.content.res.AssetManager;
/*     */ import android.content.res.Resources;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Bundle;
/*     */ import io.dcloud.common.adapter.ui.AdaWebview;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public abstract class AndroidResources
/*     */ {
/*     */   public static Resources mResources;
/*     */   public static PackageInfo mApplicationInfo;
/*  33 */   static AssetManager sAssetMgr = null;
/*     */ 
/*  35 */   public static String versionName = null;
/*     */   public static int versionCode;
/*  37 */   private static String packageName = null;
/*     */ 
/*  64 */   public static boolean sIMEAlive = false;
/*  65 */   public static boolean splashBacking = false;
/*  66 */   static Bundle sMetaDatas = null;
/*     */ 
/*     */   public static void initAndroidResources(Context paramContext)
/*     */   {
/*  39 */     if (mResources != null) return;
/*  40 */     mResources = paramContext.getResources();
/*  41 */     DeviceInfo.sApplicationContext = paramContext;
/*  42 */     sAssetMgr = paramContext.getAssets();
/*  43 */     ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
/*     */     try {
/*  45 */       packageName = localApplicationInfo.packageName;
/*  46 */       mApplicationInfo = paramContext.getPackageManager().getPackageInfo(packageName, 1);
/*  47 */       versionName = mApplicationInfo.versionName;
/*  48 */       versionCode = mApplicationInfo.versionCode;
/*     */     } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
/*  50 */       localNameNotFoundException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void clearData()
/*     */   {
/*  56 */     CanvasHelper.clearData();
/*  57 */     AdaWebview.clearData();
/*  58 */     mResources = null;
/*  59 */     sAssetMgr = null;
/*  60 */     mApplicationInfo = null;
/*     */   }
/*     */ 
/*     */   public static String getMetaValue(String paramString)
/*     */   {
/*  74 */     if (sMetaDatas == null) {
/*     */       try {
/*  76 */         sMetaDatas = DeviceInfo.sApplicationContext.getPackageManager().getApplicationInfo(packageName, 128).metaData;
/*     */       } catch (Exception localException) {
/*  78 */         localException.printStackTrace();
/*  79 */         return null;
/*     */       }
/*     */     }
/*  82 */     if ((sMetaDatas != null) && 
/*  83 */       (!PdrUtil.isEmpty(sMetaDatas.get(paramString)))) {
/*  84 */       return String.valueOf(sMetaDatas.get(paramString));
/*     */     }
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   public static void setMetaValue(String paramString1, String paramString2) {
/*  90 */     if (sMetaDatas == null) {
/*     */       try {
/*  92 */         sMetaDatas = DeviceInfo.sApplicationContext.getPackageManager().getApplicationInfo(packageName, 128).metaData;
/*     */       } catch (Exception localException) {
/*  94 */         localException.printStackTrace();
/*     */       }
/*     */     }
/*  97 */     if (sMetaDatas != null) {
/*  98 */       sMetaDatas.putString(paramString1, paramString2);
/*  99 */       System.out.println("meta data = " + sMetaDatas.get(paramString1));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getIdentifier(String paramString1, String paramString2) {
/* 104 */     int i = 0;
/* 105 */     if (mResources != null) {
/* 106 */       i = mResources.getIdentifier(paramString1, paramString2, packageName);
/*     */     }
/* 108 */     return i;
/*     */   }
/*     */ 
/*     */   public static int getIdentifierFromApk(Context paramContext, String paramString1, String paramString2)
/*     */   {
/*     */     try
/*     */     {
/* 121 */       Context localContext = paramContext.createPackageContext(paramContext.getPackageName(), 2);
/* 122 */       return localContext.getResources().getIdentifier(paramString1, paramString2, paramContext.getPackageName());
/*     */     } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
/* 124 */       localNameNotFoundException.printStackTrace();
/*     */     }
/*     */ 
/* 127 */     return 0;
/*     */   }
/*     */ 
/*     */   public static String getString(int paramInt)
/*     */   {
/* 140 */     if (mResources != null) {
/* 141 */       return mResources.getString(paramInt);
/*     */     }
/* 143 */     return "";
/*     */   }
/*     */ 
/*     */   public static boolean checkImmersedStatusBar(Context paramContext)
/*     */   {
/*     */     try
/*     */     {
/* 154 */       if (Build.VERSION.SDK_INT < 19) {
/* 155 */         return false;
/*     */       }
/* 157 */       ApplicationInfo localApplicationInfo = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
/*     */ 
/* 160 */       if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
/* 161 */         return localApplicationInfo.metaData.getBoolean("immersed.status.bar", false);
/*     */     }
/*     */     catch (PackageManager.NameNotFoundException localNameNotFoundException)
/*     */     {
/* 165 */       localNameNotFoundException.printStackTrace();
/*     */     }
/* 167 */     return false;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.AndroidResources
 * JD-Core Version:    0.6.2
 */