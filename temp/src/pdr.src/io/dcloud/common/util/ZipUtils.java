/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipException;
/*     */ import java.util.zip.ZipFile;
/*     */ import java.util.zip.ZipOutputStream;
/*     */ 
/*     */ public class ZipUtils
/*     */ {
/*     */   private static final int BUFF_SIZE = 1048576;
/*     */ 
/*     */   public static void zipFiles(File[] paramArrayOfFile, File paramFile)
/*     */     throws IOException
/*     */   {
/*  40 */     File localFile1 = paramFile.getParentFile();
/*  41 */     if (!localFile1.exists()) {
/*  42 */       localFile1.mkdirs();
/*     */     }
/*  44 */     ZipOutputStream localZipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(paramFile), 1048576));
/*     */ 
/*  46 */     for (File localFile2 : paramArrayOfFile) {
/*  47 */       zipFile(localFile2, localZipOutputStream, "");
/*     */     }
/*  49 */     localZipOutputStream.close();
/*     */   }
/*     */ 
/*     */   public static void upZipFile(File paramFile, String paramString)
/*     */     throws ZipException, IOException
/*     */   {
/*  59 */     if (!paramString.endsWith("/")) {
/*  60 */       paramString = paramString + File.separatorChar;
/*     */     }
/*  62 */     File localFile1 = new File(paramString);
/*  63 */     if (!localFile1.exists()) {
/*  64 */       localFile1.mkdirs();
/*     */     }
/*  66 */     ZipFile localZipFile = new ZipFile(paramFile);
/*  67 */     for (Enumeration localEnumeration = localZipFile.entries(); localEnumeration.hasMoreElements(); ) {
/*  68 */       ZipEntry localZipEntry = (ZipEntry)localEnumeration.nextElement();
/*  69 */       if (localZipEntry.getName().contains("../")) {
/*  70 */         Logger.d("ZIP", "util.ZipUtils Path traversal attack prevented path=" + localZipEntry.getName());
/*     */       }
/*     */       else {
/*  73 */         InputStream localInputStream = localZipFile.getInputStream(localZipEntry);
/*  74 */         String str = paramString + localZipEntry.getName();
/*     */ 
/*  76 */         str = new String(str.getBytes(), "UTF-8").replace("\\", "/");
/*  77 */         File localFile2 = new File(str);
/*  78 */         File localFile3 = localFile2.getParentFile();
/*  79 */         if (!localFile3.exists()) {
/*  80 */           localFile3.mkdirs();
/*     */         }
/*  82 */         if (!str.endsWith("/")) {
/*  83 */           if (localFile2.exists()) {
/*  84 */             localFile2.delete();
/*     */           }
/*  86 */           localFile2.createNewFile();
/*  87 */           FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
/*  88 */           byte[] arrayOfByte = new byte[1048576];
/*     */           int i;
/*  90 */           while ((i = localInputStream.read(arrayOfByte)) > 0) {
/*  91 */             localFileOutputStream.write(arrayOfByte, 0, i);
/*     */           }
/*  93 */           localInputStream.close();
/*  94 */           localFileOutputStream.close();
/*     */         }
/*     */       }
/*     */     }
/*  97 */     localZipFile.close();
/*     */   }
/*     */ 
/*     */   private static void zipFile(File paramFile, ZipOutputStream paramZipOutputStream, String paramString)
/*     */     throws FileNotFoundException, IOException
/*     */   {
/* 111 */     paramString = paramString + (paramString.trim().length() == 0 ? "" : File.separator) + paramFile.getName();
/*     */ 
/* 113 */     paramString = new String(paramString.getBytes("UTF-8"), "UTF-8");
/*     */     Object localObject1;
/* 114 */     if (paramFile.isDirectory()) {
/* 115 */       localObject1 = paramFile.listFiles();
/* 116 */       for (File localFile : localObject1)
/* 117 */         zipFile(localFile, paramZipOutputStream, paramString);
/*     */     }
/*     */     else {
/* 120 */       localObject1 = new byte[1048576];
/* 121 */       ??? = new BufferedInputStream(new FileInputStream(paramFile), 1048576);
/*     */ 
/* 123 */       paramZipOutputStream.putNextEntry(new ZipEntry(paramString));
/*     */ 
/* 125 */       while (( = ((BufferedInputStream)???).read((byte[])localObject1)) != -1) {
/* 126 */         paramZipOutputStream.write((byte[])localObject1, 0, ???);
/*     */       }
/* 128 */       ((BufferedInputStream)???).close();
/* 129 */       paramZipOutputStream.flush();
/* 130 */       paramZipOutputStream.closeEntry();
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ZipUtils
 * JD-Core Version:    0.6.2
 */