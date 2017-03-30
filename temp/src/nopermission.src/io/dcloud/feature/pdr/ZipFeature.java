/*    */ package io.dcloud.feature.pdr;
/*    */ 
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.IApp;
/*    */ import io.dcloud.common.DHInterface.IFeature;
/*    */ import io.dcloud.common.DHInterface.IFrameView;
/*    */ import io.dcloud.common.DHInterface.IWebview;
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ import io.dcloud.common.util.JSUtil;
/*    */ import io.dcloud.common.util.PdrUtil;
/*    */ import io.dcloud.common.util.ZipUtils;
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class ZipFeature
/*    */   implements IFeature
/*    */ {
/*    */   HashMap<String, ArrayList<String[]>> a;
/*    */ 
/*    */   public ZipFeature()
/*    */   {
/* 19 */     this.a = null;
/*    */   }
/*    */ 
/*    */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*    */   {
/*    */     a locala;
/*    */     IApp localIApp;
/*    */     String str;
/* 23 */     if (PdrUtil.isEquals(paramString, "compress")) {
/* 24 */       locala = new a();
/* 25 */       locala.f = paramIWebview;
/* 26 */       locala.a = true;
/* 27 */       localIApp = paramIWebview.obtainFrameView().obtainApp();
/* 28 */       locala.b = paramArrayOfString[0];
/* 29 */       locala.d = localIApp.convert2AbsFullPath(paramIWebview.obtainFullUrl(), paramArrayOfString[0]);
/* 30 */       str = paramArrayOfString[1];
/* 31 */       if (str == null) {
/* 32 */         str = "_doc/" + System.currentTimeMillis();
/*    */       }
/* 34 */       if (!str.endsWith(".zip")) {
/* 35 */         str = str + ".zip";
/*    */       }
/* 37 */       str = localIApp.convert2AbsFullPath(paramIWebview.obtainFullUrl(), str);
/* 38 */       locala.c = str;
/* 39 */       locala.e = paramArrayOfString[2];
/* 40 */       a(locala);
/* 41 */     } else if (PdrUtil.isEquals(paramString, "decompress")) {
/* 42 */       locala = new a();
/* 43 */       locala.f = paramIWebview;
/* 44 */       locala.a = false;
/* 45 */       localIApp = paramIWebview.obtainFrameView().obtainApp();
/* 46 */       str = localIApp.checkPrivateDirAndCopy2Temp(paramArrayOfString[0]);
/* 47 */       locala.c = localIApp.convert2AbsFullPath(paramIWebview.obtainFullUrl(), str);
/* 48 */       locala.d = localIApp.convert2AbsFullPath(paramIWebview.obtainFullUrl(), paramArrayOfString[1]);
/* 49 */       locala.e = paramArrayOfString[2];
/* 50 */       a(locala);
/* 51 */     } else if (PdrUtil.isEquals(paramString, "compressImage")) {
/* 52 */       a.a(paramIWebview, paramArrayOfString);
/*    */     }
/* 54 */     return null;
/*    */   }
/*    */ 
/*    */   public void init(AbsMgr paramAbsMgr, String paramString)
/*    */   {
/* 59 */     this.a = new HashMap(1);
/*    */   }
/*    */ 
/*    */   private void a(a parama) {
/* 63 */     new Thread(parama).start();
/*    */   }
/*    */   public void dispose(String paramString) {
/*    */   }
/* 71 */   class a implements Runnable { boolean a = false;
/* 72 */     String b = null;
/*    */     String c;
/*    */     String d;
/*    */     String e;
/*    */     IWebview f;
/*    */ 
/*    */     a() {  } 
/* 80 */     public void run() { a locala = this;
/*    */       try
/*    */       {
/*    */         File localFile1;
/* 82 */         if (locala.a) {
/* 83 */           Logger.d("compress mUnZipDirPath=" + locala.d + ";mZipFilePath" + locala.c);
/* 84 */           localFile1 = new File(locala.d);
/* 85 */           if (!localFile1.exists()) {
/* 86 */             localObject = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(2), locala.b + " open failed:ENOENT (No such file or directory)" });
/* 87 */             JSUtil.excCallbackError(locala.f, locala.e, (String)localObject, true);
/*    */           }
/* 89 */           localObject = locala.f.obtainFrameView().obtainApp();
/* 90 */           if (JSUtil.checkOperateDirErrorAndCallback(locala.f, locala.e, locala.c)) {
/* 91 */             return;
/*    */           }
/* 93 */           File localFile2 = new File(locala.c);
/*    */ 
/* 95 */           File[] arrayOfFile = null;
/* 96 */           if (localFile1.isDirectory()) {
/* 97 */             arrayOfFile = localFile2.listFiles();
/*    */           }
/* 99 */           if ((localFile1.isFile()) || (arrayOfFile == null)) {
/* 100 */             arrayOfFile = new File[1];
/* 101 */             arrayOfFile[0] = localFile1;
/*    */           }
/* 103 */           ZipUtils.zipFiles(arrayOfFile, localFile2);
/*    */         } else {
/* 105 */           Logger.d("decompress mUnZipDirPath=" + locala.d + ";mZipFilePath" + locala.c);
/* 106 */           if (JSUtil.checkOperateDirErrorAndCallback(locala.f, locala.e, locala.d)) {
/* 107 */             return;
/*    */           }
/* 109 */           localFile1 = new File(locala.c);
/* 110 */           if (!localFile1.exists()) {
/* 111 */             localObject = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(2), locala.b + " open failed:ENOENT (No such file or directory)" });
/* 112 */             JSUtil.excCallbackError(locala.f, locala.e, (String)localObject, true);
/* 113 */             return;
/*    */           }
/* 115 */           ZipUtils.upZipFile(localFile1, locala.d);
/*    */         }
/* 117 */         JSUtil.excCallbackSuccess(locala.f, locala.e, "");
/*    */       } catch (Exception localException) {
/* 119 */         Object localObject = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(2), localException.getMessage() });
/* 120 */         JSUtil.excCallbackError(locala.f, locala.e, (String)localObject, true);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.iptv.tianjin\app\libs\nopermission.jar
 * Qualified Name:     io.dcloud.feature.pdr.ZipFeature
 * JD-Core Version:    0.6.2
 */