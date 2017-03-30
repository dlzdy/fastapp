/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.Notification;
/*     */ import android.app.NotificationManager;
/*     */ import android.app.PendingIntent;
/*     */ import android.content.ActivityNotFoundException;
/*     */ import android.content.ComponentName;
/*     */ import android.content.ContentResolver;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.Intent.ShortcutIconResource;
/*     */ import android.content.pm.ApplicationInfo;
/*     */ import android.content.pm.PackageInfo;
/*     */ import android.content.pm.PackageManager;
/*     */ import android.content.res.AssetManager;
/*     */ import android.content.res.Resources;
/*     */ import android.database.Cursor;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Bitmap.Config;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Rect;
/*     */ import android.net.Uri;
/*     */ import android.os.Build;
/*     */ import android.text.TextUtils;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.View;
/*     */ import android.view.Window;
/*     */ import io.dcloud.common.DHInterface.ICallBack;
/*     */ import io.dcloud.common.util.IOUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.StringUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class PlatformUtil extends SP
/*     */ {
/*  50 */   public static boolean APS_COVER = false;
/*     */ 
/* 161 */   private static int _SCREEN_WIDTH = 0;
/*     */ 
/* 173 */   private static int _SCREEN_HEIGHT = 0;
/*     */ 
/* 185 */   private static int _SCREEN_STATUSBAR_HEIGHT = 0;
/*     */ 
/* 225 */   private static int _SCREEN_CONTENT_HEIGHT = 0;
/*     */ 
/* 306 */   private static int[] _pixels = null;
/*     */ 
/* 326 */   private static int[] _blackpixels = null;
/*     */   public static final byte ASSETS_RESOUCE = 0;
/*     */   public static final byte SRC_RESOUCE = 1;
/*     */   public static final byte FILE_RESOUCE = 2;
/*     */   private static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
/*     */   private static final String EXTRA_SHORTCUT_DUPLICATE = "duplicate";
/*     */   private static final String URI_HTC_LAUNCER = "content://com.htc.launcher.settings/favorites?notify=true";
/* 753 */   private static final String[] PROJECTION = { "_id", "title", "iconResource" };
/*     */   private static final String URI_SAMSUNG_LAUNCER = "content://com.sec.android.app.twlauncher.settings/favorites?notify=true";
/* 824 */   private static int MAX_SPAN_IN_ONE_SCREEN = 16;
/*     */ 
/*     */   public static void init(Context paramContext)
/*     */   {
/*  47 */     DeviceInfo.sApplicationContext = paramContext;
/*     */   }
/*     */ 
/*     */   public static InputStream getResInputStream(String paramString)
/*     */   {
/*  62 */     InputStream localInputStream = null;
/*     */     try {
/*  64 */       localInputStream = AndroidResources.sAssetMgr.open(useAndroidPath(paramString), 2);
/*     */     }
/*     */     catch (RuntimeException localRuntimeException)
/*     */     {
/*  68 */       Logger.e("PlatformUtil.getResInputStream RuntimeException pFilePath=" + paramString);
/*     */     } catch (FileNotFoundException localFileNotFoundException) {
/*  70 */       Logger.e("PlatformUtil.getResInputStream FileNotFoundException pFilePath=" + paramString);
/*     */     } catch (IOException localIOException) {
/*  72 */       Logger.e("PlatformUtil.getResInputStream IOException pFilePath=" + paramString);
/*     */     }
/*  74 */     return localInputStream;
/*     */   }
/*     */ 
/*     */   public static String[] listResFiles(String paramString)
/*     */   {
/*     */     try
/*     */     {
/*  87 */       paramString = useAndroidPath(paramString);
/*  88 */       return AndroidResources.sAssetMgr.list(paramString);
/*     */     } catch (IOException localIOException) {
/*  90 */       Logger.w("PlatformUtil.listResFiles pPath=" + paramString, localIOException);
/*     */     }
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */   public static String[] listFsAppsFiles(String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 106 */       return new File(paramString).list();
/*     */     } catch (Exception localException) {
/* 108 */       Logger.w("PlatformUtil.listResFiles pPath=" + paramString, localException);
/*     */     }
/* 110 */     return null;
/*     */   }
/*     */ 
/*     */   private static String useAndroidPath(String paramString)
/*     */   {
/* 122 */     return StringUtil.trimString(StringUtil.trimString(paramString, '/'), '\\');
/*     */   }
/*     */ 
/*     */   public static void openFileBySystem(Context paramContext, String paramString1, String paramString2, ICallBack paramICallBack) {
/* 126 */     int i = !TextUtils.isEmpty(paramString2) ? 1 : 0;
/* 127 */     int j = i != 0 ? hasAppInstalled(paramContext, paramString2) : 0;
/*     */     try {
/* 129 */       Intent localIntent = new Intent();
/* 130 */       if (j != 0) {
/* 131 */         localIntent.setPackage(paramString2);
/*     */       }
/* 133 */       localIntent.addFlags(268435456);
/* 134 */       localIntent.setAction("android.intent.action.VIEW");
/*     */ 
/* 136 */       str = PdrUtil.getMimeType(paramString1);
/* 137 */       if (paramString1.startsWith("file://")) {
/* 138 */         paramString1 = paramString1.substring("file://".length());
/*     */       }
/* 140 */       File localFile = new File(paramString1);
/*     */       Object localObject;
/* 141 */       if (localFile.exists())
/*     */       {
/* 143 */         localObject = Uri.fromFile(localFile);
/* 144 */         localIntent.setDataAndType((Uri)localObject, str.toLowerCase());
/* 145 */         paramContext.startActivity(localIntent);
/*     */       }
/* 147 */       else if (paramICallBack != null) {
/* 148 */         localObject = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(0), "not found file" });
/* 149 */         paramICallBack.onCallBack(-1, localObject);
/*     */       }
/*     */     }
/*     */     catch (ActivityNotFoundException localActivityNotFoundException)
/*     */     {
/*     */       String str;
/* 153 */       Logger.w(localActivityNotFoundException);
/* 154 */       if (paramICallBack != null) {
/* 155 */         str = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(1), "not found 3th activity" });
/* 156 */         paramICallBack.onCallBack(-1, str);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int SCREEN_WIDTH(Context paramContext)
/*     */   {
/* 167 */     if (_SCREEN_WIDTH == 0) {
/* 168 */       _SCREEN_WIDTH = paramContext.getResources().getDisplayMetrics().widthPixels;
/*     */     }
/* 170 */     return _SCREEN_WIDTH;
/*     */   }
/*     */ 
/*     */   public static int SCREEN_HEIGHT(Context paramContext)
/*     */   {
/* 179 */     if (_SCREEN_HEIGHT == 0) {
/* 180 */       _SCREEN_HEIGHT = paramContext.getResources().getDisplayMetrics().heightPixels;
/*     */     }
/* 182 */     return _SCREEN_HEIGHT;
/*     */   }
/*     */ 
/*     */   public static int MESURE_SCREEN_STATUSBAR_HEIGHT(Activity paramActivity)
/*     */   {
/* 193 */     if (_SCREEN_STATUSBAR_HEIGHT == 0) {
/* 194 */       Rect localRect = new Rect();
/* 195 */       paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
/* 196 */       _SCREEN_STATUSBAR_HEIGHT = localRect.top;
/* 197 */       _SCREEN_CONTENT_HEIGHT = localRect.height();
/*     */ 
/* 199 */       if ((_SCREEN_STATUSBAR_HEIGHT < 0) || (_SCREEN_CONTENT_HEIGHT > SCREEN_HEIGHT(paramActivity))) {
/* 200 */         _SCREEN_STATUSBAR_HEIGHT = 0;
/* 201 */         _SCREEN_CONTENT_HEIGHT = SCREEN_HEIGHT(paramActivity);
/*     */       }
/*     */     }
/* 204 */     return _SCREEN_STATUSBAR_HEIGHT;
/*     */   }
/*     */ 
/*     */   public static int MESURE_SCREEN_CONTENT_HEIGHT(Activity paramActivity) {
/* 208 */     if (_SCREEN_CONTENT_HEIGHT == 0) {
/* 209 */       Rect localRect = new Rect();
/* 210 */       paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
/* 211 */       _SCREEN_STATUSBAR_HEIGHT = localRect.top;
/* 212 */       _SCREEN_CONTENT_HEIGHT = localRect.height();
/*     */ 
/* 214 */       if ((_SCREEN_STATUSBAR_HEIGHT < 0) || (_SCREEN_CONTENT_HEIGHT > SCREEN_HEIGHT(paramActivity))) {
/* 215 */         _SCREEN_STATUSBAR_HEIGHT = 0;
/* 216 */         _SCREEN_CONTENT_HEIGHT = SCREEN_HEIGHT(paramActivity);
/*     */       }
/*     */     }
/* 219 */     return _SCREEN_CONTENT_HEIGHT;
/*     */   }
/*     */ 
/*     */   public static void RESET_H_W()
/*     */   {
/* 228 */     _SCREEN_WIDTH = 0;
/* 229 */     _SCREEN_HEIGHT = 0;
/* 230 */     _SCREEN_STATUSBAR_HEIGHT = 0;
/* 231 */     _SCREEN_CONTENT_HEIGHT = 0;
/* 232 */     _pixels = null;
/*     */   }
/*     */ 
/*     */   public static Bitmap captureView(View paramView)
/*     */   {
/* 272 */     return captureView(paramView, false, null);
/*     */   }
/*     */ 
/*     */   public static Bitmap captureView(View paramView, Bitmap.Config paramConfig) {
/* 276 */     return captureView(paramView, false, null, paramConfig);
/*     */   }
/*     */ 
/*     */   public static Bitmap captureView(View paramView, boolean paramBoolean, Rect paramRect) {
/* 280 */     return captureView(paramView, paramBoolean, paramRect, Bitmap.Config.RGB_565);
/*     */   }
/*     */ 
/*     */   public static Bitmap captureView(View paramView, boolean paramBoolean, Rect paramRect, Bitmap.Config paramConfig) {
/* 284 */     int i = paramRect != null ? paramRect.width() : paramView.getMeasuredWidth();
/* 285 */     int j = paramRect != null ? paramRect.height() : paramView.getMeasuredHeight();
/* 286 */     if (i == 0) {
/* 287 */       return null;
/*     */     }
/* 289 */     Bitmap localBitmap = Bitmap.createBitmap(i, j, paramConfig);
/* 290 */     Canvas localCanvas = new Canvas(localBitmap);
/*     */ 
/* 292 */     if (paramRect != null) {
/* 293 */       localCanvas.translate(-paramRect.left, -paramRect.top);
/*     */     }
/*     */ 
/* 297 */     paramView.draw(localCanvas);
/*     */ 
/* 299 */     if ((paramBoolean) && (isWhiteBitmap(localBitmap))) {
/* 300 */       localBitmap.recycle();
/* 301 */       return null;
/*     */     }
/* 303 */     return localBitmap;
/*     */   }
/*     */ 
/*     */   private static int[] GET_WIHTE_LINE(int paramInt)
/*     */   {
/* 318 */     if (_pixels == null) {
/* 319 */       _pixels = new int[paramInt];
/* 320 */       for (int i = 0; i < _pixels.length; i++) {
/* 321 */         _pixels[i] = -1;
/*     */       }
/*     */     }
/* 324 */     return _pixels;
/*     */   }
/*     */ 
/*     */   private static int[] GET_BLACK_LINE(int paramInt) {
/* 328 */     if (_blackpixels == null) {
/* 329 */       _blackpixels = new int[paramInt];
/* 330 */       for (int i = 0; i < _blackpixels.length; i++) {
/* 331 */         _blackpixels[i] = -16777216;
/*     */       }
/*     */     }
/* 334 */     return _blackpixels;
/*     */   }
/*     */ 
/*     */   public static boolean isWhiteBitmap(Bitmap paramBitmap)
/*     */   {
/* 359 */     return isWhiteBitmap(paramBitmap, false);
/*     */   }
/*     */ 
/*     */   public static boolean isWhiteBitmap(Bitmap paramBitmap, boolean paramBoolean)
/*     */   {
/* 364 */     int i = paramBitmap.getHeight();
/* 365 */     boolean bool = false;
/* 366 */     int[] arrayOfInt = new int[i];
/*     */ 
/* 368 */     paramBitmap.getPixels(arrayOfInt, 0, 1, paramBitmap.getWidth() / 4, 0, 1, i);
/* 369 */     bool = Arrays.equals(arrayOfInt, GET_WIHTE_LINE(i));
/* 370 */     paramBitmap.getPixels(arrayOfInt, 0, 1, paramBitmap.getWidth() / 2, 0, 1, i);
/* 371 */     bool &= Arrays.equals(arrayOfInt, GET_WIHTE_LINE(i));
/* 372 */     paramBitmap.getPixels(arrayOfInt, 0, 1, paramBitmap.getWidth() * 3 / 4, 0, 1, i);
/* 373 */     bool &= Arrays.equals(arrayOfInt, GET_WIHTE_LINE(i));
/* 374 */     if (paramBoolean) bool |= Arrays.equals(arrayOfInt, GET_BLACK_LINE(i));
/*     */ 
/* 377 */     return bool;
/*     */   }
/*     */ 
/*     */   public static boolean isLineWhiteBitmap(Bitmap paramBitmap, boolean paramBoolean) {
/* 381 */     int i = paramBitmap.getWidth();
/* 382 */     boolean bool = false;
/* 383 */     int[] arrayOfInt = new int[i];
/*     */ 
/* 386 */     paramBitmap.getPixels(arrayOfInt, 0, paramBitmap.getWidth(), 0, 0, paramBitmap.getWidth(), 1);
/* 387 */     bool = Arrays.equals(arrayOfInt, GET_WIHTE_LINE(i));
/* 388 */     if (paramBoolean) bool |= Arrays.equals(arrayOfInt, GET_BLACK_LINE(i));
/* 389 */     return bool;
/*     */   }
/*     */ 
/*     */   public static void destroyDrawingCache(View paramView)
/*     */   {
/* 396 */     paramView.destroyDrawingCache();
/*     */   }
/*     */ 
/*     */   public static Bitmap captureView(View paramView, String paramString)
/*     */   {
/* 406 */     return captureView(paramView, null, paramString);
/*     */   }
/*     */ 
/*     */   public static Bitmap captureView(View paramView, Rect paramRect, String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 418 */       int i = 0; int j = 0; int k = paramView.getWidth(); int m = paramView.getHeight();
/* 419 */       int n = paramRect != null ? 1 : 0;
/* 420 */       if (n != 0)
/* 421 */         paramView.layout(paramRect.left, paramRect.top, paramRect.right - paramRect.left, paramRect.bottom - paramRect.top);
/*     */       else {
/* 423 */         paramView.layout(i, j, k, m);
/*     */       }
/* 425 */       paramView.setDrawingCacheEnabled(true);
/* 426 */       Bitmap localBitmap = Bitmap.createBitmap(paramView.getDrawingCache());
/* 427 */       localBitmap.setDensity(paramView.getContext().getResources().getDisplayMetrics().densityDpi);
/* 428 */       if (!PdrUtil.isEmpty(paramString)) {
/* 429 */         PdrUtil.saveBitmapToFile(localBitmap, paramString);
/*     */       }
/* 431 */       paramView.setDrawingCacheEnabled(false);
/* 432 */       if (n != 0) paramView.layout(i, j, k, m);
/* 433 */       return localBitmap;
/*     */     } catch (Exception localException) {
/* 435 */       localException.printStackTrace();
/*     */     }
/* 437 */     return null;
/*     */   }
/*     */ 
/*     */   public static void launchApplication(Context paramContext, String paramString1, String paramString2, HashMap paramHashMap)
/*     */     throws Exception
/*     */   {
/* 460 */     Intent localIntent = null;
/* 461 */     if (PdrUtil.isEmpty(paramString2))
/*     */     {
/* 463 */       localIntent = new Intent("android.intent.action.MAIN");
/*     */     }
/* 465 */     else localIntent = new Intent(paramString2);
/*     */ 
/* 467 */     if ((!PdrUtil.isEmpty(paramString1)) && 
/* 468 */       (!setPackageName(paramContext, localIntent, paramString1))) {
/* 469 */       throw new RuntimeException();
/*     */     }
/*     */ 
/* 472 */     localIntent.setFlags(270532608);
/*     */ 
/* 474 */     if ((paramHashMap != null) && (!paramHashMap.isEmpty())) {
/* 475 */       Iterator localIterator = paramHashMap.keySet().iterator();
/* 476 */       while (localIterator.hasNext()) {
/* 477 */         String str1 = (String)localIterator.next();
/* 478 */         Object localObject = paramHashMap.get(str1);
/* 479 */         if ((localObject instanceof Integer)) {
/* 480 */           int i = ((Integer)localObject).intValue();
/* 481 */           localIntent.putExtra(str1, i);
/* 482 */         } else if ((localObject instanceof String)) {
/* 483 */           String str2 = (String)localObject;
/* 484 */           localIntent.putExtra(str1, str2);
/* 485 */         } else if ((localObject instanceof Boolean)) {
/* 486 */           boolean bool = ((Boolean)localObject).booleanValue();
/* 487 */           localIntent.putExtra(str1, bool);
/*     */         }
/*     */       }
/*     */     }
/* 491 */     paramContext.startActivity(localIntent);
/*     */   }
/*     */ 
/*     */   public static void openURL(Context paramContext, String paramString1, String paramString2)
/*     */     throws Exception
/*     */   {
/* 510 */     Intent localIntent = Intent.parseUri(paramString1, 0);
/* 511 */     if (!PdrUtil.isEmpty(paramString2)) {
/* 512 */       localIntent.setPackage(paramString2);
/*     */     }
/* 514 */     localIntent.setSelector(null);
/* 515 */     localIntent.setFlags(268435456);
/* 516 */     paramContext.startActivity(localIntent);
/*     */   }
/*     */ 
/*     */   public static PackageInfo parseApkInfo(Context paramContext, String paramString) throws Exception
/*     */   {
/* 521 */     String str = paramString;
/* 522 */     PackageManager localPackageManager = paramContext.getPackageManager();
/* 523 */     PackageInfo localPackageInfo = localPackageManager.getPackageArchiveInfo(str, 1);
/*     */ 
/* 525 */     return localPackageInfo;
/*     */   }
/*     */ 
/*     */   public static boolean setPackageName(Context paramContext, Intent paramIntent, String paramString)
/*     */   {
/* 545 */     PackageManager localPackageManager = paramContext.getPackageManager();
/* 546 */     Intent localIntent = localPackageManager.getLaunchIntentForPackage(paramString);
/* 547 */     if (localIntent != null) {
/* 548 */       ComponentName localComponentName = localIntent.getComponent();
/* 549 */       String str = localComponentName.getClassName();
/*     */ 
/* 551 */       paramIntent.setClassName(paramString, str);
/* 552 */       return true;
/*     */     }
/* 554 */     return false;
/*     */   }
/*     */ 
/*     */   public static byte[] getFileContent(String paramString, int paramInt)
/*     */   {
/* 567 */     byte[] arrayOfByte = null;
/* 568 */     InputStream localInputStream = getInputStream(paramString, paramInt);
/*     */     try {
/* 570 */       if (localInputStream != null)
/* 571 */         arrayOfByte = IOUtil.getBytes(localInputStream);
/*     */     }
/*     */     catch (IOException localIOException) {
/* 574 */       localIOException.printStackTrace();
/*     */     } finally {
/* 576 */       IOUtil.close(localInputStream);
/*     */     }
/* 578 */     return arrayOfByte;
/*     */   }
/*     */   public static InputStream getInputStream(String paramString, int paramInt) {
/* 581 */     Object localObject = null;
/*     */     try {
/* 583 */       if ((DeviceInfo.sDeviceRootDir != null) && (paramString.startsWith(DeviceInfo.sDeviceRootDir))) {
/* 584 */         paramInt = 2;
/*     */       }
/* 586 */       if (paramInt == 0)
/* 587 */         localObject = getResInputStream(paramString);
/* 588 */       else if (paramInt == 1)
/* 589 */         localObject = PlatformUtil.class.getClassLoader().getResourceAsStream(paramString);
/* 590 */       else if (paramInt == 2)
/* 591 */         localObject = new FileInputStream(paramString);
/*     */     }
/*     */     catch (IOException localIOException) {
/* 594 */       localIOException.printStackTrace();
/*     */     }
/* 596 */     return localObject;
/*     */   }
/*     */   public static InputStream getInputStream(String paramString) {
/* 599 */     if (paramString == null) return null;
/* 600 */     int i = 2;
/* 601 */     if ((DeviceInfo.sDeviceRootDir != null) && (paramString.startsWith(DeviceInfo.sDeviceRootDir)))
/* 602 */       i = 2;
/*     */     else {
/* 604 */       i = 0;
/*     */     }
/* 606 */     return getInputStream(paramString, i);
/*     */   }
/*     */ 
/*     */   public static Object invokeMethod(String paramString1, String paramString2) {
/* 610 */     return invokeMethod(paramString1, paramString2, null, new Class[0], new Object[0]);
/*     */   }
/*     */ 
/*     */   public static Object invokeMethod(String paramString1, String paramString2, Object paramObject) {
/* 614 */     return invokeMethod(paramString1, paramString2, paramObject, new Class[0], new Object[0]);
/*     */   }
/*     */   public static Object invokeFieldValue(String paramString1, String paramString2, Object paramObject) {
/* 617 */     Class localClass = null;
/*     */     try {
/* 619 */       if (paramObject != null) {
/* 620 */         localClass = paramObject.getClass();
/*     */       }
/* 622 */       if (localClass == null) {
/* 623 */         localClass = Class.forName(paramString1);
/*     */       }
/* 625 */       if (localClass != null) {
/* 626 */         Field localField = localClass.getField(paramString2);
/* 627 */         if (localField != null) {
/* 628 */           localField.setAccessible(true);
/* 629 */           return localField.get(paramObject);
/*     */         }
/*     */       }
/*     */     } catch (ClassNotFoundException localClassNotFoundException) {
/* 633 */       localClassNotFoundException.printStackTrace();
/*     */     } catch (Exception localException) {
/* 635 */       localException.printStackTrace();
/*     */     }
/* 637 */     return null;
/*     */   }
/*     */   public static Object invokeMethod(String paramString1, String paramString2, Object paramObject, Class[] paramArrayOfClass, Object[] paramArrayOfObject) {
/* 640 */     Object localObject = null;
/* 641 */     String str = null;
/*     */     try {
/* 643 */       Method localMethod = Class.forName(paramString1).getMethod(paramString2, paramArrayOfClass);
/* 644 */       if (localMethod != null) {
/* 645 */         localMethod.setAccessible(true);
/* 646 */         localObject = localMethod.invoke(paramObject, paramArrayOfObject);
/*     */       }
/*     */     } catch (ClassNotFoundException localClassNotFoundException) {
/* 649 */       str = "ClassNotFoundException";
/*     */     } catch (NoSuchMethodException localNoSuchMethodException) {
/* 651 */       str = "NoSuchMethodException";
/*     */     } catch (Exception localException) {
/* 653 */       str = "Exception";
/*     */     }
/* 655 */     if ((str != null) && (!"getJsContent".equals(paramString2))) {
/* 656 */       Logger.e("platform", str + " " + paramString1 + " " + paramString2);
/*     */     }
/* 658 */     return localObject;
/*     */   }
/*     */ 
/*     */   public static boolean isEmulator()
/*     */   {
/* 666 */     return (Build.MODEL.equals("sdk")) || (Build.MODEL.equals("google_sdk"));
/*     */   }
/*     */ 
/*     */   public static void showNotification(Context paramContext, String paramString1, String paramString2, Intent paramIntent, int paramInt1, int paramInt2) {
/* 670 */     NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
/*     */ 
/* 672 */     Notification localNotification = new Notification();
/* 673 */     localNotification.icon = paramContext.getApplicationInfo().icon;
/*     */ 
/* 675 */     localNotification.defaults = 1;
/* 676 */     localNotification.flags = 16;
/*     */ 
/* 678 */     PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, paramInt2, paramIntent, 1073741824);
/* 679 */     localNotification.setLatestEventInfo(paramContext, paramString1, paramString2, localPendingIntent);
/* 680 */     localNotificationManager.notify(paramInt2, localNotification);
/*     */   }
/*     */ 
/*     */   public static boolean hasAppInstalled(Context paramContext, String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 692 */       PackageManager localPackageManager = paramContext.getPackageManager();
/* 693 */       localPackageManager.getPackageInfo(paramString, 1);
/*     */     }
/*     */     catch (Exception localException) {
/* 696 */       return false;
/*     */     }
/* 698 */     return true;
/*     */   }
/*     */ 
/*     */   public static void createShortut(Context paramContext, String paramString1, String paramString2, int paramInt, boolean paramBoolean)
/*     */   {
/* 710 */     Intent localIntent1 = new Intent("android.intent.action.MAIN");
/* 711 */     localIntent1.addCategory("android.intent.category.DEFAULT");
/* 712 */     localIntent1.setClassName(paramContext, paramString2);
/*     */ 
/* 714 */     Intent localIntent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
/* 715 */     localIntent2.putExtra("android.intent.extra.shortcut.INTENT", localIntent1);
/* 716 */     localIntent2.putExtra("android.intent.extra.shortcut.NAME", paramString1);
/* 717 */     Intent.ShortcutIconResource localShortcutIconResource = Intent.ShortcutIconResource.fromContext(paramContext, paramInt);
/* 718 */     localIntent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", localShortcutIconResource);
/* 719 */     localIntent2.putExtra("duplicate", paramBoolean);
/*     */ 
/* 721 */     paramContext.sendBroadcast(localIntent2);
/*     */   }
/*     */ 
/*     */   public static void createShortut(Context paramContext, String paramString1, String paramString2, Bitmap paramBitmap, boolean paramBoolean)
/*     */   {
/* 726 */     Intent localIntent1 = new Intent("android.intent.action.MAIN");
/* 727 */     localIntent1.addCategory("android.intent.category.DEFAULT");
/* 728 */     localIntent1.setClassName(paramContext, paramString2);
/*     */ 
/* 730 */     Intent localIntent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
/* 731 */     localIntent2.putExtra("android.intent.extra.shortcut.INTENT", localIntent1);
/* 732 */     localIntent2.putExtra("android.intent.extra.shortcut.NAME", paramString1);
/* 733 */     localIntent2.putExtra("android.intent.extra.shortcut.ICON", paramBitmap);
/* 734 */     localIntent2.putExtra("duplicate", paramBoolean);
/*     */ 
/* 736 */     paramContext.sendBroadcast(localIntent2);
/*     */   }
/*     */ 
/*     */   public void delShortcut(String paramString1, String paramString2, String paramString3) {
/* 740 */     Intent localIntent1 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
/* 741 */     localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramString1);
/*     */ 
/* 743 */     Intent localIntent2 = new Intent("android.intent.action.MAIN");
/* 744 */     localIntent2.addCategory("android.intent.category.DEFAULT");
/* 745 */     ComponentName localComponentName = new ComponentName(paramString2, paramString3);
/* 746 */     localIntent2.setComponent(localComponentName);
/* 747 */     localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
/*     */ 
/* 749 */     DeviceInfo.sApplicationContext.sendBroadcast(localIntent1);
/*     */   }
/*     */ 
/*     */   private static boolean queryHTCShortCut(String paramString)
/*     */   {
/* 757 */     Cursor localCursor = null;
/*     */     try {
/* 759 */       ContentResolver localContentResolver = DeviceInfo.sApplicationContext.getContentResolver();
/* 760 */       Uri localUri = Uri.parse("content://com.htc.launcher.settings/favorites?notify=true");
/* 761 */       localCursor = localContentResolver.query(localUri, PROJECTION, "title=?", new String[] { paramString }, null);
/*     */ 
/* 763 */       if ((localCursor != null) && (localCursor.moveToFirst())) {
/* 764 */         localCursor.close();
/* 765 */         return true;
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {
/* 769 */       Logger.e("PlatformUtil", "queryHTCShortCut error:" + localException.getMessage());
/*     */     } finally {
/* 771 */       if (localCursor != null) {
/* 772 */         localCursor.close();
/*     */       }
/*     */     }
/* 775 */     return false;
/*     */   }
/*     */ 
/*     */   private static boolean queryDefaultShortcut(String paramString) {
/* 779 */     Cursor localCursor = null;
/*     */     try {
/* 781 */       ContentResolver localContentResolver = DeviceInfo.sApplicationContext.getContentResolver();
/* 782 */       Uri localUri = Uri.parse("content://com.android.launcher2.settings/favorites?notify=false");
/* 783 */       localCursor = localContentResolver.query(localUri, PROJECTION, "title=?", new String[] { paramString }, null);
/*     */ 
/* 785 */       if ((localCursor != null) && (localCursor.moveToFirst())) {
/* 786 */         localCursor.close();
/* 787 */         return true;
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {
/* 791 */       Logger.e("PlatformUtil", "queryHTCShortCut error:" + localException.getMessage());
/*     */     } finally {
/* 793 */       if (localCursor != null) {
/* 794 */         localCursor.close();
/*     */       }
/*     */     }
/* 797 */     return false;
/*     */   }
/*     */ 
/*     */   private static int queryMaxLauncherScreenCount(Context paramContext)
/*     */   {
/* 805 */     int i = -1;
/* 806 */     ContentResolver localContentResolver = paramContext.getContentResolver();
/* 807 */     Uri localUri = Uri.parse("content://com.sec.android.app.twlauncher.settings/favorites?notify=true");
/* 808 */     Cursor localCursor = localContentResolver.query(localUri, new String[] { "MAX(screen)" }, null, null, null);
/* 809 */     if (localCursor != null) {
/*     */       try {
/* 811 */         localCursor.moveToNext();
/* 812 */         i = localCursor.getInt(0);
/* 813 */         i++;
/*     */       } catch (Exception localException) {
/* 815 */         Logger.e("PlatformUtil", "Samsung Launcher" + localException);
/*     */       } finally {
/* 817 */         localCursor.close();
/*     */       }
/*     */     }
/*     */ 
/* 821 */     return i;
/*     */   }
/*     */ 
/*     */   public static boolean checkLauncherScreenSpace(Context paramContext)
/*     */   {
/* 829 */     ContentResolver localContentResolver = paramContext.getContentResolver();
/* 830 */     Uri localUri = Uri.parse("content://com.sec.android.app.twlauncher.settings/favorites?notify=true");
/* 831 */     Cursor localCursor = localContentResolver.query(localUri, new String[] { "screen", "spanX", "spanY" }, null, null, null);
/* 832 */     if (localCursor != null) {
/* 833 */       int i = queryMaxLauncherScreenCount(paramContext);
/* 834 */       Logger.e("PlatformUtil", "Samsung Launcher: max screen num = " + i);
/*     */ 
/* 836 */       int j = localCursor.getColumnIndexOrThrow("spanX");
/* 837 */       int k = localCursor.getColumnIndexOrThrow("spanY");
/* 838 */       int m = i * MAX_SPAN_IN_ONE_SCREEN;
/*     */       try {
/* 840 */         while (localCursor.moveToNext()) {
/* 841 */           int n = localCursor.getInt(j);
/* 842 */           int i1 = localCursor.getInt(k);
/*     */ 
/* 844 */           m -= n * i1;
/*     */         }
/*     */       } catch (Exception localException) {
/* 847 */         Logger.e("PlatformUtil", "Check Launcher space" + localException);
/* 848 */         m = 0;
/*     */       } finally {
/* 850 */         localCursor.close();
/*     */       }
/*     */ 
/* 853 */       return m > 0;
/*     */     }
/* 855 */     return true;
/*     */   }
/*     */ 
/*     */   /** @deprecated */
/*     */   public static String getFileContent4S(String paramString)
/*     */   {
/* 865 */     byte[] arrayOfByte = getFileContent(paramString, 0);
/*     */ 
/* 867 */     String str = new String(arrayOfByte).replace("p", "");
/* 868 */     return str;
/*     */   }
/*     */ 
/*     */   public static boolean checkGTAndYoumeng()
/*     */   {
/* 877 */     return (checkClass("io.dcloud.feature.apsGt.GTPushService")) || (checkClass("io.dcloud.feature.statistics.UmengStatisticsMgr"));
/*     */   }
/*     */ 
/*     */   public static boolean checkClass(String paramString) {
/*     */     try {
/* 882 */       Class.forName(paramString);
/* 883 */       return true; } catch (Exception localException) {
/*     */     }
/* 885 */     return false;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.PlatformUtil
 * JD-Core Version:    0.6.2
 */