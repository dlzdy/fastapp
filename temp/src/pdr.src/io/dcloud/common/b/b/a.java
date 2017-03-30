/*    */ package io.dcloud.common.b.b;
/*    */ 
/*    */ import android.graphics.Rect;
/*    */ import android.view.View;
/*    */ import android.view.ViewTreeObserver;
/*    */ import android.view.ViewTreeObserver.OnGlobalLayoutListener;
/*    */ import android.widget.FrameLayout;
/*    */ import android.widget.FrameLayout.LayoutParams;
/*    */ import io.dcloud.common.adapter.util.AndroidResources;
/*    */ import io.dcloud.common.adapter.util.DeviceInfo;
/*    */ 
/*    */ public class a
/*    */ {
/*    */   private static a a;
/*    */   private View b;
/*    */   private int c;
/*    */   private FrameLayout.LayoutParams d;
/* 35 */   private boolean e = false;
/*    */ 
/*    */   public static void a(FrameLayout paramFrameLayout)
/*    */   {
/* 27 */     if (a == null)
/* 28 */       a = new a(paramFrameLayout);
/*    */   }
/*    */ 
/*    */   private a(FrameLayout paramFrameLayout)
/*    */   {
/* 38 */     this.b = paramFrameLayout;
/* 39 */     this.b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
/*    */       public void onGlobalLayout() {
/* 41 */         a.a(a.this);
/*    */       }
/*    */     });
/* 44 */     this.d = ((FrameLayout.LayoutParams)this.b.getLayoutParams());
/* 45 */     this.e = AndroidResources.checkImmersedStatusBar(paramFrameLayout.getContext());
/*    */   }
/*    */ 
/*    */   private void b() {
/* 49 */     int i = c();
/* 50 */     if (i != this.c) {
/* 51 */       int j = this.b.getRootView().getHeight();
/* 52 */       int k = j - i;
/* 53 */       if (k > j / 4)
/*    */       {
/* 55 */         this.d.height = (j - k + (this.e ? DeviceInfo.sStatusBarHeight : 0));
/*    */       }
/*    */       else {
/* 58 */         this.d.height = j;
/*    */       }
/* 60 */       this.b.requestLayout();
/*    */ 
/* 62 */       this.c = i;
/*    */     }
/*    */   }
/*    */ 
/*    */   private int c() {
/* 67 */     Rect localRect = new Rect();
/* 68 */     this.b.getWindowVisibleDisplayFrame(localRect);
/* 69 */     return localRect.bottom - localRect.top;
/*    */   }
/*    */ 
/*    */   public static void a() {
/* 73 */     a = null;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.a
 * JD-Core Version:    0.6.2
 */