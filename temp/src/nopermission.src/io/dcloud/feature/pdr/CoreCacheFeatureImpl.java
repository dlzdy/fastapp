/*    */ package io.dcloud.feature.pdr;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.SharedPreferences;
/*    */ import android.content.SharedPreferences.Editor;
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.IApp;
/*    */ import io.dcloud.common.DHInterface.IFeature;
/*    */ import io.dcloud.common.DHInterface.IFrameView;
/*    */ import io.dcloud.common.DHInterface.IWebview;
/*    */ import io.dcloud.common.adapter.io.DHFile;
/*    */ import io.dcloud.common.util.JSUtil;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class CoreCacheFeatureImpl
/*    */   implements IFeature
/*    */ {
/*    */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*    */   {
/*    */     Object localObject;
/* 32 */     if (paramString.equals("clear"))
/*    */     {
/* 34 */       localObject = paramIWebview.obtainFrameView().obtainApp().obtainAppWebCachePath();
/*    */       try {
/* 36 */         DHFile.deleteFile((String)localObject);
/*    */       } catch (IOException localIOException) {
/* 38 */         localIOException.printStackTrace();
/*    */       }
/* 40 */       JSUtil.excCallbackSuccess(paramIWebview, paramArrayOfString[0], "");
/* 41 */     } else if (paramString.equals("calculate")) {
/* 42 */       localObject = new File(paramIWebview.obtainFrameView().obtainApp().obtainAppWebCachePath());
/* 43 */       long l2 = 0L;
/* 44 */       if (((File)localObject).exists()) {
/* 45 */         l2 = DHFile.getFileSize((File)localObject);
/*    */       }
/* 47 */       JSUtil.execCallback(paramIWebview, paramArrayOfString[0], l2, JSUtil.OK, false);
/* 48 */     } else if (paramString.equals("setMaxSize")) {
/* 49 */       long l1 = Long.parseLong(paramArrayOfString[0]);
/* 50 */       Context localContext = paramIWebview.getContext();
/* 51 */       String str = paramIWebview.obtainFrameView().obtainApp().obtainAppId();
/* 52 */       SharedPreferences localSharedPreferences = localContext.getSharedPreferences(str, 0);
/* 53 */       SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 54 */       localEditor.putLong("maxSize", l1);
/* 55 */       localEditor.commit();
/*    */     }
/* 57 */     return null;
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
 * Qualified Name:     io.dcloud.feature.pdr.CoreCacheFeatureImpl
 * JD-Core Version:    0.6.2
 */