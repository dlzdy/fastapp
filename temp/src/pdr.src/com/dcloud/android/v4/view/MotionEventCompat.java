/*     */ package com.dcloud.android.v4.view;
/*     */ 
/*     */ import android.os.Build.VERSION;
/*     */ import android.view.MotionEvent;
/*     */ 
/*     */ public class MotionEventCompat
/*     */ {
/*     */   static final MotionEventVersionImpl IMPL;
/*     */   public static final int ACTION_MASK = 255;
/*     */   public static final int ACTION_POINTER_DOWN = 5;
/*     */   public static final int ACTION_POINTER_UP = 6;
/*     */   public static final int ACTION_HOVER_MOVE = 7;
/*     */   public static final int ACTION_SCROLL = 8;
/*     */   public static final int ACTION_POINTER_INDEX_MASK = 65280;
/*     */   public static final int ACTION_POINTER_INDEX_SHIFT = 8;
/*     */   public static final int ACTION_HOVER_ENTER = 9;
/*     */   public static final int ACTION_HOVER_EXIT = 10;
/*     */   public static final int AXIS_X = 0;
/*     */   public static final int AXIS_Y = 1;
/*     */   public static final int AXIS_PRESSURE = 2;
/*     */   public static final int AXIS_SIZE = 3;
/*     */   public static final int AXIS_TOUCH_MAJOR = 4;
/*     */   public static final int AXIS_TOUCH_MINOR = 5;
/*     */   public static final int AXIS_TOOL_MAJOR = 6;
/*     */   public static final int AXIS_TOOL_MINOR = 7;
/*     */   public static final int AXIS_ORIENTATION = 8;
/*     */   public static final int AXIS_VSCROLL = 9;
/*     */   public static final int AXIS_HSCROLL = 10;
/*     */   public static final int AXIS_Z = 11;
/*     */   public static final int AXIS_RX = 12;
/*     */   public static final int AXIS_RY = 13;
/*     */   public static final int AXIS_RZ = 14;
/*     */   public static final int AXIS_HAT_X = 15;
/*     */   public static final int AXIS_HAT_Y = 16;
/*     */   public static final int AXIS_LTRIGGER = 17;
/*     */   public static final int AXIS_RTRIGGER = 18;
/*     */   public static final int AXIS_THROTTLE = 19;
/*     */   public static final int AXIS_RUDDER = 20;
/*     */   public static final int AXIS_WHEEL = 21;
/*     */   public static final int AXIS_GAS = 22;
/*     */   public static final int AXIS_BRAKE = 23;
/*     */   public static final int AXIS_DISTANCE = 24;
/*     */   public static final int AXIS_TILT = 25;
/*     */   public static final int AXIS_GENERIC_1 = 32;
/*     */   public static final int AXIS_GENERIC_2 = 33;
/*     */   public static final int AXIS_GENERIC_3 = 34;
/*     */   public static final int AXIS_GENERIC_4 = 35;
/*     */   public static final int AXIS_GENERIC_5 = 36;
/*     */   public static final int AXIS_GENERIC_6 = 37;
/*     */   public static final int AXIS_GENERIC_7 = 38;
/*     */   public static final int AXIS_GENERIC_8 = 39;
/*     */   public static final int AXIS_GENERIC_9 = 40;
/*     */   public static final int AXIS_GENERIC_10 = 41;
/*     */   public static final int AXIS_GENERIC_11 = 42;
/*     */   public static final int AXIS_GENERIC_12 = 43;
/*     */   public static final int AXIS_GENERIC_13 = 44;
/*     */   public static final int AXIS_GENERIC_14 = 45;
/*     */   public static final int AXIS_GENERIC_15 = 46;
/*     */   public static final int AXIS_GENERIC_16 = 47;
/*     */ 
/*     */   public static int getActionMasked(MotionEvent paramMotionEvent)
/*     */   {
/* 426 */     return paramMotionEvent.getAction() & 0xFF;
/*     */   }
/*     */ 
/*     */   public static int getActionIndex(MotionEvent paramMotionEvent)
/*     */   {
/* 434 */     return (paramMotionEvent.getAction() & 0xFF00) >> 8;
/*     */   }
/*     */ 
/*     */   public static int findPointerIndex(MotionEvent paramMotionEvent, int paramInt)
/*     */   {
/* 444 */     return IMPL.findPointerIndex(paramMotionEvent, paramInt);
/*     */   }
/*     */ 
/*     */   public static int getPointerId(MotionEvent paramMotionEvent, int paramInt)
/*     */   {
/* 453 */     return IMPL.getPointerId(paramMotionEvent, paramInt);
/*     */   }
/*     */ 
/*     */   public static float getX(MotionEvent paramMotionEvent, int paramInt)
/*     */   {
/* 462 */     return IMPL.getX(paramMotionEvent, paramInt);
/*     */   }
/*     */ 
/*     */   public static float getY(MotionEvent paramMotionEvent, int paramInt)
/*     */   {
/* 471 */     return IMPL.getY(paramMotionEvent, paramInt);
/*     */   }
/*     */ 
/*     */   public static int getPointerCount(MotionEvent paramMotionEvent)
/*     */   {
/* 479 */     return IMPL.getPointerCount(paramMotionEvent);
/*     */   }
/*     */ 
/*     */   public static int getSource(MotionEvent paramMotionEvent)
/*     */   {
/* 488 */     return IMPL.getSource(paramMotionEvent);
/*     */   }
/*     */ 
/*     */   public static float getAxisValue(MotionEvent paramMotionEvent, int paramInt)
/*     */   {
/* 501 */     return IMPL.getAxisValue(paramMotionEvent, paramInt);
/*     */   }
/*     */ 
/*     */   public static float getAxisValue(MotionEvent paramMotionEvent, int paramInt1, int paramInt2)
/*     */   {
/* 517 */     return IMPL.getAxisValue(paramMotionEvent, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 153 */     if (Build.VERSION.SDK_INT >= 12)
/* 154 */       IMPL = new HoneycombMr1MotionEventVersionImpl();
/* 155 */     else if (Build.VERSION.SDK_INT >= 9)
/* 156 */       IMPL = new GingerbreadMotionEventVersionImpl();
/* 157 */     else if (Build.VERSION.SDK_INT >= 5)
/* 158 */       IMPL = new EclairMotionEventVersionImpl();
/*     */     else
/* 160 */       IMPL = new BaseMotionEventVersionImpl();
/*     */   }
/*     */ 
/*     */   static class HoneycombMr1MotionEventVersionImpl extends MotionEventCompat.GingerbreadMotionEventVersionImpl
/*     */   {
/*     */     public float getAxisValue(MotionEvent paramMotionEvent, int paramInt)
/*     */     {
/* 139 */       return MotionEventCompatHoneycombMr1.getAxisValue(paramMotionEvent, paramInt);
/*     */     }
/*     */ 
/*     */     public float getAxisValue(MotionEvent paramMotionEvent, int paramInt1, int paramInt2)
/*     */     {
/* 144 */       return MotionEventCompatHoneycombMr1.getAxisValue(paramMotionEvent, paramInt1, paramInt2);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class GingerbreadMotionEventVersionImpl extends MotionEventCompat.EclairMotionEventVersionImpl
/*     */   {
/*     */     public int getSource(MotionEvent paramMotionEvent)
/*     */     {
/* 128 */       return MotionEventCompatGingerbread.getSource(paramMotionEvent);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class EclairMotionEventVersionImpl extends MotionEventCompat.BaseMotionEventVersionImpl
/*     */   {
/*     */     public int findPointerIndex(MotionEvent paramMotionEvent, int paramInt)
/*     */     {
/* 102 */       return MotionEventCompatEclair.findPointerIndex(paramMotionEvent, paramInt);
/*     */     }
/*     */ 
/*     */     public int getPointerId(MotionEvent paramMotionEvent, int paramInt) {
/* 106 */       return MotionEventCompatEclair.getPointerId(paramMotionEvent, paramInt);
/*     */     }
/*     */ 
/*     */     public float getX(MotionEvent paramMotionEvent, int paramInt) {
/* 110 */       return MotionEventCompatEclair.getX(paramMotionEvent, paramInt);
/*     */     }
/*     */ 
/*     */     public float getY(MotionEvent paramMotionEvent, int paramInt) {
/* 114 */       return MotionEventCompatEclair.getY(paramMotionEvent, paramInt);
/*     */     }
/*     */ 
/*     */     public int getPointerCount(MotionEvent paramMotionEvent) {
/* 118 */       return MotionEventCompatEclair.getPointerCount(paramMotionEvent);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class BaseMotionEventVersionImpl
/*     */     implements MotionEventCompat.MotionEventVersionImpl
/*     */   {
/*     */     public int findPointerIndex(MotionEvent paramMotionEvent, int paramInt)
/*     */     {
/*  47 */       if (paramInt == 0)
/*     */       {
/*  49 */         return 0;
/*     */       }
/*  51 */       return -1;
/*     */     }
/*     */ 
/*     */     public int getPointerId(MotionEvent paramMotionEvent, int paramInt) {
/*  55 */       if (paramInt == 0)
/*     */       {
/*  57 */         return 0;
/*     */       }
/*  59 */       throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
/*     */     }
/*     */ 
/*     */     public float getX(MotionEvent paramMotionEvent, int paramInt) {
/*  63 */       if (paramInt == 0) {
/*  64 */         return paramMotionEvent.getX();
/*     */       }
/*  66 */       throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
/*     */     }
/*     */ 
/*     */     public float getY(MotionEvent paramMotionEvent, int paramInt) {
/*  70 */       if (paramInt == 0) {
/*  71 */         return paramMotionEvent.getY();
/*     */       }
/*  73 */       throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
/*     */     }
/*     */ 
/*     */     public int getPointerCount(MotionEvent paramMotionEvent) {
/*  77 */       return 1;
/*     */     }
/*     */ 
/*     */     public int getSource(MotionEvent paramMotionEvent)
/*     */     {
/*  82 */       return 0;
/*     */     }
/*     */ 
/*     */     public float getAxisValue(MotionEvent paramMotionEvent, int paramInt)
/*     */     {
/*  87 */       return 0.0F;
/*     */     }
/*     */ 
/*     */     public float getAxisValue(MotionEvent paramMotionEvent, int paramInt1, int paramInt2)
/*     */     {
/*  92 */       return 0.0F;
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface MotionEventVersionImpl
/*     */   {
/*     */     public abstract int findPointerIndex(MotionEvent paramMotionEvent, int paramInt);
/*     */ 
/*     */     public abstract int getPointerId(MotionEvent paramMotionEvent, int paramInt);
/*     */ 
/*     */     public abstract float getX(MotionEvent paramMotionEvent, int paramInt);
/*     */ 
/*     */     public abstract float getY(MotionEvent paramMotionEvent, int paramInt);
/*     */ 
/*     */     public abstract int getPointerCount(MotionEvent paramMotionEvent);
/*     */ 
/*     */     public abstract int getSource(MotionEvent paramMotionEvent);
/*     */ 
/*     */     public abstract float getAxisValue(MotionEvent paramMotionEvent, int paramInt);
/*     */ 
/*     */     public abstract float getAxisValue(MotionEvent paramMotionEvent, int paramInt1, int paramInt2);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.MotionEventCompat
 * JD-Core Version:    0.6.2
 */