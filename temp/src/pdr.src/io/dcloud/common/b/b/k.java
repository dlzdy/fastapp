/*     */ package io.dcloud.common.b.b;
/*     */ 
/*     */ import android.annotation.TargetApi;
/*     */ import android.content.Context;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Bitmap.Config;
/*     */ import android.graphics.Rect;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.VelocityTracker;
/*     */ import android.view.View;
/*     */ import android.view.ViewConfiguration;
/*     */ import android.view.animation.AccelerateDecelerateInterpolator;
/*     */ import android.view.animation.Animation;
/*     */ import android.view.animation.Animation.AnimationListener;
/*     */ import android.view.animation.TranslateAnimation;
/*     */ import android.webkit.WebView;
/*     */ import android.widget.FrameLayout;
/*     */ import android.widget.FrameLayout.LayoutParams;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.RelativeLayout.LayoutParams;
/*     */ import io.dcloud.RInformation;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameView;
/*     */ import io.dcloud.common.adapter.ui.FrameSwitchView;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class k extends FrameLayout
/*     */ {
/*     */   e h;
/*     */   e i;
/*  40 */   private boolean a = true;
/*     */   private Context b;
/*     */   private VelocityTracker c;
/*     */   private int d;
/*     */   private float e;
/*     */   private float f;
/*  46 */   c j = null;
/*  47 */   private int g = 70;
/*     */   private float k;
/*     */   private int l;
/*     */   private RelativeLayout m;
/*     */   private int n;
/*     */   private d o;
/*  54 */   private final int p = 1;
/*  55 */   private final int q = 2;
/*  56 */   private int r = 1;
/*     */ 
/*     */   public k(Context paramContext, c paramc) {
/*  59 */     super(paramContext);
/*  60 */     this.j = paramc;
/*  61 */     a(paramContext);
/*     */   }
/*     */ 
/*     */   @TargetApi(11)
/*     */   public void a(Context paramContext) {
/*  66 */     this.b = paramContext;
/*  67 */     this.d = ViewConfiguration.get(getContext()).getScaledTouchSlop();
/*  68 */     this.h = new e(paramContext);
/*  69 */     this.i = new e(paramContext);
/*  70 */     addView(this.h, new FrameLayout.LayoutParams(-1, -1));
/*  71 */     addView(this.i, new FrameLayout.LayoutParams(-1, -1));
/*  72 */     this.m = new RelativeLayout(paramContext);
/*  73 */     this.m.setTag("shade");
/*  74 */     RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
/*  75 */     this.m.setLayoutParams(localLayoutParams1);
/*  76 */     this.m.setAlpha(0.5F);
/*  77 */     View localView = new View(this.b);
/*  78 */     this.n = 33;
/*  79 */     localView.setBackgroundResource(RInformation.DRAWEBL_SHADOW_LEFT);
/*  80 */     RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(this.n, -1);
/*  81 */     this.m.addView(localView, localLayoutParams2);
/*  82 */     addView(this.m, localLayoutParams1);
/*  83 */     this.m.setVisibility(8);
/*  84 */     this.i.setVisibility(8);
/*  85 */     this.h.setVisibility(8);
/*     */   }
/*     */ 
/*     */   public void a() {
/*  89 */     if ((this.m != null) && (this.m.getParent() == null)) {
/*  90 */       RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
/*  91 */       addView(this.m, localLayoutParams);
/*     */     }
/*  93 */     if ((this.h != null) && (this.h.getParent() == null)) {
/*  94 */       addView(this.h, new FrameLayout.LayoutParams(-1, -1));
/*     */     }
/*  96 */     if ((this.i != null) && (this.i.getParent() == null))
/*  97 */       addView(this.i, new FrameLayout.LayoutParams(-1, -1));
/*     */   }
/*     */ 
/*     */   public e b()
/*     */   {
/* 102 */     return this.h;
/*     */   }
/*     */ 
/*     */   public e c() {
/* 106 */     return this.i;
/*     */   }
/*     */ 
/*     */   public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
/*     */   {
/* 111 */     int i1 = paramMotionEvent.getActionMasked();
/* 112 */     float f1 = paramMotionEvent.getX();
/* 113 */     float f2 = paramMotionEvent.getY();
/*     */ 
/* 119 */     switch (i1) {
/*     */     case 0:
/* 121 */       this.e = f1;
/* 122 */       this.f = f2;
/* 123 */       this.k = f1;
/* 124 */       this.a = false;
/* 125 */       this.o = this.j.a();
/* 126 */       break;
/*     */     case 2:
/* 129 */       if ((this.k > this.g) || (this.o == null)) {
/* 130 */         return false;
/*     */       }
/* 132 */       float f3 = f1 - this.e;
/* 133 */       float f4 = Math.abs(f3);
/* 134 */       float f5 = Math.abs(f2 - this.f);
/* 135 */       if ((f4 > this.d) && (f4 > f5))
/*     */       {
/* 137 */         float f6 = this.o.obtainMainView().getScrollX();
/* 138 */         if (f6 < 0.0F) {
/* 139 */           if (a(this.o)) {
/* 140 */             this.a = true;
/* 141 */             this.e = f1;
/*     */           }
/*     */         }
/* 144 */         else if ((f3 > 0.0F) && 
/* 145 */           (a(this.o))) {
/* 146 */           this.a = true;
/* 147 */           this.e = f1;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */       break;
/*     */     }
/*     */ 
/* 155 */     return this.a;
/*     */   }
/*     */ 
/*     */   public boolean a(d paramd) {
/* 159 */     if (paramd == null) {
/* 160 */       return false;
/*     */     }
/*     */ 
/* 163 */     if (!paramd.obtainApp().isStreamApp()) {
/* 164 */       return false;
/*     */     }
/* 166 */     Rect localRect = new Rect();
/* 167 */     paramd.obtainWebView().obtainWebview().getGlobalVisibleRect(localRect);
/* 168 */     ViewOptions localViewOptions = paramd.obtainFrameOptions();
/* 169 */     if ((localRect.left != 0) || ((localViewOptions.width != -1) && (localViewOptions.width < getWidth())) || ((localViewOptions.height != -1) && (localViewOptions.height < getHeight()))) {
/* 170 */       return false;
/*     */     }
/*     */ 
/* 173 */     if (paramd.obtainFrameOptions().popGesture.equals("none")) {
/* 174 */       return false;
/*     */     }
/* 176 */     if ((paramd.obtainWebView() != null) && (paramd.obtainWebView().canGoBack())) {
/* 177 */       this.r = 1;
/* 178 */       return true;
/*     */     }
/* 180 */     a();
/* 181 */     ArrayList localArrayList = new ArrayList();
/* 182 */     this.j.a(paramd, localArrayList);
/* 183 */     IFrameView localIFrameView = null;
/* 184 */     if (localArrayList.size() > 1)
/* 185 */       this.o.j.processEvent(IMgr.MgrType.WindowMgr, 28, localArrayList);
/* 186 */     else if (localArrayList.size() == 1) {
/* 187 */       localIFrameView = (IFrameView)localArrayList.get(0);
/*     */     }
/* 189 */     if (localArrayList.size() > 0) {
/* 190 */       if (localIFrameView != null) {
/* 191 */         localObject = PlatformUtil.captureView(localIFrameView.obtainMainView(), true, null, Bitmap.Config.RGB_565);
/* 192 */         if (localObject == null) {
/* 193 */           return false;
/*     */         }
/* 195 */         this.h.c();
/* 196 */         this.h.setImageBitmap((Bitmap)localObject);
/* 197 */         this.h.a(true);
/* 198 */         this.h.scrollTo(this.l, 0);
/* 199 */         this.h.setVisibility(0);
/* 200 */         this.h.bringToFront();
/*     */       } else {
/* 202 */         this.h.c();
/*     */       }
/*     */ 
/* 205 */       this.l = (this.o.obtainMainView().getWidth() / 3);
/* 206 */       this.m.setVisibility(0);
/* 207 */       this.m.bringToFront();
/* 208 */       this.o.setSlipping(true);
/* 209 */       this.o.obtainMainView().setVisibility(0);
/* 210 */       this.o.obtainMainView().bringToFront();
/* 211 */       this.r = 2;
/* 212 */       Object localObject = FrameSwitchView.getInstance();
/* 213 */       if (localObject != null) {
/* 214 */         ((FrameSwitchView)localObject).endRefreshView();
/*     */       }
/* 216 */       a(paramd, "start", "undefined");
/* 217 */       return true;
/*     */     }
/* 219 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean onTouchEvent(MotionEvent paramMotionEvent)
/*     */   {
/* 224 */     if (this.o == null) {
/* 225 */       return true;
/*     */     }
/* 227 */     if (this.c == null) {
/* 228 */       this.c = VelocityTracker.obtain();
/*     */     }
/* 230 */     this.c.addMovement(paramMotionEvent);
/* 231 */     int i1 = paramMotionEvent.getAction();
/* 232 */     float f1 = paramMotionEvent.getX();
/* 233 */     float f2 = paramMotionEvent.getY();
/*     */     float f3;
/* 234 */     switch (i1) {
/*     */     case 0:
/* 236 */       this.e = f1;
/* 237 */       this.f = f2;
/* 238 */       if (this.o.obtainMainView().getScrollX() == -getWidth()) {
/* 239 */         return false;
/*     */       }
/*     */ 
/*     */       break;
/*     */     case 2:
/* 244 */       f3 = this.e - f1;
/* 245 */       this.e = f1;
/* 246 */       float f4 = this.o.obtainMainView().getScrollX();
/* 247 */       float f5 = f4 + f3;
/* 248 */       if (f5 > 0.0F) f5 = 0.0F;
/* 249 */       if ((f3 < 0.0F) && (f4 < 0.0F)) {
/* 250 */         float f6 = 0.0F;
/* 251 */         float f7 = -getWidth();
/* 252 */         if (f5 > 0.0F)
/* 253 */           f5 = 0.0F;
/* 254 */         else if (f5 < f7) {
/* 255 */           f5 = f7;
/*     */         }
/*     */       }
/* 258 */       if ((this.o.obtainMainView() != null) && (this.r == 2)) {
/* 259 */         if (this.o.obtainMainView().getVisibility() != 0) {
/* 260 */           this.o.obtainMainView().setVisibility(0);
/*     */         }
/* 262 */         if (this.h.getVisibility() != 0) {
/* 263 */           this.h.setVisibility(0);
/*     */         }
/* 265 */         this.h.bringToFront();
/* 266 */         this.m.bringToFront();
/* 267 */         this.o.obtainMainView().bringToFront();
/* 268 */         this.o.obtainMainView().scrollTo((int)f5, this.o.obtainMainView().getScrollY());
/* 269 */         if (f5 < 0.0F)
/* 270 */           this.m.scrollTo((int)f5 + this.n, this.o.obtainMainView().getScrollY());
/*     */         else
/* 272 */           this.m.scrollTo((int)f5 - this.n, this.o.obtainMainView().getScrollY());
/* 273 */         this.h.scrollTo((int)(f5 / 4.0F + this.l), this.h.getScrollY()); } break;
/*     */     case 1:
/*     */     case 3:
/* 279 */       if ((this.a) && (this.r == 2)) {
/* 280 */         this.c.computeCurrentVelocity(100);
/* 281 */         f3 = this.c.getXVelocity();
/* 282 */         int i2 = this.o.obtainMainView().getScrollX();
/* 283 */         int i3 = 0;
/* 284 */         int i4 = 0;
/* 285 */         boolean bool = false;
/* 286 */         int i5 = 200;
/* 287 */         if (i2 <= 0) {
/* 288 */           if ((i2 < -getWidth() / 2) || (f3 > 50.0F)) {
/* 289 */             i3 = getWidth();
/* 290 */             i4 = 0;
/* 291 */             bool = true;
/* 292 */           } else if ((i2 >= -getWidth() / 2) || (f3 < -50.0F)) {
/* 293 */             i3 = 0;
/* 294 */             i4 = -this.l;
/*     */           }
/* 296 */           if ((f3 > 50.0F) || (f3 < -50.0F)) {
/* 297 */             i5 = 400;
/*     */           }
/*     */         }
/* 300 */         a(i3, i4, bool, i5);
/* 301 */       } else if ((this.r == 1) && 
/* 302 */         (this.o != null) && (this.o.obtainWebView().canGoBack())) {
/* 303 */         this.o.obtainWebView().goBackOrForward(-1);
/*     */       }
/*     */ 
/* 306 */       this.c.clear();
/* 307 */       this.c.recycle();
/* 308 */       this.c = null;
/*     */     }
/* 310 */     return true;
/*     */   }
/*     */ 
/*     */   void a(int paramInt1, int paramInt2, final boolean paramBoolean, int paramInt3)
/*     */   {
/* 315 */     if (this.o == null) {
/* 316 */       return;
/*     */     }
/* 318 */     a(this.o, "end", Boolean.valueOf(paramBoolean));
/* 319 */     int i1 = this.o.obtainMainView().getScrollX();
/* 320 */     int i2 = this.h.getScrollX();
/* 321 */     int i3 = this.m.getScrollX();
/* 322 */     this.o.setSlipping(false);
/* 323 */     this.o.obtainMainView().scrollTo(0, this.o.obtainMainView().getScrollY());
/* 324 */     this.o.obtainMainView().startAnimation(a(-i1, paramInt1, paramInt3, new Animation.AnimationListener() {
/*     */       public void onAnimationStart(Animation paramAnonymousAnimation) {
/*     */       }
/*     */ 
/*     */       public void onAnimationEnd(Animation paramAnonymousAnimation) {
/* 329 */         if (paramBoolean)
/* 330 */           k.a(k.this);
/*     */       }
/*     */ 
/*     */       public void onAnimationRepeat(Animation paramAnonymousAnimation)
/*     */       {
/*     */       }
/*     */     }));
/* 336 */     this.m.scrollTo(0, this.m.getScrollY());
/* 337 */     this.m.startAnimation(a(-i3, paramInt1 - this.n, paramInt3, new Animation.AnimationListener() {
/*     */       public void onAnimationStart(Animation paramAnonymousAnimation) {
/*     */       }
/*     */ 
/*     */       public void onAnimationEnd(Animation paramAnonymousAnimation) {
/* 342 */         k.b(k.this).setVisibility(8);
/*     */       }
/*     */ 
/*     */       public void onAnimationRepeat(Animation paramAnonymousAnimation)
/*     */       {
/*     */       }
/*     */     }));
/* 347 */     this.h.scrollTo(0, this.h.getScrollY());
/* 348 */     this.h.startAnimation(a(-i2, paramInt2, paramInt3, new Animation.AnimationListener() {
/*     */       public void onAnimationStart(Animation paramAnonymousAnimation) {
/*     */       }
/*     */ 
/*     */       public void onAnimationEnd(Animation paramAnonymousAnimation) {
/* 353 */         k.this.h.a(false);
/* 354 */         k.this.h.postDelayed(new Runnable()
/*     */         {
/*     */           public void run() {
/* 357 */             k.this.h.setVisibility(8);
/* 358 */             k.this.h.setImageBitmap(null);
/*     */           }
/*     */         }
/*     */         , 250L);
/*     */       }
/*     */ 
/*     */       public void onAnimationRepeat(Animation paramAnonymousAnimation)
/*     */       {
/*     */       }
/*     */     }));
/*     */   }
/*     */ 
/*     */   private TranslateAnimation a(int paramInt1, int paramInt2, int paramInt3, Animation.AnimationListener paramAnimationListener)
/*     */   {
/* 368 */     TranslateAnimation localTranslateAnimation = new TranslateAnimation(paramInt1, paramInt2, 0.0F, 0.0F);
/* 369 */     localTranslateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
/* 370 */     localTranslateAnimation.setDuration(paramInt3);
/* 371 */     if (paramAnimationListener != null) {
/* 372 */       localTranslateAnimation.setAnimationListener(paramAnimationListener);
/*     */     }
/* 374 */     return localTranslateAnimation;
/*     */   }
/*     */ 
/*     */   private void d()
/*     */   {
/* 381 */     if (this.o != null) {
/* 382 */       String str = this.o.obtainFrameOptions().popGesture;
/* 383 */       if (str.equals("hide")) {
/* 384 */         this.o.dispatchFrameViewEvents("slide_webview_hide", null);
/* 385 */         this.o.j.c(this.o);
/* 386 */       } else if (str.equals("close")) {
/* 387 */         this.o.dispatchFrameViewEvents("slide_webview_close", null);
/* 388 */         this.o.j.d(this.o);
/*     */       }
/* 390 */       this.o = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void scrollTo(int paramInt1, int paramInt2)
/*     */   {
/* 396 */     super.scrollTo(paramInt1, paramInt2);
/* 397 */     postInvalidate();
/*     */   }
/*     */ 
/*     */   public void a(AdaFrameView paramAdaFrameView, String paramString, Object paramObject)
/*     */   {
/* 415 */     Object[] arrayOfObject = { paramString, paramObject, this.o };
/* 416 */     paramAdaFrameView.dispatchFrameViewEvents("popGesture", arrayOfObject);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.k
 * JD-Core Version:    0.6.2
 */