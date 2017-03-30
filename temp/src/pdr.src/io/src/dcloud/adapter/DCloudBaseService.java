/*    */ package io.src.dcloud.adapter;
/*    */ 
/*    */ import android.app.Service;
/*    */ import android.content.Intent;
/*    */ import android.os.IBinder;
/*    */ import io.dcloud.common.DHInterface.IReflectAble;
/*    */ 
/*    */ public class DCloudBaseService extends Service
/*    */   implements IReflectAble
/*    */ {
/*  8 */   public Service that = this;
/*    */ 
/*    */   public final void onCreate()
/*    */   {
/* 18 */     super.onCreate();
/* 19 */     onCreateImpl();
/*    */   }
/*    */ 
/*    */   public void onCreateImpl()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final IBinder onBind(Intent paramIntent)
/*    */   {
/* 36 */     return onBindImpl(paramIntent);
/*    */   }
/*    */ 
/*    */   public IBinder onBindImpl(Intent paramIntent) {
/* 40 */     return null;
/*    */   }
/*    */ 
/*    */   public final void onDestroy()
/*    */   {
/* 51 */     super.onDestroy();
/* 52 */     onDestroyImpl();
/*    */   }
/*    */ 
/*    */   public void onDestroyImpl()
/*    */   {
/*    */   }
/*    */ 
/*    */   public final int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
/*    */   {
/* 69 */     return onStartCommandImpl(paramIntent, paramInt1, paramInt2);
/*    */   }
/*    */ 
/*    */   public int onStartCommandImpl(Intent paramIntent, int paramInt1, int paramInt2) {
/* 73 */     return super.onStartCommand(paramIntent, paramInt1, paramInt2);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.src.dcloud.adapter.DCloudBaseService
 * JD-Core Version:    0.6.2
 */