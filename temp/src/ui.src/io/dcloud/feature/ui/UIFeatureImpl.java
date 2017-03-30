/*    */ package io.dcloud.feature.ui;
/*    */ 
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.BaseFeature;
/*    */ import io.dcloud.common.DHInterface.IWebview;
/*    */ import org.json.JSONArray;
/*    */ 
/*    */ public class UIFeatureImpl extends BaseFeature
/*    */ {
/* 21 */   e a = null;
/*    */ 
/*    */   public String execute(IWebview paramIWebview, String paramString, JSONArray paramJSONArray)
/*    */   {
/* 30 */     String str = null;
/*    */ 
/* 32 */     str = this.a.a(paramIWebview, paramString, paramJSONArray);
/*    */ 
/* 49 */     return str;
/*    */   }
/*    */ 
/*    */   public void init(AbsMgr paramAbsMgr, String paramString)
/*    */   {
/* 54 */     this.a = new e(paramAbsMgr, paramString);
/*    */   }
/*    */ 
/*    */   public void dispose(String paramString)
/*    */   {
/* 59 */     this.a.b(paramString);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\ui.jar
 * Qualified Name:     io.dcloud.feature.ui.UIFeatureImpl
 * JD-Core Version:    0.6.2
 */