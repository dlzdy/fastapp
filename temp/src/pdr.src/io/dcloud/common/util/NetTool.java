/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.text.TextUtils;
/*     */ import android.util.Log;
/*     */ import io.dcloud.common.adapter.util.DCloudTrustManager;
/*     */ import io.dcloud.common.constant.StringConst;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.net.URL;
/*     */ import java.net.UnknownHostException;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import javax.net.ssl.HostnameVerifier;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import javax.net.ssl.TrustManager;
/*     */ 
/*     */ public class NetTool
/*     */ {
/*  30 */   private static String TAG = "NetTool";
/*     */ 
/* 213 */   static DCloudTrustManager sCustomTrustManager = null;
/*     */ 
/* 217 */   static HostnameVerifier sCustomeHostnameVerifier = null;
/*     */ 
/*     */   public static byte[] httpGet(String paramString, HashMap<String, String> paramHashMap)
/*     */     throws Exception
/*     */   {
/*  33 */     return request(paramString, null, paramHashMap, "GET");
/*     */   }
/*     */   public static byte[] httpGet(String paramString) {
/*     */     try {
/*  37 */       return httpGet(paramString, null);
/*     */     } catch (Exception localException) {
/*  39 */       if ((((localException instanceof SocketTimeoutException)) || ((localException instanceof UnknownHostException))) && (StringConst.canChangeHost(paramString))) {
/*  40 */         paramString = StringConst.changeHost(paramString);
/*  41 */         return httpGet(paramString);
/*     */       }
/*     */     }
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */   public static byte[] httpGetThrows(String paramString)
/*     */     throws Exception
/*     */   {
/*  53 */     return httpGet(paramString, null);
/*     */   }
/*     */ 
/*     */   private static byte[] request(String paramString1, String paramString2, HashMap<String, String> paramHashMap, String paramString3) {
/*  57 */     if ((paramString1 == null) || (paramString1.length() == 0)) {
/*  58 */       Log.e(TAG, "httpPost, url is null");
/*  59 */       return null;
/*     */     }
/*     */     try {
/*  62 */       HttpURLConnection localHttpURLConnection = createConnection(new URL(paramString1), paramString3);
/*  63 */       if ((paramHashMap != null) && (!paramHashMap.isEmpty())) {
/*  64 */         Set localSet = paramHashMap.keySet();
/*  65 */         for (String str : localSet) {
/*  66 */           localHttpURLConnection.setRequestProperty(str, (String)paramHashMap.get(str));
/*     */         }
/*     */       }
/*  69 */       if ((!TextUtils.isEmpty(paramString3)) && (TextUtils.equals(paramString3.toLowerCase(), "post"))) {
/*  70 */         write(localHttpURLConnection.getOutputStream(), paramString2);
/*     */       }
/*     */ 
/* 100 */       int i = localHttpURLConnection.getResponseCode();
/* 101 */       if (i != 200) {
/* 102 */         Log.e(TAG, "httpGet fail, status code = " + i);
/* 103 */         return null;
/*     */       }
/* 105 */       return read(localHttpURLConnection.getInputStream());
/*     */     } catch (Exception localException) {
/* 107 */       if ((((localException instanceof SocketTimeoutException)) || ((localException instanceof UnknownHostException))) && (StringConst.canChangeHost(paramString1))) {
/* 108 */         paramString1 = StringConst.changeHost(paramString1);
/* 109 */         return httpPost(paramString1, paramString2, paramHashMap);
/*     */       }
/* 111 */       Log.e(TAG, "httpPost exception, e = " + localException.getMessage());
/* 112 */       localException.printStackTrace();
/* 113 */     }return null;
/*     */   }
/*     */ 
/*     */   public static byte[] httpPost(String paramString1, String paramString2, HashMap<String, String> paramHashMap) {
/* 117 */     return request(paramString1, paramString2, paramHashMap, "POST");
/*     */   }
/*     */ 
/*     */   private static void write(OutputStream paramOutputStream, String paramString) {
/*     */     try {
/* 122 */       if ((paramString != null) && (paramString.length() > 0))
/* 123 */         paramOutputStream.write(paramString.getBytes("UTF-8"));
/*     */     }
/*     */     catch (IOException localIOException) {
/* 126 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/* 130 */   private static byte[] read(InputStream paramInputStream) { ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/* 131 */     int i = 10240;
/*     */     try {
/* 133 */       int j = Math.min(i, paramInputStream.available());
/* 134 */       if (j <= 0) {
/* 135 */         j = i;
/*     */       }
/* 137 */       byte[] arrayOfByte = new byte[j];
/*     */       int k;
/* 139 */       while ((k = paramInputStream.read(arrayOfByte)) > 0) {
/* 140 */         localByteArrayOutputStream.write(arrayOfByte, 0, k);
/*     */       }
/* 142 */       paramInputStream.close();
/*     */     } catch (IOException localIOException) {
/* 144 */       localIOException.printStackTrace();
/*     */     }
/* 146 */     return localByteArrayOutputStream.toByteArray();
/*     */   }
/*     */ 
/*     */   public static HttpURLConnection createConnection(URL paramURL, String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 187 */       HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramURL.openConnection();
/* 188 */       if ((localHttpURLConnection instanceof HttpsURLConnection))
/*     */       {
/*     */         try
/*     */         {
/* 192 */           SSLContext localSSLContext = SSLContext.getInstance("TLSv1");
/*     */ 
/* 194 */           localSSLContext.init(null, new TrustManager[] { getDefaultTrustManager() }, new SecureRandom());
/*     */ 
/* 196 */           ((HttpsURLConnection)localHttpURLConnection).setSSLSocketFactory(localSSLContext.getSocketFactory());
/* 197 */           ((HttpsURLConnection)localHttpURLConnection).setHostnameVerifier(getDefaultHostnameVerifier());
/*     */         } catch (Exception localException) {
/* 199 */           throw new RuntimeException(localException);
/*     */         }
/*     */       }
/* 202 */       localHttpURLConnection.setConnectTimeout(5000);
/* 203 */       localHttpURLConnection.setReadTimeout(5000);
/* 204 */       localHttpURLConnection.setRequestMethod(paramString);
/* 205 */       localHttpURLConnection.setDoInput(true);
/* 206 */       return localHttpURLConnection;
/*     */     } catch (IOException localIOException) {
/* 208 */       localIOException.printStackTrace();
/*     */     }
/* 210 */     return null;
/*     */   }
/*     */ 
/*     */   private static DCloudTrustManager getDefaultTrustManager()
/*     */   {
/* 215 */     return sCustomTrustManager != null ? sCustomTrustManager : new DCloudTrustManager();
/*     */   }
/*     */ 
/*     */   private static HostnameVerifier getDefaultHostnameVerifier() {
/* 219 */     return sCustomeHostnameVerifier != null ? sCustomeHostnameVerifier : new HostnameVerifier() {
/*     */       public boolean verify(String paramAnonymousString, SSLSession paramAnonymousSSLSession) {
/* 221 */         return true;
/*     */       }
/*     */     };
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.NetTool
 * JD-Core Version:    0.6.2
 */