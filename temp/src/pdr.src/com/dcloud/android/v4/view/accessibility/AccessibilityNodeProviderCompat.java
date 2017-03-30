/*     */ package com.dcloud.android.v4.view.accessibility;
/*     */ 
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Bundle;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class AccessibilityNodeProviderCompat
/*     */ {
/*     */   private static final AccessibilityNodeProviderImpl IMPL;
/*     */   private final Object mProvider;
/*     */ 
/*     */   public AccessibilityNodeProviderCompat()
/*     */   {
/* 152 */     this.mProvider = IMPL.newAccessibilityNodeProviderBridge(this);
/*     */   }
/*     */ 
/*     */   public AccessibilityNodeProviderCompat(Object paramObject)
/*     */   {
/* 162 */     this.mProvider = paramObject;
/*     */   }
/*     */ 
/*     */   public Object getProvider()
/*     */   {
/* 169 */     return this.mProvider;
/*     */   }
/*     */ 
/*     */   public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int paramInt)
/*     */   {
/* 195 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
/*     */   {
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String paramString, int paramInt)
/*     */   {
/* 231 */     return null;
/*     */   }
/*     */ 
/*     */   public AccessibilityNodeInfoCompat findFocus(int paramInt)
/*     */   {
/* 246 */     return null;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 139 */     if (Build.VERSION.SDK_INT >= 19)
/* 140 */       IMPL = new AccessibilityNodeProviderKitKatImpl();
/* 141 */     else if (Build.VERSION.SDK_INT >= 16)
/* 142 */       IMPL = new AccessibilityNodeProviderJellyBeanImpl();
/*     */     else
/* 144 */       IMPL = new AccessibilityNodeProviderStubImpl();
/*     */   }
/*     */ 
/*     */   static class AccessibilityNodeProviderKitKatImpl extends AccessibilityNodeProviderCompat.AccessibilityNodeProviderStubImpl
/*     */   {
/*     */     public Object newAccessibilityNodeProviderBridge(final AccessibilityNodeProviderCompat paramAccessibilityNodeProviderCompat)
/*     */     {
/*  88 */       return AccessibilityNodeProviderCompatKitKat.newAccessibilityNodeProviderBridge(new AccessibilityNodeProviderCompatKitKat.AccessibilityNodeInfoBridge()
/*     */       {
/*     */         public boolean performAction(int paramAnonymousInt1, int paramAnonymousInt2, Bundle paramAnonymousBundle)
/*     */         {
/*  93 */           return paramAccessibilityNodeProviderCompat.performAction(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousBundle);
/*     */         }
/*     */ 
/*     */         public List<Object> findAccessibilityNodeInfosByText(String paramAnonymousString, int paramAnonymousInt)
/*     */         {
/*  99 */           List localList = paramAccessibilityNodeProviderCompat.findAccessibilityNodeInfosByText(paramAnonymousString, paramAnonymousInt);
/*     */ 
/* 101 */           ArrayList localArrayList = new ArrayList();
/* 102 */           int i = localList.size();
/* 103 */           for (int j = 0; j < i; j++) {
/* 104 */             AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat)localList.get(j);
/* 105 */             localArrayList.add(localAccessibilityNodeInfoCompat.getInfo());
/*     */           }
/* 107 */           return localArrayList;
/*     */         }
/*     */ 
/*     */         public Object createAccessibilityNodeInfo(int paramAnonymousInt)
/*     */         {
/* 112 */           AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = paramAccessibilityNodeProviderCompat.createAccessibilityNodeInfo(paramAnonymousInt);
/*     */ 
/* 114 */           if (localAccessibilityNodeInfoCompat == null) {
/* 115 */             return null;
/*     */           }
/* 117 */           return localAccessibilityNodeInfoCompat.getInfo();
/*     */         }
/*     */ 
/*     */         public Object findFocus(int paramAnonymousInt)
/*     */         {
/* 123 */           AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = paramAccessibilityNodeProviderCompat.findFocus(paramAnonymousInt);
/* 124 */           if (localAccessibilityNodeInfoCompat == null) {
/* 125 */             return null;
/*     */           }
/* 127 */           return localAccessibilityNodeInfoCompat.getInfo();
/*     */         }
/*     */       });
/*     */     }
/*     */   }
/*     */ 
/*     */   static class AccessibilityNodeProviderJellyBeanImpl extends AccessibilityNodeProviderCompat.AccessibilityNodeProviderStubImpl
/*     */   {
/*     */     public Object newAccessibilityNodeProviderBridge(final AccessibilityNodeProviderCompat paramAccessibilityNodeProviderCompat)
/*     */     {
/*  47 */       return AccessibilityNodeProviderCompatJellyBean.newAccessibilityNodeProviderBridge(new AccessibilityNodeProviderCompatJellyBean.AccessibilityNodeInfoBridge()
/*     */       {
/*     */         public boolean performAction(int paramAnonymousInt1, int paramAnonymousInt2, Bundle paramAnonymousBundle)
/*     */         {
/*  52 */           return paramAccessibilityNodeProviderCompat.performAction(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousBundle);
/*     */         }
/*     */ 
/*     */         public List<Object> findAccessibilityNodeInfosByText(String paramAnonymousString, int paramAnonymousInt)
/*     */         {
/*  58 */           List localList = paramAccessibilityNodeProviderCompat.findAccessibilityNodeInfosByText(paramAnonymousString, paramAnonymousInt);
/*     */ 
/*  60 */           ArrayList localArrayList = new ArrayList();
/*  61 */           int i = localList.size();
/*  62 */           for (int j = 0; j < i; j++) {
/*  63 */             AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat)localList.get(j);
/*  64 */             localArrayList.add(localAccessibilityNodeInfoCompat.getInfo());
/*     */           }
/*  66 */           return localArrayList;
/*     */         }
/*     */ 
/*     */         public Object createAccessibilityNodeInfo(int paramAnonymousInt)
/*     */         {
/*  72 */           AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = paramAccessibilityNodeProviderCompat.createAccessibilityNodeInfo(paramAnonymousInt);
/*     */ 
/*  74 */           if (localAccessibilityNodeInfoCompat == null) {
/*  75 */             return null;
/*     */           }
/*  77 */           return localAccessibilityNodeInfoCompat.getInfo();
/*     */         }
/*     */       });
/*     */     }
/*     */   }
/*     */ 
/*     */   static class AccessibilityNodeProviderStubImpl
/*     */     implements AccessibilityNodeProviderCompat.AccessibilityNodeProviderImpl
/*     */   {
/*     */     public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat paramAccessibilityNodeProviderCompat)
/*     */     {
/*  39 */       return null;
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface AccessibilityNodeProviderImpl
/*     */   {
/*     */     public abstract Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat paramAccessibilityNodeProviderCompat);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityNodeProviderCompat
 * JD-Core Version:    0.6.2
 */