/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.graphics.Rect;
/*     */ import android.view.WindowInsets;
/*     */ 
/*     */ class WindowInsetsCompatApi21 extends WindowInsetsCompat
/*     */ {
/*     */   private final WindowInsets mSource;
/*     */ 
/*     */   WindowInsetsCompatApi21(WindowInsets paramWindowInsets)
/*     */   {
/*  27 */     this.mSource = paramWindowInsets;
/*     */   }
/*     */ 
/*     */   public int getSystemWindowInsetLeft()
/*     */   {
/*  32 */     return this.mSource.getSystemWindowInsetLeft();
/*     */   }
/*     */ 
/*     */   public int getSystemWindowInsetTop()
/*     */   {
/*  37 */     return this.mSource.getSystemWindowInsetTop();
/*     */   }
/*     */ 
/*     */   public int getSystemWindowInsetRight()
/*     */   {
/*  42 */     return this.mSource.getSystemWindowInsetRight();
/*     */   }
/*     */ 
/*     */   public int getSystemWindowInsetBottom()
/*     */   {
/*  47 */     return this.mSource.getSystemWindowInsetBottom();
/*     */   }
/*     */ 
/*     */   public boolean hasSystemWindowInsets()
/*     */   {
/*  52 */     return this.mSource.hasSystemWindowInsets();
/*     */   }
/*     */ 
/*     */   public boolean hasInsets()
/*     */   {
/*  57 */     return this.mSource.hasInsets();
/*     */   }
/*     */ 
/*     */   public boolean isConsumed()
/*     */   {
/*  62 */     return this.mSource.isConsumed();
/*     */   }
/*     */ 
/*     */   public boolean isRound()
/*     */   {
/*  67 */     return this.mSource.isRound();
/*     */   }
/*     */ 
/*     */   public WindowInsetsCompat consumeSystemWindowInsets()
/*     */   {
/*  72 */     return new WindowInsetsCompatApi21(this.mSource.consumeSystemWindowInsets());
/*     */   }
/*     */ 
/*     */   public WindowInsetsCompat replaceSystemWindowInsets(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  77 */     return new WindowInsetsCompatApi21(this.mSource.replaceSystemWindowInsets(paramInt1, paramInt2, paramInt3, paramInt4));
/*     */   }
/*     */ 
/*     */   public WindowInsetsCompat replaceSystemWindowInsets(Rect paramRect)
/*     */   {
/*  82 */     return new WindowInsetsCompatApi21(this.mSource.replaceSystemWindowInsets(paramRect));
/*     */   }
/*     */ 
/*     */   public int getStableInsetTop()
/*     */   {
/*  87 */     return this.mSource.getStableInsetTop();
/*     */   }
/*     */ 
/*     */   public int getStableInsetLeft()
/*     */   {
/*  92 */     return this.mSource.getStableInsetLeft();
/*     */   }
/*     */ 
/*     */   public int getStableInsetRight()
/*     */   {
/*  97 */     return this.mSource.getStableInsetRight();
/*     */   }
/*     */ 
/*     */   public int getStableInsetBottom()
/*     */   {
/* 102 */     return this.mSource.getStableInsetBottom();
/*     */   }
/*     */ 
/*     */   public boolean hasStableInsets()
/*     */   {
/* 107 */     return this.mSource.hasStableInsets();
/*     */   }
/*     */ 
/*     */   public WindowInsetsCompat consumeStableInsets()
/*     */   {
/* 112 */     return new WindowInsetsCompatApi21(this.mSource.consumeStableInsets());
/*     */   }
/*     */ 
/*     */   WindowInsets unwrap() {
/* 116 */     return this.mSource;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.WindowInsetsCompatApi21
 * JD-Core Version:    0.6.2
 */