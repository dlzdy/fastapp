/*     */ package io.dcloud.common.DHInterface;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.drawable.BitmapDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.os.Build.VERSION;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.ProgressBar;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.RelativeLayout.LayoutParams;
/*     */ import io.dcloud.RInformation;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ 
/*     */ public class SplashView extends RelativeLayout
/*     */ {
/*  27 */   protected boolean mShowSplashScreen = false;
/*     */ 
/*  29 */   protected boolean mShowSplashWaiting = false;
/*  30 */   final String TAG = "SplashView";
/*     */   int screenWidth;
/*     */   int screenHeight;
/*  32 */   boolean showBitmap = false;
/*     */ 
/*  68 */   public static int STYLE_DEFAULT = 0;
/*  69 */   public static int STYLE_BLACK = 1;
/*  70 */   public static int STYLE_WHITE = 2;
/*     */ 
/*     */   public SplashView(Context paramContext, Bitmap paramBitmap)
/*     */   {
/*  37 */     super(paramContext);
/*  38 */     setBackgroundColor(-1);
/*  39 */     DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
/*  40 */     this.screenWidth = localDisplayMetrics.widthPixels;
/*  41 */     this.screenHeight = localDisplayMetrics.heightPixels;
/*  42 */     if (Build.VERSION.SDK_INT < 16)
/*  43 */       setBackgroundDrawable(new BitmapDrawable(paramBitmap));
/*     */     else
/*  45 */       setBackground(new BitmapDrawable(paramBitmap));
/*     */   }
/*     */ 
/*     */   public void showWaiting()
/*     */   {
/*  66 */     showWaiting(STYLE_DEFAULT);
/*     */   }
/*     */ 
/*     */   protected void onShowProgressBar(ViewGroup paramViewGroup, int paramInt)
/*     */   {
/*  72 */     Logger.d("Main_Path", "showWaiting style=" + paramInt);
/*  73 */     ProgressBar localProgressBar = new ProgressBar(getContext());
/*  74 */     int i = PdrUtil.parseInt("7%", this.screenWidth, -1);
/*  75 */     int j = i;
/*  76 */     setGravity(17);
/*  77 */     Drawable localDrawable = null;
/*  78 */     if (paramInt == STYLE_BLACK) {
/*  79 */       localDrawable = getContext().getResources().getDrawable(RInformation.DRAWBLE_PROGRESSBAR_BLACK_DCLOUD);
/*     */     }
/*  81 */     else if (paramInt == STYLE_WHITE) {
/*  82 */       localDrawable = getContext().getResources().getDrawable(RInformation.DRAWBLE_PROGRESSBAR_WHITE_DCLOUD);
/*     */     }
/*     */ 
/*  85 */     if (localDrawable != null) {
/*  86 */       localProgressBar.setIndeterminateDrawable(localDrawable);
/*     */     }
/*  88 */     paramViewGroup.addView(localProgressBar, new RelativeLayout.LayoutParams(i, j));
/*  89 */     Logger.d("Main_Path", "onShowProgressBar");
/*     */   }
/*     */ 
/*     */   public void showWaiting(final int paramInt) {
/*  93 */     post(new Runnable()
/*     */     {
/*     */       public void run() {
/*  96 */         SplashView.this.onShowProgressBar(SplashView.this, paramInt);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   protected void dispatchDraw(Canvas paramCanvas)
/*     */   {
/* 118 */     if (DeviceInfo.sStatusBarHeight <= 0) {
/* 119 */       Logger.d("SplashView", "paint() before DeviceInfo.updateScreenInfo()");
/* 120 */       DeviceInfo.updateStatusBarHeight((Activity)getContext());
/*     */     }
/* 122 */     Logger.d("SplashView", "dispatchDraw.....");
/*     */ 
/* 127 */     super.dispatchDraw(paramCanvas);
/*     */   }
/*     */ 
/*     */   public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
/*     */   {
/* 132 */     return true;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.SplashView
 * JD-Core Version:    0.6.2
 */