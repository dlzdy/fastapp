/*      */ package io.dcloud.common.a;
/*      */ 
/*      */ import android.app.Activity;
/*      */ import android.content.BroadcastReceiver;
/*      */ import android.content.Context;
/*      */ import android.content.Intent;
/*      */ import android.content.IntentFilter;
/*      */ import android.content.SharedPreferences;
/*      */ import android.content.SharedPreferences.Editor;
/*      */ import android.content.pm.PackageInfo;
/*      */ import android.content.pm.PackageManager;
/*      */ import android.content.res.Resources;
/*      */ import android.graphics.Color;
/*      */ import android.os.Build;
/*      */ import android.os.Build.VERSION;
/*      */ import android.os.Bundle;
/*      */ import android.text.TextUtils;
/*      */ import android.util.DisplayMetrics;
/*      */ import android.view.Window;
/*      */ import io.dcloud.a.b;
/*      */ import io.dcloud.common.DHInterface.IActivityHandler;
/*      */ import io.dcloud.common.DHInterface.IApp;
/*      */ import io.dcloud.common.DHInterface.IApp.ConfigProperty.ThridInfo;
/*      */ import io.dcloud.common.DHInterface.IApp.IAppStatusListener;
/*      */ import io.dcloud.common.DHInterface.IBoot;
/*      */ import io.dcloud.common.DHInterface.ICallBack;
/*      */ import io.dcloud.common.DHInterface.IFrameView;
/*      */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*      */ import io.dcloud.common.DHInterface.IOnCreateSplashView;
/*      */ import io.dcloud.common.DHInterface.ISmartUpdate;
/*      */ import io.dcloud.common.DHInterface.ISmartUpdate.SmartUpdateCallbackParams;
/*      */ import io.dcloud.common.DHInterface.ISysEventListener;
/*      */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*      */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*      */ import io.dcloud.common.DHInterface.IWebviewStateListener;
/*      */ import io.dcloud.common.adapter.io.DHFile;
/*      */ import io.dcloud.common.adapter.io.UnicodeInputStream;
/*      */ import io.dcloud.common.adapter.util.DeviceInfo;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.MessageHandler;
/*      */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*      */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*      */ import io.dcloud.common.adapter.util.PlatformUtil;
/*      */ import io.dcloud.common.adapter.util.SP;
/*      */ import io.dcloud.common.constant.DOMException;
/*      */ import io.dcloud.common.constant.DataInterface;
/*      */ import io.dcloud.common.constant.IntentConst;
/*      */ import io.dcloud.common.constant.StringConst;
/*      */ import io.dcloud.common.util.AppStatus;
/*      */ import io.dcloud.common.util.AppStreamUtil;
/*      */ import io.dcloud.common.util.BaseInfo;
/*      */ import io.dcloud.common.util.BaseInfo.BaseAppInfo;
/*      */ import io.dcloud.common.util.BaseInfo.CmtInfo;
/*      */ import io.dcloud.common.util.CommitDataUtil;
/*      */ import io.dcloud.common.util.IOUtil;
/*      */ import io.dcloud.common.util.JSONUtil;
/*      */ import io.dcloud.common.util.LauncherUtil;
/*      */ import io.dcloud.common.util.NetTool;
/*      */ import io.dcloud.common.util.NetworkTypeUtil;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import io.dcloud.common.util.ShortCutUtil;
/*      */ import io.dcloud.common.util.TestUtil;
/*      */ import io.dcloud.common.util.ThreadPool;
/*      */ import io.dcloud.common.util.ZipUtils;
/*      */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.nio.charset.Charset;
/*      */ import java.security.PublicKey;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.Map;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ class d extends e
/*      */   implements IApp, ISysEventListener
/*      */ {
/*   92 */   public static String a = "webapp";
/*   93 */   f b = null;
/*      */ 
/*   97 */   BaseInfo.BaseAppInfo c = null;
/*      */ 
/*  101 */   byte d = 1;
/*      */ 
/*  105 */   boolean e = false;
/*      */ 
/*  109 */   boolean f = false;
/*  110 */   a g = null;
/*      */ 
/*  114 */   String h = null;
/*  115 */   String i = null;
/*      */ 
/*  119 */   String j = "";
/*      */ 
/*  123 */   String k = null;
/*      */ 
/*  127 */   String l = null;
/*      */ 
/*  131 */   String m = null;
/*      */ 
/*  135 */   String n = null;
/*      */ 
/*  139 */   String o = null;
/*      */ 
/*  143 */   String p = null;
/*      */ 
/*  147 */   String q = null;
/*      */ 
/*  151 */   String r = null;
/*      */ 
/*  155 */   String s = null;
/*      */ 
/*  159 */   boolean t = true;
/*  160 */   boolean u = true;
/*      */ 
/*  164 */   boolean v = true;
/*      */ 
/*  168 */   boolean w = false;
/*      */ 
/*  172 */   boolean x = false;
/*      */ 
/*  176 */   boolean y = true;
/*  177 */   private String ai = null;
/*      */ 
/*  181 */   boolean z = false;
/*  182 */   private byte aj = 1;
/*      */ 
/*  184 */   private boolean ak = false;
/*      */ 
/*  188 */   private boolean al = false;
/*      */ 
/*  192 */   private boolean am = true;
/*      */ 
/*  196 */   private boolean an = true;
/*      */ 
/*  200 */   private int ao = 10000;
/*      */ 
/*  204 */   private int ap = 0;
/*  205 */   private int aq = 0;
/*      */ 
/*  209 */   private String ar = null;
/*      */ 
/*  211 */   private String as = null;
/*      */   private String at;
/*      */   private String au;
/*      */   private String av;
/*      */   private String aw;
/*  219 */   boolean A = false;
/*      */ 
/*  223 */   private String ax = null;
/*      */ 
/*  227 */   String B = null;
/*      */ 
/*  231 */   String C = null;
/*      */ 
/*  235 */   String D = "accept";
/*      */ 
/*  239 */   String E = null;
/*      */ 
/*  243 */   String F = null;
/*      */ 
/*  247 */   private String ay = null;
/*      */ 
/*  251 */   String G = null;
/*      */ 
/*  255 */   ArrayList<String> H = null;
/*      */ 
/*  259 */   HashMap<ISysEventListener.SysEventType, ArrayList<ISysEventListener>> I = null;
/*      */ 
/*  263 */   JSONObject J = null;
/*      */ 
/*  267 */   JSONObject K = null;
/*      */ 
/*  272 */   JSONObject L = null;
/*      */ 
/*  274 */   JSONObject M = null;
/*      */ 
/*  276 */   JSONObject N = null;
/*      */ 
/*  280 */   Intent O = null;
/*  281 */   ISmartUpdate P = null;
/*  282 */   IApp.IAppStatusListener Q = null;
/*      */ 
/*  284 */   String R = null;
/*      */   private String az;
/*  288 */   private String aA = "none";
/*      */ 
/*  292 */   boolean S = false;
/*  293 */   private boolean aB = false;
/*  294 */   private String aC = "default";
/*      */ 
/*  296 */   private int aD = -111111;
/*      */ 
/*  301 */   long T = 0L;
/*      */ 
/*  303 */   boolean U = true;
/*      */ 
/*  311 */   IWebviewStateListener V = null;
/*      */ 
/*  906 */   BroadcastReceiver W = null;
/*      */ 
/* 1054 */   private boolean aE = false;
/*      */ 
/* 1073 */   private String aF = "force";
/*      */ 
/* 1992 */   private String aG = null;
/* 1993 */   private String aH = null;
/*      */ 
/* 2373 */   HashMap<String, Integer> X = null;
/* 2374 */   String Y = null;
/*      */ 
/*      */   d(a parama, String paramString, byte paramByte)
/*      */   {
/*  305 */     this.g = parama;
/*  306 */     a(paramByte);
/*  307 */     this.b = new f();
/*  308 */     this.H = new ArrayList(2);
/*      */   }
/*      */ 
/*      */   public void setIAppStatusListener(IApp.IAppStatusListener paramIAppStatusListener)
/*      */   {
/*  315 */     this.Q = paramIAppStatusListener;
/*      */   }
/*      */ 
/*      */   public IApp.IAppStatusListener getIAppStatusListener()
/*      */   {
/*  320 */     return this.Q;
/*      */   }
/*      */ 
/*      */   public void setLaunchPageStateListener(IWebviewStateListener paramIWebviewStateListener)
/*      */   {
/*  325 */     this.V = paramIWebviewStateListener;
/*      */   }
/*      */ 
/*      */   public IWebviewStateListener obtainLaunchPageStateListener()
/*      */   {
/*  330 */     return this.V;
/*      */   }
/*      */ 
/*      */   public String getPathByType(byte paramByte)
/*      */   {
/*  335 */     if (paramByte == 0)
/*  336 */       return obtainAppDataPath();
/*  337 */     if (paramByte == 1)
/*  338 */       return obtainAppDocPath();
/*  339 */     if (paramByte == 2)
/*  340 */       return BaseInfo.sDocumentFullPath;
/*  341 */     if (paramByte == 3)
/*  342 */       return BaseInfo.sDownloadFullPath;
/*  343 */     if (paramByte == -1) {
/*  344 */       return BaseInfo.sBaseResAppsPath + this.h + "/" + BaseInfo.APP_WWW_FS_DIR;
/*      */     }
/*  346 */     return null;
/*      */   }
/*      */ 
/*      */   boolean a(InputStream paramInputStream, String paramString, JSONObject paramJSONObject)
/*      */   {
/*  358 */     boolean bool1 = true;
/*  359 */     f localf = this.b;
/*      */ 
/*  361 */     int i1 = (localf != null) && (localf.d) ? 1 : 0;
/*  362 */     Object localObject1 = "";
/*      */     try {
/*  364 */       if (paramInputStream != null) {
/*  365 */         paramInputStream = new UnicodeInputStream(paramInputStream, Charset.defaultCharset().name());
/*  366 */         byte[] arrayOfByte = IOUtil.getBytes(paramInputStream);
/*  367 */         localObject1 = new String(arrayOfByte);
/*      */ 
/*  369 */         String str1 = a(arrayOfByte);
/*  370 */         if (str1 != null)
/*      */         {
/*  372 */           localObject1 = str1;
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (IOException localIOException) {
/*  377 */       localIOException.printStackTrace();
/*  378 */       Logger.e("parseConfig error=" + localIOException.getMessage());
/*      */     }
/*      */ 
/*  381 */     JSONObject localJSONObject1 = null;
/*      */     try {
/*  383 */       localJSONObject1 = new JSONObject((String)localObject1);
/*      */     } catch (Exception localException) {
/*  385 */       localException.printStackTrace();
/*  386 */       localf.a = true;
/*  387 */       if (localf.c)
/*  388 */         localf.b = DOMException.toJSON(-1226, "WGTU安装包中www目录下manifest.json文件格式错误");
/*      */       else {
/*  390 */         localf.b = DOMException.toJSON(-1203, "WGT安装包中manifest.json文件格式错误");
/*      */       }
/*  392 */       return false;
/*      */     }
/*      */ 
/*  395 */     JSONObject localJSONObject2 = null; Object localObject2 = null; JSONObject localJSONObject3 = null; JSONObject localJSONObject4 = null;
/*  396 */     JSONObject localJSONObject5 = JSONUtil.getJSONObject(localJSONObject1, "version");
/*  397 */     String str2 = JSONUtil.getString(localJSONObject5, "name");
/*  398 */     ArrayList localArrayList = null;
/*  399 */     String str3 = null; String str5 = null; String str6 = null; String str7 = null; String str8 = null; String str9 = null; String str10 = null;
/*  400 */     String str11 = null; String str12 = null; String str13 = null; String str14 = null; String str15 = null; String str16 = null; String str17 = null; String str18 = null; String str19 = null; String str20 = null; String str21 = null;
/*      */ 
/*  402 */     boolean bool2 = false; boolean bool3 = false; boolean bool4 = false; boolean bool5 = false; boolean bool6 = false; boolean bool7 = false; boolean bool8 = false; boolean bool9 = false; boolean bool10 = true; boolean bool11 = true; boolean bool12 = true;
/*  403 */     JSONObject localJSONObject6 = null;
/*  404 */     int i2 = 0; int i3 = 0;
/*      */ 
/*  406 */     boolean bool13 = false;
/*  407 */     if (i1 != 0) {
/*  408 */       if (paramJSONObject != null) {
/*  409 */         localObject3 = JSONUtil.getString(paramJSONObject, "force");
/*  410 */         bool13 = Boolean.parseBoolean((String)localObject3);
/*      */       }
/*  412 */       if (!bool13)
/*      */       {
/*  415 */         if ((!TextUtils.isEmpty(this.j)) && (!BaseInfo.BaseAppInfo.compareVersion(str2, this.j))) {
/*  416 */           if (localf.c)
/*  417 */             localf.b = DOMException.toJSON(-1228, "WGTU安装包中www目录下manifest.json文件的version版本不匹配");
/*      */           else {
/*  419 */             localf.b = DOMException.toJSON(-1205, "WGT安装包中manifest.json文件的version版本不匹配");
/*      */           }
/*  421 */           localf.a = true;
/*  422 */           bool1 = false;
/*  423 */           return bool1;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  430 */     String str4 = str3 = JSONUtil.getString(localJSONObject1, "id");
/*  431 */     if (BaseInfo.ISDEBUG) {
/*  432 */       str3 = paramString;
/*      */     } else {
/*  434 */       bool1 = (PdrUtil.isEquals(paramString, str3)) && (PdrUtil.isEquals(this.h, str3));
/*  435 */       if (!bool1) {
/*  436 */         localf.a = true;
/*  437 */         if (localf.c)
/*  438 */           localf.b = DOMException.toJSON(-1226, "WGTU安装包中www目录下manifest.json文件的appid不匹配");
/*      */         else {
/*  440 */           localf.b = DOMException.toJSON(-1204, "WGT安装包中manifest.json文件的appid不匹配");
/*      */         }
/*  442 */         bool1 = false;
/*  443 */         return bool1;
/*      */       }
/*      */     }
/*  446 */     str5 = JSONUtil.getString(localJSONObject1, "name");
/*      */ 
/*  458 */     str6 = JSONUtil.getString(localJSONObject1, "description");
/*      */ 
/*  461 */     Object localObject3 = JSONUtil.getJSONObject(localJSONObject1, "developer");
/*  462 */     str7 = JSONUtil.getString((JSONObject)localObject3, "name");
/*  463 */     str8 = JSONUtil.getString((JSONObject)localObject3, "email");
/*  464 */     str9 = JSONUtil.getString((JSONObject)localObject3, "url");
/*      */ 
/*  466 */     JSONObject localJSONObject7 = JSONUtil.getJSONObject(localJSONObject1, "license");
/*  467 */     str11 = JSONUtil.getString(localJSONObject7, "url");
/*  468 */     str10 = JSONUtil.getString(localJSONObject7, "description");
/*      */ 
/*  470 */     str12 = JSONUtil.getString(localJSONObject1, "launch_path");
/*  471 */     str13 = JSONUtil.getString(localJSONObject1, "launch_path_w2a");
/*  472 */     str14 = JSONUtil.getString(localJSONObject1, "baseUrl");
/*  473 */     if (!PdrUtil.isEmpty(this.ay)) {
/*  474 */       bool9 = true;
/*      */     }
/*      */ 
/*  478 */     JSONObject localJSONObject8 = JSONUtil.getJSONObject(localJSONObject1, "plus");
/*      */ 
/*  480 */     JSONObject localJSONObject9 = JSONUtil.getJSONObject(localJSONObject8, "launchwebview");
/*  481 */     str20 = "normal";
/*  482 */     String str22 = "none";
/*  483 */     if (localJSONObject9 != null) {
/*  484 */       localJSONObject2 = JSONUtil.getJSONObject(localJSONObject9, "overrideurl");
/*  485 */       localObject4 = JSONUtil.getJSONArray(localJSONObject9, "overrideresource");
/*  486 */       if (localObject4 != null) {
/*  487 */         localObject5 = new JSONObject();
/*      */         try {
/*  489 */           ((JSONObject)localObject5).put("0", localObject4);
/*      */         } catch (JSONException localJSONException) {
/*  491 */           localJSONException.printStackTrace();
/*      */         }
/*  493 */         localObject2 = localObject5;
/*      */       }
/*  495 */       bool12 = localJSONObject9.optBoolean("injection", this.U);
/*  496 */       str20 = localJSONObject9.optString("plusrequire", str20);
/*  497 */       localJSONObject4 = JSONUtil.getJSONObject(localJSONObject9, "navigationbar");
/*  498 */       str22 = localJSONObject9.optString("geolocation", str22);
/*      */     }
/*      */ 
/*  501 */     localJSONObject3 = JSONUtil.getJSONObject(localJSONObject8, "secondwebview");
/*  502 */     str21 = "normal";
/*  503 */     String str23 = "none";
/*  504 */     if (localJSONObject3 != null) {
/*  505 */       str21 = localJSONObject3.optString("plusrequire", str21);
/*  506 */       str23 = localJSONObject3.optString("geolocation", str23);
/*      */     }
/*      */ 
/*  510 */     if (BaseInfo.isBase(this.g.getContext())) {
/*  511 */       localObject4 = JSONUtil.getString(localJSONObject8, "ramcachemode");
/*      */ 
/*  513 */       this.az = ((String)localObject4);
/*      */     }
/*      */ 
/*  516 */     if (localJSONObject8 != null)
/*      */     {
/*  518 */       this.S = localJSONObject8.optBoolean("hardwareAccelerated", (MobilePhoneModel.checkPhoneBanAcceleration(Build.BRAND)) || ((BaseInfo.isWap2AppAppid(this.h)) && (isStreamApp())));
/*  519 */       this.aA = localJSONObject8.optString("popGesture", this.aA);
/*      */     }
/*      */ 
/*  522 */     Object localObject4 = JSONUtil.getJSONObject(localJSONObject8, "cache");
/*  523 */     if (localObject4 != null) {
/*  524 */       localObject5 = JSONUtil.getString((JSONObject)localObject4, "mode");
/*  525 */       this.aC = (TextUtils.isEmpty((CharSequence)localObject5) ? this.aC : (String)localObject5);
/*      */     }
/*      */ 
/*  528 */     a(localJSONObject8);
/*      */ 
/*  530 */     Object localObject5 = JSONUtil.getJSONObject(localJSONObject8, "cers");
/*  531 */     bool10 = PdrUtil.parseBoolean(JSONUtil.getString((JSONObject)localObject5, "crash"), this.t, false);
/*  532 */     bool11 = PdrUtil.parseBoolean(JSONUtil.getString((JSONObject)localObject5, "jserror"), this.u, false);
/*      */ 
/*  534 */     localObject5 = JSONUtil.getString(localJSONObject8, "runmode");
/*  535 */     bool5 = PdrUtil.isEquals((String)localObject5, "liberate");
/*      */ 
/*  537 */     JSONObject localJSONObject10 = JSONUtil.getJSONObject(localJSONObject8, "useragent");
/*  538 */     str15 = JSONUtil.getString(localJSONObject10, "value");
/*  539 */     bool7 = PdrUtil.parseBoolean(JSONUtil.getString(localJSONObject10, "concatenate"), this.x, false);
/*      */ 
/*  542 */     localJSONObject10 = JSONUtil.getJSONObject(localJSONObject8, "splashscreen");
/*      */     Object localObject6;
/*  543 */     if (localJSONObject10 != null) {
/*  544 */       bool2 = true;
/*  545 */       localObject6 = PlatformUtil.getOrCreateBundle("pdr");
/*  546 */       if (((SharedPreferences)localObject6).contains(this.h + "__update_splash_autoclose")) {
/*  547 */         bool4 = bool3 = ((SharedPreferences)localObject6).getBoolean(this.h + "__update_splash_autoclose", bool3);
/*      */       }
/*  549 */       else if (!JSONUtil.isNull(localJSONObject10, "autoclose")) {
/*  550 */         bool4 = bool3 = Boolean.parseBoolean(String.valueOf(JSONUtil.getString(localJSONObject10, "autoclose")));
/*      */       }
/*      */ 
/*  553 */       if (bool3) {
/*  554 */         if (((SharedPreferences)localObject6).contains(this.h + "__update_splash_delay")) {
/*  555 */           i3 = i2 = ((SharedPreferences)localObject6).getInt(this.h + "__update_splash_delay", i2);
/*      */         }
/*  557 */         else if (!JSONUtil.isNull(localJSONObject10, "delay")) {
/*  558 */           i3 = i2 = PdrUtil.parseInt(JSONUtil.getString(localJSONObject10, "delay"), this.ap);
/*      */         }
/*      */       }
/*      */ 
/*  562 */       if (((SharedPreferences)localObject6).contains(this.h + "__update_splash_autoclose_w2a")) {
/*  563 */         bool4 = ((SharedPreferences)localObject6).getBoolean(this.h + "__update_splash_autoclose_w2a", bool4);
/*      */       }
/*  565 */       else if (!JSONUtil.isNull(localJSONObject10, "autoclose_w2a")) {
/*  566 */         bool4 = Boolean.parseBoolean(String.valueOf(JSONUtil.getString(localJSONObject10, "autoclose_w2a")));
/*      */       }
/*      */ 
/*  569 */       if (bool4) {
/*  570 */         if (((SharedPreferences)localObject6).contains(this.h + "__update_splash_delay_w2a")) {
/*  571 */           i3 = ((SharedPreferences)localObject6).getInt(this.h + "__update_splash_delay_w2a", i3);
/*      */         }
/*  573 */         else if (!JSONUtil.isNull(localJSONObject10, "delay_w2a")) {
/*  574 */           i3 = PdrUtil.parseInt(JSONUtil.getString(localJSONObject10, "delay_w2a"), this.aq);
/*      */         }
/*      */ 
/*  577 */         i3 = (int)(i3 + o());
/*      */       }
/*      */ 
/*  580 */       bool6 = PdrUtil.parseBoolean(JSONUtil.getString(localJSONObject10, "waiting"), this.al, false);
/*      */ 
/*  582 */       str18 = JSONUtil.getString(localJSONObject10, "event");
/*  583 */       str19 = localJSONObject10.optString("target", "default");
/*      */     }
/*      */ 
/*  587 */     localJSONObject10 = JSONUtil.getJSONObject(localJSONObject8, "error");
/*  588 */     str16 = "file:///android_asset/data/dcloud_error.html";
/*  589 */     if ((localJSONObject10 != null) && 
/*  590 */       (!JSONUtil.isNull(localJSONObject10, "url"))) {
/*  591 */       str16 = JSONUtil.getString(localJSONObject10, "url");
/*      */     }
/*      */ 
/*  596 */     localJSONObject10 = JSONUtil.getJSONObject(localJSONObject8, "ssl");
/*  597 */     if ((localJSONObject10 != null) && 
/*  598 */       (!JSONUtil.isNull(localJSONObject10, "untrustedca"))) {
/*  599 */       str17 = JSONUtil.getString(localJSONObject10, "untrustedca");
/*      */     }
/*      */ 
/*  605 */     localJSONObject10 = JSONUtil.getJSONObject(localJSONObject8, "stream");
/*      */ 
/*  607 */     this.w = ((AppStreamUtil.isAppStreamGenuine(str3)) || (BaseInfo.isWap2AppAppid(this.h)));
/*  608 */     if (localJSONObject10 != null) {
/*  609 */       this.w = localJSONObject10.optBoolean("competent", this.w);
/*  610 */       this.aF = localJSONObject10.optString("shortcut");
/*      */     }
/*  612 */     if (!this.w) {
/*  613 */       BaseInfo.sAppIsTests.put(str3, "__am=t");
/*  614 */       BaseInfo.createAppTestFile(str3);
/*      */     } else {
/*  616 */       BaseInfo.sAppIsTests.remove(str3);
/*  617 */       BaseInfo.removeTestFile(str3);
/*      */     }
/*  619 */     Logger.i(a, this.h + " app competent=" + this.w);
/*      */ 
/*  624 */     bool8 = PdrUtil.parseBoolean(JSONUtil.getString(localJSONObject1, "fullscreen"), this.ag, false);
/*  625 */     Logger.i(a, this.h + " app fullScreen=" + bool8);
/*      */ 
/*  628 */     localArrayList = new ArrayList();
/*      */ 
/*  630 */     boolean bool14 = isStreamApp();
/*  631 */     localJSONObject9 = JSONUtil.getJSONObject(localJSONObject1, "permissions");
/*  632 */     localObject4 = localJSONObject9.names();
/*  633 */     if (localObject4 != null) {
/*  634 */       localObject5 = new StringBuffer();
/*  635 */       for (int i4 = 0; i4 < ((JSONArray)localObject4).length(); i4++) {
/*  636 */         localObject6 = JSONUtil.getString((JSONArray)localObject4, i4).toLowerCase();
/*  637 */         localArrayList.add(localObject6);
/*  638 */         if (((String)localObject6).equals("push")) {
/*  639 */           JSONObject localJSONObject11 = JSONUtil.getJSONObject(localJSONObject9, (String)localObject6);
/*  640 */           String str24 = JSONUtil.getString(localJSONObject11, "cover");
/*  641 */           PlatformUtil.APS_COVER = PdrUtil.parseBoolean(str24, PlatformUtil.APS_COVER, false);
/*  642 */         } else if (((String)localObject6).equals("webview")) {
/*  643 */           localArrayList.add("ui");
/*  644 */         } else if (((String)localObject6).equals("ui")) {
/*  645 */           localArrayList.add("webview");
/*  646 */           localArrayList.add("nativeui");
/*  647 */           localArrayList.add("navigator");
/*      */         }
/*  649 */         if ((i1 != 0) && (!bool13) && (!bool14) && 
/*  650 */           (!io.dcloud.common.b.a.a.b(str3, (String)localObject6)) && (!((Boolean)this.g.processEvent(IMgr.MgrType.FeatureMgr, 9, localObject6)).booleanValue())) {
/*  651 */           bool1 = false;
/*  652 */           if (((StringBuffer)localObject5).length() > 0) {
/*  653 */             ((StringBuffer)localObject5).append(",");
/*      */           }
/*  655 */           ((StringBuffer)localObject5).append((String)localObject6);
/*      */         }
/*      */       }
/*      */ 
/*  659 */       if ((!bool1) && (i1 != 0)) {
/*  660 */         localf.b = DOMException.toJSON(-1229, "HTML5+ Runtime缺少升级包manifest.json中配置的模块:" + localObject5);
/*  661 */         localf.a = true;
/*  662 */         return false;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  672 */     localJSONObject6 = JSONUtil.getJSONObject(localJSONObject1, "qihoo");
/*      */ 
/*  675 */     if (bool1) {
/*  676 */       localf.b = k();
/*      */     }
/*  678 */     localf.a = (!bool1);
/*  679 */     if (bool1) {
/*  680 */       if (this.c != null) this.c.mAppVer = this.j;
/*  681 */       this.j = str2;
/*  682 */       this.H = localArrayList;
/*  683 */       this.h = str3;
/*  684 */       this.i = str4;
/*  685 */       this.G = str5;
/*  686 */       this.t = bool10;
/*  687 */       this.u = bool11;
/*  688 */       this.m = str6;
/*  689 */       this.n = str7;
/*  690 */       this.o = str8;
/*  691 */       this.q = str11;
/*  692 */       this.B = str12;
/*  693 */       this.C = str13;
/*  694 */       this.ay = str14;
/*  695 */       this.s = str15;
/*  696 */       this.E = str16;
/*  697 */       this.ak = bool2;
/*  698 */       this.am = bool3;
/*  699 */       this.an = bool4;
/*  700 */       this.A = bool5;
/*  701 */       this.al = bool6;
/*  702 */       this.x = bool7;
/*  703 */       this.ag = bool8;
/*  704 */       this.z = bool9;
/*  705 */       this.D = str17;
/*  706 */       this.J = localJSONObject6;
/*  707 */       this.ap = i2;
/*  708 */       this.aq = i3;
/*  709 */       this.ar = str18;
/*  710 */       this.as = str19;
/*  711 */       this.K = localJSONObject2;
/*  712 */       this.L = localObject2;
/*  713 */       this.U = bool12;
/*  714 */       this.M = localJSONObject3;
/*  715 */       this.N = localJSONObject4;
/*  716 */       this.at = str20;
/*  717 */       this.au = str21;
/*  718 */       this.av = str22;
/*  719 */       this.aw = str23;
/*  720 */       v();
/*      */     }
/*  722 */     l();
/*  723 */     return bool1;
/*      */   }
/*      */ 
/*      */   private String b(InputStream paramInputStream)
/*      */   {
/*  728 */     String str1 = null;
/*      */     try {
/*  730 */       paramInputStream = new UnicodeInputStream(paramInputStream, Charset.defaultCharset().name());
/*  731 */       byte[] arrayOfByte = IOUtil.getBytes(paramInputStream);
/*  732 */       String str2 = new String(arrayOfByte);
/*  733 */       JSONObject localJSONObject = new JSONObject(str2);
/*  734 */       str1 = localJSONObject.optString("version");
/*      */     } catch (Exception localException) {
/*  736 */       localException.printStackTrace();
/*      */     }
/*  738 */     return str1;
/*      */   }
/*      */ 
/*      */   private void p()
/*      */   {
/*      */     try {
/*  744 */       int i1 = DHFile.rename(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/", BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
/*  745 */       DHFile.copyDir("data/wap2app", BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/");
/*  746 */       if (i1 == 1)
/*  747 */         ThreadPool.self().addThreadTask(new Runnable()
/*      */         {
/*      */           public void run() {
/*      */             try {
/*  751 */               DHFile.deleteFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
/*      */             } catch (IOException localIOException) {
/*  753 */               localIOException.printStackTrace();
/*      */             }
/*      */           }
/*      */         });
/*      */     }
/*      */     catch (IOException localIOException) {
/*  759 */       localIOException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   private String q()
/*      */   {
/*  765 */     String str1 = null;
/*  766 */     int i1 = 0;
/*      */     try {
/*  768 */       if ((BaseInfo.sCoverApkRuning) && 
/*  769 */         (new File(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/" + "__template.json").exists())) {
/*  770 */         String str2 = null;
/*      */ 
/*  772 */         Object localObject1 = PlatformUtil.getInputStream(BaseInfo.sBaseConfigTemplatePath);
/*  773 */         str2 = b((InputStream)localObject1);
/*  774 */         IOUtil.close((InputStream)localObject1);
/*      */ 
/*  776 */         localObject1 = null;
/*      */ 
/*  778 */         InputStream localInputStream2 = DHFile.getInputStream(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/" + "__template.json");
/*  779 */         localObject1 = b(localInputStream2);
/*  780 */         IOUtil.close(localInputStream2);
/*      */ 
/*  782 */         if (BaseInfo.BaseAppInfo.compareVersion(str2, (String)localObject1)) {
/*  783 */           p();
/*  784 */           i1 = 1;
/*      */         }
/*      */       }
/*      */ 
/*  788 */       if (DHFile.isExist(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template.zip")) {
/*  789 */         int i2 = DHFile.rename(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/", BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
/*      */         try
/*      */         {
/*  794 */           ZipUtils.upZipFile(new File(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template.zip"), BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/");
/*  795 */           i1 = 1;
/*      */         } catch (IOException localIOException) {
/*  797 */           localIOException.printStackTrace();
/*      */         } finally {
/*  799 */           ThreadPool.self().addThreadTask(new Runnable()
/*      */           {
/*      */             public void run() {
/*      */               try {
/*  803 */                 DHFile.deleteFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app_temp/");
/*  804 */                 DHFile.deleteFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template.zip");
/*      */               } catch (IOException localIOException) {
/*  806 */                 localIOException.printStackTrace();
/*      */               }
/*      */             } } );
/*      */         }
/*      */       }
/*  811 */       else if (!new File(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/" + "__template.json").exists()) {
/*  812 */         p();
/*  813 */         i1 = 1;
/*      */       }
/*      */ 
/*  816 */       if (((i1 != 0) || (TextUtils.isEmpty(BaseInfo.sWap2AppTemplateVersion))) && (DHFile.isExist(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/" + "__template.json"))) {
/*  817 */         InputStream localInputStream1 = DHFile.getInputStream(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/" + "__template.json");
/*  818 */         str1 = b(localInputStream1);
/*  819 */         IOUtil.close(localInputStream1);
/*      */       } else {
/*  821 */         str1 = BaseInfo.sWap2AppTemplateVersion;
/*      */       }
/*      */     }
/*      */     catch (Exception localException) {
/*  825 */       localException.printStackTrace();
/*      */     }
/*  827 */     return str1;
/*      */   }
/*      */ 
/*      */   private String r()
/*      */   {
/*  832 */     String str = null;
/*      */     try
/*      */     {
/*  835 */       InputStream localInputStream = DHFile.getInputStream(BaseInfo.sBaseFsAppsPath + "/" + this.h + "/www/__template.json");
/*  836 */       str = b(localInputStream);
/*  837 */       IOUtil.close(localInputStream);
/*      */     }
/*      */     catch (IOException localIOException) {
/*  840 */       localIOException.printStackTrace();
/*      */     }
/*  842 */     return str;
/*      */   }
/*      */ 
/*      */   private void s()
/*      */   {
/*  851 */     BaseInfo.sWap2AppTemplateVersion = q();
/*  852 */     this.k = BaseInfo.sWap2AppTemplateVersion;
/*  853 */     if (!BaseInfo.isWap2AppAppid(this.h)) return; try
/*      */     {
/*  855 */       String str = r();
/*  856 */       if ((TextUtils.isEmpty(str)) || (BaseInfo.BaseAppInfo.compareVersion(BaseInfo.sWap2AppTemplateVersion, str)))
/*      */       {
/*  859 */         DHFile.copyFile(BaseInfo.sBaseWap2AppTemplatePath + "wap2app__template/", BaseInfo.sBaseFsAppsPath + this.h + "/www/", true, false);
/*      */       }
/*      */     } catch (Exception localException) {
/*  862 */       localException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   void a()
/*      */   {
/*  869 */     if ((!BaseInfo.isWap2AppAppid(this.h)) || (BaseInfo.isWap2AppCompleted(this.h))) return;
/*      */ 
/*  872 */     ThreadPool.self().addThreadTask(new Runnable()
/*      */     {
/*      */       public void run() {
/*      */         try {
/*  876 */           Logger.d("download_manager", "downloadWap2AppZip " + d.this.h);
/*  877 */           Activity localActivity = d.this.getActivity();
/*  878 */           IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(localActivity);
/*  879 */           if (localIActivityHandler != null) {
/*  880 */             String str = StringConst.STREAMAPP_KEY_BASESERVICEURL() + "w2a/rest?" + DataInterface.getUrlBaseData(d.this.getActivity(), d.this.h, BaseInfo.getCmitInfo(d.this.h).plusLauncher, BaseInfo.getCmitInfo(d.this.h).sfd) + "&tv=" + BaseInfo.getCmitInfo(d.this.h).templateVersion + "&v=" + d.this.j;
/*      */ 
/*  883 */             localIActivityHandler.downloadSimpleFileTask(d.this, str, BaseInfo.sBaseFsAppsPath + d.this.h + "/" + "wap2app__rest.zip", "wap2app_src");
/*      */           }
/*      */         } catch (Exception localException) {
/*  886 */           localException.printStackTrace();
/*      */         }
/*      */       }
/*      */     });
/*  890 */     b();
/*      */   }
/*      */ 
/*      */   public void a(IApp paramIApp) {
/*  894 */     String str1 = StringConst.STREAMAPP_KEY_ROOTPATH + "splash/" + paramIApp.obtainAppId() + ".png";
/*  895 */     if ((!new File(str1).exists()) && (!PdrUtil.isEquals("true", SP.getBundleData("pdr", paramIApp.obtainAppId() + "_no_splash_at_server")))) {
/*  896 */       String str2 = getActivity().getResources().getDisplayMetrics().widthPixels + "";
/*  897 */       String str3 = getActivity().getResources().getDisplayMetrics().heightPixels + "";
/*  898 */       String str4 = DataInterface.getSplashUrl(paramIApp.obtainAppId(), str2, str3);
/*  899 */       IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(getActivity());
/*  900 */       if (localIActivityHandler != null)
/*  901 */         localIActivityHandler.downloadSimpleFileTask(paramIApp, str4, str1, "splash");
/*      */     }
/*      */   }
/*      */ 
/*      */   void b()
/*      */   {
/*  909 */     if (this.W != null) return;
/*  910 */     String str = getActivity().getPackageName() + ".streamdownload.downloadfinish";
/*  911 */     IntentFilter localIntentFilter = new IntentFilter();
/*  912 */     localIntentFilter.addAction(str);
/*  913 */     this.W = new BroadcastReceiver()
/*      */     {
/*      */       public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent) {
/*  916 */         Bundle localBundle = paramAnonymousIntent.getExtras();
/*  917 */         String str1 = paramAnonymousIntent.getStringExtra("flag");
/*  918 */         String str2 = localBundle.getString(InvokeExecutorHelper.AppStreamUtils.getString("CONTRACT_INTENT_EXTRA_FILE"));
/*  919 */         if (!str2.contains(d.this.h)) {
/*  920 */           return;
/*      */         }
/*  922 */         if (!str2.startsWith("file://")) {
/*  923 */           str2 = "file://" + str2;
/*      */         }
/*  925 */         int i = localBundle.getInt(InvokeExecutorHelper.AppStreamUtils.getString("CONTRACT_INTENT_EXTRA_TYPE"));
/*  926 */         int j = localBundle.getInt(InvokeExecutorHelper.AppStreamUtils.getString("CONTRACT_INTENT_EXTRA_STATUS"));
/*  927 */         if (str1.compareTo("wap2app_src") == 0)
/*      */           try {
/*  929 */             int k = InvokeExecutorHelper.AppStreamUtils.getInt("CONTRACT_STATUS_SUCCESS");
/*  930 */             if (j == k) {
/*  931 */               File localFile = new File(BaseInfo.sBaseFsAppsPath + "/" + d.this.h + "/" + "wap2app__rest.zip");
/*  932 */               ZipUtils.upZipFile(localFile, BaseInfo.sBaseFsAppsPath + "/" + d.this.h + "/www");
/*  933 */               InvokeExecutorHelper.AppStreamUtils.invoke("createCompletedFile", d.this.h);
/*  934 */               localFile.delete();
/*  935 */               d.this.a(d.this);
/*  936 */               d.this.smartUpdate();
/*  937 */               Logger.d("download_manager", "download wap2app successful");
/*  938 */               d.this.getActivity().unregisterReceiver(d.this.W);
/*      */             }
/*      */           } catch (IOException localIOException) {
/*  941 */             localIOException.printStackTrace();
/*      */           }
/*      */       }
/*      */     };
/*  946 */     getActivity().registerReceiver(this.W, localIntentFilter);
/*      */   }
/*      */ 
/*      */   boolean a(String paramString, JSONObject paramJSONObject)
/*      */   {
/*  959 */     InputStream localInputStream = null;
/*  960 */     boolean bool1 = false;
/*      */     try {
/*  962 */       this.h = paramString;
/*  963 */       if ((this.aj == 0) || ((BaseInfo.mBaseAppInfoSet != null) && (!BaseInfo.mBaseAppInfoSet.containsKey(this.h)))) {
/*  964 */         localInputStream = DHFile.getInputStream(DHFile.createFileHandler(a(BaseInfo.sConfigXML)));
/*  965 */         if (localInputStream == null) {
/*  966 */           localInputStream = PlatformUtil.getResInputStream(a(BaseInfo.sConfigXML));
/*  967 */           if (localInputStream != null)
/*  968 */             this.aj = 1;
/*      */         }
/*      */       }
/*  971 */       else if (this.aj == 1) {
/*  972 */         localInputStream = PlatformUtil.getResInputStream(a(BaseInfo.sConfigXML));
/*      */       }
/*      */ 
/*  978 */       if (localInputStream == null)
/*      */       {
/*      */         boolean bool2;
/*  979 */         if ((isStreamApp()) && (!TextUtils.isEmpty(this.F))) {
/*  980 */           return true;
/*      */         }
/*  982 */         this.b.a = true;
/*  983 */         this.b.b = DOMException.toJSON(-1225, "WGTU安装包中www目录下manifest.json不存在");
/*  984 */         return bool1;
/*      */       }
/*  986 */       s();
/*      */ 
/*  988 */       bool1 = a(localInputStream, paramString, paramJSONObject);
/*  989 */       IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(getActivity());
/*  990 */       if (localIActivityHandler != null) {
/*  991 */         localIActivityHandler.updateSplash(this.G);
/*      */       }
/*  993 */       this.e = true;
/*      */     } catch (Exception localException) {
/*  995 */       Logger.w("parseConfig", localException);
/*      */     } finally {
/*  997 */       IOUtil.close(localInputStream);
/*      */     }
/*  999 */     return bool1;
/*      */   }
/*      */ 
/*      */   void a(Activity paramActivity)
/*      */   {
/* 1004 */     super.a(paramActivity);
/* 1005 */     this.aE = false;
/*      */ 
/* 1016 */     BaseInfo.sAppStateMap.remove(this.h);
/* 1017 */     Intent localIntent = paramActivity.getIntent();
/* 1018 */     if (localIntent != null) {
/* 1019 */       Bundle localBundle = localIntent.getExtras();
/* 1020 */       if ((localBundle != null) && (localBundle.containsKey("from_short_cut_start")) && (localBundle.getBoolean("from_short_cut_start"))) {
/* 1021 */         this.aE = true;
/*      */       }
/*      */ 
/* 1028 */       String str1 = localIntent.getStringExtra("from");
/* 1029 */       if ((!TextUtils.isEmpty(str1)) && (str1.equals("life")))
/* 1030 */         this.aF = "auto";
/* 1031 */       else if (localIntent.hasExtra("__sc")) {
/* 1032 */         this.aF = localIntent.getStringExtra("__sc");
/*      */       }
/*      */ 
/* 1035 */       if (TextUtils.isEmpty(this.aF))
/* 1036 */         if (this.w) {
/* 1037 */           this.aF = "force";
/*      */         } else {
/* 1039 */           SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/* 1040 */           String str2 = localSharedPreferences.getString("test_runing" + this.h, null);
/* 1041 */           if ((!TextUtils.isEmpty(str2)) && (str2.equals("__am=t")))
/* 1042 */             this.aF = "force";
/*      */           else
/* 1044 */             this.aF = "none";
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean startFromShortCut()
/*      */   {
/* 1058 */     return this.aE;
/*      */   }
/*      */ 
/*      */   public String forceShortCut()
/*      */   {
/* 1078 */     return this.aF;
/*      */   }
/*      */ 
/*      */   void a(byte paramByte) {
/* 1082 */     this.aj = paramByte;
/*      */   }
/*      */ 
/*      */   String a(String paramString)
/*      */   {
/* 1093 */     return this.ax + paramString;
/*      */   }
/*      */ 
/*      */   public String convert2RelPath(String paramString)
/*      */   {
/* 1098 */     int i1 = obtainAppDataPath().length();
/* 1099 */     int i2 = obtainAppDocPath().length();
/* 1100 */     int i3 = BaseInfo.sDocumentFullPath.length();
/* 1101 */     int i4 = BaseInfo.sDownloadFullPath.length();
/*      */ 
/* 1103 */     if (paramString.startsWith(obtainAppDataPath()))
/* 1104 */       paramString = "_www" + paramString.substring(i1 - 1);
/* 1105 */     else if (paramString.startsWith(obtainAppDataPath().substring(0, i1 - 1))) {
/* 1106 */       paramString = "_www" + paramString.substring(i1 - 1, paramString.length());
/*      */     }
/* 1109 */     else if (paramString.startsWith(obtainAppDocPath()))
/* 1110 */       paramString = "_doc" + paramString.substring(i2 - 1);
/* 1111 */     else if (paramString.startsWith(obtainAppDocPath().substring(0, i2 - 1))) {
/* 1112 */       paramString = "_doc" + paramString.substring(i2 - 1);
/*      */     }
/* 1115 */     else if (paramString.startsWith(BaseInfo.sDocumentFullPath))
/* 1116 */       paramString = "_documents" + paramString.substring(i3 - 1);
/* 1117 */     else if (paramString.startsWith(BaseInfo.sDocumentFullPath.substring(0, i3 - 1))) {
/* 1118 */       paramString = "_documents" + paramString.substring(i3 - 1);
/*      */     }
/* 1121 */     else if (paramString.startsWith(BaseInfo.sDownloadFullPath))
/* 1122 */       paramString = "_downloads" + paramString.substring(i4 - 1);
/* 1123 */     else if (paramString.startsWith(BaseInfo.sDownloadFullPath.substring(0, i4 - 1))) {
/* 1124 */       paramString = "_downloads" + paramString.substring(i4 - 1);
/*      */     }
/* 1126 */     return paramString;
/*      */   }
/*      */ 
/*      */   public String convert2AbsFullPath(String paramString1, String paramString2)
/*      */   {
/*      */     try {
/* 1132 */       if ((!PdrUtil.isEmpty(paramString2)) && (DHFile.isExist(paramString2))) return paramString2; 
/*      */     }
/* 1134 */     catch (IOException localIOException) { localIOException.printStackTrace(); }
/*      */ 
/*      */     int i1;
/* 1137 */     if ((i1 = paramString2.indexOf("?")) > 0) {
/* 1138 */       paramString2 = paramString2.substring(0, i1);
/*      */     }
/* 1140 */     if (paramString2.startsWith("_documents/")) {
/* 1141 */       paramString2 = BaseInfo.sDocumentFullPath + paramString2.substring("_documents/".length());
/* 1142 */     } else if (paramString2.startsWith("_documents")) {
/* 1143 */       paramString2 = BaseInfo.sDocumentFullPath + paramString2.substring("_documents".length());
/* 1144 */     } else if (paramString2.startsWith("_doc/")) {
/* 1145 */       paramString2 = obtainAppDocPath() + paramString2.substring("_doc/".length());
/* 1146 */     } else if (paramString2.startsWith("_doc")) {
/* 1147 */       paramString2 = obtainAppDocPath() + paramString2.substring("_doc".length());
/* 1148 */     } else if (paramString2.startsWith("_downloads/")) {
/* 1149 */       paramString2 = BaseInfo.sDownloadFullPath + paramString2.substring("_downloads/".length());
/* 1150 */     } else if (paramString2.startsWith("_downloads")) {
/* 1151 */       paramString2 = BaseInfo.sDownloadFullPath + paramString2.substring("_downloads".length());
/* 1152 */     } else if (paramString2.startsWith("_www/")) {
/* 1153 */       if (this.aj == 1)
/* 1154 */         paramString2 = BaseInfo.sBaseResAppsPath + this.h + "/" + BaseInfo.APP_WWW_FS_DIR + paramString2.substring("_www/".length());
/* 1155 */       else if (this.aj == 0)
/* 1156 */         paramString2 = this.ax + paramString2.substring("_www/".length());
/*      */     }
/* 1158 */     else if (paramString2.startsWith("_www")) {
/* 1159 */       if (this.aj == 1)
/* 1160 */         paramString2 = BaseInfo.sBaseResAppsPath + this.h + "/" + BaseInfo.APP_WWW_FS_DIR + paramString2.substring("_www".length());
/* 1161 */       else if (this.aj == 0)
/* 1162 */         paramString2 = this.ax + paramString2.substring("_www".length());
/*      */     }
/* 1164 */     else if (paramString2.startsWith("file://")) {
/* 1165 */       paramString2 = paramString2.substring("file://".length()); } else {
/* 1166 */       if (paramString2.startsWith(DeviceInfo.sDeviceRootDir))
/* 1167 */         return paramString2;
/*      */       int i2;
/* 1168 */       if (paramString2.startsWith("http://localhost")) {
/* 1169 */         paramString2 = paramString2.substring("http://localhost".length());
/* 1170 */         i2 = paramString2.indexOf("/");
/* 1171 */         paramString2 = convert2AbsFullPath(null, paramString2.substring(i2 + 1));
/*      */       } else {
/* 1173 */         i2 = 0;
/* 1174 */         if (paramString2.startsWith("/")) {
/* 1175 */           i2 = 1;
/* 1176 */           paramString2 = paramString2.substring(1);
/*      */         }
/* 1178 */         if (paramString1 != null) {
/* 1179 */           if (paramString1.startsWith("file:///android_asset/"))
/* 1180 */             paramString1 = paramString1.substring("file:///android_asset/".length());
/* 1181 */           else if (paramString1.startsWith("file://")) {
/* 1182 */             paramString1 = paramString1.substring("file://".length());
/*      */           }
/*      */         }
/* 1185 */         if ((paramString1 != null) && (i2 == 0)) {
/* 1186 */           paramString2 = PdrUtil.standardizedURL(paramString1, paramString2);
/* 1187 */         } else if (i2 != 0) {
/* 1188 */           String str1 = "www/";
/* 1189 */           String str2 = obtainAppDataPath();
/* 1190 */           if ((paramString1 != null) && (!PdrUtil.isEquals(paramString1, str2)) && (paramString1.contains(str1))) {
/* 1191 */             str2 = paramString1.substring(0, paramString1.indexOf(str1) + str1.length());
/*      */           }
/* 1193 */           paramString2 = str2 + d(paramString2);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1204 */     return paramString2;
/*      */   }
/*      */ 
/*      */   public String convert2AbsFullPath(String paramString) {
/* 1208 */     return convert2AbsFullPath(null, paramString);
/*      */   }
/*      */ 
/*      */   public String obtainRuntimeArgs()
/*      */   {
/* 1213 */     return JSONObject.quote(this.l);
/*      */   }
/*      */ 
/*      */   public void setRuntimeArgs(String paramString)
/*      */   {
/* 1219 */     this.l = paramString;
/*      */   }
/*      */ 
/*      */   public String obtainAdaptationJs()
/*      */   {
/* 1224 */     if ((this.ai == null) && (!PdrUtil.isEmpty(this.r))) {
/* 1225 */       byte[] arrayOfByte = PlatformUtil.getFileContent(a(this.r), obtainRunningAppMode() == 1 ? 0 : 2);
/* 1226 */       if (arrayOfByte != null)
/* 1227 */         this.ai = new String(arrayOfByte);
/*      */       else {
/* 1229 */         this.ai = "";
/*      */       }
/*      */     }
/* 1232 */     return this.ai;
/*      */   }
/*      */ 
/*      */   public String convert2WebviewFullPath(String paramString1, String paramString2)
/*      */   {
/* 1243 */     if (PdrUtil.isEmpty(paramString2)) return paramString2;
/* 1244 */     if (this.z) {
/* 1245 */       if (!paramString2.startsWith("http://")) {
/* 1246 */         paramString2 = this.ay + paramString2;
/*      */       }
/*      */     }
/* 1249 */     else if ((!paramString2.startsWith("file://")) && (!paramString2.startsWith("http://")) && (!paramString2.startsWith("https://"))) {
/*      */       try {
/* 1251 */         if (DHFile.isExist(paramString2))
/* 1252 */           return "file:///" + d(paramString2);
/*      */       }
/*      */       catch (IOException localIOException) {
/* 1255 */         localIOException.printStackTrace();
/*      */       }
/* 1257 */       if (paramString2.startsWith(DeviceInfo.sDeviceRootDir)) {
/* 1258 */         paramString2 = "file://" + paramString2;
/*      */       } else {
/* 1260 */         int i1 = 0;
/* 1261 */         if (paramString2.startsWith("/")) {
/* 1262 */           i1 = 1;
/* 1263 */           paramString2 = paramString2.substring(1);
/*      */         }
/* 1265 */         if (paramString2.startsWith("_www")) {
/* 1266 */           paramString2 = obtainWebviewBaseUrl() + d(paramString2.substring("_www".length()));
/* 1267 */         } else if (paramString2.startsWith("_doc")) {
/* 1268 */           paramString2 = "file://" + obtainAppDocPath() + d(paramString2.substring("_doc".length()));
/* 1269 */         } else if (paramString2.startsWith("_documents")) {
/* 1270 */           paramString2 = "file://" + BaseInfo.sDocumentFullPath + d(paramString2.substring("_documents".length()));
/* 1271 */         } else if (paramString2.startsWith("_downloads")) {
/* 1272 */           paramString2 = "file://" + BaseInfo.sDownloadFullPath + d(paramString2.substring("_downloads".length()));
/* 1273 */         } else if ((paramString1 != null) && (i1 == 0)) {
/* 1274 */           paramString2 = PdrUtil.standardizedURL(paramString1, paramString2);
/*      */         } else {
/* 1276 */           String str1 = obtainWebviewBaseUrl();
/* 1277 */           String str2 = "www/";
/* 1278 */           if ((paramString1 != null) && (!PdrUtil.isEquals(paramString1, str1)) && (paramString1.contains(str2))) {
/* 1279 */             str1 = paramString1.substring(0, paramString1.indexOf(str2) + str2.length());
/*      */           }
/* 1281 */           paramString2 = str1 + d(paramString2);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1286 */     return paramString2;
/*      */   }
/*      */ 
/*      */   private static String d(String paramString) {
/* 1290 */     if ((paramString != null) && (paramString.length() > 0) && (paramString.charAt(0) == '/'))
/* 1291 */       return d(paramString.substring(1));
/* 1292 */     return paramString;
/*      */   }
/*      */ 
/*      */   public void addFeaturePermission(String paramString)
/*      */   {
/* 1297 */     this.H.add(paramString.toLowerCase());
/*      */   }
/*      */ 
/*      */   public void addAllFeaturePermission()
/*      */   {
/* 1302 */     io.dcloud.common.b.a.a.a(this.h);
/*      */   }
/*      */ 
/*      */   void c()
/*      */   {
/* 1313 */     io.dcloud.common.b.a.a.a(this.h, this.H);
/*      */   }
/*      */ 
/*      */   void a(final ICallBack paramICallBack) {
/* 1317 */     int i1 = ((BaseInfo.ISDEBUG) || (this.A)) && (this.aj == 1) ? 1 : 0;
/* 1318 */     if (i1 != 0) {
/* 1319 */       new Thread()
/*      */       {
/*      */         public void run() {
/* 1322 */           String str = BaseInfo.sBaseFsAppsPath + d.this.h + DeviceInfo.sSeparatorChar + BaseInfo.APP_WWW_FS_DIR;
/*      */ 
/* 1324 */           long l1 = System.currentTimeMillis();
/* 1325 */           Logger.d(d.a, d.this.h + " copy resoure begin!!!");
/* 1326 */           DHFile.delete(str);
/* 1327 */           DHFile.copyDir(d.a(d.this), str);
/* 1328 */           long l2 = System.currentTimeMillis();
/* 1329 */           Logger.d(d.a, d.this.h + " copy resoure end!!! useTime=" + (l2 - l1));
/* 1330 */           d.a(d.this, (byte)0);
/* 1331 */           d.this.setAppDataPath(str);
/* 1332 */           if (d.this.c != null) {
/* 1333 */             d.this.c.saveToBundleData();
/*      */           }
/* 1335 */           MessageHandler.sendMessage(new MessageHandler.IMessages()
/*      */           {
/*      */             public void execute(Object paramAnonymous2Object) {
/* 1338 */               d.5.this.a.onCallBack(0, null);
/*      */             }
/*      */           }
/*      */           , null);
/*      */         }
/*      */       }
/* 1319 */       .start();
/*      */     }
/*      */     else
/*      */     {
/* 1345 */       paramICallBack.onCallBack(0, null);
/*      */     }
/*      */   }
/*      */ 
/*      */   boolean b(String paramString) {
/* 1350 */     if ((Build.VERSION.SDK_INT >= 21) && (getActivity() != null)) {
/* 1351 */       if (this.aD != -111111)
/* 1352 */         getActivity().getWindow().setStatusBarColor(this.aD);
/*      */       else {
/* 1354 */         getActivity().getWindow().setStatusBarColor(BaseInfo.mDeStatusBarBackground);
/*      */       }
/*      */     }
/* 1357 */     AppStatus.setAppStatus(this.h, 2);
/* 1358 */     BaseInfo.s_Runing_App_Count += 1;
/* 1359 */     this.f = true;
/* 1360 */     setRuntimeArgs(paramString);
/*      */ 
/* 1365 */     return a(5);
/*      */   }
/*      */ 
/*      */   private boolean a(int paramInt)
/*      */   {
/* 1370 */     Logger.i(a, this.h + (this.aj == 1 ? " APP_RUNNING_MODE" : " FS_RUNNING_MODE"));
/* 1371 */     c();
/* 1372 */     setStatus((byte)3);
/* 1373 */     InvokeExecutorHelper.QihooInnerStatisticUtil.invoke("statOnPageStart", this.h);
/* 1374 */     if (this.Q != null) this.Q.onStart();
/* 1375 */     Logger.i(a, "mLaunchPath=" + this.B);
/* 1376 */     Logger.i("download_manager", "webapp start task begin success appid=" + this.h + " mLaunchPath=" + this.B);
/* 1377 */     TestUtil.print(TestUtil.START_STREAM_APP, "webapp start appid=" + this.h);
/* 1378 */     BaseInfo.setLoadingLaunchePage(true, "start0");
/* 1379 */     Object localObject1 = null;
/*      */ 
/* 1381 */     String str = getActivity().getIntent().getStringExtra("__launch_path__");
/* 1382 */     if ((null != str) && (!"".equals(str.trim()))) {
/* 1383 */       getActivity().getIntent().removeExtra("__launch_path__");
/* 1384 */       localObject1 = str;
/* 1385 */       if (!"about:blank".equals(localObject1))
/* 1386 */         localObject1 = convert2WebviewFullPath(null, (String)localObject1);
/*      */     }
/* 1388 */     else if ((BaseInfo.isWap2AppAppid(this.h)) && (!TextUtils.isEmpty(this.C))) {
/* 1389 */       localObject1 = convert2WebviewFullPath(null, this.C);
/*      */     } else {
/* 1391 */       localObject1 = convert2WebviewFullPath(null, this.B);
/*      */     }
/* 1393 */     a();
/* 1394 */     Object localObject2 = this.g.processEvent(IMgr.MgrType.WindowMgr, paramInt, new Object[] { this, localObject1, Boolean.valueOf(this.S), this.aC });
/* 1395 */     return localObject2 == null ? true : Boolean.parseBoolean(String.valueOf(localObject2));
/*      */   }
/*      */ 
/*      */   boolean c(String paramString)
/*      */   {
/* 1400 */     AppStatus.setAppStatus(this.h, 2);
/* 1401 */     setRuntimeArgs(paramString);
/* 1402 */     setStatus((byte)3);
/* 1403 */     Object localObject = this.g.processEvent(IMgr.MgrType.WindowMgr, 41, new Object[] { this, convert2WebviewFullPath(null, this.F), Boolean.valueOf(this.S) });
/* 1404 */     return localObject == null ? true : Boolean.parseBoolean(String.valueOf(localObject));
/*      */   }
/*      */ 
/*      */   boolean d()
/*      */   {
/* 1415 */     setAppDataPath(BaseInfo.sBaseFsAppsPath + this.h + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
/* 1416 */     boolean bool = a(this.h, null);
/* 1417 */     if (bool) {
/* 1418 */       showSplash();
/* 1419 */       this.g.processEvent(IMgr.MgrType.FeatureMgr, 3, this.h);
/* 1420 */       return a(10);
/*      */     }
/* 1422 */     return bool;
/*      */   }
/*      */ 
/*      */   public void setStatus(byte paramByte)
/*      */   {
/* 1431 */     this.d = paramByte;
/* 1432 */     if (paramByte == 3)
/* 1433 */       this.T = System.currentTimeMillis();
/*      */   }
/*      */ 
/*      */   void e()
/*      */   {
/* 1441 */     if ((Build.VERSION.SDK_INT >= 21) && (getActivity() != null)) {
/* 1442 */       if (this.aD != -111111)
/* 1443 */         getActivity().getWindow().setStatusBarColor(this.aD);
/*      */       else {
/* 1445 */         getActivity().getWindow().setStatusBarColor(BaseInfo.mDeStatusBarBackground);
/*      */       }
/*      */     }
/* 1448 */     setStatus((byte)3);
/* 1449 */     InvokeExecutorHelper.QihooInnerStatisticUtil.invoke("statOnPageStart", this.h);
/* 1450 */     this.aa.onAppActive(this);
/* 1451 */     BaseInfo.sAppStateMap.remove(this.h);
/* 1452 */     a(ISysEventListener.SysEventType.onWebAppForeground, IntentConst.obtainArgs(obtainWebAppIntent(), this.h));
/*      */   }
/*      */ 
/*      */   void f()
/*      */   {
/* 1460 */     InvokeExecutorHelper.QihooInnerStatisticUtil.invoke("statOnPageEnd", this.h);
/* 1461 */     this.aa.onAppUnActive(this);
/* 1462 */     a(ISysEventListener.SysEventType.onWebAppPause, this);
/* 1463 */     a(ISysEventListener.SysEventType.onWebAppBackground, this);
/* 1464 */     if (this.Q != null) this.Q.onPause(this, null);
/* 1465 */     setStatus((byte)2);
/*      */   }
/*      */ 
/*      */   public void g()
/*      */   {
/* 1475 */     f();
/*      */ 
/* 1483 */     setStatus((byte)1);
/* 1484 */     this.g.processEvent(IMgr.MgrType.FeatureMgr, 3, this.h);
/* 1485 */     Logger.d("appmgr", this.h + " will active change to unrunning");
/*      */ 
/* 1487 */     this.g.processEvent(null, 0, this);
/*      */   }
/*      */ 
/*      */   private void t()
/*      */   {
/* 1495 */     if ((Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) && (Build.VERSION.SDK_INT >= 23) && (!PlatformUtil.checkGTAndYoumeng())) {
/* 1496 */       return;
/*      */     }
/* 1498 */     if (!isStreamApp())
/*      */     {
/* 1500 */       final d locald = this;
/* 1501 */       ThreadPool.self().addThreadTask(new Runnable() {
/*      */         public void run() {
/* 1503 */           HashMap localHashMap = new HashMap();
/* 1504 */           localHashMap.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
/* 1505 */           SharedPreferences localSharedPreferences = SP.getOrCreateBundle("start_statistics_data");
/* 1506 */           String str = CommitDataUtil.getStartData(locald, localSharedPreferences);
/* 1507 */           if (!TextUtils.isEmpty(str)) {
/* 1508 */             byte[] arrayOfByte = NetTool.httpPost("https://service.dcloud.net.cn/collect/plusapp/startup", str, localHashMap);
/* 1509 */             if ((arrayOfByte != null) && (str.contains("&apps=")))
/* 1510 */               localSharedPreferences.edit().putLong("commit_app_list_time", System.currentTimeMillis()).commit();
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/* 1515 */     else if (isStreamApp()) {
/* 1516 */       ThreadPool.self().addThreadTask(new Runnable()
/*      */       {
/*      */         public void run() {
/* 1519 */           HashMap localHashMap = new HashMap();
/* 1520 */           localHashMap.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
/* 1521 */           String str1 = ShortCutUtil.getSreamAppShortName(d.this.getActivity());
/* 1522 */           String str2 = LauncherUtil.getLauncherPackageName(d.this.getActivity());
/* 1523 */           JSONObject localJSONObject = new JSONObject();
/*      */           try {
/* 1525 */             if ((null != str2) && (!"".equals(str2.trim()))) {
/* 1526 */               localJSONObject.put("pn", str2);
/* 1527 */               PackageInfo localPackageInfo = d.this.getActivity().getPackageManager().getPackageInfo(str2, 0);
/* 1528 */               localJSONObject.put("pv", localPackageInfo.versionName);
/* 1529 */               localJSONObject.put("pvc", String.valueOf(localPackageInfo.versionCode));
/*      */             }
/*      */           }
/*      */           catch (Exception localException) {
/* 1533 */             localException.printStackTrace();
/*      */           }
/* 1535 */           StringBuffer localStringBuffer = new StringBuffer();
/* 1536 */           localStringBuffer.append(DataInterface.getUrlBaseData(d.this.getActivity(), d.this.obtainAppId(), null, null));
/* 1537 */           if ((null != localJSONObject.toString()) && (!"".equals(localJSONObject.toString().trim()))) {
/* 1538 */             localStringBuffer.append("&launcher=" + localJSONObject.toString());
/*      */           }
/* 1540 */           localStringBuffer.append("&shortcuts=" + str1);
/* 1541 */           localStringBuffer.append("&romv=" + DataInterface.getRomVersion());
/* 1542 */           if (!TextUtils.isEmpty(localStringBuffer))
/* 1543 */             NetTool.httpPost(StringConst.STREAMAPP_KEY_BASESERVICEURL() + "collect/shortcuts", localStringBuffer.toString(), localHashMap);
/*      */         }
/*      */       });
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean h()
/*      */   {
/* 1552 */     boolean bool = true;
/* 1553 */     Logger.d("appmgr", this.h + " onStop");
/* 1554 */     if (this.Q != null) bool = this.Q.onStop();
/* 1555 */     return bool;
/*      */   }
/*      */ 
/*      */   public void i()
/*      */   {
/* 1564 */     BaseInfo.sAppStateMap.put(this.h, obtainWebAppIntent());
/*      */   }
/*      */ 
/*      */   public void j() {
/* 1568 */     Logger.d("appmgr", "webapp.onStoped");
/* 1569 */     BaseInfo.s_Runing_App_Count -= 1;
/* 1570 */     if (TextUtils.equals(BaseInfo.sLastRunApp, this.h)) {
/* 1571 */       BaseInfo.sLastRunApp = null;
/*      */     }
/* 1573 */     a(ISysEventListener.SysEventType.onWebAppStop, this);
/* 1574 */     u();
/* 1575 */     PlatformUtil.invokeMethod("io.dcloud.feature.apsqh.QHNotifactionReceiver", "doSaveNotifications", null, new Class[] { Context.class }, new Object[] { this.Z.getBaseContext() });
/* 1576 */     PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "streamappStop", null, new Class[] { Activity.class, String.class }, new Object[] { getActivity(), obtainAppId() });
/* 1577 */     if (this.W != null) {
/*      */       try {
/* 1579 */         getActivity().unregisterReceiver(this.W);
/*      */       } catch (Exception localException) {
/* 1581 */         localException.printStackTrace();
/*      */       }
/*      */     }
/* 1584 */     t();
/* 1585 */     new Thread() {
/*      */       public void run() {
/*      */         try {
/* 1588 */           DHFile.deleteFile(d.this.obtainAppTempPath());
/*      */         } catch (IOException localIOException) {
/* 1590 */           localIOException.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/* 1585 */     .start();
/*      */ 
/* 1594 */     io.dcloud.common.b.a.a.b(this.h);
/*      */ 
/* 1596 */     this.g.c(this);
/* 1597 */     this.g.processEvent(IMgr.MgrType.WindowMgr, 25, this);
/*      */   }
/*      */ 
/*      */   private void u()
/*      */   {
/* 1602 */     if (BaseInfo.isForQihooHelper(getActivity())) {
/* 1603 */       Intent localIntent = new Intent();
/* 1604 */       localIntent.setClassName(getActivity(), DCloudAdapterUtil.getDcloudDownloadService());
/* 1605 */       localIntent.putExtra("mode", "handle_service");
/* 1606 */       getActivity().startService(localIntent);
/*      */     }
/*      */   }
/*      */ 
/*      */   IFrameView a(IWebviewStateListener paramIWebviewStateListener) {
/* 1611 */     c();
/* 1612 */     return (IFrameView)this.g.processEvent(IMgr.MgrType.WindowMgr, 17, new Object[] { this, convert2WebviewFullPath(null, this.B), paramIWebviewStateListener });
/*      */   }
/*      */ 
/*      */   public String obtainAppId()
/*      */   {
/* 1617 */     return this.h;
/*      */   }
/*      */ 
/*      */   public String checkPrivateDirAndCopy2Temp(String paramString)
/*      */   {
/* 1622 */     if ((obtainRunningAppMode() == 1) && 
/* 1623 */       (checkPrivateDir(paramString)))
/*      */     {
/* 1625 */       int i1 = paramString.indexOf(BaseInfo.APP_WWW_FS_DIR);
/* 1626 */       String str1 = paramString.substring(i1 + BaseInfo.APP_WWW_FS_DIR.length());
/* 1627 */       String str2 = this.ax + str1;
/* 1628 */       paramString = obtainAppTempPath() + str1;
/* 1629 */       if (!DHFile.exists(paramString)) {
/* 1630 */         DHFile.copyAssetsFile(str2, paramString);
/*      */       }
/*      */     }
/*      */ 
/* 1634 */     return paramString;
/*      */   }
/*      */ 
/*      */   public boolean checkPrivateDir(String paramString)
/*      */   {
/* 1639 */     boolean bool = false;
/* 1640 */     if (paramString.startsWith(obtainAppDataPath()))
/* 1641 */       bool = true;
/* 1642 */     else if (paramString.startsWith("_www")) {
/* 1643 */       bool = true;
/*      */     }
/* 1645 */     return bool;
/*      */   }
/*      */ 
/*      */   public void unregisterSysEventListener(ISysEventListener paramISysEventListener, ISysEventListener.SysEventType paramSysEventType)
/*      */   {
/* 1650 */     if (this.I != null) {
/* 1651 */       ArrayList localArrayList = (ArrayList)this.I.get(paramSysEventType);
/* 1652 */       if (localArrayList != null) {
/* 1653 */         localArrayList.remove(paramISysEventListener);
/* 1654 */         if (localArrayList.isEmpty())
/* 1655 */           this.I.remove(paramSysEventType);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void registerSysEventListener(ISysEventListener paramISysEventListener, ISysEventListener.SysEventType paramSysEventType)
/*      */   {
/* 1663 */     if (this.I == null) {
/* 1664 */       this.I = new HashMap(1);
/*      */     }
/* 1666 */     ArrayList localArrayList = (ArrayList)this.I.get(paramSysEventType);
/* 1667 */     if (localArrayList == null) {
/* 1668 */       localArrayList = new ArrayList();
/* 1669 */       this.I.put(paramSysEventType, localArrayList);
/*      */     }
/* 1671 */     localArrayList.add(paramISysEventListener);
/*      */   }
/*      */ 
/*      */   boolean a(ISysEventListener.SysEventType paramSysEventType, Object paramObject) {
/* 1675 */     boolean bool = false;
/* 1676 */     if (this.I == null) return false;
/* 1677 */     ArrayList localArrayList = (ArrayList)this.I.get(paramSysEventType);
/* 1678 */     if (localArrayList != null) {
/* 1679 */       for (int i1 = localArrayList.size() - 1; i1 >= 0; i1--) {
/* 1680 */         ISysEventListener localISysEventListener = (ISysEventListener)localArrayList.get(i1);
/* 1681 */         if (a(localISysEventListener, paramSysEventType)) {
/* 1682 */           bool |= localISysEventListener.onExecute(paramSysEventType, paramObject);
/* 1683 */           if ((bool) && (!a(paramSysEventType))) {
/*      */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1689 */     return bool;
/*      */   }
/*      */ 
/*      */   private boolean a(ISysEventListener paramISysEventListener, ISysEventListener.SysEventType paramSysEventType) {
/* 1693 */     return (!(paramISysEventListener instanceof IBoot)) || (PdrUtil.parseBoolean(String.valueOf(this.g.processEvent(null, 1, paramISysEventListener)), false, false)) || ((paramSysEventType != ISysEventListener.SysEventType.onStart) && (paramSysEventType != ISysEventListener.SysEventType.onStop) && (paramSysEventType != ISysEventListener.SysEventType.onPause) && (paramSysEventType != ISysEventListener.SysEventType.onResume));
/*      */   }
/*      */ 
/*      */   public boolean onExecute(ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*      */   {
/* 1700 */     boolean bool = false;
/* 1701 */     if (this.d == 3) {
/* 1702 */       bool = a(paramSysEventType, paramObject);
/* 1703 */       bool |= a(ISysEventListener.SysEventType.AllSystemEvent, paramObject);
/* 1704 */     } else if ((this.d == 1) && ((paramSysEventType == ISysEventListener.SysEventType.onWebAppStop) || (paramSysEventType == ISysEventListener.SysEventType.onStop))) {
/* 1705 */       j();
/*      */     }
/* 1707 */     return bool;
/*      */   }
/*      */ 
/*      */   public static boolean a(ISysEventListener.SysEventType paramSysEventType)
/*      */   {
/* 1717 */     return (paramSysEventType != ISysEventListener.SysEventType.onKeyDown) && (paramSysEventType != ISysEventListener.SysEventType.onKeyUp) && (paramSysEventType != ISysEventListener.SysEventType.onKeyLongPress);
/*      */   }
/*      */ 
/*      */   public String k()
/*      */   {
/* 1724 */     JSONObject localJSONObject = new JSONObject();
/*      */     try {
/* 1726 */       localJSONObject.put("appid", this.h);
/* 1727 */       localJSONObject.put("version", this.j);
/* 1728 */       localJSONObject.put("name", this.G);
/* 1729 */       localJSONObject.put("description", this.m);
/* 1730 */       localJSONObject.put("author", this.n);
/* 1731 */       localJSONObject.put("email", this.o);
/* 1732 */       localJSONObject.put("license", this.p);
/* 1733 */       localJSONObject.put("licensehref", this.q);
/* 1734 */       localJSONObject.put("features", new JSONArray(this.H));
/*      */     } catch (JSONException localJSONException) {
/* 1736 */       localJSONException.printStackTrace();
/*      */     }
/* 1738 */     return localJSONObject.toString();
/*      */   }
/*      */ 
/*      */   public String obtainAppName()
/*      */   {
/* 1743 */     return this.G;
/*      */   }
/*      */ 
/*      */   public String obtainAppVersionName()
/*      */   {
/* 1748 */     return this.j; } 
/*      */   // ERROR //
/*      */   boolean b(String paramString, JSONObject paramJSONObject) { // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   4: iconst_1
/*      */     //   5: putfield 403	io/dcloud/common/a/f:c	Z
/*      */     //   8: aload_0
/*      */     //   9: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   12: iconst_1
/*      */     //   13: putfield 404	io/dcloud/common/a/f:d	Z
/*      */     //   16: iconst_0
/*      */     //   17: istore_3
/*      */     //   18: new 288	java/lang/StringBuilder
/*      */     //   21: dup
/*      */     //   22: invokespecial 652	java/lang/StringBuilder:<init>	()V
/*      */     //   25: getstatic 424	io/dcloud/common/util/BaseInfo:sBaseFsAppsPath	Ljava/lang/String;
/*      */     //   28: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   31: aload_0
/*      */     //   32: getfield 381	io/dcloud/common/a/d:h	Ljava/lang/String;
/*      */     //   35: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   38: getstatic 406	io/dcloud/common/adapter/util/DeviceInfo:sSeparatorChar	C
/*      */     //   41: invokevirtual 653	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
/*      */     //   44: getstatic 414	io/dcloud/common/util/BaseInfo:APP_WWW_FS_DIR	Ljava/lang/String;
/*      */     //   47: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   50: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   53: astore 4
/*      */     //   55: new 288	java/lang/StringBuilder
/*      */     //   58: dup
/*      */     //   59: invokespecial 652	java/lang/StringBuilder:<init>	()V
/*      */     //   62: aload 4
/*      */     //   64: iconst_0
/*      */     //   65: aload 4
/*      */     //   67: invokevirtual 639	java/lang/String:length	()I
/*      */     //   70: iconst_1
/*      */     //   71: isub
/*      */     //   72: invokevirtual 643	java/lang/String:substring	(II)Ljava/lang/String;
/*      */     //   75: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   78: ldc 68
/*      */     //   80: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   83: getstatic 437	java/io/File:separatorChar	C
/*      */     //   86: invokevirtual 653	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
/*      */     //   89: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   92: astore 5
/*      */     //   94: new 288	java/lang/StringBuilder
/*      */     //   97: dup
/*      */     //   98: invokespecial 652	java/lang/StringBuilder:<init>	()V
/*      */     //   101: aload 4
/*      */     //   103: iconst_0
/*      */     //   104: aload 4
/*      */     //   106: invokevirtual 639	java/lang/String:length	()I
/*      */     //   109: iconst_1
/*      */     //   110: isub
/*      */     //   111: invokevirtual 643	java/lang/String:substring	(II)Ljava/lang/String;
/*      */     //   114: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   117: ldc 58
/*      */     //   119: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   122: getstatic 437	java/io/File:separatorChar	C
/*      */     //   125: invokevirtual 653	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
/*      */     //   128: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   131: astore 6
/*      */     //   133: new 288	java/lang/StringBuilder
/*      */     //   136: dup
/*      */     //   137: invokespecial 652	java/lang/StringBuilder:<init>	()V
/*      */     //   140: aload 4
/*      */     //   142: iconst_0
/*      */     //   143: aload 4
/*      */     //   145: invokevirtual 639	java/lang/String:length	()I
/*      */     //   148: iconst_1
/*      */     //   149: isub
/*      */     //   150: invokevirtual 643	java/lang/String:substring	(II)Ljava/lang/String;
/*      */     //   153: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   156: ldc 59
/*      */     //   158: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   161: getstatic 437	java/io/File:separatorChar	C
/*      */     //   164: invokevirtual 653	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
/*      */     //   167: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   170: astore 7
/*      */     //   172: aconst_null
/*      */     //   173: astore 8
/*      */     //   175: iconst_1
/*      */     //   176: istore 9
/*      */     //   178: aconst_null
/*      */     //   179: astore 10
/*      */     //   181: new 277	java/io/File
/*      */     //   184: dup
/*      */     //   185: aload_1
/*      */     //   186: invokespecial 613	java/io/File:<init>	(Ljava/lang/String;)V
/*      */     //   189: astore 11
/*      */     //   191: aconst_null
/*      */     //   192: astore 12
/*      */     //   194: aload 5
/*      */     //   196: invokestatic 540	io/dcloud/common/adapter/io/DHFile:delete	(Ljava/lang/Object;)Z
/*      */     //   199: pop
/*      */     //   200: aload 11
/*      */     //   202: aload 5
/*      */     //   204: invokestatic 610	io/dcloud/common/util/ZipUtils:upZipFile	(Ljava/io/File;Ljava/lang/String;)V
/*      */     //   207: goto +95 -> 302
/*      */     //   210: astore 13
/*      */     //   212: aload 5
/*      */     //   214: invokestatic 540	io/dcloud/common/adapter/io/DHFile:delete	(Ljava/lang/Object;)Z
/*      */     //   217: pop
/*      */     //   218: aload_0
/*      */     //   219: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   222: bipush 252
/*      */     //   224: ldc 200
/*      */     //   226: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   229: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   232: aload_0
/*      */     //   233: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   236: iconst_1
/*      */     //   237: putfield 401	io/dcloud/common/a/f:a	Z
/*      */     //   240: iconst_0
/*      */     //   241: istore_3
/*      */     //   242: iload_3
/*      */     //   243: istore 14
/*      */     //   245: aload 12
/*      */     //   247: invokestatic 588	io/dcloud/common/util/IOUtil:close	(Ljava/io/InputStream;)V
/*      */     //   250: iload 14
/*      */     //   252: ireturn
/*      */     //   253: astore 13
/*      */     //   255: aload 13
/*      */     //   257: invokestatic 558	io/dcloud/common/adapter/util/Logger:w	(Ljava/lang/Throwable;)V
/*      */     //   260: aload 5
/*      */     //   262: invokestatic 540	io/dcloud/common/adapter/io/DHFile:delete	(Ljava/lang/Object;)Z
/*      */     //   265: pop
/*      */     //   266: aload_0
/*      */     //   267: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   270: sipush -1201
/*      */     //   273: ldc 33
/*      */     //   275: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   278: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   281: aload_0
/*      */     //   282: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   285: iconst_1
/*      */     //   286: putfield 401	io/dcloud/common/a/f:a	Z
/*      */     //   289: iconst_0
/*      */     //   290: istore_3
/*      */     //   291: iload_3
/*      */     //   292: istore 14
/*      */     //   294: aload 12
/*      */     //   296: invokestatic 588	io/dcloud/common/util/IOUtil:close	(Ljava/io/InputStream;)V
/*      */     //   299: iload 14
/*      */     //   301: ireturn
/*      */     //   302: aconst_null
/*      */     //   303: astore 13
/*      */     //   305: new 288	java/lang/StringBuilder
/*      */     //   308: dup
/*      */     //   309: invokespecial 652	java/lang/StringBuilder:<init>	()V
/*      */     //   312: aload 5
/*      */     //   314: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   317: getstatic 418	io/dcloud/common/util/BaseInfo:WGTU_UPDATE_XML	Ljava/lang/String;
/*      */     //   320: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   323: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   326: invokestatic 539	io/dcloud/common/adapter/io/DHFile:createFileHandler	(Ljava/lang/String;)Ljava/lang/Object;
/*      */     //   329: invokestatic 543	io/dcloud/common/adapter/io/DHFile:getInputStream	(Ljava/lang/Object;)Ljava/io/InputStream;
/*      */     //   332: astore 12
/*      */     //   334: aload 12
/*      */     //   336: ifnonnull +39 -> 375
/*      */     //   339: aload_0
/*      */     //   340: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   343: sipush -1221
/*      */     //   346: ldc 34
/*      */     //   348: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   351: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   354: aload_0
/*      */     //   355: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   358: iconst_1
/*      */     //   359: putfield 401	io/dcloud/common/a/f:a	Z
/*      */     //   362: iconst_0
/*      */     //   363: istore_3
/*      */     //   364: iload_3
/*      */     //   365: istore 14
/*      */     //   367: aload 12
/*      */     //   369: invokestatic 588	io/dcloud/common/util/IOUtil:close	(Ljava/io/InputStream;)V
/*      */     //   372: iload 14
/*      */     //   374: ireturn
/*      */     //   375: aload 12
/*      */     //   377: invokestatic 606	io/dcloud/common/util/XmlUtil:XML_Parser	(Ljava/io/InputStream;)Lio/dcloud/common/util/XmlUtil$DHNode;
/*      */     //   380: astore 13
/*      */     //   382: aload 13
/*      */     //   384: ifnonnull +11 -> 395
/*      */     //   387: new 283	java/lang/Exception
/*      */     //   390: dup
/*      */     //   391: invokespecial 622	java/lang/Exception:<init>	()V
/*      */     //   394: athrow
/*      */     //   395: goto +46 -> 441
/*      */     //   398: astore 14
/*      */     //   400: aload 14
/*      */     //   402: invokevirtual 624	java/lang/Exception:printStackTrace	()V
/*      */     //   405: aload_0
/*      */     //   406: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   409: sipush -1222
/*      */     //   412: ldc 35
/*      */     //   414: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   417: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   420: aload_0
/*      */     //   421: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   424: iconst_1
/*      */     //   425: putfield 401	io/dcloud/common/a/f:a	Z
/*      */     //   428: iconst_0
/*      */     //   429: istore_3
/*      */     //   430: iload_3
/*      */     //   431: istore 15
/*      */     //   433: aload 12
/*      */     //   435: invokestatic 588	io/dcloud/common/util/IOUtil:close	(Ljava/io/InputStream;)V
/*      */     //   438: iload 15
/*      */     //   440: ireturn
/*      */     //   441: aload 13
/*      */     //   443: ifnull +232 -> 675
/*      */     //   446: aload 13
/*      */     //   448: ldc 74
/*      */     //   450: invokestatic 607	io/dcloud/common/util/XmlUtil:getAttributeValue	(Lio/dcloud/common/util/XmlUtil$DHNode;Ljava/lang/String;)Ljava/lang/String;
/*      */     //   453: astore 14
/*      */     //   455: aload 13
/*      */     //   457: ldc 81
/*      */     //   459: invokestatic 608	io/dcloud/common/util/XmlUtil:getElement	(Lio/dcloud/common/util/XmlUtil$DHNode;Ljava/lang/String;)Lio/dcloud/common/util/XmlUtil$DHNode;
/*      */     //   462: astore 15
/*      */     //   464: aload 15
/*      */     //   466: ldc 183
/*      */     //   468: invokestatic 607	io/dcloud/common/util/XmlUtil:getAttributeValue	(Lio/dcloud/common/util/XmlUtil$DHNode;Ljava/lang/String;)Ljava/lang/String;
/*      */     //   471: astore 16
/*      */     //   473: getstatic 415	io/dcloud/common/util/BaseInfo:ISDEBUG	Z
/*      */     //   476: ifne +15 -> 491
/*      */     //   479: aload_0
/*      */     //   480: getfield 381	io/dcloud/common/a/d:h	Ljava/lang/String;
/*      */     //   483: aload 14
/*      */     //   485: invokevirtual 635	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
/*      */     //   488: ifeq +169 -> 657
/*      */     //   491: aload_0
/*      */     //   492: getfield 383	io/dcloud/common/a/d:j	Ljava/lang/String;
/*      */     //   495: aload 16
/*      */     //   497: invokevirtual 634	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   500: ifeq +136 -> 636
/*      */     //   503: new 288	java/lang/StringBuilder
/*      */     //   506: dup
/*      */     //   507: invokespecial 652	java/lang/StringBuilder:<init>	()V
/*      */     //   510: aload 5
/*      */     //   512: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   515: getstatic 414	io/dcloud/common/util/BaseInfo:APP_WWW_FS_DIR	Ljava/lang/String;
/*      */     //   518: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   521: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   524: astore 8
/*      */     //   526: new 288	java/lang/StringBuilder
/*      */     //   529: dup
/*      */     //   530: invokespecial 652	java/lang/StringBuilder:<init>	()V
/*      */     //   533: aload 8
/*      */     //   535: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   538: getstatic 428	io/dcloud/common/util/BaseInfo:sConfigXML	Ljava/lang/String;
/*      */     //   541: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   544: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   547: astore 17
/*      */     //   549: aload 17
/*      */     //   551: invokestatic 544	io/dcloud/common/adapter/io/DHFile:isExist	(Ljava/lang/String;)Z
/*      */     //   554: ifeq +34 -> 588
/*      */     //   557: aload 17
/*      */     //   559: invokestatic 539	io/dcloud/common/adapter/io/DHFile:createFileHandler	(Ljava/lang/String;)Ljava/lang/Object;
/*      */     //   562: invokestatic 543	io/dcloud/common/adapter/io/DHFile:getInputStream	(Ljava/lang/Object;)Ljava/io/InputStream;
/*      */     //   565: astore 18
/*      */     //   567: aload_0
/*      */     //   568: aload 18
/*      */     //   570: aload_0
/*      */     //   571: getfield 381	io/dcloud/common/a/d:h	Ljava/lang/String;
/*      */     //   574: aload_2
/*      */     //   575: invokevirtual 482	io/dcloud/common/a/d:a	(Ljava/io/InputStream;Ljava/lang/String;Lorg/json/JSONObject;)Z
/*      */     //   578: istore 9
/*      */     //   580: aload 18
/*      */     //   582: invokestatic 588	io/dcloud/common/util/IOUtil:close	(Ljava/io/InputStream;)V
/*      */     //   585: goto +39 -> 624
/*      */     //   588: aload_0
/*      */     //   589: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   592: sipush -1225
/*      */     //   595: ldc 38
/*      */     //   597: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   600: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   603: aload_0
/*      */     //   604: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   607: iconst_1
/*      */     //   608: putfield 401	io/dcloud/common/a/f:a	Z
/*      */     //   611: iconst_0
/*      */     //   612: istore_3
/*      */     //   613: iload_3
/*      */     //   614: istore 18
/*      */     //   616: aload 12
/*      */     //   618: invokestatic 588	io/dcloud/common/util/IOUtil:close	(Ljava/io/InputStream;)V
/*      */     //   621: iload 18
/*      */     //   623: ireturn
/*      */     //   624: aload 13
/*      */     //   626: ldc 158
/*      */     //   628: invokestatic 608	io/dcloud/common/util/XmlUtil:getElement	(Lio/dcloud/common/util/XmlUtil$DHNode;Ljava/lang/String;)Lio/dcloud/common/util/XmlUtil$DHNode;
/*      */     //   631: astore 10
/*      */     //   633: goto +42 -> 675
/*      */     //   636: iconst_0
/*      */     //   637: istore 9
/*      */     //   639: aload_0
/*      */     //   640: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   643: sipush -1224
/*      */     //   646: ldc 37
/*      */     //   648: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   651: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   654: goto +21 -> 675
/*      */     //   657: iconst_0
/*      */     //   658: istore 9
/*      */     //   660: aload_0
/*      */     //   661: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   664: sipush -1223
/*      */     //   667: ldc 36
/*      */     //   669: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   672: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   675: aload 12
/*      */     //   677: invokestatic 588	io/dcloud/common/util/IOUtil:close	(Ljava/io/InputStream;)V
/*      */     //   680: goto +45 -> 725
/*      */     //   683: astore 13
/*      */     //   685: aload 13
/*      */     //   687: invokevirtual 617	java/io/IOException:printStackTrace	()V
/*      */     //   690: iconst_0
/*      */     //   691: istore 9
/*      */     //   693: aload_0
/*      */     //   694: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   697: bipush 251
/*      */     //   699: ldc 27
/*      */     //   701: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   704: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   707: aload 12
/*      */     //   709: invokestatic 588	io/dcloud/common/util/IOUtil:close	(Ljava/io/InputStream;)V
/*      */     //   712: goto +13 -> 725
/*      */     //   715: astore 19
/*      */     //   717: aload 12
/*      */     //   719: invokestatic 588	io/dcloud/common/util/IOUtil:close	(Ljava/io/InputStream;)V
/*      */     //   722: aload 19
/*      */     //   724: athrow
/*      */     //   725: iload 9
/*      */     //   727: ifeq +375 -> 1102
/*      */     //   730: iconst_0
/*      */     //   731: istore 11
/*      */     //   733: aload 6
/*      */     //   735: invokestatic 541	io/dcloud/common/adapter/io/DHFile:deleteFile	(Ljava/lang/String;)I
/*      */     //   738: pop
/*      */     //   739: aload_0
/*      */     //   740: getfield 358	io/dcloud/common/a/d:aj	B
/*      */     //   743: iconst_1
/*      */     //   744: if_icmpne +15 -> 759
/*      */     //   747: aload_0
/*      */     //   748: getfield 372	io/dcloud/common/a/d:ax	Ljava/lang/String;
/*      */     //   751: aload 6
/*      */     //   753: invokestatic 537	io/dcloud/common/adapter/io/DHFile:copyDir	(Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   756: goto +23 -> 779
/*      */     //   759: aload_0
/*      */     //   760: getfield 358	io/dcloud/common/a/d:aj	B
/*      */     //   763: ifne +16 -> 779
/*      */     //   766: aload 4
/*      */     //   768: aload 6
/*      */     //   770: iconst_1
/*      */     //   771: iconst_0
/*      */     //   772: invokestatic 538	io/dcloud/common/adapter/io/DHFile:copyFile	(Ljava/lang/String;Ljava/lang/String;ZZ)I
/*      */     //   775: pop
/*      */     //   776: iconst_1
/*      */     //   777: istore 11
/*      */     //   779: aload 10
/*      */     //   781: ldc 124
/*      */     //   783: invokestatic 609	io/dcloud/common/util/XmlUtil:getElements	(Lio/dcloud/common/util/XmlUtil$DHNode;Ljava/lang/String;)Ljava/util/ArrayList;
/*      */     //   786: astore 12
/*      */     //   788: aload 12
/*      */     //   790: ifnull +79 -> 869
/*      */     //   793: aload 12
/*      */     //   795: invokevirtual 668	java/util/ArrayList:isEmpty	()Z
/*      */     //   798: ifne +71 -> 869
/*      */     //   801: aload 12
/*      */     //   803: invokevirtual 670	java/util/ArrayList:size	()I
/*      */     //   806: istore 13
/*      */     //   808: iconst_0
/*      */     //   809: istore 14
/*      */     //   811: iload 14
/*      */     //   813: iload 13
/*      */     //   815: if_icmpge +54 -> 869
/*      */     //   818: aload 12
/*      */     //   820: iload 14
/*      */     //   822: invokevirtual 667	java/util/ArrayList:get	(I)Ljava/lang/Object;
/*      */     //   825: checkcast 274	io/dcloud/common/util/XmlUtil$DHNode
/*      */     //   828: astore 15
/*      */     //   830: aload 15
/*      */     //   832: ldc 149
/*      */     //   834: invokestatic 607	io/dcloud/common/util/XmlUtil:getAttributeValue	(Lio/dcloud/common/util/XmlUtil$DHNode;Ljava/lang/String;)Ljava/lang/String;
/*      */     //   837: astore 16
/*      */     //   839: new 288	java/lang/StringBuilder
/*      */     //   842: dup
/*      */     //   843: invokespecial 652	java/lang/StringBuilder:<init>	()V
/*      */     //   846: aload 6
/*      */     //   848: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   851: aload 16
/*      */     //   853: invokevirtual 657	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */     //   856: invokevirtual 659	java/lang/StringBuilder:toString	()Ljava/lang/String;
/*      */     //   859: invokestatic 541	io/dcloud/common/adapter/io/DHFile:deleteFile	(Ljava/lang/String;)I
/*      */     //   862: pop
/*      */     //   863: iinc 14 1
/*      */     //   866: goto -55 -> 811
/*      */     //   869: iconst_1
/*      */     //   870: aload 8
/*      */     //   872: aload 6
/*      */     //   874: iconst_1
/*      */     //   875: iconst_0
/*      */     //   876: invokestatic 538	io/dcloud/common/adapter/io/DHFile:copyFile	(Ljava/lang/String;Ljava/lang/String;ZZ)I
/*      */     //   879: if_icmpeq +33 -> 912
/*      */     //   882: aload 6
/*      */     //   884: invokestatic 541	io/dcloud/common/adapter/io/DHFile:deleteFile	(Ljava/lang/String;)I
/*      */     //   887: pop
/*      */     //   888: aload_0
/*      */     //   889: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   892: bipush 251
/*      */     //   894: ldc 27
/*      */     //   896: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   899: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   902: aload_0
/*      */     //   903: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   906: iconst_1
/*      */     //   907: putfield 401	io/dcloud/common/a/f:a	Z
/*      */     //   910: iconst_0
/*      */     //   911: ireturn
/*      */     //   912: aload 5
/*      */     //   914: invokestatic 541	io/dcloud/common/adapter/io/DHFile:deleteFile	(Ljava/lang/String;)I
/*      */     //   917: pop
/*      */     //   918: iload 11
/*      */     //   920: ifeq +38 -> 958
/*      */     //   923: aload 7
/*      */     //   925: aload 7
/*      */     //   927: iconst_0
/*      */     //   928: aload 7
/*      */     //   930: invokevirtual 639	java/lang/String:length	()I
/*      */     //   933: iconst_1
/*      */     //   934: isub
/*      */     //   935: invokevirtual 643	java/lang/String:substring	(II)Ljava/lang/String;
/*      */     //   938: bipush 47
/*      */     //   940: invokevirtual 638	java/lang/String:lastIndexOf	(I)I
/*      */     //   943: iconst_1
/*      */     //   944: iadd
/*      */     //   945: invokevirtual 642	java/lang/String:substring	(I)Ljava/lang/String;
/*      */     //   948: astore 13
/*      */     //   950: aload 4
/*      */     //   952: aload 13
/*      */     //   954: invokestatic 545	io/dcloud/common/adapter/io/DHFile:rename	(Ljava/lang/String;Ljava/lang/String;)I
/*      */     //   957: pop
/*      */     //   958: aload 6
/*      */     //   960: getstatic 414	io/dcloud/common/util/BaseInfo:APP_WWW_FS_DIR	Ljava/lang/String;
/*      */     //   963: invokestatic 545	io/dcloud/common/adapter/io/DHFile:rename	(Ljava/lang/String;Ljava/lang/String;)I
/*      */     //   966: pop
/*      */     //   967: iload 11
/*      */     //   969: ifeq +9 -> 978
/*      */     //   972: aload 7
/*      */     //   974: invokestatic 541	io/dcloud/common/adapter/io/DHFile:deleteFile	(Ljava/lang/String;)I
/*      */     //   977: pop
/*      */     //   978: goto +34 -> 1012
/*      */     //   981: astore 13
/*      */     //   983: aload 13
/*      */     //   985: invokevirtual 617	java/io/IOException:printStackTrace	()V
/*      */     //   988: aload_0
/*      */     //   989: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   992: bipush 251
/*      */     //   994: ldc 27
/*      */     //   996: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   999: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   1002: aload_0
/*      */     //   1003: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   1006: iconst_1
/*      */     //   1007: putfield 401	io/dcloud/common/a/f:a	Z
/*      */     //   1010: iconst_0
/*      */     //   1011: ireturn
/*      */     //   1012: aload_0
/*      */     //   1013: iconst_0
/*      */     //   1014: invokevirtual 476	io/dcloud/common/a/d:a	(B)V
/*      */     //   1017: aload_0
/*      */     //   1018: aload 4
/*      */     //   1020: invokevirtual 515	io/dcloud/common/a/d:setAppDataPath	(Ljava/lang/String;)V
/*      */     //   1023: aload_0
/*      */     //   1024: new 266	io/dcloud/common/util/BaseInfo$BaseAppInfo
/*      */     //   1027: dup
/*      */     //   1028: aload_0
/*      */     //   1029: getfield 381	io/dcloud/common/a/d:h	Ljava/lang/String;
/*      */     //   1032: aload_0
/*      */     //   1033: getfield 383	io/dcloud/common/a/d:j	Ljava/lang/String;
/*      */     //   1036: invokespecial 585	io/dcloud/common/util/BaseInfo$BaseAppInfo:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/*      */     //   1039: putfield 376	io/dcloud/common/a/d:c	Lio/dcloud/common/util/BaseInfo$BaseAppInfo;
/*      */     //   1042: aload_0
/*      */     //   1043: getfield 376	io/dcloud/common/a/d:c	Lio/dcloud/common/util/BaseInfo$BaseAppInfo;
/*      */     //   1046: invokevirtual 587	io/dcloud/common/util/BaseInfo$BaseAppInfo:saveToBundleData	()V
/*      */     //   1049: goto +50 -> 1099
/*      */     //   1052: astore 12
/*      */     //   1054: aload 12
/*      */     //   1056: invokevirtual 624	java/lang/Exception:printStackTrace	()V
/*      */     //   1059: aload 5
/*      */     //   1061: invokestatic 541	io/dcloud/common/adapter/io/DHFile:deleteFile	(Ljava/lang/String;)I
/*      */     //   1064: pop
/*      */     //   1065: goto +10 -> 1075
/*      */     //   1068: astore 13
/*      */     //   1070: aload 13
/*      */     //   1072: invokevirtual 617	java/io/IOException:printStackTrace	()V
/*      */     //   1075: aload_0
/*      */     //   1076: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   1079: bipush 157
/*      */     //   1081: ldc 201
/*      */     //   1083: invokestatic 573	io/dcloud/common/constant/DOMException:toJSON	(ILjava/lang/String;)Ljava/lang/String;
/*      */     //   1086: putfield 402	io/dcloud/common/a/f:b	Ljava/lang/String;
/*      */     //   1089: aload_0
/*      */     //   1090: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   1093: iconst_1
/*      */     //   1094: putfield 401	io/dcloud/common/a/f:a	Z
/*      */     //   1097: iconst_0
/*      */     //   1098: ireturn
/*      */     //   1099: goto +27 -> 1126
/*      */     //   1102: aload_0
/*      */     //   1103: getfield 375	io/dcloud/common/a/d:b	Lio/dcloud/common/a/f;
/*      */     //   1106: iconst_1
/*      */     //   1107: putfield 401	io/dcloud/common/a/f:a	Z
/*      */     //   1110: aload 5
/*      */     //   1112: invokestatic 541	io/dcloud/common/adapter/io/DHFile:deleteFile	(Ljava/lang/String;)I
/*      */     //   1115: pop
/*      */     //   1116: goto +10 -> 1126
/*      */     //   1119: astore 11
/*      */     //   1121: aload 11
/*      */     //   1123: invokevirtual 617	java/io/IOException:printStackTrace	()V
/*      */     //   1126: iload_3
/*      */     //   1127: ireturn
/*      */     //
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   194	207	210	java/io/FileNotFoundException
/*      */     //   194	207	253	java/lang/Exception
/*      */     //   305	367	398	java/lang/Exception
/*      */     //   375	395	398	java/lang/Exception
/*      */     //   194	245	683	java/io/IOException
/*      */     //   253	294	683	java/io/IOException
/*      */     //   302	367	683	java/io/IOException
/*      */     //   375	433	683	java/io/IOException
/*      */     //   441	616	683	java/io/IOException
/*      */     //   624	675	683	java/io/IOException
/*      */     //   194	245	715	finally
/*      */     //   253	294	715	finally
/*      */     //   302	367	715	finally
/*      */     //   375	433	715	finally
/*      */     //   441	616	715	finally
/*      */     //   624	675	715	finally
/*      */     //   683	707	715	finally
/*      */     //   715	717	715	finally
/*      */     //   912	978	981	java/io/IOException
/*      */     //   733	911	1052	java/lang/Exception
/*      */     //   912	1011	1052	java/lang/Exception
/*      */     //   1012	1049	1052	java/lang/Exception
/*      */     //   1059	1065	1068	java/io/IOException
/*      */     //   1110	1116	1119	java/io/IOException } 
/*      */   void a(InputStream paramInputStream) {  } 
/* 1941 */   boolean c(String paramString, JSONObject paramJSONObject) { boolean bool = false;
/* 1942 */     String str1 = obtainAppDataPath();
/* 1943 */     String str2 = str1.substring(0, str1.length() - 1) + "_backup";
/* 1944 */     byte b1 = this.aj;
/* 1945 */     String str3 = str1;
/*      */     try {
/* 1947 */       if (this.aj == 1) {
/* 1948 */         str1 = BaseInfo.sBaseFsAppsPath + this.h + File.separatorChar + BaseInfo.APP_WWW_FS_DIR;
/* 1949 */         DHFile.deleteFile(str1);
/* 1950 */         str2 = str1.substring(0, str1.length() - 1) + "_backup";
/* 1951 */       } else if (this.aj == 0) {
/* 1952 */         localFile1 = new File(str1);
/* 1953 */         File localFile2 = new File(str2);
/* 1954 */         localFile1.renameTo(localFile2);
/*      */       }
/* 1956 */       File localFile1 = new File(paramString);
/* 1957 */       ZipUtils.upZipFile(localFile1, str1);
/* 1958 */       a((byte)0);
/* 1959 */       setAppDataPath(str1);
/* 1960 */       bool = a(this.h, paramJSONObject);
/*      */     } catch (Exception localException) {
/* 1962 */       localException.printStackTrace();
/* 1963 */       this.b.a = true;
/* 1964 */       this.b.b = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(10), localException.getMessage() });
/*      */     }
/* 1966 */     if (!bool) {
/* 1967 */       Logger.e("appmgr", "unZipWebApp failed pFilePath=" + paramString);
/* 1968 */       a(b1);
/* 1969 */       setAppDataPath(str3);
/*      */       try {
/* 1971 */         DHFile.deleteFile(str1);
/*      */       } catch (IOException localIOException1) {
/* 1973 */         localIOException1.printStackTrace();
/*      */       }
/* 1975 */       if (b1 == 0)
/* 1976 */         new File(str2).renameTo(new File(str1));
/*      */     }
/*      */     else {
/* 1979 */       if (b1 == 0) {
/*      */         try {
/* 1981 */           DHFile.deleteFile(str2);
/*      */         } catch (IOException localIOException2) {
/* 1983 */           localIOException2.printStackTrace();
/*      */         }
/*      */       }
/* 1986 */       this.c = new BaseInfo.BaseAppInfo(this.h, this.j);
/* 1987 */       this.c.saveToBundleData();
/*      */     }
/* 1989 */     return bool;
/*      */   }
/*      */ 
/*      */   void l()
/*      */   {
/* 1996 */     if ((PdrUtil.isEmpty(this.ax)) || (!DeviceInfo.startsWithSdcard(this.ax))) {
/* 1997 */       setAppDataPath(BaseInfo.sBaseFsAppsPath + this.h + "/" + BaseInfo.REAL_PRIVATE_WWW_DIR);
/*      */     }
/*      */ 
/* 2000 */     if ((PdrUtil.isEmpty(this.aG)) || (!DeviceInfo.startsWithSdcard(this.aG)))
/*      */     {
/* 2002 */       setAppDocPath(BaseInfo.sBaseFsAppsPath + this.h + "/" + BaseInfo.REAL_PRIVATE_DOC_DIR);
/*      */     }
/* 2004 */     if ((PdrUtil.isEmpty(this.aH)) || (!DeviceInfo.startsWithSdcard(this.aH)))
/* 2005 */       this.aH = (BaseInfo.sBaseFsAppsPath + this.h + "/" + BaseInfo.APP_WEB_CHACHE);
/*      */   }
/*      */ 
/*      */   public String obtainAppWebCachePath()
/*      */   {
/* 2012 */     return this.aH;
/*      */   }
/*      */ 
/*      */   public String obtainAppDataPath()
/*      */   {
/* 2017 */     return this.h + "/www/";
/*      */   }
/*      */ 
/*      */   public void setAppDataPath(String paramString)
/*      */   {
/* 2025 */     if (this.aj == 1) {
/* 2026 */       if (paramString.startsWith(BaseInfo.sBaseResAppsPath))
/* 2027 */         this.ax = paramString;
/*      */       else
/* 2029 */         this.ax = (BaseInfo.sBaseResAppsPath + this.h + "/" + BaseInfo.APP_WWW_FS_DIR);
/*      */     }
/*      */     else
/* 2032 */       this.ax = PdrUtil.appendByDeviceRootDir(paramString);
/*      */   }
/*      */ 
/*      */   public String obtainAppDocPath()
/*      */   {
/* 2038 */     return this.aG;
/*      */   }
/*      */ 
/*      */   public void setAppDocPath(String paramString)
/*      */   {
/* 2043 */     this.aG = PdrUtil.appendByDeviceRootDir(paramString);
/*      */   }
/*      */ 
/*      */   public String obtainAppTempPath()
/*      */   {
/* 2048 */     return BaseInfo.sBaseFsAppsPath + this.h + "/temp/";
/*      */   }
/*      */ 
/*      */   public String obtainAppLog()
/*      */   {
/* 2053 */     return BaseInfo.sBaseFsAppsPath + this.h + "/log/";
/*      */   }
/*      */ 
/*      */   public InputStream obtainResInStream(String paramString1, String paramString2)
/*      */   {
/* 2058 */     InputStream localInputStream = null;
/* 2059 */     paramString2 = convert2AbsFullPath(paramString1, paramString2);
/* 2060 */     if (this.aj == 1) {
/* 2061 */       if (PdrUtil.isDeviceRootDir(paramString2))
/*      */         try {
/* 2063 */           localInputStream = DHFile.getInputStream(DHFile.createFileHandler(paramString2));
/*      */         } catch (IOException localIOException1) {
/* 2065 */           Logger.w("WebApp.obtainResInStream", localIOException1);
/*      */         }
/*      */       else
/* 2068 */         localInputStream = PlatformUtil.getResInputStream(paramString2);
/*      */     }
/* 2070 */     else if (this.aj == 0) {
/*      */       try {
/* 2072 */         localInputStream = DHFile.getInputStream(DHFile.createFileHandler(paramString2));
/*      */       } catch (IOException localIOException2) {
/* 2074 */         Logger.w("WebApp.obtainResInStream", localIOException2);
/*      */       }
/*      */     }
/* 2077 */     return localInputStream;
/*      */   }
/*      */ 
/*      */   public String convert2LocalFullPath(String paramString1, String paramString2)
/*      */   {
/* 2082 */     paramString2 = convert2AbsFullPath(paramString1, paramString2);
/* 2083 */     if (this.aj == 1) {
/* 2084 */       InputStream localInputStream = PlatformUtil.getResInputStream(paramString2);
/* 2085 */       if (localInputStream != null) {
/* 2086 */         String str = obtainAppTempPath() + System.currentTimeMillis();
/*      */         try {
/* 2088 */           DHFile.writeFile(localInputStream, str);
/* 2089 */           localInputStream.close();
/*      */         } catch (IOException localIOException) {
/* 2091 */           localIOException.printStackTrace();
/*      */         }
/* 2093 */         return str;
/*      */       }
/* 2095 */     } else if (this.aj == 0) {
/* 2096 */       return paramString2;
/*      */     }
/* 2098 */     return paramString2;
/*      */   }
/*      */ 
/*      */   public InputStream obtainResInStream(String paramString)
/*      */   {
/* 2103 */     return obtainResInStream(null, paramString);
/*      */   }
/*      */ 
/*      */   public byte obtainRunningAppMode()
/*      */   {
/* 2109 */     return this.aj;
/*      */   }
/*      */ 
/*      */   public byte obtainAppStatus()
/*      */   {
/* 2114 */     return this.d;
/*      */   }
/*      */ 
/*      */   public boolean isOnAppRunningMode()
/*      */   {
/* 2119 */     return this.aj == 1;
/*      */   }
/*      */ 
/*      */   public String obtainConfigProperty(String paramString) {
/* 2123 */     String str = null;
/* 2124 */     if (PdrUtil.isEquals(paramString, "autoclose"))
/* 2125 */       str = String.valueOf(this.am);
/* 2126 */     else if (PdrUtil.isEquals(paramString, "timeout"))
/* 2127 */       str = String.valueOf(this.ao);
/* 2128 */     else if (PdrUtil.isEquals(paramString, "delay"))
/* 2129 */       str = String.valueOf(this.ap);
/* 2130 */     else if (PdrUtil.isEquals(paramString, "splashscreen"))
/* 2131 */       str = String.valueOf(this.ak);
/* 2132 */     else if (PdrUtil.isEquals(paramString, "waiting"))
/* 2133 */       str = String.valueOf(this.al);
/* 2134 */     else if (PdrUtil.isEquals(paramString, "h5plus"))
/* 2135 */       str = String.valueOf(this.y);
/* 2136 */     else if (PdrUtil.isEquals(paramString, "useragent"))
/* 2137 */       str = this.s;
/* 2138 */     else if (PdrUtil.isEquals(paramString, "error"))
/* 2139 */       str = this.E;
/* 2140 */     else if (PdrUtil.isEquals(paramString, "fullscreen"))
/* 2141 */       str = String.valueOf(this.ag);
/* 2142 */     else if (PdrUtil.isEquals(paramString, "untrustedca"))
/* 2143 */       str = this.D;
/* 2144 */     else if (PdrUtil.isEquals(paramString, "loadedTime"))
/* 2145 */       str = this.R;
/* 2146 */     else if (PdrUtil.isEquals(paramString, "ramcachemode"))
/* 2147 */       str = this.az;
/* 2148 */     else if (PdrUtil.isEquals(paramString, "jserror"))
/* 2149 */       str = this.u + "";
/* 2150 */     else if (PdrUtil.isEquals(paramString, "crash"))
/* 2151 */       str = this.t + "";
/* 2152 */     else if (PdrUtil.isEquals(paramString, "use_encryption"))
/* 2153 */       str = this.aB + "";
/* 2154 */     else if (PdrUtil.isEquals(paramString, "w2a_delay"))
/* 2155 */       str = String.valueOf(this.aq);
/* 2156 */     else if (PdrUtil.isEquals(paramString, "w2a_autoclose"))
/* 2157 */       str = String.valueOf(this.an);
/* 2158 */     else if (PdrUtil.isEquals(paramString, "wap2app_running_mode"))
/* 2159 */       str = this.v + "";
/* 2160 */     else if (PdrUtil.isEquals(paramString, "injection"))
/* 2161 */       str = this.U + "";
/* 2162 */     else if (PdrUtil.isEquals(paramString, "event"))
/* 2163 */       str = this.ar;
/* 2164 */     else if (PdrUtil.isEquals(paramString, "target"))
/* 2165 */       str = this.as;
/* 2166 */     else if (PdrUtil.isEquals(paramString, "L_plusrequire"))
/* 2167 */       str = this.at;
/* 2168 */     else if (PdrUtil.isEquals(paramString, "S_pluserquire"))
/* 2169 */       str = this.au;
/* 2170 */     else if (PdrUtil.isEquals(paramString, "L_geolocation"))
/* 2171 */       str = this.av;
/* 2172 */     else if (PdrUtil.isEquals(paramString, "S_geolocation"))
/* 2173 */       str = this.aw;
/* 2174 */     else if (PdrUtil.isEquals(paramString, "StatusBarBackground")) {
/* 2175 */       str = this.aD + "";
/*      */     }
/* 2177 */     return str;
/*      */   }
/*      */ 
/*      */   public void setConfigProperty(String paramString1, String paramString2)
/*      */   {
/* 2182 */     if (PdrUtil.isEquals(paramString1, "autoclose"))
/* 2183 */       this.am = PdrUtil.parseBoolean(paramString2, this.am, false);
/* 2184 */     else if (PdrUtil.isEquals(paramString1, "timeout"))
/* 2185 */       this.ao = PdrUtil.parseInt(paramString2, this.ao);
/* 2186 */     else if (PdrUtil.isEquals(paramString1, "delay"))
/* 2187 */       this.ap = PdrUtil.parseInt(paramString2, this.ap);
/* 2188 */     else if (PdrUtil.isEquals(paramString1, "splashscreen"))
/* 2189 */       this.ak = PdrUtil.parseBoolean(paramString2, this.ak, false);
/* 2190 */     else if (PdrUtil.isEquals(paramString1, "waiting"))
/* 2191 */       this.al = PdrUtil.parseBoolean(paramString2, this.al, false);
/* 2192 */     else if (PdrUtil.isEquals(paramString1, "name"))
/* 2193 */       this.G = paramString2;
/* 2194 */     else if (PdrUtil.isEquals(paramString1, "name"))
/* 2195 */       this.n = paramString2;
/* 2196 */     else if (PdrUtil.isEquals(paramString1, "email"))
/* 2197 */       this.o = paramString2;
/* 2198 */     else if (PdrUtil.isEquals(paramString1, "url"))
/* 2199 */       this.q = paramString2;
/* 2200 */     else if (PdrUtil.isEquals(paramString1, "name"))
/* 2201 */       this.j = paramString2;
/* 2202 */     else if (PdrUtil.isEquals(paramString1, "liberate"))
/* 2203 */       this.A = PdrUtil.parseBoolean(paramString2, this.ak, false);
/* 2204 */     else if (PdrUtil.isEquals(paramString1, "h5plus"))
/* 2205 */       this.y = PdrUtil.parseBoolean(paramString2, true, false);
/* 2206 */     else if (PdrUtil.isEquals(paramString1, "useragent"))
/* 2207 */       this.s = paramString2;
/* 2208 */     else if (PdrUtil.isEquals(paramString1, "fullscreen"))
/* 2209 */       this.ag = PdrUtil.parseBoolean(paramString2, this.ag, false);
/* 2210 */     else if (PdrUtil.isEquals(paramString1, "webcache_path"))
/* 2211 */       this.aH = paramString2;
/* 2212 */     else if (PdrUtil.isEquals(paramString1, "wap2app_running_mode"))
/* 2213 */       this.v = PdrUtil.parseBoolean(paramString2, false, false);
/* 2214 */     else if (PdrUtil.isEquals(paramString1, "loadedTime"))
/* 2215 */       this.R = paramString2;
/* 2216 */     else if (PdrUtil.isEquals(paramString1, "StatusBarBackground"))
/* 2217 */       this.aD = Color.parseColor(paramString2);
/*      */   }
/*      */ 
/*      */   public String obtainWebviewBaseUrl()
/*      */   {
/* 2223 */     return b(this.aj);
/*      */   }
/*      */ 
/*      */   private String b(byte paramByte) {
/* 2227 */     String str = null;
/* 2228 */     if (this.aj == 1)
/* 2229 */       str = BaseInfo.sBaseResAppsFullPath + this.h + "/" + BaseInfo.APP_WWW_FS_DIR;
/* 2230 */     else if (this.aj == 0) {
/* 2231 */       str = "file://" + this.ax;
/*      */     }
/* 2233 */     return str;
/*      */   }
/*      */ 
/*      */   public void m() {
/* 2237 */     if (this.H != null) {
/* 2238 */       this.H.clear();
/* 2239 */       this.H = null;
/*      */     }
/* 2241 */     if (this.I != null) {
/* 2242 */       this.I.clear();
/* 2243 */       this.I = null;
/*      */     }
/* 2245 */     this.g = null;
/* 2246 */     this.c = null;
/*      */   }
/*      */ 
/*      */   public void applySmartUpdate()
/*      */   {
/* 2251 */     d();
/*      */   }
/*      */ 
/*      */   public ISmartUpdate startSmartUpdate()
/*      */   {
/* 2256 */     if (this.P == null) {
/* 2257 */       this.P = InvokeExecutorHelper.createWebAppSmartUpdater(this);
/*      */     }
/* 2259 */     this.P.checkUpdate();
/* 2260 */     return this.P;
/*      */   }
/*      */ 
/*      */   public boolean isJustDownload()
/*      */   {
/* 2266 */     return this.O != null ? this.O.getBooleanExtra("just_download", false) : false;
/*      */   }
/*      */ 
/*      */   public void smartUpdate()
/*      */   {
/* 2271 */     if (this.P != null) {
/* 2272 */       String str = PlatformUtil.getBundleData("pdr", this.h + "_smart_update_need_update");
/* 2273 */       if (!TextUtils.isEmpty(str)) {
/* 2274 */         Logger.d("will update " + str);
/*      */ 
/* 2276 */         ISmartUpdate.SmartUpdateCallbackParams localSmartUpdateCallbackParams = new ISmartUpdate.SmartUpdateCallbackParams(str);
/* 2277 */         this.P.update(localSmartUpdateCallbackParams);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void stopSmartUpdate()
/*      */   {
/* 2284 */     if (this.P != null);
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 2291 */     return this.G + "-" + this.h + "-" + super.toString();
/*      */   }
/*      */ 
/*      */   public void setWebAppIntent(Intent paramIntent)
/*      */   {
/* 2296 */     this.O = new Intent(paramIntent);
/* 2297 */     String str = this.O.getStringExtra("__first_web_url__");
/* 2298 */     if (!TextUtils.equals(this.F, "__no__"))
/* 2299 */       this.F = str;
/*      */   }
/*      */ 
/*      */   public void setWebAppActivity(Activity paramActivity)
/*      */   {
/* 2305 */     this.Z = paramActivity;
/* 2306 */     a(this.Z);
/*      */   }
/*      */ 
/*      */   public Intent obtainWebAppIntent()
/*      */   {
/* 2311 */     return this.O;
/*      */   }
/*      */ 
/*      */   public void showSplash()
/*      */   {
/* 2316 */     Activity localActivity = getActivity();
/* 2317 */     if ((localActivity instanceof IOnCreateSplashView)) {
/* 2318 */       IOnCreateSplashView localIOnCreateSplashView = (IOnCreateSplashView)localActivity;
/* 2319 */       localActivity.setIntent(this.O);
/* 2320 */       localIOnCreateSplashView.onCreateSplash(localActivity);
/*      */     }
/*      */   }
/*      */ 
/*      */   public JSONObject obtainThridInfo(IApp.ConfigProperty.ThridInfo paramThridInfo)
/*      */   {
/* 2326 */     switch (9.a[paramThridInfo.ordinal()]) {
/*      */     case 1:
/* 2328 */       return this.J;
/*      */     case 2:
/* 2330 */       return this.K;
/*      */     case 3:
/* 2332 */       return this.L;
/*      */     case 4:
/* 2334 */       return this.M;
/*      */     case 5:
/* 2336 */       return this.N;
/*      */     }
/*      */ 
/* 2340 */     return null;
/*      */   }
/*      */ 
/*      */   public String getPopGesture()
/*      */   {
/* 2345 */     return this.aA;
/*      */   }
/*      */ 
/*      */   public boolean n()
/*      */   {
/* 2366 */     return this.d == 3;
/*      */   }
/*      */ 
/*      */   private void v()
/*      */   {
/* 2377 */     this.X = new HashMap();
/* 2378 */     SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle(this.h + "_" + 1);
/* 2379 */     String str1 = this.Y = localSharedPreferences.getString("Authorize", null);
/* 2380 */     if (str1 != null) {
/* 2381 */       String[] arrayOfString1 = str1.split("&");
/* 2382 */       if ((arrayOfString1 != null) && (arrayOfString1.length > 0))
/* 2383 */         for (int i1 = 0; i1 < arrayOfString1.length; i1++) {
/* 2384 */           String str2 = arrayOfString1[i1];
/* 2385 */           if (!TextUtils.isEmpty(str2)) {
/* 2386 */             String[] arrayOfString2 = str2.split("=");
/* 2387 */             this.X.put(arrayOfString2[0], Integer.valueOf(Integer.parseInt(arrayOfString2[1])));
/*      */           }
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean a(String paramString1, String paramString2)
/*      */   {
/* 2403 */     return ((this.X.containsKey(paramString1 + "_*")) || (this.X.containsKey(paramString1 + "_" + paramString2))) && (((Integer)this.X.get(paramString1 + "_" + paramString2)).intValue() == 1);
/*      */   }
/*      */ 
/*      */   public void a(String paramString, int paramInt)
/*      */   {
/* 2414 */     this.X.put(paramString, Integer.valueOf(paramInt));
/* 2415 */     if (TextUtils.isEmpty(this.Y))
/* 2416 */       this.Y = (paramString + "=" + paramInt);
/*      */     else {
/* 2418 */       this.Y = (this.Y + "&" + paramString + "=" + paramInt);
/*      */     }
/*      */ 
/* 2421 */     SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle(this.h + "_" + 1);
/* 2422 */     localSharedPreferences.edit().putString("Authorize", this.Y).commit();
/*      */   }
/*      */ 
/*      */   private void a(JSONObject paramJSONObject)
/*      */   {
/* 2431 */     if ((!this.aB) || (b.a().a(this.h) != null))
/*      */     {
/* 2433 */       return;
/*      */     }
/* 2435 */     JSONObject localJSONObject1 = JSONUtil.getJSONObject(paramJSONObject, "confusion");
/*      */ 
/* 2437 */     if ((localJSONObject1 != null) && (localJSONObject1.has("resources"))) {
/* 2438 */       JSONObject localJSONObject2 = JSONUtil.getJSONObject(localJSONObject1, "resources");
/* 2439 */       if (localJSONObject2 != null) {
/* 2440 */         HashMap localHashMap = new HashMap();
/* 2441 */         Iterator localIterator = localJSONObject2.keys();
/* 2442 */         while (localIterator.hasNext()) {
/* 2443 */           String str1 = (String)localIterator.next();
/* 2444 */           if (localJSONObject2.has(str1)) {
/*      */             try
/*      */             {
/* 2447 */               JSONObject localJSONObject3 = localJSONObject2.getJSONObject(str1);
/*      */ 
/* 2449 */               if ((localJSONObject3 != null) && (localJSONObject3.has("algorithm")) && (localJSONObject3.has("key")))
/*      */               {
/* 2454 */                 String str2 = localJSONObject3.getString("algorithm");
/*      */ 
/* 2456 */                 String str3 = localJSONObject3.getString("key");
/*      */ 
/* 2458 */                 localHashMap.put(str1, str3);
/*      */               }
/*      */             }
/*      */             catch (JSONException localJSONException) {
/* 2462 */               localJSONException.printStackTrace();
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/* 2467 */         if (localHashMap.size() > 0)
/*      */         {
/* 2469 */           b.a().a(this.h, localHashMap);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private String a(byte[] paramArrayOfByte)
/*      */   {
/* 2482 */     String str1 = null;
/* 2483 */     if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 8))
/*      */     {
/* 2485 */       if ((paramArrayOfByte[0] == 5) && (paramArrayOfByte[1] == 15) && (paramArrayOfByte[2] == 53) && (paramArrayOfByte[3] == 43))
/*      */       {
/* 2487 */         this.aB = true;
/*      */ 
/* 2489 */         String str2 = Integer.toHexString(paramArrayOfByte[5] & 0xFF);
/* 2490 */         String str3 = Integer.toHexString(paramArrayOfByte[6] & 0xFF);
/* 2491 */         int i1 = Integer.parseInt(str2, 16);
/* 2492 */         int i2 = Integer.parseInt(str3, 16);
/*      */ 
/* 2494 */         int i3 = (i1 << 8) + i2;
/*      */ 
/* 2497 */         byte[] arrayOfByte1 = new byte[i3];
/* 2498 */         for (int i4 = 0; i4 < i3; i4++) {
/* 2499 */           arrayOfByte1[i4] = paramArrayOfByte[(i4 + 8)];
/*      */         }
/*      */ 
/*      */         try
/*      */         {
/* 2510 */           String str4 = io.dcloud.a.a.a(this.g.getContext());
/*      */ 
/* 2517 */           PublicKey localPublicKey = (PublicKey)InvokeExecutorHelper.RSAUtils.invoke("loadPublicKey", new Class[] { String.class }, new Object[] { str4 });
/*      */ 
/* 2526 */           byte[] arrayOfByte2 = (byte[])InvokeExecutorHelper.RSAUtils.invoke("decryptDataWithPublicKey", new Class[] { [B.class, PublicKey.class }, new Object[] { arrayOfByte1, localPublicKey });
/*      */ 
/* 2531 */           String str5 = new String(arrayOfByte2);
/*      */ 
/* 2536 */           byte[] arrayOfByte3 = new byte[paramArrayOfByte.length - i3 - 8];
/* 2537 */           for (int i5 = 0; i5 < paramArrayOfByte.length - i3 - 8; i5++) {
/* 2538 */             arrayOfByte3[i5] = paramArrayOfByte[(i5 + i3 + 8)];
/*      */           }
/*      */ 
/* 2541 */           String str6 = (String)InvokeExecutorHelper.AesEncryptionUtil.invoke("byte2hex", new Class[] { [B.class }, new Object[] { arrayOfByte3 });
/*      */ 
/* 2543 */           str1 = (String)InvokeExecutorHelper.AesEncryptionUtil.invoke("decrypt", new Class[] { String.class, String.class, String.class }, new Object[] { str6, str5, io.dcloud.a.a.a() });
/*      */         }
/*      */         catch (Exception localException)
/*      */         {
/* 2548 */           localException.printStackTrace();
/*      */         }
/*      */       } else {
/* 2551 */         this.aB = false;
/*      */       }
/*      */     }
/* 2554 */     return str1;
/*      */   }
/*      */ 
/*      */   public boolean isCompetentStreamApp() {
/* 2558 */     return isStreamApp() ? this.w : true;
/*      */   }
/*      */ 
/*      */   public boolean isStreamApp()
/*      */   {
/* 2563 */     boolean bool = false;
/* 2564 */     Intent localIntent = obtainWebAppIntent();
/* 2565 */     if ((localIntent != null) && (localIntent.hasExtra("is_stream_app"))) {
/* 2566 */       bool = localIntent.getBooleanExtra("is_stream_app", bool);
/*      */     }
/*      */ 
/* 2569 */     return bool;
/*      */   }
/*      */ 
/*      */   public String obtainOriginalAppId()
/*      */   {
/* 2575 */     return this.i;
/*      */   }
/*      */ 
/*      */   public float o() {
/* 2579 */     if ((io.dcloud.common.b.a.a.a(this.h, "Device".toLowerCase())) && (null != getActivity())) {
/* 2580 */       int i1 = NetworkTypeUtil.getNetworkType(getActivity());
/* 2581 */       if (i1 == 4) {
/* 2582 */         return 1000.0F;
/*      */       }
/*      */     }
/* 2585 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   public Object obtainMgrData(IMgr.MgrType paramMgrType, int paramInt, Object[] paramArrayOfObject)
/*      */   {
/* 2590 */     return this.g.processEvent(paramMgrType, paramInt, paramArrayOfObject);
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.a.d
 * JD-Core Version:    0.6.2
 */