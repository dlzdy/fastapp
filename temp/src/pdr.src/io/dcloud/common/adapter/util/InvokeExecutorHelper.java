/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.os.IBinder;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.ISmartUpdate;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ public class InvokeExecutorHelper
/*     */ {
/*  12 */   public static InvokeExecutor AppStreamUpdateManager = create("io.dcloud.streamdownload.AppStreamUpdateManager");
/*  13 */   public static InvokeExecutor AppidUtils = create("io.dcloud.streamdownload.utils.AppidUtils");
/*  14 */   public static InvokeExecutor StorageUtils = create("io.dcloud.streamdownload.utils.StorageUtils");
/*  15 */   public static InvokeExecutor AppStreamUtils = create("io.dcloud.streamdownload.utils.AppStreamUtils");
/*  16 */   public static InvokeExecutor QihooInnerStatisticUtil = create("com.qihoo.appstore.plugin.streamapp.QihooInnerStatisticUtil");
/*  17 */   public static InvokeExecutor QHPushHelper = create("com.qihoo.appstore.plugin.streamapp.QHPushHelper");
/*  18 */   public static InvokeExecutor StreamAppListActivity = create("io.dcloud.appstream.StreamAppMainActivity");
/*  19 */   public static InvokeExecutor TrafficFreeHelper = create("com.qihoo.appstore.utils.trafficFree.TrafficFreeHelper");
/*  20 */   public static InvokeExecutor TrafficProxy = create("com.qihoo.appstore.utils.traffic.TrafficProxy");
/*     */ 
/*  23 */   public static InvokeExecutor AesEncryptionUtil = create("io.dcloud.feature.encryption.AesEncryptionUtil");
/*  24 */   public static InvokeExecutor RSAUtils = create("io.dcloud.feature.encryption.RSAUtils");
/*     */ 
/*  26 */   public static InvokeExecutor DownloadTaskListManager = create("io.dcloud.streamdownload.DownloadTaskListManager");
/*     */ 
/*     */   public static ISmartUpdate createWebAppSmartUpdater(IApp paramIApp) {
/*  29 */     return (ISmartUpdate)createInvokeExecutor("io.dcloud.appstream.WebAppSmartUpdater", new Class[] { IApp.class }, new Object[] { paramIApp }).mObj;
/*     */   }
/*     */ 
/*     */   public static InvokeExecutor createIDownloadService(IBinder paramIBinder) {
/*  33 */     InvokeExecutor localInvokeExecutor = new InvokeExecutor();
/*     */     try {
/*  35 */       localInvokeExecutor.mCls = Class.forName("io.dcloud.streamdownload.IDownloadService");
/*  36 */       Method localMethod = Class.forName("io.dcloud.streamdownload.IDownloadService$Stub").getMethod("asInterface", new Class[] { IBinder.class });
/*  37 */       localInvokeExecutor.mObj = localMethod.invoke(null, new Object[] { paramIBinder });
/*     */     } catch (Exception localException) {
/*  39 */       localException.printStackTrace();
/*     */     }
/*  41 */     return localInvokeExecutor;
/*     */   }
/*     */ 
/*     */   public static InvokeExecutor createDownloadTaskListManager() {
/*  45 */     InvokeExecutor localInvokeExecutor = new InvokeExecutor();
/*     */     try {
/*  47 */       localInvokeExecutor.mCls = Class.forName("io.dcloud.streamdownload.DownloadTaskListManager");
/*  48 */       Method localMethod = Class.forName("io.dcloud.streamdownload.DownloadTaskListManager").getMethod("getInstance", new Class[0]);
/*  49 */       localInvokeExecutor.mObj = localMethod.invoke(null, new Object[0]);
/*     */     } catch (Exception localException) {
/*  51 */       localException.printStackTrace();
/*     */     }
/*  53 */     return localInvokeExecutor;
/*     */   }
/*     */ 
/*     */   public static InvokeExecutor create(String paramString) {
/*  57 */     InvokeExecutor localInvokeExecutor = new InvokeExecutor();
/*     */     try {
/*  59 */       localInvokeExecutor.mCls = Class.forName(paramString);
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/*  63 */     return localInvokeExecutor;
/*     */   }
/*     */ 
/*     */   public static InvokeExecutor createInvokeExecutor(String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject) {
/*  67 */     InvokeExecutor localInvokeExecutor = new InvokeExecutor();
/*     */     try {
/*  69 */       localInvokeExecutor.mCls = Class.forName(paramString);
/*  70 */       localInvokeExecutor.mObj = localInvokeExecutor.mCls.getConstructor(paramArrayOfClass).newInstance(paramArrayOfObject);
/*     */     } catch (Exception localException) {
/*  72 */       localException.printStackTrace();
/*  73 */       Logger.d("createInvokeExecutor clsName=" + paramString);
/*     */     }
/*  75 */     return localInvokeExecutor;
/*     */   }
/*     */ 
/*     */   public static class InvokeExecutor
/*     */   {
/* 163 */     Class mCls = null;
/* 164 */     Object mObj = null;
/*     */ 
/*     */     public final InvokeExecutor setInstance(Object paramObject)
/*     */     {
/*  80 */       this.mObj = paramObject;
/*  81 */       return this;
/*     */     }
/*     */ 
/*     */     public final boolean hasObject() {
/*  85 */       return this.mObj != null;
/*     */     }
/*     */     public final String invoke(String paramString) {
/*     */       try {
/*  89 */         if (this.mCls != null) {
/*  90 */           Method localMethod = this.mCls.getMethod(paramString, new Class[0]);
/*  91 */           if (localMethod != null)
/*  92 */             return (String)localMethod.invoke(this.mObj, new Object[0]);
/*     */         }
/*     */       }
/*     */       catch (Exception localException) {
/*  96 */         localException.printStackTrace();
/*     */       }
/*  98 */       return "";
/*     */     }
/*     */ 
/*     */     public final String invoke(String paramString1, String paramString2) {
/*     */       try {
/* 103 */         if (this.mCls != null) {
/* 104 */           Method localMethod = this.mCls.getMethod(paramString1, new Class[] { String.class });
/* 105 */           if (localMethod != null)
/* 106 */             return (String)localMethod.invoke(this.mObj, new Object[] { paramString2 });
/*     */         }
/*     */       }
/*     */       catch (Exception localException) {
/* 110 */         localException.printStackTrace();
/*     */       }
/* 112 */       return paramString2;
/*     */     }
/*     */ 
/*     */     public final int getInt(String paramString) {
/* 116 */       int i = -10000;
/*     */       try {
/* 118 */         if (this.mCls != null)
/* 119 */           return ((Integer)this.mCls.getField(paramString).get(this.mObj)).intValue();
/*     */       }
/*     */       catch (Exception localException) {
/* 122 */         localException.printStackTrace();
/*     */       }
/* 124 */       return i;
/*     */     }
/*     */ 
/*     */     public final String getString(String paramString) {
/*     */       try {
/* 129 */         if (this.mCls != null)
/* 130 */           return (String)this.mCls.getField(paramString).get(this.mObj);
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/* 135 */       return null;
/*     */     }
/*     */     public final boolean invoke(String paramString1, String paramString2, boolean paramBoolean) {
/*     */       try {
/* 139 */         if (this.mCls != null) {
/* 140 */           Method localMethod = this.mCls.getMethod(paramString1, new Class[] { String.class });
/* 141 */           if (localMethod != null)
/* 142 */             return ((Boolean)localMethod.invoke(this.mObj, new Object[] { paramString2 })).booleanValue();
/*     */         }
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/* 148 */       return paramBoolean;
/*     */     }
/*     */     public final Object invoke(String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject) {
/*     */       try {
/* 152 */         if (this.mCls != null) {
/* 153 */           Method localMethod = this.mCls.getMethod(paramString, paramArrayOfClass);
/* 154 */           if (localMethod != null)
/* 155 */             return localMethod.invoke(this.mObj, paramArrayOfObject);
/*     */         }
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/* 161 */       return null;
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.InvokeExecutorHelper
 * JD-Core Version:    0.6.2
 */