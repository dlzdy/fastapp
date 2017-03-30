/*     */ package io.dcloud;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.pm.ApplicationInfo;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.text.TextUtils;
/*     */ import io.dcloud.application.DCloudApplication;
/*     */ import io.dcloud.common.DHInterface.IReflectAble;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ public class PdrR
/*     */   implements IReflectAble
/*     */ {
/*  18 */   private static String a = null;
/*     */ 
/*  69 */   public static int DRAWABLE_SPLASH = RInformation.getInt("drawable", "splash");
/*  70 */   public static int DRAWABLE_ICON = AndroidResources.mApplicationInfo.applicationInfo.icon;
/*  71 */   public static int LAYOUT_SNOW_WHITE_PROGRESS = RInformation.getInt("layout", "dcloud_snow_white_progress");
/*  72 */   public static int LAYOUT_SNOW_BLACK_PROGRESS = RInformation.getInt("layout", "dcloud_snow_black_progress");
/*  73 */   public static int ID_PROGRESSBAR = RInformation.getInt("id", "progressBar");
/*  74 */   public static int FEATURE_LOSS_STYLE = RInformation.getInt("style", "featureLossDialog");
/*     */ 
/*  78 */   public static int ID_ICON_SPLASH = RInformation.getInt("id", "iv_icon_splash_dcloud");
/*  79 */   public static int ID_TEXT_COPYRIGHT_SPLASH = RInformation.getInt("id", "tv_copyright_splash_dcloud");
/*  80 */   public static int ID_TEXT_LOADING_SPLASH = RInformation.getInt("id", "tv_loading_splash_dcloud");
/*  81 */   public static int ID_TEXT_NAME_SPLASH = RInformation.getInt("id", "tv_name_splash_dcloud");
/*     */ 
/*  85 */   public static int LAYOUT_CUSTION_NOTIFICATION_DCLOUD = RInformation.getInt("layout", "dcloud_custom_notification");
/*  86 */   public static int ID_IMAGE_NOTIFICATION_DCLOUD = RInformation.getInt("id", "image");
/*  87 */   public static int ID_TITLE_NOTIFICATION_DCLOUD = RInformation.getInt("id", "title");
/*  88 */   public static int ID_TEXT_NOTIFICATION = RInformation.getInt("id", "text");
/*  89 */   public static int ID_TIME_NOTIFICATION_DCLOUD = RInformation.getInt("id", "time");
/*     */ 
/*  92 */   public static int DRAWABLE_DCLOUD_DIALOG_SHAPE = RInformation.getInt("drawable", "dcloud_dialog_shape");
/*  93 */   public static int LAYOUT_DIALOG_LAYOUT_DCLOUD_DIALOG = RInformation.getInt("layout", "dcloud_dialog");
/*  94 */   public static int ID_DCLOUD_DIALOG_ROOTVIEW = RInformation.getInt("id", "dcloud_dialog_rootview");
/*  95 */   public static int ID_DCLOUD_DIALOG_TITLE = RInformation.getInt("id", "dcloud_dialog_title");
/*  96 */   public static int ID_DCLOUD_DIALOG_ICON = RInformation.getInt("id", "dcloud_dialog_icon");
/*  97 */   public static int ID_DCLOUD_DIALOG_MSG = RInformation.getInt("id", "dcloud_dialog_msg");
/*  98 */   public static int ID_DCLOUD_DIALOG_BTN1 = RInformation.getInt("id", "dcloud_dialog_btn1");
/*  99 */   public static int ID_DCLOUD_DIALOG_BTN2 = RInformation.getInt("id", "dcloud_dialog_btn2");
/*     */ 
/* 101 */   public static int STYLE_DIALOG_DCLOUD_DEFALUT_DIALOG = RInformation.getInt("style", "dcloud_defalut_dialog");
/* 102 */   public static int STYLE_DIALOG_STYLE_DCLOUD_ANIM_DIALOG_WINDOW_IN_OUT = RInformation.getInt("style", "dcloud_anim_dialog_window_in_out");
/* 103 */   public static int ANIM_DIALOG_ANIM_DCLOUD_SLIDE_IN_FROM_TOP = RInformation.getInt("anim", "dcloud_slide_in_from_top");
/* 104 */   public static int ANIM_DIALOG_ANIM_DCLOUD_SLIDE_OUT_TO_TOP = RInformation.getInt("anim", "dcloud_slide_out_to_top");
/*     */ 
/* 107 */   public static int STREAMAPP_DELETE_THEME = RInformation.getInt("style", "streamDelete19Dialog");
/* 108 */   public static int STREAMAPP_DRAWABLE_APPDEFULTICON = RInformation.getInt("drawable", "dcloud_streamapp_icon_appdefault");
/*     */ 
/* 111 */   public static int DRAWBLE_PROGRESSBAR_BLACK_DCLOUD = RInformation.getInt("drawable", "dcloud_snow_black_progress");
/* 112 */   public static int DRAWBLE_PROGRESSBAR_WHITE_DCLOUD = RInformation.getInt("drawable", "dcloud_snow_white_progress");
/*     */ 
/* 115 */   public static int DRAWEBL_SHADOW_LEFT = RInformation.getInt("drawable", "dcloud_shadow_left");
/*     */ 
/*     */   public static void init(Context paramContext)
/*     */   {
/*  21 */     if (paramContext != null)
/*  22 */       a = paramContext.getPackageName();
/*     */   }
/*     */ 
/*     */   public static void checkInit() {
/*  26 */     if (TextUtils.isEmpty(a))
/*  27 */       if ((DCloudAdapterUtil.isPlugin()) && (!TextUtils.isEmpty(DCloudAdapterUtil.getPageName()))) {
/*  28 */         a = DCloudAdapterUtil.getPageName();
/*     */       }
/*  30 */       else if (DeviceInfo.sApplicationContext != null)
/*  31 */         init(DeviceInfo.sApplicationContext);
/*  32 */       else if (DCloudApplication.getInstance() != null)
/*  33 */         init(DCloudApplication.getInstance());
/*     */   }
/*     */ 
/*     */   public static int getInt(String paramString1, String paramString2)
/*     */   {
/*     */     try
/*     */     {
/*  40 */       checkInit();
/*  41 */       return Class.forName(a + ".R$" + paramString1).getField(paramString2).getInt(null);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/*  43 */       Logger.e("Not found " + a + ".R$" + paramString1 + "." + paramString2);
/*  44 */       if (BaseInfo.ISDEBUG)
/*  45 */         localNoSuchFieldException.printStackTrace();
/*     */     }
/*     */     catch (Exception localException) {
/*  48 */       Logger.e("StreamSDK", "come into exception e.getMessage()=====" + localException.getMessage() + "    type=====" + paramString1 + "    name=====" + paramString2 + "sPackageName==" + a);
/*  49 */       if (BaseInfo.ISDEBUG) {
/*  50 */         localException.printStackTrace();
/*     */       }
/*     */     }
/*  53 */     return 0;
/*     */   }
/*     */   public static int[] getIntArray(String paramString1, String paramString2) {
/*     */     try {
/*  57 */       checkInit();
/*  58 */       return (int[])Class.forName(a + ".R$" + paramString1).getField(paramString2).get(null);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/*  60 */       localNoSuchFieldException.printStackTrace();
/*  61 */       Logger.e("Not found " + a + ".R." + paramString1 + "." + paramString2);
/*     */     } catch (Exception localException) {
/*  63 */       Logger.e("Not init RInfomation sPackageName=" + a);
/*  64 */       localException.printStackTrace();
/*     */     }
/*  66 */     return null;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.PdrR
 * JD-Core Version:    0.6.2
 */