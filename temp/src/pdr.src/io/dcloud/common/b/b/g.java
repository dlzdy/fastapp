/*    */ package io.dcloud.common.b.b;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.graphics.Bitmap;
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.Paint;
/*    */ import android.graphics.Rect;
/*    */ import android.view.View;
/*    */ import android.view.Window;
/*    */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*    */ import io.dcloud.common.adapter.util.DeviceInfo;
/*    */ 
/*    */ class g extends AdaFrameItem
/*    */ {
/*    */   Bitmap a;
/*    */   int b;
/*    */   int c;
/*    */   int d;
/*    */   int e;
/*    */   int f;
/*    */   int g;
/*    */   Paint h;
/*    */   static g i;
/*    */ 
/*    */   protected void paint(Canvas paramCanvas)
/*    */   {
/* 46 */     if (DeviceInfo.sStatusBarHeight == 0) {
/* 47 */       Rect localRect = new Rect();
/* 48 */       getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
/* 49 */       DeviceInfo.sStatusBarHeight = localRect.top;
/*    */     }
/*    */ 
/* 55 */     this.c = (this.g - DeviceInfo.sStatusBarHeight - this.e);
/*    */ 
/* 57 */     paramCanvas.drawBitmap(this.a, this.b, this.c, this.h);
/*    */   }
/*    */ 
/*    */   private void b() {
/* 61 */     this.d = this.a.getWidth();
/* 62 */     this.e = this.a.getHeight();
/* 63 */     this.b = (this.f - this.d);
/* 64 */     this.c -= this.e;
/*    */   }
/*    */ 
/*    */   protected void onResize() {
/* 68 */     b();
/*    */   }
/*    */ 
/*    */   public static void a()
/*    */   {
/* 90 */     if (i != null) {
/* 91 */       i.dispose();
/* 92 */       i = null;
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.g
 * JD-Core Version:    0.6.2
 */