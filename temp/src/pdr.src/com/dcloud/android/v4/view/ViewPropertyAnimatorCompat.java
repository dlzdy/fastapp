/*      */ package com.dcloud.android.v4.view;
/*      */ 
/*      */ import android.os.Build.VERSION;
/*      */ import android.view.View;
/*      */ import android.view.animation.Interpolator;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.WeakHashMap;
/*      */ 
/*      */ public class ViewPropertyAnimatorCompat
/*      */ {
/*      */   private static final String TAG = "ViewAnimatorCompat";
/*      */   private WeakReference<View> mView;
/*   27 */   private Runnable mStartAction = null;
/*   28 */   private Runnable mEndAction = null;
/*   29 */   private int mOldLayerType = -1;
/*      */   static final int LISTENER_TAG_ID = 2113929216;
/*      */   static final ViewPropertyAnimatorCompatImpl IMPL;
/*      */ 
/*      */   ViewPropertyAnimatorCompat(View paramView)
/*      */   {
/*   36 */     this.mView = new WeakReference(paramView);
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat setDuration(long paramLong)
/*      */   {
/*      */     View localView;
/*  674 */     if ((localView = (View)this.mView.get()) != null) {
/*  675 */       IMPL.setDuration(this, localView, paramLong);
/*      */     }
/*  677 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat alpha(float paramFloat)
/*      */   {
/*      */     View localView;
/*  691 */     if ((localView = (View)this.mView.get()) != null) {
/*  692 */       IMPL.alpha(this, localView, paramFloat);
/*      */     }
/*  694 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat alphaBy(float paramFloat)
/*      */   {
/*      */     View localView;
/*  708 */     if ((localView = (View)this.mView.get()) != null) {
/*  709 */       IMPL.alphaBy(this, localView, paramFloat);
/*      */     }
/*  711 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat translationX(float paramFloat)
/*      */   {
/*      */     View localView;
/*  725 */     if ((localView = (View)this.mView.get()) != null) {
/*  726 */       IMPL.translationX(this, localView, paramFloat);
/*      */     }
/*  728 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat translationY(float paramFloat)
/*      */   {
/*      */     View localView;
/*  742 */     if ((localView = (View)this.mView.get()) != null) {
/*  743 */       IMPL.translationY(this, localView, paramFloat);
/*      */     }
/*  745 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat withEndAction(Runnable paramRunnable)
/*      */   {
/*      */     View localView;
/*  777 */     if ((localView = (View)this.mView.get()) != null) {
/*  778 */       IMPL.withEndAction(this, localView, paramRunnable);
/*      */     }
/*  780 */     return this;
/*      */   }
/*      */ 
/*      */   public long getDuration()
/*      */   {
/*      */     View localView;
/*  795 */     if ((localView = (View)this.mView.get()) != null) {
/*  796 */       return IMPL.getDuration(this, localView);
/*      */     }
/*  798 */     return 0L;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat setInterpolator(Interpolator paramInterpolator)
/*      */   {
/*      */     View localView;
/*  814 */     if ((localView = (View)this.mView.get()) != null) {
/*  815 */       IMPL.setInterpolator(this, localView, paramInterpolator);
/*      */     }
/*  817 */     return this;
/*      */   }
/*      */ 
/*      */   public Interpolator getInterpolator()
/*      */   {
/*      */     View localView;
/*  829 */     if ((localView = (View)this.mView.get()) != null) {
/*  830 */       return IMPL.getInterpolator(this, localView);
/*      */     }
/*  832 */     return null;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat setStartDelay(long paramLong)
/*      */   {
/*      */     View localView;
/*  848 */     if ((localView = (View)this.mView.get()) != null) {
/*  849 */       IMPL.setStartDelay(this, localView, paramLong);
/*      */     }
/*  851 */     return this;
/*      */   }
/*      */ 
/*      */   public long getStartDelay()
/*      */   {
/*      */     View localView;
/*  866 */     if ((localView = (View)this.mView.get()) != null) {
/*  867 */       return IMPL.getStartDelay(this, localView);
/*      */     }
/*  869 */     return 0L;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat rotation(float paramFloat)
/*      */   {
/*      */     View localView;
/*  884 */     if ((localView = (View)this.mView.get()) != null) {
/*  885 */       IMPL.rotation(this, localView, paramFloat);
/*      */     }
/*  887 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat rotationBy(float paramFloat)
/*      */   {
/*      */     View localView;
/*  901 */     if ((localView = (View)this.mView.get()) != null) {
/*  902 */       IMPL.rotationBy(this, localView, paramFloat);
/*      */     }
/*  904 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat rotationX(float paramFloat)
/*      */   {
/*      */     View localView;
/*  918 */     if ((localView = (View)this.mView.get()) != null) {
/*  919 */       IMPL.rotationX(this, localView, paramFloat);
/*      */     }
/*  921 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat rotationXBy(float paramFloat)
/*      */   {
/*      */     View localView;
/*  935 */     if ((localView = (View)this.mView.get()) != null) {
/*  936 */       IMPL.rotationXBy(this, localView, paramFloat);
/*      */     }
/*  938 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat rotationY(float paramFloat)
/*      */   {
/*      */     View localView;
/*  952 */     if ((localView = (View)this.mView.get()) != null) {
/*  953 */       IMPL.rotationY(this, localView, paramFloat);
/*      */     }
/*  955 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat rotationYBy(float paramFloat)
/*      */   {
/*      */     View localView;
/*  969 */     if ((localView = (View)this.mView.get()) != null) {
/*  970 */       IMPL.rotationYBy(this, localView, paramFloat);
/*      */     }
/*  972 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat scaleX(float paramFloat)
/*      */   {
/*      */     View localView;
/*  986 */     if ((localView = (View)this.mView.get()) != null) {
/*  987 */       IMPL.scaleX(this, localView, paramFloat);
/*      */     }
/*  989 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat scaleXBy(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1003 */     if ((localView = (View)this.mView.get()) != null) {
/* 1004 */       IMPL.scaleXBy(this, localView, paramFloat);
/*      */     }
/* 1006 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat scaleY(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1020 */     if ((localView = (View)this.mView.get()) != null) {
/* 1021 */       IMPL.scaleY(this, localView, paramFloat);
/*      */     }
/* 1023 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat scaleYBy(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1037 */     if ((localView = (View)this.mView.get()) != null) {
/* 1038 */       IMPL.scaleYBy(this, localView, paramFloat);
/*      */     }
/* 1040 */     return this;
/*      */   }
/*      */ 
/*      */   public void cancel()
/*      */   {
/*      */     View localView;
/* 1048 */     if ((localView = (View)this.mView.get()) != null)
/* 1049 */       IMPL.cancel(this, localView);
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat x(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1064 */     if ((localView = (View)this.mView.get()) != null) {
/* 1065 */       IMPL.x(this, localView, paramFloat);
/*      */     }
/* 1067 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat xBy(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1081 */     if ((localView = (View)this.mView.get()) != null) {
/* 1082 */       IMPL.xBy(this, localView, paramFloat);
/*      */     }
/* 1084 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat y(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1098 */     if ((localView = (View)this.mView.get()) != null) {
/* 1099 */       IMPL.y(this, localView, paramFloat);
/*      */     }
/* 1101 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat yBy(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1115 */     if ((localView = (View)this.mView.get()) != null) {
/* 1116 */       IMPL.yBy(this, localView, paramFloat);
/*      */     }
/* 1118 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat translationXBy(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1132 */     if ((localView = (View)this.mView.get()) != null) {
/* 1133 */       IMPL.translationXBy(this, localView, paramFloat);
/*      */     }
/* 1135 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat translationYBy(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1149 */     if ((localView = (View)this.mView.get()) != null) {
/* 1150 */       IMPL.translationYBy(this, localView, paramFloat);
/*      */     }
/* 1152 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat translationZBy(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1166 */     if ((localView = (View)this.mView.get()) != null) {
/* 1167 */       IMPL.translationZBy(this, localView, paramFloat);
/*      */     }
/* 1169 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat translationZ(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1183 */     if ((localView = (View)this.mView.get()) != null) {
/* 1184 */       IMPL.translationZ(this, localView, paramFloat);
/*      */     }
/* 1186 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat z(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1200 */     if ((localView = (View)this.mView.get()) != null) {
/* 1201 */       IMPL.z(this, localView, paramFloat);
/*      */     }
/* 1203 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat zBy(float paramFloat)
/*      */   {
/*      */     View localView;
/* 1217 */     if ((localView = (View)this.mView.get()) != null) {
/* 1218 */       IMPL.zBy(this, localView, paramFloat);
/*      */     }
/* 1220 */     return this;
/*      */   }
/*      */ 
/*      */   public void start()
/*      */   {
/*      */     View localView;
/* 1234 */     if ((localView = (View)this.mView.get()) != null)
/* 1235 */       IMPL.start(this, localView);
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat withLayer()
/*      */   {
/*      */     View localView;
/* 1271 */     if ((localView = (View)this.mView.get()) != null) {
/* 1272 */       IMPL.withLayer(this, localView);
/*      */     }
/* 1274 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat withStartAction(Runnable paramRunnable)
/*      */   {
/*      */     View localView;
/* 1296 */     if ((localView = (View)this.mView.get()) != null) {
/* 1297 */       IMPL.withStartAction(this, localView, paramRunnable);
/*      */     }
/* 1299 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
/*      */   {
/*      */     View localView;
/* 1314 */     if ((localView = (View)this.mView.get()) != null) {
/* 1315 */       IMPL.setListener(this, localView, paramViewPropertyAnimatorListener);
/*      */     }
/* 1317 */     return this;
/*      */   }
/*      */ 
/*      */   public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener)
/*      */   {
/*      */     View localView;
/* 1333 */     if ((localView = (View)this.mView.get()) != null) {
/* 1334 */       IMPL.setUpdateListener(this, localView, paramViewPropertyAnimatorUpdateListener);
/*      */     }
/* 1336 */     return this;
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  645 */     int i = Build.VERSION.SDK_INT;
/*  646 */     if (i >= 21)
/*  647 */       IMPL = new LollipopViewPropertyAnimatorCompatImpl();
/*  648 */     else if (i >= 19)
/*  649 */       IMPL = new KitKatViewPropertyAnimatorCompatImpl();
/*  650 */     else if (i >= 18)
/*  651 */       IMPL = new JBMr2ViewPropertyAnimatorCompatImpl();
/*  652 */     else if (i >= 16)
/*  653 */       IMPL = new JBViewPropertyAnimatorCompatImpl();
/*  654 */     else if (i >= 14)
/*  655 */       IMPL = new ICSViewPropertyAnimatorCompatImpl();
/*      */     else
/*  657 */       IMPL = new BaseViewPropertyAnimatorCompatImpl();
/*      */   }
/*      */ 
/*      */   static class LollipopViewPropertyAnimatorCompatImpl extends ViewPropertyAnimatorCompat.KitKatViewPropertyAnimatorCompatImpl
/*      */   {
/*      */     public void translationZ(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  624 */       ViewPropertyAnimatorCompatLollipop.translationZ(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void translationZBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  629 */       ViewPropertyAnimatorCompatLollipop.translationZBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void z(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  634 */       ViewPropertyAnimatorCompatLollipop.z(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void zBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  639 */       ViewPropertyAnimatorCompatLollipop.zBy(paramView, paramFloat);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class KitKatViewPropertyAnimatorCompatImpl extends ViewPropertyAnimatorCompat.JBMr2ViewPropertyAnimatorCompatImpl
/*      */   {
/*      */     public void setUpdateListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener)
/*      */     {
/*  617 */       ViewPropertyAnimatorCompatKK.setUpdateListener(paramView, paramViewPropertyAnimatorUpdateListener);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class JBMr2ViewPropertyAnimatorCompatImpl extends ViewPropertyAnimatorCompat.JBViewPropertyAnimatorCompatImpl
/*      */   {
/*      */     public Interpolator getInterpolator(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  610 */       return ViewPropertyAnimatorCompatJellybeanMr2.getInterpolator(paramView);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class JBViewPropertyAnimatorCompatImpl extends ViewPropertyAnimatorCompat.ICSViewPropertyAnimatorCompatImpl
/*      */   {
/*      */     public void setListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
/*      */     {
/*  587 */       ViewPropertyAnimatorCompatJB.setListener(paramView, paramViewPropertyAnimatorListener);
/*      */     }
/*      */ 
/*      */     public void withStartAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
/*      */     {
/*  592 */       ViewPropertyAnimatorCompatJB.withStartAction(paramView, paramRunnable);
/*      */     }
/*      */ 
/*      */     public void withEndAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
/*      */     {
/*  597 */       ViewPropertyAnimatorCompatJB.withEndAction(paramView, paramRunnable);
/*      */     }
/*      */ 
/*      */     public void withLayer(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  602 */       ViewPropertyAnimatorCompatJB.withLayer(paramView);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class ICSViewPropertyAnimatorCompatImpl extends ViewPropertyAnimatorCompat.BaseViewPropertyAnimatorCompatImpl
/*      */   {
/*  363 */     WeakHashMap<View, Integer> mLayerMap = null;
/*      */ 
/*      */     public void setDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong)
/*      */     {
/*  367 */       ViewPropertyAnimatorCompatICS.setDuration(paramView, paramLong);
/*      */     }
/*      */ 
/*      */     public void alpha(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  372 */       ViewPropertyAnimatorCompatICS.alpha(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void translationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  377 */       ViewPropertyAnimatorCompatICS.translationX(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void translationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  382 */       ViewPropertyAnimatorCompatICS.translationY(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public long getDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  387 */       return ViewPropertyAnimatorCompatICS.getDuration(paramView);
/*      */     }
/*      */ 
/*      */     public void setInterpolator(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Interpolator paramInterpolator)
/*      */     {
/*  392 */       ViewPropertyAnimatorCompatICS.setInterpolator(paramView, paramInterpolator);
/*      */     }
/*      */ 
/*      */     public void setStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong)
/*      */     {
/*  397 */       ViewPropertyAnimatorCompatICS.setStartDelay(paramView, paramLong);
/*      */     }
/*      */ 
/*      */     public long getStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  402 */       return ViewPropertyAnimatorCompatICS.getStartDelay(paramView);
/*      */     }
/*      */ 
/*      */     public void alphaBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  407 */       ViewPropertyAnimatorCompatICS.alphaBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void rotation(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  412 */       ViewPropertyAnimatorCompatICS.rotation(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void rotationBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  417 */       ViewPropertyAnimatorCompatICS.rotationBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void rotationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  422 */       ViewPropertyAnimatorCompatICS.rotationX(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void rotationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  427 */       ViewPropertyAnimatorCompatICS.rotationXBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void rotationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  432 */       ViewPropertyAnimatorCompatICS.rotationY(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void rotationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  437 */       ViewPropertyAnimatorCompatICS.rotationYBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void scaleX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  442 */       ViewPropertyAnimatorCompatICS.scaleX(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void scaleXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  447 */       ViewPropertyAnimatorCompatICS.scaleXBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void scaleY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  452 */       ViewPropertyAnimatorCompatICS.scaleY(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void scaleYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  457 */       ViewPropertyAnimatorCompatICS.scaleYBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void cancel(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  462 */       ViewPropertyAnimatorCompatICS.cancel(paramView);
/*      */     }
/*      */ 
/*      */     public void x(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  467 */       ViewPropertyAnimatorCompatICS.x(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void xBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  472 */       ViewPropertyAnimatorCompatICS.xBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void y(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  477 */       ViewPropertyAnimatorCompatICS.y(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void yBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  482 */       ViewPropertyAnimatorCompatICS.yBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void translationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  487 */       ViewPropertyAnimatorCompatICS.translationXBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void translationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  492 */       ViewPropertyAnimatorCompatICS.translationYBy(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void start(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  497 */       ViewPropertyAnimatorCompatICS.start(paramView);
/*      */     }
/*      */ 
/*      */     public void setListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
/*      */     {
/*  502 */       paramView.setTag(2113929216, paramViewPropertyAnimatorListener);
/*  503 */       ViewPropertyAnimatorCompatICS.setListener(paramView, new MyVpaListener(paramViewPropertyAnimatorCompat));
/*      */     }
/*      */ 
/*      */     public void withEndAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
/*      */     {
/*  508 */       ViewPropertyAnimatorCompatICS.setListener(paramView, new MyVpaListener(paramViewPropertyAnimatorCompat));
/*  509 */       paramViewPropertyAnimatorCompat.mEndAction = paramRunnable;
/*      */     }
/*      */ 
/*      */     public void withStartAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
/*      */     {
/*  514 */       ViewPropertyAnimatorCompatICS.setListener(paramView, new MyVpaListener(paramViewPropertyAnimatorCompat));
/*  515 */       paramViewPropertyAnimatorCompat.mStartAction = paramRunnable;
/*      */     }
/*      */ 
/*      */     public void withLayer(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  520 */       paramViewPropertyAnimatorCompat.mOldLayerType = ViewCompat.getLayerType(paramView);
/*  521 */       ViewPropertyAnimatorCompatICS.setListener(paramView, new MyVpaListener(paramViewPropertyAnimatorCompat));
/*      */     }
/*      */ 
/*      */     static class MyVpaListener implements ViewPropertyAnimatorListener
/*      */     {
/*      */       ViewPropertyAnimatorCompat mVpa;
/*      */ 
/*      */       MyVpaListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat) {
/*  529 */         this.mVpa = paramViewPropertyAnimatorCompat;
/*      */       }
/*      */ 
/*      */       public void onAnimationStart(View paramView)
/*      */       {
/*  534 */         if (this.mVpa.mOldLayerType >= 0) {
/*  535 */           ViewCompat.setLayerType(paramView, 2, null);
/*      */         }
/*  537 */         if (this.mVpa.mStartAction != null) {
/*  538 */           this.mVpa.mStartAction.run();
/*      */         }
/*  540 */         Object localObject = paramView.getTag(2113929216);
/*  541 */         ViewPropertyAnimatorListener localViewPropertyAnimatorListener = null;
/*  542 */         if ((localObject instanceof ViewPropertyAnimatorListener)) {
/*  543 */           localViewPropertyAnimatorListener = (ViewPropertyAnimatorListener)localObject;
/*      */         }
/*  545 */         if (localViewPropertyAnimatorListener != null)
/*  546 */           localViewPropertyAnimatorListener.onAnimationStart(paramView);
/*      */       }
/*      */ 
/*      */       public void onAnimationEnd(View paramView)
/*      */       {
/*  552 */         if (this.mVpa.mOldLayerType >= 0) {
/*  553 */           ViewCompat.setLayerType(paramView, this.mVpa.mOldLayerType, null);
/*  554 */           this.mVpa.mOldLayerType = -1;
/*      */         }
/*  556 */         if (this.mVpa.mEndAction != null) {
/*  557 */           this.mVpa.mEndAction.run();
/*      */         }
/*  559 */         Object localObject = paramView.getTag(2113929216);
/*  560 */         ViewPropertyAnimatorListener localViewPropertyAnimatorListener = null;
/*  561 */         if ((localObject instanceof ViewPropertyAnimatorListener)) {
/*  562 */           localViewPropertyAnimatorListener = (ViewPropertyAnimatorListener)localObject;
/*      */         }
/*  564 */         if (localViewPropertyAnimatorListener != null)
/*  565 */           localViewPropertyAnimatorListener.onAnimationEnd(paramView);
/*      */       }
/*      */ 
/*      */       public void onAnimationCancel(View paramView)
/*      */       {
/*  571 */         Object localObject = paramView.getTag(2113929216);
/*  572 */         ViewPropertyAnimatorListener localViewPropertyAnimatorListener = null;
/*  573 */         if ((localObject instanceof ViewPropertyAnimatorListener)) {
/*  574 */           localViewPropertyAnimatorListener = (ViewPropertyAnimatorListener)localObject;
/*      */         }
/*  576 */         if (localViewPropertyAnimatorListener != null)
/*  577 */           localViewPropertyAnimatorListener.onAnimationCancel(paramView);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   static class BaseViewPropertyAnimatorCompatImpl
/*      */     implements ViewPropertyAnimatorCompat.ViewPropertyAnimatorCompatImpl
/*      */   {
/*   82 */     WeakHashMap<View, Runnable> mStarterMap = null;
/*      */ 
/*      */     public void setDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void alpha(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*   92 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void translationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*   98 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void translationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  104 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void withEndAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
/*      */     {
/*  109 */       paramViewPropertyAnimatorCompat.mEndAction = paramRunnable;
/*  110 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public long getDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  115 */       return 0L;
/*      */     }
/*      */ 
/*      */     public void setInterpolator(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Interpolator paramInterpolator)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Interpolator getInterpolator(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  125 */       return null;
/*      */     }
/*      */ 
/*      */     public void setStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong)
/*      */     {
/*      */     }
/*      */ 
/*      */     public long getStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  135 */       return 0L;
/*      */     }
/*      */ 
/*      */     public void alphaBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  141 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void rotation(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  147 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void rotationBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  153 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void rotationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  159 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void rotationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  165 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void rotationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  171 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void rotationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  177 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void scaleX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  183 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void scaleXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  189 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void scaleY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  195 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void scaleYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  201 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void cancel(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  207 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void x(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  213 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void xBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  219 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void y(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  225 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void yBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  231 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void z(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void zBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void translationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  247 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void translationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*  253 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void translationZ(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void translationZBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void start(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  268 */       removeStartMessage(paramView);
/*  269 */       startAnimation(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void withLayer(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void withStartAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable)
/*      */     {
/*  279 */       paramViewPropertyAnimatorCompat.mStartAction = paramRunnable;
/*  280 */       postStartMessage(paramViewPropertyAnimatorCompat, paramView);
/*      */     }
/*      */ 
/*      */     public void setListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener)
/*      */     {
/*  285 */       paramView.setTag(2113929216, paramViewPropertyAnimatorListener);
/*      */     }
/*      */ 
/*      */     public void setUpdateListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener)
/*      */     {
/*      */     }
/*      */ 
/*      */     private void startAnimation(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  294 */       Object localObject = paramView.getTag(2113929216);
/*  295 */       ViewPropertyAnimatorListener localViewPropertyAnimatorListener = null;
/*  296 */       if ((localObject instanceof ViewPropertyAnimatorListener)) {
/*  297 */         localViewPropertyAnimatorListener = (ViewPropertyAnimatorListener)localObject;
/*      */       }
/*  299 */       Runnable localRunnable1 = paramViewPropertyAnimatorCompat.mStartAction;
/*  300 */       Runnable localRunnable2 = paramViewPropertyAnimatorCompat.mEndAction;
/*  301 */       if (localRunnable1 != null) {
/*  302 */         localRunnable1.run();
/*      */       }
/*  304 */       if (localViewPropertyAnimatorListener != null) {
/*  305 */         localViewPropertyAnimatorListener.onAnimationStart(paramView);
/*  306 */         localViewPropertyAnimatorListener.onAnimationEnd(paramView);
/*      */       }
/*  308 */       if (localRunnable2 != null) {
/*  309 */         localRunnable2.run();
/*      */       }
/*  311 */       if (this.mStarterMap != null)
/*  312 */         this.mStarterMap.remove(paramView);
/*      */     }
/*      */ 
/*      */     private void removeStartMessage(View paramView)
/*      */     {
/*  335 */       Runnable localRunnable = null;
/*  336 */       if (this.mStarterMap != null) {
/*  337 */         localRunnable = (Runnable)this.mStarterMap.get(paramView);
/*  338 */         if (localRunnable != null)
/*  339 */           paramView.removeCallbacks(localRunnable);
/*      */       }
/*      */     }
/*      */ 
/*      */     private void postStartMessage(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView)
/*      */     {
/*  345 */       Object localObject = null;
/*  346 */       if (this.mStarterMap != null) {
/*  347 */         localObject = (Runnable)this.mStarterMap.get(paramView);
/*      */       }
/*  349 */       if (localObject == null) {
/*  350 */         localObject = new Starter(paramViewPropertyAnimatorCompat, paramView, null);
/*  351 */         if (this.mStarterMap == null) {
/*  352 */           this.mStarterMap = new WeakHashMap();
/*      */         }
/*  354 */         this.mStarterMap.put(paramView, localObject);
/*      */       }
/*  356 */       paramView.removeCallbacks((Runnable)localObject);
/*  357 */       paramView.post((Runnable)localObject);
/*      */     }
/*      */ 
/*      */     class Starter
/*      */       implements Runnable
/*      */     {
/*      */       WeakReference<View> mViewRef;
/*      */       ViewPropertyAnimatorCompat mVpa;
/*      */ 
/*      */       private Starter(ViewPropertyAnimatorCompat paramView, View arg3)
/*      */       {
/*      */         Object localObject;
/*  321 */         this.mViewRef = new WeakReference(localObject);
/*  322 */         this.mVpa = paramView;
/*      */       }
/*      */ 
/*      */       public void run()
/*      */       {
/*  327 */         View localView = (View)this.mViewRef.get();
/*  328 */         if (localView != null)
/*  329 */           ViewPropertyAnimatorCompat.BaseViewPropertyAnimatorCompatImpl.this.startAnimation(this.mVpa, localView);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   static abstract interface ViewPropertyAnimatorCompatImpl
/*      */   {
/*      */     public abstract void setDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong);
/*      */ 
/*      */     public abstract long getDuration(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView);
/*      */ 
/*      */     public abstract void setInterpolator(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Interpolator paramInterpolator);
/*      */ 
/*      */     public abstract Interpolator getInterpolator(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView);
/*      */ 
/*      */     public abstract void setStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, long paramLong);
/*      */ 
/*      */     public abstract long getStartDelay(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView);
/*      */ 
/*      */     public abstract void alpha(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void alphaBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void rotation(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void rotationBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void rotationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void rotationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void rotationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void rotationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void scaleX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void scaleXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void scaleY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void scaleYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void cancel(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView);
/*      */ 
/*      */     public abstract void x(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void xBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void y(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void yBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void z(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void zBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void translationX(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void translationXBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void translationY(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void translationYBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void translationZ(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void translationZBy(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void start(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView);
/*      */ 
/*      */     public abstract void withLayer(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView);
/*      */ 
/*      */     public abstract void withStartAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable);
/*      */ 
/*      */     public abstract void withEndAction(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, Runnable paramRunnable);
/*      */ 
/*      */     public abstract void setListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorListener paramViewPropertyAnimatorListener);
/*      */ 
/*      */     public abstract void setUpdateListener(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, View paramView, ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener);
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewPropertyAnimatorCompat
 * JD-Core Version:    0.6.2
 */