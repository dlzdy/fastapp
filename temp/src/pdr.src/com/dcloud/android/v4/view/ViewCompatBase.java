/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.content.res.ColorStateList;
/*     */ import android.graphics.PorterDuff.Mode;
/*     */ import android.view.View;
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ class ViewCompatBase
/*     */ {
/*     */   private static final String TAG = "ViewCompatBase";
/*     */   private static Field sMinWidthField;
/*     */   private static boolean sMinWidthFieldFetched;
/*     */   private static Field sMinHeightField;
/*     */   private static boolean sMinHeightFieldFetched;
/*     */ 
/*     */   static ColorStateList getBackgroundTintList(View paramView)
/*     */   {
/*  35 */     return (paramView instanceof TintableBackgroundView) ? ((TintableBackgroundView)paramView).getSupportBackgroundTintList() : null;
/*     */   }
/*     */ 
/*     */   static void setBackgroundTintList(View paramView, ColorStateList paramColorStateList)
/*     */   {
/*  41 */     if ((paramView instanceof TintableBackgroundView))
/*  42 */       ((TintableBackgroundView)paramView).setSupportBackgroundTintList(paramColorStateList);
/*     */   }
/*     */ 
/*     */   static PorterDuff.Mode getBackgroundTintMode(View paramView)
/*     */   {
/*  47 */     return (paramView instanceof TintableBackgroundView) ? ((TintableBackgroundView)paramView).getSupportBackgroundTintMode() : null;
/*     */   }
/*     */ 
/*     */   static void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode)
/*     */   {
/*  53 */     if ((paramView instanceof TintableBackgroundView))
/*  54 */       ((TintableBackgroundView)paramView).setSupportBackgroundTintMode(paramMode);
/*     */   }
/*     */ 
/*     */   static boolean isLaidOut(View paramView)
/*     */   {
/*  59 */     return (paramView.getWidth() > 0) && (paramView.getHeight() > 0);
/*     */   }
/*     */ 
/*     */   static int getMinimumWidth(View paramView) {
/*  63 */     if (!sMinWidthFieldFetched) {
/*     */       try {
/*  65 */         sMinWidthField = View.class.getDeclaredField("mMinWidth");
/*  66 */         sMinWidthField.setAccessible(true);
/*     */       }
/*     */       catch (NoSuchFieldException localNoSuchFieldException) {
/*     */       }
/*  70 */       sMinWidthFieldFetched = true;
/*     */     }
/*     */ 
/*  73 */     if (sMinWidthField != null) {
/*     */       try {
/*  75 */         return ((Integer)sMinWidthField.get(paramView)).intValue();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */ 
/*  82 */     return 0;
/*     */   }
/*     */ 
/*     */   static int getMinimumHeight(View paramView) {
/*  86 */     if (!sMinHeightFieldFetched) {
/*     */       try {
/*  88 */         sMinHeightField = View.class.getDeclaredField("mMinHeight");
/*  89 */         sMinHeightField.setAccessible(true);
/*     */       }
/*     */       catch (NoSuchFieldException localNoSuchFieldException) {
/*     */       }
/*  93 */       sMinHeightFieldFetched = true;
/*     */     }
/*     */ 
/*  96 */     if (sMinHeightField != null) {
/*     */       try {
/*  98 */         return ((Integer)sMinHeightField.get(paramView)).intValue();
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */ 
/* 105 */     return 0;
/*     */   }
/*     */ 
/*     */   static boolean isAttachedToWindow(View paramView) {
/* 109 */     return paramView.getWindowToken() != null;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewCompatBase
 * JD-Core Version:    0.6.2
 */