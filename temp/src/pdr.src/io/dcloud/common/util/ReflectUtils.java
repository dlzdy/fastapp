/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.os.IBinder;
/*     */ import android.view.inputmethod.InputMethodManager;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ public class ReflectUtils
/*     */ {
/*     */   public static final String CLASSNAME_ICONTENTPROVIDER = "android.content.IContentProvider";
/*     */   public static final String CLASSNAME_IMOUNTSERVICE_STUB = "android.os.storage.IMountService$Stub";
/*     */   public static final String CLASSNAME_PAGEAGEPARSE_PACKAGE = "android.content.pm.PackageParser$Package";
/*     */   public static final String CLASSNAME_PAGEAGEPARSE = "android.content.pm.PackageParser";
/*     */   public static final String CLASSNAME_THUMBNAILUTILS = "android.media.ThumbnailUtils";
/*     */   public static final String CLASSNAME_THREADS = "android.provider.Telephony$Threads";
/*     */   public static final String CLASSNAME_IPACKAGESTATSOBSERVER = "android.content.pm.IPackageStatsObserver";
/*     */   public static final String CLASSNAME_IPACKAGEDATAOBSERVER = "android.content.pm.IPackageDataObserver";
/*     */   public static final String CLASSNAME_IPACKAGEINSTALLOBERVER = "android.content.pm.IPackageInstallObserver";
/*     */   public static final String CLASSNAME_AUDIOSYSTEM = "android.media.AudioSystem";
/*     */   public static final String CLASSNAME_PACKAGEMANAGER = "android.content.pm.PackageManager";
/*     */   public static final String CLASSNAME_IPACKAGEMANAGER_STUB = "android.content.pm.IPackageManager$Stub";
/*     */   public static final String CLASSNAME_APPLICATIONINFO = "android.content.pm.ApplicationInfo";
/*     */   public static final String CLASSNAME_PACKAGEINFO = "android.content.pm.PackageInfo";
/*     */   public static final String CLASSNAME_IPACKAGEMANAGER = "android.content.pm.IPackageManager";
/*     */   public static final String CLASSNAME_IPACKAGEDELETEOBSERVER = "android.content.pm.IPackageDeleteObserver";
/*     */   public static final String CLASSNAME_PROCESS = "android.os.Process";
/*     */ 
/*     */   public static Method getDeclaredMethod(Object paramObject, String paramString, Class<?>[] paramArrayOfClass)
/*     */   {
/*  45 */     Method localMethod = null;
/*  46 */     for (Class localClass = paramObject.getClass(); localClass != Object.class; localClass = localClass.getSuperclass()) {
/*     */       try {
/*  48 */         return localClass.getDeclaredMethod(paramString, paramArrayOfClass);
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */ 
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   public static Object invokeMethod(Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
/*     */   {
/*  69 */     Method localMethod = getDeclaredMethod(paramObject, paramString, paramArrayOfClass);
/*     */ 
/*  72 */     localMethod.setAccessible(true);
/*     */     try {
/*  74 */       if (null != localMethod)
/*     */       {
/*  76 */         return localMethod.invoke(paramObject, paramArrayOfObject);
/*     */       }
/*     */     } catch (IllegalArgumentException localIllegalArgumentException) {
/*  79 */       localIllegalArgumentException.printStackTrace();
/*     */     } catch (IllegalAccessException localIllegalAccessException) {
/*  81 */       localIllegalAccessException.printStackTrace();
/*     */     } catch (InvocationTargetException localInvocationTargetException) {
/*  83 */       localInvocationTargetException.printStackTrace();
/*     */     }
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   public static Field getDeclaredField(Object paramObject, String paramString)
/*     */   {
/*  96 */     Field localField = null;
/*  97 */     for (Class localClass = paramObject.getClass(); 
/*  98 */       localClass != Object.class; localClass = localClass.getSuperclass()) {
/*     */       try {
/* 100 */         return localClass.getDeclaredField(paramString);
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/*     */ 
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */   public static void setFieldValue(Object paramObject1, String paramString, Object paramObject2)
/*     */   {
/* 119 */     Field localField = getDeclaredField(paramObject1, paramString);
/*     */ 
/* 121 */     localField.setAccessible(true);
/*     */     try
/*     */     {
/* 124 */       localField.set(paramObject1, paramObject2);
/*     */     } catch (IllegalArgumentException localIllegalArgumentException) {
/* 126 */       localIllegalArgumentException.printStackTrace();
/*     */     } catch (IllegalAccessException localIllegalAccessException) {
/* 128 */       localIllegalAccessException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object getFieldValue(Object paramObject, String paramString)
/*     */   {
/* 142 */     Field localField = getDeclaredField(paramObject, paramString);
/*     */ 
/* 144 */     localField.setAccessible(true);
/*     */     try
/*     */     {
/* 147 */       return localField.get(paramObject);
/*     */     } catch (Exception localException) {
/* 149 */       localException.printStackTrace();
/*     */     }
/* 151 */     return null;
/*     */   }
/*     */ 
/*     */   public static Class classForName(String paramString) {
/*     */     try {
/* 156 */       return Class.forName(paramString); } catch (ClassNotFoundException localClassNotFoundException) {
/*     */     }
/* 158 */     throw new RuntimeException(paramString);
/*     */   }
/*     */ 
/*     */   public static Object stubAsInterface(String paramString, IBinder paramIBinder)
/*     */   {
/* 163 */     return stubAsInterface(classForName(paramString), paramIBinder);
/*     */   }
/*     */ 
/*     */   public static Object stubAsInterface(Class paramClass, IBinder paramIBinder) {
/*     */     try {
/* 168 */       return paramClass.getDeclaredMethod("asInterface", new Class[] { IBinder.class }).invoke(null, new Object[] { paramIBinder });
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException) {
/* 171 */       throw new RuntimeException(localIllegalAccessException);
/*     */     } catch (InvocationTargetException localInvocationTargetException) {
/* 173 */       throw new RuntimeException(localInvocationTargetException);
/*     */     } catch (NoSuchMethodException localNoSuchMethodException) {
/* 175 */       throw new RuntimeException(localNoSuchMethodException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Method getMethod(String paramString1, String paramString2, Class<?>[] paramArrayOfClass) {
/* 180 */     Method localMethod = null;
/*     */     try {
/* 182 */       Class localClass = Class.forName(paramString1);
/* 183 */       localMethod = localClass.getDeclaredMethod(paramString2, paramArrayOfClass);
/*     */     } catch (Exception localException) {
/* 185 */       localException.printStackTrace();
/*     */     }
/* 187 */     return localMethod;
/*     */   }
/*     */ 
/*     */   public static Method getMethod(Class<?> paramClass, String paramString, Class<?>[] paramArrayOfClass) {
/* 191 */     Method localMethod = null;
/* 192 */     if ((paramString != null) && (paramString.length() > 0)) {
/*     */       try {
/* 194 */         localMethod = paramClass.getMethod(paramString, paramArrayOfClass);
/*     */       } catch (NoSuchMethodException localNoSuchMethodException) {
/* 196 */         localNoSuchMethodException.printStackTrace();
/*     */       }
/*     */     }
/* 199 */     return localMethod;
/*     */   }
/*     */ 
/*     */   public static Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) {
/* 203 */     Object localObject = null;
/*     */     try {
/* 205 */       localObject = paramMethod.invoke(paramObject, paramArrayOfObject);
/*     */     } catch (Exception localException) {
/* 207 */       localException.printStackTrace();
/*     */     }
/* 209 */     return localObject;
/*     */   }
/*     */ 
/*     */   public static String getSystemProperties(String paramString1, String paramString2) {
/* 213 */     String str = paramString2;
/*     */     try {
/* 215 */       Class localClass = Class.forName("android.os.SystemProperties");
/* 216 */       Method localMethod = localClass.getDeclaredMethod("get", new Class[] { String.class, String.class });
/* 217 */       str = (String)localMethod.invoke(null, new Object[] { paramString1, paramString2 });
/*     */     } catch (Exception localException) {
/* 219 */       localException.printStackTrace();
/*     */     }
/* 221 */     return str;
/*     */   }
/*     */ 
/*     */   public static Context getApplicationContext() {
/* 225 */     Context localContext = null;
/*     */     try {
/* 227 */       Class localClass = Class.forName("android.app.ActivityThread");
/* 228 */       Method localMethod = localClass.getDeclaredMethod("currentApplication", new Class[0]);
/* 229 */       localContext = (Context)localMethod.invoke(null, new Object[0]);
/*     */     } catch (Exception localException) {
/* 231 */       localException.printStackTrace();
/*     */     }
/* 233 */     return localContext;
/*     */   }
/*     */ 
/*     */   public static Constructor getObjectConstructor(String paramString, Class[] paramArrayOfClass) {
/*     */     try {
/* 238 */       return Class.forName(paramString).getConstructor(paramArrayOfClass);
/*     */     } catch (NoSuchMethodException localNoSuchMethodException) {
/* 240 */       throw new RuntimeException(localNoSuchMethodException);
/*     */     } catch (ClassNotFoundException localClassNotFoundException) {
/* 242 */       throw new RuntimeException(localClassNotFoundException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object getField(Object paramObject, String paramString) throws NoSuchFieldException, IllegalAccessException {
/* 247 */     return prepareField(paramObject.getClass(), paramString).get(paramObject);
/*     */   }
/*     */ 
/*     */   public static void setField(Object paramObject1, String paramString, Object paramObject2) throws NoSuchFieldException, IllegalAccessException {
/* 251 */     prepareField(paramObject1.getClass(), paramString).set(paramObject1, paramObject2);
/*     */   }
/*     */ 
/*     */   public static int getIntField(Object paramObject, String paramString) {
/*     */     try {
/* 256 */       return paramObject.getClass().getDeclaredField(paramString).getInt(paramObject);
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException)
/*     */     {
/* 260 */       throw new RuntimeException(localIllegalAccessException);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/* 262 */       throw new RuntimeException(localNoSuchFieldException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object getObjectField(Object paramObject, String paramString) {
/*     */     try {
/* 268 */       return paramObject.getClass().getDeclaredField(paramString).get(paramObject);
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException)
/*     */     {
/* 272 */       throw new RuntimeException(localIllegalAccessException);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/* 274 */       throw new RuntimeException(localNoSuchFieldException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object getObjectFieldNoDeclared(Object paramObject, String paramString) {
/*     */     try {
/* 280 */       return paramObject.getClass().getField(paramString).get(paramObject);
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException)
/*     */     {
/* 284 */       throw new RuntimeException(localIllegalAccessException);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/* 286 */       throw new RuntimeException(localNoSuchFieldException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getStaticIntField(String paramString1, String paramString2)
/*     */   {
/*     */     try {
/* 293 */       return Class.forName(paramString1).getDeclaredField(paramString2).getInt(null);
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException)
/*     */     {
/* 297 */       throw new RuntimeException(localIllegalAccessException);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/* 299 */       throw new RuntimeException(localNoSuchFieldException);
/*     */     } catch (ClassNotFoundException localClassNotFoundException) {
/* 301 */       throw new RuntimeException(localClassNotFoundException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object getStaticObjectField(String paramString1, String paramString2) {
/*     */     try {
/* 307 */       return Class.forName(paramString1).getDeclaredField(paramString2).get(null);
/*     */     }
/*     */     catch (IllegalAccessException localIllegalAccessException)
/*     */     {
/* 311 */       throw new RuntimeException(localIllegalAccessException);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/* 313 */       throw new RuntimeException(localNoSuchFieldException);
/*     */     } catch (ClassNotFoundException localClassNotFoundException) {
/* 315 */       throw new RuntimeException(localClassNotFoundException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getStaticStringField(String paramString1, String paramString2) {
/* 320 */     return (String)getStaticObjectField(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   private static Field prepareField(Class<?> paramClass, String paramString) throws NoSuchFieldException {
/* 324 */     if (paramClass != null) {
/*     */       try {
/* 326 */         Field localField1 = paramClass.getDeclaredField(paramString);
/* 327 */         localField1.setAccessible(true);
/* 328 */         return localField1;
/*     */       } finally {
/* 330 */         paramClass = paramClass.getSuperclass();
/*     */       }
/*     */     }
/* 333 */     throw new NoSuchFieldException();
/*     */   }
/*     */ 
/*     */   public static void windowDismissed(InputMethodManager paramInputMethodManager, IBinder paramIBinder) {
/*     */     try {
/* 338 */       paramInputMethodManager.getClass().getMethod("windowDismissed", new Class[] { IBinder.class }).invoke(paramInputMethodManager, new Object[] { paramIBinder });
/*     */     } catch (IllegalAccessException localIllegalAccessException) {
/* 340 */       throw new RuntimeException(localIllegalAccessException);
/*     */     } catch (InvocationTargetException localInvocationTargetException) {
/* 342 */       throw new RuntimeException(localInvocationTargetException);
/*     */     } catch (NoSuchMethodException localNoSuchMethodException) {
/* 344 */       throw new RuntimeException(localNoSuchMethodException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void modifyPushPriority(Object paramObject1, String paramString, Object paramObject2) {
/* 349 */     Class localClass = paramObject1.getClass();
/*     */     try
/*     */     {
/* 352 */       Field localField = localClass.getDeclaredField(paramString);
/* 353 */       localField.setAccessible(true);
/* 354 */       localField.set(paramObject1, paramObject2);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/* 356 */       localNoSuchFieldException.printStackTrace();
/*     */     } catch (IllegalAccessException localIllegalAccessException) {
/* 358 */       localIllegalAccessException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void modifyPushBigContentView(Object paramObject1, String paramString, Object paramObject2) {
/* 363 */     Class localClass = paramObject1.getClass();
/*     */     try
/*     */     {
/* 366 */       Field localField = localClass.getDeclaredField(paramString);
/* 367 */       localField.setAccessible(true);
/* 368 */       localField.set(paramObject1, paramObject2);
/*     */     } catch (NoSuchFieldException localNoSuchFieldException) {
/* 370 */       localNoSuchFieldException.printStackTrace();
/*     */     } catch (IllegalAccessException localIllegalAccessException) {
/* 372 */       localIllegalAccessException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ReflectUtils
 * JD-Core Version:    0.6.2
 */