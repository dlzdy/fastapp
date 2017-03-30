/*     */ package io.dcloud.common.adapter.ui;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Paint.FontMetrics;
/*     */ import android.graphics.RectF;
/*     */ import android.graphics.Typeface;
/*     */ import android.text.TextUtils;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.View;
/*     */ import io.dcloud.common.DHInterface.INativeBitmap;
/*     */ import io.dcloud.common.adapter.util.CanvasHelper;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class FrameBitmapView extends View
/*     */ {
/*     */   private INativeBitmap mNativeBitmap;
/*     */   private Paint mPaint;
/*     */   private String mTextValue;
/*     */   private float mFontCX;
/*     */   private float mFontCY;
/*     */   private float mBitmapCX;
/*     */   private float mBitmapCY;
/*     */   private String mTexts;
/*  30 */   public static String NORMAL = "normal";
/*  31 */   public static String BOLD = "bold";
/*  32 */   public static String ITALIC = "italic";
/*     */   private int mWidth;
/*     */   private int mHeight;
/*     */   private float mScale;
/*     */   private RectF mCutRectF;
/*  38 */   RectF mTextRect = null;
/*  39 */   private int mCutIndex = 0;
/*  40 */   private boolean isInit = false;
/*     */   private ClearAnimationListener mListener;
/*  44 */   private boolean mStopAnimation = false;
/*     */ 
/*  46 */   public void setStopAnimation(boolean paramBoolean) { this.mStopAnimation = paramBoolean; }
/*     */ 
/*     */   public FrameBitmapView(Activity paramActivity) {
/*  49 */     super(paramActivity);
/*     */ 
/*  52 */     this.mPaint = new Paint();
/*     */   }
/*     */ 
/*     */   public boolean isInit() {
/*  56 */     return this.isInit;
/*     */   }
/*     */ 
/*     */   protected void onMeasure(int paramInt1, int paramInt2)
/*     */   {
/*  62 */     setMeasuredDimension(this.mWidth, this.mHeight);
/*     */   }
/*     */ 
/*     */   public void injectionData(Object paramObject, String paramString, int paramInt1, int paramInt2, float paramFloat) {
/*  66 */     this.mWidth = paramInt1;
/*  67 */     this.mHeight = paramInt2;
/*  68 */     this.mTexts = paramString;
/*  69 */     this.mScale = paramFloat;
/*  70 */     this.mNativeBitmap = ((INativeBitmap)paramObject);
/*  71 */     this.isInit = true;
/*  72 */     initBitmapXY();
/*  73 */     initTextData();
/*  74 */     bringToFront();
/*  75 */     invalidate();
/*     */   }
/*     */ 
/*     */   public void configurationChanged(int paramInt1, int paramInt2)
/*     */   {
/*  85 */     if (this.mNativeBitmap != null) {
/*  86 */       this.mWidth = paramInt1;
/*  87 */       this.mHeight = paramInt2;
/*  88 */       initBitmapXY();
/*  89 */       initTextData();
/*  90 */       invalidate();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initBitmapXY()
/*     */   {
/*  96 */     float f1 = this.mWidth;
/*  97 */     float f2 = this.mHeight;
/*  98 */     if (this.mNativeBitmap.getBitmap() != null) {
/*  99 */       this.mBitmapCX = (f1 / 2.0F - this.mNativeBitmap.getBitmap().getWidth() / 2);
/* 100 */       this.mBitmapCY = (f2 / 2.0F - this.mNativeBitmap.getBitmap().getHeight() / 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initTextData()
/*     */   {
/* 110 */     if (!TextUtils.isEmpty(this.mTexts)) {
/*     */       try
/*     */       {
/* 113 */         JSONObject localJSONObject1 = new JSONObject(this.mTexts);
/* 114 */         this.mTextValue = localJSONObject1.optString("value", "");
/* 115 */         String str1 = "0px";
/* 116 */         String str2 = "0px";
/* 117 */         String str3 = this.mWidth + "px";
/* 118 */         String str4 = "44px";
/* 119 */         String str5 = "16px";
/* 120 */         String str6 = "#000000";
/* 121 */         String str7 = NORMAL;
/* 122 */         String str8 = NORMAL;
/* 123 */         String str9 = "";
/* 124 */         String str10 = "center";
/* 125 */         String str11 = "0px";
/*     */         JSONObject localJSONObject2;
/* 126 */         if (localJSONObject1.has("textRect")) {
/* 127 */           localJSONObject2 = localJSONObject1.getJSONObject("textRect");
/* 128 */           str1 = localJSONObject2.optString("top", str1);
/* 129 */           str2 = localJSONObject2.optString("left", str2);
/* 130 */           str3 = localJSONObject2.optString("width", str3);
/* 131 */           str4 = localJSONObject2.optString("height", str4);
/*     */         }
/* 133 */         if (localJSONObject1.has("textStyles")) {
/* 134 */           localJSONObject2 = localJSONObject1.getJSONObject("textStyles");
/* 135 */           str5 = localJSONObject2.optString("size", str5);
/* 136 */           str6 = localJSONObject2.optString("color", str6);
/* 137 */           str7 = localJSONObject2.optString("weight", str7);
/* 138 */           str8 = localJSONObject2.optString("style", str8);
/* 139 */           str9 = localJSONObject2.optString("family", str9);
/* 140 */           str10 = localJSONObject2.optString("align", str10);
/* 141 */           str11 = localJSONObject2.optString("margin", str11);
/*     */         }
/*     */ 
/* 144 */         int i = PdrUtil.convertToScreenInt(str2, this.mWidth, this.mWidth, this.mScale);
/* 145 */         int j = PdrUtil.convertToScreenInt(str1, this.mHeight, this.mHeight, this.mScale);
/* 146 */         int k = PdrUtil.convertToScreenInt(str3, this.mWidth, this.mWidth, this.mScale);
/* 147 */         int m = PdrUtil.convertToScreenInt(str4, this.mHeight, this.mHeight, this.mScale);
/* 148 */         int n = PdrUtil.convertToScreenInt(str5, this.mHeight, this.mHeight, this.mScale);
/* 149 */         int i1 = PdrUtil.convertToScreenInt(str11, k, m, this.mScale);
/* 150 */         this.mPaint.setTextSize(n);
/* 151 */         this.mPaint.setColor(PdrUtil.stringToColor(str6));
/* 152 */         if (!TextUtils.isEmpty(str9)) {
/* 153 */           Typeface localTypeface = Typeface.create(str9, 0);
/* 154 */           this.mPaint.setTypeface(localTypeface);
/*     */         }
/* 156 */         this.mTextRect = new RectF(i + i1, j + i1, k - i1, m - i1);
/* 157 */         this.mPaint.setFakeBoldText(str7.equals(BOLD));
/* 158 */         if (str8.equals(ITALIC)) {
/* 159 */           this.mPaint.setTextSkewX(-0.5F);
/*     */         }
/* 161 */         float f1 = getFontlength(this.mPaint, this.mTextValue);
/* 162 */         float f2 = getFontHeight(this.mPaint);
/*     */ 
/* 164 */         if (str10.equals("right")) {
/* 165 */           this.mFontCX = (this.mTextRect.right - f1);
/* 166 */           this.mFontCY = (this.mTextRect.top + (int)(this.mTextRect.height() - f2) / 2);
/* 167 */         } else if (str10.equals("left")) {
/* 168 */           this.mFontCX = this.mTextRect.left;
/* 169 */           this.mFontCY = (this.mTextRect.top + (int)(this.mTextRect.height() - f2) / 2);
/*     */         } else {
/* 171 */           this.mFontCX = (this.mTextRect.left + (int)(this.mTextRect.width() - f1) / 2);
/* 172 */           this.mFontCY = (this.mTextRect.top + (int)(this.mTextRect.height() - f2) / 2);
/*     */         }
/*     */       }
/*     */       catch (JSONException localJSONException) {
/* 176 */         localJSONException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/* 180 */     else if (this.mNativeBitmap == null)
/* 181 */       setVisibility(8);
/*     */   }
/*     */ 
/*     */   protected void onDraw(Canvas paramCanvas)
/*     */   {
/* 190 */     this.mPaint.setAntiAlias(true);
/* 191 */     paramCanvas.save();
/* 192 */     if (this.mCutRectF != null) {
/* 193 */       paramCanvas.clipRect(this.mCutRectF);
/*     */     }
/* 195 */     paramCanvas.restore();
/* 196 */     if ((this.mNativeBitmap != null) && (this.mNativeBitmap.getBitmap() != null)) {
/* 197 */       paramCanvas.save();
/* 198 */       paramCanvas.drawBitmap(this.mNativeBitmap.getBitmap(), this.mBitmapCX, this.mBitmapCY, this.mPaint);
/* 199 */       paramCanvas.restore();
/*     */     }
/*     */ 
/* 202 */     if (!TextUtils.isEmpty(this.mTextValue)) {
/* 203 */       paramCanvas.save();
/* 204 */       paramCanvas.clipRect(this.mTextRect);
/*     */ 
/* 206 */       CanvasHelper.drawString(paramCanvas, this.mTextValue, (int)this.mFontCX, (int)this.mFontCY, 17, this.mPaint);
/* 207 */       paramCanvas.restore();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clearData()
/*     */   {
/* 213 */     this.mNativeBitmap = null;
/* 214 */     this.mTextValue = null;
/* 215 */     this.mFontCX = 0.0F;
/* 216 */     this.mFontCY = 0.0F;
/* 217 */     this.mCutIndex = 0;
/* 218 */     this.mCutRectF = null;
/* 219 */     this.mListener = null;
/* 220 */     this.mStopAnimation = false;
/* 221 */     this.isInit = false;
/*     */   }
/*     */ 
/*     */   public static float getFontlength(Paint paramPaint, String paramString)
/*     */   {
/* 228 */     return paramPaint.measureText(paramString);
/*     */   }
/*     */ 
/*     */   public static float getFontHeight(Paint paramPaint)
/*     */   {
/* 235 */     Paint.FontMetrics localFontMetrics = paramPaint.getFontMetrics();
/* 236 */     return (int)Math.ceil(localFontMetrics.bottom - localFontMetrics.top);
/*     */   }
/*     */ 
/*     */   public static float getFontLeading(Paint paramPaint)
/*     */   {
/* 243 */     Paint.FontMetrics localFontMetrics = paramPaint.getFontMetrics();
/* 244 */     return localFontMetrics.leading - localFontMetrics.ascent;
/*     */   }
/*     */ 
/*     */   public void runClearAnimation(int paramInt1, int paramInt2, ClearAnimationListener paramClearAnimationListener)
/*     */   {
/* 258 */     this.mListener = paramClearAnimationListener;
/* 259 */     runClearAnimation(paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public void runClearAnimation(final int paramInt1, final int paramInt2)
/*     */   {
/* 268 */     postDelayed(new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/* 273 */         if (FrameBitmapView.this.mStopAnimation) {
/* 274 */           if (FrameBitmapView.this.mListener != null) {
/* 275 */             FrameBitmapView.this.mListener.onAnimationEnd();
/*     */           }
/* 277 */           return;
/*     */         }
/* 279 */         FrameBitmapView.access$208(FrameBitmapView.this);
/* 280 */         int i = FrameBitmapView.this.mNativeBitmap.getBitmap().getHeight();
/* 281 */         int j = FrameBitmapView.this.mNativeBitmap.getBitmap().getWidth();
/* 282 */         int k = i / paramInt1;
/* 283 */         FrameBitmapView.this.mCutRectF = new RectF(0.0F, k * FrameBitmapView.this.mCutIndex, j, i);
/* 284 */         FrameBitmapView.this.invalidate();
/* 285 */         if (FrameBitmapView.this.mCutIndex < paramInt1)
/* 286 */           FrameBitmapView.this.runClearAnimation(paramInt1, paramInt2);
/* 287 */         else if (FrameBitmapView.this.mListener != null)
/* 288 */           FrameBitmapView.this.mListener.onAnimationEnd();
/*     */       }
/*     */     }
/*     */     , paramInt2);
/*     */   }
/*     */ 
/*     */   public boolean onTouchEvent(MotionEvent paramMotionEvent)
/*     */   {
/* 298 */     return true;
/*     */   }
/*     */ 
/*     */   public static abstract interface ClearAnimationListener
/*     */   {
/*     */     public abstract void onAnimationEnd();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.FrameBitmapView
 * JD-Core Version:    0.6.2
 */