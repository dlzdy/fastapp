/*     */ package com.dcloud.android.widget;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.Configuration;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.RectF;
/*     */ import android.graphics.Region.Op;
/*     */ import android.text.TextUtils;
/*     */ import android.view.MotionEvent;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameView;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import io.dcloud.common.b.b.h;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class AbsoluteLayout extends SlideLayout
/*     */ {
/*  28 */   AdaFrameView mFrameView = null;
/*  29 */   ViewOptions mViewOptions = null;
/*  30 */   IApp mAppHandler = null;
/*     */   static final String STATE_CHANGED_TEMPLATE = "{status:'%s',offset:'%s'}";
/*     */   h mDrag;
/*  33 */   private boolean isCanDrag = false;
/*     */ 
/*  50 */   boolean canDoMaskClickEvent = true;
/*     */   float downX;
/*     */   float downY;
/*     */   private RectF mRegionRect;
/*     */   String mCallBackID;
/*     */   private int mRegionLeft;
/*     */   private int mRegionRight;
/*     */   private int mRegionTop;
/*     */   private int mRegionBottom;
/*     */   private boolean isAnimate;
/*     */ 
/*     */   public AbsoluteLayout(Context paramContext, AdaFrameView paramAdaFrameView, IApp paramIApp)
/*     */   {
/*  35 */     super(paramContext);
/*  36 */     this.mDrag = new h(paramAdaFrameView, paramContext);
/*  37 */     this.mFrameView = paramAdaFrameView;
/*  38 */     this.mAppHandler = paramIApp;
/*  39 */     this.mViewOptions = this.mFrameView.obtainFrameOptions();
/*  40 */     setOnStateChangeListener(new SlideLayout.OnStateChangeListener()
/*     */     {
/*     */       public void onStateChanged(String paramAnonymousString1, String paramAnonymousString2)
/*     */       {
/*  45 */         AbsoluteLayout.this.mFrameView.dispatchFrameViewEvents("slideBounce", String.format("{status:'%s',offset:'%s'}", new Object[] { paramAnonymousString1, paramAnonymousString2 }));
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   private void doClickEvent(MotionEvent paramMotionEvent)
/*     */   {
/*  53 */     if (paramMotionEvent.getAction() == 0) {
/*  54 */       this.canDoMaskClickEvent = true;
/*  55 */       this.downX = paramMotionEvent.getX();
/*  56 */       this.downY = paramMotionEvent.getY();
/*     */     }
/*     */     else
/*     */     {
/*     */       float f1;
/*     */       float f2;
/*     */       int i;
/*  57 */       if (paramMotionEvent.getAction() == 1) {
/*  58 */         f1 = paramMotionEvent.getX();
/*  59 */         f2 = paramMotionEvent.getY();
/*  60 */         i = 10;
/*  61 */         if ((this.canDoMaskClickEvent) && (Math.abs(this.downX - f1) <= i) && (Math.abs(this.downY - f2) <= i))
/*  62 */           this.mFrameView.dispatchFrameViewEvents("maskClick", null);
/*     */       }
/*  64 */       else if (paramMotionEvent.getAction() == 2) {
/*  65 */         f1 = paramMotionEvent.getX();
/*  66 */         f2 = paramMotionEvent.getY();
/*  67 */         i = 10;
/*  68 */         if ((this.canDoMaskClickEvent) && (Math.abs(this.downX - f1) > i) && (Math.abs(this.downY - f2) > i))
/*  69 */           this.canDoMaskClickEvent = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
/*     */   {
/*  76 */     if (!this.mFrameView.interceptTouchEvent) {
/*  77 */       return false;
/*     */     }
/*  79 */     if ((this.mViewOptions != null) && (this.mViewOptions.hasMask())) {
/*  80 */       doClickEvent(paramMotionEvent);
/*     */ 
/*  82 */       if (0 == paramMotionEvent.getAction()) {
/*  83 */         this.isCanDrag = false;
/*     */       }
/*  85 */       if (!this.isCanDrag) {
/*  86 */         this.isCanDrag = this.mDrag.b(paramMotionEvent);
/*     */       }
/*  88 */       if (this.isCanDrag) {
/*  89 */         onTouchEvent(paramMotionEvent);
/*     */       }
/*  91 */       return true;
/*  92 */     }if ((this.mViewOptions != null) && (this.mViewOptions.hasBackground())) {
/*  93 */       super.dispatchTouchEvent(paramMotionEvent);
/*  94 */       return true;
/*     */     }
/*  96 */     return super.dispatchTouchEvent(paramMotionEvent);
/*     */   }
/*     */ 
/*     */   protected void dispatchDraw(Canvas paramCanvas)
/*     */   {
/* 101 */     paramCanvas.save();
/* 102 */     if (this.mRegionRect != null) {
/* 103 */       paramCanvas.clipRect(this.mRegionRect, Region.Op.DIFFERENCE);
/*     */     }
/* 105 */     this.mFrameView.paint(paramCanvas);
/* 106 */     super.dispatchDraw(paramCanvas);
/* 107 */     paramCanvas.restore();
/* 108 */     if ((this.mViewOptions != null) && (this.mViewOptions.hasMask()))
/* 109 */       paramCanvas.drawColor(this.mViewOptions.maskColor);
/*     */   }
/*     */ 
/*     */   protected void onDraw(Canvas paramCanvas)
/*     */   {
/* 115 */     if (this.mRegionRect != null) {
/* 116 */       paramCanvas.save();
/* 117 */       paramCanvas.clipRect(this.mViewOptions.left, this.mViewOptions.top, this.mViewOptions.left + this.mViewOptions.width, this.mViewOptions.top + this.mViewOptions.height);
/* 118 */       paramCanvas.restore();
/*     */     }
/* 120 */     super.onDraw(paramCanvas);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 125 */     return this.mFrameView.toString();
/*     */   }
/*     */ 
/*     */   protected void onConfigurationChanged(Configuration paramConfiguration) {
/* 129 */     super.onConfigurationChanged(paramConfiguration);
/* 130 */     this.mFrameView.onConfigurationChanged();
/* 131 */     if (this.isAnimate)
/* 132 */       this.isAnimate = false;
/*     */   }
/*     */ 
/*     */   public void animate(IWebview paramIWebview, String paramString1, String paramString2)
/*     */   {
/* 141 */     if (this.mViewOptions != null) {
/* 142 */       this.mCallBackID = paramString2;
/*     */       try {
/* 144 */         JSONObject localJSONObject1 = new JSONObject(paramString1);
/* 145 */         String str = localJSONObject1.optString("type");
/* 146 */         int i = localJSONObject1.optInt("duration", 200);
/* 147 */         int j = localJSONObject1.optInt("frames", 12);
/* 148 */         JSONObject localJSONObject2 = localJSONObject1.optJSONObject("region");
/* 149 */         if (localJSONObject2 != null) {
/* 150 */           this.mRegionLeft = PdrUtil.convertToScreenInt(localJSONObject2.optString("left"), this.mViewOptions.width, 0, this.mViewOptions.mWebviewScale);
/* 151 */           this.mRegionRight = PdrUtil.convertToScreenInt(localJSONObject2.optString("right"), this.mViewOptions.width, 0, this.mViewOptions.mWebviewScale);
/* 152 */           this.mRegionTop = PdrUtil.convertToScreenInt(localJSONObject2.optString("top"), this.mViewOptions.height, 0, this.mViewOptions.mWebviewScale);
/* 153 */           this.mRegionBottom = PdrUtil.convertToScreenInt(localJSONObject2.optString("bottom"), this.mViewOptions.height, 0, this.mViewOptions.mWebviewScale);
/*     */         }
/*     */ 
/* 156 */         int k = i / j;
/* 157 */         int m = (this.mViewOptions.height - (this.mRegionTop + this.mViewOptions.top + this.mRegionBottom)) / j;
/*     */ 
/* 159 */         int n = this.mViewOptions.height - (this.mRegionTop + this.mViewOptions.top + this.mRegionBottom) - m * j;
/* 160 */         if ((!TextUtils.isEmpty(str)) && (str.equals("shrink"))) {
/* 161 */           this.isAnimate = true;
/* 162 */           runDrawRectF(paramIWebview, paramString2, this.mRegionLeft, this.mRegionRight, this.mRegionTop + this.mViewOptions.top, this.mViewOptions.height - this.mRegionBottom, k, m, j, n, 1);
/*     */         }
/*     */       } catch (Exception localException) {
/* 165 */         localException.printStackTrace();
/* 166 */         endAnimatecallback(paramIWebview, paramString2);
/*     */       }
/*     */     } else {
/* 169 */       endAnimatecallback(paramIWebview, paramString2);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void runDrawRectF(final IWebview paramIWebview, final String paramString, final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4, final int paramInt5, final int paramInt6, final int paramInt7, final int paramInt8, final int paramInt9)
/*     */   {
/* 188 */     if (!this.isAnimate) {
/* 189 */       endAnimatecallback(paramIWebview, paramString);
/* 190 */       return;
/*     */     }
/* 192 */     if (this.mRegionRect == null) {
/* 193 */       this.mRegionRect = new RectF();
/*     */     }
/* 195 */     this.mRegionRect.left = paramInt1;
/* 196 */     this.mRegionRect.right = (this.mViewOptions.width - paramInt2);
/* 197 */     this.mRegionRect.top = paramInt3;
/* 198 */     if (paramInt9 == paramInt7)
/* 199 */       this.mRegionRect.bottom = (paramInt6 * paramInt9 + paramInt3 + paramInt8);
/*     */     else {
/* 201 */       this.mRegionRect.bottom = (paramInt6 * paramInt9 + paramInt3);
/*     */     }
/* 203 */     postDelayed(new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/* 208 */         AbsoluteLayout.this.invalidate();
/* 209 */         if (paramInt9 == paramInt7) {
/* 210 */           AbsoluteLayout.this.endAnimatecallback(paramIWebview, paramString);
/* 211 */           return;
/*     */         }
/* 213 */         AbsoluteLayout.this.runDrawRectF(paramIWebview, paramString, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9 + 1);
/*     */       }
/*     */     }
/*     */     , paramInt5);
/*     */   }
/*     */ 
/*     */   public void restore()
/*     */   {
/* 219 */     this.isAnimate = false;
/* 220 */     this.mRegionRect = null;
/* 221 */     invalidate();
/*     */   }
/*     */ 
/*     */   private void endAnimatecallback(IWebview paramIWebview, String paramString)
/*     */   {
/* 226 */     if (!TextUtils.isEmpty(paramString))
/* 227 */       JSUtil.execCallback(paramIWebview, paramString, null, JSUtil.OK, false, false);
/*     */   }
/*     */ 
/*     */   public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
/*     */   {
/* 232 */     boolean bool = this.mDrag.b(paramMotionEvent);
/* 233 */     if (!bool) {
/* 234 */       return super.onInterceptTouchEvent(paramMotionEvent);
/*     */     }
/* 236 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean onTouchEvent(MotionEvent paramMotionEvent) {
/* 240 */     boolean bool = this.mDrag.c(paramMotionEvent);
/* 241 */     if (!bool) {
/* 242 */       return super.onTouchEvent(paramMotionEvent);
/*     */     }
/* 244 */     return true;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.widget.AbsoluteLayout
 * JD-Core Version:    0.6.2
 */