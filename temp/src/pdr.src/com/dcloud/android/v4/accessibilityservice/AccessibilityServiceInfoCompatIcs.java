/*    */ package com.dcloud.android.v4.accessibilityservice;
/*    */ 
/*    */ import android.accessibilityservice.AccessibilityServiceInfo;
/*    */ import android.content.pm.ResolveInfo;
/*    */ 
/*    */ class AccessibilityServiceInfoCompatIcs
/*    */ {
/*    */   public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo paramAccessibilityServiceInfo)
/*    */   {
/* 29 */     return paramAccessibilityServiceInfo.getCanRetrieveWindowContent();
/*    */   }
/*    */ 
/*    */   public static String getDescription(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
/* 33 */     return paramAccessibilityServiceInfo.getDescription();
/*    */   }
/*    */ 
/*    */   public static String getId(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
/* 37 */     return paramAccessibilityServiceInfo.getId();
/*    */   }
/*    */ 
/*    */   public static ResolveInfo getResolveInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
/* 41 */     return paramAccessibilityServiceInfo.getResolveInfo();
/*    */   }
/*    */ 
/*    */   public static String getSettingsActivityName(AccessibilityServiceInfo paramAccessibilityServiceInfo) {
/* 45 */     return paramAccessibilityServiceInfo.getSettingsActivityName();
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.accessibilityservice.AccessibilityServiceInfoCompatIcs
 * JD-Core Version:    0.6.2
 */