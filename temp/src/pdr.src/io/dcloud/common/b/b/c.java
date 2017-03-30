/*      */ package io.dcloud.common.b.b;
/*      */ 
/*      */ import android.app.Activity;
/*      */ import android.content.Context;
/*      */ import android.content.Intent;
/*      */ import android.content.res.Configuration;
/*      */ import android.content.res.Resources;
/*      */ import android.graphics.Bitmap;
/*      */ import android.graphics.Canvas;
/*      */ import android.graphics.Paint;
/*      */ import android.graphics.Region.Op;
/*      */ import android.text.TextUtils;
/*      */ import android.util.DisplayMetrics;
/*      */ import android.view.MotionEvent;
/*      */ import android.view.View;
/*      */ import android.view.ViewGroup;
/*      */ import android.view.ViewTreeObserver;
/*      */ import android.view.ViewTreeObserver.OnGlobalLayoutListener;
/*      */ import android.widget.ImageView;
/*      */ import android.widget.RelativeLayout;
/*      */ import com.dcloud.android.graphics.Region;
/*      */ import io.dcloud.application.DCloudApplication;
/*      */ import io.dcloud.common.DHInterface.FeatureMessageDispatcher;
/*      */ import io.dcloud.common.DHInterface.IActivityHandler;
/*      */ import io.dcloud.common.DHInterface.IApp;
/*      */ import io.dcloud.common.DHInterface.ICallBack;
/*      */ import io.dcloud.common.DHInterface.IFrameView;
/*      */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*      */ import io.dcloud.common.DHInterface.INativeView;
/*      */ import io.dcloud.common.DHInterface.IOnCreateSplashView;
/*      */ import io.dcloud.common.DHInterface.ISysEventListener;
/*      */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*      */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*      */ import io.dcloud.common.DHInterface.IWebview;
/*      */ import io.dcloud.common.adapter.ui.AdaContainerFrameItem;
/*      */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*      */ import io.dcloud.common.adapter.ui.AdaWebview;
/*      */ import io.dcloud.common.adapter.util.AndroidResources;
/*      */ import io.dcloud.common.adapter.util.AnimOptions;
/*      */ import io.dcloud.common.adapter.util.DeviceInfo;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.MessageHandler;
/*      */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*      */ import io.dcloud.common.adapter.util.PlatformUtil;
/*      */ import io.dcloud.common.adapter.util.SP;
/*      */ import io.dcloud.common.adapter.util.ViewOptions;
/*      */ import io.dcloud.common.b.a.a;
/*      */ import io.dcloud.common.constant.StringConst;
/*      */ import io.dcloud.common.util.BaseInfo;
/*      */ import io.dcloud.common.util.BaseInfo.CmtInfo;
/*      */ import io.dcloud.common.util.DialogUtil;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import io.dcloud.common.util.TestUtil;
/*      */ import io.dcloud.common.util.TestUtil.PointTime;
/*      */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.Stack;
/*      */ import java.util.Vector;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ class c extends AdaContainerFrameItem
/*      */   implements ISysEventListener, IWebAppRootView
/*      */ {
/*   78 */   ICallBack a = null;
/*      */ 
/*   80 */   private Stack<d> n = null;
/*      */ 
/*   82 */   private ArrayList<d> o = null;
/*   83 */   d b = null;
/*      */ 
/*   85 */   boolean c = true;
/*   86 */   String d = null;
/*   87 */   IApp e = null;
/*   88 */   public boolean f = false;
/*      */ 
/*   92 */   a g = new a();
/*      */ 
/*  849 */   boolean h = false;
/*      */ 
/*  970 */   int i = 0;
/*      */ 
/* 1047 */   private boolean p = true;
/*      */ 
/* 1049 */   private ArrayList<ICallBack> q = new ArrayList();
/*      */ 
/* 1174 */   c j = null;
/*      */ 
/* 1208 */   private d r = new d();
/*      */ 
/* 1258 */   private e s = new e();
/*      */   protected byte k;
/*      */   protected String l;
/*      */   protected String m;
/* 1534 */   private e t = null;
/*      */ 
/*      */   public c(Context paramContext, IApp paramIApp, d paramd)
/*      */   {
/*   94 */     super(paramContext);
/*   95 */     this.h = (BaseInfo.sRuntimeMode != null);
/*   96 */     this.e = paramIApp;
/*   97 */     this.d = paramIApp.obtainAppId();
/*   98 */     setMainView(new b(paramContext, this));
/*      */ 
/*  102 */     this.n = new Stack();
/*  103 */     this.o = new ArrayList();
/*  104 */     paramIApp.setWebAppRootView(this);
/*  105 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onPause);
/*  106 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onResume);
/*  107 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onDeviceNetChanged);
/*  108 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onNewIntent);
/*  109 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onConfigurationChanged);
/*  110 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onSimStateChanged);
/*  111 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyboardShow);
/*  112 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onWebAppBackground);
/*  113 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onWebAppForeground);
/*  114 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyboardHide);
/*  115 */     paramIApp.registerSysEventListener(this, ISysEventListener.SysEventType.onWebAppTrimMemory);
/*  116 */     if (a.a(this.d, "Device".toLowerCase())) {
/*  117 */       String str1 = PlatformUtil.getBundleData(BaseInfo.PDR, "last_notify_net_type");
/*  118 */       String str2 = DeviceInfo.getNetWorkType();
/*  119 */       if (!PdrUtil.isEquals(str1, str2)) {
/*  120 */         Logger.d("NetCheckReceiver", "netchange last_net_type:" + str1 + ";cur_net_type:" + str2);
/*  121 */         PlatformUtil.setBundleData(BaseInfo.PDR, "last_notify_net_type", str2);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private void a(d paramd, int paramInt1, int paramInt2)
/*      */   {
/*  133 */     Logger.d("DHAppRootView.pushFrameView" + paramd);
/*      */ 
/*  135 */     this.n.insertElementAt(paramd, paramInt1);
/*  136 */     addFrameItem(paramd, paramInt2);
/*      */   }
/*      */ 
/*      */   int a(d paramd)
/*      */   {
/*  154 */     return this.n.indexOf(paramd);
/*      */   }
/*      */ 
/*      */   void b(final d paramd)
/*      */   {
/*  162 */     MessageHandler.sendMessage(new MessageHandler.IMessages()
/*      */     {
/*      */       public void execute(Object paramAnonymousObject) {
/*  165 */         if ((c.a(c.this) != null) && (paramd != null)) {
/*  166 */           Logger.d("DHAppRootView.popFrameView frame" + paramd);
/*  167 */           c.a(c.this).remove(paramd);
/*  168 */           c.this.f(paramd);
/*      */         }
/*      */       }
/*      */     }
/*      */     , null);
/*      */   }
/*      */ 
/*      */   public d a()
/*      */   {
/*  179 */     d locald = null;
/*  180 */     if (!this.n.isEmpty()) {
/*  181 */       for (int i1 = this.n.size() - 1; i1 >= 0; i1--) {
/*  182 */         locald = (d)this.n.get(i1);
/*  183 */         if ((locald.obtainMainView().getVisibility() == 0) && (!locald.isChildOfFrameView)) {
/*      */           break;
/*      */         }
/*      */       }
/*      */     }
/*  188 */     return locald;
/*      */   }
/*      */ 
/*      */   Stack<d> b()
/*      */   {
/*  198 */     return this.n;
/*      */   }
/*      */ 
/*      */   boolean c(d paramd) {
/*  202 */     for (d locald : this.o) {
/*  203 */       if ((paramd != locald) && (!locald.isChildOfFrameView) && (locald.obtainMainView().getVisibility() == 0)) {
/*  204 */         return true;
/*      */       }
/*      */     }
/*  207 */     return false;
/*      */   }
/*      */ 
/*      */   ArrayList<d> c()
/*      */   {
/*  215 */     return this.o;
/*      */   }
/*      */ 
/*      */   public void d()
/*      */   {
/*  296 */     Logger.d("Animation_Path", "AppRootView dispatchConfigurationChanged(横竖屏切换、全屏非全屏切换、虚拟返回键栏隐藏显示) 引发调整栈窗口");
/*  297 */     ArrayList localArrayList1 = new ArrayList();
/*  298 */     final ArrayList localArrayList2 = new ArrayList();
/*  299 */     a(localArrayList2, localArrayList1);
/*  300 */     for (d locald : localArrayList2) {
/*  301 */       boolean bool = this.n.contains(locald);
/*  302 */       locald.j.processEvent(IMgr.MgrType.WindowMgr, 8, locald);
/*  303 */       locald.h = (!bool);
/*      */     }
/*      */ 
/*  310 */     MessageHandler.sendMessage(new MessageHandler.IMessages()
/*      */     {
/*      */       public void execute(Object paramAnonymousObject)
/*      */       {
/*      */         try {
/*  315 */           for (int i = c.a(c.this).size() - 1; i >= 0; i--) {
/*  316 */             d locald = (d)c.a(c.this).get(i);
/*  317 */             if (!localArrayList2.contains(locald)) {
/*  318 */               locald.j.processEvent(IMgr.MgrType.WindowMgr, 22, locald);
/*  319 */               locald.g = true;
/*      */             }
/*      */           }
/*      */         } catch (Exception localException) {
/*  323 */           localException.printStackTrace();
/*  324 */           Logger.w("DHAppRootView onConfigurationChanged", localException);
/*      */         }
/*      */       }
/*      */     }
/*      */     , null);
/*      */   }
/*      */ 
/*      */   void d(d paramd)
/*      */   {
/*  337 */     AnimOptions localAnimOptions = paramd.getAnimOptions();
/*      */ 
/*  339 */     int i1 = localAnimOptions.mOption;
/*      */ 
/*  341 */     if (((!paramd.d) && (!paramd.f) && (paramd.obtainFrameOptions().hasTransparentValue())) || (paramd.obtainMainView().getVisibility() != 0))
/*      */     {
/*  344 */       if ((i1 == 3) || (i1 == 1)) {
/*  345 */         localArrayList1 = new ArrayList();
/*  346 */         b(localArrayList1, paramd);
/*  347 */         paramd.c = localArrayList1;
/*  348 */         return;
/*      */       }
/*      */ 
/*  351 */       if (i1 == 2)
/*  352 */         return;
/*  353 */       if ((i1 == 4) || (i1 == 0)) {
/*  354 */         localArrayList2 = new ArrayList();
/*  355 */         a(localArrayList2, paramd);
/*  356 */         paramd.b = localArrayList2;
/*  357 */         return;
/*      */       }
/*      */     }
/*      */ 
/*  361 */     ArrayList localArrayList1 = new ArrayList();
/*  362 */     ArrayList localArrayList2 = new ArrayList();
/*      */ 
/*  364 */     Region localRegion = new Region();
/*  365 */     for (int i2 = this.o.size() - 1; i2 >= 0; i2--) {
/*  366 */       d locald = (d)this.o.get(i2);
/*  367 */       if (locald.obtainMainView().getVisibility() == 0) {
/*  368 */         if (!locald.isChildOfFrameView) {
/*  369 */           locald.e();
/*  370 */           Object localObject = locald.obtainFrameOptions();
/*  371 */           if ((i1 == 4) || (i1 == 0)) {
/*  372 */             if (a(localRegion)) {
/*  373 */               if (b(localArrayList1, locald)) {
/*  374 */                 break;
/*      */               }
/*      */             }
/*  377 */             else if ((((ViewOptions)localObject).hasTransparentValue()) || (!a(localRegion, ((ViewOptions)localObject).left, ((ViewOptions)localObject).top, ((ViewOptions)localObject).width, ((ViewOptions)localObject).height)))
/*      */             {
/*  379 */               a(localArrayList2, locald);
/*      */             }
/*  381 */             else if (b(localArrayList1, locald)) {
/*  382 */               break;
/*      */             }
/*      */ 
/*      */           }
/*  386 */           else if (i1 == 2) {
/*  387 */             ViewOptions localViewOptions = locald.obtainFrameOptions_Animate();
/*  388 */             if ((locald == paramd) && (localViewOptions != null)) {
/*  389 */               localObject = localViewOptions;
/*      */             }
/*  391 */             if (a(localRegion)) {
/*  392 */               if (b(localArrayList1, locald)) {
/*  393 */                 break;
/*      */               }
/*      */             }
/*  396 */             else if ((((ViewOptions)localObject).hasTransparentValue()) || (!a(localRegion, ((ViewOptions)localObject).left, ((ViewOptions)localObject).top, ((ViewOptions)localObject).width, ((ViewOptions)localObject).height)))
/*      */             {
/*  399 */               a(localArrayList2, locald);
/*      */             }
/*  401 */             else if (b(localArrayList1, locald))
/*      */               {
/*      */                 break;
/*      */               }
/*      */           }
/*  406 */           else if ((i1 == 3) || (i1 == 1)) {
/*  407 */             if (locald == paramd) {
/*  408 */               b(localArrayList1, locald);
/*      */             }
/*  410 */             else if (a(localRegion)) {
/*  411 */               b(localArrayList1, locald);
/*      */             }
/*  413 */             else if ((((ViewOptions)localObject).hasTransparentValue()) || (!a(localRegion, ((ViewOptions)localObject).left, ((ViewOptions)localObject).top, ((ViewOptions)localObject).width, ((ViewOptions)localObject).height)))
/*      */             {
/*  416 */               a(localArrayList2, locald);
/*      */             }
/*  418 */             else if (b(localArrayList1, locald))
/*      */               {
/*      */                 break;
/*      */               }
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  427 */         if (b(localArrayList1, locald)) {
/*      */           break;
/*      */         }
/*      */       }
/*      */     }
/*  432 */     paramd.c = localArrayList1;
/*  433 */     paramd.b = localArrayList2;
/*      */   }
/*      */ 
/*      */   private void a(ArrayList<d> paramArrayList1, ArrayList<d> paramArrayList2) {
/*  437 */     Region localRegion = new Region();
/*  438 */     for (int i1 = this.o.size() - 1; i1 >= 0; i1--) {
/*  439 */       d locald = (d)this.o.get(i1);
/*  440 */       if (locald.obtainMainView().getVisibility() == 0) {
/*  441 */         ViewOptions localViewOptions = locald.obtainFrameOptions();
/*  442 */         if (locald.a) {
/*  443 */           a(localRegion, localViewOptions.left, localViewOptions.top, localViewOptions.width, localViewOptions.height);
/*  444 */           a(paramArrayList1, locald);
/*      */         }
/*  447 */         else if (!locald.isChildOfFrameView) {
/*  448 */           locald.e();
/*      */ 
/*  450 */           if (a(localRegion)) {
/*  451 */             if (b(paramArrayList2, locald)) {
/*  452 */               break;
/*      */             }
/*      */           }
/*  455 */           else if ((localViewOptions.hasTransparentValue()) || (!a(localRegion, localViewOptions.left, localViewOptions.top, localViewOptions.width, localViewOptions.height)))
/*      */           {
/*  457 */             a(paramArrayList1, locald);
/*      */           }
/*  459 */           else if (b(paramArrayList2, locald))
/*      */             {
/*      */               break;
/*      */             }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  469 */         if (b(paramArrayList2, locald))
/*      */           break;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   boolean a(Region paramRegion)
/*      */   {
/*  477 */     boolean bool = paramRegion.quickContains(0, 0, this.e.getInt(0), this.e.getInt(1));
/*      */ 
/*  486 */     return bool;
/*      */   }
/*      */ 
/*      */   void a(ArrayList<d> paramArrayList, d paramd)
/*      */   {
/*  491 */     paramArrayList.add(paramd);
/*      */   }
/*      */ 
/*      */   boolean b(ArrayList<d> paramArrayList, d paramd)
/*      */   {
/*  496 */     paramArrayList.add(paramd);
/*      */ 
/*  499 */     return false;
/*      */   }
/*      */ 
/*      */   boolean a(Region paramRegion, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  511 */     boolean bool = paramRegion.quickContains(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4);
/*  512 */     if (!bool) {
/*  513 */       paramRegion.op(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4, Region.Op.UNION);
/*      */     }
/*  515 */     return bool;
/*      */   }
/*      */ 
/*      */   void e()
/*      */   {
/*  522 */     Collections.sort(this.o, this.r);
/*      */   }
/*      */ 
/*      */   int e(d paramd)
/*      */   {
/*  532 */     int i1 = 0;
/*  533 */     int i2 = this.o.indexOf(paramd);
/*      */     Object localObject;
/*      */     int i4;
/*  534 */     for (int i3 = this.n.size() - 1; i3 >= 0; i3--) {
/*  535 */       localObject = (d)this.n.get(i3);
/*  536 */       i4 = this.o.indexOf(localObject);
/*  537 */       if ((i2 > i4) && (((d)localObject).getFrameType() != 3)) {
/*  538 */         i1 = i3 + 1;
/*  539 */         break;
/*      */       }
/*      */     }
/*  542 */     i3 = i1;
/*  543 */     if (i1 != 0) {
/*  544 */       localObject = obtainMainViewGroup();
/*  545 */       i4 = ((ViewGroup)localObject).getChildCount();
/*  546 */       int i5 = 0;
/*  547 */       for (int i6 = 0; i6 < i4; i6++) {
/*  548 */         View localView = ((ViewGroup)localObject).getChildAt(i6);
/*  549 */         if (((localView instanceof ImageView)) || (((localView instanceof RelativeLayout)) && (localView.getTag().equals("shade"))))
/*  550 */           i3++;
/*      */         else {
/*  552 */           i5++;
/*      */         }
/*  554 */         if (i5 >= i1) break;
/*      */       }
/*      */     }
/*  557 */     a(paramd, i1, i3);
/*      */ 
/*  561 */     return i1;
/*      */   }
/*      */ 
/*      */   void f()
/*      */   {
/*  567 */     Collections.sort(this.n, this.r);
/*  568 */     int i1 = this.n.size();
/*  569 */     for (int i2 = 0; i2 < i1; i2++) {
/*  570 */       d locald = (d)this.n.get(i2);
/*  571 */       locald.obtainMainView().bringToFront();
/*      */     }
/*      */   }
/*      */ 
/*      */   void f(d paramd)
/*      */   {
/*  577 */     Logger.d("DHAppRootView.closeFrameView pFrameView=" + paramd);
/*  578 */     paramd.onDispose();
/*  579 */     paramd.onDestroy();
/*  580 */     System.gc();
/*      */   }
/*      */ 
/*      */   public synchronized void dispose()
/*      */   {
/*  589 */     g();
/*  590 */     this.n = null;
/*  591 */     this.o = null;
/*  592 */     if (this.j != null) {
/*  593 */       this.j.b = false;
/*      */     }
/*  595 */     if (this.t != null) {
/*  596 */       this.t.setImageBitmap(null);
/*  597 */       this.t = null;
/*      */     }
/*  599 */     super.dispose();
/*      */   }
/*      */ 
/*      */   public void g()
/*      */   {
/*  607 */     Logger.d(this.d + " clearFrameView");
/*      */     Object localObject1;
/*      */     Object localObject2;
/*  608 */     if (BaseInfo.sAppStateMap.containsKey(this.d)) {
/*  609 */       localObject1 = a();
/*  610 */       localObject2 = localObject1 != null ? ((d)localObject1).obtainMainView() : null;
/*  611 */       if (localObject2 != null) {
/*      */         try {
/*  613 */           Bitmap localBitmap = PlatformUtil.captureView((View)localObject2);
/*  614 */           if (localBitmap != null) {
/*  615 */             String str = StringConst.STREAMAPP_KEY_ROOTPATH + "splash_temp/" + this.d + ".png";
/*  616 */             PdrUtil.saveBitmapToFile(localBitmap, str);
/*  617 */             if (localBitmap != null) {
/*  618 */               localBitmap.recycle();
/*      */             }
/*  620 */             localBitmap = null;
/*      */           }
/*      */         } catch (Exception localException1) {
/*  623 */           localException1.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*  627 */     if (this.o != null) {
/*  628 */       localObject1 = new d[this.o.size()];
/*  629 */       this.o.toArray((Object[])localObject1);
/*  630 */       for (Object localObject3 : localObject1) {
/*      */         try {
/*  632 */           localObject3.onDestroy();
/*      */         } catch (Exception localException2) {
/*  634 */           localException2.printStackTrace();
/*      */         }
/*      */       }
/*  637 */       localObject1 = null;
/*  638 */       this.o.clear();
/*      */     }
/*  640 */     clearView();
/*  641 */     if (this.n != null)
/*  642 */       this.n.clear();
/*      */   }
/*      */ 
/*      */   public boolean onExecute(ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*      */   {
/*  648 */     String str1 = "";
/*      */     Object localObject1;
/*      */     Object localObject2;
/*      */     Object localObject3;
/*      */     String str2;
/*  649 */     switch (7.a[paramSysEventType.ordinal()]) {
/*      */     case 1:
/*  651 */       str1 = String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "pause" });
/*  652 */       break;
/*      */     case 2:
/*  654 */       str1 = String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "resume" });
/*  655 */       break;
/*      */     case 3:
/*  657 */       if (a.a(this.d, "Device".toLowerCase())) {
/*  658 */         str1 = String.format("javascript:plus.device.imsi = ['%s'];", new Object[] { DeviceInfo.getUpdateIMSI() });
/*  659 */         str1 = str1 + String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "imsichange" }); } break;
/*      */     case 4:
/*  664 */       boolean bool = a.a(this.d, "Device".toLowerCase());
/*  665 */       if (!bool) return false;
/*  666 */       localObject1 = PlatformUtil.getBundleData(BaseInfo.PDR, "last_notify_net_type");
/*  667 */       localObject2 = DeviceInfo.getNetWorkType();
/*  668 */       if (!PdrUtil.isEquals((String)localObject1, (String)localObject2)) {
/*  669 */         Logger.d("NetCheckReceiver", "netchange last_net_type:" + (String)localObject1 + ";cur_net_type:" + (String)localObject2);
/*  670 */         PlatformUtil.setBundleData(BaseInfo.PDR, "last_notify_net_type", (String)localObject2);
/*      */       } else {
/*  672 */         return false;
/*      */       }
/*  674 */       if ((BaseInfo.isQihooLifeHelper(DCloudApplication.getInstance())) && 
/*  675 */         (DCloudApplication.self().isQihooTrafficFreeValidate) && 
/*  676 */         (!BaseInfo.isWifi(DCloudApplication.getInstance()))) {
/*  677 */         localObject3 = InvokeExecutorHelper.TrafficProxy.invoke("getInstance", new Class[0], new Object[0]);
/*  678 */         InvokeExecutorHelper.TrafficProxy.setInstance(localObject3).invoke("start");
/*      */       }
/*      */ 
/*  685 */       str1 = String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "netchange" });
/*  686 */       str1 = String.format("javascript:plus.device.imsi = ['%s'];", new Object[] { DeviceInfo.getUpdateIMSI() }) + str1;
/*  687 */       break;
/*      */     case 5:
/*  689 */       localObject3 = String.valueOf(paramObject);
/*  690 */       this.e.setRuntimeArgs((String)localObject3);
/*  691 */       str2 = BaseInfo.getLauncherData(this.e.obtainAppId());
/*      */ 
/*  693 */       String str3 = SP.getBundleData("pdr", this.e.obtainAppId() + "LAUNCHTYPE");
/*  694 */       if (TextUtils.isEmpty(str3)) {
/*  695 */         str3 = "default";
/*      */       }
/*      */ 
/*  698 */       str1 = "javascript:" + String.format("try{if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime){((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime.arguments = %s;}}catch(e){};", new Object[] { this.e.obtainRuntimeArgs() }) + String.format("if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime){((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime.launcher = '%s';}", new Object[] { str2 }) + String.format("if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime){((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime.origin = '%s';}", new Object[] { str3 });
/*      */ 
/*  703 */       str1 = str1 + String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "newintent" });
/*  704 */       break;
/*      */     case 6:
/*  707 */       this.e.updateScreenInfo(1);
/*  708 */       return false;
/*      */     case 7:
/*  711 */       str1 = String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "keyboardshow" });
/*  712 */       break;
/*      */     case 8:
/*  715 */       str1 = String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "keyboardhide" });
/*  716 */       break;
/*      */     case 9:
/*  719 */       str1 = String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "background" });
/*  720 */       break;
/*      */     case 10:
/*  723 */       localObject3 = BaseInfo.getLauncherData(this.e.obtainAppId());
/*  724 */       if (this.e.obtainWebAppIntent().getBooleanExtra("__webapp_reply__", false)) {
/*  725 */         localObject3 = "default";
/*      */       } else {
/*  727 */         str2 = String.valueOf(paramObject);
/*  728 */         this.e.setRuntimeArgs(str2);
/*  729 */         str1 = "javascript:" + String.format("try{if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime){((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime.arguments = %s;}}catch(e){};", new Object[] { this.e.obtainRuntimeArgs() }) + String.format("if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime){((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime.launcher = '%s';}", new Object[] { localObject3 });
/*      */       }
/*      */ 
/*  734 */       str1 = str1 + String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true); e.active = '%s';/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "foreground", localObject3 });
/*  735 */       break;
/*      */     case 11:
/*  738 */       str1 = String.format("javascript:(function(){if(!((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){window.__load__plus__&&window.__load__plus__();}var e = document.createEvent('HTMLEvents');var evt = '%s';e.initEvent(evt, false, true);/*console.log('dispatch ' + evt + ' event');*/document.dispatchEvent(e);})();", new Object[] { "trimmemory" });
/*  739 */       break;
/*      */     }
/*      */ 
/*  745 */     if ((this.o != null) && (!TextUtils.isEmpty(str1))) {
/*  746 */       for (int i1 = this.o.size() - 1; i1 >= 0; i1--) {
/*  747 */         localObject1 = (d)this.o.get(i1);
/*  748 */         localObject2 = ((d)localObject1).obtainWebView();
/*  749 */         if (localObject2 != null) {
/*  750 */           ((IWebview)localObject2).loadUrl(str1);
/*      */         }
/*      */       }
/*      */     }
/*  754 */     return false;
/*      */   }
/*      */ 
/*      */   void a(boolean paramBoolean)
/*      */   {
/*  769 */     for (int i1 = this.o.size() - 1; i1 >= 0; i1--) {
/*  770 */       d locald = (d)this.o.get(i1);
/*  771 */       IWebview localIWebview = locald.obtainWebView();
/*  772 */       if (localIWebview != null) {
/*  773 */         localIWebview.reload();
/*      */       }
/*  775 */       if (!paramBoolean)
/*      */         break;
/*      */     }
/*      */   }
/*      */ 
/*      */   void a(String paramString) {
/*  781 */     String[] arrayOfString = paramString.split("\\|");
/*  782 */     for (int i1 = this.o.size() - 1; i1 >= 0; i1--) {
/*  783 */       d locald = (d)this.o.get(i1);
/*  784 */       IWebview localIWebview = locald.obtainWebView();
/*  785 */       if (localIWebview != null)
/*  786 */         for (int i2 = 0; i2 < arrayOfString.length; i2++)
/*  787 */           if (localIWebview.obtainUrl().startsWith(arrayOfString[i2])) {
/*  788 */             localIWebview.reload();
/*  789 */             break;
/*      */           }
/*      */     }
/*      */   }
/*      */ 
/*      */   void a(final IApp paramIApp)
/*      */   {
/*  797 */     boolean bool = Boolean.parseBoolean(paramIApp.obtainConfigProperty("waiting"));
/*      */ 
/*  799 */     if (bool)
/*  800 */       obtainMainView().postDelayed(new Runnable()
/*      */       {
/*      */         public void run() {
/*  803 */           Activity localActivity = paramIApp.getActivity();
/*  804 */           IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(localActivity);
/*  805 */           if (localIActivityHandler != null)
/*  806 */             localIActivityHandler.showSplashWaiting();
/*      */         }
/*      */       }
/*      */       , 100L);
/*      */   }
/*      */ 
/*      */   void a(final c paramc, d paramd, int paramInt, final boolean paramBoolean)
/*      */   {
/*  825 */     if (paramd != null) {
/*  826 */       Logger.d("approotview", "closeSplashScreen0 delay=" + paramInt + ";autoClose=" + paramBoolean);
/*  827 */       final d locald = paramd;
/*  828 */       View localView = locald.obtainMainView();
/*  829 */       if (localView != null) {
/*  830 */         localView.postDelayed(new Runnable()
/*      */         {
/*      */           public void run() {
/*  833 */             MessageHandler.sendMessage(new MessageHandler.IMessages()
/*      */             {
/*      */               public void execute(Object paramAnonymous2Object) {
/*  836 */                 Logger.d("approotview", "closeSplashScreen1;autoClose=" + c.4.this.a);
/*  837 */                 c.this.a(c.4.this.b, c.4.this.a);
/*      */               }
/*      */             }
/*      */             , locald);
/*      */           }
/*      */         }
/*      */         , Math.max(paramInt, 150));
/*      */       }
/*      */       else
/*      */       {
/*  843 */         Logger.d("approotview", "closeSplashScreen2;autoClose");
/*  844 */         a(paramc, paramBoolean);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean didCloseSplash()
/*      */   {
/*  852 */     return this.h;
/*      */   }
/*      */ 
/*      */   void a(c paramc, boolean paramBoolean)
/*      */   {
/*  858 */     Logger.d("Main_Path", "closeSplashScreen0 appid=" + this.d + ";" + paramBoolean + ";closeSplashDid=" + this.h);
/*  859 */     if ((paramc != null) && (!this.h))
/*      */     {
/*  862 */       if (!this.e.isStreamApp()) {
/*  863 */         BaseInfo.run5appEndTime = TestUtil.getUseTime("run_5app_time_key", "");
/*  864 */         TestUtil.delete("run_5app_time_key");
/*      */       }
/*      */ 
/*  868 */       Activity localActivity = getActivity();
/*  869 */       IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(localActivity);
/*  870 */       if (localIActivityHandler != null) {
/*  871 */         localIActivityHandler.closeAppStreamSplash(this.d);
/*  872 */         BaseInfo.setLoadingLaunchePage(false, "closeSplashScreen0");
/*      */       }
/*  874 */       IOnCreateSplashView localIOnCreateSplashView = this.e.getOnCreateSplashView();
/*  875 */       if (localIOnCreateSplashView != null) {
/*  876 */         localIOnCreateSplashView.onCloseSplash();
/*      */       }
/*      */ 
/*  879 */       if (BaseInfo.useStreamAppStatistic(localActivity)) {
/*  880 */         Logger.d("Main_Path", "closeSplashScreen0 will commit s=1");
/*  881 */         TestUtil.PointTime localPointTime = TestUtil.PointTime.getPointTime(TestUtil.STREAM_APP_POINT);
/*  882 */         if ((localPointTime == null) && (BaseInfo.isWap2AppAppid(this.d))) {
/*  883 */           localPointTime = TestUtil.PointTime.createPointTime(TestUtil.STREAM_APP_POINT, 7);
/*      */         }
/*  885 */         if (localPointTime != null) {
/*  886 */           if (!TestUtil.PointTime.hasStreamAppStatus(localActivity, this.d, "installed")) {
/*  887 */             localPointTime.point(6);
/*  888 */             str1 = localPointTime.getPointsString();
/*  889 */             Logger.i("download_manager", "closeSplashScreen0 " + this.d + " pointTime = " + str1);
/*  890 */             TestUtil.PointTime.commit(localIActivityHandler, this.d, 1, 0, "&v=" + PdrUtil.encodeURL(this.e.obtainAppVersionName()) + "&ac=" + (paramBoolean ? 1 : 3) + "&sf=" + StringConst.getIntSF(BaseInfo.getCmitInfo(this.d).plusLauncher) + "&sfd=" + BaseInfo.getCmitInfo(this.d).sfd);
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  898 */           str1 = TestUtil.PointTime.getData(localActivity, this.d, "in_app_commit_data");
/*  899 */           if (!TextUtils.isEmpty(str1)) {
/*  900 */             TestUtil.PointTime.commit(localIActivityHandler, this.d, 1, 0, str1 + "&v=" + PdrUtil.encodeURL(this.e.obtainAppVersionName()) + "&ac=" + (paramBoolean ? 2 : 4) + "&sf=" + StringConst.getIntSF(BaseInfo.getCmitInfo(this.d).plusLauncher) + "&sfd=" + BaseInfo.getCmitInfo(this.d).sfd);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  909 */         if (TestUtil.PointTime.hasPointTime("stream_app_completed")) {
/*  910 */           TestUtil.PointTime.getPointTime("stream_app_completed").point(1, System.currentTimeMillis());
/*      */         }
/*      */ 
/*  913 */         if (TestUtil.PointTime.hasStreamAppStatus(localActivity, this.d, "download_completed")) {
/*  914 */           str1 = TestUtil.PointTime.getData(localActivity, this.d, "stream_app_start_times");
/*  915 */           if (!TextUtils.isEmpty(str1)) {
/*  916 */             str2 = TestUtil.PointTime.getData(localActivity, this.d, TestUtil.PointTime.getData(localActivity, this.d, "stream_app_completed"));
/*  917 */             TestUtil.PointTime.commit(localIActivityHandler, this.d, "stream_app_completed", "stream_app_start_times", 5, "&f=" + str1, str2);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  922 */         String str1 = TestUtil.PointTime.getData(localActivity, this.d, "stream_app_cache_pages");
/*  923 */         if (!TextUtils.isEmpty(str1)) {
/*  924 */           TestUtil.PointTime.commit(localIActivityHandler, this.d, "stream_app_cache_pages", null, 6, null, str1);
/*      */         }
/*      */ 
/*  928 */         String str2 = PlatformUtil.getBundleData("pdr", "install_apk");
/*  929 */         if (!TextUtils.isEmpty(str2)) {
/*      */           try {
/*  931 */             JSONObject localJSONObject = new JSONObject(str2);
/*  932 */             String str3 = localJSONObject.getString("apkPath");
/*  933 */             String str4 = localJSONObject.getString("msg");
/*  934 */             PlatformUtil.removeBundleData("pdr", "install_apk");
/*  935 */             DialogUtil.showInstallAPKDialog(localActivity, str4, str3);
/*      */           }
/*      */           catch (JSONException localJSONException) {
/*  938 */             localJSONException.printStackTrace();
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  944 */     this.h = true;
/*      */   }
/*      */ 
/*      */   public void onAppStart(IApp paramIApp) {
/*  948 */     this.h = false;
/*  949 */     IApp localIApp = paramIApp;
/*  950 */     if (localIApp != null) {
/*  951 */       a(localIApp);
/*      */     }
/*  953 */     obtainMainView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
/*      */     {
/*      */       public void onGlobalLayout()
/*      */       {
/*  957 */         View localView = c.this.obtainMainView();
/*  958 */         c.this.onRootViewGlobalLayout(localView);
/*  959 */         if ((localView != null) && (DeviceInfo.sDeviceSdkVer >= 16) && (localView.getViewTreeObserver() != null)) {
/*  960 */           localView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
/*  961 */           localView.getViewTreeObserver().addOnGlobalLayoutListener(this);
/*      */         }
/*      */       }
/*      */     });
/*  966 */     obtainMainView().setBackgroundColor(-1);
/*  967 */     onAppActive(paramIApp);
/*      */   }
/*      */ 
/*      */   public void onRootViewGlobalLayout(View paramView)
/*      */   {
/*  972 */     if (isDisposed()) return;
/*  973 */     if (AdaWebview.ScreemOrientationChangedNeedLayout) {
/*  974 */       AdaWebview.ScreemOrientationChangedNeedLayout = false;
/*  975 */       this.e.updateScreenInfo(3);
/*      */     }
/*      */ 
/*  978 */     int i1 = paramView.getWidth();
/*  979 */     int i2 = paramView.getHeight();
/*  980 */     int i3 = i2 - this.e.getInt(1);
/*  981 */     if (!this.e.isVerticalScreen()) {
/*  982 */       i3 = i1 - this.e.getInt(0);
/*      */     }
/*  984 */     int i4 = paramView.getResources().getDisplayMetrics().heightPixels / 3;
/*  985 */     if (Math.abs(i3) > i4) {
/*  986 */       if (i3 < 0) {
/*  987 */         AndroidResources.sIMEAlive = true;
/*      */       } else {
/*  989 */         if ((AndroidResources.sIMEAlive) && (this.q.size() > 0)) {
/*  990 */           MessageHandler.sendMessage(new MessageHandler.IMessages()
/*      */           {
/*      */             public void execute(Object paramAnonymousObject)
/*      */             {
/*  994 */               for (ICallBack localICallBack : c.b(c.this)) {
/*  995 */                 localICallBack.onCallBack(-1, null);
/*  996 */                 localICallBack = null;
/*      */               }
/*  998 */               c.b(c.this).clear();
/*      */             }
/*      */           }
/*      */           , 500L, null);
/*      */         }
/*      */ 
/* 1002 */         AndroidResources.sIMEAlive = false;
/*      */       }
/*      */ 
/* 1005 */       ISysEventListener.SysEventType localSysEventType = ISysEventListener.SysEventType.onKeyboardShow;
/* 1006 */       if (!AndroidResources.sIMEAlive) {
/* 1007 */         localSysEventType = ISysEventListener.SysEventType.onKeyboardHide;
/*      */       }
/*      */ 
/* 1010 */       if (this.n == null);
/*      */     }
/*      */ 
/* 1025 */     if (i3 != 0) {
/* 1026 */       this.e.updateScreenInfo(3);
/*      */     }
/* 1034 */     else if (i3 != 0);
/* 1038 */     if ((paramView.getHeight() != this.i) && (paramView.getHeight() == this.e.getInt(1))) {
/* 1039 */       PlatformUtil.RESET_H_W();
/* 1040 */       if (!this.p)
/* 1041 */         BaseInfo.sFullScreenChanged = true;
/* 1042 */       this.p = false;
/*      */     }
/*      */ 
/* 1045 */     this.i = paramView.getHeight();
/*      */   }
/*      */ 
/*      */   public Object a(View paramView, ICallBack paramICallBack)
/*      */   {
/* 1052 */     if (!AndroidResources.sIMEAlive) {
/* 1053 */       return paramICallBack.onCallBack(-1, null);
/*      */     }
/* 1055 */     DeviceInfo.hideIME(obtainMainView());
/*      */ 
/* 1057 */     this.q.add(paramICallBack);
/*      */ 
/* 1059 */     return null;
/*      */   }
/*      */ 
/*      */   private void b(IApp paramIApp) {
/* 1063 */     String str = paramIApp.obtainConfigProperty("fullscreen");
/* 1064 */     boolean bool = PdrUtil.parseBoolean(str, false, false);
/* 1065 */     paramIApp.setFullScreen(bool);
/*      */   }
/*      */ 
/*      */   public void onAppActive(IApp paramIApp)
/*      */   {
/* 1103 */     b(paramIApp);
/* 1104 */     Activity localActivity = paramIApp.getActivity();
/* 1105 */     boolean bool = false;
/* 1106 */     IActivityHandler localIActivityHandler1 = DCloudAdapterUtil.getIActivityHandler(localActivity);
/* 1107 */     if (localIActivityHandler1 != null)
/*      */     {
/* 1109 */       PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "makeTitleViewDefault", null, new Class[] { Activity.class }, new Object[] { localActivity });
/*      */ 
/* 1111 */       PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "UpdateTitle", null, new Class[] { Activity.class, IApp.class }, new Object[] { localActivity, paramIApp });
/*      */ 
/* 1113 */       PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "resumeTitleView", null, new Class[] { Activity.class, IApp.class }, new Object[] { localActivity, paramIApp });
/*      */ 
/* 1137 */       localIActivityHandler1.setViewAsContentView(obtainMainView());
/* 1138 */       bool = localIActivityHandler1.isStreamAppMode();
/*      */     }
/*      */ 
/* 1142 */     Logger.d("Main_Path", paramIApp.obtainAppId() + " onAppActive setContentView");
/* 1143 */     a(obtainMainView());
/* 1144 */     if (bool) {
/* 1145 */       if ((!paramIApp.isJustDownload()) || (BaseInfo.isWap2AppAppid(this.d))) {
/* 1146 */         paramIApp.startSmartUpdate();
/*      */       }
/* 1148 */       if (!InvokeExecutorHelper.StorageUtils.invoke("checkDirResourceComplete", InvokeExecutorHelper.AppidUtils.invoke("getAppFilePathByAppid", paramIApp.obtainAppId()), false))
/*      */       {
/* 1154 */         IActivityHandler localIActivityHandler2 = DCloudAdapterUtil.getIActivityHandler(localActivity);
/* 1155 */         if ((!BaseInfo.isWap2AppAppid(paramIApp.obtainAppId())) && (localIActivityHandler2 != null)) {
/* 1156 */           localIActivityHandler2.resumeAppStreamTask(this.d);
/*      */         }
/*      */       }
/*      */     }
/* 1160 */     FeatureMessageDispatcher.dispatchMessage("app_open", Integer.valueOf(1));
/*      */   }
/*      */ 
/*      */   public void onAppUnActive(IApp paramIApp) {
/* 1164 */     if (this.j != null) {
/* 1165 */       this.j.a();
/* 1166 */       this.j = null;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onAppStop(IApp paramIApp) {
/* 1171 */     onAppUnActive(paramIApp);
/*      */   }
/*      */ 
/*      */   private void a(View paramView)
/*      */   {
/*      */   }
/*      */ 
/*      */   void g(d paramd)
/*      */   {
/* 1229 */     ArrayList localArrayList1 = paramd.getParentFrameItem().mChildArrayList;
/*      */     Iterator localIterator;
/*      */     Object localObject;
/* 1230 */     if (localArrayList1.size() > 1) {
/* 1231 */       Collections.sort(localArrayList1, this.s);
/*      */ 
/* 1233 */       localArrayList2 = new ArrayList();
/* 1234 */       for (localIterator = localArrayList1.iterator(); localIterator.hasNext(); ) { localObject = (AdaFrameItem)localIterator.next();
/* 1235 */         if (((AdaFrameItem)localObject).obtainMainView() != null)
/* 1236 */           ((AdaFrameItem)localObject).obtainMainView().bringToFront();
/*      */         else {
/* 1238 */           localArrayList2.add(localObject);
/*      */         }
/*      */       }
/* 1241 */       if (localArrayList2.size() > 0)
/* 1242 */         localArrayList1.removeAll(localArrayList2);
/*      */       else {
/* 1244 */         localArrayList2 = null;
/*      */       }
/*      */     }
/* 1247 */     ArrayList localArrayList2 = paramd.getParentFrameItem().getChildNativeViewList();
/* 1248 */     if (localArrayList2 != null)
/* 1249 */       for (localIterator = localArrayList2.iterator(); localIterator.hasNext(); ) { localObject = (INativeView)localIterator.next();
/* 1250 */         if (((INativeView)localObject).obtanMainView() != null)
/* 1251 */           ((INativeView)localObject).obtanMainView().bringToFront();
/*      */       }
/*      */   }
/*      */ 
/*      */   public IFrameView findFrameViewB(IFrameView paramIFrameView)
/*      */   {
/* 1468 */     if (!this.o.contains(paramIFrameView)) {
/* 1469 */       return null;
/*      */     }
/* 1471 */     ArrayList localArrayList = new ArrayList();
/* 1472 */     a(paramIFrameView, localArrayList);
/* 1473 */     if (localArrayList.size() > 1) {
/* 1474 */       return null;
/*      */     }
/* 1476 */     int i1 = this.o.indexOf(paramIFrameView);
/* 1477 */     if (this.o != null) {
/* 1478 */       for (int i2 = i1 - 1; i2 >= 0; i2--) {
/* 1479 */         d locald = (d)this.o.get(i2);
/* 1480 */         if ((!locald.isChildOfFrameView) && (locald.obtainMainView().getVisibility() == 0))
/*      */         {
/* 1482 */           return locald;
/*      */         }
/*      */       }
/*      */     }
/* 1486 */     return null;
/*      */   }
/*      */ 
/*      */   public void a(IFrameView paramIFrameView, ArrayList<d> paramArrayList) {
/* 1490 */     if (!this.o.contains(paramIFrameView)) {
/* 1491 */       return;
/*      */     }
/* 1493 */     int i1 = this.o.indexOf(paramIFrameView);
/* 1494 */     if (this.o != null) {
/* 1495 */       Region localRegion = new Region(1);
/* 1496 */       for (int i2 = i1 - 1; i2 >= 0; i2--) {
/* 1497 */         d locald = (d)this.o.get(i2);
/* 1498 */         if ((!locald.isChildOfFrameView) && (locald.obtainMainView().getVisibility() == 0))
/*      */         {
/* 1500 */           paramArrayList.add(locald);
/* 1501 */           ViewOptions localViewOptions = locald.obtainFrameOptions();
/* 1502 */           a(localRegion, localViewOptions.left, localViewOptions.top, localViewOptions.width, localViewOptions.height);
/*      */         }
/* 1504 */         if (a(localRegion))
/*      */           break;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void a(d paramd1, d paramd2)
/*      */   {
/* 1516 */     AnimOptions localAnimOptions = paramd1.getAnimOptions();
/* 1517 */     this.k = localAnimOptions.mOption;
/* 1518 */     localAnimOptions.mOption = paramd2.getAnimOptions().mOption;
/* 1519 */     this.l = localAnimOptions.mAnimType;
/* 1520 */     localAnimOptions.mAnimType = paramd2.getAnimOptions().mAnimType;
/* 1521 */     this.m = localAnimOptions.mAnimType_close;
/* 1522 */     localAnimOptions.mAnimType_close = paramd2.getAnimOptions().mAnimType_close;
/*      */   }
/*      */ 
/*      */   public void h(d paramd)
/*      */   {
/* 1529 */     AnimOptions localAnimOptions = paramd.getAnimOptions();
/* 1530 */     localAnimOptions.mOption = this.k;
/* 1531 */     localAnimOptions.mAnimType = this.l;
/* 1532 */     localAnimOptions.mAnimType_close = this.m;
/*      */   }
/*      */ 
/*      */   public e a(d paramd, int paramInt, boolean paramBoolean)
/*      */   {
/* 1544 */     long l1 = System.currentTimeMillis();
/* 1545 */     b localb = (b)obtainMainView();
/*      */ 
/* 1547 */     int i1 = 0;
/* 1548 */     boolean bool = a(localb);
/* 1549 */     if (bool) {
/* 1550 */       if (this.t != null) {
/* 1551 */         this.t.setImageBitmap(null);
/* 1552 */         this.t = null;
/*      */       }
/* 1554 */       return null;
/*      */     }
/*      */ 
/* 1557 */     Bitmap localBitmap = null;
/* 1558 */     if (paramd.mSnapshot != null) {
/* 1559 */       localBitmap = paramd.mSnapshot;
/*      */     }
/*      */     else
/*      */     {
/* 1563 */       if ((1 == paramInt) && (this.t != null) && (this.t.b() != null) && (this.t.getTag() != null) && (paramd.hashCode() == ((Integer)this.t.getTag()).intValue()))
/*      */       {
/* 1565 */         if (this.t.getParent() != localb) {
/* 1566 */           if (this.t.getParent() != null) {
/* 1567 */             ((ViewGroup)this.t.getParent()).removeView(this.t);
/*      */           }
/* 1569 */           localb.addView(this.t);
/*      */         }
/* 1571 */         this.t.bringToFront();
/* 1572 */         this.t.setVisibility(0);
/* 1573 */         return this.t;
/*      */       }
/* 1575 */       localBitmap = PlatformUtil.captureView(paramd.obtainMainView());
/* 1576 */       i1 = 1;
/*      */     }
/* 1578 */     if ((null != localBitmap) && (!PlatformUtil.isWhiteBitmap(localBitmap))) {
/* 1579 */       if (this.t == null) {
/* 1580 */         this.t = localb.b();
/*      */       }
/*      */ 
/* 1583 */       if (this.t.getParent() != localb) {
/* 1584 */         if (this.t.getParent() != null) {
/* 1585 */           ((ViewGroup)this.t.getParent()).removeView(this.t);
/*      */         }
/* 1587 */         localb.addView(this.t);
/*      */       }
/*      */ 
/* 1590 */       this.t.bringToFront();
/* 1591 */       this.t.setImageBitmap(localBitmap);
/*      */ 
/* 1594 */       this.t.setVisibility(0);
/*      */     }
/* 1596 */     else if (this.t != null) {
/* 1597 */       this.t.setImageBitmap(null);
/* 1598 */       this.t = null;
/*      */     }
/*      */ 
/* 1610 */     if (this.t.a()) {
/* 1611 */       return null;
/*      */     }
/* 1613 */     long l2 = System.currentTimeMillis();
/* 1614 */     Logger.i("mabo", "==============B截图耗时=" + (l2 - l1));
/*      */ 
/* 1616 */     if (l2 - l1 >= BaseInfo.sTimeoutCapture) {
/* 1617 */       BaseInfo.sTimeOutCount += 1;
/* 1618 */       if (BaseInfo.sTimeOutCount > BaseInfo.sTimeOutMax)
/* 1619 */         BaseInfo.sAnimationCaptureB = false;
/*      */     }
/* 1621 */     else if (i1 != 0) {
/* 1622 */       BaseInfo.sTimeOutCount = 0;
/*      */     }
/* 1624 */     this.t.c();
/* 1625 */     return this.t;
/*      */   }
/*      */ 
/*      */   public boolean a(ViewGroup paramViewGroup)
/*      */   {
/* 1634 */     boolean bool = false;
/* 1635 */     if ((this.t != null) && (this.t.c > 0L) && 
/* 1636 */       (paramViewGroup.getHeight() != this.t.c)) {
/* 1637 */       bool = true;
/*      */     }
/*      */ 
/* 1640 */     return bool;
/*      */   }
/*      */ 
/*      */   class a
/*      */   {
/* 1419 */     int a = 0;
/* 1420 */     Vector<d> b = new Vector();
/* 1421 */     private boolean d = false;
/*      */ 
/*      */     a() {  } 
/* 1424 */     void a(d paramd) { this.b.add(paramd);
/* 1425 */       this.a += 1;
/* 1426 */       if (this.a > 1) {
/* 1427 */         this.d = true;
/*      */       } else {
/* 1429 */         this.a = 1;
/* 1430 */         this.d = false;
/*      */       }
/*      */     }
/*      */ 
/*      */     void b(d paramd)
/*      */     {
/* 1438 */       this.b.remove(paramd);
/* 1439 */       this.a -= 1;
/*      */     }
/*      */ 
/*      */     int a() {
/* 1443 */       return this.a;
/*      */     }
/*      */   }
/*      */ 
/*      */   class b extends k
/*      */   {
/* 1275 */     Paint a = new Paint();
/*      */     int b;
/*      */     int c;
/*      */     int d;
/*      */     int e;
/* 1295 */     a f = new a();
/*      */ 
/*      */     public b(Context paramc, c arg3)
/*      */     {
/* 1279 */       super(localc);
/* 1280 */       int i = (int)(DeviceInfo.DEFAULT_FONT_SIZE * DeviceInfo.sDensity * 1.2D);
/*      */ 
/* 1282 */       this.a.setColor(-13421773);
/* 1283 */       this.a.setTextSize(i);
/* 1284 */       setTag("AppRootView");
/*      */ 
/* 1286 */       this.b = ((int)this.a.measureText("缓冲中 "));
/* 1287 */       this.c = ((int)this.a.measureText("..."));
/*      */     }
/*      */     protected void onMeasure(int paramInt1, int paramInt2) {
/* 1290 */       super.onMeasure(paramInt1, paramInt2);
/* 1291 */       this.d = ((c.this.e.getInt(0) - this.b - this.c) / 2);
/* 1292 */       this.e = ((int)(c.this.e.getInt(2) * 0.8D));
/*      */     }
/*      */ 
/*      */     protected void dispatchDraw(Canvas paramCanvas)
/*      */     {
/* 1299 */       super.dispatchDraw(paramCanvas);
/* 1300 */       if (c.this.e.getMaskLayerCount() > 0) {
/* 1301 */         if (!this.f.b) {
/* 1302 */           this.f.b = true;
/* 1303 */           this.f.run();
/*      */         }
/* 1305 */         paramCanvas.drawColor(-2013265920);
/* 1306 */         paramCanvas.drawText("缓冲中 ", this.d, this.e, this.a);
/* 1307 */         if (this.f.a == 1)
/* 1308 */           paramCanvas.drawText(".", this.d + this.b, this.e, this.a);
/* 1309 */         else if (this.f.a == 2)
/* 1310 */           paramCanvas.drawText("..", this.d + this.b, this.e, this.a);
/* 1311 */         else if (this.f.a == 3) {
/* 1312 */           paramCanvas.drawText("...", this.d + this.b, this.e, this.a);
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1324 */         this.f.b = false;
/*      */       }
/*      */     }
/*      */ 
/*      */     public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
/*      */     {
/* 1348 */       if (c.this.e.getMaskLayerCount() > 0) {
/* 1349 */         return true;
/*      */       }
/*      */ 
/* 1367 */       return super.dispatchTouchEvent(paramMotionEvent);
/*      */     }
/*      */ 
/*      */     public void dispatchConfigurationChanged(Configuration paramConfiguration) {
/* 1371 */       super.dispatchConfigurationChanged(paramConfiguration);
/* 1372 */       if (BaseInfo.sDoingAnimation)
/* 1373 */         c.this.a = new ICallBack()
/*      */         {
/*      */           public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject) {
/* 1376 */             c.b.this.j.d();
/* 1377 */             c.this.a = null;
/* 1378 */             return null;
/*      */           }
/*      */         };
/*      */       else
/* 1382 */         this.j.d();
/*      */     }
/*      */ 
/*      */     protected void onConfigurationChanged(Configuration paramConfiguration)
/*      */     {
/* 1387 */       super.onConfigurationChanged(paramConfiguration);
/* 1388 */       c.d(c.this).onScreenChanged();
/* 1389 */       PlatformUtil.RESET_H_W();
/*      */     }
/*      */ 
/*      */     protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 1393 */       super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
/* 1394 */       c.e(c.this).onScreenChanged(paramInt1, paramInt2);
/* 1395 */       post(new Runnable()
/*      */       {
/*      */         public void run()
/*      */         {
/* 1400 */           if (c.c(c.this) != null)
/* 1401 */             for (d locald : c.c(c.this))
/* 1402 */               if (!locald.isChildOfFrameView)
/* 1403 */                 locald.resize();
/*      */         }
/*      */       });
/*      */     }
/*      */ 
/*      */     protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     {
/* 1414 */       super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     }
/*      */ 
/*      */     class a
/*      */       implements Runnable
/*      */     {
/* 1329 */       int a = 0;
/* 1330 */       boolean b = false;
/*      */ 
/*      */       a() {  } 
/* 1333 */       public void run() { if (this.b) {
/* 1334 */           this.a += 1;
/* 1335 */           c.b.this.invalidate();
/* 1336 */           this.a %= 4;
/* 1337 */           c.b.this.postDelayed(this, 500L);
/*      */         } else {
/* 1339 */           this.a = 0;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   class e
/*      */     implements Comparator<AdaFrameItem>
/*      */   {
/*      */     e()
/*      */     {
/*      */     }
/*      */ 
/*      */     public int a(AdaFrameItem paramAdaFrameItem1, AdaFrameItem paramAdaFrameItem2)
/*      */     {
/* 1262 */       if (((paramAdaFrameItem1 instanceof IFrameView)) && (((IFrameView)paramAdaFrameItem1).getFrameType() == 3)) return 1;
/* 1263 */       if (((paramAdaFrameItem1 instanceof IFrameView)) && (((IFrameView)paramAdaFrameItem1).getFrameType() == 3)) return -1;
/*      */ 
/* 1265 */       int i = paramAdaFrameItem1.mZIndex - paramAdaFrameItem2.mZIndex;
/* 1266 */       if (i == 0) {
/* 1267 */         i = paramAdaFrameItem1.lastShowTime > paramAdaFrameItem2.lastShowTime ? 1 : -1;
/*      */       }
/* 1269 */       return i;
/*      */     }
/*      */   }
/*      */ 
/*      */   class d
/*      */     implements Comparator<d>
/*      */   {
/*      */     d()
/*      */     {
/*      */     }
/*      */ 
/*      */     public int a(d paramd1, d paramd2)
/*      */     {
/* 1213 */       if (paramd1.getFrameType() == 3) return 1;
/* 1214 */       if (paramd2.getFrameType() == 3) return -1;
/* 1215 */       int i = paramd1.mZIndex - paramd2.mZIndex;
/* 1216 */       if (i == 0) {
/* 1217 */         i = paramd1.lastShowTime > paramd2.lastShowTime ? 1 : -1;
/*      */       }
/* 1219 */       return i;
/*      */     }
/*      */   }
/*      */ 
/*      */   class c
/*      */     implements Runnable
/*      */   {
/*      */     View a;
/*      */     boolean b;
/*      */ 
/*      */     public void run()
/*      */     {
/* 1191 */       System.gc();
/* 1192 */       if (this.b) {
/* 1193 */         Logger.d("AutoGC_Path", "================");
/* 1194 */         Logger.d("AutoGC_Path", "AutoGCRunnable freeMemory=" + Runtime.getRuntime().freeMemory());
/* 1195 */         Logger.d("AutoGC_Path", new Object[] { Integer.valueOf("Total of ArrayList is " + c.c(this.c) != null ? c.c(this.c).size() : 0) });
/* 1196 */         Logger.d("AutoGC_Path", new Object[] { Integer.valueOf("Size of Stack is " + c.a(this.c) != null ? c.a(this.c).size() : 0) });
/* 1197 */         Logger.d("AutoGC_Path", "================");
/* 1198 */         this.a.postDelayed(this, 60000L);
/*      */       }
/*      */     }
/*      */ 
/*      */     public void a() {
/* 1203 */       this.b = false;
/*      */     }
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.c
 * JD-Core Version:    0.6.2
 */