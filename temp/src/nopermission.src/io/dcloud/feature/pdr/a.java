/*     */ package io.dcloud.feature.pdr;
/*     */ 
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Bitmap.CompressFormat;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.BitmapFactory.Options;
/*     */ import android.graphics.Matrix;
/*     */ import android.text.TextUtils;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.constant.DOMException;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.ThreadPool;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class a
/*     */ {
/*     */   public static void a(IWebview paramIWebview, final String[] paramArrayOfString)
/*     */   {
/*  38 */     ThreadPool.self().addThreadTask(new Runnable()
/*     */     {
/*     */       public void run()
/*     */       {
/*  43 */         a.b(this.a, paramArrayOfString);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public static synchronized void b(IWebview paramIWebview, String[] paramArrayOfString)
/*     */   {
/*  54 */     String str1 = paramArrayOfString[0];
/*  55 */     String str2 = paramArrayOfString[1];
/*  56 */     JSONObject localJSONObject = null;
/*     */     try {
/*  58 */       localJSONObject = new JSONObject(str1);
/*     */     }
/*     */     catch (JSONException localJSONException) {
/*  61 */       localJSONException.printStackTrace();
/*  62 */       a(paramIWebview, str2, "参数错误", -1);
/*  63 */       return;
/*     */     }
/*     */ 
/*  66 */     b localb = new b();
/*  67 */     if (!localb.a(localJSONObject, paramIWebview, str2)) {
/*  68 */       return;
/*     */     }
/*     */ 
/*  72 */     Bitmap localBitmap1 = null;
/*  73 */     BitmapFactory.Options localOptions = new BitmapFactory.Options();
/*  74 */     localOptions.inJustDecodeBounds = false;
/*     */ 
/*  79 */     if (localb.e < 0) {
/*  80 */       localb.e = 50;
/*     */     }
/*     */ 
/*  84 */     if (localb.m > 1500000L)
/*  85 */       localOptions.inSampleSize = 2;
/*     */     else {
/*  87 */       localOptions.inSampleSize = 1;
/*     */     }
/*  89 */     Logger.d("CompressImage", "文件获取完毕 初始化bitmap 获取文件大小" + localb.m);
/*     */     try
/*     */     {
/*  93 */       localBitmap1 = BitmapFactory.decodeFile(localb.a, localOptions);
/*     */     } catch (OutOfMemoryError localOutOfMemoryError) {
/*  95 */       Logger.d("CompressImage", "获取bitmap 内存溢出第一次  bitmap路径" + localb.a);
/*  96 */       localOptions.inSampleSize *= 2;
/*  97 */       Logger.d("CompressImage", "获取bitmap 内存溢出第一次 第二次获取bitmmap  bitmap路径" + localb.a);
/*  98 */       localBitmap1 = BitmapFactory.decodeFile(localb.a, localOptions);
/*     */     }
/*     */ 
/* 101 */     Bitmap localBitmap2 = null;
/*     */ 
/* 103 */     if (localb.l) {
/* 104 */       localMatrix = new Matrix();
/* 105 */       float f1 = localb.f / localBitmap1.getWidth();
/* 106 */       float f2 = localb.g / localBitmap1.getHeight();
/* 107 */       localMatrix.setScale(f1, f2);
/* 108 */       localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix, false);
/*     */     }
/*     */     else {
/* 111 */       localBitmap2 = localBitmap1;
/*     */     }
/* 113 */     Logger.d("CompressImage", "缩放完毕" + localb.a);
/* 114 */     Matrix localMatrix = null;
/*     */ 
/* 116 */     if (localb.h > 0) {
/* 117 */       localMatrix = new Matrix();
/* 118 */       localMatrix.postRotate(localb.h);
/*     */     }
/* 120 */     Logger.d("CompressImage", "旋转完毕" + localb.a);
/*     */ 
/* 122 */     if (localb.k != null) {
/* 123 */       i = (int)(localb.k.a / localOptions.inSampleSize);
/* 124 */       j = (int)(localb.k.b / localOptions.inSampleSize);
/* 125 */       int k = (int)(localb.k.c / localOptions.inSampleSize);
/* 126 */       int m = (int)(localb.k.d / localOptions.inSampleSize);
/*     */ 
/* 128 */       localBitmap2 = Bitmap.createBitmap(localBitmap2, j, i, k, m, localMatrix, false);
/*     */     }
/* 130 */     else if (localMatrix != null) {
/* 131 */       localBitmap2 = Bitmap.createBitmap(localBitmap2, 0, 0, localBitmap2.getWidth(), localBitmap2.getHeight(), localMatrix, false);
/*     */     }
/*     */ 
/* 134 */     Logger.d("CompressImage", "裁剪完毕" + localb.a);
/*     */ 
/* 136 */     int i = localBitmap2.getWidth();
/* 137 */     int j = localBitmap2.getHeight();
/* 138 */     long l = a(localb.b, localBitmap2, localb.d, localb.e);
/* 139 */     if (l > 0L) {
/* 140 */       Logger.d("CompressImage", "开始保存图片" + localb.a);
/* 141 */       String str3 = String.format("{path:'file://%s', w:%d, h:%d, size:%d}", new Object[] { localb.b, Integer.valueOf(i), Integer.valueOf(j), Long.valueOf(l) });
/* 142 */       a(paramIWebview, str2, str3);
/*     */     } else {
/* 144 */       if (!localBitmap2.isRecycled()) {
/* 145 */         localBitmap2.recycle();
/*     */       }
/* 147 */       a(paramIWebview, str2, "输出图片失败", -5);
/*     */     }
/* 149 */     if (!localBitmap1.isRecycled())
/* 150 */       localBitmap1.recycle();
/*     */   }
/*     */ 
/*     */   public static void a(IWebview paramIWebview, String paramString1, String paramString2, int paramInt)
/*     */   {
/* 331 */     String str = DOMException.toJSON(paramInt, paramString2);
/* 332 */     JSUtil.execCallback(paramIWebview, paramString1, str, JSUtil.ERROR, true, false);
/*     */   }
/*     */ 
/*     */   public static void a(IWebview paramIWebview, String paramString1, String paramString2) {
/* 336 */     JSUtil.execCallback(paramIWebview, paramString1, paramString2, JSUtil.OK, true, false);
/*     */   }
/*     */ 
/*     */   public static boolean a(String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 346 */       File localFile = new File(paramString);
/* 347 */       if ((!localFile.exists()) || (localFile.length() < 5L))
/* 348 */         return false;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 352 */       return false;
/*     */     }
/* 354 */     return true;
/*     */   }
/*     */ 
/*     */   public static Bitmap.CompressFormat b(String paramString)
/*     */   {
/* 363 */     if ((paramString.contains(".jpg")) || (paramString.contains(".jpeg"))) {
/* 364 */       return Bitmap.CompressFormat.JPEG;
/*     */     }
/* 366 */     return Bitmap.CompressFormat.PNG;
/*     */   }
/*     */ 
/*     */   public static long a(String paramString, Bitmap paramBitmap, boolean paramBoolean, int paramInt)
/*     */   {
/* 377 */     File localFile = new File(paramString);
/*     */ 
/* 379 */     if (localFile.exists()) {
/* 380 */       if (localFile.length() < 1L)
/* 381 */         localFile.delete();
/* 382 */       else if (paramBoolean)
/* 383 */         localFile.delete();
/*     */       else {
/* 385 */         return -1L;
/*     */       }
/*     */     }
/* 388 */     else if (c(paramString)) {
/* 389 */       localFile = new File(paramString);
/*     */     }
/*     */     try
/*     */     {
/* 393 */       FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
/* 394 */       paramBitmap.compress(b(paramString), paramInt, localFileOutputStream);
/* 395 */       localFileOutputStream.flush();
/* 396 */       localFileOutputStream.close();
/* 397 */       if (!paramBitmap.isRecycled())
/* 398 */         paramBitmap.recycle();
/*     */     }
/*     */     catch (FileNotFoundException localFileNotFoundException)
/*     */     {
/* 402 */       localFileNotFoundException.printStackTrace();
/* 403 */       return -1L;
/*     */     }
/*     */     catch (IOException localIOException) {
/* 406 */       localIOException.printStackTrace();
/* 407 */       return -1L;
/*     */     }
/* 409 */     return localFile.length();
/*     */   }
/*     */ 
/*     */   public static boolean c(String paramString)
/*     */   {
/* 418 */     if (TextUtils.isEmpty(paramString)) {
/* 419 */       return false;
/*     */     }
/* 421 */     int i = paramString.lastIndexOf("/");
/* 422 */     if ((i == -1) || (i == 0)) {
/* 423 */       return false;
/*     */     }
/*     */     try
/*     */     {
/* 427 */       String str = paramString.substring(0, i);
/* 428 */       File localFile = new File(str);
/* 429 */       if (!localFile.exists()) {
/* 430 */         localFile.mkdirs();
/*     */       }
/* 432 */       return true; } catch (Exception localException) {
/*     */     }
/* 434 */     return false;
/*     */   }
/*     */ 
/*     */   public static float a(String paramString, float paramFloat1, float paramFloat2)
/*     */   {
/* 447 */     if (paramString.equals("auto")) {
/* 448 */       return paramFloat2;
/*     */     }
/* 450 */     return PdrUtil.parseFloat(paramString, paramFloat1, paramFloat2);
/*     */   }
/*     */ 
/*     */   static class a
/*     */   {
/*     */     float a;
/*     */     float b;
/*     */     float c;
/*     */     float d;
/*     */     float e;
/*     */     float f;
/*     */ 
/*     */     public a(String paramString1, String paramString2, String paramString3, String paramString4, float paramFloat1, float paramFloat2)
/*     */     {
/* 298 */       this.e = paramFloat1;
/* 299 */       this.f = paramFloat2;
/* 300 */       this.a = a.a(paramString1, this.f, 0.0F);
/* 301 */       this.b = a.a(paramString2, this.e, 0.0F);
/* 302 */       this.c = a.a(paramString3, this.e, this.e - this.b);
/* 303 */       this.d = a.a(paramString4, this.f, this.f - this.a);
/* 304 */       if (this.c + this.b > this.e) {
/* 305 */         this.c = (this.e - this.b);
/*     */       }
/* 307 */       if (this.d + this.a > this.f)
/* 308 */         this.d = (this.f - this.a);
/*     */     }
/*     */ 
/*     */     public boolean a()
/*     */     {
/* 316 */       if ((this.a > this.f) || (this.b > this.e)) {
/* 317 */         return false;
/*     */       }
/* 319 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   static class b
/*     */   {
/*     */     String a;
/*     */     String b;
/*     */     String c;
/*     */     boolean d;
/*     */     int e;
/*     */     float f;
/*     */     float g;
/*     */     int h;
/*     */     int i;
/*     */     int j;
/*     */     a.a k;
/* 183 */     boolean l = false;
/*     */     long m;
/*     */ 
/*     */     public boolean a(JSONObject paramJSONObject, IWebview paramIWebview, String paramString)
/*     */     {
/* 194 */       this.a = paramJSONObject.optString("src");
/* 195 */       this.b = paramJSONObject.optString("dst");
/* 196 */       if (!a(paramIWebview, paramString)) {
/* 197 */         return false;
/*     */       }
/* 199 */       this.d = paramJSONObject.optBoolean("overwrite", false);
/* 200 */       this.c = paramJSONObject.optString("format");
/* 201 */       this.e = paramJSONObject.optInt("quality", -1);
/* 202 */       a(paramJSONObject.optString("width", "auto"), paramJSONObject.optString("height", "auto"));
/* 203 */       this.h = paramJSONObject.optInt("rotate", -1);
/*     */ 
/* 205 */       JSONObject localJSONObject = paramJSONObject.optJSONObject("clip");
/* 206 */       if (localJSONObject != null) {
/* 207 */         this.k = new a.a(localJSONObject.optString("top"), localJSONObject.optString("left"), localJSONObject.optString("width"), localJSONObject.optString("height"), this.f, this.g);
/*     */ 
/* 210 */         if (!this.k.a()) {
/* 211 */           a.a(paramIWebview, paramString, "参数错误", -1);
/* 212 */           return false;
/*     */         }
/*     */       }
/* 215 */       return true;
/*     */     }
/*     */ 
/*     */     public boolean a(IWebview paramIWebview, String paramString)
/*     */     {
/* 224 */       if ((!TextUtils.isEmpty(this.a)) && (!TextUtils.isEmpty(this.b))) {
/* 225 */         this.a = paramIWebview.obtainFrameView().obtainApp().convert2AbsFullPath(paramIWebview.obtainFullUrl(), this.a);
/* 226 */         if (!a.a(this.a)) {
/* 227 */           a.a(paramIWebview, paramString, "文件不存在", -4);
/* 228 */           return false;
/*     */         }
/* 230 */         this.b = paramIWebview.obtainFrameView().obtainApp().convert2AbsFullPath(paramIWebview.obtainFullUrl(), this.b);
/*     */       } else {
/* 232 */         a.a(paramIWebview, paramString, "参数错误", -1);
/* 233 */         return false;
/*     */       }
/* 235 */       return true;
/*     */     }
/*     */ 
/*     */     public void a(String paramString1, String paramString2)
/*     */     {
/* 245 */       BitmapFactory.Options localOptions = new BitmapFactory.Options();
/*     */ 
/* 247 */       localOptions.inJustDecodeBounds = true;
/* 248 */       Bitmap localBitmap = BitmapFactory.decodeFile(this.a, localOptions);
/* 249 */       localOptions.inJustDecodeBounds = false;
/*     */ 
/* 251 */       this.i = localOptions.outWidth;
/* 252 */       this.j = localOptions.outHeight;
/* 253 */       localBitmap = null;
/* 254 */       localOptions = null;
/* 255 */       File localFile = new File(this.a);
/* 256 */       this.m = localFile.length();
/* 257 */       localFile = null;
/* 258 */       if ((paramString1.equals("auto")) && (paramString2.endsWith("auto"))) {
/* 259 */         this.l = false;
/* 260 */         this.f = this.i;
/* 261 */         this.g = this.j;
/* 262 */       } else if (paramString1.equals("auto")) {
/* 263 */         this.l = true;
/* 264 */         this.g = PdrUtil.parseFloat(paramString2, this.j, this.j);
/* 265 */         this.f = (this.i * this.g / this.j);
/* 266 */       } else if (paramString2.equals("auto")) {
/* 267 */         this.l = true;
/* 268 */         this.f = PdrUtil.parseFloat(paramString1, this.i, this.i);
/* 269 */         this.g = (this.j * this.f / this.i);
/*     */       } else {
/* 271 */         this.l = true;
/* 272 */         this.f = PdrUtil.parseFloat(paramString1, this.i, this.i);
/* 273 */         this.g = PdrUtil.parseFloat(paramString2, this.j, this.j);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.iptv.tianjin\app\libs\nopermission.jar
 * Qualified Name:     io.dcloud.feature.pdr.a
 * JD-Core Version:    0.6.2
 */