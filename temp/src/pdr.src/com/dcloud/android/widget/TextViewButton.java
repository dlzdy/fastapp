/*    */ package com.dcloud.android.widget;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.MotionEvent;
/*    */ import android.widget.TextView;
/*    */ 
/*    */ public class TextViewButton extends TextView
/*    */ {
/*    */   public TextViewButton(Context paramContext, AttributeSet paramAttributeSet)
/*    */   {
/* 13 */     super(paramContext, paramAttributeSet);
/*    */   }
/*    */ 
/*    */   public boolean onTouchEvent(MotionEvent paramMotionEvent)
/*    */   {
/* 18 */     int i = paramMotionEvent.getAction();
/* 19 */     if (i == 0)
/* 20 */       setBackgroundColor(-3355444);
/* 21 */     else if ((i == 1) || (i == 4)) {
/* 22 */       setBackgroundColor(-1118482);
/*    */     }
/*    */ 
/* 29 */     return super.onTouchEvent(paramMotionEvent);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.widget.TextViewButton
 * JD-Core Version:    0.6.2
 */