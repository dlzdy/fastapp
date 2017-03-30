/*     */ package io.dcloud.common.adapter.ui;
/*     */ 
/*     */ import android.text.TextUtils;
/*     */ import android.webkit.WebView;
/*     */ import com.dcloud.android.widget.SlideLayout;
/*     */ import io.dcloud.common.adapter.ui.fresh.ILoadingLayout.State;
/*     */ import io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.OnStateChangeListener;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class BounceView
/*     */   implements PullToRefreshBase.OnStateChangeListener
/*     */ {
/*  19 */   static final String[] keys = { "top", "left", "right", "bottom" };
/*  20 */   int[] changeStateHeights = new int[keys.length];
/*  21 */   int[] maxPullHeights = new int[keys.length];
/*  22 */   String[] mPositions = new String[keys.length];
/*  23 */   boolean[] mSupports = new boolean[keys.length];
/*     */   AdaFrameView mFrameView;
/*     */   AdaWebview mWebview;
/*     */   private float mWebviewScale;
/*     */   JSONObject mJSONObject;
/*  82 */   ILoadingLayout.State mCurState = null;
/*     */   static final String STATE_CHANGED_TEMPLATE = "{status:'%s'}";
/*     */ 
/*     */   BounceView(AdaFrameView paramAdaFrameView, AdaWebview paramAdaWebview)
/*     */   {
/*  29 */     this.mFrameView = paramAdaFrameView;
/*  30 */     this.mWebview = paramAdaWebview;
/*  31 */     this.mWebviewScale = paramAdaWebview.getScaleOfOpenerWebview();
/*     */   }
/*     */ 
/*     */   void parseJsonOption(JSONObject paramJSONObject) {
/*     */     try {
/*  36 */       this.mJSONObject = JSONUtil.combinJSONObject(this.mJSONObject, paramJSONObject);
/*  37 */       paramJSONObject = this.mJSONObject;
/*  38 */       boolean bool = paramJSONObject.isNull("position");
/*     */       JSONObject localJSONObject;
/*     */       int i;
/*     */       String str;
/*  39 */       if (!bool) {
/*  40 */         localJSONObject = JSONUtil.getJSONObject(paramJSONObject, "position");
/*     */ 
/*  42 */         for (i = 0; i < keys.length; i++)
/*  43 */           if (!localJSONObject.isNull(keys[i]))
/*     */           {
/*  46 */             str = JSONUtil.getString(localJSONObject, keys[i]);
/*  47 */             if ("none".equals(str)) {
/*  48 */               this.mSupports[i] = false;
/*  49 */             } else if ("auto".equals(str)) {
/*  50 */               this.mSupports[i] = true;
/*  51 */               this.maxPullHeights[i] = (this.mWebview.mViewOptions.height / 3);
/*  52 */               this.changeStateHeights[i] = (this.maxPullHeights[i] / 2);
/*     */             } else {
/*  54 */               this.mSupports[i] = true;
/*  55 */               this.maxPullHeights[i] = PdrUtil.convertToScreenInt(str, this.mWebview.mViewOptions.height, this.mWebview.mViewOptions.height / 3, this.mWebviewScale);
/*  56 */               this.changeStateHeights[i] = (this.maxPullHeights[i] / 2);
/*     */             }
/*     */           }
/*     */       } else {
/*  60 */         this.mSupports[0] = true;
/*  61 */         this.maxPullHeights[0] = (this.mWebview.mViewOptions.height / 3);
/*  62 */         this.changeStateHeights[0] = (this.maxPullHeights[0] / 2);
/*     */       }
/*  64 */       bool = paramJSONObject.isNull("changeoffset");
/*  65 */       if (!bool) {
/*  66 */         localJSONObject = JSONUtil.getJSONObject(paramJSONObject, "changeoffset");
/*  67 */         for (i = 0; i < keys.length; i++)
/*  68 */           if (!localJSONObject.isNull(keys[i]))
/*     */           {
/*  71 */             str = JSONUtil.getString(localJSONObject, keys[i]);
/*  72 */             this.changeStateHeights[i] = PdrUtil.convertToScreenInt(str, this.mWebview.mViewOptions.height, this.maxPullHeights[i] / 2, this.mWebviewScale);
/*     */           }
/*     */       } else {
/*  75 */         this.changeStateHeights[0] = (this.maxPullHeights[0] / 2);
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {
/*  79 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onStateChanged(ILoadingLayout.State paramState, boolean paramBoolean)
/*     */   {
/*  86 */     int i = this.mCurState != paramState ? 1 : 0;
/*  87 */     this.mCurState = paramState;
/*  88 */     if ((i != 0) && 
/*  89 */       (paramState != ILoadingLayout.State.RESET))
/*     */     {
/*  91 */       if (paramState == ILoadingLayout.State.PULL_TO_REFRESH) {
/*  92 */         Logger.d("refresh", "BounceView PULL_TO_REFRESH");
/*  93 */         this.mWebview.mFrameView.dispatchFrameViewEvents("dragBounce", String.format("{status:'%s'}", new Object[] { "beforeChangeOffset" }));
/*  94 */       } else if (paramState == ILoadingLayout.State.RELEASE_TO_REFRESH) {
/*  95 */         Logger.d("refresh", "BounceView RELEASE_TO_REFRESH");
/*  96 */         this.mWebview.mFrameView.dispatchFrameViewEvents("dragBounce", String.format("{status:'%s'}", new Object[] { "afterChangeOffset" }));
/*  97 */       } else if (paramState == ILoadingLayout.State.REFRESHING) {
/*  98 */         Logger.d("refresh", "BounceView REFRESHING");
/*  99 */         this.mWebview.mFrameView.dispatchFrameViewEvents("dragBounce", String.format("{status:'%s'}", new Object[] { "dragEndAfterChangeOffset" }));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onResize() {
/* 105 */     parseJsonOption(this.mJSONObject);
/*     */   }
/*     */ 
/*     */   public void checkOffset(AdaFrameView paramAdaFrameView, final AdaWebViewParent.PullToRefreshWebViewExt paramPullToRefreshWebViewExt, JSONObject paramJSONObject, int paramInt1, int paramInt2)
/*     */   {
/* 117 */     JSONObject localJSONObject = JSONUtil.getJSONObject(paramJSONObject, "offset");
/* 118 */     if (localJSONObject != null) {
/* 119 */       String str1 = JSONUtil.getString(localJSONObject, "top");
/* 120 */       String str2 = JSONUtil.getString(localJSONObject, "left");
/* 121 */       String str3 = JSONUtil.getString(localJSONObject, "right");
/* 122 */       if (!TextUtils.isEmpty(str1)) {
/* 123 */         int i = PdrUtil.convertToScreenInt(str1, this.mWebview.mViewOptions.height, paramInt2, this.mWebview.getScale());
/* 124 */         if (i >= paramInt1) {
/* 125 */           i = i > paramInt2 ? paramInt2 : i;
/* 126 */           paramPullToRefreshWebViewExt.smoothScrollTo(-i);
/* 127 */           paramPullToRefreshWebViewExt.doPullRefreshing(true, 250L);
/*     */         }
/*     */         else {
/* 130 */           paramPullToRefreshWebViewExt.smoothScrollTo(-i);
/* 131 */           this.mWebview.obtainWebview().postDelayed(new Runnable()
/*     */           {
/*     */             public void run()
/*     */             {
/* 136 */               paramPullToRefreshWebViewExt.smoothScrollTo(0);
/*     */             }
/*     */           }
/*     */           , 250L);
/*     */         }
/*     */ 
/*     */       }
/* 140 */       else if ((!TextUtils.isEmpty(str3)) && ((paramAdaFrameView.obtainMainView() instanceof SlideLayout))) {
/* 141 */         ((SlideLayout)paramAdaFrameView.obtainMainView()).setOffset("right", str3, this.mWebview.getScale());
/* 142 */       } else if ((!TextUtils.isEmpty(str2)) && ((paramAdaFrameView.obtainMainView() instanceof SlideLayout))) {
/* 143 */         ((SlideLayout)paramAdaFrameView.obtainMainView()).setOffset("left", str2, this.mWebview.getScale());
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.BounceView
 * JD-Core Version:    0.6.2
 */