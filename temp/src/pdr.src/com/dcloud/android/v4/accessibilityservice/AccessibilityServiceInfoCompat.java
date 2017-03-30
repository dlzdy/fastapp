/*     */ package com.dcloud.android.v4.accessibilityservice;
/*     */ 
/*     */ import android.accessibilityservice.AccessibilityServiceInfo;
/*     */ import android.content.pm.ResolveInfo;
/*     */ import android.os.Build.VERSION;
/*     */ 
/*     */ public class AccessibilityServiceInfoCompat
/*     */ {
/*     */   private static final AccessibilityServiceInfoVersionImpl IMPL;
/*     */   public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
/*     */   public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
/*     */   public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
/*     */   public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
/*     */   public static final int FEEDBACK_BRAILLE = 32;
/*     */   public static final int FEEDBACK_ALL_MASK = -1;
/*     */   public static final int DEFAULT = 1;
/*     */   public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
/*     */   public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
/*     */   public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
/*     */   public static final int FLAG_REPORT_VIEW_IDS = 16;
/*     */   public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
/*     */ 
/*     */   public static String getId(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */   {
/* 293 */     return IMPL.getId(paramAccessibilityServiceInfo);
/*     */   }
/*     */ 
/*     */   public static ResolveInfo getResolveInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */   {
/* 305 */     return IMPL.getResolveInfo(paramAccessibilityServiceInfo);
/*     */   }
/*     */ 
/*     */   public static String getSettingsActivityName(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */   {
/* 318 */     return IMPL.getSettingsActivityName(paramAccessibilityServiceInfo);
/*     */   }
/*     */ 
/*     */   public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */   {
/* 331 */     return IMPL.getCanRetrieveWindowContent(paramAccessibilityServiceInfo);
/*     */   }
/*     */ 
/*     */   public static String getDescription(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */   {
/* 344 */     return IMPL.getDescription(paramAccessibilityServiceInfo);
/*     */   }
/*     */ 
/*     */   public static String feedbackTypeToString(int paramInt)
/*     */   {
/* 356 */     StringBuilder localStringBuilder = new StringBuilder();
/* 357 */     localStringBuilder.append("[");
/* 358 */     while (paramInt > 0) {
/* 359 */       int i = 1 << Integer.numberOfTrailingZeros(paramInt);
/* 360 */       paramInt &= (i ^ 0xFFFFFFFF);
/* 361 */       if (localStringBuilder.length() > 1) {
/* 362 */         localStringBuilder.append(", ");
/*     */       }
/* 364 */       switch (i) {
/*     */       case 4:
/* 366 */         localStringBuilder.append("FEEDBACK_AUDIBLE");
/* 367 */         break;
/*     */       case 2:
/* 369 */         localStringBuilder.append("FEEDBACK_HAPTIC");
/* 370 */         break;
/*     */       case 16:
/* 372 */         localStringBuilder.append("FEEDBACK_GENERIC");
/* 373 */         break;
/*     */       case 1:
/* 375 */         localStringBuilder.append("FEEDBACK_SPOKEN");
/* 376 */         break;
/*     */       case 8:
/* 378 */         localStringBuilder.append("FEEDBACK_VISUAL");
/*     */       }
/*     */     }
/*     */ 
/* 382 */     localStringBuilder.append("]");
/* 383 */     return localStringBuilder.toString();
/*     */   }
/*     */ 
/*     */   public static String flagToString(int paramInt)
/*     */   {
/* 395 */     switch (paramInt) {
/*     */     case 1:
/* 397 */       return "DEFAULT";
/*     */     case 2:
/* 399 */       return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
/*     */     case 4:
/* 401 */       return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
/*     */     case 8:
/* 403 */       return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
/*     */     case 16:
/* 405 */       return "FLAG_REPORT_VIEW_IDS";
/*     */     case 32:
/* 407 */       return "FLAG_REQUEST_FILTER_KEY_EVENTS";
/*     */     }
/* 409 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getCapabilities(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */   {
/* 426 */     return IMPL.getCapabilities(paramAccessibilityServiceInfo);
/*     */   }
/*     */ 
/*     */   public static String capabilityToString(int paramInt)
/*     */   {
/* 438 */     switch (paramInt) {
/*     */     case 1:
/* 440 */       return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
/*     */     case 2:
/* 442 */       return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
/*     */     case 4:
/* 444 */       return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
/*     */     case 8:
/* 446 */       return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
/*     */     case 3:
/*     */     case 5:
/*     */     case 6:
/* 448 */     case 7: } return "UNKNOWN";
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 111 */     if (Build.VERSION.SDK_INT >= 18)
/* 112 */       IMPL = new AccessibilityServiceInfoJellyBeanMr2();
/* 113 */     else if (Build.VERSION.SDK_INT >= 14)
/* 114 */       IMPL = new AccessibilityServiceInfoIcsImpl();
/*     */     else
/* 116 */       IMPL = new AccessibilityServiceInfoStubImpl();
/*     */   }
/*     */ 
/*     */   static class AccessibilityServiceInfoJellyBeanMr2 extends AccessibilityServiceInfoCompat.AccessibilityServiceInfoIcsImpl
/*     */   {
/*     */     public int getCapabilities(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */     {
/* 106 */       return AccessibilityServiceInfoCompatJellyBeanMr2.getCapabilities(paramAccessibilityServiceInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */   static class AccessibilityServiceInfoIcsImpl extends AccessibilityServiceInfoCompat.AccessibilityServiceInfoStubImpl
/*     */   {
/*     */     public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */     {
/*  71 */       return AccessibilityServiceInfoCompatIcs.getCanRetrieveWindowContent(paramAccessibilityServiceInfo);
/*     */     }
/*     */ 
/*     */     public String getDescription(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */     {
/*  76 */       return AccessibilityServiceInfoCompatIcs.getDescription(paramAccessibilityServiceInfo);
/*     */     }
/*     */ 
/*     */     public String getId(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */     {
/*  81 */       return AccessibilityServiceInfoCompatIcs.getId(paramAccessibilityServiceInfo);
/*     */     }
/*     */ 
/*     */     public ResolveInfo getResolveInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */     {
/*  86 */       return AccessibilityServiceInfoCompatIcs.getResolveInfo(paramAccessibilityServiceInfo);
/*     */     }
/*     */ 
/*     */     public String getSettingsActivityName(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */     {
/*  91 */       return AccessibilityServiceInfoCompatIcs.getSettingsActivityName(paramAccessibilityServiceInfo);
/*     */     }
/*     */ 
/*     */     public int getCapabilities(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */     {
/*  96 */       if (getCanRetrieveWindowContent(paramAccessibilityServiceInfo)) {
/*  97 */         return 1;
/*     */       }
/*  99 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   static class AccessibilityServiceInfoStubImpl
/*     */     implements AccessibilityServiceInfoCompat.AccessibilityServiceInfoVersionImpl
/*     */   {
/*     */     public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*     */     {
/*  43 */       return false;
/*     */     }
/*     */ 
/*     */     public String getDescription(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
/*  47 */       return null;
/*     */     }
/*     */ 
/*     */     public String getId(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
/*  51 */       return null;
/*     */     }
/*     */ 
/*     */     public ResolveInfo getResolveInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
/*  55 */       return null;
/*     */     }
/*     */ 
/*     */     public String getSettingsActivityName(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
/*  59 */       return null;
/*     */     }
/*     */ 
/*     */     public int getCapabilities(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
/*  63 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   static abstract interface AccessibilityServiceInfoVersionImpl
/*     */   {
/*     */     public abstract String getId(AccessibilityServiceInfo paramAccessibilityServiceInfo);
/*     */ 
/*     */     public abstract ResolveInfo getResolveInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo);
/*     */ 
/*     */     public abstract boolean getCanRetrieveWindowContent(AccessibilityServiceInfo paramAccessibilityServiceInfo);
/*     */ 
/*     */     public abstract String getDescription(AccessibilityServiceInfo paramAccessibilityServiceInfo);
/*     */ 
/*     */     public abstract String getSettingsActivityName(AccessibilityServiceInfo paramAccessibilityServiceInfo);
/*     */ 
/*     */     public abstract int getCapabilities(AccessibilityServiceInfo paramAccessibilityServiceInfo);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.accessibilityservice.AccessibilityServiceInfoCompat
 * JD-Core Version:    0.6.2
 */