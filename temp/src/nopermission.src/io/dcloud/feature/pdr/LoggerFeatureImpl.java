/*    */ package io.dcloud.feature.pdr;
/*    */ 
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.IApp;
/*    */ import io.dcloud.common.DHInterface.IFeature;
/*    */ import io.dcloud.common.DHInterface.IFrameView;
/*    */ import io.dcloud.common.DHInterface.IWebview;
/*    */ import io.dcloud.common.adapter.io.DHFile;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class LoggerFeatureImpl
/*    */   implements IFeature
/*    */ {
/*    */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*    */   {
/*    */     String str;
/* 26 */     if (paramString.equals("logLevel")) {
/* 27 */       str = paramIWebview.obtainFrameView().obtainApp().obtainAppLog();
/* 28 */       b.a(str);
/* 29 */       if (paramArrayOfString[0].equals("LOG"))
/* 30 */         b.a("LOG", paramArrayOfString[1]);
/* 31 */       else if (paramArrayOfString[0].equals("ERROR"))
/* 32 */         b.b("ERROR", paramArrayOfString[1]);
/* 33 */       else if (paramArrayOfString[0].equals("WARN"))
/* 34 */         b.c("WARN", paramArrayOfString[1]);
/* 35 */       else if (paramArrayOfString[0].equals("INFO")) {
/* 36 */         b.f("INFO", paramArrayOfString[1]);
/*    */       }
/* 37 */       else if (!paramArrayOfString[0].equals("ASSERT"));
/*    */     }
/* 40 */     else if (paramString.equals("clear")) {
/* 41 */       str = paramIWebview.obtainFrameView().obtainApp().obtainAppLog();
/*    */       try {
/* 43 */         DHFile.deleteFile(str);
/*    */       } catch (IOException localIOException) {
/* 45 */         localIOException.printStackTrace();
/*    */       }
/*    */     }
/* 48 */     return null;
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
 * Qualified Name:     io.dcloud.feature.pdr.LoggerFeatureImpl
 * JD-Core Version:    0.6.2
 */