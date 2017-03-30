/*    */ package io.dcloud.common.DHInterface;
/*    */ 
/*    */ import android.app.Dialog;
/*    */ import android.content.Context;
/*    */ import android.content.DialogInterface;
/*    */ import android.content.DialogInterface.OnDismissListener;
/*    */ import android.content.res.Resources;
/*    */ import android.graphics.Bitmap;
/*    */ import android.util.DisplayMetrics;
/*    */ import android.view.View.OnClickListener;
/*    */ import android.view.Window;
/*    */ import android.view.WindowManager.LayoutParams;
/*    */ import android.widget.ImageView;
/*    */ import android.widget.TextView;
/*    */ import io.dcloud.RInformation;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ 
/*    */ public class ViewHelper
/*    */ {
/*    */   public static Dialog createDefaultDialog(Context paramContext, int paramInt1, int paramInt2)
/*    */   {
/* 31 */     Dialog localDialog = new Dialog(paramContext, RInformation.STYLE_DIALOG_DCLOUD_DEFALUT_DIALOG);
/*    */ 
/* 33 */     localDialog.requestWindowFeature(1);
/* 34 */     localDialog.setContentView(RInformation.LAYOUT_DIALOG_LAYOUT_DCLOUD_DIALOG);
/*    */ 
/* 36 */     localDialog.setCanceledOnTouchOutside(false);
/*    */ 
/* 40 */     Window localWindow = localDialog.getWindow();
/*    */ 
/* 42 */     localWindow.setWindowAnimations(RInformation.STYLE_DIALOG_STYLE_DCLOUD_ANIM_DIALOG_WINDOW_IN_OUT);
/*    */ 
/* 44 */     WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
/*    */ 
/* 48 */     localLayoutParams.x = paramInt1;
/* 49 */     localLayoutParams.y = paramInt2;
/* 50 */     localLayoutParams.width = paramContext.getResources().getDisplayMetrics().widthPixels;
/*    */ 
/* 53 */     localLayoutParams.gravity = 48;
/* 54 */     localWindow.setAttributes(localLayoutParams);
/* 55 */     return localDialog;
/*    */   }
/*    */ 
/*    */   public static void showDefaultDialog(Dialog paramDialog, View.OnClickListener paramOnClickListener, final Bitmap paramBitmap, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
/*    */   {
/* 60 */     TextView localTextView1 = (TextView)paramDialog.findViewById(RInformation.ID_DCLOUD_DIALOG_TITLE);
/* 61 */     localTextView1.setText(paramString1);
/* 62 */     TextView localTextView2 = (TextView)paramDialog.findViewById(RInformation.ID_DCLOUD_DIALOG_MSG);
/* 63 */     localTextView2.setText(paramString2);
/* 64 */     ImageView localImageView = (ImageView)paramDialog.findViewById(RInformation.ID_DCLOUD_DIALOG_ICON);
/* 65 */     localImageView.setImageBitmap(paramBitmap);
/* 66 */     TextView localTextView3 = (TextView)paramDialog.findViewById(RInformation.ID_DCLOUD_DIALOG_BTN1);
/* 67 */     localTextView3.setOnClickListener(paramOnClickListener);
/* 68 */     localTextView3.setText(paramString3);
/* 69 */     TextView localTextView4 = (TextView)paramDialog.findViewById(RInformation.ID_DCLOUD_DIALOG_BTN2);
/* 70 */     localTextView4.setText(paramString4);
/* 71 */     localTextView4.setOnClickListener(paramOnClickListener);
/* 72 */     paramDialog.show();
/* 73 */     if (paramInt > 0) {
/* 74 */       Timer localTimer = new Timer();
/* 75 */       localTimer.schedule(new TimerTask()
/*    */       {
/*    */         public void run()
/*    */         {
/* 79 */           if (this.val$d.isShowing())
/* 80 */             this.val$d.dismiss();
/*    */         }
/*    */       }
/*    */       , paramInt);
/*    */ 
/* 84 */       paramDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
/*    */       {
/*    */         public void onDismiss(DialogInterface paramAnonymousDialogInterface)
/*    */         {
/* 88 */           this.val$timer.cancel();
/* 89 */           if ((paramBitmap != null) && (!paramBitmap.isRecycled()))
/* 90 */             paramBitmap.recycle();
/*    */         }
/*    */       });
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.ViewHelper
 * JD-Core Version:    0.6.2
 */