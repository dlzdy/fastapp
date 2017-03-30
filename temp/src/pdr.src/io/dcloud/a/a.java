/*    */ package io.dcloud.a;
/*    */ 
/*    */ import android.content.Context;
/*    */ import io.dcloud.common.adapter.util.PlatformUtil;
/*    */ 
/*    */ public class a
/*    */ {
/*  8 */   private static String c = "X7YBPqS7I0cDrWg8xDIfY0YVZCGmXWQ5ugQWJrvTAemwpG4BtP7JHZQa8SEE90C9\r/jOaV/jrBnYbCXcLfwIDAQAB\r";
/*    */ 
/* 14 */   static char[] a = { 'x', 'v', '/', 't', 'E', 'C', 'D', 'b', 'i', 'A', 'D', 'x', 'L', 'w', 'y', 'q', 'b', 'e', '0', 'G', 'b', 'f', 'G', '6', 't', 'c' };
/*    */ 
/* 18 */   static char[] b = { 'H', 'B', 'y', 'W', 'j', 'U', 'H', 'g', 'm', '3', 'C', 'p', 'Z', 'a', 'c', 'r', 'I', 'x', 'K', 'A', '4', 'J', 'S', 'W', 'k', 'y', 'b', 's', 'y', 'Y', 'c', 'O', 'y', '3', 'D', 'v', '2', 't', 'y', 'N', 'w', 'N', 'A', '6', 'N', 'a', 'D', 'O', 'u', 'P', 'i', 'R', 'K', 'b', 'p', 'N', 'y', 'q', 'A', '0', 'W', 'y', 'l', 'K', 'F', 'e', '9', 'X', 'q', 'm', 'e' };
/*    */ 
/*    */   public static String a(Context paramContext)
/*    */   {
/* 26 */     if (paramContext == null) {
/* 27 */       return "";
/*    */     }
/* 29 */     String str = PlatformUtil.getFileContent4S("libso.so") + "\r" + c.a(a, 0) + c.a(b) + "\r" + c;
/*    */ 
/* 31 */     return str;
/*    */   }
/*    */ 
/*    */   public static String a()
/*    */   {
/* 36 */     return "HTML5PLUSRUNTIME";
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.a.a
 * JD-Core Version:    0.6.2
 */