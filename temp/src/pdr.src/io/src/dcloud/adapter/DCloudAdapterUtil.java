/*    */ package io.src.dcloud.adapter;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.Context;
/*    */ import io.dcloud.RInformation;
/*    */ import io.dcloud.common.DHInterface.IActivityHandler;
/*    */ import io.dcloud.common.DHInterface.IReflectAble;
/*    */ import io.dcloud.common.util.BaseInfo;
/*    */ 
/*    */ public class DCloudAdapterUtil
/*    */   implements IReflectAble
/*    */ {
/*    */   public static IActivityHandler getIActivityHandler(Activity paramActivity)
/*    */   {
/* 14 */     if ((paramActivity instanceof IActivityHandler)) {
/* 15 */       return (IActivityHandler)paramActivity;
/*    */     }
/* 17 */     return null;
/*    */   }
/*    */ 
/*    */   public static String getRuntimeJsPath() {
/* 21 */     return BaseInfo.sRuntimeJsPath;
/*    */   }
/*    */ 
/*    */   public static int getImageOnLoadingId(Context paramContext) {
/* 25 */     return RInformation.STREAMAPP_DRAWABLE_APPDEFULTICON;
/*    */   }
/*    */ 
/*    */   public static String getDcloudDownloadService() {
/* 29 */     return "io.dcloud.streamdownload.DownloadService";
/*    */   }
/*    */ 
/*    */   public static Class<?> getDownloadServiceClass()
/*    */   {
/*    */     try {
/* 35 */       return Class.forName(getDcloudDownloadService());
/*    */     } catch (ClassNotFoundException localClassNotFoundException) {
/* 37 */       localClassNotFoundException.printStackTrace();
/*    */     }
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */   public static int getImagePickNoMediaId(Context paramContext) {
/* 43 */     return RInformation.DRAWABLE_IMAGE_PICK_NO_MEDIA;
/*    */   }
/*    */   public static void Plugin2Host_closeAppStreamSplash(String paramString) {
/*    */   }
/*    */   public static void Plugin2Host_finishActivity(String paramString) {
/*    */   }
/*    */   public static boolean isPlugin() {
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   public static String getPageName() {
/* 54 */     return "";
/*    */   }
/*    */ 
/*    */   public static boolean isAutoCreateShortCut() {
/* 58 */     return true;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.src.dcloud.adapter.DCloudAdapterUtil
 * JD-Core Version:    0.6.2
 */