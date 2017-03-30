/*    */ package io.dcloud.common.adapter.io;
/*    */ 
/*    */ import android.app.Service;
/*    */ import android.content.Intent;
/*    */ import android.os.IBinder;
/*    */ import io.dcloud.common.adapter.util.DeviceInfo;
/*    */ 
/*    */ public class MiniServerService extends Service
/*    */ {
/* 20 */   String mServiceName = null;
/*    */ 
/*    */   public IBinder onBind(Intent paramIntent) {
/* 23 */     return null;
/*    */   }
/*    */ 
/*    */   public void onCreate()
/*    */   {
/* 28 */     super.onCreate();
/*    */   }
/*    */ 
/*    */   public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
/* 32 */     if ((DeviceInfo.sDeviceSdkVer > 5) && (paramIntent != null)) {
/* 33 */       this.mServiceName = paramIntent.getStringExtra("mini_server");
/* 34 */       startServer();
/*    */     }
/* 36 */     return super.onStartCommand(paramIntent, paramInt1, paramInt2);
/*    */   }
/*    */ 
/*    */   public void onStart(Intent paramIntent, int paramInt) {
/* 40 */     if ((DeviceInfo.sDeviceSdkVer <= 5) && (paramIntent != null)) {
/* 41 */       this.mServiceName = paramIntent.getStringExtra("mini_server");
/* 42 */       startServer();
/*    */     }
/* 44 */     super.onStart(paramIntent, paramInt);
/*    */   }
/*    */ 
/*    */   private void startServer() {
/* 48 */     AdaService localAdaService = AdaService.getServiceListener(this.mServiceName);
/* 49 */     if (localAdaService != null)
/* 50 */       localAdaService.onExecute();
/*    */   }
/*    */ 
/*    */   public void onDestroy()
/*    */   {
/* 55 */     AdaService localAdaService = AdaService.getServiceListener(this.mServiceName);
/* 56 */     if (localAdaService != null) {
/* 57 */       localAdaService.onDestroy();
/* 58 */       AdaService.getServiceListener(this.mServiceName);
/*    */     }
/* 60 */     super.onDestroy();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.io.MiniServerService
 * JD-Core Version:    0.6.2
 */