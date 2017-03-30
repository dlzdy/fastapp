/*    */ package io.dcloud.common.adapter.ui.fresh;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.Context;
/*    */ import android.util.FloatMath;
/*    */ import android.webkit.WebView;
/*    */ import io.dcloud.common.adapter.util.PlatformUtil;
/*    */ import io.dcloud.common.util.BaseInfo;
/*    */ 
/*    */ public class PullToRefreshWebView extends PullToRefreshBase<WebView>
/*    */ {
/*    */   public PullToRefreshWebView(Context paramContext)
/*    */   {
/* 24 */     super(paramContext);
/*    */   }
/*    */ 
/*    */   protected boolean isReadyForPullDown()
/*    */   {
/* 32 */     int i = 1;
/* 33 */     if (BaseInfo.isShowTitleBar(getContext())) {
/* 34 */       i = 0;
/* 35 */       Object localObject = PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "isTitlebarVisible", null, new Class[] { Activity.class, String.class }, new Object[] { (Activity)getContext(), getAppId() });
/*    */ 
/* 37 */       if ((null != localObject) && (((Boolean)localObject).booleanValue())) {
/* 38 */         i = 1;
/*    */       }
/*    */     }
/* 41 */     return (i != 0) && (((WebView)this.mRefreshableView).getScrollY() == 0);
/*    */   }
/*    */ 
/*    */   protected boolean isReadyForPullUp()
/*    */   {
/* 49 */     float f = FloatMath.floor(((WebView)this.mRefreshableView).getContentHeight() * ((WebView)this.mRefreshableView).getScale());
/* 50 */     return ((WebView)this.mRefreshableView).getScrollY() >= f - ((WebView)this.mRefreshableView).getHeight();
/*    */   }
/*    */ 
/*    */   protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*    */   {
/* 55 */     super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.fresh.PullToRefreshWebView
 * JD-Core Version:    0.6.2
 */