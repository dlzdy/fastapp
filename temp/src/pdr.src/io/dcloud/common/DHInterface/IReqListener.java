/*    */ package io.dcloud.common.DHInterface;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public abstract interface IReqListener
/*    */ {
/*    */   public abstract void onNetStateChanged(NetState paramNetState, boolean paramBoolean);
/*    */ 
/*    */   public abstract void onResponsing(InputStream paramInputStream);
/*    */ 
/*    */   public abstract int onReceiving(InputStream paramInputStream)
/*    */     throws Exception;
/*    */ 
/*    */   public static enum NetState
/*    */   {
/*  7 */     NET_INIT, 
/*  8 */     NET_REQUEST_BEGIN, 
/*  9 */     NET_TIMEOUT, 
/* 10 */     NET_CONNECTED, 
/* 11 */     NET_ERROR, 
/* 12 */     NET_HANDLE_BEGIN, 
/* 13 */     NET_HANDLE_ING, 
/* 14 */     NET_HANDLE_END, 
/* 15 */     NET_PAUSE;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IReqListener
 * JD-Core Version:    0.6.2
 */