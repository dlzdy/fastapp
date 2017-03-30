/*    */ package io.dcloud.a;
/*    */ 
/*    */ public class c
/*    */ {
/*    */   public static String a(char[] paramArrayOfChar)
/*    */   {
/*  6 */     return new String(paramArrayOfChar).replaceAll("y", "");
/*    */   }
/*    */ 
/*    */   public static String a(char[] paramArrayOfChar, int paramInt) {
/* 10 */     return new String(paramArrayOfChar, 2, paramArrayOfChar.length - 3).replaceAll("b", "");
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.a.c
 * JD-Core Version:    0.6.2
 */