/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import java.util.ArrayList;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class JSUtil extends Deprecated_JSUtil
/*     */ {
/*     */   public static final String QUOTE = "\"";
/*     */   public static final String COMMA = ",";
/* 225 */   public static int NO_RESULT = 0;
/* 226 */   public static int OK = 1;
/* 227 */   public static int CLASS_NOT_FOUND_EXCEPTION = 2;
/* 228 */   public static int ILLEGAL_ACCESS_EXCEPTION = 3;
/* 229 */   public static int INSTANTIATION_EXCEPTION = 4;
/* 230 */   public static int MALFORMED_URL_EXCEPTION = 5;
/* 231 */   public static int IO_EXCEPTION = 6;
/* 232 */   public static int INVALID_ACTION = 7;
/* 233 */   public static int JSON_EXCEPTION = 8;
/* 234 */   public static int ERROR = 9;
/*     */ 
/*     */   public static String[] jsonArrayToStringArr(JSONArray paramJSONArray)
/*     */     throws JSONException
/*     */   {
/*  38 */     String[] arrayOfString = new String[paramJSONArray.length()];
/*  39 */     for (int i = 0; i < paramJSONArray.length(); i++) {
/*  40 */       arrayOfString[i] = paramJSONArray.getString(i);
/*     */     }
/*  42 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public static String wrapJsVar(double paramDouble)
/*     */   {
/*  50 */     return wrapJsVar(String.valueOf(paramDouble), false);
/*     */   }
/*     */ 
/*     */   public static String wrapJsVar(float paramFloat) {
/*  54 */     return wrapJsVar(String.valueOf(paramFloat), false);
/*     */   }
/*     */ 
/*     */   public static String wrapJsVar(JSONArray paramJSONArray)
/*     */   {
/*  62 */     return wrapJsVar(paramJSONArray.toString(), false);
/*     */   }
/*     */ 
/*     */   public static String wrapJsVar(JSONObject paramJSONObject)
/*     */   {
/*  70 */     return wrapJsVar(paramJSONObject.toString(), false);
/*     */   }
/*     */ 
/*     */   public static String wrapJsVar(String paramString)
/*     */   {
/*  78 */     return wrapJsVar(paramString, true);
/*     */   }
/*     */ 
/*     */   public static String wrapJsVar(boolean paramBoolean)
/*     */   {
/*  86 */     return wrapJsVar(String.valueOf(paramBoolean), false);
/*     */   }
/*     */ 
/*     */   public static String consoleTest(String paramString) {
/*  90 */     return "console.error('" + paramString + "');";
/*     */   }
/*     */ 
/*     */   public static String arrayList2JsStringArray(ArrayList<String> paramArrayList)
/*     */   {
/* 105 */     StringBuffer localStringBuffer = new StringBuffer("[");
/* 106 */     if ((paramArrayList != null) && (!paramArrayList.isEmpty())) {
/* 107 */       int i = paramArrayList.size();
/* 108 */       for (int j = 0; j < i; j++) {
/* 109 */         localStringBuffer.append("'").append((String)paramArrayList.get(j)).append("'");
/* 110 */         if (j != i - 1) {
/* 111 */           localStringBuffer.append(",");
/*     */         }
/*     */       }
/*     */     }
/* 115 */     localStringBuffer.append("]");
/* 116 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static void execCallback(IWebview paramIWebview, String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*     */   {
/* 129 */     execCallback(paramIWebview, paramString1, paramString2, paramInt, false, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void execCallback(IWebview paramIWebview, String paramString, double paramDouble, int paramInt, boolean paramBoolean)
/*     */   {
/* 141 */     execCallback(paramIWebview, paramString, String.valueOf(paramDouble), paramInt, true, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void execCallback(IWebview paramIWebview, String paramString, JSONArray paramJSONArray, int paramInt, boolean paramBoolean)
/*     */   {
/* 153 */     execCallback(paramIWebview, paramString, paramJSONArray.toString(), paramInt, true, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void execCallback(IWebview paramIWebview, String paramString, JSONObject paramJSONObject, int paramInt, boolean paramBoolean)
/*     */   {
/* 165 */     execCallback(paramIWebview, paramString, paramJSONObject.toString(), paramInt, true, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void execGEOCallback(IWebview paramIWebview, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 170 */     if (paramIWebview != null) {
/* 171 */       String str = "(function(){try{var result= {};result.status = %d;result.message = " + (paramBoolean1 ? "%s" : "'%s'") + ";result.keepCallback = " + paramBoolean2 + ";" + "__geo__.callbackFromNative('%s', result);}catch(e){console.error(e)};})(window,navigator);";
/*     */ 
/* 177 */       str = String.format(str, new Object[] { Integer.valueOf(paramInt), paramString2, paramString1 });
/* 178 */       paramIWebview.executeScript(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void broadcastWebviewEvent(IWebview paramIWebview, String paramString1, String paramString2, String paramString3) {
/* 183 */     String str = "(function(w,n){var p = ((w.__html5plus__&&w.__html5plus__.isReady)?w.__html5plus__:(n.plus&&n.plus.isReady)?n.plus:window.plus);p && p.webview && p.webview.__Webview_LoadEvent_CallBack_ && p.webview.__Webview_LoadEvent_CallBack_(%s);})(window,navigator)";
/* 184 */     str = String.format(str, new Object[] { String.format("{WebviewID:'%s',Event:'%s',Msg:%s}", new Object[] { paramString1, paramString2, paramString3 }) });
/* 185 */     if (paramIWebview != null)
/* 186 */       paramIWebview.executeScript(str);
/*     */   }
/*     */ 
/*     */   public static boolean checkOperateDirErrorAndCallback(IWebview paramIWebview, String paramString1, String paramString2)
/*     */   {
/* 197 */     IApp localIApp = paramIWebview.obtainFrameView().obtainApp();
/* 198 */     boolean bool = false;
/* 199 */     if (localIApp.checkPrivateDir(paramString2)) {
/* 200 */       String str = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(9), "operate_dir_error" });
/* 201 */       execCallback(paramIWebview, paramString1, str, ERROR, true, false);
/* 202 */       bool = true;
/*     */     }
/* 204 */     return bool;
/*     */   }
/*     */ 
/*     */   public static String toJsResponseText(String paramString)
/*     */   {
/* 217 */     if (!PdrUtil.isEmpty(paramString)) {
/* 218 */       paramString = paramString.replace("'", "'");
/* 219 */       paramString = paramString.replace("\"", "\\\"");
/* 220 */       paramString = paramString.replace("\n", "\\n");
/* 221 */       paramString = paramString.replace("\r", "\\r");
/*     */     }
/* 223 */     return paramString;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.JSUtil
 * JD-Core Version:    0.6.2
 */