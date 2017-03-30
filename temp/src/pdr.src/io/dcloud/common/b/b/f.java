/*    */ package io.dcloud.common.b.b;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.view.animation.Animation;
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.IApp;
/*    */ import io.dcloud.common.DHInterface.IFrameView;
/*    */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*    */ import io.dcloud.common.adapter.ui.AdaWebview;
/*    */ import io.dcloud.common.adapter.util.Logger;
/*    */ import io.dcloud.common.util.Birdge;
/*    */ import io.dcloud.common.util.DLGeolocation;
/*    */ 
/*    */ class f extends AdaWebview
/*    */ {
/* 45 */   float a = -1.0F;
/*    */ 
/* 47 */   protected f(Context paramContext, AbsMgr paramAbsMgr, d paramd) { super(paramContext, paramd);
/* 48 */     Logger.d("dhwebview", "DHWebview0");
/* 49 */     paramd.m = this;
/* 50 */     paramd.n = getWebviewParent();
/* 51 */     addJsInterface("_bridge", new Birdge(new i(this)));
/* 52 */     addJsInterface("_dlGeolocation", new DLGeolocation(this));
/* 53 */     Logger.d("dhwebview", "DHWebview hashcode=" + paramd.hashCode());
/*    */   }
/*    */ 
/*    */   public float getScaleOfOpenerWebview()
/*    */   {
/* 63 */     return this.a == -1.0F ? getScale() : this.a;
/*    */   }
/*    */ 
/*    */   public IApp obtainApp()
/*    */   {
/* 88 */     return obtainFrameView().obtainApp();
/*    */   }
/*    */ 
/*    */   public void show(Animation paramAnimation) {
/* 92 */     IFrameView localIFrameView = obtainFrameView();
/* 93 */     ((d)localIFrameView).setVisible(true, true);
/* 94 */     localIFrameView.obtainWindowMgr().processEvent(IMgr.MgrType.WindowMgr, 1, new Object[] { localIFrameView, paramAnimation });
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.f
 * JD-Core Version:    0.6.2
 */