/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.content.ContentResolver;
/*     */ import android.content.ContentUris;
/*     */ import android.content.Context;
/*     */ import android.database.Cursor;
/*     */ import android.net.Uri;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Environment;
/*     */ import android.provider.DocumentsContract;
/*     */ import android.provider.MediaStore.Audio.Media;
/*     */ import android.provider.MediaStore.Images.Media;
/*     */ import android.provider.MediaStore.Video.Media;
/*     */ 
/*     */ public class ContentUriUtil
/*     */ {
/*     */   public static String getImageAbsolutePath(Context paramContext, Uri paramUri)
/*     */   {
/*  23 */     if ((paramContext == null) || (paramUri == null)) return null;
/*  24 */     if ((Build.VERSION.SDK_INT >= 19) && (DocumentsContract.isDocumentUri(paramContext, paramUri)))
/*     */     {
/*     */       String str1;
/*     */       Object localObject1;
/*     */       Object localObject2;
/*  26 */       if (isMediaDocument(paramUri)) {
/*  27 */         str1 = DocumentsContract.getDocumentId(paramUri);
/*  28 */         localObject1 = str1.split(":");
/*  29 */         localObject2 = localObject1[0];
/*  30 */         Uri localUri = null;
/*  31 */         if ("image".equals(localObject2))
/*  32 */           localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
/*  33 */         else if ("video".equals(localObject2))
/*  34 */           localUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
/*  35 */         else if ("audio".equals(localObject2)) {
/*  36 */           localUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
/*     */         }
/*  38 */         String str2 = "_id=?";
/*  39 */         String[] arrayOfString = { localObject1[1] };
/*  40 */         return getDataColumn(paramContext, localUri, str2, arrayOfString);
/*  41 */       }if (isExternalStorageDocument(paramUri)) {
/*  42 */         str1 = DocumentsContract.getDocumentId(paramUri);
/*  43 */         localObject1 = str1.split(":");
/*  44 */         localObject2 = localObject1[0];
/*  45 */         if ("primary".equalsIgnoreCase(localObject2))
/*  46 */           return Environment.getExternalStorageDirectory() + "/" + localObject1[1];
/*     */       }
/*  48 */       else if (isDownloadsDocument(paramUri)) {
/*  49 */         str1 = DocumentsContract.getDocumentId(paramUri);
/*  50 */         localObject1 = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(str1).longValue());
/*  51 */         return getDataColumn(paramContext, (Uri)localObject1, null, null);
/*     */       }
/*     */     } else {
/*  54 */       if ("content".equalsIgnoreCase(paramUri.getScheme()))
/*     */       {
/*  56 */         if (isGooglePhotosUri(paramUri))
/*  57 */           return paramUri.getLastPathSegment();
/*  58 */         return getDataColumn(paramContext, paramUri, null, null);
/*     */       }
/*     */ 
/*  61 */       if ("file".equalsIgnoreCase(paramUri.getScheme()))
/*  62 */         return paramUri.getPath();
/*     */     }
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getDataColumn(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString) {
/*  68 */     Cursor localCursor = null;
/*  69 */     String str1 = "_data";
/*  70 */     String[] arrayOfString = { str1 };
/*     */     try {
/*  72 */       localCursor = paramContext.getContentResolver().query(paramUri, arrayOfString, paramString, paramArrayOfString, null);
/*  73 */       if ((localCursor != null) && (localCursor.moveToFirst())) {
/*  74 */         int i = localCursor.getColumnIndexOrThrow(str1);
/*  75 */         return localCursor.getString(i);
/*     */       }
/*     */     } finally {
/*  78 */       if (localCursor != null)
/*  79 */         localCursor.close();
/*     */     }
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean isExternalStorageDocument(Uri paramUri)
/*     */   {
/*  89 */     return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
/*     */   }
/*     */ 
/*     */   public static boolean isDownloadsDocument(Uri paramUri)
/*     */   {
/*  97 */     return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
/*     */   }
/*     */ 
/*     */   public static boolean isMediaDocument(Uri paramUri)
/*     */   {
/* 105 */     return "com.android.providers.media.documents".equals(paramUri.getAuthority());
/*     */   }
/*     */ 
/*     */   public static boolean isGooglePhotosUri(Uri paramUri)
/*     */   {
/* 113 */     return "com.google.android.apps.photos.content".equals(paramUri.getAuthority());
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.ContentUriUtil
 * JD-Core Version:    0.6.2
 */