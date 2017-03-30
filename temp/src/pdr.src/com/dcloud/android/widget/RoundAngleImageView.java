/*    */ package com.dcloud.android.widget;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.graphics.Canvas;
/*    */ import android.graphics.Path;
/*    */ import android.graphics.Path.Direction;
/*    */ import android.graphics.RectF;
/*    */ import android.util.AttributeSet;
/*    */ import android.widget.ImageView;
/*    */ import io.dcloud.common.adapter.util.CanvasHelper;
/*    */ 
/*    */ public class RoundAngleImageView extends ImageView
/*    */ {
/* 20 */   Path path = new Path();
/*    */ 
/*    */   public RoundAngleImageView(Context paramContext, AttributeSet paramAttributeSet)
/*    */   {
/* 18 */     super(paramContext, paramAttributeSet);
/*    */   }
/*    */ 
/*    */   protected void onDraw(Canvas paramCanvas)
/*    */   {
/* 24 */     paramCanvas.save();
/* 25 */     RectF localRectF = new RectF(0.0F, 0.0F, getWidth(), getHeight());
/* 26 */     this.path.addRoundRect(localRectF, CanvasHelper.dip2px(getContext(), 8.0F), CanvasHelper.dip2px(getContext(), 8.0F), Path.Direction.CCW);
/* 27 */     paramCanvas.clipPath(this.path);
/* 28 */     if (getDrawable() == null) {
/* 29 */       paramCanvas.drawColor(-3355444);
/*    */     }
/* 31 */     super.onDraw(paramCanvas);
/* 32 */     paramCanvas.restore();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.widget.RoundAngleImageView
 * JD-Core Version:    0.6.2
 */