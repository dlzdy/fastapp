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
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.AbsoluteLayout.LayoutParams;
/*     */ import io.dcloud.common.DHInterface.IApp;
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
/*     */ public class PullRefreshView extends View
/*     */ {
/*     */   public static final String TAG = "PullRefreshView";
/*     */   public static final byte TYPE_PULL_UP_REFRESH = 0;
/*     */   public static final byte TYPE_PULL_DOWN_REFRESH = 1;
/*  33 */   byte mType = 1;
/*     */ 
/*  35 */   String mContent_down = "下拉可刷新";
/*  36 */   String mContent_over = "松开后刷新";
/*  37 */   String mContent_refresh = "正在刷新…";
/*  38 */   String mShowContent = null;
/*  39 */   String mSecInfo = null;
/*     */   private Rect src;
/*     */   private RectF dst;
/*  43 */   Bitmap mIcon = null;
/*  44 */   float mFontScale = 1.2F;
/*  45 */   int changeStateHeight = 100;
/*  46 */   int maxPullHeight = 100;
/*     */   float startX;
/*     */   float startY;
/*     */   AdaFrameItem mParent;
/*     */   AdaWebview mWebview;
/*     */   private float mWebviewScale;
/*     */   int sScreenWidth;
/*     */   int sScreenHeight;
/*  63 */   Paint paint = new Paint();
/*  64 */   int index = 0;
/*  65 */   static int MAX_FRAME_COUNT = 9;
/*  66 */   static int HEIGHT = 25;
/*     */ 
/* 149 */   private int contentLeft = 0;
/* 150 */   private int contentTop = 0;
/* 151 */   private int contentWidth = 0;
/*     */   static final byte STATE_NO_REFRESH = 0;
/*     */   static final byte STATE_ON_MOVE_ING = 1;
/*     */   static final byte STATE_ON_OVER = 2;
/*     */   static final byte STATE_ON_REFRESH_ING = 3;
/* 158 */   byte mState = 0;
/* 159 */   boolean touch_started = false;
/* 160 */   boolean mCaptureTouchEnd = false;
/*     */   static final byte FLAG_NO_THING = -1;
/*     */   static final byte FLAG_STARTED = 0;
/*     */   static final byte FLAG_MOVEED = 1;
/* 164 */   byte mFlag = 0;
/*     */   int icon_x;
/*     */   int icon_y;
/*     */   int fontSize;
/*     */   static final int color_tr = 16711920;
/* 214 */   int mScrollHeight = 0;
/* 215 */   byte SCROLL_STATE_MIN = 0;
/* 216 */   byte SCROLL_STATE_MAX = 1;
/* 217 */   byte SCROLL_STATE_MIDDLE = 2;
/* 218 */   byte mScrollState = this.SCROLL_STATE_MIN;
/*     */ 
/* 220 */   boolean mEnableScrollMinHeight = true;
/*     */ 
/* 222 */   boolean mEnableScrollMaxHeight = true;
/*     */   float lastScrollY;
/* 299 */   boolean mRefreshState = false;
/* 300 */   Timer mUpdateProgressBar = null;
/*     */ 
/*     */   public PullRefreshView(AdaFrameItem paramAdaFrameItem, AdaWebview paramAdaWebview)
/*     */   {
/*  53 */     super(paramAdaFrameItem.getContext());
/*  54 */     this.mParent = paramAdaFrameItem;
/*  55 */     this.mWebview = paramAdaWebview;
/*  56 */     this.sScreenWidth = paramAdaWebview.obtainApp().getInt(0);
/*  57 */     this.sScreenHeight = paramAdaWebview.obtainApp().getInt(1);
/*  58 */     this.mWebviewScale = paramAdaWebview.getScale();
/*  59 */     init(null);
/*  60 */     this.paint.setAntiAlias(true);
/*     */   }
/*     */ 
/*     */   protected void onDraw(Canvas paramCanvas)
/*     */   {
/*  69 */     if (this.mState != 0) {
/*  70 */       paramCanvas.drawColor(-1907998);
/*  71 */       this.paint.setColor(-16777216);
/*  72 */       this.paint.setTextSize(this.fontSize);
/*  73 */       paramCanvas.drawText(this.mShowContent, this.contentLeft, this.contentTop, this.paint);
/*  74 */       if (this.mIcon != null)
/*  75 */         paramCanvas.drawBitmap(this.mIcon, this.src, this.dst, this.paint);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateScreen()
/*     */   {
/*  81 */     this.index += 1;
/*  82 */     if (this.index >= MAX_FRAME_COUNT) {
/*  83 */       this.index = 0;
/*     */     }
/*  85 */     this.src.set(HEIGHT * this.index, 0, HEIGHT * (this.index + 1), HEIGHT);
/*  86 */     postInvalidate();
/*     */   }
/*     */   public void init(String paramString) {
/*  89 */     BitmapFactory.Options localOptions = new BitmapFactory.Options();
/*  90 */     localOptions.inScaled = false;
/*  91 */     Bitmap localBitmap = BitmapFactory.decodeStream(PlatformUtil.getResInputStream("res/dcloud_prograss_snow1.png"), null, localOptions);
/*  92 */     this.mIcon = localBitmap;
/*  93 */     HEIGHT = localBitmap.getHeight();
/*  94 */     float f1 = 0.02F;
/*  95 */     float f2 = 0.41F;
/*  96 */     float f3 = f1 + f2;
/*     */ 
/*  98 */     this.contentLeft = ((int)(this.sScreenWidth * f3));
/*  99 */     this.icon_x = ((int)(this.sScreenWidth * f2) - HEIGHT);
/*     */ 
/* 101 */     this.src = new Rect(0, 0, HEIGHT, HEIGHT);
/* 102 */     this.dst = new RectF(0.0F, 150.0F, HEIGHT, HEIGHT);
/*     */ 
/* 104 */     MAX_FRAME_COUNT = localBitmap.getWidth() / HEIGHT;
/*     */   }
/*     */ 
/*     */   public void parseJsonOption(JSONObject paramJSONObject)
/*     */   {
/*     */     try
/*     */     {
/* 113 */       boolean bool = paramJSONObject.isNull("height");
/*     */       int i;
/* 114 */       if (!bool) {
/* 115 */         i = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "height"), this.mWebview.mFrameView.mViewOptions.height, this.changeStateHeight, this.mWebviewScale);
/* 116 */         this.maxPullHeight = (this.changeStateHeight = i);
/*     */       }
/* 118 */       bool = paramJSONObject.isNull("range");
/* 119 */       if (!bool) {
/* 120 */         i = PdrUtil.convertToScreenInt(paramJSONObject.getString("range"), this.mWebview.mFrameView.mViewOptions.height, this.changeStateHeight, this.mWebviewScale);
/* 121 */         this.maxPullHeight = i;
/*     */       }
/* 123 */       bool = paramJSONObject.isNull("contentdown");
/*     */       String str;
/* 124 */       if (!bool) {
/* 125 */         str = JSONUtil.getString(paramJSONObject.getJSONObject("contentdown"), "caption");
/* 126 */         changeStringInfo(str);
/*     */       }
/* 128 */       bool = paramJSONObject.isNull("contentover");
/* 129 */       if (!bool) {
/* 130 */         str = JSONUtil.getString(paramJSONObject.getJSONObject("contentover"), "caption");
/* 131 */         this.mContent_over = str;
/*     */       }
/* 133 */       bool = paramJSONObject.isNull("contentrefresh");
/* 134 */       if (!bool) {
/* 135 */         str = JSONUtil.getString(paramJSONObject.getJSONObject("contentrefresh"), "caption");
/* 136 */         this.mContent_refresh = str;
/*     */       }
/*     */ 
/* 139 */       int j = Math.max(this.maxPullHeight - this.changeStateHeight, 0);
/* 140 */       Paint.FontMetricsInt localFontMetricsInt = DeviceInfo.sPaint.getFontMetricsInt();
/* 141 */       this.contentTop = (j + (this.changeStateHeight >> 1) + (localFontMetricsInt.bottom - localFontMetricsInt.top >> 1));
/* 142 */       this.icon_y = (j + (this.changeStateHeight - HEIGHT >> 1));
/* 143 */       this.dst.set(this.icon_x, this.icon_y, this.icon_x + HEIGHT, this.icon_y + HEIGHT);
/* 144 */       Logger.d("PullRefreshView", "height=" + this.changeStateHeight + ";range=" + this.maxPullHeight + ";contentdown=" + this.mShowContent);
/*     */     } catch (JSONException localJSONException) {
/* 146 */       localJSONException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void changeStringInfo(String paramString)
/*     */   {
/* 168 */     this.mShowContent = paramString;
/* 169 */     this.fontSize = ((int)(DeviceInfo.DEFAULT_FONT_SIZE * DeviceInfo.sDensity * this.mFontScale));
/* 170 */     this.paint.setAntiAlias(true);
/* 171 */     int i = paramString.length();
/* 172 */     float[] arrayOfFloat = new float[i];
/* 173 */     this.paint.getTextWidths(paramString, arrayOfFloat);
/* 174 */     float f = 0.0F;
/* 175 */     for (int j = 0; j < i; j++) {
/* 176 */       f += arrayOfFloat[j];
/*     */     }
/* 178 */     this.contentWidth = ((int)f);
/*     */   }
/*     */ 
/*     */   void setColorByParentChild(View paramView)
/*     */   {
/* 184 */     int i = 0;
/* 185 */     int j = 2;
/* 186 */     Object localObject = paramView;
/* 187 */     while (i != j) {
/* 188 */       View localView = (View)((View)localObject).getParent();
/* 189 */       localView.setBackgroundColor(16711920);
/* 190 */       localObject = localView;
/* 191 */       i++;
/*     */     }
/*     */   }
/*     */ 
/* 195 */   void onPullDown_start(float paramFloat1, float paramFloat2) { if (!this.touch_started) {
/* 196 */       Logger.d("PullRefreshView", "onPullDown_start");
/* 197 */       this.startX = paramFloat1;
/* 198 */       this.lastScrollY = (this.startY = paramFloat2);
/* 199 */       if (getParent() == null) {
/* 200 */         ((ViewGroup)this.mParent.obtainMainView()).addView(this, 0, new AbsoluteLayout.LayoutParams(-1, this.maxPullHeight, this.mWebview.mViewOptions.left, this.mWebview.mViewOptions.top - this.maxPullHeight));
/*     */       }
/*     */ 
/* 204 */       if (this.mState == 0) {
/* 205 */         this.mState = 1;
/* 206 */         this.mFlag = 0;
/* 207 */       } else if (this.mState == 3) {
/* 208 */         this.mFlag = 0;
/*     */       }
/* 210 */       this.touch_started = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean updateScrollState(byte paramByte)
/*     */   {
/* 225 */     boolean bool = false;
/* 226 */     this.mScrollState = paramByte;
/* 227 */     if (paramByte == this.SCROLL_STATE_MAX) {
/* 228 */       this.mScrollHeight = this.maxPullHeight;
/* 229 */       if (this.mEnableScrollMaxHeight) {
/* 230 */         bool = true;
/* 231 */         this.mEnableScrollMaxHeight = false;
/*     */       }
/* 233 */       this.mEnableScrollMinHeight = true;
/* 234 */     } else if (paramByte == this.SCROLL_STATE_MIN) {
/* 235 */       this.mEnableScrollMaxHeight = true;
/* 236 */       this.mScrollHeight = 0;
/* 237 */       if (this.mEnableScrollMinHeight) {
/* 238 */         bool = true;
/* 239 */         this.mEnableScrollMinHeight = false;
/*     */       }
/* 241 */     } else if (paramByte == this.SCROLL_STATE_MIDDLE) {
/* 242 */       this.mEnableScrollMinHeight = true;
/* 243 */       this.mEnableScrollMaxHeight = true;
/* 244 */       bool = true;
/*     */     }
/* 246 */     return bool;
/*     */   }
/*     */ 
/*     */   boolean onMove(float paramFloat1, float paramFloat2) {
/* 250 */     boolean bool1 = true;
/* 251 */     boolean bool2 = true;
/* 252 */     int i = (int)((paramFloat2 - this.lastScrollY) * this.maxPullHeight / this.mWebview.mFrameView.mViewOptions.height);
/*     */ 
/* 254 */     if (Math.abs(i) < 1) {
/* 255 */       if (this.mScrollHeight > 0) {
/* 256 */         bool1 = true;
/*     */       } else {
/* 258 */         if (((i > 0.5F) || (i < -0.5F)) && 
/* 259 */           (this.mFlag == 0)) {
/* 260 */           this.mFlag = 1;
/*     */         }
/*     */ 
/* 263 */         bool1 = false;
/*     */       }
/*     */     } else {
/* 266 */       this.mScrollHeight += i;
/*     */ 
/* 268 */       if (this.mScrollHeight >= this.maxPullHeight)
/* 269 */         bool2 = updateScrollState(this.SCROLL_STATE_MAX);
/* 270 */       else if (this.mScrollHeight <= 0)
/* 271 */         bool2 = updateScrollState(this.SCROLL_STATE_MIN);
/*     */       else {
/* 273 */         bool2 = updateScrollState(this.SCROLL_STATE_MIDDLE);
/*     */       }
/* 275 */       if (this.mState != 3) {
/* 276 */         if ((this.mState == 1) && (this.mScrollHeight >= this.changeStateHeight)) {
/* 277 */           this.mState = 2;
/* 278 */           changeStringInfo(this.mContent_over);
/* 279 */         } else if ((this.mState == 2) && (this.mScrollHeight < this.changeStateHeight)) {
/* 280 */           this.mState = 1;
/* 281 */           changeStringInfo(this.mContent_down);
/*     */         }
/*     */       }
/* 284 */       bool1 = bool2;
/* 285 */       if (bool2) {
/* 286 */         if (this.mFlag == 0) {
/* 287 */           this.mFlag = 1;
/* 288 */           Logger.d("PullRefreshView", "onMove; mFlag=FLAG_MOVEED");
/* 289 */           startUpdateScreenTimer();
/*     */         }
/*     */ 
/* 292 */         this.mParent.obtainMainView().scrollBy(0, -i);
/* 293 */         this.lastScrollY = paramFloat2;
/*     */       }
/*     */     }
/* 296 */     return bool1;
/*     */   }
/*     */ 
/*     */   void onExecuting()
/*     */   {
/* 302 */     Logger.d("PullRefreshView", "onExecuting");
/* 303 */     this.mState = 3;
/* 304 */     this.mRefreshState = true;
/* 305 */     this.mFlag = -1;
/* 306 */     Logger.d("PullRefreshView", "onExecuting; mFlag = FLAG_NO_THING");
/* 307 */     changeStringInfo(this.mContent_refresh);
/* 308 */     this.mScrollHeight = this.changeStateHeight;
/* 309 */     smoothScrollTo((ViewGroup)this.mParent.obtainMainView(), null, 0, -this.changeStateHeight, 1);
/*     */   }
/*     */   private void stopUpdateScreenTimer() {
/* 312 */     if (this.mUpdateProgressBar != null) {
/* 313 */       this.mUpdateProgressBar.cancel();
/* 314 */       this.mUpdateProgressBar = null;
/*     */     }
/*     */   }
/*     */ 
/* 318 */   private void startUpdateScreenTimer() { stopUpdateScreenTimer();
/* 319 */     this.mUpdateProgressBar = new Timer();
/* 320 */     this.mUpdateProgressBar.schedule(new TimerTask()
/*     */     {
/*     */       public void run() {
/* 323 */         PullRefreshView.this.updateScreen();
/*     */       }
/*     */     }
/*     */     , 0L, 100L);
/*     */   }
/*     */ 
/*     */   void onPullDown_end()
/*     */   {
/* 328 */     if (this.mScrollHeight <= this.changeStateHeight) {
/* 329 */       this.mState = 0;
/* 330 */       this.mScrollHeight = 0;
/* 331 */       this.mFlag = -1;
/* 332 */       Logger.d("PullRefreshView", "onPullDown_end; mFlag = FLAG_NO_THING");
/* 333 */       changeStringInfo(this.mContent_down);
/* 334 */       if (this.mUpdateProgressBar != null) {
/* 335 */         this.mUpdateProgressBar.cancel();
/*     */       }
/* 337 */       this.mParent.obtainMainView().scrollTo(0, 0);
/* 338 */       stopUpdateScreenTimer();
/*     */     }
/*     */     else {
/* 341 */       smoothScrollToStateHeight(true);
/*     */     }
/* 343 */     this.mRefreshState = false;
/*     */   }
/*     */ 
/*     */   void smoothScrollToStateHeight(boolean paramBoolean)
/*     */   {
/* 351 */     if (paramBoolean) {
/* 352 */       this.mScrollHeight = this.changeStateHeight;
/* 353 */       smoothScrollTo((ViewGroup)this.mParent.obtainMainView(), null, 0, -this.changeStateHeight, 1);
/*     */     }
/* 355 */     else if (this.mScrollHeight > this.changeStateHeight) {
/* 356 */       smoothScrollToStateHeight(true);
/*     */     } else {
/* 358 */       this.mRefreshState = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   static void smoothScrollTo(final ViewGroup paramViewGroup, View paramView, final int paramInt1, final int paramInt2, int paramInt3)
/*     */   {
/* 364 */     final Timer localTimer = new Timer();
/* 365 */     localTimer.schedule(new TimerTask() { View child;
/*     */       ViewGroup parent;
/*     */       final int TIME = 500;
/*     */       int toX;
/*     */       int toY;
/*     */       int fromX;
/*     */       int fromY;
/*     */       int timesCount;
/*     */       int flagTimes;
/*     */       int vX;
/*     */       int vY;
/*     */ 
/* 386 */       public void run() { int i = this.fromX + this.vX;
/* 387 */         int j = this.fromY + this.vY;
/* 388 */         if (this.flagTimes == this.timesCount) {
/* 389 */           i = this.toX;
/* 390 */           j = this.toY;
/*     */         }
/*     */ 
/* 393 */         PullRefreshView.scrollToByMessage(this.parent, i, j);
/* 394 */         if (this.flagTimes == this.timesCount) {
/* 395 */           if (this.child != null) {
/* 396 */             this.parent.post(new Runnable()
/*     */             {
/*     */               public void run() {
/* 399 */                 PullRefreshView.2.this.parent.removeView(PullRefreshView.2.this.child);
/*     */               }
/*     */             });
/*     */           }
/* 403 */           localTimer.cancel();
/*     */         }
/* 405 */         this.fromX = i;
/* 406 */         this.fromY = j;
/* 407 */         this.flagTimes += 1;
/*     */       }
/*     */     }
/*     */     , 0L, paramInt3);
/*     */   }
/*     */ 
/*     */   private static void scrollToByMessage(View paramView, final int paramInt1, final int paramInt2)
/*     */   {
/* 413 */     paramView.post(new Runnable()
/*     */     {
/*     */       public void run() {
/* 416 */         this.val$view.scrollTo(paramInt1, paramInt2);
/*     */       }
/*     */     });
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.PullRefreshView
 * JD-Core Version:    0.6.2
 */