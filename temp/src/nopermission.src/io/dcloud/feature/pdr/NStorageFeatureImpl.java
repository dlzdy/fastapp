/*    */ package io.dcloud.feature.pdr;
/*    */ 
/*    */ import android.content.SharedPreferences;
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.IApp;
/*    */ import io.dcloud.common.DHInterface.IFeature;
/*    */ import io.dcloud.common.DHInterface.IFrameView;
/*    */ import io.dcloud.common.DHInterface.IWebview;
/*    */ import io.dcloud.common.adapter.util.PlatformUtil;
/*    */ import io.dcloud.common.util.JSUtil;
/*    */ import io.dcloud.common.util.PdrUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class NStorageFeatureImpl
/*    */   implements IFeature
/*    */ {
/* 19 */   HashMap<String, SharedPreferences> a = new HashMap(1);
/*    */ 
/*    */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*    */   {
/* 23 */     String str1 = null;
/* 24 */     String str2 = paramIWebview.obtainFrameView().obtainApp().obtainAppId();
/* 25 */     SharedPreferences localSharedPreferences = (SharedPreferences)this.a.get(str2);
/* 26 */     if (localSharedPreferences == null) {
/* 27 */       localSharedPreferences = a(str2);
/* 28 */       this.a.put(str2, localSharedPreferences);
/*    */     }
/* 30 */     if ("getLength".equals(paramString)) {
/* 31 */       str1 = JSUtil.wrapJsVar(b(localSharedPreferences));
/*    */     }
/*    */     else
/*    */     {
/*    */       String str3;
/* 32 */       if ("getItem".equals(paramString)) {
/* 33 */         str3 = paramArrayOfString[0];
/* 34 */         if (localSharedPreferences.contains(str3))
/* 35 */           str1 = "string:" + a(localSharedPreferences, str3);
/*    */         else {
/* 37 */           str1 = "null:";
/*    */         }
/*    */ 
/*    */       }
/* 41 */       else if ("setItem".equals(paramString)) {
/* 42 */         str3 = paramArrayOfString[0];
/* 43 */         String str4 = paramArrayOfString[1];
/* 44 */         a(localSharedPreferences, str3, str4);
/* 45 */       } else if ("removeItem".equals(paramString)) {
/* 46 */         str3 = paramArrayOfString[0];
/* 47 */         b(localSharedPreferences, str3);
/* 48 */       } else if ("clear".equals(paramString)) {
/* 49 */         a(localSharedPreferences);
/* 50 */       } else if ("key".equals(paramString)) {
/* 51 */         int i = Integer.parseInt(paramArrayOfString[0]);
/* 52 */         str1 = JSUtil.wrapJsVar(a(localSharedPreferences, i), true);
/*    */       }
/*    */     }
/* 54 */     return str1;
/*    */   }
/*    */ 
/*    */   SharedPreferences a(String paramString)
/*    */   {
/* 65 */     return PlatformUtil.getOrCreateBundle(paramString + "_storages");
/*    */   }
/*    */ 
/*    */   String a(SharedPreferences paramSharedPreferences, String paramString) {
/* 69 */     return paramSharedPreferences.getString(paramString, null);
/*    */   }
/*    */   void a(SharedPreferences paramSharedPreferences, String paramString1, String paramString2) {
/* 72 */     PlatformUtil.setBundleData(paramSharedPreferences, paramString1, paramString2);
/*    */   }
/*    */   void b(SharedPreferences paramSharedPreferences, String paramString) {
/* 75 */     PlatformUtil.removeBundleData(paramSharedPreferences, paramString);
/*    */   }
/*    */   void a(SharedPreferences paramSharedPreferences) {
/* 78 */     PlatformUtil.clearBundle(paramSharedPreferences);
/*    */   }
/*    */   String a(SharedPreferences paramSharedPreferences, int paramInt) {
/* 81 */     HashMap localHashMap = (HashMap)paramSharedPreferences.getAll();
/* 82 */     return String.valueOf(PdrUtil.getKeyByIndex(localHashMap, paramInt));
/*    */   }
/*    */   int b(SharedPreferences paramSharedPreferences) {
/*    */     try {
/* 86 */       return paramSharedPreferences.getAll().size();
/*    */     } catch (Exception localException) {
/* 88 */       localException.printStackTrace();
/*    */     }
/* 90 */     return 0;
/*    */   }
/*    */ 
/*    */   public void init(AbsMgr paramAbsMgr, String paramString)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void dispose(String paramString)
/*    */   {
/*    */   }
/*    */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.iptv.tianjin\app\libs\nopermission.jar
 * Qualified Name:     io.dcloud.feature.pdr.NStorageFeatureImpl
 * JD-Core Version:    0.6.2
 */