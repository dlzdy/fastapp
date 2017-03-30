/*     */ package io.dcloud.common.adapter.ui;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Rect;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Handler;
/*     */ import android.os.Message;
/*     */ import android.text.TextUtils;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.animation.Animation;
/*     */ import android.view.animation.Animation.AnimationListener;
/*     */ import android.view.animation.AnimationSet;
/*     */ import android.view.animation.DecelerateInterpolator;
/*     */ import android.webkit.WebView;
/*     */ import android.widget.FrameLayout.LayoutParams;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.ImageView.ScaleType;
/*     */ import com.dcloud.android.v4.widget.IRefreshAble;
/*     */ import com.dcloud.android.widget.AbsoluteLayout;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.ICallBack;
/*     */ import io.dcloud.common.DHInterface.IEventCallback;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.INativeBitmap;
/*     */ import io.dcloud.common.DHInterface.IWaiter;
/*     */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.util.AnimOptions;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import io.dcloud.common.adapter.util.ViewRect;
/*     */ import io.dcloud.common.b.b.e;
/*     */ import io.dcloud.common.b.b.k;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.nineoldandroids.animation.Animator;
/*     */ import io.dcloud.nineoldandroids.animation.Animator.AnimatorListener;
/*     */ import io.dcloud.nineoldandroids.view.ViewHelper;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public abstract class AdaFrameView extends AdaContainerFrameItem
/*     */   implements IFrameView
/*     */ {
/*  53 */   public boolean isChildOfFrameView = false;
/*     */ 
/*  55 */   public Bitmap mLoadingSnapshot = null; public Bitmap mSnapshot = null;
/*     */   protected byte mFrameStatus;
/*  58 */   protected boolean mAnimationStarted = false;
/*     */   private Context mContext;
/*  60 */   RefreshView mRefreshView = null;
/*  61 */   BounceView mBounceView = null;
/*  62 */   IRefreshAble mCircleRefreshView = null;
/*  63 */   private ArrayList<IEventCallback> mListeners = null;
/*  64 */   private int mFrameType = 0;
/*  65 */   public String mAccelerationType = "auto";
/*     */ 
/*  70 */   public boolean interceptTouchEvent = true;
/*     */ 
/* 103 */   static e mPageCImageView = null;
/*     */ 
/* 105 */   private int mLastScreenHeight = 0;
/*     */ 
/* 109 */   public boolean mAnimationCapture = false;
/*     */ 
/* 566 */   public boolean isSlipping = false;
/*     */   private static final int SUCCESS = 1;
/*     */   private static final int ERROR = 0;
/* 638 */   private ICallBack mSucCallBack = null; private ICallBack mErrCallBack = null;
/* 639 */   private Handler mCaptureHandler = new Handler() {
/*     */     public void handleMessage(Message paramAnonymousMessage) {
/* 641 */       switch (paramAnonymousMessage.what) {
/*     */       case 1:
/* 643 */         if (AdaFrameView.this.mSucCallBack != null)
/* 644 */           AdaFrameView.this.mSucCallBack.onCallBack(0, null); break;
/*     */       default:
/* 647 */         if (AdaFrameView.this.mErrCallBack != null)
/* 648 */           AdaFrameView.this.mErrCallBack.onCallBack(paramAnonymousMessage.arg1, paramAnonymousMessage.obj);
/*     */         break;
/*     */       }
/* 651 */       super.handleMessage(paramAnonymousMessage);
/*     */     }
/* 639 */   };
/*     */ 
/* 793 */   Animation.AnimationListener mAnimationListener = new Animation.AnimationListener()
/*     */   {
/*     */     public void onAnimationStart(Animation paramAnonymousAnimation)
/*     */     {
/* 797 */       if (AdaFrameView.this.mAnimatorListener != null)
/* 798 */         AdaFrameView.this.mAnimatorListener.onAnimationStart(null);
/*     */     }
/*     */ 
/*     */     public void onAnimationRepeat(Animation paramAnonymousAnimation)
/*     */     {
/* 805 */       if (AdaFrameView.this.mAnimatorListener != null)
/* 806 */         AdaFrameView.this.mAnimatorListener.onAnimationRepeat(null);
/*     */     }
/*     */ 
/*     */     public void onAnimationEnd(Animation paramAnonymousAnimation)
/*     */     {
/* 812 */       if (AdaFrameView.this.mAnimatorListener != null)
/* 813 */         AdaFrameView.this.mAnimatorListener.onAnimationEnd(null);
/*     */     }
/* 793 */   };
/*     */ 
/*     */   public int getFrameType()
/*     */   {
/*  67 */     return this.mFrameType;
/*     */   }
/*     */ 
/*     */   protected AdaFrameView(Context paramContext, int paramInt, Object paramObject)
/*     */   {
/*  73 */     super(paramContext);
/*  74 */     this.mFrameType = paramInt;
/*  75 */     initMainView(paramContext, this.mFrameType, paramObject);
/*  76 */     this.mContext = paramContext;
/*  77 */     this.mNeedOrientationUpdate = true;
/*  78 */     this.mLastScreenHeight = PlatformUtil.SCREEN_HEIGHT(paramContext);
/*     */   }
/*     */ 
/*     */   protected abstract void initMainView(Context paramContext, int paramInt, Object paramObject);
/*     */ 
/*     */   public abstract String obtainPrePlusreadyJs();
/*     */ 
/*     */   public boolean isSupportLongTouch()
/*     */   {
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public byte obtainStatus()
/*     */   {
/*  94 */     return this.mFrameStatus;
/*     */   }
/*     */ 
/*     */   public void onConfigurationChanged()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void chkUseCaptureAnimation(boolean paramBoolean1, int paramInt, boolean paramBoolean2)
/*     */   {
/* 119 */     String str = "C页面" + paramInt;
/*     */ 
/* 121 */     if ((this.mAccelerationType.equals("none")) && (!paramBoolean2)) {
/* 122 */       this.mAnimationCapture = false;
/* 123 */       return;
/*     */     }
/*     */ 
/* 126 */     if ((Build.VERSION.SDK_INT >= 23) && (!PdrUtil.isEquals(this.mAnimOptions.mAnimType, "pop-in")) && (!PdrUtil.isEquals(this.mAnimOptions.mAnimType, "pop-out"))) {
/* 127 */       this.mAnimationCapture = false;
/* 128 */       return;
/*     */     }
/*     */ 
/* 131 */     if ((!obtainWebView().isLoaded()) || (DeviceInfo.sDeviceSdkVer < 11)) {
/* 132 */       this.mAnimationCapture = false;
/* 133 */       Logger.e("mabo", str + "1是否启用截图动画方案:" + this.mAnimationCapture);
/* 134 */       return;
/*     */     }
/*     */ 
/* 137 */     if ((this.isChildOfFrameView) && (!PdrUtil.isEquals(this.mAnimOptions.mAnimType, "fade-in"))) {
/* 138 */       this.mAnimationCapture = false;
/* 139 */       Logger.e("mabo", str + "2是否启用截图动画方案:" + this.mAnimationCapture);
/* 140 */       return;
/*     */     }
/*     */ 
/* 143 */     PlatformUtil.MESURE_SCREEN_STATUSBAR_HEIGHT(obtainWebView().getActivity());
/*     */ 
/* 146 */     if (this.mLastScreenHeight != PlatformUtil.SCREEN_HEIGHT(this.mContext)) {
/* 147 */       this.mLastScreenHeight = PlatformUtil.SCREEN_HEIGHT(this.mContext);
/* 148 */       this.mAnimationCapture = false;
/* 149 */       Logger.e("mabo", str + "3是否启用截图动画方案:" + this.mAnimationCapture);
/* 150 */       return;
/*     */     }
/*     */ 
/* 153 */     int i = (this.mAnimOptions.mOption == 3) || (this.mAnimOptions.mOption == 1) ? 1 : 0;
/* 154 */     int j = 0;
/* 155 */     if (((this.mAccelerationType.equals("auto")) && (!PdrUtil.isEquals(this.mAnimOptions.mAnimType, "fade-in")) && (i != 0)) || (this.mAccelerationType.equals("capture")))
/* 156 */       j = 1;
/*     */     boolean bool;
/* 159 */     if (i == 0) {
/* 160 */       bool = (this.mAnimOptions.mOption == 4) || (this.mAnimOptions.mOption == 0);
/*     */ 
/* 162 */       bool &= ((j != 0) || (PdrUtil.isEquals(this.mAnimOptions.mAnimType, "pop-in")) || (PdrUtil.isEquals(this.mAnimOptions.mAnimType, "zoom-fade-out")));
/*     */     } else {
/* 164 */       bool = true;
/*     */ 
/* 166 */       bool &= ((j != 0) || (PdrUtil.isEquals(this.mAnimOptions.mAnimType_close, "slide-out-right")) || (PdrUtil.isEquals(this.mAnimOptions.mAnimType, "slide-out-left")) || (PdrUtil.isEquals(this.mAnimOptions.mAnimType, "slide-out-top")) || (PdrUtil.isEquals(this.mAnimOptions.mAnimType, "slide-out-bottom")) || (PdrUtil.isEquals(this.mAnimOptions.mAnimType_close, "pop-out")) || (PdrUtil.isEquals(this.mAnimOptions.mAnimType, "zoom-fade-in")));
/*     */     }
/*     */ 
/* 169 */     if ((this.isChildOfFrameView) && (PdrUtil.isEquals(this.mAnimOptions.mAnimType, "fade-in"))) {
/* 170 */       bool = true;
/*     */     }
/*     */ 
/* 173 */     this.mAnimationCapture = bool;
/*     */   }
/*     */ 
/*     */   private void addCaptureImageView(ViewGroup paramViewGroup, ImageView paramImageView, Bitmap paramBitmap)
/*     */   {
/* 183 */     if (paramImageView.getParent() != paramViewGroup) {
/* 184 */       if (paramImageView.getParent() != null) {
/* 185 */         ((ViewGroup)paramImageView.getParent()).removeView(paramImageView);
/*     */       }
/* 187 */       paramViewGroup.addView(paramImageView);
/*     */     }
/* 189 */     paramImageView.bringToFront();
/* 190 */     paramImageView.setImageBitmap(paramBitmap);
/* 191 */     paramImageView.setVisibility(VISIBLE);
/*     */   }
/*     */ 
/*     */   private boolean captureAnimation(final Animator paramAnimator, int paramInt)
/*     */   {
/* 200 */     if ((!this.mAnimationCapture) || (!BaseInfo.sAnimationCaptureC)) {
/* 201 */       return false;
/*     */     }
/*     */ 
/* 204 */     int i = 0;
/* 205 */     final ViewGroup localViewGroup = (ViewGroup)obtainMainView();
/*     */ 
/* 207 */     if (checkITypeofAble()) {
/* 208 */       return false;
/*     */     }
/* 210 */     final k localk = (k)obtainWebAppRootView().obtainMainView();
/* 211 */     if ((mPageCImageView != null) && (localk.getHeight() != mPageCImageView.getHeight())) {
/* 212 */       mPageCImageView.setImageBitmap(null);
/* 213 */       mPageCImageView = null;
/* 214 */       return false;
/*     */     }
/* 216 */     if (mPageCImageView == null) {
/* 217 */       mPageCImageView = localk.c();
/*     */ 
/* 220 */       mPageCImageView.setScaleType(ImageView.ScaleType.FIT_XY);
/*     */     }
/* 222 */     if (mPageCImageView.a()) {
/* 223 */       return false;
/*     */     }
/* 225 */     Logger.e("mabo", "C页面是否启用截图动画方案:" + ((this.mAnimationCapture) && (BaseInfo.sAnimationCaptureC)) + " | " + this.mAnimOptions.mAnimType);
/* 226 */     long l1 = System.currentTimeMillis();
/* 227 */     Bitmap localBitmap = null;
/* 228 */     if (paramInt == 0) {
/* 229 */       if (this.mLoadingSnapshot != null) {
/* 230 */         localBitmap = this.mLoadingSnapshot;
/* 231 */       } else if (this.mSnapshot != null) {
/* 232 */         localBitmap = this.mSnapshot;
/*     */       } else {
/* 234 */         localBitmap = PlatformUtil.captureView(localViewGroup);
/* 235 */         i = 1;
/*     */       }
/*     */     }
/* 238 */     else if (this.mSnapshot != null) {
/* 239 */       localBitmap = this.mSnapshot;
/*     */     } else {
/* 241 */       localBitmap = PlatformUtil.captureView(localViewGroup);
/* 242 */       i = 1;
/*     */     }
/*     */ 
/* 255 */     long l2 = System.currentTimeMillis();
/* 256 */     Logger.i("mabo", "==============C截图耗时=" + (l2 - l1));
/*     */ 
/* 258 */     if (l2 - l1 >= BaseInfo.sTimeoutCapture) {
/* 259 */       BaseInfo.sTimeOutCount += 1;
/* 260 */       if (BaseInfo.sTimeOutCount > BaseInfo.sTimeOutMax)
/* 261 */         BaseInfo.sAnimationCaptureC = false;
/*     */     }
/* 263 */     else if (i != 0) {
/* 264 */       BaseInfo.sTimeOutCount = 0;
/*     */     }
/* 266 */     if ((localBitmap != null) && (!PlatformUtil.isWhiteBitmap(localBitmap))) {
/* 267 */       mPageCImageView.c();
/* 268 */       mPageCImageView.setLayoutParams(new FrameLayout.LayoutParams(localViewGroup.getMeasuredWidth(), localViewGroup.getMeasuredHeight()));
/* 269 */       addCaptureImageView(localk, mPageCImageView, localBitmap);
/* 270 */       localViewGroup.setVisibility(4);
/*     */ 
/* 272 */       ViewHelper.setX(mPageCImageView, this.mViewOptions.left);
/* 273 */       ViewHelper.setY(mPageCImageView, this.mViewOptions.top);
/* 274 */       if (this.mAnimOptions.mAnimator == null)
/*     */       {
/* 276 */         paramAnimator.setTarget(mPageCImageView);
/* 277 */         paramAnimator.addListener(new Animator.AnimatorListener()
/*     */         {
/*     */           public void onAnimationStart(Animator paramAnonymousAnimator) {
/* 280 */             if (AdaFrameView.mPageCImageView != null) {
/* 281 */               AdaFrameView.mPageCImageView.b(true);
/*     */             }
/* 283 */             BaseInfo.sDoingAnimation = true;
/* 284 */             if (AdaFrameView.this.mAnimatorListener != null)
/* 285 */               AdaFrameView.this.mAnimatorListener.onAnimationStart(paramAnonymousAnimator);
/*     */           }
/*     */ 
/*     */           public void onAnimationRepeat(Animator paramAnonymousAnimator)
/*     */           {
/* 291 */             if (AdaFrameView.this.mAnimatorListener != null)
/* 292 */               AdaFrameView.this.mAnimatorListener.onAnimationRepeat(paramAnonymousAnimator);
/*     */           }
/*     */ 
/*     */           public void onAnimationEnd(Animator paramAnonymousAnimator)
/*     */           {
/* 297 */             if (AdaFrameView.mPageCImageView != null) {
/* 298 */               AdaFrameView.mPageCImageView.b(false);
/*     */             }
/* 300 */             if (AdaFrameView.this.mAnimatorListener != null)
/* 301 */               AdaFrameView.this.mAnimatorListener.onAnimationEnd(paramAnonymousAnimator);
/* 302 */             if ((AdaFrameView.this.mAnimOptions.mOption == 3) || (AdaFrameView.this.mAnimOptions.mOption == 1))
/* 303 */               localViewGroup.setVisibility(4);
/*     */             else {
/* 305 */               localViewGroup.setVisibility(0);
/*     */             }
/* 307 */             localk.postDelayed(new Runnable()
/*     */             {
/*     */               public void run() {
/* 310 */                 if (AdaFrameView.mPageCImageView != null) {
/* 311 */                   AdaFrameView.mPageCImageView.setVisibility(4);
/* 312 */                   AdaFrameView.mPageCImageView.setImageBitmap(null);
/*     */                 }
/* 314 */                 BaseInfo.sDoingAnimation = false;
/* 315 */                 if (BaseInfo.sOpenedCount == 0)
/* 316 */                   BaseInfo.sFullScreenChanged = false;
/*     */               }
/*     */             }
/*     */             , 240L);
/*     */ 
/* 319 */             paramAnimator.removeListener(this);
/*     */           }
/*     */ 
/*     */           public void onAnimationCancel(Animator paramAnonymousAnimator) {
/* 323 */             BaseInfo.sDoingAnimation = false;
/* 324 */             if (BaseInfo.sOpenedCount == 0)
/* 325 */               BaseInfo.sFullScreenChanged = false;
/* 326 */             if (AdaFrameView.this.mAnimatorListener != null)
/* 327 */               AdaFrameView.this.mAnimatorListener.onAnimationCancel(paramAnonymousAnimator);
/*     */           } } );
/*     */       }
/*     */       else {
/* 331 */         this.mAnimOptions.mAnimator.setAnimationListener(new Animation.AnimationListener()
/*     */         {
/*     */           public void onAnimationStart(Animation paramAnonymousAnimation)
/*     */           {
/* 336 */             if (AdaFrameView.mPageCImageView != null) {
/* 337 */               AdaFrameView.mPageCImageView.b(true);
/*     */             }
/* 339 */             BaseInfo.sDoingAnimation = true;
/* 340 */             if (AdaFrameView.this.mAnimatorListener != null)
/* 341 */               AdaFrameView.this.mAnimatorListener.onAnimationStart(null);
/*     */           }
/*     */ 
/*     */           public void onAnimationRepeat(Animation paramAnonymousAnimation)
/*     */           {
/* 347 */             if (AdaFrameView.this.mAnimatorListener != null)
/* 348 */               AdaFrameView.this.mAnimatorListener.onAnimationRepeat(null);
/*     */           }
/*     */ 
/*     */           public void onAnimationEnd(Animation paramAnonymousAnimation)
/*     */           {
/* 354 */             if (AdaFrameView.mPageCImageView != null) {
/* 355 */               AdaFrameView.mPageCImageView.b(false);
/*     */             }
/* 357 */             if (AdaFrameView.this.mAnimatorListener != null)
/* 358 */               AdaFrameView.this.mAnimatorListener.onAnimationEnd(null);
/* 359 */             if ((AdaFrameView.this.mAnimOptions.mOption == 3) || (AdaFrameView.this.mAnimOptions.mOption == 1))
/* 360 */               localViewGroup.setVisibility(4);
/*     */             else {
/* 362 */               localViewGroup.setVisibility(0);
/*     */             }
/* 364 */             localViewGroup.postDelayed(new Runnable()
/*     */             {
/*     */               public void run()
/*     */               {
/* 368 */                 if ((AdaFrameView.mPageCImageView != null) && (!BaseInfo.sDoingAnimation)) {
/* 369 */                   AdaFrameView.mPageCImageView.clearAnimation();
/* 370 */                   AdaFrameView.mPageCImageView.setVisibility(4);
/* 371 */                   AdaFrameView.mPageCImageView.setImageBitmap(null);
/*     */                 }
/* 373 */                 if (BaseInfo.sOpenedCount == 0)
/* 374 */                   BaseInfo.sFullScreenChanged = false;
/*     */               }
/*     */             }
/*     */             , 240L);
/*     */           }
/*     */ 
/*     */         });
/*     */       }
/*     */ 
/* 382 */       return true;
/*     */     }
/* 384 */     return false;
/*     */   }
/*     */ 
/*     */   public void startAnimator(int paramInt)
/*     */   {
/* 394 */     startAnimator(null, paramInt);
/*     */   }
/*     */ 
/*     */   public void startAnimator(final OnAnimationEnd paramOnAnimationEnd, int paramInt) {
/* 398 */     if (this.mAnimOptions != null) {
/* 399 */       this.mAnimOptions.mUserFrameItem = this;
/* 400 */       this.mAnimOptions.sScreenWidth = obtainApp().getInt(0);
/* 401 */       this.mAnimOptions.sScreenHeight = obtainApp().getInt(1);
/* 402 */       Animator localAnimator = this.mAnimOptions.createAnimation();
/* 403 */       boolean bool = obtainFrameOptions().hasBackground();
/* 404 */       if ((bool) && (this.mAnimOptions.mOption == 2))
/*     */       {
/* 406 */         AdaWebViewParent localAdaWebViewParent = obtainWebviewParent();
/* 407 */         this.mAnimOptions.mUserFrameItem = localAdaWebViewParent;
/* 408 */         View localView = localAdaWebViewParent.obtainMainView();
/* 409 */         localAnimator.setTarget(localView);
/* 410 */         localAnimator.addListener(this.mAnimatorListener);
/* 411 */         localAnimator.start();
/*     */       }
/* 413 */       else if (captureAnimation(localAnimator, paramInt))
/*     */       {
/* 415 */         if (this.mAnimOptions.mAnimator == null)
/* 416 */           localAnimator.start();
/* 417 */         else if (mPageCImageView != null)
/* 418 */           mPageCImageView.startAnimation(this.mAnimOptions.mAnimator);
/*     */       }
/*     */       else
/*     */       {
/* 422 */         if ((mPageCImageView != null) && (!mPageCImageView.a())) {
/* 423 */           mPageCImageView.clearAnimation();
/* 424 */           mPageCImageView.setVisibility(4);
/* 425 */           mPageCImageView.setImageBitmap(null);
/*     */         }
/*     */ 
/* 428 */         this.mViewImpl.bringToFront();
/* 429 */         if (this.mAnimOptions.mAnimator == null) {
/* 430 */           localAnimator.setTarget(this.mViewImpl);
/* 431 */           localAnimator.addListener(this.mAnimatorListener);
/* 432 */           localAnimator.start();
/*     */         } else {
/* 434 */           this.mAnimOptions.mAnimator.setAnimationListener(this.mAnimationListener);
/* 435 */           this.mViewImpl.startAnimation(this.mAnimOptions.mAnimator);
/*     */         }
/*     */       }
/*     */ 
/* 439 */       localAnimator.setInterpolator(new DecelerateInterpolator());
/*     */ 
/* 447 */       int i = Math.max(this.mAnimOptions.duration_show, Math.max(this.mAnimOptions.duration_close, this.mAnimOptions.duration));
/* 448 */       MessageHandler.sendMessage(new MessageHandler.IMessages()
/*     */       {
/*     */         public void execute(Object paramAnonymousObject) {
/* 451 */           if ((!AdaFrameView.this.mAnimationStarted) && (AdaFrameView.this.mAnimatorListener != null)) {
/* 452 */             AdaFrameView.this.mAnimatorListener.onAnimationEnd(null);
/*     */           }
/*     */ 
/* 455 */           if (paramOnAnimationEnd != null) paramOnAnimationEnd.onDone();
/* 456 */           if (BaseInfo.sOpenedCount == 0)
/* 457 */             BaseInfo.sFullScreenChanged = false;
/*     */         }
/*     */       }
/*     */       , i, null);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setVisible(boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 464 */     Logger.d("View_Visible_Path", "AdaFrameView.setVisible pVisible" + paramBoolean1 + "       " + this);
/* 465 */     int i = paramBoolean1 ? VISIBLE : GONE;
/* 466 */     setVisibility(i);
/*     */   }
/*     */ 
/*     */   public final void addFrameViewListener(IEventCallback paramIEventCallback)
/*     */   {
/* 480 */     if (this.mListeners == null) {
/* 481 */       this.mListeners = new ArrayList();
/*     */     }
/* 483 */     if ((paramIEventCallback != null) && (!this.mListeners.contains(paramIEventCallback)))
/* 484 */       this.mListeners.add(paramIEventCallback);
/*     */   }
/*     */ 
/*     */   public final void removeFrameViewListener(IEventCallback paramIEventCallback)
/*     */   {
/* 493 */     if (this.mListeners != null)
/* 494 */       this.mListeners.remove(paramIEventCallback);
/*     */   }
/*     */ 
/*     */   public final void dispatchFrameViewEvents(String paramString, Object paramObject)
/*     */   {
/* 504 */     if (this.mListeners != null) {
/* 505 */       Logger.d("AdaFrameView.dispatchFrameViewEvents type=" + paramString + ";args=" + paramObject);
/* 506 */       int i = this.mListeners.size();
/* 507 */       for (int j = i - 1; j >= 0; j--) {
/* 508 */         IEventCallback localIEventCallback = (IEventCallback)this.mListeners.get(j);
/* 509 */         if (PdrUtil.isEquals(paramString, "close")) {
/* 510 */           this.mListeners.remove(j);
/*     */         }
/* 512 */         localIEventCallback.onCallBack(paramString, paramObject);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void onResize()
/*     */   {
/* 519 */     super.onResize();
/* 520 */     if (obtainApp() == null) {
/* 521 */       return;
/*     */     }
/* 523 */     if (this.mRefreshView != null) {
/* 524 */       this.mRefreshView.onResize();
/*     */     }
/* 526 */     if (this.mBounceView != null) {
/* 527 */       this.mBounceView.onResize();
/*     */     }
/* 529 */     dispatchFrameViewEvents("onresize", null);
/*     */   }
/*     */ 
/*     */   public void dispose()
/*     */   {
/* 534 */     super.dispose();
/* 535 */     if (this.mRefreshView != null) {
/* 536 */       this.mRefreshView = null;
/*     */     }
/* 538 */     if (this.mBounceView != null) {
/* 539 */       this.mBounceView = null;
/*     */     }
/* 541 */     if (this.mCircleRefreshView != null)
/* 542 */       this.mCircleRefreshView = null;
/*     */   }
/*     */ 
/*     */   public void paint(Canvas paramCanvas)
/*     */   {
/* 548 */     super.paint(paramCanvas);
/* 549 */     if ((obtainMainView() != null) && (obtainMainView().getVisibility() == 0) && (this.mRefreshView != null) && (!this.isSlipping)) {
/* 550 */       Logger.d("View_Visible_Path", "AdaFrameView.paint mRefreshView paint" + this);
/*     */ 
/* 553 */       ViewOptions localViewOptions1 = obtainWebviewParent().mViewOptions_birth;
/* 554 */       ViewOptions localViewOptions2 = obtainWebviewParent().mViewOptions;
/* 555 */       localViewOptions1 = localViewOptions1 == null ? this.mViewOptions_birth : localViewOptions1;
/* 556 */       localViewOptions2 = localViewOptions2 == null ? this.mViewOptions : localViewOptions2;
/*     */ 
/* 558 */       int i = localViewOptions1.left != localViewOptions2.left ? localViewOptions2.left : 0;
/* 559 */       int j = localViewOptions1.top != localViewOptions2.top ? localViewOptions2.top : 0;
/*     */ 
/* 562 */       this.mRefreshView.paint(paramCanvas, i + obtainWebviewParent().obtainMainView().getLeft(), j + obtainWebviewParent().obtainMainView().getTop());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setSlipping(boolean paramBoolean)
/*     */   {
/* 568 */     this.isSlipping = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void onInit()
/*     */   {
/* 573 */     this.mFrameStatus = 0;
/*     */   }
/*     */ 
/*     */   public void onPreLoading() {
/* 577 */     this.mFrameStatus = 1;
/*     */   }
/*     */ 
/*     */   public void onLoading() {
/* 581 */     this.mFrameStatus = 2;
/*     */   }
/*     */ 
/*     */   public void onPreShow(IFrameView paramIFrameView) {
/* 585 */     this.mFrameStatus = 3;
/* 586 */     transition(this.mFrameStatus);
/*     */   }
/*     */ 
/*     */   public void onDestroy() {
/* 590 */     this.mFrameStatus = 4;
/* 591 */     transition(this.mFrameStatus);
/* 592 */     dispose();
/*     */   }
/*     */ 
/*     */   public void updateFrameRelViewRect(ViewRect paramViewRect)
/*     */   {
/* 599 */     View localView = obtainMainView();
/* 600 */     if (this.mViewOptions.hasBackground()) {
/* 601 */       if (obtainWebviewParent().obtainFrameOptions().allowUpdate)
/* 602 */         obtainWebviewParent().obtainFrameOptions().updateViewData(paramViewRect);
/*     */     }
/*     */     else {
/* 605 */       this.mViewOptions.updateViewData(paramViewRect);
/* 606 */       if ((localView != null) && (localView.getVisibility() == 0) && (this.mRefreshView != null))
/*     */       {
/* 608 */         obtainWebviewParent().reInit();
/*     */       }
/*     */     }
/* 611 */     if (this.mAnimationCapture) {
/* 612 */       ViewHelper.setX(this.mViewImpl, this.mViewOptions.left);
/* 613 */       ViewHelper.setY(this.mViewImpl, this.mViewOptions.top);
/* 614 */       Logger.d("Layout_Path", new Object[] { "mViewImpl onAnimationEnd X=" + ViewHelper.getX(this.mViewImpl) + "Y=" + ViewHelper.getY(this.mViewImpl), this.mViewImpl });
/*     */     }
/* 616 */     if (localView != null)
/* 617 */       obtainMainView().invalidate();
/*     */   }
/*     */ 
/*     */   public boolean onDispose()
/*     */   {
/* 622 */     boolean bool = super.onDispose();
/* 623 */     dispatchFrameViewEvents("close", obtainWebView());
/* 624 */     if (mPageCImageView != null)
/* 625 */       mPageCImageView.setImageBitmap(null);
/* 626 */     return bool;
/*     */   }
/*     */ 
/*     */   public void transition(byte paramByte)
/*     */   {
/*     */   }
/*     */ 
/*     */   public abstract IWebview obtainWebView();
/*     */ 
/*     */   public abstract IApp obtainApp();
/*     */ 
/*     */   public abstract AbsMgr obtainWindowMgr();
/*     */ 
/*     */   /** @deprecated */
/*     */   public void captureSnapshot(final String paramString, final ICallBack paramICallBack1, final ICallBack paramICallBack2)
/*     */   {
/* 661 */     new Thread(new Runnable()
/*     */     {
/*     */       public void run() {
/* 664 */         if ((!TextUtils.isEmpty(paramString)) && ("loading".equals(paramString.toLowerCase())))
/*     */           try {
/* 666 */             AdaFrameView.this.mLoadingSnapshot = PlatformUtil.captureView(AdaFrameView.this.obtainMainView());
/* 667 */             if (AdaFrameView.this.mLoadingSnapshot != null) {
/* 668 */               AdaFrameView.this.mSucCallBack = paramICallBack1;
/* 669 */               AdaFrameView.this.mCaptureHandler.sendEmptyMessage(1);
/*     */             } else {
/* 671 */               AdaFrameView.this.mErrCallBack = paramICallBack2;
/* 672 */               AdaFrameView.this.sendErrorMessage(-100, "截图失败");
/*     */             }
/*     */           } catch (Exception localException1) {
/* 675 */             AdaFrameView.this.mErrCallBack = paramICallBack2;
/* 676 */             AdaFrameView.this.sendErrorMessage(-100, "截图失败");
/*     */           }
/*     */         else
/*     */           try {
/* 680 */             AdaFrameView.this.mSnapshot = PlatformUtil.captureView(AdaFrameView.this.obtainMainView());
/* 681 */             if (AdaFrameView.this.mSnapshot != null) {
/* 682 */               AdaFrameView.this.mSucCallBack = paramICallBack1;
/* 683 */               AdaFrameView.this.mCaptureHandler.sendEmptyMessage(1);
/*     */             } else {
/* 685 */               AdaFrameView.this.mErrCallBack = paramICallBack2;
/* 686 */               AdaFrameView.this.sendErrorMessage(-100, "截图失败");
/*     */             }
/*     */           } catch (Exception localException2) {
/* 689 */             AdaFrameView.this.mErrCallBack = paramICallBack2;
/* 690 */             AdaFrameView.this.sendErrorMessage(-100, "截图失败");
/*     */           }
/*     */       }
/*     */     }).start();
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public void clearSnapshot(String paramString)
/*     */   {
/* 703 */     if (mPageCImageView != null)
/* 704 */       mPageCImageView.setImageBitmap(null);
/* 705 */     if ((!TextUtils.isEmpty(paramString)) && ("loading".equals(paramString.toLowerCase()))) {
/* 706 */       if (this.mLoadingSnapshot != null) {
/*     */         try {
/* 708 */           if (!this.mLoadingSnapshot.isRecycled())
/* 709 */             this.mLoadingSnapshot.recycle();
/*     */         }
/*     */         catch (Exception localException1) {
/* 712 */           localException1.printStackTrace();
/*     */         }
/*     */       }
/* 715 */       this.mLoadingSnapshot = null;
/*     */     } else {
/* 717 */       if (this.mSnapshot != null) {
/*     */         try {
/* 719 */           if (!this.mSnapshot.isRecycled())
/* 720 */             this.mSnapshot.recycle();
/*     */         }
/*     */         catch (Exception localException2) {
/* 723 */           localException2.printStackTrace();
/*     */         }
/*     */       }
/* 726 */       this.mSnapshot = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void draw(final View paramView, final INativeBitmap paramINativeBitmap, final boolean paramBoolean, final Rect paramRect, final ICallBack paramICallBack1, final ICallBack paramICallBack2)
/*     */   {
/* 737 */     this.mCaptureHandler.post(new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*     */         try {
/* 742 */           Bitmap localBitmap = PlatformUtil.captureView(paramView, paramBoolean, paramRect);
/* 743 */           if (localBitmap != null) {
/* 744 */             paramINativeBitmap.setBitmap(localBitmap);
/* 745 */             AdaFrameView.this.mSucCallBack = paramICallBack1;
/* 746 */             AdaFrameView.this.mCaptureHandler.sendEmptyMessage(1);
/*     */           } else {
/* 748 */             AdaFrameView.this.mErrCallBack = paramICallBack2;
/* 749 */             AdaFrameView.this.sendErrorMessage(-101, "截图为白屏");
/*     */           }
/*     */         } catch (Exception localException) {
/* 752 */           localException.printStackTrace();
/* 753 */           AdaFrameView.this.mErrCallBack = paramICallBack2;
/* 754 */           AdaFrameView.this.sendErrorMessage(-100, "截图失败");
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   private void sendErrorMessage(int paramInt, String paramString)
/*     */   {
/* 764 */     Message localMessage = new Message();
/* 765 */     localMessage.what = 0;
/* 766 */     localMessage.arg1 = paramInt;
/* 767 */     localMessage.obj = paramString;
/* 768 */     this.mCaptureHandler.sendMessage(localMessage);
/*     */   }
/*     */ 
/*     */   public void setSnapshot(Bitmap paramBitmap)
/*     */   {
/* 773 */     if ((this.mSnapshot != null) && (!this.mSnapshot.isRecycled())) {
/* 774 */       this.mSnapshot.recycle();
/*     */     }
/* 776 */     this.mSnapshot = paramBitmap;
/*     */   }
/*     */ 
/*     */   public void setAccelerationType(String paramString)
/*     */   {
/* 782 */     this.mAccelerationType = paramString;
/*     */   }
/*     */ 
/*     */   public IFrameView findPageB()
/*     */   {
/* 787 */     return obtainWebAppRootView().findFrameViewB(this);
/*     */   }
/*     */ 
/*     */   public void animate(IWebview paramIWebview, String paramString1, String paramString2)
/*     */   {
/* 821 */     if ((obtainMainView() instanceof AbsoluteLayout)) {
/* 822 */       AbsoluteLayout localAbsoluteLayout = (AbsoluteLayout)obtainMainView();
/* 823 */       localAbsoluteLayout.animate(paramIWebview, paramString1, paramString2);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void restore()
/*     */   {
/* 829 */     if ((obtainMainView() instanceof AbsoluteLayout)) {
/* 830 */       AbsoluteLayout localAbsoluteLayout = (AbsoluteLayout)obtainMainView();
/* 831 */       localAbsoluteLayout.restore();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void interceptTouchEvent(boolean paramBoolean)
/*     */   {
/* 837 */     this.interceptTouchEvent = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean isWebviewCovered()
/*     */   {
/* 842 */     IWebview localIWebview = obtainWebView();
/* 843 */     if (null == localIWebview) {
/* 844 */       return true;
/*     */     }
/* 846 */     WebView localWebView = localIWebview.obtainWebview();
/* 847 */     if (null == localWebView) {
/* 848 */       return true;
/*     */     }
/* 850 */     if ((localWebView.getVisibility() != 0) || (localWebView.getParent() == null)) {
/* 851 */       return true;
/*     */     }
/* 853 */     Object localObject = localWebView;
/* 854 */     int i = PlatformUtil.SCREEN_WIDTH(localWebView.getContext());
/* 855 */     int j = PlatformUtil.SCREEN_HEIGHT(localWebView.getContext());
/* 856 */     Rect localRect1 = new Rect(0, 0, i, j);
/* 857 */     Rect localRect2 = new Rect();
/* 858 */     localWebView.getGlobalVisibleRect(localRect2);
/*     */ 
/* 860 */     if (!localRect1.contains(localRect2)) {
/* 861 */       return true;
/*     */     }
/* 863 */     while ((((View)localObject).getParent() instanceof ViewGroup)) {
/* 864 */       ViewGroup localViewGroup = (ViewGroup)((View)localObject).getParent();
/* 865 */       if (localViewGroup.getVisibility() != 0) {
/* 866 */         return true;
/*     */       }
/* 868 */       int k = indexOfViewInParent((View)localObject, localViewGroup);
/* 869 */       for (int m = k + 1; m < localViewGroup.getChildCount(); m++) {
/* 870 */         View localView = localViewGroup.getChildAt(m);
/* 871 */         if ((localView.getVisibility() == 0) && (!(localView instanceof IWaiter)))
/*     */         {
/* 874 */           Rect localRect3 = new Rect();
/* 875 */           localView.getGlobalVisibleRect(localRect3);
/* 876 */           if (localRect3.contains(localRect2))
/* 877 */             return true;
/*     */         }
/*     */       }
/* 880 */       localObject = localViewGroup;
/*     */     }
/* 882 */     if (((View)localObject).getParent() == null) {
/* 883 */       return true;
/*     */     }
/* 885 */     return false;
/*     */   }
/*     */ 
/*     */   private int indexOfViewInParent(View paramView, ViewGroup paramViewGroup)
/*     */   {
/* 890 */     for (int i = 0; (i < paramViewGroup.getChildCount()) && 
/* 891 */       (paramViewGroup.getChildAt(i) != paramView); i++);
/* 894 */     return i;
/*     */   }
/*     */ 
/*     */   public static abstract interface OnAnimationEnd
/*     */   {
/*     */     public abstract void onDone();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.AdaFrameView
 * JD-Core Version:    0.6.2
 */