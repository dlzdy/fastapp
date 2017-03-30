/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.os.Build.VERSION;
/*     */ import java.security.SecureRandom;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ 
/*     */ public class AESUtil
/*     */ {
/*     */   public static final String TAG = "AESUtils";
/*     */ 
/*     */   public static String encrypt(String paramString1, String paramString2)
/*     */   {
/*  28 */     byte[] arrayOfByte1 = null;
/*     */     try {
/*  30 */       byte[] arrayOfByte2 = getRawKey(paramString1.getBytes());
/*  31 */       arrayOfByte1 = encrypt(arrayOfByte2, paramString2.getBytes());
/*     */     } catch (Exception localException) {
/*  33 */       localException.printStackTrace();
/*     */     }
/*  35 */     return toHex(arrayOfByte1);
/*     */   }
/*     */ 
/*     */   public static byte[] encrypt(String paramString1, int paramInt, String paramString2, String paramString3) {
/*  39 */     byte[] arrayOfByte1 = null;
/*     */     try
/*     */     {
/*  42 */       byte[] arrayOfByte2 = paramString1.getBytes();
/*  43 */       arrayOfByte1 = encrypt(arrayOfByte2, paramString3.getBytes(), "AES/CBC/PKCS5Padding", paramString2 != null ? paramString2.getBytes() : null);
/*     */     } catch (Exception localException) {
/*  45 */       localException.printStackTrace();
/*     */     }
/*  47 */     return arrayOfByte1;
/*     */   }
/*     */ 
/*     */   public static String decrypt(String paramString1, String paramString2)
/*     */   {
/*     */     try
/*     */     {
/*  58 */       byte[] arrayOfByte1 = getRawKey(paramString1.getBytes());
/*  59 */       byte[] arrayOfByte2 = toByte(paramString2);
/*  60 */       byte[] arrayOfByte3 = decrypt(arrayOfByte1, arrayOfByte2);
/*  61 */       return new String(arrayOfByte3);
/*     */     }
/*     */     catch (Exception localException) {
/*  64 */       localException.printStackTrace();
/*  65 */     }return null;
/*     */   }
/*     */ 
/*     */   private static byte[] getRawKey(byte[] paramArrayOfByte)
/*     */     throws Exception
/*     */   {
/*  71 */     return getRawKey(paramArrayOfByte, 256);
/*     */   }
/*     */   @SuppressLint({"TrulyRandom"})
/*     */   private static byte[] getRawKey(byte[] paramArrayOfByte, int paramInt) throws Exception {
/*  75 */     KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
/*     */ 
/*  77 */     SecureRandom localSecureRandom = null;
/*  78 */     if (Build.VERSION.SDK_INT >= 17)
/*  79 */       localSecureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
/*     */     else {
/*  81 */       localSecureRandom = SecureRandom.getInstance("SHA1PRNG");
/*     */     }
/*  83 */     localSecureRandom.setSeed(paramArrayOfByte);
/*  84 */     localKeyGenerator.init(paramInt, localSecureRandom);
/*  85 */     SecretKey localSecretKey = localKeyGenerator.generateKey();
/*  86 */     byte[] arrayOfByte = localSecretKey.getEncoded();
/*  87 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   private static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString, byte[] paramArrayOfByte3) throws Exception {
/*  91 */     SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
/*     */ 
/*  93 */     Cipher localCipher = Cipher.getInstance(paramString);
/*  94 */     localCipher.init(1, localSecretKeySpec, new IvParameterSpec(paramArrayOfByte3 == null ? new byte[localCipher.getBlockSize()] : paramArrayOfByte3));
/*     */ 
/*  96 */     byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
/*  97 */     return arrayOfByte;
/*     */   }
/*     */   private static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws Exception {
/* 100 */     return encrypt(paramArrayOfByte1, paramArrayOfByte2, "AES/CBC/PKCS5Padding", null);
/*     */   }
/*     */ 
/*     */   private static byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws Exception
/*     */   {
/* 105 */     SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
/*     */ 
/* 107 */     Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 108 */     localCipher.init(2, localSecretKeySpec, new IvParameterSpec(new byte[localCipher.getBlockSize()]));
/*     */ 
/* 110 */     byte[] arrayOfByte = localCipher.doFinal(paramArrayOfByte2);
/* 111 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   private static byte[] toByte(String paramString) {
/* 115 */     int i = paramString.length() / 2;
/* 116 */     byte[] arrayOfByte = new byte[i];
/* 117 */     for (int j = 0; j < i; j++) {
/* 118 */       arrayOfByte[j] = Integer.valueOf(paramString.substring(2 * j, 2 * j + 2), 16).byteValue();
/*     */     }
/* 120 */     return arrayOfByte;
/*     */   }
/*     */ 
/*     */   private static String toHex(byte[] paramArrayOfByte) {
/* 124 */     if (paramArrayOfByte == null)
/* 125 */       return "";
/* 126 */     StringBuffer localStringBuffer = new StringBuffer(2 * paramArrayOfByte.length);
/* 127 */     for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 128 */       appendHex(localStringBuffer, paramArrayOfByte[i]);
/*     */     }
/* 130 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   private static void appendHex(StringBuffer paramStringBuffer, byte paramByte) {
/* 134 */     String str = "0123456789ABCDEF";
/* 135 */     paramStringBuffer.append("0123456789ABCDEF".charAt(paramByte >> 4 & 0xF)).append("0123456789ABCDEF".charAt(paramByte & 0xF));
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.AESUtil
 * JD-Core Version:    0.6.2
 */