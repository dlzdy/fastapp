/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.os.Bundle;
/*    */ import android.view.View;
/*    */ import android.view.ViewParent;
/*    */ 
/*    */ class ViewCompatJB
/*    */ {
/*    */   public static boolean hasTransientState(View paramView)
/*    */   {
/* 29 */     return paramView.hasTransientState();
/*    */   }
/*    */ 
/*    */   public static void setHasTransientState(View paramView, boolean paramBoolean) {
/* 33 */     paramView.setHasTransientState(paramBoolean);
/*    */   }
/*    */ 
/*    */   public static void postInvalidateOnAnimation(View paramView) {
/* 37 */     paramView.postInvalidateOnAnimation();
/*    */   }
/*    */ 
/*    */   public static void postInvalidateOnAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*    */   {
/* 42 */     paramView.postInvalidate(paramInt1, paramInt2, paramInt3, paramInt4);
/*    */   }
/*    */ 
/*    */   public static void postOnAnimation(View paramView, Runnable paramRunnable) {
/* 46 */     paramView.postOnAnimation(paramRunnable);
/*    */   }
/*    */ 
/*    */   public static void postOnAnimationDelayed(View paramView, Runnable paramRunnable, long paramLong) {
/* 50 */     paramView.postOnAnimationDelayed(paramRunnable, paramLong);
/*    */   }
/*    */ 
/*    */   public static int getImportantForAccessibility(View paramView) {
/* 54 */     return paramView.getImportantForAccessibility();
/*    */   }
/*    */ 
/*    */   public static void setImportantForAccessibility(View paramView, int paramInt) {
/* 58 */     paramView.setImportantForAccessibility(paramInt);
/*    */   }
/*    */ 
/*    */   public static boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle) {
/* 62 */     return paramView.performAccessibilityAction(paramInt, paramBundle);
/*    */   }
/*    */ 
/*    */   public static Object getAccessibilityNodeProvider(View paramView) {
/* 66 */     return paramView.getAccessibilityNodeProvider();
/*    */   }
/*    */ 
/*    */   public static ViewParent getParentForAccessibility(View paramView) {
/* 70 */     return paramView.getParentForAccessibility();
/*    */   }
/*    */ 
/*    */   public static int getMinimumWidth(View paramView) {
/* 74 */     return paramView.getMinimumWidth();
/*    */   }
/*    */ 
/*    */   public static int getMinimumHeight(View paramView) {
/* 78 */     return paramView.getMinimumHeight();
/*    */   }
/*    */ 
/*    */   public static void requestApplyInsets(View paramView) {
/* 82 */     paramView.requestFitSystemWindows();
/*    */   }
/*    */ 
/*    */   public static boolean getFitsSystemWindows(View paramView) {
/* 86 */     return paramView.getFitsSystemWindows();
/*    */   }
/*    */ 
/*    */   public static boolean hasOverlappingRendering(View paramView) {
/* 90 */     return paramView.hasOverlappingRendering();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewCompatJB
 * JD-Core Version:    0.6.2
 */