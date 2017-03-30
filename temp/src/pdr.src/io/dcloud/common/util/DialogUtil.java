/*     */ package io.dcloud.common.util;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.AlertDialog;
/*     */ import android.app.AlertDialog.Builder;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.DialogInterface.OnClickListener;
/*     */ import android.content.Intent;
/*     */ import android.net.Uri;
/*     */ import android.os.Build.VERSION;
/*     */ import io.dcloud.RInformation;
/*     */ import io.dcloud.common.DHInterface.ICallBack;
/*     */ import java.io.File;
/*     */ 
/*     */ public class DialogUtil
/*     */ {
/*     */   public static void showConfirm(Activity paramActivity, String paramString1, String paramString2, String[] paramArrayOfString, final ICallBack paramICallBack)
/*     */   {
/*  21 */     AlertDialog localAlertDialog = new AlertDialog.Builder(paramActivity).create();
/*     */ 
/*  23 */     localAlertDialog.setTitle(paramString1);
/*  24 */     localAlertDialog.setCanceledOnTouchOutside(false);
/*  25 */     localAlertDialog.setMessage(paramString2);
/*  26 */     DialogInterface.OnClickListener local1 = new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/*  29 */         if (paramAnonymousInt == -2) {
/*  30 */           this.val$ad.cancel();
/*  31 */           this.val$ad.dismiss();
/*  32 */         } else if ((paramAnonymousInt != -3) && 
/*  33 */           (paramAnonymousInt == -1)) {
/*  34 */           this.val$ad.dismiss();
/*     */         }
/*  36 */         paramICallBack.onCallBack(paramAnonymousInt, null);
/*     */       }
/*     */     };
/*  39 */     localAlertDialog.setButton(-1, paramArrayOfString[0], local1);
/*  40 */     localAlertDialog.setButton(-2, paramArrayOfString[1], local1);
/*     */ 
/*  42 */     localAlertDialog.show();
/*     */   }
/*     */ 
/*     */   public static void showInstallAPKDialog(Context paramContext, String paramString1, final String paramString2)
/*     */   {
/*  54 */     AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).setMessage(paramString1).setNegativeButton("确定", new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*     */       {
/*  59 */         NotificationUtil.cancelNotification(this.val$context, paramString2.hashCode());
/*  60 */         Intent localIntent = new Intent("android.intent.action.VIEW");
/*  61 */         localIntent.setDataAndType(Uri.fromFile(new File(paramString2)), "application/vnd.android.package-archive");
/*  62 */         this.val$context.startActivity(localIntent);
/*     */       }
/*     */     }).setNeutralButton("取消", null).create();
/*     */ 
/*  65 */     localAlertDialog.setCanceledOnTouchOutside(false);
/*  66 */     localAlertDialog.show();
/*     */   }
/*     */ 
/*     */   public static AlertDialog.Builder initDialogTheme(Activity paramActivity, boolean paramBoolean)
/*     */   {
/*  74 */     AlertDialog.Builder localBuilder = null;
/*  75 */     if ((paramBoolean) || ((Build.VERSION.SDK_INT == 23) && (BaseInfo.isForQihooHelper5_0(paramActivity))))
/*     */     {
/*  77 */       localBuilder = new AlertDialog.Builder(paramActivity);
/*  78 */     } else if (Build.VERSION.SDK_INT < 11)
/*  79 */       localBuilder = new AlertDialog.Builder(paramActivity);
/*  80 */     else if (Build.VERSION.SDK_INT < 20)
/*  81 */       localBuilder = new AlertDialog.Builder(paramActivity, RInformation.STREAMAPP_DELETE_THEME);
/*     */     else {
/*  83 */       localBuilder = new AlertDialog.Builder(paramActivity);
/*     */     }
/*  85 */     return localBuilder;
/*     */   }
/*     */ 
/*     */   public static AlertDialog.Builder initDialogTheme(Activity paramActivity)
/*     */   {
/*     */     AlertDialog.Builder localBuilder;
/*  95 */     if (Build.VERSION.SDK_INT < 11)
/*  96 */       localBuilder = new AlertDialog.Builder(paramActivity);
/*     */     else {
/*  98 */       localBuilder = new AlertDialog.Builder(paramActivity, 16974130);
/*     */     }
/* 100 */     return localBuilder;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.DialogUtil
 * JD-Core Version:    0.6.2
 */