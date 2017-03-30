/*    */ package io.dcloud.common.DHInterface;
/*    */ 
/*    */ public abstract interface ISysEventListener
/*    */ {
/*    */   public abstract boolean onExecute(SysEventType paramSysEventType, Object paramObject);
/*    */ 
/*    */   public static enum SysEventType
/*    */   {
/* 21 */     AllSystemEvent, 
/* 22 */     onActivityResult, 
/* 23 */     onCreateOptionMenu, 
/* 24 */     onKeyDown, 
/* 25 */     onKeyUp, 
/* 26 */     onSaveInstanceState, 
/* 27 */     onKeyLongPress, 
/* 28 */     onStart, 
/* 29 */     onResume, 
/* 30 */     onWebAppStop, 
/* 31 */     onWebAppSaveState, 
/* 32 */     onWebAppTrimMemory, 
/* 33 */     onWebAppBackground, 
/* 34 */     onWebAppPause, 
/* 35 */     onWebAppForeground, 
/* 36 */     onStop, 
/* 37 */     onPause, 
/* 38 */     onDeviceNetChanged, 
/*    */ 
/* 40 */     onSimStateChanged, 
/* 41 */     onNewIntent, 
/* 42 */     onConfigurationChanged, 
/* 43 */     onKeyboardShow, 
/* 44 */     onKeyboardHide, 
/* 45 */     onRequestPermissionsResult;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.ISysEventListener
 * JD-Core Version:    0.6.2
 */