/*     */ package io.dcloud.feature.internal.splash;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Paint;
/*     */ import android.graphics.Paint.Style;
/*     */ import android.graphics.Path;
/*     */ import android.graphics.Path.Direction;
/*     */ import android.graphics.RectF;
/*     */ import android.os.Build.VERSION;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.util.TypedValue;
/*     */ import android.view.View;
/*     */ 
/*     */ public class b extends View
/*     */ {
/*     */   private DisplayMetrics C;
/*  21 */   Bitmap a = null;
/*     */   int b;
/*     */   int c;
/*     */   int d;
/*     */   int e;
/*     */   int f;
/*     */   int g;
/*     */   int h;
/*     */   int i;
/*     */   int j;
/*     */   int k;
/*     */   int l;
/*     */   int m;
/*     */   int n;
/*     */   int o;
/*     */   int p;
/*     */   int q;
/*     */   int r;
/*     */   int s;
/*     */   int t;
/*     */   int u;
/*     */   int v;
/*     */   int w;
/*     */   RectF x;
/*     */   RectF y;
/*  29 */   Paint z = new Paint();
/*  30 */   Path A = new Path();
/*  31 */   int B = -1;
/*     */ 
/*     */   public b(Context paramContext) {
/*  34 */     super(paramContext);
/*     */   }
/*     */ 
/*     */   public void setBackgroundColor(int paramInt) {
/*  38 */     super.setBackgroundColor(paramInt);
/*  39 */     this.B = paramInt;
/*     */   }
/*     */ 
/*     */   float a(float paramFloat)
/*     */   {
/*  47 */     if (null == this.C) {
/*  48 */       this.C = getResources().getDisplayMetrics();
/*     */     }
/*  50 */     return TypedValue.applyDimension(1, paramFloat, this.C);
/*     */   }
/*     */ 
/*     */   public void a(Bitmap paramBitmap) {
/*  54 */     if ((this.a != null) && (!this.a.isRecycled())) {
/*  55 */       this.a.recycle();
/*     */     }
/*  57 */     this.a = paramBitmap;
/*     */   }
/*     */ 
/*     */   public void a(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
/*  61 */     int i1 = getLeft();
/*  62 */     int i2 = getTop();
/*  63 */     this.a = paramBitmap;
/*  64 */     this.b = paramInt1;
/*  65 */     this.c = paramInt2;
/*  66 */     this.f = paramInt3;
/*     */ 
/*  68 */     this.d = (this.b - this.f * 8);
/*  69 */     this.e = (this.c - this.f * 8);
/*  70 */     this.k = (i1 + (this.b - this.d) / 2);
/*  71 */     this.l = (i2 + (this.c - this.e) / 2);
/*  72 */     this.x = new RectF(this.k, this.l, this.k + this.d, this.l + this.e);
/*  73 */     this.A.reset();
/*  74 */     this.A.addRoundRect(this.x, this.k + this.d / 2, this.l + this.e / 2, Path.Direction.CCW);
/*     */ 
/*  77 */     this.j = (this.b / 2 - this.f);
/*  78 */     this.v = paramInt5;
/*  79 */     this.w = paramInt4;
/*  80 */     this.h = (i1 + this.b / 2);
/*  81 */     this.i = (i2 + this.c / 2);
/*     */ 
/*  83 */     this.u = (this.s = 270);
/*  84 */     this.g = this.f;
/*  85 */     int i3 = (this.g - this.f) / 2;
/*  86 */     this.m = (i1 + this.f - i3);
/*  87 */     this.n = (i2 + this.f - i3);
/*  88 */     this.q = (this.b - this.f * 2 + i3 * 2);
/*  89 */     this.r = (this.c - this.f * 2 + i3 * 2);
/*  90 */     this.o = (this.m + this.q);
/*  91 */     this.p = (this.n + this.r);
/*  92 */     this.y = new RectF(this.m, this.n, this.o, this.p);
/*     */   }
/*     */ 
/*     */   protected void onMeasure(int paramInt1, int paramInt2)
/*     */   {
/*  99 */     super.onMeasure(paramInt1, paramInt2);
/* 100 */     setMeasuredDimension(this.b, this.c);
/*     */   }
/*     */ 
/*     */   protected void onDraw(Canvas paramCanvas)
/*     */   {
/* 105 */     b(paramCanvas);
/* 106 */     c(paramCanvas);
/* 107 */     a(paramCanvas);
/*     */   }
/*     */ 
/*     */   private void a(Canvas paramCanvas) {
/* 111 */     this.z.reset();
/* 112 */     this.z.setAntiAlias(true);
/* 113 */     this.z.setStyle(Paint.Style.STROKE);
/* 114 */     this.z.setStrokeWidth(this.g);
/* 115 */     this.z.setColor(this.v);
/* 116 */     paramCanvas.drawArc(this.y, this.s, this.t, false, this.z);
/* 117 */     this.t = ((int)(this.t + 2.0F));
/* 118 */     if (this.t > 360) {
/* 119 */       this.t -= 360;
/*     */     }
/* 121 */     invalidate();
/*     */   }
/*     */ 
/*     */   private void b(Canvas paramCanvas) {
/* 125 */     this.z.reset();
/* 126 */     if ((this.a != null) && (!this.a.isRecycled())) {
/* 127 */       paramCanvas.save();
/* 128 */       paramCanvas.clipPath(this.A);
/* 129 */       this.z.setAntiAlias(true);
/* 130 */       paramCanvas.drawBitmap(this.a, null, this.x, this.z);
/* 131 */       paramCanvas.restore();
/* 132 */       int i1 = Build.VERSION.SDK_INT > 19 ? 3 : 40;
/* 133 */       int i2 = this.f * 4 + i1;
/* 134 */       this.z.setStrokeWidth(i2);
/* 135 */       this.z.setStyle(Paint.Style.STROKE);
/* 136 */       this.z.setAntiAlias(true);
/* 137 */       this.z.setColor(this.B);
/* 138 */       paramCanvas.drawCircle(this.x.left + this.x.width() / 2.0F, this.x.top + this.x.height() / 2.0F, this.x.width() / 2.0F + i2 / 2 - i1 / 8, this.z);
/*     */     } else {
/* 140 */       this.z.setColor(-1118482);
/* 141 */       this.z.setAntiAlias(true);
/* 142 */       this.z.setStyle(Paint.Style.FILL);
/* 143 */       paramCanvas.drawCircle(this.x.left + this.x.width() / 2.0F, this.x.top + this.x.height() / 2.0F, this.x.width() / 2.0F, this.z);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void c(Canvas paramCanvas) {
/* 148 */     this.z.reset();
/* 149 */     this.z.setStrokeWidth(this.f);
/* 150 */     this.z.setStyle(Paint.Style.STROKE);
/* 151 */     this.z.setAntiAlias(true);
/* 152 */     this.z.setColor(this.w);
/* 153 */     paramCanvas.drawCircle(this.h, this.i, this.j, this.z);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.feature.internal.splash.b
 * JD-Core Version:    0.6.2
 */