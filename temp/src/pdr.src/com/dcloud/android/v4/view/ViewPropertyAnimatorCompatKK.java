/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.animation.ValueAnimator;
/*    */ import android.animation.ValueAnimator.AnimatorUpdateListener;
/*    */ import android.view.View;
/*    */ import android.view.ViewPropertyAnimator;
/*    */ 
/*    */ class ViewPropertyAnimatorCompatKK
/*    */ {
/*    */   public static void setUpdateListener(final View paramView, ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener)
/*    */   {
/* 25 */     paramView.animate().setUpdateListener(new ValueAnimator.AnimatorUpdateListener()
/*    */     {
/*    */       public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator) {
/* 28 */         this.val$listener.onAnimationUpdate(paramView);
/*    */       }
/*    */     });
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewPropertyAnimatorCompatKK
 * JD-Core Version:    0.6.2
 */