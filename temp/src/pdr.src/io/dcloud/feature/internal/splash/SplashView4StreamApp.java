/*    */ package io.dcloud.feature.internal.splash;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.graphics.Bitmap;
/*    */ import android.text.TextUtils;
/*    */ import android.util.AttributeSet;
/*    */ import android.view.MotionEvent;
/*    */ import android.view.animation.AlphaAnimation;
/*    */ import android.widget.ImageView;
/*    */ import android.widget.RelativeLayout;
/*    */ import android.widget.TextView;
/*    */ 
/*    */ public class SplashView4StreamApp extends RelativeLayout
/*    */   implements c
/*    */ {
/*    */   private Bitmap a;
/*    */   private ImageView b;
/*    */   private TextView c;
/*    */ 
/*    */   public SplashView4StreamApp(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
/*    */   {
/* 31 */     super(paramContext, paramAttributeSet, paramInt);
/*    */   }
/*    */ 
/*    */   public SplashView4StreamApp(Context paramContext, AttributeSet paramAttributeSet) {
/* 35 */     super(paramContext, paramAttributeSet);
/*    */   }
/*    */ 
/*    */   public void a(Bitmap paramBitmap)
/*    */   {
/* 74 */     if (paramBitmap != null) {
/* 75 */       this.a = paramBitmap;
/*    */ 
/* 77 */       this.b.setImageBitmap(this.a);
/* 78 */       AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.3F, 1.0F);
/* 79 */       localAlphaAnimation.setDuration(800L);
/* 80 */       this.b.startAnimation(localAlphaAnimation);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void a(String paramString) {
/* 85 */     if ((this.c != null) && (TextUtils.isEmpty(this.c.getText())))
/* 86 */       this.c.setText(paramString);
/*    */   }
/*    */ 
/*    */   public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
/*    */   {
/* 91 */     return true;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.feature.internal.splash.SplashView4StreamApp
 * JD-Core Version:    0.6.2
 */