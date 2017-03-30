/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.R.drawable;
/*    */ import android.annotation.SuppressLint;
/*    */ import android.app.Notification;
/*    */ import android.app.NotificationManager;
/*    */ import android.app.PendingIntent;
/*    */ import android.content.Context;
/*    */ import android.content.res.Resources;
/*    */ import android.graphics.Bitmap;
/*    */ import android.widget.RemoteViews;
/*    */ import io.dcloud.RInformation;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class NotificationUtil
/*    */ {
/*    */   @SuppressLint({"SimpleDateFormat"})
/*    */   public static void createCustomNotification(Context paramContext, String paramString1, Bitmap paramBitmap, String paramString2, String paramString3, int paramInt, PendingIntent paramPendingIntent)
/*    */   {
/* 22 */     paramContext = paramContext.getApplicationContext();
/* 23 */     if (BaseInfo.isForQihooHelper(paramContext))
/*    */     {
/* 35 */       showNotification(paramContext, paramString2, paramString3, paramPendingIntent, paramBitmap, paramInt);
/*    */     } else {
/* 37 */       int i = RInformation.DRAWABLE_ICON;
/* 38 */       long l = System.currentTimeMillis();
/* 39 */       Notification localNotification = new Notification(i, paramString1, l);
/* 40 */       RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), RInformation.LAYOUT_CUSTION_NOTIFICATION_DCLOUD);
/*    */ 
/* 42 */       localRemoteViews.setImageViewBitmap(RInformation.ID_IMAGE_NOTIFICATION_DCLOUD, paramBitmap);
/*    */ 
/* 44 */       localRemoteViews.setTextViewText(RInformation.ID_TITLE_NOTIFICATION_DCLOUD, paramString2);
/*    */ 
/* 46 */       localRemoteViews.setTextViewText(RInformation.ID_TEXT_NOTIFICATION, paramString3);
/*    */ 
/* 48 */       SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 49 */       String str1 = localSimpleDateFormat.format(new Date());
/* 50 */       localRemoteViews.setTextViewText(RInformation.ID_TIME_NOTIFICATION_DCLOUD, str1.substring(str1.length() - 8, str1.length() - 3));
/*    */ 
/* 53 */       localNotification.contentView = localRemoteViews;
/* 54 */       localNotification.contentIntent = paramPendingIntent;
/* 55 */       localNotification.flags |= 16;
/* 56 */       localNotification.defaults = 1;
/* 57 */       String str2 = "notification";
/* 58 */       NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService(str2);
/*    */ 
/* 60 */       localNotificationManager.notify(paramInt, localNotification);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void showNotification(Context paramContext, String paramString1, String paramString2, PendingIntent paramPendingIntent, Bitmap paramBitmap, int paramInt) {
/* 65 */     NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
/*    */ 
/* 67 */     Notification localNotification = new Notification();
/*    */     try {
/* 69 */       Context localContext = paramContext.createPackageContext(paramContext.getPackageName(), 2);
/* 70 */       int i = localContext.getResources().getIdentifier("icon", R.drawable.class.getSimpleName(), paramContext.getPackageName());
/* 71 */       localNotification.icon = i;
/*    */     }
/*    */     catch (Exception localException) {
/*    */     }
/* 75 */     localNotification.largeIcon = paramBitmap;
/*    */ 
/* 77 */     localNotification.defaults = 1;
/* 78 */     localNotification.flags = 16;
/*    */ 
/* 80 */     localNotification.setLatestEventInfo(paramContext, paramString1, paramString2, paramPendingIntent);
/* 81 */     localNotificationManager.notify(paramInt, localNotification);
/*    */   }
/*    */ 
/*    */   public static void cancelNotification(Context paramContext, int paramInt) {
/* 85 */     NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
/* 86 */     localNotificationManager.cancel(paramInt);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.NotificationUtil
 * JD-Core Version:    0.6.2
 */