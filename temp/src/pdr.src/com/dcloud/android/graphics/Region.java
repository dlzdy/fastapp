/*    */ package com.dcloud.android.graphics;
/*    */ 
/*    */ import android.os.Build.VERSION;
/*    */ 
/*    */ public class Region extends android.graphics.Region
/*    */ {
/* 13 */   private int HOLD_SCREEN_COUNT = 2;
/*    */ 
/* 15 */   int fillScreenCounter = 1;
/*    */ 
/* 17 */   public Region() { this(0); }
/*    */ 
/*    */ 
/*    */   public Region(int paramInt)
/*    */   {
/* 22 */     if (Build.VERSION.SDK_INT >= 21)
/* 23 */       this.HOLD_SCREEN_COUNT = 1;
/*    */     else
/* 25 */       this.HOLD_SCREEN_COUNT = paramInt;
/*    */   }
/*    */ 
/*    */   boolean fillWholeScreen()
/*    */   {
/* 33 */     return this.fillScreenCounter == this.HOLD_SCREEN_COUNT;
/*    */   }
/*    */ 
/*    */   void count()
/*    */   {
/* 39 */     this.fillScreenCounter += 1;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.graphics.Region
 * JD-Core Version:    0.6.2
 */