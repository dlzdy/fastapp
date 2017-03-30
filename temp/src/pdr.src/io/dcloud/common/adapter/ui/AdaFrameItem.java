/*     */ package io.dcloud.common.adapter.ui;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.res.Configuration;
/*     */ import android.graphics.Canvas;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.view.animation.Animation;
/*     */ import android.widget.AbsoluteLayout.LayoutParams;
/*     */ import io.dcloud.common.adapter.util.AnimOptions;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import io.dcloud.common.adapter.util.ViewRect;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.nineoldandroids.animation.Animator;
/*     */ import io.dcloud.nineoldandroids.animation.Animator.AnimatorListener;
/*     */ import io.dcloud.nineoldandroids.animation.AnimatorSet;
/*     */ import io.dcloud.nineoldandroids.animation.ObjectAnimator;
/*     */ import io.dcloud.nineoldandroids.view.ViewHelper;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class AdaFrameItem
/*     */ {
/*  36 */   public int mNavigationBarHeight = 0;
/*     */ 
/*  38 */   private boolean mAutoPop = false;
/*     */ 
/*  40 */   private boolean mAutoPush = false;
/*     */   static final String TAG = "AdaFrameItem";
/*  42 */   public static int VISIBLE = 0;
/*  43 */   public static int GONE = 8;
/*  44 */   public static int INVISIBLE = 4;
/*  45 */   public boolean mNeedOrientationUpdate = false;
/*  46 */   protected boolean mLongPressed = false;
/*  47 */   protected boolean mPressed = false;
/*     */ 
/*  49 */   protected ViewOptions mViewOptions = null;
/*     */ 
/*  51 */   protected ViewOptions mViewOptions_animate = null;
/*     */ 
/*  53 */   protected ViewOptions mViewOptions_birth = null;
/*     */ 
/*  57 */   protected View mViewImpl = null;
/*  58 */   private Context mContextWrapper = null;
/*  59 */   Animator.AnimatorListener mAnimatorListener = null;
/*  60 */   protected AnimOptions mAnimOptions = null;
/*  61 */   private Animation mAnimation = null;
/*  62 */   public boolean mStranslate = false;
/*  63 */   public int mZIndex = 0;
/*     */ 
/*  65 */   public AdaContainerFrameItem mParentFrameItem = null;
/*     */ 
/*  67 */   public long lastShowTime = 0L;
/*     */ 
/*     */   public void setParentFrameItem(AdaContainerFrameItem paramAdaContainerFrameItem) {
/*  70 */     this.mParentFrameItem = paramAdaContainerFrameItem;
/*     */   }
/*     */ 
/*     */   public AdaContainerFrameItem getParentFrameItem() {
/*  74 */     return this.mParentFrameItem;
/*     */   }
/*     */   protected AdaFrameItem(Context paramContext) {
/*  77 */     this.mContextWrapper = paramContext;
/*  78 */     setFrameOptions(new ViewOptions());
/*  79 */     this.mViewOptions.mTag = this;
/*     */   }
/*     */ 
/*     */   public AnimOptions getAnimOptions()
/*     */   {
/*  88 */     if (this.mAnimOptions == null) {
/*  89 */       this.mAnimOptions = new AnimOptions();
/*     */     }
/*  91 */     return this.mAnimOptions;
/*     */   }
/*     */ 
/*     */   public void setAnimOptions(AnimOptions paramAnimOptions) {
/*  95 */     this.mAnimOptions = paramAnimOptions;
/*     */   }
/*     */ 
/*     */   public void setVisibility(int paramInt) {
/*  99 */     if ((this.mViewImpl != null) && (this.mViewImpl.getVisibility() != paramInt))
/* 100 */       this.mViewImpl.setVisibility(paramInt);
/*     */   }
/*     */ 
/*     */   public final void clearAnimInfo()
/*     */   {
/* 109 */     this.mAnimation = null;
/* 110 */     this.mAnimatorListener = null;
/* 111 */     if (this.mViewImpl != null)
/* 112 */       this.mViewImpl.clearAnimation();
/*     */   }
/*     */ 
/*     */   public void dispose()
/*     */   {
/* 117 */     clearAnimInfo();
/* 118 */     if (this.mViewImpl != null) {
/* 119 */       this.mViewImpl.setVisibility(GONE);
/*     */ 
/* 123 */       ViewGroup localViewGroup = (ViewGroup)this.mViewImpl.getParent();
/* 124 */       if (localViewGroup != null) {
/* 125 */         localViewGroup.removeView(this.mViewImpl);
/*     */       }
/* 127 */       this.mViewImpl = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isDisposed()
/*     */   {
/* 133 */     return this.mViewImpl == null;
/*     */   }
/*     */ 
/*     */   public void makeViewOptions_animate() {
/* 137 */     AdaFrameItem localAdaFrameItem = this;
/* 138 */     String str = null;
/* 139 */     ViewOptions localViewOptions1 = localAdaFrameItem.obtainFrameOptions_Birth();
/* 140 */     ViewOptions localViewOptions2 = localAdaFrameItem.obtainFrameOptions();
/* 141 */     ViewOptions localViewOptions3 = localAdaFrameItem.mViewOptions_animate;
/* 142 */     if (localViewOptions3 == null) {
/* 143 */       localViewOptions3 = localAdaFrameItem.mViewOptions_animate = ViewOptions.createViewOptionsData(localAdaFrameItem.mViewOptions, localAdaFrameItem.mViewOptions.getParentViewRect());
/*     */     }
/* 145 */     if ((this.mAnimOptions.mOption == 0) || (this.mAnimOptions.mOption == 4)) {
/* 146 */       str = this.mAnimOptions.mAnimType;
/* 147 */       str = PdrUtil.isEmpty(str) ? "none" : str;
/* 148 */       if ((PdrUtil.isEquals(str, "slide-in-right")) || (PdrUtil.isEquals(str, "pop-in")))
/* 149 */         localViewOptions2.left = this.mAnimOptions.sScreenWidth;
/* 150 */       else if (PdrUtil.isEquals(str, "slide-in-left"))
/* 151 */         localViewOptions2.left = (-localViewOptions2.width);
/* 152 */       else if (PdrUtil.isEquals(str, "slide-in-top"))
/* 153 */         localViewOptions2.top = (-localViewOptions2.height);
/* 154 */       else if (PdrUtil.isEquals(str, "slide-in-bottom"))
/* 155 */         localViewOptions2.top = this.mAnimOptions.sScreenHeight;
/*     */     }
/* 157 */     else if ((this.mAnimOptions.mOption == 1) || (3 == this.mAnimOptions.mOption)) {
/* 158 */       str = this.mAnimOptions.mAnimType_close;
/* 159 */       if (!AnimOptions.mAnimTypes.containsValue(str)) {
/* 160 */         str = (String)AnimOptions.mAnimTypes.get(this.mAnimOptions.mAnimType);
/*     */       }
/* 162 */       if ((PdrUtil.isEquals(str, "slide-out-right")) || (PdrUtil.isEquals(str, "pop-out")))
/* 163 */         localViewOptions3.left = this.mAnimOptions.sScreenWidth;
/* 164 */       else if (PdrUtil.isEquals(str, "slide-out-left"))
/* 165 */         localViewOptions3.left = (-localViewOptions3.width);
/* 166 */       else if (PdrUtil.isEquals(str, "slide-out-top"))
/* 167 */         localViewOptions3.top = (-localViewOptions3.height);
/* 168 */       else if (PdrUtil.isEquals(str, "slide-out-bottom"))
/* 169 */         localViewOptions3.top = this.mAnimOptions.sScreenHeight;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isAutoPop() {
/* 174 */     return this.mAutoPop;
/*     */   }
/*     */ 
/*     */   public boolean isAutoPush() {
/* 178 */     return this.mAutoPush;
/*     */   }
/*     */ 
/*     */   public void onPopFromStack(boolean paramBoolean)
/*     */   {
/* 185 */     this.mAutoPop = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void onPushToStack(boolean paramBoolean)
/*     */   {
/* 192 */     this.mAutoPush = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean onDispose()
/*     */   {
/* 200 */     return true;
/*     */   }
/*     */ 
/*     */   protected void paint(Canvas paramCanvas)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void scrollTo(int paramInt1, int paramInt2)
/*     */   {
/* 210 */     this.mViewImpl.scrollTo(paramInt1, paramInt2);
/*     */   }
/*     */   public void scrollBy(int paramInt1, int paramInt2) {
/* 213 */     this.mViewImpl.scrollBy(paramInt1, paramInt2);
/*     */   }
/*     */   public void setFrameOptions_Animate(ViewOptions paramViewOptions) {
/* 216 */     this.mViewOptions_animate = paramViewOptions;
/*     */   }
/*     */   public void setFrameOptions_Birth(ViewOptions paramViewOptions) {
/* 219 */     this.mViewOptions_birth = paramViewOptions;
/*     */   }
/*     */   public void setFrameOptions(ViewOptions paramViewOptions) {
/* 222 */     this.mViewOptions = paramViewOptions;
/*     */   }
/*     */   public ViewOptions obtainFrameOptions() {
/* 225 */     return this.mViewOptions;
/*     */   }
/*     */   public ViewOptions obtainFrameOptions_Animate() {
/* 228 */     return this.mViewOptions_animate;
/*     */   }
/*     */   public ViewOptions obtainFrameOptions_Birth() {
/* 231 */     return this.mViewOptions_birth;
/*     */   }
/*     */ 
/*     */   public void updateViewRect(AdaFrameItem paramAdaFrameItem, int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
/* 235 */     updateViewRect(paramAdaFrameItem, paramArrayOfInt1, paramArrayOfInt2, new boolean[] { true, true, true, true }, new boolean[] { true, true, true, false });
/*     */   }
/*     */ 
/*     */   public void updateViewRect(AdaFrameItem paramAdaFrameItem, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2) {
/* 239 */     this.mViewOptions.left = paramArrayOfInt1[0];
/* 240 */     this.mViewOptions.checkValueIsPercentage("left", this.mViewOptions.left, paramArrayOfInt2[0], paramArrayOfBoolean1[0], paramArrayOfBoolean2[0]);
/* 241 */     this.mViewOptions.top = paramArrayOfInt1[1];
/* 242 */     this.mViewOptions.checkValueIsPercentage("top", this.mViewOptions.top, paramArrayOfInt2[1], paramArrayOfBoolean1[1], paramArrayOfBoolean2[1]);
/* 243 */     this.mViewOptions.width = paramArrayOfInt1[2];
/* 244 */     this.mViewOptions.checkValueIsPercentage("width", this.mViewOptions.width, paramArrayOfInt2[0], paramArrayOfBoolean1[2], paramArrayOfBoolean2[2]);
/* 245 */     this.mViewOptions.height = paramArrayOfInt1[3];
/* 246 */     this.mViewOptions.checkValueIsPercentage("height", this.mViewOptions.height, paramArrayOfInt2[1], paramArrayOfBoolean1[3], paramArrayOfBoolean2[3]);
/* 247 */     this.mViewOptions.setParentViewRect(paramAdaFrameItem.mViewOptions);
/*     */   }
/*     */ 
/*     */   public void setAnimatorLinstener(Animator.AnimatorListener paramAnimatorListener) {
/* 251 */     this.mAnimatorListener = paramAnimatorListener;
/*     */   }
/*     */   public void startAnimator(int paramInt) {
/* 254 */     if (this.mAnimOptions != null) {
/* 255 */       this.mAnimOptions.mUserFrameItem = this;
/* 256 */       Animator localAnimator = this.mAnimOptions.createAnimation();
/* 257 */       localAnimator.addListener(this.mAnimatorListener);
/* 258 */       localAnimator.setTarget(obtainMainView());
/* 259 */       localAnimator.start();
/*     */     }
/*     */   }
/*     */ 
/* 263 */   public AdaFrameItem findLastFrameItem(AdaFrameItem paramAdaFrameItem) { return null; }
/*     */ 
/*     */   public AdaFrameItem getParent()
/*     */   {
/* 267 */     return null;
/*     */   }
/*     */ 
/*     */   public Context getContext()
/*     */   {
/* 275 */     return this.mContextWrapper;
/*     */   }
/*     */ 
/*     */   public Activity getActivity()
/*     */   {
/* 282 */     return (Activity)this.mContextWrapper;
/*     */   }
/*     */   public void setBgcolor(int paramInt) {
/* 285 */     this.mViewImpl.setBackgroundColor(paramInt);
/*     */   }
/*     */ 
/*     */   public void setMainView(View paramView)
/*     */   {
/* 294 */     this.mViewImpl = paramView;
/*     */   }
/*     */ 
/*     */   public void setWebView(WebViewImpl paramWebViewImpl)
/*     */   {
/* 302 */     this.mViewImpl = paramWebViewImpl;
/*     */   }
/*     */ 
/*     */   public View getWebView() {
/* 306 */     return this.mViewImpl;
/*     */   }
/*     */ 
/*     */   protected void useDefaultMainView()
/*     */   {
/* 315 */     setMainView(new DefaultView(this.mContextWrapper));
/*     */   }
/*     */ 
/*     */   public View obtainMainView()
/*     */   {
/* 323 */     return this.mViewImpl;
/*     */   }
/*     */ 
/*     */   public void resize() {
/* 327 */     onResize();
/*     */   }
/*     */   protected void onResize() {
/* 330 */     if ((!this.mNeedOrientationUpdate) || (isDisposed())) return;
/* 331 */     AdaFrameItem localAdaFrameItem = this;
/* 332 */     ViewOptions localViewOptions = localAdaFrameItem.mViewOptions;
/* 333 */     boolean bool = this instanceof AdaFrameView;
/* 334 */     int i = (bool) && (((AdaFrameView)this).isChildOfFrameView) ? 1 : 0;
/* 335 */     localViewOptions.onScreenChanged();
/* 336 */     View localView = localAdaFrameItem.obtainMainView();
/* 337 */     ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
/* 338 */     if ((!bool) || (i != 0)) {
/* 339 */       if ((localLayoutParams instanceof AbsoluteLayout.LayoutParams))
/*     */       {
/* 343 */         if (i != 0) {
/* 344 */           ((AbsoluteLayout.LayoutParams)localLayoutParams).x = localViewOptions.left;
/* 345 */           ((AbsoluteLayout.LayoutParams)localLayoutParams).y = localViewOptions.top;
/*     */         } else {
/* 347 */           ((AbsoluteLayout.LayoutParams)localLayoutParams).x = localViewOptions.left;
/* 348 */           ((AbsoluteLayout.LayoutParams)localLayoutParams).y = localViewOptions.top;
/*     */         }
/*     */       }
/*     */ 
/* 352 */       localLayoutParams.width = localViewOptions.width;
/* 353 */       localLayoutParams.height = localViewOptions.height;
/* 354 */     } else if ((localLayoutParams != null) && (bool)) {
/* 355 */       localLayoutParams.height = (localLayoutParams.height == -1 ? -1 : localViewOptions.height);
/* 356 */       localLayoutParams.width = (localLayoutParams.width == -1 ? -1 : localViewOptions.width);
/* 357 */       ViewHelper.setX(localView, localViewOptions.left);
/* 358 */       ViewHelper.setY(localView, localViewOptions.top);
/* 359 */       localView.requestLayout();
/* 360 */       localView.postInvalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class LayoutParamsUtil
/*     */   {
/*     */     public static ViewGroup.LayoutParams createLayoutParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     {
/* 414 */       return new AbsoluteLayout.LayoutParams(paramInt3, paramInt4, paramInt1, paramInt2);
/*     */     }
/*     */ 
/*     */     public static void setViewLayoutParams(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 418 */       ViewHelper.setX(paramView, paramInt1);
/* 419 */       ViewHelper.setY(paramView, paramInt2);
/*     */ 
/* 423 */       ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
/* 424 */       if (localLayoutParams == null) {
/* 425 */         localLayoutParams = new ViewGroup.LayoutParams(paramInt3, paramInt4);
/* 426 */         paramView.setLayoutParams(localLayoutParams);
/*     */       } else {
/* 428 */         localLayoutParams.width = paramInt3;
/* 429 */         localLayoutParams.height = paramInt4;
/*     */       }
/*     */     }
/*     */ 
/*     */     private static void preAndroid30SetViewLayoutParams(View paramView, final int paramInt1, final int paramInt2) {
/* 434 */       if (((paramInt1 != 0) || (paramInt2 != 0)) && (DeviceInfo.sDeviceSdkVer <= 10)) {
/* 435 */         AnimatorSet localAnimatorSet = new AnimatorSet();
/*     */ 
/* 437 */         ObjectAnimator localObjectAnimator = new ObjectAnimator();
/* 438 */         localObjectAnimator.setPropertyName("x");
/* 439 */         localObjectAnimator.setFloatValues(new float[] { paramInt1 - 1, paramInt1 });
/* 440 */         localAnimatorSet.playTogether(new Animator[] { localObjectAnimator });
/*     */ 
/* 443 */         localObjectAnimator = new ObjectAnimator();
/* 444 */         localObjectAnimator.setPropertyName("y");
/* 445 */         localObjectAnimator.setFloatValues(new float[] { paramInt2 - 1, paramInt2 });
/* 446 */         localAnimatorSet.playTogether(new Animator[] { localObjectAnimator });
/*     */ 
/* 448 */         localAnimatorSet.setDuration(5L);
/* 449 */         localAnimatorSet.setTarget(paramView);
/*     */ 
/* 451 */         localAnimatorSet.addListener(new Animator.AnimatorListener()
/*     */         {
/*     */           public void onAnimationStart(Animator paramAnonymousAnimator)
/*     */           {
/*     */           }
/*     */ 
/*     */           public void onAnimationRepeat(Animator paramAnonymousAnimator)
/*     */           {
/*     */           }
/*     */ 
/*     */           public void onAnimationEnd(Animator paramAnonymousAnimator)
/*     */           {
/*     */           }
/*     */ 
/*     */           public void onAnimationCancel(Animator paramAnonymousAnimator)
/*     */           {
/* 470 */             this.val$view.postDelayed(new Runnable()
/*     */             {
/*     */               public void run() {
/* 473 */                 ViewHelper.setX(AdaFrameItem.LayoutParamsUtil.1.this.val$view, AdaFrameItem.LayoutParamsUtil.1.this.val$left);
/* 474 */                 ViewHelper.setY(AdaFrameItem.LayoutParamsUtil.1.this.val$view, AdaFrameItem.LayoutParamsUtil.1.this.val$top);
/*     */               }
/*     */             }
/*     */             , 10L);
/*     */           }
/*     */         });
/* 479 */         localAnimatorSet.start();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DefaultView extends View
/*     */   {
/*     */     public DefaultView(Context arg2)
/*     */     {
/* 374 */       super();
/*     */     }
/*     */ 
/*     */     protected void onDraw(Canvas paramCanvas)
/*     */     {
/* 379 */       AdaFrameItem.this.paint(paramCanvas);
/*     */     }
/*     */ 
/*     */     protected void onConfigurationChanged(Configuration paramConfiguration)
/*     */     {
/* 384 */       super.onConfigurationChanged(paramConfiguration);
/* 385 */       AdaFrameItem.this.onResize();
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.AdaFrameItem
 * JD-Core Version:    0.6.2
 */