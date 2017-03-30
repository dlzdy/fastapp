/*     */ package io.dcloud.common.adapter.ui;
/*     */ 
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.BitmapFactory.Options;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Paint.FontMetricsInt;
/*     */ import android.graphics.Rect;
/*     */ import android.graphics.RectF;
/*     */ import android.view.View;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.adapter.ui.fresh.ILoadingLayout.State;
/*     */ import io.dcloud.common.adapter.ui.fresh.PullToRefreshBase.OnStateChangeListener;
/*     */ import io.dcloud.common.adapter.util.CanvasHelper;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class RefreshView
/*     */   implements PullToRefreshBase.OnStateChangeListener
/*     */ {
/*     */   public static final String TAG = "RefreshView";
/*     */   private Rect src;
/*     */   private RectF dst;
/*  36 */   Bitmap mIcon = null;
/*  37 */   float mFontScale = 1.2F;
/*  38 */   int changeStateHeight = 100;
/*  39 */   int maxPullHeight = 100;
/*     */   float startX;
/*     */   float startY;
/*     */   AdaFrameView mFrameView;
/*     */   AdaWebview mWebview;
/*     */   private float mWebviewScale;
/*     */   JSONObject mJSONObject;
/*  52 */   Paint paint = new Paint();
/*     */ 
/*  54 */   int index = 0;
/*     */ 
/*  56 */   int MAX_FRAME_COUNT = 9;
/*     */ 
/*  58 */   int HEIGHT = 25;
/*     */ 
/*  74 */   Timer mUpdateProgressBar = null;
/*     */ 
/* 198 */   private int contentLeft = 0;
/*     */ 
/* 200 */   private int contentTop = 0;
/*     */   int icon_x;
/*     */   int icon_y;
/*     */   int fontSize;
/* 218 */   String mContent_down = "下拉可刷新";
/* 219 */   String mContent_over = "松开后刷新";
/* 220 */   String mContent_refresh = "正在刷新…";
/* 221 */   String mShowContent = null;
/*     */ 
/* 223 */   ILoadingLayout.State mState = ILoadingLayout.State.RESET;
/*     */ 
/*     */   public RefreshView(AdaFrameView paramAdaFrameView, AdaWebview paramAdaWebview)
/*     */   {
/*  46 */     this.mFrameView = paramAdaFrameView;
/*  47 */     this.mWebview = paramAdaWebview;
/*  48 */     this.mWebviewScale = paramAdaWebview.getScale();
/*  49 */     this.paint.setAntiAlias(true);
/*     */   }
/*     */ 
/*     */   protected void paint(Canvas paramCanvas, int paramInt1, int paramInt2)
/*     */   {
/*  61 */     paramCanvas.drawColor(-1907998);
/*  62 */     this.paint.setColor(-16777216);
/*  63 */     this.paint.setTextSize(this.fontSize);
/*  64 */     if ((this.mShowContent != null) && (this.mIcon != null)) {
/*  65 */       computePosition(this.paint);
/*     */ 
/*  67 */       CanvasHelper.drawString(paramCanvas, this.mShowContent, this.contentLeft + paramInt1, this.contentTop + paramInt2, 17, this.paint);
/*  68 */       this.dst.set(paramInt1 + this.icon_x, paramInt2 + this.icon_y, paramInt1 + this.icon_x + this.HEIGHT, paramInt2 + this.icon_y + this.HEIGHT);
/*  69 */       paramCanvas.drawBitmap(this.mIcon, this.src, this.dst, this.paint);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void stopUpdateScreenTimer()
/*     */   {
/*  76 */     if (this.mUpdateProgressBar != null) {
/*  77 */       this.mUpdateProgressBar.cancel();
/*  78 */       this.mUpdateProgressBar = null;
/*     */     }
/*     */   }
/*     */ 
/*  82 */   private void startUpdateScreenTimer() { stopUpdateScreenTimer();
/*  83 */     if (this.mFrameView.obtainMainView() != null) {
/*  84 */       this.mUpdateProgressBar = new Timer();
/*  85 */       this.mUpdateProgressBar.schedule(new TimerTask()
/*     */       {
/*     */         public void run() {
/*  88 */           if (RefreshView.this.mFrameView.obtainMainView() == null)
/*  89 */             cancel();
/*     */           try
/*     */           {
/*  92 */             RefreshView.this.updateScreen();
/*  93 */             RefreshView.this.mFrameView.obtainMainView().postInvalidate(0, 0, RefreshView.this.mFrameView.obtainFrameOptions().width, RefreshView.this.maxPullHeight);
/*     */           } catch (Exception localException) {
/*  95 */             localException.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */       , 0L, 100L);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void updateScreen()
/*     */   {
/* 102 */     this.index += 1;
/* 103 */     if (this.index >= this.MAX_FRAME_COUNT) {
/* 104 */       this.index = 0;
/*     */     }
/* 106 */     this.src.set(this.HEIGHT * this.index, 0, this.HEIGHT * (this.index + 1), this.HEIGHT);
/*     */   }
/*     */ 
/*     */   public void init(String paramString)
/*     */   {
/* 115 */     this.fontSize = ((int)(DeviceInfo.DEFAULT_FONT_SIZE * DeviceInfo.sDensity * this.mFontScale));
/*     */ 
/* 117 */     this.paint.setAntiAlias(true);
/*     */ 
/* 119 */     BitmapFactory.Options localOptions = new BitmapFactory.Options();
/* 120 */     localOptions.inScaled = false;
/* 121 */     Bitmap localBitmap = BitmapFactory.decodeStream(PlatformUtil.getResInputStream("res/dcloud_prograss_snow1.png"), null, localOptions);
/* 122 */     this.mIcon = localBitmap;
/*     */ 
/* 124 */     this.HEIGHT = localBitmap.getHeight();
/* 125 */     this.src = new Rect(0, 0, this.HEIGHT, this.HEIGHT);
/* 126 */     this.dst = new RectF(0.0F, 150.0F, this.HEIGHT, this.HEIGHT);
/* 127 */     Logger.d("RefreshView", "height=" + this.changeStateHeight + ";range=" + this.maxPullHeight + ";contentdown=" + this.mShowContent);
/*     */ 
/* 131 */     this.MAX_FRAME_COUNT = (localBitmap.getWidth() / this.HEIGHT);
/*     */   }
/*     */ 
/*     */   private void computePosition(Paint paramPaint)
/*     */   {
/* 136 */     Paint.FontMetricsInt localFontMetricsInt = paramPaint.getFontMetricsInt();
/* 137 */     this.icon_y = (this.changeStateHeight - this.HEIGHT >> 1);
/* 138 */     this.contentTop = (this.icon_y + (this.HEIGHT - (localFontMetricsInt.bottom - localFontMetricsInt.top)) / 2);
/*     */ 
/* 141 */     int i = this.mFrameView.obtainApp().getInt(0);
/* 142 */     float f1 = paramPaint.measureText(this.mShowContent);
/* 143 */     float f2 = i * 0.02F;
/*     */ 
/* 145 */     this.icon_x = ((int)(i - f2 - this.HEIGHT - f1) / 2);
/* 146 */     this.contentLeft = ((int)(this.icon_x + f2 + this.HEIGHT));
/*     */   }
/*     */ 
/*     */   void onResize() {
/* 150 */     parseJsonOption(this.mJSONObject);
/*     */   }
/*     */ 
/*     */   public void parseJsonOption(JSONObject paramJSONObject)
/*     */   {
/*     */     try
/*     */     {
/* 160 */       this.mJSONObject = JSONUtil.combinJSONObject(this.mJSONObject, paramJSONObject);
/* 161 */       paramJSONObject = this.mJSONObject;
/* 162 */       boolean bool = paramJSONObject.isNull("height");
/*     */       int i;
/* 164 */       if (!bool) {
/* 165 */         i = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "height"), this.mWebview.mFrameView.mViewOptions.height, this.changeStateHeight, this.mWebviewScale);
/* 166 */         this.changeStateHeight = i;
/*     */       }
/*     */ 
/* 169 */       bool = paramJSONObject.isNull("range");
/* 170 */       if (!bool) {
/* 171 */         i = PdrUtil.convertToScreenInt(paramJSONObject.getString("range"), this.mWebview.mFrameView.mViewOptions.height, this.maxPullHeight, this.mWebviewScale);
/* 172 */         this.maxPullHeight = i;
/*     */       }
/*     */ 
/* 175 */       bool = paramJSONObject.isNull("contentdown");
/*     */       String str;
/* 176 */       if (!bool) {
/* 177 */         str = JSONUtil.getString(paramJSONObject.getJSONObject("contentdown"), "caption");
/* 178 */         this.mContent_down = str;
/*     */       }
/*     */ 
/* 181 */       bool = paramJSONObject.isNull("contentover");
/* 182 */       if (!bool) {
/* 183 */         str = JSONUtil.getString(paramJSONObject.getJSONObject("contentover"), "caption");
/* 184 */         this.mContent_over = str;
/*     */       }
/*     */ 
/* 187 */       bool = paramJSONObject.isNull("contentrefresh");
/* 188 */       if (!bool) {
/* 189 */         str = JSONUtil.getString(paramJSONObject.getJSONObject("contentrefresh"), "caption");
/* 190 */         this.mContent_refresh = str;
/*     */       }
/* 192 */       init(null);
/*     */     } catch (JSONException localJSONException) {
/* 194 */       localJSONException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void changeStringInfo(String paramString)
/*     */   {
/* 205 */     this.mShowContent = paramString;
/*     */ 
/* 208 */     int i = paramString.length();
/* 209 */     float[] arrayOfFloat = new float[i];
/* 210 */     this.paint.getTextWidths(paramString, arrayOfFloat);
/* 211 */     float f = 0.0F;
/* 212 */     for (int j = 0; j < i; j++)
/* 213 */       f += arrayOfFloat[j];
/*     */   }
/*     */ 
/*     */   public void onStateChanged(ILoadingLayout.State paramState, boolean paramBoolean)
/*     */   {
/* 227 */     int i = this.mState != paramState ? 1 : 0;
/* 228 */     this.mState = paramState;
/* 229 */     if (i != 0)
/* 230 */       if (paramState == ILoadingLayout.State.RESET) {
/* 231 */         Logger.d("refresh", "RefreshView RESET");
/* 232 */         changeStringInfo(this.mContent_down);
/* 233 */         stopUpdateScreenTimer();
/* 234 */       } else if (paramState == ILoadingLayout.State.PULL_TO_REFRESH) {
/* 235 */         Logger.d("refresh", "RefreshView PULL_TO_REFRESH");
/* 236 */         changeStringInfo(this.mContent_down);
/* 237 */       } else if (paramState == ILoadingLayout.State.RELEASE_TO_REFRESH) {
/* 238 */         Logger.d("refresh", "RefreshView RELEASE_TO_REFRESH");
/* 239 */         changeStringInfo(this.mContent_over);
/* 240 */       } else if (paramState == ILoadingLayout.State.REFRESHING) {
/* 241 */         Logger.d("refresh", "RefreshView REFRESHING");
/* 242 */         changeStringInfo(this.mContent_refresh);
/* 243 */         startUpdateScreenTimer();
/* 244 */         this.mFrameView.dispatchFrameViewEvents("pulldownrefreshevent", "3");
/*     */       }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.RefreshView
 * JD-Core Version:    0.6.2
 */