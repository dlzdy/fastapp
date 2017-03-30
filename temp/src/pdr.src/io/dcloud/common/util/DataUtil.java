/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ public class DataUtil
/*     */ {
/*     */   public String gbk2utf8(String paramString)
/*     */   {
/*   8 */     String str = GBK2Unicode(paramString);
/*   9 */     str = unicodeToUtf8(str);
/*     */ 
/*  11 */     return str;
/*     */   }
/*     */ 
/*     */   public String utf82gbk(String paramString) {
/*  15 */     String str = utf8ToUnicode(paramString);
/*  16 */     str = Unicode2GBK(str);
/*     */ 
/*  18 */     return str;
/*     */   }
/*     */ 
/*     */   public static String GBK2Unicode(String paramString)
/*     */   {
/*  28 */     StringBuffer localStringBuffer = new StringBuffer();
/*  29 */     for (int i = 0; i < paramString.length(); i++) {
/*  30 */       char c = paramString.charAt(i);
/*     */ 
/*  32 */       if (!isNeedConvert(c)) {
/*  33 */         localStringBuffer.append(c);
/*     */       }
/*     */       else
/*     */       {
/*  37 */         localStringBuffer.append("\\u" + Integer.toHexString(c));
/*     */       }
/*     */     }
/*  40 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String Unicode2GBK(String paramString)
/*     */   {
/*  50 */     int i = 0;
/*  51 */     StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/*  53 */     int j = paramString.length();
/*  54 */     while (i < j) {
/*  55 */       if ((i >= j - 1) || (!"\\u".equals(paramString.substring(i, i + 2))))
/*     */       {
/*  57 */         localStringBuffer.append(paramString.charAt(i));
/*     */ 
/*  59 */         i++;
/*     */       }
/*     */       else
/*     */       {
/*  63 */         String str = "";
/*  64 */         str = paramString.substring(i + 2, i + 6);
/*     */ 
/*  66 */         char c = (char)Integer.parseInt(str, 16);
/*     */ 
/*  68 */         localStringBuffer.append(c);
/*  69 */         i += 6;
/*     */       }
/*     */     }
/*  72 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static boolean isNeedConvert(char paramChar) {
/*  76 */     return (paramChar & 0xFF) != paramChar;
/*     */   }
/*     */ 
/*     */   public static String utf8ToUnicode(String paramString)
/*     */   {
/*  86 */     char[] arrayOfChar = paramString.toCharArray();
/*  87 */     StringBuffer localStringBuffer = new StringBuffer();
/*  88 */     for (int i = 0; i < paramString.length(); i++) {
/*  89 */       Character.UnicodeBlock localUnicodeBlock = Character.UnicodeBlock.of(arrayOfChar[i]);
/*  90 */       if ((localUnicodeBlock == Character.UnicodeBlock.BASIC_LATIN) || (localUnicodeBlock == Character.UnicodeBlock.LATIN_1_SUPPLEMENT)) {
/*  91 */         localStringBuffer.append(arrayOfChar[i]);
/*     */       }
/*     */       else
/*     */       {
/*     */         int j;
/*  92 */         if (localUnicodeBlock == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
/*  93 */           j = arrayOfChar[i] - 65248;
/*  94 */           localStringBuffer.append((char)j);
/*     */         } else {
/*  96 */           j = arrayOfChar[i];
/*  97 */           String str1 = Integer.toHexString(j);
/*  98 */           String str2 = "\\u" + str1;
/*  99 */           localStringBuffer.append(str2.toLowerCase());
/*     */         }
/*     */       }
/*     */     }
/* 102 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static String unicodeToUtf8(String paramString)
/*     */   {
/* 112 */     if (paramString == null) {
/* 113 */       return "";
/*     */     }
/* 115 */     int j = paramString.length();
/* 116 */     StringBuffer localStringBuffer = new StringBuffer(j);
/* 117 */     for (int k = 0; k < j; ) {
/* 118 */       int i = paramString.charAt(k++);
/* 119 */       if (i == 92) {
/* 120 */         i = paramString.charAt(k++);
/* 121 */         if (i == 117)
/*     */         {
/* 123 */           int m = 0;
/* 124 */           for (int n = 0; n < 4; n++) {
/* 125 */             i = paramString.charAt(k++);
/* 126 */             switch (i) {
/*     */             case 48:
/*     */             case 49:
/*     */             case 50:
/*     */             case 51:
/*     */             case 52:
/*     */             case 53:
/*     */             case 54:
/*     */             case 55:
/*     */             case 56:
/*     */             case 57:
/* 137 */               m = (m << 4) + i - 48;
/* 138 */               break;
/*     */             case 97:
/*     */             case 98:
/*     */             case 99:
/*     */             case 100:
/*     */             case 101:
/*     */             case 102:
/* 145 */               m = (m << 4) + 10 + i - 97;
/* 146 */               break;
/*     */             case 65:
/*     */             case 66:
/*     */             case 67:
/*     */             case 68:
/*     */             case 69:
/*     */             case 70:
/* 153 */               m = (m << 4) + 10 + i - 65;
/* 154 */               break;
/*     */             case 58:
/*     */             case 59:
/*     */             case 60:
/*     */             case 61:
/*     */             case 62:
/*     */             case 63:
/*     */             case 64:
/*     */             case 71:
/*     */             case 72:
/*     */             case 73:
/*     */             case 74:
/*     */             case 75:
/*     */             case 76:
/*     */             case 77:
/*     */             case 78:
/*     */             case 79:
/*     */             case 80:
/*     */             case 81:
/*     */             case 82:
/*     */             case 83:
/*     */             case 84:
/*     */             case 85:
/*     */             case 86:
/*     */             case 87:
/*     */             case 88:
/*     */             case 89:
/*     */             case 90:
/*     */             case 91:
/*     */             case 92:
/*     */             case 93:
/*     */             case 94:
/*     */             case 95:
/*     */             case 96:
/*     */             default:
/* 156 */               throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
/*     */             }
/*     */           }
/*     */ 
/* 160 */           localStringBuffer.append((char)m);
/*     */         } else {
/* 162 */           if (i == 116)
/* 163 */             i = 116;
/* 164 */           else if (i == 114)
/* 165 */             i = 114;
/* 166 */           else if (i == 110)
/* 167 */             i = 110;
/* 168 */           else if (i == 102)
/* 169 */             i = 102;
/* 170 */           localStringBuffer.append(i);
/*     */         }
/*     */       } else {
/* 173 */         localStringBuffer.append(i);
/*     */       }
/*     */     }
/* 175 */     return localStringBuffer.toString();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.DataUtil
 * JD-Core Version:    0.6.2
 */