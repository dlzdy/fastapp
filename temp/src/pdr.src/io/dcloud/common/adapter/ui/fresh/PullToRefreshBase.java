/*      */ package io.dcloud.common.adapter.ui.fresh;
/*      */ 
/*      */ import android.annotation.SuppressLint;
/*      */ import android.content.Context;
/*      */ import android.view.MotionEvent;
/*      */ import android.view.View;
/*      */ import android.view.ViewConfiguration;
/*      */ import android.view.ViewTreeObserver;
/*      */ import android.view.ViewTreeObserver.OnGlobalLayoutListener;
/*      */ import android.view.animation.DecelerateInterpolator;
/*      */ import android.view.animation.Interpolator;
/*      */ import android.widget.LinearLayout;
/*      */ import android.widget.LinearLayout.LayoutParams;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.util.BaseInfo;
/*      */ 
/*      */ @SuppressLint({"NewApi"})
/*      */ public abstract class PullToRefreshBase<T extends View> extends LinearLayout
/*      */   implements IPullToRefresh<T>
/*      */ {
/*      */   private static final int SCROLL_DURATION = 150;
/*      */   private static final float OFFSET_RADIO = 2.5F;
/*   57 */   private float mLastMotionY = -1.0F;
/*   58 */   private float mLastMotionX = -1.0F;
/*   59 */   private boolean mCanDoPullDownEvent = false;
/*      */   OnPullUpListener mOnPullUpListener;
/*      */   private OnRefreshListener<T> mRefreshListener;
/*      */   OnStateChangeListener mOnStateChangeListener;
/*      */   private LoadingLayout mHeaderLayout;
/*      */   private LoadingLayout mFooterLayout;
/*      */   private int mHeaderPullDownMaxHeight;
/*      */   private int mHeaderHeight;
/*      */   private int mFooterHeight;
/*   77 */   private boolean mPullRefreshEnabled = true;
/*      */ 
/*   79 */   private boolean mPullLoadEnabled = false;
/*      */ 
/*   81 */   private boolean mScrollLoadEnabled = false;
/*      */ 
/*   83 */   private boolean mInterceptEventEnable = true;
/*      */ 
/*   85 */   private boolean mIsHandledTouchEvent = false;
/*      */   private int mTouchSlop;
/*   89 */   private ILoadingLayout.State mPullDownState = ILoadingLayout.State.NONE;
/*      */ 
/*   91 */   private ILoadingLayout.State mPullUpState = ILoadingLayout.State.NONE;
/*      */   T mRefreshableView;
/*      */   private PullToRefreshBase<T>.SmoothScrollRunnable mSmoothScrollRunnable;
/*      */   private String mAppId;
/*  224 */   float mLastMotionY_pullup = -1.0F;
/*      */ 
/*  249 */   final int UP = 0;
/*  250 */   final int DOWN = 1;
/*  251 */   final int LEFT = 2;
/*  252 */   final int RIGHT = 3;
/*      */ 
/*  456 */   boolean mBeginPullRefresh = false;
/*      */ 
/*      */   public void setAppId(String paramString)
/*      */   {
/*  101 */     this.mAppId = paramString;
/*      */   }
/*      */ 
/*      */   public String getAppId() {
/*  105 */     return this.mAppId;
/*      */   }
/*      */ 
/*      */   public PullToRefreshBase(Context paramContext)
/*      */   {
/*  113 */     super(paramContext);
/*      */   }
/*      */ 
/*      */   public void init(Context paramContext)
/*      */   {
/*  122 */     setOrientation(1);
/*      */ 
/*  124 */     this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
/*      */ 
/*  126 */     this.mHeaderLayout = createHeaderLoadingLayout(paramContext);
/*  127 */     this.mFooterLayout = createFooterLoadingLayout(paramContext);
/*      */ 
/*  132 */     addHeaderAndFooter(paramContext);
/*      */ 
/*  135 */     getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
/*      */     {
/*      */       public void onGlobalLayout() {
/*  138 */         PullToRefreshBase.this.refreshLoadingViewsSize();
/*  139 */         PullToRefreshBase.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
/*      */       }
/*      */     });
/*  142 */     smoothScrollTo(0);
/*      */   }
/*      */ 
/*      */   public void setHeaderHeight(int paramInt) {
/*  146 */     this.mHeaderHeight = paramInt;
/*      */   }
/*      */ 
/*      */   public void setHeaderPullDownMaxHeight(int paramInt) {
/*  150 */     this.mHeaderPullDownMaxHeight = paramInt;
/*      */   }
/*      */ 
/*      */   public void refreshLoadingViewsSize()
/*      */   {
/*  159 */     int i = this.mHeaderHeight;
/*  160 */     int j = null != this.mFooterLayout ? this.mFooterLayout.getContentSize() : 0;
/*      */ 
/*  162 */     if (i < 0) {
/*  163 */       i = 0;
/*      */     }
/*      */ 
/*  166 */     if (j < 0) {
/*  167 */       j = 0;
/*      */     }
/*      */ 
/*  170 */     this.mHeaderHeight = i;
/*  171 */     this.mFooterHeight = j;
/*      */ 
/*  175 */     i = null != this.mHeaderLayout ? this.mHeaderLayout.getMeasuredHeight() : 0;
/*  176 */     Logger.d("View_Visible_Path", "PullToRefreshBase.refreshLoadingViewsSize mHeaderHeight=" + this.mHeaderHeight + ";headerHeight=" + i);
/*  177 */     j = null != this.mFooterLayout ? this.mFooterLayout.getMeasuredHeight() : 0;
/*  178 */     if (0 == j) {
/*  179 */       j = this.mFooterHeight;
/*      */     }
/*      */ 
/*  182 */     int k = getPaddingLeft();
/*  183 */     int m = getPaddingTop();
/*  184 */     int n = getPaddingRight();
/*  185 */     int i1 = getPaddingBottom();
/*      */ 
/*  187 */     m = -i;
/*  188 */     i1 = -j;
/*      */ 
/*  190 */     setPadding(k, m, n, i1);
/*      */   }
/*      */ 
/*      */   protected final void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  195 */     super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */ 
/*  198 */     refreshLoadingViewsSize();
/*      */ 
/*  201 */     refreshRefreshableViewSize(paramInt1, paramInt2);
/*      */ 
/*  207 */     post(new Runnable()
/*      */     {
/*      */       public void run() {
/*  210 */         PullToRefreshBase.this.requestLayout();
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   public void setOrientation(int paramInt)
/*      */   {
/*  217 */     if (1 != paramInt) {
/*  218 */       throw new IllegalArgumentException("This class only supports VERTICAL orientation.");
/*      */     }
/*      */ 
/*  222 */     super.setOrientation(paramInt);
/*      */   }
/*      */ 
/*      */   private boolean handlePullUpEvent(MotionEvent paramMotionEvent) {
/*  226 */     int i = paramMotionEvent.getAction();
/*  227 */     if (i == 1) {
/*  228 */       float f = paramMotionEvent.getY() - this.mLastMotionY_pullup;
/*  229 */       this.mLastMotionY_pullup = f;
/*  230 */       if ((f < -3.0F) && (isReadyForPullUp())) {
/*  231 */         this.mOnPullUpListener.onPlusScrollBottom();
/*  232 */         return false;
/*      */       }
/*  234 */     } else if (i == 0) {
/*  235 */       this.mLastMotionY_pullup = paramMotionEvent.getY();
/*      */     }
/*  237 */     return false;
/*      */   }
/*      */ 
/*      */   private double getAngle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
/*      */   {
/*  247 */     return Math.atan2(paramFloat4 - paramFloat2, paramFloat3 - paramFloat1) * 180.0D / 3.141592653589793D;
/*      */   }
/*      */ 
/*      */   private int getDirectionByAngle(double paramDouble)
/*      */   {
/*  259 */     if ((paramDouble < -45.0D) && (paramDouble > -135.0D))
/*  260 */       return 0;
/*  261 */     if ((paramDouble >= 45.0D) && (paramDouble < 135.0D))
/*  262 */       return 1;
/*  263 */     if ((paramDouble >= 135.0D) || (paramDouble <= -135.0D))
/*  264 */       return 2;
/*  265 */     if ((paramDouble >= -45.0D) && (paramDouble <= 45.0D)) {
/*  266 */       return 3;
/*      */     }
/*  268 */     return -1;
/*      */   }
/*      */ 
/*      */   private boolean canDoPullDownEvent(float paramFloat1, float paramFloat2) {
/*  272 */     if (paramFloat2 < this.mLastMotionY) return true;
/*  273 */     if (!this.mCanDoPullDownEvent) {
/*  274 */       this.mCanDoPullDownEvent = (1 == getDirectionByAngle(getAngle(this.mLastMotionX, this.mLastMotionY, paramFloat1, paramFloat2)));
/*      */     }
/*  276 */     return this.mCanDoPullDownEvent;
/*      */   }
/*      */ 
/*      */   public final boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
/*  280 */     if ((BaseInfo.isShowTitleBar(getContext())) && (
/*  281 */       (isPullLoadEnabled()) || (isPullRefreshEnabled()))) {
/*  282 */       this.mRefreshableView.onTouchEvent(paramMotionEvent);
/*      */     }
/*      */ 
/*  288 */     if (!isInterceptTouchEventEnabled()) {
/*  289 */       return false;
/*      */     }
/*      */ 
/*  292 */     if ((!isPullLoadEnabled()) && (!isPullRefreshEnabled())) {
/*  293 */       return false;
/*      */     }
/*  295 */     int i = paramMotionEvent.getAction();
/*  296 */     if ((i == 3) || (i == 1)) {
/*  297 */       this.mIsHandledTouchEvent = false;
/*  298 */       this.mCanDoPullDownEvent = false;
/*  299 */       return this.mIsHandledTouchEvent;
/*      */     }
/*      */ 
/*  302 */     if ((i != 0) && (this.mIsHandledTouchEvent)) {
/*  303 */       return true;
/*      */     }
/*      */ 
/*  306 */     switch (i) {
/*      */     case 0:
/*  308 */       this.mLastMotionY = paramMotionEvent.getY();
/*  309 */       this.mLastMotionX = paramMotionEvent.getX();
/*  310 */       this.mIsHandledTouchEvent = false;
/*  311 */       this.mCanDoPullDownEvent = false;
/*  312 */       break;
/*      */     case 2:
/*  315 */       if (canDoPullDownEvent(paramMotionEvent.getX(), paramMotionEvent.getY())) {
/*  316 */         float f1 = paramMotionEvent.getY() - this.mLastMotionY;
/*  317 */         float f2 = Math.abs(f1);
/*      */ 
/*  322 */         if ((f2 > this.mTouchSlop) || (isPullRefreshing()) || (!isPullLoading())) {
/*  323 */           this.mLastMotionY = paramMotionEvent.getY();
/*      */ 
/*  325 */           if ((isPullRefreshEnabled()) && (isReadyForPullDown()))
/*      */           {
/*  330 */             this.mIsHandledTouchEvent = ((Math.abs(getScrollYValue()) > 0) || (f1 > 0.5F));
/*      */ 
/*  333 */             if (this.mIsHandledTouchEvent) {
/*  334 */               this.mRefreshableView.onTouchEvent(paramMotionEvent);
/*  335 */               requestDisallowInterceptTouchEvent(true);
/*      */             }
/*  337 */           } else if ((isPullLoadEnabled()) && (isReadyForPullUp()))
/*      */           {
/*  339 */             this.mIsHandledTouchEvent = ((Math.abs(getScrollYValue()) > 0) || (f1 < -0.5F));
/*  340 */             if (this.mIsHandledTouchEvent)
/*  341 */               requestDisallowInterceptTouchEvent(true);
/*      */           }
/*      */         }
/*      */       }
/*  345 */       break;
/*      */     }
/*      */ 
/*  352 */     return this.mIsHandledTouchEvent ? this.mIsHandledTouchEvent : super.onTouchEvent(paramMotionEvent);
/*      */   }
/*      */ 
/*      */   public boolean onTouchEvent(MotionEvent paramMotionEvent)
/*      */   {
/*  357 */     if ((BaseInfo.isShowTitleBar(getContext())) && (
/*  358 */       (isPullLoadEnabled()) || (isPullRefreshEnabled()))) {
/*  359 */       this.mRefreshableView.onTouchEvent(paramMotionEvent);
/*      */     }
/*      */ 
/*  362 */     boolean bool = false;
/*  363 */     switch (paramMotionEvent.getAction()) {
/*      */     case 0:
/*  365 */       this.mLastMotionY = paramMotionEvent.getY();
/*  366 */       this.mIsHandledTouchEvent = false;
/*  367 */       break;
/*      */     case 2:
/*  370 */       float f = paramMotionEvent.getY() - this.mLastMotionY;
/*  371 */       this.mLastMotionY = paramMotionEvent.getY();
/*  372 */       if ((isPullRefreshEnabled()) && (isReadyForPullDown())) {
/*  373 */         pullHeaderLayout(f / 2.5F);
/*  374 */         bool = true;
/*  375 */       } else if ((isPullLoadEnabled()) && (isReadyForPullUp())) {
/*  376 */         pullFooterLayout(f / 2.5F);
/*  377 */         bool = true;
/*      */       } else {
/*  379 */         this.mIsHandledTouchEvent = false;
/*      */       }
/*  381 */       break;
/*      */     case 1:
/*      */     case 3:
/*  385 */       if (this.mIsHandledTouchEvent) {
/*  386 */         this.mIsHandledTouchEvent = false;
/*      */ 
/*  388 */         if (isReadyForPullDown())
/*      */         {
/*  390 */           if ((this.mPullRefreshEnabled) && (this.mPullDownState == ILoadingLayout.State.RELEASE_TO_REFRESH)) {
/*  391 */             startRefreshing();
/*  392 */             bool = true;
/*      */           }
/*  394 */           resetHeaderLayout();
/*  395 */         } else if (isReadyForPullUp())
/*      */         {
/*  397 */           if ((isPullLoadEnabled()) && (this.mPullUpState == ILoadingLayout.State.RELEASE_TO_REFRESH)) {
/*  398 */             startLoading();
/*  399 */             bool = true;
/*      */           }
/*  401 */           resetFooterLayout();
/*      */         }
/*      */       }
/*  404 */       requestDisallowInterceptTouchEvent(false);
/*  405 */       break;
/*      */     }
/*      */ 
/*  411 */     return bool;
/*      */   }
/*      */ 
/*      */   public void setPullRefreshEnabled(boolean paramBoolean)
/*      */   {
/*  416 */     this.mPullRefreshEnabled = paramBoolean;
/*      */   }
/*      */ 
/*      */   public void setPullLoadEnabled(boolean paramBoolean)
/*      */   {
/*  421 */     this.mPullLoadEnabled = paramBoolean;
/*      */   }
/*      */ 
/*      */   public void setScrollLoadEnabled(boolean paramBoolean)
/*      */   {
/*  426 */     this.mScrollLoadEnabled = paramBoolean;
/*      */   }
/*      */ 
/*      */   public boolean isPullRefreshEnabled()
/*      */   {
/*  431 */     return (this.mPullRefreshEnabled) && (null != this.mHeaderLayout);
/*      */   }
/*      */ 
/*      */   public boolean isPullLoadEnabled()
/*      */   {
/*  436 */     return (this.mPullLoadEnabled) && (null != this.mFooterLayout);
/*      */   }
/*      */ 
/*      */   public boolean isScrollLoadEnabled()
/*      */   {
/*  441 */     return this.mScrollLoadEnabled;
/*      */   }
/*      */ 
/*      */   public void setOnOnPullUpListener(OnPullUpListener paramOnPullUpListener) {
/*  445 */     this.mOnPullUpListener = paramOnPullUpListener;
/*      */   }
/*      */ 
/*      */   public void setOnRefreshListener(OnRefreshListener<T> paramOnRefreshListener) {
/*  449 */     this.mRefreshListener = paramOnRefreshListener;
/*      */   }
/*      */ 
/*      */   public void setOnStateChangeListener(OnStateChangeListener paramOnStateChangeListener) {
/*  453 */     this.mOnStateChangeListener = paramOnStateChangeListener;
/*      */   }
/*      */ 
/*      */   public void beginPullRefresh()
/*      */   {
/*  458 */     if (!this.mBeginPullRefresh) {
/*  459 */       Runnable local3 = new Runnable() {
/*  460 */         int deltaY = 0;
/*      */ 
/*      */         public void run() {
/*  463 */           int i = Math.abs(PullToRefreshBase.this.getScrollYValue());
/*  464 */           if ((PullToRefreshBase.this.isPullRefreshEnabled()) && (PullToRefreshBase.this.isReadyForPullDown()))
/*  465 */             if (i < PullToRefreshBase.this.mHeaderHeight) {
/*  466 */               PullToRefreshBase.this.pullHeaderLayout(this.deltaY / 2.5F);
/*  467 */               this.deltaY += 3;
/*  468 */               PullToRefreshBase.this.postDelayed(this, 5L);
/*      */             } else {
/*  470 */               PullToRefreshBase.this.startRefreshing();
/*  471 */               PullToRefreshBase.this.mBeginPullRefresh = false;
/*      */             }
/*      */         }
/*      */       };
/*  476 */       postDelayed(local3, 5L);
/*  477 */       this.mBeginPullRefresh = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onPullDownRefreshComplete() {
/*  482 */     if (isPullRefreshing()) {
/*  483 */       this.mPullDownState = ILoadingLayout.State.RESET;
/*  484 */       onStateChanged(ILoadingLayout.State.RESET, true);
/*      */ 
/*  491 */       postDelayed(new Runnable()
/*      */       {
/*      */         public void run() {
/*  494 */           PullToRefreshBase.this.setInterceptTouchEventEnabled(true);
/*  495 */           PullToRefreshBase.this.mHeaderLayout.setState(ILoadingLayout.State.RESET);
/*      */         }
/*      */       }
/*      */       , getSmoothScrollDuration());
/*      */ 
/*  499 */       resetHeaderLayout();
/*  500 */       setInterceptTouchEventEnabled(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void onPullUpRefreshComplete()
/*      */   {
/*  506 */     if (isPullLoading()) {
/*  507 */       this.mPullUpState = ILoadingLayout.State.RESET;
/*  508 */       onStateChanged(ILoadingLayout.State.RESET, false);
/*      */ 
/*  510 */       postDelayed(new Runnable()
/*      */       {
/*      */         public void run() {
/*  513 */           PullToRefreshBase.this.setInterceptTouchEventEnabled(true);
/*  514 */           PullToRefreshBase.this.mFooterLayout.setState(ILoadingLayout.State.RESET);
/*      */         }
/*      */       }
/*      */       , getSmoothScrollDuration());
/*      */ 
/*  518 */       resetFooterLayout();
/*  519 */       setInterceptTouchEventEnabled(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   public T getRefreshableView()
/*      */   {
/*  525 */     return this.mRefreshableView;
/*      */   }
/*      */ 
/*      */   public LoadingLayout getHeaderLoadingLayout()
/*      */   {
/*  530 */     return this.mHeaderLayout;
/*      */   }
/*      */ 
/*      */   public LoadingLayout getFooterLoadingLayout()
/*      */   {
/*  535 */     return this.mFooterLayout;
/*      */   }
/*      */ 
/*      */   public void setLastUpdatedLabel(CharSequence paramCharSequence)
/*      */   {
/*  540 */     if (null != this.mHeaderLayout) {
/*  541 */       this.mHeaderLayout.setLastUpdatedLabel(paramCharSequence);
/*      */     }
/*      */ 
/*  544 */     if (null != this.mFooterLayout)
/*  545 */       this.mFooterLayout.setLastUpdatedLabel(paramCharSequence);
/*      */   }
/*      */ 
/*      */   public void doPullRefreshing(final boolean paramBoolean, long paramLong)
/*      */   {
/*  556 */     postDelayed(new Runnable()
/*      */     {
/*      */       public void run() {
/*  559 */         int i = -PullToRefreshBase.this.mHeaderHeight;
/*  560 */         int j = paramBoolean ? 150 : 0;
/*      */ 
/*  562 */         PullToRefreshBase.this.startRefreshing();
/*  563 */         PullToRefreshBase.this.smoothScrollTo(i, j, 0L);
/*      */       }
/*      */     }
/*      */     , paramLong);
/*      */   }
/*      */ 
/*      */   public void setRefreshableView(T paramT)
/*      */   {
/*  570 */     this.mRefreshableView = paramT;
/*      */   }
/*      */ 
/*      */   protected abstract boolean isReadyForPullDown();
/*      */ 
/*      */   protected abstract boolean isReadyForPullUp();
/*      */ 
/*      */   protected LoadingLayout createHeaderLoadingLayout(Context paramContext)
/*      */   {
/*  595 */     if (this.mHeaderLayout == null) {
/*  596 */       return new HeaderLoadingLayout(paramContext);
/*      */     }
/*  598 */     return this.mHeaderLayout;
/*      */   }
/*      */ 
/*      */   protected LoadingLayout createFooterLoadingLayout(Context paramContext)
/*      */   {
/*  611 */     return null;
/*      */   }
/*      */ 
/*      */   protected long getSmoothScrollDuration()
/*      */   {
/*  620 */     return 150L;
/*      */   }
/*      */ 
/*      */   protected void refreshRefreshableViewSize(int paramInt1, int paramInt2)
/*      */   {
/*  630 */     if (null != this.mRefreshableView) {
/*  631 */       LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mRefreshableView.getLayoutParams();
/*  632 */       if (localLayoutParams.height != paramInt2) {
/*  633 */         localLayoutParams.height = paramInt2;
/*  634 */         this.mRefreshableView.requestLayout();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*  639 */   public void addRefreshableView(T paramT) { int i = -1;
/*  640 */     int j = -1;
/*  641 */     addView(paramT, new LinearLayout.LayoutParams(i, j));
/*      */   }
/*      */ 
/*      */   protected void addHeaderAndFooter(Context paramContext)
/*      */   {
/*  671 */     LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
/*      */ 
/*  675 */     LoadingLayout localLoadingLayout1 = this.mHeaderLayout;
/*  676 */     LoadingLayout localLoadingLayout2 = this.mFooterLayout;
/*      */ 
/*  678 */     if (null != localLoadingLayout1) {
/*  679 */       if (this == localLoadingLayout1.getParent()) {
/*  680 */         removeView(localLoadingLayout1);
/*      */       }
/*      */ 
/*  683 */       addView(localLoadingLayout1, 0, localLayoutParams);
/*      */     }
/*      */ 
/*  686 */     if (null != localLoadingLayout2) {
/*  687 */       if (this == localLoadingLayout2.getParent()) {
/*  688 */         removeView(localLoadingLayout2);
/*      */       }
/*      */ 
/*  691 */       addView(localLoadingLayout2, -1, localLayoutParams);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void pullHeaderLayout(float paramFloat)
/*      */   {
/*  702 */     int i = getScrollYValue();
/*  703 */     if ((paramFloat > 0.0F) && (Math.abs(i) >= this.mHeaderPullDownMaxHeight)) return;
/*  704 */     if ((paramFloat < 0.0F) && (i - paramFloat >= 0.0F)) {
/*  705 */       setScrollTo(0, 0);
/*  706 */       return;
/*      */     }
/*      */ 
/*  710 */     setScrollBy(0, -(int)paramFloat);
/*      */ 
/*  712 */     if ((null != this.mHeaderLayout) && (0 != this.mHeaderHeight)) {
/*  713 */       float f = Math.abs(getScrollYValue()) / this.mHeaderHeight;
/*  714 */       this.mHeaderLayout.onPull(f);
/*      */     }
/*      */ 
/*  718 */     int j = Math.abs(getScrollYValue());
/*  719 */     if ((isPullRefreshEnabled()) && (!isPullRefreshing())) {
/*  720 */       if (j > this.mHeaderHeight)
/*  721 */         this.mPullDownState = ILoadingLayout.State.RELEASE_TO_REFRESH;
/*      */       else {
/*  723 */         this.mPullDownState = ILoadingLayout.State.PULL_TO_REFRESH;
/*      */       }
/*      */ 
/*  726 */       this.mHeaderLayout.setState(this.mPullDownState);
/*  727 */       onStateChanged(this.mPullDownState, true);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void pullFooterLayout(float paramFloat)
/*      */   {
/*  737 */     int i = getScrollYValue();
/*  738 */     if ((paramFloat > 0.0F) && (i - paramFloat <= 0.0F)) {
/*  739 */       setScrollTo(0, 0);
/*  740 */       return;
/*      */     }
/*      */ 
/*  743 */     setScrollBy(0, -(int)paramFloat);
/*      */ 
/*  745 */     if ((null != this.mFooterLayout) && (0 != this.mFooterHeight)) {
/*  746 */       float f = Math.abs(getScrollYValue()) / this.mFooterHeight;
/*  747 */       this.mFooterLayout.onPull(f);
/*      */     }
/*      */ 
/*  750 */     int j = Math.abs(getScrollYValue());
/*  751 */     if ((isPullLoadEnabled()) && (!isPullLoading())) {
/*  752 */       if (j > this.mFooterHeight)
/*  753 */         this.mPullUpState = ILoadingLayout.State.RELEASE_TO_REFRESH;
/*      */       else {
/*  755 */         this.mPullUpState = ILoadingLayout.State.PULL_TO_REFRESH;
/*      */       }
/*      */ 
/*  758 */       this.mFooterLayout.setState(this.mPullUpState);
/*  759 */       onStateChanged(this.mPullUpState, false);
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void resetHeaderLayout()
/*      */   {
/*  767 */     int i = Math.abs(getScrollYValue());
/*  768 */     boolean bool = isPullRefreshing();
/*      */ 
/*  770 */     if ((bool) && (i <= this.mHeaderHeight))
/*      */     {
/*  772 */       return;
/*      */     }
/*      */ 
/*  775 */     if (bool)
/*  776 */       smoothScrollTo(-this.mHeaderHeight);
/*      */     else
/*  778 */       smoothScrollTo(0);
/*      */   }
/*      */ 
/*      */   protected void resetFooterLayout()
/*      */   {
/*  786 */     int i = Math.abs(getScrollYValue());
/*  787 */     boolean bool = isPullLoading();
/*      */ 
/*  789 */     if ((bool) && (i <= this.mFooterHeight)) {
/*  790 */       smoothScrollTo(0);
/*  791 */       return;
/*      */     }
/*      */ 
/*  794 */     if (bool)
/*  795 */       smoothScrollTo(this.mFooterHeight);
/*      */     else
/*  797 */       smoothScrollTo(0);
/*      */   }
/*      */ 
/*      */   protected boolean isPullRefreshing()
/*      */   {
/*  807 */     return this.mPullDownState == ILoadingLayout.State.REFRESHING;
/*      */   }
/*      */ 
/*      */   protected boolean isPullLoading()
/*      */   {
/*  816 */     return this.mPullUpState == ILoadingLayout.State.REFRESHING;
/*      */   }
/*      */ 
/*      */   protected void startRefreshing()
/*      */   {
/*  824 */     if (isPullRefreshing()) {
/*  825 */       return;
/*      */     }
/*      */ 
/*  828 */     this.mPullDownState = ILoadingLayout.State.REFRESHING;
/*  829 */     onStateChanged(ILoadingLayout.State.REFRESHING, true);
/*      */ 
/*  831 */     if (null != this.mHeaderLayout) {
/*  832 */       this.mHeaderLayout.setState(ILoadingLayout.State.REFRESHING);
/*      */     }
/*      */ 
/*  835 */     if (null != this.mRefreshListener)
/*      */     {
/*  837 */       postDelayed(new Runnable()
/*      */       {
/*      */         public void run() {
/*  840 */           PullToRefreshBase.this.mRefreshListener.onPullDownToRefresh(PullToRefreshBase.this);
/*      */         }
/*      */       }
/*      */       , getSmoothScrollDuration());
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void startLoading()
/*      */   {
/*  851 */     if (isPullLoading()) {
/*  852 */       return;
/*      */     }
/*      */ 
/*  855 */     this.mPullUpState = ILoadingLayout.State.REFRESHING;
/*  856 */     onStateChanged(ILoadingLayout.State.REFRESHING, false);
/*      */ 
/*  858 */     if (null != this.mFooterLayout) {
/*  859 */       this.mFooterLayout.setState(ILoadingLayout.State.REFRESHING);
/*      */     }
/*      */ 
/*  862 */     if (null != this.mRefreshListener)
/*      */     {
/*  864 */       postDelayed(new Runnable()
/*      */       {
/*      */         public void run() {
/*  867 */           PullToRefreshBase.this.mRefreshListener.onPullUpToRefresh(PullToRefreshBase.this);
/*      */         }
/*      */       }
/*      */       , getSmoothScrollDuration());
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void onStateChanged(ILoadingLayout.State paramState, boolean paramBoolean)
/*      */   {
/*  880 */     if (this.mOnStateChangeListener != null)
/*  881 */       this.mOnStateChangeListener.onStateChanged(paramState, paramBoolean);
/*      */   }
/*      */ 
/*      */   private void setScrollTo(int paramInt1, int paramInt2)
/*      */   {
/*  892 */     scrollTo(paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */   private void setScrollBy(int paramInt1, int paramInt2)
/*      */   {
/*  902 */     scrollBy(paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */   private int getScrollYValue()
/*      */   {
/*  911 */     return getScrollY();
/*      */   }
/*      */ 
/*      */   public void smoothScrollTo(int paramInt)
/*      */   {
/*  920 */     smoothScrollTo(paramInt, getSmoothScrollDuration(), 0L);
/*      */   }
/*      */ 
/*      */   private void smoothScrollTo(int paramInt, long paramLong1, long paramLong2)
/*      */   {
/*  931 */     if (null != this.mSmoothScrollRunnable) {
/*  932 */       this.mSmoothScrollRunnable.stop();
/*      */     }
/*      */ 
/*  935 */     int i = getScrollYValue();
/*  936 */     int j = i != paramInt ? 1 : 0;
/*  937 */     if (j != 0) {
/*  938 */       this.mSmoothScrollRunnable = new SmoothScrollRunnable(i, paramInt, paramLong1);
/*      */     }
/*      */ 
/*  941 */     if (j != 0)
/*  942 */       if (paramLong2 > 0L)
/*  943 */         postDelayed(this.mSmoothScrollRunnable, paramLong2);
/*      */       else
/*  945 */         post(this.mSmoothScrollRunnable);
/*      */   }
/*      */ 
/*      */   public void setInterceptTouchEventEnabled(boolean paramBoolean)
/*      */   {
/*  956 */     this.mInterceptEventEnable = paramBoolean;
/*      */   }
/*      */ 
/*      */   public boolean isInterceptTouchEventEnabled()
/*      */   {
/*  965 */     return this.mInterceptEventEnable;
/*      */   }
/*      */ 
/*      */   final class SmoothScrollRunnable
/*      */     implements Runnable
/*      */   {
/*      */     private final Interpolator mInterpolator;
/*      */     private final int mScrollToY;
/*      */     private final int mScrollFromY;
/*      */     private final long mDuration;
/*  981 */     private boolean mContinueRunning = true;
/*      */ 
/*  983 */     private long mStartTime = -1L;
/*      */ 
/*  985 */     private int mCurrentY = -1;
/*      */ 
/*      */     public SmoothScrollRunnable(int paramInt1, int paramLong, long arg4)
/*      */     {
/*  995 */       this.mScrollFromY = paramInt1;
/*  996 */       this.mScrollToY = paramLong;
/*      */       Object localObject;
/*  997 */       this.mDuration = localObject;
/*  998 */       this.mInterpolator = new DecelerateInterpolator();
/*      */     }
/*      */ 
/*      */     public void run()
/*      */     {
/* 1006 */       if (this.mDuration <= 0L) {
/* 1007 */         PullToRefreshBase.this.setScrollTo(0, this.mScrollToY);
/* 1008 */         return;
/*      */       }
/*      */ 
/* 1015 */       if (this.mStartTime == -1L) {
/* 1016 */         this.mStartTime = System.currentTimeMillis();
/*      */       }
/*      */       else
/*      */       {
/* 1024 */         long l1 = 1000L;
/* 1025 */         long l2 = 1000L * (System.currentTimeMillis() - this.mStartTime) / this.mDuration;
/* 1026 */         l2 = Math.max(Math.min(l2, 1000L), 0L);
/*      */ 
/* 1028 */         int i = Math.round((this.mScrollFromY - this.mScrollToY) * this.mInterpolator.getInterpolation((float)l2 / 1000.0F));
/*      */ 
/* 1030 */         this.mCurrentY = (this.mScrollFromY - i);
/*      */ 
/* 1032 */         PullToRefreshBase.this.setScrollTo(0, this.mCurrentY);
/*      */       }
/*      */ 
/* 1036 */       if ((this.mContinueRunning) && (this.mScrollToY != this.mCurrentY))
/* 1037 */         PullToRefreshBase.this.postDelayed(this, 16L);
/*      */     }
/*      */ 
/*      */     public void stop()
/*      */     {
/* 1045 */       this.mContinueRunning = false;
/* 1046 */       PullToRefreshBase.this.removeCallbacks(this);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static abstract interface OnPullUpListener
/*      */   {
/*      */     public abstract void onPlusScrollBottom();
/*      */   }
/*      */ 
/*      */   public static abstract interface OnStateChangeListener
/*      */   {
/*      */     public abstract void onStateChanged(ILoadingLayout.State paramState, boolean paramBoolean);
/*      */   }
/*      */ 
/*      */   public static abstract interface OnRefreshListener<V extends View>
/*      */   {
/*      */     public abstract void onPullDownToRefresh(PullToRefreshBase<V> paramPullToRefreshBase);
/*      */ 
/*      */     public abstract void onPullUpToRefresh(PullToRefreshBase<V> paramPullToRefreshBase);
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.fresh.PullToRefreshBase
 * JD-Core Version:    0.6.2
 */