/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ public final class Base64
/*     */ {
/*  18 */   private static final char[] BASE64CHARS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
/*     */   private static final char PAD = '=';
/*     */   private static final String CRLF = "\r\n";
/*     */ 
/*     */   public static String encode(String paramString)
/*     */   {
/*     */     try
/*     */     {
/*  33 */       return encode(paramString.getBytes("utf-8"));
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*     */     }
/*  36 */     return null;
/*     */   }
/*     */ 
/*     */   public static String encode(byte[] paramArrayOfByte)
/*     */   {
/*  42 */     StringBuffer localStringBuffer = new StringBuffer();
/*  43 */     byte[] arrayOfByte = paramArrayOfByte;
/*     */ 
/*  46 */     for (int j = 0; j < arrayOfByte.length; j += 3) {
/*  47 */       int i = 0;
/*     */ 
/*  49 */       if ((j % 57 == 0) && (j != 0))
/*  50 */         localStringBuffer.append("\r\n");
/*  51 */       if (arrayOfByte.length <= j + 1) {
/*  52 */         i = eightbit(arrayOfByte[j]) << 16;
/*  53 */         localStringBuffer.append(BASE64CHARS[sixbit(i >> 18)]);
/*  54 */         localStringBuffer.append(BASE64CHARS[sixbit(i >> 12)]);
/*  55 */         localStringBuffer.append('=');
/*  56 */         localStringBuffer.append('=');
/*     */       }
/*  58 */       else if (arrayOfByte.length <= j + 2) {
/*  59 */         i = eightbit(arrayOfByte[j]) << 16 | eightbit(arrayOfByte[(j + 1)]) << 8;
/*  60 */         localStringBuffer.append(BASE64CHARS[sixbit(i >> 18)]);
/*  61 */         localStringBuffer.append(BASE64CHARS[sixbit(i >> 12)]);
/*  62 */         localStringBuffer.append(BASE64CHARS[sixbit(i >> 6)]);
/*  63 */         localStringBuffer.append('=');
/*     */       }
/*     */       else {
/*  66 */         i = eightbit(arrayOfByte[j]) << 16 | eightbit(arrayOfByte[(j + 1)]) << 8 | eightbit(arrayOfByte[(j + 2)]);
/*  67 */         localStringBuffer.append(BASE64CHARS[sixbit(i >> 18)]);
/*  68 */         localStringBuffer.append(BASE64CHARS[sixbit(i >> 12)]);
/*  69 */         localStringBuffer.append(BASE64CHARS[sixbit(i >> 6)]);
/*  70 */         localStringBuffer.append(BASE64CHARS[sixbit(i)]);
/*     */       }
/*     */     }
/*  73 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String decode2String(String paramString)
/*     */   {
/*  78 */     byte[] arrayOfByte = decode2bytes(paramString);
/*     */     try {
/*  80 */       return new String(arrayOfByte, "utf-8");
/*     */     }
/*     */     catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*  83 */       localUnsupportedEncodingException.printStackTrace();
/*  84 */       return "";
/*     */     }
/*     */     catch (RuntimeException localRuntimeException)
/*     */     {
/*  88 */       localRuntimeException.printStackTrace();
/*  89 */       return "";
/*     */     }
/*     */     catch (Throwable localThrowable) {
/*  92 */       localThrowable.printStackTrace();
/*  93 */     }return "";
/*     */   }
/*     */ 
/*     */   public static byte[] decode2bytes(String paramString)
/*     */   {
/* 100 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/*     */     try
/*     */     {
/* 103 */       byte[] arrayOfByte1 = paramString.getBytes();
/* 104 */       int i = 0;
/* 105 */       int j = 0;
/* 106 */       int k = 0;
/* 107 */       for (int m = 0; m < arrayOfByte1.length; m++) {
/* 108 */         if (k < 4) {
/* 109 */           i = decodeInt(arrayOfByte1[m]);
/* 110 */           if (i != -1)
/*     */           {
/*     */             byte[] arrayOfByte4;
/* 112 */             if ((i == -2) && (k != 2) && (k != 3))
/* 113 */               return null;
/* 114 */             if ((i == -2) && (k == 2))
/*     */             {
/* 117 */               localByteArrayOutputStream.write(eightbit(j >> 4));
/* 118 */               return localByteArrayOutputStream.toByteArray();
/*     */             }
/* 120 */             if ((i == -2) && (k == 3))
/*     */             {
/* 124 */               localByteArrayOutputStream.write(eightbit(j >> 10));
/* 125 */               localByteArrayOutputStream.write(eightbit(j >> 2));
/* 126 */               return localByteArrayOutputStream.toByteArray();
/*     */             }
/*     */ 
/* 129 */             j = j << 6 | sixbit(i);
/* 130 */             k++;
/*     */           }
/*     */         }
/* 133 */         else if (k == 4)
/*     */         {
/* 137 */           localByteArrayOutputStream.write(eightbit(j >> 16));
/* 138 */           localByteArrayOutputStream.write(eightbit(j >> 8));
/* 139 */           localByteArrayOutputStream.write(eightbit(j));
/*     */ 
/* 141 */           j = 0;
/* 142 */           k = 0;
/*     */         }
/*     */       }
/*     */ 
/* 146 */       return localByteArrayOutputStream.toByteArray();
/*     */     } catch (Exception localException) {
/* 148 */       return null;
/*     */     } finally {
/* 150 */       if (localByteArrayOutputStream != null)
/*     */         try {
/* 152 */           localByteArrayOutputStream.close();
/*     */         } catch (IOException localIOException6) {
/* 154 */           localIOException6.printStackTrace();
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static int decodeInt(int paramInt)
/*     */   {
/* 162 */     if ((paramInt >= 65) && (paramInt <= 90))
/* 163 */       return paramInt - 65;
/* 164 */     if ((paramInt >= 97) && (paramInt <= 122))
/* 165 */       return paramInt - 97 + 26;
/* 166 */     if ((paramInt >= 48) && (paramInt <= 57))
/* 167 */       return paramInt - 48 + 52;
/* 168 */     if (paramInt == 43)
/* 169 */       return 62;
/* 170 */     if (paramInt == 47)
/* 171 */       return 63;
/* 172 */     if (paramInt == 61)
/* 173 */       return -2;
/* 174 */     return -1;
/*     */   }
/*     */ 
/*     */   private static int sixbit(int paramInt) {
/* 178 */     return paramInt & 0x3F;
/*     */   }
/*     */ 
/*     */   private static int eightbit(int paramInt) {
/* 182 */     return paramInt & 0xFF;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.Base64
 * JD-Core Version:    0.6.2
 */