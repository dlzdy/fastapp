/*    */ package io.dcloud.common.adapter.util;
/*    */ 
/*    */ import android.graphics.Rect;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ByteOrder;
/*    */ 
/*    */ public class NinePatchChunk
/*    */ {
/*    */   public static final int NO_COLOR = 1;
/*    */   public static final int TRANSPARENT_COLOR = 0;
/* 13 */   public Rect mPaddings = new Rect();
/*    */   public int[] mDivX;
/*    */   public int[] mDivY;
/*    */   public int[] mColor;
/*    */ 
/*    */   private static void readIntArray(int[] paramArrayOfInt, ByteBuffer paramByteBuffer)
/*    */   {
/* 20 */     int i = 0; for (int j = paramArrayOfInt.length; i < j; i++)
/* 21 */       paramArrayOfInt[i] = paramByteBuffer.getInt();
/*    */   }
/*    */ 
/*    */   private static void checkDivCount(int paramInt)
/*    */   {
/* 26 */     if ((paramInt == 0) || ((paramInt & 0x1) != 0))
/* 27 */       throw new RuntimeException("invalid nine-patch: " + paramInt);
/*    */   }
/*    */ 
/*    */   public static NinePatchChunk deserialize(byte[] paramArrayOfByte)
/*    */   {
/* 32 */     ByteBuffer localByteBuffer = ByteBuffer.wrap(paramArrayOfByte).order(ByteOrder.nativeOrder());
/*    */ 
/* 35 */     int i = localByteBuffer.get();
/* 36 */     if (i == 0) return null;
/*    */ 
/* 38 */     NinePatchChunk localNinePatchChunk = new NinePatchChunk();
/* 39 */     localNinePatchChunk.mDivX = new int[localByteBuffer.get()];
/* 40 */     localNinePatchChunk.mDivY = new int[localByteBuffer.get()];
/* 41 */     localNinePatchChunk.mColor = new int[localByteBuffer.get()];
/*    */ 
/* 43 */     checkDivCount(localNinePatchChunk.mDivX.length);
/* 44 */     checkDivCount(localNinePatchChunk.mDivY.length);
/*    */ 
/* 47 */     localByteBuffer.getInt();
/* 48 */     localByteBuffer.getInt();
/*    */ 
/* 50 */     localNinePatchChunk.mPaddings.left = localByteBuffer.getInt();
/* 51 */     localNinePatchChunk.mPaddings.right = localByteBuffer.getInt();
/* 52 */     localNinePatchChunk.mPaddings.top = localByteBuffer.getInt();
/* 53 */     localNinePatchChunk.mPaddings.bottom = localByteBuffer.getInt();
/*    */ 
/* 56 */     localByteBuffer.getInt();
/*    */ 
/* 58 */     readIntArray(localNinePatchChunk.mDivX, localByteBuffer);
/* 59 */     readIntArray(localNinePatchChunk.mDivY, localByteBuffer);
/* 60 */     readIntArray(localNinePatchChunk.mColor, localByteBuffer);
/*    */ 
/* 62 */     return localNinePatchChunk;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.NinePatchChunk
 * JD-Core Version:    0.6.2
 */