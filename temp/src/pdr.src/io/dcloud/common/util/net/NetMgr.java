/*     */ package io.dcloud.common.util.net;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.IntentFilter;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.ICore;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IMgr.NetEvent;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.net.http.LocalServer;
/*     */ import io.dcloud.common.util.net.http.MiniServer;
/*     */ 
/*     */ public class NetMgr extends AbsMgr
/*     */   implements IMgr.NetEvent
/*     */ {
/*     */   DownloadMgr mDownloadMgr;
/*     */   UploadMgr mUploadMgr;
/*  41 */   NetCheckReceiver mNetCheckReceiver = null;
/*  42 */   MiniServer mLocalServer = null;
/*  43 */   LocalServer mMiniServer = null;
/*     */ 
/*     */   public NetMgr(ICore paramICore)
/*     */   {
/*  32 */     super(paramICore, "netmgr", IMgr.MgrType.NetMgr);
/*  33 */     startMiniServer();
/*  34 */     this.mUploadMgr = UploadMgr.getUploadMgr();
/*  35 */     this.mDownloadMgr = DownloadMgr.getDownloadMgr();
/*  36 */     this.mNetCheckReceiver = new NetCheckReceiver(this);
/*  37 */     IntentFilter localIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
/*  38 */     localIntentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
/*  39 */     getContext().registerReceiver(this.mNetCheckReceiver, localIntentFilter);
/*     */   }
/*     */ 
/*     */   protected void startMiniServer()
/*     */   {
/*  50 */     if (PdrUtil.isEquals(getContext().getPackageName(), "io.dcloud.HBuilder"));
/*  54 */     this.mMiniServer = new LocalServer(this, 13131);
/*  55 */     this.mMiniServer.start();
/*     */   }
/*     */ 
/*     */   public Object processEvent(IMgr.MgrType paramMgrType, int paramInt, Object paramObject)
/*     */   {
/*  60 */     Object localObject = null;
/*     */     try {
/*  62 */       if (!checkMgrId(paramMgrType))
/*  63 */         localObject = this.mCore.dispatchEvent(paramMgrType, paramInt, paramObject);
/*     */       else {
/*  65 */         switch (paramInt)
/*     */         {
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Throwable localThrowable)
/*     */     {
/*  72 */       Logger.w("NetMgr.processEvent", localThrowable);
/*     */     }
/*  74 */     return localObject;
/*     */   }
/*     */ 
/*     */   public void onExecute(ISysEventListener.SysEventType paramSysEventType, Object paramObject) {
/*  78 */     if (paramSysEventType == ISysEventListener.SysEventType.onPause) {
/*  79 */       if (this.mMiniServer != null) {
/*  80 */         this.mMiniServer.stop();
/*  81 */         this.mMiniServer = null;
/*     */       }
/*  83 */     } else if ((paramSysEventType == ISysEventListener.SysEventType.onResume) && 
/*  84 */       (this.mMiniServer == null)) {
/*  85 */       this.mMiniServer = new LocalServer(this, 13131);
/*  86 */       this.mMiniServer.start();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void dispose() {
/*  91 */     if (this.mLocalServer != null) {
/*  92 */       this.mLocalServer.stopMiniServer();
/*     */     }
/*  94 */     if (this.mMiniServer != null) {
/*  95 */       this.mMiniServer.stop();
/*     */     }
/*  97 */     if (this.mUploadMgr != null) {
/*  98 */       this.mUploadMgr.dispose();
/*     */     }
/* 100 */     if (this.mDownloadMgr != null) {
/* 101 */       this.mDownloadMgr.dispose();
/*     */     }
/* 103 */     getContext().unregisterReceiver(this.mNetCheckReceiver);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.NetMgr
 * JD-Core Version:    0.6.2
 */