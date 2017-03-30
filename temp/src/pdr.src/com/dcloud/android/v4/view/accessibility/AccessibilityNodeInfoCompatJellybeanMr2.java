/*    */ package com.dcloud.android.v4.view.accessibility;
/*    */ 
/*    */ import android.view.accessibility.AccessibilityNodeInfo;
/*    */ import java.util.List;
/*    */ 
/*    */ class AccessibilityNodeInfoCompatJellybeanMr2
/*    */ {
/*    */   public static void setViewIdResourceName(Object paramObject, String paramString)
/*    */   {
/* 26 */     ((AccessibilityNodeInfo)paramObject).setViewIdResourceName(paramString);
/*    */   }
/*    */ 
/*    */   public static String getViewIdResourceName(Object paramObject) {
/* 30 */     return ((AccessibilityNodeInfo)paramObject).getViewIdResourceName();
/*    */   }
/*    */ 
/*    */   public static List<Object> findAccessibilityNodeInfosByViewId(Object paramObject, String paramString)
/*    */   {
/* 35 */     List localList = ((AccessibilityNodeInfo)paramObject).findAccessibilityNodeInfosByViewId(paramString);
/* 36 */     return (List)localList;
/*    */   }
/*    */ 
/*    */   public static void setTextSelection(Object paramObject, int paramInt1, int paramInt2) {
/* 40 */     ((AccessibilityNodeInfo)paramObject).setTextSelection(paramInt1, paramInt2);
/*    */   }
/*    */ 
/*    */   public static int getTextSelectionStart(Object paramObject) {
/* 44 */     return ((AccessibilityNodeInfo)paramObject).getTextSelectionStart();
/*    */   }
/*    */ 
/*    */   public static int getTextSelectionEnd(Object paramObject) {
/* 48 */     return ((AccessibilityNodeInfo)paramObject).getTextSelectionEnd();
/*    */   }
/*    */ 
/*    */   public static boolean isEditable(Object paramObject) {
/* 52 */     return ((AccessibilityNodeInfo)paramObject).isEditable();
/*    */   }
/*    */ 
/*    */   public static void setEditable(Object paramObject, boolean paramBoolean) {
/* 56 */     ((AccessibilityNodeInfo)paramObject).setEditable(paramBoolean);
/*    */   }
/*    */ 
/*    */   public static boolean refresh(Object paramObject) {
/* 60 */     return ((AccessibilityNodeInfo)paramObject).refresh();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityNodeInfoCompatJellybeanMr2
 * JD-Core Version:    0.6.2
 */