/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.net.ConnectivityManager;
/*    */ import android.net.NetworkInfo;
/*    */ 
/*    */ public class NetworkTypeUtil
/*    */ {
/*    */   public static final int NETWORK_TYPE_UNKONWN = 0;
/*    */   public static final int NETWORK_TYPE_DISABLED = 1;
/*    */   public static final int NETWORK_TYPE_LINE = 2;
/*    */   public static final int NETWORK_TYPE_WIFI = 3;
/*    */   public static final int NETWORK_TYPE_2G = 4;
/*    */   public static final int NETWORK_TYPE_3G = 5;
/*    */   public static final int NETWORK_TYPE_4G = 6;
/*    */ 
/*    */   public static int getNetworkType(Context paramContext)
/*    */   {
/* 23 */     ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
/*    */ 
/* 25 */     NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
/*    */ 
/* 27 */     if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable())) {
/* 28 */       if (localNetworkInfo.getType() == 0) {
/* 29 */         int i = localNetworkInfo.getSubtype();
/* 30 */         switch (i) {
/*    */         case 1:
/*    */         case 2:
/*    */         case 4:
/* 34 */           return 4;
/*    */         case 3:
/*    */         case 5:
/*    */         case 6:
/*    */         case 8:
/*    */         case 12:
/* 41 */           return 5;
/*    */         case 9:
/*    */         case 10:
/*    */         case 11:
/*    */         case 13:
/*    */         case 15:
/*    */         case 17:
/* 49 */           return 6;
/*    */         case 7:
/*    */         case 14:
/* 51 */         case 16: } return 0;
/*    */       }
/* 53 */       if (localNetworkInfo.getType() == 1)
/* 54 */         return 3;
/*    */     }
/*    */     else {
/* 57 */       return 1;
/*    */     }
/* 59 */     return 0;
/*    */   }
/*    */ 
/*    */   public static String getCurrentAPN(Context paramContext)
/*    */   {
/* 68 */     ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
/*    */ 
/* 70 */     NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
/*    */ 
/* 72 */     if (localNetworkInfo != null) {
/* 73 */       int i = localNetworkInfo.getType();
/* 74 */       if (0 == i) {
/* 75 */         return localNetworkInfo.getExtraInfo();
/*    */       }
/* 77 */       return "";
/*    */     }
/*    */ 
/* 80 */     return "";
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.NetworkTypeUtil
 * JD-Core Version:    0.6.2
 */