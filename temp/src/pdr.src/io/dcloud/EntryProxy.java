/*     */ package io.dcloud;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import android.util.Log;
/*     */ import android.webkit.CookieSyncManager;
/*     */ import android.widget.FrameLayout;
/*     */ import io.dcloud.application.DCloudApplication;
/*     */ import io.dcloud.common.DHInterface.ICore;
/*     */ import io.dcloud.common.DHInterface.ICore.ICoreStatusListener;
/*     */ import io.dcloud.common.DHInterface.IOnCreateSplashView;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.b.b;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.feature.internal.sdk.SDK.IntegratedMode;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class EntryProxy
/*     */ {
/*  29 */   private ArrayList<Activity> d = new ArrayList(1);
/*  30 */   public static boolean a = false;
/*  31 */   private static EntryProxy e = null;
/*  32 */   boolean b = false;
/*  33 */   b c = null;
/*     */ 
/*     */   public static EntryProxy init(Activity paramActivity, ICore.ICoreStatusListener paramICoreStatusListener)
/*     */   {
/*  42 */     a = true;
/*  43 */     Context localContext = paramActivity.getApplicationContext();
/*  44 */     DCloudApplication.setInstance(localContext);
/*  45 */     AndroidResources.initAndroidResources(localContext);
/*  46 */     if (e != null)
/*     */     {
/*  48 */       if (e.c.b() != localContext) {
/*  49 */         e.destroy(paramActivity);
/*     */       }
/*     */     }
/*  52 */     if (e == null) {
/*  53 */       e = new EntryProxy();
/*  54 */       CookieSyncManager.createInstance(localContext);
/*     */ 
/*  57 */       e.c = new b(localContext, paramICoreStatusListener);
/*     */     }
/*  59 */     e.d.add(paramActivity);
/*  60 */     return e;
/*     */   }
/*     */ 
/*     */   public static EntryProxy init(Activity paramActivity)
/*     */   {
/*  68 */     return init(paramActivity, null);
/*     */   }
/*     */   public static EntryProxy getInstnace() {
/*  71 */     return e;
/*     */   }
/*     */ 
/*     */   public boolean didCreate() {
/*  75 */     return this.b;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean onCreate(Bundle paramBundle, FrameLayout paramFrameLayout, SDK.IntegratedMode paramIntegratedMode, IOnCreateSplashView paramIOnCreateSplashView) {
/*  80 */     return onCreate(paramBundle, paramIntegratedMode, paramIOnCreateSplashView);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean onCreate(Bundle paramBundle, SDK.IntegratedMode paramIntegratedMode, IOnCreateSplashView paramIOnCreateSplashView)
/*     */   {
/*  91 */     return onCreate((Activity)this.d.get(this.d.size() - 1), paramBundle, paramIntegratedMode, paramIOnCreateSplashView);
/*     */   }
/*     */   public boolean onCreate(Activity paramActivity, Bundle paramBundle, SDK.IntegratedMode paramIntegratedMode, IOnCreateSplashView paramIOnCreateSplashView) {
/*  94 */     AndroidResources.initAndroidResources(paramActivity.getBaseContext());
/*  95 */     this.c.a(paramActivity, paramBundle, paramIntegratedMode, paramIOnCreateSplashView);
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public boolean onCreate(Bundle paramBundle)
/*     */   {
/* 104 */     return onCreate(paramBundle, null, null, null);
/*     */   }
/*     */   public boolean onActivityExecute(Activity paramActivity, ISysEventListener.SysEventType paramSysEventType, Object paramObject) {
/* 107 */     if (this.c != null)
/* 108 */       return this.c.a(paramActivity, paramSysEventType, paramObject);
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   public void onPause(Activity paramActivity) {
/* 113 */     if (this.c != null) this.c.b(paramActivity);
/* 114 */     CookieSyncManager.getInstance().stopSync();
/*     */   }
/*     */ 
/*     */   public void onResume(Activity paramActivity) {
/* 118 */     if (this.c != null) this.c.c(paramActivity);
/* 119 */     CookieSyncManager.getInstance().startSync();
/*     */   }
/*     */ 
/*     */   public void onNewIntent(Activity paramActivity, Intent paramIntent) {
/* 123 */     if (this.c != null) this.c.a(paramActivity, paramIntent); 
/*     */   }
/*     */ 
/*     */   public void onConfigurationChanged(Activity paramActivity, int paramInt)
/*     */   {
/* 127 */     if (this.c != null) this.c.a(paramActivity, paramInt); 
/*     */   }
/*     */ 
/*     */   public void onStop(Activity paramActivity)
/*     */   {
/*     */     try { this.d.remove(paramActivity);
/* 133 */       if (this.d.size() == 0)
/* 134 */         if (this.c != null) {
/* 135 */           if (this.c.a(paramActivity))
/* 136 */             clearData();
/*     */         }
/*     */         else
/* 139 */           clearData();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 143 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void clearData() {
/* 148 */     Log.d("EntryProxy", " clearData");
/* 149 */     e = null;
/* 150 */     a = false;
/* 151 */     this.b = false;
/* 152 */     AndroidResources.clearData();
/* 153 */     BaseInfo.clearData();
/* 154 */     this.c = null;
/*     */   }
/*     */ 
/*     */   public void destroy(Activity paramActivity)
/*     */   {
/* 159 */     onStop(paramActivity);
/*     */   }
/*     */ 
/*     */   public ICore getCoreHandler() {
/* 163 */     return this.c.a();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.EntryProxy
 * JD-Core Version:    0.6.2
 */