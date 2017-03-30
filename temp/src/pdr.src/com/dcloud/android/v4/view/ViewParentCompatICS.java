/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.view.View;
/*    */ import android.view.ViewParent;
/*    */ import android.view.accessibility.AccessibilityEvent;
/*    */ 
/*    */ public class ViewParentCompatICS
/*    */ {
/*    */   public static boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*    */   {
/* 29 */     return paramViewParent.requestSendAccessibilityEvent(paramView, paramAccessibilityEvent);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewParentCompatICS
 * JD-Core Version:    0.6.2
 */