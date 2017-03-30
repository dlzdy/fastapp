/*     */ package io.dcloud.common.util.net.http;
/*     */ 
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.util.IOUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.Socket;
/*     */ 
/*     */ public class Response
/*     */   implements Runnable
/*     */ {
/*  21 */   Socket mSocket = null;
/*  22 */   AbsMgr mNetMgr = null;
/*  23 */   String mUrl = null;
/*     */ 
/*  32 */   final int BUFFER_SIZE = 10240;
/*     */   private static final byte CR = 13;
/*     */   private static final byte LF = 10;
/* 151 */   private static final byte[] CRLF = { 13, 10 };
/*     */ 
/*     */   public Response(Socket paramSocket, AbsMgr paramAbsMgr)
/*     */   {
/*  26 */     this.mSocket = paramSocket;
/*  27 */     this.mNetMgr = paramAbsMgr;
/*  28 */     new Thread(this).start();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*  34 */     InputStream localInputStream1 = null;
/*  35 */     OutputStream localOutputStream = null;
/*  36 */     InputStream localInputStream2 = null;
/*     */     try
/*     */     {
/*  39 */       localInputStream1 = this.mSocket.getInputStream();
/*  40 */       Request localRequest = new Request(localInputStream1);
/*  41 */       localRequest.parse();
/*  42 */       String str1 = localRequest.getData();
/*  43 */       if (str1.startsWith("snc:")) {
/*  44 */         this.mNetMgr.processEvent(IMgr.MgrType.AppMgr, 7, str1);
/*  45 */       } else if (str1.startsWith("sconn:")) {
/*  46 */         str1 = str1.substring("sconn:".length());
/*  47 */         String str2 = "";
/*  48 */         localOutputStream = this.mSocket.getOutputStream();
/*  49 */         if (PdrUtil.isEquals(str1, "request_root_path")) {
/*  50 */           str2 = DeviceInfo.sDeviceRootDir;
/*     */         }
/*  52 */         Logger.d("miniserver", new Object[] { str1, str2 });
/*  53 */         localOutputStream.write(str2.getBytes());
/*     */       } else {
/*  55 */         this.mUrl = localRequest.getUri();
/*  56 */         if (this.mUrl == null) {
/*     */           return;
/*     */         }
/*  59 */         localOutputStream = this.mSocket.getOutputStream();
/*  60 */         byte[] arrayOfByte = new byte[10240];
/*  61 */         if (this.mUrl.startsWith("_res/"))
/*  62 */           localInputStream2 = PlatformUtil.getResInputStream("res/" + this.mUrl.substring("_res/".length()));
/*     */         else
/*  64 */           localInputStream2 = (InputStream)this.mNetMgr.processEvent(IMgr.MgrType.AppMgr, 2, this.mUrl);
/*     */         Object localObject1;
/*  68 */         if (localInputStream2 != null) {
/*  69 */           localObject1 = new ByteArrayOutputStream();
/*     */           int i;
/*  72 */           while ((i = localInputStream2.read(arrayOfByte)) > 0) {
/*  73 */             ((ByteArrayOutputStream)localObject1).write(arrayOfByte, 0, i);
/*     */           }
/*     */ 
/*  76 */           arrayOfByte = ((ByteArrayOutputStream)localObject1).toByteArray();
/*  77 */           addResponseHead(arrayOfByte.length, localOutputStream);
/*  78 */           localOutputStream.write(arrayOfByte);
/*     */         } else {
/*  80 */           Logger.i("miniserver", "error url=" + this.mUrl);
/*     */ 
/*  82 */           localObject1 = "HTTP/1.1 404 File Not Found\r\nContent-Type: text/html\r\nContent-Length: 23\r\n\r\n<h1>File Not Found</h1>";
/*     */ 
/*  86 */           localOutputStream.write(((String)localObject1).getBytes());
/*     */         }
/*     */       }
/*     */     } catch (IOException localIOException) {
/*  90 */       localIOException.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  93 */         IOUtil.close(localInputStream1);
/*  94 */         IOUtil.close(localInputStream2);
/*  95 */         IOUtil.close(localOutputStream);
/*  96 */         this.mSocket.close();
/*     */       } catch (Exception localException4) {
/*  98 */         localException4.printStackTrace();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void writeRequest(OutputStream paramOutputStream, String paramString) throws IOException
/*     */   {
/* 105 */     paramOutputStream.write("GET /index.html HTTP/1.1".getBytes());
/* 106 */     paramOutputStream.write(CRLF);
/*     */ 
/* 108 */     paramOutputStream.write(("Host: " + paramString).getBytes());
/* 109 */     paramOutputStream.write(CRLF);
/*     */ 
/* 111 */     paramOutputStream.write(CRLF);
/*     */ 
/* 113 */     paramOutputStream.flush();
/*     */   }
/*     */ 
/*     */   private void write(OutputStream paramOutputStream, String paramString) throws IOException
/*     */   {
/* 118 */     paramOutputStream.write(paramString.getBytes());
/*     */   }
/*     */ 
/*     */   private void addResponseHead(long paramLong, OutputStream paramOutputStream) throws IOException
/*     */   {
/* 123 */     writeHeader(paramOutputStream, "HTTP/1.1 200 OK");
/* 124 */     writeHeader(paramOutputStream, "Content-Type: " + PdrUtil.getMimeType(this.mUrl));
/* 125 */     writeHeader(paramOutputStream, "Access-Control-Allow-Origin: *");
/* 126 */     writeHeader(paramOutputStream, "Access-Control-Allow-Headers: *");
/*     */ 
/* 140 */     writeHeader(paramOutputStream, "Content-Length: " + paramLong);
/* 141 */     paramOutputStream.write(CRLF);
/* 142 */     paramOutputStream.flush();
/*     */   }
/*     */ 
/*     */   void writeHeader(OutputStream paramOutputStream, String paramString) throws IOException {
/* 146 */     write(paramOutputStream, paramString);
/* 147 */     paramOutputStream.write(CRLF);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.http.Response
 * JD-Core Version:    0.6.2
 */