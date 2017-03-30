/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.view.View;
/*     */ import android.view.View.AccessibilityDelegate;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.accessibility.AccessibilityEvent;
/*     */ import android.view.accessibility.AccessibilityNodeInfo;
/*     */ 
/*     */ class AccessibilityDelegateCompatIcs
/*     */ {
/*     */   public static Object newAccessibilityDelegateDefaultImpl()
/*     */   {
/*  42 */     return new View.AccessibilityDelegate();
/*     */   }
/*     */ 
/*     */   public static Object newAccessibilityDelegateBridge(AccessibilityDelegateBridge paramAccessibilityDelegateBridge) {
/*  46 */     return new View.AccessibilityDelegate()
/*     */     {
/*     */       public boolean dispatchPopulateAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent) {
/*  49 */         return this.val$bridge.dispatchPopulateAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */ 
/*     */       public void onInitializeAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */       {
/*  54 */         this.val$bridge.onInitializeAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */ 
/*     */       public void onInitializeAccessibilityNodeInfo(View paramAnonymousView, AccessibilityNodeInfo paramAnonymousAccessibilityNodeInfo)
/*     */       {
/*  59 */         this.val$bridge.onInitializeAccessibilityNodeInfo(paramAnonymousView, paramAnonymousAccessibilityNodeInfo);
/*     */       }
/*     */ 
/*     */       public void onPopulateAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */       {
/*  64 */         this.val$bridge.onPopulateAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */ 
/*     */       public boolean onRequestSendAccessibilityEvent(ViewGroup paramAnonymousViewGroup, View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */       {
/*  70 */         return this.val$bridge.onRequestSendAccessibilityEvent(paramAnonymousViewGroup, paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */ 
/*     */       public void sendAccessibilityEvent(View paramAnonymousView, int paramAnonymousInt)
/*     */       {
/*  75 */         this.val$bridge.sendAccessibilityEvent(paramAnonymousView, paramAnonymousInt);
/*     */       }
/*     */ 
/*     */       public void sendAccessibilityEventUnchecked(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */       {
/*  80 */         this.val$bridge.sendAccessibilityEventUnchecked(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static boolean dispatchPopulateAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/*  87 */     return ((View.AccessibilityDelegate)paramObject).dispatchPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public static void onInitializeAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/*  92 */     ((View.AccessibilityDelegate)paramObject).onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public static void onInitializeAccessibilityNodeInfo(Object paramObject1, View paramView, Object paramObject2) {
/*  96 */     ((View.AccessibilityDelegate)paramObject1).onInitializeAccessibilityNodeInfo(paramView, (AccessibilityNodeInfo)paramObject2);
/*     */   }
/*     */ 
/*     */   public static void onPopulateAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/* 102 */     ((View.AccessibilityDelegate)paramObject).onPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public static boolean onRequestSendAccessibilityEvent(Object paramObject, ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/* 107 */     return ((View.AccessibilityDelegate)paramObject).onRequestSendAccessibilityEvent(paramViewGroup, paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public static void sendAccessibilityEvent(Object paramObject, View paramView, int paramInt)
/*     */   {
/* 112 */     ((View.AccessibilityDelegate)paramObject).sendAccessibilityEvent(paramView, paramInt);
/*     */   }
/*     */ 
/*     */   public static void sendAccessibilityEventUnchecked(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/* 117 */     ((View.AccessibilityDelegate)paramObject).sendAccessibilityEventUnchecked(paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public static abstract interface AccessibilityDelegateBridge
/*     */   {
/*     */     public abstract boolean dispatchPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract void onInitializeAccessibilityNodeInfo(View paramView, Object paramObject);
/*     */ 
/*     */     public abstract void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract boolean onRequestSendAccessibilityEvent(ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract void sendAccessibilityEvent(View paramView, int paramInt);
/*     */ 
/*     */     public abstract void sendAccessibilityEventUnchecked(View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.AccessibilityDelegateCompatIcs
 * JD-Core Version:    0.6.2
 */