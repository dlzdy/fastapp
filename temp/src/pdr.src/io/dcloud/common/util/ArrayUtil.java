/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ public class ArrayUtil
/*    */ {
/*    */   public static String[] riseArray(String[] paramArrayOfString, String paramString)
/*    */   {
/* 17 */     if (paramArrayOfString == null) {
/* 18 */       paramArrayOfString = new String[1];
/*    */     } else {
/* 20 */       int i = paramArrayOfString.length;
/* 21 */       String[] arrayOfString = new String[i + 1];
/* 22 */       System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, i);
/* 23 */       paramArrayOfString = arrayOfString;
/*    */     }
/* 25 */     paramArrayOfString[paramArrayOfString.length] = paramString;
/* 26 */     return paramArrayOfString;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ArrayUtil
 * JD-Core Version:    0.6.2
 */