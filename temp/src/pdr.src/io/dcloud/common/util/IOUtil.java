/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ public class IOUtil
/*     */ {
/*     */   static final int BUF_SIZE = 20480;
/*     */ 
/*     */   public static void close(InputStream paramInputStream)
/*     */   {
/*  31 */     if (paramInputStream != null)
/*     */       try {
/*  33 */         paramInputStream.close();
/*     */       } catch (IOException localIOException) {
/*  35 */         localIOException.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static byte[] getBytes(InputStream paramInputStream)
/*     */     throws IOException
/*     */   {
/*  47 */     ByteArrayOutputStream localByteArrayOutputStream = null;
/*  48 */     localByteArrayOutputStream = new ByteArrayOutputStream();
/*  49 */     byte[] arrayOfByte = new byte[20480];
/*     */     while (true) {
/*  51 */       int i = paramInputStream.read(arrayOfByte);
/*  52 */       if (i == -1) {
/*     */         break;
/*     */       }
/*  55 */       localByteArrayOutputStream.write(arrayOfByte, 0, i);
/*     */     }
/*     */ 
/*  58 */     arrayOfByte = localByteArrayOutputStream.toByteArray();
/*  59 */     close(localByteArrayOutputStream);
/*  60 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   public static void close(OutputStream paramOutputStream)
/*     */   {
/*  71 */     if (paramOutputStream != null)
/*     */       try {
/*  73 */         paramOutputStream.close();
/*     */       } catch (IOException localIOException) {
/*  75 */         localIOException.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   private static StringBuilder toStringBuffer(InputStream paramInputStream)
/*     */     throws IOException
/*     */   {
/*  82 */     if (null == paramInputStream) {
/*  83 */       return null;
/*     */     }
/*  85 */     BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
/*  86 */     StringBuilder localStringBuilder = new StringBuilder();
/*  87 */     String str = null;
/*  88 */     while ((str = localBufferedReader.readLine()) != null) {
/*  89 */       localStringBuilder.append(str).append("\n");
/*     */     }
/*  91 */     paramInputStream.close();
/*  92 */     return localStringBuilder;
/*     */   }
/*     */ 
/*     */   public static String toString(InputStream paramInputStream)
/*     */     throws IOException
/*     */   {
/* 102 */     if (null == paramInputStream) {
/* 103 */       return null;
/*     */     }
/* 105 */     return toStringBuffer(paramInputStream).toString();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.IOUtil
 * JD-Core Version:    0.6.2
 */