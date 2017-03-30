/*     */ package io.dcloud.net;
/*     */ 
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IReqListener;
/*     */ import io.dcloud.common.DHInterface.IReqListener.NetState;
/*     */ import io.dcloud.common.DHInterface.IResponseListener;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.net.NetWork;
/*     */ import io.dcloud.common.util.net.RequestData;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class XMLHttpRequest
/*     */   implements IReqListener, IResponseListener
/*     */ {
/*     */   public String mUUID;
/*     */   private NetWork mNetWork;
/*     */   IWebview mWebview;
/*     */   private RequestData mRequestData;
/*     */   private int mState;
/*     */   private int mReadyState;
/*  41 */   private int mErrorCode = -1;
/*     */   private String mStatusText;
/*     */   private String mCallbackId;
/*     */   private static final int RECEIVING = 3;
/*     */   private static final int LOADED = 4;
/*     */   private static final int ERROR_TIME_OUT_CODE = 0;
/*     */   private static final int ERROR_OTHER_CODE = 1;
/*     */ 
/*     */   public XMLHttpRequest(String paramString1, String paramString2, String paramString3, IWebview paramIWebview)
/*     */   {
/*  52 */     this.mUUID = paramString1;
/*  53 */     this.mRequestData = new RequestData(paramString2, paramString3);
/*  54 */     this.mRequestData.unTrustedCAType = paramIWebview.obtainApp().obtainConfigProperty("untrustedca");
/*  55 */     this.mRequestData.addHeader("User-Agent", paramIWebview.getWebviewProperty("User-Agent"));
/*  56 */     String str = paramIWebview.getWebviewProperty(paramString2);
/*  57 */     if (!PdrUtil.isEmpty(str)) {
/*  58 */       this.mRequestData.addHeader("Cookie", str);
/*     */     }
/*  60 */     this.mNetWork = new NetWork(3, this.mRequestData, this, this);
/*  61 */     this.mWebview = paramIWebview;
/*     */   }
/*     */ 
/*     */   public NetWork getNetWork() {
/*  65 */     return this.mNetWork;
/*     */   }
/*     */ 
/*     */   public RequestData getRequestData()
/*     */   {
/*  72 */     return this.mRequestData;
/*     */   }
/*     */ 
/*     */   public void setCallbackId(String paramString)
/*     */   {
/*  79 */     this.mCallbackId = paramString;
/*     */   }
/*     */ 
/*     */   public void onResponseState(int paramInt, String paramString)
/*     */   {
/*  84 */     this.mState = paramInt;
/*  85 */     this.mStatusText = paramString;
/*  86 */     Logger.d("xhr", "onResponseState pState=" + paramInt + ";mCallbackId=" + this.mCallbackId);
/*     */   }
/*     */ 
/*     */   public void onNetStateChanged(IReqListener.NetState paramNetState, boolean paramBoolean)
/*     */   {
/*  91 */     if (paramBoolean) {
/*  92 */       this.mReadyState = 4;
/*  93 */       return;
/*     */     }
/*  95 */     switch (1.$SwitchMap$io$dcloud$common$DHInterface$IReqListener$NetState[paramNetState.ordinal()]) {
/*     */     case 1:
/*  97 */       this.mReadyState = 4;
/*  98 */       JSUtil.execCallback(this.mWebview, this.mCallbackId, toJSON(), JSUtil.OK, true);
/*  99 */       break;
/*     */     case 2:
/* 101 */       this.mReadyState = 3;
/* 102 */       JSUtil.execCallback(this.mWebview, this.mCallbackId, toJSON(), JSUtil.OK, true);
/* 103 */       break;
/*     */     case 3:
/* 105 */       this.mReadyState = 4;
/* 106 */       this.mErrorCode = 1;
/* 107 */       JSUtil.execCallback(this.mWebview, this.mCallbackId, toJSON(), JSUtil.OK, true);
/* 108 */       break;
/*     */     case 4:
/* 110 */       this.mReadyState = 4;
/* 111 */       this.mErrorCode = 0;
/* 112 */       JSUtil.execCallback(this.mWebview, this.mCallbackId, toJSON(), JSUtil.OK, true);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onResponsing(InputStream paramInputStream)
/*     */   {
/*     */   }
/*     */ 
/*     */   public int onReceiving(InputStream paramInputStream)
/*     */   {
/* 124 */     return 0;
/*     */   }
/*     */ 
/*     */   public JSONObject toJSON() {
/* 128 */     JSONObject localJSONObject1 = new JSONObject();
/* 129 */     String str = this.mNetWork.getResponseText();
/*     */     try {
/* 131 */       localJSONObject1.put("readyState", this.mReadyState);
/* 132 */       localJSONObject1.put("status", this.mState);
/* 133 */       localJSONObject1.put("statusText", this.mStatusText);
/* 134 */       localJSONObject1.put("responseText", str);
/* 135 */       JSONObject localJSONObject2 = headersToJSON(this.mNetWork.getHeadersAndValues());
/* 136 */       localJSONObject1.put("header", localJSONObject2);
/*     */ 
/* 138 */       if (this.mErrorCode > -1)
/* 139 */         localJSONObject1.put("error", this.mErrorCode);
/*     */     }
/*     */     catch (JSONException localJSONException)
/*     */     {
/* 143 */       localJSONException.printStackTrace();
/*     */     }
/* 145 */     return localJSONObject1;
/*     */   }
/*     */ 
/*     */   private String toJsResponseText(String paramString)
/*     */   {
/* 158 */     return JSONUtil.toJSONableString(paramString);
/*     */   }
/*     */ 
/*     */   private JSONObject headersToJSON(Map<String, String> paramMap)
/*     */   {
/* 171 */     JSONObject localJSONObject = new JSONObject();
/* 172 */     Iterator localIterator = paramMap.keySet().iterator();
/*     */ 
/* 174 */     while (localIterator.hasNext()) {
/* 175 */       String str1 = (String)localIterator.next();
/* 176 */       String str2 = (String)paramMap.get(str1);
/*     */       try {
/* 178 */         localJSONObject.put(str1, str2);
/*     */       } catch (JSONException localJSONException) {
/* 180 */         localJSONException.printStackTrace();
/*     */       }
/*     */     }
/* 183 */     return localJSONObject;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\xhr.jar
 * Qualified Name:     io.dcloud.net.XMLHttpRequest
 * JD-Core Version:    0.6.2
 */