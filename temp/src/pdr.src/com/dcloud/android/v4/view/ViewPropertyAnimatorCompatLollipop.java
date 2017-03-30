/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.view.View;
/*    */ import android.view.ViewPropertyAnimator;
/*    */ 
/*    */ class ViewPropertyAnimatorCompatLollipop
/*    */ {
/*    */   public static void translationZ(View paramView, float paramFloat)
/*    */   {
/* 24 */     paramView.animate().translationZ(paramFloat);
/*    */   }
/*    */ 
/*    */   public static void translationZBy(View paramView, float paramFloat) {
/* 28 */     paramView.animate().translationZBy(paramFloat);
/*    */   }
/*    */ 
/*    */   public static void z(View paramView, float paramFloat) {
/* 32 */     paramView.animate().z(paramFloat);
/*    */   }
/*    */ 
/*    */   public static void zBy(View paramView, float paramFloat) {
/* 36 */     paramView.animate().zBy(paramFloat);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewPropertyAnimatorCompatLollipop
 * JD-Core Version:    0.6.2
 */