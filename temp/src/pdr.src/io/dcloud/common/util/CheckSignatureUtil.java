/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.text.TextUtils;
/*    */ import io.dcloud.application.DCloudApplication;
/*    */ 
/*    */ public class CheckSignatureUtil
/*    */ {
/*    */   private static final String SIGNATURES_MD5 = "9303f6b5f17a1146f3e19d7a955942c8";
/*    */ 
/*    */   public static boolean check(String paramString)
/*    */   {
/* 24 */     String str1 = getSignature(paramString);
/* 25 */     if (TextUtils.isEmpty(str1))
/*    */     {
/* 27 */       return true;
/*    */     }
/*    */ 
/* 30 */     String str2 = ApkUtils.getAppSignatureMd5(DCloudApplication.getInstance().getApplicationContext(), DCloudApplication.getInstance().getApplicationContext().getPackageName());
/*    */ 
/* 33 */     if (TextUtils.isEmpty(str2))
/*    */     {
/* 35 */       return true;
/*    */     }
/* 37 */     if (str2.equalsIgnoreCase(str1)) {
/* 38 */       return true;
/*    */     }
/*    */ 
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */   public static String getSignature(String paramString) {
/* 45 */     String[] arrayOfString = ApkUtils.getApkFileSignatureAndPackageName(DCloudApplication.getInstance().getApplicationContext(), paramString);
/*    */ 
/* 48 */     if ((arrayOfString != null) && (arrayOfString.length > 0)) {
/* 49 */       return arrayOfString[0];
/*    */     }
/* 51 */     return "";
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.CheckSignatureUtil
 * JD-Core Version:    0.6.2
 */