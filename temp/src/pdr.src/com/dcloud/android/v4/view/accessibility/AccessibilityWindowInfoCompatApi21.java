/*    */ package com.dcloud.android.v4.view.accessibility;
/*    */ 
/*    */ import android.graphics.Rect;
/*    */ import android.view.accessibility.AccessibilityWindowInfo;
/*    */ 
/*    */ class AccessibilityWindowInfoCompatApi21
/*    */ {
/*    */   public static Object obtain()
/*    */   {
/* 28 */     return AccessibilityWindowInfo.obtain();
/*    */   }
/*    */ 
/*    */   public static Object obtain(Object paramObject) {
/* 32 */     return AccessibilityWindowInfo.obtain((AccessibilityWindowInfo)paramObject);
/*    */   }
/*    */ 
/*    */   public static int getType(Object paramObject)
/*    */   {
/* 37 */     return ((AccessibilityWindowInfo)paramObject).getType();
/*    */   }
/*    */ 
/*    */   public static int getLayer(Object paramObject) {
/* 41 */     return ((AccessibilityWindowInfo)paramObject).getLayer();
/*    */   }
/*    */ 
/*    */   public static Object getRoot(Object paramObject) {
/* 45 */     return ((AccessibilityWindowInfo)paramObject).getRoot();
/*    */   }
/*    */ 
/*    */   public static Object getParent(Object paramObject) {
/* 49 */     return ((AccessibilityWindowInfo)paramObject).getParent();
/*    */   }
/*    */ 
/*    */   public static int getId(Object paramObject) {
/* 53 */     return ((AccessibilityWindowInfo)paramObject).getId();
/*    */   }
/*    */ 
/*    */   public static void getBoundsInScreen(Object paramObject, Rect paramRect) {
/* 57 */     ((AccessibilityWindowInfo)paramObject).getBoundsInScreen(paramRect);
/*    */   }
/*    */ 
/*    */   public static boolean isActive(Object paramObject) {
/* 61 */     return ((AccessibilityWindowInfo)paramObject).isActive();
/*    */   }
/*    */ 
/*    */   public static boolean isFocused(Object paramObject) {
/* 65 */     return ((AccessibilityWindowInfo)paramObject).isFocused();
/*    */   }
/*    */ 
/*    */   public static boolean isAccessibilityFocused(Object paramObject) {
/* 69 */     return ((AccessibilityWindowInfo)paramObject).isAccessibilityFocused();
/*    */   }
/*    */ 
/*    */   public static int getChildCount(Object paramObject) {
/* 73 */     return ((AccessibilityWindowInfo)paramObject).getChildCount();
/*    */   }
/*    */ 
/*    */   public static Object getChild(Object paramObject, int paramInt) {
/* 77 */     return ((AccessibilityWindowInfo)paramObject).getChild(paramInt);
/*    */   }
/*    */ 
/*    */   public static void recycle(Object paramObject) {
/* 81 */     ((AccessibilityWindowInfo)paramObject).recycle();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityWindowInfoCompatApi21
 * JD-Core Version:    0.6.2
 */