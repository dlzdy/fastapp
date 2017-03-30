/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.view.View;
/*    */ import android.view.ViewPropertyAnimator;
/*    */ import android.view.animation.Interpolator;
/*    */ 
/*    */ class ViewPropertyAnimatorCompatJellybeanMr2
/*    */ {
/*    */   public static Interpolator getInterpolator(View paramView)
/*    */   {
/* 23 */     return (Interpolator)paramView.animate().getInterpolator();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewPropertyAnimatorCompatJellybeanMr2
 * JD-Core Version:    0.6.2
 */