/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.AlertDialog;
/*     */ import android.app.AlertDialog.Builder;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.DialogInterface.OnCancelListener;
/*     */ import android.content.DialogInterface.OnClickListener;
/*     */ import android.content.Intent;
/*     */ import android.content.SharedPreferences;
/*     */ import android.content.SharedPreferences.Editor;
/*     */ import android.net.Uri;
/*     */ import android.os.Binder;
/*     */ import android.os.Build;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.IBinder;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class AppPermissionUtil
/*     */ {
/*  32 */   public static HashMap<String, Integer> mXiaoMiCode19OPSIDs = new HashMap();
/*  33 */   public static HashMap<String, Integer> mXiaoMiCode21OPSIDs = new HashMap();
/*  34 */   public static HashMap<String, Integer> mXiaoMiCode23OPSIDs = new HashMap();
/*     */ 
/*  36 */   public static String OP_INSTALL_SHORTCUT = "op_install_shortcut";
/*     */   public static final int MODE_DEFAULT = 3;
/*     */   public static final int MODE_ASK = 4;
/*     */   public static final int MODE_ERRORED = 2;
/*     */   public static final int MODE_IGNORED = 1;
/*     */   public static final int MODE_UNKNOWN = -1;
/*     */   public static final int MODE_ALLOWED = 0;
/*     */ 
/*     */   public static int getShotCutOpId()
/*     */   {
/*  57 */     if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.XIAOMI)) {
/*  58 */       switch (Build.VERSION.SDK_INT) {
/*     */       case 19:
/*  60 */         return ((Integer)mXiaoMiCode19OPSIDs.get(OP_INSTALL_SHORTCUT)).intValue();
/*     */       case 21:
/*     */       case 22:
/*  63 */         return ((Integer)mXiaoMiCode21OPSIDs.get(OP_INSTALL_SHORTCUT)).intValue();
/*     */       case 23:
/*  65 */         return ((Integer)mXiaoMiCode23OPSIDs.get(OP_INSTALL_SHORTCUT)).intValue();
/*     */       case 20:
/*  67 */       }return -1;
/*     */     }
/*  69 */     if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) {
/*  70 */       switch (Build.VERSION.SDK_INT) {
/*     */       case 23:
/*  72 */         return 16777216;
/*     */       }
/*     */     }
/*  75 */     return -1;
/*     */   }
/*     */ 
/*     */   public static int checkOp(Context paramContext)
/*     */   {
/*  84 */     int i = Build.VERSION.SDK_INT;
/*  85 */     if (i >= 19) {
/*  86 */       Object localObject = paramContext.getSystemService("appops");
/*  87 */       Class localClass = localObject.getClass();
/*     */       try {
/*  89 */         Field localField = localClass.getDeclaredField("OP_INSTALL_SHORTCUT");
/*  90 */         int j = ((Integer)localField.get(localClass)).intValue();
/*  91 */         Class[] arrayOfClass = new Class[3];
/*  92 */         arrayOfClass[0] = Integer.TYPE;
/*  93 */         arrayOfClass[1] = Integer.TYPE;
/*  94 */         arrayOfClass[2] = String.class;
/*  95 */         Method localMethod = localClass.getDeclaredMethod("checkOp", arrayOfClass);
/*  96 */         String str = paramContext.getPackageName();
/*  97 */         if (BaseInfo.isForQihooHelper(paramContext)) {
/*  98 */           str = "com.qihoo.appstore";
/*     */         }
/* 100 */         return ((Integer)localMethod.invoke(localObject, new Object[] { Integer.valueOf(j), Integer.valueOf(Binder.getCallingUid()), str })).intValue();
/*     */       } catch (Exception localException) {
/* 102 */         localException.printStackTrace();
/*     */       }
/*     */     }
/* 105 */     return -1;
/*     */   }
/*     */ 
/*     */   public static boolean checkShortcutOps(IApp paramIApp, Activity paramActivity, String paramString1, String paramString2)
/*     */   {
/* 114 */     SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/*     */ 
/* 116 */     if (getCheckShortcutOps(paramActivity) == 1) {
/* 117 */       showShortCutOpsDialog(paramIApp, paramActivity, paramString1, localSharedPreferences);
/* 118 */       return false;
/*     */     }
/*     */ 
/* 138 */     return true;
/*     */   }
/*     */ 
/*     */   public static void showShortCutOpsDialog(IApp paramIApp, final Activity paramActivity, final String paramString, final SharedPreferences paramSharedPreferences) {
/* 142 */     AlertDialog.Builder localBuilder = DialogUtil.initDialogTheme(paramActivity, !BaseInfo.isForQihooHelper(paramActivity));
/* 143 */     String str = "桌面快捷方式权限被禁用，无法在桌面安装本应用。请点击“前往设置权限”-在打开的界面滚动到最下方-点击权限管理-滚动到最下方-点击桌面快捷方式-允许。";
/* 144 */     if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU))
/* 145 */       str = "桌面快捷方式权限被禁用，无法在桌面安装本应用。请点击“前往设置权限”打开应用信息界面-权限管理-桌面快捷方式-允许。";
/* 146 */     else if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) {
/* 147 */       str = "桌面快捷方式权限被禁用，无法在桌面安装本应用。请点击“前往设置权限”打开应用信息界面-权限-设置单项权限-创建桌面快捷方式-允许.";
/*     */     }
/* 149 */     localBuilder.setTitle("开启“桌面快捷方式”权限").setMessage(str).setPositiveButton("前往设置权限", new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*     */       {
/* 155 */         Uri localUri = Uri.parse("package:" + this.val$activity.getPackageName());
/* 156 */         if (BaseInfo.isForQihooHelper(this.val$activity)) {
/* 157 */           localUri = Uri.parse("package:com.qihoo.appstore");
/*     */         }
/* 159 */         Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", localUri);
/* 160 */         paramSharedPreferences.edit().putBoolean(paramString + "_is_create_shortcut", true).commit();
/* 161 */         this.val$activity.startActivity(localIntent);
/*     */       }
/*     */     }).setNegativeButton("不在桌面安装", new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*     */       {
/* 168 */         AppPermissionUtil.againShortcutOpsDialog(this.val$app, paramActivity, paramString, this.val$app.obtainAppName());
/*     */       }
/*     */     });
/* 171 */     AlertDialog localAlertDialog = localBuilder.create();
/* 172 */     localAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
/*     */     {
/*     */       public void onCancel(DialogInterface paramAnonymousDialogInterface)
/*     */       {
/* 177 */         AppPermissionUtil.againShortcutOpsDialog(this.val$app, paramActivity, paramString, this.val$app.obtainAppName());
/*     */       }
/*     */     });
/* 180 */     localAlertDialog.setCanceledOnTouchOutside(false);
/* 181 */     localAlertDialog.show();
/*     */   }
/*     */ 
/*     */   public static void againShortcutOpsDialog(IApp paramIApp, Activity paramActivity, final String paramString1, String paramString2)
/*     */   {
/* 191 */     final SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/* 192 */     AlertDialog.Builder localBuilder = DialogUtil.initDialogTheme(paramActivity, !BaseInfo.isForQihooHelper(paramActivity));
/* 193 */     localBuilder.setTitle("开启“桌面快捷方式”权限").setMessage("本应用无法安装到桌面，以后无法从桌面启动本应用。强烈建议配置桌面快捷方式权限!").setPositiveButton("前往设置权限", new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*     */       {
/* 199 */         Uri localUri = Uri.parse("package:" + this.val$activity.getPackageName());
/* 200 */         if (BaseInfo.isForQihooHelper(this.val$activity)) {
/* 201 */           localUri = Uri.parse("package:com.qihoo.appstore");
/*     */         }
/* 203 */         Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", localUri);
/* 204 */         localSharedPreferences.edit().putBoolean(paramString1 + "_is_create_shortcut", true).commit();
/* 205 */         this.val$activity.startActivity(localIntent);
/*     */       }
/*     */     }).setNegativeButton("不在桌面安装", new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*     */       {
/* 213 */         ShortCutUtil.commitShortcut(this.val$app, 12, true, false, false, 0);
/* 214 */         if ((Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU)) || (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)))
/* 215 */           ShortCutUtil.createShortcutToDeskTop(this.val$app, false);
/*     */       }
/*     */     });
/* 219 */     AlertDialog localAlertDialog = localBuilder.create();
/* 220 */     localAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
/*     */     {
/*     */       public void onCancel(DialogInterface paramAnonymousDialogInterface)
/*     */       {
/* 226 */         ShortCutUtil.commitShortcut(this.val$app, 12, true, false, false, 0);
/*     */       }
/*     */     });
/* 229 */     localAlertDialog.setCanceledOnTouchOutside(false);
/* 230 */     localAlertDialog.show();
/*     */   }
/*     */ 
/*     */   public static int getCheckShortcutOps(Activity paramActivity)
/*     */   {
/* 240 */     int i = getShotCutOpId();
/* 241 */     if (-1 != i) {
/* 242 */       return checkOp(paramActivity);
/*     */     }
/* 244 */     return 0;
/*     */   }
/*     */ 
/*     */   public static boolean isFlymeShortcutallowAllow(Context paramContext, Intent paramIntent)
/*     */   {
/* 254 */     int i = getFlymeShortcutPid();
/* 255 */     if (i != -1) {
/* 256 */       int j = getFlymePermissionGranted(paramContext, i, paramIntent);
/* 257 */       if (j == 1) {
/* 258 */         return false;
/*     */       }
/*     */     }
/* 261 */     return true;
/*     */   }
/*     */ 
/*     */   private static int getFlymePermissionGranted(Context paramContext, int paramInt, Intent paramIntent)
/*     */   {
/*     */     try
/*     */     {
/* 273 */       Class localClass1 = Class.forName("meizu.security.IFlymePermissionService$Stub");
/* 274 */       Class localClass2 = Class.forName("android.os.ServiceManager");
/* 275 */       Method localMethod1 = localClass2.getDeclaredMethod("getService", new Class[] { String.class });
/* 276 */       IBinder localIBinder = (IBinder)localMethod1.invoke(localClass2, new Object[] { "flyme_permission" });
/* 277 */       Method localMethod2 = localClass1.getDeclaredMethod("asInterface", new Class[] { IBinder.class });
/* 278 */       Object localObject = localMethod2.invoke(localClass1, new Object[] { localIBinder });
/* 279 */       Method localMethod3 = localObject.getClass().getMethod("noteIntentOperation", new Class[] { Integer.TYPE, Integer.TYPE, String.class, Intent.class });
/* 280 */       int i = Binder.getCallingPid();
/* 281 */       String str = paramContext.getPackageName();
/* 282 */       if (BaseInfo.isForQihooHelper(paramContext)) {
/* 283 */         str = "com.qihoo.appstore";
/*     */       }
/* 285 */       return ((Integer)localMethod3.invoke(localObject, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(i), str, paramIntent })).intValue();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 289 */       localException.printStackTrace();
/*     */     }
/* 291 */     return -1;
/*     */   }
/*     */ 
/*     */   private static int getFlymeShortcutPid()
/*     */   {
/*     */     try
/*     */     {
/* 300 */       Class localClass = Class.forName("meizu.security.FlymePermissionManager");
/* 301 */       Field localField = localClass.getDeclaredField("OP_SEND_SHORTCUT_BROADCAST");
/* 302 */       localField.setAccessible(true);
/*     */ 
/* 304 */       return ((Integer)localField.get(localClass)).intValue();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 308 */       localException.printStackTrace();
/*     */     }
/* 310 */     return -1;
/*     */   }
/*     */ 
/*     */   public static boolean isEmuiShortcutallowAllow()
/*     */   {
/*     */     try
/*     */     {
/* 319 */       int i = getShotCutOpId();
/* 320 */       if (-1 == i) {
/* 321 */         return true;
/*     */       }
/* 323 */       Class localClass = Class.forName("com.huawei.hsm.permission.StubController");
/* 324 */       Method localMethod = localClass.getDeclaredMethod("holdForGetPermissionSelection", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, String.class });
/* 325 */       int j = ((Integer)localMethod.invoke(localClass, new Object[] { Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()), Integer.valueOf(Binder.getCallingPid()), null })).intValue();
/* 326 */       if (j == 2)
/* 327 */         return false;
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 331 */       localException.printStackTrace();
/*     */     }
/* 333 */     return true;
/*     */   }
/*     */ 
/*     */   public static int checkPermission(Context paramContext, String paramString)
/*     */   {
/* 343 */     if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU)) {
/* 344 */       if (!isFlymeShortcutallowAllow(paramContext, ShortCutUtil.getHeadShortCutIntent(paramString))) {
/* 345 */         return 1;
/*     */       }
/* 347 */       return 3;
/*     */     }
/* 349 */     if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.XIAOMI)) {
/* 350 */       int i = checkOp(paramContext);
/* 351 */       switch (i) {
/*     */       case 3:
/*     */       case 4:
/* 354 */         return 2;
/*     */       case 0:
/*     */       case 1:
/* 357 */         return i;
/*     */       case -1:
/* 359 */         return 4;
/*     */       case 2:
/*     */       }
/* 362 */     } else if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) {
/* 363 */       if (!isEmuiShortcutallowAllow()) {
/* 364 */         return 1;
/*     */       }
/* 366 */       return 3;
/*     */     }
/*     */ 
/* 369 */     return 4;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  46 */     mXiaoMiCode19OPSIDs.put(OP_INSTALL_SHORTCUT, Integer.valueOf(60));
/*  47 */     mXiaoMiCode21OPSIDs.put(OP_INSTALL_SHORTCUT, Integer.valueOf(63));
/*  48 */     mXiaoMiCode23OPSIDs.put(OP_INSTALL_SHORTCUT, Integer.valueOf(10017));
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.AppPermissionUtil
 * JD-Core Version:    0.6.2
 */