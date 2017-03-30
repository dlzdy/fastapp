/*    */ package io.dcloud.common.adapter.ui;
/*    */ 
/*    */ import android.os.Build.VERSION;
/*    */ import android.webkit.JavascriptInterface;
/*    */ import io.dcloud.common.DHInterface.IReflectAble;
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ import io.dcloud.common.util.JSONUtil;
/*    */ import java.util.HashMap;
/*    */ import org.json.JSONArray;
/*    */ 
/*    */ public class ReceiveJSValue
/*    */   implements IReflectAble
/*    */ {
/* 16 */   public static String SYNC_HANDLER = "SYNC_HANDLER";
/*    */ 
/* 30 */   private static HashMap<String, ReceiveJSValueCallback> arrs = new HashMap();
/* 31 */   private String android42Js = null;
/*    */ 
/*    */   public static final String registerCallback(String paramString, ReceiveJSValueCallback paramReceiveJSValueCallback)
/*    */   {
/* 40 */     return registerCallback(null, paramString, paramReceiveJSValueCallback);
/*    */   }
/*    */   static final String registerCallback(AdaWebview paramAdaWebview, String paramString, ReceiveJSValueCallback paramReceiveJSValueCallback) {
/* 43 */     String str1 = String.valueOf(paramReceiveJSValueCallback.hashCode());
/* 44 */     arrs.put(str1, paramReceiveJSValueCallback);
/*    */ 
/* 46 */     String str2 = "";
/* 47 */     if ((paramAdaWebview != null) && (paramAdaWebview.mWebViewImpl.mReceiveJSValue_android42 != null)) {
/* 48 */       str2 = paramAdaWebview.mWebViewImpl.mReceiveJSValue_android42.android42Js;
/*    */     }
/* 50 */     return str2 + String.format(new StringBuilder().append("window.SYNC_HANDLER && ").append(SYNC_HANDLER).append(".__js__call__native__('").append(str1).append("',(function(){var ret = %s;var type = (typeof ret );return JSON.stringify([type,ret]);})());").toString(), new Object[] { paramString });
/*    */   }
/*    */ 
/*    */   public static final void addJavascriptInterface(AdaWebview paramAdaWebview) {
/* 54 */     if (Build.VERSION.SDK_INT > 17) {
/* 55 */       paramAdaWebview.mWebViewImpl.addJavascriptInterface(new ReceiveJSValue(), SYNC_HANDLER);
/*    */     } else {
/* 57 */       paramAdaWebview.mWebViewImpl.mReceiveJSValue_android42 = new ReceiveJSValue();
/* 58 */       paramAdaWebview.mWebViewImpl.mReceiveJSValue_android42.android42Js = "window.SYNC_HANDLER||(window.SYNC_HANDLER = {__js__call__native__: function(uuid, js) {return window.prompt('__js__call__native__','sync:' + JSON.stringify([uuid, js]));}});";
/*    */     }
/*    */   }
/*    */ 
/*    */   @JavascriptInterface
/*    */   public final String __js__call__native__(String paramString1, String paramString2) {
/* 64 */     ReceiveJSValueCallback localReceiveJSValueCallback = (ReceiveJSValueCallback)arrs.remove(paramString1);
/* 65 */     Logger.d("ReceiveJSValue", "__js__call__native__ js=" + paramString2);
/* 66 */     return localReceiveJSValueCallback != null ? localReceiveJSValueCallback.callback(JSONUtil.createJSONArray(paramString2)) : "";
/*    */   }
/*    */ 
/*    */   public static abstract interface ReceiveJSValueCallback
/*    */   {
/*    */     public abstract String callback(JSONArray paramJSONArray);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.ReceiveJSValue
 * JD-Core Version:    0.6.2
 */