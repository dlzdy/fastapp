/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.graphics.Rect;
/*     */ 
/*     */ public class WindowInsetsCompat
/*     */ {
/*     */   public int getSystemWindowInsetLeft()
/*     */   {
/*  43 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getSystemWindowInsetTop()
/*     */   {
/*  56 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getSystemWindowInsetRight()
/*     */   {
/*  69 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getSystemWindowInsetBottom()
/*     */   {
/*  82 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean hasSystemWindowInsets()
/*     */   {
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean hasInsets()
/*     */   {
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isConsumed()
/*     */   {
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isRound()
/*     */   {
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   public WindowInsetsCompat consumeSystemWindowInsets()
/*     */   {
/* 144 */     return this;
/*     */   }
/*     */ 
/*     */   public WindowInsetsCompat replaceSystemWindowInsets(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 158 */     return this;
/*     */   }
/*     */ 
/*     */   public WindowInsetsCompat replaceSystemWindowInsets(Rect paramRect)
/*     */   {
/* 170 */     return this;
/*     */   }
/*     */ 
/*     */   public int getStableInsetTop()
/*     */   {
/* 185 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getStableInsetLeft()
/*     */   {
/* 201 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getStableInsetRight()
/*     */   {
/* 216 */     return 0;
/*     */   }
/*     */ 
/*     */   public int getStableInsetBottom()
/*     */   {
/* 232 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean hasStableInsets()
/*     */   {
/* 247 */     return false;
/*     */   }
/*     */ 
/*     */   public WindowInsetsCompat consumeStableInsets()
/*     */   {
/* 256 */     return this;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.WindowInsetsCompat
 * JD-Core Version:    0.6.2
 */