/*     */ package com.dcloud.android.v4.view.accessibility;
/*     */ 
/*     */ import android.os.Bundle;
/*     */ import android.view.accessibility.AccessibilityNodeInfo;
/*     */ import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
/*     */ import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;
/*     */ import android.view.accessibility.AccessibilityNodeInfo.RangeInfo;
/*     */ 
/*     */ class AccessibilityNodeInfoCompatKitKat
/*     */ {
/*     */   static int getLiveRegion(Object paramObject)
/*     */   {
/*  27 */     return ((AccessibilityNodeInfo)paramObject).getLiveRegion();
/*     */   }
/*     */ 
/*     */   static void setLiveRegion(Object paramObject, int paramInt) {
/*  31 */     ((AccessibilityNodeInfo)paramObject).setLiveRegion(paramInt);
/*     */   }
/*     */ 
/*     */   static Object getCollectionInfo(Object paramObject) {
/*  35 */     return ((AccessibilityNodeInfo)paramObject).getCollectionInfo();
/*     */   }
/*     */ 
/*     */   static Object getCollectionItemInfo(Object paramObject) {
/*  39 */     return ((AccessibilityNodeInfo)paramObject).getCollectionItemInfo();
/*     */   }
/*     */ 
/*     */   public static void setCollectionInfo(Object paramObject1, Object paramObject2) {
/*  43 */     ((AccessibilityNodeInfo)paramObject1).setCollectionInfo((AccessibilityNodeInfo.CollectionInfo)paramObject2);
/*     */   }
/*     */ 
/*     */   public static void setCollectionItemInfo(Object paramObject1, Object paramObject2)
/*     */   {
/*  48 */     ((AccessibilityNodeInfo)paramObject1).setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo)paramObject2);
/*     */   }
/*     */ 
/*     */   static Object getRangeInfo(Object paramObject)
/*     */   {
/*  53 */     return ((AccessibilityNodeInfo)paramObject).getRangeInfo();
/*     */   }
/*     */ 
/*     */   public static void setRangeInfo(Object paramObject1, Object paramObject2) {
/*  57 */     ((AccessibilityNodeInfo)paramObject1).setRangeInfo((AccessibilityNodeInfo.RangeInfo)paramObject2);
/*     */   }
/*     */ 
/*     */   public static Object obtainCollectionInfo(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
/*     */   {
/*  62 */     return AccessibilityNodeInfo.CollectionInfo.obtain(paramInt1, paramInt2, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static Object obtainCollectionItemInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
/*     */   {
/*  67 */     return AccessibilityNodeInfo.CollectionItemInfo.obtain(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setContentInvalid(Object paramObject, boolean paramBoolean)
/*     */   {
/*  72 */     ((AccessibilityNodeInfo)paramObject).setContentInvalid(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static boolean isContentInvalid(Object paramObject) {
/*  76 */     return ((AccessibilityNodeInfo)paramObject).isContentInvalid();
/*     */   }
/*     */ 
/*     */   public static boolean canOpenPopup(Object paramObject) {
/*  80 */     return ((AccessibilityNodeInfo)paramObject).canOpenPopup();
/*     */   }
/*     */ 
/*     */   public static void setCanOpenPopup(Object paramObject, boolean paramBoolean) {
/*  84 */     ((AccessibilityNodeInfo)paramObject).setCanOpenPopup(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static Bundle getExtras(Object paramObject) {
/*  88 */     return ((AccessibilityNodeInfo)paramObject).getExtras();
/*     */   }
/*     */ 
/*     */   public static int getInputType(Object paramObject) {
/*  92 */     return ((AccessibilityNodeInfo)paramObject).getInputType();
/*     */   }
/*     */ 
/*     */   public static void setInputType(Object paramObject, int paramInt) {
/*  96 */     ((AccessibilityNodeInfo)paramObject).setInputType(paramInt);
/*     */   }
/*     */ 
/*     */   public static boolean isDismissable(Object paramObject) {
/* 100 */     return ((AccessibilityNodeInfo)paramObject).isDismissable();
/*     */   }
/*     */ 
/*     */   public static void setDismissable(Object paramObject, boolean paramBoolean) {
/* 104 */     ((AccessibilityNodeInfo)paramObject).setDismissable(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static boolean isMultiLine(Object paramObject) {
/* 108 */     return ((AccessibilityNodeInfo)paramObject).isMultiLine();
/*     */   }
/*     */ 
/*     */   public static void setMultiLine(Object paramObject, boolean paramBoolean) {
/* 112 */     ((AccessibilityNodeInfo)paramObject).setMultiLine(paramBoolean);
/*     */   }
/*     */ 
/*     */   static class RangeInfo
/*     */   {
/*     */     static float getCurrent(Object paramObject)
/*     */     {
/* 153 */       return ((AccessibilityNodeInfo.RangeInfo)paramObject).getCurrent();
/*     */     }
/*     */ 
/*     */     static float getMax(Object paramObject) {
/* 157 */       return ((AccessibilityNodeInfo.RangeInfo)paramObject).getMax();
/*     */     }
/*     */ 
/*     */     static float getMin(Object paramObject) {
/* 161 */       return ((AccessibilityNodeInfo.RangeInfo)paramObject).getMin();
/*     */     }
/*     */ 
/*     */     static int getType(Object paramObject) {
/* 165 */       return ((AccessibilityNodeInfo.RangeInfo)paramObject).getType();
/*     */     }
/*     */   }
/*     */ 
/*     */   static class CollectionItemInfo
/*     */   {
/*     */     static int getColumnIndex(Object paramObject)
/*     */     {
/* 131 */       return ((AccessibilityNodeInfo.CollectionItemInfo)paramObject).getColumnIndex();
/*     */     }
/*     */ 
/*     */     static int getColumnSpan(Object paramObject) {
/* 135 */       return ((AccessibilityNodeInfo.CollectionItemInfo)paramObject).getColumnSpan();
/*     */     }
/*     */ 
/*     */     static int getRowIndex(Object paramObject) {
/* 139 */       return ((AccessibilityNodeInfo.CollectionItemInfo)paramObject).getRowIndex();
/*     */     }
/*     */ 
/*     */     static int getRowSpan(Object paramObject) {
/* 143 */       return ((AccessibilityNodeInfo.CollectionItemInfo)paramObject).getRowSpan();
/*     */     }
/*     */ 
/*     */     static boolean isHeading(Object paramObject) {
/* 147 */       return ((AccessibilityNodeInfo.CollectionItemInfo)paramObject).isHeading();
/*     */     }
/*     */   }
/*     */ 
/*     */   static class CollectionInfo
/*     */   {
/*     */     static int getColumnCount(Object paramObject)
/*     */     {
/* 117 */       return ((AccessibilityNodeInfo.CollectionInfo)paramObject).getColumnCount();
/*     */     }
/*     */ 
/*     */     static int getRowCount(Object paramObject) {
/* 121 */       return ((AccessibilityNodeInfo.CollectionInfo)paramObject).getRowCount();
/*     */     }
/*     */ 
/*     */     static boolean isHierarchical(Object paramObject) {
/* 125 */       return ((AccessibilityNodeInfo.CollectionInfo)paramObject).isHierarchical();
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityNodeInfoCompatKitKat
 * JD-Core Version:    0.6.2
 */