/*    */ package io.dcloud.common.util.net;
/*    */ 
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ 
/*    */ public class DownloadMgr
/*    */ {
/*    */   private static DownloadMgr mDownloadMgr;
/*    */   private NetWorkLoop mDownloadLoop;
/*    */ 
/*    */   private DownloadMgr()
/*    */   {
/* 26 */     this.mDownloadLoop = new NetWorkLoop();
/* 27 */     this.mDownloadLoop.startThreadPool();
/*    */   }
/*    */ 
/*    */   public static DownloadMgr getDownloadMgr()
/*    */   {
/* 39 */     if (mDownloadMgr == null) {
/* 40 */       mDownloadMgr = new DownloadMgr();
/*    */     }
/* 42 */     return mDownloadMgr;
/*    */   }
/*    */ 
/*    */   public void addQuestTask(NetWork paramNetWork) {
/* 46 */     this.mDownloadLoop.addNetWork(paramNetWork);
/*    */   }
/*    */ 
/*    */   public void removeTask(NetWork paramNetWork) {
/* 50 */     this.mDownloadLoop.removeNetWork(paramNetWork);
/*    */   }
/*    */ 
/*    */   public void dispose() {
/* 54 */     Logger.d("DownloadMgr: dispose");
/* 55 */     this.mDownloadLoop.dispose();
/* 56 */     mDownloadMgr = null;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.DownloadMgr
 * JD-Core Version:    0.6.2
 */