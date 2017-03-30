/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ public class AESHelper
/*     */ {
/*     */   private static final String CipherMode = "AES/ECB/PKCS5Padding";
/*     */ 
/*     */   private static SecretKeySpec createKey(String paramString)
/*     */   {
/*  16 */     byte[] arrayOfByte = null;
/*  17 */     if (paramString == null) {
/*  18 */       paramString = "";
/*     */     }
/*  20 */     StringBuffer localStringBuffer = new StringBuffer(32);
/*  21 */     localStringBuffer.append(paramString);
/*  22 */     while (localStringBuffer.length() < 32) {
/*  23 */       localStringBuffer.append("0");
/*     */     }
/*  25 */     if (localStringBuffer.length() > 32) {
/*  26 */       localStringBuffer.setLength(32);
/*     */     }
/*     */     try
/*     */     {
/*  30 */       arrayOfByte = localStringBuffer.toString().getBytes("UTF-8");
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*  32 */       localUnsupportedEncodingException.printStackTrace();
/*     */     }
/*  34 */     return new SecretKeySpec(arrayOfByte, "AES");
/*     */   }
/*     */ 
/*     */   public static byte[] encrypt(byte[] paramArrayOfByte, String paramString)
/*     */   {
/*     */     try {
/*  40 */       SecretKeySpec localSecretKeySpec = createKey(paramString);
/*  41 */       System.out.println(localSecretKeySpec);
/*  42 */       Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
/*  43 */       localCipher.init(1, localSecretKeySpec);
/*  44 */       return localCipher.doFinal(paramArrayOfByte);
/*     */     }
/*     */     catch (Exception localException) {
/*  47 */       localException.printStackTrace();
/*     */     }
/*  49 */     return null;
/*     */   }
/*     */ 
/*     */   public static String encrypt(String paramString1, String paramString2)
/*     */   {
/*  54 */     byte[] arrayOfByte = null;
/*     */     try {
/*  56 */       arrayOfByte = paramString1.getBytes("UTF-8");
/*     */     } catch (Exception localException) {
/*  58 */       localException.printStackTrace();
/*     */     }
/*  60 */     arrayOfByte = encrypt(arrayOfByte, paramString2);
/*  61 */     String str = byte2hex(arrayOfByte);
/*  62 */     return str;
/*     */   }
/*     */ 
/*     */   public static byte[] decrypt(byte[] paramArrayOfByte, String paramString)
/*     */   {
/*     */     try {
/*  68 */       SecretKeySpec localSecretKeySpec = createKey(paramString);
/*  69 */       Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
/*  70 */       localCipher.init(2, localSecretKeySpec);
/*  71 */       return localCipher.doFinal(paramArrayOfByte);
/*     */     }
/*     */     catch (Exception localException) {
/*  74 */       localException.printStackTrace();
/*     */     }
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */   public static String decrypt(String paramString1, String paramString2)
/*     */   {
/*  81 */     byte[] arrayOfByte = null;
/*     */     try {
/*  83 */       arrayOfByte = hex2byte(paramString1);
/*     */     } catch (Exception localException) {
/*  85 */       localException.printStackTrace();
/*     */     }
/*  87 */     arrayOfByte = decrypt(arrayOfByte, paramString2);
/*  88 */     if (arrayOfByte == null)
/*  89 */       return null;
/*  90 */     String str = null;
/*     */     try {
/*  92 */       str = new String(arrayOfByte, "UTF-8");
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*  94 */       localUnsupportedEncodingException.printStackTrace();
/*     */     }
/*  96 */     return str;
/*     */   }
/*     */ 
/*     */   public static String byte2hex(byte[] paramArrayOfByte)
/*     */   {
/* 101 */     StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
/* 102 */     String str = "";
/* 103 */     for (int i = 0; i < paramArrayOfByte.length; i++)
/*     */     {
/* 105 */       str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
/* 106 */       if (str.length() == 1) {
/* 107 */         localStringBuffer.append("0");
/*     */       }
/* 109 */       localStringBuffer.append(str);
/*     */     }
/* 111 */     return localStringBuffer.toString().toUpperCase();
/*     */   }
/*     */ 
/*     */   private static byte[] hex2byte(String paramString)
/*     */   {
/* 116 */     if ((paramString == null) || (paramString.length() < 2)) {
/* 117 */       return new byte[0];
/*     */     }
/* 119 */     paramString = paramString.toLowerCase();
/* 120 */     int i = paramString.length() / 2;
/* 121 */     byte[] arrayOfByte = new byte[i];
/* 122 */     for (int j = 0; j < i; j++) {
/* 123 */       String str = paramString.substring(2 * j, 2 * j + 2);
/* 124 */       arrayOfByte[j] = ((byte)(Integer.parseInt(str, 16) & 0xFF));
/*     */     }
/* 126 */     return arrayOfByte;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.AESHelper
 * JD-Core Version:    0.6.2
 */