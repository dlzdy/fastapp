/*     */ package io.dcloud.common.constant;
/*     */ 
/*     */ import android.content.Intent;
/*     */ import android.net.Uri;
/*     */ import android.os.Bundle;
/*     */ import android.text.TextUtils;
/*     */ import android.webkit.URLUtil;
/*     */ import io.dcloud.common.adapter.util.SP;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.StringUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Set;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class IntentConst
/*     */ {
/*     */   public static final String APPID = "appid";
/*     */   public static final String INTENT_DATA = "http://update.dcloud.net.cn/apps/";
/*     */   public static final String SHORT_CUT_APPID = "short_cut_appid";
/*     */   public static final String SHORT_CUT_MODE = "short_cut_mode";
/*     */   public static final String QIHOO_360_BROWSER_URL = "360_browser_url";
/*     */   public static final String PROCESS_TYPE = "_process_type_";
/*  27 */   public static int PROCESS_TYPE_DEFALUT = 0;
/*     */ 
/*  29 */   public static int PROCESS_TYPE_HEAD = 1;
/*     */   public static final String SHORT_CUT_SRC = "shoort_cut_src";
/*     */   public static final String APP_SPLASH_PATH = "app_splash_path";
/*     */   public static final String WEBAPP_ACTIVITY_HAS_STREAM_SPLASH = "has_stream_splash";
/*     */   public static final String WEBAPP_ACTIVITY_HIDE_STREAM_SPLASH = "hide_stream_splash";
/*     */   public static final String WEBAPP_ACTIVITY_JUST_DOWNLOAD = "just_download";
/*     */   public static final String WEBAPP_ACTIVITY_APPICON = "app_icon";
/*     */   public static final String WEBAPP_ACTIVITY_APPEXTERN = "app_extern";
/*     */   public static final String WEBAPP_ACTIVITY_APPNAME = "app_name";
/*     */   public static final String WEBAPP_ACTIVITY_SPLASH_MODE = "__splash_mode__";
/*     */   public static final String WEBAPP_ACTIVITY_LAUNCH_PATH = "__launch_path__";
/*     */   public static final String WEBAPP_SHORT_CUT_CLASS_NAME = "short_cut_class_name";
/*     */   public static final String FROM_SHORT_CUT_STRAT = "from_short_cut_start";
/*     */   public static final String FROM_BARCODE = "from_barcode";
/*     */   public static final String FROM_PUSH = "from_push";
/*     */   public static final String IS_WEBAPP_REPLY = "__webapp_reply__";
/*     */   public static final String START_FROM = "__start_from__";
/*     */   public static final int START_FROM_UNKONW = -1;
/*     */   public static final int START_FROM_STREAM_OPEN = 1;
/*     */   public static final int START_FROM_SHORT_CUT = 2;
/*     */   public static final int START_FROM_PUSH = 3;
/*     */   public static final int START_FROM_BARCODE = 4;
/*     */   public static final int START_FROM_MYAPP = 5;
/*     */   public static final int START_FROM_SECHEME = 6;
/*     */   public static final String FROM_STREAM_OPEN_FLAG = "__from_stream_open_flag__";
/*     */   public static final String FROM_STREAM_OPEN_STYLE = "__from_stream_open_style__";
/*     */   public static final String FROM_STREAM_OPEN_AUTOCLOSE = "__from_stream_open_autoclose__";
/*     */   public static final String FROM_STREAM_OPEN_TIMEOUT = "__from_stream_open_timeout__";
/*     */   public static final String NAME = "__name__";
/*     */   public static final String EXTRAS = "__extras__";
/*     */   public static final String DELETE_PUSH_BY_USER = "__by_user__";
/*     */   public static final String IS_STREAM_APP = "is_stream_app";
/*     */   public static final String STREAM_LAUNCHER = "__launcher__";
/*     */   public static final String TEST_STREAM_APP = "__am";
/*     */   public static final String PUSH_PAYLOAD = "__payload__";
/*     */   public static final String FIRST_WEB_URL = "__first_web_url__";
/*     */   public static final String IS_START_FIRST_WEB = "__start_first_web__";
/*     */   public static final String START_FORCE_SHORT = "__sc";
/*     */   public static final String QIHOO_START_PARAM_FROM = "from";
/*     */   public static final String STREAMSDK_ACTIONBAR_PARAM = "__actionbar__";
/*     */   public static final String QIHOO_START_PARAM_MODE = "mode";
/*     */   public static final String PENDING_INTENT_MODE = "__pending_intent_mode__";
/*     */   public static final String PENDING_INTENT_MODE_ACTIVITY = "__pending_intent_mode_activity__";
/*     */   public static final String PENDING_INTENT_MODE_SERVICE = "__pending_intent_mode_service__";
/*     */   public static final String RUNING_STREAPP_LAUNCHER = "plus.runtime.launcher";
/*     */   public static final String SPLASH_VIEW = "__splash_view__";
/*     */   public static final String PL_UPDATE = "__plugin_update__";
/*     */   public static final String PL_AUTO_HIDE = "__plugin_auto_hide__";
/*     */   public static final String PL_AUTO_HIDE_SHOW_PN = "__plugin_auto_hide_show_pname__";
/*     */   public static final String PL_AUTO_HIDE_SHOW_ACTIVITY = "__plugin_auto_hide_show_activity__";
/* 102 */   private static ArrayList<String> TO_JS_CANT_USE_KEYS = new ArrayList();
/*     */ 
/*     */   public static boolean allowToHtml(String paramString) {
/* 105 */     return (!TO_JS_CANT_USE_KEYS.contains(paramString)) && (!paramString.startsWith("com.morgoo.droidplugin"));
/*     */   }
/*     */ 
/*     */   public static Intent modifyStartFrom(Intent paramIntent)
/*     */   {
/* 152 */     if ((paramIntent != null) && (paramIntent.getIntExtra("__start_from__", -1) == -1)) {
/* 153 */       if (paramIntent.getBooleanExtra("from_short_cut_start", false))
/* 154 */         paramIntent.putExtra("__start_from__", 2);
/* 155 */       else if (paramIntent.getBooleanExtra("from_push", false))
/* 156 */         paramIntent.putExtra("__start_from__", 3);
/* 157 */       else if (paramIntent.getBooleanExtra("from_barcode", false)) {
/* 158 */         paramIntent.putExtra("__start_from__", 4);
/*     */       }
/*     */     }
/* 161 */     return paramIntent;
/*     */   }
/*     */ 
/*     */   public static Intent removeArgs(Intent paramIntent, String paramString)
/*     */   {
/* 172 */     if (paramIntent == null) return paramIntent;
/* 173 */     Uri localUri = paramIntent.getData();
/* 174 */     if ((localUri != null) && (!URLUtil.isNetworkUrl(localUri.toString()))) {
/* 175 */       int i = localUri.toString().indexOf("://");
/* 176 */       paramIntent.setData(Uri.parse(localUri.toString().substring(0, i + "://".length())));
/*     */     }
/* 178 */     if (paramIntent.getExtras() != null) {
/* 179 */       Bundle localBundle = paramIntent.getExtras();
/* 180 */       if (localBundle != null) {
/* 181 */         Set localSet = localBundle.keySet();
/* 182 */         if (localSet != null) {
/* 183 */           int j = localSet.size();
/* 184 */           String[] arrayOfString = new String[j];
/* 185 */           localSet.toArray(arrayOfString);
/* 186 */           for (int k = j - 1; k >= 0; k--) {
/* 187 */             String str = arrayOfString[k];
/* 188 */             if (allowToHtml(str)) {
/* 189 */               localBundle.remove(str);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 195 */     return paramIntent;
/*     */   }
/*     */   public static String obtainArgs(Intent paramIntent, String paramString) {
/* 198 */     if (paramIntent == null) return "";
/* 199 */     Uri localUri = paramIntent.getData();
/*     */     String str1;
/* 200 */     if ((localUri != null) && (!URLUtil.isNetworkUrl(localUri.toString()))) {
/* 201 */       str1 = BaseInfo.getLaunchType(paramIntent);
/* 202 */       BaseInfo.putLauncherData(paramString, str1);
/* 203 */       saveType(paramString, str1);
/*     */ 
/* 205 */       return localUri.toString() + "";
/* 206 */     }if (paramIntent.getExtras() != null) {
/* 207 */       str1 = BaseInfo.getLaunchType(paramIntent);
/* 208 */       BaseInfo.putLauncherData(paramString, str1);
/* 209 */       saveType(paramString, str1);
/* 210 */       if ((paramIntent != null) && (paramIntent.hasExtra("__extras__"))) {
/* 211 */         return paramIntent.getStringExtra("__extras__");
/*     */       }
/* 213 */       JSONObject localJSONObject = new JSONObject();
/* 214 */       Bundle localBundle = paramIntent.getExtras();
/*     */       Object localObject;
/* 215 */       if (localBundle != null) {
/* 216 */         localObject = localBundle.keySet();
/* 217 */         if (localObject != null) {
/* 218 */           int i = ((Set)localObject).size();
/* 219 */           String[] arrayOfString = new String[i];
/* 220 */           ((Set)localObject).toArray(arrayOfString);
/* 221 */           for (int j = 0; j < i; j++) {
/* 222 */             String str3 = arrayOfString[j];
/* 223 */             if (allowToHtml(str3)) {
/*     */               try {
/* 225 */                 localJSONObject.put(str3, localBundle.get(str3).toString());
/*     */               } catch (Exception localException) {
/* 227 */                 localException.printStackTrace();
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 233 */       if (TextUtils.equals(str1, "push")) {
/* 234 */         localObject = paramIntent.getStringExtra("__payload__");
/* 235 */         String str2 = StringUtil.getSCString((String)localObject, "__sc");
/* 236 */         if (!TextUtils.isEmpty(str2)) {
/* 237 */           paramIntent.putExtra("__sc", str2);
/*     */         }
/* 239 */         return TextUtils.isEmpty((CharSequence)localObject) ? "" : localObject;
/*     */       }
/* 241 */       if (localJSONObject.length() > 0) {
/* 242 */         return localJSONObject.toString();
/*     */       }
/* 244 */       return "";
/*     */     }
/*     */ 
/* 247 */     return "";
/*     */   }
/*     */ 
/*     */   private static void saveType(String paramString1, String paramString2)
/*     */   {
/* 252 */     String str = SP.getBundleData("pdr", paramString1 + "LAUNCHTYPE");
/* 253 */     if (TextUtils.isEmpty(str))
/* 254 */       SP.setBundleData("pdr", paramString1 + "LAUNCHTYPE", paramString2);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 108 */     TO_JS_CANT_USE_KEYS.add("__name__");
/* 109 */     TO_JS_CANT_USE_KEYS.add("__from_stream_open_autoclose__");
/* 110 */     TO_JS_CANT_USE_KEYS.add("__from_stream_open_timeout__");
/* 111 */     TO_JS_CANT_USE_KEYS.add("__start_from__");
/* 112 */     TO_JS_CANT_USE_KEYS.add("__plugin_auto_hide__");
/* 113 */     TO_JS_CANT_USE_KEYS.add("__plugin_auto_hide_show_pname__");
/* 114 */     TO_JS_CANT_USE_KEYS.add("__plugin_auto_hide_show_activity__");
/* 115 */     TO_JS_CANT_USE_KEYS.add("__splash_view__");
/* 116 */     TO_JS_CANT_USE_KEYS.add("__from_stream_open_style__");
/* 117 */     TO_JS_CANT_USE_KEYS.add("__from_stream_open_flag__");
/* 118 */     TO_JS_CANT_USE_KEYS.add("__launcher__");
/* 119 */     TO_JS_CANT_USE_KEYS.add("short_cut_appid");
/* 120 */     TO_JS_CANT_USE_KEYS.add("appid");
/* 121 */     TO_JS_CANT_USE_KEYS.add("mode");
/* 122 */     TO_JS_CANT_USE_KEYS.add("short_cut_mode");
/* 123 */     TO_JS_CANT_USE_KEYS.add("shoort_cut_src");
/* 124 */     TO_JS_CANT_USE_KEYS.add("__am");
/* 125 */     TO_JS_CANT_USE_KEYS.add("from");
/* 126 */     TO_JS_CANT_USE_KEYS.add("__sc");
/* 127 */     TO_JS_CANT_USE_KEYS.add("app_splash_path");
/* 128 */     TO_JS_CANT_USE_KEYS.add("has_stream_splash");
/* 129 */     TO_JS_CANT_USE_KEYS.add("app_icon");
/* 130 */     TO_JS_CANT_USE_KEYS.add("app_extern");
/* 131 */     TO_JS_CANT_USE_KEYS.add("app_name");
/* 132 */     TO_JS_CANT_USE_KEYS.add("from_short_cut_start");
/* 133 */     TO_JS_CANT_USE_KEYS.add("from_barcode");
/* 134 */     TO_JS_CANT_USE_KEYS.add("from_push");
/* 135 */     TO_JS_CANT_USE_KEYS.add("__by_user__");
/* 136 */     TO_JS_CANT_USE_KEYS.add("is_stream_app");
/* 137 */     TO_JS_CANT_USE_KEYS.add("short_cut_class_name");
/* 138 */     TO_JS_CANT_USE_KEYS.add("just_download");
/* 139 */     TO_JS_CANT_USE_KEYS.add("hide_stream_splash");
/* 140 */     TO_JS_CANT_USE_KEYS.add("has_stream_splash");
/* 141 */     TO_JS_CANT_USE_KEYS.add("__first_web_url__");
/* 142 */     TO_JS_CANT_USE_KEYS.add("__start_first_web__");
/* 143 */     TO_JS_CANT_USE_KEYS.add("plus.runtime.launcher");
/* 144 */     TO_JS_CANT_USE_KEYS.add("__splash_mode__");
/* 145 */     TO_JS_CANT_USE_KEYS.add("__launch_path__");
/* 146 */     TO_JS_CANT_USE_KEYS.add("_process_type_");
/* 147 */     TO_JS_CANT_USE_KEYS.add("__webapp_reply__");
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.constant.IntentConst
 * JD-Core Version:    0.6.2
 */