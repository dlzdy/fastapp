/*    */ package io.dcloud.common.util.net.http;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class Request
/*    */ {
/*    */   private InputStream input;
/*    */   private String uri;
/*    */   private String mData;
/*    */ 
/*    */   public Request(InputStream paramInputStream)
/*    */   {
/* 12 */     this.input = paramInputStream;
/*    */   }
/*    */   public void parse() {
/* 17 */     StringBuffer localStringBuffer = new StringBuffer(2048);
/*    */ 
/* 19 */     byte[] arrayOfByte = new byte[2048];
/*    */     int i;
/*    */     try { i = this.input.read(arrayOfByte);
/*    */     } catch (IOException localIOException) {
/* 23 */       localIOException.printStackTrace();
/* 24 */       i = -1;
/*    */     }
/* 26 */     for (int j = 0; j < i; j++) {
/* 27 */       localStringBuffer.append((char)arrayOfByte[j]);
/*    */     }
/* 29 */     this.mData = localStringBuffer.toString();
/* 30 */     this.uri = parseUri(this.mData);
/*    */ 
/* 32 */     this.uri = ((this.uri != null) && (this.uri.startsWith("/")) ? this.uri.substring(1) : this.uri);
/*    */   }
/*    */ 
/*    */   private String parseUri(String paramString)
/*    */   {
/* 37 */     int i = paramString.indexOf(' ');
/* 38 */     if (i != -1) {
/* 39 */       int j = paramString.indexOf(' ', i + 1);
/* 40 */       if (j > i)
/* 41 */         return paramString.substring(i + 1, j);
/*    */     }
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */   public String getUri() {
/* 47 */     return this.uri;
/*    */   }
/*    */ 
/*    */   public String getData() {
/* 51 */     return this.mData;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.http.Request
 * JD-Core Version:    0.6.2
 */