/*      */ package io.dcloud.common.util;
/*      */ 
/*      */ import android.app.Activity;
/*      */ import android.app.AlertDialog;
/*      */ import android.app.AlertDialog.Builder;
/*      */ import android.content.ContentResolver;
/*      */ import android.content.Context;
/*      */ import android.content.DialogInterface;
/*      */ import android.content.DialogInterface.OnClickListener;
/*      */ import android.content.Intent;
/*      */ import android.content.SharedPreferences;
/*      */ import android.content.SharedPreferences.Editor;
/*      */ import android.content.pm.PackageManager;
/*      */ import android.content.res.Resources;
/*      */ import android.database.Cursor;
/*      */ import android.graphics.Bitmap;
/*      */ import android.graphics.BitmapFactory;
/*      */ import android.net.Uri;
/*      */ import android.os.Build;
/*      */ import android.os.Build.VERSION;
/*      */ import android.text.TextUtils;
/*      */ import android.util.DisplayMetrics;
/*      */ import android.widget.Toast;
/*      */ import com.nostra13.dcloudimageloader.cache.disc.DiscCacheAware;
/*      */ import com.nostra13.dcloudimageloader.core.ImageLoader;
/*      */ import io.dcloud.RInformation;
/*      */ import io.dcloud.common.DHInterface.IApp;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.MessageHandler;
/*      */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*      */ import io.dcloud.common.adapter.util.PlatformUtil;
/*      */ import io.dcloud.common.adapter.util.SP;
/*      */ import io.dcloud.common.constant.DataInterface;
/*      */ import io.dcloud.common.constant.StringConst;
/*      */ import java.io.File;
/*      */ import java.net.URLEncoder;
/*      */ import java.util.Iterator;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ public class ShortCutUtil
/*      */ {
/*      */   private static final String SHORTCUT_SRC_QIHOO = "short_cut_src_qihoo";
/*      */   private static final String SHORTCUT_SRC_STREAM_APPS = "short_cut_src_stream_apps";
/*      */   public static final String SHORT_CUT_EXISTING = "short_cut_existing";
/*      */   public static final String SHORT_CUT_NONE = "short_cut_none";
/*      */   public static final String NOPERMISSIONS = "nopermissions";
/*      */   public static final String UNKNOWN = "unknown";
/*      */   private static final String PLUGIN_INTENT_TYPE = "com.qihoo.browser.pluginIntent";
/*      */   private static final String PLUGIN_INTENT_PLUGIN_NAME = "com.qihoo.browser.pluginIntent.name";
/*      */   private static final String PLUGIN_INTENT_PLUGIN_ACTIVITY = "com.qihoo.browser.pluginIntent.activity";
/*      */   public static final String ACTION_DESKTOP_LINK = "com.qihoo.browser.action.SHORTCUT2";
/*      */   static TypeRunnable mRunnable;
/*      */ 
/*      */   public static void updateShortcutFromDeskTop(Activity paramActivity, String paramString1, String paramString2, Bitmap paramBitmap, String paramString3)
/*      */   {
/*   90 */     removeShortcutFromDeskTop(paramActivity, paramString1, paramString2, paramString3, null);
/*      */ 
/*   92 */     createShortcutToDeskTop(paramActivity, paramString1, paramString2, paramBitmap, paramString3, null, false);
/*      */   }
/*      */ 
/*      */   public static boolean removeShortcutFromDeskTop(Context paramContext, String paramString1, String paramString2, String paramString3, JSONObject paramJSONObject)
/*      */   {
/*  107 */     Intent localIntent1 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
/*      */ 
/*  110 */     localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramString2);
/*  111 */     localIntent1.putExtra("duplicate", false);
/*      */ 
/*  113 */     Intent localIntent2 = new Intent();
/*  114 */     if (TextUtils.isEmpty(paramString3)) {
/*  115 */       localIntent2 = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
/*      */     }
/*      */     else {
/*  118 */       localIntent2.setClassName(paramContext.getPackageName(), paramString3);
/*  119 */       localIntent2.setAction("android.intent.action.MAIN");
/*  120 */       localIntent2.addCategory("android.intent.category.LAUNCHER");
/*      */     }
/*  122 */     if (paramJSONObject != null) {
/*  123 */       localObject = paramJSONObject.keys();
/*  124 */       while (((Iterator)localObject).hasNext()) {
/*      */         try {
/*  126 */           String str1 = (String)((Iterator)localObject).next();
/*  127 */           String str2 = paramJSONObject.getString(str1);
/*  128 */           localIntent2.putExtra(str1, str2);
/*      */         } catch (JSONException localJSONException) {
/*  130 */           localJSONException.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*  134 */     addShortCutSrc(paramContext, localIntent2);
/*  135 */     localIntent2.putExtra("short_cut_appid", paramString1);
/*  136 */     localIntent2.putExtra("from_short_cut_start", true);
/*  137 */     localIntent2.setFlags(268435456);
/*  138 */     Object localObject = Uri.parse("http://m3w.cn/s/" + paramString1);
/*      */ 
/*  140 */     localIntent2.setData((Uri)localObject);
/*  141 */     localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
/*      */ 
/*  144 */     paramContext.sendBroadcast(localIntent1);
/*  145 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean createShortcutToDeskTop(Context paramContext, String paramString1, String paramString2, Bitmap paramBitmap, String paramString3, JSONObject paramJSONObject, boolean paramBoolean)
/*      */   {
/*  162 */     return createShortcutToDeskTop(paramContext, paramString1, paramString2, paramBitmap, paramString3, paramJSONObject, paramBoolean, false);
/*      */   }
/*      */ 
/*      */   public static boolean createShortcutToDeskTop(IApp paramIApp, boolean paramBoolean)
/*      */   {
/*  173 */     String str1 = DataInterface.getIconImageUrl(paramIApp.obtainAppId(), paramIApp.getActivity().getResources().getDisplayMetrics().widthPixels + "");
/*  174 */     String str2 = ImageLoader.getInstance().getDiscCache().get(str1).getPath();
/*  175 */     Bitmap localBitmap = null;
/*  176 */     if (localBitmap == null) {
/*  177 */       if ((!TextUtils.isEmpty(str2)) && (str2.startsWith("file://"))) {
/*  178 */         str2 = str2.substring("file://".length());
/*      */       }
/*  180 */       localBitmap = BitmapFactory.decodeFile(str2);
/*      */     }
/*  182 */     String str3 = "";
/*  183 */     Intent localIntent = paramIApp.obtainWebAppIntent();
/*  184 */     if (localIntent != null) {
/*  185 */       str3 = localIntent.getStringExtra("short_cut_class_name");
/*      */     }
/*  187 */     return createShortcutToDeskTop(paramIApp.getActivity(), paramIApp.obtainAppId(), paramIApp.obtainAppName(), localBitmap, str3, null, false, paramBoolean);
/*      */   }
/*      */ 
/*      */   public static boolean createShortcutToDeskTop(Context paramContext, String paramString1, String paramString2, Bitmap paramBitmap, String paramString3, JSONObject paramJSONObject, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  217 */     Intent localIntent1 = getHeadShortCutIntent(paramString2);
/*      */ 
/*  219 */     Intent localIntent2 = new Intent();
/*  220 */     if (TextUtils.isEmpty(paramString3)) {
/*  221 */       localIntent2 = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
/*      */     }
/*      */     else {
/*  224 */       localIntent2.setClassName(paramContext.getPackageName(), paramString3);
/*  225 */       localIntent2.setAction("android.intent.action.MAIN");
/*  226 */       if ((!BaseInfo.isForQihooHelper(paramContext)) && (!BaseInfo.isStreamSDK)) {
/*  227 */         localIntent2.addCategory("android.intent.category.LAUNCHER");
/*      */       }
/*      */     }
/*  230 */     if (paramJSONObject != null) {
/*  231 */       localObject = paramJSONObject.keys();
/*  232 */       while (((Iterator)localObject).hasNext()) {
/*      */         try {
/*  234 */           String str1 = (String)((Iterator)localObject).next();
/*  235 */           String str2 = paramJSONObject.getString(str1);
/*  236 */           localIntent2.putExtra(str1, str2);
/*      */         } catch (JSONException localJSONException) {
/*  238 */           localJSONException.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*  242 */     addShortCutSrc(paramContext, localIntent2);
/*  243 */     localIntent2.putExtra("short_cut_appid", paramString1);
/*  244 */     localIntent2.putExtra("from_short_cut_start", true);
/*  245 */     localIntent2.setFlags(268435456);
/*  246 */     Object localObject = "http://m3w.cn/s/" + paramString1 + (paramBoolean2 ? "&time=" + System.currentTimeMillis() : "");
/*  247 */     localIntent2.setData(Uri.parse((String)localObject));
/*  248 */     if (BaseInfo.isForQihooBrowser(paramContext)) {
/*  249 */       localIntent2.setAction("com.qihoo.browser.action.SHORTCUT2");
/*  250 */       localIntent2.addFlags(268435456);
/*  251 */       localIntent2.addFlags(67108864);
/*      */ 
/*  253 */       localIntent2.setDataAndType(localObject == null ? null : Uri.parse((String)localObject), "com.qihoo.browser.pluginIntent");
/*  254 */       localIntent2.putExtra("com.qihoo.browser.pluginIntent.name", "io.dcloud.streamapp");
/*  255 */       localIntent2.putExtra("com.qihoo.browser.pluginIntent.activity", "io.dcloud.streamapp.StreamAppListActivity");
/*  256 */       localIntent2.putExtra("type", 1);
/*  257 */       localIntent2.putExtra("appid", paramString1);
/*  258 */       localIntent2.setClassName(paramContext, "com.qihoo.browser.activity.SplashActivity");
/*  259 */     } else if (BaseInfo.isStreamSDK) {
/*  260 */       localIntent2.setClassName(paramContext, "io.dcloud.appstream.StreamAppMainActivity");
/*      */     }
/*  262 */     localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
/*  263 */     localIntent1.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
/*  264 */     paramContext.sendBroadcast(localIntent1);
/*  265 */     return true;
/*      */   }
/*      */ 
/*      */   private static void addShortCutSrc(Context paramContext, Intent paramIntent)
/*      */   {
/*  270 */     if (BaseInfo.isStreamApp(paramContext))
/*  271 */       paramIntent.putExtra("shoort_cut_src", "short_cut_src_stream_apps");
/*  272 */     else if (BaseInfo.isForQihooHelper(paramContext))
/*  273 */       paramIntent.putExtra("shoort_cut_src", "short_cut_src_qihoo");
/*  274 */     else if (BaseInfo.isStreamSDK)
/*  275 */       paramIntent.putExtra("shoort_cut_src", BaseInfo.sChannel);
/*      */   }
/*      */ 
/*      */   public static boolean hasShortcut(Context paramContext, String paramString)
/*      */   {
/*  293 */     boolean bool = false;
/*      */ 
/*  295 */     if ((Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) && (Build.VERSION.SDK_INT >= 23)) {
/*  296 */       return bool;
/*      */     }
/*  298 */     String str = requestShortCut(paramContext, paramString);
/*  299 */     if ("short_cut_existing".equals(str))
/*  300 */       bool = true;
/*      */     else {
/*  302 */       bool = false;
/*      */     }
/*  304 */     return bool;
/*      */   }
/*      */ 
/*      */   public static String requestShortCut(Context paramContext, String paramString)
/*      */   {
/*  313 */     String str1 = "unknown";
/*  314 */     ContentResolver localContentResolver = paramContext.getContentResolver();
/*  315 */     Uri localUri = getUriFromLauncher(paramContext);
/*  316 */     Logger.e("shortcututil", "requestShortCut: uri===" + localUri);
/*  317 */     if (null != localUri) {
/*      */       try {
/*  319 */         Cursor localCursor = localContentResolver.query(localUri, new String[] { "title", "intent" }, "title=? ", new String[] { paramString }, null);
/*  320 */         if ((localCursor != null) && (localCursor.getCount() > 0))
/*  321 */           Logger.e("shortcututil", "c != null && c.getCount() > 0");
/*  322 */         while (localCursor.moveToNext()) {
/*  323 */           String str2 = localCursor.getString(localCursor.getColumnIndex("intent"));
/*  324 */           if (!TextUtils.isEmpty(str2)) {
/*  325 */             Logger.e("shortcututil", "intent=====" + str2);
/*  326 */             if (str2.contains("short_cut_appid")) {
/*  327 */               if (BaseInfo.isStreamApp(paramContext)) {
/*  328 */                 if ((str2.contains("short_cut_src_stream_apps")) || (str2.contains("io.dcloud.appstream.StreamAppMainActivity")))
/*  329 */                   str1 = "short_cut_existing";
/*      */               }
/*  331 */               else if (BaseInfo.isForQihooHelper(paramContext)) {
/*  332 */                 if ((str2.contains("short_cut_src_qihoo")) || (str2.contains("io.dcloud.appstream.StreamAppListFakeActivity")))
/*  333 */                   str1 = "short_cut_existing";
/*      */               }
/*  335 */               else if (BaseInfo.isBase(paramContext))
/*  336 */                 str1 = "short_cut_existing";
/*  337 */               else if ((BaseInfo.isStreamSDK) && (
/*  338 */                 (str2.contains(BaseInfo.sChannel)) || (str2.contains("io.dcloud.appstream.StreamAppMainActivity"))))
/*  339 */                 str1 = "short_cut_existing";
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  344 */             str1 = "short_cut_none";
/*      */           }
/*  346 */           continue;
/*      */ 
/*  348 */           str1 = "short_cut_none";
/*      */         }
/*  350 */         if ((localCursor != null) && (!localCursor.isClosed()))
/*  351 */           localCursor.close();
/*      */       }
/*      */       catch (Exception localException) {
/*  354 */         if (localException.getMessage().contains("READ_SETTINGS")) {
/*  355 */           str1 = "nopermissions";
/*      */         }
/*  357 */         localException.printStackTrace();
/*      */       }
/*      */     }
/*  360 */     return str1;
/*      */   }
/*      */ 
/*      */   public static String requestShortCutForCommit(Context paramContext, String paramString)
/*      */   {
/*  369 */     if ((Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) && (Build.VERSION.SDK_INT >= 23)) {
/*  370 */       return "unknown";
/*      */     }
/*  372 */     return requestShortCut(paramContext, paramString);
/*      */   }
/*      */ 
/*      */   public static String getSreamAppShortName(Context paramContext)
/*      */   {
/*  381 */     String str1 = "";
/*      */     try {
/*  383 */       JSONArray localJSONArray = new JSONArray();
/*  384 */       ContentResolver localContentResolver = paramContext.getContentResolver();
/*  385 */       Uri localUri = getUriFromLauncher(paramContext);
/*  386 */       Cursor localCursor = localContentResolver.query(localUri, new String[] { "title", "intent" }, null, null, null);
/*  387 */       if ((localCursor != null) && (localCursor.getCount() > 0)) {
/*  388 */         while (localCursor.moveToNext()) {
/*  389 */           String str2 = localCursor.getString(localCursor.getColumnIndex("intent"));
/*  390 */           String str3 = localCursor.getString(localCursor.getColumnIndex("title"));
/*  391 */           if ((!TextUtils.isEmpty(str3)) && (!TextUtils.isEmpty(str2)))
/*      */           {
/*  394 */             if (str2.contains("short_cut_appid")) {
/*  395 */               JSONObject localJSONObject = new JSONObject();
/*  396 */               if (BaseInfo.isStreamApp(paramContext)) {
/*  397 */                 if ((str2.contains("short_cut_src_stream_apps")) || (str2.contains("io.dcloud.appstream.StreamAppMainActivity"))) {
/*  398 */                   localJSONObject.put("name", str3);
/*  399 */                   localJSONArray.put(localJSONObject);
/*      */                 }
/*  401 */               } else if ((BaseInfo.isForQihooHelper(paramContext)) && (
/*  402 */                 (str2.contains("short_cut_src_qihoo")) || (str2.contains("io.dcloud.appstream.StreamAppListFakeActivity")))) {
/*  403 */                 localJSONObject.put("name", str3);
/*  404 */                 localJSONArray.put(localJSONObject);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*  410 */       if ((localCursor != null) && (!localCursor.isClosed())) {
/*  411 */         localCursor.close();
/*      */       }
/*  413 */       str1 = URLEncoder.encode(localJSONArray.toString(), "UTF-8");
/*      */     } catch (Exception localException) {
/*  415 */       localException.printStackTrace();
/*      */     }
/*  417 */     return str1;
/*      */   }
/*      */ 
/*      */   private static Uri getUriFromLauncher(Context paramContext)
/*      */   {
/*  422 */     Logger.e("111", "getUriFromLauncher: Build.MANUFACTURER.==" + Build.MANUFACTURER);
/*  423 */     StringBuilder localStringBuilder = new StringBuilder();
/*  424 */     String str1 = null;
/*  425 */     String str2 = LauncherUtil.getLauncherPackageName(paramContext);
/*  426 */     Logger.e("tag", "getUriFromLauncher: packageName" + str2);
/*  427 */     if ("com.nd.android.pandahome2".equals(str2)) {
/*  428 */       return Uri.parse("content://com.nd.android.launcher2.settings/com.nd.hilauncherdev/favorites?notify=true");
/*      */     }
/*  430 */     str1 = LauncherUtil.getAuthorityFromPermission(paramContext, str2 + ".permission.READ_SETTINGS");
/*  431 */     Logger.e("TAG", "getUriFromLauncher: LauncherUtil.getAuthorityFromPermissionwithpackagename(" + str1);
/*  432 */     if (TextUtils.isEmpty(str1)) {
/*  433 */       str1 = LauncherUtil.getAuthorityFromPermissionDefault(paramContext);
/*  434 */       Logger.e("TAG", "getUriFromLauncher: LauncherUtil.getAuthorityFromPermissionDefault(" + str1);
/*      */     }
/*  436 */     if (!TextUtils.isEmpty(str1)) {
/*  437 */       localStringBuilder.append("content://");
/*  438 */       localStringBuilder.append(str1);
/*  439 */       if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.OPPO))
/*  440 */         localStringBuilder.append("/singledesktopitems?notify=true");
/*      */       else {
/*  442 */         localStringBuilder.append("/favorites?notify=true");
/*      */       }
/*  444 */       return Uri.parse(localStringBuilder.toString());
/*      */     }
/*  446 */     if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.QiKU)) {
/*  447 */       return Uri.parse("content://com.yulong.android.launcher3.compound/compoundworkspace?notify=false");
/*      */     }
/*      */ 
/*  450 */     return null;
/*      */   }
/*      */ 
/*      */   public static boolean isOpsCreateShortcut(Context paramContext, String paramString)
/*      */   {
/*  461 */     SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/*  462 */     boolean bool = localSharedPreferences.getBoolean(paramString + "_is_create_shortcut", false);
/*  463 */     if (bool) localSharedPreferences.edit().remove(paramString + "_is_create_shortcut").commit();
/*  464 */     return bool;
/*      */   }
/*      */ 
/*      */   public static boolean isRunShortCut(String paramString)
/*      */   {
/*  475 */     SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/*  476 */     String str = localSharedPreferences.getString("record_run_short_cut", "");
/*  477 */     if (str.equals(paramString)) {
/*  478 */       localSharedPreferences.edit().remove("record_run_short_cut").commit();
/*  479 */       return true;
/*      */     }
/*  481 */     return false;
/*      */   }
/*      */ 
/*      */   public static void onResumeCreateShortcut(IApp paramIApp)
/*      */   {
/*  490 */     String str1 = DataInterface.getIconImageUrl(paramIApp.obtainAppId(), paramIApp.getActivity().getResources().getDisplayMetrics().widthPixels + "");
/*  491 */     String str2 = ImageLoader.getInstance().getDiscCache().get(str1).getPath();
/*  492 */     int i = AppPermissionUtil.getCheckShortcutOps(paramIApp.getActivity());
/*  493 */     boolean bool1 = false;
/*  494 */     boolean bool2 = false;
/*  495 */     if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU)) {
/*  496 */       bool1 = AppPermissionUtil.isFlymeShortcutallowAllow(paramIApp.getActivity(), getHeadShortCutIntent(paramIApp.obtainAppName()));
/*  497 */       if (!bool1)
/*  498 */         commitShortcut(paramIApp, 12, true, false, true, 0);
/*      */     }
/*  500 */     else if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) {
/*  501 */       bool2 = AppPermissionUtil.isEmuiShortcutallowAllow();
/*  502 */       if (!bool2) {
/*  503 */         commitShortcut(paramIApp, 12, true, false, true, 0);
/*      */       }
/*      */ 
/*      */     }
/*  507 */     else if (i == 1)
/*      */     {
/*  509 */       commitShortcut(paramIApp, 12, true, false, true, 0);
/*  510 */       AppPermissionUtil.checkShortcutOps(paramIApp, paramIApp.getActivity(), paramIApp.obtainAppId(), paramIApp.obtainAppName());
/*  511 */       localObject = null;
/*  512 */       if (localObject == null) {
/*  513 */         if ((!TextUtils.isEmpty(str2)) && (str2.startsWith("file://"))) {
/*  514 */           str2 = str2.substring("file://".length());
/*      */         }
/*  516 */         localObject = BitmapFactory.decodeFile(str2);
/*      */       }
/*  518 */       String str3 = "";
/*  519 */       Intent localIntent = paramIApp.obtainWebAppIntent();
/*  520 */       if (localIntent != null) {
/*  521 */         str3 = localIntent.getStringExtra("short_cut_class_name");
/*      */       }
/*  523 */       createShortcutToDeskTop(paramIApp.getActivity(), paramIApp.obtainAppId(), paramIApp.obtainAppName(), (Bitmap)localObject, str3, null, false);
/*  524 */       return;
/*      */     }
/*      */ 
/*  527 */     Object localObject = Build.BRAND.equalsIgnoreCase(MobilePhoneModel.GOOGLE) ? Build.MANUFACTURER : Build.BRAND;
/*  528 */     if ((MobilePhoneModel.SMARTISAN.equals(localObject)) || ((i != 1) && (MobilePhoneModel.XIAOMI.equals(localObject))) || ((MobilePhoneModel.MEIZU.equals(localObject)) && (bool1)) || ((Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) && (bool2)))
/*      */     {
/*  532 */       commitShortcut(paramIApp, 12, true, true, true, 0);
/*      */     }
/*      */ 
/*  535 */     createShortcut(paramIApp, str2, null, false);
/*      */   }
/*      */ 
/*      */   public static void createShortcut(IApp paramIApp, String paramString, Bitmap paramBitmap, boolean paramBoolean)
/*      */   {
/*  567 */     Logger.e("StreamSDK", "come in createShortcut");
/*      */ 
/*  572 */     if ((paramIApp == null) || (TextUtils.isEmpty(paramString)) || (paramIApp.startFromShortCut()) || (paramIApp.forceShortCut().equals("none"))) {
/*  573 */       return;
/*      */     }
/*  575 */     Logger.e("StreamSDK", "come out return 1");
/*      */ 
/*  577 */     Intent localIntent1 = paramIApp.obtainWebAppIntent();
/*  578 */     boolean bool1 = localIntent1.getIntExtra("__start_from__", -1) == 5;
/*  579 */     Logger.e("StreamSDK", "isMyRuning" + bool1);
/*  580 */     if (bool1) {
/*  581 */       return;
/*      */     }
/*      */ 
/*  585 */     Activity localActivity = paramIApp.getActivity();
/*  586 */     String str1 = paramIApp.obtainAppName();
/*  587 */     String str2 = paramIApp.obtainAppId();
/*  588 */     Bitmap localBitmap = paramBitmap;
/*  589 */     if (localBitmap == null) {
/*  590 */       if ((!TextUtils.isEmpty(paramString)) && (paramString.startsWith("file://"))) {
/*  591 */         paramString = paramString.substring("file://".length());
/*      */       }
/*  593 */       localBitmap = BitmapFactory.decodeFile(paramString);
/*      */     }
/*  595 */     if ((localBitmap == null) && (!paramIApp.isStreamApp())) {
/*  596 */       localBitmap = BitmapFactory.decodeResource(localActivity.getResources(), RInformation.DRAWABLE_ICON);
/*      */     }
/*      */ 
/*  599 */     String str3 = "";
/*  600 */     Intent localIntent2 = paramIApp.obtainWebAppIntent();
/*  601 */     if (localIntent2 != null) {
/*  602 */       str3 = localIntent2.getStringExtra("short_cut_class_name");
/*      */     }
/*  604 */     SharedPreferences localSharedPreferences1 = PlatformUtil.getOrCreateBundle("pdr");
/*      */ 
/*  606 */     boolean bool2 = localSharedPreferences1.getBoolean(str2 + "_staremapp_first_short_cut", true);
/*      */ 
/*  608 */     if (ShortcutCreateUtil.isDisableShort(paramIApp.getActivity())) {
/*  609 */       handleDisableShort(paramIApp.getActivity(), str2, bool2, localSharedPreferences1);
/*  610 */       localSharedPreferences1.edit().putBoolean(str2 + "_staremapp_first_short_cut", false).commit();
/*  611 */       if (paramIApp.isStreamApp()) {
/*  612 */         createShortcutToDeskTop(localActivity, str2, str1, localBitmap, str3, null, false);
/*      */       }
/*  614 */       Logger.e("StreamSDK", "判断当前手机是否不支持创建快捷方式io.dcloud.common.util.ShortcutCreateUtil.isDisableShort(app.getActivity())" + ShortcutCreateUtil.isDisableShort(paramIApp.getActivity()));
/*  615 */       return;
/*      */     }
/*      */ 
/*  618 */     if ((MobilePhoneModel.isSpecialPhone(localActivity)) && (showSettingsDialog(paramIApp, paramString, paramBitmap))) {
/*  619 */       Logger.e("StreamSDK", "检测如果当前手机为特殊对待的手机，并且未提示过创建快捷方式设置 拦截创建MobilePhoneModel.isSpecialPhone(context) && showSettingsDialog(app, filePath, bitmap)");
/*  620 */       return;
/*      */     }
/*      */ 
/*  624 */     int i = 0;
/*      */ 
/*  626 */     String str4 = localSharedPreferences1.getString("test_runing" + str2, null);
/*  627 */     if ((!TextUtils.isEmpty(str4)) && (str4.equals("__am=t"))) {
/*  628 */       i = 1;
/*      */     }
/*  630 */     if ((!paramIApp.isCompetentStreamApp()) && (i == 0)) {
/*  631 */       Logger.e("StreamSDK", "流应用，并且未审核状态时，不自动创建快捷方式!app.isCompetentStreamApp() && !isTestShortCut");
/*  632 */       return;
/*      */     }
/*  634 */     if (hasShortcut(localActivity, str1)) {
/*  635 */       Logger.e("StreamSDK", "ShortCutUtil.hasShortcut(context, name)");
/*  636 */       return;
/*      */     }
/*  638 */     boolean bool3 = localSharedPreferences1.getBoolean(str2 + "_created_shortcut", false);
/*      */ 
/*  644 */     if ((Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU)) && (!AppPermissionUtil.isFlymeShortcutallowAllow(localActivity, getHeadShortCutIntent(str1)))) {
/*  645 */       AppPermissionUtil.showShortCutOpsDialog(paramIApp, paramIApp.getActivity(), str2, localSharedPreferences1);
/*  646 */       Logger.e("StreamSDK", "判断魅族快捷方式权限");
/*  647 */       return;
/*      */     }
/*      */ 
/*  650 */     if ((Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) && (!AppPermissionUtil.isEmuiShortcutallowAllow())) {
/*  651 */       AppPermissionUtil.showShortCutOpsDialog(paramIApp, paramIApp.getActivity(), str2, localSharedPreferences1);
/*  652 */       Logger.e("StreamSDK", "判断华为快捷方式权限");
/*  653 */       return;
/*      */     }
/*      */ 
/*  656 */     if (!ShortcutCreateUtil.isDuplicateLauncher(localActivity))
/*      */     {
/*  658 */       if ((paramIApp.forceShortCut().equals("auto")) && (bool3)) {
/*  659 */         Logger.e("StreamSDK", "对支持去重和已创建快捷方式 并且无法查询是否创建快捷方式的不创建桌面图标");
/*      */         return;
/*      */       }
/*      */     }
/*      */     Object localObject;
/*  664 */     if (!bool3) {
/*  665 */       localObject = SP.getOrCreateBundle("streamapp_create_shortcut");
/*  666 */       bool3 = ((SharedPreferences)localObject).getBoolean("is_create_shortcut" + str2, false);
/*      */     }
/*      */ 
/*  669 */     if ((BaseInfo.isForQihooHelper(localActivity)) && (("H5EC86117".equalsIgnoreCase(str2)) || ("H5BCD03E4".equalsIgnoreCase(str2)) || ("H532A4BFF".equalsIgnoreCase(str2))))
/*      */     {
/*  673 */       localObject = str2 + "_storages";
/*  674 */       SharedPreferences localSharedPreferences2 = localActivity.getSharedPreferences((String)localObject, 0);
/*  675 */       if ((localSharedPreferences2.contains("SHORTCUT")) && (Boolean.parseBoolean(localSharedPreferences2.getString("SHORTCUT", "false"))))
/*      */       {
/*  677 */         Logger.e("StreamSDK", "按照邮件要求如果是36Kr资讯  H5EC86117 SHORTCUT  true 点评外卖  H5BCD03E4 SHORTCUT true 挑食     H532A4BFF  SHORTCUT true就要去读取配置文件");
/*  678 */         return;
/*      */       }
/*      */     }
/*      */ 
/*  682 */     if (localSharedPreferences1.getBoolean("short_cut_one_tips", true)) {
/*  683 */       localSharedPreferences1.edit().putBoolean("short_cut_one_tips", false).commit();
/*      */     }
/*  685 */     localSharedPreferences1.edit().putBoolean(str2 + "_staremapp_first_short_cut", false).commit();
/*  686 */     if (createShortcutToDeskTop(localActivity, str2, str1, localBitmap, str3, null, false)) {
/*  687 */       Logger.e("StreamSDK", "come into createShortcutToDeskTop and return ture already");
/*  688 */       if (paramBoolean) {
/*  689 */         if (isHasShortCut(paramIApp, 15000L, "auto"))
/*      */         {
/*  691 */           return;
/*      */         }
/*      */ 
/*  694 */         showCreateShortCutToast(paramIApp);
/*      */       }
/*      */       else
/*      */       {
/*  698 */         if (isHasShortCut(paramIApp, 1000L, "auto")) {
/*  699 */           return;
/*      */         }
/*  701 */         showCreateShortCutToast(paramIApp);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  706 */     commitShortcut(paramIApp, 11, 0);
/*  707 */     localSharedPreferences1.edit().putString(str2 + "_create_shortcut_name", str1).commit();
/*  708 */     localSharedPreferences1.edit().putBoolean(str2 + "_created_shortcut", true).commit();
/*      */   }
/*      */ 
/*      */   public static void commitShortcut(IApp paramIApp, int paramInt1, int paramInt2)
/*      */   {
/*  713 */     commitShortcut(paramIApp, paramInt1, false, false, false, paramInt2);
/*      */   }
/*      */ 
/*      */   public static void commitShortcut(IApp paramIApp, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt2) {
/*  717 */     commitShortcut(paramIApp, paramInt1, paramBoolean1, paramBoolean2, paramBoolean3, paramInt2, null);
/*      */   }
/*      */ 
/*      */   public static void commitShortcut(final IApp paramIApp, final int paramInt1, final boolean paramBoolean1, final boolean paramBoolean2, final boolean paramBoolean3, final int paramInt2, String paramString)
/*      */   {
/*  729 */     new Thread() {
/*      */       public void run() {
/*  731 */         String str1 = this.val$name;
/*      */         try {
/*  733 */           Thread.sleep(2000L);
/*      */         }
/*      */         catch (InterruptedException localInterruptedException) {
/*  736 */           localInterruptedException.printStackTrace();
/*      */         }
/*  738 */         if (TextUtils.isEmpty(str1)) {
/*  739 */           str1 = paramIApp.obtainAppName();
/*      */         }
/*  741 */         int i = AppPermissionUtil.checkPermission(paramIApp.getActivity(), str1);
/*  742 */         Intent localIntent = paramIApp.getActivity().getIntent();
/*  743 */         String str2 = paramIApp.obtainAppVersionName();
/*  744 */         String str3 = DataInterface.getStreamappFrom(localIntent);
/*  745 */         String str4 = StringConst.STREAMAPP_KEY_BASESERVICEURL() + "collect/startup?s=" + paramInt1 + "&" + DataInterface.getUrlBaseData(paramIApp.getActivity(), paramIApp.obtainAppId(), BaseInfo.getLaunchType(paramIApp.getActivity().getIntent()), str3) + "&romv=" + DataInterface.getRomVersion() + "&scf=" + paramInt2 + "&scp=" + i + "&v=" + PdrUtil.encodeURL(paramIApp.obtainAppVersionName());
/*      */ 
/*  747 */         if (paramBoolean1) {
/*  748 */           int j = paramBoolean2 ? 1 : 0;
/*  749 */           int k = paramBoolean3 ? 1 : 0;
/*  750 */           str4 = str4 + "&scr=" + j + "&scs=" + k;
/*      */         }
/*      */         else {
/*  753 */           String str5 = "u";
/*  754 */           String str6 = ShortCutUtil.requestShortCutForCommit(paramIApp.getActivity(), str1);
/*  755 */           if ("short_cut_existing".equals(str6))
/*  756 */             str5 = "s";
/*  757 */           else if ("short_cut_none".equals(str6)) {
/*  758 */             str5 = "n";
/*      */           }
/*  760 */           str4 = str4 + "&sc=" + str5;
/*      */         }
/*      */         try {
/*  763 */           NetTool.httpGet(str4);
/*      */         } catch (Exception localException) {
/*  765 */           localException.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*  729 */     .start();
/*      */   }
/*      */ 
/*      */   public static void handleDisableShort(Activity paramActivity, String paramString, boolean paramBoolean, SharedPreferences paramSharedPreferences)
/*      */   {
/*  778 */     if (BaseInfo.isForQihooHelper(paramActivity))
/*      */     {
/*  780 */       if ((paramBoolean) && (!ApkUtils.isApkInstalled(paramActivity, "io.dcloud.streamapps")))
/*  781 */         showDownloadStreams(paramActivity);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean showSettingsDialog(final IApp paramIApp, final String paramString, final Bitmap paramBitmap)
/*      */   {
/*  795 */     SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/*  796 */     if ((!localSharedPreferences.getBoolean("short_cut_one_tips", true)) && (!Build.BRAND.equals(MobilePhoneModel.SMARTISAN))) {
/*  797 */       return false;
/*      */     }
/*      */ 
/*  800 */     if (localSharedPreferences.getBoolean("short_cut_one_tips", true)) {
/*  801 */       localSharedPreferences.edit().putBoolean("short_cut_one_tips", false).commit();
/*      */     }
/*  803 */     String str1 = "";
/*  804 */     String str2 = "前往设置权限";
/*  805 */     String str3 = "我设置过了";
/*  806 */     if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.QiKU)) {
/*  807 */       str1 = "奇酷手机使用流应用需先确定设置中已启用“快捷方式”。请点击“前往设置权限”打开的设置界面-桌面-快捷方式启用-开启。如果设置界面中无“桌面”选项请升级到最新版奇酷ROM。";
/*  808 */       str2 = "前往设置启用";
/*  809 */     } else if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.VIVO))
/*      */     {
/*  811 */       if ((!ApkUtils.isApkInstalled(paramIApp.getActivity(), "com.iqoo.secure")) || (Build.VERSION.SDK_INT < 21)) {
/*  812 */         return false;
/*      */       }
/*  814 */       localObject = "流应用";
/*  815 */       if (BaseInfo.isForQihooHelper(paramIApp.getActivity())) {
/*  816 */         localObject = "360手机助手";
/*      */       }
/*  818 */       str1 = "VIVO手机使用流应用需先确定开启桌面快捷方式。请点击“前往设置权限”-软件管理-桌面快捷方式管理-“" + (String)localObject + "”-允许。如果软件管理界面中无“桌面快捷方式管理”项请升级到最新版ROM。";
/*  819 */     } else if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
/*  820 */       str1 = "锤子手机使用九/十六宫格视图，无法在桌面安装本应用。请点击“前往设置视图“-桌面设置项-在单板块视图项中选择“安卓原生”-在弹出提示框中选择“确定”。";
/*  821 */       str3 = "不在桌面安装";
/*      */     }
/*      */ 
/*  824 */     Object localObject = DialogUtil.initDialogTheme(paramIApp.getActivity(), !BaseInfo.isForQihooHelper(paramIApp.getActivity()));
/*  825 */     ((AlertDialog.Builder)localObject).setMessage(str1).setTitle("创建桌面图标提醒").setPositiveButton(str2, new DialogInterface.OnClickListener()
/*      */     {
/*      */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*      */       {
/*  830 */         this.val$pdrSharedPre.edit().putString("record_run_short_cut", paramIApp.obtainAppId()).commit();
/*      */         Object localObject;
/*  831 */         if ((Build.BRAND.equalsIgnoreCase(MobilePhoneModel.QiKU)) || (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN))) {
/*  832 */           localObject = new Intent("android.settings.SETTINGS");
/*  833 */           paramIApp.getActivity().startActivity((Intent)localObject);
/*  834 */         } else if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.VIVO)) {
/*  835 */           localObject = paramIApp.getActivity().getPackageManager();
/*  836 */           Intent localIntent = new Intent();
/*  837 */           localIntent = ((PackageManager)localObject).getLaunchIntentForPackage("com.iqoo.secure");
/*  838 */           localIntent.setFlags(337641472);
/*  839 */           paramIApp.getActivity().startActivity(localIntent);
/*      */         }
/*      */       }
/*      */     }).setNegativeButton(str3, new DialogInterface.OnClickListener()
/*      */     {
/*      */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*      */       {
/*  847 */         if (!Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
/*  848 */           ShortCutUtil.createShortcut(this.val$app, paramString, paramBitmap, false);
/*      */         }
/*      */         else
/*  851 */           ShortCutUtil.commitShortcut(this.val$app, 12, true, false, false, 0);
/*      */       }
/*      */     });
/*  855 */     AlertDialog localAlertDialog = ((AlertDialog.Builder)localObject).create();
/*  856 */     localAlertDialog.setCanceledOnTouchOutside(false);
/*  857 */     localAlertDialog.show();
/*  858 */     return true;
/*      */   }
/*      */ 
/*      */   public static boolean showSettingsDialog(final Activity paramActivity, final String paramString1, final String paramString2, final String paramString3, final Bitmap paramBitmap)
/*      */   {
/*  872 */     SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/*  873 */     AlertDialog.Builder localBuilder = DialogUtil.initDialogTheme(paramActivity, !BaseInfo.isForQihooHelper(paramActivity));
/*  874 */     localBuilder.setMessage("奇酷手机使用流应用需先确定权限开启，在设置-桌面设置中勾选快捷方式启用").setTitle("创建快捷方式提醒").setPositiveButton("前往设置", new DialogInterface.OnClickListener()
/*      */     {
/*      */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*      */       {
/*  879 */         this.val$pdrSharedPre.edit().putString("record_run_short_cut", paramString1).commit();
/*  880 */         Intent localIntent = new Intent("android.settings.SETTINGS");
/*  881 */         paramActivity.startActivity(localIntent);
/*      */       }
/*      */     }).setNegativeButton("我设置过了", new DialogInterface.OnClickListener()
/*      */     {
/*      */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*      */       {
/*  888 */         if (ShortCutUtil.createShortcutToDeskTop(this.val$context, paramString1, paramString2, paramBitmap, paramString3, null, false))
/*  889 */           ShortCutUtil.runShortCutToast(this.val$context);
/*      */       }
/*      */     });
/*  893 */     AlertDialog localAlertDialog = localBuilder.create();
/*  894 */     localAlertDialog.setCanceledOnTouchOutside(false);
/*  895 */     localAlertDialog.show();
/*  896 */     return true;
/*      */   }
/*      */ 
/*      */   public static void showDownloadStreams(Activity paramActivity)
/*      */   {
/*  903 */     AlertDialog.Builder localBuilder = DialogUtil.initDialogTheme(paramActivity, !BaseInfo.isForQihooHelper(paramActivity));
/*  904 */     localBuilder.setMessage("此设备可能不支持在桌面安装应用，下次仍然需要从360手机助手中启动。也可以下载独立的流应用管理器。").setTitle("温馨提醒").setPositiveButton("下载管理器", new DialogInterface.OnClickListener()
/*      */     {
/*      */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*      */       {
/*  909 */         Intent localIntent = new Intent();
/*  910 */         localIntent.setAction("android.intent.action.VIEW");
/*  911 */         Uri localUri = Uri.parse("http://www.dcloud.io/streamapp/streamapp.apk");
/*  912 */         localIntent.setData(localUri);
/*  913 */         this.val$context.startActivity(localIntent);
/*      */       }
/*      */     }).setNegativeButton("我知道了", new DialogInterface.OnClickListener()
/*      */     {
/*      */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*      */       {
/*      */       }
/*      */     });
/*  923 */     AlertDialog localAlertDialog = localBuilder.create();
/*  924 */     localAlertDialog.setCanceledOnTouchOutside(false);
/*  925 */     localAlertDialog.show();
/*      */   }
/*      */ 
/*      */   public static void runShortCutToast(Activity paramActivity)
/*      */   {
/*  934 */     if ((ShortcutCreateUtil.canCreateShortcut(paramActivity)) && (ShortcutCreateUtil.needToast(paramActivity)))
/*  935 */       Toast.makeText(paramActivity, "桌面图标创建成功", 0).show();
/*  936 */     else if (ShortcutCreateUtil.isDisableShort(paramActivity)) {
/*  937 */       if (ShortcutCreateUtil.isSystemLauncher(paramActivity))
/*  938 */         Toast.makeText(paramActivity, "当前系统不支持创建桌面图标", 0).show();
/*      */       else
/*  940 */         Toast.makeText(paramActivity, "当前第三方的桌面不支持创建桌面图标", 0).show();
/*      */     }
/*  942 */     else if ((!ShortcutCreateUtil.isDisableShort(paramActivity)) && (!ShortcutCreateUtil.canCreateShortcut(paramActivity)))
/*      */     {
/*  944 */       Toast.makeText(paramActivity, "请切换到桌面查看桌面图标是否创建", 0).show();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean isHasShortCut(IApp paramIApp, long paramLong, String paramString)
/*      */   {
/*  959 */     if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.XIAOMI)) {
/*  960 */       if (mRunnable != null) {
/*  961 */         if ((mRunnable.getType().equals("back")) && (paramString.equals(mRunnable.getType()))) {
/*  962 */           return true;
/*      */         }
/*  964 */         removeRunHandler();
/*      */       }
/*      */ 
/*  967 */       BaseInfo.isPostChcekShortCut = true;
/*  968 */       mRunnable = new TypeRunnable()
/*      */       {
/*      */         String type;
/*      */ 
/*      */         public void run()
/*      */         {
/*  974 */           BaseInfo.isPostChcekShortCut = false;
/*  975 */           if (!this.val$app.getActivity().isFinishing()) {
/*  976 */             SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/*      */ 
/*  978 */             if (ShortCutUtil.hasShortcut(this.val$app.getActivity(), this.val$app.obtainAppName())) {
/*  979 */               localSharedPreferences.edit().putString(this.val$app.obtainAppId() + "_create_shortcut_name", this.val$app.obtainAppName()).commit();
/*  980 */               localSharedPreferences.edit().putBoolean(this.val$app.obtainAppId() + "_created_shortcut", true).commit();
/*      */ 
/*  982 */               ShortCutUtil.commitShortcut(this.val$app, 11, 0);
/*  983 */               ShortCutUtil.showCreateShortCutToast(this.val$app);
/*      */             }
/*  986 */             else if (AppPermissionUtil.getCheckShortcutOps(this.val$app.getActivity()) == 0) {
/*  987 */               ShortCutUtil.createShortcutToDeskTop(this.val$app, true);
/*  988 */               ShortCutUtil.commitShortcut(this.val$app, 11, 0);
/*  989 */               ShortCutUtil.showCreateShortCutToast(this.val$app);
/*      */             } else {
/*  991 */               ShortCutUtil.commitShortcut(this.val$app, 11, 0);
/*  992 */               AppPermissionUtil.showShortCutOpsDialog(this.val$app, this.val$app.getActivity(), this.val$app.obtainAppId(), localSharedPreferences);
/*      */             }
/*      */           }
/*      */ 
/*  996 */           ShortCutUtil.mRunnable = null;
/*      */         }
/*      */ 
/*      */         public String getType()
/*      */         {
/* 1002 */           return this.type;
/*      */         }
/*      */ 
/*      */         public void setType(String paramAnonymousString)
/*      */         {
/* 1008 */           this.type = paramAnonymousString;
/*      */         }
/*      */       };
/* 1011 */       mRunnable.setType(paramString);
/* 1012 */       MessageHandler.postDelayed(mRunnable, paramLong);
/* 1013 */       return true;
/*      */     }
/* 1015 */     return false;
/*      */   }
/*      */ 
/*      */   public static void removeRunHandler()
/*      */   {
/* 1025 */     if (mRunnable != null) {
/* 1026 */       BaseInfo.isPostChcekShortCut = false;
/* 1027 */       MessageHandler.removeCallbacks(mRunnable);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void showCreateShortCutToast(IApp paramIApp) {
/* 1032 */     String str = String.format("\"%s\"已创建桌面图标", new Object[] { paramIApp.obtainAppName() });
/*      */ 
/* 1034 */     if ((paramIApp.forceShortCut().equals("force")) && (!ShortcutCreateUtil.isDuplicateLauncher(paramIApp.getActivity()))) {
/* 1035 */       str = "“" + paramIApp.obtainAppName() + "”已创建桌面图标，如有重复请手动删除";
/* 1036 */       Toast.makeText(paramIApp.getActivity(), str, 1).show();
/* 1037 */     } else if (ShortcutCreateUtil.needToast(paramIApp.getActivity())) {
/* 1038 */       Toast.makeText(paramIApp.getActivity(), str, 1).show();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static Intent getHeadShortCutIntent(String paramString) {
/* 1043 */     Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
/*      */ 
/* 1046 */     localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
/* 1047 */     localIntent.putExtra("duplicate", false);
/* 1048 */     return localIntent;
/*      */   }
/*      */ 
/*      */   static abstract interface TypeRunnable extends Runnable
/*      */   {
/*      */     public abstract String getType();
/*      */ 
/*      */     public abstract void setType(String paramString);
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ShortCutUtil
 * JD-Core Version:    0.6.2
 */