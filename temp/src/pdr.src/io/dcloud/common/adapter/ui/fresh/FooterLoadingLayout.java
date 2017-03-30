/*    */ package io.dcloud.common.adapter.ui.fresh;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.res.Resources;
/*    */ import android.util.AttributeSet;
/*    */ import android.util.DisplayMetrics;
/*    */ import android.view.View;
/*    */ import android.widget.TextView;
/*    */ 
/*    */ public class FooterLoadingLayout extends LoadingLayout
/*    */ {
/*    */   public FooterLoadingLayout(Context paramContext)
/*    */   {
/* 19 */     super(paramContext);
/* 20 */     init(paramContext);
/*    */   }
/*    */ 
/*    */   public FooterLoadingLayout(Context paramContext, AttributeSet paramAttributeSet)
/*    */   {
/* 30 */     super(paramContext, paramAttributeSet);
/* 31 */     init(paramContext);
/*    */   }
/*    */ 
/*    */   private void init(Context paramContext)
/*    */   {
/* 40 */     setState(ILoadingLayout.State.RESET);
/*    */   }
/*    */ 
/*    */   protected View createLoadingView(Context paramContext, AttributeSet paramAttributeSet)
/*    */   {
/* 45 */     TextView localTextView = new TextView(paramContext);
/* 46 */     localTextView.setText("createLoadingView");
/* 47 */     return localTextView;
/*    */   }
/*    */ 
/*    */   public void setLastUpdatedLabel(CharSequence paramCharSequence)
/*    */   {
/*    */   }
/*    */ 
/*    */   public int getContentSize()
/*    */   {
/* 57 */     return (int)(getResources().getDisplayMetrics().density * 100.0F);
/*    */   }
/*    */ 
/*    */   protected void onStateChanged(ILoadingLayout.State paramState1, ILoadingLayout.State paramState2)
/*    */   {
/* 62 */     super.onStateChanged(paramState1, paramState2);
/*    */   }
/*    */ 
/*    */   protected void onReset()
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void onPullToRefresh()
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void onReleaseToRefresh()
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void onRefreshing()
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void onNoMoreData()
/*    */   {
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.fresh.FooterLoadingLayout
 * JD-Core Version:    0.6.2
 */