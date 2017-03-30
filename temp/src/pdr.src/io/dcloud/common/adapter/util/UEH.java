/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.app.ActivityManager;
/*     */ import android.app.ActivityManager.RunningAppProcessInfo;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.pm.ApplicationInfo;
/*     */ import android.os.Build;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Debug.MemoryInfo;
/*     */ import android.os.Environment;
/*     */ import android.os.Process;
/*     */ import io.dcloud.common.constant.StringConst;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.BaseInfo.CmtInfo;
/*     */ import io.dcloud.common.util.NetTool;
/*     */ import io.dcloud.common.util.NetworkTypeUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.TelephonyUtil;
/*     */ import io.dcloud.common.util.ThreadPool;
/*     */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.lang.reflect.Field;
/*     */ import java.net.URLEncoder;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class UEH
/*     */ {
/*     */   private static final String CRASH_DIRECTORY = "crash/";
/*     */   private static final boolean SAVE_CRASH_LOG = true;
/*  38 */   private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINESE);
/*     */ 
/*  40 */   public static boolean sInited = false;
/*     */ 
/*  42 */   public static void catchUncaughtException(Context paramContext) { if (!sInited) {
/*  43 */       Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
/*     */       {
/*     */         public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable) {
/*  46 */           File localFile = UEH.handleUncaughtException(this.val$context, paramAnonymousThrowable);
/*     */           try
/*     */           {
/*  49 */             if (BaseInfo.getCmitInfo(BaseInfo.sLastRunApp).rptCrs)
/*  50 */               UEH.commitUncatchException(this.val$context, paramAnonymousThrowable);
/*     */           }
/*     */           catch (Exception localException) {
/*  53 */             localException.printStackTrace();
/*  54 */             Logger.e("UncaughtExceptionHandler", "commitUncatchException");
/*     */           }
/*  56 */           paramAnonymousThrowable.printStackTrace();
/*  57 */           Logger.e("UncaughtExceptionHandler", paramAnonymousThrowable.toString());
/*  58 */           Process.killProcess(Process.myPid());
/*     */         }
/*     */       });
/*  61 */       sInited = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static File handleUncaughtException(Context paramContext, Throwable paramThrowable)
/*     */   {
/*  81 */     File localFile1 = null;
/*     */     try {
/*  83 */       Field[] arrayOfField = Build.class.getDeclaredFields();
/*  84 */       StringBuffer localStringBuffer = new StringBuffer();
/*     */       File localFile2;
/*  85 */       for (localFile2 : arrayOfField) {
/*     */         try {
/*  87 */           localFile2.setAccessible(true);
/*  88 */           String str2 = localFile2.getName() + ":" + localFile2.get(null) + "\n";
/*  89 */           localStringBuffer.append(str2);
/*     */         } catch (Exception localException2) {
/*  91 */           localException2.printStackTrace();
/*     */         }
/*     */       }
/*     */ 
/*  95 */       ??? = new StringWriter();
/*  96 */       PrintWriter localPrintWriter = new PrintWriter((Writer)???);
/*  97 */       paramThrowable.printStackTrace(localPrintWriter);
/*  98 */       String str1 = ((StringWriter)???).toString();
/*     */ 
/* 100 */       if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState()))
/*     */       {
/* 103 */         localFile2 = new File(BaseInfo.getCrashLogsPath(paramContext) + "crash/");
/*     */       } else {
/* 105 */         File localFile3 = paramContext.getCacheDir();
/* 106 */         localFile2 = new File(localFile3.getAbsolutePath() + "crash/");
/*     */       }
/*     */ 
/* 110 */       if (!localFile2.exists()) {
/* 111 */         localFile2.mkdirs();
/*     */       }
/*     */ 
/* 114 */       long l = System.currentTimeMillis();
/* 115 */       String str3 = formatter.format(new Date());
/* 116 */       String str4 = "crash_" + l + "_" + str3 + ".log";
/* 117 */       localFile1 = new File(localFile2.getAbsolutePath(), str4);
/* 118 */       FileOutputStream localFileOutputStream = new FileOutputStream(localFile1);
/* 119 */       localStringBuffer.append(str1);
/* 120 */       localFileOutputStream.write(localStringBuffer.toString().getBytes());
/* 121 */       localFileOutputStream.flush();
/* 122 */       localFileOutputStream.close();
/*     */     } catch (Exception localException1) {
/* 124 */       localException1.printStackTrace();
/*     */     }
/* 126 */     return localFile1;
/*     */   }
/*     */   public static void commitUncatchException(Context paramContext, String paramString1, String paramString2) {
/* 129 */     StringBuffer localStringBuffer = new StringBuffer();
/* 130 */     commitBaseUncatchInfo(paramContext, localStringBuffer);
/* 131 */     localStringBuffer.append("etype=2");
/* 132 */     localStringBuffer.append("&log=" + PdrUtil.encodeURL(paramString2));
/* 133 */     localStringBuffer.append("&eurl=" + PdrUtil.encodeURL(paramString1));
/* 134 */     commitErrorLog(paramContext, localStringBuffer.toString());
/*     */   }
/*     */   private static void commitBaseUncatchInfo(Context paramContext, StringBuffer paramStringBuffer) {
/* 137 */     String str1 = TelephonyUtil.getIMEI(paramContext);
/* 138 */     int i = NetworkTypeUtil.getNetworkType(paramContext);
/* 139 */     String str2 = null;
/*     */     try {
/* 141 */       str2 = URLEncoder.encode(Build.MODEL, "utf-8");
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/* 143 */       localUnsupportedEncodingException.printStackTrace();
/*     */     }
/* 145 */     int j = Build.VERSION.SDK_INT;
/* 146 */     paramStringBuffer.append("s=99");
/* 147 */     paramStringBuffer.append("&imei=" + str1);
/* 148 */     paramStringBuffer.append("&md=" + str2);
/* 149 */     paramStringBuffer.append("&os=" + j);
/* 150 */     paramStringBuffer.append("&appid=" + BaseInfo.sLastRunApp);
/* 151 */     paramStringBuffer.append("&net=" + i);
/* 152 */     paramStringBuffer.append("&vb=" + BaseInfo.sBaseVersion);
/* 153 */     paramStringBuffer.append("&appcount=" + BaseInfo.s_Runing_App_Count);
/* 154 */     paramStringBuffer.append("&wvcount=" + BaseInfo.s_Webview_Count);
/* 155 */     paramStringBuffer.append("&pn=" + paramContext.getPackageName());
/* 156 */     paramStringBuffer.append("&mem=" + getAppUseMem(paramContext));
/* 157 */     paramStringBuffer.append("&vd=" + Build.MANUFACTURER);
/*     */   }
/*     */ 
/*     */   private static void commitUncatchException(Context paramContext, Throwable paramThrowable) {
/* 161 */     StringWriter localStringWriter = new StringWriter();
/* 162 */     PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
/* 163 */     paramThrowable.printStackTrace(localPrintWriter);
/* 164 */     String str = localStringWriter.toString();
/* 165 */     StringBuffer localStringBuffer = new StringBuffer();
/* 166 */     commitBaseUncatchInfo(paramContext, localStringBuffer);
/* 167 */     localStringBuffer.append("etype=1");
/* 168 */     localStringBuffer.append("&log=" + PdrUtil.encodeURL(str));
/* 169 */     commitErrorLog(paramContext, localStringBuffer.toString());
/*     */   }
/*     */ 
/*     */   private static String getAppUseMem(Context paramContext) {
/*     */     try {
/* 174 */       localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
/* 175 */       List localList = localActivityManager.getRunningAppProcesses();
/* 176 */       for (ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo : localList)
/*     */       {
/* 178 */         int i = localRunningAppProcessInfo.uid;
/* 179 */         if (paramContext.getApplicationInfo().uid == i)
/*     */         {
/* 181 */           int j = localRunningAppProcessInfo.pid;
/*     */ 
/* 183 */           String str = localRunningAppProcessInfo.processName;
/*     */ 
/* 185 */           int[] arrayOfInt = { j };
/*     */ 
/* 187 */           Debug.MemoryInfo[] arrayOfMemoryInfo = localActivityManager.getProcessMemoryInfo(arrayOfInt);
/*     */ 
/* 189 */           int k = arrayOfMemoryInfo[0].dalvikPrivateDirty;
/* 190 */           return k + "kb";
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Throwable localThrowable)
/*     */     {
/*     */       ActivityManager localActivityManager;
/* 194 */       localThrowable.printStackTrace();
/*     */     }
/* 196 */     return "";
/*     */   }
/*     */   private static void commitErrorLog(Context paramContext, String paramString) {
/* 199 */     if ((BaseInfo.isStreamApp(paramContext)) || (BaseInfo.isForQihooHelper(paramContext))) {
/* 200 */       Class localClass = null;
/*     */       try {
/* 202 */         localClass = Class.forName(DCloudAdapterUtil.getDcloudDownloadService());
/*     */       } catch (ClassNotFoundException localClassNotFoundException) {
/* 204 */         localClassNotFoundException.printStackTrace();
/*     */       }
/* 206 */       if (localClass != null) {
/* 207 */         Intent localIntent = new Intent(paramContext, localClass);
/* 208 */         localIntent.putExtra("_____flag____", true);
/* 209 */         localIntent.putExtra("_____error____", true);
/* 210 */         localIntent.putExtra("msg", paramString);
/* 211 */         paramContext.startService(localIntent);
/*     */       }
/*     */     } else {
/* 214 */       ThreadPool.self().addThreadTask(new Runnable() {
/*     */         public void run() {
/* 216 */           HashMap localHashMap = new HashMap();
/* 217 */           localHashMap.put("Content-Type", "application/x-www-form-urlencoded");
/* 218 */           NetTool.httpPost(StringConst.STREAMAPP_KEY_BASESERVICEURL() + "collect/crash", this.val$msg, localHashMap);
/*     */         }
/*     */       });
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void commitUncatchException(File paramFile)
/*     */   {
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.UEH
 * JD-Core Version:    0.6.2
 */