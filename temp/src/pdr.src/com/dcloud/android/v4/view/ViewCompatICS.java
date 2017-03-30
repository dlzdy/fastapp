/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.view.View;
/*    */ import android.view.View.AccessibilityDelegate;
/*    */ import android.view.accessibility.AccessibilityEvent;
/*    */ import android.view.accessibility.AccessibilityNodeInfo;
/*    */ import com.dcloud.android.annotation.Nullable;
/*    */ 
/*    */ class ViewCompatICS
/*    */ {
/*    */   public static boolean canScrollHorizontally(View paramView, int paramInt)
/*    */   {
/* 31 */     return paramView.canScrollHorizontally(paramInt);
/*    */   }
/*    */ 
/*    */   public static boolean canScrollVertically(View paramView, int paramInt) {
/* 35 */     return paramView.canScrollVertically(paramInt);
/*    */   }
/*    */ 
/*    */   public static void setAccessibilityDelegate(View paramView, @Nullable Object paramObject) {
/* 39 */     paramView.setAccessibilityDelegate((View.AccessibilityDelegate)paramObject);
/*    */   }
/*    */ 
/*    */   public static void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent) {
/* 43 */     paramView.onPopulateAccessibilityEvent(paramAccessibilityEvent);
/*    */   }
/*    */ 
/*    */   public static void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent) {
/* 47 */     paramView.onInitializeAccessibilityEvent(paramAccessibilityEvent);
/*    */   }
/*    */ 
/*    */   public static void onInitializeAccessibilityNodeInfo(View paramView, Object paramObject) {
/* 51 */     paramView.onInitializeAccessibilityNodeInfo((AccessibilityNodeInfo)paramObject);
/*    */   }
/*    */ 
/*    */   public static void setFitsSystemWindows(View paramView, boolean paramBoolean) {
/* 55 */     paramView.setFitsSystemWindows(paramBoolean);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewCompatICS
 * JD-Core Version:    0.6.2
 */