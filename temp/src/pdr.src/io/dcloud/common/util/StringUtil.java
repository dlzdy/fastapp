/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class StringUtil
/*     */ {
/*     */   public static String trimString(String paramString, char paramChar)
/*     */   {
/*  18 */     String str = paramString;
/*  19 */     if ((str != null) && (!str.equals(""))) {
/*  20 */       int i = str.charAt(0) == paramChar ? 1 : 0;
/*  21 */       int j = str.length();
/*  22 */       int k = str.charAt(j - 1) == paramChar ? j - 1 : j;
/*  23 */       str = str.substring(i, k);
/*     */     }
/*  25 */     return str;
/*     */   }
/*     */ 
/*     */   public static String[] trimString(String[] paramArrayOfString, char paramChar)
/*     */   {
/*  37 */     String[] arrayOfString = paramArrayOfString;
/*  38 */     for (int i = 0; i < paramArrayOfString.length; i++) {
/*  39 */       paramArrayOfString[i] = trimString(paramArrayOfString[i], paramChar);
/*     */     }
/*  41 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public static String convert(String paramString)
/*     */   {
/*  52 */     paramString = paramString == null ? "" : paramString;
/*     */ 
/*  54 */     StringBuffer localStringBuffer = new StringBuffer(1000);
/*     */ 
/*  57 */     localStringBuffer.setLength(0);
/*  58 */     for (int j = 0; j < paramString.length(); j++) {
/*  59 */       int i = paramString.charAt(j);
/*  60 */       localStringBuffer.append("\\u");
/*  61 */       int k = i >>> 8;
/*  62 */       String str = Integer.toHexString(k);
/*  63 */       if (str.length() == 1)
/*  64 */         localStringBuffer.append("0");
/*  65 */       localStringBuffer.append(str);
/*  66 */       k = i & 0xFF;
/*  67 */       str = Integer.toHexString(k);
/*  68 */       if (str.length() == 1)
/*  69 */         localStringBuffer.append("0");
/*  70 */       localStringBuffer.append(str);
/*     */     }
/*  72 */     return new String(localStringBuffer);
/*     */   }
/*     */ 
/*     */   public static String revert(String paramString)
/*     */   {
/*  83 */     paramString = paramString == null ? "" : paramString;
/*  84 */     if (paramString.indexOf("\\u") == -1)
/*  85 */       return paramString;
/*  86 */     StringBuffer localStringBuffer = new StringBuffer(1000);
/*  87 */     for (int i = 0; i < paramString.length(); ) {
/*  88 */       if (paramString.substring(i).startsWith("\\u")) {
/*  89 */         String str1 = paramString.substring(i, i + 6);
/*  90 */         String str2 = str1.substring(2);
/*  91 */         int j = 0;
/*  92 */         for (int k = 0; k < str2.length(); k++) {
/*  93 */           int m = str2.charAt(k);
/*  94 */           int n = 0;
/*  95 */           switch (m) {
/*     */           case 97:
/*  97 */             n = 10;
/*  98 */             break;
/*     */           case 98:
/* 100 */             n = 11;
/* 101 */             break;
/*     */           case 99:
/* 103 */             n = 12;
/* 104 */             break;
/*     */           case 100:
/* 106 */             n = 13;
/* 107 */             break;
/*     */           case 101:
/* 109 */             n = 14;
/* 110 */             break;
/*     */           case 102:
/* 112 */             n = 15;
/* 113 */             break;
/*     */           default:
/* 115 */             n = m - 48;
/*     */           }
/*     */ 
/* 118 */           j += n * (int)Math.pow(16.0D, str2.length() - k - 1);
/*     */         }
/* 120 */         localStringBuffer.append((char)j);
/* 121 */         i += 6;
/*     */       } else {
/* 123 */         localStringBuffer.append(paramString.charAt(i++));
/*     */       }
/*     */     }
/* 126 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String getSCString(String paramString1, String paramString2)
/*     */   {
/* 137 */     if (paramString1 != null) {
/*     */       try {
/* 139 */         JSONObject localJSONObject = new JSONObject(paramString1);
/* 140 */         if (localJSONObject != null)
/* 141 */           return localJSONObject.optString(paramString2);
/*     */       }
/*     */       catch (JSONException localJSONException)
/*     */       {
/* 145 */         localJSONException.printStackTrace();
/* 146 */         return null;
/*     */       }
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.StringUtil
 * JD-Core Version:    0.6.2
 */