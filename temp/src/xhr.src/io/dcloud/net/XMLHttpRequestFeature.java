/*    */ package io.dcloud.net;
/*    */ 
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.IFeature;
/*    */ import io.dcloud.common.DHInterface.IWebview;
/*    */ 
/*    */ public class XMLHttpRequestFeature
/*    */   implements IFeature
/*    */ {
/*    */   private XMLHttpRequestMgr mXHRMgr;
/*    */ 
/*    */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*    */   {
/* 24 */     return this.mXHRMgr.execute(paramIWebview, paramString, paramArrayOfString);
/*    */   }
/*    */ 
/*    */   public void init(AbsMgr paramAbsMgr, String paramString)
/*    */   {
/* 29 */     this.mXHRMgr = new XMLHttpRequestMgr();
/*    */   }
/*    */ 
/*    */   public void dispose(String paramString)
/*    */   {
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\xhr.jar
 * Qualified Name:     io.dcloud.net.XMLHttpRequestFeature
 * JD-Core Version:    0.6.2
 */