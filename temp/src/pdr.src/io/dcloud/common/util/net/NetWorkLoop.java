/*     */ package io.dcloud.common.util.net;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ 
/*     */ public class NetWorkLoop
/*     */ {
/*     */   protected LinkedList<NetWork> mQuestTask;
/*     */   protected LinkedList<NetWork> mExeTask;
/*  31 */   private final int MAX_EXE_REQUESTDATA = 5;
/*     */   protected Thread mSyncThread;
/*     */   private LoopComparator mComparator;
/*     */ 
/*     */   public NetWorkLoop()
/*     */   {
/*  50 */     this.mComparator = new LoopComparator();
/*  51 */     this.mQuestTask = new LinkedList();
/*  52 */     this.mExeTask = new LinkedList();
/*     */   }
/*     */ 
/*     */   public void startThreadPool()
/*     */   {
/*  64 */     this.mSyncThread = new Thread() {
/*     */       public void run() {
/*     */         while (true)
/*     */           try {
/*  68 */             if (NetWorkLoop.this.mSyncThread == null) {
/*     */               break;
/*     */             }
/*  71 */             if (NetWorkLoop.this.mQuestTask.isEmpty())
/*  72 */               Thread.sleep(100L);
/*     */             else
/*  74 */               synchronized (NetWorkLoop.this.mExeTask) {
/*  75 */                 if (NetWorkLoop.this.mExeTask.size() < 5)
/*  76 */                   synchronized (NetWorkLoop.this.mQuestTask) {
/*  77 */                     NetWork localNetWork = (NetWork)NetWorkLoop.this.mQuestTask.get(0);
/*  78 */                     NetWorkLoop.this.mExeTask.add(localNetWork);
/*  79 */                     NetWorkLoop.this.mQuestTask.remove(localNetWork);
/*  80 */                     NetWorkLoop.this.execSyncTask(localNetWork);
/*     */                   }
/*     */               }
/*     */           }
/*     */           catch (Exception localException)
/*     */           {
/*  86 */             localException.printStackTrace();
/*     */           }
/*     */       }
/*     */     };
/*  91 */     this.mSyncThread.start();
/*     */   }
/*     */ 
/*     */   protected void execSyncTask(NetWork paramNetWork)
/*     */   {
/* 103 */     paramNetWork.mNetWorkLoop = this;
/* 104 */     paramNetWork.startWork();
/*     */   }
/*     */ 
/*     */   public synchronized void addNetWork(NetWork paramNetWork)
/*     */   {
/* 116 */     this.mQuestTask.add(paramNetWork);
/*     */ 
/* 118 */     Collections.sort(this.mQuestTask, this.mComparator);
/*     */   }
/*     */ 
/*     */   public synchronized void removeNetWork(NetWork paramNetWork)
/*     */   {
/*     */     try
/*     */     {
/* 131 */       if (this.mQuestTask.contains(paramNetWork)) {
/* 132 */         this.mQuestTask.remove(paramNetWork);
/*     */       }
/* 134 */       if (this.mExeTask.contains(paramNetWork)) {
/* 135 */         this.mExeTask.remove(paramNetWork);
/*     */       }
/* 137 */       paramNetWork.cancelWork();
/*     */     } catch (Exception localException) {
/* 139 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void dispose() {
/* 144 */     this.mSyncThread = null;
/*     */     try
/*     */     {
/*     */       Iterator localIterator;
/*     */       NetWork localNetWork;
/* 146 */       if ((this.mExeTask != null) && (this.mExeTask.size() > 0)) {
/* 147 */         for (localIterator = this.mExeTask.iterator(); localIterator.hasNext(); ) { localNetWork = (NetWork)localIterator.next();
/* 148 */           localNetWork.dispose();
/*     */         }
/* 150 */         this.mExeTask.clear();
/* 151 */         this.mExeTask = null;
/*     */       }
/* 153 */       if ((this.mQuestTask != null) && (this.mQuestTask.size() > 0)) {
/* 154 */         for (localIterator = this.mQuestTask.iterator(); localIterator.hasNext(); ) { localNetWork = (NetWork)localIterator.next();
/* 155 */           localNetWork.dispose();
/*     */         }
/* 157 */         this.mQuestTask.clear();
/* 158 */         this.mQuestTask = null;
/*     */       }
/*     */     } catch (ConcurrentModificationException localConcurrentModificationException) {
/* 161 */       localConcurrentModificationException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   class LoopComparator
/*     */     implements Comparator<NetWork>
/*     */   {
/*     */     LoopComparator()
/*     */     {
/*     */     }
/*     */ 
/*     */     public int compare(NetWork paramNetWork1, NetWork paramNetWork2)
/*     */     {
/* 183 */       return paramNetWork1.mPriority - paramNetWork2.mPriority;
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.NetWorkLoop
 * JD-Core Version:    0.6.2
 */