/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.util.Log;
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ class ViewCompatEclairMr1
/*    */ {
/*    */   public static final String TAG = "ViewCompat";
/*    */   private static Method sChildrenDrawingOrderMethod;
/*    */ 
/*    */   public static boolean isOpaque(View paramView)
/*    */   {
/* 33 */     return paramView.isOpaque();
/*    */   }
/*    */ 
/*    */   public static void setChildrenDrawingOrderEnabled(ViewGroup paramViewGroup, boolean paramBoolean) {
/* 37 */     if (sChildrenDrawingOrderMethod == null) {
/*    */       try {
/* 39 */         sChildrenDrawingOrderMethod = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[] { Boolean.TYPE });
/*    */       }
/*    */       catch (NoSuchMethodException localNoSuchMethodException) {
/* 42 */         Log.e("ViewCompat", "Unable to find childrenDrawingOrderEnabled", localNoSuchMethodException);
/*    */       }
/* 44 */       sChildrenDrawingOrderMethod.setAccessible(true);
/*    */     }
/*    */     try {
/* 47 */       sChildrenDrawingOrderMethod.invoke(paramViewGroup, new Object[] { Boolean.valueOf(paramBoolean) });
/*    */     } catch (IllegalAccessException localIllegalAccessException) {
/* 49 */       Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", localIllegalAccessException);
/*    */     } catch (IllegalArgumentException localIllegalArgumentException) {
/* 51 */       Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", localIllegalArgumentException);
/*    */     } catch (InvocationTargetException localInvocationTargetException) {
/* 53 */       Log.e("ViewCompat", "Unable to invoke childrenDrawingOrderEnabled", localInvocationTargetException);
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewCompatEclairMr1
 * JD-Core Version:    0.6.2
 */