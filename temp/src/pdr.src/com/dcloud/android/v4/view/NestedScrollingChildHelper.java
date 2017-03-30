/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.view.View;
/*     */ import android.view.ViewParent;
/*     */ 
/*     */ public class NestedScrollingChildHelper
/*     */ {
/*     */   private final View mView;
/*     */   private ViewParent mNestedScrollingParent;
/*     */   private boolean mIsNestedScrollingEnabled;
/*     */   private int[] mTempNestedScrollConsumed;
/*     */ 
/*     */   public NestedScrollingChildHelper(View paramView)
/*     */   {
/*  47 */     this.mView = paramView;
/*     */   }
/*     */ 
/*     */   public void setNestedScrollingEnabled(boolean paramBoolean)
/*     */   {
/*  60 */     if (this.mIsNestedScrollingEnabled) {
/*  61 */       ViewCompat.stopNestedScroll(this.mView);
/*     */     }
/*  63 */     this.mIsNestedScrollingEnabled = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean isNestedScrollingEnabled()
/*     */   {
/*  76 */     return this.mIsNestedScrollingEnabled;
/*     */   }
/*     */ 
/*     */   public boolean hasNestedScrollingParent()
/*     */   {
/*  90 */     return this.mNestedScrollingParent != null;
/*     */   }
/*     */ 
/*     */   public boolean startNestedScroll(int paramInt)
/*     */   {
/* 105 */     if (hasNestedScrollingParent())
/*     */     {
/* 107 */       return true;
/*     */     }
/* 109 */     if (isNestedScrollingEnabled()) {
/* 110 */       ViewParent localViewParent = this.mView.getParent();
/* 111 */       View localView = this.mView;
/* 112 */       while (localViewParent != null) {
/* 113 */         if (ViewParentCompat.onStartNestedScroll(localViewParent, localView, this.mView, paramInt)) {
/* 114 */           this.mNestedScrollingParent = localViewParent;
/* 115 */           ViewParentCompat.onNestedScrollAccepted(localViewParent, localView, this.mView, paramInt);
/* 116 */           return true;
/*     */         }
/* 118 */         if ((localViewParent instanceof View)) {
/* 119 */           localView = (View)localViewParent;
/*     */         }
/* 121 */         localViewParent = localViewParent.getParent();
/*     */       }
/*     */     }
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */   public void stopNestedScroll()
/*     */   {
/* 135 */     if (this.mNestedScrollingParent != null) {
/* 136 */       ViewParentCompat.onStopNestedScroll(this.mNestedScrollingParent, this.mView);
/* 137 */       this.mNestedScrollingParent = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
/*     */   {
/* 152 */     if ((isNestedScrollingEnabled()) && (this.mNestedScrollingParent != null)) {
/* 153 */       if ((paramInt1 != 0) || (paramInt2 != 0) || (paramInt3 != 0) || (paramInt4 != 0)) {
/* 154 */         int i = 0;
/* 155 */         int j = 0;
/* 156 */         if (paramArrayOfInt != null) {
/* 157 */           this.mView.getLocationInWindow(paramArrayOfInt);
/* 158 */           i = paramArrayOfInt[0];
/* 159 */           j = paramArrayOfInt[1];
/*     */         }
/*     */ 
/* 162 */         ViewParentCompat.onNestedScroll(this.mNestedScrollingParent, this.mView, paramInt1, paramInt2, paramInt3, paramInt4);
/*     */ 
/* 165 */         if (paramArrayOfInt != null) {
/* 166 */           this.mView.getLocationInWindow(paramArrayOfInt);
/* 167 */           paramArrayOfInt[0] -= i;
/* 168 */           paramArrayOfInt[1] -= j;
/*     */         }
/* 170 */         return true;
/* 171 */       }if (paramArrayOfInt != null)
/*     */       {
/* 173 */         paramArrayOfInt[0] = 0;
/* 174 */         paramArrayOfInt[1] = 0;
/*     */       }
/*     */     }
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 190 */     if ((isNestedScrollingEnabled()) && (this.mNestedScrollingParent != null)) {
/* 191 */       if ((paramInt1 != 0) || (paramInt2 != 0)) {
/* 192 */         int i = 0;
/* 193 */         int j = 0;
/* 194 */         if (paramArrayOfInt2 != null) {
/* 195 */           this.mView.getLocationInWindow(paramArrayOfInt2);
/* 196 */           i = paramArrayOfInt2[0];
/* 197 */           j = paramArrayOfInt2[1];
/*     */         }
/*     */ 
/* 200 */         if (paramArrayOfInt1 == null) {
/* 201 */           if (this.mTempNestedScrollConsumed == null) {
/* 202 */             this.mTempNestedScrollConsumed = new int[2];
/*     */           }
/* 204 */           paramArrayOfInt1 = this.mTempNestedScrollConsumed;
/*     */         }
/* 206 */         paramArrayOfInt1[0] = 0;
/* 207 */         paramArrayOfInt1[1] = 0;
/* 208 */         ViewParentCompat.onNestedPreScroll(this.mNestedScrollingParent, this.mView, paramInt1, paramInt2, paramArrayOfInt1);
/*     */ 
/* 210 */         if (paramArrayOfInt2 != null) {
/* 211 */           this.mView.getLocationInWindow(paramArrayOfInt2);
/* 212 */           paramArrayOfInt2[0] -= i;
/* 213 */           paramArrayOfInt2[1] -= j;
/*     */         }
/* 215 */         return (paramArrayOfInt1[0] != 0) || (paramArrayOfInt1[1] != 0);
/* 216 */       }if (paramArrayOfInt2 != null) {
/* 217 */         paramArrayOfInt2[0] = 0;
/* 218 */         paramArrayOfInt2[1] = 0;
/*     */       }
/*     */     }
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
/*     */   {
/* 234 */     if ((isNestedScrollingEnabled()) && (this.mNestedScrollingParent != null)) {
/* 235 */       return ViewParentCompat.onNestedFling(this.mNestedScrollingParent, this.mView, paramFloat1, paramFloat2, paramBoolean);
/*     */     }
/*     */ 
/* 238 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
/*     */   {
/* 251 */     if ((isNestedScrollingEnabled()) && (this.mNestedScrollingParent != null)) {
/* 252 */       return ViewParentCompat.onNestedPreFling(this.mNestedScrollingParent, this.mView, paramFloat1, paramFloat2);
/*     */     }
/*     */ 
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */   public void onDetachedFromWindow()
/*     */   {
/* 267 */     ViewCompat.stopNestedScroll(this.mView);
/*     */   }
/*     */ 
/*     */   public void onStopNestedScroll(View paramView)
/*     */   {
/* 280 */     ViewCompat.stopNestedScroll(this.mView);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.NestedScrollingChildHelper
 * JD-Core Version:    0.6.2
 */