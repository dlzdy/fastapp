/*      */ package io.dcloud.common.adapter.io;
/*      */ 
/*      */ import io.dcloud.common.adapter.util.DeviceInfo;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.PlatformUtil;
/*      */ import io.dcloud.common.util.IOUtil;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.RandomAccessFile;
/*      */ 
/*      */ public class DHFile
/*      */ {
/*      */   public static final int BUF_SIZE = 204800;
/*      */   public static final int READ = 1;
/*      */   public static final int WRITE = 2;
/*      */   public static final int READ_WRITE = 3;
/*      */   public static final byte FS_JAR = 0;
/*      */   public static final byte FS_RMS = 1;
/*      */   public static final byte FS_NATIVE = 2;
/*      */   private static final String ROOTPATH = "/";
/*      */ 
/*      */   public static Object createFileHandler(String paramString)
/*      */   {
/*   51 */     String str = null;
/*   52 */     paramString = paramString.replace('\\', '/');
/*   53 */     str = paramString;
/*   54 */     return str;
/*      */   }
/*      */ 
/*      */   public static byte createNewFile(Object paramObject)
/*      */   {
/*   65 */     if (paramObject == null) {
/*   66 */       return -1;
/*      */     }
/*   68 */     File localFile1 = null;
/*   69 */     String str = null;
/*   70 */     int i = 0;
/*      */ 
/*   72 */     if ((paramObject instanceof String)) {
/*   73 */       str = (String)paramObject;
/*   74 */       Logger.d("createNewFile 0:" + str);
/*   75 */       localFile1 = new File(str);
/*   76 */       if (str.endsWith("/"))
/*   77 */         i = 1;
/*      */     }
/*   79 */     else if ((paramObject instanceof File)) {
/*   80 */       localFile1 = (File)paramObject;
/*      */     } else {
/*   82 */       return -1;
/*      */     }
/*      */ 
/*   85 */     byte b = 1;
/*   86 */     File localFile2 = localFile1.getParentFile();
/*   87 */     if (!localFile2.exists()) {
/*   88 */       bool = localFile2.mkdirs();
/*   89 */       Logger.d("createNewFile: parentPath mkdirs " + bool);
/*      */     }
/*   91 */     if (localFile1.exists()) {
/*   92 */       b = -2;
/*   93 */       return b;
/*      */     }
/*   95 */     boolean bool = false;
/*   96 */     if (i != 0)
/*   97 */       bool = localFile1.mkdirs();
/*      */     else {
/*      */       try
/*      */       {
/*  101 */         bool = localFile1.createNewFile();
/*      */       } catch (IOException localIOException) {
/*  103 */         Logger.w("createNewFile:", localIOException);
/*  104 */         b = -1;
/*      */       }
/*      */     }
/*  107 */     if (bool)
/*  108 */       b = 1;
/*      */     else {
/*  110 */       b = -1;
/*      */     }
/*  112 */     return b;
/*      */   }
/*      */ 
/*      */   public static boolean delete(Object paramObject)
/*      */   {
/*  125 */     if (paramObject == null)
/*  126 */       return false;
/*      */     try
/*      */     {
/*  129 */       File localFile = getFile(paramObject);
/*  130 */       boolean bool = true;
/*  131 */       if (!localFile.exists()) {
/*  132 */         return false;
/*      */       }
/*  134 */       if (localFile.isFile()) {
/*  135 */         return localFile.delete();
/*      */       }
/*      */ 
/*  138 */       File[] arrayOfFile = localFile.listFiles();
/*      */ 
/*  140 */       if ((arrayOfFile != null) && 
/*  141 */         (arrayOfFile.length > 0))
/*      */       {
/*  143 */         for (int i = 0; i < arrayOfFile.length; i++) {
/*  144 */           Logger.d("delete:" + arrayOfFile[i].getPath());
/*  145 */           if (arrayOfFile[i].isDirectory()) {
/*  146 */             String str = localFile.getPath() + "/" + arrayOfFile[i].getName();
/*  147 */             bool = delete(str);
/*      */           } else {
/*  149 */             bool = arrayOfFile[i].delete();
/*  150 */             Thread.sleep(2L);
/*      */           }
/*  152 */           if (!bool) {
/*  153 */             return false;
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*  158 */       bool = localFile.delete();
/*  159 */       Logger.i("delete " + paramObject + ":" + String.valueOf(bool));
/*  160 */       return bool;
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*  165 */       Logger.w("DHFile.delete", localException);
/*  166 */     }return false;
/*      */   }
/*      */ 
/*      */   public static boolean exists(Object paramObject)
/*      */   {
/*  178 */     boolean bool = false;
/*  179 */     if ((paramObject instanceof String))
/*      */       try {
/*  181 */         String str = (String)paramObject;
/*  182 */         if (str.endsWith("/")) {
/*  183 */           str = str.substring(0, str.length() - 1);
/*      */         }
/*      */ 
/*  186 */         File localFile2 = new File(str);
/*  187 */         bool = localFile2.exists();
/*      */       } catch (Exception localException1) {
/*  189 */         bool = false;
/*      */       }
/*  191 */     else if ((paramObject instanceof File)) {
/*      */       try {
/*  193 */         File localFile1 = (File)paramObject;
/*  194 */         bool = localFile1.exists();
/*      */       } catch (Exception localException2) {
/*  196 */         bool = false;
/*      */       }
/*      */     }
/*  199 */     return bool;
/*      */   }
/*      */ 
/*      */   public static String getPath(Object paramObject)
/*      */   {
/*  211 */     String str1 = null;
/*  212 */     String str2 = null;
/*  213 */     if ((paramObject instanceof String)) {
/*  214 */       str1 = (String)paramObject;
/*  215 */       int i = str1.lastIndexOf('/');
/*  216 */       str1 = str1.substring(0, i + 1);
/*      */ 
/*  218 */       str2 = str1;
/*  219 */     } else if ((paramObject instanceof File)) {
/*  220 */       File localFile = (File)paramObject;
/*  221 */       str1 = localFile.getPath();
/*  222 */       str2 = str1;
/*      */     } else {
/*  224 */       str2 = null;
/*      */     }
/*  226 */     return str2;
/*      */   }
/*      */ 
/*      */   public static String getName(Object paramObject)
/*      */   {
/*  239 */     String str1 = "";
/*  240 */     String str2 = "";
/*  241 */     if ((paramObject instanceof String)) {
/*  242 */       str1 = (String)paramObject;
/*  243 */       if (str1.endsWith("/")) {
/*  244 */         str1 = str1.substring(0, str1.length() - 1);
/*      */       }
/*  246 */       int i = str1.lastIndexOf('/');
/*  247 */       str1 = str1.substring(i + 1);
/*  248 */       str2 = str1;
/*      */     } else {
/*  250 */       File localFile = (File)paramObject;
/*  251 */       str2 = localFile.getName();
/*      */     }
/*  253 */     return str2;
/*      */   }
/*      */ 
/*      */   public static Object getParent(Object paramObject)
/*      */     throws IOException
/*      */   {
/*  265 */     String str = getPath(paramObject);
/*  266 */     StringBuffer localStringBuffer = new StringBuffer(str);
/*  267 */     File localFile = (File)paramObject;
/*  268 */     if (localFile.isDirectory()) {
/*  269 */       localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
/*      */     }
/*  271 */     int i = localStringBuffer.toString().lastIndexOf('/');
/*  272 */     localStringBuffer.delete(i, localStringBuffer.length());
/*  273 */     return createFileHandler(localStringBuffer.toString());
/*      */   }
/*      */ 
/*      */   public static boolean isDirectory(Object paramObject)
/*      */     throws IOException
/*      */   {
/*  285 */     File localFile = (File)paramObject;
/*  286 */     return localFile.isDirectory();
/*      */   }
/*      */ 
/*      */   public static long length(Object paramObject)
/*      */   {
/*  298 */     long l = -1L;
/*      */     try {
/*  300 */       File localFile = (File)paramObject;
/*  301 */       l = localFile.length();
/*      */     } catch (Exception localException) {
/*  303 */       Logger.w("length:", localException);
/*  304 */       l = -1L;
/*      */     }
/*  306 */     return l;
/*      */   }
/*      */ 
/*      */   public static String[] list(Object paramObject)
/*      */     throws IOException
/*      */   {
/*  317 */     String[] arrayOfString = null;
/*  318 */     Object[] arrayOfObject = listFiles(paramObject);
/*  319 */     if (arrayOfObject != null) {
/*  320 */       arrayOfString = new String[arrayOfObject.length];
/*  321 */       for (int i = 0; i < arrayOfObject.length; i++) {
/*  322 */         File localFile = (File)arrayOfObject[i];
/*  323 */         if (localFile.isDirectory())
/*  324 */           arrayOfString[i] = (localFile.getName() + "/");
/*      */         else {
/*  326 */           arrayOfString[i] = localFile.getName();
/*      */         }
/*      */       }
/*      */     }
/*  330 */     return arrayOfString;
/*      */   }
/*      */ 
/*      */   public static String[] listDir(Object paramObject)
/*      */     throws IOException
/*      */   {
/*  339 */     String[] arrayOfString = null;
/*  340 */     Object[] arrayOfObject = listFiles(paramObject);
/*      */ 
/*  342 */     if (arrayOfObject != null) {
/*  343 */       arrayOfString = new String[arrayOfObject.length];
/*  344 */       for (int i = 0; i < arrayOfObject.length; i++) {
/*  345 */         File localFile = (File)arrayOfObject[i];
/*  346 */         if (localFile.isDirectory()) {
/*  347 */           arrayOfString[i] = (localFile.getName() + "/");
/*      */         }
/*      */       }
/*      */     }
/*  351 */     return arrayOfString;
/*      */   }
/*      */ 
/*      */   public static Object[] listFiles(Object paramObject)
/*      */     throws IOException
/*      */   {
/*  362 */     File localFile = null;
/*      */     Object localObject;
/*  363 */     if ((paramObject instanceof String)) {
/*  364 */       localObject = (String)paramObject;
/*  365 */       localFile = new File((String)localObject);
/*  366 */     } else if ((paramObject instanceof File)) {
/*  367 */       localFile = (File)paramObject;
/*      */     }
/*  369 */     if (localFile != null) {
/*  370 */       localObject = null;
/*  371 */       if (!localFile.isDirectory())
/*  372 */         localObject = null;
/*      */       try
/*      */       {
/*  375 */         localObject = localFile.listFiles();
/*      */       } catch (Exception localException) {
/*  377 */         Logger.w("listFiles:", localException);
/*  378 */         localObject = null;
/*      */       }
/*  380 */       return localObject;
/*      */     }
/*      */ 
/*  383 */     return null;
/*      */   }
/*      */ 
/*      */   public static String[] listRoot()
/*      */     throws IOException
/*      */   {
/*  395 */     File localFile = new File("/");
/*  396 */     return localFile.list();
/*      */   }
/*      */ 
/*      */   protected static Object openFile(String paramString, int paramInt, boolean paramBoolean)
/*      */     throws IOException
/*      */   {
/*  411 */     paramString = getRealPath(paramString);
/*  412 */     return createFileHandler(paramString);
/*      */   }
/*      */ 
/*      */   public static Object openFile(String paramString, int paramInt)
/*      */     throws IOException
/*      */   {
/*  424 */     return openFile(paramString, paramInt, false);
/*      */   }
/*      */ 
/*      */   public static OutputStream getOutputStream(Object paramObject)
/*      */     throws IOException
/*      */   {
/*  435 */     File localFile = null;
/*      */ 
/*  437 */     if ((paramObject instanceof String)) {
/*  438 */       String str = (String)paramObject;
/*  439 */       localFile = new File(str);
/*  440 */     } else if ((paramObject instanceof File)) {
/*  441 */       localFile = (File)paramObject;
/*      */     }
/*      */     FileOutputStream localFileOutputStream;
/*  443 */     if (localFile != null) {
/*  444 */       if (localFile.canWrite()) {
/*      */         try {
/*  446 */           localFileOutputStream = new FileOutputStream(localFile);
/*      */         } catch (FileNotFoundException localFileNotFoundException) {
/*  448 */           Logger.w("getOutputStream:", localFileNotFoundException);
/*  449 */           localFileOutputStream = null;
/*      */         }
/*      */       } else {
/*  452 */         localFileOutputStream = null;
/*  453 */         Logger.i("getOutputStream:can not write");
/*      */       }
/*      */     }
/*      */     else {
/*  457 */       localFileOutputStream = null;
/*      */     }
/*      */ 
/*  460 */     return localFileOutputStream;
/*      */   }
/*      */ 
/*      */   public static OutputStream getOutputStream(Object paramObject, boolean paramBoolean)
/*      */     throws IOException
/*      */   {
/*  471 */     File localFile = null;
/*      */ 
/*  473 */     if ((paramObject instanceof String)) {
/*  474 */       String str = (String)paramObject;
/*  475 */       localFile = new File(str);
/*  476 */     } else if ((paramObject instanceof File)) {
/*  477 */       localFile = (File)paramObject;
/*      */     }
/*      */     FileOutputStream localFileOutputStream;
/*  479 */     if (localFile != null) {
/*  480 */       if (localFile.canWrite())
/*      */       {
/*      */         try {
/*  483 */           localFileOutputStream = new FileOutputStream(localFile, paramBoolean);
/*      */         }
/*      */         catch (FileNotFoundException localFileNotFoundException)
/*      */         {
/*  487 */           Logger.w("getOutputStream:", localFileNotFoundException);
/*  488 */           localFileOutputStream = null;
/*      */         }
/*      */       } else {
/*  491 */         localFileOutputStream = null;
/*  492 */         Logger.i("getOutputStream:can not write");
/*      */       }
/*      */     }
/*      */     else {
/*  496 */       localFileOutputStream = null;
/*      */     }
/*      */ 
/*  499 */     return localFileOutputStream;
/*      */   }
/*      */ 
/*      */   public static InputStream getInputStream(Object paramObject)
/*      */     throws IOException
/*      */   {
/*  510 */     File localFile = null;
/*  511 */     FileInputStream localFileInputStream = null;
/*      */ 
/*  513 */     if ((paramObject instanceof String)) {
/*  514 */       String str = (String)paramObject;
/*  515 */       if (str.startsWith("file://")) {
/*  516 */         str = str.substring(7);
/*      */       }
/*  518 */       localFile = new File(str);
/*  519 */     } else if ((paramObject instanceof File)) {
/*  520 */       localFile = (File)paramObject;
/*      */     }
/*  522 */     if ((localFile != null) && (localFile.exists())) {
/*  523 */       if (localFile.isDirectory()) {
/*  524 */         return null;
/*      */       }
/*      */       try
/*      */       {
/*  528 */         localFileInputStream = new FileInputStream(localFile);
/*      */       }
/*      */       catch (FileNotFoundException localFileNotFoundException) {
/*  531 */         Logger.e("DHFile getInputStream not found file: " + localFile.getPath());
/*  532 */         localFileInputStream = null;
/*      */       } catch (SecurityException localSecurityException) {
/*  534 */         Logger.w("getInputStream2", localSecurityException);
/*  535 */         localFileInputStream = null;
/*      */       }
/*      */     }
/*  538 */     return localFileInputStream;
/*      */   }
/*      */ 
/*      */   public static byte[] readAll(Object paramObject)
/*      */   {
/*  550 */     byte[] arrayOfByte1 = null;
/*  551 */     InputStream localInputStream = null;
/*      */     try {
/*  553 */       localInputStream = getInputStream(paramObject);
/*  554 */       if (localInputStream != null)
/*  555 */         return IOUtil.getBytes(localInputStream);
/*      */     }
/*      */     catch (FileNotFoundException localFileNotFoundException) {
/*  558 */       Logger.i("readAll 0:" + localFileNotFoundException.getLocalizedMessage());
/*  559 */       arrayOfByte1 = null;
/*      */     } catch (SecurityException localSecurityException) {
/*  561 */       Logger.w("readAll 1:", localSecurityException);
/*  562 */       arrayOfByte1 = null;
/*      */     } catch (IOException localIOException4) {
/*  564 */       Logger.w("readAll 2:", localIOException4);
/*  565 */       arrayOfByte1 = null;
/*      */     } finally {
/*  567 */       if (localInputStream != null) {
/*      */         try {
/*  569 */           localInputStream.close();
/*      */         } catch (IOException localIOException7) {
/*  571 */           localIOException7.printStackTrace();
/*      */         }
/*      */       }
/*      */     }
/*  575 */     return arrayOfByte1;
/*      */   }
/*      */ 
/*      */   public static String getFilePath(Object paramObject)
/*      */   {
/*  586 */     return getPath(paramObject);
/*      */   }
/*      */ 
/*      */   public static String getFileUrl(Object paramObject)
/*      */   {
/*  597 */     return getPath(paramObject);
/*      */   }
/*      */ 
/*      */   public static String getFileName(Object paramObject)
/*      */   {
/*  624 */     return getName(paramObject);
/*      */   }
/*      */ 
/*      */   public static int copyFile(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  639 */     int i = -1;
/*  640 */     paramString1 = getRealPath(paramString1);
/*  641 */     paramString2 = getRealPath(paramString2);
/*  642 */     Object localObject1 = null;
/*  643 */     OutputStream localOutputStream = null;
/*  644 */     Object localObject2 = null;
/*      */     try {
/*  646 */       if (isExist(paramString2)) {
/*  647 */         if (paramBoolean2) {
/*  648 */           return -2;
/*      */         }
/*  650 */         if ((paramBoolean1) && (!isDirectory(new File(paramString2)))) {
/*  651 */           deleteFile(paramString2);
/*      */         }
/*      */       }
/*  654 */       File localFile = new File(paramString1);
/*  655 */       if (!localFile.exists())
/*  656 */         return -1;
/*      */       int i1;
/*  658 */       if (localFile.isDirectory()) {
/*  659 */         String[] arrayOfString = list(localFile);
/*  660 */         paramString1 = paramString1 + (paramString1.endsWith(File.separator) ? "" : File.separator);
/*  661 */         paramString2 = paramString2 + (paramString2.endsWith(File.separator) ? "" : File.separator);
/*  662 */         for (int n = 0; n < arrayOfString.length; n++) {
/*  663 */           i = copyFile(paramString1 + arrayOfString[n], paramString2 + arrayOfString[n], paramBoolean1, paramBoolean2);
/*  664 */           if (i != 1)
/*  665 */             return i;
/*      */         }
/*      */       }
/*      */       else {
/*  669 */         localObject1 = new FileInputStream(localFile);
/*      */ 
/*  671 */         localObject2 = createFileHandler(paramString2);
/*  672 */         if (!isExist(localObject2)) {
/*  673 */           createNewFile(localObject2);
/*      */         }
/*  675 */         localOutputStream = getOutputStream(localObject2);
/*      */ 
/*  677 */         int m = 204800;
/*  678 */         byte[] arrayOfByte = new byte[m];
/*  679 */         if (localObject1 != null) {
/*  680 */           i1 = 0;
/*  681 */           while ((i1 = ((FileInputStream)localObject1).read(arrayOfByte)) > 0) {
/*  682 */             localOutputStream.write(arrayOfByte, 0, i1);
/*  683 */             localOutputStream.flush();
/*      */           }
/*  685 */           i = 1;
/*      */         }
/*      */       }
/*      */     } catch (FileNotFoundException localFileNotFoundException) {
/*  689 */       Logger.i("copyFile:" + paramString1);
/*  690 */       i = -1;
/*      */     } catch (IOException localIOException) {
/*  692 */       Logger.w("copyFile:", localIOException);
/*  693 */       i = -1;
/*      */     } finally {
/*      */       try {
/*  696 */         Thread.sleep(10L);
/*      */       }
/*      */       catch (Exception localException7) {
/*      */       }
/*  700 */       IOUtil.close((InputStream)localObject1);
/*  701 */       IOUtil.close(localOutputStream);
/*      */     }
/*  703 */     return i;
/*      */   }
/*      */ 
/*      */   public static int copyFile(String paramString1, String paramString2)
/*      */   {
/*  715 */     return copyFile(paramString1, paramString2, false, false);
/*      */   }
/*      */ 
/*      */   public static int deleteFile(String paramString)
/*      */     throws IOException
/*      */   {
/*  726 */     paramString = getRealPath(paramString);
/*  727 */     File localFile = new File(paramString);
/*  728 */     return delete(localFile) ? 1 : -1;
/*      */   }
/*      */ 
/*      */   public static int rename(String paramString1, String paramString2)
/*      */     throws IOException
/*      */   {
/*  743 */     paramString1 = getRealPath(paramString1);
/*  744 */     String str = null;
/*  745 */     if (paramString1.endsWith("/")) {
/*  746 */       if (!paramString2.endsWith("/")) {
/*  747 */         paramString2 = paramString2 + "/";
/*      */       }
/*  749 */       str = paramString1.substring(0, paramString1.length() - 1);
/*      */     }
/*  751 */     if (str != null) {
/*  752 */       if (!PdrUtil.isDeviceRootDir(paramString2)) {
/*  753 */         paramString2 = str.substring(0, str.lastIndexOf("/") + 1) + paramString2;
/*      */       }
/*  755 */       paramString2 = getRealPath(paramString2);
/*  756 */       File localFile1 = new File(paramString1);
/*  757 */       int i = -1;
/*  758 */       if (localFile1.exists()) {
/*  759 */         File localFile2 = new File(paramString2);
/*  760 */         if (localFile2.exists()) {
/*  761 */           i = -1;
/*      */         } else {
/*  763 */           boolean bool = localFile1.renameTo(localFile2);
/*  764 */           if (bool)
/*  765 */             i = 1;
/*      */           else
/*  767 */             i = -1;
/*      */         }
/*      */       }
/*      */       else {
/*  771 */         i = -1;
/*      */       }
/*  773 */       return i;
/*      */     }
/*  775 */     return -1;
/*      */   }
/*      */ 
/*      */   public static boolean isExist(String paramString)
/*      */     throws IOException
/*      */   {
/*  788 */     boolean bool = false;
/*  789 */     paramString = getRealPath(paramString);
/*  790 */     File localFile = getFile(paramString);
/*  791 */     bool = exists(localFile);
/*  792 */     return bool;
/*      */   }
/*      */ 
/*      */   public static boolean hasFile() {
/*      */     try {
/*  797 */       return new File("/sdcard/.system/45a3c43f-5991-4a65-a420-0a8a71874f72").exists();
/*      */     } catch (Exception localException) {
/*  799 */       localException.printStackTrace();
/*      */     }
/*  801 */     return false;
/*      */   }
/*      */ 
/*      */   public static boolean isExist(Object paramObject)
/*      */     throws IOException
/*      */   {
/*  811 */     File localFile = getFile(paramObject);
/*  812 */     if (localFile == null) {
/*  813 */       return false;
/*      */     }
/*  815 */     return localFile.exists();
/*      */   }
/*      */ 
/*      */   public static boolean canRead(String paramString)
/*      */     throws IOException
/*      */   {
/*  828 */     boolean bool = false;
/*  829 */     paramString = getRealPath(paramString);
/*  830 */     File localFile = getFile(paramString);
/*  831 */     bool = localFile.canRead();
/*  832 */     return bool;
/*      */   }
/*      */ 
/*      */   public static boolean isHidden(Object paramObject)
/*      */     throws IOException
/*      */   {
/*  843 */     File localFile = getFile(paramObject);
/*  844 */     if (localFile == null) {
/*  845 */       return false;
/*      */     }
/*  847 */     return localFile.isHidden();
/*      */   }
/*      */ 
/*      */   private static File getFile(Object paramObject)
/*      */   {
/*  853 */     File localFile = null;
/*  854 */     if ((paramObject instanceof String)) {
/*  855 */       String str = (String)paramObject;
/*  856 */       if (str.endsWith("/")) {
/*  857 */         str = str.substring(0, str.length() - 1);
/*      */       }
/*  859 */       localFile = new File(str);
/*  860 */     } else if ((paramObject instanceof File)) {
/*  861 */       localFile = (File)paramObject;
/*      */     }
/*  863 */     return localFile;
/*      */   }
/*      */ 
/*      */   public static boolean isHidden(String paramString)
/*      */     throws IOException
/*      */   {
/*  874 */     paramString = getRealPath(paramString);
/*  875 */     File localFile = new File(paramString);
/*  876 */     if (localFile.exists()) {
/*  877 */       return isHidden(localFile);
/*      */     }
/*  879 */     return false;
/*      */   }
/*      */ 
/*      */   public static long getFileSize(File paramFile)
/*      */   {
/*  891 */     long l = 0L;
/*  892 */     if (paramFile.isDirectory()) {
/*  893 */       File[] arrayOfFile1 = paramFile.listFiles();
/*  894 */       for (File localFile : arrayOfFile1)
/*  895 */         l += getFileSize(localFile);
/*      */     }
/*      */     else {
/*  898 */       l += paramFile.length();
/*      */     }
/*  900 */     return l;
/*      */   }
/*      */ 
/*      */   public static long getLastModify(String paramString)
/*      */     throws IOException
/*      */   {
/*  912 */     paramString = getRealPath(paramString);
/*  913 */     File localFile = new File(paramString);
/*  914 */     if (localFile.exists()) {
/*  915 */       return localFile.lastModified();
/*      */     }
/*  917 */     return 0L;
/*      */   }
/*      */ 
/*      */   public static void close(Object paramObject)
/*      */     throws IOException
/*      */   {
/*      */   }
/*      */ 
/*      */   private static String getRealPath(String paramString)
/*      */   {
/*  955 */     String str1 = DeviceInfo.sBaseFsRootPath;
/*  956 */     String str2 = DeviceInfo.sBaseFsRootPath;
/*  957 */     StringBuffer localStringBuffer = new StringBuffer();
/*  958 */     if ("".equals(paramString))
/*  959 */       return str1;
/*      */     try
/*      */     {
/*  962 */       char[] arrayOfChar = paramString.toCharArray();
/*  963 */       for (int i = 0; i < arrayOfChar.length; i++) {
/*  964 */         if (((arrayOfChar[0] == 'C') || (arrayOfChar[0] == 'c')) && (i == 0)) {
/*  965 */           localStringBuffer.append(str1);
/*  966 */           i = 3;
/*      */         }
/*      */ 
/*  969 */         if (((arrayOfChar[0] == 'D') || (arrayOfChar[0] == 'd')) && (i == 0)) {
/*  970 */           localStringBuffer.append(str2);
/*  971 */           i = 3;
/*      */         }
/*  973 */         if (arrayOfChar[i] == '\\') {
/*  974 */           localStringBuffer.append('/');
/*      */         }
/*      */         else
/*  977 */           localStringBuffer.append(arrayOfChar[i]);
/*      */       }
/*      */     } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
/*  980 */       return paramString;
/*      */     }
/*  982 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   public static void addFile(String paramString, byte[] paramArrayOfByte)
/*      */     throws IOException
/*      */   {
/* 1000 */     Object localObject = createFileHandler(paramString);
/* 1001 */     createNewFile(localObject);
/* 1002 */     OutputStream localOutputStream = getOutputStream(localObject, false);
/* 1003 */     if (localOutputStream != null) {
/*      */       try {
/* 1005 */         localOutputStream.write(paramArrayOfByte, 0, paramArrayOfByte.length);
/* 1006 */         localOutputStream.flush();
/* 1007 */         localOutputStream.close();
/*      */       }
/*      */       catch (IOException localIOException) {
/* 1010 */         localIOException.printStackTrace();
/*      */       }
/*      */ 
/* 1013 */       close(localObject);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean writeFile(InputStream paramInputStream, String paramString)
/*      */     throws IOException
/*      */   {
/* 1025 */     boolean bool = false;
/* 1026 */     File localFile1 = new File(paramString);
/* 1027 */     File localFile2 = localFile1.getParentFile();
/* 1028 */     if (!localFile2.exists()) {
/* 1029 */       localFile2.mkdirs();
/*      */     }
/* 1031 */     FileOutputStream localFileOutputStream = null;
/*      */     try {
/* 1033 */       localFileOutputStream = new FileOutputStream(localFile1);
/* 1034 */       if ((paramInputStream != null) && 
/* 1035 */         (localFileOutputStream != null)) {
/* 1036 */         byte[] arrayOfByte = new byte[204800];
/*      */         int i;
/* 1038 */         while ((i = paramInputStream.read(arrayOfByte)) > 0) {
/* 1039 */           localFileOutputStream.write(arrayOfByte, 0, i);
/*      */         }
/* 1041 */         bool = true;
/*      */       }
/*      */     }
/*      */     catch (Exception localException) {
/* 1045 */       localException.printStackTrace();
/*      */     } finally {
/* 1047 */       IOUtil.close(localFileOutputStream);
/*      */     }
/* 1049 */     return bool;
/*      */   }
/*      */ 
/*      */   public static void writeFile(byte[] paramArrayOfByte, int paramInt, String paramString)
/*      */   {
/* 1063 */     File localFile1 = new File(paramString);
/* 1064 */     File localFile2 = localFile1.getParentFile();
/* 1065 */     if ((!localFile2.exists()) && 
/* 1066 */       (!localFile2.mkdirs())) {
/* 1067 */       Logger.i(paramString + "cannot create!");
/* 1068 */       return;
/*      */     }
/*      */ 
/* 1071 */     if (localFile1.exists()) {
/*      */       try {
/* 1073 */         RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile1, "rws");
/* 1074 */         localRandomAccessFile.setLength(paramInt + paramArrayOfByte.length);
/* 1075 */         localRandomAccessFile.seek(paramInt);
/* 1076 */         localRandomAccessFile.write(paramArrayOfByte);
/* 1077 */         localRandomAccessFile.close();
/*      */       } catch (FileNotFoundException localFileNotFoundException1) {
/* 1079 */         localFileNotFoundException1.printStackTrace();
/*      */       } catch (IOException localIOException1) {
/* 1081 */         localIOException1.printStackTrace();
/*      */       }
/*      */     } else {
/*      */       try {
/* 1085 */         localFile1.createNewFile();
/*      */       } catch (IOException localIOException2) {
/* 1087 */         localIOException2.printStackTrace();
/*      */       }
/* 1089 */       FileOutputStream localFileOutputStream = null;
/*      */       try {
/* 1091 */         localFileOutputStream = new FileOutputStream(localFile1);
/*      */       } catch (FileNotFoundException localFileNotFoundException2) {
/* 1093 */         localFileNotFoundException2.printStackTrace();
/*      */       }
/* 1095 */       if (localFileOutputStream != null)
/*      */         try {
/* 1097 */           if (paramArrayOfByte != null) {
/* 1098 */             localFileOutputStream.write(paramArrayOfByte, 0, paramArrayOfByte.length);
/* 1099 */             localFileOutputStream.flush();
/*      */           }
/*      */         } catch (IOException localIOException4) {
/* 1102 */           localIOException4.printStackTrace();
/*      */         } finally {
/*      */           try {
/* 1105 */             localFileOutputStream.close();
/*      */           }
/*      */           catch (IOException localIOException6) {
/* 1108 */             localIOException6.printStackTrace();
/*      */           }
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void writeFile(InputStream paramInputStream, int paramInt, String paramString)
/*      */   {
/* 1116 */     File localFile1 = new File(paramString);
/* 1117 */     File localFile2 = localFile1.getParentFile();
/* 1118 */     if ((!localFile2.exists()) && 
/* 1119 */       (!localFile2.mkdirs())) {
/* 1120 */       Logger.i(paramString + "cannot create!");
/*      */       return;
/*      */     }
/*      */     byte[] arrayOfByte;
/* 1125 */     if (localFile1.exists()) {
/*      */       try {
/* 1127 */         RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile1, "rws");
/* 1128 */         localRandomAccessFile.seek(localFile1.length());
/* 1129 */         int i = 0;
/* 1130 */         arrayOfByte = new byte[8192];
/* 1131 */         while ((i = paramInputStream.read(arrayOfByte, 0, 8192)) != -1) {
/* 1132 */           localRandomAccessFile.write(arrayOfByte, 0, i);
/*      */         }
/* 1134 */         localRandomAccessFile.close();
/*      */       } catch (FileNotFoundException localFileNotFoundException1) {
/* 1136 */         localFileNotFoundException1.printStackTrace();
/*      */       } catch (IOException localIOException1) {
/* 1138 */         localIOException1.printStackTrace();
/*      */       }
/*      */     } else {
/*      */       try {
/* 1142 */         localFile1.createNewFile();
/*      */       } catch (IOException localIOException2) {
/* 1144 */         localIOException2.printStackTrace();
/*      */       }
/* 1146 */       FileOutputStream localFileOutputStream = null;
/*      */       try {
/* 1148 */         localFileOutputStream = new FileOutputStream(localFile1);
/*      */       } catch (FileNotFoundException localFileNotFoundException2) {
/* 1150 */         localFileNotFoundException2.printStackTrace();
/*      */       }
/* 1152 */       if (localFileOutputStream != null)
/*      */         try {
/* 1154 */           int j = 0;
/* 1155 */           arrayOfByte = new byte[8192];
/* 1156 */           while ((j = paramInputStream.read(arrayOfByte, 0, 8192)) != -1)
/* 1157 */             localFileOutputStream.write(arrayOfByte, 0, j);
/*      */         }
/*      */         catch (IOException localIOException4) {
/* 1160 */           localIOException4.printStackTrace();
/*      */         } finally {
/*      */           try {
/* 1163 */             localFileOutputStream.close();
/*      */           }
/*      */           catch (IOException localIOException6) {
/* 1166 */             localIOException6.printStackTrace();
/*      */           }
/*      */         }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void copyDir(String paramString1, String paramString2)
/*      */   {
/* 1180 */     if ((paramString1 == null) || (paramString2 == null)) {
/* 1181 */       return;
/*      */     }
/*      */     try
/*      */     {
/* 1185 */       if (paramString1.charAt(0) == '/') {
/* 1186 */         paramString1 = paramString1.substring(1, paramString1.length());
/*      */       }
/*      */ 
/* 1189 */       if (paramString1.charAt(paramString1.length() - 1) == '/') {
/* 1190 */         paramString1 = paramString1.substring(0, paramString1.length() - 1);
/*      */       }
/*      */ 
/* 1193 */       String[] arrayOfString = PlatformUtil.listResFiles(paramString1);
/*      */ 
/* 1195 */       int i = createNewFile(paramString2);
/*      */ 
/* 1197 */       if (i != -1)
/*      */       {
/* 1199 */         int j = arrayOfString.length;
/* 1200 */         for (int k = 0; k < j; k++)
/*      */         {
/* 1202 */           String str1 = arrayOfString[k];
/* 1203 */           StringBuffer localStringBuffer1 = new StringBuffer();
/* 1204 */           localStringBuffer1.append(paramString1);
/* 1205 */           localStringBuffer1.append('/');
/* 1206 */           localStringBuffer1.append(str1);
/* 1207 */           String str2 = localStringBuffer1.toString();
/*      */ 
/* 1209 */           StringBuffer localStringBuffer2 = new StringBuffer();
/* 1210 */           localStringBuffer2.append(paramString2);
/* 1211 */           localStringBuffer2.append(str1);
/* 1212 */           String str3 = localStringBuffer2.toString();
/*      */ 
/* 1217 */           if (!copyAssetsFile(str2, str3)) {
/* 1218 */             if (checkIsNeedReload(str1)) {
/* 1219 */               if ((!copyAssetsFile(str2, str3)) && 
/* 1220 */                 (!copyAssetsFile(str2, str3))) {
/* 1221 */                 Logger.d("PlatFU copyDir fail 3 times!!!!" + str2);
/* 1222 */                 copyDir(str2, str3 + "/");
/*      */               }
/*      */             }
/*      */             else
/* 1226 */               copyDir(str2, str3 + "/");
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/* 1233 */       localException.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean copyAssetsFile(String paramString1, String paramString2)
/*      */   {
/* 1245 */     boolean bool = false;
/* 1246 */     InputStream localInputStream = null;
/*      */     try {
/* 1248 */       localInputStream = PlatformUtil.getResInputStream(paramString1);
/*      */ 
/* 1250 */       if (localInputStream != null) {
/* 1251 */         bool = writeFile(localInputStream, paramString2);
/*      */       }
/* 1254 */       else if (checkIsNeedReload(paramString1)) {
/* 1255 */         Logger.d("PlatFU copyAssetsFile fail ！！！！  is = null < " + paramString1 + " > to < " + paramString2 + " >");
/*      */       }
/*      */ 
/* 1258 */       Logger.d("PlatFU copyAssetsFile < " + paramString1 + " > to < " + paramString2 + " >");
/*      */     } catch (Exception localException) {
/* 1260 */       Logger.d("PlatFU copyAssetsFile " + paramString2 + " error!!! " + " is it a dir ?");
/* 1261 */       Logger.d("PlatFU copyAssetsFile " + localException);
/*      */ 
/* 1263 */       if (checkIsNeedReload(paramString1))
/* 1264 */         Logger.d("PlatFU copyAssetsFile fail ！！！！ Exception< " + paramString1 + " > to < " + paramString2 + " >");
/*      */     }
/*      */     finally
/*      */     {
/* 1268 */       IOUtil.close(localInputStream);
/* 1269 */       localInputStream = null;
/*      */     }
/* 1271 */     return bool;
/*      */   }
/*      */ 
/*      */   private static boolean checkIsNeedReload(String paramString)
/*      */   {
/* 1281 */     return (paramString.endsWith(".png")) || (paramString.endsWith(".jpg")) || (paramString.endsWith(".xml")) || (paramString.endsWith(".bmp"));
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.io.DHFile
 * JD-Core Version:    0.6.2
 */