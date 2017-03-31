/*     */ package io.dcloud.feature.ui.navigator;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.SharedPreferences;
/*     */ import android.content.SharedPreferences.Editor;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.os.Build;
/*     */ import android.text.TextUtils;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.View;
/*     */ import android.webkit.CookieManager;
/*     */ import android.widget.Toast;
/*     */ import com.nostra13.universalimageloader.core.ImageLoader;
/*     */ import com.nostra13.universalimageloader.core.assist.FailReason;
/*     */ import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
/*     */ import io.dcloud.RInformation;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFeature;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.io.DHFile;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.constant.DataInterface;
/*     */ import io.dcloud.common.constant.StringConst;
/*     */ import io.dcloud.common.util.AppPermissionUtil;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.ShortCutUtil;
/*     */ import io.dcloud.common.util.ShortcutCreateUtil;
/*     */ import io.dcloud.common.util.TestUtil;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class NavigatorUIFeatureImpl
/*     */   implements IFeature
/*     */ {
/*  51 */   private static final String b = NavigatorUIFeatureImpl.class.getSimpleName();
/*     */   AbsMgr a;
/*     */ 
/*     */   public String execute(final IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*     */   {
/*  62 */     String str1 = null;
/*  63 */     final IApp localIApp = paramIWebview.obtainApp();
/*  64 */     String str2 = localIApp.obtainAppId();
/*  65 */     if (paramString.equals("closeSplashscreen")) {
/*  66 */       Logger.d("Main_Path", "appid=" + str2 + " closeSplashscreen");
/*  67 */       TestUtil.print(TestUtil.START_STREAM_APP, "closeSplashscreen appid=" + str2);
/*  68 */       Logger.i("download_manager", "javascript webapp task begin success appid=" + str2 + " closeSplashscreen");
/*  69 */       this.a.processEvent(IMgr.MgrType.WindowMgr, 11, paramIWebview.obtainFrameView());
/*     */     }
/*     */     else
/*     */     {
/*     */       String str3;
/*  71 */       if (paramString.equals("setFullscreen"))
/*     */       {
/*  73 */         str3 = paramArrayOfString[0];
/*  74 */         if (localIApp != null) {
/*  75 */           localIApp.setFullScreen(PdrUtil.parseBoolean(String.valueOf(str3), false, false));
/*     */         }
/*     */       }
/*  78 */       else if (paramString.equals("isFullScreen")) {
/*  79 */         if (localIApp != null)
/*  80 */           str1 = JSUtil.wrapJsVar(localIApp.isFullScreen());
/*     */       }
/*     */       else
/*     */       {
/*     */         Object localObject1;
/*  83 */         if (paramString.equals("setUserAgent")) {
/*  84 */           str3 = paramArrayOfString[0];
/*  85 */           localObject1 = paramArrayOfString[1];
/*     */ 
/*  87 */           localIApp.setConfigProperty("useragent", str3);
/*  88 */           localIApp.setConfigProperty("h5plus", (String)localObject1);
/*     */ 
/*  90 */           paramIWebview.setWebviewProperty("User-Agent", str3);
/*     */         }
/*  92 */         else if (paramString.equals("getUserAgent")) {
/*  93 */           str1 = paramIWebview.getWebviewProperty("User-Agent");
/*     */         }
/*  95 */         else if (paramString.equals("setCookie")) {
/*  96 */           paramIWebview.setWebviewProperty(paramArrayOfString[0], paramArrayOfString[1]);
/*     */         }
/*  98 */         else if (paramString.equals("getCookie")) {
/*  99 */           str1 = paramIWebview.getWebviewProperty(paramArrayOfString[0]);
/*     */         }
/* 101 */         else if (paramString.equals("removeAllCookie")) {
/*     */           try {
/* 103 */             CookieManager.getInstance().removeAllCookie();
/*     */           } catch (Exception localException1) {
/* 105 */             localException1.printStackTrace();
/*     */           }
/*     */         }
/* 108 */         else if (paramString.equals("removeSessionCookie")) {
/*     */           try {
/* 110 */             CookieManager.getInstance().removeSessionCookie();
/*     */           } catch (Exception localException2) {
/* 112 */             localException2.printStackTrace();
/*     */           }
/*     */         }
/* 115 */         else if (!paramString.equals("removeCookie"))
/*     */         {
/*     */           String str4;
/* 117 */           if (paramString.equals("setLogs")) {
/* 118 */             str4 = paramArrayOfString[0];
/* 119 */             Logger.setOpen(PdrUtil.parseBoolean(String.valueOf(str4), false, false));
/*     */           }
/* 121 */           else if (paramString.equals("isLogs")) {
/* 122 */             str1 = JSUtil.wrapJsVar(Logger.isOpen());
/*     */           }
/*     */           else
/*     */           {
/*     */             Object localObject2;
/*     */             Object localObject3;
/*     */             String str8;
/* 123 */             if (paramString.equals("createShortcut")) {
/* 124 */               str4 = paramArrayOfString[0];
/* 125 */               localObject1 = paramArrayOfString[1];
/*     */ 
/* 127 */               localObject2 = null;
/* 128 */               localObject3 = "";
/* 129 */               String str6 = "";
/* 130 */               str8 = "";
/* 131 */               boolean bool2 = true;
/* 132 */               boolean bool3 = true;
/* 133 */               String str11 = String.format("\"%s\"已创建桌面图标", new Object[] { localIApp.obtainAppName() });
/*     */ 
/* 135 */               JSONObject localJSONObject1 = null;
/*     */               try {
/* 137 */                 localObject2 = new JSONObject(str4);
/* 138 */                 bool2 = ((JSONObject)localObject2).optBoolean("force", bool2);
/* 139 */                 if (bool2) {
/* 140 */                   str11 = str11 + "，如有重复请手动删除";
/*     */                 }
/* 142 */                 localObject3 = ((JSONObject)localObject2).optString("name");
/* 143 */                 str6 = ((JSONObject)localObject2).optString("icon");
/* 144 */                 str8 = ((JSONObject)localObject2).optString("classname");
/* 145 */                 str11 = ((JSONObject)localObject2).has("toast") ? ((JSONObject)localObject2).optString("toast") : str11;
/* 146 */                 localJSONObject1 = ((JSONObject)localObject2).optJSONObject("extra");
/* 147 */                 bool3 = ((JSONObject)localObject2).optBoolean("check", bool3);
/*     */               } catch (JSONException localJSONException3) {
/* 149 */                 localJSONException3.printStackTrace();
/*     */               }
/*     */ 
/* 152 */               if ((BaseInfo.isForQihooBrowser(paramIWebview.getActivity())) && (!TextUtils.isEmpty(str11))) {
/* 153 */                 str11 = "已创建桌面图标，如未成功请检查权限或桌面设置。";
/*     */               }
/* 155 */               Bitmap localBitmap = null;
/*     */               try {
/* 157 */                 if (!TextUtils.isEmpty(str6)) {
/* 158 */                   String str12 = localIApp.convert2AbsFullPath(paramIWebview.obtainFullUrl(), str6);
/* 159 */                   localBitmap = BitmapFactory.decodeFile(str12);
/*     */                 }
/* 161 */                 if (localBitmap == null) {
/*     */                   try {
/* 163 */                     localBitmap = a(localIApp);
/*     */                   } catch (Exception localException3) {
/* 165 */                     localException3.printStackTrace();
/*     */                   }
/*     */                 }
/* 168 */                 if (localBitmap == null) {
/* 169 */                   boolean bool4 = paramIWebview.obtainApp().isStreamApp();
/* 170 */                   if (bool4) {
/* 171 */                     final IWebview localIWebview = paramIWebview;
/* 172 */                     final boolean bool5 = bool2;
/* 173 */                     final boolean bool6 = bool3;
/* 174 */                     final Object localObject4 = localObject3;
/* 175 */                     final String str13 = str8;
/* 176 */                     final String str14 = str11;
/* 177 */                     final JSONObject localJSONObject2 = localJSONObject1;
/* 178 */                     String str15 = DataInterface.getIconImageUrl(str2, localIApp.getActivity().getResources().getDisplayMetrics().widthPixels + "");
/* 179 */                     ImageLoader.getInstance().loadImage(str15, new ImageLoadingListener() {
/*     */                       public void onLoadingStarted(String paramAnonymousString, View paramAnonymousView) {
/*     */                       }
/*     */ 
/*     */                       public void onLoadingFailed(String paramAnonymousString, View paramAnonymousView, FailReason paramAnonymousFailReason) {
/* 184 */                         NavigatorUIFeatureImpl.a(NavigatorUIFeatureImpl.this, localIWebview, localObject4, null, str13, str14, localJSONObject2, bool5, bool6, this.h);
/*     */                       }
/*     */ 
/*     */                       public void onLoadingComplete(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap) {
/* 188 */                         NavigatorUIFeatureImpl.a(NavigatorUIFeatureImpl.this, localIWebview, localObject4, paramAnonymousBitmap, str13, str14, localJSONObject2, bool5, bool6, this.h);
/*     */                       }
/*     */ 
/*     */                       public void onLoadingCancelled(String paramAnonymousString, View paramAnonymousView)
/*     */                       {
/*     */                       }
/*     */                     });
/* 195 */                     return str1;
/*     */                   }
/* 197 */                   if (localBitmap == null) {
/* 198 */                     localBitmap = BitmapFactory.decodeResource(paramIWebview.getContext().getResources(), RInformation.DRAWABLE_ICON);
/*     */                   }
/*     */                 }
/*     */               }
/*     */               catch (Exception localException4)
/*     */               {
/* 204 */                 localException4.printStackTrace();
/*     */               }
/*     */ 
/* 207 */               a(paramIWebview, (String)localObject3, localBitmap, str8, str11, localJSONObject1, bool2, bool3, (String)localObject1);
/*     */             }
/* 211 */             else if (paramString.equals("getStatusbarHeight")) {
/* 212 */               DeviceInfo.updateStatusBarHeight(paramIWebview.getActivity());
/* 213 */               str1 = JSUtil.wrapJsVar(DeviceInfo.sStatusBarHeight / paramIWebview.getScale());
/* 214 */             } else if (paramString.equals("isImmersedStatusbar")) {
/* 215 */               str1 = JSUtil.wrapJsVar(AndroidResources.checkImmersedStatusBar(paramIWebview.getActivity()));
/* 216 */             } else if (paramString.equals("hasShortcut")) {
/* 217 */               str4 = paramArrayOfString[0];
/* 218 */               localObject1 = paramArrayOfString[1];
/*     */ 
/* 220 */               localObject2 = null;
/* 221 */               localObject3 = paramIWebview.obtainApp().obtainAppName();
/*     */               try {
/* 223 */                 localObject2 = new JSONObject(str4);
/* 224 */                 localObject3 = ((JSONObject)localObject2).optString("name", (String)localObject3);
/*     */               } catch (JSONException localJSONException2) {
/* 226 */                 localJSONException2.printStackTrace();
/*     */               }
/*     */ 
/* 229 */               a(paramIWebview.getContext(), paramIWebview, (String)localObject1, (String)localObject3);
/*     */             }
/* 231 */             else if (paramString.equals("updateSplashscreen")) {
/* 232 */               str4 = paramArrayOfString[0];
/*     */               try {
/* 234 */                 localObject1 = new JSONObject(str4);
/* 235 */                 localObject2 = PlatformUtil.getOrCreateBundle("pdr");
/* 236 */                 localObject3 = ((SharedPreferences)localObject2).edit();
/* 237 */                 String str7 = localIApp.obtainAppId();
/* 238 */                 str8 = ((JSONObject)localObject1).optString("image", null);
/* 239 */                 if (!TextUtils.isEmpty(str8)) {
/* 240 */                   String str9 = localIApp.convert2AbsFullPath(paramIWebview.obtainFullUrl(), str8);
/* 241 */                   if (PdrUtil.isDeviceRootDir(str9)) {
/* 242 */                     String str10 = StringConst.STREAMAPP_KEY_ROOTPATH + "splash/" + localIApp.obtainAppId() + ".png";
/* 243 */                     DHFile.copyFile(str9, str10, true, false);
/*     */                   }
/*     */ 
/* 246 */                   if ((!BaseInfo.isStreamApp(paramIWebview.getActivity())) && (!BaseInfo.isForQihooHelper(paramIWebview.getActivity()))) {
/* 247 */                     ((SharedPreferences.Editor)localObject3).putString("update_splash_img_path", str9);
/*     */                   }
/*     */                 }
/* 250 */                 if (!((JSONObject)localObject1).isNull("autoclose")) {
/* 251 */                   ((SharedPreferences.Editor)localObject3).putBoolean(str7 + "__update_splash_autoclose", ((JSONObject)localObject1).optBoolean("autoclose"));
/*     */                 }
/* 253 */                 if (!((JSONObject)localObject1).isNull("delay")) {
/* 254 */                   ((SharedPreferences.Editor)localObject3).putInt(str7 + "__update_splash_delay", ((JSONObject)localObject1).optInt("delay"));
/*     */                 }
/* 256 */                 if (BaseInfo.isWap2AppAppid(str7))
/*     */                 {
/* 259 */                   if (!((JSONObject)localObject1).isNull("autoclose_w2a")) {
/* 260 */                     ((SharedPreferences.Editor)localObject3).putBoolean(str7 + "__update_splash_autoclose_w2a", ((JSONObject)localObject1).optBoolean("autoclose_w2a"));
/*     */                   }
/* 262 */                   if (!((JSONObject)localObject1).isNull("delay_w2a")) {
/* 263 */                     ((SharedPreferences.Editor)localObject3).putInt(str7 + "__update_splash_delay_w2a", ((JSONObject)localObject1).optInt("delay_w2a"));
/*     */                   }
/*     */                 }
/* 266 */                 ((SharedPreferences.Editor)localObject3).commit();
/*     */               }
/*     */               catch (JSONException localJSONException1) {
/* 269 */                 localJSONException1.printStackTrace();
/*     */               }
/* 271 */             } else if (paramString.equals("requestPermission")) {
/* 272 */               str4 = paramArrayOfString[0];
/* 273 */               final String str5 = paramArrayOfString[1];
/* 274 */               final int i = str5.hashCode();
/* 275 */               localObject3 = a(str4);
/* 276 */               localIApp.registerSysEventListener(new ISysEventListener() {
/*     */                 public boolean onExecute(ISysEventListener.SysEventType paramAnonymousSysEventType, Object paramAnonymousObject) {
/* 278 */                   Object[] arrayOfObject = (Object[])paramAnonymousObject;
/* 279 */                   int i = ((Integer)arrayOfObject[0]).intValue();
/* 280 */                   String[] arrayOfString = (String[])arrayOfObject[1];
/* 281 */                   int[] arrayOfInt = (int[])arrayOfObject[2];
/* 282 */                   if ((ISysEventListener.SysEventType.onRequestPermissionsResult == paramAnonymousSysEventType) && (i == i)) {
/* 283 */                     localIApp.unregisterSysEventListener(this, ISysEventListener.SysEventType.onRequestPermissionsResult);
/*     */                     int j;
/* 285 */                     if (arrayOfInt.length > 0)
/* 286 */                       j = arrayOfInt[0];
/*     */                     else {
/* 288 */                       j = paramIWebview.obtainApp().checkSelfPermission(this.d);
/*     */                     }
/* 290 */                     String str = NavigatorUIFeatureImpl.a(NavigatorUIFeatureImpl.this, j);
/* 291 */                     JSUtil.execCallback(paramIWebview, str5, String.format("{result:'%s'}", new Object[] { str }), JSUtil.OK, true, false);
/*     */                   }
/* 293 */                   return true;
/*     */                 }
/*     */               }
/*     */               , ISysEventListener.SysEventType.onRequestPermissionsResult);
/*     */ 
/* 296 */               localIApp.requestPermissions(new String[] { localObject3 }, i);
/* 297 */             } else if (paramString.equals("checkPermission")) {
/* 298 */               str1 = JSUtil.wrapJsVar(a(paramIWebview, paramArrayOfString));
/* 299 */             } else if ("isBackground".equals(paramString)) {
/* 300 */               boolean bool1 = localIApp.obtainAppStatus() == 2;
/* 301 */               str1 = JSUtil.wrapJsVar(bool1);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 303 */     return str1;
/*     */   }
/*     */ 
/*     */   private String a(IWebview paramIWebview, String[] paramArrayOfString)
/*     */   {
/* 327 */     String str1 = paramArrayOfString[0];
/* 328 */     String str2 = paramIWebview.obtainApp().obtainAppId();
/* 329 */     String str3 = paramIWebview.obtainApp().obtainAppName();
/* 330 */     Context localContext = paramIWebview.getContext();
/* 331 */     if (str1.equals("SHORTCUT")) {
/* 332 */       if (Build.BRAND.equals(MobilePhoneModel.MEIZU)) {
/* 333 */         if (!AppPermissionUtil.isFlymeShortcutallowAllow(localContext, ShortCutUtil.getHeadShortCutIntent(str3))) {
/* 334 */           return "denied";
/*     */         }
/* 336 */         return "notdeny";
/*     */       }
/* 338 */       if (Build.MANUFACTURER.equals(MobilePhoneModel.SMARTISAN)) {
/* 339 */         if (MobilePhoneModel.isSmartisanLauncherPhone(localContext)) {
/* 340 */           return "denied";
/*     */         }
/* 342 */         return "notdeny";
/*     */       }
/*     */ 
/* 345 */       if (AppPermissionUtil.getShotCutOpId() != -1) {
/* 346 */         int i = AppPermissionUtil.getShotCutOpId();
/* 347 */         if (Build.BRAND.equals(MobilePhoneModel.XIAOMI)) {
/* 348 */           int j = AppPermissionUtil.checkOp(localContext);
/* 349 */           switch (j) {
/*     */           case 3:
/*     */           case 4:
/* 352 */             return "undetermined";
/*     */           case 1:
/* 354 */             return "denied";
/*     */           case 0:
/* 356 */             return "authorized";
/*     */           case -1:
/* 358 */             return "unsupported";
/*     */           case 2:
/*     */           }
/* 361 */         } else if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) {
/* 362 */           if (!AppPermissionUtil.isEmuiShortcutallowAllow()) {
/* 363 */             return "denied";
/*     */           }
/* 365 */           return "notdeny";
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 370 */       String str4 = a(str1);
/* 371 */       String str5 = a(paramIWebview.obtainApp().checkSelfPermission(str4));
/* 372 */       return String.valueOf(str5);
/*     */     }
/* 374 */     return "unknown";
/*     */   }
/*     */ 
/*     */   private String a(String paramString) {
/* 378 */     if ("CAMERA".equals(paramString))
/* 379 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "CAMERA", null));
/* 380 */     if ("RECORD".equals(paramString)) {
/* 381 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "RECORD_AUDIO", null));
/*     */     }
/* 383 */     if ("LOCATION".equals(paramString))
/* 384 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "ACCESS_FINE_LOCATION", null));
/* 385 */     if ("CONTACTS".equals(paramString))
/* 386 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "WRITE_CONTACTS", null));
/* 387 */     if ("SHORTCUT".equals(paramString)) {
/* 388 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "INSTALL_SHORTCUT", null));
/*     */     }
/* 390 */     return paramString;
/*     */   }
/*     */ 
/*     */   private String a(int paramInt)
/*     */   {
/* 395 */     switch (paramInt) {
/*     */     case -1:
/* 397 */       return "undetermined";
/*     */     case 0:
/* 400 */       return "authorized";
/*     */     }
/*     */ 
/* 403 */     return "unknown";
/*     */   }
/*     */ 
/*     */   private void a(Context paramContext, IWebview paramIWebview, String paramString1, String paramString2)
/*     */   {
/* 408 */     String str1 = "";
/* 409 */     String str2 = ShortCutUtil.requestShortCut(paramContext, paramString2);
/* 410 */     if ("short_cut_existing".equals(str2)) {
/* 411 */       str1 = String.format("{result:%s}", new Object[] { "existing" });
/*     */     }
/* 413 */     else if ("short_cut_none".equals(str2)) {
/* 414 */       str1 = String.format("{result:%s}", new Object[] { "none" });
/*     */     }
/* 416 */     else if ("nopermissions".equals(str2)) {
/* 417 */       str1 = String.format("{result:%s}", new Object[] { "nopermissions" });
/*     */     }
/*     */     else {
/* 420 */       str1 = String.format("{result:%s}", new Object[] { "unknown" });
/*     */     }
/*     */     try
/*     */     {
/* 424 */       JSUtil.execCallback(paramIWebview, paramString1, new JSONObject(str1), JSUtil.OK, false);
/*     */     }
/*     */     catch (JSONException localJSONException) {
/* 427 */       localJSONException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void b(final Context paramContext, final IWebview paramIWebview, final String paramString1, final String paramString2) {
/* 432 */     Runnable local3 = new Runnable()
/*     */     {
/*     */       public void run() {
/* 435 */         String str1 = "false";
/*     */ 
/* 437 */         String str2 = ShortCutUtil.requestShortCutForCommit(paramContext, paramString2);
/* 438 */         if ("short_cut_existing".equals(str2)) {
/* 439 */           str1 = "true";
/*     */         }
/* 441 */         String str3 = String.format("{sure:%s}", new Object[] { str1 });
/*     */         try {
/* 443 */           JSUtil.execCallback(paramIWebview, paramString1, new JSONObject(str3), JSUtil.OK, false);
/*     */         } catch (JSONException localJSONException) {
/* 445 */           localJSONException.printStackTrace();
/*     */         }
/*     */       }
/*     */     };
/* 449 */     MessageHandler.postDelayed(local3, 500L);
/*     */   }
/*     */ 
/*     */   public void init(AbsMgr paramAbsMgr, String paramString)
/*     */   {
/* 454 */     this.a = paramAbsMgr;
/*     */   }
/*     */ 
/*     */   public void dispose(String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   private Bitmap a(IApp paramIApp)
/*     */   {
/* 463 */     String str = b(paramIApp);
/* 464 */     if (str != null) {
/* 465 */       return BitmapFactory.decodeFile(str);
/*     */     }
/* 467 */     return null;
/*     */   }
/*     */ 
/*     */   private String b(IApp paramIApp) {
/* 471 */     String str = "";
/* 472 */     Intent localIntent = paramIApp.obtainWebAppIntent();
/* 473 */     if (localIntent != null) {
/* 474 */       str = localIntent.getStringExtra("app_icon");
/*     */     }
/*     */ 
/* 477 */     return str;
/*     */   }
/*     */ 
/*     */   private void a(IWebview paramIWebview, String paramString1, Bitmap paramBitmap, String paramString2, String paramString3, JSONObject paramJSONObject, boolean paramBoolean1, boolean paramBoolean2, String paramString4)
/*     */   {
/* 482 */     IApp localIApp = paramIWebview.obtainApp();
/* 483 */     String str = localIApp.obtainAppId();
/* 484 */     Activity localActivity = paramIWebview.getActivity();
/* 485 */     SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/* 486 */     if (PdrUtil.isEmpty(paramString1)) {
/* 487 */       paramString1 = localIApp.obtainAppName();
/*     */     }
/*     */ 
/* 511 */     boolean bool = localSharedPreferences.getBoolean(str + "_created_shortcut", false);
/* 512 */     if (TextUtils.isEmpty(paramString2)) {
/* 513 */       Intent localIntent = paramIWebview.obtainApp().obtainWebAppIntent();
/* 514 */       if (localIntent != null) {
/* 515 */         paramString2 = localIntent.getStringExtra("short_cut_class_name");
/*     */       }
/*     */     }
/* 518 */     if (ShortcutCreateUtil.isDuplicateLauncher(localActivity)) {
/* 519 */       if (ShortCutUtil.createShortcutToDeskTop(localActivity, str, paramString1, paramBitmap, paramString2, paramJSONObject, true)) {
/* 520 */         if ((!TextUtils.isEmpty(paramString3)) && (ShortcutCreateUtil.needToast(localActivity))) {
/* 521 */           Toast.makeText(localActivity, paramString3, 1).show();
/*     */         }
/* 523 */         ShortCutUtil.commitShortcut(localIApp, 11, 1);
/*     */       }
/*     */     }
/* 526 */     else if (!ShortCutUtil.hasShortcut(localActivity, paramString1))
/*     */     {
/* 528 */       if (paramBoolean1) {
/* 529 */         if (!TextUtils.isEmpty(paramString3)) {
/* 530 */           Toast.makeText(localActivity, paramString3, 1).show();
/*     */         }
/* 532 */         ShortCutUtil.createShortcutToDeskTop(localActivity, str, paramString1, paramBitmap, paramString2, paramJSONObject, true);
/* 533 */         ShortCutUtil.commitShortcut(localIApp, 11, 1);
/*     */       } else {
/* 535 */         if (bool) {
/* 536 */           return;
/*     */         }
/* 538 */         if (ShortCutUtil.createShortcutToDeskTop(localActivity, str, paramString1, paramBitmap, paramString2, paramJSONObject, true)) {
/* 539 */           if ((!TextUtils.isEmpty(paramString3)) && (ShortcutCreateUtil.needToast(localActivity))) {
/* 540 */             Toast.makeText(localActivity, paramString3, 1).show();
/*     */           }
/* 542 */           ShortCutUtil.commitShortcut(localIApp, 11, 1);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 547 */     b(paramIWebview.getContext(), paramIWebview, paramString4, paramString1);
/*     */   }
/*     */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.doctor.hd\app\libs\navigatorui.jar
 * Qualified Name:     io.dcloud.feature.ui.navigator.NavigatorUIFeatureImpl
 * JD-Core Version:    0.6.2
 */