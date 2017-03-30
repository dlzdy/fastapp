/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.os.Build;
/*     */ import android.os.Build.VERSION;
/*     */ import io.dcloud.common.util.LauncherUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class MobilePhoneModel
/*     */ {
/*  18 */   public static String MOTO = "motorola";
/*  19 */   public static String LETV = "Letv";
/*  20 */   public static String SAMSUNG = "samsung";
/*  21 */   public static String LENOVO = "Lenovo";
/*  22 */   public static String HTC = "htc";
/*  23 */   public static String XIAOMI = "Xiaomi";
/*  24 */   public static String HUAWEI = "Huawei";
/*  25 */   public static String GOOGLE = "google";
/*  26 */   public static String MEIZU = "Meizu";
/*  27 */   public static String QiKU = "QiKU";
/*  28 */   public static String VIVO = "vivo";
/*  29 */   public static String OPPO = "OPPO";
/*  30 */   public static String SMARTISAN = "SMARTISAN";
/*  31 */   public static String COOLPAD = "Coolpad";
/*  32 */   public static String YULONG = "YuLong";
/*  33 */   public static String GIONEE = "GIONEE";
/*  34 */   public static String ONEPLUS = "OnePlus";
/*  35 */   public static String ZTE = "ZTE";
/*     */ 
/*     */   /** @deprecated */
/*  69 */   private static final ArrayList<String> NO_CREATE = new ArrayList();
/*     */ 
/*     */   /** @deprecated */
/*     */   private static final ArrayList<String> CREATE_TOAST;
/*     */ 
/*     */   /** @deprecated */
/*     */   private static final ArrayList<String> CREATE_NO_TOAST;
/*     */ 
/*     */   public static boolean checkPhoneBanAcceleration(String paramString)
/*     */   {
/*  44 */     if ((paramString != null) && (Build.VERSION.SDK_INT == 21))
/*     */     {
/*  46 */       if ((paramString.equalsIgnoreCase(SAMSUNG)) || (paramString.equalsIgnoreCase(MOTO)) || (paramString.equalsIgnoreCase(LETV)) || (paramString.equalsIgnoreCase(LENOVO)) || (paramString.equalsIgnoreCase(HTC)) || (paramString.equalsIgnoreCase(MEIZU)) || (paramString.equalsIgnoreCase(XIAOMI)))
/*     */       {
/*  53 */         return false;
/*     */       }
/*  55 */       return true;
/*     */     }
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean hasToast(String paramString) {
/*  61 */     System.err.println(Build.MODEL + ";launcherName=" + paramString);
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public static boolean needToast(String paramString)
/*     */   {
/* 111 */     return CREATE_NO_TOAST.contains(paramString);
/*     */   }
/*     */ 
/*     */   public static boolean isSpecialPhone(Context paramContext)
/*     */   {
/* 120 */     String str = Build.BRAND.equals(GOOGLE) ? Build.MANUFACTURER : Build.BRAND;
/* 121 */     return (QiKU.equalsIgnoreCase(str)) || (VIVO.equalsIgnoreCase(str)) || (isSmartisanLauncherPhone(paramContext));
/*     */   }
/*     */ 
/*     */   public static boolean isSmartisanLauncherPhone(Context paramContext) {
/* 125 */     String str1 = LauncherUtil.getLauncherPackageName(paramContext);
/* 126 */     String str2 = Build.BRAND.equalsIgnoreCase(GOOGLE) ? Build.MANUFACTURER : Build.BRAND;
/* 127 */     return (SMARTISAN.equalsIgnoreCase(str2)) && (str1.contains("com.smartisanos.launcher"));
/*     */   }
/*     */ 
/*     */   public static boolean isDLGeoPhone() {
/* 131 */     String str = Build.BRAND.equals(GOOGLE) ? Build.MANUFACTURER : Build.BRAND;
/* 132 */     if ((str.equalsIgnoreCase(HUAWEI)) || (str.equalsIgnoreCase(OPPO)) || (str.equalsIgnoreCase(GIONEE)) || (str.equalsIgnoreCase(XIAOMI)) || (str.equalsIgnoreCase(QiKU)) || (str.equalsIgnoreCase(VIVO)) || (str.equalsIgnoreCase(MEIZU)) || (str.equalsIgnoreCase(LENOVO)) || (str.equalsIgnoreCase(ZTE)) || (str.equalsIgnoreCase(COOLPAD)) || (str.equalsIgnoreCase(ONEPLUS)))
/*     */     {
/* 143 */       return false;
/*     */     }
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  71 */     NO_CREATE.add("com.android.launcher3");
/*  72 */     NO_CREATE.add("com.zte.mifavor.launcher");
/*     */ 
/*  79 */     CREATE_TOAST = new ArrayList();
/*     */ 
/*  81 */     CREATE_TOAST.add("com.sec.android.app.twlauncher");
/*  82 */     CREATE_TOAST.add("com.oppo.launcher");
/*  83 */     CREATE_TOAST.add("com.tencent.qlauncher");
/*  84 */     CREATE_TOAST.add("com.sec.android.app.launcher");
/*  85 */     CREATE_TOAST.add("com.huawei.android.launcher");
/*     */ 
/*  92 */     CREATE_NO_TOAST = new ArrayList();
/*     */ 
/*  94 */     CREATE_NO_TOAST.add("com.android.launcher3");
/*  95 */     CREATE_NO_TOAST.add("com.android.launcher");
/*  96 */     CREATE_NO_TOAST.add("com.lenovo.launcher ");
/*  97 */     CREATE_NO_TOAST.add("com.cyanogenmod.trebuchet");
/*  98 */     CREATE_NO_TOAST.add("com.miui.home");
/*  99 */     CREATE_NO_TOAST.add("com.htc.launcher");
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.MobilePhoneModel
 * JD-Core Version:    0.6.2
 */