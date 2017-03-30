/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.view.MotionEvent;
/*    */ 
/*    */ class MotionEventCompatEclair
/*    */ {
/*    */   public static int findPointerIndex(MotionEvent paramMotionEvent, int paramInt)
/*    */   {
/* 26 */     return paramMotionEvent.findPointerIndex(paramInt);
/*    */   }
/*    */   public static int getPointerId(MotionEvent paramMotionEvent, int paramInt) {
/* 29 */     return paramMotionEvent.getPointerId(paramInt);
/*    */   }
/*    */   public static float getX(MotionEvent paramMotionEvent, int paramInt) {
/* 32 */     return paramMotionEvent.getX(paramInt);
/*    */   }
/*    */   public static float getY(MotionEvent paramMotionEvent, int paramInt) {
/* 35 */     return paramMotionEvent.getY(paramInt);
/*    */   }
/*    */   public static int getPointerCount(MotionEvent paramMotionEvent) {
/* 38 */     return paramMotionEvent.getPointerCount();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.MotionEventCompatEclair
 * JD-Core Version:    0.6.2
 */