/*     */ package io.dcloud.feature.pdr;
/*     */ 
/*     */ import android.util.Log;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ public class b extends Logger
/*     */ {
/*     */   private static String a;
/*     */   private static File b;
/*  36 */   private static Boolean c = Boolean.valueOf(true);
/*     */ 
/*     */   public static void a(String paramString)
/*     */   {
/*  41 */     if (c.booleanValue()) {
/*  42 */       a = paramString;
/*  43 */       a();
/*  44 */       c = Boolean.valueOf(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void a(String paramString1, String paramString2)
/*     */   {
/*  55 */     Log.d(paramString1, paramString2);
/*  56 */     a(D, paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public static void b(String paramString1, String paramString2)
/*     */   {
/*  65 */     Log.e(paramString1, paramString2);
/*  66 */     a(E, paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public static void c(String paramString1, String paramString2)
/*     */   {
/*  79 */     Log.i(paramString1, paramString2);
/*  80 */     a(W, paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public static void f(String paramString1, String paramString2)
/*     */   {
/*  89 */     Log.i(paramString1, paramString2);
/*  90 */     a(I, paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public static void a()
/*     */   {
/* 101 */     StringBuffer localStringBuffer = new StringBuffer();
/* 102 */     localStringBuffer.append(a).append(File.separatorChar).append(generateTimeStamp(Boolean.valueOf(false))).append(".log");
/*     */ 
/* 104 */     File localFile = new File(a);
/* 105 */     b = new File(localStringBuffer.toString());
/* 106 */     if (!localFile.exists())
/* 107 */       localFile.mkdirs();
/*     */     else {
/* 109 */       deleteOldLog(localFile);
/*     */     }
/*     */ 
/* 112 */     if (!b.exists())
/*     */       try {
/* 114 */         b.createNewFile();
/*     */       } catch (IOException localIOException) {
/* 116 */         b = null;
/* 117 */         localIOException.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   private static void a(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 131 */     String str = generateLog(paramString1, paramString2, paramString3);
/* 132 */     if ((null != b) && (null != str)) {
/* 133 */       FileOutputStream localFileOutputStream = null;
/*     */       try {
/* 135 */         localFileOutputStream = new FileOutputStream(b, true);
/* 136 */         byte[] arrayOfByte = str.getBytes();
/* 137 */         localFileOutputStream.write(arrayOfByte);
/* 138 */         localFileOutputStream.flush();
/*     */       } catch (Exception localException) {
/* 140 */         localException.printStackTrace();
/*     */       } finally {
/*     */         try {
/* 143 */           if (localFileOutputStream != null)
/* 144 */             localFileOutputStream.close();
/*     */         }
/*     */         catch (IOException localIOException3) {
/* 147 */           localIOException3.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.iptv.tianjin\app\libs\nopermission.jar
 * Qualified Name:     io.dcloud.feature.pdr.b
 * JD-Core Version:    0.6.2
 */