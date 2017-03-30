/*    */ package io.dcloud.common.adapter.util;
/*    */ 
/*    */ import java.security.cert.CertificateException;
/*    */ import java.security.cert.X509Certificate;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ 
/*    */ public class DCloudTrustManager
/*    */   implements X509TrustManager
/*    */ {
/*    */   public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
/*    */     throws CertificateException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
/*    */     throws CertificateException
/*    */   {
/*    */   }
/*    */ 
/*    */   public X509Certificate[] getAcceptedIssuers()
/*    */   {
/* 28 */     return new X509Certificate[0];
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.DCloudTrustManager
 * JD-Core Version:    0.6.2
 */