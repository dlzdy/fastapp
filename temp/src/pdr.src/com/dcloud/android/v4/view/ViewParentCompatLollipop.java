/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.util.Log;
/*    */ import android.view.View;
/*    */ import android.view.ViewParent;
/*    */ 
/*    */ class ViewParentCompatLollipop
/*    */ {
/*    */   private static final String TAG = "ViewParentCompat";
/*    */ 
/*    */   public static boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*    */   {
/*    */     try
/*    */     {
/* 30 */       return paramViewParent.onStartNestedScroll(paramView1, paramView2, paramInt);
/*    */     } catch (AbstractMethodError localAbstractMethodError) {
/* 32 */       Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onStartNestedScroll", localAbstractMethodError);
/*    */     }
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */   public static void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
/*    */   {
/*    */     try
/*    */     {
/* 41 */       paramViewParent.onNestedScrollAccepted(paramView1, paramView2, paramInt);
/*    */     } catch (AbstractMethodError localAbstractMethodError) {
/* 43 */       Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedScrollAccepted", localAbstractMethodError);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void onStopNestedScroll(ViewParent paramViewParent, View paramView)
/*    */   {
/*    */     try {
/* 50 */       paramViewParent.onStopNestedScroll(paramView);
/*    */     } catch (AbstractMethodError localAbstractMethodError) {
/* 52 */       Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onStopNestedScroll", localAbstractMethodError);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*    */   {
/*    */     try
/*    */     {
/* 60 */       paramViewParent.onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
/*    */     } catch (AbstractMethodError localAbstractMethodError) {
/* 62 */       Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedScroll", localAbstractMethodError);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
/*    */   {
/*    */     try
/*    */     {
/* 70 */       paramViewParent.onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt);
/*    */     } catch (AbstractMethodError localAbstractMethodError) {
/* 72 */       Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedPreScroll", localAbstractMethodError);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
/*    */   {
/*    */     try
/*    */     {
/* 80 */       return paramViewParent.onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
/*    */     } catch (AbstractMethodError localAbstractMethodError) {
/* 82 */       Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedFling", localAbstractMethodError);
/*    */     }
/* 84 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2)
/*    */   {
/*    */     try
/*    */     {
/* 91 */       return paramViewParent.onNestedPreFling(paramView, paramFloat1, paramFloat2);
/*    */     } catch (AbstractMethodError localAbstractMethodError) {
/* 93 */       Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedPreFling", localAbstractMethodError);
/*    */     }
/* 95 */     return false;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewParentCompatLollipop
 * JD-Core Version:    0.6.2
 */