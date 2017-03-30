/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public class Md5
/*     */ {
/*     */   static final int S11 = 7;
/*     */   static final int S12 = 12;
/*     */   static final int S13 = 17;
/*     */   static final int S14 = 22;
/*     */   static final int S21 = 5;
/*     */   static final int S22 = 9;
/*     */   static final int S23 = 14;
/*     */   static final int S24 = 20;
/*     */   static final int S31 = 4;
/*     */   static final int S32 = 11;
/*     */   static final int S33 = 16;
/*     */   static final int S34 = 23;
/*     */   static final int S41 = 6;
/*     */   static final int S42 = 10;
/*     */   static final int S43 = 15;
/*     */   static final int S44 = 21;
/*  42 */   static final byte[] PADDING = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
/*     */ 
/*  52 */   private long[] state = new long[4];
/*  53 */   private long[] count = new long[2];
/*  54 */   private byte[] buffer = new byte[64];
/*     */   public String digestHexStr;
/*  63 */   private byte[] digest = new byte[16];
/*     */   public static final int BUFFERSIZE = 51200;
/*     */ 
/*     */   public String getMD5ofStr(String paramString)
/*     */   {
/*  70 */     md5Init();
/*  71 */     md5Update(paramString.getBytes(), paramString.length());
/*  72 */     md5Final();
/*  73 */     this.digestHexStr = "";
/*  74 */     for (int i = 0; i < 16; i++) {
/*  75 */       this.digestHexStr += byteHEX(this.digest[i]);
/*     */     }
/*  77 */     return this.digestHexStr;
/*     */   }
/*     */   public byte[] getMD5ofBytes(String paramString) {
/*  80 */     md5Init();
/*  81 */     byte[] arrayOfByte = null;
/*  82 */     arrayOfByte = paramString.getBytes();
/*     */ 
/*  88 */     md5Update(arrayOfByte, arrayOfByte.length);
/*  89 */     md5Final();
/*  90 */     return this.digest;
/*     */   }
/*     */ 
/*     */   public Md5() {
/*  94 */     md5Init();
/*     */   }
/*     */ 
/*     */   public byte[] getMD5ofStream(InputStream paramInputStream)
/*     */   {
/* 104 */     md5Init();
/* 105 */     byte[] arrayOfByte = new byte[51200];
/*     */     try {
/*     */       while (true) {
/* 108 */         int i = paramInputStream.read(arrayOfByte, 0, 51200);
/* 109 */         if (i == -1) {
/* 110 */           md5Final();
/* 111 */           return this.digest;
/*     */         }
/*     */ 
/* 114 */         md5Update(arrayOfByte, i);
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 119 */       localIOException.printStackTrace();
/* 120 */     }return null;
/*     */   }
/*     */ 
/*     */   private void md5Init()
/*     */   {
/* 125 */     this.count[0] = 0L;
/* 126 */     this.count[1] = 0L;
/*     */ 
/* 129 */     this.state[0] = 1732584193L;
/* 130 */     this.state[1] = 4023233417L;
/* 131 */     this.state[2] = 2562383102L;
/* 132 */     this.state[3] = 271733878L;
/*     */   }
/*     */ 
/*     */   private long F(long paramLong1, long paramLong2, long paramLong3)
/*     */   {
/* 142 */     return paramLong1 & paramLong2 | (paramLong1 ^ 0xFFFFFFFF) & paramLong3;
/*     */   }
/*     */ 
/*     */   private long G(long paramLong1, long paramLong2, long paramLong3)
/*     */   {
/* 147 */     return paramLong1 & paramLong3 | paramLong2 & (paramLong3 ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */   private long H(long paramLong1, long paramLong2, long paramLong3)
/*     */   {
/* 152 */     return paramLong1 ^ paramLong2 ^ paramLong3;
/*     */   }
/*     */ 
/*     */   private long I(long paramLong1, long paramLong2, long paramLong3) {
/* 156 */     return paramLong2 ^ (paramLong1 | paramLong3 ^ 0xFFFFFFFF);
/*     */   }
/*     */ 
/*     */   private long FF(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
/*     */   {
/* 167 */     paramLong1 += F(paramLong2, paramLong3, paramLong4) + paramLong5 + paramLong7;
/* 168 */     paramLong1 = (int)paramLong1 << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6);
/* 169 */     paramLong1 += paramLong2;
/* 170 */     return paramLong1;
/*     */   }
/*     */ 
/*     */   private long GG(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
/*     */   {
/* 175 */     paramLong1 += G(paramLong2, paramLong3, paramLong4) + paramLong5 + paramLong7;
/* 176 */     paramLong1 = (int)paramLong1 << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6);
/* 177 */     paramLong1 += paramLong2;
/* 178 */     return paramLong1;
/*     */   }
/*     */ 
/*     */   private long HH(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
/*     */   {
/* 183 */     paramLong1 += H(paramLong2, paramLong3, paramLong4) + paramLong5 + paramLong7;
/* 184 */     paramLong1 = (int)paramLong1 << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6);
/* 185 */     paramLong1 += paramLong2;
/* 186 */     return paramLong1;
/*     */   }
/*     */ 
/*     */   private long II(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, long paramLong7)
/*     */   {
/* 191 */     paramLong1 += I(paramLong2, paramLong3, paramLong4) + paramLong5 + paramLong7;
/* 192 */     paramLong1 = (int)paramLong1 << (int)paramLong6 | (int)paramLong1 >>> (int)(32L - paramLong6);
/* 193 */     paramLong1 += paramLong2;
/* 194 */     return paramLong1;
/*     */   }
/*     */ 
/*     */   private void md5Update(byte[] paramArrayOfByte, int paramInt)
/*     */   {
/* 204 */     byte[] arrayOfByte = new byte[64];
/* 205 */     int j = (int)(this.count[0] >>> 3) & 0x3F;
/*     */ 
/* 207 */     if (this.count[0] += (paramInt << 3) < paramInt << 3)
/* 208 */       this.count[1] += 1L;
/* 209 */     this.count[1] += (paramInt >>> 29);
/*     */ 
/* 211 */     int k = 64 - j;
/*     */     int i;
/* 214 */     if (paramInt >= k) {
/* 215 */       md5Memcpy(this.buffer, paramArrayOfByte, j, 0, k);
/* 216 */       md5Transform(this.buffer);
/*     */ 
/* 218 */       for (i = k; i + 63 < paramInt; i += 64)
/*     */       {
/* 220 */         md5Memcpy(arrayOfByte, paramArrayOfByte, 0, i, 64);
/* 221 */         md5Transform(arrayOfByte);
/*     */       }
/* 223 */       j = 0;
/*     */     }
/*     */     else
/*     */     {
/* 228 */       i = 0;
/*     */     }
/*     */ 
/* 231 */     md5Memcpy(this.buffer, paramArrayOfByte, j, i, paramInt - i);
/*     */   }
/*     */ 
/*     */   private void md5Final()
/*     */   {
/* 239 */     byte[] arrayOfByte = new byte[8];
/*     */ 
/* 243 */     Encode(arrayOfByte, this.count, 8);
/*     */ 
/* 246 */     int i = (int)(this.count[0] >>> 3) & 0x3F;
/* 247 */     int j = i < 56 ? 56 - i : 120 - i;
/* 248 */     md5Update(PADDING, j);
/*     */ 
/* 251 */     md5Update(arrayOfByte, 8);
/*     */ 
/* 254 */     Encode(this.digest, this.state, 16);
/*     */   }
/*     */ 
/*     */   private void md5Memcpy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 266 */     for (int i = 0; i < paramInt3; i++)
/* 267 */       paramArrayOfByte1[(paramInt1 + i)] = paramArrayOfByte2[(paramInt2 + i)];
/*     */   }
/*     */ 
/*     */   private void md5Transform(byte[] paramArrayOfByte)
/*     */   {
/* 274 */     long l1 = this.state[0]; long l2 = this.state[1]; long l3 = this.state[2]; long l4 = this.state[3];
/* 275 */     long[] arrayOfLong = new long[16];
/*     */ 
/* 277 */     Decode(arrayOfLong, paramArrayOfByte, 64);
/*     */ 
/* 280 */     l1 = FF(l1, l2, l3, l4, arrayOfLong[0], 7L, 3614090360L);
/* 281 */     l4 = FF(l4, l1, l2, l3, arrayOfLong[1], 12L, 3905402710L);
/* 282 */     l3 = FF(l3, l4, l1, l2, arrayOfLong[2], 17L, 606105819L);
/* 283 */     l2 = FF(l2, l3, l4, l1, arrayOfLong[3], 22L, 3250441966L);
/* 284 */     l1 = FF(l1, l2, l3, l4, arrayOfLong[4], 7L, 4118548399L);
/* 285 */     l4 = FF(l4, l1, l2, l3, arrayOfLong[5], 12L, 1200080426L);
/* 286 */     l3 = FF(l3, l4, l1, l2, arrayOfLong[6], 17L, 2821735955L);
/* 287 */     l2 = FF(l2, l3, l4, l1, arrayOfLong[7], 22L, 4249261313L);
/* 288 */     l1 = FF(l1, l2, l3, l4, arrayOfLong[8], 7L, 1770035416L);
/* 289 */     l4 = FF(l4, l1, l2, l3, arrayOfLong[9], 12L, 2336552879L);
/* 290 */     l3 = FF(l3, l4, l1, l2, arrayOfLong[10], 17L, 4294925233L);
/* 291 */     l2 = FF(l2, l3, l4, l1, arrayOfLong[11], 22L, 2304563134L);
/* 292 */     l1 = FF(l1, l2, l3, l4, arrayOfLong[12], 7L, 1804603682L);
/* 293 */     l4 = FF(l4, l1, l2, l3, arrayOfLong[13], 12L, 4254626195L);
/* 294 */     l3 = FF(l3, l4, l1, l2, arrayOfLong[14], 17L, 2792965006L);
/* 295 */     l2 = FF(l2, l3, l4, l1, arrayOfLong[15], 22L, 1236535329L);
/*     */ 
/* 298 */     l1 = GG(l1, l2, l3, l4, arrayOfLong[1], 5L, 4129170786L);
/* 299 */     l4 = GG(l4, l1, l2, l3, arrayOfLong[6], 9L, 3225465664L);
/* 300 */     l3 = GG(l3, l4, l1, l2, arrayOfLong[11], 14L, 643717713L);
/* 301 */     l2 = GG(l2, l3, l4, l1, arrayOfLong[0], 20L, 3921069994L);
/* 302 */     l1 = GG(l1, l2, l3, l4, arrayOfLong[5], 5L, 3593408605L);
/* 303 */     l4 = GG(l4, l1, l2, l3, arrayOfLong[10], 9L, 38016083L);
/* 304 */     l3 = GG(l3, l4, l1, l2, arrayOfLong[15], 14L, 3634488961L);
/* 305 */     l2 = GG(l2, l3, l4, l1, arrayOfLong[4], 20L, 3889429448L);
/* 306 */     l1 = GG(l1, l2, l3, l4, arrayOfLong[9], 5L, 568446438L);
/* 307 */     l4 = GG(l4, l1, l2, l3, arrayOfLong[14], 9L, 3275163606L);
/* 308 */     l3 = GG(l3, l4, l1, l2, arrayOfLong[3], 14L, 4107603335L);
/* 309 */     l2 = GG(l2, l3, l4, l1, arrayOfLong[8], 20L, 1163531501L);
/* 310 */     l1 = GG(l1, l2, l3, l4, arrayOfLong[13], 5L, 2850285829L);
/* 311 */     l4 = GG(l4, l1, l2, l3, arrayOfLong[2], 9L, 4243563512L);
/* 312 */     l3 = GG(l3, l4, l1, l2, arrayOfLong[7], 14L, 1735328473L);
/* 313 */     l2 = GG(l2, l3, l4, l1, arrayOfLong[12], 20L, 2368359562L);
/*     */ 
/* 316 */     l1 = HH(l1, l2, l3, l4, arrayOfLong[5], 4L, 4294588738L);
/* 317 */     l4 = HH(l4, l1, l2, l3, arrayOfLong[8], 11L, 2272392833L);
/* 318 */     l3 = HH(l3, l4, l1, l2, arrayOfLong[11], 16L, 1839030562L);
/* 319 */     l2 = HH(l2, l3, l4, l1, arrayOfLong[14], 23L, 4259657740L);
/* 320 */     l1 = HH(l1, l2, l3, l4, arrayOfLong[1], 4L, 2763975236L);
/* 321 */     l4 = HH(l4, l1, l2, l3, arrayOfLong[4], 11L, 1272893353L);
/* 322 */     l3 = HH(l3, l4, l1, l2, arrayOfLong[7], 16L, 4139469664L);
/* 323 */     l2 = HH(l2, l3, l4, l1, arrayOfLong[10], 23L, 3200236656L);
/* 324 */     l1 = HH(l1, l2, l3, l4, arrayOfLong[13], 4L, 681279174L);
/* 325 */     l4 = HH(l4, l1, l2, l3, arrayOfLong[0], 11L, 3936430074L);
/* 326 */     l3 = HH(l3, l4, l1, l2, arrayOfLong[3], 16L, 3572445317L);
/* 327 */     l2 = HH(l2, l3, l4, l1, arrayOfLong[6], 23L, 76029189L);
/* 328 */     l1 = HH(l1, l2, l3, l4, arrayOfLong[9], 4L, 3654602809L);
/* 329 */     l4 = HH(l4, l1, l2, l3, arrayOfLong[12], 11L, 3873151461L);
/* 330 */     l3 = HH(l3, l4, l1, l2, arrayOfLong[15], 16L, 530742520L);
/* 331 */     l2 = HH(l2, l3, l4, l1, arrayOfLong[2], 23L, 3299628645L);
/*     */ 
/* 334 */     l1 = II(l1, l2, l3, l4, arrayOfLong[0], 6L, 4096336452L);
/* 335 */     l4 = II(l4, l1, l2, l3, arrayOfLong[7], 10L, 1126891415L);
/* 336 */     l3 = II(l3, l4, l1, l2, arrayOfLong[14], 15L, 2878612391L);
/* 337 */     l2 = II(l2, l3, l4, l1, arrayOfLong[5], 21L, 4237533241L);
/* 338 */     l1 = II(l1, l2, l3, l4, arrayOfLong[12], 6L, 1700485571L);
/* 339 */     l4 = II(l4, l1, l2, l3, arrayOfLong[3], 10L, 2399980690L);
/* 340 */     l3 = II(l3, l4, l1, l2, arrayOfLong[10], 15L, 4293915773L);
/* 341 */     l2 = II(l2, l3, l4, l1, arrayOfLong[1], 21L, 2240044497L);
/* 342 */     l1 = II(l1, l2, l3, l4, arrayOfLong[8], 6L, 1873313359L);
/* 343 */     l4 = II(l4, l1, l2, l3, arrayOfLong[15], 10L, 4264355552L);
/* 344 */     l3 = II(l3, l4, l1, l2, arrayOfLong[6], 15L, 2734768916L);
/* 345 */     l2 = II(l2, l3, l4, l1, arrayOfLong[13], 21L, 1309151649L);
/* 346 */     l1 = II(l1, l2, l3, l4, arrayOfLong[4], 6L, 4149444226L);
/* 347 */     l4 = II(l4, l1, l2, l3, arrayOfLong[11], 10L, 3174756917L);
/* 348 */     l3 = II(l3, l4, l1, l2, arrayOfLong[2], 15L, 718787259L);
/* 349 */     l2 = II(l2, l3, l4, l1, arrayOfLong[9], 21L, 3951481745L);
/*     */ 
/* 351 */     this.state[0] += l1;
/* 352 */     this.state[1] += l2;
/* 353 */     this.state[2] += l3;
/* 354 */     this.state[3] += l4;
/*     */   }
/*     */ 
/*     */   private void Encode(byte[] paramArrayOfByte, long[] paramArrayOfLong, int paramInt)
/*     */   {
/* 364 */     int i = 0; for (int j = 0; j < paramInt; j += 4) {
/* 365 */       paramArrayOfByte[j] = ((byte)(int)(paramArrayOfLong[i] & 0xFF));
/* 366 */       paramArrayOfByte[(j + 1)] = ((byte)(int)(paramArrayOfLong[i] >>> 8 & 0xFF));
/* 367 */       paramArrayOfByte[(j + 2)] = ((byte)(int)(paramArrayOfLong[i] >>> 16 & 0xFF));
/* 368 */       paramArrayOfByte[(j + 3)] = ((byte)(int)(paramArrayOfLong[i] >>> 24 & 0xFF));
/*     */ 
/* 364 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void Decode(long[] paramArrayOfLong, byte[] paramArrayOfByte, int paramInt)
/*     */   {
/* 378 */     int i = 0; for (int j = 0; j < paramInt; j += 4) {
/* 379 */       paramArrayOfLong[i] = (b2iu(paramArrayOfByte[j]) | b2iu(paramArrayOfByte[(j + 1)]) << 8 | b2iu(paramArrayOfByte[(j + 2)]) << 16 | b2iu(paramArrayOfByte[(j + 3)]) << 24);
/*     */ 
/* 378 */       i++;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static long b2iu(byte paramByte)
/*     */   {
/* 391 */     return paramByte < 0 ? paramByte & 0xFF : paramByte;
/*     */   }
/*     */ 
/*     */   public static String byteHEX(byte paramByte)
/*     */   {
/* 399 */     char[] arrayOfChar1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/* 402 */     char[] arrayOfChar2 = new char[2];
/* 403 */     arrayOfChar2[0] = arrayOfChar1[(paramByte >>> 4 & 0xF)];
/* 404 */     arrayOfChar2[1] = arrayOfChar1[(paramByte & 0xF)];
/* 405 */     String str = new String(arrayOfChar2);
/* 406 */     return str;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.Md5
 * JD-Core Version:    0.6.2
 */