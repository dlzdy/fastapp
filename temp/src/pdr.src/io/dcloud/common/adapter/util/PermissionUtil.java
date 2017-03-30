/*    */ package io.dcloud.common.adapter.util;
/*    */ 
/*    */ import io.dcloud.common.DHInterface.IReflectAble;
/*    */ import io.dcloud.common.DHInterface.IWebview;
/*    */ import io.dcloud.common.b.a.a;
/*    */ 
/*    */ public class PermissionUtil
/*    */   implements IReflectAble
/*    */ {
/*    */   public static String convertNativePermission(String paramString)
/*    */   {
/* 14 */     return a.d(paramString);
/*    */   }
/*    */ 
/*    */   public static String checkPermission(IWebview paramIWebview, String[] paramArrayOfString) {
/* 18 */     return a.a(paramIWebview, paramArrayOfString);
/*    */   }
/*    */ 
/*    */   public static String convert5PlusValue(int paramInt) {
/* 22 */     return a.a(paramInt);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.PermissionUtil
 * JD-Core Version:    0.6.2
 */