/*     */ package com.dcloud.android.v4.widget;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.RadialGradient;
/*     */ import android.graphics.Shader.TileMode;
/*     */ import android.graphics.drawable.ShapeDrawable;
/*     */ import android.graphics.drawable.shapes.OvalShape;
/*     */ import android.os.Build.VERSION;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.animation.Animation.AnimationListener;
/*     */ import android.widget.ImageView;
/*     */ import com.dcloud.android.v4.view.ViewCompat;
/*     */ 
/*     */ class CircleImageView extends ImageView
/*     */ {
/*     */   private static final int KEY_SHADOW_COLOR = 1577058304;
/*     */   private static final int FILL_SHADOW_COLOR = 1023410176;
/*     */   private static final float X_OFFSET = 0.0F;
/*     */   private static final float Y_OFFSET = 1.0F;
/*     */   private static final float SHADOW_RADIUS = 2.5F;
/*     */   private static final int SHADOW_ELEVATION = 4;
/*     */   private Animation.AnimationListener mListener;
/*     */   private int mShadowRadius;
/*  50 */   boolean mUseElevation = true;
/*     */ 
/*  52 */   public CircleImageView(Context paramContext, int paramInt, float paramFloat, boolean paramBoolean) { super(paramContext);
/*  53 */     float f = getContext().getResources().getDisplayMetrics().density;
/*  54 */     int i = (int)(paramFloat * f * 2.0F);
/*  55 */     int j = (int)(f * 1.0F);
/*  56 */     int k = (int)(f * 0.0F);
/*  57 */     this.mUseElevation = paramBoolean;
/*  58 */     this.mShadowRadius = ((int)(f * 2.5F));
/*     */     ShapeDrawable localShapeDrawable;
/*  61 */     if (elevationSupported()) {
/*  62 */       localShapeDrawable = new ShapeDrawable(new OvalShape());
/*  63 */       ViewCompat.setElevation(this, 4.0F * f);
/*     */     } else {
/*  65 */       OvalShadow localOvalShadow = new OvalShadow(this.mShadowRadius, i);
/*  66 */       localShapeDrawable = new ShapeDrawable(localOvalShadow);
/*  67 */       ViewCompat.setLayerType(this, 1, localShapeDrawable.getPaint());
/*  68 */       localShapeDrawable.getPaint().setShadowLayer(this.mShadowRadius, k, j, 1577058304);
/*     */ 
/*  70 */       int m = this.mShadowRadius;
/*     */ 
/*  72 */       setPadding(m, m, m, m);
/*     */     }
/*  74 */     localShapeDrawable.getPaint().setColor(paramInt);
/*  75 */     setBackgroundDrawable(localShapeDrawable); }
/*     */ 
/*     */   public CircleImageView(Context paramContext, int paramInt, float paramFloat) {
/*  78 */     this(paramContext, paramInt, paramFloat, true);
/*     */   }
/*     */ 
/*     */   private boolean elevationSupported() {
/*  82 */     return (Build.VERSION.SDK_INT >= 21) && (this.mUseElevation);
/*     */   }
/*     */ 
/*     */   protected void onMeasure(int paramInt1, int paramInt2)
/*     */   {
/*  87 */     super.onMeasure(paramInt1, paramInt2);
/*  88 */     if (!elevationSupported())
/*  89 */       setMeasuredDimension(getMeasuredWidth() + this.mShadowRadius * 2, getMeasuredHeight() + this.mShadowRadius * 2);
/*     */   }
/*     */ 
/*     */   public void setAnimationListener(Animation.AnimationListener paramAnimationListener)
/*     */   {
/*  95 */     this.mListener = paramAnimationListener;
/*     */   }
/*     */ 
/*     */   public void onAnimationStart()
/*     */   {
/* 100 */     super.onAnimationStart();
/* 101 */     if (this.mListener != null)
/* 102 */       this.mListener.onAnimationStart(getAnimation());
/*     */   }
/*     */ 
/*     */   public void onAnimationEnd()
/*     */   {
/* 108 */     super.onAnimationEnd();
/* 109 */     if (this.mListener != null)
/* 110 */       this.mListener.onAnimationEnd(getAnimation());
/*     */   }
/*     */ 
/*     */   public void setBackgroundColorRes(int paramInt)
/*     */   {
/* 120 */     setBackgroundColor(getContext().getResources().getColor(paramInt));
/*     */   }
/*     */ 
/*     */   public void setBackgroundColor(int paramInt)
/*     */   {
/* 125 */     if ((getBackground() instanceof ShapeDrawable))
/* 126 */       ((ShapeDrawable)getBackground()).getPaint().setColor(paramInt);
/*     */   }
/*     */ 
/*     */   private class OvalShadow extends OvalShape {
/*     */     private RadialGradient mRadialGradient;
/*     */     private Paint mShadowPaint;
/*     */     private int mCircleDiameter;
/*     */ 
/*     */     public OvalShadow(int paramInt1, int arg3) {
/* 137 */       this.mShadowPaint = new Paint();
/* 138 */       CircleImageView.this.mShadowRadius = paramInt1;
/*     */       int i;
/* 139 */       this.mCircleDiameter = i;
/* 140 */       this.mRadialGradient = new RadialGradient(this.mCircleDiameter / 2, this.mCircleDiameter / 2, CircleImageView.this.mShadowRadius, new int[] { 1023410176, 0 }, null, Shader.TileMode.CLAMP);
/*     */ 
/* 144 */       this.mShadowPaint.setShader(this.mRadialGradient);
/*     */     }
/*     */ 
/*     */     public void draw(Canvas paramCanvas, Paint paramPaint)
/*     */     {
/* 149 */       int i = CircleImageView.this.getWidth();
/* 150 */       int j = CircleImageView.this.getHeight();
/* 151 */       paramCanvas.drawCircle(i / 2, j / 2, this.mCircleDiameter / 2 + CircleImageView.this.mShadowRadius, this.mShadowPaint);
/*     */ 
/* 153 */       paramCanvas.drawCircle(i / 2, j / 2, this.mCircleDiameter / 2, paramPaint);
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.widget.CircleImageView
 * JD-Core Version:    0.6.2
 */