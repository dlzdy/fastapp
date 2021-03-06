/*    */ package com.dcloud.android.v4.view.accessibility;
/*    */ 
/*    */ import android.os.Bundle;
/*    */ import android.view.accessibility.AccessibilityNodeInfo;
/*    */ import android.view.accessibility.AccessibilityNodeProvider;
/*    */ import java.util.List;
/*    */ 
/*    */ class AccessibilityNodeProviderCompatKitKat
/*    */ {
/*    */   public static Object newAccessibilityNodeProviderBridge(AccessibilityNodeInfoBridge paramAccessibilityNodeInfoBridge)
/*    */   {
/* 39 */     return new AccessibilityNodeProvider()
/*    */     {
/*    */       public AccessibilityNodeInfo createAccessibilityNodeInfo(int paramAnonymousInt) {
/* 42 */         return (AccessibilityNodeInfo)this.val$bridge.createAccessibilityNodeInfo(paramAnonymousInt);
/*    */       }
/*    */ 
/*    */       public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String paramAnonymousString, int paramAnonymousInt)
/*    */       {
/* 50 */         return this.val$bridge.findAccessibilityNodeInfosByText(paramAnonymousString, paramAnonymousInt);
/*    */       }
/*    */ 
/*    */       public boolean performAction(int paramAnonymousInt1, int paramAnonymousInt2, Bundle paramAnonymousBundle)
/*    */       {
/* 56 */         return this.val$bridge.performAction(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousBundle);
/*    */       }
/*    */ 
/*    */       public AccessibilityNodeInfo findFocus(int paramAnonymousInt)
/*    */       {
/* 61 */         return (AccessibilityNodeInfo)this.val$bridge.findFocus(paramAnonymousInt);
/*    */       }
/*    */     };
/*    */   }
/*    */ 
/*    */   static abstract interface AccessibilityNodeInfoBridge
/*    */   {
/*    */     public abstract Object createAccessibilityNodeInfo(int paramInt);
/*    */ 
/*    */     public abstract boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle);
/*    */ 
/*    */     public abstract List<Object> findAccessibilityNodeInfosByText(String paramString, int paramInt);
/*    */ 
/*    */     public abstract Object findFocus(int paramInt);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityNodeProviderCompatKitKat
 * JD-Core Version:    0.6.2
 */