/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.animation.Animator;
/*     */ import android.animation.AnimatorListenerAdapter;
/*     */ import android.view.View;
/*     */ import android.view.ViewPropertyAnimator;
/*     */ import android.view.animation.Interpolator;
/*     */ 
/*     */ class ViewPropertyAnimatorCompatICS
/*     */ {
/*     */   public static void setDuration(View paramView, long paramLong)
/*     */   {
/*  26 */     paramView.animate().setDuration(paramLong);
/*     */   }
/*     */ 
/*     */   public static void alpha(View paramView, float paramFloat) {
/*  30 */     paramView.animate().alpha(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void translationX(View paramView, float paramFloat) {
/*  34 */     paramView.animate().translationX(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void translationY(View paramView, float paramFloat) {
/*  38 */     paramView.animate().translationY(paramFloat);
/*     */   }
/*     */ 
/*     */   public static long getDuration(View paramView) {
/*  42 */     return paramView.animate().getDuration();
/*     */   }
/*     */ 
/*     */   public static void setInterpolator(View paramView, Interpolator paramInterpolator) {
/*  46 */     paramView.animate().setInterpolator(paramInterpolator);
/*     */   }
/*     */ 
/*     */   public static void setStartDelay(View paramView, long paramLong) {
/*  50 */     paramView.animate().setStartDelay(paramLong);
/*     */   }
/*     */ 
/*     */   public static long getStartDelay(View paramView) {
/*  54 */     return paramView.animate().getStartDelay();
/*     */   }
/*     */ 
/*     */   public static void alphaBy(View paramView, float paramFloat) {
/*  58 */     paramView.animate().alphaBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void rotation(View paramView, float paramFloat) {
/*  62 */     paramView.animate().rotation(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void rotationBy(View paramView, float paramFloat) {
/*  66 */     paramView.animate().rotationBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void rotationX(View paramView, float paramFloat) {
/*  70 */     paramView.animate().rotationX(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void rotationXBy(View paramView, float paramFloat) {
/*  74 */     paramView.animate().rotationXBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void rotationY(View paramView, float paramFloat) {
/*  78 */     paramView.animate().rotationY(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void rotationYBy(View paramView, float paramFloat) {
/*  82 */     paramView.animate().rotationYBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void scaleX(View paramView, float paramFloat) {
/*  86 */     paramView.animate().scaleX(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void scaleXBy(View paramView, float paramFloat) {
/*  90 */     paramView.animate().scaleXBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void scaleY(View paramView, float paramFloat) {
/*  94 */     paramView.animate().scaleY(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void scaleYBy(View paramView, float paramFloat) {
/*  98 */     paramView.animate().scaleYBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void cancel(View paramView) {
/* 102 */     paramView.animate().cancel();
/*     */   }
/*     */ 
/*     */   public static void x(View paramView, float paramFloat) {
/* 106 */     paramView.animate().x(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void xBy(View paramView, float paramFloat) {
/* 110 */     paramView.animate().xBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void y(View paramView, float paramFloat) {
/* 114 */     paramView.animate().y(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void yBy(View paramView, float paramFloat) {
/* 118 */     paramView.animate().yBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void translationXBy(View paramView, float paramFloat) {
/* 122 */     paramView.animate().translationXBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void translationYBy(View paramView, float paramFloat) {
/* 126 */     paramView.animate().translationYBy(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void start(View paramView) {
/* 130 */     paramView.animate().start();
/*     */   }
/*     */ 
/*     */   public static void setListener(final View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
/*     */   {
/* 135 */     if (paramViewPropertyAnimatorListener != null)
/* 136 */       paramView.animate().setListener(new AnimatorListenerAdapter()
/*     */       {
/*     */         public void onAnimationCancel(Animator paramAnonymousAnimator) {
/* 139 */           this.val$listener.onAnimationCancel(paramView);
/*     */         }
/*     */ 
/*     */         public void onAnimationEnd(Animator paramAnonymousAnimator)
/*     */         {
/* 144 */           this.val$listener.onAnimationEnd(paramView);
/*     */         }
/*     */ 
/*     */         public void onAnimationStart(Animator paramAnonymousAnimator)
/*     */         {
/* 149 */           this.val$listener.onAnimationStart(paramView);
/*     */         }
/*     */       });
/*     */     else
/* 153 */       paramView.animate().setListener(null);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewPropertyAnimatorCompatICS
 * JD-Core Version:    0.6.2
 */