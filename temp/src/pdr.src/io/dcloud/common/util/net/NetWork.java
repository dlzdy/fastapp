/*     */ package io.dcloud.common.util.net;
/*     */ 
/*     */ import android.webkit.CookieManager;
/*     */ import io.dcloud.common.DHInterface.IReqListener;
/*     */ import io.dcloud.common.DHInterface.IReqListener.NetState;
/*     */ import io.dcloud.common.DHInterface.IResponseListener;
/*     */ import io.dcloud.common.adapter.util.DCloudTrustManager;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.net.URI;
/*     */ import java.net.UnknownHostException;
/*     */ import java.security.KeyManagementException;
/*     */ import java.security.KeyStore;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.UnrecoverableKeyException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import org.apache.http.Header;
/*     */ import org.apache.http.HttpEntity;
/*     */ import org.apache.http.HttpResponse;
/*     */ import org.apache.http.HttpVersion;
/*     */ import org.apache.http.ProtocolException;
/*     */ import org.apache.http.StatusLine;
/*     */ import org.apache.http.auth.AuthScope;
/*     */ import org.apache.http.auth.UsernamePasswordCredentials;
/*     */ import org.apache.http.client.CredentialsProvider;
/*     */ import org.apache.http.client.RedirectHandler;
/*     */ import org.apache.http.client.methods.HttpRequestBase;
/*     */ import org.apache.http.client.params.HttpClientParams;
/*     */ import org.apache.http.conn.ClientConnectionManager;
/*     */ import org.apache.http.conn.ConnectTimeoutException;
/*     */ import org.apache.http.conn.ConnectionPoolTimeoutException;
/*     */ import org.apache.http.conn.params.ConnPerRouteBean;
/*     */ import org.apache.http.conn.scheme.PlainSocketFactory;
/*     */ import org.apache.http.conn.scheme.Scheme;
/*     */ import org.apache.http.conn.scheme.SchemeRegistry;
/*     */ import org.apache.http.conn.scheme.SocketFactory;
/*     */ import org.apache.http.impl.client.BasicCredentialsProvider;
/*     */ import org.apache.http.impl.client.DefaultHttpClient;
/*     */ import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
/*     */ import org.apache.http.params.BasicHttpParams;
/*     */ import org.apache.http.params.HttpConnectionParams;
/*     */ import org.apache.http.params.HttpParams;
/*     */ import org.apache.http.params.HttpProtocolParams;
/*     */ import org.apache.http.protocol.HttpContext;
/*     */ import org.apache.http.util.EntityUtils;
/*     */ 
/*     */ public class NetWork
/*     */   implements Runnable
/*     */ {
/*     */   private static final String DEFALUT_CHARSET = "UTF-8";
/*     */   private static final String PARAM_CHARSET = ";charset=";
/*     */   private static final String CONTENT_TYPE_UPLOAD = "application/x-www-form-urlencoded";
/*     */   private static final String CONTENT_TYPE_COMMON = "text/plain;charset=UTF-8";
/*  85 */   NetWorkLoop mNetWorkLoop = null;
/*     */   private int mWorkType;
/*     */   public static final int WORK_UPLOAD = 1;
/*     */   public static final int WORK_DOWNLOAD = 2;
/*     */   public static final int WORK_COMMON = 3;
/*     */   DefaultHttpClient mHttpClient;
/*  96 */   public int mTimes = 1;
/*     */ 
/*  98 */   public int MAX_TIMES = 3;
/*     */ 
/* 103 */   public static long AUTO_RECONNECTTIME = 30000L;
/*     */ 
/* 105 */   protected long mRetryIntervalTime = AUTO_RECONNECTTIME;
/*     */   protected RequestData mRequestData;
/*     */   protected HttpRequestBase mRequest;
/*     */   protected String mMainBoundry;
/*     */   protected IReqListener mReqListener;
/*     */   protected IResponseListener mResponseListener;
/*     */   protected String mResponseText;
/*     */   protected InputStream mResponseInput;
/*     */   protected HttpResponse mResponse;
/*     */   protected Map<String, String> mHeaderList;
/*     */   public int mPriority;
/*     */   protected boolean isAbort;
/*     */ 
/*     */   public NetWork(int paramInt, RequestData paramRequestData, IReqListener paramIReqListener, IResponseListener paramIResponseListener)
/*     */   {
/* 163 */     this.mWorkType = paramInt;
/* 164 */     this.mRequestData = paramRequestData;
/* 165 */     this.mReqListener = paramIReqListener;
/* 166 */     this.mResponseListener = paramIResponseListener;
/* 167 */     this.mHeaderList = new HashMap();
/* 168 */     this.mMainBoundry = getBoundry();
/*     */   }
/*     */ 
/*     */   public void startWork()
/*     */   {
/* 179 */     Thread localThread = new Thread(this);
/* 180 */     localThread.setPriority(1);
/* 181 */     localThread.start();
/* 182 */     this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_INIT, this.isAbort);
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     Object localObject1;
/*     */     try {
/* 189 */       this.mRequest = this.mRequestData.getHttpRequest();
/*     */     } catch (IllegalArgumentException localIllegalArgumentException) {
/* 191 */       localObject1 = localIllegalArgumentException.getMessage();
/* 192 */       this.mResponseListener.onResponseState(-1, (String)localObject1);
/* 193 */       this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
/* 194 */       this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_ERROR, this.isAbort);
/* 195 */       if (this.mNetWorkLoop != null) {
/* 196 */         this.mNetWorkLoop.removeNetWork(this);
/*     */       }
/* 198 */       return;
/*     */     }
/* 200 */     if (!this.mRequestData.containHeader("Content-Type")) {
/* 201 */       if (this.mWorkType == 1)
/* 202 */         this.mRequest.addHeader("Content-Type", "application/x-www-form-urlencoded");
/* 203 */       else if (this.mWorkType != 2)
/*     */       {
/* 205 */         this.mRequest.addHeader("Content-Type", "text/plain;charset=UTF-8");
/*     */       }
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 211 */       this.mHttpClient = createHttpClient();
/*     */ 
/* 213 */       this.mHttpClient.setRedirectHandler(new RedirectHandler()
/*     */       {
/*     */         public boolean isRedirectRequested(HttpResponse paramAnonymousHttpResponse, HttpContext paramAnonymousHttpContext)
/*     */         {
/* 218 */           return false;
/*     */         }
/*     */ 
/*     */         public URI getLocationURI(HttpResponse paramAnonymousHttpResponse, HttpContext paramAnonymousHttpContext)
/*     */           throws ProtocolException
/*     */         {
/* 224 */           return null;
/*     */         }
/*     */       });
/* 227 */       if (!this.mRequestData.isRedirect) {
/* 228 */         this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_REQUEST_BEGIN, this.isAbort);
/*     */       }
/*     */ 
/* 232 */       HttpResponse localHttpResponse = executeHttpRequest(this.mRequest);
/*     */ 
/* 234 */       if (!this.mRequestData.isRedirect) {
/* 235 */         this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_BEGIN, this.isAbort);
/*     */       }
/*     */ 
/* 238 */       localObject1 = localHttpResponse.getLastHeader("Set-Cookie");
/* 239 */       if (null != localObject1) {
/* 240 */         String str1 = ((Header)localObject1).getValue();
/* 241 */         if (!PdrUtil.isEmpty(str1)) {
/* 242 */           CookieManager.getInstance().setCookie(this.mRequestData.getUrl(), str1);
/*     */         }
/*     */       }
/*     */ 
/* 246 */       int j = localHttpResponse.getStatusLine().getStatusCode();
/*     */ 
/* 248 */       String str3 = localHttpResponse.getStatusLine().getReasonPhrase();
/* 249 */       this.mResponseListener.onResponseState(j, str3);
/*     */       Object localObject2;
/* 251 */       switch (j) {
/*     */       case 200:
/* 253 */         setHeadersAndValues(localHttpResponse.getAllHeaders());
/* 254 */         this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
/* 255 */         localObject2 = localHttpResponse.getEntity();
/* 256 */         switch (this.mWorkType) {
/*     */         case 2:
/* 258 */           this.mResponseInput = getUngzippedContent((HttpEntity)localObject2);
/* 259 */           this.mReqListener.onResponsing(this.mResponseInput);
/* 260 */           break;
/*     */         default:
/* 262 */           handleResponseText(localHttpResponse, (HttpEntity)localObject2);
/*     */         }
/*     */ 
/* 266 */         this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
/* 267 */         break;
/*     */       case 301:
/*     */       case 302:
/*     */       case 303:
/* 273 */         localObject2 = localHttpResponse.getHeaders("Location");
/* 274 */         if ((localObject2 != null) && (localObject2.length > 0)) { String str4 = localObject2[0].getValue();
/* 276 */           System.out.println("重定向的URL:" + str4);
/* 277 */           str4 = str4.replace(" ", "%20");
/* 278 */           this.mRequestData.clearData();
/* 279 */           this.mRequestData.setUrl(str4);
/* 280 */           run();
/*     */           return; } break;
/*     */       case 400:
/*     */       case 401:
/*     */       case 404:
/*     */       case 500:
/*     */       default:
/* 290 */         setHeadersAndValues(localHttpResponse.getAllHeaders());
/* 291 */         this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
/* 292 */         localObject2 = localHttpResponse.getEntity();
/* 293 */         handleResponseText(localHttpResponse, (HttpEntity)localObject2);
/* 294 */         this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
/*     */       }
/*     */ 
/* 298 */       this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_END, this.isAbort);
/*     */     } catch (Exception localException) {
/* 300 */       localException.printStackTrace();
/* 301 */       int i = 0;
/* 302 */       String str2 = localException.getMessage();
/* 303 */       this.mResponseListener.onResponseState(i, str2);
/* 304 */       this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_HANDLE_ING, this.isAbort);
/* 305 */       if (((localException instanceof ConnectTimeoutException)) || ((localException instanceof ConnectionPoolTimeoutException)) || ((localException instanceof SocketTimeoutException)))
/*     */       {
/* 308 */         this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_TIMEOUT, this.isAbort);
/*     */       }
/* 310 */       else this.mReqListener.onNetStateChanged(IReqListener.NetState.NET_ERROR, this.isAbort);
/*     */     }
/*     */ 
/* 313 */     if (this.mNetWorkLoop != null)
/* 314 */       this.mNetWorkLoop.removeNetWork(this);
/*     */   }
/*     */ 
/*     */   public static String getPAuth()
/*     */   {
/* 321 */     return InvokeExecutorHelper.TrafficFreeHelper.invoke("getPAuth");
/*     */   }
/*     */ 
/*     */   private String getCharset(String paramString) {
/* 325 */     String str = null;
/* 326 */     if (paramString != null) {
/* 327 */       paramString = paramString.replace(" ", "");
/* 328 */       if (paramString.contains(";charset=")) {
/* 329 */         str = paramString.substring(paramString.indexOf(";charset=") + ";charset=".length());
/*     */       }
/*     */     }
/* 332 */     return str;
/*     */   }
/*     */ 
/*     */   public void handleResponseText(HttpResponse paramHttpResponse, HttpEntity paramHttpEntity) throws IOException {
/*     */     try {
/* 337 */       String str1 = getCharset(this.mRequestData.mOverrideMimeType);
/* 338 */       if (str1 == null) {
/* 339 */         String str2 = paramHttpResponse.getFirstHeader("Content-Type").getValue();
/* 340 */         str1 = getCharset(str2);
/*     */       }
/* 342 */       if (str1 == null) {
/* 343 */         str1 = "UTF-8";
/*     */       }
/* 345 */       this.mResponseText = EntityUtils.toString(paramHttpEntity, str1);
/*     */     } catch (Exception localException) {
/* 347 */       localException.printStackTrace();
/* 348 */       this.mResponseText = EntityUtils.toString(paramHttpEntity, "UTF-8");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void cancelWork()
/*     */   {
/* 360 */     this.isAbort = true;
/* 361 */     if (this.mRequest != null) {
/* 362 */       this.mRequest.abort();
/* 363 */       this.mRequest = null;
/*     */     }
/* 365 */     if (this.mHttpClient != null) {
/* 366 */       this.mHttpClient.getConnectionManager().shutdown();
/* 367 */       this.mHttpClient = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void dispose()
/*     */   {
/*     */   }
/*     */ 
/*     */   public HttpResponse executeHttpRequest(HttpRequestBase paramHttpRequestBase)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 394 */       this.mHttpClient.getConnectionManager().closeExpiredConnections();
/* 395 */       return this.mHttpClient.execute(paramHttpRequestBase);
/*     */     } catch (IOException localIOException) {
/* 397 */       paramHttpRequestBase.abort();
/* 398 */       throw localIOException;
/*     */     }
/*     */   }
/*     */ 
/*     */   public DefaultHttpClient createHttpClient()
/*     */   {
/*     */     try
/*     */     {
/* 423 */       SchemeRegistry localSchemeRegistry = new SchemeRegistry();
/*     */ 
/* 425 */       if (this.mRequestData.URL_METHOD.equals("https")) {
/* 426 */         localObject1 = null;
/* 427 */         localObject2 = KeyStore.getInstance(KeyStore.getDefaultType());
/* 428 */         ((KeyStore)localObject2).load(null, null);
/* 429 */         if ((PdrUtil.isEquals(this.mRequestData.unTrustedCAType, "refuse")) || (PdrUtil.isEquals(this.mRequestData.unTrustedCAType, "warning"))) {
/* 430 */           localObject1 = new org.apache.http.conn.ssl.SSLSocketFactory((KeyStore)localObject2);
/* 431 */           ((org.apache.http.conn.ssl.SSLSocketFactory)localObject1).setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
/*     */         }
/*     */         else {
/* 434 */           localObject1 = new SSLSocketFactoryEx((KeyStore)localObject2);
/* 435 */           ((org.apache.http.conn.ssl.SSLSocketFactory)localObject1).setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
/*     */         }
/* 437 */         localSchemeRegistry.register(new Scheme("https", (SocketFactory)localObject1, 443));
/*     */       } else {
/* 439 */         localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
/*     */       }
/*     */ 
/* 443 */       Object localObject1 = createHttpParams();
/*     */ 
/* 445 */       HttpClientParams.setRedirecting((HttpParams)localObject1, true);
/*     */ 
/* 447 */       Object localObject2 = new ThreadSafeClientConnManager((HttpParams)localObject1, localSchemeRegistry);
/*     */ 
/* 450 */       return new DefaultHttpClient((ClientConnectionManager)localObject2, (HttpParams)localObject1); } catch (Exception localException) {
/*     */     }
/* 452 */     return new DefaultHttpClient();
/*     */   }
/*     */ 
/*     */   public HttpParams createHttpParams()
/*     */   {
/* 460 */     BasicHttpParams localBasicHttpParams = new BasicHttpParams();
/*     */ 
/* 464 */     HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, this.mRequestData.mTimeout);
/* 465 */     HttpConnectionParams.setSoTimeout(localBasicHttpParams, this.mRequestData.mTimeout);
/*     */ 
/* 467 */     HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
/* 468 */     HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
/* 469 */     if (this.mRequestData.URL_METHOD.equals("https"))
/*     */     {
/* 471 */       localBasicHttpParams.setParameter("http.conn-manager.max-total", Integer.valueOf(5));
/* 472 */       localBasicHttpParams.setParameter("http.conn-manager.max-per-route", new ConnPerRouteBean(3));
/* 473 */       localBasicHttpParams.setParameter("http.protocol.expect-continue", Boolean.valueOf(false));
/* 474 */       localBasicHttpParams.setBooleanParameter("http.protocol.expect-continue", false);
/* 475 */       BasicCredentialsProvider localBasicCredentialsProvider = new BasicCredentialsProvider();
/* 476 */       localBasicCredentialsProvider.setCredentials(new AuthScope(this.mRequestData.getIP(), -1), new UsernamePasswordCredentials("admin", "password"));
/*     */     }
/*     */ 
/* 479 */     return localBasicHttpParams;
/*     */   }
/*     */ 
/*     */   public static InputStream getUngzippedContent(HttpEntity paramHttpEntity)
/*     */     throws IOException
/*     */   {
/* 498 */     Object localObject = paramHttpEntity.getContent();
/* 499 */     if (localObject == null) {
/* 500 */       return localObject;
/*     */     }
/* 502 */     Header localHeader = paramHttpEntity.getContentEncoding();
/* 503 */     if (localHeader == null) {
/* 504 */       return localObject;
/*     */     }
/* 506 */     String str = localHeader.getValue();
/* 507 */     if (str == null) {
/* 508 */       return localObject;
/*     */     }
/* 510 */     if (str.contains("gzip")) {
/* 511 */       localObject = new GZIPInputStream((InputStream)localObject);
/*     */     }
/* 513 */     return localObject;
/*     */   }
/*     */ 
/*     */   public static String getBoundry()
/*     */   {
/* 520 */     StringBuffer localStringBuffer = new StringBuffer("------");
/* 521 */     for (int i = 1; i < 7; i++) {
/* 522 */       long l = System.currentTimeMillis() + i;
/* 523 */       if (l % 3L == 0L)
/* 524 */         localStringBuffer.append((char)(int)l % '\t');
/* 525 */       else if (l % 3L == 1L)
/* 526 */         localStringBuffer.append((char)(int)(65L + l % 26L));
/*     */       else {
/* 528 */         localStringBuffer.append((char)(int)(97L + l % 26L));
/*     */       }
/*     */     }
/* 531 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   private void setHeadersAndValues(Header[] paramArrayOfHeader)
/*     */   {
/* 544 */     for (Header localHeader : paramArrayOfHeader)
/* 545 */       this.mHeaderList.put(localHeader.getName(), localHeader.getValue());
/*     */   }
/*     */ 
/*     */   public Map<String, String> getHeadersAndValues()
/*     */   {
/* 558 */     return this.mHeaderList;
/*     */   }
/*     */ 
/*     */   public String getResponseText()
/*     */   {
/* 565 */     return this.mResponseText;
/*     */   }
/*     */ 
/*     */   public InputStream getResponseInput()
/*     */   {
/* 572 */     return this.mResponseInput;
/*     */   }
/*     */ 
/*     */   public void setRetryIntervalTime(long paramLong)
/*     */   {
/* 580 */     if (paramLong > 0L)
/* 581 */       this.mRetryIntervalTime = paramLong;
/*     */   }
/*     */ 
/*     */   private static class SSLSocketFactoryEx extends org.apache.http.conn.ssl.SSLSocketFactory
/*     */   {
/* 587 */     SSLContext sslContext = SSLContext.getInstance("TLS");
/*     */ 
/*     */     public SSLSocketFactoryEx(KeyStore paramKeyStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
/* 590 */       super();
/*     */ 
/* 592 */       DCloudTrustManager localDCloudTrustManager = new DCloudTrustManager();
/*     */ 
/* 594 */       this.sslContext.init(null, new TrustManager[] { localDCloudTrustManager }, null);
/*     */     }
/*     */ 
/*     */     public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean) throws IOException, UnknownHostException
/*     */     {
/* 599 */       return this.sslContext.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
/*     */     }
/*     */ 
/*     */     public Socket createSocket() throws IOException
/*     */     {
/* 604 */       return this.sslContext.getSocketFactory().createSocket();
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.NetWork
 * JD-Core Version:    0.6.2
 */