/*     */ package io.dcloud.common.adapter.ui;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.webkit.WebView;
/*     */ import com.dcloud.android.v4.widget.IRefreshAble;
/*     */ import com.dcloud.android.v4.widget.SwipeRefreshLayout;
/*     */ import com.dcloud.android.widget.AbsoluteLayout;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.OnStateChangeListener;
/*     */ import io.dcloud.common.adapter.ui.fresh.PullToRefreshWebView;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import java.util.ArrayList;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class AdaWebViewParent extends AdaContainerFrameItem
/*     */ {
/*  18 */   PullToRefreshWebViewExt mPullReFreshViewImpl = null;
/*  19 */   AdaWebview mAdaWebview = null;
/*     */ 
/*  21 */   AdaWebViewParent(Context paramContext) { super(paramContext);
/*  22 */     this.mPullReFreshViewImpl = new PullToRefreshWebViewExt(paramContext);
/*  23 */     this.mPullReFreshViewImpl.setPullLoadEnabled(false);
/*  24 */     this.mPullReFreshViewImpl.setInterceptTouchEventEnabled(false);
/*     */ 
/*  26 */     setMainView(this.mPullReFreshViewImpl); }
/*     */ 
/*     */   void fillsWithWebview(AdaWebview paramAdaWebview)
/*     */   {
/*  30 */     this.mAdaWebview = paramAdaWebview;
/*  31 */     this.mPullReFreshViewImpl.setRefreshableView((WebView)this.mAdaWebview.obtainMainView());
/*  32 */     this.mPullReFreshViewImpl.addRefreshableView((WebView)paramAdaWebview.obtainMainView());
/*  33 */     this.mPullReFreshViewImpl.setAppId(this.mAdaWebview.obtainApp().obtainAppId());
/*  34 */     getChilds().add(paramAdaWebview);
/*     */   }
/*     */ 
/*     */   void parsePullToReFresh(JSONObject paramJSONObject)
/*     */   {
/*  39 */     AdaFrameView localAdaFrameView = (AdaFrameView)this.mAdaWebview.obtainFrameView();
/*  40 */     if (localAdaFrameView.mBounceView != null) {
/*  41 */       return;
/*     */     }
/*  43 */     boolean bool = Boolean.parseBoolean(JSONUtil.getString(paramJSONObject, "support"));
/*  44 */     String str = "default";
/*  45 */     if (paramJSONObject != null) {
/*  46 */       str = paramJSONObject.optString("style", str);
/*     */     }
/*  48 */     if (bool)
/*     */       try {
/*  50 */         if ("circle".equals(str)) {
/*  51 */           if (localAdaFrameView.mCircleRefreshView == null) {
/*  52 */             localAdaFrameView.mCircleRefreshView = new SwipeRefreshLayout(this.mAdaWebview.getContext(), null, false);
/*  53 */             localAdaFrameView.mCircleRefreshView.onInit(this.mAdaWebview.obtainWebview(), this.mAdaWebview.mWebViewImpl);
/*  54 */             localAdaFrameView.mCircleRefreshView.parseData(paramJSONObject, localAdaFrameView.mViewOptions.width, localAdaFrameView.mViewOptions.height, this.mAdaWebview.getScale());
/*     */           }
/*  56 */           localAdaFrameView.mCircleRefreshView.setRefreshEnable(true);
/*  57 */           if (localAdaFrameView.mRefreshView != null)
/*  58 */             this.mPullReFreshViewImpl.setInterceptTouchEventEnabled(false);
/*     */         }
/*     */         else
/*     */         {
/*  62 */           if (localAdaFrameView.mRefreshView == null) {
/*  63 */             localAdaFrameView.mRefreshView = new RefreshView(localAdaFrameView, this.mAdaWebview);
/*     */           }
/*  65 */           if (localAdaFrameView.mCircleRefreshView != null) {
/*  66 */             localAdaFrameView.mCircleRefreshView.setRefreshEnable(false);
/*     */           }
/*  68 */           localAdaFrameView.mRefreshView.parseJsonOption(paramJSONObject);
/*     */ 
/*  70 */           int i = localAdaFrameView.mRefreshView.maxPullHeight;
/*  71 */           int j = localAdaFrameView.mRefreshView.changeStateHeight;
/*  72 */           initPullView(localAdaFrameView.mRefreshView, i, j);
/*     */         }
/*     */       } catch (Exception localException) {
/*  75 */         localException.printStackTrace();
/*     */       }
/*     */     else
/*  78 */       this.mPullReFreshViewImpl.setInterceptTouchEventEnabled(false);
/*     */   }
/*     */ 
/*     */   void endPullRefresh()
/*     */   {
/*  83 */     if ((((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView != null) && (((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView.isRefreshEnable()))
/*  84 */       ((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView.endRefresh();
/*     */     else
/*  86 */       this.mPullReFreshViewImpl.onPullDownRefreshComplete();
/*     */   }
/*     */ 
/*     */   void beginPullRefresh() {
/*  90 */     if ((((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView != null) && (((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView.isRefreshEnable()))
/*  91 */       ((AdaFrameView)this.mAdaWebview.obtainFrameView()).mCircleRefreshView.beginRefresh();
/*     */     else
/*  93 */       this.mPullReFreshViewImpl.beginPullRefresh();
/*     */   }
/*     */ 
/*     */   public void reInit() {
/*  97 */     this.mPullReFreshViewImpl.refreshLoadingViewsSize();
/*     */   }
/*     */ 
/*     */   private void initPullView(PullToRefreshBase.OnStateChangeListener paramOnStateChangeListener, int paramInt1, int paramInt2) {
/* 101 */     Logger.d("View_Visible_Path", "AdaWebViewParent.initPullView changeStateHeight=" + paramInt2);
/* 102 */     this.mPullReFreshViewImpl.setInterceptTouchEventEnabled(true);
/* 103 */     this.mPullReFreshViewImpl.setOnStateChangeListener(paramOnStateChangeListener);
/* 104 */     this.mPullReFreshViewImpl.init(getContext());
/* 105 */     this.mPullReFreshViewImpl.setHeaderHeight(paramInt2 > paramInt1 ? paramInt1 : paramInt2);
/* 106 */     this.mPullReFreshViewImpl.setHeaderPullDownMaxHeight(paramInt1 > paramInt2 ? paramInt1 : paramInt2);
/*     */ 
/* 108 */     this.mAdaWebview.setWebviewProperty("bounce", "none");
/*     */   }
/*     */ 
/*     */   void parseBounce(JSONObject paramJSONObject) {
/* 112 */     AdaFrameView localAdaFrameView = (AdaFrameView)this.mAdaWebview.obtainFrameView();
/* 113 */     if (localAdaFrameView.mRefreshView != null) {
/* 114 */       return;
/*     */     }
/*     */ 
/* 117 */     if (localAdaFrameView.mBounceView == null) {
/* 118 */       localAdaFrameView.mBounceView = new BounceView(localAdaFrameView, this.mAdaWebview);
/*     */     }
/* 120 */     int i = this.mAdaWebview.mViewOptions.height / 3;
/* 121 */     int j = i / 2;
/* 122 */     if (paramJSONObject == null) {
/* 123 */       localAdaFrameView.mBounceView.mSupports[0] = true;
/* 124 */       i = localAdaFrameView.mBounceView.maxPullHeights[0] = this.mAdaWebview.mViewOptions.height / 3;
/* 125 */       j = localAdaFrameView.mBounceView.changeStateHeights[0] = localAdaFrameView.mBounceView.maxPullHeights[0] / 2;
/*     */     } else {
/* 127 */       localAdaFrameView.mBounceView.parseJsonOption(paramJSONObject);
/* 128 */       i = localAdaFrameView.mBounceView.maxPullHeights[0];
/* 129 */       j = localAdaFrameView.mBounceView.changeStateHeights[0];
/*     */     }
/*     */ 
/* 132 */     if (localAdaFrameView.mBounceView.mSupports[0] != 0)
/* 133 */       initPullView(localAdaFrameView.mBounceView, i, j);
/*     */     else {
/* 135 */       this.mPullReFreshViewImpl.setInterceptTouchEventEnabled(false);
/*     */     }
/* 137 */     localAdaFrameView.mBounceView.checkOffset(localAdaFrameView, this.mPullReFreshViewImpl, paramJSONObject, j, i);
/* 138 */     if (((localAdaFrameView.obtainMainView() instanceof AbsoluteLayout)) && (!paramJSONObject.isNull("slideoffset")))
/* 139 */       ((AbsoluteLayout)localAdaFrameView.obtainMainView()).initSlideInfo(paramJSONObject, this.mAdaWebview.getScale(), this.mAdaWebview.getWebView().getWidth());
/*     */   }
/*     */ 
/*     */   void resetBounce()
/*     */   {
/* 146 */     endPullRefresh();
/* 147 */     AdaFrameView localAdaFrameView = (AdaFrameView)this.mAdaWebview.obtainFrameView();
/* 148 */     if ((localAdaFrameView.obtainMainView() instanceof AbsoluteLayout))
/* 149 */       ((AbsoluteLayout)localAdaFrameView.obtainMainView()).reset();
/*     */   }
/*     */ 
/*     */   public void dispose()
/*     */   {
/* 155 */     super.dispose();
/* 156 */     if (this.mAdaWebview != null) {
/* 157 */       this.mAdaWebview.dispose();
/* 158 */       this.mAdaWebview = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void onResize() {
/* 163 */     super.onResize();
/* 164 */     if (this.mAdaWebview != null) {
/* 165 */       AdaFrameView localAdaFrameView = (AdaFrameView)this.mAdaWebview.obtainFrameView();
/*     */ 
/* 167 */       endPullRefresh();
/* 168 */       int i = 0;
/* 169 */       int j = 0;
/* 170 */       if (localAdaFrameView.mRefreshView != null) {
/* 171 */         j = localAdaFrameView.mRefreshView.maxPullHeight;
/* 172 */         i = localAdaFrameView.mRefreshView.changeStateHeight;
/* 173 */       } else if (localAdaFrameView.mBounceView != null) {
/* 174 */         j = localAdaFrameView.mBounceView.maxPullHeights[0];
/* 175 */         i = localAdaFrameView.mBounceView.changeStateHeights[0];
/*     */       }
/* 177 */       else if ((localAdaFrameView.mCircleRefreshView != null) && (localAdaFrameView.mCircleRefreshView.isRefreshEnable())) {
/* 178 */         localAdaFrameView.mCircleRefreshView.onResize(localAdaFrameView.mViewOptions.width, localAdaFrameView.mViewOptions.height, this.mAdaWebview.getScale());
/*     */       }
/* 180 */       if ((i != 0) && (j != 0)) {
/* 181 */         this.mPullReFreshViewImpl.setHeaderHeight(i > j ? j : i);
/* 182 */         this.mPullReFreshViewImpl.setHeaderPullDownMaxHeight(j > i ? j : i);
/* 183 */         this.mPullReFreshViewImpl.refreshLoadingViewsSize();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 214 */     return this.mAdaWebview.toString();
/*     */   }
/*     */ 
/*     */   class PullToRefreshWebViewExt extends PullToRefreshWebView
/*     */   {
/*     */     public PullToRefreshWebViewExt(Context arg2)
/*     */     {
/* 190 */       super();
/*     */     }
/*     */ 
/*     */     public void setLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
/*     */     {
/* 198 */       super.setLayoutParams(paramLayoutParams);
/*     */     }
/*     */     public boolean onTouchEvent(MotionEvent paramMotionEvent) {
/* 201 */       int i = AdaWebViewParent.this.mAdaWebview.obtainMainView().getVisibility() == AdaFrameView.GONE ? 1 : 0;
/* 202 */       return (i != 0) || (super.onTouchEvent(paramMotionEvent));
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.AdaWebViewParent
 * JD-Core Version:    0.6.2
 */