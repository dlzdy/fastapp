/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ public class JsEventUtil
/*    */ {
/*    */   public static String eventListener_format(String paramString1, String paramString2, boolean paramBoolean)
/*    */   {
/*  6 */     String str = String.format("{evt:'%s',args:" + (paramBoolean ? "'%s'" : "%s") + "}", new Object[] { paramString1, paramString2 });
/*  7 */     return str;
/*    */   }
/*    */   public static String broadcastEvents_format(String paramString1, String paramString2, boolean paramBoolean, String[] paramArrayOfString) {
/* 10 */     return String.format("{evt:'%s',args:" + (paramBoolean ? "'%s'" : "%s") + ",callbackId:'%s'}", new Object[] { paramString1, paramString2, paramArrayOfString });
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.JsEventUtil
 * JD-Core Version:    0.6.2
 */