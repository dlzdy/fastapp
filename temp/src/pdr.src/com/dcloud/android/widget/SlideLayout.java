/*     */ package com.dcloud.android.widget;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.text.TextUtils;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.VelocityTracker;
/*     */ import android.view.View;
/*     */ import android.view.ViewConfiguration;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.widget.AbsoluteLayout;
/*     */ import android.widget.Scroller;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class SlideLayout extends AbsoluteLayout
/*     */ {
/*  21 */   private boolean mInterceptEventEnable = true;
/*     */ 
/*  23 */   private boolean mIsHandledTouchEvent = false;
/*     */   private int mTouchSlop;
/*  27 */   private float mLastMotionX = -1.0F;
/*     */   private VelocityTracker mVelocityTracker;
/*     */   private static final int SNAP_VELOCITY = 1000;
/*  32 */   private boolean mCanDoSlideTransverseEvent = false;
/*     */ 
/*  34 */   private int mSlideTransverseLeftMaxWitch = 0;
/*     */ 
/*  36 */   private int mSlideTransverseRightMaxWitch = 0;
/*     */ 
/*  38 */   private int mSlideLeftPosition = -1;
/*     */ 
/*  40 */   private int mSlideRightPosition = -1;
/*     */ 
/*  43 */   boolean isRightSlide = false;
/*     */ 
/*  45 */   boolean isLeftSlide = false;
/*     */ 
/*  47 */   boolean isSlideOpen = false;
/*     */   private static final int SCROLL_DURATION = 150;
/*     */   private Scroller mScroller;
/*  51 */   private float mFirstX = 0.0F;
/*  52 */   private static String LEFT = "left";
/*  53 */   private static String RIGHT = "right";
/*     */ 
/*  55 */   private static String BEFORE_SLIDE = "beforeSlide";
/*     */ 
/*  57 */   private static String AFTER_SLIDE = "afterSlide";
/*     */   private OnStateChangeListener mChangeListener;
/*     */ 
/*     */   public void setOnStateChangeListener(OnStateChangeListener paramOnStateChangeListener)
/*     */   {
/*  63 */     this.mChangeListener = paramOnStateChangeListener;
/*     */   }
/*     */ 
/*     */   public SlideLayout(Context paramContext) {
/*  67 */     super(paramContext);
/*  68 */     this.mScroller = new Scroller(getContext());
/*  69 */     this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
/*     */   }
/*     */   public void setWidth(int paramInt) {
/*  72 */     ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
/*  73 */     if (localLayoutParams != null) {
/*  74 */       localLayoutParams.width = paramInt;
/*  75 */       requestLayout();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setHeight(int paramInt) {
/*  80 */     ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
/*  81 */     if (localLayoutParams != null) {
/*  82 */       localLayoutParams.height = paramInt;
/*  83 */       requestLayout();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void initSlideInfo(JSONObject paramJSONObject, float paramFloat, int paramInt) {
/*  88 */     JSONObject localJSONObject1 = JSONUtil.getJSONObject(paramJSONObject, "slideoffset");
/*  89 */     if (localJSONObject1 != null) {
/*  90 */       JSONObject localJSONObject2 = JSONUtil.getJSONObject(paramJSONObject, "position");
/*  91 */       if (localJSONObject2 != null) {
/*  92 */         str1 = localJSONObject2.optString(LEFT);
/*  93 */         str2 = localJSONObject2.optString(RIGHT);
/*  94 */         if (!TextUtils.isEmpty(str1)) {
/*  95 */           this.mSlideLeftPosition = PdrUtil.convertToScreenInt(str1, paramInt, paramInt / 2, paramFloat);
/*     */         }
/*  97 */         if (!TextUtils.isEmpty(str2)) {
/*  98 */           this.mSlideRightPosition = PdrUtil.convertToScreenInt(str2, paramInt, paramInt / 2, paramFloat);
/*     */         }
/*     */       }
/* 101 */       this.mInterceptEventEnable = paramJSONObject.optBoolean("preventTouchEvent", true);
/* 102 */       String str1 = JSONUtil.getString(localJSONObject1, LEFT);
/* 103 */       if (!TextUtils.isEmpty(str1)) {
/* 104 */         this.isLeftSlide = (this.mSlideLeftPosition > 0);
/* 105 */         this.mSlideTransverseLeftMaxWitch = PdrUtil.convertToScreenInt(str1, paramInt, paramInt / 2, paramFloat);
/*     */       }
/*     */ 
/* 108 */       String str2 = JSONUtil.getString(localJSONObject1, RIGHT);
/* 109 */       if (!TextUtils.isEmpty(str2)) {
/* 110 */         this.isRightSlide = (this.mSlideRightPosition > 0);
/* 111 */         this.mSlideTransverseRightMaxWitch = PdrUtil.convertToScreenInt(str2, paramInt, paramInt / 2, paramFloat);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
/*     */   {
/* 120 */     int i = paramMotionEvent.getAction();
/* 121 */     if (!this.mInterceptEventEnable) {
/* 122 */       return false;
/*     */     }
/* 124 */     if ((!this.isLeftSlide) && (!this.isRightSlide)) {
/* 125 */       return false;
/*     */     }
/* 127 */     if ((i == 3) || (i == 1)) {
/* 128 */       this.mIsHandledTouchEvent = false;
/* 129 */       clearChildrenCache();
/* 130 */       return this.mIsHandledTouchEvent;
/*     */     }
/*     */ 
/* 133 */     if ((i != 0) && (this.mIsHandledTouchEvent)) {
/* 134 */       return true;
/*     */     }
/* 136 */     switch (i) {
/*     */     case 0:
/* 138 */       this.mLastMotionX = paramMotionEvent.getX();
/* 139 */       this.mFirstX = paramMotionEvent.getX();
/* 140 */       this.mIsHandledTouchEvent = false;
/* 141 */       this.mCanDoSlideTransverseEvent = false;
/* 142 */       break;
/*     */     case 2:
/* 145 */       float f = paramMotionEvent.getX();
/* 146 */       int j = (int)Math.abs(f - this.mFirstX);
/* 147 */       if (j > this.mTouchSlop) {
/* 148 */         enableChildrenCache();
/* 149 */         this.mIsHandledTouchEvent = true;
/* 150 */         this.mCanDoSlideTransverseEvent = true;
/* 151 */         requestDisallowInterceptTouchEvent(true);
/*     */       }
/*     */       break;
/*     */     }
/* 155 */     return this.mIsHandledTouchEvent;
/*     */   }
/*     */ 
/*     */   public boolean onTouchEvent(MotionEvent paramMotionEvent)
/*     */   {
/* 161 */     if ((this.mCanDoSlideTransverseEvent) && (this.mVelocityTracker == null))
/* 162 */       this.mVelocityTracker = VelocityTracker.obtain();
/*     */     int j;
/*     */     int k;
/*     */     int m;
/* 164 */     switch (paramMotionEvent.getAction()) {
/*     */     case 0:
/* 166 */       this.mIsHandledTouchEvent = false;
/* 167 */       if (!this.mScroller.isFinished())
/* 168 */         this.mScroller.abortAnimation(); break;
/*     */     case 2:
/* 174 */       if (this.mVelocityTracker != null) {
/* 175 */         this.mVelocityTracker.addMovement(paramMotionEvent);
/*     */       }
/* 177 */       float f = paramMotionEvent.getX();
/* 178 */       if (this.mCanDoSlideTransverseEvent) {
/* 179 */         int i = (int)(this.mLastMotionX - f);
/* 180 */         this.mLastMotionX = f;
/* 181 */         j = getScrollX();
/* 182 */         k = getChildAt(getChildCount() - 1).getRight() - j - getWidth();
/* 183 */         if (j == 0) {
/* 184 */           if ((this.isRightSlide) && (i > 0))
/* 185 */             scrollBy(i, 0);
/* 186 */           else if ((this.isLeftSlide) && (i < 0))
/* 187 */             scrollBy(i, 0);
/*     */         }
/* 189 */         else if ((j > 0) && (k < 0) && (this.isRightSlide)) {
/* 190 */           m = i + j;
/* 191 */           if (Math.abs(m) <= this.mSlideRightPosition) {
/* 192 */             if (m < 0) {
/* 193 */               scrollBy(0, 0);
/*     */             }
/* 195 */             else if (Math.abs(m) >= this.mSlideTransverseRightMaxWitch)
/* 196 */               scrollBy((int)(i / 1.5D), 0);
/*     */             else {
/* 198 */               scrollBy(i, 0);
/*     */             }
/*     */           }
/*     */         }
/* 202 */         else if ((j < 0) && (k > 0) && (this.isLeftSlide)) {
/* 203 */           m = i + j;
/* 204 */           if (Math.abs(m) <= this.mSlideLeftPosition) {
/* 205 */             if (m > 0) {
/* 206 */               scrollBy(0, 0);
/*     */             }
/* 208 */             else if (Math.abs(m) >= this.mSlideTransverseLeftMaxWitch)
/* 209 */               scrollBy((int)(i / 1.5D), 0);
/*     */             else {
/* 211 */               scrollBy(i, 0);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 216 */       break;
/*     */     case 1:
/*     */     case 3:
/* 220 */       if (this.mIsHandledTouchEvent) {
/* 221 */         this.mIsHandledTouchEvent = false;
/* 222 */         if (this.mCanDoSlideTransverseEvent) {
/* 223 */           requestDisallowInterceptTouchEvent(false);
/* 224 */           VelocityTracker localVelocityTracker = this.mVelocityTracker;
/* 225 */           localVelocityTracker.computeCurrentVelocity(1000);
/* 226 */           j = (int)localVelocityTracker.getXVelocity();
/* 227 */           k = getScrollX();
/*     */ 
/* 229 */           if (j > 1000) {
/* 230 */             if ((k < 0) && (this.isLeftSlide) && (this.mSlideLeftPosition >= this.mSlideTransverseLeftMaxWitch)) {
/* 231 */               m = this.mSlideTransverseLeftMaxWitch - Math.abs(k);
/* 232 */               smoothScrollTo(-m, 0);
/* 233 */               this.isSlideOpen = true;
/* 234 */               setState(LEFT, AFTER_SLIDE);
/* 235 */             } else if ((k > 0) && (this.isRightSlide)) {
/* 236 */               smoothScrollTo(-k, 0);
/* 237 */               this.isSlideOpen = false;
/* 238 */               setState(RIGHT, BEFORE_SLIDE);
/*     */             } else {
/* 240 */               upSlideTo(k);
/*     */             }
/* 242 */           } else if (j < -1000) {
/* 243 */             if ((k < 0) && (this.isLeftSlide)) {
/* 244 */               smoothScrollTo(-k, 0);
/* 245 */               this.isSlideOpen = false;
/* 246 */               setState(LEFT, BEFORE_SLIDE);
/* 247 */             } else if ((k > 0) && (this.isRightSlide) && (this.mSlideRightPosition >= this.mSlideTransverseRightMaxWitch)) {
/* 248 */               m = this.mSlideTransverseRightMaxWitch - Math.abs(k);
/* 249 */               smoothScrollTo(m, 0);
/* 250 */               this.isSlideOpen = true;
/* 251 */               setState(RIGHT, AFTER_SLIDE);
/*     */             } else {
/* 253 */               upSlideTo(k);
/*     */             }
/*     */           }
/* 256 */           else upSlideTo(k);
/*     */ 
/* 258 */           if (this.mVelocityTracker != null) {
/* 259 */             this.mVelocityTracker.recycle();
/* 260 */             this.mVelocityTracker = null;
/*     */           }
/*     */         }
/*     */       }
/* 264 */       if (this.mVelocityTracker != null) {
/* 265 */         this.mVelocityTracker.recycle();
/* 266 */         this.mVelocityTracker = null;
/*     */       }
/*     */       break;
/*     */     }
/* 270 */     return super.onTouchEvent(paramMotionEvent);
/*     */   }
/*     */ 
/*     */   public void upSlideTo(int paramInt)
/*     */   {
/*     */     int i;
/* 279 */     if ((paramInt < 0) && (Math.abs(paramInt) >= this.mSlideTransverseLeftMaxWitch / 2) && (this.mSlideLeftPosition >= this.mSlideTransverseLeftMaxWitch)) {
/* 280 */       i = this.mSlideTransverseLeftMaxWitch - Math.abs(paramInt);
/* 281 */       smoothScrollTo(-i, 0);
/* 282 */       this.isSlideOpen = true;
/* 283 */       setState(LEFT, AFTER_SLIDE);
/* 284 */     } else if ((paramInt > 0) && (Math.abs(paramInt) >= this.mSlideTransverseRightMaxWitch / 2) && (this.mSlideRightPosition >= this.mSlideTransverseRightMaxWitch)) {
/* 285 */       i = this.mSlideTransverseRightMaxWitch - Math.abs(paramInt);
/* 286 */       smoothScrollTo(i, 0);
/* 287 */       this.isSlideOpen = true;
/* 288 */       setState(RIGHT, AFTER_SLIDE);
/*     */     } else {
/* 290 */       if (paramInt > 0) {
/* 291 */         smoothScrollTo(-paramInt, 0);
/* 292 */         setState(RIGHT, BEFORE_SLIDE);
/*     */       } else {
/* 294 */         smoothScrollTo(-paramInt, 0);
/* 295 */         setState(LEFT, BEFORE_SLIDE);
/*     */       }
/* 297 */       this.isSlideOpen = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setState(final String paramString1, final String paramString2) {
/* 302 */     if (this.mChangeListener != null)
/* 303 */       postDelayed(new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 308 */           SlideLayout.this.mChangeListener.onStateChanged(paramString2, paramString1);
/*     */         }
/*     */       }
/*     */       , 150L);
/*     */   }
/*     */ 
/*     */   public void setOffset(String paramString1, String paramString2, float paramFloat)
/*     */   {
/* 316 */     int i = PdrUtil.convertToScreenInt(paramString2, getWidth(), 0, paramFloat);
/* 317 */     int j = getScrollX();
/*     */     int k;
/* 318 */     if (paramString1.equals(LEFT)) {
/* 319 */       if (i == 0) {
/* 320 */         if (j != 0) {
/* 321 */           smoothScrollTo(-j, 0);
/* 322 */           setState(LEFT, BEFORE_SLIDE);
/*     */         }
/*     */       } else {
/* 325 */         if (i > this.mSlideLeftPosition) {
/* 326 */           i = this.mSlideLeftPosition;
/*     */         }
/* 328 */         k = i - Math.abs(j);
/* 329 */         smoothScrollTo(-k, 0);
/* 330 */         postDelayed(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 335 */             SlideLayout.this.upSlideTo(SlideLayout.this.getScrollX());
/*     */           }
/*     */         }
/*     */         , k * 2 + 200);
/*     */       }
/*     */ 
/*     */     }
/* 341 */     else if (i == 0) {
/* 342 */       if (j != 0) {
/* 343 */         smoothScrollTo(-j, 0);
/* 344 */         setState(RIGHT, BEFORE_SLIDE);
/*     */       }
/*     */     } else {
/* 347 */       if (i > this.mSlideRightPosition) {
/* 348 */         i = this.mSlideRightPosition;
/*     */       }
/* 350 */       k = i - Math.abs(j);
/* 351 */       smoothScrollTo(k, 0);
/* 352 */       postDelayed(new Runnable()
/*     */       {
/*     */         public void run()
/*     */         {
/* 357 */           SlideLayout.this.upSlideTo(SlideLayout.this.getScrollX());
/*     */         }
/*     */       }
/*     */       , k * 2 + 200);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void computeScroll()
/*     */   {
/* 368 */     if (this.mScroller.computeScrollOffset())
/*     */     {
/* 370 */       scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
/*     */ 
/* 372 */       postInvalidate();
/*     */     } else {
/* 374 */       clearChildrenCache();
/*     */     }
/* 376 */     super.computeScroll();
/*     */   }
/*     */ 
/*     */   private void smoothScrollTo(int paramInt1, int paramInt2)
/*     */   {
/* 387 */     enableChildrenCache();
/* 388 */     this.mScroller.startScroll(getScrollX(), 0, paramInt1, 0, Math.abs(paramInt1) * 2);
/*     */ 
/* 390 */     invalidate();
/*     */   }
/*     */ 
/*     */   void enableChildrenCache() {
/* 394 */     int i = getChildCount();
/* 395 */     for (int j = 0; j < i; j++) {
/* 396 */       View localView = getChildAt(j);
/* 397 */       localView.setDrawingCacheEnabled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   void clearChildrenCache() {
/* 402 */     int i = getChildCount();
/* 403 */     for (int j = 0; j < i; j++) {
/* 404 */       View localView = getChildAt(j);
/* 405 */       localView.setDrawingCacheEnabled(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setInterceptTouchEventEnabled(boolean paramBoolean)
/*     */   {
/* 415 */     this.mIsHandledTouchEvent = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/* 423 */     int i = getScrollX();
/* 424 */     if (i == 0) {
/* 425 */       return;
/*     */     }
/* 427 */     smoothScrollTo(-i, 0);
/* 428 */     if (i < 0)
/* 429 */       setState(LEFT, BEFORE_SLIDE);
/*     */     else
/* 431 */       setState(RIGHT, BEFORE_SLIDE);
/*     */   }
/*     */ 
/*     */   public static abstract interface OnStateChangeListener
/*     */   {
/*     */     public abstract void onStateChanged(String paramString1, String paramString2);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.widget.SlideLayout
 * JD-Core Version:    0.6.2
 */