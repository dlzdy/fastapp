/*    */ package com.dcloud.android.v4.view.accessibility;
/*    */ 
/*    */ import android.view.accessibility.AccessibilityEvent;
/*    */ 
/*    */ class AccessibilityEventCompatKitKat
/*    */ {
/*    */   public static void setContentChangeTypes(AccessibilityEvent paramAccessibilityEvent, int paramInt)
/*    */   {
/* 23 */     paramAccessibilityEvent.setContentChangeTypes(paramInt);
/*    */   }
/*    */ 
/*    */   public static int getContentChangeTypes(AccessibilityEvent paramAccessibilityEvent) {
/* 27 */     return paramAccessibilityEvent.getContentChangeTypes();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityEventCompatKitKat
 * JD-Core Version:    0.6.2
 */