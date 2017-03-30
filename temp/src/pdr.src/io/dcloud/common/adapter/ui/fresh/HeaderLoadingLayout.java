/*    */ package io.dcloud.common.adapter.ui.fresh;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.res.Resources;
/*    */ import android.util.AttributeSet;
/*    */ import android.util.DisplayMetrics;
/*    */ import android.view.View;
/*    */ import android.widget.TextView;
/*    */ 
/*    */ public class HeaderLoadingLayout extends LoadingLayout
/*    */ {
/*    */   private static final int ROTATE_ANIM_DURATION = 150;
/*    */ 
/*    */   public HeaderLoadingLayout(Context paramContext)
/*    */   {
/* 25 */     super(paramContext);
/* 26 */     init(paramContext);
/*    */   }
/*    */ 
/*    */   public HeaderLoadingLayout(Context paramContext, AttributeSet paramAttributeSet)
/*    */   {
/* 36 */     super(paramContext, paramAttributeSet);
/* 37 */     init(paramContext);
/*    */   }
/*    */ 
/*    */   private void init(Context paramContext)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setLastUpdatedLabel(CharSequence paramCharSequence)
/*    */   {
/*    */   }
/*    */ 
/*    */   public int getContentSize()
/*    */   {
/* 55 */     return (int)(getResources().getDisplayMetrics().density * 100.0F);
/*    */   }
/*    */ 
/*    */   protected View createLoadingView(Context paramContext, AttributeSet paramAttributeSet)
/*    */   {
/* 60 */     TextView localTextView = new TextView(paramContext);
/* 61 */     return localTextView;
/*    */   }
/*    */ 
/*    */   protected void onStateChanged(ILoadingLayout.State paramState1, ILoadingLayout.State paramState2)
/*    */   {
/* 66 */     super.onStateChanged(paramState1, paramState2);
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
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.fresh.HeaderLoadingLayout
 * JD-Core Version:    0.6.2
 */