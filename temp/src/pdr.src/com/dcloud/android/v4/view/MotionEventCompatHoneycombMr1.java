/*    */ package com.dcloud.android.v4.view;
/*    */ 
/*    */ import android.view.MotionEvent;
/*    */ 
/*    */ class MotionEventCompatHoneycombMr1
/*    */ {
/*    */   static float getAxisValue(MotionEvent paramMotionEvent, int paramInt)
/*    */   {
/* 26 */     return paramMotionEvent.getAxisValue(paramInt);
/*    */   }
/*    */ 
/*    */   static float getAxisValue(MotionEvent paramMotionEvent, int paramInt1, int paramInt2) {
/* 30 */     return paramMotionEvent.getAxisValue(paramInt1, paramInt2);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.MotionEventCompatHoneycombMr1
 * JD-Core Version:    0.6.2
 */