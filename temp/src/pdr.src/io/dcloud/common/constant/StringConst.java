/*    */ package io.dcloud.common.constant;
/*    */ 
/*    */ import android.text.TextUtils;
/*    */ import io.dcloud.common.adapter.util.AndroidResources;
/*    */ 
/*    */ public final class StringConst extends AndroidResources
/*    */   implements AbsoluteConst
/*    */ {
/* 19 */   static String T_URL_BASE_DATA = "appid=%s&imei=%s&net=%d&md=%s&os=%d&vb=%s&sf=%d&p=a&d1=%d&sfd=%s&vd=%s";
/*    */ 
/* 61 */   private static long sChangeTime = 0L;
/*    */ 
/*    */   public static int getIntSF(String paramString)
/*    */   {
/* 22 */     if (!TextUtils.isEmpty(paramString)) {
/* 23 */       if ("barcode".equals(paramString))
/* 24 */         return 2;
/* 25 */       if ("scheme".equals(paramString))
/* 26 */         return 3;
/* 27 */       if ("stream".equals(paramString))
/* 28 */         return 6;
/* 29 */       if ("shortcut".equals(paramString))
/* 30 */         return 5;
/* 31 */       if ("push".equals(paramString))
/* 32 */         return 4;
/* 33 */       if ("myapp".equals(paramString))
/* 34 */         return 7;
/* 35 */       if (paramString.indexOf("third:") == 0) {
/* 36 */         return 9;
/*    */       }
/*    */     }
/*    */ 
/* 40 */     return 1;
/*    */   }
/*    */ 
/*    */   public static String STREAMAPP_KEY_BASESERVICEURL()
/*    */   {
/* 47 */     return mainHost();
/*    */   }
/*    */ 
/*    */   public static String changeHost(String paramString)
/*    */   {
/* 54 */     paramString = paramString.replace(mainHost(), backupHost());
/*    */ 
/* 58 */     return paramString;
/*    */   }
/*    */ 
/*    */   private static String mainHost()
/*    */   {
/* 63 */     return "http://stream.dcloud.net.cn/";
/*    */   }
/*    */   private static String backupHost() {
/* 66 */     return "http://stream.mobihtml5.com/";
/*    */   }
/*    */ 
/*    */   public static boolean canChangeHost(String paramString) {
/* 70 */     return paramString.contains(backupHost()) ? false : paramString.contains(mainHost());
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.constant.StringConst
 * JD-Core Version:    0.6.2
 */