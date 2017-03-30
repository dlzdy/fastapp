/*     */ package io.dcloud.feature.ui;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.IntentFilter;
/*     */ import android.content.SharedPreferences;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Bitmap;
/*     */ import android.os.Bundle;
/*     */ import android.text.TextUtils;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.View;
/*     */ import com.nostra13.dcloudimageloader.cache.disc.DiscCacheAware;
/*     */ import com.nostra13.dcloudimageloader.core.ImageLoader;
/*     */ import com.nostra13.dcloudimageloader.core.assist.FailReason;
/*     */ import com.nostra13.dcloudimageloader.core.assist.ImageLoadingListener;
/*     */ import io.dcloud.application.DCloudApplication;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IActivityHandler;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*     */ import io.dcloud.common.adapter.util.AnimOptions;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*     */ import io.dcloud.common.adapter.util.SP;
/*     */ import io.dcloud.common.constant.DataInterface;
/*     */ import io.dcloud.common.constant.StringConst;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.BaseInfo.CmtInfo;
/*     */ import io.dcloud.common.util.NetworkTypeUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.ShortCutUtil;
/*     */ import io.dcloud.common.util.TestUtil;
/*     */ import io.dcloud.common.util.TestUtil.PointTime;
/*     */ import io.dcloud.feature.internal.reflect.BroadcastReceiver;
/*     */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*     */ import java.io.File;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import org.json.JSONArray;
/*     */ 
/*     */ public class a
/*     */   implements ISysEventListener
/*     */ {
/*  61 */   ArrayList<c> a = null;
/*     */ 
/*  63 */   ArrayList<c> b = new ArrayList(1);
/*     */   AbsMgr c;
/*  65 */   HashMap<String, b> d = new HashMap();
/*  66 */   IApp e = null;
/*  67 */   IActivityHandler f = null;
/*     */ 
/*  70 */   InvokeExecutorHelper.InvokeExecutor g = InvokeExecutorHelper.AppStreamUtils;
/*  71 */   InvokeExecutorHelper.InvokeExecutor h = InvokeExecutorHelper.AppidUtils;
/*  72 */   InvokeExecutorHelper.InvokeExecutor i = InvokeExecutorHelper.StorageUtils;
/*  73 */   String j = null;
/*     */ 
/*  75 */   boolean k = false;
/*     */ 
/*  77 */   boolean l = false;
/*     */   SharedPreferences m;
/*     */   private c s;
/*     */   private String t;
/*  81 */   private List<String> u = new ArrayList();
/*     */ 
/* 331 */   ArrayList<c> n = null;
/*     */ 
/* 346 */   ISysEventListener o = new ISysEventListener()
/*     */   {
/*     */     public boolean onExecute(ISysEventListener.SysEventType paramAnonymousSysEventType, Object paramAnonymousObject) {
/* 349 */       Object[] arrayOfObject = (Object[])paramAnonymousObject;
/* 350 */       boolean bool = false;
/* 351 */       if (paramAnonymousSysEventType == ISysEventListener.SysEventType.onKeyUp) {
/* 352 */         int i = ((Integer)arrayOfObject[0]).intValue();
/* 353 */         if ((paramAnonymousSysEventType == ISysEventListener.SysEventType.onKeyUp) && 
/* 354 */           (i == 4) && 
/* 355 */           (a.this.e.getMaskLayerCount() > 0)) {
/* 356 */           a.a(a.this, 1);
/* 357 */           a.this.e.clearMaskLayerCount();
/* 358 */           a.this.e.unregisterSysEventListener(a.this.o, ISysEventListener.SysEventType.onKeyUp);
/* 359 */           if (a.this.n != null) {
/* 360 */             for (c localc : a.this.n) {
/* 361 */               localc.y = false;
/*     */             }
/* 363 */             a.this.n.clear();
/*     */           }
/* 365 */           a.this.e.obtainWebAppRootView().obtainMainView().invalidate();
/* 366 */           bool = true;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 371 */       return bool;
/*     */     }
/* 346 */   };
/*     */ 
/* 551 */   b p = null;
/*     */ 
/* 646 */   private a v = new a();
/*     */ 
/* 719 */   StringBuffer q = new StringBuffer();
/*     */ 
/* 721 */   HashMap<String, Integer> r = new HashMap();
/*     */ 
/*     */   a(AbsMgr paramAbsMgr, IApp paramIApp)
/*     */   {
/*  84 */     this.c = paramAbsMgr;
/*  85 */     this.e = paramIApp;
/*  86 */     this.m = DCloudApplication.getInstance().getApplicationContext().getSharedPreferences("pdr", 0);
/*     */ 
/*  91 */     IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(paramIApp.getActivity());
/*  92 */     if (localIActivityHandler != null) {
/*  93 */       this.f = localIActivityHandler;
/*     */     }
/*  95 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
/*  96 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyDown);
/*  97 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyLongPress);
/*  98 */     boolean bool1 = paramIApp.isStreamApp();
/*  99 */     if ((bool1) && (!PdrUtil.isEquals(BaseInfo.sDefaultBootApp, this.e.obtainAppId())))
/*     */     {
/* 102 */       if (this.f != null) {
/* 103 */         boolean bool2 = b(this.e.obtainAppId());
/* 104 */         Logger.d("AppWidgetMgr" + this.e.obtainAppId() + "isStreamApp=" + bool2);
/* 105 */         BaseInfo.setLoadingLaunchePage(bool2, "AppWidgetMgr");
/*     */ 
/* 107 */         this.j = (this.e.getActivity().getPackageName() + this.g.getString("CONTRACT_BROADCAST_ACTION"));
/* 108 */         this.p = new b(this.f);
/* 109 */         this.p.a();
/* 110 */         if (!bool2) {
/* 111 */           b(paramIApp);
/*     */         }
/*     */ 
/* 114 */         Logger.d("AppWidgetMgr isStreamApp=" + bool2);
/* 115 */         MessageHandler.sendMessage(new MessageHandler.IMessages()
/*     */         {
/*     */           public void execute(Object paramAnonymousObject) {
/* 118 */             a.this.a((IApp)paramAnonymousObject);
/*     */           }
/*     */         }
/*     */         , paramIApp);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   void a(String paramString, b paramb)
/*     */   {
/* 126 */     this.d.put(paramString, paramb);
/*     */   }
/*     */ 
/*     */   b a(String paramString) {
/* 130 */     return (b)this.d.get(paramString);
/*     */   }
/*     */   void a() {
/* 133 */     this.b.clear();
/* 134 */     if ((this.f != null) && (this.p != null)) {
/* 135 */       Logger.d("stream_manager", "AppWidgetMgr.clearData");
/* 136 */       this.f.unregisterReceiver(this.p);
/*     */     }
/*     */   }
/*     */ 
/*     */   boolean b(String paramString) {
/* 141 */     boolean bool = false;
/* 142 */     if (BaseInfo.isWap2AppAppid(paramString))
/* 143 */       bool = !BaseInfo.isWap2AppCompleted(paramString);
/*     */     else {
/* 145 */       bool = !this.i.invoke("checkDirResourceComplete", this.h.invoke("getAppFilePathByAppid", paramString), false);
/*     */     }
/* 147 */     return bool;
/*     */   }
/*     */ 
/*     */   public void a(IFrameView paramIFrameView)
/*     */   {
/* 157 */     this.c.processEvent(IMgr.MgrType.WindowMgr, 8, paramIFrameView);
/*     */   }
/*     */ 
/*     */   public void b(IFrameView paramIFrameView)
/*     */   {
/* 163 */     this.c.processEvent(IMgr.MgrType.WindowMgr, 22, paramIFrameView);
/*     */   }
/*     */ 
/*     */   int a(c paramc)
/*     */   {
/* 173 */     int i1 = this.a.size();
/* 174 */     int i2 = i1;
/* 175 */     for (int i3 = i1 - 1; i3 >= 0; i3--) {
/* 176 */       c localc = (c)this.a.get(i3);
/* 177 */       int i4 = localc.A;
/* 178 */       int i5 = paramc.A;
/* 179 */       if (i4 <= i5) {
/* 180 */         i2 = i3 + 1;
/* 181 */         break;
/*     */       }
/*     */     }
/* 184 */     return i2;
/*     */   }
/*     */ 
/*     */   void a(String paramString, c paramc, int paramInt)
/*     */   {
/* 194 */     if (this.a == null) {
/* 195 */       this.a = new ArrayList(1);
/*     */     }
/* 197 */     if (!this.a.contains(paramc)) {
/* 198 */       this.a.add(paramInt, paramc);
/*     */     }
/*     */ 
/* 202 */     Collections.sort(this.b, this.v);
/* 203 */     Collections.sort(this.a, this.v);
/*     */   }
/*     */ 
/*     */   void b(c paramc)
/*     */   {
/* 218 */     Logger.d("Map_Path", "sortNWindowByZIndex beign");
/* 219 */     Collections.sort(this.b, this.v);
/* 220 */     Collections.sort(this.a, this.v);
/* 221 */     this.c.processEvent(IMgr.MgrType.WindowMgr, 26, paramc.u.obtainWebAppRootView());
/*     */   }
/*     */ 
/*     */   void c(c paramc)
/*     */   {
/* 229 */     if (!this.b.contains(paramc))
/* 230 */       this.b.add(paramc);
/*     */   }
/*     */ 
/*     */   c b() {
/* 234 */     return a(2);
/*     */   }
/*     */ 
/*     */   c a(int paramInt)
/*     */   {
/* 243 */     int i1 = this.b.size();
/* 244 */     for (int i2 = 0; i2 < i1; i2++) {
/* 245 */       c localc = (c)this.b.get(i2);
/* 246 */       if (localc.u.getFrameType() == paramInt) {
/* 247 */         return localc;
/*     */       }
/*     */     }
/* 250 */     return null;
/*     */   }
/*     */ 
/*     */   c c() {
/* 254 */     return a(3);
/*     */   }
/*     */ 
/*     */   c c(IFrameView paramIFrameView)
/*     */   {
/* 263 */     int i1 = this.b.size();
/* 264 */     for (int i2 = 0; i2 < i1; i2++) {
/* 265 */       c localc = (c)this.b.get(i2);
/* 266 */       if (localc.u.equals(paramIFrameView)) {
/* 267 */         return localc;
/*     */       }
/*     */     }
/* 270 */     return null;
/*     */   }
/*     */ 
/*     */   synchronized c a(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 281 */     int i1 = this.b.size();
/* 282 */     for (int i2 = 0; i2 < i1; i2++) {
/* 283 */       c localc = (c)this.b.get(i2);
/* 284 */       String str1 = String.valueOf(localc.u.hashCode());
/* 285 */       String str2 = localc.l();
/* 286 */       if ((PdrUtil.isEquals(paramString1, str1)) || (PdrUtil.isEquals(paramString2, localc.e)) || (PdrUtil.isEquals(paramString3, str2)))
/*     */       {
/* 289 */         return localc;
/*     */       }
/*     */     }
/* 292 */     return null;
/*     */   }
/*     */   String d() {
/* 295 */     StringBuffer localStringBuffer = new StringBuffer("[");
/* 296 */     int i1 = 0;
/* 297 */     for (c localc : this.b) {
/* 298 */       localStringBuffer.append(localc.d()).append(",");
/* 299 */       i1 = 1;
/*     */     }
/*     */ 
/* 302 */     if (i1 != 0) localStringBuffer.delete(localStringBuffer.length() - 1, localStringBuffer.length());
/* 303 */     localStringBuffer.append("]");
/* 304 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   String e() {
/* 308 */     StringBuffer localStringBuffer = new StringBuffer("[");
/* 309 */     int i1 = 0;
/* 310 */     for (c localc : this.b) {
/* 311 */       if (localc.h()) {
/* 312 */         localStringBuffer.append(localc.d()).append(",");
/* 313 */         i1 = 1;
/*     */       }
/*     */     }
/*     */ 
/* 317 */     if (i1 != 0) localStringBuffer.delete(localStringBuffer.length() - 1, localStringBuffer.length());
/* 318 */     localStringBuffer.append("]");
/* 319 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   void d(c paramc)
/*     */   {
/* 327 */     this.a.remove(paramc);
/* 328 */     this.b.remove(paramc);
/*     */   }
/*     */ 
/*     */   void e(c paramc)
/*     */   {
/* 333 */     if (!paramc.y)
/*     */     {
/* 335 */       Logger.d("AppWidgetMgr.showMaskLayer " + paramc.v);
/* 336 */       if (this.n == null) this.n = new ArrayList();
/* 337 */       if (this.e.getMaskLayerCount() == 0) {
/* 338 */         this.e.registerSysEventListener(this.o, ISysEventListener.SysEventType.onKeyUp);
/*     */       }
/* 340 */       paramc.y = true;
/* 341 */       this.n.add(paramc);
/* 342 */       this.c.processEvent(IMgr.MgrType.WindowMgr, 29, paramc.u);
/*     */     }
/*     */   }
/*     */ 
/*     */   void f(c paramc)
/*     */   {
/* 376 */     if (paramc == null) {
/* 377 */       return;
/*     */     }
/*     */ 
/* 380 */     Logger.d("AppWidgetMgr.hideMaskLayer " + paramc.v);
/* 381 */     this.c.processEvent(IMgr.MgrType.WindowMgr, 30, paramc.u);
/* 382 */     if (this.e.getMaskLayerCount() == 0) {
/* 383 */       this.e.unregisterSysEventListener(this.o, ISysEventListener.SysEventType.onKeyUp);
/*     */     }
/* 385 */     paramc.y = false;
/* 386 */     if (this.n != null) this.n.remove(paramc);
/*     */   }
/*     */ 
/*     */   private boolean a(String paramString1, ISysEventListener.SysEventType paramSysEventType, String paramString2, int paramInt, boolean paramBoolean)
/*     */   {
/* 399 */     boolean bool1 = false;
/* 400 */     String str = String.format("{keyType:'%s',keyCode:%d}", new Object[] { paramString2, Integer.valueOf(paramInt) });
/* 401 */     if (this.a != null) {
/* 402 */       int i1 = this.a.size();
/* 403 */       Logger.d("AppWidgetMgr", "syncExecBaseEvent windowCount = " + i1);
/* 404 */       for (int i2 = i1 - 1; i2 >= 0; i2--) {
/* 405 */         c localc = (c)this.a.get(i2);
/* 406 */         if ((localc != null) && (localc.a == null) && (localc.B)) {
/* 407 */           boolean bool2 = localc.b(paramString1, str, paramBoolean);
/* 408 */           if (bool2) {
/* 409 */             bool1 = true;
/* 410 */             break;
/*     */           }
/* 412 */           if ((!BaseInfo.USE_ACTIVITY_HANDLE_KEYEVENT) && 
/* 413 */             (paramString2 != null) && (
/* 413 */             (localc.c(paramString2)) || ((paramSysEventType == ISysEventListener.SysEventType.onKeyDown) && (((localc.c("back")) && (paramInt == 4)) || ((localc.c("menu")) && (paramInt == 82)) || ((localc.c("volumedown")) && (paramInt == 25)) || ((localc.c("volumeup")) && (paramInt == 24)) || ((localc.c("search")) && (paramInt == 84))))))
/*     */           {
/* 425 */             bool1 = true;
/* 426 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 432 */     return bool1;
/*     */   }
/*     */ 
/*     */   public boolean onExecute(ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*     */   {
/* 437 */     if (BaseInfo.sDoingAnimation) return true;
/* 438 */     Object[] arrayOfObject = (Object[])paramObject;
/* 439 */     boolean bool = false;
/*     */     c localc;
/* 440 */     if ((paramSysEventType == ISysEventListener.SysEventType.onKeyUp) || (paramSysEventType == ISysEventListener.SysEventType.onKeyDown) || (paramSysEventType == ISysEventListener.SysEventType.onKeyLongPress)) {
/* 441 */       int i1 = ((Integer)arrayOfObject[0]).intValue();
/* 442 */       if (paramSysEventType == ISysEventListener.SysEventType.onKeyUp) {
/* 443 */         if (i1 == 4)
/*     */         {
/* 445 */           localc = c();
/* 446 */           IFrameView localIFrameView = null;
/*     */           Object localObject;
/* 447 */           if (localc != null) {
/* 448 */             localIFrameView = localc.u;
/* 449 */             localObject = String.format("{keyType:'%s',keyCode:%d}", new Object[] { "back", Integer.valueOf(i1) });
/* 450 */             bool = localc.b("back", (String)localObject, false);
/* 451 */             if ((!bool) && (this.b.size() == 1)) {
/* 452 */               this.c.processEvent(IMgr.MgrType.WindowMgr, 20, this.e);
/* 453 */               bool = true;
/*     */             }
/*     */           } else {
/* 456 */             localIFrameView = (IFrameView)this.c.processEvent(IMgr.MgrType.WindowMgr, 43, this.e);
/*     */           }
/* 458 */           if ((!bool) && (localIFrameView != null)) {
/* 459 */             localObject = localIFrameView.obtainWebView();
/* 460 */             if (((IWebview)localObject).canGoBack()) {
/* 461 */               ((IWebview)localObject).goBackOrForward(-1);
/* 462 */               bool = true;
/* 463 */             } else if (localc != null) {
/* 464 */               d(localc);
/* 465 */               localc.g();
/*     */             }
/* 467 */             if (!bool) {
/* 468 */               Collections.sort(this.b, this.v);
/* 469 */               Collections.sort(this.a, this.v);
/* 470 */               this.c.processEvent(IMgr.MgrType.WindowMgr, 26, this.e.obtainWebAppRootView());
/* 471 */               AnimOptions localAnimOptions = ((AdaFrameItem)localIFrameView).getAnimOptions();
/* 472 */               String str1 = "0";
/* 473 */               String str2 = "none";
/* 474 */               if (!PdrUtil.isEmpty(str1))
/* 475 */                 localAnimOptions.duration_close = PdrUtil.parseInt(str1, localAnimOptions.duration_close);
/*     */               else {
/* 477 */                 localAnimOptions.duration_close = localAnimOptions.duration_show;
/*     */               }
/* 479 */               localAnimOptions.mOption = 1;
/*     */ 
/* 481 */               localAnimOptions.setCloseAnimType(PdrUtil.isEmpty(str2) ? "auto" : str2);
/*     */ 
/* 483 */               this.c.processEvent(IMgr.MgrType.WindowMgr, 2, localIFrameView);
/*     */ 
/* 485 */               bool = true;
/*     */             }
/*     */           }
/*     */ 
/* 489 */           if (!bool)
/* 490 */             bool = a("back", paramSysEventType, "back", i1, false);
/*     */         }
/* 492 */         else if (i1 == 82) {
/* 493 */           bool = a("menu", paramSysEventType, "menu", i1, false);
/* 494 */         } else if (i1 == 24) {
/* 495 */           bool = a("volumeup", paramSysEventType, "volumeup", i1, false);
/* 496 */         } else if (i1 == 25) {
/* 497 */           bool = a("volumedown", paramSysEventType, "volumedown", i1, false);
/* 498 */         } else if (i1 == 84) {
/* 499 */           bool = a("search", paramSysEventType, "search", i1, false);
/*     */         }
/*     */       }
/* 502 */       localc = paramSysEventType == ISysEventListener.SysEventType.onKeyLongPress ? "longpressed" : paramSysEventType == ISysEventListener.SysEventType.onKeyDown ? "keydown" : paramSysEventType == ISysEventListener.SysEventType.onKeyUp ? "keyup" : null;
/*     */ 
/* 506 */       bool |= a(localc, paramSysEventType, localc, i1, false);
/*     */     }
/* 508 */     else if (paramSysEventType == ISysEventListener.SysEventType.onStop) {
/* 509 */       for (Iterator localIterator = this.b.iterator(); localIterator.hasNext(); ) { localc = (c)localIterator.next();
/* 510 */         localc.e().onDispose();
/* 511 */         localc.e().dispose();
/*     */       }
/* 513 */       bool = true;
/*     */     }
/* 515 */     return bool;
/*     */   }
/*     */ 
/*     */   boolean g(c paramc) {
/* 519 */     if (!b(this.e.obtainAppId())) {
/* 520 */       paramc.z = false;
/*     */     }
/* 522 */     if ((paramc.z) && (c(paramc.v))) {
/* 523 */       paramc.k().loadUrl(paramc.v);
/* 524 */       paramc.z = false;
/*     */     }
/* 526 */     return paramc.z;
/*     */   }
/*     */ 
/*     */   public boolean c(String paramString) {
/* 530 */     boolean bool = true;
/* 531 */     if ((this.f != null) && (paramString != null)) {
/* 532 */       if ((!PdrUtil.isNetPath(paramString.toLowerCase(Locale.getDefault()))) && (b(this.e.obtainAppId())))
/*     */       {
/* 535 */         if (BaseInfo.isWap2AppAppid(this.e.obtainAppId())) {
/* 536 */           if (paramString.startsWith("file://")) {
/* 537 */             paramString = paramString.substring("file://".length());
/*     */           }
/* 539 */           paramString = PdrUtil.stripQuery(PdrUtil.stripAnchor(paramString));
/* 540 */           if (!new File(paramString).exists())
/* 541 */             bool = false;
/*     */         }
/*     */         else {
/* 544 */           bool = this.f.queryUrl(paramString, this.e.obtainAppId());
/*     */         }
/*     */       }
/* 547 */       Logger.d("hasFile = " + bool + ";filePath=" + paramString);
/*     */     }
/* 549 */     return bool;
/*     */   }
/*     */ 
/*     */   boolean d(String paramString)
/*     */   {
/* 559 */     if (BaseInfo.isWap2AppAppid(this.e.obtainAppId())) {
/* 560 */       return true;
/*     */     }
/* 562 */     if (b(this.e.obtainAppId())) {
/* 563 */       boolean bool = this.g.invoke("checkFilepathValidity", paramString, false);
/* 564 */       return bool;
/*     */     }
/* 566 */     return true;
/*     */   }
/*     */ 
/*     */   private boolean e(String paramString)
/*     */   {
/* 571 */     return new File(paramString).exists();
/*     */   }
/*     */ 
/*     */   public void a(IApp paramIApp, String paramString, Bitmap paramBitmap) {
/* 575 */     if (DCloudAdapterUtil.isAutoCreateShortCut())
/* 576 */       ShortCutUtil.createShortcut(paramIApp, paramString, paramBitmap, true);
/*     */   }
/*     */ 
/*     */   public void a(final IApp paramIApp)
/*     */   {
/* 581 */     String str = DataInterface.getIconImageUrl(paramIApp.obtainAppId(), this.c.getContext().getResources().getDisplayMetrics().widthPixels + "");
/*     */ 
/* 585 */     ImageLoader.getInstance().loadImage(str, new ImageLoadingListener() {
/*     */       public void onLoadingStarted(String paramAnonymousString, View paramAnonymousView) {
/*     */       }
/*     */ 
/*     */       public void onLoadingFailed(String paramAnonymousString, View paramAnonymousView, FailReason paramAnonymousFailReason) {
/* 590 */         if (StringConst.canChangeHost(paramAnonymousString)) {
/* 591 */           paramAnonymousString = StringConst.changeHost(paramAnonymousString);
/* 592 */           ImageLoader.getInstance().loadImage(paramAnonymousString, this);
/*     */         }
/*     */       }
/*     */ 
/*     */       public void onLoadingComplete(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap)
/*     */       {
/* 598 */         a.this.a(paramIApp, ImageLoader.getInstance().getDiscCache().get(paramAnonymousString).getPath(), paramAnonymousBitmap);
/*     */       }
/*     */ 
/*     */       public void onLoadingCancelled(String paramAnonymousString, View paramAnonymousView) {
/*     */       } } );
/*     */   }
/*     */ 
/*     */   public void b(IApp paramIApp) {
/* 606 */     String str1 = StringConst.STREAMAPP_KEY_ROOTPATH + "splash/" + paramIApp.obtainAppId() + ".png";
/* 607 */     if ((!e(str1)) && (!PdrUtil.isEquals("true", SP.getBundleData("pdr", paramIApp.obtainAppId() + "_no_splash_at_server")))) {
/* 608 */       String str2 = this.c.getContext().getResources().getDisplayMetrics().widthPixels + "";
/* 609 */       String str3 = this.c.getContext().getResources().getDisplayMetrics().heightPixels + "";
/* 610 */       String str4 = DataInterface.getSplashUrl(paramIApp.obtainAppId(), str2, str3);
/*     */ 
/* 616 */       this.f.downloadSimpleFileTask(paramIApp, str4, str1, "splash");
/*     */     }
/*     */   }
/*     */ 
/*     */   boolean a(c paramc, String paramString)
/*     */   {
/* 626 */     this.s = paramc;
/* 627 */     boolean bool = false;
/* 628 */     if (c(paramString)) {
/* 629 */       Logger.d("webviewLoadUrl will loadUrl");
/* 630 */       paramc.k().loadUrl(paramString);
/* 631 */       bool = true;
/*     */     }
/*     */     else {
/* 634 */       paramc.z = true;
/*     */ 
/* 636 */       if (BaseInfo.isWap2AppAppid(this.e.obtainAppId())) {
/* 637 */         this.c.processEvent(IMgr.MgrType.AppMgr, 18, this.e);
/*     */       }
/*     */       else {
/* 640 */         this.p.a(paramc, paramString);
/*     */       }
/*     */     }
/* 643 */     return bool;
/*     */   }
/*     */ 
/*     */   private void b(int paramInt)
/*     */   {
/* 659 */     int i1 = 0;
/* 660 */     if (!TestUtil.PointTime.hasStreamAppStatus(this.e.getActivity(), this.e.obtainAppId(), "download_completed"))
/*     */     {
/* 662 */       if (TestUtil.PointTime.hasPointTime("stream_app_cache_pages")) {
/* 663 */         TestUtil.PointTime.getPointTime("stream_app_cache_pages").point(2, System.currentTimeMillis());
/* 664 */         StringBuffer localStringBuffer = new StringBuffer();
/* 665 */         TestUtil.PointTime localPointTime = TestUtil.PointTime.getPointTime("stream_app_cache_pages");
/* 666 */         localStringBuffer.append("&d=").append(localPointTime.getPoint(0));
/* 667 */         localStringBuffer.append("&de=").append(System.currentTimeMillis());
/* 668 */         localStringBuffer.append("&db=").append(localPointTime.getPoint(1));
/* 669 */         localStringBuffer.append("&dc=").append(localPointTime.getPoint(2));
/* 670 */         localStringBuffer.append("&v=").append(PdrUtil.encodeURL(this.e.obtainAppVersionName()));
/* 671 */         localStringBuffer.append("&bc=").append(paramInt);
/* 672 */         localStringBuffer.append("&br=").append(f());
/* 673 */         localStringBuffer.append("&net=").append(NetworkTypeUtil.getNetworkType(this.e.getActivity()));
/* 674 */         String str = null;
/*     */         try {
/* 676 */           str = URLEncoder.encode(this.q.toString(), "utf-8");
/* 677 */           this.q = new StringBuffer();
/*     */         } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/* 679 */           localUnsupportedEncodingException.printStackTrace();
/*     */         }
/* 681 */         localStringBuffer.append("&bl=").append(str);
/* 682 */         TestUtil.PointTime.commit(this.f, this.e.obtainAppId(), "stream_app_cache_pages", null, 6, null, localStringBuffer.toString());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void f(String paramString) {
/* 688 */     if (!TestUtil.PointTime.hasStreamAppStatus(this.e.getActivity(), this.e.obtainAppId(), "download_completed"))
/*     */     {
/* 690 */       Integer localInteger = (Integer)this.r.remove(paramString);
/* 691 */       if (localInteger == null)
/* 692 */         localInteger = new Integer(1);
/*     */       else {
/* 694 */         localInteger = new Integer(localInteger.intValue() + 1);
/*     */       }
/* 696 */       this.r.put(paramString, localInteger);
/*     */     }
/*     */   }
/*     */ 
/* 700 */   private int f() { int i1 = 0;
/* 701 */     Set localSet = this.r.keySet();
/* 702 */     for (String str : localSet) {
/* 703 */       int i2 = ((Integer)this.r.get(str)).intValue();
/* 704 */       if (i2 > i1) i1 = i2;
/*     */     }
/* 706 */     return i1; }
/*     */ 
/*     */   private void g()
/*     */   {
/* 710 */     if ((!TextUtils.isEmpty(this.t)) && (c(this.t)))
/* 711 */       f(this.s);
/*     */   }
/*     */ 
/*     */   private void h()
/*     */   {
/* 716 */     f(this.s);
/*     */   }
/*     */ 
/*     */   class b
/*     */     implements BroadcastReceiver
/*     */   {
/* 727 */     HashMap<String, ArrayList<c>> a = new HashMap();
/* 728 */     IActivityHandler b = null;
/*     */ 
/*     */     b(IActivityHandler arg2)
/*     */     {
/*     */       Object localObject;
/* 730 */       this.b = localObject;
/*     */     }
/*     */     public synchronized void a(c paramc, String paramString) {
/* 733 */       Logger.d("stream_manager", "requestStreamAppPage url=" + paramString);
/* 734 */       if (TextUtils.isEmpty(a.a(a.this))) {
/* 735 */         a.a(a.this, paramString);
/*     */       }
/* 737 */       if (!a.b(a.this).contains(paramString)) {
/* 738 */         a.b(a.this).add(paramString);
/*     */       }
/* 740 */       boolean bool = this.b.raiseFilePriority(paramString, a.this.e.obtainAppId());
/*     */ 
/* 745 */       ArrayList localArrayList = (ArrayList)this.a.get(paramString);
/* 746 */       if (localArrayList == null) {
/* 747 */         localArrayList = new ArrayList();
/* 748 */         this.a.put(paramString, localArrayList);
/*     */ 
/* 750 */         String str = paramString.substring(paramString.indexOf("www/") + "www/".length(), paramString.length());
/* 751 */         a.this.q.append(str).append(",");
/*     */       }
/* 753 */       localArrayList.add(paramc);
/* 754 */       if (!TestUtil.PointTime.hasStreamAppStatus(a.this.e.getActivity(), a.this.e.obtainAppId(), "download_completed"))
/*     */       {
/* 756 */         if (TestUtil.PointTime.hasPointTime("stream_app_cache_pages"))
/* 757 */           TestUtil.PointTime.getPointTime("stream_app_cache_pages").point(1, System.currentTimeMillis());
/*     */       }
/*     */     }
/*     */ 
/*     */     public synchronized void a(int paramInt, String paramString) {
/* 762 */       ArrayList localArrayList = (ArrayList)this.a.get(paramString);
/* 763 */       if (localArrayList != null) {
/* 764 */         Logger.i("stream_manager", "responseStreamAppPage url=" + paramString + " status=" + paramInt);
/* 765 */         int i = a.this.g.getInt("CONTRACT_STATUS_SUCCESS");
/* 766 */         if (paramInt == i) {
/* 767 */           for (c localc1 : localArrayList) {
/* 768 */             localc1.z = false;
/* 769 */             if (localc1.k() != null) {
/* 770 */               localc1.k().loadUrl(paramString);
/*     */             }
/*     */ 
/* 773 */             if (localc1.w == 1) {
/* 774 */               Object[] arrayOfObject = (Object[])localc1.x;
/* 775 */               IWebview localIWebview = (IWebview)arrayOfObject[0];
/* 776 */               JSONArray localJSONArray = (JSONArray)arrayOfObject[1];
/* 777 */               c localc2 = (c)arrayOfObject[2];
/* 778 */               String str = (String)arrayOfObject[3];
/* 779 */               localc1.a(localIWebview, localJSONArray, localc2, str);
/* 780 */               localc1.x = null;
/* 781 */             } else if (localc1.w != 2)
/*     */             {
/* 783 */               if (localc1.w != 3);
/*     */             }
/*     */ 
/* 786 */             if (localc1.y)
/*     */             {
/* 788 */               a.this.f(localc1);
/*     */             }
/*     */           }
/* 791 */           this.a.remove(paramString);
/*     */ 
/* 793 */           if (this.a.isEmpty())
/*     */           {
/* 795 */             a.a(a.this, 0);
/*     */           }
/*     */         }
/*     */         else {
/*     */           try {
/* 800 */             Thread.sleep(10L);
/*     */           } catch (InterruptedException localInterruptedException) {
/* 802 */             localInterruptedException.printStackTrace();
/*     */           }
/* 804 */           a.b(a.this, paramString);
/* 805 */           this.b.addAppStreamTask(paramString, a.this.e.obtainAppId());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     public void a()
/*     */     {
/* 864 */       IntentFilter localIntentFilter = new IntentFilter();
/* 865 */       localIntentFilter.addAction(a.this.j);
/* 866 */       this.b.registerReceiver(this, localIntentFilter);
/*     */     }
/*     */ 
/*     */     private void b() {
/* 870 */       if (TextUtils.isEmpty(a.a(a.this))) {
/* 871 */         c localc = a.this.b();
/*     */ 
/* 873 */         a.a(a.this, localc.k().getOriginalUrl());
/*     */ 
/* 875 */         ArrayList localArrayList = (ArrayList)this.a.get(a.a(a.this));
/*     */ 
/* 877 */         if (localArrayList == null) {
/* 878 */           localArrayList = new ArrayList();
/* 879 */           this.a.put(a.a(a.this), localArrayList);
/* 880 */           String str = a.a(a.this).substring(a.a(a.this).indexOf("www/") + "www/".length(), a.a(a.this).length());
/*     */ 
/* 884 */           a.this.q.append(str).append(",");
/*     */         }
/* 886 */         localArrayList.add(localc);
/*     */       }
/*     */     }
/*     */ 
/*     */     private void c() {
/* 891 */       for (c localc : a.this.b)
/* 892 */         if (localc.z) {
/* 893 */           localc.u.obtainWebView().loadUrl(localc.v);
/* 894 */           localc.z = false;
/*     */         }
/*     */     }
/*     */ 
/*     */     public void onReceive(Context paramContext, Intent paramIntent)
/*     */     {
/* 901 */       if (PdrUtil.isEquals(paramIntent.getAction(), a.this.j)) {
/* 902 */         Bundle localBundle = paramIntent.getExtras();
/* 903 */         String str1 = paramIntent.getStringExtra("flag");
/* 904 */         String str2 = localBundle.getString(a.this.g.getString("CONTRACT_INTENT_EXTRA_FILE"));
/* 905 */         if (!str2.startsWith("file://")) {
/* 906 */           str2 = "file://" + str2;
/*     */         }
/* 908 */         int i = localBundle.getInt(a.this.g.getString("CONTRACT_INTENT_EXTRA_TYPE"));
/* 909 */         int j = localBundle.getInt(a.this.g.getString("CONTRACT_INTENT_EXTRA_STATUS"));
/* 910 */         Logger.d("AppWidgetMgr.onReceive", new Object[] { Integer.valueOf(i), Integer.valueOf(j), str2, str1 });
/*     */         int k;
/* 911 */         if (str1.compareTo("icon") == 0)
/*     */         {
/* 913 */           k = a.this.g.getInt("CONTRACT_STATUS_SUCCESS");
/* 914 */           if (j == k)
/* 915 */             a.this.a(a.this.e, str2, null);
/*     */           else
/* 917 */             a.this.a(a.this.e);
/*     */         }
/* 919 */         else if (str1.compareTo("splash") == 0)
/*     */         {
/* 921 */           k = a.this.g.getInt("CONTRACT_STATUS_NOT_FOUND");
/* 922 */           if (j == k)
/* 923 */             SP.setBundleData("pdr", a.this.e.obtainAppId() + "_no_splash_at_server", "true");
/*     */         }
/*     */         else
/*     */         {
/*     */           Object localObject;
/* 925 */           if ("stream_page_zip".equalsIgnoreCase(str1)) {
/* 926 */             a.c(a.this);
/* 927 */             b();
/* 928 */             a(j, a.a(a.this));
/*     */ 
/* 930 */             for (k = 0; k < a.b(a.this).size(); k++) {
/* 931 */               localObject = (String)a.b(a.this).get(k);
/* 932 */               a(j, (String)localObject);
/*     */             }
/* 934 */             a.b(a.this).clear();
/* 935 */           } else if ("stream_idle_zip".equalsIgnoreCase(str1)) {
/* 936 */             a.d(a.this);
/* 937 */             b();
/* 938 */             a(j, a.a(a.this));
/* 939 */             for (k = 0; k < a.b(a.this).size(); k++) {
/* 940 */               localObject = (String)a.b(a.this).get(k);
/* 941 */               a(j, (String)localObject);
/*     */             }
/* 943 */             a.b(a.this).clear();
/* 944 */           } else if ("wap2app_src".equalsIgnoreCase(str1))
/*     */           {
/* 946 */             a.d(a.this);
/* 947 */             c();
/*     */           }
/* 950 */           else if ((i == a.this.g.getInt("CONTRACT_TYPE_STREAM_PAGE")) || (i == a.this.g.getInt("CONTRACT_TYPE_STREAM_MAIN_PAGE")) || (i == a.this.g.getInt("CONTRACT_TYPE_SINGLE_FILE")))
/*     */           {
/* 954 */             a(j, str2);
/* 955 */           } else if ((a.this.b(a.this.e.obtainAppId())) && (i == a.this.g.getInt("CONTRACT_TYPE_STREAM_APP")) && (j == a.this.g.getInt("CONTRACT_STATUS_SUCCESS"))) {
/* 956 */             Logger.d("stream_manager", "AppWidgetManager onReceive " + localBundle);
/*     */ 
/* 958 */             a.this.e.smartUpdate();
/* 959 */             if ((!TestUtil.PointTime.hasStreamAppStatus(paramContext, a.this.e.obtainAppId(), "installed")) && (TestUtil.PointTime.hasPointTime(TestUtil.STREAM_APP_POINT)))
/*     */             {
/* 962 */               TestUtil.PointTime.commit(a.this.f, a.this.e.obtainAppId(), 1, 0, "&v=" + PdrUtil.encodeURL(a.this.e.obtainAppVersionName()) + "&ac=5" + "&sf=" + StringConst.getIntSF(BaseInfo.getCmitInfo(a.this.e.obtainAppId()).plusLauncher) + "&sfd=" + BaseInfo.getCmitInfo(a.this.e.obtainAppId()).sfd);
/*     */             }
/*     */ 
/* 970 */             String str3 = TestUtil.PointTime.getAllCommitData(paramContext, a.this.e.obtainAppId());
/* 971 */             if (!TextUtils.isEmpty(str3)) {
/* 972 */               TestUtil.PointTime.commit(a.this.f, a.this.e.obtainAppId(), 3, 0, str3 + "&v=" + PdrUtil.encodeURL(a.this.e.obtainAppVersionName()));
/*     */             }
/*     */ 
/* 975 */             if (TestUtil.PointTime.hasPointTime("stream_app_completed")) {
/* 976 */               TestUtil.PointTime.getPointTime("stream_app_completed").point(2, System.currentTimeMillis());
/*     */             }
/*     */ 
/* 979 */             if (TestUtil.PointTime.hasPointTime("stream_app_completed")) {
/* 980 */               localObject = new StringBuffer();
/* 981 */               TestUtil.PointTime localPointTime = TestUtil.PointTime.getPointTime("stream_app_completed");
/* 982 */               ((StringBuffer)localObject).append("&d=").append(localPointTime.getPoint(0));
/* 983 */               ((StringBuffer)localObject).append("&de=").append(System.currentTimeMillis());
/* 984 */               ((StringBuffer)localObject).append("&di=").append(localPointTime.getPoint(1));
/* 985 */               ((StringBuffer)localObject).append("&df=").append(localPointTime.getPoint(2));
/* 986 */               ((StringBuffer)localObject).append("&v=").append(PdrUtil.encodeURL(a.this.e.obtainAppVersionName()));
/* 987 */               ((StringBuffer)localObject).append("&net=").append(NetworkTypeUtil.getNetworkType(a.this.e.getActivity()));
/* 988 */               String str4 = TestUtil.PointTime.getData(paramContext, a.this.e.obtainAppId(), "stream_app_start_times");
/* 989 */               if (!TextUtils.isEmpty(str4))
/* 990 */                 str4 = "&f=" + str4;
/*     */               else {
/* 992 */                 str4 = null;
/*     */               }
/* 994 */               TestUtil.PointTime.commit(a.this.f, a.this.e.obtainAppId(), "stream_app_completed", "stream_app_start_times", 5, str4, ((StringBuffer)localObject).toString());
/*     */             }
/* 996 */             a.this.b(a.this.e);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   class a
/*     */     implements Comparator<c>
/*     */   {
/*     */     a()
/*     */     {
/*     */     }
/*     */ 
/*     */     public int a(c paramc1, c paramc2)
/*     */     {
/* 650 */       int i = paramc1.A - paramc2.A;
/* 651 */       if (i == 0) {
/* 652 */         i = paramc1.q > paramc2.q ? 1 : -1;
/*     */       }
/* 654 */       return i;
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\ui.jar
 * Qualified Name:     io.dcloud.feature.ui.a
 * JD-Core Version:    0.6.2
 */