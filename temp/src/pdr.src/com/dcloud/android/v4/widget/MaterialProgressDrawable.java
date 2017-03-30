/*     */ package com.dcloud.android.v4.widget;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.ColorFilter;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Paint.Cap;
/*     */ import android.graphics.Paint.Style;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.Path.FillType;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.RectF;
/*     */ import android.graphics.drawable.Animatable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.graphics.drawable.Drawable.Callback;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.View;
/*     */ import android.view.animation.Animation;
/*     */ import android.view.animation.Animation.AnimationListener;
/*     */ import android.view.animation.Interpolator;
/*     */ import android.view.animation.LinearInterpolator;
/*     */ import android.view.animation.Transformation;
/*     */ import com.dcloud.android.annotation.IntDef;
/*     */ import com.dcloud.android.annotation.NonNull;
/*     */ import com.dcloud.android.v4.view.animation.FastOutSlowInInterpolator;
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.annotation.Retention;
/*     */ import java.lang.annotation.RetentionPolicy;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ class MaterialProgressDrawable extends Drawable
/*     */   implements Animatable
/*     */ {
/*  52 */   private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
/*  53 */   private static final Interpolator MATERIAL_INTERPOLATOR = new FastOutSlowInInterpolator();
/*     */   private static final float FULL_ROTATION = 1080.0F;
/*     */   static final int LARGE = 0;
/*     */   static final int DEFAULT = 1;
/*     */   private static final int CIRCLE_DIAMETER = 40;
/*     */   private static final float CENTER_RADIUS = 8.75F;
/*     */   private static final float STROKE_WIDTH = 2.5F;
/*     */   private static final int CIRCLE_DIAMETER_LARGE = 56;
/*     */   private static final float CENTER_RADIUS_LARGE = 12.5F;
/*     */   private static final float STROKE_WIDTH_LARGE = 3.0F;
/*  74 */   private final int[] COLORS = { -16777216 };
/*     */   private static final float COLOR_START_DELAY_OFFSET = 0.75F;
/*     */   private static final float END_TRIM_START_DELAY_OFFSET = 0.5F;
/*     */   private static final float START_TRIM_DURATION_OFFSET = 0.5F;
/*     */   private static final int ANIMATION_DURATION = 1332;
/*     */   private static final float NUM_POINTS = 5.0F;
/*  92 */   private final ArrayList<Animation> mAnimators = new ArrayList();
/*     */   private final Ring mRing;
/*     */   private float mRotation;
/*     */   private static final int ARROW_WIDTH = 10;
/*     */   private static final int ARROW_HEIGHT = 5;
/*     */   private static final float ARROW_OFFSET_ANGLE = 5.0F;
/*     */   private static final int ARROW_WIDTH_LARGE = 12;
/*     */   private static final int ARROW_HEIGHT_LARGE = 6;
/*     */   private static final float MAX_PROGRESS_ARC = 0.8F;
/*     */   private Resources mResources;
/*     */   private View mParent;
/*     */   private Animation mAnimation;
/*     */   private float mRotationCount;
/*     */   private double mWidth;
/*     */   private double mHeight;
/*     */   boolean mFinishing;
/* 449 */   private final Drawable.Callback mCallback = new Drawable.Callback()
/*     */   {
/*     */     public void invalidateDrawable(Drawable paramAnonymousDrawable) {
/* 452 */       MaterialProgressDrawable.this.invalidateSelf();
/*     */     }
/*     */ 
/*     */     public void scheduleDrawable(Drawable paramAnonymousDrawable, Runnable paramAnonymousRunnable, long paramAnonymousLong)
/*     */     {
/* 457 */       MaterialProgressDrawable.this.scheduleSelf(paramAnonymousRunnable, paramAnonymousLong);
/*     */     }
/*     */ 
/*     */     public void unscheduleDrawable(Drawable paramAnonymousDrawable, Runnable paramAnonymousRunnable)
/*     */     {
/* 462 */       MaterialProgressDrawable.this.unscheduleSelf(paramAnonymousRunnable);
/*     */     }
/* 449 */   };
/*     */ 
/*     */   public MaterialProgressDrawable(Context paramContext, View paramView)
/*     */   {
/* 119 */     this.mParent = paramView;
/* 120 */     this.mResources = paramContext.getResources();
/*     */ 
/* 122 */     this.mRing = new Ring(this.mCallback);
/* 123 */     this.mRing.setColors(this.COLORS);
/*     */ 
/* 125 */     updateSizes(1);
/* 126 */     setupAnimators();
/*     */   }
/*     */ 
/*     */   private void setSizeParameters(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, float paramFloat1, float paramFloat2)
/*     */   {
/* 131 */     Ring localRing = this.mRing;
/* 132 */     DisplayMetrics localDisplayMetrics = this.mResources.getDisplayMetrics();
/* 133 */     float f = localDisplayMetrics.density;
/*     */ 
/* 135 */     this.mWidth = (paramDouble1 * f);
/* 136 */     this.mHeight = (paramDouble2 * f);
/* 137 */     localRing.setStrokeWidth((float)paramDouble4 * f);
/* 138 */     localRing.setCenterRadius(paramDouble3 * f);
/* 139 */     localRing.setColorIndex(0);
/* 140 */     localRing.setArrowDimensions(paramFloat1 * f, paramFloat2 * f);
/* 141 */     localRing.setInsets((int)this.mWidth, (int)this.mHeight);
/*     */   }
/*     */ 
/*     */   public void updateSizes(@ProgressDrawableSize int paramInt)
/*     */   {
/* 152 */     if (paramInt == 0) {
/* 153 */       setSizeParameters(56.0D, 56.0D, 12.5D, 3.0D, 12.0F, 6.0F);
/*     */     }
/*     */     else
/* 156 */       setSizeParameters(40.0D, 40.0D, 8.75D, 2.5D, 10.0F, 5.0F);
/*     */   }
/*     */ 
/*     */   public void showArrow(boolean paramBoolean)
/*     */   {
/* 165 */     this.mRing.setShowArrow(paramBoolean);
/*     */   }
/*     */ 
/*     */   public void setArrowScale(float paramFloat)
/*     */   {
/* 172 */     this.mRing.setArrowScale(paramFloat);
/*     */   }
/*     */ 
/*     */   public void setStartEndTrim(float paramFloat1, float paramFloat2)
/*     */   {
/* 182 */     this.mRing.setStartTrim(paramFloat1);
/* 183 */     this.mRing.setEndTrim(paramFloat2);
/*     */   }
/*     */ 
/*     */   public void setProgressRotation(float paramFloat)
/*     */   {
/* 192 */     this.mRing.setRotation(paramFloat);
/*     */   }
/*     */ 
/*     */   public void setBackgroundColor(int paramInt)
/*     */   {
/* 199 */     this.mRing.setBackgroundColor(paramInt);
/*     */   }
/*     */ 
/*     */   public void setColorSchemeColors(int[] paramArrayOfInt)
/*     */   {
/* 210 */     this.mRing.setColors(paramArrayOfInt);
/* 211 */     this.mRing.setColorIndex(0);
/*     */   }
/*     */ 
/*     */   public int getIntrinsicHeight()
/*     */   {
/* 216 */     return (int)this.mHeight;
/*     */   }
/*     */ 
/*     */   public int getIntrinsicWidth()
/*     */   {
/* 221 */     return (int)this.mWidth;
/*     */   }
/*     */ 
/*     */   public void draw(Canvas paramCanvas)
/*     */   {
/* 226 */     Rect localRect = getBounds();
/* 227 */     int i = paramCanvas.save();
/* 228 */     paramCanvas.rotate(this.mRotation, localRect.exactCenterX(), localRect.exactCenterY());
/* 229 */     this.mRing.draw(paramCanvas, localRect);
/* 230 */     paramCanvas.restoreToCount(i);
/*     */   }
/*     */ 
/*     */   public void setAlpha(int paramInt)
/*     */   {
/* 235 */     this.mRing.setAlpha(paramInt);
/*     */   }
/*     */ 
/*     */   public int getAlpha() {
/* 239 */     return this.mRing.getAlpha();
/*     */   }
/*     */ 
/*     */   public void setColorFilter(ColorFilter paramColorFilter)
/*     */   {
/* 244 */     this.mRing.setColorFilter(paramColorFilter);
/*     */   }
/*     */ 
/*     */   void setRotation(float paramFloat)
/*     */   {
/* 249 */     this.mRotation = paramFloat;
/* 250 */     invalidateSelf();
/*     */   }
/*     */ 
/*     */   private float getRotation()
/*     */   {
/* 255 */     return this.mRotation;
/*     */   }
/*     */ 
/*     */   public int getOpacity()
/*     */   {
/* 260 */     return -3;
/*     */   }
/*     */ 
/*     */   public boolean isRunning()
/*     */   {
/* 265 */     ArrayList localArrayList = this.mAnimators;
/* 266 */     int i = localArrayList.size();
/* 267 */     for (int j = 0; j < i; j++) {
/* 268 */       Animation localAnimation = (Animation)localArrayList.get(j);
/* 269 */       if ((localAnimation.hasStarted()) && (!localAnimation.hasEnded())) {
/* 270 */         return true;
/*     */       }
/*     */     }
/* 273 */     return false;
/*     */   }
/*     */ 
/*     */   public void start()
/*     */   {
/* 278 */     this.mAnimation.reset();
/* 279 */     this.mRing.storeOriginals();
/*     */ 
/* 281 */     if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
/* 282 */       this.mFinishing = true;
/* 283 */       this.mAnimation.setDuration(666L);
/* 284 */       this.mParent.startAnimation(this.mAnimation);
/*     */     } else {
/* 286 */       this.mRing.setColorIndex(0);
/* 287 */       this.mRing.resetOriginals();
/* 288 */       this.mAnimation.setDuration(1332L);
/* 289 */       this.mParent.startAnimation(this.mAnimation);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/* 295 */     this.mParent.clearAnimation();
/* 296 */     setRotation(0.0F);
/* 297 */     this.mRing.setShowArrow(false);
/* 298 */     this.mRing.setColorIndex(0);
/* 299 */     this.mRing.resetOriginals();
/*     */   }
/*     */ 
/*     */   private float getMinProgressArc(Ring paramRing) {
/* 303 */     return (float)Math.toRadians(paramRing.getStrokeWidth() / (6.283185307179586D * paramRing.getCenterRadius()));
/*     */   }
/*     */ 
/*     */   private int evaluateColorChange(float paramFloat, int paramInt1, int paramInt2)
/*     */   {
/* 309 */     int i = Integer.valueOf(paramInt1).intValue();
/* 310 */     int j = i >> 24 & 0xFF;
/* 311 */     int k = i >> 16 & 0xFF;
/* 312 */     int m = i >> 8 & 0xFF;
/* 313 */     int n = i & 0xFF;
/*     */ 
/* 315 */     int i1 = Integer.valueOf(paramInt2).intValue();
/* 316 */     int i2 = i1 >> 24 & 0xFF;
/* 317 */     int i3 = i1 >> 16 & 0xFF;
/* 318 */     int i4 = i1 >> 8 & 0xFF;
/* 319 */     int i5 = i1 & 0xFF;
/*     */ 
/* 321 */     return j + (int)(paramFloat * (i2 - j)) << 24 | k + (int)(paramFloat * (i3 - k)) << 16 | m + (int)(paramFloat * (i4 - m)) << 8 | n + (int)(paramFloat * (i5 - n));
/*     */   }
/*     */ 
/*     */   private void updateRingColor(float paramFloat, Ring paramRing)
/*     */   {
/* 333 */     if (paramFloat > 0.75F)
/*     */     {
/* 337 */       paramRing.setColor(evaluateColorChange((paramFloat - 0.75F) / 0.25F, paramRing.getStartingColor(), paramRing.getNextColor()));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void applyFinishTranslation(float paramFloat, Ring paramRing)
/*     */   {
/* 347 */     updateRingColor(paramFloat, paramRing);
/* 348 */     float f1 = (float)(Math.floor(paramRing.getStartingRotation() / 0.8F) + 1.0D);
/*     */ 
/* 350 */     float f2 = getMinProgressArc(paramRing);
/* 351 */     float f3 = paramRing.getStartingStartTrim() + (paramRing.getStartingEndTrim() - f2 - paramRing.getStartingStartTrim()) * paramFloat;
/*     */ 
/* 354 */     paramRing.setStartTrim(f3);
/* 355 */     paramRing.setEndTrim(paramRing.getStartingEndTrim());
/* 356 */     float f4 = paramRing.getStartingRotation() + (f1 - paramRing.getStartingRotation()) * paramFloat;
/*     */ 
/* 358 */     paramRing.setRotation(f4);
/*     */   }
/*     */ 
/*     */   private void setupAnimators() {
/* 362 */     final Ring localRing = this.mRing;
/* 363 */     Animation local1 = new Animation()
/*     */     {
/*     */       public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation) {
/* 366 */         if (MaterialProgressDrawable.this.mFinishing) {
/* 367 */           MaterialProgressDrawable.this.applyFinishTranslation(paramAnonymousFloat, localRing);
/*     */         }
/*     */         else
/*     */         {
/* 371 */           float f1 = MaterialProgressDrawable.this.getMinProgressArc(localRing);
/* 372 */           float f2 = localRing.getStartingEndTrim();
/* 373 */           float f3 = localRing.getStartingStartTrim();
/* 374 */           float f4 = localRing.getStartingRotation();
/*     */ 
/* 376 */           MaterialProgressDrawable.this.updateRingColor(paramAnonymousFloat, localRing);
/*     */ 
/* 380 */           if (paramAnonymousFloat <= 0.5F)
/*     */           {
/* 384 */             f5 = paramAnonymousFloat / 0.5F;
/*     */ 
/* 386 */             f6 = f3 + (0.8F - f1) * MaterialProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f5);
/*     */ 
/* 389 */             localRing.setStartTrim(f6);
/*     */           }
/*     */ 
/* 394 */           if (paramAnonymousFloat > 0.5F)
/*     */           {
/* 398 */             f5 = 0.8F - f1;
/* 399 */             f6 = (paramAnonymousFloat - 0.5F) / 0.5F;
/*     */ 
/* 401 */             float f7 = f2 + f5 * MaterialProgressDrawable.MATERIAL_INTERPOLATOR.getInterpolation(f6);
/*     */ 
/* 403 */             localRing.setEndTrim(f7);
/*     */           }
/*     */ 
/* 406 */           float f5 = f4 + 0.25F * paramAnonymousFloat;
/* 407 */           localRing.setRotation(f5);
/*     */ 
/* 409 */           float f6 = 216.0F * paramAnonymousFloat + 1080.0F * (MaterialProgressDrawable.this.mRotationCount / 5.0F);
/*     */ 
/* 411 */           MaterialProgressDrawable.this.setRotation(f6);
/*     */         }
/*     */       }
/*     */     };
/* 415 */     local1.setRepeatCount(-1);
/* 416 */     local1.setRepeatMode(1);
/* 417 */     local1.setInterpolator(LINEAR_INTERPOLATOR);
/* 418 */     local1.setAnimationListener(new Animation.AnimationListener()
/*     */     {
/*     */       public void onAnimationStart(Animation paramAnonymousAnimation)
/*     */       {
/* 422 */         MaterialProgressDrawable.this.mRotationCount = 0.0F;
/*     */       }
/*     */ 
/*     */       public void onAnimationEnd(Animation paramAnonymousAnimation)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void onAnimationRepeat(Animation paramAnonymousAnimation)
/*     */       {
/* 432 */         localRing.storeOriginals();
/* 433 */         localRing.goToNextColor();
/* 434 */         localRing.setStartTrim(localRing.getEndTrim());
/* 435 */         if (MaterialProgressDrawable.this.mFinishing)
/*     */         {
/* 438 */           MaterialProgressDrawable.this.mFinishing = false;
/* 439 */           paramAnonymousAnimation.setDuration(1332L);
/* 440 */           localRing.setShowArrow(false);
/*     */         } else {
/* 442 */           MaterialProgressDrawable.this.mRotationCount = ((MaterialProgressDrawable.this.mRotationCount + 1.0F) % 5.0F);
/*     */         }
/*     */       }
/*     */     });
/* 446 */     this.mAnimation = local1;
/*     */   }
/*     */ 
/*     */   private static class Ring {
/* 467 */     private final RectF mTempBounds = new RectF();
/* 468 */     private final Paint mPaint = new Paint();
/* 469 */     private final Paint mArrowPaint = new Paint();
/*     */     private final Drawable.Callback mCallback;
/* 473 */     private float mStartTrim = 0.0F;
/* 474 */     private float mEndTrim = 0.0F;
/* 475 */     private float mRotation = 0.0F;
/* 476 */     private float mStrokeWidth = 5.0F;
/* 477 */     private float mStrokeInset = 2.5F;
/*     */     private int[] mColors;
/*     */     private int mColorIndex;
/*     */     private float mStartingStartTrim;
/*     */     private float mStartingEndTrim;
/*     */     private float mStartingRotation;
/*     */     private boolean mShowArrow;
/*     */     private Path mArrow;
/*     */     private float mArrowScale;
/*     */     private double mRingCenterRadius;
/*     */     private int mArrowWidth;
/*     */     private int mArrowHeight;
/*     */     private int mAlpha;
/* 494 */     private final Paint mCirclePaint = new Paint(1);
/*     */     private int mBackgroundColor;
/*     */     private int mCurrentColor;
/*     */ 
/*     */     public Ring(Drawable.Callback paramCallback) {
/* 499 */       this.mCallback = paramCallback;
/*     */ 
/* 501 */       this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
/* 502 */       this.mPaint.setAntiAlias(true);
/* 503 */       this.mPaint.setStyle(Paint.Style.STROKE);
/*     */ 
/* 505 */       this.mArrowPaint.setStyle(Paint.Style.FILL);
/* 506 */       this.mArrowPaint.setAntiAlias(true);
/*     */     }
/*     */ 
/*     */     public void setBackgroundColor(int paramInt) {
/* 510 */       this.mBackgroundColor = paramInt;
/*     */     }
/*     */ 
/*     */     public void setArrowDimensions(float paramFloat1, float paramFloat2)
/*     */     {
/* 520 */       this.mArrowWidth = ((int)paramFloat1);
/* 521 */       this.mArrowHeight = ((int)paramFloat2);
/*     */     }
/*     */ 
/*     */     public void draw(Canvas paramCanvas, Rect paramRect)
/*     */     {
/* 528 */       RectF localRectF = this.mTempBounds;
/* 529 */       localRectF.set(paramRect);
/* 530 */       localRectF.inset(this.mStrokeInset, this.mStrokeInset);
/*     */ 
/* 532 */       float f1 = (this.mStartTrim + this.mRotation) * 360.0F;
/* 533 */       float f2 = (this.mEndTrim + this.mRotation) * 360.0F;
/* 534 */       float f3 = f2 - f1;
/*     */ 
/* 536 */       this.mPaint.setColor(this.mCurrentColor);
/* 537 */       paramCanvas.drawArc(localRectF, f1, f3, false, this.mPaint);
/*     */ 
/* 539 */       drawTriangle(paramCanvas, f1, f3, paramRect);
/*     */ 
/* 541 */       if (this.mAlpha < 255) {
/* 542 */         this.mCirclePaint.setColor(this.mBackgroundColor);
/* 543 */         this.mCirclePaint.setAlpha(255 - this.mAlpha);
/* 544 */         paramCanvas.drawCircle(paramRect.exactCenterX(), paramRect.exactCenterY(), paramRect.width() / 2, this.mCirclePaint);
/*     */       }
/*     */     }
/*     */ 
/*     */     private void drawTriangle(Canvas paramCanvas, float paramFloat1, float paramFloat2, Rect paramRect)
/*     */     {
/* 550 */       if (this.mShowArrow) {
/* 551 */         if (this.mArrow == null) {
/* 552 */           this.mArrow = new Path();
/* 553 */           this.mArrow.setFillType(Path.FillType.EVEN_ODD);
/*     */         } else {
/* 555 */           this.mArrow.reset();
/*     */         }
/*     */ 
/* 560 */         float f1 = (int)this.mStrokeInset / 2 * this.mArrowScale;
/* 561 */         float f2 = (float)(this.mRingCenterRadius * Math.cos(0.0D) + paramRect.exactCenterX());
/* 562 */         float f3 = (float)(this.mRingCenterRadius * Math.sin(0.0D) + paramRect.exactCenterY());
/*     */ 
/* 568 */         this.mArrow.moveTo(0.0F, 0.0F);
/* 569 */         this.mArrow.lineTo(this.mArrowWidth * this.mArrowScale, 0.0F);
/* 570 */         this.mArrow.lineTo(this.mArrowWidth * this.mArrowScale / 2.0F, this.mArrowHeight * this.mArrowScale);
/*     */ 
/* 572 */         this.mArrow.offset(f2 - f1, f3);
/* 573 */         this.mArrow.close();
/*     */ 
/* 575 */         this.mArrowPaint.setColor(this.mCurrentColor);
/* 576 */         paramCanvas.rotate(paramFloat1 + paramFloat2 - 5.0F, paramRect.exactCenterX(), paramRect.exactCenterY());
/*     */ 
/* 578 */         paramCanvas.drawPath(this.mArrow, this.mArrowPaint);
/*     */       }
/*     */     }
/*     */ 
/*     */     public void setColors(@NonNull int[] paramArrayOfInt)
/*     */     {
/* 588 */       this.mColors = paramArrayOfInt;
/*     */ 
/* 590 */       setColorIndex(0);
/*     */     }
/*     */ 
/*     */     public void setColor(int paramInt)
/*     */     {
/* 601 */       this.mCurrentColor = paramInt;
/*     */     }
/*     */ 
/*     */     public void setColorIndex(int paramInt)
/*     */     {
/* 609 */       this.mColorIndex = paramInt;
/* 610 */       this.mCurrentColor = this.mColors[this.mColorIndex];
/*     */     }
/*     */ 
/*     */     public int getNextColor()
/*     */     {
/* 617 */       return this.mColors[getNextColorIndex()];
/*     */     }
/*     */ 
/*     */     private int getNextColorIndex() {
/* 621 */       return (this.mColorIndex + 1) % this.mColors.length;
/*     */     }
/*     */ 
/*     */     public void goToNextColor()
/*     */     {
/* 629 */       setColorIndex(getNextColorIndex());
/*     */     }
/*     */ 
/*     */     public void setColorFilter(ColorFilter paramColorFilter) {
/* 633 */       this.mPaint.setColorFilter(paramColorFilter);
/* 634 */       invalidateSelf();
/*     */     }
/*     */ 
/*     */     public void setAlpha(int paramInt)
/*     */     {
/* 641 */       this.mAlpha = paramInt;
/*     */     }
/*     */ 
/*     */     public int getAlpha()
/*     */     {
/* 648 */       return this.mAlpha;
/*     */     }
/*     */ 
/*     */     public void setStrokeWidth(float paramFloat)
/*     */     {
/* 655 */       this.mStrokeWidth = paramFloat;
/* 656 */       this.mPaint.setStrokeWidth(paramFloat);
/* 657 */       invalidateSelf();
/*     */     }
/*     */ 
/*     */     public float getStrokeWidth()
/*     */     {
/* 662 */       return this.mStrokeWidth;
/*     */     }
/*     */ 
/*     */     public void setStartTrim(float paramFloat)
/*     */     {
/* 667 */       this.mStartTrim = paramFloat;
/* 668 */       invalidateSelf();
/*     */     }
/*     */ 
/*     */     public float getStartTrim()
/*     */     {
/* 673 */       return this.mStartTrim;
/*     */     }
/*     */ 
/*     */     public float getStartingStartTrim() {
/* 677 */       return this.mStartingStartTrim;
/*     */     }
/*     */ 
/*     */     public float getStartingEndTrim() {
/* 681 */       return this.mStartingEndTrim;
/*     */     }
/*     */ 
/*     */     public int getStartingColor() {
/* 685 */       return this.mColors[this.mColorIndex];
/*     */     }
/*     */ 
/*     */     public void setEndTrim(float paramFloat)
/*     */     {
/* 690 */       this.mEndTrim = paramFloat;
/* 691 */       invalidateSelf();
/*     */     }
/*     */ 
/*     */     public float getEndTrim()
/*     */     {
/* 696 */       return this.mEndTrim;
/*     */     }
/*     */ 
/*     */     public void setRotation(float paramFloat)
/*     */     {
/* 701 */       this.mRotation = paramFloat;
/* 702 */       invalidateSelf();
/*     */     }
/*     */ 
/*     */     public float getRotation()
/*     */     {
/* 707 */       return this.mRotation;
/*     */     }
/*     */ 
/*     */     public void setInsets(int paramInt1, int paramInt2) {
/* 711 */       float f1 = Math.min(paramInt1, paramInt2);
/*     */       float f2;
/* 713 */       if ((this.mRingCenterRadius <= 0.0D) || (f1 < 0.0F))
/* 714 */         f2 = (float)Math.ceil(this.mStrokeWidth / 2.0F);
/*     */       else {
/* 716 */         f2 = (float)(f1 / 2.0F - this.mRingCenterRadius);
/*     */       }
/* 718 */       this.mStrokeInset = f2;
/*     */     }
/*     */ 
/*     */     public float getInsets()
/*     */     {
/* 723 */       return this.mStrokeInset;
/*     */     }
/*     */ 
/*     */     public void setCenterRadius(double paramDouble)
/*     */     {
/* 731 */       this.mRingCenterRadius = paramDouble;
/*     */     }
/*     */ 
/*     */     public double getCenterRadius() {
/* 735 */       return this.mRingCenterRadius;
/*     */     }
/*     */ 
/*     */     public void setShowArrow(boolean paramBoolean)
/*     */     {
/* 742 */       if (this.mShowArrow != paramBoolean) {
/* 743 */         this.mShowArrow = paramBoolean;
/* 744 */         invalidateSelf();
/*     */       }
/*     */     }
/*     */ 
/*     */     public void setArrowScale(float paramFloat)
/*     */     {
/* 752 */       if (paramFloat != this.mArrowScale) {
/* 753 */         this.mArrowScale = paramFloat;
/* 754 */         invalidateSelf();
/*     */       }
/*     */     }
/*     */ 
/*     */     public float getStartingRotation()
/*     */     {
/* 762 */       return this.mStartingRotation;
/*     */     }
/*     */ 
/*     */     public void storeOriginals()
/*     */     {
/* 770 */       this.mStartingStartTrim = this.mStartTrim;
/* 771 */       this.mStartingEndTrim = this.mEndTrim;
/* 772 */       this.mStartingRotation = this.mRotation;
/*     */     }
/*     */ 
/*     */     public void resetOriginals()
/*     */     {
/* 779 */       this.mStartingStartTrim = 0.0F;
/* 780 */       this.mStartingEndTrim = 0.0F;
/* 781 */       this.mStartingRotation = 0.0F;
/* 782 */       setStartTrim(0.0F);
/* 783 */       setEndTrim(0.0F);
/* 784 */       setRotation(0.0F);
/*     */     }
/*     */ 
/*     */     private void invalidateSelf() {
/* 788 */       this.mCallback.invalidateDrawable(null);
/*     */     }
/*     */   }
/*     */ 
/*     */   @IntDef({0L, 1L})
/*     */   @Retention(RetentionPolicy.CLASS)
/*     */   public static @interface ProgressDrawableSize
/*     */   {
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.widget.MaterialProgressDrawable
 * JD-Core Version:    0.6.2
 */