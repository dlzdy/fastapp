/*    */ package com.dcloud.android.v4.view.accessibility;
/*    */ 
/*    */ import android.view.View;
/*    */ import android.view.accessibility.AccessibilityNodeInfo;
/*    */ import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
/*    */ import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
/*    */ import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;
/*    */ import java.util.List;
/*    */ 
/*    */ class AccessibilityNodeInfoCompatApi21
/*    */ {
/*    */   static List<Object> getActionList(Object paramObject)
/*    */   {
/* 30 */     List localList = ((AccessibilityNodeInfo)paramObject).getActionList();
/* 31 */     return (List)localList;
/*    */   }
/*    */ 
/*    */   static void addAction(Object paramObject1, Object paramObject2) {
/* 35 */     ((AccessibilityNodeInfo)paramObject1).addAction((AccessibilityNodeInfo.AccessibilityAction)paramObject2);
/*    */   }
/*    */ 
/*    */   public static boolean removeAction(Object paramObject1, Object paramObject2) {
/* 39 */     return ((AccessibilityNodeInfo)paramObject1).removeAction((AccessibilityNodeInfo.AccessibilityAction)paramObject2);
/*    */   }
/*    */ 
/*    */   public static Object obtainCollectionInfo(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
/*    */   {
/* 44 */     return AccessibilityNodeInfo.CollectionInfo.obtain(paramInt1, paramInt2, paramBoolean, paramInt3);
/*    */   }
/*    */ 
/*    */   public static Object obtainCollectionItemInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
/*    */   {
/* 50 */     return AccessibilityNodeInfo.CollectionItemInfo.obtain(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1, paramBoolean2);
/*    */   }
/*    */ 
/*    */   public static CharSequence getError(Object paramObject)
/*    */   {
/* 55 */     return ((AccessibilityNodeInfo)paramObject).getError();
/*    */   }
/*    */ 
/*    */   public static void setError(Object paramObject, CharSequence paramCharSequence) {
/* 59 */     ((AccessibilityNodeInfo)paramObject).setError(paramCharSequence);
/*    */   }
/*    */ 
/*    */   public static void setMaxTextLength(Object paramObject, int paramInt) {
/* 63 */     ((AccessibilityNodeInfo)paramObject).setMaxTextLength(paramInt);
/*    */   }
/*    */ 
/*    */   public static int getMaxTextLength(Object paramObject) {
/* 67 */     return ((AccessibilityNodeInfo)paramObject).getMaxTextLength();
/*    */   }
/*    */ 
/*    */   public static Object getWindow(Object paramObject) {
/* 71 */     return ((AccessibilityNodeInfo)paramObject).getWindow();
/*    */   }
/*    */ 
/*    */   public static boolean removeChild(Object paramObject, View paramView) {
/* 75 */     return ((AccessibilityNodeInfo)paramObject).removeChild(paramView);
/*    */   }
/*    */ 
/*    */   public static boolean removeChild(Object paramObject, View paramView, int paramInt) {
/* 79 */     return ((AccessibilityNodeInfo)paramObject).removeChild(paramView, paramInt);
/*    */   }
/*    */ 
/*    */   static Object newAccessibilityAction(int paramInt, CharSequence paramCharSequence)
/*    */   {
/* 89 */     return new AccessibilityNodeInfo.AccessibilityAction(paramInt, paramCharSequence);
/*    */   }
/*    */ 
/*    */   static int getAccessibilityActionId(Object paramObject) {
/* 93 */     return ((AccessibilityNodeInfo.AccessibilityAction)paramObject).getId();
/*    */   }
/*    */ 
/*    */   static CharSequence getAccessibilityActionLabel(Object paramObject) {
/* 97 */     return ((AccessibilityNodeInfo.AccessibilityAction)paramObject).getLabel();
/*    */   }
/*    */ 
/*    */   static class CollectionItemInfo
/*    */   {
/*    */     public static boolean isSelected(Object paramObject)
/*    */     {
/* 84 */       return ((AccessibilityNodeInfo.CollectionItemInfo)paramObject).isSelected();
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityNodeInfoCompatApi21
 * JD-Core Version:    0.6.2
 */