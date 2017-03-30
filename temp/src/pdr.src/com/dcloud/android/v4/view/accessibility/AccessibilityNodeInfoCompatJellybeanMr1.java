/*    */ package com.dcloud.android.v4.view.accessibility;
/*    */ 
/*    */ import android.view.View;
/*    */ import android.view.accessibility.AccessibilityNodeInfo;
/*    */ 
/*    */ class AccessibilityNodeInfoCompatJellybeanMr1
/*    */ {
/*    */   public static void setLabelFor(Object paramObject, View paramView)
/*    */   {
/* 25 */     ((AccessibilityNodeInfo)paramObject).setLabelFor(paramView);
/*    */   }
/*    */ 
/*    */   public static void setLabelFor(Object paramObject, View paramView, int paramInt) {
/* 29 */     ((AccessibilityNodeInfo)paramObject).setLabelFor(paramView, paramInt);
/*    */   }
/*    */ 
/*    */   public static Object getLabelFor(Object paramObject) {
/* 33 */     return ((AccessibilityNodeInfo)paramObject).getLabelFor();
/*    */   }
/*    */ 
/*    */   public static void setLabeledBy(Object paramObject, View paramView) {
/* 37 */     ((AccessibilityNodeInfo)paramObject).setLabeledBy(paramView);
/*    */   }
/*    */ 
/*    */   public static void setLabeledBy(Object paramObject, View paramView, int paramInt) {
/* 41 */     ((AccessibilityNodeInfo)paramObject).setLabeledBy(paramView, paramInt);
/*    */   }
/*    */ 
/*    */   public static Object getLabeledBy(Object paramObject) {
/* 45 */     return ((AccessibilityNodeInfo)paramObject).getLabeledBy();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityNodeInfoCompatJellybeanMr1
 * JD-Core Version:    0.6.2
 */