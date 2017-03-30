/*     */ package com.dcloud.android.v4.view.accessibility;
/*     */ 
/*     */ import android.graphics.Rect;
/*     */ import android.view.View;
/*     */ import android.view.accessibility.AccessibilityNodeInfo;
/*     */ import java.util.List;
/*     */ 
/*     */ class AccessibilityNodeInfoCompatIcs
/*     */ {
/*     */   public static Object obtain()
/*     */   {
/*  31 */     return AccessibilityNodeInfo.obtain();
/*     */   }
/*     */ 
/*     */   public static Object obtain(View paramView) {
/*  35 */     return AccessibilityNodeInfo.obtain(paramView);
/*     */   }
/*     */ 
/*     */   public static Object obtain(Object paramObject) {
/*  39 */     return AccessibilityNodeInfo.obtain((AccessibilityNodeInfo)paramObject);
/*     */   }
/*     */ 
/*     */   public static void addAction(Object paramObject, int paramInt) {
/*  43 */     ((AccessibilityNodeInfo)paramObject).addAction(paramInt);
/*     */   }
/*     */ 
/*     */   public static void addChild(Object paramObject, View paramView) {
/*  47 */     ((AccessibilityNodeInfo)paramObject).addChild(paramView);
/*     */   }
/*     */ 
/*     */   public static List<Object> findAccessibilityNodeInfosByText(Object paramObject, String paramString)
/*     */   {
/*  52 */     List localList = ((AccessibilityNodeInfo)paramObject).findAccessibilityNodeInfosByText(paramString);
/*  53 */     return (List)localList;
/*     */   }
/*     */ 
/*     */   public static int getActions(Object paramObject) {
/*  57 */     return ((AccessibilityNodeInfo)paramObject).getActions();
/*     */   }
/*     */ 
/*     */   public static void getBoundsInParent(Object paramObject, Rect paramRect) {
/*  61 */     ((AccessibilityNodeInfo)paramObject).getBoundsInParent(paramRect);
/*     */   }
/*     */ 
/*     */   public static void getBoundsInScreen(Object paramObject, Rect paramRect) {
/*  65 */     ((AccessibilityNodeInfo)paramObject).getBoundsInScreen(paramRect);
/*     */   }
/*     */ 
/*     */   public static Object getChild(Object paramObject, int paramInt) {
/*  69 */     return ((AccessibilityNodeInfo)paramObject).getChild(paramInt);
/*     */   }
/*     */ 
/*     */   public static int getChildCount(Object paramObject) {
/*  73 */     return ((AccessibilityNodeInfo)paramObject).getChildCount();
/*     */   }
/*     */ 
/*     */   public static CharSequence getClassName(Object paramObject) {
/*  77 */     return ((AccessibilityNodeInfo)paramObject).getClassName();
/*     */   }
/*     */ 
/*     */   public static CharSequence getContentDescription(Object paramObject) {
/*  81 */     return ((AccessibilityNodeInfo)paramObject).getContentDescription();
/*     */   }
/*     */ 
/*     */   public static CharSequence getPackageName(Object paramObject) {
/*  85 */     return ((AccessibilityNodeInfo)paramObject).getPackageName();
/*     */   }
/*     */ 
/*     */   public static Object getParent(Object paramObject) {
/*  89 */     return ((AccessibilityNodeInfo)paramObject).getParent();
/*     */   }
/*     */ 
/*     */   public static CharSequence getText(Object paramObject) {
/*  93 */     return ((AccessibilityNodeInfo)paramObject).getText();
/*     */   }
/*     */ 
/*     */   public static int getWindowId(Object paramObject) {
/*  97 */     return ((AccessibilityNodeInfo)paramObject).getWindowId();
/*     */   }
/*     */ 
/*     */   public static boolean isCheckable(Object paramObject) {
/* 101 */     return ((AccessibilityNodeInfo)paramObject).isCheckable();
/*     */   }
/*     */ 
/*     */   public static boolean isChecked(Object paramObject) {
/* 105 */     return ((AccessibilityNodeInfo)paramObject).isChecked();
/*     */   }
/*     */ 
/*     */   public static boolean isClickable(Object paramObject) {
/* 109 */     return ((AccessibilityNodeInfo)paramObject).isClickable();
/*     */   }
/*     */ 
/*     */   public static boolean isEnabled(Object paramObject) {
/* 113 */     return ((AccessibilityNodeInfo)paramObject).isEnabled();
/*     */   }
/*     */ 
/*     */   public static boolean isFocusable(Object paramObject) {
/* 117 */     return ((AccessibilityNodeInfo)paramObject).isFocusable();
/*     */   }
/*     */ 
/*     */   public static boolean isFocused(Object paramObject) {
/* 121 */     return ((AccessibilityNodeInfo)paramObject).isFocused();
/*     */   }
/*     */ 
/*     */   public static boolean isLongClickable(Object paramObject) {
/* 125 */     return ((AccessibilityNodeInfo)paramObject).isLongClickable();
/*     */   }
/*     */ 
/*     */   public static boolean isPassword(Object paramObject) {
/* 129 */     return ((AccessibilityNodeInfo)paramObject).isPassword();
/*     */   }
/*     */ 
/*     */   public static boolean isScrollable(Object paramObject) {
/* 133 */     return ((AccessibilityNodeInfo)paramObject).isScrollable();
/*     */   }
/*     */ 
/*     */   public static boolean isSelected(Object paramObject) {
/* 137 */     return ((AccessibilityNodeInfo)paramObject).isSelected();
/*     */   }
/*     */ 
/*     */   public static boolean performAction(Object paramObject, int paramInt) {
/* 141 */     return ((AccessibilityNodeInfo)paramObject).performAction(paramInt);
/*     */   }
/*     */ 
/*     */   public static void setBoundsInParent(Object paramObject, Rect paramRect) {
/* 145 */     ((AccessibilityNodeInfo)paramObject).setBoundsInParent(paramRect);
/*     */   }
/*     */ 
/*     */   public static void setBoundsInScreen(Object paramObject, Rect paramRect) {
/* 149 */     ((AccessibilityNodeInfo)paramObject).setBoundsInScreen(paramRect);
/*     */   }
/*     */ 
/*     */   public static void setCheckable(Object paramObject, boolean paramBoolean) {
/* 153 */     ((AccessibilityNodeInfo)paramObject).setCheckable(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setChecked(Object paramObject, boolean paramBoolean) {
/* 157 */     ((AccessibilityNodeInfo)paramObject).setChecked(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setClassName(Object paramObject, CharSequence paramCharSequence) {
/* 161 */     ((AccessibilityNodeInfo)paramObject).setClassName(paramCharSequence);
/*     */   }
/*     */ 
/*     */   public static void setClickable(Object paramObject, boolean paramBoolean) {
/* 165 */     ((AccessibilityNodeInfo)paramObject).setClickable(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setContentDescription(Object paramObject, CharSequence paramCharSequence) {
/* 169 */     ((AccessibilityNodeInfo)paramObject).setContentDescription(paramCharSequence);
/*     */   }
/*     */ 
/*     */   public static void setEnabled(Object paramObject, boolean paramBoolean) {
/* 173 */     ((AccessibilityNodeInfo)paramObject).setEnabled(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setFocusable(Object paramObject, boolean paramBoolean) {
/* 177 */     ((AccessibilityNodeInfo)paramObject).setFocusable(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setFocused(Object paramObject, boolean paramBoolean) {
/* 181 */     ((AccessibilityNodeInfo)paramObject).setFocused(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setLongClickable(Object paramObject, boolean paramBoolean) {
/* 185 */     ((AccessibilityNodeInfo)paramObject).setLongClickable(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setPackageName(Object paramObject, CharSequence paramCharSequence) {
/* 189 */     ((AccessibilityNodeInfo)paramObject).setPackageName(paramCharSequence);
/*     */   }
/*     */ 
/*     */   public static void setParent(Object paramObject, View paramView) {
/* 193 */     ((AccessibilityNodeInfo)paramObject).setParent(paramView);
/*     */   }
/*     */ 
/*     */   public static void setPassword(Object paramObject, boolean paramBoolean) {
/* 197 */     ((AccessibilityNodeInfo)paramObject).setPassword(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setScrollable(Object paramObject, boolean paramBoolean) {
/* 201 */     ((AccessibilityNodeInfo)paramObject).setScrollable(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setSelected(Object paramObject, boolean paramBoolean) {
/* 205 */     ((AccessibilityNodeInfo)paramObject).setSelected(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void setSource(Object paramObject, View paramView) {
/* 209 */     ((AccessibilityNodeInfo)paramObject).setSource(paramView);
/*     */   }
/*     */ 
/*     */   public static void setText(Object paramObject, CharSequence paramCharSequence) {
/* 213 */     ((AccessibilityNodeInfo)paramObject).setText(paramCharSequence);
/*     */   }
/*     */ 
/*     */   public static void recycle(Object paramObject) {
/* 217 */     ((AccessibilityNodeInfo)paramObject).recycle();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityNodeInfoCompatIcs
 * JD-Core Version:    0.6.2
 */