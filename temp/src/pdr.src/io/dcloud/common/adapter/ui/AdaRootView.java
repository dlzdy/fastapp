/*    */ package io.dcloud.common.adapter.ui;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.content.res.Configuration;
/*    */ import android.gesture.Gesture;
/*    */ import android.gesture.GestureOverlayView;
/*    */ import android.gesture.GestureOverlayView.OnGestureListener;
/*    */ import android.view.MotionEvent;
/*    */ import android.widget.FrameLayout;
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ import io.dcloud.common.adapter.util.ViewOptions;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class AdaRootView extends AdaContainerFrameItem
/*    */ {
/* 28 */   FrameLayout mMyRootView = null;
/*    */ 
/* 30 */   protected AdaRootView(Context paramContext, FrameLayout paramFrameLayout) { super(paramContext);
/*    */ 
/* 32 */     if (paramFrameLayout != null)
/* 33 */       this.mMyRootView = paramFrameLayout;
/*    */     else {
/* 35 */       this.mMyRootView = new MyRootView(paramContext, this);
/*    */     }
/* 37 */     setMainView(this.mMyRootView);
/*    */   }
/*    */ 
/*    */   public static class GestureListenerImpl
/*    */     implements GestureOverlayView.OnGestureListener
/*    */   {
/*    */     public void onGestureStarted(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent)
/*    */     {
/*    */     }
/*    */ 
/*    */     public void onGesture(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent)
/*    */     {
/*    */     }
/*    */ 
/*    */     public void onGestureEnded(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent)
/*    */     {
/* 76 */       Gesture localGesture = paramGestureOverlayView.getGesture();
/* 77 */       ArrayList localArrayList = localGesture.getStrokes();
/*    */     }
/*    */ 
/*    */     public void onGestureCancelled(GestureOverlayView paramGestureOverlayView, MotionEvent paramMotionEvent)
/*    */     {
/*    */     }
/*    */   }
/*    */ 
/*    */   class MyRootView extends FrameLayout
/*    */   {
/* 45 */     AdaRootView mProxy = null;
/*    */ 
/* 47 */     public MyRootView(Context paramAdaRootView, AdaRootView arg3) { super();
/*    */       Object localObject;
/* 48 */       this.mProxy = localObject; }
/*    */ 
/*    */     protected void onConfigurationChanged(Configuration paramConfiguration)
/*    */     {
/* 52 */       super.onConfigurationChanged(paramConfiguration);
/* 53 */       AdaRootView.this.mViewOptions.onScreenChanged();
/*    */     }
/*    */ 
/*    */     protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 57 */       super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
/* 58 */       AdaRootView.this.mViewOptions.onScreenChanged(paramInt1, paramInt2);
/* 59 */       Logger.d("Layout_Path", new Object[] { "AdaRootView onSizeChanged", Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4) });
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.AdaRootView
 * JD-Core Version:    0.6.2
 */