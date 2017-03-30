/*     */ package com.dcloud.android.v4.view.accessibility;
/*     */ 
/*     */ import android.graphics.Rect;
/*     */ import android.os.Build.VERSION;
/*     */ 
/*     */ public class AccessibilityWindowInfoCompat
/*     */ {
/*     */   private static final AccessibilityWindowInfoImpl IMPL;
/*     */   private Object mInfo;
/*     */   private static final int UNDEFINED = -1;
/*     */   public static final int TYPE_APPLICATION = 1;
/*     */   public static final int TYPE_INPUT_METHOD = 2;
/*     */   public static final int TYPE_SYSTEM = 3;
/*     */   public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
/*     */ 
/*     */   static AccessibilityWindowInfoCompat wrapNonNullInstance(Object paramObject)
/*     */   {
/* 239 */     if (paramObject != null) {
/* 240 */       return new AccessibilityWindowInfoCompat(paramObject);
/*     */     }
/* 242 */     return null;
/*     */   }
/*     */ 
/*     */   private AccessibilityWindowInfoCompat(Object paramObject) {
/* 246 */     this.mInfo = paramObject;
/*     */   }
/*     */ 
/*     */   public int getType()
/*     */   {
/* 260 */     return IMPL.getType(this.mInfo);
/*     */   }
/*     */ 
/*     */   public int getLayer()
/*     */   {
/* 270 */     return IMPL.getLayer(this.mInfo);
/*     */   }
/*     */ 
/*     */   public AccessibilityNodeInfoCompat getRoot()
/*     */   {
/* 279 */     return AccessibilityNodeInfoCompat.wrapNonNullInstance(IMPL.getRoot(this.mInfo));
/*     */   }
/*     */ 
/*     */   public AccessibilityWindowInfoCompat getParent()
/*     */   {
/* 288 */     return wrapNonNullInstance(IMPL.getParent(this.mInfo));
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/* 297 */     return IMPL.getId(this.mInfo);
/*     */   }
/*     */ 
/*     */   public void getBoundsInScreen(Rect paramRect)
/*     */   {
/* 306 */     IMPL.getBoundsInScreen(this.mInfo, paramRect);
/*     */   }
/*     */ 
/*     */   public boolean isActive()
/*     */   {
/* 317 */     return IMPL.isActive(this.mInfo);
/*     */   }
/*     */ 
/*     */   public boolean isFocused()
/*     */   {
/* 326 */     return IMPL.isFocused(this.mInfo);
/*     */   }
/*     */ 
/*     */   public boolean isAccessibilityFocused()
/*     */   {
/* 335 */     return IMPL.isAccessibilityFocused(this.mInfo);
/*     */   }
/*     */ 
/*     */   public int getChildCount()
/*     */   {
/* 344 */     return IMPL.getChildCount(this.mInfo);
/*     */   }
/*     */ 
/*     */   public AccessibilityWindowInfoCompat getChild(int paramInt)
/*     */   {
/* 354 */     return wrapNonNullInstance(IMPL.getChild(this.mInfo, paramInt));
/*     */   }
/*     */ 
/*     */   public static AccessibilityWindowInfoCompat obtain()
/*     */   {
/* 364 */     return wrapNonNullInstance(IMPL.obtain());
/*     */   }
/*     */ 
/*     */   public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat paramAccessibilityWindowInfoCompat)
/*     */   {
/* 376 */     return wrapNonNullInstance(IMPL.obtain(paramAccessibilityWindowInfoCompat.mInfo));
/*     */   }
/*     */ 
/*     */   public void recycle()
/*     */   {
/* 388 */     IMPL.recycle(this.mInfo);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 393 */     return this.mInfo == null ? 0 : this.mInfo.hashCode();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 398 */     if (this == paramObject) {
/* 399 */       return true;
/*     */     }
/* 401 */     if (paramObject == null) {
/* 402 */       return false;
/*     */     }
/* 404 */     if (getClass() != paramObject.getClass()) {
/* 405 */       return false;
/*     */     }
/* 407 */     AccessibilityWindowInfoCompat localAccessibilityWindowInfoCompat = (AccessibilityWindowInfoCompat)paramObject;
/* 408 */     if (this.mInfo == null) {
/* 409 */       if (localAccessibilityWindowInfoCompat.mInfo != null)
/* 410 */         return false;
/*     */     }
/* 412 */     else if (!this.mInfo.equals(localAccessibilityWindowInfoCompat.mInfo)) {
/* 413 */       return false;
/*     */     }
/* 415 */     return true;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 420 */     StringBuilder localStringBuilder = new StringBuilder();
/* 421 */     Rect localRect = new Rect();
/* 422 */     getBoundsInScreen(localRect);
/* 423 */     localStringBuilder.append("AccessibilityWindowInfo[");
/* 424 */     localStringBuilder.append("id=").append(getId());
/* 425 */     localStringBuilder.append(", type=").append(typeToString(getType()));
/* 426 */     localStringBuilder.append(", layer=").append(getLayer());
/* 427 */     localStringBuilder.append(", bounds=").append(localRect);
/* 428 */     localStringBuilder.append(", focused=").append(isFocused());
/* 429 */     localStringBuilder.append(", active=").append(isActive());
/* 430 */     localStringBuilder.append(", hasParent=").append(getParent() != null);
/* 431 */     localStringBuilder.append(", hasChildren=").append(getChildCount() > 0);
/* 432 */     localStringBuilder.append(']');
/* 433 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   private static String typeToString(int paramInt) {
/* 437 */     switch (paramInt) {
/*     */     case 1:
/* 439 */       return "TYPE_APPLICATION";
/*     */     case 2:
/* 442 */       return "TYPE_INPUT_METHOD";
/*     */     case 3:
/* 445 */       return "TYPE_SYSTEM";
/*     */     case 4:
/* 448 */       return "TYPE_ACCESSIBILITY_OVERLAY";
/*     */     }
/*     */ 
/* 451 */     return "<UNKNOWN>";
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 189 */     if (Build.VERSION.SDK_INT >= 21)
/* 190 */       IMPL = new AccessibilityWindowInfoApi21Impl(null);
/*     */     else
/* 192 */       IMPL = new AccessibilityWindowInfoStubImpl(null);
/*     */   }
/*     */ 
/*     */   private static class AccessibilityWindowInfoApi21Impl extends AccessibilityWindowInfoCompat.AccessibilityWindowInfoStubImpl
/*     */   {
/*     */     private AccessibilityWindowInfoApi21Impl()
/*     */     {
/* 116 */       super();
/*     */     }
/*     */     public Object obtain() {
/* 119 */       return AccessibilityWindowInfoCompatApi21.obtain();
/*     */     }
/*     */ 
/*     */     public Object obtain(Object paramObject)
/*     */     {
/* 124 */       return AccessibilityWindowInfoCompatApi21.obtain(paramObject);
/*     */     }
/*     */ 
/*     */     public int getType(Object paramObject)
/*     */     {
/* 129 */       return AccessibilityWindowInfoCompatApi21.getType(paramObject);
/*     */     }
/*     */ 
/*     */     public int getLayer(Object paramObject)
/*     */     {
/* 134 */       return AccessibilityWindowInfoCompatApi21.getLayer(paramObject);
/*     */     }
/*     */ 
/*     */     public Object getRoot(Object paramObject)
/*     */     {
/* 139 */       return AccessibilityWindowInfoCompatApi21.getRoot(paramObject);
/*     */     }
/*     */ 
/*     */     public Object getParent(Object paramObject)
/*     */     {
/* 144 */       return AccessibilityWindowInfoCompatApi21.getParent(paramObject);
/*     */     }
/*     */ 
/*     */     public int getId(Object paramObject)
/*     */     {
/* 149 */       return AccessibilityWindowInfoCompatApi21.getId(paramObject);
/*     */     }
/*     */ 
/*     */     public void getBoundsInScreen(Object paramObject, Rect paramRect)
/*     */     {
/* 154 */       AccessibilityWindowInfoCompatApi21.getBoundsInScreen(paramObject, paramRect);
/*     */     }
/*     */ 
/*     */     public boolean isActive(Object paramObject)
/*     */     {
/* 159 */       return AccessibilityWindowInfoCompatApi21.isActive(paramObject);
/*     */     }
/*     */ 
/*     */     public boolean isFocused(Object paramObject)
/*     */     {
/* 164 */       return AccessibilityWindowInfoCompatApi21.isFocused(paramObject);
/*     */     }
/*     */ 
/*     */     public boolean isAccessibilityFocused(Object paramObject)
/*     */     {
/* 169 */       return AccessibilityWindowInfoCompatApi21.isAccessibilityFocused(paramObject);
/*     */     }
/*     */ 
/*     */     public int getChildCount(Object paramObject)
/*     */     {
/* 174 */       return AccessibilityWindowInfoCompatApi21.getChildCount(paramObject);
/*     */     }
/*     */ 
/*     */     public Object getChild(Object paramObject, int paramInt)
/*     */     {
/* 179 */       return AccessibilityWindowInfoCompatApi21.getChild(paramObject, paramInt);
/*     */     }
/*     */ 
/*     */     public void recycle(Object paramObject)
/*     */     {
/* 184 */       AccessibilityWindowInfoCompatApi21.recycle(paramObject);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class AccessibilityWindowInfoStubImpl
/*     */     implements AccessibilityWindowInfoCompat.AccessibilityWindowInfoImpl
/*     */   {
/*     */     public Object obtain()
/*     */     {
/*  49 */       return null;
/*     */     }
/*     */ 
/*     */     public Object obtain(Object paramObject)
/*     */     {
/*  54 */       return null;
/*     */     }
/*     */ 
/*     */     public int getType(Object paramObject)
/*     */     {
/*  59 */       return -1;
/*     */     }
/*     */ 
/*     */     public int getLayer(Object paramObject)
/*     */     {
/*  64 */       return -1;
/*     */     }
/*     */ 
/*     */     public Object getRoot(Object paramObject)
/*     */     {
/*  69 */       return null;
/*     */     }
/*     */ 
/*     */     public Object getParent(Object paramObject)
/*     */     {
/*  74 */       return null;
/*     */     }
/*     */ 
/*     */     public int getId(Object paramObject)
/*     */     {
/*  79 */       return -1;
/*     */     }
/*     */ 
/*     */     public void getBoundsInScreen(Object paramObject, Rect paramRect)
/*     */     {
/*     */     }
/*     */ 
/*     */     public boolean isActive(Object paramObject)
/*     */     {
/*  88 */       return true;
/*     */     }
/*     */ 
/*     */     public boolean isFocused(Object paramObject)
/*     */     {
/*  93 */       return true;
/*     */     }
/*     */ 
/*     */     public boolean isAccessibilityFocused(Object paramObject)
/*     */     {
/*  98 */       return true;
/*     */     }
/*     */ 
/*     */     public int getChildCount(Object paramObject)
/*     */     {
/* 103 */       return 0;
/*     */     }
/*     */ 
/*     */     public Object getChild(Object paramObject, int paramInt)
/*     */     {
/* 108 */       return null;
/*     */     }
/*     */ 
/*     */     public void recycle(Object paramObject)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private static abstract interface AccessibilityWindowInfoImpl
/*     */   {
/*     */     public abstract Object obtain();
/*     */ 
/*     */     public abstract Object obtain(Object paramObject);
/*     */ 
/*     */     public abstract int getType(Object paramObject);
/*     */ 
/*     */     public abstract int getLayer(Object paramObject);
/*     */ 
/*     */     public abstract Object getRoot(Object paramObject);
/*     */ 
/*     */     public abstract Object getParent(Object paramObject);
/*     */ 
/*     */     public abstract int getId(Object paramObject);
/*     */ 
/*     */     public abstract void getBoundsInScreen(Object paramObject, Rect paramRect);
/*     */ 
/*     */     public abstract boolean isActive(Object paramObject);
/*     */ 
/*     */     public abstract boolean isFocused(Object paramObject);
/*     */ 
/*     */     public abstract boolean isAccessibilityFocused(Object paramObject);
/*     */ 
/*     */     public abstract int getChildCount(Object paramObject);
/*     */ 
/*     */     public abstract Object getChild(Object paramObject, int paramInt);
/*     */ 
/*     */     public abstract void recycle(Object paramObject);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityWindowInfoCompat
 * JD-Core Version:    0.6.2
 */