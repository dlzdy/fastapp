/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.os.Build.VERSION;
/*     */ import android.view.View;
/*     */ import android.view.ViewParent;
/*     */ import android.view.accessibility.AccessibilityEvent;
/*     */ import android.view.accessibility.AccessibilityManager;
/*     */ 
/*     */ public class ViewParentCompat
/*     */ {
/*     */   static final ViewParentCompatImpl IMPL;
/*     */ 
/*     */   public static boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */   {
/* 241 */     return IMPL.requestSendAccessibilityEvent(paramViewParent, paramView, paramAccessibilityEvent);
/*     */   }
/*     */ 
/*     */   public static boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*     */   {
/* 268 */     return IMPL.onStartNestedScroll(paramViewParent, paramView1, paramView2, paramInt);
/*     */   }
/*     */ 
/*     */   public static void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*     */   {
/* 289 */     IMPL.onNestedScrollAccepted(paramViewParent, paramView1, paramView2, paramInt);
/*     */   }
/*     */ 
/*     */   public static void onStopNestedScroll(ViewParent paramViewParent, View paramView)
/*     */   {
/* 304 */     IMPL.onStopNestedScroll(paramViewParent, paramView);
/*     */   }
/*     */ 
/*     */   public static void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 330 */     IMPL.onNestedScroll(paramViewParent, paramView, paramInt1, paramInt2, paramInt3, paramInt4);
/*     */   }
/*     */ 
/*     */   public static void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
/*     */   {
/* 355 */     IMPL.onNestedPreScroll(paramViewParent, paramView, paramInt1, paramInt2, paramArrayOfInt);
/*     */   }
/*     */ 
/*     */   public static boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
/*     */   {
/* 379 */     return IMPL.onNestedFling(paramViewParent, paramView, paramFloat1, paramFloat2, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2)
/*     */   {
/* 404 */     return IMPL.onNestedPreFling(paramViewParent, paramView, paramFloat1, paramFloat2);
/*     */   }
/*     */ 
/*     */   public static void notifySubtreeAccessibilityStateChanged(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*     */   {
/* 424 */     IMPL.notifySubtreeAccessibilityStateChanged(paramViewParent, paramView1, paramView2, paramInt);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 203 */     int i = Build.VERSION.SDK_INT;
/* 204 */     if (i >= 21)
/* 205 */       IMPL = new ViewParentCompatLollipopImpl();
/* 206 */     else if (i >= 19)
/* 207 */       IMPL = new ViewParentCompatKitKatImpl();
/* 208 */     else if (i >= 14)
/* 209 */       IMPL = new ViewParentCompatICSImpl();
/*     */     else
/* 211 */       IMPL = new ViewParentCompatStubImpl();
/*     */   }
/*     */ 
/*     */   static class ViewParentCompatLollipopImpl extends ViewParentCompat.ViewParentCompatKitKatImpl
/*     */   {
/*     */     public boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*     */     {
/* 158 */       return ViewParentCompatLollipop.onStartNestedScroll(paramViewParent, paramView1, paramView2, paramInt);
/*     */     }
/*     */ 
/*     */     public void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*     */     {
/* 165 */       ViewParentCompatLollipop.onNestedScrollAccepted(paramViewParent, paramView1, paramView2, paramInt);
/*     */     }
/*     */ 
/*     */     public void onStopNestedScroll(ViewParent paramViewParent, View paramView)
/*     */     {
/* 171 */       ViewParentCompatLollipop.onStopNestedScroll(paramViewParent, paramView);
/*     */     }
/*     */ 
/*     */     public void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     {
/* 177 */       ViewParentCompatLollipop.onNestedScroll(paramViewParent, paramView, paramInt1, paramInt2, paramInt3, paramInt4);
/*     */     }
/*     */ 
/*     */     public void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
/*     */     {
/* 184 */       ViewParentCompatLollipop.onNestedPreScroll(paramViewParent, paramView, paramInt1, paramInt2, paramArrayOfInt);
/*     */     }
/*     */ 
/*     */     public boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
/*     */     {
/* 190 */       return ViewParentCompatLollipop.onNestedFling(paramViewParent, paramView, paramFloat1, paramFloat2, paramBoolean);
/*     */     }
/*     */ 
/*     */     public boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2)
/*     */     {
/* 197 */       return ViewParentCompatLollipop.onNestedPreFling(paramViewParent, paramView, paramFloat1, paramFloat2);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class ViewParentCompatKitKatImpl extends ViewParentCompat.ViewParentCompatICSImpl
/*     */   {
/*     */     public void notifySubtreeAccessibilityStateChanged(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*     */     {
/* 149 */       ViewParentCompatKitKat.notifySubtreeAccessibilityStateChanged(paramViewParent, paramView1, paramView2, paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class ViewParentCompatICSImpl extends ViewParentCompat.ViewParentCompatStubImpl
/*     */   {
/*     */     public boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/* 140 */       return ViewParentCompatICS.requestSendAccessibilityEvent(paramViewParent, paramView, paramAccessibilityEvent);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class ViewParentCompatStubImpl
/*     */     implements ViewParentCompat.ViewParentCompatImpl
/*     */   {
/*     */     public boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
/*     */     {
/*  58 */       if (paramView == null) {
/*  59 */         return false;
/*     */       }
/*  61 */       AccessibilityManager localAccessibilityManager = (AccessibilityManager)paramView.getContext().getSystemService("accessibility");
/*     */ 
/*  63 */       localAccessibilityManager.sendAccessibilityEvent(paramAccessibilityEvent);
/*  64 */       return true;
/*     */     }
/*     */ 
/*     */     public boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*     */     {
/*  70 */       if ((paramViewParent instanceof NestedScrollingParent)) {
/*  71 */         return ((NestedScrollingParent)paramViewParent).onStartNestedScroll(paramView1, paramView2, paramInt);
/*     */       }
/*     */ 
/*  74 */       return false;
/*     */     }
/*     */ 
/*     */     public void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*     */     {
/*  80 */       if ((paramViewParent instanceof NestedScrollingParent))
/*  81 */         ((NestedScrollingParent)paramViewParent).onNestedScrollAccepted(paramView1, paramView2, paramInt);
/*     */     }
/*     */ 
/*     */     public void onStopNestedScroll(ViewParent paramViewParent, View paramView)
/*     */     {
/*  88 */       if ((paramViewParent instanceof NestedScrollingParent))
/*  89 */         ((NestedScrollingParent)paramViewParent).onStopNestedScroll(paramView);
/*     */     }
/*     */ 
/*     */     public void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */     {
/*  96 */       if ((paramViewParent instanceof NestedScrollingParent))
/*  97 */         ((NestedScrollingParent)paramViewParent).onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
/*     */     }
/*     */ 
/*     */     public void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
/*     */     {
/* 105 */       if ((paramViewParent instanceof NestedScrollingParent))
/* 106 */         ((NestedScrollingParent)paramViewParent).onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt);
/*     */     }
/*     */ 
/*     */     public boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
/*     */     {
/* 113 */       if ((paramViewParent instanceof NestedScrollingParent)) {
/* 114 */         return ((NestedScrollingParent)paramViewParent).onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
/*     */       }
/*     */ 
/* 117 */       return false;
/*     */     }
/*     */ 
/*     */     public boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2)
/*     */     {
/* 123 */       if ((paramViewParent instanceof NestedScrollingParent)) {
/* 124 */         return ((NestedScrollingParent)paramViewParent).onNestedPreFling(paramView, paramFloat1, paramFloat2);
/*     */       }
/*     */ 
/* 127 */       return false;
/*     */     }
/*     */ 
/*     */     public void notifySubtreeAccessibilityStateChanged(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface ViewParentCompatImpl
/*     */   {
/*     */     public abstract boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent);
/*     */ 
/*     */     public abstract boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt);
/*     */ 
/*     */     public abstract void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt);
/*     */ 
/*     */     public abstract void onStopNestedScroll(ViewParent paramViewParent, View paramView);
/*     */ 
/*     */     public abstract void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ 
/*     */     public abstract void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt);
/*     */ 
/*     */     public abstract boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean);
/*     */ 
/*     */     public abstract boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2);
/*     */ 
/*     */     public abstract void notifySubtreeAccessibilityStateChanged(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewParentCompat
 * JD-Core Version:    0.6.2
 */