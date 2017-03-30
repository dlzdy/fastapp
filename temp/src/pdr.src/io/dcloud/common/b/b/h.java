/*      */ package io.dcloud.common.b.b;
/*      */ 
/*      */ import android.content.Context;
/*      */ import android.content.res.Resources;
/*      */ import android.graphics.Rect;
/*      */ import android.os.Build.VERSION;
/*      */ import android.text.TextUtils;
/*      */ import android.util.DisplayMetrics;
/*      */ import android.view.MotionEvent;
/*      */ import android.view.VelocityTracker;
/*      */ import android.view.View;
/*      */ import android.view.ViewConfiguration;
/*      */ import android.view.ViewGroup.LayoutParams;
/*      */ import android.view.ViewParent;
/*      */ import android.view.ViewTreeObserver;
/*      */ import android.view.ViewTreeObserver.OnGlobalLayoutListener;
/*      */ import android.webkit.WebView;
/*      */ import android.widget.AbsoluteLayout.LayoutParams;
/*      */ import android.widget.FrameLayout;
/*      */ import android.widget.FrameLayout.LayoutParams;
/*      */ import io.dcloud.common.DHInterface.AbsMgr;
/*      */ import io.dcloud.common.DHInterface.IFrameView;
/*      */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*      */ import io.dcloud.common.DHInterface.INativeView;
/*      */ import io.dcloud.common.DHInterface.IWebview;
/*      */ import io.dcloud.common.adapter.util.DragBean;
/*      */ import io.dcloud.common.adapter.util.ViewOptions;
/*      */ import io.dcloud.common.util.JSONUtil;
/*      */ import io.dcloud.common.util.JSUtil;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import io.dcloud.nineoldandroids.animation.Animator;
/*      */ import io.dcloud.nineoldandroids.animation.Animator.AnimatorListener;
/*      */ import io.dcloud.nineoldandroids.animation.ValueAnimator;
/*      */ import io.dcloud.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
/*      */ import io.dcloud.nineoldandroids.view.ViewHelper;
/*      */ import java.math.BigDecimal;
/*      */ import java.util.HashMap;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ public class h
/*      */ {
/*      */   private VelocityTracker a;
/*   46 */   private boolean b = true;
/*      */   private int c;
/*      */   private float d;
/*      */   private float e;
/*      */   private float f;
/*      */   private int g;
/*   52 */   private int h = 0;
/*   53 */   private int i = 0;
/*      */ 
/*   55 */   private int j = 0;
/*   56 */   private int k = 0;
/*      */   private d l;
/*      */   private d m;
/*   61 */   private View n = null;
/*      */   private View o;
/*   65 */   private boolean p = false;
/*      */ 
/*   67 */   private int q = 2147483647;
/*      */   private String r;
/*   71 */   private int s = 2147483647;
/*      */   private String t;
/*   75 */   private int u = 2147483647;
/*      */   private String v;
/*   79 */   private int w = 2147483647;
/*      */   private String x;
/*      */   private String y;
/*   86 */   private boolean z = false;
/*      */ 
/*   88 */   private boolean A = true;
/*      */ 
/*   90 */   private boolean B = true;
/*      */ 
/*   93 */   private boolean C = true;
/*      */ 
/*   95 */   private int D = -1;
/*      */   private IFrameView E;
/*      */   private String F;
/*      */   private String G;
/*      */   private String H;
/*      */   private float I;
/*      */   private int J;
/*      */   private int K;
/*  118 */   private boolean L = false;
/*  119 */   private boolean M = false;
/*  120 */   private int N = 2147483647;
/*      */ 
/*  122 */   private static boolean O = false;
/*      */ 
/*  124 */   private int P = 0;
/*      */   private DisplayMetrics Q;
/* 1007 */   private boolean R = true; private boolean S = true; private boolean T = true; private boolean U = true; private boolean V = true; private boolean W = true;
/*      */ 
/*      */   public h(IFrameView paramIFrameView, Context paramContext)
/*      */   {
/*  129 */     O = false;
/*  130 */     this.P = 0;
/*  131 */     if ((paramIFrameView instanceof d)) {
/*  132 */       this.l = ((d)paramIFrameView);
/*  133 */       if (null == this.Q) {
/*  134 */         this.Q = paramContext.getResources().getDisplayMetrics();
/*  135 */         this.J = this.Q.widthPixels;
/*  136 */         this.K = this.Q.widthPixels;
/*      */       }
/*      */     }
/*  139 */     this.c = ViewConfiguration.get(paramContext).getScaledTouchSlop();
/*      */   }
/*      */ 
/*      */   public HashMap<String, DragBean> a() {
/*  143 */     if ((null != this.l) && 
/*  144 */       (null != this.l.obtainFrameOptions())) {
/*  145 */       return this.l.obtainFrameOptions().dragData;
/*      */     }
/*      */ 
/*  148 */     return null;
/*      */   }
/*      */ 
/*      */   public void a(MotionEvent paramMotionEvent) {
/*  152 */     if (null != this.Q) {
/*  153 */       this.J = this.Q.widthPixels;
/*      */     }
/*  155 */     this.I = paramMotionEvent.getRawX();
/*  156 */     this.d = paramMotionEvent.getRawX();
/*  157 */     this.f = this.d;
/*  158 */     this.e = paramMotionEvent.getRawY();
/*  159 */     this.b = false;
/*  160 */     this.z = false;
/*  161 */     this.p = false;
/*  162 */     this.D = -1;
/*      */ 
/*  164 */     this.l.obtainWebView().obtainWebview().loadUrl("javascript:window.__needNotifyNative__=true;");
/*      */ 
/*  166 */     this.l.obtainWebView().setWebviewProperty("needTouchEvent", "false");
/*      */ 
/*  168 */     if (this.a == null) {
/*  169 */       this.a = VelocityTracker.obtain();
/*      */     }
/*  171 */     this.a.addMovement(paramMotionEvent);
/*      */   }
/*      */ 
/*      */   public boolean b(MotionEvent paramMotionEvent)
/*      */   {
/*  179 */     if (null == a()) {
/*  180 */       return false;
/*      */     }
/*  182 */     int i1 = paramMotionEvent.getActionMasked();
/*  183 */     float f1 = paramMotionEvent.getRawX();
/*  184 */     float f2 = paramMotionEvent.getRawY();
/*  185 */     if ((i1 == 0) && (paramMotionEvent.getEdgeFlags() != 0)) {
/*  186 */       return false;
/*      */     }
/*  188 */     switch (i1) {
/*      */     case 0:
/*  190 */       a(paramMotionEvent);
/*  191 */       break;
/*      */     case 2:
/*  193 */       float f3 = f1 - this.d;
/*  194 */       float f4 = Math.abs(f3);
/*  195 */       float f5 = Math.abs(f2 - this.e);
/*      */ 
/*  197 */       if (f4 >= f5)
/*      */       {
/*  199 */         int i2 = 0;
/*      */ 
/*  204 */         float f6 = paramMotionEvent.getRawX() - this.I;
/*      */ 
/*  206 */         if (this.c * 3 <= Math.abs(f6))
/*      */         {
/*  209 */           str1 = this.l.obtainWebView().getWebviewProperty("needTouchEvent");
/*      */ 
/*  211 */           if ("false".equals(str1)) {
/*  212 */             i2 = 1;
/*      */           }
/*      */         }
/*  215 */         String str1 = "";
/*      */ 
/*  217 */         if (i2 != 0)
/*      */         {
/*  219 */           if (f3 >= 0.0F) {
/*  220 */             str1 = "right";
/*      */ 
/*  222 */             if ((!this.p) && (b("right"))) {
/*  223 */               this.G = "right";
/*  224 */               this.b = true;
/*  225 */               this.p = true;
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  230 */             str1 = "left";
/*      */ 
/*  232 */             if ((!this.p) && (b("left"))) {
/*  233 */               this.G = "left";
/*  234 */               this.b = true;
/*  235 */               this.p = true;
/*      */             }
/*      */           }
/*  238 */           if (-1 == this.D) {
/*  239 */             this.d = f1;
/*  240 */             View localView = a(str1);
/*  241 */             if (null != localView) {
/*  242 */               HashMap localHashMap = a();
/*  243 */               String str2 = e(str1);
/*  244 */               if ((null != localHashMap) && (localHashMap.containsKey(str2))) {
/*  245 */                 a(((DragBean)localHashMap.get(str2)).dragBindViewOp);
/*      */               }
/*  247 */               this.G = str1;
/*  248 */               this.b = true;
/*  249 */               this.o = localView;
/*  250 */               this.M = true;
/*  251 */               this.D = 1;
/*      */             }
/*      */             else {
/*  254 */               c(this.G);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  258 */       break;
/*      */     case 1:
/*  261 */       this.p = false;
/*  262 */       this.D = -1;
/*      */     }
/*      */ 
/*  265 */     if (this.b) {
/*  266 */       if (this.M) {
/*  267 */         this.b = j();
/*      */       }
/*  269 */       else if ((this.z) && (this.A) && (this.B)) {
/*  270 */         if (((!a(this.l)) && (!a(this.m))) || ((null != this.o) && ((this.o instanceof INativeView))))
/*      */         {
/*  272 */           this.b = g();
/*      */         }
/*      */ 
/*      */       }
/*  276 */       else if ((this.z) && (!this.A) && (this.B)) {
/*  277 */         if (((!a(this.l)) && (!a(this.m))) || ((null != this.o) && ((this.o instanceof INativeView))))
/*      */         {
/*  279 */           this.b = i();
/*      */         }
/*      */ 
/*      */       }
/*  283 */       else if ((!this.z) && (this.A)) {
/*  284 */         if (!a(this.l)) {
/*  285 */           h();
/*      */         }
/*      */ 
/*      */       }
/*  289 */       else if ((this.z) && (!this.B) && (this.A)) {
/*  290 */         if ((!a(this.l)) && (!a(this.m)))
/*  291 */           this.b = h();
/*      */       }
/*      */       else {
/*  294 */         this.b = false;
/*      */       }
/*      */     }
/*  297 */     if (this.b) {
/*  298 */       a("start", false, "0");
/*      */     }
/*  300 */     return this.b;
/*      */   }
/*      */ 
/*      */   private boolean b(String paramString) {
/*  304 */     this.n = this.l.obtainMainView();
/*      */ 
/*  306 */     this.q = 2147483647;
/*  307 */     this.s = 2147483647;
/*  308 */     this.r = null;
/*  309 */     this.t = null;
/*      */ 
/*  311 */     HashMap localHashMap = a();
/*  312 */     if ((null != localHashMap) && (localHashMap.containsKey(paramString))) {
/*  313 */       DragBean localDragBean = (DragBean)localHashMap.get(paramString);
/*  314 */       if (null != localDragBean) {
/*  315 */         this.F = localDragBean.dragCbId;
/*  316 */         this.E = localDragBean.dragCallBackWebView;
/*  317 */         JSONObject localJSONObject = localDragBean.dragCurrentViewOp;
/*      */         try {
/*  319 */           this.H = JSONUtil.getString(localJSONObject, "direction");
/*      */           Object localObject;
/*  320 */           if (localJSONObject.has("moveMode")) {
/*  321 */             localObject = JSONUtil.getString(localJSONObject, "moveMode");
/*  322 */             this.y = ((String)localObject);
/*  323 */             this.A = (("followFinger".equalsIgnoreCase(this.y)) || ("follow".equalsIgnoreCase(this.y)) || ("bounce".equalsIgnoreCase(this.y)));
/*      */           }
/*      */ 
/*  326 */           if (localJSONObject.has("over")) {
/*  327 */             localObject = JSONUtil.getJSONObject(localJSONObject, "over");
/*  328 */             if (null != localObject) {
/*  329 */               if (((JSONObject)localObject).has("left")) {
/*  330 */                 this.q = PdrUtil.parseInt(JSONUtil.getString((JSONObject)localObject, "left"), this.J, 2147483647);
/*      */               }
/*  332 */               if (((JSONObject)localObject).has("action")) {
/*  333 */                 this.r = JSONUtil.getString((JSONObject)localObject, "action");
/*      */               }
/*      */             }
/*      */           }
/*  337 */           if (localJSONObject.has("cancel")) {
/*  338 */             localObject = JSONUtil.getJSONObject(localJSONObject, "cancel");
/*  339 */             if (null != localObject) {
/*  340 */               if (((JSONObject)localObject).has("left")) {
/*  341 */                 this.s = PdrUtil.parseInt(JSONUtil.getString((JSONObject)localObject, "left"), this.J, 2147483647);
/*      */               }
/*  343 */               if (((JSONObject)localObject).has("action"))
/*  344 */                 this.t = JSONUtil.getString((JSONObject)localObject, "action");
/*      */             }
/*      */           }
/*      */         }
/*      */         catch (Exception localException) {
/*  349 */           localException.printStackTrace();
/*  350 */           return false;
/*      */         }
/*      */       }
/*      */     } else {
/*  354 */       return false;
/*      */     }
/*  356 */     return true;
/*      */   }
/*      */ 
/*      */   private void a(JSONObject paramJSONObject) {
/*  360 */     if (null != paramJSONObject) {
/*  361 */       this.u = 2147483647;
/*  362 */       this.w = 2147483647;
/*  363 */       this.v = null;
/*  364 */       this.x = null;
/*  365 */       this.B = "follow".equalsIgnoreCase(JSONUtil.getString(paramJSONObject, "moveMode"));
/*      */       JSONObject localJSONObject;
/*  366 */       if (paramJSONObject.has("over")) {
/*  367 */         localJSONObject = JSONUtil.getJSONObject(paramJSONObject, "over");
/*  368 */         if (null != localJSONObject) {
/*  369 */           if (localJSONObject.has("left")) {
/*  370 */             this.u = PdrUtil.parseInt(JSONUtil.getString(localJSONObject, "left"), this.J, 2147483647);
/*      */           }
/*  372 */           if (localJSONObject.has("action")) {
/*  373 */             this.v = JSONUtil.getString(localJSONObject, "action");
/*      */           }
/*      */         }
/*      */       }
/*  377 */       if (paramJSONObject.has("cancel")) {
/*  378 */         localJSONObject = JSONUtil.getJSONObject(paramJSONObject, "cancel");
/*  379 */         if (null != localJSONObject) {
/*  380 */           if (localJSONObject.has("left")) {
/*  381 */             this.w = PdrUtil.parseInt(JSONUtil.getString(localJSONObject, "left"), this.J, 2147483647);
/*      */           }
/*  383 */           if (localJSONObject.has("action"))
/*  384 */             this.x = JSONUtil.getString(localJSONObject, "action");
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean a(View paramView1, View paramView2)
/*      */   {
/*  397 */     if ((null != paramView1) && (null != paramView2)) {
/*  398 */       ViewParent localViewParent1 = paramView1.getParent();
/*  399 */       ViewParent localViewParent2 = paramView2.getParent();
/*  400 */       for (; localViewParent1 != null; localViewParent1 = localViewParent1.getParent())
/*      */       {
/*  402 */         if (localViewParent1 == paramView2) {
/*  403 */           return false;
/*      */         }
/*  405 */         for (; localViewParent2 != null; localViewParent2 = localViewParent2.getParent())
/*      */         {
/*  407 */           if (localViewParent2 == paramView1) {
/*  408 */             return false;
/*      */           }
/*      */ 
/*  411 */           if (localViewParent1 == localViewParent2) {
/*  412 */             return true;
/*      */           }
/*      */         }
/*      */ 
/*  416 */         localViewParent2 = paramView2.getParent();
/*      */       }
/*      */     }
/*  419 */     return false;
/*      */   }
/*      */ 
/*      */   private int c(String paramString)
/*      */   {
/*  427 */     if (this.D != -1) {
/*  428 */       return this.D;
/*      */     }
/*  430 */     this.m = null;
/*  431 */     this.o = null;
/*      */ 
/*  433 */     JSONObject localJSONObject = null;
/*  434 */     HashMap localHashMap = a();
/*      */     d locald2;
/*  435 */     if ((null != localHashMap) && (localHashMap.containsKey(paramString))) {
/*  436 */       DragBean localDragBean = (DragBean)localHashMap.get(paramString);
/*  437 */       if (null != localDragBean) {
/*  438 */         localJSONObject = localDragBean.dragBindViewOp;
/*  439 */         a(localJSONObject);
/*  440 */         IFrameView localIFrameView = localDragBean.dragBindWebView;
/*  441 */         if ((null != localIFrameView) && ((localIFrameView instanceof d))) {
/*  442 */           this.z = true;
/*  443 */           locald2 = (d)localIFrameView;
/*  444 */           this.m = locald2;
/*  445 */           this.o = locald2.obtainMainView();
/*      */         } else {
/*  447 */           this.o = localDragBean.nativeView;
/*  448 */           if (null != this.o) {
/*  449 */             this.z = true;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  455 */     if ((!a(this.m)) || ((null != this.o) && ((this.o instanceof INativeView)))) {
/*  456 */       if (0 != this.o.getVisibility()) {
/*  457 */         this.m = null;
/*  458 */         this.o = null;
/*  459 */         return 0;
/*      */       }
/*      */ 
/*  463 */       if ((null == this.o.getParent()) && ((this.n.getParent() instanceof FrameLayout)) && (!(this.o instanceof INativeView))) {
/*  464 */         this.m.pushToViewStack();
/*      */       }
/*      */ 
/*  467 */       if ((!a(this.n, this.o)) && (!(this.o instanceof INativeView))) {
/*  468 */         this.m = null;
/*  469 */         this.o = null;
/*  470 */         return 0;
/*      */       }
/*      */ 
/*  474 */       if ((!(this.o instanceof INativeView)) && 
/*  475 */         (this.B) && 
/*  476 */         (this.n.getParent() != this.o.getParent())) {
/*  477 */         this.D = 0;
/*  478 */         return this.D;
/*      */       }
/*      */ 
/*  482 */       if ((this.o instanceof INativeView)) {
/*  483 */         this.o.bringToFront();
/*      */       }
/*  485 */       int i1 = b(this.o);
/*  486 */       d locald1 = b(this.n);
/*      */ 
/*  489 */       if ((0 == locald1) && (this.n.getWidth() == this.J) && ((i1 >= this.J) || (i1 <= -a(this.o)))) {
/*  490 */         this.N = i1;
/*      */ 
/*  492 */         if ((this.A) && (this.B)) {
/*  493 */           locald2 = locald1;
/*      */ 
/*  496 */           if ("right".equals(paramString)) {
/*  497 */             this.g = (locald2 - a(this.o));
/*      */           }
/*  500 */           else if ("left".equals(paramString)) {
/*  501 */             this.g = (locald2 + this.n.getWidth());
/*      */           }
/*  503 */           b(this.o, this.g);
/*      */         }
/*  506 */         else if ((!this.A) && (this.B))
/*      */         {
/*  509 */           if ("right".equals(paramString)) {
/*  510 */             this.g = (-a(this.o));
/*      */           }
/*  513 */           else if ("left".equals(paramString)) {
/*  514 */             this.g = this.n.getWidth();
/*      */           }
/*  516 */           b(this.o, this.g);
/*      */         }
/*      */       }
/*  519 */       this.D = 1;
/*      */     } else {
/*  521 */       this.D = 0;
/*      */     }
/*  523 */     return this.D;
/*      */   }
/*      */ 
/*      */   public boolean c(MotionEvent paramMotionEvent)
/*      */   {
/*  530 */     if (this.a == null) {
/*  531 */       this.a = VelocityTracker.obtain();
/*      */     }
/*  533 */     if ((this.D != -1) && (!O)) {
/*  534 */       if (2 == paramMotionEvent.getAction()) {
/*  535 */         e();
/*  536 */         if ("left".equals(this.G)) {
/*  537 */           if (paramMotionEvent.getRawX() < this.d)
/*  538 */             this.a.addMovement(paramMotionEvent);
/*      */         }
/*  540 */         else if (("right".equals(this.G)) && 
/*  541 */           (paramMotionEvent.getRawX() > this.d)) {
/*  542 */           this.a.addMovement(paramMotionEvent);
/*      */         }
/*      */       }
/*      */ 
/*  546 */       if ((1 == paramMotionEvent.getAction()) || (3 == paramMotionEvent.getAction())) {
/*  547 */         this.a.addMovement(paramMotionEvent);
/*      */       }
/*  549 */       if (this.M) {
/*  550 */         return g(paramMotionEvent);
/*      */       }
/*      */ 
/*  553 */       if ((this.z) && (this.A) && (this.B)) {
/*  554 */         if (((!a(this.l)) && (!a(this.m))) || ((null != this.o) && ((this.o instanceof INativeView))))
/*      */         {
/*  556 */           return f(paramMotionEvent);
/*      */         }
/*      */ 
/*      */       }
/*  560 */       else if ((this.z) && (!this.A) && (this.B)) {
/*  561 */         if (((!a(this.l)) && (!a(this.m))) || ((null != this.o) && ((this.o instanceof INativeView))))
/*      */         {
/*  563 */           return d(paramMotionEvent);
/*      */         }
/*      */ 
/*      */       }
/*  567 */       else if ((this.z) && (!this.B) && (this.A)) {
/*  568 */         if ((!a(this.l)) && (!a(this.m))) {
/*  569 */           return e(paramMotionEvent);
/*      */         }
/*      */ 
/*      */       }
/*  573 */       else if ((!this.z) && (this.A) && 
/*  574 */         (!a(this.l))) {
/*  575 */         return e(paramMotionEvent);
/*      */       }
/*      */ 
/*  579 */       return true;
/*      */     }
/*  581 */     return false;
/*      */   }
/*      */ 
/*      */   private boolean d(MotionEvent paramMotionEvent) {
/*  585 */     float f1 = paramMotionEvent.getRawX();
/*  586 */     switch (paramMotionEvent.getAction()) {
/*      */     case 2:
/*  588 */       float f2 = f1 - this.d;
/*  589 */       this.d = f1;
/*  590 */       float f3 = b(this.o);
/*  591 */       float f4 = f3 + f2;
/*  592 */       if ("right".equals(this.G)) {
/*  593 */         if (f4 > this.k) {
/*  594 */           f4 = this.k;
/*      */         }
/*  596 */         else if (f4 < this.j)
/*  597 */           f4 = this.j;
/*      */       }
/*  599 */       else if ("left".equals(this.G)) {
/*  600 */         if (f4 > this.j) {
/*  601 */           f4 = this.j;
/*      */         }
/*  603 */         else if (f4 < this.k) {
/*  604 */           f4 = this.k;
/*      */         }
/*      */       }
/*  607 */       b(this.o, (int)f4);
/*  608 */       break;
/*      */     case 1:
/*      */     case 3:
/*  612 */       if (this.C) {
/*  613 */         b();
/*      */       }
/*      */       break;
/*      */     }
/*  617 */     return true;
/*      */   }
/*      */   private boolean e(MotionEvent paramMotionEvent) {
/*  620 */     float f1 = paramMotionEvent.getRawX();
/*  621 */     switch (paramMotionEvent.getAction()) {
/*      */     case 2:
/*  623 */       float f2 = f1 - this.d;
/*  624 */       this.d = f1;
/*      */ 
/*  626 */       f2 = a(f2);
/*      */ 
/*  628 */       float f3 = b(this.n);
/*  629 */       float f4 = f3 + f2;
/*  630 */       if ("right".equals(this.G)) {
/*  631 */         if (f4 > this.i) {
/*  632 */           f4 = this.i;
/*      */         }
/*  634 */         else if (f4 < this.h)
/*  635 */           f4 = this.h;
/*      */       }
/*  637 */       else if ("left".equals(this.G)) {
/*  638 */         if (f4 > this.h) {
/*  639 */           f4 = this.h;
/*      */         }
/*  641 */         else if (f4 < this.i) {
/*  642 */           f4 = this.i;
/*      */         }
/*      */       }
/*  645 */       b(this.n, (int)f4);
/*  646 */       break;
/*      */     case 1:
/*      */     case 3:
/*  650 */       if (this.C) {
/*  651 */         c();
/*      */       }
/*      */       break;
/*      */     }
/*  655 */     return true;
/*      */   }
/*      */   private boolean f(MotionEvent paramMotionEvent) {
/*  658 */     float f1 = paramMotionEvent.getRawX();
/*  659 */     switch (paramMotionEvent.getAction()) {
/*      */     case 2:
/*  661 */       float f2 = (int)(f1 - this.d);
/*  662 */       this.d = f1;
/*      */ 
/*  664 */       f2 = a(f2);
/*      */ 
/*  666 */       float f3 = f2 + b(this.n);
/*  667 */       float f4 = f2 + b(this.o);
/*  668 */       if ("right".equals(this.G)) {
/*  669 */         if (f3 >= this.i) {
/*  670 */           f3 = this.i;
/*      */         }
/*  672 */         else if (f3 <= this.h) {
/*  673 */           f3 = this.h;
/*      */         }
/*  675 */         if (f4 >= this.k) {
/*  676 */           f4 = this.k;
/*      */         }
/*  678 */         else if (f4 <= this.j)
/*  679 */           f4 = this.j;
/*      */       }
/*  681 */       else if ("left".equals(this.G)) {
/*  682 */         if (f3 >= this.h) {
/*  683 */           f3 = this.h;
/*      */         }
/*  685 */         else if (f3 <= this.i) {
/*  686 */           f3 = this.i;
/*      */         }
/*  688 */         if (f4 >= this.j) {
/*  689 */           f4 = this.j;
/*      */         }
/*  691 */         else if (f4 <= this.k) {
/*  692 */           f4 = this.k;
/*      */         }
/*      */       }
/*      */ 
/*  696 */       b(this.n, (int)f3);
/*  697 */       b(this.o, (int)f4);
/*  698 */       break;
/*      */     case 1:
/*      */     case 3:
/*  702 */       if (this.C) {
/*  703 */         d();
/*      */       }
/*      */       break;
/*      */     }
/*  707 */     return true;
/*      */   }
/*      */ 
/*      */   private void b() {
/*  711 */     this.L = true;
/*  712 */     O = true;
/*  713 */     if (null != this.o) {
/*  714 */       this.o.requestLayout();
/*  715 */       this.o.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
/*      */       {
/*      */         public void onGlobalLayout() {
/*  718 */           if (Build.VERSION.SDK_INT >= 16) {
/*  719 */             h.a(h.this).getViewTreeObserver().removeOnGlobalLayoutListener(this);
/*      */           }
/*  721 */           int i = 0;
/*  722 */           boolean bool = false;
/*  723 */           if (h.a(h.this, h.b(h.this))) {
/*  724 */             bool = true;
/*  725 */             i = h.c(h.this);
/*      */           }
/*      */           else {
/*  728 */             j = 0;
/*  729 */             int k = h.a(h.this, h.a(h.this)) / 2;
/*  730 */             Rect localRect = new Rect();
/*  731 */             h.a(h.this).getGlobalVisibleRect(localRect);
/*  732 */             j = localRect.right - localRect.left;
/*  733 */             if (j >= k) {
/*  734 */               bool = true;
/*  735 */               i = h.c(h.this);
/*      */             } else {
/*  737 */               i = h.d(h.this);
/*      */             }
/*      */           }
/*  740 */           int j = h.b(h.this, h.a(h.this));
/*  741 */           h.a(h.this, h.a(h.this), j, i, bool).start();
/*      */         } } );
/*      */     }
/*      */   }
/*      */ 
/*      */   private void c() {
/*  747 */     this.L = true;
/*  748 */     O = true;
/*  749 */     this.n.requestLayout();
/*  750 */     this.n.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
/*      */     {
/*      */       public void onGlobalLayout() {
/*  753 */         if (Build.VERSION.SDK_INT >= 16) {
/*  754 */           h.e(h.this).getViewTreeObserver().removeOnGlobalLayoutListener(this);
/*      */         }
/*  756 */         int i = 0;
/*  757 */         boolean bool1 = false;
/*  758 */         boolean bool2 = false;
/*      */         Rect localRect1;
/*  759 */         if (h.a(h.this, h.b(h.this))) {
/*  760 */           bool1 = true;
/*  761 */           i = h.f(h.this);
/*      */         }
/*      */         else
/*      */         {
/*      */           int m;
/*  763 */           if (null != h.a(h.this))
/*      */           {
/*  765 */             int j = 0;
/*  766 */             m = h.a(h.this, h.a(h.this)) / 2;
/*  767 */             Rect localRect2 = new Rect();
/*  768 */             h.a(h.this).getGlobalVisibleRect(localRect2);
/*  769 */             Rect localRect3 = new Rect();
/*  770 */             h.e(h.this).getGlobalVisibleRect(localRect3);
/*  771 */             if (localRect2.left == 0) {
/*  772 */               j = localRect3.left - localRect2.left;
/*  773 */               if ("right".equals(h.b(h.this))) {
/*  774 */                 if (j >= m) {
/*  775 */                   bool1 = true;
/*  776 */                   i = h.f(h.this);
/*      */                 } else {
/*  778 */                   i = h.g(h.this);
/*      */                 }
/*  780 */               } else if ("left".equals(h.b(h.this)))
/*  781 */                 if (j <= m) {
/*  782 */                   bool1 = true;
/*  783 */                   i = h.f(h.this);
/*      */                 } else {
/*  785 */                   i = h.g(h.this);
/*      */                 }
/*      */             }
/*  788 */             else if (localRect2.right == h.h(h.this)) {
/*  789 */               j = localRect2.right - localRect3.right;
/*  790 */               if ("right".equals(h.b(h.this))) {
/*  791 */                 if (j <= m) {
/*  792 */                   bool1 = true;
/*  793 */                   i = h.f(h.this);
/*      */                 } else {
/*  795 */                   i = h.g(h.this);
/*      */                 }
/*  797 */               } else if ("left".equals(h.b(h.this)))
/*  798 */                 if (j >= m) {
/*  799 */                   bool1 = true;
/*  800 */                   i = h.f(h.this);
/*      */                 } else {
/*  802 */                   i = h.g(h.this);
/*      */                 }
/*      */             }
/*      */           }
/*      */           else {
/*  807 */             localRect1 = new Rect();
/*  808 */             h.e(h.this).getGlobalVisibleRect(localRect1);
/*      */ 
/*  810 */             m = localRect1.right - localRect1.left;
/*  811 */             int n = h.e(h.this).getWidth() / 2;
/*  812 */             if (("right".equals(h.b(h.this))) && (h.g(h.this) < 0)) {
/*  813 */               if (m >= n) {
/*  814 */                 bool1 = true;
/*  815 */                 i = h.f(h.this);
/*      */               } else {
/*  817 */                 i = h.g(h.this);
/*      */               }
/*  819 */             } else if (("left".equals(h.b(h.this))) && (h.f(h.this) >= 0)) {
/*  820 */               if (m >= n) {
/*  821 */                 bool1 = true;
/*  822 */                 i = h.f(h.this);
/*      */               } else {
/*  824 */                 i = h.g(h.this);
/*      */               }
/*      */             }
/*  827 */             else if (m <= n) {
/*  828 */               bool1 = true;
/*  829 */               i = h.f(h.this);
/*      */             } else {
/*  831 */               i = h.g(h.this);
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/*  836 */         if (null != h.a(h.this)) {
/*  837 */           localRect1 = new Rect();
/*  838 */           h.a(h.this).getGlobalVisibleRect(localRect1);
/*  839 */           if (localRect1.left == 0) {
/*  840 */             if ("left".equals(h.b(h.this)))
/*  841 */               bool2 = true;
/*      */           }
/*  843 */           else if ((localRect1.right == h.h(h.this)) && 
/*  844 */             ("right".equals(h.b(h.this)))) {
/*  845 */             bool2 = true;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  850 */         if ("bounce".equalsIgnoreCase(h.i(h.this))) {
/*  851 */           bool2 = false;
/*  852 */           bool1 = false;
/*  853 */           i = h.g(h.this);
/*  854 */           h.b(h.this, null);
/*      */         }
/*  856 */         int k = h.b(h.this, h.e(h.this));
/*  857 */         h.a(h.this, h.e(h.this), k, i, bool1, bool2).start();
/*      */       } } );
/*      */   }
/*      */ 
/*      */   private void d() {
/*  862 */     this.L = true;
/*  863 */     O = true;
/*  864 */     this.n.requestLayout();
/*  865 */     this.n.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
/*      */     {
/*      */       public void onGlobalLayout() {
/*  868 */         if (Build.VERSION.SDK_INT >= 16) {
/*  869 */           h.e(h.this).getViewTreeObserver().removeOnGlobalLayoutListener(this);
/*      */         }
/*  871 */         boolean bool = false;
/*  872 */         int i = 0;
/*  873 */         int j = 0;
/*  874 */         if (h.a(h.this, h.b(h.this))) {
/*  875 */           bool = true;
/*  876 */           i = h.f(h.this);
/*  877 */           j = h.c(h.this);
/*  878 */           if ("bounce".equalsIgnoreCase(h.i(h.this))) {
/*  879 */             bool = false;
/*  880 */             i = h.g(h.this);
/*  881 */             j = h.d(h.this);
/*  882 */             h.b(h.this, null);
/*      */           }
/*      */         }
/*  885 */         else if ("bounce".equalsIgnoreCase(h.i(h.this))) {
/*  886 */           bool = false;
/*  887 */           i = h.g(h.this);
/*  888 */           j = h.d(h.this);
/*  889 */           h.b(h.this, null);
/*      */         } else {
/*  891 */           k = 0;
/*  892 */           m = h.h(h.this);
/*  893 */           int n = h.b(h.this, h.a(h.this));
/*  894 */           int i1 = n + h.a(h.this, h.a(h.this));
/*  895 */           int i2 = h.b(h.this, h.e(h.this));
/*  896 */           int i3 = i2 + h.e(h.this).getWidth();
/*      */ 
/*  899 */           if (h.e(h.this).getWidth() < h.h(h.this)) {
/*  900 */             Rect localRect = new Rect();
/*  901 */             h.e(h.this).getGlobalVisibleRect(localRect);
/*  902 */             int i5 = localRect.right - localRect.left;
/*  903 */             int i6 = h.e(h.this).getWidth() / 2;
/*      */ 
/*  905 */             if (0 == localRect.left) {
/*  906 */               if (i5 <= i6) {
/*  907 */                 bool = true;
/*  908 */                 i = h.f(h.this);
/*  909 */                 j = h.c(h.this);
/*      */               } else {
/*  911 */                 i = h.g(h.this);
/*  912 */                 j = h.d(h.this);
/*      */               }
/*      */ 
/*      */             }
/*  916 */             else if (h.h(h.this) == localRect.right)
/*  917 */               if (i5 <= i6) {
/*  918 */                 bool = true;
/*  919 */                 i = h.f(h.this);
/*  920 */                 j = h.c(h.this);
/*      */               } else {
/*  922 */                 i = h.g(h.this);
/*  923 */                 j = h.d(h.this);
/*      */               }
/*      */           }
/*      */           else
/*      */           {
/*      */             int i4;
/*  929 */             if ((n <= k) && (k < i1))
/*      */             {
/*  931 */               i4 = i1 - k;
/*  932 */               if ((0 < i4) && (i4 < h.a(h.this, h.a(h.this)))) {
/*  933 */                 if (i1 - k >= h.a(h.this, h.a(h.this)) / 2) {
/*  934 */                   bool = true;
/*  935 */                   i = h.f(h.this);
/*  936 */                   j = h.c(h.this);
/*      */                 } else {
/*  938 */                   i = h.g(h.this);
/*  939 */                   j = h.d(h.this);
/*      */                 }
/*      */               }
/*      */               else {
/*  943 */                 if (i4 == h.a(h.this, h.a(h.this))) {
/*  944 */                   h.a(h.this, true);
/*  945 */                   h.a(h.this, h.e(h.this), h.f(h.this));
/*  946 */                   h.a(h.this, h.a(h.this), h.c(h.this));
/*  947 */                   h.a(false);
/*  948 */                   return;
/*      */                 }
/*      */ 
/*  952 */                 h.a(h.this, false);
/*  953 */                 h.a(h.this, h.e(h.this), h.g(h.this));
/*  954 */                 h.a(h.this, h.a(h.this), h.d(h.this));
/*  955 */                 h.a(false);
/*  956 */                 return;
/*      */               }
/*      */ 
/*      */             }
/*  960 */             else if ((n < m) && (m <= i1))
/*      */             {
/*  962 */               i4 = m - n;
/*  963 */               if ((0 < i4) && (i4 < h.a(h.this, h.a(h.this)))) {
/*  964 */                 if (m - n >= h.a(h.this, h.a(h.this)) / 2) {
/*  965 */                   bool = true;
/*  966 */                   i = h.f(h.this);
/*  967 */                   j = h.c(h.this);
/*      */                 } else {
/*  969 */                   i = h.g(h.this);
/*  970 */                   j = h.d(h.this);
/*      */                 }
/*      */               }
/*      */               else {
/*  974 */                 if (i4 == h.a(h.this, h.a(h.this))) {
/*  975 */                   h.a(h.this, true);
/*  976 */                   h.a(h.this, h.e(h.this), h.f(h.this));
/*  977 */                   h.a(h.this, h.a(h.this), h.c(h.this));
/*  978 */                   h.a(false);
/*  979 */                   return;
/*      */                 }
/*      */ 
/*  983 */                 h.a(h.this, false);
/*  984 */                 h.a(h.this, h.e(h.this), h.g(h.this));
/*  985 */                 h.a(h.this, h.a(h.this), h.d(h.this));
/*  986 */                 h.a(false);
/*  987 */                 return;
/*      */               }
/*      */             } else {
/*  990 */               h.a(h.this, false);
/*  991 */               h.a(h.this, h.e(h.this), h.g(h.this));
/*  992 */               h.a(h.this, h.a(h.this), h.d(h.this));
/*  993 */               h.a(false);
/*  994 */               return;
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/*  999 */         int k = h.b(h.this, h.e(h.this));
/* 1000 */         h.a(h.this, h.e(h.this), k, i, bool).start();
/* 1001 */         int m = h.b(h.this, h.a(h.this));
/* 1002 */         h.a(h.this, h.a(h.this), m, j, bool).start();
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void e()
/*      */   {
/* 1009 */     float f1 = Math.abs(b(this.n) - this.h);
/* 1010 */     int i1 = Math.abs(this.i - this.h);
/* 1011 */     String str = null;
/* 1012 */     if ((0.0F <= f1) && (f1 < i1 * 0.2F) && (this.R)) {
/* 1013 */       this.R = false;
/* 1014 */       this.S = true; this.T = true; this.U = true; this.V = true; this.W = true;
/* 1015 */       str = "0";
/* 1016 */     } else if ((i1 * 0.2F <= f1) && (f1 < i1 * 0.4F) && (this.S)) {
/* 1017 */       this.S = false;
/* 1018 */       this.R = true; this.T = true; this.U = true; this.V = true; this.W = true;
/* 1019 */       str = "20";
/* 1020 */     } else if ((i1 * 0.4F <= f1) && (f1 < i1 * 0.6F) && (this.T)) {
/* 1021 */       this.T = false;
/* 1022 */       this.R = true; this.S = true; this.U = true; this.V = true; this.W = true;
/* 1023 */       str = "40";
/* 1024 */     } else if ((i1 * 0.6F <= f1) && (f1 < i1 * 0.8F) && (this.U)) {
/* 1025 */       this.U = false;
/* 1026 */       this.R = true; this.S = true; this.T = true; this.V = true; this.W = true;
/* 1027 */       str = "60";
/* 1028 */     } else if ((i1 * 0.8F <= f1) && (f1 < i1) && (this.V)) {
/* 1029 */       this.V = false;
/* 1030 */       this.R = true; this.S = true; this.T = true; this.U = true; this.W = true;
/* 1031 */       str = "80";
/* 1032 */     } else if ((i1 <= f1) && (this.W)) {
/* 1033 */       this.W = false;
/* 1034 */       this.R = true; this.S = true; this.T = true; this.U = true; this.V = true;
/* 1035 */       str = "100";
/*      */     }
/* 1037 */     if (!TextUtils.isEmpty(str))
/* 1038 */       a("move", false, str);
/*      */   }
/*      */ 
/*      */   private void b(boolean paramBoolean) {
/* 1042 */     if (this.L) {
/* 1043 */       this.L = false;
/* 1044 */       a("end", paramBoolean, paramBoolean ? "100" : "0");
/*      */     }
/*      */   }
/*      */ 
/* 1048 */   private void a(boolean paramBoolean1, boolean paramBoolean2) { if (this.L) {
/* 1049 */       this.L = false;
/* 1050 */       a("end", paramBoolean1, paramBoolean2, paramBoolean1 ? "100" : "0");
/*      */     }
/*      */   }
/*      */ 
/*      */   private void a(View paramView, int paramInt)
/*      */   {
/* 1059 */     if (null != paramView)
/* 1060 */       if ((paramView == this.n) && (null != this.l)) {
/* 1061 */         this.l.obtainFrameOptions().left = paramInt;
/* 1062 */         this.l.obtainFrameOptions().checkValueIsPercentage("left", paramInt, this.J, true, true);
/* 1063 */       } else if ((paramView == this.o) && (null != this.m)) {
/* 1064 */         this.m.obtainFrameOptions().left = paramInt;
/* 1065 */         this.m.obtainFrameOptions().checkValueIsPercentage("left", paramInt, this.J, true, true);
/*      */       }
/*      */   }
/*      */ 
/*      */   private ValueAnimator a(View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
/*      */   {
/* 1079 */     return a(paramView, paramInt1, paramInt2, paramBoolean, false);
/*      */   }
/*      */ 
/*      */   private ValueAnimator a(final View paramView, int paramInt1, final int paramInt2, final boolean paramBoolean1, final boolean paramBoolean2)
/*      */   {
/* 1090 */     ValueAnimator localValueAnimator = null;
/* 1091 */     ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
/* 1092 */     if ((paramView instanceof INativeView))
/* 1093 */       localValueAnimator = ValueAnimator.ofInt(new int[] { paramInt1, paramInt2 });
/* 1094 */     else if ((localLayoutParams instanceof AbsoluteLayout.LayoutParams))
/* 1095 */       localValueAnimator = ValueAnimator.ofInt(new int[] { paramInt1, paramInt2 });
/* 1096 */     else if ((localLayoutParams instanceof FrameLayout.LayoutParams)) {
/* 1097 */       localValueAnimator = ValueAnimator.ofFloat(new float[] { paramInt1, paramInt2 });
/*      */     }
/*      */ 
/* 1101 */     long l1 = new BigDecimal(450).multiply(new BigDecimal(Math.abs(paramInt2 - paramInt1)).divide(new BigDecimal(this.J), 4, 4)).longValue();
/*      */ 
/* 1103 */     l1 = Math.max(l1, 200L);
/* 1104 */     l1 = Math.min(l1, 250L);
/* 1105 */     localValueAnimator.setDuration(l1);
/* 1106 */     localValueAnimator.addListener(new Animator.AnimatorListener()
/*      */     {
/*      */       public void onAnimationStart(Animator paramAnonymousAnimator) {
/* 1109 */         h.j(h.this);
/*      */       }
/*      */ 
/*      */       public void onAnimationEnd(Animator paramAnonymousAnimator) {
/* 1113 */         if (h.k(h.this)) {
/* 1114 */           h.b(h.this, false);
/* 1115 */           h.a(h.this, "end", paramBoolean1, paramBoolean2, paramBoolean1 ? "100" : "0");
/*      */         }
/*      */         int i;
/* 1117 */         if (paramBoolean1) {
/* 1118 */           if ((paramView == h.e(h.this)) && (null != h.l(h.this))) {
/* 1119 */             i = h.b(h.this, h.e(h.this));
/* 1120 */             if ((i <= -h.e(h.this).getWidth()) || (i >= h.h(h.this))) {
/* 1121 */               h.l(h.this).popFromViewStack();
/*      */             }
/*      */           }
/*      */         }
/* 1125 */         else if (paramView == h.a(h.this))
/*      */         {
/* 1127 */           i = h.b(h.this, h.a(h.this));
/* 1128 */           if ((i <= -h.h(h.this)) || (i >= h.h(h.this))) {
/* 1129 */             if ((2147483647 != h.m(h.this)) && (h.m(h.this) != i)) {
/* 1130 */               h.b(h.this, h.a(h.this), h.m(h.this));
/*      */             }
/* 1132 */             h.a(h.this, 2147483647);
/*      */           }
/* 1134 */           if ((!(paramView instanceof INativeView)) && (null != h.n(h.this))) {
/* 1135 */             int j = h.b(h.this, h.a(h.this));
/* 1136 */             if ((j <= -h.a(h.this, h.a(h.this))) || (j >= h.h(h.this))) {
/* 1137 */               h.n(h.this).popFromViewStack();
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/* 1142 */         h.a(h.this, paramView, paramInt2);
/* 1143 */         String str1 = null;
/* 1144 */         d locald = null;
/* 1145 */         if (null != paramView) {
/* 1146 */           if (!paramBoolean2) {
/* 1147 */             if (paramView == h.e(h.this)) {
/* 1148 */               locald = h.l(h.this);
/* 1149 */               if (paramBoolean1)
/* 1150 */                 str1 = h.o(h.this);
/*      */               else
/* 1152 */                 str1 = h.p(h.this);
/*      */             }
/* 1154 */             else if (paramView == h.a(h.this)) {
/* 1155 */               locald = h.n(h.this);
/* 1156 */               if (paramBoolean1)
/* 1157 */                 str1 = h.q(h.this);
/*      */               else {
/* 1159 */                 str1 = h.r(h.this);
/*      */               }
/*      */             }
/*      */           }
/* 1163 */           int k = 0;
/* 1164 */           if (("hide".equalsIgnoreCase(str1)) || ("close".equalsIgnoreCase(str1))) {
/* 1165 */             k = 1;
/*      */           }
/* 1167 */           if (k != 0) {
/* 1168 */             if ((paramView instanceof INativeView)) {
/* 1169 */               String str2 = ((INativeView)paramView).getViewId();
/* 1170 */               String str3 = ((INativeView)paramView).getViewUUId();
/* 1171 */               if (str1.equalsIgnoreCase("close")) {
/* 1172 */                 str1 = "view_clear";
/*      */               }
/* 1174 */               h.l(h.this).obtainWindowMgr().processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[] { h.l(h.this).obtainWebView(), "nativeobj", str1, JSONUtil.createJSONArray("['" + str2 + "','" + str3 + "']") });
/*      */             }
/* 1177 */             else if (null != locald) {
/* 1178 */               locald.obtainWindowMgr().processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[] { locald.obtainWebView(), "ui", "execMethod", JSONUtil.createJSONArray("[\"NWindow\",\"" + str1 + "\",[\"" + locald.obtainWebView().getWebviewUUID() + "\",[null,null,null]]]") });
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 1185 */         if (h.s(h.this) <= 1) {
/* 1186 */           h.a(false);
/* 1187 */           h.c(h.this, null);
/*      */         }
/* 1189 */         if (h.s(h.this) >= 1)
/* 1190 */           h.t(h.this);
/*      */       }
/*      */ 
/*      */       public void onAnimationCancel(Animator paramAnonymousAnimator)
/*      */       {
/*      */       }
/*      */ 
/*      */       public void onAnimationRepeat(Animator paramAnonymousAnimator)
/*      */       {
/*      */       }
/*      */     });
/* 1198 */     localValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
/*      */     {
/*      */       public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator) {
/* 1201 */         ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
/* 1202 */         if ((paramView instanceof INativeView)) {
/* 1203 */           h.b(h.this, paramView, ((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
/* 1204 */           paramView.requestLayout();
/* 1205 */           paramView.invalidate();
/*      */         }
/* 1207 */         else if ((localLayoutParams instanceof FrameLayout.LayoutParams)) {
/* 1208 */           ViewHelper.setX(paramView, ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
/* 1209 */         } else if ((localLayoutParams instanceof AbsoluteLayout.LayoutParams)) {
/* 1210 */           ((AbsoluteLayout.LayoutParams)localLayoutParams).x = ((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue();
/* 1211 */           paramView.requestLayout();
/*      */         }
/*      */       }
/*      */     });
/* 1215 */     return localValueAnimator;
/*      */   }
/*      */ 
/*      */   private boolean d(String paramString)
/*      */   {
/* 1222 */     if (null != this.a) {
/* 1223 */       VelocityTracker localVelocityTracker = this.a;
/* 1224 */       localVelocityTracker.computeCurrentVelocity(1000, 1000.0F);
/* 1225 */       float f1 = localVelocityTracker.getXVelocity();
/* 1226 */       this.a.clear();
/* 1227 */       this.a.recycle();
/* 1228 */       this.a = null;
/* 1229 */       if (Math.abs(f1) >= 200.0F) {
/* 1230 */         return true;
/*      */       }
/*      */     }
/* 1233 */     return false;
/*      */   }
/*      */ 
/*      */   private void a(String paramString1, boolean paramBoolean, String paramString2)
/*      */   {
/* 1241 */     a(paramString1, paramBoolean, false, paramString2);
/*      */   }
/*      */ 
/*      */   private void a(String paramString1, boolean paramBoolean1, boolean paramBoolean2, String paramString2)
/*      */   {
/* 1250 */     if ((null != this.E) && (!TextUtils.isEmpty(this.F)) && (!TextUtils.isEmpty(paramString1))) {
/* 1251 */       String str1 = null; String str2 = null;
/* 1252 */       if (null != this.o) {
/* 1253 */         if ((this.o instanceof INativeView)) {
/* 1254 */           str2 = ((INativeView)this.o).getViewId();
/*      */         }
/* 1256 */         else if ((null != this.m) && (null != this.m.obtainWebView())) {
/* 1257 */           str2 = this.m.obtainWebView().obtainFrameId();
/*      */         }
/*      */       }
/*      */ 
/* 1261 */       if ((null != this.l) && (null != this.l.obtainWebView())) {
/* 1262 */         str1 = this.l.obtainWebView().obtainFrameId();
/*      */       }
/* 1264 */       String str4 = str1;
/* 1265 */       String str5 = str2;
/* 1266 */       if (paramBoolean1)
/* 1267 */         str3 = paramBoolean2 ? str1 : str2;
/*      */       else {
/* 1269 */         str3 = paramBoolean2 ? str2 : str1;
/*      */       }
/* 1271 */       if ((!a(this.m)) && (this.m.isWebviewCovered())) {
/* 1272 */         if ((!a(this.l)) && (!this.l.isWebviewCovered()))
/* 1273 */           str3 = str1;
/*      */       }
/* 1275 */       else if ((!a(this.l)) && (this.l.isWebviewCovered()) && 
/* 1276 */         (!a(this.m)) && (!this.m.isWebviewCovered())) {
/* 1277 */         str3 = str2;
/*      */       }
/*      */ 
/* 1280 */       String str3 = TextUtils.isEmpty(str3) ? "undefined" : str3;
/* 1281 */       str4 = TextUtils.isEmpty(str4) ? "" : str4;
/* 1282 */       str5 = TextUtils.isEmpty(str5) ? "" : str5;
/* 1283 */       if (null != this.E.obtainWebView())
/*      */       {
/*      */         String str6;
/* 1285 */         if ("end".equals(paramString1)) {
/* 1286 */           str6 = String.format("{\"type\":\"%s\",\"result\":%b,\"id\":\"%s\",\"targetId\":\"%s\",\"otherId\":\"%s\",\"direction\":\"%s\",\"progress\":\"%s\"}", new Object[] { paramString1, Boolean.valueOf(paramBoolean1), str3, str4, str5, this.G, paramString2 });
/*      */         }
/*      */         else
/*      */         {
/* 1291 */           str6 = String.format("{\"type\":\"%s\",\"id\":\"%s\",\"targetId\":\"%s\",\"otherId\":\"%s\",\"direction\":\"%s\",\"progress\":\"%s\"}", new Object[] { paramString1, str3, str4, str5, this.G, paramString2 });
/*      */         }
/*      */ 
/* 1295 */         JSUtil.execCallback(this.E.obtainWebView(), this.F, str6, JSUtil.OK, true, true);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean g(MotionEvent paramMotionEvent) {
/* 1301 */     int i1 = paramMotionEvent.getAction();
/* 1302 */     float f1 = paramMotionEvent.getRawX();
/* 1303 */     float f2 = paramMotionEvent.getRawY();
/* 1304 */     switch (i1) {
/*      */     case 0:
/* 1306 */       this.d = f1;
/* 1307 */       this.e = f2;
/* 1308 */       break;
/*      */     case 2:
/* 1311 */       float f3 = f1 - this.d;
/* 1312 */       this.d = f1;
/*      */ 
/* 1315 */       float f4 = 0.0F;
/*      */ 
/* 1317 */       float f5 = 0.0F;
/*      */ 
/* 1319 */       ViewGroup.LayoutParams localLayoutParams1 = this.n.getLayoutParams();
/* 1320 */       if ((localLayoutParams1 instanceof FrameLayout.LayoutParams))
/* 1321 */         f4 = ViewHelper.getX(this.n);
/* 1322 */       else if ((localLayoutParams1 instanceof AbsoluteLayout.LayoutParams)) {
/* 1323 */         f4 = ((AbsoluteLayout.LayoutParams)localLayoutParams1).x;
/*      */       }
/*      */ 
/* 1326 */       if (null != this.o) {
/* 1327 */         ViewGroup.LayoutParams localLayoutParams2 = this.o.getLayoutParams();
/* 1328 */         if ((this.o instanceof INativeView)) {
/* 1329 */           f5 = b(this.o);
/*      */         }
/* 1331 */         else if ((localLayoutParams2 instanceof FrameLayout.LayoutParams))
/* 1332 */           f5 = ViewHelper.getX(this.o);
/* 1333 */         else if ((localLayoutParams2 instanceof AbsoluteLayout.LayoutParams))
/* 1334 */           f5 = ((AbsoluteLayout.LayoutParams)localLayoutParams2).x;
/*      */         int i2;
/* 1337 */         if (0.0F != f4) {
/* 1338 */           if ("right".equals(this.G))
/*      */           {
/* 1340 */             if (f4 + f3 >= 0.0F) {
/* 1341 */               f3 = -f4;
/*      */             }
/*      */             else {
/* 1344 */               i2 = (int)(f4 + this.n.getWidth());
/* 1345 */               if (i2 + f3 >= this.J) {
/* 1346 */                 f3 = this.J - i2;
/*      */               }
/*      */             }
/*      */           }
/* 1350 */           else if ("left".equals(this.G)) {
/* 1351 */             if (f4 + f3 <= 0.0F) {
/* 1352 */               f3 = -f4;
/*      */             } else {
/* 1354 */               i2 = (int)(f4 + this.n.getWidth());
/* 1355 */               if (i2 + f3 <= this.J) {
/* 1356 */                 f3 = this.J - i2;
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/* 1361 */         if ("right".equals(this.G)) {
/* 1362 */           if (f5 + f3 >= this.J) {
/* 1363 */             f3 = this.J - f5;
/*      */           } else {
/* 1365 */             i2 = (int)(f5 + a(this.o));
/* 1366 */             if (i2 + f3 <= this.J) {
/* 1367 */               f3 = this.J - i2;
/*      */             }
/*      */           }
/*      */         }
/* 1371 */         else if ("left".equals(this.G)) {
/* 1372 */           i2 = (int)(f5 + a(this.o));
/* 1373 */           if (i2 + f3 <= 0.0F) {
/* 1374 */             f3 = -i2;
/*      */           }
/* 1376 */           else if (f5 + f3 >= 0.0F)
/* 1377 */             f3 = -f5;
/*      */         }
/*      */         AbsoluteLayout.LayoutParams localLayoutParams;
/* 1382 */         if (0.0F != f4) {
/* 1383 */           if ((localLayoutParams1 instanceof FrameLayout.LayoutParams)) {
/* 1384 */             ViewHelper.setX(this.n, ViewHelper.getX(this.n) + f3);
/* 1385 */           } else if ((localLayoutParams1 instanceof AbsoluteLayout.LayoutParams)) {
/* 1386 */             localLayoutParams = (AbsoluteLayout.LayoutParams)localLayoutParams1;
/* 1387 */             localLayoutParams.x += (int)f3;
/* 1388 */             this.n.requestLayout();
/*      */           }
/*      */         }
/*      */ 
/* 1392 */         if ((this.o instanceof INativeView)) {
/* 1393 */           b(this.o, (int)(f4 + f3));
/* 1394 */           this.o.requestLayout();
/* 1395 */           this.o.invalidate();
/*      */         }
/* 1397 */         else if ((localLayoutParams2 instanceof FrameLayout.LayoutParams)) {
/* 1398 */           ViewHelper.setX(this.o, ViewHelper.getX(this.o) + f3);
/* 1399 */         } else if ((localLayoutParams2 instanceof AbsoluteLayout.LayoutParams)) {
/* 1400 */           localLayoutParams = (AbsoluteLayout.LayoutParams)localLayoutParams2;
/* 1401 */           localLayoutParams.x += (int)f3;
/* 1402 */           this.o.requestLayout();
/*      */         }
/*      */       }
/* 1404 */       break;
/*      */     case 1:
/*      */     case 3:
/* 1409 */       this.M = false;
/* 1410 */       if (this.C) {
/* 1411 */         f();
/*      */       }
/*      */       break;
/*      */     }
/* 1415 */     return true;
/*      */   }
/*      */   private void f() {
/* 1418 */     this.L = true;
/* 1419 */     O = true;
/* 1420 */     if (null != this.o) {
/* 1421 */       this.n.requestLayout();
/* 1422 */       this.n.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
/*      */       {
/*      */         public void onGlobalLayout() {
/* 1425 */           if (Build.VERSION.SDK_INT >= 16) {
/* 1426 */             h.e(h.this).getViewTreeObserver().removeOnGlobalLayoutListener(this);
/*      */           }
/*      */ 
/* 1429 */           boolean bool = false;
/* 1430 */           int i = 0;
/* 1431 */           int j = 0;
/* 1432 */           if (h.a(h.this, h.b(h.this)))
/*      */           {
/* 1440 */             bool = true;
/* 1441 */             if ("right".equals(h.b(h.this))) {
/* 1442 */               i = 0;
/* 1443 */               j = h.h(h.this);
/* 1444 */             } else if ("left".equals(h.b(h.this))) {
/* 1445 */               i = 0;
/* 1446 */               j = -h.a(h.this, h.a(h.this));
/*      */             }
/*      */           }
/*      */           else {
/* 1450 */             View localView = (View)h.e(h.this).getParent();
/* 1451 */             int[] arrayOfInt1 = new int[2];
/* 1452 */             int[] arrayOfInt2 = new int[2];
/* 1453 */             int[] arrayOfInt3 = new int[2];
/* 1454 */             localView.getLocationOnScreen(arrayOfInt1);
/* 1455 */             h.e(h.this).getLocationOnScreen(arrayOfInt2);
/* 1456 */             h.a(h.this).getLocationOnScreen(arrayOfInt3);
/*      */ 
/* 1458 */             int n = arrayOfInt1[0] + localView.getWidth();
/* 1459 */             int i1 = arrayOfInt2[0] + h.e(h.this).getWidth();
/* 1460 */             int i2 = arrayOfInt3[0] + h.a(h.this, h.a(h.this));
/*      */             int i3;
/* 1462 */             if ((arrayOfInt3[0] <= arrayOfInt1[0]) && (arrayOfInt1[0] < i2))
/*      */             {
/* 1464 */               i3 = i2 - arrayOfInt1[0];
/* 1465 */               if ((0 < i3) && (i3 < h.a(h.this, h.a(h.this)))) {
/* 1466 */                 if (i3 <= h.a(h.this, h.a(h.this)) / 2) {
/* 1467 */                   bool = true;
/* 1468 */                   i = 0;
/* 1469 */                   j = -h.a(h.this, h.a(h.this));
/*      */                 } else {
/* 1471 */                   i = h.a(h.this, h.a(h.this));
/* 1472 */                   j = 0;
/*      */                 }
/*      */               }
/*      */               else {
/* 1476 */                 if (i3 == h.a(h.this, h.a(h.this))) {
/* 1477 */                   h.a(h.this, false, true);
/* 1478 */                   h.a(h.this, h.e(h.this), h.a(h.this, h.a(h.this)));
/* 1479 */                   h.a(h.this, h.a(h.this), 0);
/* 1480 */                   h.a(false);
/* 1481 */                   return;
/*      */                 }
/*      */ 
/* 1485 */                 h.a(h.this, true, true);
/* 1486 */                 h.a(h.this, h.e(h.this), 0);
/* 1487 */                 h.a(h.this, h.a(h.this), -h.a(h.this, h.a(h.this)));
/* 1488 */                 h.a(false);
/* 1489 */                 return;
/*      */               }
/*      */ 
/*      */             }
/* 1493 */             else if ((arrayOfInt3[0] < n) && (n <= i2))
/*      */             {
/* 1495 */               i3 = n - arrayOfInt3[0];
/* 1496 */               if ((0 < i3) && (i3 < h.a(h.this, h.a(h.this)))) {
/* 1497 */                 if (i3 <= h.a(h.this, h.a(h.this)) / 2) {
/* 1498 */                   bool = true;
/* 1499 */                   i = 0;
/* 1500 */                   j = h.h(h.this);
/*      */                 } else {
/* 1502 */                   i = -h.a(h.this, h.a(h.this));
/* 1503 */                   j = h.h(h.this) - h.a(h.this, h.a(h.this));
/*      */                 }
/*      */               }
/*      */               else {
/* 1507 */                 if (i3 == h.a(h.this, h.a(h.this))) {
/* 1508 */                   h.a(h.this, false, true);
/* 1509 */                   h.a(h.this, h.e(h.this), -h.a(h.this, h.a(h.this)));
/* 1510 */                   h.a(h.this, h.a(h.this), h.h(h.this) - h.a(h.this, h.a(h.this)));
/* 1511 */                   h.a(false);
/* 1512 */                   return;
/*      */                 }
/*      */ 
/* 1516 */                 h.a(h.this, true, true);
/* 1517 */                 h.a(h.this, h.e(h.this), 0);
/* 1518 */                 h.a(h.this, h.a(h.this), h.h(h.this));
/* 1519 */                 h.a(false);
/* 1520 */                 return;
/*      */               }
/*      */             } else {
/* 1523 */               h.a(h.this, true, true);
/* 1524 */               h.a(h.this, h.e(h.this), 0);
/* 1525 */               h.a(h.this, h.a(h.this), h.h(h.this));
/* 1526 */               h.a(false);
/* 1527 */               return;
/*      */             }
/*      */           }
/* 1530 */           int k = h.b(h.this, h.e(h.this));
/* 1531 */           if (0 != k) {
/* 1532 */             h.a(h.this, h.e(h.this), k, i, bool, true).start();
/*      */           }
/* 1534 */           int m = h.b(h.this, h.a(h.this));
/* 1535 */           h.a(h.this, h.a(h.this), m, j, bool, true).start();
/*      */         } } );
/*      */     }
/*      */   }
/*      */ 
/*      */   public View a(String paramString) {
/* 1541 */     String str = e(paramString);
/* 1542 */     ViewOptions localViewOptions = this.l.obtainFrameOptions();
/* 1543 */     HashMap localHashMap = localViewOptions.dragData;
/* 1544 */     if ((null != localHashMap) && (localHashMap.containsKey(str))) {
/* 1545 */       DragBean localDragBean = (DragBean)localHashMap.get(str);
/* 1546 */       if (null != localDragBean) {
/* 1547 */         IFrameView localIFrameView = localDragBean.dragBindWebView;
/* 1548 */         if ((null != localIFrameView) && ("follow".equalsIgnoreCase(JSONUtil.getString(localDragBean.dragBindViewOp, "moveMode")))) {
/* 1549 */           View localView = localIFrameView.obtainMainView();
/* 1550 */           if ((null != localView) && (0 == localView.getVisibility()) && (localView.getWidth() < this.J)) {
/* 1551 */             int i1 = b(localView);
/* 1552 */             int i2 = i1 + localView.getWidth();
/* 1553 */             if (((0 <= i1) && (i1 < this.J)) || ((0 < i2) && (i2 <= this.J))) {
/* 1554 */               return localView;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1560 */     return null;
/*      */   }
/*      */ 
/*      */   private float a(float paramFloat)
/*      */   {
/* 1571 */     if (("bounce".equalsIgnoreCase(this.y)) && 
/* 1572 */       (0.0F != paramFloat)) {
/* 1573 */       int i1 = paramFloat < 0.0F ? 1 : 0;
/*      */ 
/* 1576 */       paramFloat = new BigDecimal(paramFloat).multiply(new BigDecimal(Math.abs(this.i - b(this.n))).divide(new BigDecimal(this.i - this.h), 4, 4)).intValue();
/*      */ 
/* 1579 */       paramFloat = (int)(paramFloat * 1.5F);
/* 1580 */       return i1 != 0 ? Math.min(paramFloat, -2.0F) : Math.max(paramFloat, 2.0F);
/*      */     }
/*      */ 
/* 1583 */     return paramFloat;
/*      */   }
/*      */   private String e(String paramString) {
/* 1586 */     String str = null;
/* 1587 */     if ("left".equals(paramString))
/* 1588 */       str = "right";
/* 1589 */     else if ("right".equals(paramString)) {
/* 1590 */       str = "left";
/*      */     }
/* 1592 */     return str;
/*      */   }
/*      */   private int a(View paramView) {
/* 1595 */     if (null != paramView) {
/* 1596 */       if ((paramView instanceof INativeView)) {
/* 1597 */         return ((INativeView)paramView).getStyleWidth();
/*      */       }
/* 1599 */       return paramView.getWidth();
/*      */     }
/*      */ 
/* 1602 */     return 0;
/*      */   }
/*      */ 
/*      */   private int b(View paramView)
/*      */   {
/* 1610 */     if (null != paramView) {
/* 1611 */       if ((paramView instanceof INativeView)) {
/* 1612 */         return ((INativeView)paramView).getStyleLeft();
/*      */       }
/* 1614 */       if ((paramView.getLayoutParams() instanceof AbsoluteLayout.LayoutParams))
/* 1615 */         return ((AbsoluteLayout.LayoutParams)paramView.getLayoutParams()).x;
/* 1616 */       if ((paramView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
/* 1617 */         return (int)ViewHelper.getX(paramView);
/*      */       }
/*      */     }
/* 1620 */     return 0;
/*      */   }
/*      */ 
/*      */   private void b(View paramView, int paramInt)
/*      */   {
/* 1628 */     if (null != paramView)
/* 1629 */       if ((paramView instanceof INativeView)) {
/* 1630 */         ((INativeView)paramView).setStyleLeft(paramInt);
/*      */       }
/* 1632 */       else if ((paramView.getLayoutParams() instanceof AbsoluteLayout.LayoutParams)) {
/* 1633 */         AbsoluteLayout.LayoutParams localLayoutParams = (AbsoluteLayout.LayoutParams)paramView.getLayoutParams();
/* 1634 */         localLayoutParams.height = paramView.getHeight();
/* 1635 */         localLayoutParams.width = paramView.getWidth();
/* 1636 */         localLayoutParams.x = paramInt;
/* 1637 */         paramView.requestLayout();
/* 1638 */       } else if ((paramView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
/* 1639 */         ViewHelper.setX(paramView, paramInt);
/*      */       }
/*      */   }
/*      */ 
/*      */   private boolean a(d paramd)
/*      */   {
/* 1649 */     if ((null == paramd) || ((null != paramd) && (null == paramd.obtainWebView())) || ((null != paramd) && (null == paramd.obtainMainView())) || ((null != paramd) && (null == paramd.obtainWebView()) && (null == paramd.obtainMainView())))
/*      */     {
/* 1651 */       return true;
/*      */     }
/* 1653 */     return false;
/*      */   }
/*      */ 
/*      */   private boolean g() {
/* 1657 */     boolean bool1 = h();
/* 1658 */     boolean bool2 = i();
/* 1659 */     if ((!bool1) || (!bool2)) {
/* 1660 */       return false;
/*      */     }
/* 1662 */     return true;
/*      */   }
/*      */   private boolean h() {
/* 1665 */     this.h = b(this.n);
/*      */     Rect localRect1;
/*      */     Rect localRect2;
/* 1666 */     if ("right".equals(this.G)) {
/* 1667 */       if (2147483647 != this.q) {
/* 1668 */         this.i = this.q;
/* 1669 */         if (this.h == this.i)
/* 1670 */           return false;
/*      */       }
/*      */       else {
/* 1673 */         this.i = this.J;
/* 1674 */         if (null != this.o) {
/* 1675 */           if ("bounce".equalsIgnoreCase(this.y)) {
/* 1676 */             this.i = (this.h + a(this.n) / 2);
/*      */           }
/* 1678 */           else if (a(this.o) < this.J) {
/* 1679 */             localRect1 = new Rect();
/* 1680 */             this.o.getGlobalVisibleRect(localRect1);
/* 1681 */             localRect2 = new Rect();
/* 1682 */             this.n.getGlobalVisibleRect(localRect2);
/* 1683 */             if ((0 == localRect1.left) && (localRect1.right == localRect2.left)) {
/* 1684 */               return false;
/*      */             }
/* 1686 */             if ((!this.B) && (0 == localRect1.left)) {
/* 1687 */               this.i = a(this.o);
/*      */             }
/* 1689 */             else if ((this.B) && (0 == localRect1.right)) {
/* 1690 */               this.i = a(this.o);
/*      */             }
/* 1692 */             else if (this.J == localRect1.right)
/* 1693 */               this.i = 0;
/*      */           }
/*      */         }
/* 1696 */         else if ((null == this.o) && (this.h < 0)) {
/* 1697 */           this.i = 0;
/*      */         }
/*      */       }
/*      */     }
/* 1701 */     else if ("left".equals(this.G)) {
/* 1702 */       if (2147483647 != this.q) {
/* 1703 */         this.i = this.q;
/* 1704 */         if (this.h == this.i)
/* 1705 */           return false;
/*      */       }
/*      */       else {
/* 1708 */         this.i = (-a(this.n));
/* 1709 */         if (null != this.o) {
/* 1710 */           if ("bounce".equalsIgnoreCase(this.y)) {
/* 1711 */             this.i = (this.h - a(this.n) / 2);
/* 1712 */           } else if (a(this.o) < this.J) {
/* 1713 */             localRect1 = new Rect();
/* 1714 */             this.o.getGlobalVisibleRect(localRect1);
/* 1715 */             localRect2 = new Rect();
/* 1716 */             this.n.getGlobalVisibleRect(localRect2);
/* 1717 */             if ((this.J == localRect1.right) && (localRect1.left == localRect2.right)) {
/* 1718 */               return false;
/*      */             }
/* 1720 */             if ((!this.B) && (this.J == localRect1.right)) {
/* 1721 */               this.i = (-a(this.o));
/*      */             }
/* 1723 */             else if ((this.B) && (this.J == localRect1.left)) {
/* 1724 */               this.i = (-a(this.o));
/*      */             }
/* 1726 */             else if (0 == localRect1.left)
/* 1727 */               this.i = 0;
/*      */           }
/*      */         }
/* 1730 */         else if ((null == this.o) && (this.h > 0)) {
/* 1731 */           this.i = 0;
/*      */         }
/*      */       }
/*      */     }
/* 1735 */     return true;
/*      */   }
/*      */   private boolean i() {
/* 1738 */     this.j = b(this.o);
/* 1739 */     if ("right".equals(this.G)) {
/* 1740 */       if (2147483647 != this.u) {
/* 1741 */         this.k = this.u;
/* 1742 */         if (this.j == this.k)
/* 1743 */           return false;
/*      */       }
/*      */       else {
/* 1746 */         this.k = a(this.o);
/* 1747 */         if ((this.j == 0) || (this.j == this.J)) {
/* 1748 */           return false;
/*      */         }
/* 1750 */         if ("bounce".equalsIgnoreCase(this.y)) {
/* 1751 */           this.k = (this.j + a(this.n) / 2);
/*      */         }
/* 1753 */         else if (this.j < 0) {
/* 1754 */           this.k = 0;
/*      */         }
/*      */       }
/*      */     }
/* 1758 */     else if ("left".equals(this.G)) {
/* 1759 */       if (2147483647 != this.u) {
/* 1760 */         this.k = this.u;
/* 1761 */         if (this.j == this.k)
/* 1762 */           return false;
/*      */       }
/*      */       else {
/* 1765 */         int i1 = a(this.o);
/* 1766 */         this.k = (-i1);
/* 1767 */         if ("bounce".equalsIgnoreCase(this.y)) {
/* 1768 */           this.k = (this.j - a(this.n) / 2);
/*      */         }
/* 1770 */         else if (i1 < this.J) {
/* 1771 */           int i2 = b(this.o) + a(this.o);
/* 1772 */           if ((i2 == this.J) || (i2 == 0)) {
/* 1773 */             return false;
/*      */           }
/* 1775 */           Rect localRect = new Rect();
/* 1776 */           this.o.getGlobalVisibleRect(localRect);
/* 1777 */           if (this.J == localRect.left)
/* 1778 */             this.k = (this.J - i1);
/* 1779 */           else if (0 == localRect.right) {
/* 1780 */             this.k = 0;
/*      */           }
/*      */         }
/* 1783 */         else if ((i1 == this.J) && (this.j > 0)) {
/* 1784 */           this.k = 0;
/*      */         }
/*      */       }
/*      */     }
/* 1788 */     return true;
/*      */   }
/*      */   private boolean j() {
/* 1791 */     if ("right".equals(this.G)) {
/* 1792 */       if (b(this.o) == 0) {
/* 1793 */         return false;
/*      */       }
/*      */     }
/* 1796 */     else if (("left".equals(this.G)) && 
/* 1797 */       (b(this.o) + a(this.o) == this.J)) {
/* 1798 */       return false;
/*      */     }
/*      */ 
/* 1801 */     return true;
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.h
 * JD-Core Version:    0.6.2
 */