/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.app.DownloadManager;
/*     */ import android.app.DownloadManager.Query;
/*     */ import android.app.DownloadManager.Request;
/*     */ import android.content.BroadcastReceiver;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.IntentFilter;
/*     */ import android.database.Cursor;
/*     */ import android.net.Uri;
/*     */ import android.util.Log;
/*     */ import io.dcloud.common.DHInterface.ICallBack;
/*     */ import io.dcloud.common.adapter.io.DHFile;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DownloadUtil extends BroadcastReceiver
/*     */ {
/*  28 */   private static DownloadUtil m = null;
/*     */   private DownloadManager mDownloader;
/*  30 */   HashMap<Long, MyRequest> rs = null;
/*     */ 
/*  32 */   private DownloadUtil(Context paramContext) { this.mDownloader = ((DownloadManager)paramContext.getSystemService("download"));
/*  33 */     paramContext.registerReceiver(this, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
/*  34 */     this.rs = new HashMap(2); }
/*     */ 
/*     */   public static long startRequest(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, ICallBack paramICallBack)
/*     */   {
/*  38 */     if (m == null) {
/*  39 */       m = new DownloadUtil(paramContext);
/*     */     }
/*  41 */     MyRequest localMyRequest = new MyRequest(paramString1, paramString2, paramICallBack);
/*     */     try {
/*  43 */       if (!DHFile.isExist(paramString3))
/*  44 */         new File(paramString3).mkdirs();
/*     */     }
/*     */     catch (IOException localIOException) {
/*  47 */       localIOException.printStackTrace();
/*     */     }
/*  49 */     if (paramString3.startsWith(DeviceInfo.sDeviceRootDir)) {
/*  50 */       paramString3 = paramString3.substring(DeviceInfo.sDeviceRootDir.length() + 1);
/*     */     }
/*  52 */     localMyRequest.m.setTitle(paramString4);
/*  53 */     localMyRequest.m.setDestinationInExternalPublicDir(paramString3, paramString4);
/*  54 */     long l = m.mDownloader.enqueue(localMyRequest.m);
/*  55 */     localMyRequest.id = l;
/*  56 */     m.rs.put(Long.valueOf(l), localMyRequest);
/*  57 */     return l;
/*     */   }
/*     */ 
/*     */   public void onReceive(Context paramContext, Intent paramIntent)
/*     */   {
/*  62 */     Log.v("intent", "" + paramIntent.getLongExtra("extra_download_id", 0L));
/*     */     try {
/*  64 */       queryDownloadStatus();
/*     */     } catch (Exception localException) {
/*  66 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void queryDownloadStatus() {
/*  71 */     Set localSet = this.rs.keySet();
/*  72 */     Long[] arrayOfLong = new Long[localSet.size()];
/*  73 */     localSet.toArray(arrayOfLong);
/*  74 */     for (int i = arrayOfLong.length - 1; i >= 0; i--) {
/*  75 */       long l = arrayOfLong[i].longValue();
/*  76 */       DownloadManager.Query localQuery = new DownloadManager.Query();
/*  77 */       localQuery.setFilterById(new long[] { l });
/*  78 */       Cursor localCursor = this.mDownloader.query(localQuery);
/*  79 */       if (localCursor.moveToFirst()) {
/*  80 */         int j = localCursor.getInt(localCursor.getColumnIndex("status"));
/*  81 */         switch (j) {
/*     */         case 4:
/*  83 */           Log.v("down", "STATUS_PAUSED");
/*     */         case 1:
/*  85 */           Log.v("down", "STATUS_PENDING");
/*     */         case 2:
/*  88 */           Log.v("down", "STATUS_RUNNING");
/*  89 */           break;
/*     */         case 8:
/*  92 */           Log.v("down", "下载完成");
/*     */ 
/*  94 */           String str = null;
/*     */           try {
/*  96 */             str = this.mDownloader.getUriForDownloadedFile(l).getPath();
/*     */           } catch (Exception localException) {
/*  98 */             localException.printStackTrace();
/*     */           }
/* 100 */           MyRequest localMyRequest = (MyRequest)this.rs.remove(Long.valueOf(l));
/* 101 */           if (localMyRequest.back != null)
/* 102 */             localMyRequest.back.onCallBack(0, str); break;
/*     */         case 16:
/* 107 */           Log.v("down", "STATUS_FAILED");
/* 108 */           this.mDownloader.remove(new long[] { l });
/* 109 */           this.rs.remove(Long.valueOf(l));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   static class MyRequest
/*     */   {
/* 117 */     DownloadManager.Request m = null;
/*     */     long id;
/* 119 */     ICallBack back = null;
/*     */ 
/* 121 */     MyRequest(String paramString1, String paramString2, ICallBack paramICallBack) { this.m = new DownloadManager.Request(Uri.parse(paramString1));
/* 122 */       this.m.setMimeType(paramString2);
/* 123 */       this.back = paramICallBack;
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.DownloadUtil
 * JD-Core Version:    0.6.2
 */