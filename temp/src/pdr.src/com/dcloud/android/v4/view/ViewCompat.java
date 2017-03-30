/*      */ package com.dcloud.android.v4.view;
/*      */ 
/*      */ import android.content.res.ColorStateList;
/*      */ import android.graphics.Paint;
/*      */ import android.graphics.PorterDuff.Mode;
/*      */ import android.graphics.Rect;
/*      */ import android.graphics.drawable.Drawable;
/*      */ import android.os.Build.VERSION;
/*      */ import android.os.Bundle;
/*      */ import android.util.Log;
/*      */ import android.view.View;
/*      */ import android.view.ViewGroup;
/*      */ import android.view.ViewParent;
/*      */ import android.view.accessibility.AccessibilityEvent;
/*      */ import com.dcloud.android.annotation.FloatRange;
/*      */ import com.dcloud.android.annotation.IntDef;
/*      */ import com.dcloud.android.annotation.Nullable;
/*      */ import com.dcloud.android.v4.view.accessibility.AccessibilityNodeInfoCompat;
/*      */ import com.dcloud.android.v4.view.accessibility.AccessibilityNodeProviderCompat;
/*      */ import java.lang.annotation.Annotation;
/*      */ import java.lang.annotation.Retention;
/*      */ import java.lang.annotation.RetentionPolicy;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.WeakHashMap;
/*      */ 
/*      */ public class ViewCompat
/*      */ {
/*      */   private static final String TAG = "ViewCompat";
/*      */   public static final int OVER_SCROLL_ALWAYS = 0;
/*      */   public static final int OVER_SCROLL_IF_CONTENT_SCROLLS = 1;
/*      */   public static final int OVER_SCROLL_NEVER = 2;
/*      */   private static final long FAKE_FRAME_TIME = 10L;
/*      */   public static final int IMPORTANT_FOR_ACCESSIBILITY_AUTO = 0;
/*      */   public static final int IMPORTANT_FOR_ACCESSIBILITY_YES = 1;
/*      */   public static final int IMPORTANT_FOR_ACCESSIBILITY_NO = 2;
/*      */   public static final int IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS = 4;
/*      */   public static final int ACCESSIBILITY_LIVE_REGION_NONE = 0;
/*      */   public static final int ACCESSIBILITY_LIVE_REGION_POLITE = 1;
/*      */   public static final int ACCESSIBILITY_LIVE_REGION_ASSERTIVE = 2;
/*      */   public static final int LAYER_TYPE_NONE = 0;
/*      */   public static final int LAYER_TYPE_SOFTWARE = 1;
/*      */   public static final int LAYER_TYPE_HARDWARE = 2;
/*      */   public static final int LAYOUT_DIRECTION_LTR = 0;
/*      */   public static final int LAYOUT_DIRECTION_RTL = 1;
/*      */   public static final int LAYOUT_DIRECTION_INHERIT = 2;
/*      */   public static final int LAYOUT_DIRECTION_LOCALE = 3;
/*      */   public static final int MEASURED_SIZE_MASK = 16777215;
/*      */   public static final int MEASURED_STATE_MASK = -16777216;
/*      */   public static final int MEASURED_HEIGHT_STATE_SHIFT = 16;
/*      */   public static final int MEASURED_STATE_TOO_SMALL = 16777216;
/*      */   public static final int SCROLL_AXIS_NONE = 0;
/*      */   public static final int SCROLL_AXIS_HORIZONTAL = 1;
/*      */   public static final int SCROLL_AXIS_VERTICAL = 2;
/*      */   static final ViewCompatImpl IMPL;
/*      */ 
/*      */   public static boolean canScrollHorizontally(View paramView, int paramInt)
/*      */   {
/* 1564 */     return IMPL.canScrollHorizontally(paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public static boolean canScrollVertically(View paramView, int paramInt)
/*      */   {
/* 1575 */     return IMPL.canScrollVertically(paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public static int getOverScrollMode(View paramView)
/*      */   {
/* 1589 */     return IMPL.getOverScrollMode(paramView);
/*      */   }
/*      */ 
/*      */   public static void setOverScrollMode(View paramView, int paramInt)
/*      */   {
/* 1605 */     IMPL.setOverScrollMode(paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public static void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
/*      */   {
/* 1642 */     IMPL.onPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
/*      */   }
/*      */ 
/*      */   public static void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
/*      */   {
/* 1675 */     IMPL.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
/*      */   }
/*      */ 
/*      */   public static void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
/*      */   {
/* 1711 */     IMPL.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
/*      */   }
/*      */ 
/*      */   public static void setAccessibilityDelegate(View paramView, AccessibilityDelegateCompat paramAccessibilityDelegateCompat)
/*      */   {
/* 1726 */     IMPL.setAccessibilityDelegate(paramView, paramAccessibilityDelegateCompat);
/*      */   }
/*      */ 
/*      */   public static boolean hasAccessibilityDelegate(View paramView)
/*      */   {
/* 1736 */     return IMPL.hasAccessibilityDelegate(paramView);
/*      */   }
/*      */ 
/*      */   public static boolean hasTransientState(View paramView)
/*      */   {
/* 1748 */     return IMPL.hasTransientState(paramView);
/*      */   }
/*      */ 
/*      */   public static void setHasTransientState(View paramView, boolean paramBoolean)
/*      */   {
/* 1759 */     IMPL.setHasTransientState(paramView, paramBoolean);
/*      */   }
/*      */ 
/*      */   public static void postInvalidateOnAnimation(View paramView)
/*      */   {
/* 1772 */     IMPL.postInvalidateOnAnimation(paramView);
/*      */   }
/*      */ 
/*      */   public static void postInvalidateOnAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/* 1790 */     IMPL.postInvalidateOnAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */   public static void postOnAnimation(View paramView, Runnable paramRunnable)
/*      */   {
/* 1804 */     IMPL.postOnAnimation(paramView, paramRunnable);
/*      */   }
/*      */ 
/*      */   public static void postOnAnimationDelayed(View paramView, Runnable paramRunnable, long paramLong)
/*      */   {
/* 1821 */     IMPL.postOnAnimationDelayed(paramView, paramRunnable, paramLong);
/*      */   }
/*      */ 
/*      */   public static int getImportantForAccessibility(View paramView)
/*      */   {
/* 1839 */     return IMPL.getImportantForAccessibility(paramView);
/*      */   }
/*      */ 
/*      */   public static void setImportantForAccessibility(View paramView, int paramInt)
/*      */   {
/* 1863 */     IMPL.setImportantForAccessibility(paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public static boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
/*      */   {
/* 1881 */     return IMPL.performAccessibilityAction(paramView, paramInt, paramBundle);
/*      */   }
/*      */ 
/*      */   public static AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView)
/*      */   {
/* 1908 */     return IMPL.getAccessibilityNodeProvider(paramView);
/*      */   }
/*      */ 
/*      */   public static float getAlpha(View paramView)
/*      */   {
/* 1919 */     return IMPL.getAlpha(paramView);
/*      */   }
/*      */ 
/*      */   public static void setLayerType(View paramView, int paramInt, Paint paramPaint)
/*      */   {
/* 1962 */     IMPL.setLayerType(paramView, paramInt, paramPaint);
/*      */   }
/*      */ 
/*      */   public static int getLayerType(View paramView)
/*      */   {
/* 1983 */     return IMPL.getLayerType(paramView);
/*      */   }
/*      */ 
/*      */   public static int getLabelFor(View paramView)
/*      */   {
/* 1994 */     return IMPL.getLabelFor(paramView);
/*      */   }
/*      */ 
/*      */   public static void setLabelFor(View paramView, int paramInt)
/*      */   {
/* 2005 */     IMPL.setLabelFor(paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public static void setLayerPaint(View paramView, Paint paramPaint)
/*      */   {
/* 2039 */     IMPL.setLayerPaint(paramView, paramPaint);
/*      */   }
/*      */ 
/*      */   public static int getLayoutDirection(View paramView)
/*      */   {
/* 2054 */     return IMPL.getLayoutDirection(paramView);
/*      */   }
/*      */ 
/*      */   public static void setLayoutDirection(View paramView, int paramInt)
/*      */   {
/* 2074 */     IMPL.setLayoutDirection(paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public static ViewParent getParentForAccessibility(View paramView)
/*      */   {
/* 2086 */     return IMPL.getParentForAccessibility(paramView);
/*      */   }
/*      */ 
/*      */   public static boolean isOpaque(View paramView)
/*      */   {
/* 2099 */     return IMPL.isOpaque(paramView);
/*      */   }
/*      */ 
/*      */   public static int resolveSizeAndState(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2116 */     return IMPL.resolveSizeAndState(paramInt1, paramInt2, paramInt3);
/*      */   }
/*      */ 
/*      */   public static int getMeasuredWidthAndState(View paramView)
/*      */   {
/* 2130 */     return IMPL.getMeasuredWidthAndState(paramView);
/*      */   }
/*      */ 
/*      */   public static int getMeasuredHeightAndState(View paramView)
/*      */   {
/* 2144 */     return IMPL.getMeasuredHeightAndState(paramView);
/*      */   }
/*      */ 
/*      */   public static int getMeasuredState(View paramView)
/*      */   {
/* 2155 */     return IMPL.getMeasuredState(paramView);
/*      */   }
/*      */ 
/*      */   public static int combineMeasuredStates(int paramInt1, int paramInt2)
/*      */   {
/* 2167 */     return IMPL.combineMeasuredStates(paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */   public static int getAccessibilityLiveRegion(View paramView)
/*      */   {
/* 2180 */     return IMPL.getAccessibilityLiveRegion(paramView);
/*      */   }
/*      */ 
/*      */   public static void setAccessibilityLiveRegion(View paramView, int paramInt)
/*      */   {
/* 2212 */     IMPL.setAccessibilityLiveRegion(paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public static int getPaddingStart(View paramView)
/*      */   {
/* 2224 */     return IMPL.getPaddingStart(paramView);
/*      */   }
/*      */ 
/*      */   public static int getPaddingEnd(View paramView)
/*      */   {
/* 2236 */     return IMPL.getPaddingEnd(paramView);
/*      */   }
/*      */ 
/*      */   public static void setPaddingRelative(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/* 2253 */     IMPL.setPaddingRelative(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */ 
/*      */   public static void dispatchStartTemporaryDetach(View paramView)
/*      */   {
/* 2260 */     IMPL.dispatchStartTemporaryDetach(paramView);
/*      */   }
/*      */ 
/*      */   public static void dispatchFinishTemporaryDetach(View paramView)
/*      */   {
/* 2267 */     IMPL.dispatchFinishTemporaryDetach(paramView);
/*      */   }
/*      */ 
/*      */   public static float getTranslationX(View paramView)
/*      */   {
/* 2280 */     return IMPL.getTranslationX(paramView);
/*      */   }
/*      */ 
/*      */   public static float getTranslationY(View paramView)
/*      */   {
/* 2293 */     return IMPL.getTranslationY(paramView);
/*      */   }
/*      */ 
/*      */   public static int getMinimumWidth(View paramView)
/*      */   {
/* 2304 */     return IMPL.getMinimumWidth(paramView);
/*      */   }
/*      */ 
/*      */   public static int getMinimumHeight(View paramView)
/*      */   {
/* 2315 */     return IMPL.getMinimumHeight(paramView);
/*      */   }
/*      */ 
/*      */   public static ViewPropertyAnimatorCompat animate(View paramView)
/*      */   {
/* 2327 */     return IMPL.animate(paramView);
/*      */   }
/*      */ 
/*      */   public static void setTranslationX(View paramView, float paramFloat)
/*      */   {
/* 2341 */     IMPL.setTranslationX(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void setTranslationY(View paramView, float paramFloat)
/*      */   {
/* 2357 */     IMPL.setTranslationY(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void setAlpha(View paramView, @FloatRange(from=0.0D, to=1.0D) float paramFloat)
/*      */   {
/* 2373 */     IMPL.setAlpha(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void setX(View paramView, float paramFloat)
/*      */   {
/* 2387 */     IMPL.setX(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void setY(View paramView, float paramFloat)
/*      */   {
/* 2401 */     IMPL.setY(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void setRotation(View paramView, float paramFloat)
/*      */   {
/* 2413 */     IMPL.setRotation(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void setRotationX(View paramView, float paramFloat)
/*      */   {
/* 2426 */     IMPL.setRotationX(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void setRotationY(View paramView, float paramFloat)
/*      */   {
/* 2439 */     IMPL.setRotationY(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void setScaleX(View paramView, float paramFloat)
/*      */   {
/* 2451 */     IMPL.setScaleX(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static void setScaleY(View paramView, float paramFloat)
/*      */   {
/* 2463 */     IMPL.setScaleY(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static float getPivotX(View paramView)
/*      */   {
/* 2474 */     return IMPL.getPivotX(paramView);
/*      */   }
/*      */ 
/*      */   public static void setPivotX(View paramView, float paramFloat)
/*      */   {
/* 2489 */     IMPL.setPivotX(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static float getPivotY(View paramView)
/*      */   {
/* 2501 */     return IMPL.getPivotY(paramView);
/*      */   }
/*      */ 
/*      */   public static void setPivotY(View paramView, float paramFloat)
/*      */   {
/* 2516 */     IMPL.setPivotX(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static float getRotation(View paramView) {
/* 2520 */     return IMPL.getRotation(paramView);
/*      */   }
/*      */ 
/*      */   public static float getRotationX(View paramView) {
/* 2524 */     return IMPL.getRotationX(paramView);
/*      */   }
/*      */ 
/*      */   public static float getRotationY(View paramView) {
/* 2528 */     return IMPL.getRotationY(paramView);
/*      */   }
/*      */ 
/*      */   public static float getScaleX(View paramView) {
/* 2532 */     return IMPL.getScaleX(paramView);
/*      */   }
/*      */ 
/*      */   public static float getScaleY(View paramView) {
/* 2536 */     return IMPL.getScaleY(paramView);
/*      */   }
/*      */ 
/*      */   public static float getX(View paramView) {
/* 2540 */     return IMPL.getX(paramView);
/*      */   }
/*      */ 
/*      */   public static float getY(View paramView) {
/* 2544 */     return IMPL.getY(paramView);
/*      */   }
/*      */ 
/*      */   public static void setElevation(View paramView, float paramFloat)
/*      */   {
/* 2551 */     IMPL.setElevation(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static float getElevation(View paramView)
/*      */   {
/* 2560 */     return IMPL.getElevation(paramView);
/*      */   }
/*      */ 
/*      */   public static void setTranslationZ(View paramView, float paramFloat)
/*      */   {
/* 2567 */     IMPL.setTranslationZ(paramView, paramFloat);
/*      */   }
/*      */ 
/*      */   public static float getTranslationZ(View paramView)
/*      */   {
/* 2576 */     return IMPL.getTranslationZ(paramView);
/*      */   }
/*      */ 
/*      */   public static void setTransitionName(View paramView, String paramString)
/*      */   {
/* 2587 */     IMPL.setTransitionName(paramView, paramString);
/*      */   }
/*      */ 
/*      */   public static String getTransitionName(View paramView)
/*      */   {
/* 2601 */     return IMPL.getTransitionName(paramView);
/*      */   }
/*      */ 
/*      */   public static int getWindowSystemUiVisibility(View paramView)
/*      */   {
/* 2608 */     return IMPL.getWindowSystemUiVisibility(paramView);
/*      */   }
/*      */ 
/*      */   public static void requestApplyInsets(View paramView)
/*      */   {
/* 2616 */     IMPL.requestApplyInsets(paramView);
/*      */   }
/*      */ 
/*      */   public static void setChildrenDrawingOrderEnabled(ViewGroup paramViewGroup, boolean paramBoolean)
/*      */   {
/* 2629 */     IMPL.setChildrenDrawingOrderEnabled(paramViewGroup, paramBoolean);
/*      */   }
/*      */ 
/*      */   public static boolean getFitsSystemWindows(View paramView)
/*      */   {
/* 2637 */     return IMPL.getFitsSystemWindows(paramView);
/*      */   }
/*      */ 
/*      */   public static void setFitsSystemWindows(View paramView, boolean paramBoolean)
/*      */   {
/* 2647 */     IMPL.setFitsSystemWindows(paramView, paramBoolean);
/*      */   }
/*      */ 
/*      */   public static void jumpDrawablesToCurrentState(View paramView)
/*      */   {
/* 2658 */     IMPL.jumpDrawablesToCurrentState(paramView);
/*      */   }
/*      */ 
/*      */   public static void setOnApplyWindowInsetsListener(View paramView, OnApplyWindowInsetsListener paramOnApplyWindowInsetsListener)
/*      */   {
/* 2667 */     IMPL.setOnApplyWindowInsetsListener(paramView, paramOnApplyWindowInsetsListener);
/*      */   }
/*      */ 
/*      */   public static WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
/*      */   {
/* 2683 */     return IMPL.onApplyWindowInsets(paramView, paramWindowInsetsCompat);
/*      */   }
/*      */ 
/*      */   public static WindowInsetsCompat dispatchApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
/*      */   {
/* 2700 */     return IMPL.dispatchApplyWindowInsets(paramView, paramWindowInsetsCompat);
/*      */   }
/*      */ 
/*      */   public static void setSaveFromParentEnabled(View paramView, boolean paramBoolean)
/*      */   {
/* 2711 */     IMPL.setSaveFromParentEnabled(paramView, paramBoolean);
/*      */   }
/*      */ 
/*      */   public static void setActivated(View paramView, boolean paramBoolean)
/*      */   {
/* 2724 */     IMPL.setActivated(paramView, paramBoolean);
/*      */   }
/*      */ 
/*      */   public static boolean hasOverlappingRendering(View paramView)
/*      */   {
/* 2742 */     return IMPL.hasOverlappingRendering(paramView);
/*      */   }
/*      */ 
/*      */   public static boolean isPaddingRelative(View paramView)
/*      */   {
/* 2752 */     return IMPL.isPaddingRelative(paramView);
/*      */   }
/*      */ 
/*      */   public static ColorStateList getBackgroundTintList(View paramView)
/*      */   {
/* 2762 */     return IMPL.getBackgroundTintList(paramView);
/*      */   }
/*      */ 
/*      */   public static void setBackgroundTintList(View paramView, ColorStateList paramColorStateList)
/*      */   {
/* 2773 */     IMPL.setBackgroundTintList(paramView, paramColorStateList);
/*      */   }
/*      */ 
/*      */   public static PorterDuff.Mode getBackgroundTintMode(View paramView)
/*      */   {
/* 2784 */     return IMPL.getBackgroundTintMode(paramView);
/*      */   }
/*      */ 
/*      */   public static void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode)
/*      */   {
/* 2797 */     IMPL.setBackgroundTintMode(paramView, paramMode);
/*      */   }
/*      */ 
/*      */   public static void setNestedScrollingEnabled(View paramView, boolean paramBoolean)
/*      */   {
/* 2815 */     IMPL.setNestedScrollingEnabled(paramView, paramBoolean);
/*      */   }
/*      */ 
/*      */   public static boolean isNestedScrollingEnabled(View paramView)
/*      */   {
/* 2831 */     return IMPL.isNestedScrollingEnabled(paramView);
/*      */   }
/*      */ 
/*      */   public static boolean startNestedScroll(View paramView, int paramInt)
/*      */   {
/* 2873 */     return IMPL.startNestedScroll(paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public static void stopNestedScroll(View paramView)
/*      */   {
/* 2884 */     IMPL.stopNestedScroll(paramView);
/*      */   }
/*      */ 
/*      */   public static boolean hasNestedScrollingParent(View paramView)
/*      */   {
/* 2896 */     return IMPL.hasNestedScrollingParent(paramView);
/*      */   }
/*      */ 
/*      */   public static boolean dispatchNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
/*      */   {
/* 2924 */     return IMPL.dispatchNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public static boolean dispatchNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */   {
/* 2949 */     return IMPL.dispatchNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
/*      */   }
/*      */ 
/*      */   public static boolean dispatchNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
/*      */   {
/* 2972 */     return IMPL.dispatchNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
/*      */   }
/*      */ 
/*      */   public static boolean dispatchNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
/*      */   {
/* 3006 */     return IMPL.dispatchNestedPreFling(paramView, paramFloat1, paramFloat2);
/*      */   }
/*      */ 
/*      */   public static boolean isLaidOut(View paramView)
/*      */   {
/* 3014 */     return IMPL.isLaidOut(paramView);
/*      */   }
/*      */ 
/*      */   public static float getZ(View paramView)
/*      */   {
/* 3025 */     return IMPL.getZ(paramView);
/*      */   }
/*      */ 
/*      */   public static void offsetTopAndBottom(View paramView, int paramInt)
/*      */   {
/* 3034 */     paramView.offsetTopAndBottom(paramInt);
/*      */ 
/* 3036 */     if ((paramInt != 0) && (Build.VERSION.SDK_INT < 11))
/*      */     {
/* 3038 */       paramView.invalidate();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void offsetLeftAndRight(View paramView, int paramInt)
/*      */   {
/* 3047 */     paramView.offsetLeftAndRight(paramInt);
/*      */ 
/* 3049 */     if ((paramInt != 0) && (Build.VERSION.SDK_INT < 11))
/*      */     {
/* 3051 */       paramView.invalidate();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void setClipBounds(View paramView, Rect paramRect)
/*      */   {
/* 3067 */     IMPL.setClipBounds(paramView, paramRect);
/*      */   }
/*      */ 
/*      */   public static Rect getClipBounds(View paramView)
/*      */   {
/* 3079 */     return IMPL.getClipBounds(paramView);
/*      */   }
/*      */ 
/*      */   public static boolean isAttachedToWindow(View paramView)
/*      */   {
/* 3086 */     return IMPL.isAttachedToWindow(paramView);
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/* 1534 */     int i = Build.VERSION.SDK_INT;
/* 1535 */     if (i >= 21)
/* 1536 */       IMPL = new LollipopViewCompatImpl();
/* 1537 */     else if (i >= 19)
/* 1538 */       IMPL = new KitKatViewCompatImpl();
/* 1539 */     else if (i >= 17)
/* 1540 */       IMPL = new JbMr1ViewCompatImpl();
/* 1541 */     else if (i >= 16)
/* 1542 */       IMPL = new JBViewCompatImpl();
/* 1543 */     else if (i >= 14)
/* 1544 */       IMPL = new ICSViewCompatImpl();
/* 1545 */     else if (i >= 11)
/* 1546 */       IMPL = new HCViewCompatImpl();
/* 1547 */     else if (i >= 9)
/* 1548 */       IMPL = new GBViewCompatImpl();
/* 1549 */     else if (i >= 7)
/* 1550 */       IMPL = new EclairMr1ViewCompatImpl();
/*      */     else
/* 1552 */       IMPL = new BaseViewCompatImpl();
/*      */   }
/*      */ 
/*      */   static class LollipopViewCompatImpl extends ViewCompat.KitKatViewCompatImpl
/*      */   {
/*      */     public void setTransitionName(View paramView, String paramString)
/*      */     {
/* 1403 */       ViewCompatLollipop.setTransitionName(paramView, paramString);
/*      */     }
/*      */ 
/*      */     public String getTransitionName(View paramView)
/*      */     {
/* 1408 */       return ViewCompatLollipop.getTransitionName(paramView);
/*      */     }
/*      */ 
/*      */     public void requestApplyInsets(View paramView)
/*      */     {
/* 1413 */       ViewCompatLollipop.requestApplyInsets(paramView);
/*      */     }
/*      */ 
/*      */     public void setElevation(View paramView, float paramFloat)
/*      */     {
/* 1418 */       ViewCompatLollipop.setElevation(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public float getElevation(View paramView)
/*      */     {
/* 1423 */       return ViewCompatLollipop.getElevation(paramView);
/*      */     }
/*      */ 
/*      */     public void setTranslationZ(View paramView, float paramFloat)
/*      */     {
/* 1428 */       ViewCompatLollipop.setTranslationZ(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public float getTranslationZ(View paramView)
/*      */     {
/* 1433 */       return ViewCompatLollipop.getTranslationZ(paramView);
/*      */     }
/*      */ 
/*      */     public void setOnApplyWindowInsetsListener(View paramView, OnApplyWindowInsetsListener paramOnApplyWindowInsetsListener)
/*      */     {
/* 1438 */       ViewCompatLollipop.setOnApplyWindowInsetsListener(paramView, paramOnApplyWindowInsetsListener);
/*      */     }
/*      */ 
/*      */     public void setNestedScrollingEnabled(View paramView, boolean paramBoolean)
/*      */     {
/* 1443 */       ViewCompatLollipop.setNestedScrollingEnabled(paramView, paramBoolean);
/*      */     }
/*      */ 
/*      */     public boolean isNestedScrollingEnabled(View paramView)
/*      */     {
/* 1448 */       return ViewCompatLollipop.isNestedScrollingEnabled(paramView);
/*      */     }
/*      */ 
/*      */     public boolean startNestedScroll(View paramView, int paramInt)
/*      */     {
/* 1453 */       return ViewCompatLollipop.startNestedScroll(paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public void stopNestedScroll(View paramView)
/*      */     {
/* 1458 */       ViewCompatLollipop.stopNestedScroll(paramView);
/*      */     }
/*      */ 
/*      */     public boolean hasNestedScrollingParent(View paramView)
/*      */     {
/* 1463 */       return ViewCompatLollipop.hasNestedScrollingParent(paramView);
/*      */     }
/*      */ 
/*      */     public boolean dispatchNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
/*      */     {
/* 1469 */       return ViewCompatLollipop.dispatchNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
/*      */     }
/*      */ 
/*      */     public boolean dispatchNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     {
/* 1476 */       return ViewCompatLollipop.dispatchNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
/*      */     }
/*      */ 
/*      */     public boolean dispatchNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
/*      */     {
/* 1483 */       return ViewCompatLollipop.dispatchNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
/*      */     }
/*      */ 
/*      */     public boolean dispatchNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
/*      */     {
/* 1488 */       return ViewCompatLollipop.dispatchNestedPreFling(paramView, paramFloat1, paramFloat2);
/*      */     }
/*      */ 
/*      */     public boolean isImportantForAccessibility(View paramView)
/*      */     {
/* 1493 */       return ViewCompatLollipop.isImportantForAccessibility(paramView);
/*      */     }
/*      */ 
/*      */     public ColorStateList getBackgroundTintList(View paramView)
/*      */     {
/* 1498 */       return ViewCompatLollipop.getBackgroundTintList(paramView);
/*      */     }
/*      */ 
/*      */     public void setBackgroundTintList(View paramView, ColorStateList paramColorStateList)
/*      */     {
/* 1503 */       ViewCompatLollipop.setBackgroundTintList(paramView, paramColorStateList);
/*      */     }
/*      */ 
/*      */     public void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode)
/*      */     {
/* 1508 */       ViewCompatLollipop.setBackgroundTintMode(paramView, paramMode);
/*      */     }
/*      */ 
/*      */     public PorterDuff.Mode getBackgroundTintMode(View paramView)
/*      */     {
/* 1513 */       return ViewCompatLollipop.getBackgroundTintMode(paramView);
/*      */     }
/*      */ 
/*      */     public WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
/*      */     {
/* 1518 */       return ViewCompatLollipop.onApplyWindowInsets(paramView, paramWindowInsetsCompat);
/*      */     }
/*      */ 
/*      */     public WindowInsetsCompat dispatchApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
/*      */     {
/* 1523 */       return ViewCompatLollipop.dispatchApplyWindowInsets(paramView, paramWindowInsetsCompat);
/*      */     }
/*      */ 
/*      */     public float getZ(View paramView)
/*      */     {
/* 1528 */       return ViewCompatLollipop.getZ(paramView);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class KitKatViewCompatImpl extends ViewCompat.JbMr2ViewCompatImpl
/*      */   {
/*      */     public int getAccessibilityLiveRegion(View paramView)
/*      */     {
/* 1376 */       return ViewCompatKitKat.getAccessibilityLiveRegion(paramView);
/*      */     }
/*      */ 
/*      */     public void setAccessibilityLiveRegion(View paramView, int paramInt)
/*      */     {
/* 1381 */       ViewCompatKitKat.setAccessibilityLiveRegion(paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public void setImportantForAccessibility(View paramView, int paramInt)
/*      */     {
/* 1386 */       ViewCompatJB.setImportantForAccessibility(paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public boolean isLaidOut(View paramView)
/*      */     {
/* 1391 */       return ViewCompatKitKat.isLaidOut(paramView);
/*      */     }
/*      */ 
/*      */     public boolean isAttachedToWindow(View paramView)
/*      */     {
/* 1396 */       return ViewCompatKitKat.isAttachedToWindow(paramView);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class JbMr2ViewCompatImpl extends ViewCompat.JbMr1ViewCompatImpl
/*      */   {
/*      */     public void setClipBounds(View paramView, Rect paramRect)
/*      */     {
/* 1364 */       ViewCompatJellybeanMr2.setClipBounds(paramView, paramRect);
/*      */     }
/*      */ 
/*      */     public Rect getClipBounds(View paramView)
/*      */     {
/* 1369 */       return ViewCompatJellybeanMr2.getClipBounds(paramView);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class JbMr1ViewCompatImpl extends ViewCompat.JBViewCompatImpl
/*      */   {
/*      */     public int getLabelFor(View paramView)
/*      */     {
/* 1312 */       return ViewCompatJellybeanMr1.getLabelFor(paramView);
/*      */     }
/*      */ 
/*      */     public void setLabelFor(View paramView, int paramInt)
/*      */     {
/* 1317 */       ViewCompatJellybeanMr1.setLabelFor(paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public void setLayerPaint(View paramView, Paint paramPaint)
/*      */     {
/* 1322 */       ViewCompatJellybeanMr1.setLayerPaint(paramView, paramPaint);
/*      */     }
/*      */ 
/*      */     public int getLayoutDirection(View paramView)
/*      */     {
/* 1327 */       return ViewCompatJellybeanMr1.getLayoutDirection(paramView);
/*      */     }
/*      */ 
/*      */     public void setLayoutDirection(View paramView, int paramInt)
/*      */     {
/* 1332 */       ViewCompatJellybeanMr1.setLayoutDirection(paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public int getPaddingStart(View paramView)
/*      */     {
/* 1337 */       return ViewCompatJellybeanMr1.getPaddingStart(paramView);
/*      */     }
/*      */ 
/*      */     public int getPaddingEnd(View paramView)
/*      */     {
/* 1342 */       return ViewCompatJellybeanMr1.getPaddingEnd(paramView);
/*      */     }
/*      */ 
/*      */     public void setPaddingRelative(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     {
/* 1347 */       ViewCompatJellybeanMr1.setPaddingRelative(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     }
/*      */ 
/*      */     public int getWindowSystemUiVisibility(View paramView)
/*      */     {
/* 1352 */       return ViewCompatJellybeanMr1.getWindowSystemUiVisibility(paramView);
/*      */     }
/*      */ 
/*      */     public boolean isPaddingRelative(View paramView)
/*      */     {
/* 1357 */       return ViewCompatJellybeanMr1.isPaddingRelative(paramView);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class JBViewCompatImpl extends ViewCompat.ICSViewCompatImpl
/*      */   {
/*      */     public boolean hasTransientState(View paramView)
/*      */     {
/* 1228 */       return ViewCompatJB.hasTransientState(paramView);
/*      */     }
/*      */ 
/*      */     public void setHasTransientState(View paramView, boolean paramBoolean) {
/* 1232 */       ViewCompatJB.setHasTransientState(paramView, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void postInvalidateOnAnimation(View paramView) {
/* 1236 */       ViewCompatJB.postInvalidateOnAnimation(paramView);
/*      */     }
/*      */ 
/*      */     public void postInvalidateOnAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/* 1240 */       ViewCompatJB.postInvalidateOnAnimation(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     }
/*      */ 
/*      */     public void postOnAnimation(View paramView, Runnable paramRunnable) {
/* 1244 */       ViewCompatJB.postOnAnimation(paramView, paramRunnable);
/*      */     }
/*      */ 
/*      */     public void postOnAnimationDelayed(View paramView, Runnable paramRunnable, long paramLong) {
/* 1248 */       ViewCompatJB.postOnAnimationDelayed(paramView, paramRunnable, paramLong);
/*      */     }
/*      */ 
/*      */     public int getImportantForAccessibility(View paramView) {
/* 1252 */       return ViewCompatJB.getImportantForAccessibility(paramView);
/*      */     }
/*      */ 
/*      */     public void setImportantForAccessibility(View paramView, int paramInt)
/*      */     {
/* 1259 */       if (paramInt == 4) {
/* 1260 */         paramInt = 2;
/*      */       }
/* 1262 */       ViewCompatJB.setImportantForAccessibility(paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle) {
/* 1266 */       return ViewCompatJB.performAccessibilityAction(paramView, paramInt, paramBundle);
/*      */     }
/*      */ 
/*      */     public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView) {
/* 1270 */       Object localObject = ViewCompatJB.getAccessibilityNodeProvider(paramView);
/* 1271 */       if (localObject != null) {
/* 1272 */         return new AccessibilityNodeProviderCompat(localObject);
/*      */       }
/* 1274 */       return null;
/*      */     }
/*      */ 
/*      */     public ViewParent getParentForAccessibility(View paramView)
/*      */     {
/* 1279 */       return ViewCompatJB.getParentForAccessibility(paramView);
/*      */     }
/*      */ 
/*      */     public int getMinimumWidth(View paramView)
/*      */     {
/* 1284 */       return ViewCompatJB.getMinimumWidth(paramView);
/*      */     }
/*      */ 
/*      */     public int getMinimumHeight(View paramView)
/*      */     {
/* 1289 */       return ViewCompatJB.getMinimumHeight(paramView);
/*      */     }
/*      */ 
/*      */     public void requestApplyInsets(View paramView)
/*      */     {
/* 1294 */       ViewCompatJB.requestApplyInsets(paramView);
/*      */     }
/*      */ 
/*      */     public boolean getFitsSystemWindows(View paramView)
/*      */     {
/* 1299 */       return ViewCompatJB.getFitsSystemWindows(paramView);
/*      */     }
/*      */ 
/*      */     public boolean hasOverlappingRendering(View paramView)
/*      */     {
/* 1304 */       return ViewCompatJB.hasOverlappingRendering(paramView);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class ICSViewCompatImpl extends ViewCompat.HCViewCompatImpl
/*      */   {
/*      */     static Field mAccessibilityDelegateField;
/* 1154 */     static boolean accessibilityDelegateCheckFailed = false;
/*      */ 
/*      */     public boolean canScrollHorizontally(View paramView, int paramInt) {
/* 1157 */       return ViewCompatICS.canScrollHorizontally(paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public boolean canScrollVertically(View paramView, int paramInt) {
/* 1161 */       return ViewCompatICS.canScrollVertically(paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent) {
/* 1165 */       ViewCompatICS.onPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
/*      */     }
/*      */ 
/*      */     public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent) {
/* 1169 */       ViewCompatICS.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
/*      */     }
/*      */ 
/*      */     public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat) {
/* 1173 */       ViewCompatICS.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat.getInfo());
/*      */     }
/*      */ 
/*      */     public void setAccessibilityDelegate(View paramView, @Nullable AccessibilityDelegateCompat paramAccessibilityDelegateCompat)
/*      */     {
/* 1178 */       ViewCompatICS.setAccessibilityDelegate(paramView, paramAccessibilityDelegateCompat == null ? null : paramAccessibilityDelegateCompat.getBridge());
/*      */     }
/*      */ 
/*      */     public boolean hasAccessibilityDelegate(View paramView)
/*      */     {
/* 1184 */       if (accessibilityDelegateCheckFailed) {
/* 1185 */         return false;
/*      */       }
/* 1187 */       if (mAccessibilityDelegateField == null)
/*      */         try {
/* 1189 */           mAccessibilityDelegateField = View.class.getDeclaredField("mAccessibilityDelegate");
/*      */ 
/* 1191 */           mAccessibilityDelegateField.setAccessible(true);
/*      */         } catch (Throwable localThrowable1) {
/* 1193 */           accessibilityDelegateCheckFailed = true;
/* 1194 */           return false;
/*      */         }
/*      */       try
/*      */       {
/* 1198 */         return mAccessibilityDelegateField.get(paramView) != null;
/*      */       } catch (Throwable localThrowable2) {
/* 1200 */         accessibilityDelegateCheckFailed = true;
/* 1201 */       }return false;
/*      */     }
/*      */ 
/*      */     public ViewPropertyAnimatorCompat animate(View paramView)
/*      */     {
/* 1207 */       if (this.mViewPropertyAnimatorCompatMap == null) {
/* 1208 */         this.mViewPropertyAnimatorCompatMap = new WeakHashMap();
/*      */       }
/*      */ 
/* 1211 */       ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = (ViewPropertyAnimatorCompat)this.mViewPropertyAnimatorCompatMap.get(paramView);
/* 1212 */       if (localViewPropertyAnimatorCompat == null) {
/* 1213 */         localViewPropertyAnimatorCompat = new ViewPropertyAnimatorCompat(paramView);
/* 1214 */         this.mViewPropertyAnimatorCompatMap.put(paramView, localViewPropertyAnimatorCompat);
/*      */       }
/* 1216 */       return localViewPropertyAnimatorCompat;
/*      */     }
/*      */ 
/*      */     public void setFitsSystemWindows(View paramView, boolean paramBoolean)
/*      */     {
/* 1221 */       ViewCompatICS.setFitsSystemWindows(paramView, paramBoolean);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class HCViewCompatImpl extends ViewCompat.GBViewCompatImpl
/*      */   {
/*      */     long getFrameTime()
/*      */     {
/*  994 */       return ViewCompatHC.getFrameTime();
/*      */     }
/*      */ 
/*      */     public float getAlpha(View paramView) {
/*  998 */       return ViewCompatHC.getAlpha(paramView);
/*      */     }
/*      */ 
/*      */     public void setLayerType(View paramView, int paramInt, Paint paramPaint) {
/* 1002 */       ViewCompatHC.setLayerType(paramView, paramInt, paramPaint);
/*      */     }
/*      */ 
/*      */     public int getLayerType(View paramView) {
/* 1006 */       return ViewCompatHC.getLayerType(paramView);
/*      */     }
/*      */ 
/*      */     public void setLayerPaint(View paramView, Paint paramPaint)
/*      */     {
/* 1012 */       setLayerType(paramView, getLayerType(paramView), paramPaint);
/*      */ 
/* 1014 */       paramView.invalidate();
/*      */     }
/*      */ 
/*      */     public int resolveSizeAndState(int paramInt1, int paramInt2, int paramInt3) {
/* 1018 */       return ViewCompatHC.resolveSizeAndState(paramInt1, paramInt2, paramInt3);
/*      */     }
/*      */ 
/*      */     public int getMeasuredWidthAndState(View paramView) {
/* 1022 */       return ViewCompatHC.getMeasuredWidthAndState(paramView);
/*      */     }
/*      */ 
/*      */     public int getMeasuredHeightAndState(View paramView) {
/* 1026 */       return ViewCompatHC.getMeasuredHeightAndState(paramView);
/*      */     }
/*      */ 
/*      */     public int getMeasuredState(View paramView) {
/* 1030 */       return ViewCompatHC.getMeasuredState(paramView);
/*      */     }
/*      */ 
/*      */     public float getTranslationX(View paramView) {
/* 1034 */       return ViewCompatHC.getTranslationX(paramView);
/*      */     }
/*      */ 
/*      */     public float getTranslationY(View paramView) {
/* 1038 */       return ViewCompatHC.getTranslationY(paramView);
/*      */     }
/*      */ 
/*      */     public void setTranslationX(View paramView, float paramFloat) {
/* 1042 */       ViewCompatHC.setTranslationX(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setTranslationY(View paramView, float paramFloat) {
/* 1046 */       ViewCompatHC.setTranslationY(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setAlpha(View paramView, float paramFloat) {
/* 1050 */       ViewCompatHC.setAlpha(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setX(View paramView, float paramFloat) {
/* 1054 */       ViewCompatHC.setX(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setY(View paramView, float paramFloat) {
/* 1058 */       ViewCompatHC.setY(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setRotation(View paramView, float paramFloat) {
/* 1062 */       ViewCompatHC.setRotation(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setRotationX(View paramView, float paramFloat) {
/* 1066 */       ViewCompatHC.setRotationX(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setRotationY(View paramView, float paramFloat) {
/* 1070 */       ViewCompatHC.setRotationY(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setScaleX(View paramView, float paramFloat) {
/* 1074 */       ViewCompatHC.setScaleX(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setScaleY(View paramView, float paramFloat) {
/* 1078 */       ViewCompatHC.setScaleY(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setPivotX(View paramView, float paramFloat) {
/* 1082 */       ViewCompatHC.setPivotX(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public void setPivotY(View paramView, float paramFloat) {
/* 1086 */       ViewCompatHC.setPivotY(paramView, paramFloat);
/*      */     }
/*      */ 
/*      */     public float getX(View paramView) {
/* 1090 */       return ViewCompatHC.getX(paramView);
/*      */     }
/*      */ 
/*      */     public float getY(View paramView)
/*      */     {
/* 1095 */       return ViewCompatHC.getY(paramView);
/*      */     }
/*      */ 
/*      */     public float getRotation(View paramView)
/*      */     {
/* 1100 */       return ViewCompatHC.getRotation(paramView);
/*      */     }
/*      */ 
/*      */     public float getRotationX(View paramView)
/*      */     {
/* 1105 */       return ViewCompatHC.getRotationX(paramView);
/*      */     }
/*      */ 
/*      */     public float getRotationY(View paramView)
/*      */     {
/* 1110 */       return ViewCompatHC.getRotationY(paramView);
/*      */     }
/*      */ 
/*      */     public float getScaleX(View paramView)
/*      */     {
/* 1115 */       return ViewCompatHC.getScaleX(paramView);
/*      */     }
/*      */ 
/*      */     public float getScaleY(View paramView)
/*      */     {
/* 1120 */       return ViewCompatHC.getScaleY(paramView);
/*      */     }
/*      */ 
/*      */     public float getPivotX(View paramView)
/*      */     {
/* 1125 */       return ViewCompatHC.getPivotX(paramView);
/*      */     }
/*      */ 
/*      */     public float getPivotY(View paramView) {
/* 1129 */       return ViewCompatHC.getPivotY(paramView);
/*      */     }
/*      */ 
/*      */     public void jumpDrawablesToCurrentState(View paramView) {
/* 1133 */       ViewCompatHC.jumpDrawablesToCurrentState(paramView);
/*      */     }
/*      */ 
/*      */     public void setSaveFromParentEnabled(View paramView, boolean paramBoolean)
/*      */     {
/* 1138 */       ViewCompatHC.setSaveFromParentEnabled(paramView, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setActivated(View paramView, boolean paramBoolean)
/*      */     {
/* 1143 */       ViewCompatHC.setActivated(paramView, paramBoolean);
/*      */     }
/*      */ 
/*      */     public int combineMeasuredStates(int paramInt1, int paramInt2)
/*      */     {
/* 1148 */       return ViewCompatHC.combineMeasuredStates(paramInt1, paramInt2);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class GBViewCompatImpl extends ViewCompat.EclairMr1ViewCompatImpl
/*      */   {
/*      */     public int getOverScrollMode(View paramView)
/*      */     {
/*  983 */       return ViewCompatGingerbread.getOverScrollMode(paramView);
/*      */     }
/*      */ 
/*      */     public void setOverScrollMode(View paramView, int paramInt) {
/*  987 */       ViewCompatGingerbread.setOverScrollMode(paramView, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class EclairMr1ViewCompatImpl extends ViewCompat.BaseViewCompatImpl
/*      */   {
/*      */     public boolean isOpaque(View paramView)
/*      */     {
/*  971 */       return ViewCompatEclairMr1.isOpaque(paramView);
/*      */     }
/*      */ 
/*      */     public void setChildrenDrawingOrderEnabled(ViewGroup paramViewGroup, boolean paramBoolean)
/*      */     {
/*  976 */       ViewCompatEclairMr1.setChildrenDrawingOrderEnabled(paramViewGroup, paramBoolean);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class BaseViewCompatImpl
/*      */     implements ViewCompat.ViewCompatImpl
/*      */   {
/*      */     private Method mDispatchStartTemporaryDetach;
/*      */     private Method mDispatchFinishTemporaryDetach;
/*      */     private boolean mTempDetachBound;
/*  393 */     WeakHashMap<View, ViewPropertyAnimatorCompat> mViewPropertyAnimatorCompatMap = null;
/*      */ 
/*      */     public boolean canScrollHorizontally(View paramView, int paramInt)
/*      */     {
/*  397 */       return ((paramView instanceof ScrollingView)) && (canScrollingViewScrollHorizontally((ScrollingView)paramView, paramInt));
/*      */     }
/*      */ 
/*      */     public boolean canScrollVertically(View paramView, int paramInt) {
/*  401 */       return ((paramView instanceof ScrollingView)) && (canScrollingViewScrollVertically((ScrollingView)paramView, paramInt));
/*      */     }
/*      */ 
/*      */     public int getOverScrollMode(View paramView) {
/*  405 */       return 2;
/*      */     }
/*      */ 
/*      */     public void setOverScrollMode(View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setAccessibilityDelegate(View paramView, AccessibilityDelegateCompat paramAccessibilityDelegateCompat) {
/*      */     }
/*      */ 
/*      */     public boolean hasAccessibilityDelegate(View paramView) {
/*  416 */       return false;
/*      */     }
/*      */ 
/*      */     public void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent) {
/*      */     }
/*      */ 
/*      */     public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat) {
/*      */     }
/*      */ 
/*      */     public boolean hasTransientState(View paramView) {
/*  430 */       return false;
/*      */     }
/*      */     public void setHasTransientState(View paramView, boolean paramBoolean) {
/*      */     }
/*      */ 
/*      */     public void postInvalidateOnAnimation(View paramView) {
/*  436 */       paramView.invalidate();
/*      */     }
/*      */     public void postInvalidateOnAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
/*  439 */       paramView.invalidate(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     }
/*      */     public void postOnAnimation(View paramView, Runnable paramRunnable) {
/*  442 */       paramView.postDelayed(paramRunnable, getFrameTime());
/*      */     }
/*      */     public void postOnAnimationDelayed(View paramView, Runnable paramRunnable, long paramLong) {
/*  445 */       paramView.postDelayed(paramRunnable, getFrameTime() + paramLong);
/*      */     }
/*      */     long getFrameTime() {
/*  448 */       return 10L;
/*      */     }
/*      */     public int getImportantForAccessibility(View paramView) {
/*  451 */       return 0;
/*      */     }
/*      */     public void setImportantForAccessibility(View paramView, int paramInt) {
/*      */     }
/*      */ 
/*      */     public boolean isImportantForAccessibility(View paramView) {
/*  457 */       return true;
/*      */     }
/*      */     public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle) {
/*  460 */       return false;
/*      */     }
/*      */     public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView) {
/*  463 */       return null;
/*      */     }
/*      */     public float getAlpha(View paramView) {
/*  466 */       return 1.0F;
/*      */     }
/*      */     public void setLayerType(View paramView, int paramInt, Paint paramPaint) {
/*      */     }
/*      */ 
/*      */     public int getLayerType(View paramView) {
/*  472 */       return 0;
/*      */     }
/*      */     public int getLabelFor(View paramView) {
/*  475 */       return 0;
/*      */     }
/*      */ 
/*      */     public void setLabelFor(View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setLayerPaint(View paramView, Paint paramPaint) {
/*      */     }
/*      */ 
/*      */     public int getLayoutDirection(View paramView) {
/*  486 */       return 0;
/*      */     }
/*      */ 
/*      */     public void setLayoutDirection(View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public ViewParent getParentForAccessibility(View paramView)
/*      */     {
/*  496 */       return paramView.getParent();
/*      */     }
/*      */ 
/*      */     public boolean isOpaque(View paramView)
/*      */     {
/*  501 */       Drawable localDrawable = paramView.getBackground();
/*  502 */       if (localDrawable != null) {
/*  503 */         return localDrawable.getOpacity() == -1;
/*      */       }
/*  505 */       return false;
/*      */     }
/*      */ 
/*      */     public int resolveSizeAndState(int paramInt1, int paramInt2, int paramInt3) {
/*  509 */       return View.resolveSize(paramInt1, paramInt2);
/*      */     }
/*      */ 
/*      */     public int getMeasuredWidthAndState(View paramView)
/*      */     {
/*  514 */       return paramView.getMeasuredWidth();
/*      */     }
/*      */ 
/*      */     public int getMeasuredHeightAndState(View paramView)
/*      */     {
/*  519 */       return paramView.getMeasuredHeight();
/*      */     }
/*      */ 
/*      */     public int getMeasuredState(View paramView)
/*      */     {
/*  524 */       return 0;
/*      */     }
/*      */ 
/*      */     public int getAccessibilityLiveRegion(View paramView)
/*      */     {
/*  529 */       return 0;
/*      */     }
/*      */ 
/*      */     public void setAccessibilityLiveRegion(View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public int getPaddingStart(View paramView)
/*      */     {
/*  539 */       return paramView.getPaddingLeft();
/*      */     }
/*      */ 
/*      */     public int getPaddingEnd(View paramView)
/*      */     {
/*  544 */       return paramView.getPaddingRight();
/*      */     }
/*      */ 
/*      */     public void setPaddingRelative(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */     {
/*  549 */       paramView.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */     }
/*      */ 
/*      */     public void dispatchStartTemporaryDetach(View paramView)
/*      */     {
/*  554 */       if (!this.mTempDetachBound) {
/*  555 */         bindTempDetach();
/*      */       }
/*  557 */       if (this.mDispatchStartTemporaryDetach != null) {
/*      */         try {
/*  559 */           this.mDispatchStartTemporaryDetach.invoke(paramView, new Object[0]);
/*      */         } catch (Exception localException) {
/*  561 */           Log.d("ViewCompat", "Error calling dispatchStartTemporaryDetach", localException);
/*      */         }
/*      */       }
/*      */       else
/*  565 */         paramView.onStartTemporaryDetach();
/*      */     }
/*      */ 
/*      */     public void dispatchFinishTemporaryDetach(View paramView)
/*      */     {
/*  571 */       if (!this.mTempDetachBound) {
/*  572 */         bindTempDetach();
/*      */       }
/*  574 */       if (this.mDispatchFinishTemporaryDetach != null) {
/*      */         try {
/*  576 */           this.mDispatchFinishTemporaryDetach.invoke(paramView, new Object[0]);
/*      */         } catch (Exception localException) {
/*  578 */           Log.d("ViewCompat", "Error calling dispatchFinishTemporaryDetach", localException);
/*      */         }
/*      */       }
/*      */       else
/*  582 */         paramView.onFinishTemporaryDetach();
/*      */     }
/*      */ 
/*      */     public boolean hasOverlappingRendering(View paramView)
/*      */     {
/*  588 */       return true;
/*      */     }
/*      */ 
/*      */     private void bindTempDetach() {
/*      */       try {
/*  593 */         this.mDispatchStartTemporaryDetach = View.class.getDeclaredMethod("dispatchStartTemporaryDetach", new Class[0]);
/*      */ 
/*  595 */         this.mDispatchFinishTemporaryDetach = View.class.getDeclaredMethod("dispatchFinishTemporaryDetach", new Class[0]);
/*      */       }
/*      */       catch (NoSuchMethodException localNoSuchMethodException) {
/*  598 */         Log.e("ViewCompat", "Couldn't find method", localNoSuchMethodException);
/*      */       }
/*  600 */       this.mTempDetachBound = true;
/*      */     }
/*      */ 
/*      */     public float getTranslationX(View paramView)
/*      */     {
/*  605 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getTranslationY(View paramView)
/*      */     {
/*  610 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getX(View paramView)
/*      */     {
/*  615 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getY(View paramView)
/*      */     {
/*  620 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getRotation(View paramView)
/*      */     {
/*  625 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getRotationX(View paramView)
/*      */     {
/*  630 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getRotationY(View paramView)
/*      */     {
/*  635 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getScaleX(View paramView)
/*      */     {
/*  640 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getScaleY(View paramView)
/*      */     {
/*  645 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public int getMinimumWidth(View paramView)
/*      */     {
/*  650 */       return ViewCompatBase.getMinimumWidth(paramView);
/*      */     }
/*      */ 
/*      */     public int getMinimumHeight(View paramView)
/*      */     {
/*  655 */       return ViewCompatBase.getMinimumHeight(paramView);
/*      */     }
/*      */ 
/*      */     public ViewPropertyAnimatorCompat animate(View paramView)
/*      */     {
/*  660 */       return new ViewPropertyAnimatorCompat(paramView);
/*      */     }
/*      */ 
/*      */     public void setRotation(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setTranslationX(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setTranslationY(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setAlpha(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setRotationX(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setRotationY(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setScaleX(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setScaleY(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setX(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setY(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setPivotX(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setPivotY(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public float getPivotX(View paramView)
/*      */     {
/*  725 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public float getPivotY(View paramView)
/*      */     {
/*  730 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public void setTransitionName(View paramView, String paramString)
/*      */     {
/*      */     }
/*      */ 
/*      */     public String getTransitionName(View paramView)
/*      */     {
/*  739 */       return null;
/*      */     }
/*      */ 
/*      */     public int getWindowSystemUiVisibility(View paramView)
/*      */     {
/*  744 */       return 0;
/*      */     }
/*      */ 
/*      */     public void requestApplyInsets(View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setElevation(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public float getElevation(View paramView)
/*      */     {
/*  757 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public void setTranslationZ(View paramView, float paramFloat)
/*      */     {
/*      */     }
/*      */ 
/*      */     public float getTranslationZ(View paramView)
/*      */     {
/*  766 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     public void setClipBounds(View paramView, Rect paramRect)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Rect getClipBounds(View paramView)
/*      */     {
/*  775 */       return null;
/*      */     }
/*      */ 
/*      */     public void setChildrenDrawingOrderEnabled(ViewGroup paramViewGroup, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public boolean getFitsSystemWindows(View paramView)
/*      */     {
/*  785 */       return false;
/*      */     }
/*      */ 
/*      */     public void setFitsSystemWindows(View paramView, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void jumpDrawablesToCurrentState(View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setOnApplyWindowInsetsListener(View paramView, OnApplyWindowInsetsListener paramOnApplyWindowInsetsListener)
/*      */     {
/*      */     }
/*      */ 
/*      */     public WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
/*      */     {
/*  806 */       return paramWindowInsetsCompat;
/*      */     }
/*      */ 
/*      */     public WindowInsetsCompat dispatchApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat)
/*      */     {
/*  811 */       return paramWindowInsetsCompat;
/*      */     }
/*      */ 
/*      */     public void setSaveFromParentEnabled(View paramView, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setActivated(View paramView, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public boolean isPaddingRelative(View paramView)
/*      */     {
/*  826 */       return false;
/*      */     }
/*      */ 
/*      */     public void setNestedScrollingEnabled(View paramView, boolean paramBoolean) {
/*  830 */       if ((paramView instanceof NestedScrollingChild))
/*  831 */         ((NestedScrollingChild)paramView).setNestedScrollingEnabled(paramBoolean);
/*      */     }
/*      */ 
/*      */     public boolean isNestedScrollingEnabled(View paramView)
/*      */     {
/*  837 */       if ((paramView instanceof NestedScrollingChild)) {
/*  838 */         return ((NestedScrollingChild)paramView).isNestedScrollingEnabled();
/*      */       }
/*  840 */       return false;
/*      */     }
/*      */ 
/*      */     public ColorStateList getBackgroundTintList(View paramView)
/*      */     {
/*  845 */       return ViewCompatBase.getBackgroundTintList(paramView);
/*      */     }
/*      */ 
/*      */     public void setBackgroundTintList(View paramView, ColorStateList paramColorStateList)
/*      */     {
/*  850 */       ViewCompatBase.setBackgroundTintList(paramView, paramColorStateList);
/*      */     }
/*      */ 
/*      */     public void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode)
/*      */     {
/*  855 */       ViewCompatBase.setBackgroundTintMode(paramView, paramMode);
/*      */     }
/*      */ 
/*      */     public PorterDuff.Mode getBackgroundTintMode(View paramView)
/*      */     {
/*  860 */       return ViewCompatBase.getBackgroundTintMode(paramView);
/*      */     }
/*      */ 
/*      */     private boolean canScrollingViewScrollHorizontally(ScrollingView paramScrollingView, int paramInt) {
/*  864 */       int i = paramScrollingView.computeHorizontalScrollOffset();
/*  865 */       int j = paramScrollingView.computeHorizontalScrollRange() - paramScrollingView.computeHorizontalScrollExtent();
/*      */ 
/*  867 */       if (j == 0) return false;
/*  868 */       if (paramInt < 0) {
/*  869 */         return i > 0;
/*      */       }
/*  871 */       return i < j - 1;
/*      */     }
/*      */ 
/*      */     private boolean canScrollingViewScrollVertically(ScrollingView paramScrollingView, int paramInt)
/*      */     {
/*  876 */       int i = paramScrollingView.computeVerticalScrollOffset();
/*  877 */       int j = paramScrollingView.computeVerticalScrollRange() - paramScrollingView.computeVerticalScrollExtent();
/*      */ 
/*  879 */       if (j == 0) return false;
/*  880 */       if (paramInt < 0) {
/*  881 */         return i > 0;
/*      */       }
/*  883 */       return i < j - 1;
/*      */     }
/*      */ 
/*      */     public boolean startNestedScroll(View paramView, int paramInt)
/*      */     {
/*  888 */       if ((paramView instanceof NestedScrollingChild)) {
/*  889 */         return ((NestedScrollingChild)paramView).startNestedScroll(paramInt);
/*      */       }
/*  891 */       return false;
/*      */     }
/*      */ 
/*      */     public void stopNestedScroll(View paramView)
/*      */     {
/*  896 */       if ((paramView instanceof NestedScrollingChild))
/*  897 */         ((NestedScrollingChild)paramView).stopNestedScroll();
/*      */     }
/*      */ 
/*      */     public boolean hasNestedScrollingParent(View paramView)
/*      */     {
/*  903 */       if ((paramView instanceof NestedScrollingChild)) {
/*  904 */         return ((NestedScrollingChild)paramView).hasNestedScrollingParent();
/*      */       }
/*  906 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean dispatchNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
/*      */     {
/*  912 */       if ((paramView instanceof NestedScrollingChild)) {
/*  913 */         return ((NestedScrollingChild)paramView).dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
/*      */       }
/*      */ 
/*  916 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean dispatchNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */     {
/*  922 */       if ((paramView instanceof NestedScrollingChild)) {
/*  923 */         return ((NestedScrollingChild)paramView).dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
/*      */       }
/*      */ 
/*  926 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean dispatchNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
/*      */     {
/*  932 */       if ((paramView instanceof NestedScrollingChild)) {
/*  933 */         return ((NestedScrollingChild)paramView).dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
/*      */       }
/*      */ 
/*  936 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean dispatchNestedPreFling(View paramView, float paramFloat1, float paramFloat2)
/*      */     {
/*  941 */       if ((paramView instanceof NestedScrollingChild)) {
/*  942 */         return ((NestedScrollingChild)paramView).dispatchNestedPreFling(paramFloat1, paramFloat2);
/*      */       }
/*  944 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isLaidOut(View paramView)
/*      */     {
/*  949 */       return ViewCompatBase.isLaidOut(paramView);
/*      */     }
/*      */ 
/*      */     public int combineMeasuredStates(int paramInt1, int paramInt2)
/*      */     {
/*  954 */       return paramInt1 | paramInt2;
/*      */     }
/*      */ 
/*      */     public float getZ(View paramView)
/*      */     {
/*  959 */       return getTranslationZ(paramView) + getElevation(paramView);
/*      */     }
/*      */ 
/*      */     public boolean isAttachedToWindow(View paramView)
/*      */     {
/*  964 */       return ViewCompatBase.isAttachedToWindow(paramView);
/*      */     }
/*      */   }
/*      */ 
/*      */   static abstract interface ViewCompatImpl
/*      */   {
/*      */     public abstract boolean canScrollHorizontally(View paramView, int paramInt);
/*      */ 
/*      */     public abstract boolean canScrollVertically(View paramView, int paramInt);
/*      */ 
/*      */     public abstract int getOverScrollMode(View paramView);
/*      */ 
/*      */     public abstract void setOverScrollMode(View paramView, int paramInt);
/*      */ 
/*      */     public abstract void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent);
/*      */ 
/*      */     public abstract void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent);
/*      */ 
/*      */     public abstract void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat);
/*      */ 
/*      */     public abstract void setAccessibilityDelegate(View paramView, @Nullable AccessibilityDelegateCompat paramAccessibilityDelegateCompat);
/*      */ 
/*      */     public abstract boolean hasAccessibilityDelegate(View paramView);
/*      */ 
/*      */     public abstract boolean hasTransientState(View paramView);
/*      */ 
/*      */     public abstract void setHasTransientState(View paramView, boolean paramBoolean);
/*      */ 
/*      */     public abstract void postInvalidateOnAnimation(View paramView);
/*      */ 
/*      */     public abstract void postInvalidateOnAnimation(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*      */ 
/*      */     public abstract void postOnAnimation(View paramView, Runnable paramRunnable);
/*      */ 
/*      */     public abstract void postOnAnimationDelayed(View paramView, Runnable paramRunnable, long paramLong);
/*      */ 
/*      */     public abstract int getImportantForAccessibility(View paramView);
/*      */ 
/*      */     public abstract void setImportantForAccessibility(View paramView, int paramInt);
/*      */ 
/*      */     public abstract boolean isImportantForAccessibility(View paramView);
/*      */ 
/*      */     public abstract boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle);
/*      */ 
/*      */     public abstract AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View paramView);
/*      */ 
/*      */     public abstract float getAlpha(View paramView);
/*      */ 
/*      */     public abstract void setLayerType(View paramView, int paramInt, Paint paramPaint);
/*      */ 
/*      */     public abstract int getLayerType(View paramView);
/*      */ 
/*      */     public abstract int getLabelFor(View paramView);
/*      */ 
/*      */     public abstract void setLabelFor(View paramView, int paramInt);
/*      */ 
/*      */     public abstract void setLayerPaint(View paramView, Paint paramPaint);
/*      */ 
/*      */     public abstract int getLayoutDirection(View paramView);
/*      */ 
/*      */     public abstract void setLayoutDirection(View paramView, int paramInt);
/*      */ 
/*      */     public abstract ViewParent getParentForAccessibility(View paramView);
/*      */ 
/*      */     public abstract boolean isOpaque(View paramView);
/*      */ 
/*      */     public abstract int resolveSizeAndState(int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */     public abstract int getMeasuredWidthAndState(View paramView);
/*      */ 
/*      */     public abstract int getMeasuredHeightAndState(View paramView);
/*      */ 
/*      */     public abstract int getMeasuredState(View paramView);
/*      */ 
/*      */     public abstract int getAccessibilityLiveRegion(View paramView);
/*      */ 
/*      */     public abstract void setAccessibilityLiveRegion(View paramView, int paramInt);
/*      */ 
/*      */     public abstract int getPaddingStart(View paramView);
/*      */ 
/*      */     public abstract int getPaddingEnd(View paramView);
/*      */ 
/*      */     public abstract void setPaddingRelative(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*      */ 
/*      */     public abstract void dispatchStartTemporaryDetach(View paramView);
/*      */ 
/*      */     public abstract void dispatchFinishTemporaryDetach(View paramView);
/*      */ 
/*      */     public abstract float getX(View paramView);
/*      */ 
/*      */     public abstract float getY(View paramView);
/*      */ 
/*      */     public abstract float getRotation(View paramView);
/*      */ 
/*      */     public abstract float getRotationX(View paramView);
/*      */ 
/*      */     public abstract float getRotationY(View paramView);
/*      */ 
/*      */     public abstract float getScaleX(View paramView);
/*      */ 
/*      */     public abstract float getScaleY(View paramView);
/*      */ 
/*      */     public abstract float getTranslationX(View paramView);
/*      */ 
/*      */     public abstract float getTranslationY(View paramView);
/*      */ 
/*      */     public abstract int getMinimumWidth(View paramView);
/*      */ 
/*      */     public abstract int getMinimumHeight(View paramView);
/*      */ 
/*      */     public abstract ViewPropertyAnimatorCompat animate(View paramView);
/*      */ 
/*      */     public abstract void setRotation(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setRotationX(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setRotationY(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setScaleX(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setScaleY(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setTranslationX(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setTranslationY(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setX(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setY(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setAlpha(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setPivotX(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract void setPivotY(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract float getPivotX(View paramView);
/*      */ 
/*      */     public abstract float getPivotY(View paramView);
/*      */ 
/*      */     public abstract void setElevation(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract float getElevation(View paramView);
/*      */ 
/*      */     public abstract void setTranslationZ(View paramView, float paramFloat);
/*      */ 
/*      */     public abstract float getTranslationZ(View paramView);
/*      */ 
/*      */     public abstract void setClipBounds(View paramView, Rect paramRect);
/*      */ 
/*      */     public abstract Rect getClipBounds(View paramView);
/*      */ 
/*      */     public abstract void setTransitionName(View paramView, String paramString);
/*      */ 
/*      */     public abstract String getTransitionName(View paramView);
/*      */ 
/*      */     public abstract int getWindowSystemUiVisibility(View paramView);
/*      */ 
/*      */     public abstract void requestApplyInsets(View paramView);
/*      */ 
/*      */     public abstract void setChildrenDrawingOrderEnabled(ViewGroup paramViewGroup, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean getFitsSystemWindows(View paramView);
/*      */ 
/*      */     public abstract boolean hasOverlappingRendering(View paramView);
/*      */ 
/*      */     public abstract void setFitsSystemWindows(View paramView, boolean paramBoolean);
/*      */ 
/*      */     public abstract void jumpDrawablesToCurrentState(View paramView);
/*      */ 
/*      */     public abstract void setOnApplyWindowInsetsListener(View paramView, OnApplyWindowInsetsListener paramOnApplyWindowInsetsListener);
/*      */ 
/*      */     public abstract WindowInsetsCompat onApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat);
/*      */ 
/*      */     public abstract WindowInsetsCompat dispatchApplyWindowInsets(View paramView, WindowInsetsCompat paramWindowInsetsCompat);
/*      */ 
/*      */     public abstract void setSaveFromParentEnabled(View paramView, boolean paramBoolean);
/*      */ 
/*      */     public abstract void setActivated(View paramView, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isPaddingRelative(View paramView);
/*      */ 
/*      */     public abstract ColorStateList getBackgroundTintList(View paramView);
/*      */ 
/*      */     public abstract void setBackgroundTintList(View paramView, ColorStateList paramColorStateList);
/*      */ 
/*      */     public abstract PorterDuff.Mode getBackgroundTintMode(View paramView);
/*      */ 
/*      */     public abstract void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode);
/*      */ 
/*      */     public abstract void setNestedScrollingEnabled(View paramView, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isNestedScrollingEnabled(View paramView);
/*      */ 
/*      */     public abstract boolean startNestedScroll(View paramView, int paramInt);
/*      */ 
/*      */     public abstract void stopNestedScroll(View paramView);
/*      */ 
/*      */     public abstract boolean hasNestedScrollingParent(View paramView);
/*      */ 
/*      */     public abstract boolean dispatchNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt);
/*      */ 
/*      */     public abstract boolean dispatchNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2);
/*      */ 
/*      */     public abstract boolean dispatchNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean dispatchNestedPreFling(View paramView, float paramFloat1, float paramFloat2);
/*      */ 
/*      */     public abstract boolean isLaidOut(View paramView);
/*      */ 
/*      */     public abstract int combineMeasuredStates(int paramInt1, int paramInt2);
/*      */ 
/*      */     public abstract float getZ(View paramView);
/*      */ 
/*      */     public abstract boolean isAttachedToWindow(View paramView);
/*      */   }
/*      */ 
/*      */   @IntDef({0L, 1L})
/*      */   @Retention(RetentionPolicy.SOURCE)
/*      */   private static @interface ResolvedLayoutDirectionMode
/*      */   {
/*      */   }
/*      */ 
/*      */   @IntDef({0L, 1L, 2L, 3L})
/*      */   @Retention(RetentionPolicy.SOURCE)
/*      */   private static @interface LayoutDirectionMode
/*      */   {
/*      */   }
/*      */ 
/*      */   @IntDef({0L, 1L, 2L})
/*      */   @Retention(RetentionPolicy.SOURCE)
/*      */   private static @interface LayerType
/*      */   {
/*      */   }
/*      */ 
/*      */   @IntDef({0L, 1L, 2L})
/*      */   @Retention(RetentionPolicy.SOURCE)
/*      */   private static @interface AccessibilityLiveRegion
/*      */   {
/*      */   }
/*      */ 
/*      */   @IntDef({0L, 1L, 2L, 4L})
/*      */   @Retention(RetentionPolicy.SOURCE)
/*      */   private static @interface ImportantForAccessibility
/*      */   {
/*      */   }
/*      */ 
/*      */   @IntDef({0L, 1L, 2L})
/*      */   @Retention(RetentionPolicy.SOURCE)
/*      */   private static @interface OverScroll
/*      */   {
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.ViewCompat
 * JD-Core Version:    0.6.2
 */