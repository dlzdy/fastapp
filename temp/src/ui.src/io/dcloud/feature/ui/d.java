/*    */ package io.dcloud.feature.ui;
/*    */ 
/*    */ import io.dcloud.common.DHInterface.IEventCallback;
/*    */ import io.dcloud.common.DHInterface.IFrameView;
/*    */ import io.dcloud.common.DHInterface.IWebview;
/*    */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*    */ import io.dcloud.common.adapter.ui.AdaFrameView;
/*    */ import io.dcloud.common.util.PdrUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class d extends b
/*    */   implements IEventCallback
/*    */ {
/*    */   ArrayList<IFrameView> q;
/*    */   IFrameView r;
/*    */ 
/*    */   public d(String paramString, ArrayList<IFrameView> paramArrayList, JSONObject paramJSONObject)
/*    */   {
/* 22 */     super(paramString);
/*    */ 
/* 24 */     this.q = paramArrayList;
/*    */   }
/*    */ 
/*    */   public void h()
/*    */   {
/* 30 */     this.c.b(this.r);
/* 31 */     for (IFrameView localIFrameView : this.q) {
/* 32 */       this.c.b(localIFrameView);
/*    */ 
/* 34 */       ((AdaFrameView)localIFrameView).isChildOfFrameView = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void a(boolean paramBoolean) {
/* 39 */     this.r.setVisible(paramBoolean, true);
/*    */   }
/*    */ 
/*    */   public void a(IFrameView paramIFrameView) {
/* 43 */     this.r = paramIFrameView;
/*    */   }
/*    */ 
/*    */   public String a(IWebview paramIWebview, String paramString, JSONArray paramJSONArray)
/*    */   {
/* 55 */     String str1 = null;
/*    */     try {
/* 57 */       if ("addEventListener".equals(paramString)) {
/* 58 */         JSONArray localJSONArray = paramJSONArray;
/* 59 */         String str2 = localJSONArray.getString(0);
/* 60 */         String str3 = localJSONArray.getString(1);
/* 61 */         a(str3, str2, (String)this.b.get(paramIWebview));
/* 62 */       } else if (!"setSelectIndex".equals(paramString));
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 66 */       localException.printStackTrace();
/*    */     }
/* 68 */     return str1;
/*    */   }
/*    */ 
/*    */   public AdaFrameItem e()
/*    */   {
/* 74 */     return (AdaFrameItem)this.r;
/*    */   }
/*    */ 
/*    */   public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void g()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Object onCallBack(String paramString, Object paramObject)
/*    */   {
/* 92 */     if (PdrUtil.isEquals(paramString, "selectchanged")) {
/* 93 */       a(paramString, PdrUtil.isEmpty(paramObject) ? null : String.valueOf(paramObject), false);
/*    */     }
/* 95 */     return null;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\ui.jar
 * Qualified Name:     io.dcloud.feature.ui.d
 * JD-Core Version:    0.6.2
 */