/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.app.ActivityManager;
/*    */ import android.app.ActivityManager.RunningServiceInfo;
/*    */ import android.content.ComponentName;
/*    */ import android.content.Context;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ServiceUtil
/*    */ {
/*    */   public static boolean isServiceRunning(Context paramContext, Class<?> paramClass)
/*    */   {
/* 12 */     ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
/*    */ 
/* 14 */     List localList = localActivityManager.getRunningServices(1024);
/*    */ 
/* 16 */     for (ActivityManager.RunningServiceInfo localRunningServiceInfo : localList) {
/* 17 */       if (paramClass.getName().equals(localRunningServiceInfo.service.getClassName())) {
/* 18 */         return true;
/*    */       }
/*    */     }
/* 21 */     return false;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ServiceUtil
 * JD-Core Version:    0.6.2
 */