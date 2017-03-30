/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import java.util.concurrent.LinkedBlockingQueue;
/*    */ import java.util.concurrent.ThreadPoolExecutor;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ public class ThreadPool
/*    */ {
/* 11 */   ThreadPoolExecutor threadPool = null;
/*    */   private static final int MAX_COUNT = 3;
/*    */ 
/*    */   private ThreadPool()
/*    */   {
/* 15 */     this.threadPool = new ThreadPoolExecutor(3, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
/*    */   }
/*    */ 
/*    */   public static ThreadPool self()
/*    */   {
/* 21 */     return ThreadPoolHolder.mInstance;
/*    */   }
/*    */ 
/*    */   public synchronized void addThreadTask(Runnable paramRunnable)
/*    */   {
/* 42 */     this.threadPool.execute(paramRunnable);
/*    */   }
/*    */ 
/*    */   static class ThreadPoolHolder {
/* 46 */     static ThreadPool mInstance = new ThreadPool(null);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ThreadPool
 * JD-Core Version:    0.6.2
 */