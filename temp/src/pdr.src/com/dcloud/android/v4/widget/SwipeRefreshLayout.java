/*      */ package com.dcloud.android.v4.widget;
/*      */ 
/*      */ import android.content.Context;
/*      */ import android.content.res.Resources;
/*      */ import android.content.res.TypedArray;
/*      */ import android.graphics.Canvas;
/*      */ import android.graphics.Color;
/*      */ import android.graphics.drawable.Drawable;
/*      */ import android.os.Build.VERSION;
/*      */ import android.text.TextUtils;
/*      */ import android.util.AttributeSet;
/*      */ import android.util.DisplayMetrics;
/*      */ import android.view.MotionEvent;
/*      */ import android.view.View;
/*      */ import android.view.View.MeasureSpec;
/*      */ import android.view.ViewConfiguration;
/*      */ import android.view.ViewGroup;
/*      */ import android.view.animation.Animation;
/*      */ import android.view.animation.Animation.AnimationListener;
/*      */ import android.view.animation.DecelerateInterpolator;
/*      */ import android.view.animation.Transformation;
/*      */ import android.widget.AbsListView;
/*      */ import com.dcloud.android.annotation.ColorInt;
/*      */ import com.dcloud.android.annotation.ColorRes;
/*      */ import com.dcloud.android.v4.view.MotionEventCompat;
/*      */ import com.dcloud.android.v4.view.NestedScrollingChild;
/*      */ import com.dcloud.android.v4.view.NestedScrollingChildHelper;
/*      */ import com.dcloud.android.v4.view.NestedScrollingParent;
/*      */ import com.dcloud.android.v4.view.NestedScrollingParentHelper;
/*      */ import com.dcloud.android.v4.view.ViewCompat;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ public class SwipeRefreshLayout extends ViewGroup
/*      */   implements NestedScrollingChild, NestedScrollingParent, IRefreshAble
/*      */ {
/*      */   public static final int LARGE = 0;
/*      */   public static final int DEFAULT = 1;
/*   80 */   private static final String LOG_TAG = SwipeRefreshLayout.class.getSimpleName();
/*      */   private static final int MAX_ALPHA = 255;
/*      */   private static final int STARTING_PROGRESS_ALPHA = 76;
/*      */   private static final int CIRCLE_DIAMETER = 40;
/*      */   private static final int CIRCLE_DIAMETER_LARGE = 56;
/*      */   private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0F;
/*      */   private static final int INVALID_POINTER = -1;
/*      */   private static final float DRAG_RATE = 1.8F;
/*      */   private static final float MAX_PROGRESS_ANGLE = 0.8F;
/*      */   private static final int SCALE_DOWN_DURATION = 150;
/*      */   private static final int ALPHA_ANIMATION_DURATION = 300;
/*      */   private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
/*      */   private static final int ANIMATE_TO_START_DURATION = 200;
/*      */   private static final int CIRCLE_BG_LIGHT = -328966;
/*      */   private static final int DEFAULT_CIRCLE_TARGET = 64;
/*      */   private View mTarget;
/*      */   private IRefreshAble.OnRefreshListener mListener;
/*  111 */   private boolean mRefreshing = false;
/*      */   private int mTouchSlop;
/*  114 */   private float mTotalDragDistance = -1.0F;
/*      */   private float mTotalUnconsumed;
/*      */   private final NestedScrollingParentHelper mNestedScrollingParentHelper;
/*      */   private final NestedScrollingChildHelper mNestedScrollingChildHelper;
/*  121 */   private final int[] mParentScrollConsumed = new int[2];
/*      */   private int mMediumAnimationDuration;
/*      */   private int mCurrentTargetOffsetTop;
/*  126 */   private boolean mOriginalOffsetCalculated = false;
/*      */   private float mInitialMotionY;
/*      */   private float mInitialDownY;
/*      */   private float mInitialDownX;
/*  131 */   private boolean mIsBeingDragged = false;
/*      */   private boolean mScale;
/*      */   private boolean mReturningToStart;
/*      */   private final DecelerateInterpolator mDecelerateInterpolator;
/*  140 */   private static final int[] LAYOUT_ATTRS = { 16842766 };
/*      */   private CircleImageView mCircleView;
/*  145 */   private int mCircleViewIndex = -1;
/*      */   protected int mFrom;
/*      */   private float mStartingScale;
/*      */   protected int mOriginalOffsetTop;
/*      */   private MaterialProgressDrawable mProgress;
/*      */   private Animation mScaleAnimation;
/*      */   private Animation mScaleDownAnimation;
/*      */   private Animation mAlphaStartAnimation;
/*      */   private Animation mAlphaMaxAnimation;
/*      */   private Animation mScaleDownToStartAnimation;
/*      */   private float mSpinnerFinalOffset;
/*      */   private boolean mNotify;
/*      */   private int mCircleWidth;
/*      */   private int mCircleHeight;
/*      */   private boolean mUsingCustomStart;
/*  176 */   private boolean mPlusRefreshing = false;
/*  177 */   private Animation.AnimationListener mRefreshListener = new Animation.AnimationListener()
/*      */   {
/*      */     public void onAnimationStart(Animation paramAnonymousAnimation)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void onAnimationRepeat(Animation paramAnonymousAnimation) {
/*  184 */       SwipeRefreshLayout.this.parentInvalidate();
/*      */     }
/*      */ 
/*      */     public void onAnimationEnd(Animation paramAnonymousAnimation)
/*      */     {
/*  189 */       if (SwipeRefreshLayout.this.mRefreshing)
/*      */       {
/*  191 */         SwipeRefreshLayout.this.mProgress.setAlpha(255);
/*  192 */         SwipeRefreshLayout.this.mProgress.start();
/*  193 */         SwipeRefreshLayout.this.mPlusRefreshing = true;
/*  194 */         if ((SwipeRefreshLayout.this.mNotify) && 
/*  195 */           (SwipeRefreshLayout.this.mListener != null))
/*  196 */           SwipeRefreshLayout.this.mListener.onRefresh(3);
/*      */       }
/*      */       else
/*      */       {
/*  200 */         SwipeRefreshLayout.this.mProgress.stop();
/*  201 */         SwipeRefreshLayout.this.mPlusRefreshing = false;
/*  202 */         SwipeRefreshLayout.this.mCircleView.setVisibility(8);
/*  203 */         SwipeRefreshLayout.this.setColorViewAlpha(255);
/*      */ 
/*  205 */         if (SwipeRefreshLayout.this.mScale)
/*  206 */           SwipeRefreshLayout.this.setAnimationProgress(0.0F);
/*      */         else {
/*  208 */           SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(SwipeRefreshLayout.this.mOriginalOffsetTop - SwipeRefreshLayout.this.mCurrentTargetOffsetTop, true);
/*      */         }
/*      */       }
/*      */ 
/*  212 */       SwipeRefreshLayout.this.mCurrentTargetOffsetTop = SwipeRefreshLayout.this.mCircleView.getTop();
/*      */     }
/*  177 */   };
/*      */ 
/*  283 */   boolean mUseSys = false;
/*      */ 
/*  662 */   boolean mBeginRefresh = false;
/*      */ 
/*  685 */   private boolean isSetOffset = false;
/*  686 */   JSONObject mJsonData = null;
/*      */ 
/*  980 */   View mParentView = null;
/*      */   static final int PULL_BOTTOM = -1;
/*  982 */   int mPullDirect = -1;
/*      */ 
/*  988 */   private boolean mRefreshEnable = true;
/*      */   static final int PULL_DEGREE_GAP = 40;
/* 1058 */   boolean mHandledDown = false;
/*      */ 
/* 1181 */   private final Animation mAnimateToCorrectPosition = new Animation()
/*      */   {
/*      */     public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation) {
/* 1184 */       int i = 0;
/* 1185 */       int j = 0;
/* 1186 */       if (!SwipeRefreshLayout.this.mUsingCustomStart)
/* 1187 */         j = (int)(SwipeRefreshLayout.this.mSpinnerFinalOffset - Math.abs(SwipeRefreshLayout.this.mOriginalOffsetTop));
/*      */       else {
/* 1189 */         j = (int)SwipeRefreshLayout.this.mSpinnerFinalOffset;
/*      */       }
/* 1191 */       i = SwipeRefreshLayout.this.mFrom + (int)((j - SwipeRefreshLayout.this.mFrom) * paramAnonymousFloat);
/* 1192 */       int k = i - SwipeRefreshLayout.this.mCircleView.getTop();
/* 1193 */       SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(k, false);
/* 1194 */       SwipeRefreshLayout.this.mProgress.setArrowScale(1.0F - paramAnonymousFloat);
/*      */     }
/* 1181 */   };
/*      */ 
/* 1198 */   private final Animation mPeek = new Animation()
/*      */   {
/*      */     public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation) {
/* 1201 */       int i = 0;
/* 1202 */       int j = 0;
/* 1203 */       if (!SwipeRefreshLayout.this.mUsingCustomStart)
/* 1204 */         j = (int)(SwipeRefreshLayout.this.mSpinnerFinalOffset - Math.abs(SwipeRefreshLayout.this.mOriginalOffsetTop));
/*      */       else {
/* 1206 */         j = (int)SwipeRefreshLayout.this.mSpinnerFinalOffset;
/*      */       }
/* 1208 */       i = SwipeRefreshLayout.this.mFrom + (int)((j - SwipeRefreshLayout.this.mFrom) * paramAnonymousFloat);
/* 1209 */       int k = i - SwipeRefreshLayout.this.mCircleView.getTop();
/* 1210 */       SwipeRefreshLayout.this.setTargetOffsetTopAndBottom(k, false);
/* 1211 */       SwipeRefreshLayout.this.mProgress.setArrowScale(1.0F - paramAnonymousFloat);
/*      */     }
/* 1198 */   };
/*      */ 
/* 1222 */   private final Animation mAnimateToStartPosition = new Animation()
/*      */   {
/*      */     public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation) {
/* 1225 */       SwipeRefreshLayout.this.moveToStart(paramAnonymousFloat);
/*      */     }
/* 1222 */   };
/*      */ 
/*      */   private void setColorViewAlpha(int paramInt)
/*      */   {
/*  217 */     this.mCircleView.getBackground().setAlpha(paramInt);
/*  218 */     this.mProgress.setAlpha(paramInt);
/*      */   }
/*      */ 
/*      */   public void setProgressViewOffset(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  236 */     this.mScale = paramBoolean;
/*  237 */     this.mCircleView.setVisibility(8);
/*  238 */     this.mOriginalOffsetTop = (this.mCurrentTargetOffsetTop = paramInt1);
/*  239 */     this.mSpinnerFinalOffset = paramInt2;
/*  240 */     this.mTotalDragDistance = paramInt3;
/*  241 */     this.mUsingCustomStart = true;
/*  242 */     this.mCircleView.invalidate();
/*      */   }
/*      */ 
/*      */   public void setProgressViewEndTarget(boolean paramBoolean, int paramInt)
/*      */   {
/*  258 */     this.mSpinnerFinalOffset = paramInt;
/*  259 */     this.mScale = paramBoolean;
/*  260 */     this.mCircleView.invalidate();
/*      */   }
/*      */ 
/*      */   public void setSize(int paramInt)
/*      */   {
/*  267 */     if ((paramInt != 0) && (paramInt != 1)) {
/*  268 */       return;
/*      */     }
/*  270 */     DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
/*  271 */     if (paramInt == 0)
/*  272 */       this.mCircleHeight = (this.mCircleWidth = (int)(56.0F * localDisplayMetrics.density));
/*      */     else {
/*  274 */       this.mCircleHeight = (this.mCircleWidth = (int)(40.0F * localDisplayMetrics.density));
/*      */     }
/*      */ 
/*  279 */     this.mCircleView.setImageDrawable(null);
/*  280 */     this.mProgress.updateSizes(paramInt);
/*  281 */     this.mCircleView.setImageDrawable(this.mProgress);
/*      */   }
/*      */ 
/*      */   public SwipeRefreshLayout(Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean) {
/*  285 */     super(paramContext);
/*  286 */     this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
/*  287 */     this.mUseSys = paramBoolean;
/*  288 */     this.mMediumAnimationDuration = getResources().getInteger(17694721);
/*      */ 
/*  291 */     setWillNotDraw(false);
/*  292 */     this.mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
/*      */ 
/*  294 */     if (paramAttributeSet != null) {
/*  295 */       localObject = paramContext.obtainStyledAttributes(paramAttributeSet, LAYOUT_ATTRS);
/*  296 */       setEnabled(((TypedArray)localObject).getBoolean(0, true));
/*  297 */       ((TypedArray)localObject).recycle();
/*      */     }
/*      */ 
/*  300 */     Object localObject = getResources().getDisplayMetrics();
/*  301 */     this.mCircleWidth = ((int)(40.0F * ((DisplayMetrics)localObject).density));
/*  302 */     this.mCircleHeight = ((int)(40.0F * ((DisplayMetrics)localObject).density));
/*      */ 
/*  304 */     createProgressView();
/*  305 */     ViewCompat.setChildrenDrawingOrderEnabled(this, true);
/*      */ 
/*  307 */     this.mSpinnerFinalOffset = (64.0F * ((DisplayMetrics)localObject).density);
/*  308 */     this.mTotalDragDistance = (this.mSpinnerFinalOffset * 2.0F);
/*  309 */     this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
/*      */ 
/*  311 */     this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
/*  312 */     setNestedScrollingEnabled(true);
/*      */   }
/*      */ 
/*      */   public SwipeRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
/*      */   {
/*  321 */     this(paramContext, paramAttributeSet, true);
/*      */   }
/*      */ 
/*      */   protected int getChildDrawingOrder(int paramInt1, int paramInt2) {
/*  325 */     if (this.mCircleViewIndex < 0)
/*  326 */       return paramInt2;
/*  327 */     if (paramInt2 == paramInt1 - 1)
/*      */     {
/*  329 */       return this.mCircleViewIndex;
/*  330 */     }if (paramInt2 >= this.mCircleViewIndex)
/*      */     {
/*  332 */       return paramInt2 + 1;
/*      */     }
/*      */ 
/*  335 */     return paramInt2;
/*      */   }
/*      */ 
/*      */   private void createProgressView()
/*      */   {
/*  340 */     this.mCircleView = new CircleImageView(getContext(), -328966, 20.0F, this.mUseSys);
/*  341 */     this.mProgress = new MaterialProgressDrawable(getContext(), this);
/*  342 */     this.mProgress.setBackgroundColor(-328966);
/*  343 */     this.mCircleView.setImageDrawable(this.mProgress);
/*  344 */     this.mCircleView.setVisibility(8);
/*  345 */     addView(this.mCircleView);
/*      */   }
/*      */ 
/*      */   public void setOnRefreshListener(IRefreshAble.OnRefreshListener paramOnRefreshListener)
/*      */   {
/*  353 */     this.mListener = paramOnRefreshListener;
/*      */   }
/*      */ 
/*      */   private boolean isAlphaUsedForScale()
/*      */   {
/*  360 */     return Build.VERSION.SDK_INT < 11;
/*      */   }
/*      */ 
/*      */   public void setRefreshing(boolean paramBoolean)
/*      */   {
/*  370 */     if ((paramBoolean) && (this.mRefreshing != paramBoolean))
/*      */     {
/*  372 */       this.mRefreshing = paramBoolean;
/*  373 */       int i = 0;
/*  374 */       if (!this.mUsingCustomStart)
/*  375 */         i = (int)(this.mSpinnerFinalOffset + this.mOriginalOffsetTop);
/*      */       else {
/*  377 */         i = (int)this.mSpinnerFinalOffset;
/*      */       }
/*  379 */       setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop, true);
/*      */ 
/*  381 */       this.mNotify = false;
/*  382 */       startScaleUpAnimation(this.mRefreshListener);
/*      */     } else {
/*  384 */       setRefreshing(paramBoolean, true);
/*      */     }
/*      */   }
/*      */ 
/*  388 */   private void startScaleUpAnimation(Animation.AnimationListener paramAnimationListener) { this.mCircleView.setVisibility(0);
/*  389 */     if (Build.VERSION.SDK_INT >= 11)
/*      */     {
/*  393 */       this.mProgress.setAlpha(255);
/*      */     }
/*  395 */     this.mScaleAnimation = new Animation()
/*      */     {
/*      */       public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation) {
/*  398 */         SwipeRefreshLayout.this.setAnimationProgress(paramAnonymousFloat);
/*      */       }
/*      */     };
/*  401 */     this.mScaleAnimation.setDuration(this.mMediumAnimationDuration);
/*  402 */     if (paramAnimationListener != null) {
/*  403 */       this.mCircleView.setAnimationListener(paramAnimationListener);
/*      */     }
/*  405 */     this.mCircleView.clearAnimation();
/*  406 */     this.mCircleView.startAnimation(this.mScaleAnimation);
/*      */   }
/*      */ 
/*      */   private void setAnimationProgress(float paramFloat)
/*      */   {
/*  414 */     if (isAlphaUsedForScale()) {
/*  415 */       setColorViewAlpha((int)(paramFloat * 255.0F));
/*      */     } else {
/*  417 */       ViewCompat.setScaleX(this.mCircleView, paramFloat);
/*  418 */       ViewCompat.setScaleY(this.mCircleView, paramFloat);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void setRefreshing(boolean paramBoolean1, boolean paramBoolean2) {
/*  423 */     if (this.mRefreshing != paramBoolean1) {
/*  424 */       this.mNotify = paramBoolean2;
/*  425 */       ensureTarget();
/*  426 */       this.mRefreshing = paramBoolean1;
/*  427 */       if (this.mRefreshing)
/*  428 */         animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
/*      */       else
/*  430 */         startScaleDownAnimation(this.mRefreshListener);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void startScaleDownAnimation(Animation.AnimationListener paramAnimationListener)
/*      */   {
/*  436 */     this.mScaleDownAnimation = new Animation()
/*      */     {
/*      */       public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation) {
/*  439 */         SwipeRefreshLayout.this.setAnimationProgress(1.0F - paramAnonymousFloat);
/*      */       }
/*      */     };
/*  442 */     this.mScaleDownAnimation.setDuration(150L);
/*  443 */     this.mCircleView.setAnimationListener(paramAnimationListener);
/*  444 */     this.mCircleView.clearAnimation();
/*  445 */     this.mCircleView.startAnimation(this.mScaleDownAnimation);
/*      */   }
/*      */ 
/*      */   private void startProgressAlphaStartAnimation() {
/*  449 */     this.mAlphaStartAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 76);
/*      */   }
/*      */ 
/*      */   private void startProgressAlphaMaxAnimation() {
/*  453 */     this.mAlphaMaxAnimation = startAlphaAnimation(this.mProgress.getAlpha(), 255);
/*      */   }
/*      */ 
/*      */   private Animation startAlphaAnimation(final int paramInt1, final int paramInt2)
/*      */   {
/*  459 */     if ((this.mScale) && (isAlphaUsedForScale())) {
/*  460 */       return null;
/*      */     }
/*  462 */     Animation local4 = new Animation()
/*      */     {
/*      */       public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation) {
/*  465 */         SwipeRefreshLayout.this.mProgress.setAlpha((int)(paramInt1 + (paramInt2 - paramInt1) * paramAnonymousFloat));
/*      */       }
/*      */     };
/*  470 */     local4.setDuration(300L);
/*      */ 
/*  472 */     this.mCircleView.setAnimationListener(null);
/*  473 */     this.mCircleView.clearAnimation();
/*  474 */     this.mCircleView.startAnimation(local4);
/*  475 */     return local4;
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public void setProgressBackgroundColor(int paramInt)
/*      */   {
/*  483 */     setProgressBackgroundColorSchemeResource(paramInt);
/*      */   }
/*      */ 
/*      */   public void setProgressBackgroundColorSchemeResource(@ColorRes int paramInt)
/*      */   {
/*  492 */     setProgressBackgroundColorSchemeColor(getResources().getColor(paramInt));
/*      */   }
/*      */ 
/*      */   public void setProgressBackgroundColorSchemeColor(@ColorInt int paramInt)
/*      */   {
/*  501 */     this.mCircleView.setBackgroundColor(paramInt);
/*  502 */     this.mProgress.setBackgroundColor(paramInt);
/*      */   }
/*      */ 
/*      */   @Deprecated
/*      */   public void setColorScheme(@ColorInt int[] paramArrayOfInt)
/*      */   {
/*  510 */     setColorSchemeResources(paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public void setColorSchemeResources(@ColorRes int[] paramArrayOfInt)
/*      */   {
/*  521 */     Resources localResources = getResources();
/*  522 */     int[] arrayOfInt = new int[paramArrayOfInt.length];
/*  523 */     for (int i = 0; i < paramArrayOfInt.length; i++) {
/*  524 */       arrayOfInt[i] = localResources.getColor(paramArrayOfInt[i]);
/*      */     }
/*  526 */     setColorSchemeColors(arrayOfInt);
/*      */   }
/*      */ 
/*      */   @ColorInt
/*      */   public void setColorSchemeColors(int[] paramArrayOfInt)
/*      */   {
/*  538 */     ensureTarget();
/*  539 */     this.mProgress.setColorSchemeColors(paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public boolean isRefreshing()
/*      */   {
/*  547 */     return this.mRefreshing;
/*      */   }
/*      */ 
/*      */   private void ensureTarget()
/*      */   {
/*  553 */     if (this.mTarget == null)
/*  554 */       for (int i = 0; i < getChildCount(); i++) {
/*  555 */         View localView = getChildAt(i);
/*  556 */         if (!localView.equals(this.mCircleView)) {
/*  557 */           this.mTarget = localView;
/*  558 */           break;
/*      */         }
/*      */       }
/*      */   }
/*      */ 
/*      */   public void setDistanceToTriggerSync(int paramInt)
/*      */   {
/*  570 */     this.mTotalDragDistance = paramInt;
/*      */   }
/*      */ 
/*      */   protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  575 */     int i = getMeasuredWidth();
/*  576 */     int j = getMeasuredHeight();
/*  577 */     if (getChildCount() == 0) {
/*  578 */       return;
/*      */     }
/*  580 */     if (this.mTarget == null) {
/*  581 */       ensureTarget();
/*      */     }
/*      */ 
/*  589 */     int k = this.mCircleView.getMeasuredWidth();
/*  590 */     int m = this.mCircleView.getMeasuredHeight();
/*  591 */     this.mCircleView.layout(i / 2 - k / 2, this.mCurrentTargetOffsetTop, i / 2 + k / 2, this.mCurrentTargetOffsetTop + m);
/*      */   }
/*      */ 
/*      */   public void onMeasure(int paramInt1, int paramInt2)
/*      */   {
/*  597 */     super.onMeasure(paramInt1, paramInt2);
/*  598 */     if (this.mTarget == null) {
/*  599 */       ensureTarget();
/*      */     }
/*      */ 
/*  608 */     this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mCircleHeight, 1073741824));
/*      */ 
/*  610 */     if ((!this.mUsingCustomStart) && (!this.mOriginalOffsetCalculated)) {
/*  611 */       this.mOriginalOffsetCalculated = true;
/*  612 */       this.mCurrentTargetOffsetTop = (this.mOriginalOffsetTop = -this.mCircleView.getMeasuredHeight());
/*      */     }
/*  614 */     this.mCircleViewIndex = -1;
/*      */ 
/*  616 */     for (int i = 0; i < getChildCount(); i++)
/*  617 */       if (getChildAt(i) == this.mCircleView) {
/*  618 */         this.mCircleViewIndex = i;
/*  619 */         break;
/*      */       }
/*      */   }
/*      */ 
/*      */   public int getProgressCircleDiameter()
/*      */   {
/*  633 */     return this.mCircleView != null ? this.mCircleView.getMeasuredHeight() : 0;
/*      */   }
/*      */ 
/*      */   public boolean canChildScrollUp()
/*      */   {
/*  641 */     if (this.mTarget != null) {
/*  642 */       if (Build.VERSION.SDK_INT < 14) {
/*  643 */         if ((this.mTarget instanceof AbsListView)) {
/*  644 */           AbsListView localAbsListView = (AbsListView)this.mTarget;
/*  645 */           return (localAbsListView.getChildCount() > 0) && ((localAbsListView.getFirstVisiblePosition() > 0) || (localAbsListView.getChildAt(0).getTop() < localAbsListView.getPaddingTop()));
/*      */         }
/*      */ 
/*  649 */         return (ViewCompat.canScrollVertically(this.mTarget, -1)) || (this.mTarget.getScrollY() > 0);
/*      */       }
/*      */ 
/*  652 */       return ViewCompat.canScrollVertically(this.mTarget, -1);
/*      */     }
/*      */ 
/*  655 */     return false;
/*      */   }
/*      */ 
/*      */   public void endRefresh() {
/*  659 */     setRefreshing(false);
/*      */   }
/*      */ 
/*      */   public void beginRefresh()
/*      */   {
/*  664 */     if ((!this.mBeginRefresh) && (this.mCircleView.getVisibility() != 0)) {
/*  665 */       Runnable local5 = new Runnable() {
/*  666 */         int offset = 0;
/*      */ 
/*      */         public void run()
/*      */         {
/*  670 */           if (this.offset < SwipeRefreshLayout.this.mSpinnerFinalOffset + SwipeRefreshLayout.this.mOriginalOffsetTop) {
/*  671 */             SwipeRefreshLayout.this.moveSpinner(this.offset);
/*  672 */             SwipeRefreshLayout.this.postDelayed(this, 1L);
/*  673 */             this.offset += 35;
/*      */           } else {
/*  675 */             SwipeRefreshLayout.this.setRefreshing(true, true);
/*  676 */             SwipeRefreshLayout.this.mBeginRefresh = false;
/*      */           }
/*      */         }
/*      */       };
/*  680 */       post(local5);
/*  681 */       this.mBeginRefresh = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void parseData(JSONObject paramJSONObject, int paramInt1, int paramInt2, float paramFloat)
/*      */   {
/*      */     try
/*      */     {
/*  689 */       if ((paramFloat == 0.0F) || (paramFloat == 1.0F)) {
/*  690 */         paramFloat = this.mParentView.getContext().getResources().getDisplayMetrics().density;
/*      */       }
/*  692 */       this.mJsonData = paramJSONObject;
/*  693 */       String str1 = paramJSONObject.optString("offset");
/*  694 */       int i = this.mOriginalOffsetTop;
/*  695 */       if (!TextUtils.isEmpty(str1)) {
/*  696 */         i = PdrUtil.convertToScreenInt(str1, paramInt2, i, paramFloat);
/*      */       }
/*  698 */       String str2 = paramJSONObject.optString("height");
/*  699 */       int j = (int)this.mSpinnerFinalOffset;
/*  700 */       if (!TextUtils.isEmpty(str2)) {
/*  701 */         j = PdrUtil.convertToScreenInt(str2, paramInt2, j, paramFloat);
/*      */       }
/*  703 */       j += i;
/*  704 */       String str3 = paramJSONObject.optString("range");
/*  705 */       int k = (int)this.mTotalDragDistance;
/*  706 */       if (!TextUtils.isEmpty(str3)) {
/*  707 */         k = PdrUtil.convertToScreenInt(str3, paramInt2, k, paramFloat);
/*      */       }
/*  709 */       k += i;
/*  710 */       setColorSchemeColors(new int[] { Color.parseColor("#2BD009") });
/*  711 */       if (this.mOriginalOffsetTop != i) {
/*  712 */         this.isSetOffset = false;
/*      */       }
/*  714 */       if (!this.isSetOffset) {
/*  715 */         this.isSetOffset = true;
/*  716 */         setProgressViewOffset(false, i, j, k);
/*      */       }
/*      */     } catch (Exception localException) {
/*  719 */       localException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onResize(int paramInt1, int paramInt2, float paramFloat)
/*      */   {
/*  725 */     parseData(this.mJsonData, paramInt1, paramInt2, paramFloat);
/*      */   }
/*      */ 
/*      */   public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
/*      */   {
/*  748 */     if (((Build.VERSION.SDK_INT >= 21) || (!(this.mTarget instanceof AbsListView))) && ((this.mTarget == null) || (ViewCompat.isNestedScrollingEnabled(this.mTarget))))
/*      */     {
/*  752 */       super.requestDisallowInterceptTouchEvent(paramBoolean);
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt)
/*      */   {
/*  760 */     if ((isEnabled()) && ((paramInt & 0x2) != 0))
/*      */     {
/*  762 */       startNestedScroll(paramInt & 0x2);
/*  763 */       return true;
/*      */     }
/*  765 */     return false;
/*      */   }
/*      */ 
/*      */   public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt)
/*      */   {
/*  771 */     this.mNestedScrollingParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt);
/*  772 */     this.mTotalUnconsumed = 0.0F;
/*      */   }
/*      */ 
/*      */   public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
/*      */   {
/*  779 */     if ((paramInt2 > 0) && (this.mTotalUnconsumed > 0.0F)) {
/*  780 */       if (paramInt2 > this.mTotalUnconsumed) {
/*  781 */         paramArrayOfInt[1] = (paramInt2 - (int)this.mTotalUnconsumed);
/*  782 */         this.mTotalUnconsumed = 0.0F;
/*      */       } else {
/*  784 */         this.mTotalUnconsumed -= paramInt2;
/*  785 */         paramArrayOfInt[1] = paramInt2;
/*      */       }
/*      */ 
/*  788 */       moveSpinner(this.mTotalUnconsumed);
/*      */     }
/*      */ 
/*  792 */     int[] arrayOfInt = this.mParentScrollConsumed;
/*  793 */     if (dispatchNestedPreScroll(paramInt1 - paramArrayOfInt[0], paramInt2 - paramArrayOfInt[1], arrayOfInt, null)) {
/*  794 */       paramArrayOfInt[0] += arrayOfInt[0];
/*  795 */       paramArrayOfInt[1] += arrayOfInt[1];
/*      */     }
/*      */   }
/*      */ 
/*      */   public int getNestedScrollAxes()
/*      */   {
/*  801 */     return this.mNestedScrollingParentHelper.getNestedScrollAxes();
/*      */   }
/*      */ 
/*      */   public void onStopNestedScroll(View paramView)
/*      */   {
/*  806 */     this.mNestedScrollingParentHelper.onStopNestedScroll(paramView);
/*      */ 
/*  809 */     if (this.mTotalUnconsumed > 0.0F) {
/*  810 */       finishSpinner(this.mTotalUnconsumed);
/*  811 */       this.mTotalUnconsumed = 0.0F;
/*      */     }
/*      */ 
/*  814 */     stopNestedScroll();
/*      */   }
/*      */ 
/*      */   public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  820 */     if (paramInt4 < 0) {
/*  821 */       paramInt4 = Math.abs(paramInt4);
/*  822 */       this.mTotalUnconsumed += paramInt4;
/*  823 */       moveSpinner(this.mTotalUnconsumed);
/*      */     }
/*      */ 
/*  826 */     dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt1, null);
/*      */   }
/*      */ 
/*      */   public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
/*      */   {
/*  833 */     return false;
/*      */   }
/*      */ 
/*      */   public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
/*      */   {
/*  838 */     return false;
/*      */   }
/*      */ 
/*      */   public void setNestedScrollingEnabled(boolean paramBoolean)
/*      */   {
/*  843 */     this.mNestedScrollingChildHelper.setNestedScrollingEnabled(paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isNestedScrollingEnabled()
/*      */   {
/*  848 */     return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
/*      */   }
/*      */ 
/*      */   public boolean startNestedScroll(int paramInt)
/*      */   {
/*  853 */     return this.mNestedScrollingChildHelper.startNestedScroll(paramInt);
/*      */   }
/*      */ 
/*      */   public void stopNestedScroll()
/*      */   {
/*  858 */     this.mNestedScrollingChildHelper.stopNestedScroll();
/*      */   }
/*      */ 
/*      */   public boolean hasNestedScrollingParent()
/*      */   {
/*  863 */     return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
/*      */   }
/*      */ 
/*      */   public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
/*      */   {
/*  869 */     return this.mNestedScrollingChildHelper.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */   {
/*  875 */     return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
/*      */   }
/*      */ 
/*      */   public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
/*      */   {
/*  880 */     return this.mNestedScrollingChildHelper.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
/*      */   {
/*  885 */     return this.mNestedScrollingChildHelper.dispatchNestedPreFling(paramFloat1, paramFloat2);
/*      */   }
/*      */ 
/*      */   private boolean isAnimationRunning(Animation paramAnimation) {
/*  889 */     return (paramAnimation != null) && (paramAnimation.hasStarted()) && (!paramAnimation.hasEnded());
/*      */   }
/*      */ 
/*      */   private void moveSpinner(float paramFloat) {
/*  893 */     this.mProgress.showArrow(true);
/*  894 */     float f1 = paramFloat / this.mTotalDragDistance;
/*      */ 
/*  896 */     float f2 = Math.min(1.0F, Math.abs(f1));
/*  897 */     float f3 = (float)Math.max(f2 - 0.4D, 0.0D) * 5.0F / 3.0F;
/*  898 */     float f4 = Math.abs(paramFloat) - this.mTotalDragDistance;
/*  899 */     float f5 = this.mUsingCustomStart ? this.mSpinnerFinalOffset - this.mOriginalOffsetTop : this.mSpinnerFinalOffset;
/*      */ 
/*  901 */     float f6 = Math.max(0.0F, Math.min(f4, f5 * 2.0F) / f5);
/*      */ 
/*  903 */     float f7 = (float)(f6 / 4.0F - Math.pow(f6 / 4.0F, 2.0D)) * 2.0F;
/*      */ 
/*  905 */     float f8 = f5 * f7 * 2.0F;
/*      */ 
/*  907 */     int i = this.mOriginalOffsetTop + (int)(f5 * f2 + f8);
/*      */ 
/*  909 */     if (this.mCircleView.getVisibility() != 0) {
/*  910 */       this.mCircleView.setVisibility(0);
/*      */     }
/*  912 */     if (!this.mScale) {
/*  913 */       ViewCompat.setScaleX(this.mCircleView, 1.0F);
/*  914 */       ViewCompat.setScaleY(this.mCircleView, 1.0F);
/*      */     }
/*  916 */     if (paramFloat < this.mTotalDragDistance) {
/*  917 */       if (this.mScale) {
/*  918 */         setAnimationProgress(paramFloat / this.mTotalDragDistance);
/*      */       }
/*  920 */       if ((this.mProgress.getAlpha() > 76) && (!isAnimationRunning(this.mAlphaStartAnimation)))
/*      */       {
/*  923 */         startProgressAlphaStartAnimation();
/*      */       }
/*  925 */       f9 = f3 * 0.8F;
/*  926 */       this.mProgress.setStartEndTrim(0.0F, Math.min(0.8F, f9));
/*  927 */       this.mProgress.setArrowScale(Math.min(1.0F, f3));
/*      */     }
/*  929 */     else if ((this.mProgress.getAlpha() < 255) && (!isAnimationRunning(this.mAlphaMaxAnimation)))
/*      */     {
/*  931 */       startProgressAlphaMaxAnimation();
/*      */     }
/*      */ 
/*  934 */     float f9 = (-0.25F + 0.4F * f3 + f7 * 2.0F) * 0.5F;
/*  935 */     this.mProgress.setProgressRotation(f9);
/*  936 */     float f10 = i - this.mCurrentTargetOffsetTop;
/*  937 */     if (f10 + this.mCurrentTargetOffsetTop > this.mTotalDragDistance) {
/*  938 */       f10 = this.mTotalDragDistance - this.mCurrentTargetOffsetTop;
/*      */     }
/*  940 */     setTargetOffsetTopAndBottom((int)f10, true);
/*      */   }
/*      */ 
/*      */   private void cancelRefresh() {
/*  944 */     this.mRefreshing = false;
/*  945 */     this.mProgress.setStartEndTrim(0.0F, 0.0F);
/*  946 */     Animation.AnimationListener local6 = null;
/*  947 */     if (!this.mScale)
/*  948 */       local6 = new Animation.AnimationListener()
/*      */       {
/*      */         public void onAnimationStart(Animation paramAnonymousAnimation)
/*      */         {
/*      */         }
/*      */ 
/*      */         public void onAnimationEnd(Animation paramAnonymousAnimation)
/*      */         {
/*  956 */           if (!SwipeRefreshLayout.this.mScale)
/*  957 */             SwipeRefreshLayout.this.startScaleDownAnimation(null);
/*      */         }
/*      */ 
/*      */         public void onAnimationRepeat(Animation paramAnonymousAnimation)
/*      */         {
/*      */         }
/*      */       };
/*  967 */     animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, local6);
/*  968 */     this.mProgress.showArrow(false);
/*      */   }
/*      */   private void finishSpinner(float paramFloat) {
/*  971 */     if (paramFloat > this.mTotalDragDistance) {
/*  972 */       setRefreshing(true, true);
/*      */     }
/*      */     else
/*  975 */       cancelRefresh();
/*      */   }
/*      */ 
/*      */   public void onInit(ViewGroup paramViewGroup, IRefreshAble.OnRefreshListener paramOnRefreshListener)
/*      */   {
/*  984 */     this.mParentView = paramViewGroup;
/*  985 */     setOnRefreshListener(paramOnRefreshListener);
/*  986 */     paramViewGroup.addView(this, -1, -1);
/*      */   }
/*      */ 
/*      */   public void setRefreshEnable(boolean paramBoolean) {
/*  990 */     this.mRefreshEnable = paramBoolean;
/*      */   }
/*      */   public boolean isRefreshEnable() {
/*  993 */     return this.mRefreshEnable;
/*      */   }
/*      */ 
/*      */   protected void dispatchDraw(Canvas paramCanvas) {
/*  997 */     if (this.mUseSys)
/*  998 */       super.dispatchDraw(paramCanvas);
/*      */     else
/* 1000 */       parentInvalidate();
/*      */   }
/*      */ 
/*      */   public void onSelfDraw(Canvas paramCanvas)
/*      */   {
/* 1016 */     if ((this.mCircleView.getVisibility() == 0) && (this.mCircleView.getTop() > this.mOriginalOffsetTop - this.mCircleView.getMeasuredHeight()) && ((this.mParentView.getScrollY() <= 0) || (this.mPlusRefreshing)))
/*      */     {
/* 1020 */       paramCanvas.save();
/* 1021 */       int i = this.mCircleView.getMeasuredWidth();
/* 1022 */       int j = this.mCircleView.getMeasuredHeight();
/* 1023 */       int k = (this.mParentView.getWidth() - i) / 2 + this.mParentView.getScrollX();
/* 1024 */       int m = this.mParentView.getScrollY() - j + this.mCircleView.getTop();
/* 1025 */       m = Math.max(m, this.mOriginalOffsetTop);
/* 1026 */       paramCanvas.clipRect(k, m, k + i, m + j);
/* 1027 */       paramCanvas.translate(this.mParentView.getScrollX(), this.mParentView.getScrollY() - j);
/* 1028 */       super.dispatchDraw(paramCanvas);
/*      */ 
/* 1030 */       paramCanvas.restore();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void parentInvalidate() {
/* 1035 */     if (this.mParentView != null) {
/* 1036 */       int i = (this.mParentView.getWidth() - this.mCircleWidth) / 2 + this.mParentView.getScrollX();
/* 1037 */       int j = this.mOriginalOffsetTop + this.mCircleHeight + this.mParentView.getScrollY();
/* 1038 */       this.mParentView.invalidate(i, j, i + this.mCircleWidth, this.mCircleView.getTop() + j + this.mCircleHeight);
/*      */     }
/*      */   }
/*      */ 
/* 1042 */   private boolean handleTouchEvent(MotionEvent paramMotionEvent) { if (isRefreshing()) return false;
/*      */ 
/* 1051 */     if ((paramMotionEvent.getAction() != 0) && (!this.mHandledDown))
/*      */     {
/* 1053 */       return false;
/*      */     }
/* 1055 */     return true;
/*      */   }
/*      */ 
/*      */   public boolean onSelfTouchEvent(MotionEvent paramMotionEvent)
/*      */   {
/* 1062 */     if (!handleTouchEvent(paramMotionEvent)) return false;
/* 1063 */     int i = MotionEventCompat.getActionMasked(paramMotionEvent);
/*      */ 
/* 1065 */     int j = 0;
/* 1066 */     if ((this.mReturningToStart) && (i == 0)) {
/* 1067 */       this.mReturningToStart = false;
/*      */     }
/*      */ 
/* 1070 */     if ((!isEnabled()) || (this.mReturningToStart) || (canChildScrollUp()))
/*      */     {
/* 1072 */       j = 0;
/*      */     }
/*      */     else
/*      */     {
/*      */       float f1;
/*      */       float f2;
/* 1075 */       switch (i) {
/*      */       case 0:
/* 1077 */         if (!this.mIsBeingDragged) {
/* 1078 */           this.mHandledDown = true;
/* 1079 */           setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mCircleView.getTop(), true);
/* 1080 */           f1 = paramMotionEvent.getY();
/* 1081 */           if (f1 == -1.0F) {
/* 1082 */             j = 0;
/*      */           } else {
/* 1084 */             this.mInitialDownY = f1;
/* 1085 */             this.mInitialDownX = paramMotionEvent.getX();
/*      */           }
/*      */         } else {
/* 1088 */           moveSpinner(this.mOriginalOffsetTop);
/* 1089 */           this.mIsBeingDragged = false;
/*      */         }
/* 1091 */         break;
/*      */       case 2:
/* 1094 */         f1 = paramMotionEvent.getY();
/* 1095 */         f2 = f1 - this.mInitialDownY;
/* 1096 */         float f3 = paramMotionEvent.getX();
/* 1097 */         if ((f2 > this.mTouchSlop) && (!this.mIsBeingDragged) && (Math.atan(Math.abs(f3 - this.mInitialDownX) / Math.abs(f1 - this.mInitialDownY)) * 180.0D / 3.141592653589793D < 40.0D)) {
/* 1098 */           this.mInitialMotionY = (this.mInitialDownY + this.mTouchSlop);
/* 1099 */           this.mIsBeingDragged = true;
/* 1100 */           this.mProgress.setAlpha(76);
/*      */         }
/*      */ 
/* 1103 */         if (this.mIsBeingDragged) {
/* 1104 */           j = 1;
/* 1105 */           float f4 = (f1 - this.mInitialMotionY) * 1.8F;
/* 1106 */           if (f4 > 0.0F)
/*      */           {
/* 1108 */             moveSpinner(f4);
/*      */           }
/*      */         }
/*      */ 
/* 1112 */         break;
/*      */       case 1:
/*      */       case 3:
/* 1117 */         f1 = paramMotionEvent.getY();
/* 1118 */         if (this.mIsBeingDragged) {
/* 1119 */           f2 = (f1 - this.mInitialMotionY) * 1.8F;
/* 1120 */           j = 1;
/* 1121 */           finishSpinner(f2);
/*      */         }
/* 1123 */         this.mInitialDownY = 0.0F;
/* 1124 */         this.mIsBeingDragged = false;
/* 1125 */         this.mHandledDown = false;
/*      */       }
/*      */     }
/*      */ 
/* 1129 */     if (j != 0) parentInvalidate();
/* 1130 */     return (j != 0) || (this.mUseSys);
/*      */   }
/*      */ 
/*      */   public boolean onTouchEvent(MotionEvent paramMotionEvent) {
/* 1134 */     if (this.mUseSys) {
/* 1135 */       return onSelfTouchEvent(paramMotionEvent);
/*      */     }
/* 1137 */     return false;
/*      */   }
/*      */ 
/*      */   private void animateOffsetToCorrectPosition(int paramInt, Animation.AnimationListener paramAnimationListener) {
/* 1141 */     this.mFrom = paramInt;
/* 1142 */     this.mAnimateToCorrectPosition.reset();
/* 1143 */     this.mAnimateToCorrectPosition.setDuration(200L);
/* 1144 */     this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
/* 1145 */     if (paramAnimationListener != null) {
/* 1146 */       this.mCircleView.setAnimationListener(paramAnimationListener);
/*      */     }
/* 1148 */     this.mCircleView.clearAnimation();
/* 1149 */     this.mCircleView.startAnimation(this.mAnimateToCorrectPosition);
/*      */   }
/*      */ 
/*      */   private void peek(int paramInt, Animation.AnimationListener paramAnimationListener) {
/* 1153 */     this.mFrom = paramInt;
/* 1154 */     this.mPeek.reset();
/* 1155 */     this.mPeek.setDuration(500L);
/* 1156 */     this.mPeek.setInterpolator(this.mDecelerateInterpolator);
/* 1157 */     if (paramAnimationListener != null) {
/* 1158 */       this.mCircleView.setAnimationListener(paramAnimationListener);
/*      */     }
/* 1160 */     this.mCircleView.clearAnimation();
/* 1161 */     this.mCircleView.startAnimation(this.mPeek);
/*      */   }
/*      */ 
/*      */   private void animateOffsetToStartPosition(int paramInt, Animation.AnimationListener paramAnimationListener) {
/* 1165 */     if (this.mScale)
/*      */     {
/* 1167 */       startScaleDownReturnToStartAnimation(paramInt, paramAnimationListener);
/*      */     } else {
/* 1169 */       this.mFrom = paramInt;
/* 1170 */       this.mAnimateToStartPosition.reset();
/* 1171 */       this.mAnimateToStartPosition.setDuration(200L);
/* 1172 */       this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
/* 1173 */       if (paramAnimationListener != null) {
/* 1174 */         this.mCircleView.setAnimationListener(paramAnimationListener);
/*      */       }
/* 1176 */       this.mCircleView.clearAnimation();
/* 1177 */       this.mCircleView.startAnimation(this.mAnimateToStartPosition);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void moveToStart(float paramFloat)
/*      */   {
/* 1216 */     int i = 0;
/* 1217 */     i = this.mFrom + (int)((this.mOriginalOffsetTop - this.mFrom) * paramFloat);
/* 1218 */     int j = i - this.mCircleView.getTop();
/* 1219 */     setTargetOffsetTopAndBottom(j, false);
/*      */   }
/*      */ 
/*      */   private void startScaleDownReturnToStartAnimation(int paramInt, Animation.AnimationListener paramAnimationListener)
/*      */   {
/* 1231 */     this.mFrom = paramInt;
/* 1232 */     if (isAlphaUsedForScale())
/* 1233 */       this.mStartingScale = this.mProgress.getAlpha();
/*      */     else {
/* 1235 */       this.mStartingScale = ViewCompat.getScaleX(this.mCircleView);
/*      */     }
/* 1237 */     this.mScaleDownToStartAnimation = new Animation()
/*      */     {
/*      */       public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation) {
/* 1240 */         float f = SwipeRefreshLayout.this.mStartingScale + -SwipeRefreshLayout.this.mStartingScale * paramAnonymousFloat;
/* 1241 */         SwipeRefreshLayout.this.setAnimationProgress(f);
/* 1242 */         SwipeRefreshLayout.this.moveToStart(paramAnonymousFloat);
/*      */       }
/*      */     };
/* 1245 */     this.mScaleDownToStartAnimation.setDuration(150L);
/* 1246 */     if (paramAnimationListener != null) {
/* 1247 */       this.mCircleView.setAnimationListener(paramAnimationListener);
/*      */     }
/* 1249 */     this.mCircleView.clearAnimation();
/* 1250 */     this.mCircleView.startAnimation(this.mScaleDownToStartAnimation);
/*      */   }
/*      */ 
/*      */   private void setTargetOffsetTopAndBottom(int paramInt, boolean paramBoolean) {
/* 1254 */     this.mCircleView.bringToFront();
/* 1255 */     this.mCircleView.offsetTopAndBottom(paramInt);
/* 1256 */     this.mCurrentTargetOffsetTop = this.mCircleView.getTop();
/* 1257 */     if ((paramBoolean) && (Build.VERSION.SDK_INT < 11))
/* 1258 */       invalidate();
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.widget.SwipeRefreshLayout
 * JD-Core Version:    0.6.2
 */