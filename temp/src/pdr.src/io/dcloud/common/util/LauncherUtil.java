/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import android.content.pm.ActivityInfo;
/*    */ import android.content.pm.PackageInfo;
/*    */ import android.content.pm.PackageManager;
/*    */ import android.content.pm.ProviderInfo;
/*    */ import android.content.pm.ResolveInfo;
/*    */ import android.os.Build.VERSION;
/*    */ import android.text.TextUtils;
/*    */ import java.util.List;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class LauncherUtil
/*    */ {
/*    */   public static String getLauncherPackageName(Context paramContext)
/*    */   {
/* 22 */     Intent localIntent = new Intent("android.intent.action.MAIN");
/* 23 */     localIntent.addCategory("android.intent.category.HOME");
/* 24 */     ResolveInfo localResolveInfo = paramContext.getPackageManager().resolveActivity(localIntent, 0);
/* 25 */     if ((localResolveInfo == null) || (localResolveInfo.activityInfo == null))
/*    */     {
/* 27 */       return "";
/*    */     }
/* 29 */     if (localResolveInfo.activityInfo.packageName.equals("android")) {
/* 30 */       return "";
/*    */     }
/* 32 */     return localResolveInfo.activityInfo.packageName;
/*    */   }
/*    */ 
/*    */   public static String getAuthorityFromPermissionDefault(Context paramContext)
/*    */   {
/* 44 */     String str = getAuthorityFromPermission(paramContext, "com.android.launcher.permission.READ_SETTINGS");
/* 45 */     if (TextUtils.isEmpty(str)) {
/* 46 */       int i = Build.VERSION.SDK_INT;
/* 47 */       if (i < 19) {
/* 48 */         return getAuthorityFromPermission(paramContext, "com.android.launcher2.permission.READ_SETTINGS");
/*    */       }
/* 50 */       return getAuthorityFromPermission(paramContext, "com.android.launcher3.permission.READ_SETTINGS");
/*    */     }
/*    */ 
/* 53 */     return str;
/*    */   }
/*    */ 
/*    */   public static String getAuthorityFromPermission(Context paramContext, String paramString)
/*    */   {
/* 69 */     if (TextUtils.isEmpty(paramString))
/* 70 */       return "";
/*    */     try
/*    */     {
/* 73 */       List localList = paramContext.getPackageManager().getInstalledPackages(8);
/* 74 */       if (localList == null) {
/* 75 */         return "";
/*    */       }
/* 77 */       for (PackageInfo localPackageInfo : localList) {
/* 78 */         ProviderInfo[] arrayOfProviderInfo1 = localPackageInfo.providers;
/* 79 */         if (arrayOfProviderInfo1 != null) {
/* 80 */           for (ProviderInfo localProviderInfo : arrayOfProviderInfo1) {
/* 81 */             if (((paramString.equals(localProviderInfo.readPermission)) || (paramString.equals(localProviderInfo.writePermission))) && 
/* 82 */               (Pattern.matches("(.)*launcher(.)?\\.settings", localProviderInfo.authority)))
/* 83 */               return localProviderInfo.authority;
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 90 */       localException.printStackTrace();
/*    */     }
/* 92 */     return "";
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.LauncherUtil
 * JD-Core Version:    0.6.2
 */