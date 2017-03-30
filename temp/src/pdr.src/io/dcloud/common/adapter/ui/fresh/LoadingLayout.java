/*     */ package io.dcloud.common.adapter.ui.fresh;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.util.AttributeSet;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.widget.FrameLayout;
/*     */ 
/*     */ public abstract class LoadingLayout extends FrameLayout
/*     */   implements ILoadingLayout
/*     */ {
/*     */   private View mContainer;
/*  21 */   private ILoadingLayout.State mCurState = ILoadingLayout.State.NONE;
/*     */ 
/*  23 */   private ILoadingLayout.State mPreState = ILoadingLayout.State.NONE;
/*     */ 
/*     */   public LoadingLayout(Context paramContext)
/*     */   {
/*  31 */     this(paramContext, null);
/*     */   }
/*     */ 
/*     */   public LoadingLayout(Context paramContext, AttributeSet paramAttributeSet)
/*     */   {
/*  41 */     this(paramContext, paramAttributeSet, 0);
/*     */   }
/*     */ 
/*     */   public LoadingLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
/*     */   {
/*  52 */     super(paramContext, paramAttributeSet, paramInt);
/*     */ 
/*  54 */     init(paramContext, paramAttributeSet);
/*     */   }
/*     */ 
/*     */   protected void init(Context paramContext, AttributeSet paramAttributeSet)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void show(boolean paramBoolean)
/*     */   {
/*  82 */     if (paramBoolean == (0 == getVisibility())) {
/*  83 */       return;
/*     */     }
/*     */ 
/*  86 */     ViewGroup.LayoutParams localLayoutParams = this.mContainer.getLayoutParams();
/*  87 */     if (null != localLayoutParams) {
/*  88 */       if (paramBoolean)
/*  89 */         localLayoutParams.height = -2;
/*     */       else {
/*  91 */         localLayoutParams.height = 0;
/*     */       }
/*  93 */       setVisibility(paramBoolean ? 0 : 4);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setLastUpdatedLabel(CharSequence paramCharSequence)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setLoadingDrawable(Drawable paramDrawable)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setPullLabel(CharSequence paramCharSequence)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setRefreshingLabel(CharSequence paramCharSequence)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setReleaseLabel(CharSequence paramCharSequence)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void setState(ILoadingLayout.State paramState)
/*     */   {
/* 144 */     if (this.mCurState != paramState) {
/* 145 */       this.mPreState = this.mCurState;
/* 146 */       this.mCurState = paramState;
/* 147 */       onStateChanged(paramState, this.mPreState);
/*     */     }
/*     */   }
/*     */ 
/*     */   public ILoadingLayout.State getState()
/*     */   {
/* 153 */     return this.mCurState;
/*     */   }
/*     */ 
/*     */   public void onPull(float paramFloat)
/*     */   {
/*     */   }
/*     */ 
/*     */   protected ILoadingLayout.State getPreState()
/*     */   {
/* 167 */     return this.mPreState;
/*     */   }
/*     */ 
/*     */   protected void onStateChanged(ILoadingLayout.State paramState1, ILoadingLayout.State paramState2)
/*     */   {
/* 177 */     switch (1.$SwitchMap$io$dcloud$common$adapter$ui$fresh$ILoadingLayout$State[paramState1.ordinal()]) {
/*     */     case 1:
/* 179 */       onReset();
/* 180 */       break;
/*     */     case 2:
/* 183 */       onReleaseToRefresh();
/* 184 */       break;
/*     */     case 3:
/* 187 */       onPullToRefresh();
/* 188 */       break;
/*     */     case 4:
/* 191 */       onRefreshing();
/* 192 */       break;
/*     */     case 5:
/* 195 */       onNoMoreData();
/* 196 */       break;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void onReset()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void onPullToRefresh()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void onReleaseToRefresh()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void onRefreshing()
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void onNoMoreData()
/*     */   {
/*     */   }
/*     */ 
/*     */   public abstract int getContentSize();
/*     */ 
/*     */   protected abstract View createLoadingView(Context paramContext, AttributeSet paramAttributeSet);
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.fresh.LoadingLayout
 * JD-Core Version:    0.6.2
 */