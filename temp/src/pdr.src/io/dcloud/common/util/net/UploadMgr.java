/*    */ package io.dcloud.common.util.net;
/*    */ 
/*    */ public class UploadMgr
/*    */ {
/*    */   private static UploadMgr mUploadMgr;
/*    */   private NetWorkLoop mUploadLoop;
/*    */ 
/*    */   private UploadMgr()
/*    */   {
/* 23 */     this.mUploadLoop = new NetWorkLoop();
/* 24 */     this.mUploadLoop.startThreadPool();
/*    */   }
/*    */ 
/*    */   public static UploadMgr getUploadMgr()
/*    */   {
/* 36 */     if (mUploadMgr == null) {
/* 37 */       mUploadMgr = new UploadMgr();
/*    */     }
/* 39 */     return mUploadMgr;
/*    */   }
/*    */ 
/*    */   public void start(NetWork paramNetWork)
/*    */   {
/* 50 */     this.mUploadLoop.addNetWork(paramNetWork);
/*    */   }
/*    */ 
/*    */   public void abort(NetWork paramNetWork)
/*    */   {
/* 61 */     removeNetWork(paramNetWork);
/*    */   }
/*    */ 
/*    */   public void removeNetWork(NetWork paramNetWork) {
/* 65 */     this.mUploadLoop.removeNetWork(paramNetWork);
/*    */   }
/*    */ 
/*    */   public void dispose()
/*    */   {
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.UploadMgr
 * JD-Core Version:    0.6.2
 */