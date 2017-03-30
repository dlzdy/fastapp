/*    */ package io.dcloud.feature.internal.splash;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.graphics.Bitmap;
/*    */ import android.graphics.Typeface;
/*    */ import android.text.TextUtils;
/*    */ import android.view.MotionEvent;
/*    */ import android.widget.RelativeLayout;
/*    */ import android.widget.RelativeLayout.LayoutParams;
/*    */ import android.widget.TextView;
/*    */ 
/*    */ public class d extends RelativeLayout
/*    */   implements c
/*    */ {
/*    */   String a;
/* 18 */   b b = null;
/* 19 */   TextView c = null;
/*    */ 
/* 21 */   public d(Context paramContext, Bitmap paramBitmap, String paramString) { super(paramContext);
/* 22 */     this.a = paramString;
/* 23 */     setBackgroundColor(-1);
/* 24 */     b(paramBitmap); }
/*    */ 
/*    */   private void b(Bitmap paramBitmap) {
/* 27 */     this.b = new b(getContext());
/* 28 */     int i = (int)this.b.a(65.0F);
/* 29 */     int j = i;
/*    */ 
/* 31 */     this.b.a(paramBitmap, i, j, (int)this.b.a(1.0F), 12962246, -14570443);
/* 32 */     RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(i, j);
/* 33 */     localLayoutParams.addRule(14);
/* 34 */     localLayoutParams.topMargin = ((int)this.b.a(80.0F));
/* 35 */     this.b.setId(16908313);
/* 36 */     addView(this.b, localLayoutParams);
/*    */ 
/* 39 */     localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
/* 40 */     localLayoutParams.addRule(3, this.b.getId());
/* 41 */     localLayoutParams.addRule(13);
/* 42 */     localLayoutParams.topMargin = ((int)this.b.a(6.0F));
/* 43 */     this.c = new TextView(getContext());
/* 44 */     this.c.setSingleLine();
/* 45 */     this.c.setTextSize(this.b.a(6.0F));
/* 46 */     this.c.setTextColor(-10855846);
/* 47 */     a(this.a);
/* 48 */     this.c.setTypeface(Typeface.create("宋体", 0));
/* 49 */     addView(this.c, localLayoutParams);
/*    */   }
/*    */ 
/*    */   public void a(String paramString)
/*    */   {
/* 54 */     if ((this.c != null) && (TextUtils.isEmpty(this.c.getText())))
/* 55 */       this.c.setText(paramString);
/*    */   }
/*    */ 
/*    */   public void a(Bitmap paramBitmap)
/*    */   {
/* 61 */     this.b.a(paramBitmap);
/*    */   }
/*    */ 
/*    */   public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
/* 65 */     return true;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.feature.internal.splash.d
 * JD-Core Version:    0.6.2
 */