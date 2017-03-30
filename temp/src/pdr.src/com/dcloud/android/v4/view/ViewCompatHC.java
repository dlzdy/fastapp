/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.animation.ValueAnimator;
/*     */ import android.graphics.Paint;
/*     */ import android.view.View;
/*     */ 
/*     */ class ViewCompatHC
/*     */ {
/*     */   static long getFrameTime()
/*     */   {
/*  25 */     return ValueAnimator.getFrameDelay();
/*     */   }
/*     */ 
/*     */   public static float getAlpha(View paramView) {
/*  29 */     return paramView.getAlpha();
/*     */   }
/*     */ 
/*     */   public static void setLayerType(View paramView, int paramInt, Paint paramPaint) {
/*  33 */     paramView.setLayerType(paramInt, paramPaint);
/*     */   }
/*     */ 
/*     */   public static int getLayerType(View paramView) {
/*  37 */     return paramView.getLayerType();
/*     */   }
/*     */ 
/*     */   public static int resolveSizeAndState(int paramInt1, int paramInt2, int paramInt3) {
/*  41 */     return View.resolveSizeAndState(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */ 
/*     */   public static int getMeasuredWidthAndState(View paramView) {
/*  45 */     return paramView.getMeasuredWidthAndState();
/*     */   }
/*     */ 
/*     */   public static int getMeasuredHeightAndState(View paramView) {
/*  49 */     return paramView.getMeasuredHeightAndState();
/*     */   }
/*     */ 
/*     */   public static int getMeasuredState(View paramView) {
/*  53 */     return paramView.getMeasuredState();
/*     */   }
/*     */ 
/*     */   public static float getTranslationX(View paramView) {
/*  57 */     return paramView.getTranslationX();
/*     */   }
/*     */ 
/*     */   public static float getTranslationY(View paramView) {
/*  61 */     return paramView.getTranslationY();
/*     */   }
/*     */ 
/*     */   public static float getX(View paramView) {
/*  65 */     return paramView.getX();
/*     */   }
/*     */ 
/*     */   public static float getY(View paramView) {
/*  69 */     return paramView.getY();
/*     */   }
/*     */ 
/*     */   public static float getRotation(View paramView) {
/*  73 */     return paramView.getRotation();
/*     */   }
/*     */ 
/*     */   public static float getRotationX(View paramView) {
/*  77 */     return paramView.getRotationX();
/*     */   }
/*     */ 
/*     */   public static float getRotationY(View paramView) {
/*  81 */     return paramView.getRotationY();
/*     */   }
/*     */ 
/*     */   public static float getScaleX(View paramView) {
/*  85 */     return paramView.getScaleX();
/*     */   }
/*     */ 
/*     */   public static float getScaleY(View paramView) {
/*  89 */     return paramView.getScaleY();
/*     */   }
/*     */ 
/*     */   public static void setTranslationX(View paramView, float paramFloat) {
/*  93 */     paramView.setTranslationX(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setTranslationY(View paramView, float paramFloat) {
/*  97 */     paramView.setTranslationY(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setAlpha(View paramView, float paramFloat) {
/* 101 */     paramView.setAlpha(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setX(View paramView, float paramFloat) {
/* 105 */     paramView.setX(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setY(View paramView, float paramFloat) {
/* 109 */     paramView.setY(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setRotation(View paramView, float paramFloat) {
/* 113 */     paramView.setRotation(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setRotationX(View paramView, float paramFloat) {
/* 117 */     paramView.setRotationX(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setRotationY(View paramView, float paramFloat) {
/* 121 */     paramView.setRotationY(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setScaleX(View paramView, float paramFloat) {
/* 125 */     paramView.setScaleX(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setScaleY(View paramView, float paramFloat) {
/* 129 */     paramView.setScaleY(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setPivotX(View paramView, float paramFloat) {
/* 133 */     paramView.setPivotX(paramFloat);
/*     */   }
/*     */ 
/*     */   public static void setPivotY(View paramView, float paramFloat) {
/* 137 */     paramView.setPivotY(paramFloat);
/*     */   }
/*     */ 
/*     */   public static float getPivotX(View paramView) {
/* 141 */     return paramView.getPivotX();
/*     */   }
/*     */ 
/*     */   public static float getPivotY(View paramView) {
/* 145 */     return paramView.getPivotY();
/*     */   }
/*     */ 
/*     */   public static void jumpDrawablesToCurrentState(View paramView) {
/* 149 */     paramView.jumpDrawablesToCurrentState();
/*     */   }
/*     */ 
/*     */   public static void setSaveFromParentEnabled(View paramView, boolean paramBoolean) {
/* 153 */     paramView.setSaveFromParentEnabled(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setActivated(View paramView, boolean paramBoolean) {
/* 157 */     paramView.setActivated(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static int combineMeasuredStates(int paramInt1, int paramInt2) {
/* 161 */     return View.combineMeasuredStates(paramInt1, paramInt2);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewCompatHC
 * JD-Core Version:    0.6.2
 */