/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.text.TextUtils;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ 
/*     */ public class Md5Utils
/*     */ {
/*  17 */   private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*  18 */   private static final char[] HEX_LOWER_CASE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*     */   public static final String ALGORITHM = "MD5";
/*     */   public static final String DEFAULT_CHARSET = "UTF-8";
/*     */ 
/*     */   public static String md5LowerCase(String paramString)
/*     */   {
/*  23 */     if (TextUtils.isEmpty(paramString))
/*  24 */       return "";
/*     */     try
/*     */     {
/*  27 */       MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/*  28 */       byte[] arrayOfByte = paramString.getBytes("UTF-8");
/*  29 */       localMessageDigest.update(arrayOfByte);
/*  30 */       arrayOfByte = localMessageDigest.digest();
/*  31 */       paramString = toLowerCaseHex(arrayOfByte);
/*     */     } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*     */     } catch (Exception localException) {
/*     */     }
/*  36 */     return paramString;
/*     */   }
/*     */ 
/*     */   public static String md5(String paramString) {
/*     */     try {
/*  41 */       MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/*  42 */       byte[] arrayOfByte = paramString.getBytes("UTF-8");
/*  43 */       localMessageDigest.update(arrayOfByte);
/*     */ 
/*  45 */       arrayOfByte = localMessageDigest.digest();
/*  46 */       paramString = toHex(arrayOfByte);
/*     */     } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*     */     } catch (Exception localException) {
/*     */     }
/*  51 */     return paramString;
/*     */   }
/*     */ 
/*     */   public static String md5(byte[] paramArrayOfByte) {
/*     */     try {
/*  56 */       MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/*  57 */       localMessageDigest.update(paramArrayOfByte);
/*  58 */       byte[] arrayOfByte = localMessageDigest.digest();
/*  59 */       return toHex(arrayOfByte);
/*     */     } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
/*     */     }
/*  62 */     return "";
/*     */   }
/*     */ 
/*     */   public static String md5(File paramFile) {
/*  66 */     FileInputStream localFileInputStream = null;
/*  67 */     byte[] arrayOfByte = new byte[1024];
/*     */     try
/*     */     {
/*  72 */       localFileInputStream = new FileInputStream(paramFile);
/*  73 */       MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/*     */       int i;
/*  74 */       while ((i = localFileInputStream.read(arrayOfByte)) > 0) {
/*  75 */         localMessageDigest.update(arrayOfByte, 0, i);
/*     */       }
/*     */ 
/*  78 */       return toHex(localMessageDigest.digest());
/*     */     } catch (Exception localException) {
/*  80 */       localException.printStackTrace();
/*     */     } finally {
/*  82 */       if (localFileInputStream != null) {
/*     */         try {
/*  84 */           localFileInputStream.close();
/*     */         } catch (IOException localIOException3) {
/*  86 */           localIOException3.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */   private static String toHex(byte[] paramArrayOfByte) {
/*  95 */     StringBuilder localStringBuilder = new StringBuilder();
/*  96 */     for (int k : paramArrayOfByte) {
/*  97 */       localStringBuilder.append(HEX[((0xF0 & k) >> 4)]);
/*  98 */       localStringBuilder.append(HEX[(0xF & k)]);
/*     */     }
/* 100 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   private static String toLowerCaseHex(byte[] paramArrayOfByte) {
/* 104 */     StringBuilder localStringBuilder = new StringBuilder();
/* 105 */     for (int k : paramArrayOfByte) {
/* 106 */       localStringBuilder.append(HEX_LOWER_CASE[((0xF0 & k) >> 4)]);
/* 107 */       localStringBuilder.append(HEX_LOWER_CASE[(0xF & k)]);
/*     */     }
/* 109 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static boolean verifyFileMd5(String paramString1, String paramString2) {
/* 113 */     if (paramString1.length() > 0) {
/* 114 */       String str = md5(new File(paramString1));
/* 115 */       if ((str != null) && (paramString2 != null) && (0 == str.compareToIgnoreCase(paramString2)))
/* 116 */         return true;
/*     */     }
/* 118 */     return false;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.Md5Utils
 * JD-Core Version:    0.6.2
 */