/*     */ package io.dcloud.common.adapter.ui;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.res.Configuration;
/*     */ import android.text.TextUtils;
/*     */ import android.view.Display;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.Window;
/*     */ import android.view.WindowManager;
/*     */ import android.view.animation.AccelerateDecelerateInterpolator;
/*     */ import android.view.animation.Animation;
/*     */ import android.view.animation.Animation.AnimationListener;
/*     */ import android.view.animation.TranslateAnimation;
/*     */ import android.widget.RelativeLayout;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IReflectAble;
/*     */ import io.dcloud.common.DHInterface.IWaiter;
/*     */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class FrameSwitchView
/*     */   implements IReflectAble
/*     */ {
/*     */   private Activity mActivity;
/*     */   private SwitchLayout mSwitchLayout;
/*  33 */   private boolean isInit = false;
/*  34 */   static FrameSwitchView mInstance = null;
/*     */ 
/*  36 */   private int mDuration = 300;
/*     */   private FrameBitmapView mLeftFrameBpView;
/*     */   private FrameBitmapView mRightFrameBpView;
/*     */   private View mLeftView;
/*     */   private View mRightView;
/*  43 */   private boolean isVisibility = false;
/*     */   private static final String POP_IN = "pop-in";
/*     */   private static final String POP_OUT = "pop-out";
/*     */   private static final String SLIDE_IN_RIGHT = "slide-in-right";
/*     */   private static final String SLIDE_OUT_RIGHT = "slide-out-right";
/*  49 */   private String mAniType = "pop-in";
/*  50 */   private IWebview mWebViewImpl = null;
/*  51 */   private String mCallbackId = null;
/*     */   private int mAppScreenWidth;
/*     */   private int mAppScreenHeight;
/*  53 */   private boolean isRuning = false;
/*     */ 
/* 297 */   FrameBitmapView.ClearAnimationListener clearAnimationListener = new FrameBitmapView.ClearAnimationListener()
/*     */   {
/*     */     public void onAnimationEnd()
/*     */     {
/* 302 */       FrameSwitchView.this.endRefreshView();
/*     */     }
/* 297 */   };
/*     */ 
/*     */   public static FrameSwitchView getInstance(Activity paramActivity)
/*     */   {
/*  56 */     if (mInstance == null) {
/*  57 */       mInstance = new FrameSwitchView(paramActivity);
/*     */     }
/*  59 */     return mInstance;
/*     */   }
/*     */ 
/*     */   public static FrameSwitchView getInstance() {
/*  63 */     return mInstance;
/*     */   }
/*     */ 
/*     */   private FrameSwitchView(Activity paramActivity) {
/*  67 */     this.mActivity = paramActivity;
/*     */   }
/*     */ 
/*     */   public void initView()
/*     */   {
/*  74 */     if (!this.isInit) {
/*  75 */       this.isInit = true;
/*  76 */       ViewGroup localViewGroup = (ViewGroup)this.mActivity.getWindow().getDecorView();
/*  77 */       this.mSwitchLayout = new SwitchLayout(this.mActivity);
/*  78 */       this.mLeftFrameBpView = new FrameBitmapView(this.mActivity);
/*  79 */       this.mRightFrameBpView = new FrameBitmapView(this.mActivity);
/*  80 */       this.mSwitchLayout.addView(this.mLeftFrameBpView);
/*  81 */       this.mSwitchLayout.addView(this.mRightFrameBpView);
/*  82 */       this.mSwitchLayout.setVisibility(8);
/*  83 */       localViewGroup.addView(this.mSwitchLayout);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initScreenData() {
/*  88 */     WindowManager localWindowManager = this.mActivity.getWindowManager();
/*  89 */     int i = localWindowManager.getDefaultDisplay().getWidth();
/*  90 */     int j = localWindowManager.getDefaultDisplay().getHeight();
/*  91 */     int[] arrayOfInt = new int[2];
/*  92 */     this.mWebViewImpl.obtainFrameView().obtainWebAppRootView().obtainMainView().getLocationOnScreen(arrayOfInt);
/*  93 */     this.mAppScreenWidth = (i - arrayOfInt[0]);
/*  94 */     this.mAppScreenHeight = (j - arrayOfInt[1]);
/*  95 */     this.mSwitchLayout.setPadding(0, arrayOfInt[1], 0, 0);
/*     */   }
/*     */ 
/*     */   public boolean isInit() {
/*  99 */     return this.isInit;
/*     */   }
/*     */ 
/*     */   public void startAnimation(IWebview paramIWebview, String paramString1, Object paramObject1, String paramString2, Object paramObject2, String paramString3, String paramString4) {
/*     */     try {
/* 104 */       this.mWebViewImpl = paramIWebview;
/* 105 */       JSONObject localJSONObject = new JSONObject(paramString1);
/* 106 */       this.mAniType = localJSONObject.optString("type", "pop-in");
/* 107 */       this.mDuration = localJSONObject.optInt("duration", this.mDuration);
/* 108 */       initScreenData();
/* 109 */       if (paramObject1 != null) {
/* 110 */         if ((paramObject1 instanceof View)) {
/* 111 */           this.mLeftView = ((View)paramObject1);
/* 112 */           addView(this.mLeftView);
/*     */         } else {
/* 114 */           this.mLeftFrameBpView.injectionData(paramObject1, paramString2, this.mAppScreenWidth, this.mAppScreenHeight, paramIWebview.getScale());
/*     */         }
/*     */       }
/* 117 */       else return;
/*     */ 
/* 120 */       if (paramObject2 != null) {
/* 121 */         if ((paramObject2 instanceof View)) {
/* 122 */           this.mRightView = ((View)paramObject2);
/* 123 */           addView((View)paramObject2);
/*     */         } else {
/* 125 */           this.mRightFrameBpView.injectionData(paramObject2, paramString3, this.mAppScreenWidth, this.mAppScreenHeight, paramIWebview.getScale());
/*     */         }
/*     */       }
/* 128 */       else if (this.mAniType.equals("pop-in"))
/* 129 */         this.mAniType = "slide-in-right";
/* 130 */       else if (this.mAniType.equals("pop-out")) {
/* 131 */         this.mAniType = "slide-out-right";
/*     */       }
/*     */ 
/* 135 */       runingAnimation(paramIWebview, this.mAppScreenWidth, this.mAppScreenHeight, this.mAniType, paramString4);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addView(View paramView)
/*     */   {
/* 146 */     ViewGroup localViewGroup = (ViewGroup)paramView.getParent();
/* 147 */     if (!(localViewGroup instanceof SwitchLayout)) {
/* 148 */       if (localViewGroup != null) {
/* 149 */         localViewGroup.removeView(paramView);
/*     */       }
/* 151 */       this.mSwitchLayout.addView(paramView);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void runingAnimation(final IWebview paramIWebview, final int paramInt1, int paramInt2, String paramString1, final String paramString2)
/*     */   {
/* 164 */     initScreenData();
/* 165 */     this.isRuning = true;
/* 166 */     if (this.mSwitchLayout.getVisibility() != 0) {
/* 167 */       this.mSwitchLayout.setVisibility(0);
/*     */     }
/* 169 */     final FrameBitmapView localFrameBitmapView1 = this.mLeftView != null ? this.mLeftView : this.mLeftFrameBpView;
/* 170 */     final FrameBitmapView localFrameBitmapView2 = this.mRightView != null ? this.mRightView : this.mRightFrameBpView;
/* 171 */     localFrameBitmapView1.setVisibility(0);
/* 172 */     if (paramString1.equals("pop-in")) {
/* 173 */       localFrameBitmapView2.setVisibility(0);
/* 174 */       this.isVisibility = true;
/* 175 */       localFrameBitmapView1.startAnimation(getAnimation(0, -(paramInt1 / 6), this.mDuration, new Animation.AnimationListener() {
/*     */         public void onAnimationStart(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationRepeat(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationEnd(Animation paramAnonymousAnimation) {
/* 181 */           localFrameBitmapView1.setVisibility(8);
/* 182 */           FrameSwitchView.this.isRuning = false;
/* 183 */           JSUtil.execCallback(paramIWebview, paramString2, null, JSUtil.OK, false, false);
/*     */         }
/*     */       }));
/* 186 */       localFrameBitmapView2.startAnimation(getAnimation(paramInt1, 0, this.mDuration, new Animation.AnimationListener() {
/*     */         public void onAnimationStart(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationRepeat(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationEnd(Animation paramAnonymousAnimation) {
/* 192 */           FrameSwitchView.this.endAnimationLayout(localFrameBitmapView2, 0);
/*     */         }
/*     */       }));
/*     */     }
/* 196 */     else if (paramString1.equals("pop-out")) {
/* 197 */       localFrameBitmapView2.setVisibility(0);
/* 198 */       this.isVisibility = true;
/* 199 */       localFrameBitmapView1.startAnimation(getAnimation(-(paramInt1 / 2), 0, this.mDuration, new Animation.AnimationListener() {
/*     */         public void onAnimationStart(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationRepeat(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationEnd(Animation paramAnonymousAnimation) {
/* 205 */           FrameSwitchView.this.endAnimationLayout(localFrameBitmapView1, 0);
/* 206 */           FrameSwitchView.this.isRuning = false;
/* 207 */           JSUtil.execCallback(paramIWebview, paramString2, null, JSUtil.OK, false, false);
/*     */         }
/*     */       }));
/* 210 */       localFrameBitmapView2.startAnimation(getAnimation(0, paramInt1, this.mDuration, new Animation.AnimationListener() {
/*     */         public void onAnimationStart(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationRepeat(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationEnd(Animation paramAnonymousAnimation) {
/* 216 */           localFrameBitmapView2.setVisibility(8);
/*     */         } } ));
/*     */     }
/* 219 */     else if (paramString1.equals("slide-in-right")) {
/* 220 */       if (localFrameBitmapView2 != null) {
/* 221 */         localFrameBitmapView2.setVisibility(8);
/*     */       }
/* 223 */       this.isVisibility = true;
/* 224 */       localFrameBitmapView1.startAnimation(getAnimation(paramInt1, 0, this.mDuration, new Animation.AnimationListener() {
/*     */         public void onAnimationStart(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationRepeat(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationEnd(Animation paramAnonymousAnimation) {
/* 230 */           FrameSwitchView.this.endAnimationLayout(localFrameBitmapView1, 0);
/* 231 */           FrameSwitchView.this.isRuning = false;
/* 232 */           JSUtil.execCallback(paramIWebview, paramString2, null, JSUtil.OK, false, false);
/*     */         } } ));
/*     */     }
/* 235 */     else if (paramString1.equals("slide-out-right")) {
/* 236 */       if (localFrameBitmapView2 != null) {
/* 237 */         localFrameBitmapView2.setVisibility(8);
/*     */       }
/* 239 */       this.isVisibility = true;
/* 240 */       localFrameBitmapView1.startAnimation(getAnimation(0, paramInt1, this.mDuration, new Animation.AnimationListener() {
/*     */         public void onAnimationStart(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationRepeat(Animation paramAnonymousAnimation) {
/*     */         }
/*     */         public void onAnimationEnd(Animation paramAnonymousAnimation) {
/* 246 */           FrameSwitchView.this.endAnimationLayout(localFrameBitmapView1, paramInt1);
/* 247 */           FrameSwitchView.this.isRuning = false;
/* 248 */           JSUtil.execCallback(paramIWebview, paramString2, null, JSUtil.OK, false, false);
/*     */         } } ));
/*     */     }
/*     */     else {
/* 252 */       this.isRuning = false;
/* 253 */       JSUtil.execCallback(paramIWebview, paramString2, null, JSUtil.OK, false, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void endAnimationLayout(View paramView, int paramInt)
/*     */   {
/* 263 */     int i = paramView.getLeft() + paramInt;
/* 264 */     int j = paramView.getTop();
/* 265 */     int k = paramView.getWidth();
/* 266 */     int m = paramView.getHeight();
/* 267 */     paramView.clearAnimation();
/* 268 */     paramView.layout(i, j, i + k, j + m);
/*     */   }
/*     */ 
/*     */   public void clearSwitchAnimation(String paramString)
/*     */   {
/* 294 */     endRefreshView();
/*     */   }
/*     */ 
/*     */   public void endRefreshView()
/*     */   {
/* 310 */     if (this.isVisibility) {
/* 311 */       this.mLeftFrameBpView.clearAnimation();
/* 312 */       this.mRightFrameBpView.clearAnimation();
/* 313 */       this.isVisibility = false;
/*     */     }
/* 315 */     if (this.mSwitchLayout.getVisibility() == 0) {
/* 316 */       this.mSwitchLayout.setVisibility(8);
/* 317 */       if (this.mLeftView != null) {
/* 318 */         ((IWaiter)this.mLeftView).doForFeature("clearAnimate", null);
/* 319 */         this.mSwitchLayout.removeView(this.mLeftView);
/* 320 */         this.mLeftView = null;
/*     */       }
/* 322 */       if (this.mRightView != null) {
/* 323 */         ((IWaiter)this.mRightView).doForFeature("clearAnimate", null);
/* 324 */         this.mSwitchLayout.removeView(this.mRightView);
/* 325 */         this.mRightView = null;
/*     */       }
/* 327 */       this.mLeftFrameBpView.clearData();
/* 328 */       this.mRightFrameBpView.clearData();
/* 329 */       this.mLeftFrameBpView.requestLayout();
/* 330 */       this.mRightFrameBpView.requestLayout();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void stopAnimation()
/*     */   {
/* 338 */     initScreenData();
/* 339 */     if ((this.mLeftFrameBpView != null) && (this.mLeftFrameBpView.isInit())) {
/* 340 */       this.mLeftFrameBpView.setStopAnimation(true);
/* 341 */       this.mLeftFrameBpView.configurationChanged(this.mAppScreenWidth, this.mAppScreenHeight);
/*     */     }
/* 343 */     if ((this.mRightFrameBpView != null) && (this.mRightFrameBpView.isInit())) {
/* 344 */       this.mRightFrameBpView.setStopAnimation(true);
/* 345 */       this.mRightFrameBpView.configurationChanged(this.mAppScreenWidth, this.mAppScreenHeight);
/*     */     }
/*     */     FrameBitmapView localFrameBitmapView;
/* 347 */     if ("pop-in".equals(this.mAniType)) {
/* 348 */       endAnimationLayout(this.mRightView != null ? this.mRightView : this.mRightFrameBpView, 0);
/* 349 */       localFrameBitmapView = this.mLeftView != null ? this.mLeftView : this.mLeftFrameBpView;
/* 350 */       localFrameBitmapView.setVisibility(8);
/* 351 */     } else if ("pop-out".equals(this.mAniType)) {
/* 352 */       localFrameBitmapView = this.mRightView != null ? this.mRightView : this.mRightFrameBpView;
/* 353 */       localFrameBitmapView.setVisibility(8);
/* 354 */       endAnimationLayout(this.mLeftView != null ? this.mLeftView : this.mLeftFrameBpView, 0);
/*     */     } else {
/* 356 */       endRefreshView();
/*     */     }
/*     */   }
/*     */ 
/*     */   private TranslateAnimation getAnimation(int paramInt1, int paramInt2, int paramInt3, Animation.AnimationListener paramAnimationListener)
/*     */   {
/* 369 */     TranslateAnimation localTranslateAnimation = new TranslateAnimation(paramInt1, paramInt2, 0.0F, 0.0F);
/* 370 */     localTranslateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
/* 371 */     localTranslateAnimation.setDuration(paramInt3);
/* 372 */     localTranslateAnimation.setAnimationListener(paramAnimationListener);
/* 373 */     return localTranslateAnimation;
/*     */   }
/*     */ 
/*     */   public void clearData()
/*     */   {
/* 380 */     if (this.isInit) {
/* 381 */       this.isInit = false;
/* 382 */       this.mActivity = null;
/* 383 */       this.mSwitchLayout = null;
/* 384 */       mInstance = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private class SwitchLayout extends RelativeLayout implements IWaiter
/*     */   {
/*     */     public SwitchLayout(Context arg2)
/*     */     {
/* 392 */       super();
/*     */     }
/*     */ 
/*     */     protected void onConfigurationChanged(Configuration paramConfiguration)
/*     */     {
/* 399 */       super.onConfigurationChanged(paramConfiguration);
/* 400 */       if ((FrameSwitchView.this.mWebViewImpl != null) && (FrameSwitchView.this.isVisibility))
/* 401 */         FrameSwitchView.this.stopAnimation();
/*     */     }
/*     */ 
/*     */     public boolean onTouchEvent(MotionEvent paramMotionEvent)
/*     */     {
/* 409 */       if (getVisibility() != 0) {
/* 410 */         return super.onTouchEvent(paramMotionEvent);
/*     */       }
/* 412 */       if (((FrameSwitchView.this.mLeftFrameBpView != null) && (FrameSwitchView.this.mLeftFrameBpView.isInit())) || ((FrameSwitchView.this.mRightFrameBpView != null) && (FrameSwitchView.this.mRightFrameBpView.isInit()))) {
/* 413 */         return true;
/*     */       }
/* 415 */       return super.onTouchEvent(paramMotionEvent);
/*     */     }
/*     */ 
/*     */     public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
/*     */     {
/* 421 */       if (!TextUtils.isEmpty(FrameSwitchView.this.mAniType)) {
/* 422 */         if ((FrameSwitchView.this.mAniType.equals("pop-in")) && (FrameSwitchView.this.mRightView != null) && ((FrameSwitchView.this.mRightView instanceof IWaiter)))
/* 423 */           return ((Boolean)((IWaiter)FrameSwitchView.this.mRightView).doForFeature("checkTouch", paramMotionEvent)).booleanValue();
/* 424 */         if (((FrameSwitchView.this.mAniType.equals("slide-in-right")) || (FrameSwitchView.this.mAniType.equals("slide-out-right")) || (FrameSwitchView.this.mAniType.equals("pop-out"))) && (FrameSwitchView.this.mLeftView != null) && ((FrameSwitchView.this.mLeftView instanceof IWaiter)))
/* 425 */           return ((Boolean)((IWaiter)FrameSwitchView.this.mLeftView).doForFeature("checkTouch", paramMotionEvent)).booleanValue();
/* 426 */         if (((FrameSwitchView.this.mLeftFrameBpView != null) && (FrameSwitchView.this.mLeftFrameBpView.isInit())) || ((FrameSwitchView.this.mRightFrameBpView != null) && (FrameSwitchView.this.mRightFrameBpView.isInit()))) {
/* 427 */           return super.dispatchTouchEvent(paramMotionEvent);
/*     */         }
/*     */       }
/* 430 */       return FrameSwitchView.this.isRuning;
/*     */     }
/*     */ 
/*     */     public Object doForFeature(String paramString, Object paramObject)
/*     */     {
/* 435 */       return null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.FrameSwitchView
 * JD-Core Version:    0.6.2
 */