/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ 
/*     */ class Deprecated_JSUtil
/*     */ {
/*     */   @Deprecated
/*     */   public static void execCallback(IWebview paramIWebview, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 255 */     if (paramIWebview != null) {
/* 256 */       String str = "(function(w,n){try{var p=((w.__html5plus__&&w.__html5plus__.isReady)?w.__html5plus__:(n.plus&&n.plus.isReady)?n.plus:window.plus);var result= {};result.status = %d;result.message = " + (paramBoolean1 ? "%s" : "'%s'") + ";result.keepCallback = " + paramBoolean2 + ";" + "p && p.bridge.callbackFromNative('%s', result);}catch(e){console.error(e)};})(window,navigator);";
/*     */ 
/* 264 */       str = String.format(str, new Object[] { Integer.valueOf(paramInt), paramString2, paramString1 });
/*     */ 
/* 272 */       paramIWebview.executeScript(str);
/*     */     }
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static void excCallbackSuccess(IWebview paramIWebview, String paramString1, String paramString2)
/*     */   {
/* 289 */     excCallbackSuccess(paramIWebview, paramString1, paramString2, false);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static void excCallbackSuccess(IWebview paramIWebview, String paramString1, String paramString2, boolean paramBoolean)
/*     */   {
/* 306 */     excCallbackSuccess(paramIWebview, paramString1, paramString2, paramBoolean, false);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static void excCallbackSuccess(IWebview paramIWebview, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 325 */     execCallback(paramIWebview, paramString1, paramString2, 1, paramBoolean1, paramBoolean2);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static void excCallbackError(IWebview paramIWebview, String paramString1, String paramString2)
/*     */   {
/* 341 */     excCallbackError(paramIWebview, paramString1, paramString2, false);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static void excCallbackError(IWebview paramIWebview, String paramString1, String paramString2, boolean paramBoolean)
/*     */   {
/* 354 */     execCallback(paramIWebview, paramString1, paramString2, JSUtil.ERROR, paramBoolean, false);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static void excDownloadCallBack(IWebview paramIWebview, String paramString1, String paramString2)
/*     */   {
/* 370 */     String str = "((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).downloader.__handlerEvt__('%s', %s);";
/* 371 */     str = String.format(str, new Object[] { paramString2, paramString1 });
/* 372 */     paramIWebview.executeScript(str);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static void excUploadCallBack(IWebview paramIWebview, String paramString1, String paramString2)
/*     */   {
/* 389 */     String str = "((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).uploader.__handlerEvt__('%s', %s);";
/* 390 */     str = String.format(str, new Object[] { paramString2, paramString1 });
/* 391 */     paramIWebview.executeScript(str);
/*     */   }
/*     */ 
/*     */   @Deprecated
/*     */   public static String wrapJsVar(String paramString, boolean paramBoolean)
/*     */   {
/* 403 */     StringBuffer localStringBuffer = new StringBuffer("(function(){return ");
/* 404 */     if (paramBoolean)
/* 405 */       localStringBuffer.append("'").append(paramString).append("';");
/*     */     else {
/* 407 */       localStringBuffer.append(paramString).append(";");
/*     */     }
/* 409 */     localStringBuffer.append("})()");
/* 410 */     return localStringBuffer.toString();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.Deprecated_JSUtil
 * JD-Core Version:    0.6.2
 */