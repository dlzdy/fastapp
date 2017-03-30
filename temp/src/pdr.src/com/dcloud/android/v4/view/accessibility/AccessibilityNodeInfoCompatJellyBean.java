/*    */ package com.dcloud.android.v4.view.accessibility;
/*    */ 
/*    */ import android.os.Bundle;
/*    */ import android.view.View;
/*    */ import android.view.accessibility.AccessibilityNodeInfo;
/*    */ 
/*    */ class AccessibilityNodeInfoCompatJellyBean
/*    */ {
/*    */   public static void addChild(Object paramObject, View paramView, int paramInt)
/*    */   {
/* 29 */     ((AccessibilityNodeInfo)paramObject).addChild(paramView, paramInt);
/*    */   }
/*    */ 
/*    */   public static void setSource(Object paramObject, View paramView, int paramInt) {
/* 33 */     ((AccessibilityNodeInfo)paramObject).setSource(paramView, paramInt);
/*    */   }
/*    */ 
/*    */   public static boolean isVisibleToUser(Object paramObject) {
/* 37 */     return ((AccessibilityNodeInfo)paramObject).isVisibleToUser();
/*    */   }
/*    */ 
/*    */   public static void setVisibleToUser(Object paramObject, boolean paramBoolean) {
/* 41 */     ((AccessibilityNodeInfo)paramObject).setVisibleToUser(paramBoolean);
/*    */   }
/*    */ 
/*    */   public static boolean performAction(Object paramObject, int paramInt, Bundle paramBundle) {
/* 45 */     return ((AccessibilityNodeInfo)paramObject).performAction(paramInt, paramBundle);
/*    */   }
/*    */ 
/*    */   public static void setMovementGranularities(Object paramObject, int paramInt) {
/* 49 */     ((AccessibilityNodeInfo)paramObject).setMovementGranularities(paramInt);
/*    */   }
/*    */ 
/*    */   public static int getMovementGranularities(Object paramObject) {
/* 53 */     return ((AccessibilityNodeInfo)paramObject).getMovementGranularities();
/*    */   }
/*    */ 
/*    */   public static Object obtain(View paramView, int paramInt) {
/* 57 */     return AccessibilityNodeInfo.obtain(paramView, paramInt);
/*    */   }
/*    */ 
/*    */   public static Object findFocus(Object paramObject, int paramInt) {
/* 61 */     return ((AccessibilityNodeInfo)paramObject).findFocus(paramInt);
/*    */   }
/*    */ 
/*    */   public static Object focusSearch(Object paramObject, int paramInt) {
/* 65 */     return ((AccessibilityNodeInfo)paramObject).focusSearch(paramInt);
/*    */   }
/*    */ 
/*    */   public static void setParent(Object paramObject, View paramView, int paramInt) {
/* 69 */     ((AccessibilityNodeInfo)paramObject).setParent(paramView, paramInt);
/*    */   }
/*    */ 
/*    */   public static boolean isAccessibilityFocused(Object paramObject) {
/* 73 */     return ((AccessibilityNodeInfo)paramObject).isAccessibilityFocused();
/*    */   }
/*    */ 
/*    */   public static void setAccesibilityFocused(Object paramObject, boolean paramBoolean) {
/* 77 */     ((AccessibilityNodeInfo)paramObject).setAccessibilityFocused(paramBoolean);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityNodeInfoCompatJellyBean
 * JD-Core Version:    0.6.2
 */