/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class AppStatus
/*    */ {
/*    */   public static final int STOPPED = 0;
/*    */   public static final int ACTIVE = 2;
/* 10 */   private static HashMap<String, Integer> sMaps = new HashMap();
/*    */ 
/*    */   public static void setAppStatus(String paramString, int paramInt) {
/* 13 */     sMaps.put(paramString, Integer.valueOf(paramInt));
/*    */   }
/*    */ 
/*    */   public static int getAppStatus(String paramString) {
/* 17 */     if (sMaps.containsKey(paramString)) {
/* 18 */       return ((Integer)sMaps.get(paramString)).intValue();
/*    */     }
/* 20 */     return 2;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.AppStatus
 * JD-Core Version:    0.6.2
 */