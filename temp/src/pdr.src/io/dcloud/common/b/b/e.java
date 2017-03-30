/*    */ package io.dcloud.common.b.b;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.graphics.Bitmap;
/*    */ import android.view.MotionEvent;
/*    */ import android.widget.FrameLayout.LayoutParams;
/*    */ import android.widget.ImageView;
/*    */ import io.dcloud.nineoldandroids.view.ViewHelper;
/*    */ 
/*    */ public class e extends ImageView
/*    */ {
/* 14 */   boolean a = true;
/* 15 */   public long b = 0L;
/* 16 */   public long c = 0L;
/*    */   public Bitmap d;
/* 19 */   boolean e = false;
/*    */ 
/* 21 */   public boolean a() { return this.e; }
/*    */ 
/*    */   public void a(boolean paramBoolean) {
/* 24 */     this.e = paramBoolean;
/*    */   }
/*    */   public e(Context paramContext) {
/* 27 */     super(paramContext);
/*    */   }
/*    */ 
/*    */   public Bitmap b()
/*    */   {
/* 32 */     return this.d;
/*    */   }
/*    */ 
/*    */   public void setImageBitmap(Bitmap paramBitmap)
/*    */   {
/* 37 */     if (this.e) {
/* 38 */       a(paramBitmap);
/* 39 */       return;
/*    */     }
/* 41 */     a(this.d);
/* 42 */     this.d = paramBitmap;
/* 43 */     if (paramBitmap != null) {
/* 44 */       this.b = paramBitmap.getWidth();
/* 45 */       this.c = paramBitmap.getHeight();
/*    */     } else {
/* 47 */       this.b = 0L;
/* 48 */       this.c = 0L;
/*    */     }
/* 50 */     super.setImageBitmap(paramBitmap);
/*    */   }
/*    */ 
/*    */   public void a(Bitmap paramBitmap) {
/* 54 */     if ((paramBitmap != null) && (!paramBitmap.isRecycled()))
/* 55 */       paramBitmap.recycle();
/*    */   }
/*    */ 
/*    */   public void c() {
/* 59 */     ViewHelper.setAlpha(this, 1.0F);
/* 60 */     ViewHelper.setScaleX(this, 1.0F);
/* 61 */     ViewHelper.setScaleY(this, 1.0F);
/* 62 */     ViewHelper.setRotationX(this, 0.0F);
/* 63 */     ViewHelper.setRotationY(this, 0.0F);
/* 64 */     ViewHelper.setTranslationX(this, 0.0F);
/* 65 */     ViewHelper.setTranslationY(this, 0.0F);
/* 66 */     ViewHelper.setX(this, 0.0F);
/* 67 */     ViewHelper.setY(this, 0.0F);
/* 68 */     ViewHelper.setScrollX(this, 0);
/* 69 */     ViewHelper.setScrollY(this, 0);
/* 70 */     this.e = false;
/* 71 */     setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
/* 72 */     requestLayout();
/*    */   }
/*    */ 
/*    */   public void b(boolean paramBoolean)
/*    */   {
/* 80 */     this.a = paramBoolean;
/*    */   }
/*    */ 
/*    */   public boolean onTouchEvent(MotionEvent paramMotionEvent)
/*    */   {
/* 86 */     if (this.a) {
/* 87 */       return true;
/*    */     }
/* 89 */     return super.onTouchEvent(paramMotionEvent);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.e
 * JD-Core Version:    0.6.2
 */