/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.app.AlertDialog;
/*    */ import android.app.AlertDialog.Builder;
/*    */ import android.app.Dialog;
/*    */ import android.content.DialogInterface;
/*    */ import android.content.DialogInterface.OnClickListener;
/*    */ import android.content.DialogInterface.OnDismissListener;
/*    */ import android.content.Intent;
/*    */ import android.net.Uri;
/*    */ import android.os.Build.VERSION;
/*    */ import io.dcloud.RInformation;
/*    */ import io.dcloud.common.DHInterface.IApp;
/*    */ import io.dcloud.common.DHInterface.IWebview;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class ErrorDialogUtil
/*    */ {
/* 21 */   private static ArrayList<String> list = new ArrayList();
/*    */ 
/*    */   public static Dialog getLossDialog(final IWebview paramIWebview, String paramString1, String paramString2, String paramString3)
/*    */   {
/* 33 */     if ((paramIWebview.obtainApp().isStreamApp()) || (list.contains(paramString3))) {
/* 34 */       return null;
/*    */     }
/* 36 */     list.add(paramString3);
/*    */     AlertDialog.Builder localBuilder;
/* 38 */     if (Build.VERSION.SDK_INT < 11)
/* 39 */       localBuilder = new AlertDialog.Builder(paramIWebview.getActivity());
/*    */     else {
/* 41 */       localBuilder = new AlertDialog.Builder(paramIWebview.getActivity(), RInformation.FEATURE_LOSS_STYLE);
/*    */     }
/* 43 */     AlertDialog localAlertDialog = localBuilder.setTitle("HTML5+ Runtime").setIcon(17301659).setMessage(paramString1).setPositiveButton("确定", new DialogInterface.OnClickListener()
/*    */     {
/*    */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*    */       {
/* 49 */         Intent localIntent = new Intent();
/* 50 */         localIntent.setAction("android.intent.action.VIEW");
/* 51 */         Uri localUri = Uri.parse(this.val$link);
/* 52 */         localIntent.setData(localUri);
/* 53 */         paramIWebview.getActivity().startActivity(localIntent);
/*    */       }
/*    */     }).setNegativeButton("取消", null).create();
/*    */ 
/* 56 */     localAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
/*    */     {
/*    */       public void onDismiss(DialogInterface paramAnonymousDialogInterface)
/*    */       {
/* 61 */         ErrorDialogUtil.list.remove(this.val$key);
/*    */       }
/*    */     });
/* 64 */     return localAlertDialog;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ErrorDialogUtil
 * JD-Core Version:    0.6.2
 */