/*     */ package io.dcloud;
/*     */ 
/*     */ import android.content.ComponentName;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.IntentFilter;
/*     */ import android.content.ServiceConnection;
/*     */ import android.os.Bundle;
/*     */ import android.os.Handler;
/*     */ import android.os.IBinder;
/*     */ import android.text.TextUtils;
/*     */ import io.dcloud.common.DHInterface.IActivityHandler;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IReflectAble;
/*     */ import io.dcloud.common.adapter.io.DHFile;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.constant.DataInterface;
/*     */ import io.dcloud.common.util.ImageLoaderUtil;
/*     */ import io.dcloud.feature.internal.reflect.BroadcastReceiver;
/*     */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*     */ import io.src.dcloud.adapter.DCloudBaseActivity;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ abstract class b extends DCloudBaseActivity
/*     */   implements IActivityHandler, IReflectAble
/*     */ {
/*  47 */   private HashMap<String, c> a = new HashMap();
/*     */   InvokeExecutorHelper.InvokeExecutor d;
/* 131 */   private ServiceConnection b = new ServiceConnection()
/*     */   {
/*     */     public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
/*     */     {
/* 135 */       b.this.d = InvokeExecutorHelper.createIDownloadService(paramAnonymousIBinder);
/* 136 */       Logger.d("stream_manager", "onServiceConnected ;tService =" + (b.this.d != null));
/*     */     }
/*     */ 
/*     */     public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
/*     */     {
/* 141 */       b.this.d = null;
/*     */     }
/* 131 */   };
/*     */ 
/* 157 */   static final InvokeExecutorHelper.InvokeExecutor e = InvokeExecutorHelper.AppStreamUtils;
/* 158 */   static final int f = e != null ? e.getInt("PRIORITY_MIN") : 0;
/* 159 */   static final int g = e != null ? e.getInt("CONTRACT_STATUS_SUCCESS") : 0;
/* 160 */   static final int h = e != null ? e.getInt("CONTRACT_TYPE_WAP2APP_INDEX_ZIP") : 0;
/* 161 */   static final int i = e != null ? e.getInt("CONTRACT_TYPE_STREAM_MAIN_PAGE") : 0;
/* 162 */   static final int j = e != null ? e.getInt("CONTRACT_TYPE_STREAM_APP") : 0;
/* 163 */   static final Class[] k = { String.class, Integer.TYPE, String.class, String.class };
/*     */ 
/* 200 */   Class[] l = { String.class, Integer.TYPE, String.class, String.class };
/*     */ 
/* 209 */   private static String c = "www/";
/*     */ 
/* 240 */   final Class[] m = { String.class, Integer.TYPE, Integer.TYPE, String.class };
/*     */ 
/* 246 */   final Class[] n = { String.class, String.class, String.class, Integer.TYPE, String.class, String.class };
/*     */ 
/* 252 */   final Class[] o = { String.class, String.class };
/*     */ 
/*     */   public void onCreate(Bundle paramBundle)
/*     */   {
/*  36 */     super.onCreate(paramBundle);
/*  37 */     AndroidResources.initAndroidResources(this.that);
/*  38 */     ImageLoaderUtil.initImageLoader(this.that);
/*  39 */     if (isStreamAppMode())
/*  40 */       bindDCloudServices();
/*     */   }
/*     */ 
/*     */   public boolean isStreamAppMode()
/*     */   {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */   public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter, String paramString, Handler paramHandler)
/*     */   {
/*  50 */     Intent localIntent = null;
/*  51 */     c localc = new c(paramBroadcastReceiver, paramIntentFilter);
/*     */     try {
/*  53 */       localIntent = registerReceiver(localc, paramIntentFilter, paramString, paramHandler);
/*  54 */       this.a.put(paramBroadcastReceiver.toString(), localc);
/*     */     } catch (Exception localException) {
/*  56 */       localException.printStackTrace();
/*     */     }
/*  58 */     return localIntent;
/*     */   }
/*     */ 
/*     */   public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter) {
/*  62 */     Intent localIntent = null;
/*  63 */     c localc = new c(paramBroadcastReceiver, paramIntentFilter);
/*     */     try {
/*  65 */       localIntent = registerReceiver(localc, paramIntentFilter);
/*  66 */       this.a.put(paramBroadcastReceiver.toString(), localc);
/*     */     }
/*     */     catch (Exception localException) {
/*  69 */       localException.printStackTrace();
/*     */     }
/*  71 */     return localIntent;
/*     */   }
/*     */ 
/*     */   public void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver) {
/*  75 */     c localc = (c)this.a.remove(paramBroadcastReceiver.toString());
/*  76 */     if (localc != null)
/*  77 */       unregisterReceiver(localc);
/*     */   }
/*     */ 
/*     */   private void a()
/*     */   {
/*  82 */     Collection localCollection = this.a.values();
/*  83 */     for (c localc : localCollection) {
/*  84 */       unregisterReceiver(localc);
/*     */     }
/*  86 */     this.a.clear();
/*     */   }
/*     */ 
/*     */   public void onDestroy() {
/*  90 */     super.onDestroy();
/*     */     try {
/*  92 */       a();
/*  93 */       if (isStreamAppMode()) {
/*  94 */         b();
/*  95 */         unbindDCloudServices();
/*     */       }
/*     */     } catch (Exception localException) {
/*  98 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void b() {
/* 103 */     Intent localIntent = new Intent();
/* 104 */     localIntent.setClassName(getPackageName(), DCloudAdapterUtil.getDcloudDownloadService());
/* 105 */     localIntent.setAction("com.qihoo.appstore.plugin.streamapp.ACTION_PUSH_TO_PLUGIN");
/* 106 */     localIntent.putExtra("_____flag____", true);
/* 107 */     localIntent.putExtra("_____collect____", true);
/* 108 */     startService(localIntent);
/*     */   }
/*     */ 
/*     */   public void unbindDCloudServices() {
/* 112 */     unbindService(this.b);
/*     */   }
/*     */   public void bindDCloudServices() {
/* 115 */     Class localClass = null;
/*     */     try {
/* 117 */       localClass = Class.forName(DCloudAdapterUtil.getDcloudDownloadService());
/*     */     } catch (ClassNotFoundException localClassNotFoundException) {
/* 119 */       localClassNotFoundException.printStackTrace();
/*     */     }
/* 121 */     if (localClass != null) {
/* 122 */       Intent localIntent = new Intent(this.that, localClass);
/*     */ 
/* 125 */       bindService(localIntent, this.b, 1);
/*     */ 
/* 127 */       Logger.d("stream_manager", "bindDCloudServices");
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean raiseFilePriority(String paramString1, String paramString2)
/*     */   {
/* 147 */     Logger.d("stream_manager", "raiseFilePriority filePath=" + paramString1 + ";tService =" + (this.d != null));
/* 148 */     if (this.d != null) {
/*     */       try {
/* 150 */         return this.d.invoke("setCurrentPage", getUrlByFilePath(paramString2, paramString1), false);
/*     */       } catch (Exception localException) {
/* 152 */         Logger.d("stream_manager", new Object[] { "addAppStreamTask filePath=" + paramString1, localException });
/*     */       }
/*     */     }
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */   public void addAppStreamTask(String paramString1, String paramString2)
/*     */   {
/* 166 */     Logger.d("stream_manager", "addAppStreamTask filePath=" + paramString1 + ";tService =" + (this.d != null));
/* 167 */     if (this.d != null)
/*     */       try {
/* 169 */         this.d.invoke("addSimpleFileTask", k, new Object[] { getUrlByFilePath(paramString2, paramString1), Integer.valueOf(f), paramString1, paramString2 });
/*     */       } catch (Exception localException) {
/* 171 */         Logger.d("stream_manager", new Object[] { "addAppStreamTask filePath=" + paramString1, localException });
/*     */       }
/*     */   }
/*     */ 
/*     */   public boolean queryUrl(String paramString1, String paramString2)
/*     */   {
/*     */     try
/*     */     {
/* 179 */       int i1 = ((Integer)e.invoke("checkPageOrResourceStatus", new Class[] { String.class, String.class }, new Object[] { paramString1, paramString2 })).intValue();
/* 180 */       boolean bool = i1 == g;
/* 181 */       Logger.d("stream_manager", "queryUrl filePath=" + paramString1 + ";ret =" + bool);
/* 182 */       return bool;
/*     */     } catch (Exception localException) {
/* 184 */       localException.printStackTrace();
/*     */     }
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */   public void resumeAppStreamTask(String paramString) {
/* 190 */     Logger.d("Main_Path", "resumeAppStreamTask app=" + paramString + ";tService =" + (this.d != null));
/*     */     try {
/* 192 */       if (this.d != null)
/* 193 */         this.d.invoke("resumeDownload", paramString);
/*     */     }
/*     */     catch (Exception localException) {
/* 196 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void downloadSimpleFileTask(IApp paramIApp, String paramString1, String paramString2, String paramString3)
/*     */   {
/* 204 */     if (this.d != null)
/* 205 */       this.d.invoke("addSimpleFileTask", this.l, new Object[] { paramString1, Integer.valueOf(f), paramString2, paramString3 });
/*     */   }
/*     */ 
/*     */   public String getUrlByFilePath(String paramString1, String paramString2)
/*     */   {
/* 211 */     String str1 = DataInterface.getBaseUrl();
/* 212 */     if (this.d != null) {
/* 213 */       str1 = this.d.invoke("getAppRootUrl", paramString1);
/*     */ 
/* 216 */       if (TextUtils.isEmpty(str1)) {
/* 217 */         str2 = (String)InvokeExecutorHelper.AppStreamUtils.invoke("getJsonFilePath", new Class[] { String.class }, new Object[] { paramString1 });
/*     */ 
/* 219 */         String str3 = "";
/*     */         try {
/* 221 */           byte[] arrayOfByte = DHFile.readAll(str2);
/* 222 */           if (arrayOfByte != null) {
/* 223 */             str3 = new String(arrayOfByte);
/*     */           }
/*     */ 
/* 226 */           JSONObject localJSONObject = new JSONObject(str3);
/* 227 */           str1 = localJSONObject.getString("rooturl");
/*     */         }
/*     */         catch (Exception localException) {
/*     */         }
/*     */       }
/*     */     }
/* 233 */     String str2 = paramString2.substring(paramString2.indexOf(c) + c.length());
/* 234 */     return str1 + str2;
/*     */   }
/*     */ 
/*     */   public Context getContext() {
/* 238 */     return this.that;
/*     */   }
/*     */ 
/*     */   public void commitPointData0(String paramString1, int paramInt1, int paramInt2, String paramString2) {
/* 242 */     if (this.d != null)
/* 243 */       this.d.invoke("commitPointData0", this.m, new Object[] { paramString1, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramString2 });
/*     */   }
/*     */ 
/*     */   public void commitPointData(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5)
/*     */   {
/* 248 */     if (this.d != null)
/* 249 */       this.d.invoke("commitPointData", this.n, new Object[] { paramString1, paramString2, paramString3, Integer.valueOf(paramInt), paramString4, paramString5 });
/*     */   }
/*     */ 
/*     */   public void commitActivateData(String paramString1, String paramString2)
/*     */   {
/* 257 */     if (this.d != null)
/* 258 */       this.d.invoke("commitActivateData", this.o, new Object[] { paramString1, paramString2 });
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.b
 * JD-Core Version:    0.6.2
 */