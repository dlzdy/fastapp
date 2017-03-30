/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.AlertDialog;
/*     */ import android.app.AlertDialog.Builder;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.DialogInterface.OnClickListener;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Bitmap.CompressFormat;
/*     */ import android.graphics.Bitmap.Config;
/*     */ import android.graphics.drawable.BitmapDrawable;
/*     */ import android.text.TextUtils;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.webkit.MimeTypeMap;
/*     */ import android.webkit.URLUtil;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.TextView;
/*     */ import android.widget.Toast;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class PdrUtil
/*     */ {
/*     */   public static String encodeURL(String paramString)
/*     */   {
/*     */     try
/*     */     {
/*  49 */       return URLEncoder.encode(paramString, "utf-8");
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*  51 */       Logger.e("URLEncode error str=" + paramString);
/*  52 */     }return URLEncoder.encode(paramString);
/*     */   }
/*     */ 
/*     */   public static long parseLong(String paramString, long paramLong)
/*     */   {
/*     */     try
/*     */     {
/*  64 */       return Long.parseLong(paramString); } catch (Exception localException) {
/*     */     }
/*  66 */     return paramLong;
/*     */   }
/*     */ 
/*     */   public static Object getObject(Object[] paramArrayOfObject, int paramInt)
/*     */   {
/*     */     try
/*     */     {
/*  78 */       return paramArrayOfObject[paramInt];
/*     */     } catch (Exception localException) {
/*  80 */       localException.printStackTrace();
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean parseBoolean(String paramString, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  93 */     if ((paramString == null) || (paramString.equals(""))) {
/*  94 */       return paramBoolean1;
/*     */     }
/*  96 */     return paramString.equalsIgnoreCase("false") ? false | paramBoolean2 : paramString.equalsIgnoreCase("true") ? 0x1 & (!paramBoolean2 ? 1 : 0) : paramBoolean1;
/*     */   }
/*     */ 
/*     */   public static String getUrlPathName(String paramString) {
/* 100 */     return paramString != null ? URLUtil.stripAnchor(stripQuery(paramString)) : paramString;
/*     */   }
/*     */   public static String stripAnchor(String paramString) {
/* 103 */     return URLUtil.stripAnchor(paramString);
/*     */   }
/*     */   public static String stripQuery(String paramString) {
/* 106 */     int i = paramString.indexOf('?');
/* 107 */     if (i != -1) {
/* 108 */       return paramString.substring(0, i);
/*     */     }
/* 110 */     return paramString;
/*     */   }
/*     */ 
/*     */   public static String standardizedURL(String paramString1, String paramString2)
/*     */   {
/* 120 */     paramString1 = stripAnchor(paramString1);
/* 121 */     paramString1 = stripQuery(paramString1);
/* 122 */     if (paramString2.startsWith("./")) {
/* 123 */       paramString2 = paramString2.substring("./".length());
/* 124 */       int i = paramString1.lastIndexOf('/');
/* 125 */       if (i >= 0) {
/* 126 */         return paramString1.substring(0, i + 1) + paramString2;
/*     */       }
/*     */     }
/* 129 */     String str = "../";
/* 130 */     int j = paramString2.indexOf(str);
/* 131 */     int k = paramString1.lastIndexOf('/');
/* 132 */     int m = k > -1 ? 1 : 0;
/* 133 */     if (m != 0) {
/* 134 */       paramString1 = paramString1.substring(0, k);
/* 135 */       while (j > -1) {
/* 136 */         paramString2 = paramString2.substring(str.length());
/* 137 */         k = paramString1.lastIndexOf('/');
/* 138 */         paramString1 = paramString1.substring(0, k);
/* 139 */         j = paramString2.indexOf(str);
/*     */       }
/* 141 */       paramString2 = paramString1 + '/' + paramString2;
/*     */     }
/* 143 */     return paramString2;
/*     */   }
/*     */ 
/*     */   public static boolean checkAlphaTransparent(int paramInt)
/*     */   {
/* 151 */     return (paramInt != -1) && (paramInt >>> 24 != 255);
/*     */   }
/*     */ 
/*     */   public static int parseInt(String paramString, int paramInt)
/*     */   {
/*     */     try
/*     */     {
/* 162 */       if (paramString == null) return paramInt;
/* 163 */       return Integer.parseInt(paramString); } catch (Exception localException) {
/*     */     }
/* 165 */     return paramInt;
/*     */   }
/*     */ 
/*     */   public static float parseFloat(String paramString, float paramFloat)
/*     */   {
/*     */     try {
/* 171 */       if (paramString == null) return paramFloat;
/* 172 */       return Float.parseFloat(paramString); } catch (Exception localException) {
/*     */     }
/* 174 */     return paramFloat;
/*     */   }
/*     */ 
/*     */   public static float parseFloat(String paramString, float paramFloat1, float paramFloat2)
/*     */   {
/* 187 */     if (paramString == null) return paramFloat2;
/* 188 */     paramString = paramString.toLowerCase();
/* 189 */     if (paramString.endsWith("px"))
/* 190 */       paramString = paramString.substring(0, paramString.length() - 2);
/*     */     try
/*     */     {
/* 193 */       return Float.parseFloat(paramString);
/*     */     } catch (NumberFormatException localNumberFormatException) {
/* 195 */       if (paramString.endsWith("%")) {
/* 196 */         paramString = paramString.substring(0, paramString.length() - 1);
/*     */         try {
/* 198 */           return paramFloat1 * Float.parseFloat(paramString) / 100.0F;
/*     */         } catch (Exception localException) {
/* 200 */           return paramFloat2;
/*     */         }
/*     */       }
/*     */     }
/* 204 */     return paramFloat2;
/*     */   }
/*     */ 
/*     */   public static int convertToScreenInt(String paramString, int paramInt1, int paramInt2, float paramFloat)
/*     */   {
/*     */     try
/*     */     {
/* 220 */       if (paramString == null) return paramInt2;
/* 221 */       if (paramString.endsWith("px")) {
/* 222 */         paramString = paramString.substring(0, paramString.length() - 2);
/* 223 */         if ((paramString != null) && (paramString.contains("."))) {
/* 224 */           return (int)(Float.parseFloat(paramString) * paramFloat);
/*     */         }
/* 226 */         return (int)(Integer.parseInt(paramString) * paramFloat);
/*     */       }
/* 228 */       if (paramString.endsWith("%")) {
/* 229 */         paramString = paramString.substring(0, paramString.length() - 1);
/*     */         try {
/* 231 */           return paramInt1 * Integer.parseInt(paramString) / 100;
/*     */         } catch (NumberFormatException localNumberFormatException) {
/* 233 */           return paramInt2;
/*     */         }
/*     */       }
/* 236 */       return (int)(Double.parseDouble(paramString) * paramFloat);
/*     */     } catch (Exception localException) {
/*     */     }
/* 239 */     return paramInt2;
/*     */   }
/*     */ 
/*     */   public static int parseInt(String paramString, int paramInt1, int paramInt2)
/*     */   {
/*     */     try
/*     */     {
/* 253 */       if (paramString == null) return paramInt2;
/* 254 */       paramString = paramString.toLowerCase();
/* 255 */       if (paramString.endsWith("px")) {
/* 256 */         paramString = paramString.substring(0, paramString.length() - 2);
/* 257 */         return Integer.parseInt(paramString);
/*     */       }
/* 259 */       if (paramString.endsWith("%")) {
/* 260 */         paramString = paramString.substring(0, paramString.length() - 1);
/*     */         try {
/* 262 */           return paramInt1 * Integer.parseInt(paramString) / 100;
/*     */         } catch (NumberFormatException localNumberFormatException) {
/* 264 */           return paramInt2;
/*     */         }
/*     */       }
/* 267 */       if (paramString.startsWith("#")) {
/* 268 */         paramString = "0x" + paramString.substring(1);
/*     */       }
/* 270 */       if (paramString.startsWith("0x")) {
/* 271 */         return Integer.valueOf(paramString.substring(2), 16).intValue();
/*     */       }
/* 273 */       return Integer.parseInt(paramString);
/*     */     } catch (Exception localException) {
/*     */     }
/* 276 */     return paramInt2;
/*     */   }
/*     */ 
/*     */   private static void loadProperties2HashMap(HashMap<String, String> paramHashMap, String paramString)
/*     */   {
/* 289 */     InputStream localInputStream = PlatformUtil.getResInputStream(paramString);
/* 290 */     Properties localProperties = new Properties();
/*     */     try {
/* 292 */       localProperties.load(localInputStream);
/* 293 */       Enumeration localEnumeration = localProperties.propertyNames();
/* 294 */       if (localEnumeration != null)
/*     */       {
/* 296 */         while (localEnumeration.hasMoreElements()) {
/* 297 */           String str = (String)localEnumeration.nextElement();
/* 298 */           paramHashMap.put(str.toLowerCase(), (String)localProperties.get(str));
/*     */         }
/*     */       }
/*     */     } catch (IOException localIOException) {
/* 302 */       localIOException.printStackTrace();
/*     */     } finally {
/* 304 */       IOUtil.close(localInputStream);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object getKeyByValue(HashMap paramHashMap, Object paramObject, boolean paramBoolean)
/*     */   {
/* 315 */     if ((paramBoolean) && (!paramHashMap.containsValue(paramObject))) return null;
/* 316 */     Iterator localIterator = paramHashMap.keySet().iterator();
/* 317 */     while (localIterator.hasNext()) {
/* 318 */       Object localObject1 = localIterator.next();
/* 319 */       Object localObject2 = paramHashMap.get(localObject1);
/* 320 */       if ((localObject2 != null) && (localObject2.equals(paramObject))) {
/* 321 */         return localObject1;
/*     */       }
/*     */     }
/* 324 */     return null;
/*     */   }
/*     */ 
/*     */   public static Object getKeyByIndex(HashMap paramHashMap, int paramInt)
/*     */   {
/* 333 */     if (paramInt < 0) return null;
/* 334 */     Iterator localIterator = paramHashMap.keySet().iterator();
/* 335 */     int i = 0;
/* 336 */     while (localIterator.hasNext()) {
/* 337 */       Object localObject = localIterator.next();
/* 338 */       if (paramInt == i) {
/* 339 */         return localObject;
/*     */       }
/* 341 */       i++;
/*     */     }
/* 343 */     return null;
/*     */   }
/*     */   public static Object getKeyByValue(HashMap paramHashMap, Object paramObject) {
/* 346 */     return getKeyByValue(paramHashMap, paramObject, false);
/*     */   }
/*     */ 
/*     */   public static void loadProperties2HashMap(HashMap<String, String> paramHashMap1, HashMap<String, String> paramHashMap2, HashMap<String, HashMap<String, String>> paramHashMap, String paramString)
/*     */   {
/* 359 */     InputStream localInputStream = PlatformUtil.getResInputStream(paramString);
/*     */     Object localObject3;
/*     */     Object localObject4;
/*     */     Object localObject5;
/*     */     Object localObject6;
/* 360 */     if (localInputStream != null) {
/* 361 */       XmlUtil.DHNode localDHNode1 = XmlUtil.XML_Parser(localInputStream);
/* 362 */       if (localDHNode1 != null) {
/* 363 */         XmlUtil.DHNode localDHNode2 = XmlUtil.getElement(localDHNode1, "features");
/* 364 */         ArrayList localArrayList = XmlUtil.getElements(localDHNode2, "feature");
/* 365 */         if ((localArrayList != null) && (!localArrayList.isEmpty())) {
/* 366 */           for (localObject1 = localArrayList.iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (XmlUtil.DHNode)((Iterator)localObject1).next();
/* 367 */             localObject3 = XmlUtil.getAttributeValue((XmlUtil.DHNode)localObject2, "name").toLowerCase();
/* 368 */             localObject4 = XmlUtil.getAttributeValue((XmlUtil.DHNode)localObject2, "value");
/* 369 */             if ("ui".equals(localObject3)) {
/* 370 */               paramHashMap2.put("webview", localObject4);
/*     */             }
/* 372 */             paramHashMap2.put(localObject3, localObject4);
/* 373 */             localObject5 = XmlUtil.getElements((XmlUtil.DHNode)localObject2, "module");
/* 374 */             if ((localObject5 != null) && (!((ArrayList)localObject5).isEmpty())) {
/* 375 */               localObject6 = (HashMap)paramHashMap.get(localObject3);
/* 376 */               if (localObject6 == null) {
/* 377 */                 localObject6 = new LinkedHashMap(2);
/* 378 */                 paramHashMap.put(localObject3, localObject6);
/*     */               }
/* 380 */               for (XmlUtil.DHNode localDHNode3 : (ArrayList)localObject5) {
/* 381 */                 String str1 = XmlUtil.getAttributeValue(localDHNode3, "name").toLowerCase();
/* 382 */                 String str2 = XmlUtil.getAttributeValue(localDHNode3, "value");
/* 383 */                 ((HashMap)localObject6).put(str1, str2);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 388 */         Object localObject1 = XmlUtil.getElement(localDHNode1, "services");
/* 389 */         Object localObject2 = XmlUtil.getElements((XmlUtil.DHNode)localObject1, "service");
/* 390 */         if ((localObject2 != null) && (!((ArrayList)localObject2).isEmpty()))
/* 391 */           for (localObject3 = ((ArrayList)localObject2).iterator(); ((Iterator)localObject3).hasNext(); ) { localObject4 = (XmlUtil.DHNode)((Iterator)localObject3).next();
/* 392 */             localObject5 = XmlUtil.getAttributeValue((XmlUtil.DHNode)localObject4, "name").toLowerCase();
/* 393 */             localObject6 = XmlUtil.getAttributeValue((XmlUtil.DHNode)localObject4, "value");
/* 394 */             paramHashMap1.put(localObject5, localObject6);
/*     */           }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isEmpty(Object paramObject)
/*     */   {
/* 407 */     return (paramObject == null) || (paramObject.equals("")) || ((paramObject.toString().length() == 4) && (paramObject.toString().toLowerCase().equals("null")));
/*     */   }
/*     */ 
/*     */   public static String getNonString(String paramString1, String paramString2)
/*     */   {
/* 417 */     return isEmpty(paramString1) ? paramString2 : paramString1;
/*     */   }
/*     */ 
/*     */   public static boolean isEquals(String paramString1, String paramString2)
/*     */   {
/* 427 */     if (isEmpty(paramString1)) return false;
/* 428 */     if (isEmpty(paramString2)) return false;
/* 429 */     return paramString1.equalsIgnoreCase(paramString2);
/*     */   }
/*     */ 
/*     */   public static boolean isContains(String paramString1, String paramString2)
/*     */   {
/* 438 */     if (isEmpty(paramString1)) return false;
/* 439 */     if (isEmpty(paramString2)) return false;
/* 440 */     return paramString1.contains(paramString2);
/*     */   }
/*     */ 
/*     */   public static int stringToColor(String paramString)
/*     */   {
/* 449 */     int i = -1;
/*     */     try {
/* 451 */       int j = paramString.length();
/* 452 */       int k = 255;
/* 453 */       int m = 255;
/* 454 */       int n = 255;
/* 455 */       int i1 = 255;
/*     */       Object localObject;
/*     */       int i2;
/* 456 */       if ((j == 4) || (j == 7) || (j == 3) || (j == 6)) {
/* 457 */         if ((j == 4) || (j == 7)) {
/* 458 */           paramString = paramString.substring(1);
/*     */         }
/* 460 */         if (paramString.length() == 3) {
/* 461 */           localObject = new StringBuffer();
/* 462 */           for (i2 = 0; i2 < 3; i2++) {
/* 463 */             ((StringBuffer)localObject).append(paramString.charAt(i2)).append(paramString.charAt(i2));
/*     */           }
/* 465 */           paramString = ((StringBuffer)localObject).toString();
/*     */         }
/*     */ 
/* 468 */         i = (k << 24) + Integer.parseInt(paramString, 16);
/*     */       }
/*     */       else
/*     */       {
/* 473 */         localObject = null;
/* 474 */         i2 = 0;
/* 475 */         if (paramString.startsWith("rgba")) {
/* 476 */           paramString = paramString.substring("rgba".length() + 1, paramString.length() - 1);
/* 477 */           localObject = paramString.split(",");
/* 478 */           i2 = 1;
/* 479 */         } else if (paramString.startsWith("rgb")) {
/* 480 */           paramString = paramString.substring("rgb".length() + 1, paramString.length() - 1);
/* 481 */           localObject = paramString.split(",");
/*     */         }
/*     */         try {
/* 484 */           if (localObject != null)
/* 485 */             if (i2 == 0) {
/* 486 */               m = Integer.parseInt(localObject[0]);
/* 487 */               n = Integer.parseInt(localObject[1]);
/* 488 */               i1 = Integer.parseInt(localObject[2]);
/*     */             } else {
/* 490 */               m = Integer.parseInt(localObject[0]);
/* 491 */               n = Integer.parseInt(localObject[1]);
/* 492 */               i1 = Integer.parseInt(localObject[2]);
/* 493 */               k = (int)(k * Float.parseFloat(localObject[3]));
/*     */             }
/*     */         }
/*     */         catch (Exception localException2) {
/*     */         }
/* 498 */         i = (k << 24) + (m << 16) + (n << 8) + i1;
/*     */       }
/*     */     }
/*     */     catch (Exception localException1)
/*     */     {
/*     */     }
/* 504 */     return i;
/*     */   }
/*     */ 
/*     */   public static String getMimeType(String paramString)
/*     */   {
/* 514 */     MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
/* 515 */     String str1 = MimeTypeMap.getFileExtensionFromUrl(paramString);
/* 516 */     if ((isEmpty(str1)) && (paramString.lastIndexOf(".") >= 0)) {
/* 517 */       str1 = paramString.substring(paramString.lastIndexOf(".") + 1);
/*     */     }
/* 519 */     String str2 = localMimeTypeMap.getMimeTypeFromExtension(str1);
/* 520 */     return TextUtils.isEmpty(str2) ? "application/" + str1 : TextUtils.isEmpty(str1) ? "*/*" : str2;
/*     */   }
/*     */ 
/*     */   public static String getExtensionFromMimeType(String paramString)
/*     */   {
/* 529 */     return MimeTypeMap.getSingleton().getExtensionFromMimeType(paramString);
/*     */   }
/*     */ 
/*     */   public static boolean isNetPath(String paramString)
/*     */   {
/* 537 */     return (paramString != null) && (((paramString.startsWith("http://")) && (!paramString.startsWith("http://localhost"))) || ((paramString.startsWith("https://")) && (!paramString.startsWith("https://localhost"))));
/*     */   }
/*     */ 
/*     */   public static String getDownloadFilename(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 545 */     Object localObject = null;
/*     */     try {
/* 547 */       if (!isEmpty(paramString1)) {
/* 548 */         String str1 = paramString1.substring(paramString1.indexOf(";") + 1);
/* 549 */         String[] arrayOfString = str1.trim().split("=");
/* 550 */         String str3 = arrayOfString[0].replace("\"", "");
/* 551 */         String str4 = arrayOfString[1].replace("\"", "");
/* 552 */         if ((!isEmpty(arrayOfString[1])) && 
/* 553 */           (isEquals("filename", str3)) && (!isEmpty(str4)))
/* 554 */           localObject = str4;
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {
/* 558 */       Logger.d("PdrUtil.getDownloadFilename " + paramString1 + " not found filename");
/*     */     }
/* 560 */     if (isEmpty(localObject)) {
/* 561 */       int i = paramString3.lastIndexOf('/');
/* 562 */       if ((i > 0) && (i < paramString3.length() - 1)) {
/* 563 */         localObject = paramString3.substring(i + 1);
/* 564 */         i = ((String)localObject).indexOf("?");
/* 565 */         if (i > 0)
/* 566 */           if (i < ((String)localObject).length() - 1)
/* 567 */             localObject = ((String)localObject).substring(0, i);
/*     */           else
/* 569 */             localObject = String.valueOf(System.currentTimeMillis());
/*     */       }
/*     */       else
/*     */       {
/* 573 */         localObject = String.valueOf(System.currentTimeMillis());
/*     */       }
/*     */     }
/* 576 */     if (((String)localObject).indexOf(".") < 0) {
/* 577 */       String str2 = getExtensionFromMimeType(paramString2);
/* 578 */       if (!isEmpty(str2)) {
/* 579 */         localObject = (String)localObject + "." + str2;
/*     */       }
/*     */     }
/*     */     try
/*     */     {
/* 584 */       localObject = URLDecoder.decode((String)localObject, "UTF-8");
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/* 586 */       localUnsupportedEncodingException.printStackTrace();
/*     */     }
/* 588 */     return localObject;
/*     */   }
/*     */ 
/*     */   public static Bitmap renderCroppedGreyscaleBitmap(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 603 */     int[] arrayOfInt = new int[paramInt5 * paramInt6];
/* 604 */     byte[] arrayOfByte = paramArrayOfByte;
/* 605 */     int i = paramInt4 * paramInt1 + paramInt3;
/*     */ 
/* 607 */     for (int j = 0; j < paramInt6; j++) {
/* 608 */       int k = j * paramInt5;
/* 609 */       for (int m = 0; m < paramInt5; m++) {
/* 610 */         int n = arrayOfByte[(i + m)] & 0xFF;
/* 611 */         arrayOfInt[(k + m)] = (0xFF000000 | n * 65793);
/*     */       }
/* 613 */       i += paramInt1;
/*     */     }
/*     */ 
/* 616 */     Bitmap localBitmap = Bitmap.createBitmap(paramInt5, paramInt6, Bitmap.Config.ARGB_8888);
/* 617 */     localBitmap.setPixels(arrayOfInt, 0, paramInt5, 0, 0, paramInt5, paramInt6);
/* 618 */     return localBitmap;
/*     */   }
/*     */ 
/*     */   public static String getDefaultPrivateDocPath(String paramString1, String paramString2)
/*     */   {
/* 629 */     if (isEmpty(paramString1))
/* 630 */       paramString1 = "_doc/" + System.currentTimeMillis();
/* 631 */     else if (paramString1.endsWith("/")) {
/* 632 */       paramString1 = paramString1 + System.currentTimeMillis();
/*     */     }
/* 634 */     if (!paramString1.endsWith("." + paramString2)) {
/* 635 */       paramString1 = paramString1 + "." + paramString2;
/*     */     }
/* 637 */     return paramString1;
/*     */   }
/*     */ 
/*     */   public static boolean saveBitmapToFile(Bitmap paramBitmap, String paramString)
/*     */   {
/* 647 */     boolean bool = false;
/*     */     try {
/* 649 */       File localFile1 = new File(paramString);
/* 650 */       File localFile2 = localFile1.getParentFile();
/* 651 */       if (!localFile2.exists()) {
/* 652 */         localFile2.mkdirs();
/*     */       }
/* 654 */       if (localFile1.exists()) {
/* 655 */         localFile1.delete();
/*     */       }
/* 657 */       localFile1.createNewFile();
/* 658 */       FileOutputStream localFileOutputStream = new FileOutputStream(localFile1);
/* 659 */       paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
/* 660 */       bool = true;
/*     */     } catch (Exception localException) {
/* 662 */       localException.printStackTrace();
/*     */     }
/* 664 */     return bool;
/*     */   }
/*     */ 
/*     */   public static void alert(Activity paramActivity, String paramString, Bitmap paramBitmap)
/*     */   {
/* 675 */     AlertDialog localAlertDialog = new AlertDialog.Builder(paramActivity).create();
/* 676 */     LinearLayout localLinearLayout = new LinearLayout(paramActivity);
/* 677 */     localLinearLayout.setOrientation(1);
/* 678 */     localLinearLayout.setGravity(17);
/* 679 */     TextView localTextView = new TextView(paramActivity);
/* 680 */     localTextView.setText(paramString);
/* 681 */     localLinearLayout.addView(localTextView);
/* 682 */     ImageView localImageView = new ImageView(paramActivity);
/* 683 */     localImageView.setBackgroundDrawable(new BitmapDrawable(paramBitmap));
/* 684 */     localLinearLayout.addView(localImageView, new ViewGroup.LayoutParams(-2, -2));
/* 685 */     localAlertDialog.setView(localLinearLayout);
/* 686 */     localAlertDialog.setCanceledOnTouchOutside(false);
/* 687 */     localAlertDialog.setButton("确定", new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/* 690 */         this.val$ad.dismiss();
/*     */       }
/*     */     });
/* 693 */     localAlertDialog.show();
/*     */   }
/*     */ 
/*     */   public static void toast(Context paramContext, String paramString, Bitmap paramBitmap) {
/* 697 */     Toast localToast = Toast.makeText(paramContext, paramString, 1);
/* 698 */     if (paramBitmap != null) {
/* 699 */       int i = paramBitmap.getHeight();
/* 700 */       int j = paramBitmap.getWidth();
/* 701 */       View localView = localToast.getView();
/* 702 */       ImageView localImageView = new ImageView(paramContext);
/* 703 */       localImageView.setImageBitmap(paramBitmap);
/* 704 */       ((ViewGroup)localView).addView(localImageView, 0);
/* 705 */       localToast.setText(paramString + " w=" + j + ";h=" + i);
/*     */     }
/* 707 */     localToast.setDuration(1);
/* 708 */     localToast.show();
/*     */   }
/*     */ 
/*     */   public static boolean isDeviceRootDir(String paramString)
/*     */   {
/* 717 */     return (paramString.startsWith(DeviceInfo.sDeviceRootDir)) || (paramString.startsWith("/sdcard/")) || (paramString.startsWith(DeviceInfo.sDeviceRootDir.substring(1))) || (paramString.startsWith("sdcard/"));
/*     */   }
/*     */ 
/*     */   public static String appendByDeviceRootDir(String paramString)
/*     */   {
/* 728 */     if (paramString == null) return paramString;
/* 729 */     if (!paramString.startsWith(DeviceInfo.sDeviceRootDir)) {
/* 730 */       if (paramString.startsWith("file://")) {
/* 731 */         paramString = paramString.substring("file://".length());
/*     */       }
/* 733 */       if (paramString.indexOf("sdcard/") > -1) {
/* 734 */         paramString = paramString.substring(paramString.indexOf("sdcard/") + "sdcard/".length());
/*     */       }
/* 736 */       paramString = paramString + "/";
/* 737 */       return DeviceInfo.sDeviceRootDir + "/" + paramString;
/*     */     }
/* 739 */     return paramString;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.PdrUtil
 * JD-Core Version:    0.6.2
 */