/*    */ package io.dcloud.common.adapter.io;
/*    */ 
/*    */ import android.content.BroadcastReceiver;
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ import io.dcloud.common.adapter.util.PlatformUtil;
/*    */ 
/*    */ public class DownloadReceiver extends BroadcastReceiver
/*    */ {
/* 11 */   public static String ACTION_DOWNLOAD_COMPLETED = "action.download.completed_io.dcloud";
/* 12 */   public static String ACTION_OPEN_FILE = "action.openfile.io.dcloud";
/* 13 */   public static String KEY_FILEURI = "FileUri";
/*    */ 
/*    */   public void onReceive(Context paramContext, Intent paramIntent) {
/* 16 */     String str1 = paramIntent.getAction();
/* 17 */     if (ACTION_OPEN_FILE.equals(str1)) {
/* 18 */       String str2 = paramIntent.getStringExtra(KEY_FILEURI);
/* 19 */       Logger.d("DownloadReceiver", "action=" + ACTION_OPEN_FILE + ";" + KEY_FILEURI + "=" + str2);
/* 20 */       PlatformUtil.openFileBySystem(paramContext, String.valueOf(str2), null, null);
/*    */     } else {
/* 22 */       Logger.d("not handle '" + str1 + "' cation");
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.io.DownloadReceiver
 * JD-Core Version:    0.6.2
 */