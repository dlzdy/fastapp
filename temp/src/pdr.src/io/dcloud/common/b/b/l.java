/*      */ package io.dcloud.common.b.b;
/*      */ 
/*      */ import android.app.Activity;
/*      */ import android.content.Context;
/*      */ import android.content.Intent;
/*      */ import android.content.res.Resources;
/*      */ import android.graphics.Canvas;
/*      */ import android.os.Build.VERSION;
/*      */ import android.text.TextUtils;
/*      */ import android.util.TypedValue;
/*      */ import android.view.View;
/*      */ import android.view.ViewGroup;
/*      */ import android.view.ViewGroup.LayoutParams;
/*      */ import android.view.WindowManager.LayoutParams;
/*      */ import android.webkit.WebView;
/*      */ import io.dcloud.common.DHInterface.AbsMgr;
/*      */ import io.dcloud.common.DHInterface.IActivityHandler;
/*      */ import io.dcloud.common.DHInterface.IApp;
/*      */ import io.dcloud.common.DHInterface.IApp.ConfigProperty.ThridInfo;
/*      */ import io.dcloud.common.DHInterface.ICallBack;
/*      */ import io.dcloud.common.DHInterface.ICore;
/*      */ import io.dcloud.common.DHInterface.IEventCallback;
/*      */ import io.dcloud.common.DHInterface.IFrameView;
/*      */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*      */ import io.dcloud.common.DHInterface.IMgr.WindowEvent;
/*      */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*      */ import io.dcloud.common.DHInterface.IWebview;
/*      */ import io.dcloud.common.DHInterface.IWebviewStateListener;
/*      */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*      */ import io.dcloud.common.adapter.ui.AdaFrameItem.LayoutParamsUtil;
/*      */ import io.dcloud.common.adapter.ui.AdaFrameView;
/*      */ import io.dcloud.common.adapter.ui.AdaWebViewParent;
/*      */ import io.dcloud.common.adapter.util.AndroidResources;
/*      */ import io.dcloud.common.adapter.util.AnimOptions;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.MessageHandler;
/*      */ import io.dcloud.common.adapter.util.ViewOptions;
/*      */ import io.dcloud.common.adapter.util.ViewRect;
/*      */ import io.dcloud.common.util.BaseInfo;
/*      */ import io.dcloud.common.util.JSONUtil;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import io.dcloud.common.util.TestUtil;
/*      */ import io.dcloud.common.util.TestUtil.PointTime;
/*      */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*      */ import java.io.File;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Set;
/*      */ import java.util.Stack;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ public class l extends AbsMgr
/*      */   implements IMgr.WindowEvent
/*      */ {
/*   70 */   HashMap<String, c> a = new HashMap(0);
/*      */ 
/*  904 */   String b = null;
/*      */ 
/* 1404 */   a c = null;
/* 1405 */   WindowManager.LayoutParams d = null;
/*      */ 
/*      */   public l(ICore paramICore)
/*      */   {
/*   74 */     super(paramICore, "windowmgr", IMgr.MgrType.WindowMgr);
/*      */   }
/*      */ 
/*      */   void a(ViewGroup paramViewGroup, IApp paramIApp, IWebview paramIWebview, ViewGroup.LayoutParams paramLayoutParams)
/*      */   {
/*   81 */     a(paramIApp, paramIApp.obtainAppId());
/*   82 */     c localc = (c)this.a.get(paramIApp.obtainAppId());
/*   83 */     d locald = (d)paramIWebview.obtainFrameView();
/*   84 */     locald.l = localc;
/*      */ 
/*   86 */     View localView = locald.obtainMainView();
/*   87 */     if (localView.getParent() != null) {
/*   88 */       ((ViewGroup)localView.getParent()).removeView(localView);
/*      */     }
/*   90 */     paramViewGroup.addView(localView, paramLayoutParams);
/*      */   }
/*      */ 
/*      */   synchronized boolean a(IApp paramIApp, String paramString)
/*      */   {
/*  104 */     Logger.e("streamsdk", "come into createAppRootView pAppid===" + paramString);
/*  105 */     boolean bool = false;
/*  106 */     c localc = (c)this.a.get(paramString);
/*  107 */     if ((localc == null) || (!localc.c)) {
/*  108 */       if ((localc != null) && (!localc.c)) {
/*  109 */         this.a.remove(paramString);
/*      */       }
/*  111 */       Logger.e("streamsdk", "come into createAppRootView and new le rootview  pAppid===" + paramString);
/*  112 */       Logger.d("Main_Path", "create " + paramString + " AppRootView");
/*  113 */       d locald = null;
/*  114 */       localc = new c(paramIApp.getActivity(), paramIApp, locald);
/*  115 */       localc.onAppStart(paramIApp);
/*  116 */       localc.obtainFrameOptions().setParentViewRect(paramIApp.getAppViewRect());
/*  117 */       localc.obtainFrameOptions().updateViewData(JSONUtil.createJSONObject("{}"));
/*  118 */       bool = true;
/*  119 */       this.a.put(paramString, localc);
/*      */     }
/*  121 */     return bool;
/*      */   }
/*      */ 
/*      */   public Object processEvent(IMgr.MgrType paramMgrType, final int paramInt, Object paramObject)
/*      */   {
/*  126 */     Object localObject1 = null;
/*      */     try {
/*  128 */       if (!checkMgrId(paramMgrType)) {
/*  129 */         localObject1 = this.mCore.dispatchEvent(paramMgrType, paramInt, paramObject);
/*      */       }
/*      */       else
/*      */       {
/*      */         Object localObject2;
/*      */         Object localObject3;
/*      */         Object localObject6;
/*      */         Object localObject9;
/*      */         Object localObject11;
/*      */         Object localObject13;
/*      */         Object localObject14;
/*  131 */         switch (paramInt) {
/*      */         case 20:
/*  133 */           localObject2 = null;
/*  134 */           if ((paramObject instanceof IApp))
/*  135 */             localObject2 = (c)((IApp)paramObject).obtainWebAppRootView();
/*  136 */           else if ((paramObject instanceof String)) {
/*  137 */             localObject2 = (c)this.a.get((String)paramObject);
/*      */           }
/*  139 */           if (localObject2 != null) {
/*  140 */             ((c)localObject2).e.setStatus((byte)2);
/*  141 */             ((c)localObject2).c = false;
/*      */           }
/*  143 */           processEvent(IMgr.MgrType.AppMgr, 10, paramObject);
/*      */ 
/*  152 */           break;
/*      */         case 17:
/*  155 */           localObject2 = (Object[])paramObject;
/*  156 */           localObject3 = (IApp)localObject2[0];
/*  157 */           localObject6 = (String)localObject2[1];
/*  158 */           localObject9 = (IWebviewStateListener)localObject2[2];
/*  159 */           a((IApp)localObject3, ((IApp)localObject3).obtainAppId());
/*  160 */           localObject11 = (c)this.a.get(((IApp)localObject3).obtainAppId());
/*      */ 
/*  162 */           localObject13 = (IFrameView)processEvent(IMgr.MgrType.WindowMgr, 3, new Object[] { Integer.valueOf(2), localObject3, { localObject6 }, localObject11 });
/*      */ 
/*  166 */           localObject14 = ((IFrameView)localObject13).obtainWebView();
/*  167 */           if (localObject9 != null) {
/*  168 */             ((IWebview)localObject14).addStateListener((IWebviewStateListener)localObject9);
/*  169 */             ((IWebviewStateListener)localObject9).onCallBack(-1, localObject14);
/*      */           }
/*  171 */           ((IWebview)localObject14).loadUrl((String)localObject6);
/*  172 */           localObject1 = localObject13;
/*  173 */           break;
/*      */         case 16:
/*  176 */           localObject2 = (Object[])paramObject;
/*  177 */           localObject3 = (ViewGroup)localObject2[0];
/*  178 */           localObject6 = (IApp)localObject2[1];
/*  179 */           localObject9 = (IWebview)localObject2[2];
/*  180 */           localObject11 = (ViewGroup.LayoutParams)localObject2[3];
/*  181 */           a((ViewGroup)localObject3, (IApp)localObject6, (IWebview)localObject9, (ViewGroup.LayoutParams)localObject11);
/*  182 */           break;
/*      */         case 32:
/*  185 */           localObject2 = null;
/*  186 */           localObject3 = null;
/*  187 */           if ((paramObject instanceof IApp)) {
/*  188 */             localObject6 = (IApp)paramObject;
/*  189 */             localObject2 = ((IApp)localObject6).getActivity();
/*  190 */             localObject3 = ((IApp)localObject6).obtainAppId();
/*  191 */           } else if ((paramObject instanceof Object[])) {
/*  192 */             localObject6 = (Object[])paramObject;
/*  193 */             localObject2 = (Activity)localObject6[0];
/*  194 */             localObject3 = (String)localObject6[1];
/*      */           }
/*  196 */           localObject6 = this.a.keySet().iterator();
/*  197 */           localObject1 = Boolean.valueOf(false);
/*      */         case 25:
/*      */         case 43:
/*      */         case 42:
/*      */         case 41:
/*      */         case 44:
/*      */         case 5:
/*      */         case 10:
/*      */         case 11:
/*      */         case 4:
/*      */         case 18:
/*      */         case 6:
/*      */         case 26:
/*      */         case 3:
/*      */         case 1:
/*      */         case 24:
/*      */         case 23:
/*      */         case 2:
/*      */         case 21:
/*      */         case 28:
/*      */         case 27:
/*      */         case 22:
/*      */         case 31:
/*      */         case 8:
/*      */         case 9:
/*      */         case 7:
/*      */         case 13:
/*      */         case 12:
/*      */         case 14:
/*      */         case 29:
/*      */         case 30:
/*      */         case -1:
/*      */         case 33:
/*      */         case 40:
/*      */         case 45:
/*  198 */           while (((Iterator)localObject6).hasNext()) {
/*  199 */             localObject9 = (String)((Iterator)localObject6).next();
/*  200 */             if (!PdrUtil.isEquals((String)localObject9, (String)localObject3)) {
/*  201 */               localObject11 = (c)this.a.get(localObject9);
/*  202 */               if (localObject2 == ((c)localObject11).getActivity()) {
/*  203 */                 localObject1 = Boolean.valueOf(true);
/*  204 */                 break;
/*      */               }
/*      */             }
/*  207 */             continue;
/*      */ 
/*  211 */             localObject2 = (IApp)paramObject;
/*  212 */             localObject3 = ((IApp)localObject2).getActivity();
/*  213 */             localObject6 = (c)((IApp)localObject2).obtainWebAppRootView();
/*  214 */             ((c)localObject6).dispose();
/*  215 */             this.a.remove(((c)localObject6).d);
/*      */ 
/*  219 */             break;
/*      */ 
/*  222 */             localObject2 = (IApp)paramObject;
/*  223 */             localObject1 = ((c)((IApp)localObject2).obtainWebAppRootView()).b;
/*  224 */             break;
/*      */ 
/*  227 */             localObject2 = (d)paramObject;
/*  228 */             if (localObject2 == ((d)localObject2).l.b) {
/*  229 */               ((d)localObject2).l.b = null; break;
/*      */ 
/*  235 */               a(paramInt, paramObject);
/*  236 */               break;
/*      */ 
/*  238 */               localObject2 = (IApp)paramObject;
/*  239 */               localObject3 = (c)this.a.get(((IApp)localObject2).obtainAppId());
/*  240 */               localObject1 = ((c)localObject3).a();
/*  241 */               break;
/*      */ 
/*  245 */               b(paramInt, paramObject);
/*  246 */               break;
/*      */ 
/*  249 */               localObject2 = (d)paramObject;
/*  250 */               ((d)localObject2).l.a(((d)localObject2).l, false);
/*  251 */               break;
/*      */ 
/*  254 */               localObject2 = (Object[])paramObject;
/*  255 */               localObject1 = Boolean.valueOf(a((IApp)localObject2[0], String.valueOf(localObject2[1])));
/*  256 */               break;
/*      */ 
/*  259 */               localObject1 = a();
/*  260 */               break;
/*      */ 
/*  263 */               if ((paramObject instanceof String)) {
/*  264 */                 localObject2 = (String)paramObject;
/*  265 */                 localObject1 = ((c)this.a.get(localObject2)).c();
/*      */               } else {
/*  267 */                 localObject1 = b().c();
/*      */ 
/*  269 */                 break;
/*      */ 
/*  272 */                 localObject2 = (c)paramObject;
/*  273 */                 if (localObject2 != null) {
/*  274 */                   ((c)localObject2).e();
/*  275 */                   ((c)localObject2).f(); break;
/*      */ 
/*  280 */                   localObject2 = (Object[])paramObject;
/*  281 */                   int i = Integer.parseInt(String.valueOf(localObject2[0]));
/*  282 */                   localObject6 = (IApp)localObject2[1];
/*  283 */                   localObject9 = (Object[])localObject2[2];
/*  284 */                   localObject11 = null;
/*  285 */                   localObject13 = null;
/*  286 */                   if (localObject2.length >= 4) {
/*  287 */                     if ((localObject2[3] instanceof c))
/*  288 */                       localObject11 = (c)localObject2[3];
/*  289 */                     else if ((localObject2[3] instanceof d)) {
/*  290 */                       localObject13 = (d)localObject2[3];
/*      */                     }
/*      */                   }
/*  293 */                   localObject14 = null;
/*  294 */                   if ((localObject2.length >= 5) && 
/*  295 */                     ((localObject2[4] instanceof IEventCallback))) {
/*  296 */                     localObject14 = (IEventCallback)localObject2[4];
/*      */                   }
/*      */ 
/*  299 */                   localObject11 = localObject11 == null ? (c)((IApp)localObject6).obtainWebAppRootView() : localObject11;
/*      */ 
/*  301 */                   localObject13 = localObject13 == null ? ((c)localObject11).a() : localObject11 == null ? null : localObject13;
/*      */ 
/*  305 */                   Object localObject15 = null;
/*  306 */                   if (localObject2.length == 6)
/*  307 */                     localObject15 = localObject2[5];
/*      */                   try
/*      */                   {
/*  310 */                     if (i == 1) {
/*  311 */                       localObject1 = a(i, (IApp)localObject6, (c)localObject11, (d)localObject13, (IEventCallback)localObject14, (Object[])localObject9, localObject15, ((d)localObject13).obtainWebView().getScale());
/*      */                     }
/*      */                     else
/*  314 */                       localObject1 = a(i, (IApp)localObject6, (c)localObject11, (d)localObject13, (IEventCallback)localObject14, (Object[])localObject9);
/*      */                   }
/*      */                   catch (Exception localException)
/*      */                   {
/*  318 */                     localException.printStackTrace();
/*  319 */                     Logger.e("winmgr", "Exception msg=" + localException);
/*      */                   }
/*      */ 
/*  324 */                   localObject2 = (Object[])paramObject;
/*  325 */                   if ((localObject2[0] instanceof d)) {
/*  326 */                     Object localObject4 = (d)localObject2[0];
/*      */ 
/*  328 */                     Logger.d("Animation_Path", "showWindow" + localObject4);
/*  329 */                     ((d)localObject4).l.a(((d)localObject4).obtainWebView().obtainWebview(), new ICallBack()
/*      */                     {
/*      */                       public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject)
/*      */                       {
/*  334 */                         if (this.a.r) return null;
/*  335 */                         int i = this.a.obtainMainView().getVisibility() == 0 ? 1 : 0;
/*  336 */                         this.a.setVisible(true, false);
/*  337 */                         this.a.m();
/*  338 */                         this.a.lastShowTime = System.currentTimeMillis();
/*  339 */                         this.a.l.e();
/*  340 */                         if (!this.a.isChildOfFrameView) {
/*  341 */                           this.a.l.d(this.a);
/*  342 */                           this.a.onPushToStack(this.a.isAutoPop());
/*  343 */                           c localc = this.a.l;
/*  344 */                           if (localc != null) {
/*  345 */                             boolean bool2 = localc.b().contains(this.a);
/*      */ 
/*  347 */                             if (!bool2) {
/*  348 */                               localc.e(this.a);
/*      */                             }
/*      */                             else
/*      */                             {
/*  353 */                               localc.f();
/*      */                             }
/*      */                           }
/*      */                         }
/*  357 */                         else if (this.a.getParentFrameItem() != null) {
/*  358 */                           this.a.l.g(this.a);
/*      */                         }
/*      */ 
/*  361 */                         boolean bool1 = ((Boolean)this.b[1]).booleanValue();
/*      */ 
/*  363 */                         int j = this.a.obtainApp().getInt(0);
/*  364 */                         int k = this.a.obtainApp().getInt(1);
/*  365 */                         int m = (j == this.a.obtainFrameOptions().width) && (k == this.a.obtainFrameOptions().height) ? 1 : 0;
/*      */ 
/*  367 */                         if (((bool1) || (BaseInfo.isDefaultAim)) && (!this.a.isChildOfFrameView) && (i == 0)) {
/*  368 */                           if ((m != 0) || (PdrUtil.isEquals(this.a.getAnimOptions().mAnimType, "pop-in"))) {
/*  369 */                             j.a(this.a, 0);
/*      */                           }
/*  371 */                           if ((bool1) && (m != 0)) {
/*  372 */                             this.a.f();
/*  373 */                             b.a(0, new AdaFrameItem[] { this.a });
/*      */                           } else {
/*  375 */                             this.a.h();
/*      */                           }
/*      */ 
/*      */                         }
/*  379 */                         else if ((bool1) && (PdrUtil.isEquals(this.a.getAnimOptions().mAnimType, "fade-in"))) {
/*  380 */                           this.a.f();
/*  381 */                           b.a(0, new AdaFrameItem[] { this.a });
/*      */                         } else {
/*  383 */                           this.a.h();
/*      */                         }
/*      */ 
/*  386 */                         return null;
/*      */                       }
/*      */                     });
/*  390 */                     break;
/*      */ 
/*  396 */                     localObject2 = null;
/*  397 */                     if ((paramObject instanceof d)) {
/*  398 */                       localObject2 = (d)paramObject;
/*      */                     }
/*  400 */                     if (localObject2 == null) return null;
/*  401 */                     localObject4 = localObject2;
/*      */ 
/*  403 */                     Logger.d("Animation_Path", "hideShowWindow" + localObject4);
/*  404 */                     ((d)localObject4).l.a(((d)localObject4).obtainWebView().obtainWebview(), new ICallBack()
/*      */                     {
/*      */                       public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject)
/*      */                       {
/*  409 */                         this.a.setVisible(true, false);
/*  410 */                         this.a.m();
/*  411 */                         this.a.lastShowTime = System.currentTimeMillis();
/*  412 */                         this.a.l.e();
/*      */ 
/*  415 */                         if (!this.a.isChildOfFrameView) {
/*  416 */                           TestUtil.record("computeStackArray");
/*  417 */                           this.a.l.d(this.a);
/*  418 */                           this.a.onPushToStack(this.a.isAutoPop());
/*  419 */                           TestUtil.print("computeStackArray", "计算满屏幕时间");
/*      */ 
/*  421 */                           boolean bool = this.a.l.b().contains(this.a);
/*      */ 
/*  423 */                           if (!bool) {
/*  424 */                             this.a.l.e(this.a);
/*      */                           }
/*      */                           else
/*      */                           {
/*  429 */                             this.a.l.f();
/*      */                           }
/*      */                         }
/*  432 */                         else if (this.a.getParentFrameItem() != null) {
/*  433 */                           this.a.l.g(this.a);
/*      */                         }
/*      */ 
/*  436 */                         if (!this.a.isChildOfFrameView) {
/*  437 */                           int i = this.a.obtainApp().getInt(0);
/*  438 */                           int j = this.a.obtainApp().getInt(1);
/*  439 */                           int k = (i == this.a.obtainFrameOptions().width) && (j == this.a.obtainFrameOptions().height) ? 1 : 0;
/*  440 */                           if (k != 0)
/*      */                           {
/*  442 */                             j.a(this.a, 0);
/*      */                           }
/*      */ 
/*  445 */                           if (PdrUtil.isEquals(this.a.getAnimOptions().mAnimType, "none")) {
/*  446 */                             this.a.makeViewOptions_animate();
/*  447 */                             this.a.j();
/*      */                           } else {
/*  449 */                             this.a.f();
/*  450 */                             b.a(0, new AdaFrameItem[] { this.a });
/*      */                           }
/*      */ 
/*      */                         }
/*  454 */                         else if (PdrUtil.isEquals(this.a.getAnimOptions().mAnimType, "fade-in")) {
/*  455 */                           this.a.f();
/*  456 */                           b.a(0, new AdaFrameItem[] { this.a });
/*      */                         } else {
/*  458 */                           this.a.makeViewOptions_animate();
/*  459 */                           this.a.j();
/*      */                         }
/*      */ 
/*  462 */                         return null;
/*      */                       }
/*      */                     });
/*  465 */                     break;
/*      */ 
/*  468 */                     localObject2 = null;
/*  469 */                     if ((paramObject instanceof d)) {
/*  470 */                       localObject2 = (d)paramObject;
/*      */                     }
/*  472 */                     if (localObject2 == null) return null;
/*  473 */                     Logger.d("Animation_Path", "hideWindow" + localObject2);
/*  474 */                     localObject4 = localObject2;
/*  475 */                     ((d)localObject4).l.a(((d)localObject4).obtainWebView().obtainWebview(), new ICallBack()
/*      */                     {
/*      */                       public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject)
/*      */                       {
/*  481 */                         int i = this.a.l.a(this.a);
/*  482 */                         this.a.m();
/*      */ 
/*  484 */                         int j = this.a.obtainMainView().getVisibility() == AdaFrameView.VISIBLE ? 1 : 0;
/*      */ 
/*  486 */                         if ((this.a.f) && (j != 0) && (!this.a.isChildOfFrameView))
/*      */                         {
/*  490 */                           this.a.l.d(this.a);
/*  491 */                           if (this.a.a()) {
/*  492 */                             l.this.processEvent(IMgr.MgrType.WindowMgr, 28, this.a.b);
/*  493 */                             this.a.b = null;
/*      */                           }
/*      */ 
/*  496 */                           int k = this.a.obtainApp().getInt(0);
/*  497 */                           int m = this.a.obtainApp().getInt(1);
/*  498 */                           int n = (k == this.a.obtainFrameOptions().width) && (m == this.a.obtainFrameOptions().height) ? 1 : 0;
/*      */ 
/*  500 */                           if (((!PdrUtil.isEquals(this.a.getAnimOptions().mAnimType_close, "none")) || ((BaseInfo.isDefaultAim) && (n != 0))) && (i >= 0))
/*      */                           {
/*  502 */                             this.a.f();
/*      */ 
/*  504 */                             if (!PdrUtil.isEquals(this.a.getAnimOptions().mAnimType_close, "none")) {
/*  505 */                               j.a(this.a, 1);
/*      */                             }
/*      */ 
/*  508 */                             b.a(1, new AdaFrameItem[] { this.a });
/*      */                           } else {
/*  510 */                             this.a.makeViewOptions_animate();
/*  511 */                             this.a.k();
/*  512 */                             this.a.l();
/*      */                           }
/*      */                         }
/*      */                         else {
/*  516 */                           this.a.makeViewOptions_animate();
/*  517 */                           this.a.k();
/*  518 */                           this.a.l();
/*      */                         }
/*  520 */                         return null;
/*      */                       }
/*      */                     });
/*  523 */                     break;
/*      */ 
/*  527 */                     localObject2 = null;
/*  528 */                     if ((paramObject instanceof d)) {
/*  529 */                       localObject2 = (d)paramObject;
/*      */                     }
/*  531 */                     if (localObject2 == null) {
/*  532 */                       return null;
/*      */                     }
/*  534 */                     localObject4 = localObject2;
/*  535 */                     Logger.d("Animation_Path", "closeWindow" + localObject4);
/*  536 */                     localObject1 = ((d)localObject4).l.a(((d)localObject4).obtainWebView().obtainWebview(), new ICallBack()
/*      */                     {
/*      */                       public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject)
/*      */                       {
/*  540 */                         Object localObject = null;
/*      */ 
/*  542 */                         int i = this.a.l.a(this.a);
/*  543 */                         this.a.m();
/*  544 */                         if ((this.a.l.c(this.a)) || (!this.a.l.h)) {
/*  545 */                           if (!this.a.isChildOfFrameView) {
/*  546 */                             this.a.l.d(this.a);
/*  547 */                             if (this.a.a()) {
/*  548 */                               l.this.processEvent(IMgr.MgrType.WindowMgr, 28, this.a.b);
/*  549 */                               this.a.b = null;
/*      */                             }
/*      */                           }
/*      */ 
/*  553 */                           int j = this.a.obtainMainView().getVisibility() == 0 ? 1 : 0;
/*  554 */                           int k = this.a.obtainApp().getInt(0);
/*  555 */                           int m = this.a.obtainApp().getInt(1);
/*  556 */                           int n = (k == this.a.obtainFrameOptions().width) && (m == this.a.obtainFrameOptions().height) ? 1 : 0;
/*  557 */                           if ((i >= 0) && (n != 0) && (!PdrUtil.isEquals(this.a.getAnimOptions().mAnimType_close, "none")))
/*      */                           {
/*  559 */                             j.a(this.a, 1);
/*      */                           }
/*      */ 
/*  563 */                           this.a.p();
/*  564 */                           if ((paramInt == 2) && (i >= 0)) {
/*  565 */                             if ((this.a.f) && (j != 0) && (!PdrUtil.isEquals(this.a.getAnimOptions().mAnimType_close, "none"))) {
/*  566 */                               this.a.f();
/*  567 */                               b.a(1, new AdaFrameItem[] { this.a });
/*      */                             } else {
/*  569 */                               this.a.g();
/*      */                             }
/*  571 */                             if (this.a.getFrameType() == 3)
/*  572 */                               l.this.processEvent(IMgr.MgrType.WindowMgr, 42, this.a);
/*      */                           }
/*      */                           else {
/*  575 */                             this.a.g();
/*      */                           }
/*  577 */                           localObject = "true";
/*      */                         }
/*      */                         else {
/*  580 */                           IApp localIApp = this.a.obtainApp();
/*  581 */                           this.a.p();
/*  582 */                           if (paramInt == 2) {
/*  583 */                             if (this.a.f) {
/*  584 */                               this.a.getAnimOptions().mAnimType_close = "zoom-fade-in";
/*  585 */                               this.a.f();
/*  586 */                               b.a(1, new AdaFrameItem[] { this.a });
/*      */                             } else {
/*  588 */                               this.a.g();
/*      */                             }
/*  590 */                             if (this.a.getFrameType() == 3)
/*  591 */                               l.this.processEvent(IMgr.MgrType.WindowMgr, 42, this.a);
/*      */                           }
/*      */                           else {
/*  594 */                             this.a.g();
/*      */                           }
/*      */ 
/*  597 */                           boolean bool = Boolean.parseBoolean(String.valueOf(l.this.processEvent(IMgr.MgrType.AppMgr, 13, localIApp)));
/*  598 */                           if (bool) {
/*  599 */                             l.this.processEvent(IMgr.MgrType.AppMgr, 10, localIApp);
/*  600 */                             localObject = Boolean.valueOf(bool);
/*      */                           }
/*      */                           else {
/*  603 */                             localObject = "true";
/*      */                           }
/*      */                         }
/*  606 */                         this.a.h = false;
/*  607 */                         this.a.g = false;
/*  608 */                         this.a.f = false;
/*  609 */                         return localObject;
/*      */                       }
/*      */                     });
/*  612 */                     break;
/*      */ 
/*  615 */                     if (paramObject != null) {
/*  616 */                       localObject2 = (ArrayList)paramObject;
/*  617 */                       if ((localObject2 != null) && (((ArrayList)localObject2).size() > 0)) {
/*  618 */                         boolean bool1 = false;
/*  619 */                         for (int k = ((ArrayList)localObject2).size() - 1; k >= 0; k--) {
/*  620 */                           localObject9 = (d)((ArrayList)localObject2).get(k);
/*  621 */                           if (localObject9 != null) {
/*  622 */                             if (!((d)localObject9).l.b().contains(localObject9))
/*      */                             {
/*  624 */                               ((d)localObject9).onPushToStack(true);
/*  625 */                               Logger.d("Auto_Pop_Push_Path", "auto_push " + localObject9);
/*  626 */                               Logger.d("Animation_Path", "auto_push " + localObject9);
/*  627 */                               bool1 |= ((d)localObject9).e;
/*  628 */                               ((d)localObject9).e = false;
/*  629 */                               ((d)localObject9).l.e((d)localObject9);
/*      */                             }
/*  631 */                             ((d)localObject9).h = true;
/*  632 */                             ((d)localObject9).g = false;
/*  633 */                             ((d)localObject9).f = true;
/*  634 */                             Logger.i("shutao", "AUTO_PUSH:" + localObject9);
/*      */                           }
/*      */                         }
/*  637 */                         if (bool1) {
/*  638 */                           ((d)((ArrayList)localObject2).get(0)).l.resize();
/*      */                         }
/*      */                       }
/*  641 */                       break;
/*      */ 
/*  645 */                       localObject2 = (ArrayList)paramObject;
/*  646 */                       if (localObject2 != null)
/*      */                       {
/*      */                         Object localObject7;
/*  647 */                         for (int j = ((ArrayList)localObject2).size() - 1; j >= 0; j--) {
/*  648 */                           localObject7 = (d)((ArrayList)localObject2).get(j);
/*  649 */                           if ((localObject7 != null) && (((d)localObject7).l.b().contains(localObject7)) && (!((d)localObject7).a))
/*      */                           {
/*  652 */                             ((d)localObject7).onPopFromStack(((d)localObject7).d());
/*  653 */                             Logger.d("Auto_Pop_Push_Path", "auto_pop " + localObject7);
/*  654 */                             Logger.d("Animation_Path", "auto_pop " + localObject7);
/*      */ 
/*  656 */                             ((d)localObject7).l.removeFrameItem((AdaFrameItem)localObject7);
/*  657 */                             ((d)localObject7).l.b().remove(localObject7);
/*  658 */                             ((d)localObject7).h = false;
/*  659 */                             ((d)localObject7).g = true;
/*  660 */                             ((d)localObject7).f = false;
/*  661 */                             Logger.i("shutao", "AUTO_POP:" + localObject7);
/*      */                           }
/*      */                         }
/*  647 */                         break;
/*      */ 
/*  668 */                         if (paramObject != null) {
/*  669 */                           localObject2 = (d)paramObject;
/*  670 */                           if ((localObject2 != null) && (((d)localObject2).l.b().contains(localObject2)))
/*      */                           {
/*  672 */                             Logger.d("View_Visible_Path", "setUnParent " + localObject2);
/*  673 */                             ((d)localObject2).onPopFromStack(((d)localObject2).d());
/*  674 */                             ((d)localObject2).l.removeFrameItem((AdaFrameItem)localObject2);
/*  675 */                             ((d)localObject2).l.b().remove(localObject2);
/*      */                           }
/*  677 */                           ((d)localObject2).f = false;
/*  678 */                           ((d)localObject2).g = false;
/*  679 */                           break;
/*      */ 
/*  683 */                           localObject2 = (IFrameView)paramObject;
/*  684 */                           Object localObject5 = ((IFrameView)localObject2).obtainMainView();
/*      */ 
/*  686 */                           localObject7 = DCloudAdapterUtil.getIActivityHandler(((IFrameView)localObject2).obtainApp().getActivity());
/*  687 */                           if (localObject7 != null) {
/*  688 */                             ((IActivityHandler)localObject7).setWebViewIntoPreloadView((View)localObject5); break;
/*      */ 
/*  693 */                             localObject2 = (d)paramObject;
/*  694 */                             if ((localObject2 != null) && (!((d)localObject2).l.b().contains(localObject2)))
/*      */                             {
/*  696 */                               Logger.d("View_Visible_Path", "setParent " + localObject2);
/*  697 */                               ((d)localObject2).e();
/*  698 */                               ((d)localObject2).l.e((d)localObject2);
/*  699 */                               if (((d)localObject2).e) {
/*  700 */                                 ((d)localObject2).resize();
/*  701 */                                 ((d)localObject2).e = false;
/*      */                               }
/*      */                             }
/*  704 */                             ((d)localObject2).f = true;
/*  705 */                             ((d)localObject2).h = false;
/*  706 */                             break;
/*      */ 
/*  709 */                             localObject2 = String.valueOf(paramObject);
/*  710 */                             localObject5 = (c)this.a.get(localObject2);
/*  711 */                             if ((localObject5 != null) && (!((c)localObject5).b().isEmpty()))
/*      */                             {
/*  713 */                               localObject1 = ((c)localObject5).b().firstElement(); break;
/*      */ 
/*  718 */                               localObject2 = (Object[])paramObject;
/*  719 */                               localObject5 = (d)localObject2[0];
/*  720 */                               boolean bool2 = ((Boolean)localObject2[1]).booleanValue();
/*  721 */                               boolean bool3 = ((Boolean)localObject2[2]).booleanValue();
/*  722 */                               boolean bool4 = ((Boolean)localObject2[3]).booleanValue();
/*  723 */                               Logger.d("Animation_Path", "setStyle " + localObject5 + (bool4 ? "设置透明度变化" : bool3 ? "zindex发生了变化" : bool2 ? "发生位置区域变化" : ""));
/*  724 */                               ((d)localObject5).d = bool4;
/*  725 */                               ((d)localObject5).m();
/*  726 */                               ((d)localObject5).e = (!((d)localObject5).l.b().contains(localObject5));
/*  727 */                               if ((((d)localObject5).f) && (bool2))
/*      */                               {
/*  729 */                                 if (!((d)localObject5).isChildOfFrameView) {
/*  730 */                                   ((d)localObject5).l.d((d)localObject5);
/*  731 */                                   if (((d)localObject5).a()) {
/*  732 */                                     processEvent(IMgr.MgrType.WindowMgr, 28, ((d)localObject5).b);
/*  733 */                                     ((d)localObject5).b = null;
/*      */                                   }
/*  735 */                                   ((d)localObject5).f();
/*  736 */                                   b.a(0, new AdaFrameItem[] { localObject5 });
/*      */                                 } else {
/*  738 */                                   ((d)localObject5).makeViewOptions_animate();
/*  739 */                                   ((d)localObject5).i();
/*  740 */                                   if (bool2) {
/*  741 */                                     ((d)localObject5).obtainMainView().dispatchConfigurationChanged(AndroidResources.mResources.getConfiguration());
/*  742 */                                     if (Build.VERSION.SDK_INT < 20) {
/*  743 */                                       ((d)localObject5).obtainMainView().requestLayout();
/*      */                                     }
/*  745 */                                     ((d)localObject5).l.resize();
/*      */                                   }
/*      */                                 }
/*      */                               } else {
/*  749 */                                 if (!((d)localObject5).isChildOfFrameView) {
/*  750 */                                   ((d)localObject5).l.d((d)localObject5);
/*  751 */                                   if ((bool3) || (bool4)) {
/*  752 */                                     processEvent(IMgr.MgrType.WindowMgr, 28, ((d)localObject5).b);
/*  753 */                                     ((d)localObject5).b = null;
/*      */                                   }
/*      */                                 }
/*  756 */                                 ((d)localObject5).makeViewOptions_animate();
/*  757 */                                 ((d)localObject5).i();
/*  758 */                                 if (bool2) {
/*  759 */                                   ((d)localObject5).obtainMainView().dispatchConfigurationChanged(AndroidResources.mResources.getConfiguration());
/*  760 */                                   if (Build.VERSION.SDK_INT < 20) {
/*  761 */                                     ((d)localObject5).obtainMainView().requestLayout(); break;
/*      */ 
/*  769 */                                     b().a(true);
/*  770 */                                     break;
/*      */ 
/*  773 */                                     b().a(false);
/*  774 */                                     break;
/*      */ 
/*  776 */                                     b().a(String.valueOf(paramObject));
/*  777 */                                     break;
/*      */ 
/*  780 */                                     localObject2 = (d)paramObject;
/*  781 */                                     a((d)localObject2);
/*  782 */                                     break;
/*      */ 
/*  785 */                                     localObject2 = (d)paramObject;
/*  786 */                                     b((d)localObject2);
/*  787 */                                     break;
/*      */ 
/*  790 */                                     TestUtil.debug(b().obtainMainViewGroup());
/*  791 */                                     break;
/*      */ 
/*  795 */                                     localObject2 = (String)paramObject;
/*  796 */                                     localObject5 = b();
/*  797 */                                     Object localObject8 = ((c)localObject5).c();
/*      */ 
/*  799 */                                     for (Object localObject10 = ((ArrayList)localObject8).iterator(); ((Iterator)localObject10).hasNext(); ) { localObject12 = (d)((Iterator)localObject10).next();
/*  800 */                                       ((d)localObject12).obtainWebView().executeScript((String)localObject2);
/*      */                                     }
/*      */                                     Object localObject12;
/*  803 */                                     break;
/*      */ 
/*  807 */                                     localObject2 = String.valueOf(paramObject);
/*  808 */                                     if (this.a.containsKey(localObject2)) {
/*  809 */                                       localObject5 = (c)this.a.get(localObject2);
/*  810 */                                       ((c)localObject5).dispose();
/*  811 */                                       this.a.remove(localObject2);
/*  812 */                                       break;
/*      */ 
/*  816 */                                       localObject2 = (Object[])paramObject;
/*  817 */                                       localObject5 = (d)localObject2[0];
/*  818 */                                       localObject8 = (d)localObject2[1];
/*  819 */                                       ((d)localObject8).lastShowTime -= 1L;
/*  820 */                                       localObject10 = ((d)localObject5).l.b();
/*  821 */                                       localObject12 = ((d)localObject5).l.c();
/*  822 */                                       ((Stack)localObject10).remove(localObject5);
/*  823 */                                       ((ArrayList)localObject12).remove(localObject5);
/*  824 */                                       int m = ((ArrayList)localObject12).indexOf(localObject8);
/*  825 */                                       ((ArrayList)localObject12).add(m, localObject5);
/*  826 */                                       ((d)localObject5).l.a(((d)localObject5).obtainWebView().obtainWebview(), new ICallBack()
/*      */                                       {
/*      */                                         public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject)
/*      */                                         {
/*  831 */                                           this.a.l.e(this.a);
/*  832 */                                           this.a.setVisible(true, false);
/*  833 */                                           return Boolean.valueOf(false); }  } ); }  }  }  }  }  }  }  }  }  }  }  }  }  } case 0:
/*      */         case 15:
/*      */         case 19:
/*      */         case 34:
/*      */         case 35:
/*      */         case 36:
/*      */         case 37:
/*      */         case 38:
/*      */         case 39:
/*      */         }
/*      */       } } catch (Throwable localThrowable) { Logger.w("WindowMgr.processEvent", localThrowable); }
/*      */ 
/*  845 */     return localObject1;
/*      */   }
/*      */ 
/*      */   private void a(int paramInt, Object paramObject)
/*      */   {
/*  850 */     c localc = null;
/*  851 */     String str = null;
/*  852 */     if ((paramObject instanceof Object[])) {
/*  853 */       Object[] arrayOfObject = (Object[])paramObject;
/*  854 */       IApp localIApp = (IApp)arrayOfObject[0];
/*  855 */       int i = arrayOfObject.length >= 3 ? ((Boolean)arrayOfObject[2]).booleanValue() : 0;
/*  856 */       str = localIApp.obtainAppId();
/*  857 */       localc = (c)this.a.get(str);
/*  858 */       d locald = localc.b;
/*  859 */       int j = locald == null ? 1 : 0;
/*  860 */       if (locald == null) {
/*  861 */         localObject = localIApp.obtainWebAppIntent().getStringExtra("__from_stream_open_style__");
/*  862 */         JSONObject localJSONObject = null;
/*      */         try {
/*  864 */           if (!TextUtils.isEmpty((CharSequence)localObject)) {
/*  865 */             localJSONObject = new JSONObject((String)localObject);
/*  866 */             localIApp.obtainWebAppIntent().removeExtra("__from_stream_open_style__");
/*      */           } else {
/*  868 */             localJSONObject = JSONUtil.createJSONObject("{}");
/*      */           }
/*      */         } catch (JSONException localJSONException) {
/*  871 */           localJSONException.printStackTrace();
/*      */         }
/*  873 */         locald = (d)processEvent(IMgr.MgrType.WindowMgr, 3, new Object[] { Integer.valueOf(3), localIApp, { arrayOfObject[1], localJSONObject }, localc });
/*      */ 
/*  882 */         localc.b = locald;
/*      */       }
/*  884 */       Object localObject = locald.obtainWebView();
/*      */ 
/*  886 */       if (Build.VERSION.SDK_INT > 10) {
/*  887 */         if (i == 0) {
/*  888 */           ((IWebview)localObject).obtainWebview().setLayerType(1, null);
/*      */         }
/*      */         else {
/*  891 */           ((IWebview)localObject).obtainWebview().setLayerType(0, null);
/*      */         }
/*      */       }
/*      */ 
/*  895 */       Logger.d("Main_Path", "load " + str + " launchPage =" + arrayOfObject[1]);
/*      */ 
/*  897 */       ((IWebview)localObject).loadUrl(String.valueOf(arrayOfObject[1]));
/*  898 */       if (j != 0)
/*  899 */         localc.e(locald);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void b(final int paramInt, Object paramObject)
/*      */   {
/*  906 */     Logger.e("StreamSDK", "come into activeAppRootView");
/*  907 */     c localc1 = null;
/*  908 */     String str1 = null;
/*  909 */     d locald1 = null;
/*  910 */     int i = 0;
/*  911 */     boolean bool1 = true;
/*  912 */     if ((paramObject instanceof Object[])) {
/*  913 */       Object[] arrayOfObject = (Object[])paramObject;
/*  914 */       final IApp localIApp = (IApp)arrayOfObject[0];
/*  915 */       boolean bool2 = arrayOfObject.length >= 3 ? ((Boolean)arrayOfObject[2]).booleanValue() : false;
/*  916 */       String str2 = arrayOfObject.length >= 4 ? (String)arrayOfObject[3] : "default";
/*  917 */       String str3 = String.valueOf(arrayOfObject[1]);
/*  918 */       str1 = localIApp.obtainAppId();
/*  919 */       localc1 = (c)this.a.get(str1);
/*  920 */       int j = 2;
/*  921 */       if (10 == paramInt) {
/*  922 */         Logger.i("winmgr", "RESTART_APP_ROOT_VIEW");
/*  923 */         localc1.g();
/*  924 */         localc1.onAppStart(localIApp);
/*      */       }
/*      */ 
/*  927 */       String str4 = localIApp.obtainWebAppIntent().getStringExtra("__from_stream_open_style__");
/*  928 */       JSONObject localJSONObject = null;
/*      */       try {
/*  930 */         if (!TextUtils.isEmpty(str4)) {
/*  931 */           localJSONObject = new JSONObject(str4);
/*  932 */           localIApp.obtainWebAppIntent().removeExtra("__from_stream_open_style__");
/*      */         } else {
/*  934 */           localJSONObject = JSONUtil.createJSONObject("{}");
/*      */         }
/*      */       } catch (JSONException localJSONException) {
/*  937 */         localJSONException.printStackTrace();
/*      */       }
/*  939 */       locald1 = (d)processEvent(IMgr.MgrType.WindowMgr, 3, new Object[] { Integer.valueOf(j), localIApp, { arrayOfObject[1], localJSONObject }, localc1 });
/*      */ 
/*  950 */       final IWebview localIWebview = locald1.obtainWebView();
/*      */ 
/*  952 */       if (Build.VERSION.SDK_INT > 10) {
/*  953 */         if (!bool2)
/*  954 */           localIWebview.obtainWebview().setLayerType(1, null);
/*      */         else {
/*  956 */           localIWebview.obtainWebview().setLayerType(0, null);
/*      */         }
/*      */       }
/*  959 */       if (localJSONObject.has("id"))
/*  960 */         localIWebview.setFrameId(localJSONObject.optString("id"));
/*      */       else {
/*  962 */         localIWebview.setFrameId(str1);
/*      */       }
/*  964 */       localIWebview.setWebViewCacheMode(str2);
/*  965 */       locald1.obtainFrameOptions().name = str1;
/*  966 */       locald1.obtainFrameOptions().mUseHardwave = bool2;
/*  967 */       boolean bool3 = true;
/*      */ 
/*  969 */       IWebviewStateListener localIWebviewStateListener = localIApp.obtainLaunchPageStateListener();
/*  970 */       if (localIWebviewStateListener != null) {
/*  971 */         str5 = String.valueOf(localIWebviewStateListener.onCallBack(-1, localIWebview));
/*  972 */         bool3 = PdrUtil.parseBoolean(str5, true, false);
/*  973 */         localIWebview.addStateListener(localIApp.obtainLaunchPageStateListener());
/*      */       }
/*  975 */       String str5 = localIApp.obtainConfigProperty("delay");
/*  976 */       i = Integer.parseInt(str5);
/*  977 */       String str6 = localIApp.obtainConfigProperty("autoclose");
/*  978 */       bool1 = Boolean.parseBoolean(str6);
/*      */ 
/*  980 */       final long l = System.currentTimeMillis();
/*  981 */       final c localc2 = localc1;
/*  982 */       final d locald2 = locald1;
/*  983 */       int k = (BaseInfo.isWap2AppAppid(str1)) && (Boolean.parseBoolean(localIApp.obtainConfigProperty("w2a_autoclose"))) ? 1 : 0;
/*  984 */       Intent localIntent = localIApp.obtainWebAppIntent();
/*  985 */       int m = 6000;
/*  986 */       boolean bool5 = (bool1) || (k != 0);
/*  987 */       m = localIntent.getIntExtra("__from_stream_open_timeout__", m);
/*  988 */       bool5 = localIntent.getBooleanExtra("__from_stream_open_autoclose__", bool5);
/*  989 */       final boolean bool6 = bool5;
/*  990 */       int n = Integer.parseInt(localIApp.obtainConfigProperty("w2a_delay"));
/*  991 */       final int i1 = k != 0 ? n : i;
/*      */ 
/*  994 */       if ((BaseInfo.isWap2AppAppid(str1)) && (PdrUtil.isNetPath(str3)))
/*  995 */         this.b = "rendering";
/*      */       else {
/*  997 */         this.b = "loaded";
/*      */       }
/*  999 */       String str7 = localIApp.obtainConfigProperty("target");
/* 1000 */       if (TextUtils.isEmpty(str7)) {
/* 1001 */         str7 = "default";
/*      */       }
/* 1003 */       String str8 = localIApp.obtainConfigProperty("event");
/* 1004 */       if (!TextUtils.isEmpty(str8))
/* 1005 */         this.b = str8;
/*      */       Object localObject;
/* 1007 */       if ((BaseInfo.isWap2AppAppid(str1)) && (localIApp.isStreamApp())) {
/* 1008 */         if (!localIWebview.getWebviewProperty("plusrequire").equals("none")) {
/* 1009 */           localIWebview.appendPreloadJsFile(localIApp.convert2LocalFullPath(null, "_www/__wap2app.js"));
/* 1010 */           localIWebview.appendPreloadJsFile(localIApp.convert2LocalFullPath(null, "_www/__wap2appconfig.js"));
/*      */         }
/* 1012 */         str9 = localIApp.convert2LocalFullPath(null, "_www/__wap2app.css");
/* 1013 */         localObject = new File(str9);
/* 1014 */         if (((File)localObject).exists()) {
/* 1015 */           localIWebview.setCssFile(str9, null);
/*      */         }
/*      */       }
/* 1018 */       Logger.d("Main_Path", "_need_auto_close_splash = " + bool1 + ";_delay=" + i + ";appid=" + str1 + ";f_event=" + this.b);
/* 1019 */       final String str9 = str7;
/* 1020 */       localIWebview.addStateListener(new IWebviewStateListener()
/*      */       {
/*      */         public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject)
/*      */         {
/* 1024 */           if (BaseInfo.useStreamAppStatistic(l.this.getContext())) {
/* 1025 */             TestUtil.PointTime localPointTime = TestUtil.PointTime.getPointTime(TestUtil.STREAM_APP_POINT);
/* 1026 */             if (localPointTime != null) localPointTime.point(5);
/*      */           }
/* 1028 */           int k = 1;
/* 1029 */           if ("titleUpdate".equals(str9))
/* 1030 */             k = 4;
/* 1031 */           else if ("rendering".equals(str9)) {
/* 1032 */             k = 6;
/*      */           }
/* 1034 */           Logger.d("Main_Path", "activeAppRootViewWithWapPage  IWebviewStateListener pType= " + paramAnonymousInt);
/* 1035 */           if (paramAnonymousInt == k) {
/* 1036 */             if ((bool6) && (str9.equals("default"))) {
/* 1037 */               l.this.a(localIWebview, localIApp, localc2, paramInt, locald2, i1);
/*      */             }
/* 1039 */             BaseInfo.setLoadingLaunchePage(false, "f_need_auto_close_splash");
/*      */ 
/* 1041 */             long l = System.currentTimeMillis() - l;
/*      */ 
/* 1043 */             localIApp.setConfigProperty("loadedTime", String.valueOf(l));
/* 1044 */             Logger.d("shutao", "首页面loadtime=" + l + "type=" + paramAnonymousInt);
/* 1045 */             if (paramAnonymousInt == 4)
/* 1046 */               l.this.processEvent(IMgr.MgrType.AppMgr, 18, localIApp);
/*      */           }
/* 1048 */           else if (paramAnonymousInt == 3) {
/* 1049 */             IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(localIApp.getActivity());
/* 1050 */             if (localIActivityHandler != null) {
/* 1051 */               localIActivityHandler.updateParam("progress", paramAnonymousObject);
/*      */             }
/*      */           }
/* 1054 */           return null;
/*      */         }
/*      */       });
/* 1058 */       if (bool6) {
/* 1059 */         if (BaseInfo.useStreamAppStatistic(getContext())) {
/* 1060 */           localObject = TestUtil.PointTime.getPointTime(TestUtil.STREAM_APP_POINT);
/* 1061 */           if (localObject != null) ((TestUtil.PointTime)localObject).point(6, 150L);
/*      */         }
/* 1063 */         if (BaseInfo.ISDEBUG) {
/* 1064 */           int i2 = Integer.parseInt(localIApp.obtainConfigProperty("timeout"));
/* 1065 */           locald2.obtainMainView().postDelayed(new Runnable()
/*      */           {
/*      */             public void run() {
/* 1068 */               int i = Integer.parseInt(localIApp.obtainConfigProperty("timeout"));
/* 1069 */               if (i != -1)
/* 1070 */                 localc2.a(localc2, locald2, i1, true);
/*      */             }
/*      */           }
/*      */           , i2);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1076 */       localIWebview.obtainWebview().postDelayed(new Runnable()
/*      */       {
/*      */         public void run() {
/* 1079 */           Logger.d("delay 6s closeSplashScreen");
/* 1080 */           localc2.a(localc2, locald2, i1, true);
/*      */         }
/*      */       }
/*      */       , m);
/*      */ 
/* 1085 */       Logger.d("Main_Path", "load " + str1 + " launchPage =" + arrayOfObject[1]);
/* 1086 */       if ("about:blank".equals(arrayOfObject[1]))
/* 1087 */         localIWebview.loadContentData("<html><head><meta charset=\"utf-8\"></head><body></body><html>", "text/html", "utf-8");
/*      */       else {
/* 1089 */         localIWebview.loadUrl(String.valueOf(arrayOfObject[1]));
/*      */       }
/* 1091 */       boolean bool4 = Boolean.parseBoolean(localIApp.obtainConfigProperty("splashscreen"));
/* 1092 */       if (bool4)
/* 1093 */         locald1.o = 1;
/*      */       else {
/* 1095 */         locald1.o = 0;
/*      */       }
/*      */ 
/* 1098 */       if (bool3) {
/* 1099 */         localc1.e(locald1);
/*      */       }
/* 1101 */       a(localIApp, localIWebview);
/* 1102 */     } else if ((paramObject instanceof String)) {
/* 1103 */       str1 = String.valueOf(paramObject);
/* 1104 */       localc1 = (c)this.a.get(str1);
/*      */     }
/*      */   }
/*      */ 
/* 1108 */   void a(IApp paramIApp, IWebview paramIWebview) { JSONObject localJSONObject = paramIApp.obtainThridInfo(IApp.ConfigProperty.ThridInfo.SecondWebviewJsonData);
/* 1109 */     if (localJSONObject != null)
/* 1110 */       processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[] { paramIWebview, "UI", "n_createSecondWebview", null });
/*      */   }
/*      */ 
/*      */   public void a(final IWebview paramIWebview, final IApp paramIApp, final c paramc, final int paramInt1, final d paramd, final int paramInt2)
/*      */   {
/* 1115 */     if (paramc.h)
/* 1116 */       return;
/*      */     try
/*      */     {
/* 1119 */       if (!paramIWebview.checkWhite("top"))
/*      */       {
/* 1121 */         paramIApp.setConfigProperty("timeout", "-1");
/* 1122 */         paramc.a(paramc, paramd, paramInt2, true);
/*      */       }
/*      */       else {
/* 1125 */         MessageHandler.postDelayed(new Runnable()
/*      */         {
/*      */           public void run()
/*      */           {
/* 1129 */             l.this.a(paramIWebview, paramIApp, paramc, paramInt1, paramd, paramInt2);
/*      */           }
/*      */         }
/*      */         , 100L);
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/* 1134 */       localException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   d a(int paramInt, final IApp paramIApp, final c paramc, d paramd, IEventCallback paramIEventCallback, Object[] paramArrayOfObject)
/*      */   {
/* 1159 */     TestUtil.record("createFrameView", "createFrameView");
/* 1160 */     String str1 = String.valueOf(paramArrayOfObject[0]);
/* 1161 */     Logger.d("Layout_Path", "WindowMgr createWindow");
/* 1162 */     int i = paramIApp.getInt(0);
/* 1163 */     int j = paramIApp.getInt(1);
/* 1164 */     JSONObject localJSONObject = null;
/* 1165 */     final d locald = new d(paramIApp.getActivity(), this, paramIApp, paramc, paramInt, null);
/* 1166 */     ViewOptions localViewOptions1 = locald.obtainFrameOptions();
/* 1167 */     ViewOptions localViewOptions2 = paramc.obtainFrameOptions();
/* 1168 */     localViewOptions1.setParentViewRect(localViewOptions2);
/* 1169 */     localViewOptions1.popGesture = paramIApp.getPopGesture();
/* 1170 */     f localf1 = null;
/* 1171 */     localf1 = new f(paramIApp.getActivity(), this, locald);
/* 1172 */     if (paramd != null) {
/* 1173 */       localf1.a = paramd.obtainWebView().getScale();
/*      */     }
/* 1175 */     localf1.setOriginalUrl(str1);
/* 1176 */     localViewOptions1.mWebviewScale = localf1.getScale();
/* 1177 */     if (paramArrayOfObject.length > 1) {
/* 1178 */       localJSONObject = (JSONObject)paramArrayOfObject[1];
/* 1179 */       localViewOptions1.updateViewData(localJSONObject);
/*      */     } else {
/* 1181 */       localViewOptions1.width = i;
/* 1182 */       localViewOptions1.height = j;
/*      */     }
/* 1184 */     if (Build.VERSION.SDK_INT > 10) {
/* 1185 */       if ((!localViewOptions1.mUseHardwave) && ((paramInt != 2) || (!BaseInfo.isWap2AppAppid(paramIApp.obtainAppId())) || (!paramIApp.isStreamApp())))
/* 1186 */         localf1.obtainWebview().setLayerType(1, null);
/*      */       else {
/* 1188 */         localf1.obtainWebview().setLayerType(0, null);
/*      */       }
/*      */     }
/*      */ 
/* 1192 */     if (localViewOptions1.mBounce)
/* 1193 */       localf1.obtainWebview().setOverScrollMode(0);
/*      */     else {
/* 1195 */       localf1.obtainWebview().setOverScrollMode(2);
/*      */     }
/* 1197 */     localf1.setWebViewCacheMode(localViewOptions1.mCacheMode);
/* 1198 */     localf1.init();
/* 1199 */     Logger.d("createWindow3");
/* 1200 */     locald.addFrameViewListener(paramIEventCallback);
/*      */ 
/* 1202 */     locald.setFrameOptions_Birth(ViewOptions.createViewOptionsData(localViewOptions1, paramc.obtainFrameOptions()));
/*      */ 
/* 1204 */     AdaWebViewParent localAdaWebViewParent = locald.obtainWebviewParent();
/*      */ 
/* 1206 */     String str2 = JSONUtil.getString(localJSONObject, "blockNetworkImage");
/* 1207 */     localf1.setWebviewProperty("blockNetworkImage", str2);
/*      */ 
/* 1209 */     if (localJSONObject != null) {
/* 1210 */       boolean bool1 = localJSONObject.optBoolean("visible", true);
/* 1211 */       locald.obtainMainView().setVisibility(bool1 ? 0 : 8);
/*      */     }
/*      */ 
/* 1214 */     String str3 = JSONUtil.getString(localJSONObject, "injection");
/* 1215 */     localf1.setWebviewProperty("injection", str3);
/*      */ 
/* 1218 */     String str4 = JSONUtil.getString(localJSONObject, "videoFullscreen");
/* 1219 */     localf1.setWebviewProperty("videoFullscreen", str4);
/*      */ 
/* 1222 */     if (locald.getFrameType() != 2)
/* 1223 */       localViewOptions1.navigationbar = JSONUtil.getJSONObject(localJSONObject, "navigationbar");
/*      */     else {
/* 1225 */       localViewOptions1.navigationbar = locald.obtainApp().obtainThridInfo(IApp.ConfigProperty.ThridInfo.NavigationbarJsonData);
/*      */     }
/*      */ 
/* 1228 */     locald.b(localViewOptions1, locald, localAdaWebViewParent, localf1);
/*      */     int k;
/*      */     int m;
/*      */     int i1;
/*      */     Object localObject2;
/* 1230 */     if (localViewOptions1.hasBackground()) {
/* 1231 */       localObject1 = localAdaWebViewParent.obtainFrameOptions();
/* 1232 */       ((ViewOptions)localObject1).setParentViewRect(localViewOptions2);
/* 1233 */       ((ViewOptions)localObject1).updateViewData(localViewOptions1);
/* 1234 */       localViewOptions1.left = 0;
/* 1235 */       localViewOptions1.top = 0;
/* 1236 */       localViewOptions1.width = -1;
/* 1237 */       localViewOptions1.height = -1;
/* 1238 */       k = ((ViewOptions)localObject1).left;
/* 1239 */       m = ((ViewOptions)localObject1).top;
/* 1240 */       int n = ((ViewOptions)localObject1).width;
/* 1241 */       i1 = ((ViewOptions)localObject1).height;
/* 1242 */       localAdaWebViewParent.setFrameOptions_Birth(ViewOptions.createViewOptionsData((ViewOptions)localObject1, localViewOptions2, (ViewRect)localObject1));
/* 1243 */       ((ViewOptions)localObject1).allowUpdate = false;
/* 1244 */       localAdaWebViewParent.mNeedOrientationUpdate = true;
/* 1245 */       localViewOptions1.checkValueIsPercentage("left", -1, -1, false, true);
/* 1246 */       localViewOptions1.checkValueIsPercentage("top", -1, -1, false, true);
/* 1247 */       localViewOptions1.checkValueIsPercentage("width", -1, -1, false, true);
/* 1248 */       localViewOptions1.checkValueIsPercentage("height", -1, -1, false, true);
/*      */ 
/* 1252 */       if (a(k, m, n, i1, paramc.obtainFrameOptions().width, paramc.obtainFrameOptions().height))
/*      */       {
/* 1255 */         Logger.d("winmgr", "createWindow use LayoutParams.MATCH_PARENT !");
/* 1256 */         ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
/* 1257 */         locald.addFrameItem(locald.obtainWebviewParent(), localLayoutParams);
/*      */       } else {
/* 1259 */         locald.addFrameItem(locald.obtainWebviewParent(), AdaFrameItem.LayoutParamsUtil.createLayoutParams(k, m, n, i1));
/*      */ 
/* 1261 */         if ((k + n > i) || (m + i1 > j)) {
/* 1262 */           Logger.d("winmgr", "updateLayoutParams allW=" + (k + n) + ";pdrW=" + i + ";pdrH=" + j + ";allH=" + (m + i1) + ";url=" + str1);
/*      */ 
/* 1266 */           locald.a(Math.max(k + n, i), Math.max(m + i1, j));
/*      */         }
/*      */       }
/*      */     } else {
/* 1270 */       localObject1 = locald.obtainMainView();
/* 1271 */       k = localViewOptions1.width == i ? -1 : localViewOptions1.width;
/* 1272 */       m = localViewOptions1.height == j ? -1 : localViewOptions1.height;
/* 1273 */       AdaFrameItem.LayoutParamsUtil.setViewLayoutParams((View)localObject1, localViewOptions1.left, localViewOptions1.top, k, m);
/* 1274 */       localObject2 = new ViewGroup.LayoutParams(-1, -1);
/*      */ 
/* 1276 */       locald.addFrameItem(locald.obtainWebviewParent(), (ViewGroup.LayoutParams)localObject2);
/*      */     }
/*      */ 
/* 1279 */     e(locald);
/* 1280 */     Logger.d("winmgr", "createWindow end !");
/* 1281 */     TestUtil.print("createFrameView", "createFrameView耗时：");
/* 1282 */     Object localObject1 = paramIApp.obtainConfigProperty("target");
/* 1283 */     if ((paramInt == 4) && (((String)localObject1).equals("second")))
/*      */     {
/* 1285 */       String str5 = paramIApp.obtainAppId();
/* 1286 */       if ((BaseInfo.isWap2AppAppid(str5)) && (PdrUtil.isNetPath(str1)))
/* 1287 */         this.b = "rendering";
/*      */       else {
/* 1289 */         this.b = "loaded";
/*      */       }
/* 1291 */       String str6 = paramIApp.obtainConfigProperty("event");
/* 1292 */       if (!TextUtils.isEmpty(str6)) {
/* 1293 */         this.b = str6;
/*      */       }
/* 1295 */       localObject2 = paramIApp.obtainConfigProperty("autoclose");
/* 1296 */       i1 = (BaseInfo.isWap2AppAppid(str5)) && (Boolean.parseBoolean(paramIApp.obtainConfigProperty("w2a_autoclose"))) ? 1 : 0;
/* 1297 */       boolean bool2 = true;
/* 1298 */       bool2 = Boolean.parseBoolean((String)localObject2);
/* 1299 */       boolean bool3 = (bool2) || (i1 != 0);
/* 1300 */       final boolean bool4 = bool3;
/* 1301 */       String str7 = paramIApp.obtainConfigProperty("delay");
/* 1302 */       int i2 = Integer.parseInt(str7);
/* 1303 */       int i3 = Integer.parseInt(paramIApp.obtainConfigProperty("w2a_delay"));
/* 1304 */       final int i4 = i1 != 0 ? i3 : i2;
/* 1305 */       final f localf2 = localf1;
/* 1306 */       localf1.addStateListener(new IWebviewStateListener()
/*      */       {
/*      */         public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject) {
/* 1309 */           int i = 1;
/* 1310 */           if ("titleUpdate".equals(l.this.b))
/* 1311 */             i = 4;
/* 1312 */           else if ("rendering".equals(l.this.b)) {
/* 1313 */             i = 6;
/*      */           }
/* 1315 */           if ((paramAnonymousInt == i) && 
/* 1316 */             (bool4)) {
/* 1317 */             l.this.a(localf2, paramIApp, paramc, 1, locald, i4);
/*      */           }
/*      */ 
/* 1320 */           return null;
/*      */         }
/*      */       });
/*      */     }
/* 1324 */     return locald;
/*      */   }
/*      */ 
/*      */   d a(int paramInt, IApp paramIApp, c paramc, d paramd, IEventCallback paramIEventCallback, Object[] paramArrayOfObject, Object paramObject, float paramFloat)
/*      */   {
/* 1340 */     d locald = new d(paramIApp.getActivity(), this, paramIApp, paramc, paramInt, paramObject);
/* 1341 */     int i = paramIApp.getInt(0);
/* 1342 */     int j = paramIApp.getInt(1);
/* 1343 */     ViewOptions localViewOptions1 = locald.obtainFrameOptions();
/* 1344 */     ViewOptions localViewOptions2 = paramc.obtainFrameOptions();
/* 1345 */     localViewOptions1.setParentViewRect(localViewOptions2);
/* 1346 */     locald.addFrameViewListener(paramIEventCallback);
/* 1347 */     locald.setFrameOptions_Birth(ViewOptions.createViewOptionsData(localViewOptions1, paramc.obtainFrameOptions()));
/* 1348 */     localViewOptions1.mWebviewScale = paramFloat;
/*      */ 
/* 1361 */     return locald;
/*      */   }
/*      */ 
/*      */   private boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*      */   {
/* 1366 */     return (paramInt1 == 0) && (paramInt2 == 0) && (paramInt3 == paramInt5) && (paramInt4 == paramInt6);
/*      */   }
/*      */ 
/*      */   private d a()
/*      */   {
/* 1377 */     c localc = b();
/* 1378 */     return localc != null ? localc.a() : null;
/*      */   }
/*      */ 
/*      */   private c b() {
/* 1382 */     String str = String.valueOf(processEvent(IMgr.MgrType.AppMgr, 11, null));
/* 1383 */     return (c)this.a.get(str);
/*      */   }
/*      */ 
/*      */   public void dispose()
/*      */   {
/*      */     try
/*      */     {
/* 1390 */       Set localSet = this.a.keySet();
/* 1391 */       for (String str : localSet) {
/* 1392 */         c localc = (c)this.a.get(str);
/* 1393 */         localc.dispose();
/*      */       }
/* 1395 */       this.a.clear();
/* 1396 */       if (BaseInfo.ISDEBUG)
/* 1397 */         g.a();
/*      */     }
/*      */     catch (Exception localException) {
/* 1400 */       localException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void a(d paramd)
/*      */   {
/* 1439 */     IApp localIApp = paramd.obtainApp();
/*      */ 
/* 1446 */     localIApp.setMaskLayer(true);
/*      */ 
/* 1448 */     localIApp.obtainWebAppRootView().obtainMainView().invalidate();
/*      */   }
/*      */ 
/*      */   public void b(d paramd) {
/* 1452 */     IApp localIApp = paramd.obtainApp();
/*      */ 
/* 1458 */     localIApp.setMaskLayer(false);
/*      */ 
/* 1460 */     localIApp.obtainWebAppRootView().obtainMainView().invalidate();
/*      */   }
/*      */ 
/*      */   public void c(d paramd)
/*      */   {
/* 1482 */     paramd.m();
/* 1483 */     paramd.l.d(paramd);
/* 1484 */     if (paramd.a()) {
/* 1485 */       processEvent(IMgr.MgrType.WindowMgr, 28, paramd.b);
/* 1486 */       paramd.b = null;
/*      */     }
/* 1488 */     paramd.makeViewOptions_animate();
/* 1489 */     paramd.k();
/*      */ 
/* 1491 */     paramd.l();
/*      */   }
/*      */ 
/*      */   public void d(d paramd)
/*      */   {
/* 1499 */     paramd.m();
/* 1500 */     paramd.l.d(paramd);
/* 1501 */     if (paramd.a()) {
/* 1502 */       processEvent(IMgr.MgrType.WindowMgr, 28, paramd.b);
/* 1503 */       paramd.b = null;
/*      */     }
/* 1505 */     paramd.p();
/* 1506 */     paramd.g();
/* 1507 */     paramd.h = false;
/* 1508 */     paramd.g = false;
/* 1509 */     paramd.f = false;
/*      */   }
/*      */ 
/*      */   private void e(d paramd)
/*      */   {
/* 1517 */     if (null != paramd.obtainFrameOptions().navigationbar) {
/* 1518 */       JSONObject localJSONObject = paramd.obtainFrameOptions().navigationbar;
/*      */       try {
/* 1520 */         if ((!localJSONObject.has("titletext")) || (TextUtils.isEmpty(localJSONObject.optString("titletext")))) {
/* 1521 */           localJSONObject.put("titletext", paramd.obtainWebView().obtainApp().obtainAppName());
/*      */         }
/* 1523 */         if ((!localJSONObject.has("titlecolor")) || (TextUtils.isEmpty(localJSONObject.optString("titlecolor")))) {
/* 1524 */           localJSONObject.put("titlecolor", "#FFFFFF");
/*      */         }
/* 1526 */         if ((!localJSONObject.has("backgroundcolor")) || (TextUtils.isEmpty(localJSONObject.optString("backgroundcolor"))))
/* 1527 */           localJSONObject.put("backgroundcolor", "#1b1a1f");
/*      */       }
/*      */       catch (Exception localException) {
/* 1530 */         localException.printStackTrace();
/*      */       }
/* 1532 */       String str1 = localJSONObject.optString("titletext");
/* 1533 */       String str2 = localJSONObject.optString("titlecolor");
/* 1534 */       String str3 = localJSONObject.optString("backgroundcolor");
/* 1535 */       String str4 = String.valueOf(paramd.obtainWebView().obtainWebview().hashCode());
/*      */ 
/* 1538 */       Object localObject = paramd.j.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[] { paramd.obtainWebView(), "nativeobj", "View", { paramd, paramd.obtainWebView(), str4, str4, JSONUtil.createJSONObject("{'top':'0px','left':'0px','height':'44px','width':'100%','backgroudColor':'" + str3 + "','position':'dock','dock':'top'}"), null } });
/*      */ 
/* 1542 */       if ((null != localObject) && ((localObject instanceof View))) {
/* 1543 */         View localView = (View)localObject;
/* 1544 */         localView.setTag("NavigationBar");
/*      */ 
/* 1546 */         paramd.j.processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[] { paramd.obtainWebView(), "nativeobj", "drawText", JSONUtil.createJSONArray("['" + str4 + "','" + str4 + "','" + str1 + "',{'top':'0px','left':'0px','width':'100%','height':'100%'},{'size':'16px','color':'" + str2 + "'},'" + str4 + "',null]") });
/*      */ 
/* 1549 */         paramd.j.processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[] { paramd.obtainWebView(), "nativeobj", "show", JSONUtil.createJSONArray("['" + str4 + "','" + str4 + "']") });
/*      */ 
/* 1552 */         paramd.j.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[] { paramd.obtainWebView(), "nativeobj", "addNativeView", { paramd, str4 } });
/*      */ 
/* 1555 */         paramd.mNavigationBarHeight = ((int)TypedValue.applyDimension(1, 42.0F, paramd.obtainWebView().obtainWebview().getContext().getResources().getDisplayMetrics()));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   class a extends View
/*      */   {
/*      */     protected void onDraw(Canvas paramCanvas)
/*      */     {
/* 1472 */       super.onDraw(paramCanvas);
/*      */     }
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.l
 * JD-Core Version:    0.6.2
 */