/*    */ package io.dcloud.common.util.net.http;
/*    */ 
/*    */ import android.os.AsyncTask;
/*    */ import android.util.Log;
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.ServerSocket;
/*    */ import java.net.Socket;
/*    */ 
/*    */ public class SNCListener
/*    */ {
/* 16 */   public static String TAG = "SNCListener";
/*    */   ServerSocket server;
/*    */   static final int PORT = 13132;
/*    */   AbsMgr mNetMgr;
/*    */ 
/*    */   public SNCListener()
/*    */   {
/* 17 */     this.server = null;
/*    */ 
/* 19 */     this.mNetMgr = null;
/*    */   }
/*    */   public void start(AbsMgr paramAbsMgr) {
/*    */     try { this.mNetMgr = paramAbsMgr;
/* 23 */       this.server = new ServerSocket(13132);
/*    */     } catch (IOException localIOException) {
/* 25 */       localIOException.printStackTrace();
/* 26 */       return;
/*    */     }
/* 28 */     new RepackTestTask(null).execute(new Object[0]);
/*    */   }
/*    */   private class RepackTestTask extends AsyncTask {
/*    */     private RepackTestTask() {
/*    */     }
/*    */     protected Object doInBackground(Object[] paramArrayOfObject) {
/* 34 */       Socket localSocket = null;
/*    */ 
/* 36 */       Log.d(SNCListener.TAG, "listening port(13132)");
/*    */       while (true)
/*    */         try
/*    */         {
/* 40 */           localSocket = SNCListener.this.server.accept();
/* 41 */           Log.d(SNCListener.TAG, "Get a connection from " + localSocket.getRemoteSocketAddress().toString());
/*    */ 
/* 43 */           InputStream localInputStream = localSocket.getInputStream();
/* 44 */           byte[] arrayOfByte = new byte[localInputStream.available()];
/* 45 */           localInputStream.read(arrayOfByte);
/* 46 */           String str = new String(arrayOfByte);
/* 47 */           Log.d(SNCListener.TAG, "Get some words" + str);
/* 48 */           publishProgress(new Object[] { str });
/* 49 */           localSocket.close();
/*    */         } catch (IOException localIOException) {
/* 51 */           Log.e(SNCListener.TAG, "" + localIOException);
/*    */         }
/*    */         catch (Exception localException) {
/* 54 */           localException.printStackTrace();
/*    */         }
/*    */     }
/*    */ 
/*    */     protected void onProgressUpdate(Object[] paramArrayOfObject)
/*    */     {
/* 60 */       super.onProgressUpdate(paramArrayOfObject);
/* 61 */       String str = paramArrayOfObject[0].toString();
/* 62 */       SNCListener.this.mNetMgr.processEvent(IMgr.MgrType.AppMgr, 7, str);
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.http.SNCListener
 * JD-Core Version:    0.6.2
 */