/*    */ package io.dcloud.common.adapter.io;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public abstract class AdaService
/*    */ {
/*    */   static final String TAG = "AdaService";
/* 21 */   protected Context mContextWrapper = null;
/* 22 */   private String mServiceName = null;
/*    */ 
/* 73 */   static HashMap<String, AdaService> mServicesHandler = new HashMap(2);
/*    */ 
/*    */   protected AdaService(Context paramContext, String paramString)
/*    */   {
/* 24 */     this.mContextWrapper = paramContext;
/* 25 */     this.mServiceName = paramString;
/*    */   }
/*    */ 
/*    */   public final void startMiniServer()
/*    */   {
/* 33 */     mServicesHandler.put(this.mServiceName, this);
/* 34 */     Intent localIntent = new Intent(this.mContextWrapper, MiniServerService.class);
/* 35 */     localIntent.putExtra("mini_server", this.mServiceName);
/* 36 */     this.mContextWrapper.startService(localIntent);
/* 37 */     Logger.d("AdaService", "pname=" + this.mContextWrapper.getPackageName() + " startMiniServer");
/*    */   }
/*    */ 
/*    */   public final void stopMiniServer()
/*    */   {
/* 46 */     Intent localIntent = new Intent(this.mContextWrapper, MiniServerService.class);
/* 47 */     localIntent.putExtra("mini_server", this.mServiceName);
/* 48 */     this.mContextWrapper.stopService(localIntent);
/* 49 */     Logger.d("AdaService", "pname=" + this.mContextWrapper.getPackageName() + " stopMiniServer");
/*    */   }
/*    */ 
/*    */   public abstract void onExecute();
/*    */ 
/*    */   public abstract void onDestroy();
/*    */ 
/*    */   public static final AdaService getServiceListener(String paramString)
/*    */   {
/* 66 */     return (AdaService)mServicesHandler.get(paramString);
/*    */   }
/*    */ 
/*    */   public static final void removeServiceListener(String paramString) {
/* 70 */     mServicesHandler.remove(paramString);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.io.AdaService
 * JD-Core Version:    0.6.2
 */