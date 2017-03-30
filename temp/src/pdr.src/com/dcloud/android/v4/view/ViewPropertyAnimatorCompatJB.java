/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.animation.Animator;
/*    */ import android.animation.AnimatorListenerAdapter;
/*    */ import android.view.View;
/*    */ import android.view.ViewPropertyAnimator;
/*    */ 
/*    */ class ViewPropertyAnimatorCompatJB
/*    */ {
/*    */   public static void withStartAction(View paramView, Runnable paramRunnable)
/*    */   {
/* 25 */     paramView.animate().withStartAction(paramRunnable);
/*    */   }
/*    */ 
/*    */   public static void withEndAction(View paramView, Runnable paramRunnable) {
/* 29 */     paramView.animate().withEndAction(paramRunnable);
/*    */   }
/*    */ 
/*    */   public static void withLayer(View paramView) {
/* 33 */     paramView.animate().withLayer();
/*    */   }
/*    */ 
/*    */   public static void setListener(final View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
/*    */   {
/* 38 */     if (paramViewPropertyAnimatorListener != null)
/* 39 */       paramView.animate().setListener(new AnimatorListenerAdapter()
/*    */       {
/*    */         public void onAnimationCancel(Animator paramAnonymousAnimator) {
/* 42 */           this.val$listener.onAnimationCancel(paramView);
/*    */         }
/*    */ 
/*    */         public void onAnimationEnd(Animator paramAnonymousAnimator)
/*    */         {
/* 47 */           this.val$listener.onAnimationEnd(paramView);
/*    */         }
/*    */ 
/*    */         public void onAnimationStart(Animator paramAnonymousAnimator)
/*    */         {
/* 52 */           this.val$listener.onAnimationStart(paramView);
/*    */         }
/*    */       });
/*    */     else
/* 56 */       paramView.animate().setListener(null);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewPropertyAnimatorCompatJB
 * JD-Core Version:    0.6.2
 */