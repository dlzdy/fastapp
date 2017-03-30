/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.os.Environment;
/*     */ import android.text.TextUtils;
/*     */ import android.util.Log;
/*     */ import io.dcloud.common.adapter.io.DHFile;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.ThreadPool;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class Logger
/*     */ {
/*     */   public static final String Capture_TAG = "Capture_Tag";
/*     */   public static final String Event_TAG = "Event_Tag";
/*     */   public static final String AppMgr_TAG = "appmgr";
/*     */   public static final String StreamApp_TAG = "stream_manager";
/*     */   public static final String AutoGC_TAG = "AutoGC_Path";
/*     */   public static final String Android_System_TAG = "Android_System_Path";
/*     */   public static final String LAYOUT_TAG = "Layout_Path";
/*     */   public static final String MAIN_TAG = "Main_Path";
/*     */   public static final String AUTO_POP_PUSH_TAG = "Auto_Pop_Push_Path";
/*     */   public static final String ANIMATION_TAG = "Animation_Path";
/*     */   public static final String MAP_TAG = "Map_Path";
/*     */   public static final String VIEW_VISIBLE_TAG = "View_Visible_Path";
/*     */   private static final String MSC_TAG = "DCloud_";
/*     */   private static final String LOGTAG = "DCloud_LOG";
/*     */   private static final String EXCEPTION_TAG = "DCloud_Exception";
/*     */   private static File mLogFile;
/*  48 */   private static String LogPath = "";
/*     */ 
/*  53 */   private static boolean isStoreLog = false;
/*     */ 
/*  57 */   private static long TIMES = 432000000L;
/*     */ 
/*  62 */   private static boolean isOpen = true;
/*     */ 
/*  64 */   protected static String I = "I"; protected static String D = "D"; protected static String E = "E"; protected static String W = "W";
/*  65 */   static String pkg = "";
/*     */ 
/*  82 */   private static int MAX_CRASH_FILE_COUNT = 3;
/*     */   public static final String TIMESTAMP_HH_MM_SS_SSS = "HH:mm:ss.SSS";
/*     */   public static final String TIMESTAMP_YYYY_MM_DD = "yyyyMMdd";
/*     */   public static final String TIMESTAMP_YYYY_MM_DD_HH_MM_SS_SSS = "yyyyMMdd HH:mm:ss.SSS";
/*     */ 
/*     */   public static void initLogger(Context paramContext)
/*     */   {
/*  67 */     pkg = paramContext.getPackageName();
/*  68 */     if (DeviceInfo.isSwitchDirectory) {
/*  69 */       pkg = "io.dcloud.streamapp";
/*     */     }
/*  71 */     isOpen = (BaseInfo.isBase(paramContext)) || (DHFile.hasFile());
/*     */ 
/*  73 */     isOpen |= BaseInfo.ISDEBUG;
/*  74 */     if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState()))
/*     */     {
/*  76 */       LogPath = BaseInfo.getCrashLogsPath(paramContext);
/*     */     }
/*  78 */     init(LogPath, "crash");
/*  79 */     init(LogPath + "crash/", null);
/*  80 */     setOpen(isOpen);
/*     */   }
/*     */ 
/*     */   private static void init(String paramString1, final String paramString2)
/*     */   {
/*     */     try
/*     */     {
/*  91 */       ThreadPool.self().addThreadTask(new Runnable()
/*     */       {
/*     */         public void run() {
/*  94 */           File localFile = new File(this.val$path);
/*  95 */           File[] arrayOfFile = localFile.listFiles();
/*  96 */           if ((arrayOfFile != null) && (arrayOfFile.length > Logger.MAX_CRASH_FILE_COUNT)) {
/*  97 */             int i = arrayOfFile[0].getName().compareTo(arrayOfFile[1].getName()) < 0 ? 1 : 0;
/*     */             int j;
/*  98 */             if (i != 0) {
/*  99 */               for (j = 0; j < arrayOfFile.length - Logger.MAX_CRASH_FILE_COUNT; j++) {
/* 100 */                 if (!TextUtils.equals(paramString2, arrayOfFile[j].getName()))
/* 101 */                   arrayOfFile[j].delete();
/*     */               }
/*     */             }
/*     */             else {
/* 105 */               for (j = arrayOfFile.length - 1; j >= Logger.MAX_CRASH_FILE_COUNT; j--)
/* 106 */                 if (!TextUtils.equals(paramString2, arrayOfFile[j].getName()))
/* 107 */                   arrayOfFile[j].delete();
/*     */             }
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 115 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setOpen(boolean paramBoolean) {
/* 120 */     isOpen = paramBoolean;
/* 121 */     if (paramBoolean) {
/* 122 */       File localFile = new File(LogPath);
/* 123 */       if (!localFile.exists()) {
/* 124 */         canStoreLogToSDcard();
/* 125 */         storeLogToSDcard();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isOpen() {
/* 131 */     return isOpen;
/*     */   }
/*     */ 
/*     */   public static void d(String paramString1, String paramString2)
/*     */   {
/* 141 */     if (isOpen) {
/* 142 */       if (!BaseInfo.ISDEBUG)
/* 143 */         Log.i(paramString1, paramString2);
/*     */       else {
/* 145 */         Log.d(paramString1, paramString2);
/*     */       }
/* 147 */       WriteLogToSDcard(D, paramString1, paramString2);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void d(String paramString, Object[] paramArrayOfObject)
/*     */   {
/* 157 */     if (isOpen) {
/* 158 */       StringBuffer localStringBuffer = new StringBuffer();
/* 159 */       if (paramArrayOfObject != null) {
/* 160 */         for (Object localObject : paramArrayOfObject) {
/* 161 */           localStringBuffer.append(localObject).append(";");
/*     */         }
/*     */       }
/* 164 */       if (!BaseInfo.ISDEBUG)
/* 165 */         Log.i(paramString, localStringBuffer.toString());
/*     */       else {
/* 167 */         Log.d(paramString, localStringBuffer.toString());
/*     */       }
/* 169 */       WriteLogToSDcard(D, paramString, localStringBuffer.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void d(String paramString)
/*     */   {
/* 179 */     d("DCloud_LOG", paramString);
/*     */   }
/*     */ 
/*     */   public static void i(String paramString1, String paramString2)
/*     */   {
/* 188 */     if ((isOpen) && (paramString2 != null)) {
/* 189 */       Log.i(paramString1, paramString2);
/* 190 */       WriteLogToSDcard(I, paramString1, paramString2);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void i(String paramString)
/*     */   {
/* 199 */     i("DCloud_LOG", paramString);
/*     */   }
/*     */ 
/*     */   public static void w(String paramString, Throwable paramThrowable)
/*     */   {
/* 208 */     if (isOpen) {
/* 209 */       if (paramThrowable != null)
/* 210 */         paramThrowable.printStackTrace();
/* 211 */       Log.w("DCloud_Exception", paramString, paramThrowable);
/* 212 */       WriteExceptionToSDcard(W, "DCloud_Exception", paramString, paramThrowable);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void w(Throwable paramThrowable)
/*     */   {
/* 222 */     w("", paramThrowable);
/*     */   }
/*     */ 
/*     */   public static void e(String paramString1, String paramString2)
/*     */   {
/* 231 */     if (isOpen) {
/* 232 */       Log.e(paramString1, paramString2);
/* 233 */       WriteLogToSDcard(E, paramString1, paramString2);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void e(String paramString)
/*     */   {
/* 242 */     e("DCloud_LOG", paramString);
/*     */   }
/*     */ 
/*     */   public static boolean isTurnOn()
/*     */   {
/* 255 */     return isStoreLog;
/*     */   }
/*     */ 
/*     */   public static void turnOff()
/*     */   {
/* 265 */     isStoreLog = false;
/*     */   }
/*     */ 
/*     */   protected static boolean isSDcardExists()
/*     */   {
/* 278 */     String str = Environment.getExternalStorageState();
/* 279 */     return "mounted".equals(str);
/*     */   }
/*     */ 
/*     */   private static String concatString(String paramString1, String paramString2)
/*     */   {
/* 294 */     String str = null;
/* 295 */     if ((null != paramString1) && (null != paramString2)) {
/* 296 */       int i = paramString1.length() + paramString2.length();
/* 297 */       StringBuffer localStringBuffer = new StringBuffer(i);
/* 298 */       localStringBuffer.append(paramString1).append(paramString2);
/* 299 */       str = localStringBuffer.toString();
/*     */     }
/* 301 */     return str;
/*     */   }
/*     */ 
/*     */   public static boolean canStoreLogToSDcard()
/*     */   {
/* 313 */     if ((isSDcardExists()) && (isOpen))
/*     */     {
/* 315 */       File localFile = new File(LogPath);
/* 316 */       if (!localFile.exists())
/* 317 */         localFile.mkdirs();
/*     */       else {
/* 319 */         deleteOldLog(localFile);
/*     */       }
/* 321 */       if ((localFile.exists()) && (localFile.canWrite())) {
/* 322 */         isStoreLog = true;
/*     */       }
/*     */     }
/* 325 */     return isStoreLog;
/*     */   }
/*     */ 
/*     */   protected static String generateLog(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 342 */     String str = " ";
/* 343 */     StringBuffer localStringBuffer = new StringBuffer();
/* 344 */     localStringBuffer.append(generateTimeStamp(Boolean.valueOf(true))).append(" ").append(paramString1).append(" ").append("-").append(" ").append(paramString2).append(" ").append(paramString3).append("\n");
/*     */ 
/* 347 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   private static void WriteExceptionToSDcard(String paramString1, String paramString2, String paramString3, Throwable paramThrowable)
/*     */   {
/* 361 */     if (null != paramThrowable)
/*     */     {
/* 363 */       String str1 = generateLog(paramString1, "DCloud_Exception", paramThrowable.getClass().getName());
/*     */ 
/* 365 */       WriteLogToSDcard(paramString1, paramString2, str1);
/* 366 */       StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
/* 367 */       if (null != arrayOfStackTraceElement) {
/* 368 */         int i = 0; for (int j = arrayOfStackTraceElement.length; i < j; i++)
/*     */         {
/* 370 */           StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
/* 371 */           String str2 = "    at " + localStackTraceElement.toString();
/* 372 */           String str3 = generateLog(paramString1, "DCloud_Exception", str2);
/* 373 */           WriteLogToSDcard(paramString1, paramString2, paramString3);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void WriteLogToSDcard(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 390 */     if (!isOpen) return;
/* 391 */     String str = generateLog(paramString1, paramString2, paramString3);
/* 392 */     if ((null != mLogFile) && (null != str)) {
/* 393 */       FileOutputStream localFileOutputStream = null;
/*     */       try {
/* 395 */         localFileOutputStream = new FileOutputStream(mLogFile, true);
/* 396 */         byte[] arrayOfByte = str.getBytes();
/* 397 */         localFileOutputStream.write(arrayOfByte);
/* 398 */         localFileOutputStream.flush();
/*     */       } catch (Exception localException) {
/* 400 */         localException.printStackTrace();
/*     */       } finally {
/*     */         try {
/* 403 */           if (localFileOutputStream != null)
/* 404 */             localFileOutputStream.close();
/*     */         }
/*     */         catch (IOException localIOException3) {
/* 407 */           localIOException3.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String generateTimeStamp(Boolean paramBoolean)
/*     */   {
/* 423 */     String str = null;
/* 424 */     if (paramBoolean.booleanValue())
/*     */     {
/* 426 */       str = "HH:mm:ss.SSS";
/*     */     }
/*     */     else {
/* 429 */       str = "yyyyMMdd";
/*     */     }
/* 431 */     return generateTimeStamp(str, new Date());
/*     */   }
/*     */ 
/*     */   public static String generateTimeStamp(String paramString, Date paramDate)
/*     */   {
/* 445 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString, Locale.ENGLISH);
/* 446 */     localSimpleDateFormat.applyPattern(paramString);
/* 447 */     return localSimpleDateFormat.format(paramDate);
/*     */   }
/*     */ 
/*     */   public static void storeLogToSDcard()
/*     */   {
/* 456 */     if (isStoreLog)
/*     */     {
/* 458 */       StringBuffer localStringBuffer = new StringBuffer();
/* 459 */       String str1 = generateTimeStamp(Boolean.valueOf(false)) + ".log";
/* 460 */       String str2 = "crash_" + System.currentTimeMillis() + "_" + str1 + ".log";
/* 461 */       localStringBuffer.append(LogPath).append(File.separatorChar).append(str1);
/* 462 */       mLogFile = new File(localStringBuffer.toString());
/* 463 */       if (!mLogFile.exists()) {
/*     */         try {
/* 465 */           mLogFile.createNewFile();
/*     */         } catch (IOException localIOException) {
/* 467 */           mLogFile = null;
/* 468 */           localIOException.printStackTrace();
/*     */         }
/*     */       }
/* 471 */       WriteLogToSDcard("日志路径:", "Logger", "path=" + localStringBuffer.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static void deleteOldLog(File paramFile)
/*     */   {
/* 484 */     File[] arrayOfFile1 = paramFile.listFiles();
/* 485 */     Date localDate1 = new Date();
/* 486 */     for (File localFile : arrayOfFile1)
/* 487 */       if (!localFile.isDirectory()) {
/* 488 */         String str = localFile.getName().substring(0, 8);
/* 489 */         SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
/*     */         try {
/* 491 */           Date localDate2 = localSimpleDateFormat.parse(str);
/* 492 */           if (localDate1.getTime() - localDate2.getTime() > TIMES)
/* 493 */             localFile.delete();
/*     */         }
/*     */         catch (ParseException localParseException) {
/* 496 */           localParseException.printStackTrace();
/*     */         }
/*     */       }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.Logger
 * JD-Core Version:    0.6.2
 */