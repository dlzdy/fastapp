/*    */ package io.dcloud.common.util.net;
/*    */ 
/*    */ import android.content.BroadcastReceiver;
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*    */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*    */ 
/*    */ public class NetCheckReceiver extends BroadcastReceiver
/*    */ {
/*    */   public static final String netACTION = "android.net.conn.CONNECTIVITY_CHANGE";
/*    */   public static final String simACTION = "android.intent.action.SIM_STATE_CHANGED";
/* 16 */   AbsMgr mNetMgr = null;
/*    */ 
/* 18 */   NetCheckReceiver(AbsMgr paramAbsMgr) { this.mNetMgr = paramAbsMgr; }
/*    */ 
/*    */   public void onReceive(Context paramContext, Intent paramIntent)
/*    */   {
/* 22 */     if (paramIntent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
/* 23 */       this.mNetMgr.processEvent(IMgr.MgrType.AppMgr, 1, new Object[] { ISysEventListener.SysEventType.onDeviceNetChanged, null, null });
/*    */     }
/* 28 */     else if (paramIntent.getAction().equals("android.intent.action.SIM_STATE_CHANGED"))
/* 29 */       this.mNetMgr.processEvent(IMgr.MgrType.AppMgr, 1, new Object[] { ISysEventListener.SysEventType.onSimStateChanged, null, null });
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.NetCheckReceiver
 * JD-Core Version:    0.6.2
 */