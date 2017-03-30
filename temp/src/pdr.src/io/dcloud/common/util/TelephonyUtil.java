/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.net.wifi.WifiInfo;
/*     */ import android.net.wifi.WifiManager;
/*     */ import android.os.Environment;
/*     */ import android.provider.Settings.Secure;
/*     */ import android.telephony.TelephonyManager;
/*     */ import android.text.TextUtils;
/*     */ import android.util.Base64;
/*     */ import io.dcloud.common.adapter.io.DHFile;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.adapter.util.SP;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class TelephonyUtil
/*     */ {
/*  25 */   private static String sImei = "";
/*  26 */   private static String sImeiAndBakInfo = null;
/*     */   private static final String K = "DCloud&2*0^1#600";
/* 278 */   private static String sIMSI = null;
/*     */ 
/*     */   private static boolean isUnValid(String paramString)
/*     */   {
/*  29 */     return (TextUtils.isEmpty(paramString)) || (paramString.contains("Unknown")) || (paramString.contains("00000000"));
/*     */   }
/*     */   public static String getIMEI(Context paramContext) {
/*  32 */     return getIMEI(paramContext, true);
/*     */   }
/*     */ 
/*     */   private static String[] getMultiIMEI(Context paramContext) {
/*  41 */     TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
/*  42 */     StringBuffer localStringBuffer = new StringBuffer();
/*  43 */     Object localObject1 = null;
/*  44 */     Object localObject2 = null;
/*     */     String str3;
/*     */     try {
/*  46 */       String str1 = (String)PlatformUtil.invokeMethod(localTelephonyManager.getClass().getName(), "getImei", localTelephonyManager);
/*  47 */       str3 = (String)PlatformUtil.invokeMethod(localTelephonyManager.getClass().getName(), "getImei2", localTelephonyManager);
/*  48 */       if (!isUnValid(str1)) {
/*  49 */         localObject1 = str1;
/*     */       }
/*  51 */       if (!isUnValid(str3)) {
/*  52 */         localObject2 = str3;
/*  53 */         if (isUnValid(localObject1)) {
/*  54 */           str1 = localTelephonyManager.getDeviceId();
/*  55 */           if ((!isUnValid(str1)) && (!TextUtils.equals(str1, str3))) {
/*  56 */             localObject1 = str1;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception localException1)
/*     */     {
/*     */     }
/*  64 */     if ((isUnValid(localObject1)) || (isUnValid(localObject2))) {
/*     */       try {
/*  66 */         String str2 = (String)PlatformUtil.invokeMethod(localTelephonyManager.getClass().getName(), "getImei", localTelephonyManager, new Class[] { Integer.TYPE }, new Object[] { Integer.valueOf(0) });
/*  67 */         str3 = (String)PlatformUtil.invokeMethod(localTelephonyManager.getClass().getName(), "getImei", localTelephonyManager, new Class[] { Integer.TYPE }, new Object[] { Integer.valueOf(1) });
/*  68 */         if ((str2 != null) && (!TextUtils.equals(localObject1, str2))) {
/*  69 */           localObject1 = str2;
/*     */         }
/*  71 */         if ((str3 != null) && (!TextUtils.equals(localObject2, str3))) {
/*  72 */           localObject2 = str3;
/*  73 */           if (isUnValid(localObject1)) {
/*  74 */             str2 = localTelephonyManager.getDeviceId();
/*  75 */             if ((!isUnValid(str2)) && (!TextUtils.equals(str2, str3))) {
/*  76 */               localObject1 = str2;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception localException2)
/*     */       {
/*     */       }
/*     */     }
/*  85 */     if ((!isUnValid(localObject1)) && (!isUnValid(localObject2))) {
/*  86 */       return new String[] { localObject1, localObject2 };
/*     */     }
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getIMEI(Context paramContext, boolean paramBoolean)
/*     */   {
/*  98 */     StringBuffer localStringBuffer = new StringBuffer();
/*     */ 
/* 100 */     if (paramBoolean) {
/* 101 */       localStringBuffer.append("|");
/*     */     }
/* 103 */     if (paramContext == null) {
/* 104 */       return paramBoolean ? "|||||" : "";
/*     */     }
/* 106 */     if ((paramBoolean) && (!TextUtils.isEmpty(sImeiAndBakInfo))) {
/* 107 */       return sImeiAndBakInfo;
/*     */     }
/* 109 */     if ((!isUnValid(sImei)) && (!paramBoolean)) {
/* 110 */       return sImei;
/*     */     }
/*     */ 
/* 113 */     String[] arrayOfString = getMultiIMEI(paramContext);
/*     */ 
/* 115 */     boolean bool = isUnValid(sImei);
/*     */     Object localObject1;
/*     */     Object localObject2;
/* 116 */     if ((bool) || (paramBoolean)) {
/*     */       try {
/* 118 */         String str1 = SP.getBundleData("pdr", "_dpush_uuid_");
/* 119 */         if ((!isUnValid(str1)) && (paramBoolean))
/*     */         {
/* 122 */           localObject1 = AESUtil.encrypt("DCloud&2*0^1#600", 128, "HTML5PLUSRUNTIME", str1);
/* 123 */           str1 = Base64.encodeToString((byte[])localObject1, 2);
/* 124 */           str1 = URLEncoder.encode(str1);
/* 125 */           str1 = str1 + "&ie=1";
/* 126 */           return str1;
/*     */         }
/* 128 */         if (arrayOfString != null) {
/* 129 */           sImei = arrayOfString[0] + "," + arrayOfString[1];
/*     */         } else {
/* 131 */           localObject1 = (TelephonyManager)paramContext.getSystemService("phone");
/*     */ 
/* 133 */           sImei = ((TelephonyManager)localObject1).getDeviceId();
/*     */         }
/*     */       }
/*     */       catch (Exception localException1) {
/* 137 */         localException1.printStackTrace();
/*     */       } finally {
/* 139 */         if (paramBoolean)
/* 140 */           localStringBuffer.append(sImei).append("|");
/*     */       }
/*     */     }
/*     */     else {
/* 144 */       return sImei;
/*     */     }
/*     */ 
/* 148 */     bool = isUnValid(sImei);
/*     */     String str2;
/* 149 */     if ((bool) || (paramBoolean)) {
/* 150 */       str2 = null;
/*     */       try {
/* 152 */         localObject1 = (WifiManager)paramContext.getSystemService("wifi");
/* 153 */         if (((WifiManager)localObject1).isWifiEnabled()) {
/* 154 */           localObject2 = ((WifiManager)localObject1).getConnectionInfo();
/* 155 */           str2 = ((WifiInfo)localObject2).getMacAddress().replace(":", "");
/*     */         }
/* 157 */         if (bool)
/* 158 */           sImei = str2;
/*     */       }
/*     */       catch (Exception localException2) {
/* 161 */         localException2.printStackTrace();
/*     */       } finally {
/* 163 */         if (paramBoolean)
/* 164 */           localStringBuffer.append(TextUtils.isEmpty(str2) ? "" : str2).append("|");
/*     */       }
/*     */     }
/*     */     else {
/* 168 */       return sImei;
/*     */     }
/*     */ 
/* 172 */     bool = isUnValid(sImei);
/* 173 */     if ((bool) || (paramBoolean)) {
/* 174 */       str2 = null;
/*     */       try {
/* 176 */         str2 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
/* 177 */         if (bool)
/* 178 */           sImei = str2;
/*     */       }
/*     */       catch (Exception localException3) {
/* 181 */         localException3.printStackTrace();
/*     */       } finally {
/* 183 */         if (paramBoolean)
/* 184 */           localStringBuffer.append(TextUtils.isEmpty(str2) ? "" : str2).append("|");
/*     */       }
/*     */     }
/*     */     else {
/* 188 */       return sImei;
/*     */     }
/*     */ 
/* 192 */     bool = isUnValid(sImei);
/* 193 */     if ((bool) || (paramBoolean))
/*     */     {
/* 195 */       str2 = null;
/*     */       try {
/* 197 */         String str3 = paramContext.getFilesDir() + "/.imei.txt";
/* 198 */         localObject2 = null;
/* 199 */         File localFile1 = new File(str3);
/* 200 */         File localFile2 = null;
/* 201 */         if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState()))
/*     */         {
/* 203 */           localObject2 = Environment.getExternalStorageDirectory() + "/.imei.txt";
/* 204 */           localFile2 = new File((String)localObject2);
/*     */         }
/* 206 */         if (localFile1.isDirectory()) {
/* 207 */           localFile1.delete();
/*     */         }
/* 209 */         if ((localFile1.exists()) && (localFile1.length() > 0L)) {
/*     */           try {
/* 211 */             str2 = IOUtil.toString(new FileInputStream(localFile1));
/*     */           } catch (FileNotFoundException localFileNotFoundException1) {
/* 213 */             localFileNotFoundException1.printStackTrace();
/*     */           } catch (IOException localIOException1) {
/* 215 */             localIOException1.printStackTrace();
/*     */           }
/* 217 */           if (localFile2 != null) {
/* 218 */             if (!localFile2.getParentFile().exists()) {
/* 219 */               localFile2.getParentFile().mkdirs();
/* 220 */               localFile2.createNewFile();
/*     */             }
/* 222 */             if (localFile2.length() <= 0L)
/* 223 */               DHFile.copyFile(str3, (String)localObject2);
/*     */           }
/*     */         }
/*     */         else {
/* 227 */           if (!localFile1.getParentFile().exists()) {
/* 228 */             localFile1.getParentFile().mkdirs();
/* 229 */             localFile1.createNewFile();
/*     */           }
/* 231 */           if (localFile2.isDirectory()) {
/* 232 */             localFile2.delete();
/*     */           }
/* 234 */           if ((localFile2.exists()) && (localFile2.length() > 0L)) {
/*     */             try {
/* 236 */               str2 = IOUtil.toString(new FileInputStream(localFile2));
/*     */             } catch (IOException localIOException2) {
/* 238 */               localIOException2.printStackTrace();
/*     */             }
/*     */           } else {
/* 241 */             UUID localUUID = UUID.randomUUID();
/* 242 */             str2 = localUUID.toString();
/* 243 */             str2 = str2.replaceAll("-", "").replace("\n", "");
/*     */             try {
/* 245 */               FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
/* 246 */               localFileOutputStream.write(str2.getBytes());
/* 247 */               localFileOutputStream.flush();
/* 248 */               localFileOutputStream.close();
/*     */             } catch (FileNotFoundException localFileNotFoundException2) {
/* 250 */               localFileNotFoundException2.printStackTrace();
/*     */             } catch (IOException localIOException3) {
/* 252 */               localIOException3.printStackTrace();
/*     */             }
/*     */           }
/* 255 */           DHFile.copyFile((String)localObject2, str3);
/*     */         }
/* 257 */         if (bool)
/* 258 */           sImei = str2;
/*     */       }
/*     */       catch (Exception localException4) {
/* 261 */         localException4.printStackTrace();
/*     */       } finally {
/* 263 */         if (paramBoolean) {
/* 264 */           localStringBuffer.append(TextUtils.isEmpty(str2) ? "" : str2.replace("\n", "")).append("|");
/*     */         }
/*     */       }
/* 267 */       byte[] arrayOfByte = AESUtil.encrypt("DCloud&2*0^1#600", 128, "HTML5PLUSRUNTIME", localStringBuffer.toString());
/* 268 */       sImeiAndBakInfo = Base64.encodeToString(arrayOfByte, 2);
/* 269 */       sImeiAndBakInfo = URLEncoder.encode(sImeiAndBakInfo);
/* 270 */       sImeiAndBakInfo += "&ie=1";
/*     */     } else {
/* 272 */       return sImei;
/*     */     }
/*     */ 
/* 276 */     return sImeiAndBakInfo;
/*     */   }
/*     */ 
/*     */   public static String getIMSI(Context paramContext) {
/*     */     try {
/* 281 */       if (paramContext == null) {
/* 282 */         return "";
/*     */       }
/* 284 */       if (sIMSI != null) {
/* 285 */         return sIMSI;
/*     */       }
/* 287 */       TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
/* 288 */       sIMSI = localTelephonyManager.getSubscriberId();
/*     */     } catch (Exception localException) {
/* 290 */       localException.printStackTrace();
/*     */     }
/* 292 */     return sIMSI;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.TelephonyUtil
 * JD-Core Version:    0.6.2
 */