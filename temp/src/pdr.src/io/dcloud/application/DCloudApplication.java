/*    */ package io.dcloud.application;
/*    */ 
/*    */ import android.app.Application;
/*    */ import android.content.Context;
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ import io.dcloud.common.adapter.util.UEH;
/*    */ 
/*    */ public class DCloudApplication extends Application
/*    */ {
/* 14 */   private static final String a = DCloudApplication.class.getSimpleName();
/*    */ 
/* 20 */   public boolean isQihooTrafficFreeValidate = true;
/*    */   private static DCloudApplication b;
/* 24 */   private static Context c = null;
/*    */ 
/* 26 */   public static Context getInstance() { return c; }
/*    */ 
/*    */   public static void setInstance(Context paramContext)
/*    */   {
/* 30 */     if (c == null)
/* 31 */       c = paramContext;
/*    */   }
/*    */ 
/*    */   public static DCloudApplication self() {
/* 35 */     return b;
/*    */   }
/*    */ 
/*    */   public void onCreate()
/*    */   {
/* 40 */     super.onCreate();
/* 41 */     b = this;
/* 42 */     setInstance(getApplicationContext());
/* 43 */     UEH.catchUncaughtException(getApplicationContext());
/*    */   }
/*    */ 
/*    */   public void onLowMemory()
/*    */   {
/* 49 */     super.onLowMemory();
/* 50 */     Logger.e(a, "onLowMemory" + Runtime.getRuntime().maxMemory());
/*    */   }
/*    */ 
/*    */   public void onTrimMemory(int paramInt)
/*    */   {
/* 55 */     super.onTrimMemory(paramInt);
/* 56 */     Logger.e(a, "onTrimMemory");
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.application.DCloudApplication
 * JD-Core Version:    0.6.2
 */