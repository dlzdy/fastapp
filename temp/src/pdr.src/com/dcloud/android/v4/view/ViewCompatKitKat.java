/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.view.View;
/*    */ 
/*    */ class ViewCompatKitKat
/*    */ {
/*    */   public static int getAccessibilityLiveRegion(View paramView)
/*    */   {
/* 26 */     return paramView.getAccessibilityLiveRegion();
/*    */   }
/*    */ 
/*    */   public static void setAccessibilityLiveRegion(View paramView, int paramInt) {
/* 30 */     paramView.setAccessibilityLiveRegion(paramInt);
/*    */   }
/*    */ 
/*    */   public static boolean isLaidOut(View paramView) {
/* 34 */     return paramView.isLaidOut();
/*    */   }
/*    */ 
/*    */   public static boolean isAttachedToWindow(View paramView) {
/* 38 */     return paramView.isAttachedToWindow();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewCompatKitKat
 * JD-Core Version:    0.6.2
 */