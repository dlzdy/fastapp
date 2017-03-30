/*     */ package io.dcloud.feature;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.Dialog;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.os.Build.VERSION;
/*     */ import android.text.TextUtils;
/*     */ import android.util.Log;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.BaseFeature;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IBoot;
/*     */ import io.dcloud.common.DHInterface.ICore;
/*     */ import io.dcloud.common.DHInterface.IFeature;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IMgr.FeatureEvent;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IWaiter;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.io.DHFile;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.adapter.util.SP;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.DataUtil;
/*     */ import io.dcloud.common.util.ErrorDialogUtil;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.feature.internal.sdk.SDK.IntegratedMode;
/*     */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ 
/*     */ public class b extends AbsMgr
/*     */   implements IMgr.FeatureEvent
/*     */ {
/*  64 */   private HashMap<String, HashMap<String, String>> c = null;
/*     */ 
/*  66 */   private HashMap<String, String> d = null;
/*     */ 
/*  68 */   private HashMap<String, IFeature> e = null;
/*     */ 
/*  70 */   private HashMap<String, String> f = null;
/*  71 */   private HashMap<String, IBoot> g = null;
/*  72 */   private String h = null;
/*     */ 
/*  74 */   private ArrayList<String> i = new ArrayList();
/*     */ 
/* 178 */   HashMap<String, String> a = new HashMap();
/*     */ 
/* 460 */   c b = null;
/*     */ 
/*     */   public b(ICore paramICore)
/*     */   {
/*  76 */     super(paramICore, "featuremgr", IMgr.MgrType.FeatureMgr);
/*  77 */     c();
/*     */     try
/*     */     {
/*  80 */       if ((BaseInfo.ISDEBUG) && (DHFile.isExist("/sdcard/dcloud/all.js")))
/*  81 */         this.h = new String(PlatformUtil.getFileContent("/sdcard/dcloud/all.js", 2));
/*     */       else
/*  83 */         this.h = new String(PlatformUtil.getFileContent(DCloudAdapterUtil.getRuntimeJsPath(), 1));
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*  87 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Object processEvent(IMgr.MgrType paramMgrType, int paramInt, Object paramObject)
/*     */   {
/*  93 */     Object localObject1 = null;
/*     */     try {
/*  95 */       if (!checkMgrId(paramMgrType)) {
/*  96 */         localObject1 = this.mCore.dispatchEvent(paramMgrType, paramInt, paramObject);
/*     */       }
/*     */       else
/*     */       {
/*     */         Object localObject2;
/*     */         Object localObject3;
/*     */         Object localObject4;
/*     */         Object localObject5;
/*  98 */         switch (paramInt) {
/*     */         case 7:
/* 100 */           break;
/*     */         case 6:
/* 103 */           break;
/*     */         case 5:
/* 106 */           localObject2 = (String[])paramObject;
/* 107 */           localObject3 = localObject2[0];
/* 108 */           localObject4 = localObject2[1];
/* 109 */           localObject5 = localObject2[2];
/* 110 */           if ((!PdrUtil.isEmpty(localObject3)) && (!PdrUtil.isEmpty(localObject4))) {
/* 111 */             this.d.put(((String)localObject3).toLowerCase(), localObject4);
/*     */           }
/* 113 */           if (!PdrUtil.isEmpty(localObject5))
/* 114 */             this.i.add(localObject5); break;
/*     */         case 0:
/* 119 */           this.e = new HashMap(1);
/* 120 */           this.d = new HashMap(1);
/* 121 */           this.c = new HashMap(1);
/* 122 */           this.g = new HashMap();
/* 123 */           this.f = new HashMap(1);
/* 124 */           localObject1 = a();
/* 125 */           break;
/*     */         case 4:
/* 127 */           localObject2 = String.valueOf(paramObject);
/* 128 */           localObject1 = this.c.get(localObject2);
/* 129 */           break;
/*     */         case 1:
/* 132 */           localObject2 = (Object[])paramObject;
/* 133 */           localObject1 = a((Object[])localObject2);
/* 134 */           break;
/*     */         case 2:
/* 136 */           localObject3 = (Object[])paramObject;
/* 137 */           localObject4 = (IApp)localObject3[0];
/* 138 */           localObject5 = (IFrameView)localObject3[1];
/* 139 */           localObject1 = a((IApp)localObject4, (IFrameView)localObject5);
/* 140 */           break;
/*     */         case 8:
/* 143 */           localObject3 = (String[])paramObject;
/* 144 */           localObject1 = Boolean.valueOf(io.dcloud.common.b.a.a.a(localObject3[0], localObject3[1]));
/* 145 */           break;
/*     */         case 3:
/* 148 */           a(String.valueOf(paramObject));
/* 149 */           break;
/*     */         case 9:
/* 151 */           localObject1 = Boolean.valueOf(b(String.valueOf(paramObject)));
/* 152 */           break;
/*     */         case 10:
/*     */           try {
/* 155 */             localObject3 = (Object[])paramObject;
/* 156 */             localObject4 = (IWebview)localObject3[0];
/* 157 */             localObject5 = String.valueOf(localObject3[1]);
/* 158 */             IFeature localIFeature = a((String)localObject5, ((IWebview)localObject4).getActivity());
/* 159 */             if ((localIFeature instanceof IWaiter)) {
/* 160 */               String str = String.valueOf(localObject3[2]);
/* 161 */               localObject1 = ((IWaiter)localIFeature).doForFeature(str, localObject3[3]);
/*     */             }
/*     */           } catch (Exception localException) {
/* 164 */             localException.printStackTrace();
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Throwable localThrowable)
/*     */     {
/* 173 */       Logger.w("FeatureMgr.processEvent", localThrowable);
/*     */     }
/* 175 */     return localObject1;
/*     */   }
/*     */ 
/*     */   private String a(IApp paramIApp, IFrameView paramIFrameView)
/*     */   {
/* 180 */     String str = (String)this.a.get(paramIApp.obtainAppId());
/* 181 */     if (str == null) {
/* 182 */       str = a(paramIApp, paramIFrameView.obtainWebView());
/* 183 */       if (Build.VERSION.SDK_INT > 19)
/*     */       {
/* 185 */         this.a.put(paramIApp.obtainAppId(), str);
/*     */       }
/*     */     }
/* 188 */     return str;
/*     */   }
/*     */ 
/*     */   public String a(IWebview paramIWebview) {
/* 192 */     StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 194 */     String str = paramIWebview.getWebviewUUID();
/* 195 */     if (PdrUtil.isEmpty(str)) {
/* 196 */       str = String.valueOf(paramIWebview.obtainFrameView().hashCode());
/*     */     }
/* 198 */     localStringBuffer.append("window.__HtMl_Id__= '" + str + "';");
/* 199 */     if (PdrUtil.isEmpty(paramIWebview.obtainFrameId()))
/* 200 */       localStringBuffer.append("window.__WebVieW_Id__= undefined;");
/*     */     else {
/* 202 */       localStringBuffer.append("window.__WebVieW_Id__= '" + paramIWebview.obtainFrameId() + "';");
/*     */     }
/* 204 */     localStringBuffer.append("try{window.plus.__tag__='_plus_all_'}catch(e){}");
/* 205 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   private String a(IApp paramIApp, IWebview paramIWebview) {
/* 209 */     String str1 = "__load__plus__";
/* 210 */     StringBuffer localStringBuffer = new StringBuffer("javascript:");
/* 211 */     localStringBuffer.append("function ").append(str1).append("(){try{");
/* 212 */     if (Build.VERSION.SDK_INT <= 19) {
/* 213 */       localStringBuffer.append(a(paramIWebview));
/*     */     }
/* 215 */     localStringBuffer.append("window._____isDebug_____=" + BaseInfo.ISDEBUG + ";");
/* 216 */     localStringBuffer.append("window._____platform_____=1;");
/* 217 */     localStringBuffer.append("window._____platform_os_version_____=" + Build.VERSION.SDK_INT + ";");
/* 218 */     localStringBuffer.append(this.h);
/* 219 */     if (io.dcloud.common.b.a.a.a(paramIApp.obtainAppId(), "Device".toLowerCase())) {
/* 220 */       if (PdrUtil.isEmpty(DeviceInfo.DEVICESTATUS_JS)) {
/*     */         try {
/* 222 */           DeviceInfo.initGsmCdmaCell();
/*     */         } catch (SecurityException localSecurityException) {
/* 224 */           localSecurityException.printStackTrace();
/*     */         }
/* 226 */         DeviceInfo.getDevicestatus_js();
/*     */       }
/* 228 */       localStringBuffer.append(DeviceInfo.DEVICESTATUS_JS);
/*     */     }
/*     */ 
/* 231 */     SDK.IntegratedMode localIntegratedMode = BaseInfo.sRuntimeMode;
/* 232 */     localStringBuffer.append("window.__NWin_Enable__=" + (localIntegratedMode == SDK.IntegratedMode.WEBVIEW ? String.valueOf(false) : String.valueOf(true)) + ";");
/*     */ 
/* 242 */     if (io.dcloud.common.b.a.a.a(paramIApp.obtainAppId(), "Runtime")) {
/* 243 */       String str2 = paramIApp.obtainConfigProperty("loadedTime");
/* 244 */       String str3 = null;
/* 245 */       if ((paramIApp.isStreamApp()) || (BaseInfo.ISAMU))
/* 246 */         str3 = String.format("if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){var p = ((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus);p.runtime.version = '%s';p.runtime.innerVersion = '%s';p.runtime.appid='%s';                                             p.runtime.launchLoadedTime = '%s';}", new Object[] { paramIApp.obtainAppVersionName(), BaseInfo.sBaseVersion, paramIApp.obtainAppId(), str2 });
/*     */       else {
/* 248 */         str3 = String.format("if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus)){var p = ((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus);p.runtime.version = '%s';p.runtime.innerVersion = '%s';p.runtime.appid='%s';                                             p.runtime.launchLoadedTime = '%s';}", new Object[] { AndroidResources.mApplicationInfo.versionName, BaseInfo.sBaseVersion, paramIApp.obtainAppId(), str2 });
/*     */       }
/* 250 */       localStringBuffer.append(str3);
/* 251 */       String str4 = String.format("try{if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime){((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime.arguments = %s;}}catch(e){};", new Object[] { DataUtil.utf8ToUnicode(paramIApp.obtainRuntimeArgs()) });
/* 252 */       localStringBuffer.append(str4);
/* 253 */       String str5 = BaseInfo.getLauncherData(paramIApp.obtainAppId());
/* 254 */       String str6 = paramIApp.obtainWebAppIntent().getStringExtra("__from_stream_open_flag__");
/* 255 */       if ((!TextUtils.isEmpty(str6)) && (!TextUtils.equals(str6, paramIApp.obtainAppId()))) {
/* 256 */         str5 = str5 + ":" + str6;
/*     */       }
/* 258 */       String str7 = String.format("if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime){((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime.launcher = '%s';}", new Object[] { str5 });
/* 259 */       localStringBuffer.append(str7);
/* 260 */       String str8 = String.format("if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime){((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime.channel = '%s';}", new Object[] { BaseInfo.sChannel });
/* 261 */       localStringBuffer.append(str8);
/* 262 */       String str9 = SP.getBundleData("pdr", paramIApp.obtainAppId() + "LAUNCHTYPE");
/* 263 */       if (TextUtils.isEmpty(str9)) {
/* 264 */         str9 = "default";
/*     */       }
/* 266 */       String str10 = String.format("if(((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime){((window.__html5plus__&&__html5plus__.isReady)?__html5plus__:(navigator.plus&&navigator.plus.isReady)?navigator.plus:window.plus).runtime.origin = '%s';}", new Object[] { str9 });
/* 267 */       localStringBuffer.append(str10);
/*     */     }
/* 269 */     int j = this.i.size();
/* 270 */     for (int k = 0; k < j; k++) {
/* 271 */       localStringBuffer.append((String)this.i.get(k));
/*     */     }
/* 273 */     localStringBuffer.append(b());
/* 274 */     localStringBuffer.append("}catch(e){console.log('__load__plus__ function error=' + e);}}").append("window.__load__plus__&&window.__load__plus__();");
/*     */ 
/* 276 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   private String b() {
/* 280 */     StringBuffer localStringBuffer = new StringBuffer();
/* 281 */     localStringBuffer.append(";Object.defineProperty(plus.screen,\"resolutionHeight\",{get:function(){var e=window,l=e.__html5plus__&&e.__html5plus__.isReady?e.__html5plus__:n.plus&&n.plus.isReady?n.plus:window.plus;return l.bridge.execSync(\"Device\",\"s.resolutionHeight\",[])}}),Object.defineProperty(plus.screen,\"resolutionWidth\",{get:function(){var e=window,l=e.__html5plus__&&e.__html5plus__.isReady?e.__html5plus__:n.plus&&n.plus.isReady?n.plus:window.plus;return l.bridge.execSync(\"Device\",\"s.resolutionWidth\",[])}}),Object.defineProperty(plus.display,\"resolutionHeight\",{get:function(){var e=window,l=e.__html5plus__&&e.__html5plus__.isReady?e.__html5plus__:n.plus&&n.plus.isReady?n.plus:window.plus;return l.bridge.execSync(\"Device\",\"d.resolutionHeight\",[])}}),Object.defineProperty(plus.display,\"resolutionWidth\",{get:function(){var e=window,l=e.__html5plus__&&e.__html5plus__.isReady?e.__html5plus__:n.plus&&n.plus.isReady?n.plus:window.plus;return l.bridge.execSync(\"Device\",\"d.resolutionWidth\",[])}});");
/* 282 */     localStringBuffer.append(";plus.webview.__test__('save');");
/* 283 */     if (Build.VERSION.SDK_INT > 19)
/*     */     {
/* 285 */       localStringBuffer.append("plus.webview.__test__('update');");
/*     */     }
/* 287 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   private synchronized String a(Object[] paramArrayOfObject)
/*     */   {
/* 300 */     IWebview localIWebview = (IWebview)paramArrayOfObject[0];
/* 301 */     String str1 = String.valueOf(paramArrayOfObject[1]).toLowerCase();
/* 302 */     String str2 = String.valueOf(paramArrayOfObject[2]);
/* 303 */     JSONArray localJSONArray1 = (JSONArray)paramArrayOfObject[3];
/* 304 */     IFeature localIFeature = a(str1, localIWebview.getActivity());
/*     */     Object localObject;
/* 305 */     if (localIFeature != null) {
/* 306 */       if (((localIFeature instanceof BaseFeature)) && (!((BaseFeature)localIFeature).isOldMode())) {
/* 307 */         localObject = localJSONArray1;
/* 308 */         return ((BaseFeature)localIFeature).execute(localIWebview, str2, (JSONArray)localObject);
/*     */       }
/* 310 */       localObject = null;
/*     */ 
/* 314 */       JSONArray localJSONArray2 = localJSONArray1;
/* 315 */       if (localJSONArray2 != null) {
/*     */         try {
/* 317 */           localObject = JSUtil.jsonArrayToStringArr(localJSONArray2);
/*     */         } catch (JSONException localJSONException) {
/* 319 */           localJSONException.printStackTrace();
/*     */         }
/*     */       }
/*     */ 
/* 323 */       return localIFeature.execute(localIWebview, str2, (String[])localObject);
/*     */     }
/* 325 */     if ((localIWebview.obtainFrameView().getFrameType() != 3) && (!io.dcloud.common.b.a.a.b(localIWebview.obtainApp().obtainAppId(), str1)))
/*     */     {
/* 327 */       if (!localIWebview.obtainApp().isStreamApp())
/*     */       {
/* 329 */         localObject = "请检查assets/data/properties.xml文件是否添加" + str1 + "相关节点。";
/* 330 */         Logger.e("featuremgr", "not found " + str1 + " feature plugin ; action=" + str2 + ";" + (String)localObject);
/*     */ 
/* 335 */         a(localIWebview, str1);
/* 336 */         return "";
/*     */       }
/*     */     }
/* 338 */     return "";
/*     */   }
/*     */ 
/*     */   IFeature a(String paramString, Activity paramActivity)
/*     */   {
/* 348 */     Object localObject = (IFeature)this.e.get(paramString);
/* 349 */     if (localObject == null)
/*     */     {
/* 351 */       String str = (String)this.d.get(paramString);
/* 352 */       if ((str != null) && (this.g.containsKey(paramString))) {
/* 353 */         IBoot localIBoot = (IBoot)this.g.get(paramString);
/* 354 */         if (((localIBoot instanceof BaseFeature)) && (str.equals(localIBoot.getClass().getName()))) {
/* 355 */           localObject = (BaseFeature)localIBoot;
/*     */         }
/*     */       }
/* 358 */       if (localObject == null) {
/* 359 */         if (str != null)
/*     */           try {
/* 361 */             localObject = a.b(this, paramString);
/* 362 */             if (localObject == null) {
/* 363 */               localObject = (IFeature)Class.forName(str).newInstance();
/* 364 */               this.e.put(paramString, localObject);
/* 365 */               ((IFeature)localObject).init(this, paramString);
/*     */             } else {
/* 367 */               this.e.put(paramString, localObject);
/*     */             }
/*     */           } catch (InstantiationException localInstantiationException) {
/* 370 */             localInstantiationException.printStackTrace();
/*     */           } catch (IllegalAccessException localIllegalAccessException) {
/* 372 */             localIllegalAccessException.printStackTrace();
/*     */           } catch (ClassNotFoundException localClassNotFoundException) {
/* 374 */             localClassNotFoundException.printStackTrace();
/*     */             try {
/* 376 */               localObject = (IFeature)b(paramString, paramActivity);
/* 377 */               if (localObject != null) {
/* 378 */                 this.e.put(paramString, localObject);
/* 379 */                 ((IFeature)localObject).init(this, paramString);
/*     */               }
/*     */             } catch (Exception localException2) {
/* 382 */               localException2.printStackTrace();
/*     */             }
/*     */           }
/*     */         else
/*     */           try {
/* 387 */             localObject = (IFeature)b(paramString, paramActivity);
/* 388 */             if (localObject != null) {
/* 389 */               this.e.put(paramString, localObject);
/* 390 */               ((IFeature)localObject).init(this, paramString);
/*     */             }
/*     */           } catch (Exception localException1) {
/* 393 */             localException1.printStackTrace();
/*     */           }
/*     */       }
/*     */       else {
/* 397 */         this.e.put(paramString, localObject);
/*     */       }
/*     */     }
/* 400 */     return localObject;
/*     */   }
/*     */ 
/*     */   HashMap<String, IBoot> a()
/*     */   {
/* 410 */     HashMap localHashMap1 = this.g;
/* 411 */     HashMap localHashMap2 = this.f;
/* 412 */     PdrUtil.loadProperties2HashMap(localHashMap2, this.d, this.c, BaseInfo.s_properties);
/* 413 */     Set localSet = localHashMap2.keySet();
/* 414 */     if (localSet != null) {
/* 415 */       this.g = (localHashMap1 = new HashMap(2));
/* 416 */       for (localObject1 = localSet.iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (String)((Iterator)localObject1).next();
/* 417 */         IBoot localIBoot = a.a(this, (String)localObject2);
/* 418 */         if (localIBoot == null) {
/* 419 */           String str = (String)localHashMap2.get(localObject2);
/*     */           try {
/* 421 */             localIBoot = (IBoot)Class.forName(str).newInstance();
/* 422 */             if ((localIBoot instanceof BaseFeature)) ((BaseFeature)localIBoot).init(this, (String)localObject2); 
/*     */           }
/* 424 */           catch (InstantiationException localInstantiationException) { Log.d("Main_Path", "FeatureMgr.loadBootOptions " + localInstantiationException.getMessage());
/*     */           } catch (IllegalAccessException localIllegalAccessException) {
/* 426 */             Log.d("Main_Path", "FeatureMgr.loadBootOptions " + localIllegalAccessException.getMessage());
/*     */           } catch (ClassNotFoundException localClassNotFoundException) {
/* 428 */             localClassNotFoundException.printStackTrace();
/*     */           }
/*     */         }
/* 431 */         if (localIBoot != null) {
/* 432 */           if ((localIBoot instanceof BaseFeature)) {
/* 433 */             ((BaseFeature)localIBoot).init(this, (String)localObject2);
/*     */           }
/* 435 */           localHashMap1.put(localObject2, localIBoot);
/*     */         }
/*     */       }
/*     */     }
/* 439 */     localHashMap1 = a(localHashMap1);
/* 440 */     a(this.d.values().iterator());
/* 441 */     Object localObject1 = this.c.values();
/* 442 */     Object localObject2 = ((Collection)localObject1).iterator();
/* 443 */     while (((Iterator)localObject2).hasNext()) {
/* 444 */       a(((HashMap)((Iterator)localObject2).next()).values().iterator());
/*     */     }
/*     */ 
/* 447 */     return localHashMap1;
/*     */   }
/*     */ 
/*     */   private void c() {
/*     */     try {
/* 452 */       String str = "io.dcloud.feature.d";
/* 453 */       Class localClass = Class.forName(str);
/* 454 */       Constructor localConstructor = localClass.getConstructor(new Class[] { Context.class });
/* 455 */       this.b = ((c)localConstructor.newInstance(new Object[] { getContext() }));
/*     */     } catch (Exception localException) {
/* 457 */       Logger.e("fmgr no dp");
/*     */     }
/*     */   }
/*     */ 
/*     */   private Object b(String paramString, Activity paramActivity) {
/* 462 */     return this.b != null ? this.b.a(paramString, paramActivity) : null;
/*     */   }
/*     */ 
/*     */   private HashMap<String, IBoot> a(HashMap<String, IBoot> paramHashMap) {
/* 466 */     if (this.b == null) return paramHashMap;
/* 467 */     if (paramHashMap == null) {
/* 468 */       paramHashMap = new HashMap();
/*     */     }
/* 470 */     HashMap localHashMap = this.b.a();
/* 471 */     Set localSet = localHashMap.keySet();
/* 472 */     if (localSet != null) {
/* 473 */       for (String str : localSet) {
/* 474 */         Object localObject = this.b.a(str);
/* 475 */         if (localObject != null) {
/* 476 */           if ((localObject instanceof BaseFeature)) {
/* 477 */             ((BaseFeature)localObject).init(this, str);
/*     */           }
/* 479 */           paramHashMap.put(str, (IBoot)localObject);
/*     */         }
/*     */       }
/*     */     }
/* 483 */     return paramHashMap;
/*     */   }
/*     */   private void a(Iterator<String> paramIterator) {
/* 486 */     while (paramIterator.hasNext()) {
/* 487 */       String str1 = (String)paramIterator.next();
/* 488 */       String str2 = String.valueOf(PlatformUtil.invokeMethod(str1, "getJsContent"));
/* 489 */       if (!PdrUtil.isEmpty(str2))
/* 490 */         this.i.add(str2);
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean b(String paramString)
/*     */   {
/* 496 */     return this.d.containsKey(paramString);
/*     */   }
/*     */ 
/*     */   public void a(String paramString) {
/* 500 */     if (this.e != null) {
/* 501 */       Collection localCollection = this.e.values();
/* 502 */       if (localCollection != null) {
/* 503 */         for (IFeature localIFeature : localCollection) {
/* 504 */           localIFeature.dispose(paramString);
/*     */         }
/*     */       }
/*     */     }
/* 508 */     if (this.a != null)
/* 509 */       this.a.remove(paramString);
/*     */   }
/*     */ 
/*     */   public void dispose()
/*     */   {
/* 514 */     if (this.e != null) {
/* 515 */       Collection localCollection = this.e.values();
/* 516 */       if (localCollection != null) {
/* 517 */         for (IFeature localIFeature : localCollection) {
/* 518 */           localIFeature.dispose(null);
/*     */         }
/*     */       }
/* 521 */       this.e.clear();
/* 522 */       this.e = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void a(IWebview paramIWebview, String paramString) {
/* 527 */     String str = String.format("打包时未添加%s模块, 请参考 http://ask.dcloud.net.cn/article/283", new Object[] { paramString });
/* 528 */     Dialog localDialog = ErrorDialogUtil.getLossDialog(paramIWebview, str, "http://ask.dcloud.net.cn/article/283", paramString);
/* 529 */     if (localDialog != null)
/* 530 */       localDialog.show();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.feature.b
 * JD-Core Version:    0.6.2
 */