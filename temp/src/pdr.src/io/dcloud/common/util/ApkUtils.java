/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.pm.ApplicationInfo;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.content.pm.PackageManager.NameNotFoundException;
/*     */ import android.content.pm.Signature;
/*     */ import android.content.res.Resources;
/*     */ import android.net.Uri;
/*     */ import android.os.Build.VERSION;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.util.Log;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class ApkUtils
/*     */ {
/*     */   private static final String TAG = "ApkUtils";
/*     */   private static final int APP_TYPE_ALL = 0;
/*     */   private static final int APP_TYPE_NON_SYSTEM = 1;
/*     */   private static final int APP_TYPE_SYSTEM = 2;
/*     */   public static final int APK_INSTALLED = 0;
/*     */   public static final int APK_UNINSTALLED = -2;
/*     */   public static final int APK_UPGRADE = 1;
/*     */   public static final int APK_DOWNGRADE = -1;
/* 611 */   public static final String[] coreApks = { "com.android.phone", "com.android.inputmethod.latin", "android", "com.android.bluetooth", "com.android.certinstaller", "com.android.sidekick", "com.google.android.gsf", "com.google.android.partnersetup", "com.android.htmlviewer", "com.android.wallpaper.livepicker", "com.android.stk", "com.android.providers.userdictionary", "com.android.packageinstaller", "com.android.providers.telocation", "com.android.email", "com.android.providers.telephony", "com.android.calculator2", "com.android.providers.contacts", "com.android.browser", "com.android.monitor", "com.android.soundrecorder", "com.android.providers.media", "com.android.launcher", "com.android.calendar", "com.android.providers.calendar", "com.android.defcontainer", "com.android.settings", "com.android.providers.settings", "com.android.deskclock", "com.android.providers.drm", "com.android.providers.applications", "com.android.contacts", "com.android.gallery", "com.google.android.location", "com.android.fileexplorer", "com.android.updater", "com.android.providers.downloads.ui", "com.android.providers.downloads", "com.android.mms", "com.android.server.vpn", "com.android.providers.subscribedfeeds", "com.android.thememanager", "com.android.systemui", "com.android.wallpaper", "com.google.android.gm", "com.google.android.backup", "com.google.android.syncadapters.calendar", "com.google.android.syncadapters.contacts", "com.android.vending.updater", "com.android.vending", "com.google.android.feedback", "com.google.android.street", "com.android.setupwizard", "com.google.android.googlequicksearchbox", "com.google.android.apps.uploader", "com.android.camera", "com.google.android.apps.genie.geniewidget", "com.android.music", "com.android.musicvis", "com.google.android.voicesearch", "com.noshufou.android.su", "com.qihoo.root", "com.lbe.security.miui", "com.lbe.security.su", "com.lbe.security.shuame", "eu.chainfire.supersu", "com.miui.uac", "com.android.protips" };
/*     */ 
/*     */   public static int getInstallState(PackageInfo paramPackageInfo, int paramInt)
/*     */   {
/*     */     int i;
/*  44 */     if (paramPackageInfo == null)
/*  45 */       i = -2;
/*  46 */     else if (paramPackageInfo.versionCode == paramInt)
/*  47 */       i = 0;
/*  48 */     else if (paramPackageInfo.versionCode < paramInt)
/*  49 */       i = 1;
/*     */     else
/*  51 */       i = -1;
/*  52 */     return i;
/*     */   }
/*     */ 
/*     */   public static PackageInfo getInstalledApp(Context paramContext, PackageManager paramPackageManager, String paramString)
/*     */   {
/*  57 */     PackageInfo localPackageInfo = null;
/*     */ 
/*  59 */     paramPackageManager = paramContext.getPackageManager();
/*     */     try {
/*  61 */       localPackageInfo = paramPackageManager.getPackageInfo(paramString, 0);
/*     */     } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
/*  63 */       localNameNotFoundException.printStackTrace();
/*     */     }
/*     */ 
/*  66 */     return localPackageInfo;
/*     */   }
/*     */ 
/*     */   public static String getInstalledAppNameByPackageInfo(Context paramContext, PackageManager paramPackageManager, PackageInfo paramPackageInfo)
/*     */   {
/*  71 */     if (paramPackageManager == null) {
/*  72 */       paramPackageManager = paramContext.getPackageManager();
/*     */     }
/*  74 */     return paramPackageManager.getApplicationLabel(paramPackageInfo.applicationInfo).toString();
/*     */   }
/*     */ 
/*     */   public static List<PackageInfo> getInstalledApps(Context paramContext) {
/*  78 */     PackageManager localPackageManager = paramContext.getPackageManager();
/*  79 */     return getInstalledApps(paramContext, localPackageManager);
/*     */   }
/*     */ 
/*     */   public static List<PackageInfo> getInstalledApps(Context paramContext, PackageManager paramPackageManager)
/*     */   {
/*  87 */     return getAllApps(paramContext, paramPackageManager, 0);
/*     */   }
/*     */ 
/*     */   public static List<PackageInfo> getInstalledNonSystemApps(Context paramContext, PackageManager paramPackageManager)
/*     */   {
/*  95 */     return getAllApps(paramContext, paramPackageManager, 1);
/*     */   }
/*     */ 
/*     */   public static List<PackageInfo> getInstalledSystemApps(Context paramContext, PackageManager paramPackageManager)
/*     */   {
/* 103 */     return getAllApps(paramContext, paramPackageManager, 2);
/*     */   }
/*     */ 
/*     */   private static List<PackageInfo> getAllApps(Context paramContext, PackageManager paramPackageManager, int paramInt)
/*     */   {
/* 108 */     ArrayList localArrayList = new ArrayList();
/*     */ 
/* 110 */     List localList = paramPackageManager.getInstalledPackages(512);
/*     */     Iterator localIterator;
/*     */     PackageInfo localPackageInfo;
/* 112 */     switch (paramInt) {
/*     */     case 0:
/* 114 */       for (localIterator = localList.iterator(); localIterator.hasNext(); ) { localPackageInfo = (PackageInfo)localIterator.next();
/* 115 */         localArrayList.add(localPackageInfo);
/*     */       }
/* 117 */       break;
/*     */     case 1:
/* 120 */       for (localIterator = localList.iterator(); localIterator.hasNext(); ) { localPackageInfo = (PackageInfo)localIterator.next();
/* 121 */         if (!isSystemApp(localPackageInfo.applicationInfo)) {
/* 122 */           localArrayList.add(localPackageInfo);
/*     */         }
/*     */       }
/* 125 */       break;
/*     */     case 2:
/* 128 */       for (localIterator = localList.iterator(); localIterator.hasNext(); ) { localPackageInfo = (PackageInfo)localIterator.next();
/* 129 */         if (isSystemApp(localPackageInfo.applicationInfo)) {
/* 130 */           localArrayList.add(localPackageInfo);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 135 */     return localArrayList;
/*     */   }
/*     */ 
/*     */   public static List<String> getInstalledAppPkgs(Context paramContext) {
/* 139 */     ArrayList localArrayList = new ArrayList();
/*     */ 
/* 141 */     List localList = paramContext.getPackageManager().getInstalledPackages(0);
/*     */ 
/* 143 */     for (PackageInfo localPackageInfo : localList) {
/* 144 */       if (!isSystemApp(localPackageInfo.applicationInfo)) {
/* 145 */         localArrayList.add(localPackageInfo.packageName);
/*     */       }
/*     */     }
/*     */ 
/* 149 */     return localArrayList;
/*     */   }
/*     */ 
/*     */   public static List<PackageInfo> getInstalledPackages(PackageManager paramPackageManager, int paramInt)
/*     */   {
/* 154 */     List localList = paramPackageManager.getInstalledPackages(paramInt);
/* 155 */     return localList;
/*     */   }
/*     */ 
/*     */   public static boolean isApkInstalled(Context paramContext, String paramString) {
/* 159 */     if (paramString == null) {
/* 160 */       return false;
/*     */     }
/* 162 */     if (paramString.equals(paramContext.getPackageName())) {
/* 163 */       return true;
/*     */     }
/* 165 */     boolean bool = false;
/*     */     try
/*     */     {
/* 168 */       bool = paramContext.getPackageManager().getPackageInfo(paramString, 512) != null;
/*     */     }
/*     */     catch (PackageManager.NameNotFoundException localNameNotFoundException) {
/* 171 */       localNameNotFoundException.printStackTrace();
/*     */     }
/*     */ 
/* 174 */     return bool;
/*     */   }
/*     */ 
/*     */   public static boolean isApkInstalled(Context paramContext, String paramString1, String paramString2)
/*     */   {
/* 179 */     if (paramString1 == null) {
/* 180 */       return false;
/*     */     }
/* 182 */     if (paramString1.equals(paramContext.getPackageName())) {
/* 183 */       return true;
/*     */     }
/* 185 */     boolean bool = false;
/*     */     try
/*     */     {
/* 188 */       PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramString1, 512);
/*     */ 
/* 190 */       if (localPackageInfo == null) {
/* 191 */         return false;
/*     */       }
/* 193 */       bool = String.valueOf(localPackageInfo.versionCode).equals(paramString2);
/*     */     } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
/* 195 */       localNameNotFoundException.printStackTrace();
/*     */     }
/*     */ 
/* 198 */     return bool;
/*     */   }
/*     */ 
/*     */   public static void uninstall(Context paramContext, String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 241 */       Uri localUri = Uri.fromParts("package", paramString, null);
/* 242 */       Intent localIntent = new Intent("android.intent.action.DELETE", localUri);
/* 243 */       localIntent.addFlags(268435456);
/* 244 */       paramContext.startActivity(localIntent);
/*     */     } catch (Exception localException) {
/* 246 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean openApp(Context paramContext, String paramString)
/*     */   {
/* 260 */     Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
/*     */ 
/* 262 */     if (localIntent != null) {
/* 263 */       paramContext.startActivity(localIntent);
/* 264 */       return true;
/*     */     }
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isSystemApp(ApplicationInfo paramApplicationInfo)
/*     */   {
/* 277 */     if ((paramApplicationInfo.flags & 0x1) > 0) {
/* 278 */       if ((paramApplicationInfo.publicSourceDir.startsWith("data/dataapp")) || (paramApplicationInfo.publicSourceDir.startsWith("/data/dataapp")))
/*     */       {
/* 280 */         return false;
/*     */       }
/* 282 */       return true;
/*     */     }
/* 284 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getAppSignatureMd5(Context paramContext, String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 296 */       PackageManager localPackageManager = paramContext.getPackageManager();
/* 297 */       PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramString, 64);
/*     */ 
/* 299 */       byte[] arrayOfByte = localPackageInfo.signatures[0].toByteArray();
/* 300 */       return Md5Utils.md5LowerCase(Arrays.toString(arrayOfByte));
/*     */     } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
/*     */     }
/* 303 */     return null;
/*     */   }
/*     */ 
/*     */   private static String[] getPackageSignatures(Context paramContext, String paramString)
/*     */   {
/*     */     try {
/* 309 */       PackageManager localPackageManager = paramContext.getPackageManager();
/* 310 */       PackageInfo localPackageInfo = localPackageManager.getPackageArchiveInfo(paramString, 64);
/*     */ 
/* 312 */       if ((localPackageInfo != null) && (localPackageInfo.signatures != null) && (localPackageInfo.signatures.length > 0))
/*     */       {
/* 314 */         byte[] arrayOfByte = localPackageInfo.signatures[0].toByteArray();
/* 315 */         String str1 = HashUtils.getHash(Arrays.toString(arrayOfByte)).toLowerCase();
/*     */ 
/* 317 */         String str2 = localPackageInfo.applicationInfo.packageName;
/* 318 */         return new String[] { str1, str2 };
/*     */       }
/*     */     } catch (Throwable localThrowable) {
/* 321 */       localThrowable.printStackTrace();
/*     */     }
/* 323 */     return null;
/*     */   }
/*     */ 
/*     */   public static Object parsePackage(String paramString, int paramInt)
/*     */   {
/*     */     try
/*     */     {
/*     */       Object localObject1;
/* 331 */       if (Build.VERSION.SDK_INT >= 21) {
/* 332 */         localObject1 = ReflectUtils.getObjectConstructor("android.content.pm.PackageParser", new Class[0]).newInstance(new Object[0]);
/*     */       }
/*     */       else {
/* 335 */         localObject1 = ReflectUtils.getObjectConstructor("android.content.pm.PackageParser", new Class[] { String.class }).newInstance(new Object[] { paramString });
/*     */       }
/*     */ 
/* 340 */       DisplayMetrics localDisplayMetrics = new DisplayMetrics();
/* 341 */       localDisplayMetrics.setToDefaults();
/* 342 */       File localFile = new File(paramString);
/*     */       Method localMethod;
/*     */       Object localObject2;
/* 350 */       if (Build.VERSION.SDK_INT >= 21) {
/* 351 */         localMethod = localObject1.getClass().getMethod("parsePackage", new Class[] { File.class, Integer.TYPE });
/*     */ 
/* 353 */         localObject2 = localMethod.invoke(localObject1, new Object[] { localFile, Integer.valueOf(paramInt) });
/*     */       }
/*     */       else {
/* 356 */         localMethod = localObject1.getClass().getMethod("parsePackage", new Class[] { File.class, String.class, DisplayMetrics.class, Integer.TYPE });
/*     */ 
/* 359 */         localObject2 = localMethod.invoke(localObject1, new Object[] { localFile, paramString, localDisplayMetrics, Integer.valueOf(paramInt) });
/*     */       }
/*     */ 
/* 362 */       if (localObject2 == null) {
/* 363 */         Log.d("ApkUtils", "---parsePackage is null------;;sourceFile=" + localFile.getAbsolutePath());
/*     */ 
/* 365 */         return null;
/*     */       }
/*     */ 
/* 368 */       if (Build.VERSION.SDK_INT >= 21) {
/* 369 */         localMethod = localObject1.getClass().getDeclaredMethod("collectCertificates", new Class[] { ReflectUtils.classForName("android.content.pm.PackageParser$Package"), File.class, Integer.TYPE });
/*     */ 
/* 376 */         localMethod.setAccessible(true);
/* 377 */         localMethod.invoke(localObject1, new Object[] { localObject2, localFile, Integer.valueOf(1) });
/*     */       } else {
/* 379 */         localMethod = localObject1.getClass().getDeclaredMethod("collectCertificates", new Class[] { ReflectUtils.classForName("android.content.pm.PackageParser$Package"), Integer.TYPE });
/*     */ 
/* 386 */         localMethod.invoke(localObject1, new Object[] { localObject2, Integer.valueOf(1) });
/*     */       }
/* 388 */       return localObject2;
/*     */     } catch (Exception localException) {
/* 390 */       localException.printStackTrace();
/*     */     }
/* 392 */     return null;
/*     */   }
/*     */ 
/*     */   public static String[] getApkFileSignatureAndPackageName(Context paramContext, String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 409 */       String[] arrayOfString = getPackageSignatures(paramContext, paramString);
/*     */ 
/* 411 */       if (arrayOfString != null) {
/* 412 */         return arrayOfString;
/*     */       }
/*     */ 
/* 416 */       Object localObject = null;
/*     */       try
/*     */       {
/* 419 */         localObject = parsePackage(paramString, 64);
/*     */       } catch (OutOfMemoryError localOutOfMemoryError) {
/* 421 */         localObject = null;
/*     */       }
/* 423 */       if (localObject == null) {
/* 424 */         return null;
/*     */       }
/* 426 */       Signature[] arrayOfSignature = getApkSignature(localObject, paramString);
/* 427 */       if ((arrayOfSignature != null) && (arrayOfSignature.length > 0)) {
/* 428 */         byte[] arrayOfByte = arrayOfSignature[0].toByteArray();
/* 429 */         String str1 = HashUtils.getHash(Arrays.toString(arrayOfByte)).toLowerCase();
/*     */ 
/* 431 */         String str2 = (String)ReflectUtils.getObjectFieldNoDeclared(ReflectUtils.getField(localObject, "applicationInfo"), "packageName");
/*     */ 
/* 436 */         return new String[] { str1, str2 };
/*     */       }
/* 438 */       return null;
/*     */     }
/*     */     catch (Exception localException) {
/* 441 */       localException.printStackTrace();
/* 442 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Signature[] getApkSignature(Object paramObject, String paramString)
/*     */   {
/* 448 */     Signature[] arrayOfSignature = new Signature[0];
/*     */     try {
/* 450 */       arrayOfSignature = (Signature[])ReflectUtils.getField(paramObject, "mSignatures");
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/* 452 */       localNoSuchFieldException.printStackTrace();
/*     */     } catch (IllegalAccessException localIllegalAccessException) {
/* 454 */       localIllegalAccessException.printStackTrace();
/*     */     }
/* 456 */     if (arrayOfSignature == null) {
/* 457 */       return null;
/*     */     }
/* 459 */     if (arrayOfSignature.length <= 0) {
/* 460 */       return null;
/*     */     }
/* 462 */     return arrayOfSignature;
/*     */   }
/*     */ 
/*     */   public static String[] getApkFileSignatureAndPackageNameEx(Context paramContext, String paramString)
/*     */   {
/*     */     try {
/* 468 */       Object localObject = parsePackage(paramString, 64);
/* 469 */       if (localObject != null) {
/* 470 */         Signature[] arrayOfSignature = (Signature[])ReflectUtils.getField(localObject, "mSignatures");
/*     */ 
/* 472 */         if ((arrayOfSignature == null) || (arrayOfSignature.length == 0)) {
/* 473 */           return null;
/*     */         }
/*     */ 
/* 476 */         byte[] arrayOfByte = arrayOfSignature[0].toByteArray();
/* 477 */         String str1 = HashUtils.getHash(Arrays.toString(arrayOfByte)).toLowerCase();
/*     */ 
/* 479 */         String str2 = (String)ReflectUtils.getField(localObject, "packageName");
/*     */ 
/* 481 */         return new String[] { str1, str2 };
/*     */       }
/*     */     } catch (Exception localException) {
/* 484 */       localException.printStackTrace();
/* 485 */       return null;
/*     */     }
/*     */ 
/* 488 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean isValidAppPackageName(String paramString) {
/* 492 */     String str = "^[a-zA-Z_]\\w*(\\.[a-zA-Z_]\\w*)*$";
/* 493 */     Pattern localPattern = Pattern.compile(str);
/* 494 */     Matcher localMatcher = localPattern.matcher(paramString);
/* 495 */     return localMatcher.matches();
/*     */   }
/*     */ 
/*     */   public static String getApkFileLable(Context paramContext, String paramString)
/*     */   {
/* 500 */     String str1 = "android.content.pm.PackageParser";
/* 501 */     String str2 = "android.content.res.AssetManager";
/*     */     try {
/* 503 */       String str3 = paramString;
/*     */ 
/* 505 */       Class localClass1 = Class.forName(str1);
/* 506 */       Class[] arrayOfClass = new Class[1];
/* 507 */       arrayOfClass[0] = String.class;
/*     */       Constructor localConstructor1;
/* 509 */       if (Build.VERSION.SDK_INT >= 21)
/* 510 */         localConstructor1 = localClass1.getConstructor(new Class[0]);
/*     */       else {
/* 512 */         localConstructor1 = localClass1.getConstructor(arrayOfClass);
/*     */       }
/*     */ 
/* 515 */       Object[] arrayOfObject = new Object[1];
/* 516 */       arrayOfObject[0] = str3;
/*     */       Object localObject1;
/* 518 */       if (Build.VERSION.SDK_INT >= 21)
/* 519 */         localObject1 = localConstructor1.newInstance(new Object[0]);
/*     */       else {
/* 521 */         localObject1 = localConstructor1.newInstance(arrayOfObject);
/*     */       }
/*     */ 
/* 524 */       Log.d("DownloadUtils", "pkgParser:" + localObject1.toString());
/*     */ 
/* 526 */       DisplayMetrics localDisplayMetrics = new DisplayMetrics();
/*     */ 
/* 528 */       localDisplayMetrics.setToDefaults();
/*     */ 
/* 531 */       if (Build.VERSION.SDK_INT >= 21) {
/* 532 */         arrayOfClass = new Class[2];
/* 533 */         arrayOfClass[0] = File.class;
/* 534 */         arrayOfClass[1] = Integer.TYPE;
/*     */       } else {
/* 536 */         arrayOfClass = new Class[4];
/* 537 */         arrayOfClass[0] = File.class;
/* 538 */         arrayOfClass[1] = String.class;
/* 539 */         arrayOfClass[2] = DisplayMetrics.class;
/* 540 */         arrayOfClass[3] = Integer.TYPE;
/*     */       }
/* 542 */       Method localMethod1 = localClass1.getDeclaredMethod("parsePackage", arrayOfClass);
/*     */ 
/* 545 */       if (Build.VERSION.SDK_INT >= 21) {
/* 546 */         arrayOfObject = new Object[2];
/* 547 */         arrayOfObject[0] = new File(str3);
/* 548 */         arrayOfObject[1] = Integer.valueOf(0);
/*     */       } else {
/* 550 */         arrayOfObject = new Object[4];
/* 551 */         arrayOfObject[0] = new File(str3);
/* 552 */         arrayOfObject[1] = str3;
/* 553 */         arrayOfObject[2] = localDisplayMetrics;
/* 554 */         arrayOfObject[3] = Integer.valueOf(0);
/*     */       }
/*     */ 
/* 557 */       Object localObject2 = localMethod1.invoke(localObject1, arrayOfObject);
/*     */ 
/* 560 */       Field localField = localObject2.getClass().getDeclaredField("applicationInfo");
/*     */ 
/* 563 */       ApplicationInfo localApplicationInfo = (ApplicationInfo)localField.get(localObject2);
/*     */ 
/* 566 */       Log.d("DownloadUtils", "pkg:" + localApplicationInfo.packageName + " uid=" + localApplicationInfo.uid);
/*     */ 
/* 569 */       Class localClass2 = Class.forName(str2);
/*     */ 
/* 571 */       Constructor localConstructor2 = localClass2.getConstructor((Class[])null);
/*     */ 
/* 573 */       Object localObject3 = localConstructor2.newInstance((Object[])null);
/* 574 */       arrayOfClass = new Class[1];
/* 575 */       arrayOfClass[0] = String.class;
/* 576 */       Method localMethod2 = localClass2.getDeclaredMethod("addAssetPath", arrayOfClass);
/*     */ 
/* 578 */       arrayOfObject = new Object[1];
/* 579 */       arrayOfObject[0] = str3;
/* 580 */       localMethod2.invoke(localObject3, arrayOfObject);
/* 581 */       Resources localResources = paramContext.getResources();
/*     */ 
/* 583 */       arrayOfClass = new Class[3];
/* 584 */       arrayOfClass[0] = localObject3.getClass();
/* 585 */       arrayOfClass[1] = localResources.getDisplayMetrics().getClass();
/* 586 */       arrayOfClass[2] = localResources.getConfiguration().getClass();
/*     */ 
/* 588 */       Constructor localConstructor3 = Resources.class.getConstructor(arrayOfClass);
/*     */ 
/* 590 */       arrayOfObject = new Object[3];
/* 591 */       arrayOfObject[0] = localObject3;
/* 592 */       arrayOfObject[1] = localResources.getDisplayMetrics();
/* 593 */       arrayOfObject[2] = localResources.getConfiguration();
/*     */ 
/* 595 */       localResources = (Resources)localConstructor3.newInstance(arrayOfObject);
/*     */ 
/* 597 */       CharSequence localCharSequence = null;
/* 598 */       if (localApplicationInfo.labelRes != 0) {
/* 599 */         localCharSequence = localResources.getText(localApplicationInfo.labelRes);
/*     */       }
/*     */ 
/* 602 */       Log.d("DownloadUtils", "label=" + localCharSequence);
/* 603 */       return localCharSequence.toString();
/*     */     }
/*     */     catch (Exception localException) {
/* 606 */       localException.printStackTrace();
/*     */     }
/* 608 */     return "-1";
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ApkUtils
 * JD-Core Version:    0.6.2
 */