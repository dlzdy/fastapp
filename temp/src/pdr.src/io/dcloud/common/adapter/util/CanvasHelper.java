/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Bitmap.Config;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Paint.Align;
/*     */ import android.graphics.Paint.FontMetrics;
/*     */ import android.graphics.drawable.BitmapDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.util.DisplayMetrics;
/*     */ import io.dcloud.common.util.IOUtil;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public class CanvasHelper
/*     */ {
/*  19 */   private static BitmapDrawable sDrawable = null;
/*     */   public static final int BASELINE = 0;
/*     */   public static final int BOTTOM = 80;
/*     */   public static final int HCENTER = 1;
/*     */   public static final int LEFT = 3;
/*     */   public static final int RIGHT = 5;
/*     */   public static final int TOP = 48;
/*     */   public static final int VCENTER = 16;
/*     */   private static final int DEVIANT = 5;
/*     */ 
/*     */   public static Drawable getDrawable()
/*     */   {
/*  21 */     if (sDrawable == null) {
/*  22 */       Bitmap localBitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.RGB_565);
/*  23 */       Canvas localCanvas = new Canvas(localBitmap);
/*  24 */       localCanvas.drawColor(0);
/*  25 */       sDrawable = new BitmapDrawable(localBitmap);
/*     */     }
/*     */ 
/*  28 */     return sDrawable;
/*     */   }
/*     */ 
/*     */   public static void clearData() {
/*  32 */     if (sDrawable != null) {
/*  33 */       sDrawable.getBitmap().recycle();
/*  34 */       sDrawable = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Bitmap getBitmap(String paramString)
/*     */   {
/*  40 */     Bitmap localBitmap = null;
/*     */     try {
/*  42 */       InputStream localInputStream = PlatformUtil.getInputStream(paramString);
/*  43 */       localBitmap = BitmapFactory.decodeStream(localInputStream);
/*  44 */       IOUtil.close(localInputStream);
/*     */     } catch (Exception localException) {
/*  46 */       localException.printStackTrace();
/*     */     }
/*  48 */     return localBitmap;
/*     */   }
/*     */ 
/*     */   public static Drawable getDrawable(Context paramContext, String paramString) {
/*  52 */     Bitmap localBitmap = getBitmap(paramString);
/*  53 */     if (localBitmap == null) return null;
/*  54 */     return new BitmapDrawable(paramContext.getResources(), localBitmap);
/*     */   }
/*     */ 
/*     */   public static Drawable getDrawable(String paramString) {
/*  58 */     Bitmap localBitmap = getBitmap(paramString);
/*  59 */     if (localBitmap == null) return null;
/*  60 */     return new BitmapDrawable(localBitmap);
/*     */   }
/*     */ 
/*     */   public static void drawNinePatchs(Canvas paramCanvas, Bitmap paramBitmap, int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  64 */     Paint localPaint = new Paint();
/*  65 */     int i = paramBitmap.getWidth();
/*  66 */     int j = paramBitmap.getHeight();
/*     */     int i3;
/*     */     int i2;
/*     */     int i1;
/*     */     int n;
/*     */     int m;
/*  69 */     int k = m = n = i1 = i2 = i3 = 0;
/*  70 */     int i4 = 1;
/*  71 */     while (i4 <= 9) {
/*  72 */       int i5 = 0; int i6 = 0; int i7 = 0; int i8 = 0;
/*  73 */       if (i4 == 1) {
/*  74 */         i7 = paramArrayOfInt[0]; i8 = paramArrayOfInt[1];
/*  75 */         k = paramInt1; m = paramInt2; n = k + i7; i1 = m + i8;
/*  76 */         i2 = paramInt1; i3 = m;
/*  77 */         i5 = i7; i6 = i8;
/*  78 */       } else if (i4 == 2) {
/*  79 */         i7 = i - paramArrayOfInt[0] - paramArrayOfInt[2]; i8 = paramArrayOfInt[1];
/*  80 */         k = paramInt1 + paramArrayOfInt[0]; m = paramInt2; n = k + i7; i1 = m + i8;
/*  81 */         i2 = paramInt1; i3 = m;
/*  82 */         i5 = paramInt3 - paramArrayOfInt[0] - paramArrayOfInt[3]; i6 = i8;
/*  83 */       } else if (i4 == 3) {
/*  84 */         i7 = paramArrayOfInt[2]; i8 = paramArrayOfInt[1];
/*  85 */         k = paramInt1 + paramInt3 - i7; m = paramInt2; n = paramInt1 + paramInt3; i1 = m + i8;
/*  86 */         i2 = n - i; i3 = m;
/*  87 */         i5 = i7; i6 = i8;
/*  88 */       } else if (i4 == 4) {
/*  89 */         i7 = paramArrayOfInt[0]; i8 = j - paramArrayOfInt[1] - paramArrayOfInt[3];
/*  90 */         k = paramInt1; m = paramInt2 + paramArrayOfInt[1]; n = k + i7; i1 = m + i8;
/*  91 */         i2 = paramInt1; i3 = paramInt2;
/*  92 */         i5 = i7; i6 = paramInt4 - paramArrayOfInt[1] - paramArrayOfInt[3];
/*  93 */       } else if (i4 == 5) {
/*  94 */         i7 = i - paramArrayOfInt[0] - paramArrayOfInt[2]; i8 = j - paramArrayOfInt[1] - paramArrayOfInt[3];
/*  95 */         k = paramInt1 + paramArrayOfInt[0]; m = paramInt2 + paramArrayOfInt[1]; n = k + i7; i1 = m + i8;
/*  96 */         i2 = paramInt1; i3 = paramInt2;
/*  97 */         i5 = paramInt3 - paramArrayOfInt[0] - paramArrayOfInt[2]; i6 = paramInt4 - paramArrayOfInt[1] - paramArrayOfInt[3];
/*  98 */       } else if (i4 == 6) {
/*  99 */         i7 = paramArrayOfInt[2]; i8 = j - paramArrayOfInt[1] - paramArrayOfInt[3];
/* 100 */         k = paramInt1 + paramInt3 - i7; m = paramInt2 + paramArrayOfInt[1]; n = paramInt1 + paramInt3; i1 = m + i8;
/* 101 */         i2 = k - (i - i7); i3 = paramInt2;
/* 102 */         i5 = i7; i6 = paramInt4 - paramArrayOfInt[1] - paramArrayOfInt[3];
/* 103 */       } else if (i4 == 7) {
/* 104 */         i7 = paramArrayOfInt[0]; i8 = paramArrayOfInt[3];
/* 105 */         k = paramInt1; m = paramInt2 + paramInt4 - i8; n = k + i7; i1 = m + i8;
/* 106 */         i2 = paramInt1; i3 = m - j + i8;
/* 107 */         i5 = i7; i6 = i8;
/* 108 */       } else if (i4 == 8) {
/* 109 */         i7 = i - paramArrayOfInt[0] - paramArrayOfInt[2]; i8 = paramArrayOfInt[3];
/* 110 */         k = paramInt1 + paramArrayOfInt[0]; m = paramInt2 + paramInt4 - i8; n = k + i7; i1 = m + i8;
/* 111 */         i2 = paramInt1; i3 = m - j + i8;
/* 112 */         i5 = paramInt3 - paramArrayOfInt[0] - paramArrayOfInt[2]; i6 = paramArrayOfInt[3];
/* 113 */       } else if (i4 == 9) {
/* 114 */         i7 = paramArrayOfInt[2]; i8 = paramArrayOfInt[3];
/* 115 */         k = paramInt1 + paramInt3 - i7; m = paramInt2 + paramInt4 - i8; n = paramInt1 + paramInt3; i1 = m + i8;
/* 116 */         i2 = n - i; i3 = m - j + i8;
/* 117 */         i5 = i7; i6 = i8;
/*     */       }
/* 119 */       int i9 = i6 / i8 + (i6 % i8 > 0 ? 1 : 0);
/* 120 */       int i10 = i5 / i7 + (i5 % i7 > 0 ? 1 : 0);
/* 121 */       for (int i11 = 0; i11 < i9; i11++) {
/* 122 */         for (int i12 = 0; i12 < i10; i12++) {
/* 123 */           int i13 = n + i12 * i7; if (i13 > i5 + k) i13 = i5 + k;
/* 124 */           int i14 = i1 + i11 * i8; if (i14 > i6 + m) i14 = i6 + m;
/* 125 */           drawClipBitmap(paramCanvas, paramBitmap, localPaint, k + i12 * i7, m + i11 * i8, i13, i14, i2 + i12 * i7, i3 + i11 * i8);
/*     */         }
/*     */       }
/* 128 */       i4++;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void drawClipBitmap(Canvas paramCanvas, Bitmap paramBitmap, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 134 */     paramCanvas.save();
/* 135 */     paramCanvas.clipRect(paramInt1, paramInt2, paramInt3, paramInt4);
/* 136 */     paramCanvas.drawBitmap(paramBitmap, paramInt5, paramInt6, paramPaint);
/* 137 */     paramCanvas.restore();
/*     */   }
/*     */ 
/*     */   public static void drawString(Canvas paramCanvas, String paramString, int paramInt1, int paramInt2, int paramInt3, Paint paramPaint)
/*     */   {
/* 165 */     if (paramCanvas == null) {
/* 166 */       return;
/*     */     }
/* 168 */     Canvas localCanvas = paramCanvas;
/* 169 */     if (paramPaint == null) {
/* 170 */       return;
/*     */     }
/* 172 */     Paint localPaint = paramPaint;
/* 173 */     int i = (int)localPaint.getTextSize();
/*     */ 
/* 175 */     if ((paramInt3 & 0x3) == 3)
/* 176 */       localPaint.setTextAlign(Paint.Align.LEFT);
/* 177 */     else if ((paramInt3 & 0x5) == 5)
/* 178 */       localPaint.setTextAlign(Paint.Align.RIGHT);
/* 179 */     else if ((paramInt3 & 0x1) != 1)
/*     */     {
/* 182 */       localPaint.setTextAlign(Paint.Align.LEFT);
/*     */     }
/*     */ 
/* 185 */     if ((paramInt3 & 0x30) == 48) {
/* 186 */       paramInt2 = paramInt2 + i - i / 5;
/* 187 */     } else if ((paramInt3 & 0x50) == 80) {
/* 188 */       Paint.FontMetrics localFontMetrics = localPaint.getFontMetrics();
/* 189 */       paramInt2 -= (int)(localFontMetrics.descent / 2.0F);
/* 190 */     } else if ((paramInt3 & 0x10) == 16) {
/* 191 */       paramInt2 = paramInt2 + i >> 0;
/*     */     } else {
/* 193 */       paramInt2 = paramInt2 + i - i / 5;
/*     */     }
/* 195 */     if (paramString != null)
/* 196 */       localCanvas.drawText(paramString, paramInt1, paramInt2, localPaint);
/*     */   }
/*     */ 
/*     */   public static int getFontHeight(Paint paramPaint)
/*     */   {
/* 203 */     Paint.FontMetrics localFontMetrics = paramPaint.getFontMetrics();
/* 204 */     return (int)Math.ceil(localFontMetrics.descent - localFontMetrics.top) + 2;
/*     */   }
/*     */ 
/*     */   public static float getViablePx(int paramInt) {
/* 208 */     return paramInt * DeviceInfo.sDensity;
/*     */   }
/*     */ 
/*     */   public static int dip2px(Context paramContext, float paramFloat)
/*     */   {
/* 215 */     float f = paramContext.getResources().getDisplayMetrics().density;
/* 216 */     return (int)(paramFloat * f + 0.5F);
/*     */   }
/*     */ 
/*     */   public static int px2dip(Context paramContext, float paramFloat)
/*     */   {
/* 223 */     float f = paramContext.getResources().getDisplayMetrics().density;
/* 224 */     return (int)(paramFloat / f + 0.5F);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.CanvasHelper
 * JD-Core Version:    0.6.2
 */