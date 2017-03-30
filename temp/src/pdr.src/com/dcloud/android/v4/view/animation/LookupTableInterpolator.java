/*    */ package com.dcloud.android.v4.view.animation;
/*    */ 
/*    */ import android.view.animation.Interpolator;
/*    */ 
/*    */ abstract class LookupTableInterpolator
/*    */   implements Interpolator
/*    */ {
/*    */   private final float[] mValues;
/*    */   private final float mStepSize;
/*    */ 
/*    */   public LookupTableInterpolator(float[] paramArrayOfFloat)
/*    */   {
/* 31 */     this.mValues = paramArrayOfFloat;
/* 32 */     this.mStepSize = (1.0F / (this.mValues.length - 1));
/*    */   }
/*    */ 
/*    */   public float getInterpolation(float paramFloat)
/*    */   {
/* 37 */     if (paramFloat >= 1.0F) {
/* 38 */       return 1.0F;
/*    */     }
/* 40 */     if (paramFloat <= 0.0F) {
/* 41 */       return 0.0F;
/*    */     }
/*    */ 
/* 46 */     int i = Math.min((int)(paramFloat * (this.mValues.length - 1)), this.mValues.length - 2);
/*    */ 
/* 49 */     float f1 = i * this.mStepSize;
/* 50 */     float f2 = paramFloat - f1;
/* 51 */     float f3 = f2 / this.mStepSize;
/*    */ 
/* 54 */     return this.mValues[i] + f3 * (this.mValues[(i + 1)] - this.mValues[i]);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.animation.LookupTableInterpolator
 * JD-Core Version:    0.6.2
 */