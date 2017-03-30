/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.SharedPreferences;
/*     */ import android.content.SharedPreferences.Editor;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class SP
/*     */ {
/*     */   public static final String N_BASE = "pdr";
/*     */   private static final String N_SMART_UPDATE = "_smart_update";
/*     */   public static final String K_SMART_UPDATE_PARAMS = "_smart_update_need_update";
/*     */   public static final String K_SMART_UPDATE_PACKAGE_DOWNLOAD_SUCCESS = "_smart_update_packge_success";
/*     */   public static final String K_SMART_UPDATE_NEED_UPDATE_ICON = "_smart_update_need_update_icon";
/*     */   public static final String N_STORAGES = "_storages";
/*     */   public static final String K_STORAGES_SHORTCUT = "SHORTCUT";
/*     */   public static final String K_CREATED_SHORTCUT = "_created_shortcut";
/*     */   public static final String K_CREATE_SHORTCUT_NAME = "_create_shortcut_name";
/*     */   public static final String SMART_UPDATE = "pdr";
/*     */   public static final String UPDATE_PARAMS = "_smart_update_need_update";
/*     */   public static final String UPDATE_PACKAGE_DOWNLOAD_SUCCESS = "_smart_update_packge_success";
/*     */   public static final String NEED_UPDATE_ICON = "_smart_update_need_update_icon";
/*     */   public static final String STREAM_APP_NOT_FOUND_SPLASH_AT_SERVER = "_no_splash_at_server";
/*     */   public static final String IS_CREATE_SHORTCUT = "_is_create_shortcut";
/*     */   public static final String K_DEVICE_DPUSH_UUID = "_dpush_uuid_";
/*     */   public static final String K_LAST_POS = "_dpush_last_pos_";
/*     */   public static final String K_COLLECTED = "_dpush_collected_";
/*     */   public static final String K_SHORT_CUT_ONE_TIPS = "short_cut_one_tips";
/*     */   public static final String RECORD_RUN_SHORT_CUT = "record_run_short_cut";
/*     */   public static final String REPAIR_FIRST_SHORT_CUT = "repaid_first_short_cut";
/*     */   public static final String STAREMAPP_FIRST_SHORT_CUT = "_staremapp_first_short_cut";
/*     */   public static final String UPDATE_SPLASH_IMG_PATH = "update_splash_img_path";
/*     */   public static final String UPDATE_SPLASH_AUTOCLOSE = "__update_splash_autoclose";
/*     */   public static final String UPDATE_SPLASH_DELAY = "__update_splash_delay";
/*     */   public static final String UPDATE_SPLASH_AUTOCLOSE_W2A = "__update_splash_autoclose_w2a";
/*     */   public static final String UPDATE_SPLASH_DELAY_W2A = "__update_splash_delay_w2a";
/*     */   public static final String WELCOME_SPLASH_SHOW = "__welcome_splash_show";
/*     */   public static final String CHECK_PATH_STREAMAPP = "check_path_streamapp";
/*  96 */   private static HashMap<String, SharedPreferences> mBundles = null;
/*     */ 
/*     */   public static String getBundleData(String paramString1, String paramString2)
/*     */   {
/*  90 */     SharedPreferences localSharedPreferences = getOrCreateBundle(paramString1);
/*  91 */     String str = localSharedPreferences.getString(paramString2, null);
/*  92 */     return str;
/*     */   }
/*     */ 
/*     */   public static synchronized SharedPreferences getOrCreateBundle(String paramString)
/*     */   {
/* 105 */     if (mBundles == null) {
/* 106 */       mBundles = new HashMap(1);
/*     */     }
/* 108 */     SharedPreferences localSharedPreferences = (SharedPreferences)mBundles.get(paramString);
/* 109 */     if (localSharedPreferences == null) {
/* 110 */       localSharedPreferences = DeviceInfo.sApplicationContext.getSharedPreferences(paramString, 0);
/*     */ 
/* 112 */       mBundles.put(paramString, localSharedPreferences);
/*     */     }
/* 114 */     return localSharedPreferences;
/*     */   }
/*     */ 
/*     */   public static void setBundleData(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
/*     */   {
/* 131 */     SharedPreferences localSharedPreferences = paramSharedPreferences;
/* 132 */     SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 133 */     localEditor.putString(paramString1, paramString2);
/* 134 */     localEditor.commit();
/*     */   }
/*     */ 
/*     */   public static void removeBundleData(SharedPreferences paramSharedPreferences, String paramString)
/*     */   {
/* 148 */     SharedPreferences localSharedPreferences = paramSharedPreferences;
/* 149 */     SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 150 */     localEditor.remove(paramString);
/* 151 */     localEditor.commit();
/*     */   }
/*     */ 
/*     */   public static void clearBundle(SharedPreferences paramSharedPreferences)
/*     */   {
/* 167 */     SharedPreferences localSharedPreferences = paramSharedPreferences;
/* 168 */     SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 169 */     localEditor.clear();
/* 170 */     localEditor.commit();
/*     */   }
/*     */ 
/*     */   public static void setBundleData(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 187 */     SharedPreferences localSharedPreferences = getOrCreateBundle(paramString1);
/* 188 */     SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 189 */     localEditor.putString(paramString2, paramString3);
/* 190 */     localEditor.commit();
/*     */   }
/*     */ 
/*     */   public static void removeBundleData(String paramString1, String paramString2)
/*     */   {
/* 204 */     SharedPreferences localSharedPreferences = getOrCreateBundle(paramString1);
/* 205 */     SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 206 */     localEditor.remove(paramString2);
/* 207 */     localEditor.commit();
/*     */   }
/*     */ 
/*     */   public static void clearBundle(String paramString)
/*     */   {
/* 223 */     SharedPreferences localSharedPreferences = getOrCreateBundle(paramString);
/* 224 */     SharedPreferences.Editor localEditor = localSharedPreferences.edit();
/* 225 */     localEditor.clear();
/* 226 */     localEditor.commit();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.SP
 * JD-Core Version:    0.6.2
 */