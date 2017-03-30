/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.webkit.JavascriptInterface;
/*    */ import io.dcloud.common.DHInterface.IJsInterface;
/*    */ import io.dcloud.common.adapter.util.MessageHandler;
/*    */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*    */ import org.json.JSONArray;
/*    */ 
/*    */ public class Birdge
/*    */   implements IJsInterface, MessageHandler.IMessages
/*    */ {
/*    */   IJsInterface mJsInterface;
/*    */ 
/*    */   public Birdge(IJsInterface paramIJsInterface)
/*    */   {
/* 17 */     this.mJsInterface = paramIJsInterface;
/*    */   }
/*    */ 
/*    */   @JavascriptInterface
/*    */   public String prompt(String paramString1, String paramString2) {
/* 22 */     return this.mJsInterface.prompt(paramString1, paramString2);
/*    */   }
/*    */   @Deprecated
/*    */   public String exec(String paramString1, String paramString2, String paramString3) {
/* 26 */     return this.mJsInterface.exec(paramString1, paramString2, paramString3);
/*    */   }
/*    */   public String exec(String paramString1, String paramString2, JSONArray paramJSONArray) {
/* 29 */     return this.mJsInterface.exec(paramString1, paramString2, paramJSONArray);
/*    */   }
/*    */ 
/*    */   @JavascriptInterface
/*    */   public void forceStop(String paramString) {
/* 34 */     MessageHandler.sendMessage(this, paramString);
/*    */   }
/*    */ 
/*    */   public void execute(Object paramObject) {
/* 38 */     this.mJsInterface.forceStop((String)paramObject);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.Birdge
 * JD-Core Version:    0.6.2
 */