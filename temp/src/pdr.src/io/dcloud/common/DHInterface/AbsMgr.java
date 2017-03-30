/*    */ package io.dcloud.common.DHInterface;
/*    */ 
/*    */ import android.content.Context;
/*    */ 
/*    */ public abstract class AbsMgr
/*    */   implements IMgr
/*    */ {
/* 13 */   protected Context mContextWrapper = null;
/* 14 */   protected ICore mCore = null;
/* 15 */   protected String mMgrName = null;
/*    */   protected IMgr.MgrType mMgrType;
/* 17 */   protected boolean mIsStreamAppMode = false;
/*    */ 
/*    */   protected AbsMgr(ICore paramICore, String paramString, IMgr.MgrType paramMgrType)
/*    */   {
/* 26 */     this.mCore = paramICore;
/* 27 */     this.mIsStreamAppMode = paramICore.isStreamAppMode();
/* 28 */     this.mMgrName = paramString;
/* 29 */     this.mMgrType = paramMgrType;
/* 30 */     this.mContextWrapper = paramICore.obtainContext();
/*    */   }
/*    */ 
/*    */   public final boolean checkMgrId(IMgr.MgrType paramMgrType)
/*    */   {
/* 38 */     return this.mMgrType == paramMgrType;
/*    */   }
/*    */ 
/*    */   public final Context getContext()
/*    */   {
/* 45 */     return this.mContextWrapper;
/*    */   }
/*    */   public final boolean isStreamAppMode() {
/* 48 */     return this.mIsStreamAppMode;
/*    */   }
/*    */ 
/*    */   public void onExecute(ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void dispose()
/*    */   {
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.AbsMgr
 * JD-Core Version:    0.6.2
 */