/*     */ package io.dcloud.common.b;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.os.Bundle;
/*     */ import android.text.TextUtils;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IActivityHandler;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IApp.IAppStatusListener;
/*     */ import io.dcloud.common.DHInterface.IBoot;
/*     */ import io.dcloud.common.DHInterface.ICore;
/*     */ import io.dcloud.common.DHInterface.ICore.ICoreStatusListener;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IOnCreateSplashView;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.adapter.util.AsyncTaskHandler;
/*     */ import io.dcloud.common.adapter.util.AsyncTaskHandler.IAsyncTaskListener;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.b.b.l;
/*     */ import io.dcloud.common.constant.IntentConst;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.NetTool;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.TestUtil;
/*     */ import io.dcloud.common.util.ThreadPool;
/*     */ import io.dcloud.common.util.net.NetMgr;
/*     */ import io.dcloud.feature.b;
/*     */ import io.dcloud.feature.internal.sdk.SDK;
/*     */ import io.dcloud.feature.internal.sdk.SDK.IntegratedMode;
/*     */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ class a
/*     */   implements ICore
/*     */ {
/*  57 */   boolean a = false;
/*  58 */   Context b = null;
/*  59 */   AbsMgr c = null;
/*  60 */   AbsMgr d = null;
/*  61 */   AbsMgr e = null;
/*  62 */   AbsMgr f = null;
/*     */ 
/*  64 */   private ICore.ICoreStatusListener l = null;
/*     */ 
/*  66 */   HashMap<String, IBoot> g = null;
/*  67 */   final int h = 0; final int i = 1; final int j = 2; final int k = 3;
/*     */ 
/*  71 */   private boolean m = false;
/*  72 */   private static a n = null;
/*     */ 
/*     */   public static a a(Context paramContext, ICore.ICoreStatusListener paramICoreStatusListener) {
/*  75 */     if (n == null) n = new a();
/*  76 */     n.b = paramContext;
/*  77 */     n.l = paramICoreStatusListener;
/*  78 */     SDK.initSDK(n);
/*  79 */     return n;
/*     */   }
/*     */ 
/*     */   public final boolean isStreamAppMode() {
/*  83 */     return this.m;
/*     */   }
/*     */   public final void setIsStreamAppMode(boolean paramBoolean) {
/*  86 */     this.m = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean onActivityExecute(Activity paramActivity, ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*     */   {
/*  91 */     String str = null;
/*     */     Object localObject2;
/*  92 */     if ((paramObject instanceof IApp)) {
/*  93 */       str = ((IApp)paramObject).obtainAppId();
/*     */     } else {
/*  95 */       str = BaseInfo.sRuntimeMode != null ? null : BaseInfo.sDefaultBootApp;
/*  96 */       localObject1 = paramActivity.getIntent();
/*  97 */       if (localObject1 != null) {
/*  98 */         localObject2 = ((Intent)localObject1).getExtras();
/*  99 */         if ((localObject2 != null) && (((Bundle)localObject2).containsKey("appid"))) {
/* 100 */           str = ((Bundle)localObject2).getString("appid");
/*     */         }
/*     */       }
/*     */     }
/* 104 */     Object localObject1 = dispatchEvent(IMgr.MgrType.AppMgr, 1, new Object[] { paramSysEventType, paramObject, str });
/* 105 */     if ((paramSysEventType.equals(ISysEventListener.SysEventType.onKeyUp)) && (!((Boolean)localObject1).booleanValue())) {
/* 106 */       localObject2 = (Object[])paramObject;
/* 107 */       int i1 = ((Integer)localObject2[0]).intValue();
/* 108 */       if (i1 == 4) {
/* 109 */         if (paramSysEventType.equals(ISysEventListener.SysEventType.onKeyUp))
/*     */         {
/* 111 */           if ((paramActivity instanceof IActivityHandler)) {
/* 112 */             ((IActivityHandler)paramActivity).closeAppStreamSplash(str);
/*     */           }
/* 114 */           a(paramActivity, paramActivity.getIntent(), null, str);
/*     */         }
/* 116 */         return true;
/*     */       }
/*     */     }
/* 119 */     return Boolean.parseBoolean(String.valueOf(localObject1));
/*     */   }
/*     */ 
/*     */   public void a(Activity paramActivity, Bundle paramBundle, SDK.IntegratedMode paramIntegratedMode)
/*     */   {
/* 132 */     BaseInfo.sRuntimeMode = paramIntegratedMode;
/* 133 */     if (paramIntegratedMode != null) BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT = true;
/* 134 */     if (this.a) return;
/* 135 */     IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(paramActivity);
/* 136 */     if (localIActivityHandler != null) {
/* 137 */       this.m = localIActivityHandler.isStreamAppMode();
/*     */     }
/* 139 */     a(paramActivity.getApplicationContext());
/* 140 */     Logger.i("Core onInit mode=" + paramIntegratedMode);
/*     */ 
/* 142 */     a(ISysEventListener.SysEventType.onStart, paramBundle);
/* 143 */     Logger.i("Core onInit mCoreListener=" + this.l);
/*     */     try {
/* 145 */       if ((BaseInfo.sRuntimeMode == null) || (BaseInfo.sRuntimeMode == SDK.IntegratedMode.RUNTIME)) {
/* 146 */         if (this.l != null)
/* 147 */           this.l.onCoreInitEnd(this);
/*     */       }
/* 149 */       else if (this.l != null)
/* 150 */         this.l.onCoreInitEnd(this);
/*     */     }
/*     */     catch (Exception localException) {
/* 153 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(Context paramContext) {
/* 158 */     TestUtil.record("core", Thread.currentThread());
/*     */ 
/* 160 */     this.f = new b(this);
/* 161 */     this.g = ((HashMap)dispatchEvent(IMgr.MgrType.FeatureMgr, 0, this.b));
/*     */ 
/* 163 */     BaseInfo.parseControl(this, this.l);
/* 164 */     Logger.initLogger(paramContext);
/* 165 */     DeviceInfo.init(paramContext);
/*     */ 
/* 167 */     this.d = new io.dcloud.common.a.a(this);
/*     */ 
/* 169 */     this.c = new l(this);
/* 170 */     this.e = new NetMgr(this);
/* 171 */     this.a = true;
/*     */     try {
/* 173 */       b(paramContext);
/*     */     } catch (Exception localException) {
/* 175 */       Logger.e("initSDKData " + localException.getLocalizedMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void b(final Context paramContext) {
/* 180 */     if ((!BaseInfo.s_Is_DCloud_Packaged) && (BaseInfo.existsBase()) && (TextUtils.isEmpty(PlatformUtil.getBundleData(BaseInfo.PDR, "ns"))))
/*     */     {
/* 184 */       ThreadPool.self().addThreadTask(new Runnable()
/*     */       {
/*     */         public void run() {
/*     */           try {
/* 188 */             String str1 = "https://service.dcloud.net.cn/sta/so?p=a&pn=%s&ver=%s&appid=%s";
/* 189 */             PackageManager localPackageManager = paramContext.getPackageManager();
/* 190 */             PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
/* 191 */             String str2 = localPackageInfo.versionName;
/* 192 */             byte[] arrayOfByte = NetTool.httpGet(String.format(str1, new Object[] { paramContext.getPackageName(), str2, BaseInfo.sDefaultBootApp }));
/* 193 */             JSONObject localJSONObject = new JSONObject(new String(arrayOfByte, "utf-8"));
/* 194 */             if ((localJSONObject != null) && (localJSONObject.has("ret")) && (localJSONObject.optInt("ret") == 0))
/* 195 */               PlatformUtil.setBundleData(BaseInfo.PDR, "ns", "true");
/*     */           } catch (Exception localException) {
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(Activity paramActivity, IApp paramIApp) {
/* 204 */     onActivityExecute(paramActivity, ISysEventListener.SysEventType.onWebAppStop, paramIApp);
/*     */   }
/*     */   public void a(final Activity paramActivity, final Intent paramIntent, final IApp paramIApp, final String paramString) {
/* 207 */     int i1 = 1;
/* 208 */     if (this.l != null) {
/* 209 */       i1 = !this.l.onCoreStop() ? 1 : 0;
/*     */     }
/* 211 */     if (paramIApp != null) {
/* 212 */       dispatchEvent(IMgr.MgrType.AppMgr, 13, paramIApp);
/*     */     }
/* 214 */     if (i1 != 0) {
/* 215 */       IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(paramActivity);
/* 216 */       final boolean bool = TextUtils.equals(paramActivity.getIntent().getStringExtra("appid"), paramString);
/* 217 */       if ((localIActivityHandler != null) && ((paramIApp == null) || ((paramIApp != null) && (paramIApp.isStreamApp())))) {
/* 218 */         if (localIActivityHandler.isStreamAppMode())
/*     */         {
/* 220 */           IntentConst.modifyStartFrom(paramIntent);
/* 221 */           int i2 = paramIntent.getIntExtra("__start_from__", -1);
/* 222 */           if ((!BaseInfo.sAppStateMap.containsKey(paramString)) && (i2 != 1) && (!BaseInfo.isShowTitleBar(paramActivity)) && (bool))
/*     */           {
/* 230 */             Logger.d("Main_Path", "moveTaskToBack");
/* 231 */             paramActivity.moveTaskToBack(false);
/* 232 */             BaseInfo.setLoadingLaunchePage(false, "core stopApp");
/*     */           }
/*     */         }
/*     */ 
/* 236 */         MessageHandler.sendMessage(new MessageHandler.IMessages()
/*     */         {
/*     */           public void execute(Object paramAnonymousObject) {
/* 239 */             Activity localActivity = paramActivity;
/* 240 */             Intent localIntent = paramIntent;
/* 241 */             String str = paramString;
/* 242 */             if (paramIApp != null) {
/* 243 */               a.a(a.this, paramActivity, paramIApp);
/* 244 */               localActivity = paramIApp.getActivity();
/* 245 */               localIntent = paramIApp.obtainWebAppIntent();
/* 246 */               str = paramIApp.obtainAppId();
/*     */             }
/* 248 */             if (bool) {
/* 249 */               a.a(a.this, paramActivity, localIntent, paramIApp, str);
/*     */             }
/* 251 */             boolean bool = ((Boolean)a.this.dispatchEvent(IMgr.MgrType.WindowMgr, 32, new Object[] { paramActivity, paramString })).booleanValue();
/* 252 */             if (!bool)
/* 253 */               paramActivity.finish();
/*     */           }
/*     */         }
/*     */         , 10L, null);
/*     */       }
/*     */       else
/*     */       {
/* 258 */         paramActivity.finish();
/*     */       }
/*     */     }
/* 261 */     BaseInfo.sGlobalFullScreen = false;
/*     */   }
/*     */ 
/*     */   private void b(Activity paramActivity, Intent paramIntent, IApp paramIApp, String paramString) {
/* 265 */     IntentConst.modifyStartFrom(paramIntent);
/*     */ 
/* 267 */     int i1 = paramIntent.getIntExtra("__start_from__", -1);
/* 268 */     if (((i1 != 2) && (i1 != 5)) || (BaseInfo.isShowTitleBar(paramActivity)))
/*     */     {
/* 271 */       if (!BaseInfo.sAppStateMap.containsKey(paramString)) {
/* 272 */         IApp localIApp = (IApp)dispatchEvent(IMgr.MgrType.AppMgr, 19, null);
/* 273 */         int i2 = localIApp != null ? 1 : 0;
/* 274 */         String str1 = null;
/* 275 */         Intent localIntent = null;
/* 276 */         if (i2 != 0)
/* 277 */           str1 = localIApp.obtainAppId();
/* 278 */         else if (!BaseInfo.sAppStateMap.isEmpty()) {
/* 279 */           str1 = BaseInfo.getLastKey(BaseInfo.sAppStateMap);
/*     */         }
/*     */ 
/* 282 */         if ((paramIApp != null) && (paramIApp.getIAppStatusListener() != null))
/*     */         {
/* 284 */           str1 = paramIApp.getIAppStatusListener().onStoped(BaseInfo.sAppStateMap.containsKey(paramIApp.obtainAppId()), str1);
/*     */         }
/*     */ 
/* 288 */         if (str1 != null) {
/* 289 */           String str2 = null;
/* 290 */           if ((i2 != 0) && (TextUtils.equals(localIApp.obtainAppId(), str1))) {
/* 291 */             paramActivity = localIApp.getActivity();
/* 292 */             localIntent = localIApp.obtainWebAppIntent();
/* 293 */           } else if (BaseInfo.sAppStateMap.containsKey(str1)) {
/* 294 */             localIntent = (Intent)BaseInfo.sAppStateMap.get(str1);
/*     */           } else {
/* 296 */             localIntent = new Intent();
/* 297 */             localIntent.putExtra("appid", str1);
/* 298 */             localIntent.putExtra("__start_from__", 1);
/*     */           }
/* 300 */           localIntent.putExtra("__webapp_reply__", true);
/* 301 */           paramActivity.setIntent(localIntent);
/* 302 */           Logger.d(str1 + " will be the Frontest");
/* 303 */           str2 = IntentConst.obtainArgs(localIntent, str1);
/* 304 */           dispatchEvent(null, 2, new Object[] { paramActivity, str1, str2, paramActivity });
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/* 310 */   public IApp a(Activity paramActivity, String paramString1, String paramString2, IOnCreateSplashView paramIOnCreateSplashView) { return a(paramActivity, paramString1, paramString2, paramIOnCreateSplashView, false); }
/*     */ 
/*     */   public IApp a(Activity paramActivity, String paramString1, String paramString2, IOnCreateSplashView paramIOnCreateSplashView, boolean paramBoolean)
/*     */   {
/* 314 */     TestUtil.record("GET_STATUS_BY_APPID");
/* 315 */     Logger.d("syncStartApp " + paramString1);
/* 316 */     int i1 = Byte.parseByte(String.valueOf(dispatchEvent(IMgr.MgrType.AppMgr, 12, paramString1)));
/* 317 */     TestUtil.print("GET_STATUS_BY_APPID");
/* 318 */     int i2 = !a(paramActivity.getIntent()) ? 1 : 0;
/* 319 */     if (1 == i1) {
/* 320 */       Logger.d("syncStartApp " + paramString1 + " STATUS_UN_RUNNING");
/* 321 */       if (paramIOnCreateSplashView != null) {
/* 322 */         Logger.d("syncStartApp " + paramString1 + " ShowSplash");
/* 323 */         paramIOnCreateSplashView.onCreateSplash(paramActivity);
/*     */       }
/*     */     }
/*     */ 
/* 327 */     if (i2 != 0) {
/*     */       try {
/* 329 */         TestUtil.record(TestUtil.START_APP_SET_ROOTVIEW, "启动" + paramString1);
/* 330 */         IApp localIApp = (IApp)dispatchEvent(IMgr.MgrType.AppMgr, 0, new Object[] { paramActivity, paramString1, paramString2 });
/* 331 */         localIApp.setOnCreateSplashView(paramIOnCreateSplashView);
/*     */ 
/* 333 */         if ((paramBoolean) && ((3 == i1) || (2 == i1))) {
/* 334 */           onActivityExecute(paramActivity, ISysEventListener.SysEventType.onNewIntent, paramString2);
/*     */         }
/*     */ 
/* 337 */         return localIApp;
/*     */       } catch (Exception localException) {
/* 339 */         Logger.d("syncStartApp appid=" + paramString1);
/*     */       }
/*     */     }
/* 342 */     return null;
/*     */   }
/*     */   public boolean a(Intent paramIntent) {
/* 345 */     if (paramIntent != null) {
/* 346 */       return paramIntent.getBooleanExtra("has_stream_splash", false);
/*     */     }
/* 348 */     return false;
/*     */   }
/*     */   public void a(final Activity paramActivity, final String paramString1, final String paramString2) {
/* 351 */     AsyncTaskHandler.executeAsyncTask(new AsyncTaskHandler.IAsyncTaskListener()
/*     */     {
/*     */       public void onExecuteBegin()
/*     */       {
/*     */       }
/*     */ 
/*     */       public Object onExecuting() {
/* 358 */         return null;
/*     */       }
/*     */ 
/*     */       public void onExecuteEnd(Object paramAnonymousObject)
/*     */       {
/* 363 */         MessageHandler.sendMessage(new MessageHandler.IMessages()
/*     */         {
/*     */           public void execute(Object paramAnonymous2Object) {
/* 366 */             a.this.a(a.3.this.a, a.3.this.b, a.3.this.c, null);
/*     */           }
/*     */         }
/*     */         , null);
/*     */       }
/*     */ 
/*     */       public void onCancel()
/*     */       {
/*     */       }
/*     */     }
/*     */     , null);
/*     */   }
/*     */ 
/*     */   private void a(ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*     */   {
/* 383 */     if (this.g != null) {
/* 384 */       Collection localCollection = this.g.values();
/* 385 */       if (localCollection != null)
/* 386 */         for (IBoot localIBoot : localCollection)
/*     */           try {
/* 388 */             if (localIBoot != null)
/* 389 */               switch (4.a[paramSysEventType.ordinal()])
/*     */               {
/*     */               case 1:
/* 394 */                 localIBoot.onStart(this.b, (Bundle)paramObject, new String[] { BaseInfo.sDefaultBootApp });
/* 395 */                 break;
/*     */               case 2:
/* 397 */                 localIBoot.onStop();
/* 398 */                 break;
/*     */               case 3:
/* 400 */                 localIBoot.onPause();
/* 401 */                 break;
/*     */               case 4:
/* 403 */                 localIBoot.onResume();
/*     */               }
/*     */           }
/*     */           catch (Exception localException) {
/* 407 */             localException.printStackTrace();
/*     */           }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean a(Activity paramActivity)
/*     */   {
/* 415 */     PlatformUtil.invokeMethod("io.dcloud.feature.apsqh.QHNotifactionReceiver", "doSaveNotifications", null, new Class[] { Context.class }, new Object[] { paramActivity.getBaseContext() });
/*     */     try
/*     */     {
/* 418 */       onActivityExecute(paramActivity, ISysEventListener.SysEventType.onStop, null);
/*     */ 
/* 420 */       this.a = false;
/* 421 */       BaseInfo.setLoadingLaunchePage(false, "onStop");
/* 422 */       a(ISysEventListener.SysEventType.onStop, null);
/* 423 */       n = null;
/* 424 */       this.c.dispose();
/* 425 */       this.c = null;
/* 426 */       this.d.dispose();
/* 427 */       this.d = null;
/* 428 */       this.e.dispose();
/* 429 */       this.e = null;
/* 430 */       this.f.dispose();
/* 431 */       this.f = null;
/* 432 */       Logger.e("Main_Path", "core exit");
/*     */     } catch (Exception localException) {
/* 434 */       localException.printStackTrace();
/*     */     }
/* 436 */     return true;
/*     */   }
/*     */ 
/*     */   public void b(Activity paramActivity)
/*     */   {
/* 442 */     a(ISysEventListener.SysEventType.onPause, null);
/* 443 */     if (this.a) {
/* 444 */       if (this.e != null) {
/* 445 */         this.e.onExecute(ISysEventListener.SysEventType.onPause, null);
/*     */       }
/* 447 */       onActivityExecute(paramActivity, ISysEventListener.SysEventType.onPause, null);
/*     */     }
/* 449 */     System.gc();
/*     */   }
/*     */ 
/*     */   public void c(Activity paramActivity) {
/* 453 */     a(ISysEventListener.SysEventType.onResume, null);
/*     */ 
/* 455 */     this.e.onExecute(ISysEventListener.SysEventType.onResume, null);
/* 456 */     boolean bool = onActivityExecute(paramActivity, ISysEventListener.SysEventType.onResume, null);
/* 457 */     if (!bool)
/*     */     {
/* 459 */       Object localObject1 = null;
/*     */ 
/* 461 */       Intent localIntent = paramActivity.getIntent();
/*     */       Object localObject2;
/* 462 */       if (localIntent != null) {
/* 463 */         localObject2 = localIntent.getExtras();
/* 464 */         localObject1 = localObject2 != null ? ((Bundle)localObject2).getString("appid") : localObject1;
/*     */       }
/* 466 */       if (!PdrUtil.isEmpty(localObject1)) {
/* 467 */         localObject2 = IntentConst.obtainArgs(localIntent, localObject1);
/* 468 */         a(paramActivity, localObject1, (String)localObject2, (paramActivity instanceof IOnCreateSplashView) ? (IOnCreateSplashView)paramActivity : null);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Object dispatchEvent(IMgr.MgrType paramMgrType, int paramInt, Object paramObject)
/*     */   {
/* 475 */     Object localObject = null;
/* 476 */     if (paramMgrType == null)
/* 477 */       localObject = a(paramInt, paramObject);
/*     */     else {
/*     */       try {
/* 480 */         if (n == null) n = this;
/* 481 */         switch (4.b[paramMgrType.ordinal()]) {
/*     */         case 1:
/* 483 */           if (n.d != null)
/* 484 */             localObject = n.d.processEvent(paramMgrType, paramInt, paramObject); break;
/*     */         case 2:
/* 487 */           localObject = n.e.processEvent(paramMgrType, paramInt, paramObject);
/* 488 */           break;
/*     */         case 3:
/* 490 */           localObject = n.f.processEvent(paramMgrType, paramInt, paramObject);
/* 491 */           break;
/*     */         case 4:
/* 493 */           localObject = n.c.processEvent(paramMgrType, paramInt, paramObject);
/*     */         }
/*     */       }
/*     */       catch (Exception localException) {
/* 497 */         localException.printStackTrace();
/*     */       }
/*     */     }
/* 500 */     return localObject;
/*     */   }
/*     */ 
/*     */   private Object a(int paramInt, Object paramObject) {
/* 504 */     Object localObject1 = null;
/*     */     Object localObject2;
/*     */     Object localObject3;
/*     */     String str;
/*     */     Object localObject4;
/*     */     Object localObject5;
/* 505 */     switch (paramInt) {
/*     */     case -1:
/* 507 */       localObject1 = BaseInfo.sRuntimeMode;
/* 508 */       break;
/*     */     case 1:
/* 510 */       localObject1 = Boolean.valueOf(this.g.containsValue((IBoot)paramObject));
/* 511 */       break;
/*     */     case 0:
/* 513 */       localObject2 = null;
/* 514 */       localObject3 = null;
/* 515 */       str = null;
/* 516 */       localObject4 = null;
/* 517 */       if ((paramObject instanceof IApp)) {
/* 518 */         localObject3 = (IApp)paramObject;
/* 519 */         localObject2 = ((IApp)localObject3).getActivity();
/* 520 */         localObject4 = ((IApp)localObject3).obtainWebAppIntent();
/* 521 */         str = ((IApp)localObject3).obtainAppId();
/* 522 */       } else if ((paramObject instanceof Object[])) {
/* 523 */         localObject5 = (Object[])paramObject;
/* 524 */         localObject2 = (Activity)localObject5[0];
/* 525 */         localObject4 = (Intent)localObject5[1];
/* 526 */         str = (String)localObject5[2];
/*     */       }
/* 528 */       a((Activity)localObject2, (Intent)localObject4, (IApp)localObject3, str);
/*     */ 
/* 530 */       break;
/*     */     case 2:
/* 532 */       localObject2 = (Object[])paramObject;
/* 533 */       localObject3 = (Activity)localObject2[0];
/* 534 */       str = (String)localObject2[1];
/* 535 */       localObject4 = (String)localObject2[2];
/* 536 */       localObject5 = (IOnCreateSplashView)localObject2[3];
/* 537 */       a((Activity)localObject3, str, (String)localObject4, (IOnCreateSplashView)localObject5);
/*     */     }
/*     */ 
/* 541 */     return localObject1;
/*     */   }
/*     */ 
/*     */   public Context obtainContext() {
/* 545 */     return this.b;
/*     */   }
/*     */ 
/*     */   public void removeStreamApp(String paramString)
/*     */   {
/* 551 */     if (this.d != null) {
/* 552 */       this.d.processEvent(IMgr.MgrType.AppMgr, 17, paramString);
/*     */     }
/* 554 */     if (this.c != null)
/* 555 */       this.d.processEvent(IMgr.MgrType.WindowMgr, 40, paramString);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.a
 * JD-Core Version:    0.6.2
 */