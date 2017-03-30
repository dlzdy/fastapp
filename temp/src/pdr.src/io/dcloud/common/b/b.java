/*     */ package io.dcloud.common.b;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.ICore;
/*     */ import io.dcloud.common.DHInterface.ICore.ICoreStatusListener;
/*     */ import io.dcloud.common.DHInterface.IOnCreateSplashView;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.constant.IntentConst;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.feature.internal.sdk.SDK.IntegratedMode;
/*     */ 
/*     */ public class b
/*     */ {
/*  38 */   Context a = null;
/*  39 */   a b = null;
/*     */ 
/* 115 */   private long c = 0L;
/*     */ 
/*     */   public b(Context paramContext, ICore.ICoreStatusListener paramICoreStatusListener)
/*     */   {
/*  41 */     this.a = paramContext;
/*  42 */     this.b = a.a(paramContext, paramICoreStatusListener);
/*     */   }
/*     */ 
/*     */   public void a(Activity paramActivity, Bundle paramBundle, SDK.IntegratedMode paramIntegratedMode, IOnCreateSplashView paramIOnCreateSplashView) {
/*  46 */     if ((paramIOnCreateSplashView != null) && (paramIntegratedMode != SDK.IntegratedMode.WEBAPP) && (paramIntegratedMode != SDK.IntegratedMode.WEBVIEW))
/*     */     {
/*  48 */       paramIOnCreateSplashView.onCreateSplash(null);
/*     */     }
/*  50 */     this.b.a(paramActivity, paramBundle, paramIntegratedMode);
/*  51 */     if ((!paramActivity.getIntent().getBooleanExtra("has_stream_splash", false)) && (BaseInfo.sRuntimeMode == null))
/*     */     {
/*  54 */       a(paramActivity, paramActivity.getIntent(), null);
/*     */     }
/*     */   }
/*     */ 
/*     */   public IApp a(Activity paramActivity, Intent paramIntent, IOnCreateSplashView paramIOnCreateSplashView) {
/*  59 */     Object localObject1 = null;
/*     */ 
/*  61 */     if (paramIntent != null) {
/*  62 */       localObject2 = paramIntent.getExtras();
/*  63 */       localObject1 = localObject2 != null ? ((Bundle)localObject2).getString("appid") : localObject1;
/*     */     }
/*  65 */     if (PdrUtil.isEmpty(localObject1)) {
/*  66 */       localObject1 = BaseInfo.sDefaultBootApp;
/*     */     }
/*     */ 
/*  69 */     Object localObject2 = IntentConst.obtainArgs(paramIntent, (String)localObject1);
/*  70 */     Logger.i("onStart appid=" + (String)localObject1 + ";intentArgs=" + (String)localObject2);
/*  71 */     IApp localIApp = null;
/*  72 */     if (paramIOnCreateSplashView == null)
/*  73 */       this.b.a(paramActivity, (String)localObject1, (String)localObject2);
/*     */     else {
/*  75 */       localIApp = this.b.a(paramActivity, (String)localObject1, (String)localObject2, paramIOnCreateSplashView);
/*     */     }
/*  77 */     return localIApp;
/*     */   }
/*     */   public boolean a(Activity paramActivity, ISysEventListener.SysEventType paramSysEventType, Object paramObject) {
/*  80 */     if ((!paramActivity.getIntent().getBooleanExtra("has_stream_splash", false)) || ("all".equalsIgnoreCase(BaseInfo.sSplashExitCondition))) {
/*  81 */       if (System.currentTimeMillis() - this.c > 500L)
/*  82 */         return this.b.onActivityExecute(paramActivity, paramSysEventType, paramObject);
/*  83 */       if ((this.c > 0L) && (paramSysEventType == ISysEventListener.SysEventType.onKeyUp))
/*     */       {
/*  85 */         return true;
/*     */       }
/*  87 */       return false;
/*     */     }
/*     */ 
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean a(Activity paramActivity) {
/*  94 */     Logger.i("onStop");
/*  95 */     if (this.b.a(paramActivity)) {
/*  96 */       this.b = null;
/*  97 */       return true;
/*     */     }
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   public void b(Activity paramActivity) {
/* 103 */     Logger.i("onPause");
/* 104 */     this.b.b(paramActivity);
/* 105 */     this.c = 0L;
/*     */   }
/*     */ 
/*     */   public void c(Activity paramActivity)
/*     */   {
/* 117 */     this.c = System.currentTimeMillis();
/* 118 */     Logger.i("onResume resumeTime=" + this.c);
/*     */ 
/* 120 */     this.b.c(paramActivity);
/*     */   }
/*     */ 
/*     */   public void a(Activity paramActivity, Intent paramIntent)
/*     */   {
/* 128 */     Object localObject1 = null;
/*     */ 
/* 130 */     if (paramIntent != null) {
/* 131 */       localObject2 = paramIntent.getExtras();
/* 132 */       localObject1 = localObject2 != null ? ((Bundle)localObject2).getString("appid") : localObject1;
/*     */     }
/* 134 */     Object localObject2 = IntentConst.obtainArgs(paramIntent, localObject1);
/* 135 */     if (!PdrUtil.isEmpty(localObject1))
/* 136 */       this.b.a(paramActivity, localObject1, (String)localObject2, (paramActivity instanceof IOnCreateSplashView) ? (IOnCreateSplashView)paramActivity : null, true);
/*     */     else
/* 138 */       this.b.onActivityExecute(paramActivity, ISysEventListener.SysEventType.onNewIntent, localObject2);
/*     */   }
/*     */ 
/*     */   public void a(Activity paramActivity, int paramInt)
/*     */   {
/* 149 */     Logger.i("Layout_Path", "onConfigurationChanged pConfig=" + paramInt);
/* 150 */     this.b.onActivityExecute(paramActivity, ISysEventListener.SysEventType.onConfigurationChanged, Integer.valueOf(1));
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public ICore a() {
/* 155 */     return this.b;
/*     */   }
/*     */ 
/*     */   public Context b() {
/* 159 */     return this.a;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b
 * JD-Core Version:    0.6.2
 */