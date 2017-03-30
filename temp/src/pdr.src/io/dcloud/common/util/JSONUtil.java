/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import java.util.Iterator;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class JSONUtil
/*     */ {
/*     */   public static JSONArray createJSONArray(String paramString)
/*     */   {
/*  14 */     if (!paramString.startsWith("[")) {
/*  15 */       paramString = "[" + paramString;
/*     */     }
/*  17 */     if (!paramString.endsWith("]")) {
/*  18 */       paramString = paramString + "]";
/*     */     }
/*  20 */     JSONArray localJSONArray = null;
/*     */     try {
/*  22 */       localJSONArray = new JSONArray(paramString);
/*     */     }
/*     */     catch (JSONException localJSONException)
/*     */     {
/*     */     }
/*  27 */     return localJSONArray;
/*     */   }
/*     */ 
/*     */   public static JSONObject combinJSONObject(JSONObject paramJSONObject1, JSONObject paramJSONObject2) {
/*  31 */     if (paramJSONObject1 == null) return paramJSONObject2;
/*  32 */     if (paramJSONObject1 == paramJSONObject2) return paramJSONObject1;
/*  33 */     Iterator localIterator = paramJSONObject2.keys();
/*     */     try {
/*  35 */       if (localIterator != null) {
/*  36 */         while (localIterator.hasNext()) {
/*  37 */           String str = String.valueOf(localIterator.next());
/*  38 */           Object localObject1 = paramJSONObject1.opt(str);
/*  39 */           Object localObject2 = paramJSONObject2.opt(str);
/*  40 */           if (localObject2 != null)
/*  41 */             if (localObject1 == null)
/*  42 */               paramJSONObject1.putOpt(str, localObject2);
/*  43 */             else if ((localObject2 instanceof JSONObject)) {
/*  44 */               if ((localObject1 instanceof JSONObject))
/*  45 */                 combinJSONObject((JSONObject)localObject1, (JSONObject)localObject2);
/*     */               else
/*  47 */                 paramJSONObject1.putOpt(str, localObject2);
/*     */             }
/*     */             else
/*  50 */               paramJSONObject1.putOpt(str, localObject2);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (JSONException localJSONException)
/*     */     {
/*  56 */       localJSONException.printStackTrace();
/*     */     }
/*  58 */     return paramJSONObject1;
/*     */   }
/*     */   public static String getString(JSONObject paramJSONObject, String paramString) {
/*     */     try {
/*  62 */       if (paramJSONObject == null) return null;
/*  63 */       return paramJSONObject.getString(paramString);
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */   public static long getLong(JSONObject paramJSONObject, String paramString) {
/*     */     try {
/*  72 */       if ((paramJSONObject == null) || (!paramJSONObject.has(paramString))) return 0L;
/*  73 */       return paramJSONObject.getLong(paramString);
/*     */     } catch (Exception localException) {
/*  75 */       localException.printStackTrace();
/*     */     }
/*  77 */     return 0L;
/*     */   }
/*     */ 
/*     */   public static boolean getBoolean(JSONObject paramJSONObject, String paramString) {
/*     */     try {
/*  82 */       if ((paramJSONObject == null) || (!paramJSONObject.has(paramString))) return false;
/*  83 */       return paramJSONObject.getBoolean(paramString);
/*     */     } catch (Exception localException) {
/*  85 */       localException.printStackTrace();
/*     */     }
/*  87 */     return false;
/*     */   }
/*     */   public static JSONObject createJSONObject(String paramString) {
/*     */     try {
/*  91 */       return new JSONObject(paramString);
/*     */     } catch (JSONException localJSONException) {
/*  93 */       Logger.d("jsonutil", "JSONException pJson=" + paramString);
/*     */     }
/*     */ 
/*  96 */     return null;
/*     */   }
/*     */   public static JSONObject getJSONObject(JSONObject paramJSONObject, String paramString) {
/*     */     try {
/* 100 */       Object localObject = paramJSONObject.opt(paramString);
/* 101 */       if ((localObject instanceof JSONObject))
/* 102 */         return (JSONObject)localObject;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */   public static JSONObject getJSONObject(JSONArray paramJSONArray, int paramInt) {
/*     */     try {
/* 112 */       Object localObject = paramJSONArray.get(paramInt);
/* 113 */       if ((localObject instanceof JSONObject))
/* 114 */         return (JSONObject)localObject;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/* 119 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getString(JSONArray paramJSONArray, int paramInt) {
/*     */     try {
/* 124 */       Object localObject = paramJSONArray.get(paramInt);
/* 125 */       if (!PdrUtil.isEmpty(localObject))
/* 126 */         return String.valueOf(localObject);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getInt(JSONObject paramJSONObject, String paramString) {
/*     */     try {
/* 136 */       Integer localInteger = Integer.valueOf(paramJSONObject.getInt(paramString));
/* 137 */       if ((localInteger instanceof Integer))
/* 138 */         return ((Integer)localInteger).intValue();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/* 143 */     return 0;
/*     */   }
/*     */ 
/*     */   public static boolean isNull(JSONObject paramJSONObject, String paramString)
/*     */   {
/* 153 */     if (paramJSONObject == null) return true;
/* 154 */     boolean bool = true;
/*     */     try {
/* 156 */       bool = paramJSONObject.isNull(paramString);
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/* 160 */     return bool;
/*     */   }
/*     */ 
/*     */   public static JSONArray getJSONArray(JSONArray paramJSONArray, int paramInt) {
/*     */     try {
/* 165 */       Object localObject = paramJSONArray.get(paramInt);
/* 166 */       if ((localObject instanceof JSONArray))
/* 167 */         return (JSONArray)localObject;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */   public static JSONArray getJSONArray(JSONObject paramJSONObject, String paramString) {
/*     */     try {
/* 177 */       Object localObject = paramJSONObject.get(paramString);
/* 178 */       if ((localObject instanceof JSONArray))
/* 179 */         return (JSONArray)localObject;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/* 184 */     return null;
/*     */   }
/*     */ 
/*     */   public static String toJSONableString(String paramString) {
/* 188 */     if (paramString != null)
/*     */     {
/* 205 */       return JSONObject.quote(paramString);
/*     */     }
/* 207 */     return "''";
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.JSONUtil
 * JD-Core Version:    0.6.2
 */