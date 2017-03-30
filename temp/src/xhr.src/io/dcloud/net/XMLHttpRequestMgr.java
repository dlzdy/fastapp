/*     */ package io.dcloud.net;
/*     */ 
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.net.NetWorkLoop;
/*     */ import io.dcloud.common.util.net.RequestData;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class XMLHttpRequestMgr
/*     */ {
/*     */   private NetWorkLoop mNetWorkLoop;
/*  30 */   public HashMap<String, ArrayList<XMLHttpRequest>> mXMLHttpRequests = null;
/*     */ 
/*     */   public XMLHttpRequestMgr() {
/*  33 */     this.mNetWorkLoop = new NetWorkLoop();
/*  34 */     this.mXMLHttpRequests = new HashMap();
/*  35 */     this.mNetWorkLoop.startThreadPool();
/*     */   }
/*     */ 
/*     */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*     */   {
/*  40 */     String str1 = paramIWebview.obtainFrameView().obtainApp().obtainAppId();
/*     */     Object localObject1;
/*     */     String str2;
/*     */     Object localObject2;
/*     */     Object localObject3;
/*     */     int i;
/*     */     Object localObject4;
/*     */     Object localObject5;
/*  41 */     if ("send".equals(paramString)) {
/*  42 */       localObject1 = findXMLHttpRequest(str1, paramArrayOfString[0]);
/*  43 */       ((XMLHttpRequest)localObject1).setCallbackId(paramArrayOfString[1]);
/*  44 */       str2 = paramArrayOfString[2];
/*  45 */       localObject2 = ((XMLHttpRequest)localObject1).getRequestData();
/*  46 */       ((RequestData)localObject2).addBody(str2);
/*     */       try {
/*  48 */         JSONObject localJSONObject = new JSONObject(paramArrayOfString[3]);
/*  49 */         localObject3 = localJSONObject.names();
/*     */ 
/*  51 */         if ((localObject3 != null) && (((JSONArray)localObject3).length() > 0))
/*  52 */           for (i = 0; i < ((JSONArray)localObject3).length(); i++) {
/*  53 */             localObject4 = ((JSONArray)localObject3).optString(i);
/*  54 */             localObject5 = localJSONObject.optString((String)localObject4);
/*  55 */             ((RequestData)localObject2).addHeader((String)localObject4, (String)localObject5);
/*     */           }
/*     */       }
/*     */       catch (JSONException localJSONException) {
/*  59 */         localJSONException.printStackTrace();
/*     */       }
/*  61 */       this.mNetWorkLoop.addNetWork(((XMLHttpRequest)localObject1).getNetWork());
/*  62 */     } else if ("open".equals(paramString)) {
/*  63 */       localObject1 = paramArrayOfString[0];
/*  64 */       str2 = paramArrayOfString[1];
/*  65 */       localObject2 = paramArrayOfString[2];
/*  66 */       String str3 = paramArrayOfString[3];
/*  67 */       localObject3 = paramArrayOfString[4];
/*  68 */       localObject4 = new XMLHttpRequest((String)localObject1, (String)localObject2, str2, paramIWebview);
/*  69 */       localObject5 = ((XMLHttpRequest)localObject4).getRequestData();
/*  70 */       i = PdrUtil.parseInt(paramArrayOfString[5], ((RequestData)localObject5).mTimeout);
/*  71 */       ((RequestData)localObject5).mTimeout = i;
/*  72 */       ((RequestData)localObject5).addHeader(str3, (String)localObject3);
/*  73 */       pushXMLHttpRequest(str1, (XMLHttpRequest)localObject4);
/*  74 */     } else if ("overrideMimeType".equals(paramString)) {
/*  75 */       localObject1 = findXMLHttpRequest(str1, paramArrayOfString[0]);
/*  76 */       if (localObject1 != null)
/*  77 */         ((XMLHttpRequest)localObject1).getRequestData().mOverrideMimeType = paramArrayOfString[1];
/*     */     }
/*  79 */     else if ("abort".equals(paramString)) {
/*  80 */       localObject1 = findXMLHttpRequest(str1, paramArrayOfString[0]);
/*  81 */       this.mNetWorkLoop.removeNetWork(((XMLHttpRequest)localObject1).getNetWork());
/*     */     }
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */   private void pushXMLHttpRequest(String paramString, XMLHttpRequest paramXMLHttpRequest) {
/*  87 */     ArrayList localArrayList = (ArrayList)this.mXMLHttpRequests.get(paramString);
/*  88 */     if (localArrayList == null) {
/*  89 */       localArrayList = new ArrayList();
/*  90 */       this.mXMLHttpRequests.put(paramString, localArrayList);
/*     */     }
/*  92 */     localArrayList.add(paramXMLHttpRequest);
/*     */   }
/*     */ 
/*     */   private XMLHttpRequest findXMLHttpRequest(String paramString1, String paramString2) {
/*  96 */     Object localObject = null;
/*  97 */     ArrayList localArrayList = (ArrayList)this.mXMLHttpRequests.get(paramString1);
/*  98 */     if (localArrayList != null) {
/*  99 */       int i = localArrayList.size();
/* 100 */       for (int j = 0; j < i; j++) {
/* 101 */         XMLHttpRequest localXMLHttpRequest = (XMLHttpRequest)localArrayList.get(j);
/* 102 */         if (paramString2.equals(localXMLHttpRequest.mUUID)) {
/* 103 */           localObject = localXMLHttpRequest;
/* 104 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 108 */     return localObject;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\xhr.jar
 * Qualified Name:     io.dcloud.net.XMLHttpRequestMgr
 * JD-Core Version:    0.6.2
 */