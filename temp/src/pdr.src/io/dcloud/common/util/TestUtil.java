/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.SharedPreferences;
/*     */ import android.content.SharedPreferences.Editor;
/*     */ import android.text.TextUtils;
/*     */ import android.view.ViewGroup;
/*     */ import io.dcloud.common.DHInterface.IActivityHandler;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class TestUtil
/*     */ {
/*  18 */   public static String CREATE_WEBVIEW = "createWebview";
/*  19 */   public static String SHOW_WEBVIEW = "showWebview";
/*  20 */   public static String START_SHOW_WEBVIEW_ANIMATION = "startShowWebviewAnimation";
/*  21 */   public static String CREATE_SHOW_WEBVIEW_ANIMATION = "createShowWebviewAnimation";
/*  22 */   public static String WEBVIEW_INIT = "webview_init";
/*  23 */   public static String CREATE_NWINDOW = "createNWindow une create";
/*  24 */   public static String CREATE_VIEW_OPTIONS = "createViewOptions";
/*  25 */   public static String START_APP_SET_ROOTVIEW = "start_app_set_rootview";
/*  26 */   public static String START_STREAM_APP = "start_stream_app";
/*  27 */   public static String START_STREAM_APP_RETRY = "r";
/*  28 */   public static String STREAM_APP_POINT = "t";
/*     */   static final boolean DEBUG = true;
/*  35 */   private static ArrayList<Timer> mTimers = new ArrayList(1);
/*  36 */   private static HashMap mObjs = new HashMap(2);
/*     */   private static final String TAG = "useTime";
/*     */ 
/*     */   public static void record(String paramString, Object paramObject)
/*     */   {
/*  46 */     mObjs.put(paramString, paramObject);
/*     */   }
/*     */ 
/*     */   public static Object getRecord(String paramString)
/*     */   {
/*  55 */     Object localObject = null;
/*     */ 
/*  57 */     return mObjs.get(paramString);
/*     */   }
/*     */ 
/*     */   public static void record(String paramString)
/*     */   {
/*  67 */     record0(paramString, "");
/*     */   }
/*     */ 
/*     */   public static void record(String paramString1, String paramString2)
/*     */   {
/*  75 */     record0(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   public static void delete(String paramString) {
/*  79 */     Timer localTimer = findTimer(paramString);
/*  80 */     if (localTimer != null)
/*  81 */       mTimers.remove(localTimer);
/*     */   }
/*     */ 
/*     */   public static void timePoints(String paramString, int paramInt)
/*     */   {
/*     */   }
/*     */ 
/*     */   public static long getUseTime(String paramString1, String paramString2)
/*     */   {
/*  95 */     Timer localTimer = findTimer(paramString1);
/*  96 */     if (localTimer != null) {
/*  97 */       return localTimer.pointTime(paramString2);
/*     */     }
/*  99 */     return 0L;
/*     */   }
/*     */ 
/*     */   private static void record0(String paramString1, String paramString2) {
/* 103 */     Timer localTimer = findTimer(paramString1);
/* 104 */     if (localTimer != null) {
/* 105 */       mTimers.remove(localTimer);
/*     */     }
/* 107 */     mTimers.add(new Timer(paramString1, paramString2));
/*     */   }
/*     */ 
/*     */   public static void setRecordExtra(String paramString1, String paramString2)
/*     */   {
/* 118 */     Timer localTimer = findTimer(paramString1);
/* 119 */     if (localTimer != null)
/* 120 */       localTimer.extra = paramString2;
/*     */   }
/*     */ 
/*     */   public static void print(String paramString)
/*     */   {
/* 130 */     Timer localTimer = findTimer(paramString);
/* 131 */     if (localTimer != null)
/* 132 */       localTimer.print();
/*     */   }
/*     */ 
/*     */   public static void print(String paramString1, String paramString2)
/*     */   {
/* 139 */     Timer localTimer = findTimer(paramString1);
/* 140 */     if (localTimer != null)
/* 141 */       localTimer.print(paramString2);
/*     */   }
/*     */ 
/*     */   public static void mark(String paramString)
/*     */   {
/* 146 */     Logger.d("useTime", paramString);
/*     */   }
/*     */   public static void clearTimers() {
/* 149 */     mTimers.clear();
/*     */   }
/*     */   private static Timer findTimer(String paramString) {
/* 152 */     Object localObject = null;
/*     */ 
/* 154 */     for (int i = mTimers.size() - 1; i >= 0; i--) {
/* 155 */       Timer localTimer = (Timer)mTimers.get(i);
/* 156 */       if (localTimer.name.equals(paramString)) {
/* 157 */         localObject = localTimer;
/* 158 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 162 */     return localObject;
/*     */   }
/*     */ 
/*     */   public static void debug(ViewGroup paramViewGroup)
/*     */   {
/*     */   }
/*     */ 
/*     */   public static class DCErrorInfo
/*     */   {
/*     */     int ec;
/*     */     int et;
/*     */ 
/*     */     public DCErrorInfo(int paramInt1, int paramInt2)
/*     */     {
/* 661 */       this.ec = paramInt1;
/* 662 */       this.et = paramInt2;
/*     */     }
/*     */   }
/*     */ 
/*     */   static class Timer
/*     */   {
/*     */     String name;
/*     */     String extra;
/*     */     long birthTime;
/*     */     long wholeUseTime;
/*     */     long lastPrintTime;
/*     */     long lastPointTime;
/*     */ 
/*     */     Timer(String paramString)
/*     */     {
/* 622 */       this.name = paramString;
/* 623 */       this.lastPointTime = (this.lastPrintTime = this.birthTime = System.currentTimeMillis());
/*     */     }
/*     */     Timer(String paramString1, String paramString2) {
/* 626 */       this(paramString1);
/* 627 */       this.extra = paramString2;
/*     */     }
/*     */     void print() {
/* 630 */       this.lastPrintTime = System.currentTimeMillis();
/* 631 */       this.wholeUseTime = (this.lastPrintTime - this.birthTime);
/*     */ 
/* 633 */       Logger.i("useTime", "name :" + this.name + "; <<-- " + this.extra + " -->>" + " wholeUseTime = " + this.wholeUseTime);
/*     */     }
/*     */ 
/*     */     long pointTime(String paramString) {
/* 637 */       long l1 = this.lastPrintTime;
/* 638 */       this.lastPointTime = System.currentTimeMillis();
/* 639 */       long l2 = this.lastPointTime - l1;
/* 640 */       Logger.i("useTime", "name :" + this.name + "; <<-- " + paramString + " -->>" + " pointTime = " + l2);
/* 641 */       return l2;
/*     */     }
/*     */     void print(String paramString) {
/* 644 */       long l1 = this.lastPrintTime;
/* 645 */       print();
/* 646 */       long l2 = this.lastPrintTime - l1;
/* 647 */       Logger.e("useTime", "name :" + this.name + "; <<-- " + paramString + " -->> useTime = " + l2);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class PointTime
/*     */   {
/*     */     public static final int SUCCESS = 0;
/*     */     public static final int FILE_EXIST = 1;
/*     */     public static final int NETWORK_ERROR = 2;
/*     */     public static final int URL_ERROR = 3;
/*     */     public static final int SDCARD_ERROR = 4;
/*     */     public static final int RESUOURCE_NOT_FOUND = 5;
/*     */     public static final int CONNECT_TIMEOUT_ERROR = 7;
/*     */     public static final int SOCKET_TIMEOUT_ERROR = 8;
/*     */     public static final int OTHER_ERROR = 6;
/*     */     public static final int SDCARD_SPACE_SHORTAGE = 9;
/*     */     public static final int IO_ERROR = 10;
/*     */     public static final int FILE_DELETION_ERROR = 11;
/*     */     public static final int FILE_RENAME_ERROR = 12;
/*     */     public static final int FILE_CREATION_ERROR = 13;
/*     */     public static final int PARSE_ERROR = 14;
/*     */     public static final int DECOMPRESSION_ERROR = 15;
/*     */     public static final int NOT_NETWORK = 20;
/*     */     public static final int FILE_INPUT_READ = 16;
/*     */     public static final int UNKNOWN_HOST_ERROR = 21;
/*     */     public static final int ERROR_TYPE_STREAM = 1;
/*     */     public static final int ERROR_TYPE_INDEX_ZIP = 2;
/*     */     public static final int ERROR_TYPE_INDEXS = 3;
/*     */     public static final int ERROR_TYPE_WAP2APP_INDEX = 4;
/*     */     public static final int S_TYPE_0 = 0;
/*     */     public static final int S_TYPE_1 = 1;
/*     */     public static final int S_TYPE_2 = 2;
/*     */     public static final int S_TYPE_3 = 3;
/*     */     public static final int S_TYPE_4 = 4;
/*     */     public static final int S_TYPE_5 = 5;
/*     */     public static final int S_TYPE_6 = 6;
/*     */     public static final int S_TYPE_8 = 8;
/*     */     public static final int S_TYPE_9 = 9;
/*     */     public static final int S_TYPE_10 = 10;
/*     */     public static final int S_TYPE_11 = 11;
/*     */     public static final int S_TYPE_12 = 12;
/*     */     public static final int T_0 = 0;
/*     */     public static final int T_1 = 1;
/*     */     public static final int T_2 = 2;
/*     */     public static final int T_3 = 3;
/*     */     public static final int T_4 = 4;
/*     */     public static final int T_5 = 5;
/*     */     public static final int T_6 = 6;
/*     */     public static final int AC_TYPE_1 = 1;
/*     */     public static final int AC_TYPE_2 = 2;
/*     */     public static final int AC_TYPE_3 = 3;
/*     */     public static final int AC_TYPE_4 = 4;
/*     */     public static final int AC_TYPE_5 = 5;
/*     */     public static final String STATUS_INSTALLED = "installed";
/*     */     public static final String STATUS_DOWNLOAD_COMPLETED = "download_completed";
/*     */     public static final String DATA_IN_APP_COMMIT_DATA = "in_app_commit_data";
/*     */     public static final String DATA_DOWNLOAD_COMPLETED = "stream_app_completed";
/*     */     public static final String DATA_START_TIMES = "stream_app_start_times";
/*     */     public static final String DATA_CACHE_PAGES = "stream_app_cache_pages";
/*     */     public static final String DATA_START_TIMES_ACTIVATE = "stream_app_start_times_activate";
/* 267 */     private static ArrayList<TestUtil.DCErrorInfo> arrayList = new ArrayList();
/*     */     static final String PT = "point_time_";
/* 270 */     long[] points = null;
/* 271 */     int index = 0;
/*     */     String appid;
/*     */     String name;
/* 274 */     public static int mEc = -1;
/* 275 */     public static int mEt = -1;
/* 276 */     long lastPointTime = System.currentTimeMillis();
/* 277 */     long startTime = System.currentTimeMillis();
/*     */ 
/* 372 */     static HashMap<String, PointTime> sPoinTimes = new HashMap();
/*     */ 
/*     */     PointTime(String paramString, int paramInt)
/*     */     {
/* 279 */       this.name = paramString;
/* 280 */       this.points = new long[paramInt];
/*     */     }
/*     */ 
/*     */     public static synchronized void addErrorInfo(TestUtil.DCErrorInfo paramDCErrorInfo)
/*     */     {
/* 288 */       arrayList.add(paramDCErrorInfo);
/* 289 */       mEc = paramDCErrorInfo.ec;
/* 290 */       mEt = paramDCErrorInfo.et;
/*     */     }
/*     */ 
/*     */     public static String getErrorInfoString()
/*     */     {
/* 298 */       if ((arrayList != null) && (arrayList.size() > 0)) {
/* 299 */         StringBuffer localStringBuffer1 = new StringBuffer();
/* 300 */         StringBuffer localStringBuffer2 = new StringBuffer();
/* 301 */         for (int i = 0; i < arrayList.size(); i++) {
/* 302 */           TestUtil.DCErrorInfo localDCErrorInfo = (TestUtil.DCErrorInfo)arrayList.get(i);
/* 303 */           if (i == 0) {
/* 304 */             localStringBuffer1.append(localDCErrorInfo.ec);
/* 305 */             localStringBuffer2.append(localDCErrorInfo.et);
/*     */           } else {
/* 307 */             localStringBuffer1.append("%2c").append(localDCErrorInfo.ec);
/* 308 */             localStringBuffer2.append("%2c").append(localDCErrorInfo.et);
/*     */           }
/*     */         }
/* 311 */         arrayList.clear();
/* 312 */         return "&ec=" + localStringBuffer1.toString() + "&et=" + localStringBuffer2.toString();
/*     */       }
/* 314 */       return null;
/*     */     }
/*     */ 
/*     */     public void updateData(PointTime paramPointTime) {
/* 318 */       this.appid = paramPointTime.appid;
/* 319 */       this.index = paramPointTime.index;
/* 320 */       this.startTime = paramPointTime.startTime;
/*     */ 
/* 322 */       System.arraycopy(paramPointTime.points, 0, this.points, 0, paramPointTime.points.length);
/*     */     }
/*     */ 
/*     */     public PointTime point() {
/* 326 */       long l = this.lastPointTime;
/* 327 */       point(this.index, System.currentTimeMillis() - l);
/* 328 */       return this;
/*     */     }
/*     */     public PointTime point(int paramInt) {
/* 331 */       long l = this.lastPointTime;
/* 332 */       point(paramInt, System.currentTimeMillis() - l);
/* 333 */       return this;
/*     */     }
/*     */     public PointTime point(int paramInt, long paramLong) {
/* 336 */       if (paramInt < this.points.length) {
/* 337 */         this.lastPointTime = System.currentTimeMillis();
/* 338 */         this.points[paramInt] = paramLong;
/* 339 */         this.index += 1;
/*     */       }
/* 341 */       return this;
/*     */     }
/*     */ 
/*     */     public long getPoint(int paramInt) {
/* 345 */       return this.points[paramInt];
/*     */     }
/*     */     public int getIndex() {
/* 348 */       return this.index;
/*     */     }
/*     */ 
/*     */     public long getStartTime() {
/* 352 */       return this.startTime;
/*     */     }
/*     */ 
/*     */     public String getPointsString() {
/* 356 */       return getPointsString("|");
/*     */     }
/*     */ 
/*     */     public String getPointsString(String paramString) {
/* 360 */       StringBuffer localStringBuffer = new StringBuffer(paramString);
/* 361 */       for (int i = 0; i < this.points.length; i++) {
/* 362 */         if (i < this.index) {
/* 363 */           localStringBuffer.append(this.points[i]);
/*     */         }
/* 365 */         localStringBuffer.append(paramString);
/*     */       }
/* 367 */       return localStringBuffer.toString();
/*     */     }
/*     */     public static boolean hasPointTime(String paramString) {
/* 370 */       return sPoinTimes.containsKey(paramString);
/*     */     }
/*     */ 
/*     */     public static PointTime createPointTime(String paramString, int paramInt)
/*     */     {
/* 380 */       PointTime localPointTime = new PointTime(paramString, paramInt);
/* 381 */       sPoinTimes.remove(paramString);
/* 382 */       sPoinTimes.put(paramString, localPointTime);
/* 383 */       return localPointTime;
/*     */     }
/*     */ 
/*     */     public static PointTime updatePointTime(String paramString) {
/* 387 */       PointTime localPointTime1 = (PointTime)sPoinTimes.get(paramString);
/* 388 */       PointTime localPointTime2 = localPointTime1;
/* 389 */       if (localPointTime1 != null) {
/* 390 */         sPoinTimes.remove(paramString);
/* 391 */         localPointTime2 = new PointTime(paramString, localPointTime1.points.length);
/* 392 */         localPointTime2.updateData(localPointTime1);
/* 393 */         sPoinTimes.put(paramString, localPointTime2);
/*     */       }
/* 395 */       return localPointTime2;
/*     */     }
/*     */ 
/*     */     public static void savePointData(Context paramContext, String paramString1, int paramInt, String paramString2)
/*     */     {
/*     */       try
/*     */       {
/* 407 */         PointTime localPointTime1 = getPointTime(TestUtil.STREAM_APP_POINT);
/* 408 */         PointTime localPointTime2 = getPointTime(TestUtil.START_STREAM_APP_RETRY);
/* 409 */         SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("point_time_" + paramString1, 0);
/* 410 */         SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 411 */         int i = localSharedPreferences.getInt(TestUtil.START_STREAM_APP_RETRY, 0);
/* 412 */         int j = localPointTime2 != null ? localPointTime2.getIndex() : 0;
/* 413 */         if (localPointTime2 != null) {
/* 414 */           localPointTime2.index = 0;
/*     */         }
/* 416 */         int k = i;
/* 417 */         String str1 = "&t=%s&d=%s&r=%d&c=%d&net=%d&de=%s";
/* 418 */         String str2 = TestUtil.STREAM_APP_POINT;
/* 419 */         String str3 = paramString2;
/*     */ 
/* 421 */         if (i != 0)
/*     */         {
/* 423 */           localObject = new StringBuffer();
/* 424 */           ((StringBuffer)localObject).append("&t").append(k).append("=%s").append("&d").append(k).append("=%s").append("&r").append(k).append("=%s").append("&c").append(k).append("=%d").append("&net").append(k).append("=%d").append("&de").append(k).append("=%s");
/*     */ 
/* 430 */           if (!TextUtils.isEmpty(str3)) {
/* 431 */             str3 = paramString2.replace("&ec", "&ec" + k).replace("&et", "&et" + k);
/*     */           }
/* 433 */           str1 = ((StringBuffer)localObject).toString();
/*     */         }
/* 435 */         if (i > 0) {
/* 436 */           str2 = TestUtil.STREAM_APP_POINT + i;
/*     */         }
/* 438 */         Object localObject = String.format(str1, new Object[] { URLEncoder.encode(localPointTime1.getPointsString(), "utf-8"), Long.valueOf(localPointTime1.getStartTime()), Integer.valueOf(j), Integer.valueOf(paramInt), Integer.valueOf(NetworkTypeUtil.getNetworkType(paramContext)), Long.valueOf(System.currentTimeMillis()) });
/*     */ 
/* 446 */         if (!TextUtils.isEmpty(str3)) {
/* 447 */           localObject = (String)localObject + str3;
/*     */         }
/* 449 */         localEditor.putString(str2, (String)localObject);
/* 450 */         localEditor.putInt(TestUtil.START_STREAM_APP_RETRY, i + 1);
/* 451 */         localEditor.commit();
/*     */       } catch (Exception localException) {
/* 453 */         localException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*     */     public static String getAllCommitData(Context paramContext, String paramString)
/*     */     {
/* 462 */       SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("point_time_" + paramString, 0);
/* 463 */       int i = localSharedPreferences.getInt(TestUtil.START_STREAM_APP_RETRY, 0);
/* 464 */       if (i == 0) return null;
/* 465 */       StringBuffer localStringBuffer = new StringBuffer();
/* 466 */       for (int j = 0; j < i; j++) {
/* 467 */         String str = localSharedPreferences.getString(TestUtil.STREAM_APP_POINT + (j != 0 ? Integer.valueOf(j) : ""), "");
/* 468 */         if (!TextUtils.isEmpty(str)) {
/* 469 */           localStringBuffer.append(str);
/*     */         }
/*     */       }
/* 472 */       return localStringBuffer.toString();
/*     */     }
/*     */ 
/*     */     public static void delData(Context paramContext, String paramString1, String paramString2) {
/* 476 */       SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("point_time_" + paramString1, 0);
/* 477 */       localSharedPreferences.edit().remove(paramString2).commit();
/*     */     }
/*     */ 
/*     */     public static String getData(Context paramContext, String paramString1, String paramString2) {
/* 481 */       SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("point_time_" + paramString1, 0);
/* 482 */       return localSharedPreferences.getString(paramString2, "");
/*     */     }
/*     */     public static void saveData(Context paramContext, String paramString1, String paramString2, String paramString3) {
/* 485 */       SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("point_time_" + paramString1, 0);
/* 486 */       SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 487 */       localEditor.putString(paramString2, paramString3);
/* 488 */       localEditor.commit();
/*     */     }
/*     */ 
/*     */     public static void delPointData(Context paramContext, String paramString)
/*     */     {
/* 496 */       SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("point_time_" + paramString, 0);
/* 497 */       SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 498 */       int i = localSharedPreferences.getInt(TestUtil.START_STREAM_APP_RETRY, 0);
/* 499 */       for (int j = 0; j < i; j++) {
/* 500 */         localEditor.remove(TestUtil.STREAM_APP_POINT + j);
/*     */       }
/* 502 */       localEditor.remove(TestUtil.START_STREAM_APP_RETRY);
/* 503 */       localEditor.commit();
/*     */     }
/*     */     public static PointTime destroyPointTime(String paramString) {
/* 506 */       return (PointTime)sPoinTimes.remove(paramString);
/*     */     }
/*     */ 
/*     */     public static PointTime getPointTime(String paramString)
/*     */     {
/* 514 */       return (PointTime)sPoinTimes.get(paramString);
/*     */     }
/*     */ 
/*     */     public static void saveStreamAppStatus(Context paramContext, String paramString1, String paramString2)
/*     */     {
/* 524 */       SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("point_time_" + paramString1, 0);
/* 525 */       SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 526 */       localEditor.putBoolean(paramString2, true);
/* 527 */       localEditor.commit();
/*     */     }
/*     */ 
/*     */     public static void deleteStreamAppStatus(Context paramContext, String paramString1, String paramString2) {
/* 531 */       SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("point_time_" + paramString1, 0);
/* 532 */       SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 533 */       localEditor.remove(paramString2);
/* 534 */       localEditor.commit();
/*     */     }
/*     */ 
/*     */     public static boolean hasStreamAppStatus(Context paramContext, String paramString1, String paramString2)
/*     */     {
/* 544 */       SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("point_time_" + paramString1, 0);
/* 545 */       return localSharedPreferences.getBoolean(paramString2, false);
/*     */     }
/*     */ 
/*     */     public static String getBaseVer(Context paramContext) {
/* 549 */       return BaseInfo.sBaseVersion;
/*     */     }
/*     */ 
/*     */     public static void commit(IActivityHandler paramIActivityHandler, String paramString1, int paramInt1, int paramInt2, String paramString2)
/*     */     {
/* 566 */       if ((paramIActivityHandler != null) && ((paramInt1 == 3) || (checkCommitEnv(paramIActivityHandler.getContext(), paramString1, TestUtil.STREAM_APP_POINT))))
/* 567 */         paramIActivityHandler.commitPointData0(paramString1, paramInt1, paramInt2, paramString2);
/*     */     }
/*     */ 
/*     */     public static boolean checkCommitEnv(Context paramContext, String paramString1, String paramString2)
/*     */     {
/* 572 */       if (AppStatus.getAppStatus(paramString1) == 0) return false;
/* 573 */       if ((BaseInfo.useStreamAppStatistic(paramContext)) && (hasPointTime(paramString2))) {
/* 574 */         return true;
/*     */       }
/* 576 */       return false;
/*     */     }
/*     */ 
/*     */     public static void commit(IActivityHandler paramIActivityHandler, String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5)
/*     */     {
/* 595 */       if ((paramIActivityHandler != null) && (checkCommitEnv(paramIActivityHandler.getContext(), paramString1, paramString2)))
/* 596 */         paramIActivityHandler.commitPointData(paramString1, paramString2, paramString3, paramInt, paramString4, paramString5);
/*     */     }
/*     */ 
/*     */     public static void commitActivate(IActivityHandler paramIActivityHandler, String paramString1, String paramString2)
/*     */     {
/* 607 */       if (paramIActivityHandler != null)
/* 608 */         paramIActivityHandler.commitActivateData(paramString1, paramString2);
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.TestUtil
 * JD-Core Version:    0.6.2
 */