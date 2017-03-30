/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.os.Build;
/*     */ import android.os.Build.VERSION;
/*     */ import android.text.TextUtils;
/*     */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ShortcutCreateUtil
/*     */ {
/*  22 */   public static final String TAG = ShortcutCreateUtil.class.getSimpleName();
/*     */   private static final boolean DEFALT_VALUE = true;
/*  31 */   private static List<String> hasToastTipPackages = new ArrayList();
/*     */ 
/*  35 */   private static List<String> noToastTipPackages = new ArrayList();
/*     */ 
/*  39 */   private static List<String> disablePackages = new ArrayList();
/*     */ 
/*  44 */   private static List<String> selfDisablePackages = new ArrayList();
/*     */ 
/*  48 */   private static List<String> thirdDisablePackages = new ArrayList();
/*     */ 
/*  53 */   private static List<String> deletePackages = new ArrayList();
/*     */ 
/*  58 */   private static List<String> systemLauncher = new ArrayList();
/*     */ 
/*  63 */   private static List<String> duplicatePackages = new ArrayList();
/*     */ 
/*     */   private static List<String> getDuplicatePackages()
/*     */   {
/* 248 */     return duplicatePackages;
/*     */   }
/*     */ 
/*     */   private static List<String> getHasTipPackageList() {
/* 252 */     return hasToastTipPackages;
/*     */   }
/*     */ 
/*     */   private static List<String> getNoTipPackageList() {
/* 256 */     return noToastTipPackages;
/*     */   }
/*     */ 
/*     */   private static List<String> getDisablePackageList()
/*     */   {
/* 261 */     if (disablePackages.size() == 0) {
/* 262 */       disablePackages.addAll(selfDisablePackages);
/* 263 */       disablePackages.addAll(thirdDisablePackages);
/*     */     }
/* 265 */     return disablePackages;
/*     */   }
/*     */ 
/*     */   private static List<String> getSystemLauncherList() {
/* 269 */     return systemLauncher;
/*     */   }
/*     */ 
/*     */   public static boolean canCreateShortcut(Context paramContext)
/*     */   {
/* 280 */     String str = LauncherUtil.getLauncherPackageName(paramContext);
/*     */ 
/* 282 */     if (TextUtils.isEmpty(str)) {
/* 283 */       return true;
/*     */     }
/*     */ 
/* 288 */     if (("com.android.launcher3".equalsIgnoreCase(str)) && ("vivo".equalsIgnoreCase(getBrand())))
/*     */     {
/* 291 */       return false;
/*     */     }
/* 293 */     if ((str.equals("com.android.launcher3")) && (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN))) {
/* 294 */       return true;
/*     */     }
/* 296 */     if ((Build.BRAND.equalsIgnoreCase(MobilePhoneModel.COOLPAD)) && (str.equals("com.yulong.android.launcher3")) && (Build.VERSION.SDK_INT >= 19)) {
/* 297 */       return false;
/*     */     }
/* 299 */     if ((getHasTipPackageList().contains(str)) || (getNoTipPackageList().contains(str)))
/*     */     {
/* 302 */       return true;
/*     */     }
/* 304 */     if (getDisablePackageList().contains(str))
/*     */     {
/* 306 */       return false;
/*     */     }
/*     */ 
/* 309 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean needToast(Context paramContext)
/*     */   {
/* 319 */     return getNoTipPackageList().contains(LauncherUtil.getLauncherPackageName(paramContext));
/*     */   }
/*     */ 
/*     */   public static String getBrand()
/*     */   {
/* 328 */     return Build.BRAND;
/*     */   }
/*     */ 
/*     */   public static boolean isDisableShort(Context paramContext)
/*     */   {
/* 338 */     String str = LauncherUtil.getLauncherPackageName(paramContext);
/*     */ 
/* 340 */     if ((str.equals("com.bbk.launcher2")) && (Build.VERSION.SDK_INT >= 21)) {
/* 341 */       return false;
/*     */     }
/*     */ 
/* 344 */     if (("com.oppo.launcher".equals(str)) && (Build.VERSION.SDK_INT >= 21)) {
/* 345 */       return true;
/*     */     }
/* 347 */     if ((str.equals("com.android.launcher3")) && (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN))) {
/* 348 */       return false;
/*     */     }
/* 350 */     if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU)) {
/* 351 */       return false;
/*     */     }
/* 353 */     if ((Build.BRAND.equalsIgnoreCase(MobilePhoneModel.COOLPAD)) && (str.equals("com.yulong.android.launcher3")) && (Build.VERSION.SDK_INT >= 19)) {
/* 354 */       return true;
/*     */     }
/* 356 */     if (MobilePhoneModel.isSpecialPhone(paramContext))
/*     */     {
/* 358 */       return false;
/*     */     }
/* 360 */     return getDisablePackageList().contains(str);
/*     */   }
/*     */ 
/*     */   public static boolean isSystemLauncher(Context paramContext)
/*     */   {
/* 370 */     return getSystemLauncherList().contains(LauncherUtil.getLauncherPackageName(paramContext));
/*     */   }
/*     */ 
/*     */   public static boolean isDeleteLaucher(Context paramContext)
/*     */   {
/* 380 */     return deletePackages.contains(LauncherUtil.getLauncherPackageName(paramContext));
/*     */   }
/*     */ 
/*     */   public static boolean isDuplicateLauncher(Context paramContext)
/*     */   {
/* 390 */     String str = LauncherUtil.getLauncherPackageName(paramContext);
/*     */ 
/* 392 */     if ((str.equals("com.android.launcher3")) && (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.SMARTISAN))) {
/* 393 */       return true;
/*     */     }
/* 395 */     return duplicatePackages.contains(LauncherUtil.getLauncherPackageName(paramContext));
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  67 */     noToastTipPackages.add("com.miui.home");
/*  68 */     noToastTipPackages.add("com.miui.mihome2");
/*     */ 
/*  70 */     noToastTipPackages.add("com.htc.launcher");
/*     */ 
/*  72 */     noToastTipPackages.add("com.huawei.launcher2");
/*     */ 
/*  74 */     noToastTipPackages.add("com.zte.mifavor.launcher");
/*     */ 
/*  76 */     noToastTipPackages.add("com.cyanogenmod.trebuchet");
/*     */ 
/*  78 */     noToastTipPackages.add("com.lenovo.launcher");
/*     */ 
/*  80 */     noToastTipPackages.add("com.you.launcher");
/*     */ 
/*  82 */     noToastTipPackages.add("com.android.launcher3");
/*  83 */     noToastTipPackages.add("com.meizu.flyme.easylauncher");
/*  84 */     noToastTipPackages.add("com.meizu.flyme.launcher");
/*     */ 
/*  86 */     noToastTipPackages.add("com.ztemt.launcher");
/*     */ 
/*  88 */     noToastTipPackages.add("cn.nubia.launcher");
/*     */ 
/*  90 */     noToastTipPackages.add("com.zte.lqsoft.launcher");
/*     */ 
/*  92 */     noToastTipPackages.add("com.yulong.android.launcher3");
/*     */ 
/*  94 */     noToastTipPackages.add("com.google.android.googlequicksearchbox");
/*     */ 
/* 100 */     hasToastTipPackages.add("com.android.launcher");
/* 101 */     hasToastTipPackages.add("com.android.launcher2");
/*     */ 
/* 103 */     hasToastTipPackages.add("com.oppo.launcher");
/*     */ 
/* 105 */     hasToastTipPackages.add("com.dianxinos.dxhome");
/*     */ 
/* 107 */     hasToastTipPackages.add("com.xsg.launcher");
/*     */ 
/* 109 */     hasToastTipPackages.add("com.sec.android.app.launcher");
/* 110 */     hasToastTipPackages.add("com.sec.android.app.twlauncher");
/*     */ 
/* 112 */     hasToastTipPackages.add("com.qihoo360.launcher");
/*     */ 
/* 114 */     hasToastTipPackages.add("com.huawei.android.launcher");
/*     */ 
/* 116 */     hasToastTipPackages.add("com.sonyericsson.home");
/*     */ 
/* 121 */     thirdDisablePackages.add("com.nd.android.pandahome2");
/*     */ 
/* 123 */     thirdDisablePackages.add("com.gau.go.launcherex");
/*     */ 
/* 125 */     thirdDisablePackages.add("com.Dean.launcher");
/*     */ 
/* 127 */     thirdDisablePackages.add("com.moxiu.launcher");
/*     */ 
/* 129 */     thirdDisablePackages.add("com.tencent.launcher");
/*     */ 
/* 131 */     thirdDisablePackages.add("com.apusapps.launcher");
/*     */ 
/* 133 */     thirdDisablePackages.add("com.baoruan.launcher2");
/*     */ 
/* 135 */     thirdDisablePackages.add("com.lx.launcher");
/*     */ 
/* 137 */     thirdDisablePackages.add("com.ltp.launcherpad");
/*     */ 
/* 139 */     thirdDisablePackages.add("com.zui.launcher");
/*     */ 
/* 141 */     thirdDisablePackages.add("com.lewa.launcher5");
/*     */ 
/* 143 */     thirdDisablePackages.add("com.mycheering.launcher");
/*     */ 
/* 145 */     thirdDisablePackages.add("com.jeejen.family");
/*     */ 
/* 150 */     selfDisablePackages.add("com.smartisanos.launcher");
/*     */ 
/* 154 */     selfDisablePackages.add("com.zte.mifavor.launcher");
/*     */ 
/* 156 */     selfDisablePackages.add("com.bbk.launcher2");
/*     */ 
/* 160 */     selfDisablePackages.add("com.gionee.amisystem");
/*     */ 
/* 164 */     selfDisablePackages.add("com.oneplus.hydrogen.launcher");
/*     */ 
/* 168 */     selfDisablePackages.add("com.sonyericsson.setupwizard");
/*     */ 
/* 170 */     selfDisablePackages.add("com.gionee.navil");
/*     */ 
/* 172 */     selfDisablePackages.add("com.sec.android.app.easylauncher");
/*     */ 
/* 174 */     selfDisablePackages.add("com.nbbsw.launcherdoov");
/*     */ 
/* 176 */     selfDisablePackages.add("com.huaqin.launcherEx");
/*     */ 
/* 178 */     selfDisablePackages.add("com.ibingo.launcher");
/*     */ 
/* 184 */     deletePackages.add("com.sec.android.app.launcher");
/* 185 */     deletePackages.add("com.sec.android.app.twlauncher");
/*     */ 
/* 187 */     deletePackages.add("com.huawei.android.launcher");
/*     */ 
/* 189 */     deletePackages.add("com.htc.launcher");
/*     */ 
/* 191 */     deletePackages.add("com.android.launcher");
/* 192 */     deletePackages.add("com.android.launcher2");
/*     */ 
/* 197 */     systemLauncher.add("com.huawei.android.launcher");
/* 198 */     systemLauncher.add("com.huawei.launcher2");
/*     */ 
/* 200 */     systemLauncher.add("com.miui.home");
/* 201 */     systemLauncher.add("com.miui.mihome2");
/*     */ 
/* 203 */     systemLauncher.add("com.android.launcher2");
/*     */ 
/* 205 */     systemLauncher.add("com.sec.android.app.launcher");
/* 206 */     systemLauncher.add("com.sec.android.app.twlauncher");
/* 207 */     systemLauncher.add("com.cyanogenmod.trebuchet");
/*     */ 
/* 209 */     systemLauncher.add("com.lenovo.launcher");
/*     */ 
/* 211 */     systemLauncher.add("com.zte.mifavor.launcher");
/*     */ 
/* 213 */     systemLauncher.add("com.android.launcher");
/*     */ 
/* 215 */     systemLauncher.add("com.oppo.launcher");
/*     */ 
/* 217 */     systemLauncher.add("com.htc.launcher");
/*     */ 
/* 219 */     systemLauncher.add("com.sonyericsson.home");
/*     */ 
/* 221 */     systemLauncher.add("com.android.launcher3");
/*     */ 
/* 223 */     systemLauncher.add("com.yulong.android.launcher3");
/*     */ 
/* 225 */     systemLauncher.add("com.oneplus.hydrogen.launcher");
/*     */ 
/* 227 */     systemLauncher.add("com.smartisanos.launcher");
/*     */ 
/* 232 */     duplicatePackages.add("com.huawei.launcher2");
/* 233 */     duplicatePackages.add("com.miui.home");
/* 234 */     duplicatePackages.add("com.miui.mihome2");
/* 235 */     duplicatePackages.add("com.lenovo.launcher");
/*     */ 
/* 237 */     duplicatePackages.add("com.sec.android.app.twlauncher");
/*     */ 
/* 239 */     duplicatePackages.add("com.yulong.android.launcher3");
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ShortcutCreateUtil
 * JD-Core Version:    0.6.2
 */