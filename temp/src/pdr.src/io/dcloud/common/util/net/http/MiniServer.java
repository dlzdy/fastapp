/*    */ package io.dcloud.common.util.net.http;
/*    */ 
/*    */ import android.content.Context;
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.adapter.io.AdaService;
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ import java.io.IOException;
/*    */ import java.net.InetAddress;
/*    */ import java.net.ServerSocket;
/*    */ import java.net.Socket;
/*    */ import java.net.UnknownHostException;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MiniServer extends AdaService
/*    */ {
/* 18 */   final String TAG = "miniserver";
/*    */ 
/* 20 */   final int mPort = 13130;
/*    */ 
/* 22 */   final String HOST = "127.0.0.1";
/*    */ 
/* 24 */   AbsMgr mNetMgr = null;
/*    */ 
/* 30 */   ArrayList<Server> mServerSockets = null;
/*    */ 
/*    */   public MiniServer(Context paramContext, AbsMgr paramAbsMgr)
/*    */   {
/* 26 */     super(paramContext, "mini_server");
/* 27 */     this.mNetMgr = paramAbsMgr;
/* 28 */     this.mServerSockets = new ArrayList(1);
/*    */   }
/*    */ 
/*    */   public void onExecute()
/*    */   {
/* 33 */     Server localServer = new Server(13130, "127.0.0.1");
/* 34 */     if (localServer.mServerSocket != null) {
/* 35 */       Logger.d("miniserver", "onExecute _server=" + localServer);
/* 36 */       localServer.start();
/* 37 */       this.mServerSockets.add(localServer);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void onDestroy()
/*    */   {
/* 45 */     for (Server localServer : this.mServerSockets) {
/* 46 */       Logger.d("miniserver", "onDestroy _server=" + localServer + ";PORT=" + 13130);
/* 47 */       localServer.stop();
/*    */     }
/*    */   }
/*    */ 
/*    */   class Server implements Runnable
/*    */   {
/* 53 */     boolean mRunning = false;
/* 54 */     ServerSocket mServerSocket = null;
/*    */ 
/*    */     public Server(int paramString, String arg3)
/*    */     {
/*    */       try
/*    */       {
/*    */         String str;
/* 57 */         this.mServerSocket = new ServerSocket(paramString, 1, InetAddress.getByName(str));
/*    */       } catch (UnknownHostException localUnknownHostException) {
/* 59 */         localUnknownHostException.printStackTrace();
/*    */       } catch (IOException localIOException) {
/* 61 */         localIOException.printStackTrace();
/*    */       }
/*    */     }
/*    */ 
/*    */     void start() {
/* 66 */       new Thread(this).start();
/*    */     }
/*    */ 
/*    */     void stop()
/*    */     {
/* 71 */       if (this.mServerSocket != null) {
/* 72 */         Logger.d("miniserver", "close serversocket port=13130");
/*    */         try {
/* 74 */           this.mServerSocket.close();
/* 75 */           this.mServerSocket = null;
/*    */         } catch (IOException localIOException) {
/* 77 */           localIOException.printStackTrace();
/*    */         }
/*    */       }
/* 80 */       this.mRunning = false;
/*    */     }
/*    */ 
/*    */     public void run() {
/* 84 */       this.mRunning = true;
/* 85 */       while (this.mRunning)
/*    */       {
/* 87 */         Logger.d("miniserver", "13130;serversocket running...");
/* 88 */         Socket localSocket = null;
/*    */         try
/*    */         {
/* 91 */           localSocket = this.mServerSocket.accept();
/* 92 */           Logger.d("miniserver", "mPort=13130;socket=" + localSocket);
/* 93 */           new Response(localSocket, MiniServer.this.mNetMgr);
/*    */         }
/*    */         catch (Exception localException)
/*    */         {
/* 97 */           localException.printStackTrace();
/* 98 */           this.mRunning = false;
/* 99 */           Logger.d("miniserver", new Object[] { "Exception stop mPort=13130;socket=" + localSocket, localException });
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.http.MiniServer
 * JD-Core Version:    0.6.2
 */