/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.view.View;
/*    */ import android.view.ViewParent;
/*    */ 
/*    */ class ViewParentCompatKitKat
/*    */ {
/*    */   public static void notifySubtreeAccessibilityStateChanged(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*    */   {
/* 25 */     paramViewParent.notifySubtreeAccessibilityStateChanged(paramView1, paramView2, paramInt);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewParentCompatKitKat
 * JD-Core Version:    0.6.2
 */