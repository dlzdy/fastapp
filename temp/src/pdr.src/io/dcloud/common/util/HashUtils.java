/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ 
/*    */ public class HashUtils
/*    */ {
/*  7 */   private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*    */ 
/*    */   public static String toHexString(byte[] paramArrayOfByte) {
/* 10 */     StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
/* 11 */     for (int i = 0; i < paramArrayOfByte.length; i++) {
/* 12 */       localStringBuilder.append(HEX_DIGITS[((paramArrayOfByte[i] & 0xF0) >>> 4)]);
/* 13 */       localStringBuilder.append(HEX_DIGITS[(paramArrayOfByte[i] & 0xF)]);
/*    */     }
/* 15 */     return localStringBuilder.toString();
/*    */   }
/*    */ 
/*    */   public static String getHash(String paramString)
/*    */   {
/*    */     try {
/* 21 */       MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/* 22 */       localMessageDigest.update(paramString.getBytes());
/*    */ 
/* 24 */       byte[] arrayOfByte = localMessageDigest.digest();
/*    */ 
/* 26 */       return toHexString(arrayOfByte);
/*    */     }
/*    */     catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
/*    */     {
/* 30 */       localNoSuchAlgorithmException.printStackTrace();
/*    */     }
/* 32 */     return paramString;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.HashUtils
 * JD-Core Version:    0.6.2
 */