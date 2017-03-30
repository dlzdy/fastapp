/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.content.res.ColorStateList;
/*     */ import android.graphics.PorterDuff.Mode;
/*     */ import android.view.View;
/*     */ import android.view.View.OnApplyWindowInsetsListener;
/*     */ import android.view.WindowInsets;
/*     */ 
/*     */ class ViewCompatLollipop
/*     */ {
/*     */   public static void setTransitionName(View paramView, String paramString)
/*     */   {
/*  27 */     paramView.setTransitionName(paramString);
/*     */   }
/*     */ 
/*     */   public static String getTransitionName(View paramView) {
/*  31 */     return paramView.getTransitionName();
/*     */   }
/*     */ 
/*     */   public static void requestApplyInsets(View paramView) {
/*  35 */     paramView.requestApplyInsets();
/*     */   }
/*     */ 
/*     */   public static void setElevation(View paramView, float paramFloat) {
/*  39 */     paramView.setElevation(paramFloat);
/*     */   }
/*     */ 
/*     */   public static float getElevation(View paramView) {
/*  43 */     return paramView.getElevation();
/*     */   }
/*     */ 
/*     */   public static void setTranslationZ(View paramView, float paramFloat) {
/*  47 */     paramView.setTranslationZ(paramFloat);
/*     */   }
/*     */ 
/*     */   public static float getTranslationZ(View paramView) {
/*  51 */     return paramView.getTranslationZ();
/*     */   }
/*     */ 
/*     */   public static void setOnApplyWindowInsetsListener(View paramView, OnApplyWindowInsetsListener paramOnApplyWindowInsetsListener)
/*     */   {
/*  56 */     paramView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener()
/*     */     {
/*     */       public WindowInsets onApplyWindowInsets(View paramAnonymousView, WindowInsets paramAnonymousWindowInsets)
/*     */       {
/*  60 */         WindowInsetsCompatApi21 localWindowInsetsCompatApi21 = new WindowInsetsCompatApi21(paramAnonymousWindowInsets);
/*     */ 
/*  62 */         localWindowInsetsCompatApi21 = (WindowInsetsCompatApi21)this.val$listener.onApplyWindowInsets(paramAnonymousView, localWindowInsetsCompatApi21);
/*     */ 
/*  64 */         return localWindowInsetsCompatApi21.unwrap();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public static boolean isImportantForAccessibility(View paramView) {
/*  70 */     return paramView.isImportantForAccessibility();
/*     */   }
/*     */ 
/*     */   static ColorStateList getBackgroundTintList(View paramView) {
/*  74 */     return paramView.getBackgroundTintList();
/*     */   }
/*     */ 
/*     */   static void setBackgroundTintList(View paramView, ColorStateList paramColorStateList) {
/*  78 */     paramView.setBackgroundTintList(paramColorStateList);
/*     */   }
/*     */ 
/*     */   static PorterDuff.Mode getBackgroundTintMode(View paramView) {
/*  82 */     return paramView.getBackgroundTintMode();
/*     */   }
/*     */ 
/*     */   static void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode) {
/*  86 */     paramView.setBackgroundTintMode(paramMode);
/*     */   }
/*     */ 
/*     */   public static WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat) {
/*  90 */     if ((paramWindowInsetsCompat instanceof WindowInsetsCompatApi21))
/*     */     {
/*  92 */       WindowInsets localWindowInsets1 = ((WindowInsetsCompatApi21)paramWindowInsetsCompat).unwrap();
/*     */ 
/*  94 */       WindowInsets localWindowInsets2 = paramView.onApplyWindowInsets(localWindowInsets1);
/*     */ 
/*  96 */       if (localWindowInsets2 != localWindowInsets1)
/*     */       {
/*  98 */         paramWindowInsetsCompat = new WindowInsetsCompatApi21(localWindowInsets2);
/*     */       }
/*     */     }
/* 101 */     return paramWindowInsetsCompat;
/*     */   }
/*     */ 
/*     */   public static WindowInsetsCompat dispatchApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat) {
/* 105 */     if ((paramWindowInsetsCompat instanceof WindowInsetsCompatApi21))
/*     */     {
/* 107 */       WindowInsets localWindowInsets1 = ((WindowInsetsCompatApi21)paramWindowInsetsCompat).unwrap();
/*     */ 
/* 109 */       WindowInsets localWindowInsets2 = paramView.dispatchApplyWindowInsets(localWindowInsets1);
/*     */ 
/* 111 */       if (localWindowInsets2 != localWindowInsets1)
/*     */       {
/* 113 */         paramWindowInsetsCompat = new WindowInsetsCompatApi21(localWindowInsets2);
/*     */       }
/*     */     }
/* 116 */     return paramWindowInsetsCompat;
/*     */   }
/*     */ 
/*     */   public static void setNestedScrollingEnabled(View paramView, boolean paramBoolean) {
/* 120 */     paramView.setNestedScrollingEnabled(paramBoolean);
/*     */   }
/*     */ 
/*     */   public static boolean isNestedScrollingEnabled(View paramView) {
/* 124 */     return paramView.isNestedScrollingEnabled();
/*     */   }
/*     */ 
/*     */   public static boolean startNestedScroll(View paramView, int paramInt) {
/* 128 */     return paramView.startNestedScroll(paramInt);
/*     */   }
/*     */ 
/*     */   public static void stopNestedScroll(View paramView) {
/* 132 */     paramView.stopNestedScroll();
/*     */   }
/*     */ 
/*     */   public static boolean hasNestedScrollingParent(View paramView) {
/* 136 */     return paramView.hasNestedScrollingParent();
/*     */   }
/*     */ 
/*     */   public static boolean dispatchNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
/*     */   {
/* 141 */     return paramView.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
/*     */   }
/*     */ 
/*     */   public static boolean dispatchNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 147 */     return paramView.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
/*     */   }
/*     */ 
/*     */   public static boolean dispatchNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
/*     */   {
/* 152 */     return paramView.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static boolean dispatchNestedPreFling(View paramView, float paramFloat1, float paramFloat2) {
/* 156 */     return paramView.dispatchNestedPreFling(paramFloat1, paramFloat2);
/*     */   }
/*     */ 
/*     */   public static float getZ(View paramView) {
/* 160 */     return paramView.getZ();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewCompatLollipop
 * JD-Core Version:    0.6.2
 */