/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ 
/*    */ public class NestedScrollingParentHelper
/*    */ {
/*    */   private final ViewGroup mViewGroup;
/*    */   private int mNestedScrollAxes;
/*    */ 
/*    */   public NestedScrollingParentHelper(ViewGroup paramViewGroup)
/*    */   {
/* 46 */     this.mViewGroup = paramViewGroup;
/*    */   }
/*    */ 
/*    */   public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
/*    */   {
/* 58 */     this.mNestedScrollAxes = paramInt;
/*    */   }
/*    */ 
/*    */   public int getNestedScrollAxes()
/*    */   {
/* 69 */     return this.mNestedScrollAxes;
/*    */   }
/*    */ 
/*    */   public void onStopNestedScroll(View paramView)
/*    */   {
/* 82 */     this.mNestedScrollAxes = 0;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.NestedScrollingParentHelper
 * JD-Core Version:    0.6.2
 */