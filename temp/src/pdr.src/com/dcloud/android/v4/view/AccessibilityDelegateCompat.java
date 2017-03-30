/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Bundle;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.accessibility.AccessibilityEvent;
/*     */ import com.dcloud.android.v4.view.accessibility.AccessibilityNodeInfoCompat;
/*     */ import com.dcloud.android.v4.view.accessibility.AccessibilityNodeProviderCompat;
/*     */ 
/*     */ public class AccessibilityDelegateCompat
/*     */ {
/*     */   private static final AccessibilityDelegateImpl IMPL;
/* 302 */   private static final Object DEFAULT_DELEGATE = IMPL.newAccessiblityDelegateDefaultImpl();
/*     */   final Object mBridge;
/*     */ 
/*     */   public AccessibilityDelegateCompat()
/*     */   {
/* 311 */     this.mBridge = IMPL.newAccessiblityDelegateBridge(this);
/*     */   }
/*     */ 
/*     */   Object getBridge()
/*     */   {
/* 318 */     return this.mBridge;
/*     */   }
/*     */ 
/*     */   public void sendAccessibilityEvent(View paramView, int paramInt)
/*     */   {
/* 336 */     IMPL.sendAccessibilityEvent(DEFAULT_DELEGATE, paramView, paramInt);
/*     */   }
/*     */ 
/*     */   public void sendAccessibilityEventUnchecked(View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/* 358 */     IMPL.sendAccessibilityEventUnchecked(DEFAULT_DELEGATE, paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public boolean dispatchPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/* 379 */     return IMPL.dispatchPopulateAccessibilityEvent(DEFAULT_DELEGATE, paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/* 399 */     IMPL.onPopulateAccessibilityEvent(DEFAULT_DELEGATE, paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/* 419 */     IMPL.onInitializeAccessibilityEvent(DEFAULT_DELEGATE, paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
/*     */   {
/* 438 */     IMPL.onInitializeAccessibilityNodeInfo(DEFAULT_DELEGATE, paramView, paramAccessibilityNodeInfoCompat);
/*     */   }
/*     */ 
/*     */   public boolean onRequestSendAccessibilityEvent(ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/* 462 */     return IMPL.onRequestSendAccessibilityEvent(DEFAULT_DELEGATE, paramViewGroup, paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView)
/*     */   {
/* 480 */     return IMPL.getAccessibilityNodeProvider(DEFAULT_DELEGATE, paramView);
/*     */   }
/*     */ 
/*     */   public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
/*     */   {
/* 500 */     return IMPL.performAccessibilityAction(DEFAULT_DELEGATE, paramView, paramInt, paramBundle);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 295 */     if (Build.VERSION.SDK_INT >= 16)
/* 296 */       IMPL = new AccessibilityDelegateJellyBeanImpl();
/* 297 */     else if (Build.VERSION.SDK_INT >= 14)
/* 298 */       IMPL = new AccessibilityDelegateIcsImpl();
/*     */     else
/* 300 */       IMPL = new AccessibilityDelegateStubImpl();
/*     */   }
/*     */ 
/*     */   static class AccessibilityDelegateJellyBeanImpl extends AccessibilityDelegateCompat.AccessibilityDelegateIcsImpl
/*     */   {
/*     */     public Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat paramAccessibilityDelegateCompat)
/*     */     {
/* 217 */       return AccessibilityDelegateCompatJellyBean.newAccessibilityDelegateBridge(new AccessibilityDelegateCompatJellyBean.AccessibilityDelegateBridgeJellyBean()
/*     */       {
/*     */         public boolean dispatchPopulateAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 223 */           return paramAccessibilityDelegateCompat.dispatchPopulateAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */ 
/*     */         public void onInitializeAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 228 */           paramAccessibilityDelegateCompat.onInitializeAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */ 
/*     */         public void onInitializeAccessibilityNodeInfo(View paramAnonymousView, Object paramAnonymousObject)
/*     */         {
/* 233 */           paramAccessibilityDelegateCompat.onInitializeAccessibilityNodeInfo(paramAnonymousView, new AccessibilityNodeInfoCompat(paramAnonymousObject));
/*     */         }
/*     */ 
/*     */         public void onPopulateAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 239 */           paramAccessibilityDelegateCompat.onPopulateAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */ 
/*     */         public boolean onRequestSendAccessibilityEvent(ViewGroup paramAnonymousViewGroup, View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 245 */           return paramAccessibilityDelegateCompat.onRequestSendAccessibilityEvent(paramAnonymousViewGroup, paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */ 
/*     */         public void sendAccessibilityEvent(View paramAnonymousView, int paramAnonymousInt)
/*     */         {
/* 250 */           paramAccessibilityDelegateCompat.sendAccessibilityEvent(paramAnonymousView, paramAnonymousInt);
/*     */         }
/*     */ 
/*     */         public void sendAccessibilityEventUnchecked(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 255 */           paramAccessibilityDelegateCompat.sendAccessibilityEventUnchecked(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */ 
/*     */         public Object getAccessibilityNodeProvider(View paramAnonymousView)
/*     */         {
/* 260 */           AccessibilityNodeProviderCompat localAccessibilityNodeProviderCompat = paramAccessibilityDelegateCompat.getAccessibilityNodeProvider(paramAnonymousView);
/*     */ 
/* 262 */           return localAccessibilityNodeProviderCompat != null ? localAccessibilityNodeProviderCompat.getProvider() : null;
/*     */         }
/*     */ 
/*     */         public boolean performAccessibilityAction(View paramAnonymousView, int paramAnonymousInt, Bundle paramAnonymousBundle)
/*     */         {
/* 267 */           return paramAccessibilityDelegateCompat.performAccessibilityAction(paramAnonymousView, paramAnonymousInt, paramAnonymousBundle);
/*     */         }
/*     */       });
/*     */     }
/*     */ 
/*     */     public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object paramObject, View paramView)
/*     */     {
/* 275 */       Object localObject = AccessibilityDelegateCompatJellyBean.getAccessibilityNodeProvider(paramObject, paramView);
/*     */ 
/* 277 */       if (localObject != null) {
/* 278 */         return new AccessibilityNodeProviderCompat(localObject);
/*     */       }
/* 280 */       return null;
/*     */     }
/*     */ 
/*     */     public boolean performAccessibilityAction(Object paramObject, View paramView, int paramInt, Bundle paramBundle)
/*     */     {
/* 286 */       return AccessibilityDelegateCompatJellyBean.performAccessibilityAction(paramObject, paramView, paramInt, paramBundle);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class AccessibilityDelegateIcsImpl extends AccessibilityDelegateCompat.AccessibilityDelegateStubImpl
/*     */   {
/*     */     public Object newAccessiblityDelegateDefaultImpl()
/*     */     {
/* 122 */       return AccessibilityDelegateCompatIcs.newAccessibilityDelegateDefaultImpl();
/*     */     }
/*     */ 
/*     */     public Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat paramAccessibilityDelegateCompat)
/*     */     {
/* 127 */       return AccessibilityDelegateCompatIcs.newAccessibilityDelegateBridge(new AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge()
/*     */       {
/*     */         public boolean dispatchPopulateAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 132 */           return paramAccessibilityDelegateCompat.dispatchPopulateAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */ 
/*     */         public void onInitializeAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 137 */           paramAccessibilityDelegateCompat.onInitializeAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */ 
/*     */         public void onInitializeAccessibilityNodeInfo(View paramAnonymousView, Object paramAnonymousObject)
/*     */         {
/* 142 */           paramAccessibilityDelegateCompat.onInitializeAccessibilityNodeInfo(paramAnonymousView, new AccessibilityNodeInfoCompat(paramAnonymousObject));
/*     */         }
/*     */ 
/*     */         public void onPopulateAccessibilityEvent(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 148 */           paramAccessibilityDelegateCompat.onPopulateAccessibilityEvent(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */ 
/*     */         public boolean onRequestSendAccessibilityEvent(ViewGroup paramAnonymousViewGroup, View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 154 */           return paramAccessibilityDelegateCompat.onRequestSendAccessibilityEvent(paramAnonymousViewGroup, paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */ 
/*     */         public void sendAccessibilityEvent(View paramAnonymousView, int paramAnonymousInt)
/*     */         {
/* 159 */           paramAccessibilityDelegateCompat.sendAccessibilityEvent(paramAnonymousView, paramAnonymousInt);
/*     */         }
/*     */ 
/*     */         public void sendAccessibilityEventUnchecked(View paramAnonymousView, AccessibilityEvent paramAnonymousAccessibilityEvent)
/*     */         {
/* 164 */           paramAccessibilityDelegateCompat.sendAccessibilityEventUnchecked(paramAnonymousView, paramAnonymousAccessibilityEvent);
/*     */         }
/*     */       });
/*     */     }
/*     */ 
/*     */     public boolean dispatchPopulateAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/* 172 */       return AccessibilityDelegateCompatIcs.dispatchPopulateAccessibilityEvent(paramObject, paramView, paramAccessibilityEvent);
/*     */     }
/*     */ 
/*     */     public void onInitializeAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/* 179 */       AccessibilityDelegateCompatIcs.onInitializeAccessibilityEvent(paramObject, paramView, paramAccessibilityEvent);
/*     */     }
/*     */ 
/*     */     public void onInitializeAccessibilityNodeInfo(Object paramObject, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
/*     */     {
/* 185 */       AccessibilityDelegateCompatIcs.onInitializeAccessibilityNodeInfo(paramObject, paramView, paramAccessibilityNodeInfoCompat.getInfo());
/*     */     }
/*     */ 
/*     */     public void onPopulateAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/* 192 */       AccessibilityDelegateCompatIcs.onPopulateAccessibilityEvent(paramObject, paramView, paramAccessibilityEvent);
/*     */     }
/*     */ 
/*     */     public boolean onRequestSendAccessibilityEvent(Object paramObject, ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/* 198 */       return AccessibilityDelegateCompatIcs.onRequestSendAccessibilityEvent(paramObject, paramViewGroup, paramView, paramAccessibilityEvent);
/*     */     }
/*     */ 
/*     */     public void sendAccessibilityEvent(Object paramObject, View paramView, int paramInt)
/*     */     {
/* 204 */       AccessibilityDelegateCompatIcs.sendAccessibilityEvent(paramObject, paramView, paramInt);
/*     */     }
/*     */ 
/*     */     public void sendAccessibilityEventUnchecked(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/* 210 */       AccessibilityDelegateCompatIcs.sendAccessibilityEventUnchecked(paramObject, paramView, paramAccessibilityEvent);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class AccessibilityDelegateStubImpl
/*     */     implements AccessibilityDelegateCompat.AccessibilityDelegateImpl
/*     */   {
/*     */     public Object newAccessiblityDelegateDefaultImpl()
/*     */     {
/*  57 */       return null;
/*     */     }
/*     */ 
/*     */     public Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat paramAccessibilityDelegateCompat)
/*     */     {
/*  62 */       return null;
/*     */     }
/*     */ 
/*     */     public boolean dispatchPopulateAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/*  68 */       return false;
/*     */     }
/*     */ 
/*     */     public void onInitializeAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void onInitializeAccessibilityNodeInfo(Object paramObject, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void onPopulateAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean onRequestSendAccessibilityEvent(Object paramObject, ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/*  92 */       return true;
/*     */     }
/*     */ 
/*     */     public void sendAccessibilityEvent(Object paramObject, View paramView, int paramInt)
/*     */     {
/*     */     }
/*     */ 
/*     */     public void sendAccessibilityEventUnchecked(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/*     */     }
/*     */ 
/*     */     public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object paramObject, View paramView)
/*     */     {
/* 109 */       return null;
/*     */     }
/*     */ 
/*     */     public boolean performAccessibilityAction(Object paramObject, View paramView, int paramInt, Bundle paramBundle)
/*     */     {
/* 115 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface AccessibilityDelegateImpl
/*     */   {
/*     */     public abstract Object newAccessiblityDelegateDefaultImpl();
/*     */ 
/*     */     public abstract Object newAccessiblityDelegateBridge(AccessibilityDelegateCompat paramAccessibilityDelegateCompat);
/*     */ 
/*     */     public abstract boolean dispatchPopulateAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract void onInitializeAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract void onInitializeAccessibilityNodeInfo(Object paramObject, View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat);
/*     */ 
/*     */     public abstract void onPopulateAccessibilityEvent(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract boolean onRequestSendAccessibilityEvent(Object paramObject, ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract void sendAccessibilityEvent(Object paramObject, View paramView, int paramInt);
/*     */ 
/*     */     public abstract void sendAccessibilityEventUnchecked(Object paramObject, View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract AccessibilityNodeProviderCompat getAccessibilityNodeProvider(Object paramObject, View paramView);
/*     */ 
/*     */     public abstract boolean performAccessibilityAction(Object paramObject, View paramView, int paramInt, Bundle paramBundle);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.AccessibilityDelegateCompat
 * JD-Core Version:    0.6.2
 */