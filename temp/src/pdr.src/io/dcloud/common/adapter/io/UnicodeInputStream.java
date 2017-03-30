/*     */ package io.dcloud.common.adapter.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PushbackInputStream;
/*     */ 
/*     */ public class UnicodeInputStream extends InputStream
/*     */ {
/*     */   PushbackInputStream internalIn;
/*  19 */   boolean isInited = false;
/*     */   String defaultEnc;
/*     */   String encoding;
/*     */   private static final int BOM_SIZE = 4;
/*     */ 
/*     */   public UnicodeInputStream(InputStream paramInputStream, String paramString)
/*     */   {
/*  26 */     this.internalIn = new PushbackInputStream(paramInputStream, 4);
/*  27 */     this.defaultEnc = paramString;
/*     */   }
/*     */ 
/*     */   public String getDefaultEncoding() {
/*  31 */     return this.defaultEnc;
/*     */   }
/*     */ 
/*     */   public String getEncoding() {
/*  35 */     if (!this.isInited) {
/*     */       try {
/*  37 */         init();
/*     */       } catch (IOException localIOException) {
/*  39 */         IllegalStateException localIllegalStateException = new IllegalStateException("Init method failed.");
/*     */ 
/*  41 */         localIllegalStateException.initCause(localIllegalStateException);
/*  42 */         throw localIllegalStateException;
/*     */       }
/*     */     }
/*  45 */     return this.encoding;
/*     */   }
/*     */ 
/*     */   protected void init()
/*     */     throws IOException
/*     */   {
/*  53 */     if (this.isInited) {
/*  54 */       return;
/*     */     }
/*  56 */     byte[] arrayOfByte = new byte[4];
/*     */ 
/*  58 */     int i = this.internalIn.read(arrayOfByte, 0, arrayOfByte.length);
/*     */     int j;
/*  60 */     if ((arrayOfByte[0] == 0) && (arrayOfByte[1] == 0) && (arrayOfByte[2] == -2) && (arrayOfByte[3] == -1))
/*     */     {
/*  62 */       this.encoding = "UTF-32BE";
/*  63 */       j = i - 4;
/*  64 */     } else if ((arrayOfByte[0] == -1) && (arrayOfByte[1] == -2) && (arrayOfByte[2] == 0) && (arrayOfByte[3] == 0))
/*     */     {
/*  66 */       this.encoding = "UTF-32LE";
/*  67 */       j = i - 4;
/*  68 */     } else if ((arrayOfByte[0] == -17) && (arrayOfByte[1] == -69) && (arrayOfByte[2] == -65))
/*     */     {
/*  70 */       this.encoding = "UTF-8";
/*  71 */       j = i - 3;
/*  72 */     } else if ((arrayOfByte[0] == -2) && (arrayOfByte[1] == -1)) {
/*  73 */       this.encoding = "UTF-16BE";
/*  74 */       j = i - 2;
/*  75 */     } else if ((arrayOfByte[0] == -1) && (arrayOfByte[1] == -2)) {
/*  76 */       this.encoding = "UTF-16LE";
/*  77 */       j = i - 2;
/*     */     }
/*     */     else {
/*  80 */       this.encoding = this.defaultEnc;
/*  81 */       j = i;
/*     */     }
/*     */ 
/*  85 */     if (j > 0) {
/*  86 */       this.internalIn.unread(arrayOfByte, i - j, j);
/*     */     }
/*  88 */     this.isInited = true;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException
/*     */   {
/*  93 */     this.isInited = true;
/*  94 */     this.internalIn.close();
/*     */   }
/*     */ 
/*     */   public int read() throws IOException {
/*  98 */     init();
/*  99 */     this.isInited = true;
/* 100 */     return this.internalIn.read();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.io.UnicodeInputStream
 * JD-Core Version:    0.6.2
 */