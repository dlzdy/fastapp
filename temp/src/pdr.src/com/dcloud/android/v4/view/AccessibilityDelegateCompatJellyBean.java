/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.os.Bundle;
/*     */ import android.view.View;
/*     */ import android.view.View.AccessibilityDelegate;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.accessibility.AccessibilityEvent;
/*     */ import android.view.accessibility.AccessibilityNodeInfo;
/*     */ import android.view.accessibility.AccessibilityNodeProvider;
/*     */ 
/*     */ class AccessibilityDelegateCompatJellyBean
/*     */ {
/*     */   public static Object newAccessibilityDelegateBridge(AccessibilityDelegateBridgeJellyBean paramAccessibilityDelegateBridgeJellyBean)
/*     */   {
/*  47 */     return new View.AccessibilityDelegate()
/*     */     {
/*     */       public boolean dispatchPopulateAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */       {
/*  51 */         return this.val$bridge.dispatchPopulateAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */ 
/*     */       public void onInitializeAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */       {
/*  56 */         this.val$bridge.onInitializeAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */ 
/*     */       public void onInitializeAccessibilityNodeInfo(View paramAnonymousView, AccessibilityNodeInfo paramAnonymousAccessibilityNodeInfo)
/*     */       {
/*  61 */         this.val$bridge.onInitializeAccessibilityNodeInfo(paramAnonymousView, paramAnonymousAccessibilityNodeInfo);
/*     */       }
/*     */ 
/*     */       public void onPopulateAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */       {
/*  66 */         this.val$bridge.onPopulateAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */ 
/*     */       public boolean onRequestSendAccessibilityEvent(ViewGroup paramAnonymousViewGroup, View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */       {
/*  72 */         return this.val$bridge.onRequestSendAccessibilityEvent(paramAnonymousViewGroup, paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */ 
/*     */       public void sendAccessibilityEvent(View paramAnonymousView, int paramAnonymousInt)
/*     */       {
/*  77 */         this.val$bridge.sendAccessibilityEvent(paramAnonymousView, paramAnonymousInt);
/*     */       }
/*     */ 
/*     */       public void sendAccessibilityEventUnchecked(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */       {
/*  82 */         this.val$bridge.sendAccessibilityEventUnchecked(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */       }
/*     */ 
/*     */       public AccessibilityNodeProvider getAccessibilityNodeProvider(View paramAnonymousView)
/*     */       {
/*  87 */         return (AccessibilityNodeProvider)this.val$bridge.getAccessibilityNodeProvider(paramAnonymousView);
/*     */       }
/*     */ 
/*     */       public boolean performAccessibilityAction(View paramAnonymousView, int paramAnonymousInt, Bundle paramAnonymousBundle)
/*     */       {
/*  92 */         return this.val$bridge.performAccessibilityAction(paramAnonymousView, paramAnonymousInt, paramAnonymousBundle);
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static Object getAccessibilityNodeProvider(Object paramObject, View paramView)
/*     */   {
/*  99 */     return ((View.AccessibilityDelegate)paramObject).getAccessibilityNodeProvider(paramView);
/*     */   }
/*     */ 
/*     */   public static boolean performAccessibilityAction(Object paramObject, View paramView, int paramInt, Bundle paramBundle)
/*     */   {
/* 104 */     return ((View.AccessibilityDelegate)paramObject).performAccessibilityAction(paramView, paramInt, paramBundle);
/*     */   }
/*     */ 
/*     */   public static abstract interface AccessibilityDelegateBridgeJellyBean
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
/*     */ 
/*     */     public abstract Object getAccessibilityNodeProvider(View paramView);
/*     */ 
/*     */     public abstract boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.AccessibilityDelegateCompatJellyBean
 * JD-Core Version:    0.6.2
 */