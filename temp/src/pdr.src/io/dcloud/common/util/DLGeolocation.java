/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.webkit.JavascriptInterface;
/*     */ import io.dcloud.common.DHInterface.IJsInterface;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class DLGeolocation
/*     */   implements IJsInterface
/*     */ {
/*     */   IWebview mWebview;
/*     */ 
/*     */   public DLGeolocation(IWebview paramIWebview)
/*     */   {
/*  28 */     this.mWebview = paramIWebview;
/*     */   }
/*     */ 
/*     */   @JavascriptInterface
/*     */   public String exec(String paramString1, String paramString2, String paramString3) {
/*  33 */     runGeolocation(paramString1, paramString2, paramString3);
/*  34 */     return null;
/*     */   }
/*     */ 
/*     */   public String exec(String paramString1, String paramString2, JSONArray paramJSONArray) {
/*  38 */     return null;
/*     */   }
/*     */ 
/*     */   public String prompt(String paramString1, String paramString2) {
/*  42 */     return null;
/*     */   }
/*     */ 
/*     */   public void forceStop(String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void runGeolocation(String paramString1, String paramString2, String paramString3)
/*     */   {
/*  52 */     if (this.mWebview != null)
/*     */       try {
/*  54 */         String str = "io.dcloud.js.geolocation.amap.AMapGeoManager";
/*  55 */         Object localObject1 = PlatformUtil.invokeMethod(str, "getInstance", null, new Class[] { Context.class }, new Object[] { this.mWebview.getContext() });
/*  56 */         if (localObject1 == null) {
/*  57 */           localObject2 = Class.forName(str);
/*  58 */           Constructor localConstructor = ((Class)localObject2).getConstructor(new Class[] { Context.class });
/*  59 */           localObject1 = localConstructor.newInstance(new Object[] { this.mWebview.getContext() });
/*     */         }
/*  61 */         Object localObject2 = localObject1.getClass().getMethod("execute", new Class[] { IWebview.class, String.class, [Ljava.lang.String.class, Boolean.TYPE });
/*  62 */         ((Method)localObject2).invoke(localObject1, new Object[] { this.mWebview, paramString1, getGeoArgs(paramString1, paramString3, paramString2), Boolean.valueOf(true) });
/*     */       } catch (Exception localException) {
/*  64 */         localException.printStackTrace();
/*  65 */         JSUtil.execGEOCallback(this.mWebview, paramString2, String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(-100), "定位失败" }), JSUtil.ERROR, true, false);
/*     */       }
/*     */   }
/*     */ 
/*     */   public static boolean checkAMapGeo()
/*     */   {
/*  75 */     String str = "io.dcloud.js.geolocation.amap.AMapGeoManager";
/*     */     try {
/*  77 */       Class localClass = Class.forName(str);
/*  78 */       if (localClass != null)
/*  79 */         return true;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean checkInjectGeo(String paramString)
/*     */   {
/*  93 */     if ((paramString.equals("replace")) || ((paramString.equals("auto")) && (MobilePhoneModel.isDLGeoPhone()))) {
/*  94 */       return true;
/*     */     }
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */   public String[] getGeoArgs(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 105 */     String[] arrayOfString = null;
/*     */     try {
/* 107 */       JSONObject localJSONObject = new JSONObject(paramString2);
/* 108 */       if (localJSONObject != null) {
/* 109 */         int i = 0;
/* 110 */         if (localJSONObject.has("maximumAge")) {
/* 111 */           i = localJSONObject.optInt("maximumAge");
/*     */         }
/* 113 */         boolean bool1 = false;
/* 114 */         if (localJSONObject.has("enableHighAccuracy")) {
/* 115 */           bool1 = localJSONObject.optBoolean("enableHighAccuracy");
/*     */         }
/* 117 */         int j = 0;
/* 118 */         if (localJSONObject.has("timeout")) {
/* 119 */           j = localJSONObject.optInt("timeout");
/*     */         }
/* 121 */         boolean bool2 = true;
/* 122 */         if (localJSONObject.has("geocode")) {
/* 123 */           bool2 = localJSONObject.optBoolean("geocode");
/*     */         }
/*     */ 
/* 126 */         if (paramString1.equals("getCurrentPosition")) {
/* 127 */           arrayOfString = new String[] { paramString3, bool1 + "", i + "", null, null, bool2 + "", j + "" };
/*     */         }
/*     */         else
/*     */         {
/*     */           String str;
/* 128 */           if (paramString1.equals("watchPosition")) {
/* 129 */             str = localJSONObject.optString("id");
/* 130 */             arrayOfString = new String[] { paramString3, str, bool1 + "", "", "", bool2 + "", j + "", i + "" };
/* 131 */           } else if (paramString1.equals("clearwatch")) {
/* 132 */             str = localJSONObject.optString("id");
/* 133 */             arrayOfString = new String[] { str };
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (JSONException localJSONException) { localJSONException.printStackTrace(); }
/*     */ 
/* 139 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public static String getGEOJS()
/*     */   {
/* 147 */     return "!function(){window.__geo__={};var a=__geo__;a.callbacks={},a.callbackId=function(o,c){var e=\"dlgeolocation\"+(new Date).valueOf();return a.callbacks[e]={s:o,e:c},e},a.callbackFromNative=function(o,c){var e=a.callbacks[o];e&&(1==c.status&&e.success?e.s&&e.s(c.message):e.e&&e.e(c.message),c.keepCallback||delete a.callbacks[o])},navigator.geolocation.getCurrentPosition=function(o,c,e){var t=o,n=c||function(){},l=e||{},i=JSON.stringify(l);_dlGeolocation.exec(\"getCurrentPosition\",a.callbackId(t,n),i)},navigator.geolocation.watchPosition=function(o,c,e){var t=o,n=c||function(){},l=e||{},i=JSON.stringify(l);i.id=\"dlwatchPosition\"+(new Date).valueOf(),_dlGeolocation.exec(\"watchPosition\",a.callbackId(t,n),i)},navigator.geolocation.clearwatch=function(a){_dlGeolocation.exec(\"clearwatch\",null,{id:a})}}();";
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.DLGeolocation
 * JD-Core Version:    0.6.2
 */