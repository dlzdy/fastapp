/*     */ package io.dcloud;
/*     */ 
/*     */ import android.annotation.TargetApi;
/*     */ import android.app.Activity;
/*     */ import android.app.ActivityManager;
/*     */ import android.app.ActivityManager.MemoryInfo;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.res.Configuration;
/*     */ import android.content.res.Resources;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Bundle;
/*     */ import android.os.Handler;
/*     */ import android.util.Log;
/*     */ import android.view.KeyEvent;
/*     */ import android.view.Menu;
/*     */ import android.view.View;
/*     */ import android.view.Window;
/*     */ import android.view.WindowManager.LayoutParams;
/*     */ import io.dcloud.common.DHInterface.IActivityHandler;
/*     */ import io.dcloud.common.DHInterface.ICore;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IOnCreateSplashView;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.UEH;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.TestUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ 
/*     */ abstract class a extends b
/*     */   implements IOnCreateSplashView, d
/*     */ {
/*  36 */   String a = null;
/*  37 */   String b = "Main_App";
/*  38 */   EntryProxy c = null;
/*     */ 
/*     */   public void onCreate(final Bundle paramBundle) {
/*  41 */     super.onCreate(paramBundle);
/*  42 */     DeviceInfo.initPath(this.that);
/*  43 */     UEH.catchUncaughtException(this.that);
/*  44 */     Log.d("download_manager", "BaseActivity onCreate");
/*  45 */     TestUtil.print(TestUtil.START_STREAM_APP, "BaseActivity onCreate");
/*  46 */     onRuntimePreCreate(paramBundle);
/*  47 */     onCreateSplash(this.that);
/*  48 */     Runnable local1 = new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  53 */         a.a(a.this, a.this.getIntent());
/*  54 */         a.this.a = ("Main_Path_" + a.this.b);
/*  55 */         io.dcloud.feature.internal.splash.a.a("Main_App");
/*  56 */         Logger.d(a.this.a, "onCreate appid=" + a.this.b);
/*  57 */         a.this.onRuntimeCreate(paramBundle);
/*     */       }
/*     */     };
/*  60 */     new Handler().postDelayed(local1, 200L);
/*     */   }
/*     */ 
/*     */   public void setViewAsContentView(View paramView) {
/*  64 */     setContentView(paramView);
/*     */   }
/*     */ 
/*     */   public void closeAppStreamSplash()
/*     */   {
/*  83 */     InvokeExecutorHelper.StreamAppListActivity.invoke("closeSplashPage", new Class[] { Boolean.class }, new Object[] { Boolean.valueOf(true) });
/*  84 */     Logger.d("Main_Path", "BaseActivity.closeAppStreamSplash");
/*     */   }
/*     */   private void a(Intent paramIntent) {
/*  87 */     Bundle localBundle = paramIntent.getExtras();
/*  88 */     if ((localBundle != null) && (localBundle.containsKey("appid")))
/*  89 */       this.b = localBundle.getString("appid");
/*     */   }
/*     */ 
/*     */   protected void onRuntimePreCreate(Bundle paramBundle) {
/*  93 */     Log.d(this.a, "onRuntimePreCreate appid=" + this.b);
/*     */ 
/*  96 */     this.that.getWindow().setFormat(-3);
/*  97 */     int i = 0;
/*  98 */     if (i != 0) {
/*  99 */       DeviceInfo.openHardwareAccelerated(this.that, DeviceInfo.HARDWAREACCELERATED_WINDOW, this.that.getWindow());
/*     */     }
/* 101 */     io.dcloud.feature.internal.splash.a.a("main", this.that);
/* 102 */     a();
/*     */   }
/*     */ 
/*     */   private void a()
/*     */   {
/* 117 */     if (AndroidResources.checkImmersedStatusBar(this.that))
/* 118 */       a(true);
/*     */   }
/*     */ 
/*     */   @TargetApi(19)
/*     */   private void a(boolean paramBoolean)
/*     */   {
/* 156 */     Window localWindow = this.that.getWindow();
/* 157 */     WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
/* 158 */     int i = 67108864;
/* 159 */     if (paramBoolean)
/* 160 */       localLayoutParams.flags |= 67108864;
/*     */     else {
/* 162 */       localLayoutParams.flags &= -67108865;
/*     */     }
/* 164 */     localWindow.setAttributes(localLayoutParams);
/*     */   }
/*     */ 
/*     */   public Object onCreateSplash(Context paramContext)
/*     */   {
/* 180 */     return "splash";
/*     */   }
/*     */ 
/*     */   public void onCloseSplash()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void onRuntimeCreate(Bundle paramBundle)
/*     */   {
/* 190 */     Logger.d(this.a, "onRuntimeCreate appid=" + this.b);
/* 191 */     this.c = EntryProxy.init(this.that);
/* 192 */     this.c.onCreate(this.that, paramBundle, null, null);
/*     */   }
/*     */ 
/*     */   public boolean onCreateOptionsMenu(Menu paramMenu)
/*     */   {
/* 207 */     Logger.d(this.a, "onCreateOptionsMenu appid=" + this.b);
/* 208 */     if (this.c != null) return this.c.onActivityExecute(this.that, ISysEventListener.SysEventType.onCreateOptionMenu, paramMenu);
/* 209 */     return super.onCreateOptionsMenu(paramMenu);
/*     */   }
/*     */ 
/*     */   public void onPause()
/*     */   {
/* 223 */     super.onPause();
/* 224 */     Logger.d(this.a, "onPause appid=" + this.b);
/* 225 */     if (this.c != null) this.c.onPause(this.that); 
/*     */   }
/*     */ 
/*     */   public void onResume()
/*     */   {
/* 229 */     super.onResume();
/*     */ 
/* 231 */     a(getIntent());
/*     */ 
/* 235 */     Logger.d(this.a, "onResume appid=" + this.b);
/* 236 */     if (this.c != null) this.c.onResume(this.that);
/*     */ 
/* 238 */     if ((Build.VERSION.SDK_INT >= 21) && (BaseInfo.mDeStatusBarBackground == -111111))
/* 239 */       BaseInfo.mDeStatusBarBackground = getWindow().getStatusBarColor();
/*     */   }
/*     */ 
/*     */   public void updateParam(String paramString, Object paramObject)
/*     */   {
/*     */     Object localObject;
/* 249 */     if ("tab_change".equals(paramString)) {
/* 250 */       Logger.d("BaseActivity updateParam newintent value(appid)=" + paramObject);
/* 251 */       localObject = (Intent)BaseInfo.sAppStateMap.get((String)paramObject);
/* 252 */       if (localObject != null)
/* 253 */         handleNewIntent((Intent)localObject);
/*     */       else
/* 255 */         this.c.getCoreHandler().dispatchEvent(IMgr.MgrType.AppMgr, 21, paramObject);
/*     */     }
/* 257 */     else if ("closewebapp".equals(paramString)) {
/* 258 */       localObject = (Activity)paramObject;
/* 259 */       String str = null;
/* 260 */       Bundle localBundle = ((Activity)localObject).getIntent().getExtras();
/* 261 */       if ((localBundle != null) && (localBundle.containsKey("appid"))) {
/* 262 */         str = localBundle.getString("appid");
/*     */       }
/* 264 */       if ((localObject instanceof IActivityHandler)) {
/* 265 */         ((IActivityHandler)localObject).closeAppStreamSplash(str);
/*     */       }
/* 267 */       this.c.getCoreHandler().dispatchEvent(null, 0, new Object[] { localObject, ((Activity)localObject).getIntent(), str });
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onNewIntentImpl(Intent paramIntent) {
/* 272 */     super.onNewIntentImpl(paramIntent);
/* 273 */     Logger.d("syncStartApp", "BaseActivity onNewIntent appid=" + this.b);
/* 274 */     handleNewIntent(paramIntent);
/*     */   }
/*     */ 
/*     */   protected void handleNewIntent(Intent paramIntent)
/*     */   {
/* 279 */     setIntent(paramIntent);
/* 280 */     a(paramIntent);
/* 281 */     Logger.d("syncStartApp", "BaseActivity handleNewIntent =" + this.b + ";" + (paramIntent.getFlags() != 274726912));
/* 282 */     if ((paramIntent.getFlags() != 274726912) && 
/* 283 */       (this.c != null))
/* 284 */       this.c.onNewIntent(this.that, paramIntent);
/*     */   }
/*     */ 
/*     */   public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
/*     */   {
/* 290 */     if (this.c != null)
/* 291 */       this.c.onActivityExecute(this.that, ISysEventListener.SysEventType.onRequestPermissionsResult, new Object[] { Integer.valueOf(paramInt), paramArrayOfString, paramArrayOfInt });
/*     */   }
/*     */ 
/*     */   public void onDestroy()
/*     */   {
/* 297 */     super.onDestroy();
/* 298 */     io.dcloud.feature.internal.splash.a.b("Main_App");
/* 299 */     Logger.d(this.a, "onDestroy appid=" + this.b);
/* 300 */     if (this.c != null) this.c.onStop(this.that);
/* 301 */     if (BaseInfo.mLaunchers != null) {
/* 302 */       BaseInfo.mLaunchers.clear();
/*     */     }
/* 304 */     MessageHandler.removeCallbacksAndMessages();
/*     */   }
/*     */ 
/*     */   public boolean onKeyEventExecute(ISysEventListener.SysEventType paramSysEventType, int paramInt, KeyEvent paramKeyEvent) {
/* 308 */     return this.c != null ? this.c.onActivityExecute(this.that, paramSysEventType, new Object[] { Integer.valueOf(paramInt), paramKeyEvent }) : false;
/*     */   }
/*     */ 
/*     */   public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
/* 312 */     if (!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) return super.onKeyDown(paramInt, paramKeyEvent);
/* 313 */     boolean bool = false;
/* 314 */     if (paramKeyEvent.getRepeatCount() == 0)
/* 315 */       bool = onKeyEventExecute(ISysEventListener.SysEventType.onKeyDown, paramInt, paramKeyEvent);
/*     */     else {
/* 317 */       bool = onKeyEventExecute(ISysEventListener.SysEventType.onKeyLongPress, paramInt, paramKeyEvent);
/*     */     }
/* 319 */     return bool ? bool : super.onKeyDown(paramInt, paramKeyEvent);
/*     */   }
/*     */ 
/*     */   public void onBackPressed() {
/* 323 */     if (!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) {
/* 324 */       super.onBackPressed();
/* 325 */       return;
/*     */     }
/* 327 */     boolean bool = onKeyEventExecute(ISysEventListener.SysEventType.onKeyUp, 4, null);
/* 328 */     if ((!bool) && (this.c != null)) {
/* 329 */       this.c.destroy(this.that);
/* 330 */       super.onBackPressed();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
/* 335 */     if (!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) {
/* 336 */       return super.onKeyUp(paramInt, paramKeyEvent);
/*     */     }
/* 338 */     Logger.d(this.a, "onKeyUp");
/* 339 */     boolean bool = false;
/* 340 */     if ((paramInt != 4) && (this.c != null)) {
/* 341 */       bool = this.c.onActivityExecute(this.that, ISysEventListener.SysEventType.onKeyUp, new Object[] { Integer.valueOf(paramInt), paramKeyEvent });
/*     */     }
/* 343 */     return bool ? bool : super.onKeyUp(paramInt, paramKeyEvent);
/*     */   }
/*     */ 
/*     */   public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent) {
/* 347 */     if (!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) {
/* 348 */       return super.onKeyLongPress(paramInt, paramKeyEvent);
/*     */     }
/* 350 */     int i = this.c != null ? this.c.onActivityExecute(this.that, ISysEventListener.SysEventType.onKeyLongPress, new Object[] { Integer.valueOf(paramInt), paramKeyEvent }) : 0;
/* 351 */     return i != 0 ? i : super.onKeyLongPress(paramInt, paramKeyEvent);
/*     */   }
/*     */ 
/*     */   public void onConfigurationChanged(Configuration paramConfiguration) {
/*     */     try {
/* 356 */       Logger.d(this.a, "onConfigurationChanged");
/* 357 */       int i = getResources().getConfiguration().orientation;
/* 358 */       if (this.c != null) this.c.onConfigurationChanged(this.that, i);
/* 359 */       super.onConfigurationChanged(paramConfiguration);
/*     */     }
/*     */     catch (Exception localException) {
/* 362 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
/* 367 */     Logger.d(this.a, "onActivityResult");
/* 368 */     if (this.c != null) this.c.onActivityExecute(this.that, ISysEventListener.SysEventType.onActivityResult, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramIntent });
/*     */   }
/*     */ 
/*     */   public void onSaveInstanceState(Bundle paramBundle)
/*     */   {
/* 373 */     if ((paramBundle != null) && (getIntent() != null) && (getIntent().getExtras() != null)) {
/* 374 */       paramBundle.putAll(getIntent().getExtras());
/*     */     }
/* 376 */     Logger.d(this.a, "onSaveInstanceState");
/* 377 */     if (this.c != null) this.c.onActivityExecute(this.that, ISysEventListener.SysEventType.onSaveInstanceState, new Object[] { paramBundle });
/* 378 */     super.onSaveInstanceState(paramBundle);
/*     */   }
/*     */ 
/*     */   public void onLowMemory() {
/* 382 */     super.onLowMemory();
/* 383 */     Logger.d(this.a, "onLowMemory");
/* 384 */     displayBriefMemory();
/*     */   }
/*     */ 
/*     */   protected void displayBriefMemory() {
/* 388 */     ActivityManager localActivityManager = (ActivityManager)getSystemService("activity");
/* 389 */     ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
/* 390 */     localActivityManager.getMemoryInfo(localMemoryInfo);
/* 391 */     Logger.i("mabo", "===============================");
/* 392 */     Logger.i("mabo", "程序最高可用内存:" + Runtime.getRuntime().maxMemory() / 1048576L + "M");
/* 393 */     Logger.i("mabo", "程序总共占用内存:" + Runtime.getRuntime().totalMemory() / 1048576L + "M");
/* 394 */     Logger.i("mabo", "程序所占剩余内存:" + Runtime.getRuntime().freeMemory() / 1048576L + "M");
/* 395 */     Logger.i("mabo", "系统剩余内存:" + localMemoryInfo.availMem / 1048576L + "M");
/* 396 */     Logger.i("mabo", "系统是否处于低内存运行：" + localMemoryInfo.lowMemory);
/* 397 */     Logger.i("mabo", "当系统剩余内存低于" + localMemoryInfo.threshold / 1048576L + "M" + "时就看成低内存运行");
/*     */   }
/*     */ 
/*     */   public Resources getResources()
/*     */   {
/* 402 */     Resources localResources = super.getResources();
/* 403 */     Configuration localConfiguration = localResources.getConfiguration();
/* 404 */     float f = 1.0F;
/*     */     try {
/* 406 */       f = Float.parseFloat(BaseInfo.sFontScale);
/* 407 */       if (localConfiguration.fontScale != f) {
/* 408 */         localConfiguration.fontScale = f;
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException)
/*     */     {
/* 415 */       if (("none".equals(BaseInfo.sFontScale)) && 
/* 416 */         (localConfiguration.fontScale != 1.0F)) {
/* 417 */         localConfiguration.fontScale = 1.0F;
/*     */       }
/*     */     }
/*     */ 
/* 421 */     return localResources;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.a
 * JD-Core Version:    0.6.2
 */