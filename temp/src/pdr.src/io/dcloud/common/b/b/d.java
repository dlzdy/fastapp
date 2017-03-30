/*     */ package io.dcloud.common.b.b;
/*     */ 
/*     */ import android.annotation.TargetApi;
/*     */ import android.content.Context;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import com.dcloud.android.widget.AbsoluteLayout;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.ICallBack;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameView;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameView.OnAnimationEnd;
/*     */ import io.dcloud.common.adapter.ui.AdaWebViewParent;
/*     */ import io.dcloud.common.adapter.util.AnimOptions;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import io.dcloud.nineoldandroids.animation.Animator;
/*     */ import io.dcloud.nineoldandroids.animation.Animator.AnimatorListener;
/*     */ import io.dcloud.nineoldandroids.view.ViewHelper;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Stack;
/*     */ 
/*     */ class d extends AdaFrameView
/*     */ {
/*  48 */   boolean a = false;
/*     */   ArrayList<d> b;
/*     */   ArrayList<d> c;
/*  54 */   boolean d = false;
/*     */ 
/*  56 */   private boolean s = true;
/*  57 */   private boolean t = false;
/*     */ 
/*  59 */   boolean e = false;
/*     */ 
/*  61 */   boolean f = true;
/*     */ 
/*  63 */   boolean g = false;
/*     */ 
/*  65 */   boolean h = false;
/*  66 */   public static int i = 0;
/*  67 */   l j = null;
/*  68 */   IApp k = null;
/*  69 */   c l = null;
/*  70 */   f m = null;
/*  71 */   AdaWebViewParent n = null;
/*     */ 
/*  73 */   byte o = 2;
/*     */ 
/*  77 */   private boolean u = true;
/*     */ 
/*  81 */   ViewOptions p = null;
/*     */ 
/* 313 */   Animator.AnimatorListener q = new Animator.AnimatorListener()
/*     */   {
/*     */     public void onAnimationEnd(Animator paramAnonymousAnimator) {
/* 316 */       Logger.e("DHFrameView", "---------------------onAnimationEnd");
/* 317 */       AnimOptions localAnimOptions = d.this.getAnimOptions();
/* 318 */       switch (localAnimOptions.mOption) {
/*     */       case 3:
/* 320 */         d.this.k();
/* 321 */         break;
/*     */       case 4:
/* 323 */         break;
/*     */       case 1:
/* 325 */         break;
/*     */       case 2:
/* 327 */         break;
/*     */       case 0:
/*     */       }
/*     */ 
/* 332 */       io.dcloud.common.util.BaseInfo.sDoingAnimation = false;
/* 333 */       if (d.this.obtainMainView() == null) {
/* 334 */         return;
/*     */       }
/* 336 */       d.this.obtainMainView().post(new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 340 */           d.2.a(d.2.this);
/*     */         }
/*     */       });
/*     */     }
/*     */ 
/*     */     private void a() {
/* 346 */       AnimOptions localAnimOptions = d.this.getAnimOptions();
/* 347 */       switch (localAnimOptions.mOption) {
/*     */       case 3:
/* 349 */         d.this.l();
/* 350 */         break;
/*     */       case 4:
/* 352 */         if (j.a != null) {
/* 353 */           j.a.clearAnimation();
/*     */         }
/* 355 */         d.this.j();
/* 356 */         break;
/*     */       case 1:
/* 358 */         d.this.g();
/* 359 */         break;
/*     */       case 2:
/* 361 */         d.this.i();
/* 362 */         break;
/*     */       case 0:
/* 364 */         if (j.a != null) {
/* 365 */           j.a.clearAnimation();
/*     */         }
/* 367 */         d.this.h();
/*     */       }
/*     */     }
/*     */ 
/*     */     public void onAnimationCancel(Animator paramAnonymousAnimator)
/*     */     {
/* 377 */       io.dcloud.common.util.BaseInfo.sDoingAnimation = false;
/*     */     }
/*     */     public void onAnimationRepeat(Animator paramAnonymousAnimator) {
/*     */     }
/*     */ 
/*     */     public void onAnimationStart(Animator paramAnonymousAnimator) {
/* 383 */       Logger.e("DHFrameView", "---------------------onAnimationStart");
/* 384 */       io.dcloud.common.util.BaseInfo.sDoingAnimation = true;
/* 385 */       d.a(d.this, true);
/* 386 */       if (d.this.getAnimOptions().mOption == 2)
/* 387 */         d.a(d.this.obtainMainView(), d.a(d.this).left, d.b(d.this).top, "onAnimationStart");
/*     */     }
/* 313 */   };
/*     */ 
/* 518 */   boolean r = false;
/*     */ 
/*     */   d(Context paramContext, l paraml, IApp paramIApp, c paramc, int paramInt, Object paramObject)
/*     */   {
/*  86 */     super(paramContext, paramInt, paramObject);
/*  87 */     this.lastShowTime = System.currentTimeMillis();
/*  88 */     i += 1;
/*  89 */     Logger.i("dhframeview", "construction Count=" + i);
/*  90 */     this.j = paraml;
/*  91 */     this.k = paramIApp;
/*  92 */     this.l = paramc;
/*  93 */     this.l.c().add(this);
/*  94 */     this.s = paramIApp.isVerticalScreen();
/*  95 */     this.t = paramIApp.isFullScreen();
/*     */   }
/*     */ 
/*     */   protected void initMainView(Context paramContext, int paramInt, Object paramObject)
/*     */   {
/* 100 */     if (paramInt != 1)
/*     */     {
/* 107 */       AbsoluteLayout localAbsoluteLayout = new AbsoluteLayout(paramContext, this, this.k);
/* 108 */       setMainView(localAbsoluteLayout);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setNeedRender(boolean paramBoolean) {
/* 113 */     this.a = paramBoolean;
/*     */   }
/*     */   public AbsMgr obtainWindowMgr() {
/* 116 */     return this.j;
/*     */   }
/*     */ 
/*     */   @TargetApi(11)
/*     */   private void a(View paramView)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void startAnimator(int paramInt)
/*     */   {
/* 143 */     chkUseCaptureAnimation(false, hashCode(), this.mSnapshot != null);
/*     */ 
/* 148 */     this.m.setScrollIndicator("none");
/* 149 */     final int i1 = this.m.mChildArrayList.size();
/* 150 */     if (i1 != 0) {
/* 151 */       for (int i2 = 0; i2 < i1; i2++) {
/* 152 */         if ((this.m.mChildArrayList.get(i2) instanceof d)) {
/* 153 */           d locald = (d)this.m.mChildArrayList.get(i2);
/* 154 */           locald.m.setScrollIndicator("none");
/* 155 */           a(locald.m.getWebView());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 160 */     super.startAnimator(new AdaFrameView.OnAnimationEnd()
/*     */     {
/*     */       public void onDone() {
/* 163 */         d.this.obtainMainView().postDelayed(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*     */             try {
/* 168 */               if (d.this.p != null) {
/* 169 */                 d.this.m.setScrollIndicator(d.this.p.getScrollIndicator());
/*     */               }
/*     */ 
/* 172 */               for (int i = 0; i < d.1.this.a; i++)
/* 173 */                 if ((d.this.m.mChildArrayList.get(i) instanceof d)) {
/* 174 */                   d locald = (d)d.this.m.mChildArrayList.get(i);
/* 175 */                   locald.m.setScrollIndicator(locald.p.getScrollIndicator());
/*     */                 }
/*     */             }
/*     */             catch (Exception localException)
/*     */             {
/*     */             }
/*     */           }
/*     */         }
/*     */         , 128L);
/*     */       }
/*     */     }
/*     */     , paramInt);
/*     */   }
/*     */ 
/*     */   public void onInit()
/*     */   {
/* 193 */     super.onInit();
/*     */   }
/*     */ 
/*     */   boolean a() {
/* 197 */     return (this.b != null) && (this.b.size() > 0);
/*     */   }
/*     */ 
/*     */   boolean b() {
/* 201 */     return (this.c != null) && (this.c.size() > 0);
/*     */   }
/*     */ 
/*     */   public void onPreLoading() {
/* 205 */     super.onPreLoading();
/* 206 */     if (this.o == 0)
/* 207 */       c();
/*     */   }
/*     */ 
/*     */   public void transition(byte paramByte)
/*     */   {
/* 212 */     if ((this.o == paramByte) && (paramByte == 2))
/* 213 */       c();
/*     */   }
/*     */ 
/*     */   void c()
/*     */   {
/* 222 */     f();
/*     */   }
/*     */ 
/*     */   public void onLoading()
/*     */   {
/* 230 */     super.onLoading();
/*     */   }
/*     */ 
/*     */   boolean d()
/*     */   {
/* 238 */     AnimOptions localAnimOptions = getAnimOptions();
/* 239 */     if (localAnimOptions != null) {
/* 240 */       int i1 = localAnimOptions.mOption;
/* 241 */       return i1 != 1;
/*     */     }
/* 243 */     return false;
/*     */   }
/*     */ 
/*     */   public void onPopFromStack(boolean paramBoolean)
/*     */   {
/* 249 */     super.onPopFromStack(paramBoolean);
/* 250 */     if (this.k != null) {
/* 251 */       this.s = this.k.isVerticalScreen();
/* 252 */       this.t = this.k.isFullScreen();
/* 253 */       Logger.d("Animation_Path", "onPopFromStack " + (this.s ? "竖屏出栈" : "横屏出栈") + this);
/*     */     } else {
/* 255 */       Logger.d("Animation_Path", "已经提前出栈了 " + (this.s ? "竖屏出栈" : "横屏出栈") + this);
/*     */     }
/*     */   }
/*     */ 
/*     */   void e()
/*     */   {
/* 263 */     if ((this.s != this.k.isVerticalScreen()) || (this.t != this.k.isFullScreen())) {
/* 264 */       Logger.d("Animation_Path", "onPushToStack frame " + (this.s ? "调整为横屏状态" : "调整为竖屏状态") + this);
/* 265 */       resize();
/*     */ 
/* 267 */       this.s = this.k.isVerticalScreen();
/* 268 */       this.t = this.k.isFullScreen();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onPreShow(IFrameView paramIFrameView) {
/* 273 */     super.onPreShow(paramIFrameView);
/*     */   }
/*     */ 
/*     */   public void onDestroy()
/*     */   {
/* 278 */     super.onDestroy();
/* 279 */     i -= 1;
/* 280 */     Logger.i("dhframeview", "onDestroy Count=" + i);
/*     */   }
/*     */ 
/*     */   public IApp obtainApp()
/*     */   {
/* 285 */     return this.k;
/*     */   }
/*     */ 
/*     */   void f() {
/* 289 */     setAnimatorLinstener(this.q);
/*     */   }
/*     */ 
/*     */   void a(ViewOptions paramViewOptions, AdaFrameItem paramAdaFrameItem1, AdaFrameItem paramAdaFrameItem2, AdaFrameItem paramAdaFrameItem3)
/*     */   {
/* 294 */     if ((DeviceInfo.sDeviceSdkVer >= 11) && 
/* 295 */       (paramViewOptions.opacity != -1.0F)) {
/* 296 */       paramAdaFrameItem1.obtainMainView().setAlpha(paramViewOptions.opacity);
/*     */     }
/*     */ 
/* 299 */     if (paramViewOptions.hasBackground()) {
/* 300 */       paramAdaFrameItem1.setBgcolor(paramViewOptions.background);
/* 301 */     } else if (paramViewOptions.isTransparent()) {
/* 302 */       paramAdaFrameItem2.setBgcolor(0);
/* 303 */       paramAdaFrameItem3.setBgcolor(0);
/* 304 */       paramAdaFrameItem1.setBgcolor(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   void b(ViewOptions paramViewOptions, AdaFrameItem paramAdaFrameItem1, AdaFrameItem paramAdaFrameItem2, AdaFrameItem paramAdaFrameItem3) {
/* 309 */     this.p = paramViewOptions;
/* 310 */     ((IWebview)paramAdaFrameItem3).setScrollIndicator(paramViewOptions.getScrollIndicator());
/* 311 */     a(paramViewOptions, paramAdaFrameItem1, paramAdaFrameItem2, paramAdaFrameItem3);
/*     */   }
/*     */ 
/*     */   static void a(View paramView, int paramInt1, int paramInt2, String paramString)
/*     */   {
/* 430 */     if (DeviceInfo.sDeviceSdkVer <= 10)
/* 431 */       paramView.layout(paramInt1, paramInt2, paramView.getRight() + paramInt1, paramView.getBottom() + paramInt2);
/*     */   }
/*     */ 
/*     */   void g()
/*     */   {
/* 443 */     setVisibility(GONE);
/* 444 */     n();
/* 445 */     o();
/* 446 */     q();
/* 447 */     clearAnimInfo();
/* 448 */     Logger.d("Animation_Path", "onCloseAnimationEnd;" + this);
/*     */   }
/*     */   void h() {
/* 451 */     dispatchFrameViewEvents("show_animation_end", null);
/* 452 */     if (this.mViewOptions_animate != null) {
/* 453 */       updateFrameRelViewRect(this.mViewOptions_animate);
/* 454 */       this.mViewOptions_animate = null;
/*     */     }
/*     */ 
/* 457 */     n();
/* 458 */     this.f = true;
/* 459 */     q();
/* 460 */     clearAnimInfo();
/* 461 */     Logger.d("Animation_Path", "onShowAnimationEnd;" + this);
/*     */   }
/*     */   void i() {
/* 464 */     Object localObject = this;
/* 465 */     boolean bool = obtainFrameOptions().hasBackground();
/* 466 */     if (bool) {
/* 467 */       localObject = obtainWebviewParent();
/*     */     }
/* 469 */     ViewOptions localViewOptions = ((AdaFrameItem)localObject).obtainFrameOptions_Animate();
/* 470 */     if (localViewOptions != null) {
/* 471 */       updateFrameRelViewRect(localViewOptions);
/* 472 */       ((AdaFrameItem)localObject).setFrameOptions_Animate(null);
/*     */     }
/* 474 */     a(obtainMainView(), this.mViewOptions.left, this.mViewOptions.top, "onStyleChangedAnimationEnd");
/* 475 */     n();
/* 476 */     q();
/* 477 */     clearAnimInfo();
/* 478 */     Logger.d("Animation_Path", "onStyleChangedAnimationEnd;" + localObject.toString());
/*     */   }
/*     */   void j() {
/* 481 */     dispatchFrameViewEvents("show_animation_end", null);
/* 482 */     if (this.mViewOptions_animate != null) {
/* 483 */       updateFrameRelViewRect(this.mViewOptions_animate);
/* 484 */       this.mViewOptions_animate = null;
/*     */     }
/* 486 */     a(obtainMainView(), this.mViewOptions.left, this.mViewOptions.top, "onHideShowAnimationEnd");
/* 487 */     n();
/* 488 */     this.f = true;
/* 489 */     q();
/* 490 */     clearAnimInfo();
/* 491 */     Logger.d("Animation_Path", "onHideShowAnimationEnd;" + toString());
/*     */   }
/*     */   void k() {
/* 494 */     setVisible(false, true);
/*     */   }
/*     */ 
/*     */   void l() {
/* 498 */     dispatchFrameViewEvents("hide", null);
/* 499 */     if ((!this.mViewOptions.hasBackground()) && (!this.isChildOfFrameView)) {
/* 500 */       ViewHelper.setX(obtainMainView(), this.mViewOptions.left);
/* 501 */       ViewHelper.setY(obtainMainView(), this.mViewOptions.top);
/* 502 */       ViewHelper.setScaleX(obtainMainView(), 1.0F);
/* 503 */       ViewHelper.setScaleY(obtainMainView(), 1.0F);
/* 504 */       if (!this.mViewOptions.hasTransparentValue()) {
/* 505 */         ViewHelper.setAlpha(obtainMainView(), 1.0F);
/*     */       }
/*     */     }
/* 508 */     if (this.mViewOptions_animate != null) {
/* 509 */       updateFrameRelViewRect(this.mViewOptions_animate);
/* 510 */       this.mViewOptions_animate = null;
/*     */     }
/* 512 */     n();
/* 513 */     this.g = false;
/* 514 */     q();
/* 515 */     clearAnimInfo();
/* 516 */     Logger.d("Animation_Path", "onHideAnimationEnd;" + toString());
/*     */   }
/*     */ 
/*     */   public void m()
/*     */   {
/* 523 */     this.r = true;
/*     */ 
/* 540 */     Logger.d("Animation_Path", "onWillDoAnimation " + this);
/* 541 */     if (this.l != null) {
/* 542 */       this.l.g.a(this);
/*     */     }
/* 544 */     if (this.d) {
/* 545 */       ViewOptions localViewOptions = obtainFrameOptions_Animate();
/* 546 */       this.mViewOptions.opacity = localViewOptions.opacity;
/* 547 */       this.mViewOptions.background = localViewOptions.background;
/* 548 */       this.mViewOptions.strBackground = localViewOptions.strBackground;
/* 549 */       a(this.mViewOptions, this, obtainWebviewParent(), (AdaFrameItem)obtainWebView());
/*     */     }
/*     */   }
/*     */ 
/* 553 */   public void n() { this.r = false;
/*     */ 
/* 572 */     this.mAnimationStarted = true;
/* 573 */     Logger.d("Animation_Path", "onDoneAnimation " + this);
/* 574 */     if (this.l != null) {
/* 575 */       this.b = null;
/* 576 */       if (this.l.g.a() == 1)
/*     */       {
/* 579 */         this.l.d(this);
/*     */ 
/* 581 */         if (!this.isChildOfFrameView) {
/* 582 */           if (a()) {
/* 583 */             Logger.d("Animation_Path", "on_Done_Animation 动画完后存在窗口入栈；" + this);
/* 584 */             this.j.processEvent(IMgr.MgrType.WindowMgr, 28, this.b);
/*     */           }
/* 586 */           if (b()) {
/* 587 */             Logger.d("Animation_Path", "on_Done_Animation 动画完后存在窗口出栈；" + this);
/* 588 */             a(this.c);
/*     */           }
/* 590 */           else if (this.l.a != null) {
/* 591 */             this.l.a.onCallBack(-1, null);
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/* 596 */       this.l.g.b(this);
/* 597 */       this.c = null;
/*     */     } }
/*     */ 
/*     */   private void q() {
/* 601 */     this.b = null;
/* 602 */     this.c = null;
/* 603 */     this.d = false;
/*     */   }
/*     */ 
/*     */   private void a(final ArrayList<d> paramArrayList) {
/* 607 */     Logger.d("Animation_Path", "removeFrameViewFromViewStack DoAnimation Frame=" + this + ";Will PopFrames=" + paramArrayList);
/* 608 */     MessageHandler.sendMessage(new MessageHandler.IMessages()
/*     */     {
/*     */       public void execute(Object paramAnonymousObject) {
/* 611 */         d.this.j.processEvent(IMgr.MgrType.WindowMgr, 27, paramArrayList);
/* 612 */         if (d.this.l.a != null)
/* 613 */           d.this.l.a.onCallBack(-1, null);
/*     */       }
/*     */     }
/*     */     , null);
/*     */   }
/*     */ 
/*     */   public IWebview obtainWebView()
/*     */   {
/* 620 */     return this.m;
/*     */   }
/*     */ 
/*     */   public AdaWebViewParent obtainWebviewParent()
/*     */   {
/* 625 */     return this.n;
/*     */   }
/*     */ 
/*     */   void o() {
/* 629 */     if (this.l != null)
/* 630 */       this.l.b(this);
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 634 */     return this.m != null ? this.m.toString() : super.toString();
/*     */   }
/*     */ 
/*     */   public String obtainPrePlusreadyJs() {
/* 638 */     if (this.j != null) {
/* 639 */       return (String)this.j.processEvent(IMgr.MgrType.FeatureMgr, 2, new Object[] { this.k, this });
/*     */     }
/* 641 */     return "";
/*     */   }
/*     */   public void a(int paramInt1, int paramInt2) {
/* 644 */     ViewGroup.LayoutParams localLayoutParams = obtainMainView().getLayoutParams();
/* 645 */     if (localLayoutParams == null) {
/* 646 */       localLayoutParams = new ViewGroup.LayoutParams(paramInt1, paramInt2);
/* 647 */       obtainMainView().setLayoutParams(localLayoutParams);
/*     */     } else {
/* 649 */       localLayoutParams.width = paramInt1;
/* 650 */       localLayoutParams.height = paramInt2;
/*     */     }
/*     */   }
/*     */ 
/*     */   public AdaFrameItem getParent()
/*     */   {
/* 656 */     return this.l;
/*     */   }
/*     */   public void p() {
/* 659 */     if (this.l != null)
/* 660 */       this.l.c().remove(this);
/*     */   }
/*     */ 
/*     */   public IWebAppRootView obtainWebAppRootView()
/*     */   {
/* 665 */     return this.l;
/*     */   }
/*     */ 
/*     */   public void dispose() {
/* 669 */     super.dispose();
/* 670 */     if (this.l != null) {
/* 671 */       this.l.b().remove(this);
/* 672 */       p();
/*     */     }
/* 674 */     this.j = null;
/* 675 */     this.k = null;
/* 676 */     this.mParentFrameItem = null;
/* 677 */     this.l = null;
/* 678 */     this.m = null;
/* 679 */     this.q = null;
/*     */   }
/*     */ 
/*     */   public void onConfigurationChanged()
/*     */   {
/* 685 */     super.onConfigurationChanged();
/* 686 */     resize();
/* 687 */     this.s = this.k.isVerticalScreen();
/* 688 */     this.t = this.k.isFullScreen();
/* 689 */     Logger.d("Android_System_Path", new Object[] { "onConfigurationChanged", this });
/*     */   }
/*     */ 
/*     */   public void pushToViewStack()
/*     */   {
/* 695 */     if ((!this.isChildOfFrameView) && (!this.f)) {
/* 696 */       ArrayList localArrayList = new ArrayList();
/* 697 */       localArrayList.add(this);
/* 698 */       this.j.processEvent(IMgr.MgrType.WindowMgr, 28, localArrayList);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void popFromViewStack()
/*     */   {
/* 704 */     if ((!this.isChildOfFrameView) && (this.f)) {
/* 705 */       ArrayList localArrayList = new ArrayList();
/* 706 */       localArrayList.add(this);
/* 707 */       this.j.processEvent(IMgr.MgrType.WindowMgr, 27, localArrayList);
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.d
 * JD-Core Version:    0.6.2
 */