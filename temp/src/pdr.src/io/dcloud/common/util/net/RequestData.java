/*     */ package io.dcloud.common.util.net;
/*     */ 
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.SequenceInputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import org.apache.http.NameValuePair;
/*     */ import org.apache.http.client.methods.HttpDelete;
/*     */ import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
/*     */ import org.apache.http.client.methods.HttpGet;
/*     */ import org.apache.http.client.methods.HttpHead;
/*     */ import org.apache.http.client.methods.HttpOptions;
/*     */ import org.apache.http.client.methods.HttpPost;
/*     */ import org.apache.http.client.methods.HttpPut;
/*     */ import org.apache.http.client.methods.HttpRequestBase;
/*     */ import org.apache.http.client.methods.HttpTrace;
/*     */ import org.apache.http.client.utils.URLEncodedUtils;
/*     */ import org.apache.http.entity.InputStreamEntity;
/*     */ import org.apache.http.message.BasicNameValuePair;
/*     */ 
/*     */ public class RequestData
/*     */ {
/*     */   private HashMap<String, String> mNameValue;
/*     */   private HashMap<String, File> mFiles;
/*     */   private HashMap<String, String> mHeads;
/*     */   private ArrayList<String> mBodys;
/*  67 */   public String URL_METHOD = "http";
/*     */   public static final String URL_HTTP = "http";
/*     */   public static final String URL_HTTPS = "https";
/*  71 */   public String unTrustedCAType = "accept";
/*     */   private String mUrl;
/*     */   private String mReqmethod;
/*     */   private String mIp;
/*     */   private String mPort;
/*     */   private long mContentLength;
/*  91 */   public boolean isRedirect = false;
/*     */   private HttpRequestBase mHttpRequest;
/*  96 */   public int mTimeout = 120000;
/*     */ 
/*  98 */   public String mOverrideMimeType = null;
/*     */ 
/*     */   public RequestData(String paramString1, String paramString2)
/*     */   {
/* 109 */     this.mUrl = paramString1;
/* 110 */     this.mReqmethod = paramString2;
/* 111 */     if ((this.mUrl != null) && (this.mUrl.startsWith("https"))) {
/* 112 */       this.URL_METHOD = "https";
/* 113 */       initIpAndPort();
/*     */     }
/* 115 */     this.mNameValue = new HashMap();
/* 116 */     this.mHeads = new HashMap();
/* 117 */     this.mFiles = new HashMap();
/* 118 */     this.mBodys = new ArrayList();
/*     */   }
/*     */ 
/*     */   public String getReqmethod()
/*     */   {
/* 125 */     return this.mReqmethod;
/*     */   }
/*     */ 
/*     */   public void setReqmethod(String paramString)
/*     */   {
/* 132 */     this.mReqmethod = paramString;
/*     */   }
/*     */ 
/*     */   public boolean addParemeter(String paramString1, String paramString2)
/*     */   {
/* 147 */     boolean bool = false;
/* 148 */     if ((!PdrUtil.isEmpty(paramString1)) && (!PdrUtil.isEmpty(paramString2)) && 
/* 149 */       (!this.mFiles.containsKey(paramString1)) && (!this.mNameValue.containsKey(paramString1))) {
/* 150 */       this.mNameValue.put(paramString1, paramString2);
/* 151 */       bool = true;
/*     */     }
/*     */ 
/* 154 */     return bool;
/*     */   }
/*     */ 
/*     */   public boolean addFile(String paramString, File paramFile)
/*     */   {
/* 169 */     boolean bool = false;
/* 170 */     if ((!PdrUtil.isEmpty(paramString)) && (!PdrUtil.isEmpty(paramFile)) && 
/* 171 */       (!this.mFiles.containsKey(paramString)) && (!this.mNameValue.containsKey(paramString))) {
/* 172 */       this.mFiles.put(paramString, paramFile);
/* 173 */       bool = true;
/*     */     }
/*     */ 
/* 176 */     return bool;
/*     */   }
/*     */ 
/*     */   public boolean addHeader(String paramString1, String paramString2)
/*     */   {
/* 190 */     boolean bool = false;
/* 191 */     if ((!PdrUtil.isEmpty(paramString1)) && (!PdrUtil.isEmpty(paramString2)) && 
/* 192 */       (!this.mHeads.containsKey(paramString1))) {
/* 193 */       this.mHeads.put(paramString1, paramString2);
/* 194 */       bool = true;
/*     */     }
/*     */ 
/* 197 */     return bool;
/*     */   }
/*     */ 
/*     */   public boolean containHeader(String paramString)
/*     */   {
/* 205 */     if (this.mHeads != null) {
/* 206 */       return this.mHeads.containsKey(paramString);
/*     */     }
/* 208 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean addBody(String paramString)
/*     */   {
/* 221 */     boolean bool = false;
/* 222 */     if (!PdrUtil.isEmpty(paramString)) {
/* 223 */       this.mBodys.add(paramString);
/* 224 */       bool = true;
/*     */     }
/* 226 */     return bool;
/*     */   }
/*     */ 
/*     */   public HttpRequestBase getHttpRequest()
/*     */     throws IllegalArgumentException
/*     */   {
/* 239 */     if (this.mHttpRequest == null) {
/* 240 */       HttpOption localHttpOption = HttpOption.valueOf(this.mReqmethod.toUpperCase());
/*     */       try {
/* 242 */         switch (1.$SwitchMap$io$dcloud$common$util$net$RequestData$HttpOption[localHttpOption.ordinal()]) {
/*     */         case 1:
/* 244 */           this.mHttpRequest = new HttpPost(this.mUrl);
/* 245 */           addBody((HttpEntityEnclosingRequestBase)this.mHttpRequest);
/* 246 */           break;
/*     */         case 2:
/* 248 */           this.mHttpRequest = new HttpPut(this.mUrl);
/* 249 */           addBody((HttpEntityEnclosingRequestBase)this.mHttpRequest);
/* 250 */           break;
/*     */         case 3:
/* 252 */           this.mHttpRequest = new HttpDelete(this.mUrl);
/* 253 */           break;
/*     */         case 4:
/* 255 */           this.mHttpRequest = new HttpHead(this.mUrl);
/* 256 */           break;
/*     */         case 5:
/* 258 */           this.mHttpRequest = new HttpTrace(this.mUrl);
/* 259 */           break;
/*     */         case 6:
/* 261 */           this.mHttpRequest = new HttpOptions(this.mUrl);
/* 262 */           break;
/*     */         case 7:
/*     */         default:
/* 265 */           String str = URLEncodedUtils.format(getReqData(), "UTF-8");
/* 266 */           if (PdrUtil.isEmpty(str))
/* 267 */             this.mHttpRequest = new HttpGet(this.mUrl);
/*     */           else
/* 269 */             this.mHttpRequest = new HttpGet(this.mUrl + "?" + str);
/*     */           break;
/*     */         }
/*     */       }
/*     */       catch (IllegalArgumentException localIllegalArgumentException) {
/* 274 */         throw localIllegalArgumentException;
/*     */       }
/* 276 */       addHeader(this.mHttpRequest);
/*     */     }
/* 278 */     return this.mHttpRequest;
/*     */   }
/*     */ 
/*     */   public void clearData() {
/* 282 */     this.mHttpRequest.abort();
/* 283 */     this.mHttpRequest = null;
/*     */   }
/*     */   private String initIpAndPort() {
/* 286 */     String str1 = this.mUrl.substring("https://".length());
/* 287 */     String str2 = "443";
/* 288 */     int i = str1.lastIndexOf(":");
/* 289 */     if (i == -1) {
/* 290 */       i = str1.indexOf("/");
/* 291 */       str1 = str1.substring(0, i);
/* 292 */       str2 = "443";
/*     */     }
/*     */     else {
/* 295 */       int j = str1.indexOf("/");
/* 296 */       if (j > i + 1) {
/* 297 */         str2 = str1.substring(i + 1, j);
/*     */       }
/* 299 */       str1 = str1.substring(0, i);
/*     */     }
/* 301 */     return str2;
/*     */   }
/*     */ 
/*     */   public String getIP() {
/* 305 */     return this.mIp;
/*     */   }
/*     */ 
/*     */   public String getPort() {
/* 309 */     return this.mPort;
/*     */   }
/*     */ 
/*     */   public String getUrl() {
/* 313 */     return this.mUrl;
/*     */   }
/*     */ 
/*     */   public void setUrl(String paramString) {
/* 317 */     this.mUrl = paramString;
/*     */   }
/*     */ 
/*     */   public void addHeader(HttpRequestBase paramHttpRequestBase)
/*     */   {
/* 329 */     Iterator localIterator = this.mHeads.keySet().iterator();
/*     */ 
/* 331 */     while (localIterator.hasNext()) {
/* 332 */       String str1 = (String)localIterator.next();
/* 333 */       String str2 = (String)this.mHeads.get(str1);
/* 334 */       paramHttpRequestBase.addHeader(str1, str2);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addHeader(HttpURLConnection paramHttpURLConnection)
/*     */   {
/* 348 */     Iterator localIterator = this.mHeads.keySet().iterator();
/*     */ 
/* 350 */     while (localIterator.hasNext()) {
/* 351 */       String str1 = (String)localIterator.next();
/* 352 */       String str2 = (String)this.mHeads.get(str1);
/* 353 */       paramHttpURLConnection.addRequestProperty(str1, str2);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addBody(HttpEntityEnclosingRequestBase paramHttpEntityEnclosingRequestBase)
/*     */   {
/* 367 */     for (String str : this.mBodys) {
/* 368 */       Vector localVector = new Vector();
/* 369 */       this.mContentLength = appendPostParemeter(localVector, str, this.mContentLength);
/* 370 */       Enumeration localEnumeration = localVector.elements();
/* 371 */       SequenceInputStream localSequenceInputStream = new SequenceInputStream(localEnumeration);
/* 372 */       InputStreamEntity localInputStreamEntity = new InputStreamEntity(localSequenceInputStream, this.mContentLength);
/* 373 */       paramHttpEntityEnclosingRequestBase.setEntity(localInputStreamEntity);
/*     */     }
/*     */   }
/*     */ 
/*     */   private List<NameValuePair> getReqData()
/*     */   {
/* 387 */     ArrayList localArrayList = new ArrayList();
/* 388 */     Iterator localIterator = this.mNameValue.keySet().iterator();
/*     */ 
/* 390 */     while (localIterator.hasNext()) {
/* 391 */       String str1 = (String)localIterator.next();
/* 392 */       String str2 = (String)this.mNameValue.get(str1);
/* 393 */       localArrayList.add(new BasicNameValuePair(str1, str2));
/*     */     }
/* 395 */     return localArrayList;
/*     */   }
/*     */ 
/*     */   public long appendPostParemeter(Vector<InputStream> paramVector, String paramString, long paramLong)
/*     */   {
/* 411 */     ByteArrayInputStream localByteArrayInputStream = null;
/*     */     try
/*     */     {
/* 414 */       localByteArrayInputStream = new ByteArrayInputStream(paramString.getBytes("UTF-8"));
/* 415 */       paramVector.add(localByteArrayInputStream);
/* 416 */       return localByteArrayInputStream.available() + paramLong;
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/* 418 */       localUnsupportedEncodingException.printStackTrace();
/*     */     }
/* 420 */     return 0L;
/*     */   }
/*     */ 
/*     */   static enum HttpOption
/*     */   {
/*  65 */     GET, POST, PUT, DELETE, HEAD, TRACE, OPTIONS;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.net.RequestData
 * JD-Core Version:    0.6.2
 */