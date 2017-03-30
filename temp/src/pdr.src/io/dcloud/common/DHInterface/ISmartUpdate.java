/*    */ package io.dcloud.common.DHInterface;
/*    */ 
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public abstract interface ISmartUpdate
/*    */ {
/*    */   public abstract void checkUpdate();
/*    */ 
/*    */   public abstract void update(SmartUpdateCallbackParams paramSmartUpdateCallbackParams);
/*    */ 
/*    */   public abstract void reload();
/*    */ 
/*    */   public static class SmartUpdateCallbackParams
/*    */   {
/* 19 */     public boolean needUpdate = false;
/* 20 */     public int type = 1;
/*    */     public String url;
/*    */     public String icon_url;
/*    */     public String splash_url;
/*    */     public String wap2app_url;
/*    */     public String oldVersion;
/*    */     public String updateVersion;
/*    */     public int mode;
/*    */ 
/*    */     public SmartUpdateCallbackParams(String paramString)
/*    */     {
/*    */       try
/*    */       {
/* 31 */         JSONObject localJSONObject = new JSONObject(paramString);
/* 32 */         int i = localJSONObject.optInt("status", 0);
/* 33 */         i = localJSONObject.optInt("ret", i == 1 ? 0 : 1);
/* 34 */         this.url = localJSONObject.optString("url");
/* 35 */         this.icon_url = localJSONObject.optString("icon_url");
/* 36 */         this.splash_url = localJSONObject.optString("splash_url");
/* 37 */         this.wap2app_url = localJSONObject.optString("wap2app_url");
/* 38 */         if (i == 0) {
/* 39 */           String str = localJSONObject.optString("type");
/* 40 */           this.updateVersion = localJSONObject.optString("updateVersion");
/* 41 */           this.needUpdate = true;
/* 42 */           if (str.equals("wgt")) {
/* 43 */             this.type = 0;
/*    */           }
/*    */         }
/* 46 */         this.mode = localJSONObject.optInt("up_mode");
/*    */       } catch (JSONException localJSONException) {
/* 48 */         localJSONException.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public static abstract interface OnSmartUpdateCallback
/*    */   {
/*    */     public abstract void onCallback(ISmartUpdate.SmartUpdateCallbackParams paramSmartUpdateCallbackParams);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.ISmartUpdate
 * JD-Core Version:    0.6.2
 */