/*    */ package io.dcloud;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import android.content.IntentFilter;
/*    */ 
/*    */ class c extends android.content.BroadcastReceiver
/*    */ {
/* 10 */   io.dcloud.feature.internal.reflect.BroadcastReceiver a = null;
/* 11 */   IntentFilter b = null;
/*    */ 
/* 13 */   c(io.dcloud.feature.internal.reflect.BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter) { this.a = paramBroadcastReceiver;
/* 14 */     this.b = paramIntentFilter; }
/*    */ 
/*    */   public void onReceive(Context paramContext, Intent paramIntent)
/*    */   {
/* 18 */     if ((this.a != null) && (this.b != null) && (this.b.hasAction(paramIntent.getAction())))
/* 19 */       this.a.onReceive(paramContext, paramIntent);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.c
 * JD-Core Version:    0.6.2
 */