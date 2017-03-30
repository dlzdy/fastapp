/*    */ package io.dcloud.common.util.net.http;
/*    */ 
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ import java.io.IOException;
/*    */ import java.net.InetAddress;
/*    */ import java.net.ServerSocket;
/*    */ import java.net.Socket;
/*    */ import java.net.UnknownHostException;
/*    */ 
/*    */ public class LocalServer
/*    */   implements Runnable
/*    */ {
/* 14 */   final String TAG = "miniserver";
/* 15 */   int mPort = 13131;
/*    */ 
/* 17 */   String HOST = "127.0.0.1";
/* 18 */   ServerSocket mServerSocket = null;
/* 19 */   boolean mRunning = false;
/* 20 */   AbsMgr mNetMgr = null;
/*    */ 
/* 22 */   public LocalServer(AbsMgr paramAbsMgr, int paramInt) { this.mPort = paramInt;
/* 23 */     this.mNetMgr = paramAbsMgr;
/*    */     try {
/* 25 */       this.mServerSocket = new ServerSocket(paramInt, 1, InetAddress.getByName(this.HOST));
/*    */     } catch (UnknownHostException localUnknownHostException) {
/* 27 */       localUnknownHostException.printStackTrace();
/*    */     } catch (IOException localIOException) {
/* 29 */       localIOException.printStackTrace();
/*    */     } }
/*    */ 
/*    */   public void start()
/*    */   {
/* 34 */     new Thread(this).start();
/*    */   }
/*    */ 
/*    */   public void stop() {
/* 38 */     if (this.mServerSocket != null) {
/* 39 */       Logger.d("miniserver", "close serversocket port=" + this.mPort);
/*    */       try {
/* 41 */         this.mServerSocket.close();
/* 42 */         this.mServerSocket = null;
/*    */       } catch (IOException localIOException) {
/* 44 */         localIOException.printStackTrace();
/*    */       }
/*    */     }
/* 47 */     this.mRunning = false;
/*    */   }
/*    */ 
/*    */   public void run() {
/* 51 */     this.mRunning = true;
/* 52 */     while (this.mRunning)
/*    */     {
/* 54 */       Logger.d("miniserver", this.mPort + " serversocket running...");
/* 55 */       Socket localSocket = null;
/*    */       try
/*    */       {
/* 58 */         localSocket = this.mServerSocket.accept();
/* 59 */         Logger.d("miniserver", "mPort=" + this.mPort + ";socket=" + localSocket);
/* 60 */         new Response(localSocket, this.mNetMgr);
/*    */       }
/*    */       catch (Exception localException)
/*    */       {
/* 64 */         localException.printStackTrace();
/* 65 */         this.mRunning = false;
/* 66 */         Logger.w("Exception stop mPort=" + this.mPort + ";socket=", localException);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.http.LocalServer
 * JD-Core Version:    0.6.2
 */