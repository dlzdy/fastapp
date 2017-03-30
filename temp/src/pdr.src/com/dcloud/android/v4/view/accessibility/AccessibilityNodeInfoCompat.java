/*      */ package com.dcloud.android.v4.view.accessibility;
/*      */ 
/*      */ import android.graphics.Rect;
/*      */ import android.os.Build.VERSION;
/*      */ import android.os.Bundle;
/*      */ import android.view.View;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ 
/*      */ public class AccessibilityNodeInfoCompat
/*      */ {
/*      */   private static final AccessibilityNodeInfoImpl IMPL;
/*      */   private final Object mInfo;
/*      */   public static final int ACTION_FOCUS = 1;
/*      */   public static final int ACTION_CLEAR_FOCUS = 2;
/*      */   public static final int ACTION_SELECT = 4;
/*      */   public static final int ACTION_CLEAR_SELECTION = 8;
/*      */   public static final int ACTION_CLICK = 16;
/*      */   public static final int ACTION_LONG_CLICK = 32;
/*      */   public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
/*      */   public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
/*      */   public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
/*      */   public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
/*      */   public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
/*      */   public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
/*      */   public static final int ACTION_SCROLL_FORWARD = 4096;
/*      */   public static final int ACTION_SCROLL_BACKWARD = 8192;
/*      */   public static final int ACTION_COPY = 16384;
/*      */   public static final int ACTION_PASTE = 32768;
/*      */   public static final int ACTION_CUT = 65536;
/*      */   public static final int ACTION_SET_SELECTION = 131072;
/*      */   public static final int ACTION_EXPAND = 262144;
/*      */   public static final int ACTION_COLLAPSE = 524288;
/*      */   public static final int ACTION_DISMISS = 1048576;
/*      */   public static final int ACTION_SET_TEXT = 2097152;
/*      */   public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
/*      */   public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
/*      */   public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
/*      */   public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
/*      */   public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
/*      */   public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
/*      */   public static final int FOCUS_INPUT = 1;
/*      */   public static final int FOCUS_ACCESSIBILITY = 2;
/*      */   public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
/*      */   public static final int MOVEMENT_GRANULARITY_WORD = 2;
/*      */   public static final int MOVEMENT_GRANULARITY_LINE = 4;
/*      */   public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
/*      */   public static final int MOVEMENT_GRANULARITY_PAGE = 16;
/*      */ 
/*      */   static AccessibilityNodeInfoCompat wrapNonNullInstance(Object paramObject)
/*      */   {
/* 2238 */     if (paramObject != null) {
/* 2239 */       return new AccessibilityNodeInfoCompat(paramObject);
/*      */     }
/* 2241 */     return null;
/*      */   }
/*      */ 
/*      */   public AccessibilityNodeInfoCompat(Object paramObject)
/*      */   {
/* 2251 */     this.mInfo = paramObject;
/*      */   }
/*      */ 
/*      */   public Object getInfo()
/*      */   {
/* 2258 */     return this.mInfo;
/*      */   }
/*      */ 
/*      */   public static AccessibilityNodeInfoCompat obtain(View paramView)
/*      */   {
/* 2269 */     return wrapNonNullInstance(IMPL.obtain(paramView));
/*      */   }
/*      */ 
/*      */   public static AccessibilityNodeInfoCompat obtain(View paramView, int paramInt)
/*      */   {
/* 2283 */     return wrapNonNullInstance(IMPL.obtain(paramView, paramInt));
/*      */   }
/*      */ 
/*      */   public static AccessibilityNodeInfoCompat obtain()
/*      */   {
/* 2293 */     return wrapNonNullInstance(IMPL.obtain());
/*      */   }
/*      */ 
/*      */   public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
/*      */   {
/* 2304 */     return wrapNonNullInstance(IMPL.obtain(paramAccessibilityNodeInfoCompat.mInfo));
/*      */   }
/*      */ 
/*      */   public void setSource(View paramView)
/*      */   {
/* 2313 */     IMPL.setSource(this.mInfo, paramView);
/*      */   }
/*      */ 
/*      */   public void setSource(View paramView, int paramInt)
/*      */   {
/* 2336 */     IMPL.setSource(this.mInfo, paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public AccessibilityNodeInfoCompat findFocus(int paramInt)
/*      */   {
/* 2351 */     return wrapNonNullInstance(IMPL.findFocus(this.mInfo, paramInt));
/*      */   }
/*      */ 
/*      */   public AccessibilityNodeInfoCompat focusSearch(int paramInt)
/*      */   {
/* 2369 */     return wrapNonNullInstance(IMPL.focusSearch(this.mInfo, paramInt));
/*      */   }
/*      */ 
/*      */   public int getWindowId()
/*      */   {
/* 2378 */     return IMPL.getWindowId(this.mInfo);
/*      */   }
/*      */ 
/*      */   public int getChildCount()
/*      */   {
/* 2387 */     return IMPL.getChildCount(this.mInfo);
/*      */   }
/*      */ 
/*      */   public AccessibilityNodeInfoCompat getChild(int paramInt)
/*      */   {
/* 2404 */     return wrapNonNullInstance(IMPL.getChild(this.mInfo, paramInt));
/*      */   }
/*      */ 
/*      */   public void addChild(View paramView)
/*      */   {
/* 2419 */     IMPL.addChild(this.mInfo, paramView);
/*      */   }
/*      */ 
/*      */   public void addChild(View paramView, int paramInt)
/*      */   {
/* 2437 */     IMPL.addChild(this.mInfo, paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public boolean removeChild(View paramView)
/*      */   {
/* 2455 */     return IMPL.removeChild(this.mInfo, paramView);
/*      */   }
/*      */ 
/*      */   public boolean removeChild(View paramView, int paramInt)
/*      */   {
/* 2469 */     return IMPL.removeChild(this.mInfo, paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public int getActions()
/*      */   {
/* 2482 */     return IMPL.getActions(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void addAction(int paramInt)
/*      */   {
/* 2497 */     IMPL.addAction(this.mInfo, paramInt);
/*      */   }
/*      */ 
/*      */   public void addAction(AccessibilityActionCompat paramAccessibilityActionCompat)
/*      */   {
/* 2512 */     IMPL.addAction(this.mInfo, paramAccessibilityActionCompat.mAction);
/*      */   }
/*      */ 
/*      */   public boolean removeAction(AccessibilityActionCompat paramAccessibilityActionCompat)
/*      */   {
/* 2530 */     return IMPL.removeAction(this.mInfo, paramAccessibilityActionCompat.mAction);
/*      */   }
/*      */ 
/*      */   public boolean performAction(int paramInt)
/*      */   {
/* 2546 */     return IMPL.performAction(this.mInfo, paramInt);
/*      */   }
/*      */ 
/*      */   public boolean performAction(int paramInt, Bundle paramBundle)
/*      */   {
/* 2563 */     return IMPL.performAction(this.mInfo, paramInt, paramBundle);
/*      */   }
/*      */ 
/*      */   public void setMovementGranularities(int paramInt)
/*      */   {
/* 2579 */     IMPL.setMovementGranularities(this.mInfo, paramInt);
/*      */   }
/*      */ 
/*      */   public int getMovementGranularities()
/*      */   {
/* 2588 */     return IMPL.getMovementGranularities(this.mInfo);
/*      */   }
/*      */ 
/*      */   public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(String paramString)
/*      */   {
/* 2605 */     ArrayList localArrayList = new ArrayList();
/* 2606 */     List localList = IMPL.findAccessibilityNodeInfosByText(this.mInfo, paramString);
/* 2607 */     int i = localList.size();
/* 2608 */     for (int j = 0; j < i; j++) {
/* 2609 */       Object localObject = localList.get(j);
/* 2610 */       localArrayList.add(new AccessibilityNodeInfoCompat(localObject));
/*      */     }
/* 2612 */     return localArrayList;
/*      */   }
/*      */ 
/*      */   public AccessibilityNodeInfoCompat getParent()
/*      */   {
/* 2626 */     return wrapNonNullInstance(IMPL.getParent(this.mInfo));
/*      */   }
/*      */ 
/*      */   public void setParent(View paramView)
/*      */   {
/* 2641 */     IMPL.setParent(this.mInfo, paramView);
/*      */   }
/*      */ 
/*      */   public void setParent(View paramView, int paramInt)
/*      */   {
/* 2664 */     IMPL.setParent(this.mInfo, paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public void getBoundsInParent(Rect paramRect)
/*      */   {
/* 2673 */     IMPL.getBoundsInParent(this.mInfo, paramRect);
/*      */   }
/*      */ 
/*      */   public void setBoundsInParent(Rect paramRect)
/*      */   {
/* 2688 */     IMPL.setBoundsInParent(this.mInfo, paramRect);
/*      */   }
/*      */ 
/*      */   public void getBoundsInScreen(Rect paramRect)
/*      */   {
/* 2697 */     IMPL.getBoundsInScreen(this.mInfo, paramRect);
/*      */   }
/*      */ 
/*      */   public void setBoundsInScreen(Rect paramRect)
/*      */   {
/* 2712 */     IMPL.setBoundsInScreen(this.mInfo, paramRect);
/*      */   }
/*      */ 
/*      */   public boolean isCheckable()
/*      */   {
/* 2721 */     return IMPL.isCheckable(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setCheckable(boolean paramBoolean)
/*      */   {
/* 2736 */     IMPL.setCheckable(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isChecked()
/*      */   {
/* 2745 */     return IMPL.isChecked(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setChecked(boolean paramBoolean)
/*      */   {
/* 2760 */     IMPL.setChecked(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isFocusable()
/*      */   {
/* 2769 */     return IMPL.isFocusable(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setFocusable(boolean paramBoolean)
/*      */   {
/* 2784 */     IMPL.setFocusable(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isFocused()
/*      */   {
/* 2793 */     return IMPL.isFocused(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setFocused(boolean paramBoolean)
/*      */   {
/* 2808 */     IMPL.setFocused(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isVisibleToUser()
/*      */   {
/* 2817 */     return IMPL.isVisibleToUser(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setVisibleToUser(boolean paramBoolean)
/*      */   {
/* 2833 */     IMPL.setVisibleToUser(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isAccessibilityFocused()
/*      */   {
/* 2842 */     return IMPL.isAccessibilityFocused(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setAccessibilityFocused(boolean paramBoolean)
/*      */   {
/* 2858 */     IMPL.setAccessibilityFocused(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isSelected()
/*      */   {
/* 2867 */     return IMPL.isSelected(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setSelected(boolean paramBoolean)
/*      */   {
/* 2882 */     IMPL.setSelected(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isClickable()
/*      */   {
/* 2891 */     return IMPL.isClickable(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setClickable(boolean paramBoolean)
/*      */   {
/* 2906 */     IMPL.setClickable(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isLongClickable()
/*      */   {
/* 2915 */     return IMPL.isLongClickable(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setLongClickable(boolean paramBoolean)
/*      */   {
/* 2930 */     IMPL.setLongClickable(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isEnabled()
/*      */   {
/* 2939 */     return IMPL.isEnabled(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setEnabled(boolean paramBoolean)
/*      */   {
/* 2954 */     IMPL.setEnabled(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isPassword()
/*      */   {
/* 2963 */     return IMPL.isPassword(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setPassword(boolean paramBoolean)
/*      */   {
/* 2978 */     IMPL.setPassword(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isScrollable()
/*      */   {
/* 2987 */     return IMPL.isScrollable(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setScrollable(boolean paramBoolean)
/*      */   {
/* 3002 */     IMPL.setScrollable(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public CharSequence getPackageName()
/*      */   {
/* 3011 */     return IMPL.getPackageName(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setPackageName(CharSequence paramCharSequence)
/*      */   {
/* 3026 */     IMPL.setPackageName(this.mInfo, paramCharSequence);
/*      */   }
/*      */ 
/*      */   public CharSequence getClassName()
/*      */   {
/* 3035 */     return IMPL.getClassName(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setClassName(CharSequence paramCharSequence)
/*      */   {
/* 3050 */     IMPL.setClassName(this.mInfo, paramCharSequence);
/*      */   }
/*      */ 
/*      */   public CharSequence getText()
/*      */   {
/* 3059 */     return IMPL.getText(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setText(CharSequence paramCharSequence)
/*      */   {
/* 3074 */     IMPL.setText(this.mInfo, paramCharSequence);
/*      */   }
/*      */ 
/*      */   public CharSequence getContentDescription()
/*      */   {
/* 3083 */     return IMPL.getContentDescription(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setContentDescription(CharSequence paramCharSequence)
/*      */   {
/* 3098 */     IMPL.setContentDescription(this.mInfo, paramCharSequence);
/*      */   }
/*      */ 
/*      */   public void recycle()
/*      */   {
/* 3109 */     IMPL.recycle(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setViewIdResourceName(String paramString)
/*      */   {
/* 3124 */     IMPL.setViewIdResourceName(this.mInfo, paramString);
/*      */   }
/*      */ 
/*      */   public String getViewIdResourceName()
/*      */   {
/* 3140 */     return IMPL.getViewIdResourceName(this.mInfo);
/*      */   }
/*      */ 
/*      */   public int getLiveRegion()
/*      */   {
/* 3162 */     return IMPL.getLiveRegion(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setLiveRegion(int paramInt)
/*      */   {
/* 3178 */     IMPL.setLiveRegion(this.mInfo, paramInt);
/*      */   }
/*      */ 
/*      */   public CollectionInfoCompat getCollectionInfo()
/*      */   {
/* 3188 */     Object localObject = IMPL.getCollectionInfo(this.mInfo);
/* 3189 */     if (localObject == null) return null;
/* 3190 */     return new CollectionInfoCompat(localObject, null);
/*      */   }
/*      */ 
/*      */   public void setCollectionInfo(Object paramObject) {
/* 3194 */     IMPL.setCollectionInfo(this.mInfo, ((CollectionInfoCompat)paramObject).mInfo);
/*      */   }
/*      */ 
/*      */   public void setCollectionItemInfo(Object paramObject) {
/* 3198 */     IMPL.setCollectionItemInfo(this.mInfo, ((CollectionItemInfoCompat)paramObject).mInfo);
/*      */   }
/*      */ 
/*      */   public CollectionItemInfoCompat getCollectionItemInfo()
/*      */   {
/* 3208 */     Object localObject = IMPL.getCollectionItemInfo(this.mInfo);
/* 3209 */     if (localObject == null) return null;
/* 3210 */     return new CollectionItemInfoCompat(localObject, null);
/*      */   }
/*      */ 
/*      */   public RangeInfoCompat getRangeInfo()
/*      */   {
/* 3219 */     Object localObject = IMPL.getRangeInfo(this.mInfo);
/* 3220 */     if (localObject == null) return null;
/* 3221 */     return new RangeInfoCompat(localObject, null);
/*      */   }
/*      */ 
/*      */   public void setRangeInfo(RangeInfoCompat paramRangeInfoCompat)
/*      */   {
/* 3235 */     IMPL.setRangeInfo(this.mInfo, paramRangeInfoCompat.mInfo);
/*      */   }
/*      */ 
/*      */   public List<AccessibilityActionCompat> getActionList()
/*      */   {
/* 3244 */     List localList = IMPL.getActionList(this.mInfo);
/* 3245 */     if (localList != null) {
/* 3246 */       ArrayList localArrayList = new ArrayList();
/* 3247 */       int i = localList.size();
/* 3248 */       for (int j = 0; j < i; j++) {
/* 3249 */         Object localObject = localList.get(j);
/* 3250 */         localArrayList.add(new AccessibilityActionCompat(localObject, null));
/*      */       }
/* 3252 */       return localArrayList;
/*      */     }
/* 3254 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */   public void setContentInvalid(boolean paramBoolean)
/*      */   {
/* 3270 */     IMPL.setContentInvalid(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isContentInvalid()
/*      */   {
/* 3280 */     return IMPL.isContentInvalid(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setError(CharSequence paramCharSequence)
/*      */   {
/* 3296 */     IMPL.setError(this.mInfo, paramCharSequence);
/*      */   }
/*      */ 
/*      */   public CharSequence getError()
/*      */   {
/* 3305 */     return IMPL.getError(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setLabelFor(View paramView)
/*      */   {
/* 3315 */     IMPL.setLabelFor(this.mInfo, paramView);
/*      */   }
/*      */ 
/*      */   public void setLabelFor(View paramView, int paramInt)
/*      */   {
/* 3333 */     IMPL.setLabelFor(this.mInfo, paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public AccessibilityNodeInfoCompat getLabelFor()
/*      */   {
/* 3348 */     return wrapNonNullInstance(IMPL.getLabelFor(this.mInfo));
/*      */   }
/*      */ 
/*      */   public void setLabeledBy(View paramView)
/*      */   {
/* 3358 */     IMPL.setLabeledBy(this.mInfo, paramView);
/*      */   }
/*      */ 
/*      */   public void setLabeledBy(View paramView, int paramInt)
/*      */   {
/* 3381 */     IMPL.setLabeledBy(this.mInfo, paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public AccessibilityNodeInfoCompat getLabeledBy()
/*      */   {
/* 3396 */     return wrapNonNullInstance(IMPL.getLabeledBy(this.mInfo));
/*      */   }
/*      */ 
/*      */   public boolean canOpenPopup()
/*      */   {
/* 3405 */     return IMPL.canOpenPopup(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setCanOpenPopup(boolean paramBoolean)
/*      */   {
/* 3419 */     IMPL.setCanOpenPopup(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByViewId(String paramString)
/*      */   {
/* 3445 */     List localList = IMPL.findAccessibilityNodeInfosByViewId(this.mInfo, paramString);
/* 3446 */     if (localList != null) {
/* 3447 */       ArrayList localArrayList = new ArrayList();
/* 3448 */       for (Iterator localIterator = localList.iterator(); localIterator.hasNext(); ) { Object localObject = localIterator.next();
/* 3449 */         localArrayList.add(new AccessibilityNodeInfoCompat(localObject));
/*      */       }
/* 3451 */       return localArrayList;
/*      */     }
/* 3453 */     return Collections.emptyList();
/*      */   }
/*      */ 
/*      */   public Bundle getExtras()
/*      */   {
/* 3471 */     return IMPL.getExtras(this.mInfo);
/*      */   }
/*      */ 
/*      */   public int getInputType()
/*      */   {
/* 3480 */     return IMPL.getInputType(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setInputType(int paramInt)
/*      */   {
/* 3497 */     IMPL.setInputType(this.mInfo, paramInt);
/*      */   }
/*      */ 
/*      */   public void setMaxTextLength(int paramInt)
/*      */   {
/* 3516 */     IMPL.setMaxTextLength(this.mInfo, paramInt);
/*      */   }
/*      */ 
/*      */   public int getMaxTextLength()
/*      */   {
/* 3526 */     return IMPL.getMaxTextLength(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setTextSelection(int paramInt1, int paramInt2)
/*      */   {
/* 3543 */     IMPL.setTextSelection(this.mInfo, paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */   public int getTextSelectionStart()
/*      */   {
/* 3552 */     return IMPL.getTextSelectionStart(this.mInfo);
/*      */   }
/*      */ 
/*      */   public int getTextSelectionEnd()
/*      */   {
/* 3561 */     return IMPL.getTextSelectionEnd(this.mInfo);
/*      */   }
/*      */ 
/*      */   public AccessibilityNodeInfoCompat getTraversalBefore()
/*      */   {
/* 3574 */     return wrapNonNullInstance(IMPL.getTraversalBefore(this.mInfo));
/*      */   }
/*      */ 
/*      */   public void setTraversalBefore(View paramView)
/*      */   {
/* 3592 */     IMPL.setTraversalBefore(this.mInfo, paramView);
/*      */   }
/*      */ 
/*      */   public void setTraversalBefore(View paramView, int paramInt)
/*      */   {
/* 3617 */     IMPL.setTraversalBefore(this.mInfo, paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public AccessibilityNodeInfoCompat getTraversalAfter()
/*      */   {
/* 3631 */     return wrapNonNullInstance(IMPL.getTraversalAfter(this.mInfo));
/*      */   }
/*      */ 
/*      */   public void setTraversalAfter(View paramView)
/*      */   {
/* 3649 */     IMPL.setTraversalAfter(this.mInfo, paramView);
/*      */   }
/*      */ 
/*      */   public void setTraversalAfter(View paramView, int paramInt)
/*      */   {
/* 3673 */     IMPL.setTraversalAfter(this.mInfo, paramView, paramInt);
/*      */   }
/*      */ 
/*      */   public AccessibilityWindowInfoCompat getWindow()
/*      */   {
/* 3684 */     return AccessibilityWindowInfoCompat.wrapNonNullInstance(IMPL.getWindow(this.mInfo));
/*      */   }
/*      */ 
/*      */   public boolean isDismissable()
/*      */   {
/* 3693 */     return IMPL.isDismissable(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setDismissable(boolean paramBoolean)
/*      */   {
/* 3707 */     IMPL.setDismissable(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isEditable()
/*      */   {
/* 3716 */     return IMPL.isEditable(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setEditable(boolean paramBoolean)
/*      */   {
/* 3732 */     IMPL.setEditable(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean isMultiLine()
/*      */   {
/* 3741 */     return IMPL.isMultiLine(this.mInfo);
/*      */   }
/*      */ 
/*      */   public void setMultiLine(boolean paramBoolean)
/*      */   {
/* 3755 */     IMPL.setMultiLine(this.mInfo, paramBoolean);
/*      */   }
/*      */ 
/*      */   public boolean refresh()
/*      */   {
/* 3768 */     return IMPL.refresh(this.mInfo);
/*      */   }
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 3773 */     return this.mInfo == null ? 0 : this.mInfo.hashCode();
/*      */   }
/*      */ 
/*      */   public boolean equals(Object paramObject)
/*      */   {
/* 3778 */     if (this == paramObject) {
/* 3779 */       return true;
/*      */     }
/* 3781 */     if (paramObject == null) {
/* 3782 */       return false;
/*      */     }
/* 3784 */     if (getClass() != paramObject.getClass()) {
/* 3785 */       return false;
/*      */     }
/* 3787 */     AccessibilityNodeInfoCompat localAccessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat)paramObject;
/* 3788 */     if (this.mInfo == null) {
/* 3789 */       if (localAccessibilityNodeInfoCompat.mInfo != null)
/* 3790 */         return false;
/*      */     }
/* 3792 */     else if (!this.mInfo.equals(localAccessibilityNodeInfoCompat.mInfo)) {
/* 3793 */       return false;
/*      */     }
/* 3795 */     return true;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 3800 */     StringBuilder localStringBuilder = new StringBuilder();
/* 3801 */     localStringBuilder.append(super.toString());
/*      */ 
/* 3803 */     Rect localRect = new Rect();
/*      */ 
/* 3805 */     getBoundsInParent(localRect);
/* 3806 */     localStringBuilder.append("; boundsInParent: " + localRect);
/*      */ 
/* 3808 */     getBoundsInScreen(localRect);
/* 3809 */     localStringBuilder.append("; boundsInScreen: " + localRect);
/*      */ 
/* 3811 */     localStringBuilder.append("; packageName: ").append(getPackageName());
/* 3812 */     localStringBuilder.append("; className: ").append(getClassName());
/* 3813 */     localStringBuilder.append("; text: ").append(getText());
/* 3814 */     localStringBuilder.append("; contentDescription: ").append(getContentDescription());
/* 3815 */     localStringBuilder.append("; viewId: ").append(getViewIdResourceName());
/*      */ 
/* 3817 */     localStringBuilder.append("; checkable: ").append(isCheckable());
/* 3818 */     localStringBuilder.append("; checked: ").append(isChecked());
/* 3819 */     localStringBuilder.append("; focusable: ").append(isFocusable());
/* 3820 */     localStringBuilder.append("; focused: ").append(isFocused());
/* 3821 */     localStringBuilder.append("; selected: ").append(isSelected());
/* 3822 */     localStringBuilder.append("; clickable: ").append(isClickable());
/* 3823 */     localStringBuilder.append("; longClickable: ").append(isLongClickable());
/* 3824 */     localStringBuilder.append("; enabled: ").append(isEnabled());
/* 3825 */     localStringBuilder.append("; password: ").append(isPassword());
/* 3826 */     localStringBuilder.append("; scrollable: " + isScrollable());
/*      */ 
/* 3828 */     localStringBuilder.append("; [");
/* 3829 */     for (int i = getActions(); i != 0; ) {
/* 3830 */       int j = 1 << Integer.numberOfTrailingZeros(i);
/* 3831 */       i &= (j ^ 0xFFFFFFFF);
/* 3832 */       localStringBuilder.append(getActionSymbolicName(j));
/* 3833 */       if (i != 0) {
/* 3834 */         localStringBuilder.append(", ");
/*      */       }
/*      */     }
/* 3837 */     localStringBuilder.append("]");
/*      */ 
/* 3839 */     return localStringBuilder.toString();
/*      */   }
/*      */ 
/*      */   private static String getActionSymbolicName(int paramInt) {
/* 3843 */     switch (paramInt) {
/*      */     case 1:
/* 3845 */       return "ACTION_FOCUS";
/*      */     case 2:
/* 3847 */       return "ACTION_CLEAR_FOCUS";
/*      */     case 4:
/* 3849 */       return "ACTION_SELECT";
/*      */     case 8:
/* 3851 */       return "ACTION_CLEAR_SELECTION";
/*      */     case 16:
/* 3853 */       return "ACTION_CLICK";
/*      */     case 32:
/* 3855 */       return "ACTION_LONG_CLICK";
/*      */     case 64:
/* 3857 */       return "ACTION_ACCESSIBILITY_FOCUS";
/*      */     case 128:
/* 3859 */       return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
/*      */     case 256:
/* 3861 */       return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
/*      */     case 512:
/* 3863 */       return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
/*      */     case 1024:
/* 3865 */       return "ACTION_NEXT_HTML_ELEMENT";
/*      */     case 2048:
/* 3867 */       return "ACTION_PREVIOUS_HTML_ELEMENT";
/*      */     case 4096:
/* 3869 */       return "ACTION_SCROLL_FORWARD";
/*      */     case 8192:
/* 3871 */       return "ACTION_SCROLL_BACKWARD";
/*      */     case 65536:
/* 3873 */       return "ACTION_CUT";
/*      */     case 16384:
/* 3875 */       return "ACTION_COPY";
/*      */     case 32768:
/* 3877 */       return "ACTION_PASTE";
/*      */     case 131072:
/* 3879 */       return "ACTION_SET_SELECTION";
/*      */     }
/* 3881 */     return "ACTION_UNKNOWN";
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/* 1877 */     if (Build.VERSION.SDK_INT >= 22)
/* 1878 */       IMPL = new AccessibilityNodeInfoApi22Impl();
/* 1879 */     else if (Build.VERSION.SDK_INT >= 21)
/* 1880 */       IMPL = new AccessibilityNodeInfoApi21Impl();
/* 1881 */     else if (Build.VERSION.SDK_INT >= 19)
/* 1882 */       IMPL = new AccessibilityNodeInfoKitKatImpl();
/* 1883 */     else if (Build.VERSION.SDK_INT >= 18)
/* 1884 */       IMPL = new AccessibilityNodeInfoJellybeanMr2Impl();
/* 1885 */     else if (Build.VERSION.SDK_INT >= 17)
/* 1886 */       IMPL = new AccessibilityNodeInfoJellybeanMr1Impl();
/* 1887 */     else if (Build.VERSION.SDK_INT >= 16)
/* 1888 */       IMPL = new AccessibilityNodeInfoJellybeanImpl();
/* 1889 */     else if (Build.VERSION.SDK_INT >= 14)
/* 1890 */       IMPL = new AccessibilityNodeInfoIcsImpl();
/*      */     else
/* 1892 */       IMPL = new AccessibilityNodeInfoStubImpl();
/*      */   }
/*      */ 
/*      */   static class AccessibilityNodeInfoApi22Impl extends AccessibilityNodeInfoCompat.AccessibilityNodeInfoApi21Impl
/*      */   {
/*      */     public Object getTraversalBefore(Object paramObject)
/*      */     {
/* 1847 */       return AccessibilityNodeInfoCompatApi22.getTraversalBefore(paramObject);
/*      */     }
/*      */ 
/*      */     public void setTraversalBefore(Object paramObject, View paramView)
/*      */     {
/* 1852 */       AccessibilityNodeInfoCompatApi22.setTraversalBefore(paramObject, paramView);
/*      */     }
/*      */ 
/*      */     public void setTraversalBefore(Object paramObject, View paramView, int paramInt)
/*      */     {
/* 1857 */       AccessibilityNodeInfoCompatApi22.setTraversalBefore(paramObject, paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public Object getTraversalAfter(Object paramObject)
/*      */     {
/* 1862 */       return AccessibilityNodeInfoCompatApi22.getTraversalAfter(paramObject);
/*      */     }
/*      */ 
/*      */     public void setTraversalAfter(Object paramObject, View paramView)
/*      */     {
/* 1867 */       AccessibilityNodeInfoCompatApi22.setTraversalAfter(paramObject, paramView);
/*      */     }
/*      */ 
/*      */     public void setTraversalAfter(Object paramObject, View paramView, int paramInt)
/*      */     {
/* 1872 */       AccessibilityNodeInfoCompatApi22.setTraversalAfter(paramObject, paramView, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class AccessibilityNodeInfoApi21Impl extends AccessibilityNodeInfoCompat.AccessibilityNodeInfoKitKatImpl
/*      */   {
/*      */     public Object newAccessibilityAction(int paramInt, CharSequence paramCharSequence)
/*      */     {
/* 1761 */       return AccessibilityNodeInfoCompatApi21.newAccessibilityAction(paramInt, paramCharSequence);
/*      */     }
/*      */ 
/*      */     public List<Object> getActionList(Object paramObject)
/*      */     {
/* 1766 */       return AccessibilityNodeInfoCompatApi21.getActionList(paramObject);
/*      */     }
/*      */ 
/*      */     public Object obtainCollectionInfo(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
/*      */     {
/* 1772 */       return AccessibilityNodeInfoCompatApi21.obtainCollectionInfo(paramInt1, paramInt2, paramBoolean, paramInt3);
/*      */     }
/*      */ 
/*      */     public void addAction(Object paramObject1, Object paramObject2)
/*      */     {
/* 1778 */       AccessibilityNodeInfoCompatApi21.addAction(paramObject1, paramObject2);
/*      */     }
/*      */ 
/*      */     public boolean removeAction(Object paramObject1, Object paramObject2)
/*      */     {
/* 1783 */       return AccessibilityNodeInfoCompatApi21.removeAction(paramObject1, paramObject2);
/*      */     }
/*      */ 
/*      */     public int getAccessibilityActionId(Object paramObject)
/*      */     {
/* 1788 */       return AccessibilityNodeInfoCompatApi21.getAccessibilityActionId(paramObject);
/*      */     }
/*      */ 
/*      */     public CharSequence getAccessibilityActionLabel(Object paramObject)
/*      */     {
/* 1793 */       return AccessibilityNodeInfoCompatApi21.getAccessibilityActionLabel(paramObject);
/*      */     }
/*      */ 
/*      */     public Object obtainCollectionItemInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
/*      */     {
/* 1799 */       return AccessibilityNodeInfoCompatApi21.obtainCollectionItemInfo(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1, paramBoolean2);
/*      */     }
/*      */ 
/*      */     public boolean isCollectionItemSelected(Object paramObject)
/*      */     {
/* 1805 */       return AccessibilityNodeInfoCompatApi21.CollectionItemInfo.isSelected(paramObject);
/*      */     }
/*      */ 
/*      */     public CharSequence getError(Object paramObject)
/*      */     {
/* 1810 */       return AccessibilityNodeInfoCompatApi21.getError(paramObject);
/*      */     }
/*      */ 
/*      */     public void setError(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/* 1815 */       AccessibilityNodeInfoCompatApi21.setError(paramObject, paramCharSequence);
/*      */     }
/*      */ 
/*      */     public void setMaxTextLength(Object paramObject, int paramInt)
/*      */     {
/* 1820 */       AccessibilityNodeInfoCompatApi21.setMaxTextLength(paramObject, paramInt);
/*      */     }
/*      */ 
/*      */     public int getMaxTextLength(Object paramObject)
/*      */     {
/* 1825 */       return AccessibilityNodeInfoCompatApi21.getMaxTextLength(paramObject);
/*      */     }
/*      */ 
/*      */     public Object getWindow(Object paramObject)
/*      */     {
/* 1830 */       return AccessibilityNodeInfoCompatApi21.getWindow(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean removeChild(Object paramObject, View paramView)
/*      */     {
/* 1835 */       return AccessibilityNodeInfoCompatApi21.removeChild(paramObject, paramView);
/*      */     }
/*      */ 
/*      */     public boolean removeChild(Object paramObject, View paramView, int paramInt)
/*      */     {
/* 1840 */       return AccessibilityNodeInfoCompatApi21.removeChild(paramObject, paramView, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class AccessibilityNodeInfoKitKatImpl extends AccessibilityNodeInfoCompat.AccessibilityNodeInfoJellybeanMr2Impl
/*      */   {
/*      */     public int getLiveRegion(Object paramObject)
/*      */     {
/* 1610 */       return AccessibilityNodeInfoCompatKitKat.getLiveRegion(paramObject);
/*      */     }
/*      */ 
/*      */     public void setLiveRegion(Object paramObject, int paramInt)
/*      */     {
/* 1615 */       AccessibilityNodeInfoCompatKitKat.setLiveRegion(paramObject, paramInt);
/*      */     }
/*      */ 
/*      */     public Object getCollectionInfo(Object paramObject)
/*      */     {
/* 1620 */       return AccessibilityNodeInfoCompatKitKat.getCollectionInfo(paramObject);
/*      */     }
/*      */ 
/*      */     public void setCollectionInfo(Object paramObject1, Object paramObject2)
/*      */     {
/* 1625 */       AccessibilityNodeInfoCompatKitKat.setCollectionInfo(paramObject1, paramObject2);
/*      */     }
/*      */ 
/*      */     public Object obtainCollectionInfo(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
/*      */     {
/* 1631 */       return AccessibilityNodeInfoCompatKitKat.obtainCollectionInfo(paramInt1, paramInt2, paramBoolean, paramInt3);
/*      */     }
/*      */ 
/*      */     public Object obtainCollectionItemInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
/*      */     {
/* 1638 */       return AccessibilityNodeInfoCompatKitKat.obtainCollectionItemInfo(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1);
/*      */     }
/*      */ 
/*      */     public int getCollectionInfoColumnCount(Object paramObject)
/*      */     {
/* 1644 */       return AccessibilityNodeInfoCompatKitKat.CollectionInfo.getColumnCount(paramObject);
/*      */     }
/*      */ 
/*      */     public int getCollectionInfoRowCount(Object paramObject)
/*      */     {
/* 1649 */       return AccessibilityNodeInfoCompatKitKat.CollectionInfo.getRowCount(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isCollectionInfoHierarchical(Object paramObject)
/*      */     {
/* 1654 */       return AccessibilityNodeInfoCompatKitKat.CollectionInfo.isHierarchical(paramObject);
/*      */     }
/*      */ 
/*      */     public Object getCollectionItemInfo(Object paramObject)
/*      */     {
/* 1659 */       return AccessibilityNodeInfoCompatKitKat.getCollectionItemInfo(paramObject);
/*      */     }
/*      */ 
/*      */     public Object getRangeInfo(Object paramObject)
/*      */     {
/* 1664 */       return AccessibilityNodeInfoCompatKitKat.getRangeInfo(paramObject);
/*      */     }
/*      */ 
/*      */     public void setRangeInfo(Object paramObject1, Object paramObject2)
/*      */     {
/* 1669 */       AccessibilityNodeInfoCompatKitKat.setRangeInfo(paramObject1, paramObject2);
/*      */     }
/*      */ 
/*      */     public int getCollectionItemColumnIndex(Object paramObject)
/*      */     {
/* 1674 */       return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.getColumnIndex(paramObject);
/*      */     }
/*      */ 
/*      */     public int getCollectionItemColumnSpan(Object paramObject)
/*      */     {
/* 1679 */       return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.getColumnSpan(paramObject);
/*      */     }
/*      */ 
/*      */     public int getCollectionItemRowIndex(Object paramObject)
/*      */     {
/* 1684 */       return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.getRowIndex(paramObject);
/*      */     }
/*      */ 
/*      */     public int getCollectionItemRowSpan(Object paramObject)
/*      */     {
/* 1689 */       return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.getRowSpan(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isCollectionItemHeading(Object paramObject)
/*      */     {
/* 1694 */       return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.isHeading(paramObject);
/*      */     }
/*      */ 
/*      */     public void setCollectionItemInfo(Object paramObject1, Object paramObject2)
/*      */     {
/* 1699 */       AccessibilityNodeInfoCompatKitKat.setCollectionItemInfo(paramObject1, paramObject2);
/*      */     }
/*      */ 
/*      */     public void setContentInvalid(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1704 */       AccessibilityNodeInfoCompatKitKat.setContentInvalid(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public boolean isContentInvalid(Object paramObject)
/*      */     {
/* 1709 */       return AccessibilityNodeInfoCompatKitKat.isContentInvalid(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean canOpenPopup(Object paramObject)
/*      */     {
/* 1714 */       return AccessibilityNodeInfoCompatKitKat.canOpenPopup(paramObject);
/*      */     }
/*      */ 
/*      */     public void setCanOpenPopup(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1719 */       AccessibilityNodeInfoCompatKitKat.setCanOpenPopup(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public Bundle getExtras(Object paramObject)
/*      */     {
/* 1724 */       return AccessibilityNodeInfoCompatKitKat.getExtras(paramObject);
/*      */     }
/*      */ 
/*      */     public int getInputType(Object paramObject)
/*      */     {
/* 1729 */       return AccessibilityNodeInfoCompatKitKat.getInputType(paramObject);
/*      */     }
/*      */ 
/*      */     public void setInputType(Object paramObject, int paramInt)
/*      */     {
/* 1734 */       AccessibilityNodeInfoCompatKitKat.setInputType(paramObject, paramInt);
/*      */     }
/*      */ 
/*      */     public boolean isDismissable(Object paramObject)
/*      */     {
/* 1739 */       return AccessibilityNodeInfoCompatKitKat.isDismissable(paramObject);
/*      */     }
/*      */ 
/*      */     public void setDismissable(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1744 */       AccessibilityNodeInfoCompatKitKat.setDismissable(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public boolean isMultiLine(Object paramObject)
/*      */     {
/* 1749 */       return AccessibilityNodeInfoCompatKitKat.isMultiLine(paramObject);
/*      */     }
/*      */ 
/*      */     public void setMultiLine(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1754 */       AccessibilityNodeInfoCompatKitKat.setMultiLine(paramObject, paramBoolean);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class AccessibilityNodeInfoJellybeanMr2Impl extends AccessibilityNodeInfoCompat.AccessibilityNodeInfoJellybeanMr1Impl
/*      */   {
/*      */     public String getViewIdResourceName(Object paramObject)
/*      */     {
/* 1562 */       return AccessibilityNodeInfoCompatJellybeanMr2.getViewIdResourceName(paramObject);
/*      */     }
/*      */ 
/*      */     public void setViewIdResourceName(Object paramObject, String paramString)
/*      */     {
/* 1567 */       AccessibilityNodeInfoCompatJellybeanMr2.setViewIdResourceName(paramObject, paramString);
/*      */     }
/*      */ 
/*      */     public List<Object> findAccessibilityNodeInfosByViewId(Object paramObject, String paramString)
/*      */     {
/* 1572 */       return AccessibilityNodeInfoCompatJellybeanMr2.findAccessibilityNodeInfosByViewId(paramObject, paramString);
/*      */     }
/*      */ 
/*      */     public void setTextSelection(Object paramObject, int paramInt1, int paramInt2)
/*      */     {
/* 1578 */       AccessibilityNodeInfoCompatJellybeanMr2.setTextSelection(paramObject, paramInt1, paramInt2);
/*      */     }
/*      */ 
/*      */     public int getTextSelectionStart(Object paramObject)
/*      */     {
/* 1583 */       return AccessibilityNodeInfoCompatJellybeanMr2.getTextSelectionStart(paramObject);
/*      */     }
/*      */ 
/*      */     public int getTextSelectionEnd(Object paramObject)
/*      */     {
/* 1588 */       return AccessibilityNodeInfoCompatJellybeanMr2.getTextSelectionEnd(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isEditable(Object paramObject)
/*      */     {
/* 1593 */       return AccessibilityNodeInfoCompatJellybeanMr2.isEditable(paramObject);
/*      */     }
/*      */ 
/*      */     public void setEditable(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1598 */       AccessibilityNodeInfoCompatJellybeanMr2.setEditable(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public boolean refresh(Object paramObject)
/*      */     {
/* 1603 */       return AccessibilityNodeInfoCompatJellybeanMr2.refresh(paramObject);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class AccessibilityNodeInfoJellybeanMr1Impl extends AccessibilityNodeInfoCompat.AccessibilityNodeInfoJellybeanImpl
/*      */   {
/*      */     public void setLabelFor(Object paramObject, View paramView)
/*      */     {
/* 1528 */       AccessibilityNodeInfoCompatJellybeanMr1.setLabelFor(paramObject, paramView);
/*      */     }
/*      */ 
/*      */     public void setLabelFor(Object paramObject, View paramView, int paramInt)
/*      */     {
/* 1533 */       AccessibilityNodeInfoCompatJellybeanMr1.setLabelFor(paramObject, paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public Object getLabelFor(Object paramObject)
/*      */     {
/* 1538 */       return AccessibilityNodeInfoCompatJellybeanMr1.getLabelFor(paramObject);
/*      */     }
/*      */ 
/*      */     public void setLabeledBy(Object paramObject, View paramView)
/*      */     {
/* 1543 */       AccessibilityNodeInfoCompatJellybeanMr1.setLabeledBy(paramObject, paramView);
/*      */     }
/*      */ 
/*      */     public void setLabeledBy(Object paramObject, View paramView, int paramInt)
/*      */     {
/* 1548 */       AccessibilityNodeInfoCompatJellybeanMr1.setLabeledBy(paramObject, paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public Object getLabeledBy(Object paramObject)
/*      */     {
/* 1553 */       return AccessibilityNodeInfoCompatJellybeanMr1.getLabeledBy(paramObject);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class AccessibilityNodeInfoJellybeanImpl extends AccessibilityNodeInfoCompat.AccessibilityNodeInfoIcsImpl
/*      */   {
/*      */     public Object obtain(View paramView, int paramInt)
/*      */     {
/* 1460 */       return AccessibilityNodeInfoCompatJellyBean.obtain(paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public Object findFocus(Object paramObject, int paramInt)
/*      */     {
/* 1465 */       return AccessibilityNodeInfoCompatJellyBean.findFocus(paramObject, paramInt);
/*      */     }
/*      */ 
/*      */     public Object focusSearch(Object paramObject, int paramInt)
/*      */     {
/* 1470 */       return AccessibilityNodeInfoCompatJellyBean.focusSearch(paramObject, paramInt);
/*      */     }
/*      */ 
/*      */     public void addChild(Object paramObject, View paramView, int paramInt)
/*      */     {
/* 1475 */       AccessibilityNodeInfoCompatJellyBean.addChild(paramObject, paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public void setSource(Object paramObject, View paramView, int paramInt)
/*      */     {
/* 1480 */       AccessibilityNodeInfoCompatJellyBean.setSource(paramObject, paramView, paramInt);
/*      */     }
/*      */ 
/*      */     public boolean isVisibleToUser(Object paramObject)
/*      */     {
/* 1485 */       return AccessibilityNodeInfoCompatJellyBean.isVisibleToUser(paramObject);
/*      */     }
/*      */ 
/*      */     public void setVisibleToUser(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1490 */       AccessibilityNodeInfoCompatJellyBean.setVisibleToUser(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public boolean isAccessibilityFocused(Object paramObject)
/*      */     {
/* 1495 */       return AccessibilityNodeInfoCompatJellyBean.isAccessibilityFocused(paramObject);
/*      */     }
/*      */ 
/*      */     public void setAccessibilityFocused(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1500 */       AccessibilityNodeInfoCompatJellyBean.setAccesibilityFocused(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public boolean performAction(Object paramObject, int paramInt, Bundle paramBundle)
/*      */     {
/* 1505 */       return AccessibilityNodeInfoCompatJellyBean.performAction(paramObject, paramInt, paramBundle);
/*      */     }
/*      */ 
/*      */     public void setMovementGranularities(Object paramObject, int paramInt)
/*      */     {
/* 1510 */       AccessibilityNodeInfoCompatJellyBean.setMovementGranularities(paramObject, paramInt);
/*      */     }
/*      */ 
/*      */     public int getMovementGranularities(Object paramObject)
/*      */     {
/* 1515 */       return AccessibilityNodeInfoCompatJellyBean.getMovementGranularities(paramObject);
/*      */     }
/*      */ 
/*      */     public void setParent(Object paramObject, View paramView, int paramInt)
/*      */     {
/* 1520 */       AccessibilityNodeInfoCompatJellyBean.setParent(paramObject, paramView, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class AccessibilityNodeInfoIcsImpl extends AccessibilityNodeInfoCompat.AccessibilityNodeInfoStubImpl
/*      */   {
/*      */     public Object obtain()
/*      */     {
/* 1223 */       return AccessibilityNodeInfoCompatIcs.obtain();
/*      */     }
/*      */ 
/*      */     public Object obtain(View paramView)
/*      */     {
/* 1228 */       return AccessibilityNodeInfoCompatIcs.obtain(paramView);
/*      */     }
/*      */ 
/*      */     public Object obtain(Object paramObject)
/*      */     {
/* 1233 */       return AccessibilityNodeInfoCompatIcs.obtain(paramObject);
/*      */     }
/*      */ 
/*      */     public void addAction(Object paramObject, int paramInt)
/*      */     {
/* 1238 */       AccessibilityNodeInfoCompatIcs.addAction(paramObject, paramInt);
/*      */     }
/*      */ 
/*      */     public void addChild(Object paramObject, View paramView)
/*      */     {
/* 1243 */       AccessibilityNodeInfoCompatIcs.addChild(paramObject, paramView);
/*      */     }
/*      */ 
/*      */     public List<Object> findAccessibilityNodeInfosByText(Object paramObject, String paramString)
/*      */     {
/* 1248 */       return AccessibilityNodeInfoCompatIcs.findAccessibilityNodeInfosByText(paramObject, paramString);
/*      */     }
/*      */ 
/*      */     public int getActions(Object paramObject)
/*      */     {
/* 1253 */       return AccessibilityNodeInfoCompatIcs.getActions(paramObject);
/*      */     }
/*      */ 
/*      */     public void getBoundsInParent(Object paramObject, Rect paramRect)
/*      */     {
/* 1258 */       AccessibilityNodeInfoCompatIcs.getBoundsInParent(paramObject, paramRect);
/*      */     }
/*      */ 
/*      */     public void getBoundsInScreen(Object paramObject, Rect paramRect)
/*      */     {
/* 1263 */       AccessibilityNodeInfoCompatIcs.getBoundsInScreen(paramObject, paramRect);
/*      */     }
/*      */ 
/*      */     public Object getChild(Object paramObject, int paramInt)
/*      */     {
/* 1268 */       return AccessibilityNodeInfoCompatIcs.getChild(paramObject, paramInt);
/*      */     }
/*      */ 
/*      */     public int getChildCount(Object paramObject)
/*      */     {
/* 1273 */       return AccessibilityNodeInfoCompatIcs.getChildCount(paramObject);
/*      */     }
/*      */ 
/*      */     public CharSequence getClassName(Object paramObject)
/*      */     {
/* 1278 */       return AccessibilityNodeInfoCompatIcs.getClassName(paramObject);
/*      */     }
/*      */ 
/*      */     public CharSequence getContentDescription(Object paramObject)
/*      */     {
/* 1283 */       return AccessibilityNodeInfoCompatIcs.getContentDescription(paramObject);
/*      */     }
/*      */ 
/*      */     public CharSequence getPackageName(Object paramObject)
/*      */     {
/* 1288 */       return AccessibilityNodeInfoCompatIcs.getPackageName(paramObject);
/*      */     }
/*      */ 
/*      */     public Object getParent(Object paramObject)
/*      */     {
/* 1293 */       return AccessibilityNodeInfoCompatIcs.getParent(paramObject);
/*      */     }
/*      */ 
/*      */     public CharSequence getText(Object paramObject)
/*      */     {
/* 1298 */       return AccessibilityNodeInfoCompatIcs.getText(paramObject);
/*      */     }
/*      */ 
/*      */     public int getWindowId(Object paramObject)
/*      */     {
/* 1303 */       return AccessibilityNodeInfoCompatIcs.getWindowId(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isCheckable(Object paramObject)
/*      */     {
/* 1308 */       return AccessibilityNodeInfoCompatIcs.isCheckable(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isChecked(Object paramObject)
/*      */     {
/* 1313 */       return AccessibilityNodeInfoCompatIcs.isChecked(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isClickable(Object paramObject)
/*      */     {
/* 1318 */       return AccessibilityNodeInfoCompatIcs.isClickable(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isEnabled(Object paramObject)
/*      */     {
/* 1323 */       return AccessibilityNodeInfoCompatIcs.isEnabled(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isFocusable(Object paramObject)
/*      */     {
/* 1328 */       return AccessibilityNodeInfoCompatIcs.isFocusable(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isFocused(Object paramObject)
/*      */     {
/* 1333 */       return AccessibilityNodeInfoCompatIcs.isFocused(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isLongClickable(Object paramObject)
/*      */     {
/* 1338 */       return AccessibilityNodeInfoCompatIcs.isLongClickable(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isPassword(Object paramObject)
/*      */     {
/* 1343 */       return AccessibilityNodeInfoCompatIcs.isPassword(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isScrollable(Object paramObject)
/*      */     {
/* 1348 */       return AccessibilityNodeInfoCompatIcs.isScrollable(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean isSelected(Object paramObject)
/*      */     {
/* 1353 */       return AccessibilityNodeInfoCompatIcs.isSelected(paramObject);
/*      */     }
/*      */ 
/*      */     public boolean performAction(Object paramObject, int paramInt)
/*      */     {
/* 1358 */       return AccessibilityNodeInfoCompatIcs.performAction(paramObject, paramInt);
/*      */     }
/*      */ 
/*      */     public void setBoundsInParent(Object paramObject, Rect paramRect)
/*      */     {
/* 1363 */       AccessibilityNodeInfoCompatIcs.setBoundsInParent(paramObject, paramRect);
/*      */     }
/*      */ 
/*      */     public void setBoundsInScreen(Object paramObject, Rect paramRect)
/*      */     {
/* 1368 */       AccessibilityNodeInfoCompatIcs.setBoundsInScreen(paramObject, paramRect);
/*      */     }
/*      */ 
/*      */     public void setCheckable(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1373 */       AccessibilityNodeInfoCompatIcs.setCheckable(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setChecked(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1378 */       AccessibilityNodeInfoCompatIcs.setChecked(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setClassName(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/* 1383 */       AccessibilityNodeInfoCompatIcs.setClassName(paramObject, paramCharSequence);
/*      */     }
/*      */ 
/*      */     public void setClickable(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1388 */       AccessibilityNodeInfoCompatIcs.setClickable(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setContentDescription(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/* 1393 */       AccessibilityNodeInfoCompatIcs.setContentDescription(paramObject, paramCharSequence);
/*      */     }
/*      */ 
/*      */     public void setEnabled(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1398 */       AccessibilityNodeInfoCompatIcs.setEnabled(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setFocusable(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1403 */       AccessibilityNodeInfoCompatIcs.setFocusable(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setFocused(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1408 */       AccessibilityNodeInfoCompatIcs.setFocused(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setLongClickable(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1413 */       AccessibilityNodeInfoCompatIcs.setLongClickable(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setPackageName(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/* 1418 */       AccessibilityNodeInfoCompatIcs.setPackageName(paramObject, paramCharSequence);
/*      */     }
/*      */ 
/*      */     public void setParent(Object paramObject, View paramView)
/*      */     {
/* 1423 */       AccessibilityNodeInfoCompatIcs.setParent(paramObject, paramView);
/*      */     }
/*      */ 
/*      */     public void setPassword(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1428 */       AccessibilityNodeInfoCompatIcs.setPassword(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setScrollable(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1433 */       AccessibilityNodeInfoCompatIcs.setScrollable(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setSelected(Object paramObject, boolean paramBoolean)
/*      */     {
/* 1438 */       AccessibilityNodeInfoCompatIcs.setSelected(paramObject, paramBoolean);
/*      */     }
/*      */ 
/*      */     public void setSource(Object paramObject, View paramView)
/*      */     {
/* 1443 */       AccessibilityNodeInfoCompatIcs.setSource(paramObject, paramView);
/*      */     }
/*      */ 
/*      */     public void setText(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/* 1448 */       AccessibilityNodeInfoCompatIcs.setText(paramObject, paramCharSequence);
/*      */     }
/*      */ 
/*      */     public void recycle(Object paramObject)
/*      */     {
/* 1453 */       AccessibilityNodeInfoCompatIcs.recycle(paramObject);
/*      */     }
/*      */   }
/*      */ 
/*      */   static class AccessibilityNodeInfoStubImpl
/*      */     implements AccessibilityNodeInfoCompat.AccessibilityNodeInfoImpl
/*      */   {
/*      */     public Object newAccessibilityAction(int paramInt, CharSequence paramCharSequence)
/*      */     {
/*  619 */       return null;
/*      */     }
/*      */ 
/*      */     public Object obtain()
/*      */     {
/*  624 */       return null;
/*      */     }
/*      */ 
/*      */     public Object obtain(View paramView)
/*      */     {
/*  629 */       return null;
/*      */     }
/*      */ 
/*      */     public Object obtain(View paramView, int paramInt)
/*      */     {
/*  634 */       return null;
/*      */     }
/*      */ 
/*      */     public Object obtain(Object paramObject)
/*      */     {
/*  639 */       return null;
/*      */     }
/*      */ 
/*      */     public void addAction(Object paramObject, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void addAction(Object paramObject1, Object paramObject2)
/*      */     {
/*      */     }
/*      */ 
/*      */     public boolean removeAction(Object paramObject1, Object paramObject2)
/*      */     {
/*  654 */       return false;
/*      */     }
/*      */ 
/*      */     public int getAccessibilityActionId(Object paramObject)
/*      */     {
/*  659 */       return 0;
/*      */     }
/*      */ 
/*      */     public CharSequence getAccessibilityActionLabel(Object paramObject)
/*      */     {
/*  664 */       return null;
/*      */     }
/*      */ 
/*      */     public void addChild(Object paramObject, View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void addChild(Object paramObject, View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public boolean removeChild(Object paramObject, View paramView)
/*      */     {
/*  679 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean removeChild(Object paramObject, View paramView, int paramInt)
/*      */     {
/*  684 */       return false;
/*      */     }
/*      */ 
/*      */     public List<Object> findAccessibilityNodeInfosByText(Object paramObject, String paramString)
/*      */     {
/*  689 */       return Collections.emptyList();
/*      */     }
/*      */ 
/*      */     public int getActions(Object paramObject)
/*      */     {
/*  694 */       return 0;
/*      */     }
/*      */ 
/*      */     public void getBoundsInParent(Object paramObject, Rect paramRect)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void getBoundsInScreen(Object paramObject, Rect paramRect)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Object getChild(Object paramObject, int paramInt)
/*      */     {
/*  709 */       return null;
/*      */     }
/*      */ 
/*      */     public int getChildCount(Object paramObject)
/*      */     {
/*  714 */       return 0;
/*      */     }
/*      */ 
/*      */     public CharSequence getClassName(Object paramObject)
/*      */     {
/*  719 */       return null;
/*      */     }
/*      */ 
/*      */     public CharSequence getContentDescription(Object paramObject)
/*      */     {
/*  724 */       return null;
/*      */     }
/*      */ 
/*      */     public CharSequence getPackageName(Object paramObject)
/*      */     {
/*  729 */       return null;
/*      */     }
/*      */ 
/*      */     public Object getParent(Object paramObject)
/*      */     {
/*  734 */       return null;
/*      */     }
/*      */ 
/*      */     public CharSequence getText(Object paramObject)
/*      */     {
/*  739 */       return null;
/*      */     }
/*      */ 
/*      */     public int getWindowId(Object paramObject)
/*      */     {
/*  744 */       return 0;
/*      */     }
/*      */ 
/*      */     public boolean isCheckable(Object paramObject)
/*      */     {
/*  749 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isChecked(Object paramObject)
/*      */     {
/*  754 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isClickable(Object paramObject)
/*      */     {
/*  759 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isEnabled(Object paramObject)
/*      */     {
/*  764 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isFocusable(Object paramObject)
/*      */     {
/*  769 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isFocused(Object paramObject)
/*      */     {
/*  774 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isVisibleToUser(Object paramObject)
/*      */     {
/*  779 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isAccessibilityFocused(Object paramObject)
/*      */     {
/*  784 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isLongClickable(Object paramObject)
/*      */     {
/*  789 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isPassword(Object paramObject)
/*      */     {
/*  794 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isScrollable(Object paramObject)
/*      */     {
/*  799 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isSelected(Object paramObject)
/*      */     {
/*  804 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean performAction(Object paramObject, int paramInt)
/*      */     {
/*  809 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean performAction(Object paramObject, int paramInt, Bundle paramBundle)
/*      */     {
/*  814 */       return false;
/*      */     }
/*      */ 
/*      */     public void setMovementGranularities(Object paramObject, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public int getMovementGranularities(Object paramObject)
/*      */     {
/*  824 */       return 0;
/*      */     }
/*      */ 
/*      */     public void setBoundsInParent(Object paramObject, Rect paramRect)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setBoundsInScreen(Object paramObject, Rect paramRect)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setCheckable(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setChecked(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setClassName(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setClickable(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setContentDescription(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setEnabled(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setFocusable(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setFocused(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setVisibleToUser(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setAccessibilityFocused(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setLongClickable(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setPackageName(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setParent(Object paramObject, View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setPassword(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setScrollable(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setSelected(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setSource(Object paramObject, View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setSource(Object paramObject, View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Object findFocus(Object paramObject, int paramInt)
/*      */     {
/*  929 */       return null;
/*      */     }
/*      */ 
/*      */     public Object focusSearch(Object paramObject, int paramInt)
/*      */     {
/*  934 */       return null;
/*      */     }
/*      */ 
/*      */     public void setText(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void recycle(Object paramObject)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setParent(Object paramObject, View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public String getViewIdResourceName(Object paramObject)
/*      */     {
/*  954 */       return null;
/*      */     }
/*      */ 
/*      */     public void setViewIdResourceName(Object paramObject, String paramString)
/*      */     {
/*      */     }
/*      */ 
/*      */     public int getLiveRegion(Object paramObject)
/*      */     {
/*  964 */       return 0;
/*      */     }
/*      */ 
/*      */     public void setLiveRegion(Object paramObject, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Object getCollectionInfo(Object paramObject)
/*      */     {
/*  974 */       return null;
/*      */     }
/*      */ 
/*      */     public void setCollectionInfo(Object paramObject1, Object paramObject2)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Object getCollectionItemInfo(Object paramObject)
/*      */     {
/*  983 */       return null;
/*      */     }
/*      */ 
/*      */     public void setCollectionItemInfo(Object paramObject1, Object paramObject2)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Object getRangeInfo(Object paramObject)
/*      */     {
/*  992 */       return null;
/*      */     }
/*      */ 
/*      */     public void setRangeInfo(Object paramObject1, Object paramObject2)
/*      */     {
/*      */     }
/*      */ 
/*      */     public List<Object> getActionList(Object paramObject)
/*      */     {
/* 1001 */       return null;
/*      */     }
/*      */ 
/*      */     public Object obtainCollectionInfo(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
/*      */     {
/* 1007 */       return null;
/*      */     }
/*      */ 
/*      */     public int getCollectionInfoColumnCount(Object paramObject)
/*      */     {
/* 1012 */       return 0;
/*      */     }
/*      */ 
/*      */     public int getCollectionInfoRowCount(Object paramObject)
/*      */     {
/* 1017 */       return 0;
/*      */     }
/*      */ 
/*      */     public boolean isCollectionInfoHierarchical(Object paramObject)
/*      */     {
/* 1022 */       return false;
/*      */     }
/*      */ 
/*      */     public Object obtainCollectionItemInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
/*      */     {
/* 1028 */       return null;
/*      */     }
/*      */ 
/*      */     public int getCollectionItemColumnIndex(Object paramObject)
/*      */     {
/* 1033 */       return 0;
/*      */     }
/*      */ 
/*      */     public int getCollectionItemColumnSpan(Object paramObject)
/*      */     {
/* 1038 */       return 0;
/*      */     }
/*      */ 
/*      */     public int getCollectionItemRowIndex(Object paramObject)
/*      */     {
/* 1043 */       return 0;
/*      */     }
/*      */ 
/*      */     public int getCollectionItemRowSpan(Object paramObject)
/*      */     {
/* 1048 */       return 0;
/*      */     }
/*      */ 
/*      */     public boolean isCollectionItemHeading(Object paramObject)
/*      */     {
/* 1053 */       return false;
/*      */     }
/*      */ 
/*      */     public boolean isCollectionItemSelected(Object paramObject)
/*      */     {
/* 1058 */       return false;
/*      */     }
/*      */ 
/*      */     public Object getTraversalBefore(Object paramObject)
/*      */     {
/* 1063 */       return null;
/*      */     }
/*      */ 
/*      */     public void setTraversalBefore(Object paramObject, View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setTraversalBefore(Object paramObject, View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Object getTraversalAfter(Object paramObject)
/*      */     {
/* 1076 */       return null;
/*      */     }
/*      */ 
/*      */     public void setTraversalAfter(Object paramObject, View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setTraversalAfter(Object paramObject, View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setContentInvalid(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public boolean isContentInvalid(Object paramObject)
/*      */     {
/* 1093 */       return false;
/*      */     }
/*      */ 
/*      */     public void setError(Object paramObject, CharSequence paramCharSequence)
/*      */     {
/*      */     }
/*      */ 
/*      */     public CharSequence getError(Object paramObject)
/*      */     {
/* 1102 */       return null;
/*      */     }
/*      */ 
/*      */     public void setLabelFor(Object paramObject, View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setLabelFor(Object paramObject, View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Object getLabelFor(Object paramObject)
/*      */     {
/* 1115 */       return null;
/*      */     }
/*      */ 
/*      */     public void setLabeledBy(Object paramObject, View paramView)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setLabeledBy(Object paramObject, View paramView, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public Object getLabeledBy(Object paramObject)
/*      */     {
/* 1128 */       return null;
/*      */     }
/*      */ 
/*      */     public boolean canOpenPopup(Object paramObject)
/*      */     {
/* 1133 */       return false;
/*      */     }
/*      */ 
/*      */     public void setCanOpenPopup(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public List<Object> findAccessibilityNodeInfosByViewId(Object paramObject, String paramString)
/*      */     {
/* 1142 */       return Collections.emptyList();
/*      */     }
/*      */ 
/*      */     public Bundle getExtras(Object paramObject)
/*      */     {
/* 1147 */       return new Bundle();
/*      */     }
/*      */ 
/*      */     public int getInputType(Object paramObject)
/*      */     {
/* 1152 */       return 0;
/*      */     }
/*      */ 
/*      */     public void setInputType(Object paramObject, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public void setMaxTextLength(Object paramObject, int paramInt)
/*      */     {
/*      */     }
/*      */ 
/*      */     public int getMaxTextLength(Object paramObject)
/*      */     {
/* 1165 */       return -1;
/*      */     }
/*      */ 
/*      */     public void setTextSelection(Object paramObject, int paramInt1, int paramInt2)
/*      */     {
/*      */     }
/*      */ 
/*      */     public int getTextSelectionStart(Object paramObject)
/*      */     {
/* 1174 */       return -1;
/*      */     }
/*      */ 
/*      */     public int getTextSelectionEnd(Object paramObject)
/*      */     {
/* 1179 */       return -1;
/*      */     }
/*      */ 
/*      */     public Object getWindow(Object paramObject)
/*      */     {
/* 1184 */       return null;
/*      */     }
/*      */ 
/*      */     public boolean isDismissable(Object paramObject)
/*      */     {
/* 1189 */       return false;
/*      */     }
/*      */ 
/*      */     public void setDismissable(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public boolean isEditable(Object paramObject)
/*      */     {
/* 1198 */       return false;
/*      */     }
/*      */ 
/*      */     public void setEditable(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public boolean isMultiLine(Object paramObject)
/*      */     {
/* 1207 */       return false;
/*      */     }
/*      */ 
/*      */     public void setMultiLine(Object paramObject, boolean paramBoolean)
/*      */     {
/*      */     }
/*      */ 
/*      */     public boolean refresh(Object paramObject)
/*      */     {
/* 1216 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */   static abstract interface AccessibilityNodeInfoImpl
/*      */   {
/*      */     public abstract Object newAccessibilityAction(int paramInt, CharSequence paramCharSequence);
/*      */ 
/*      */     public abstract Object obtain();
/*      */ 
/*      */     public abstract Object obtain(View paramView);
/*      */ 
/*      */     public abstract Object obtain(Object paramObject);
/*      */ 
/*      */     public abstract Object obtain(View paramView, int paramInt);
/*      */ 
/*      */     public abstract void setSource(Object paramObject, View paramView);
/*      */ 
/*      */     public abstract void setSource(Object paramObject, View paramView, int paramInt);
/*      */ 
/*      */     public abstract Object findFocus(Object paramObject, int paramInt);
/*      */ 
/*      */     public abstract Object focusSearch(Object paramObject, int paramInt);
/*      */ 
/*      */     public abstract int getWindowId(Object paramObject);
/*      */ 
/*      */     public abstract int getChildCount(Object paramObject);
/*      */ 
/*      */     public abstract Object getChild(Object paramObject, int paramInt);
/*      */ 
/*      */     public abstract void addChild(Object paramObject, View paramView);
/*      */ 
/*      */     public abstract void addChild(Object paramObject, View paramView, int paramInt);
/*      */ 
/*      */     public abstract boolean removeChild(Object paramObject, View paramView);
/*      */ 
/*      */     public abstract boolean removeChild(Object paramObject, View paramView, int paramInt);
/*      */ 
/*      */     public abstract int getActions(Object paramObject);
/*      */ 
/*      */     public abstract void addAction(Object paramObject, int paramInt);
/*      */ 
/*      */     public abstract void addAction(Object paramObject1, Object paramObject2);
/*      */ 
/*      */     public abstract boolean removeAction(Object paramObject1, Object paramObject2);
/*      */ 
/*      */     public abstract int getAccessibilityActionId(Object paramObject);
/*      */ 
/*      */     public abstract CharSequence getAccessibilityActionLabel(Object paramObject);
/*      */ 
/*      */     public abstract boolean performAction(Object paramObject, int paramInt);
/*      */ 
/*      */     public abstract boolean performAction(Object paramObject, int paramInt, Bundle paramBundle);
/*      */ 
/*      */     public abstract void setMovementGranularities(Object paramObject, int paramInt);
/*      */ 
/*      */     public abstract int getMovementGranularities(Object paramObject);
/*      */ 
/*      */     public abstract List<Object> findAccessibilityNodeInfosByText(Object paramObject, String paramString);
/*      */ 
/*      */     public abstract Object getParent(Object paramObject);
/*      */ 
/*      */     public abstract void setParent(Object paramObject, View paramView, int paramInt);
/*      */ 
/*      */     public abstract void setParent(Object paramObject, View paramView);
/*      */ 
/*      */     public abstract void getBoundsInParent(Object paramObject, Rect paramRect);
/*      */ 
/*      */     public abstract void setBoundsInParent(Object paramObject, Rect paramRect);
/*      */ 
/*      */     public abstract void getBoundsInScreen(Object paramObject, Rect paramRect);
/*      */ 
/*      */     public abstract void setBoundsInScreen(Object paramObject, Rect paramRect);
/*      */ 
/*      */     public abstract boolean isCheckable(Object paramObject);
/*      */ 
/*      */     public abstract void setCheckable(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isChecked(Object paramObject);
/*      */ 
/*      */     public abstract void setChecked(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isFocusable(Object paramObject);
/*      */ 
/*      */     public abstract void setFocusable(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isFocused(Object paramObject);
/*      */ 
/*      */     public abstract void setFocused(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isVisibleToUser(Object paramObject);
/*      */ 
/*      */     public abstract void setVisibleToUser(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isAccessibilityFocused(Object paramObject);
/*      */ 
/*      */     public abstract void setAccessibilityFocused(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isSelected(Object paramObject);
/*      */ 
/*      */     public abstract void setSelected(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isClickable(Object paramObject);
/*      */ 
/*      */     public abstract void setClickable(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isLongClickable(Object paramObject);
/*      */ 
/*      */     public abstract void setLongClickable(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isEnabled(Object paramObject);
/*      */ 
/*      */     public abstract void setEnabled(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isPassword(Object paramObject);
/*      */ 
/*      */     public abstract void setPassword(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isScrollable(Object paramObject);
/*      */ 
/*      */     public abstract void setScrollable(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract CharSequence getPackageName(Object paramObject);
/*      */ 
/*      */     public abstract void setPackageName(Object paramObject, CharSequence paramCharSequence);
/*      */ 
/*      */     public abstract CharSequence getClassName(Object paramObject);
/*      */ 
/*      */     public abstract void setClassName(Object paramObject, CharSequence paramCharSequence);
/*      */ 
/*      */     public abstract CharSequence getText(Object paramObject);
/*      */ 
/*      */     public abstract void setText(Object paramObject, CharSequence paramCharSequence);
/*      */ 
/*      */     public abstract CharSequence getContentDescription(Object paramObject);
/*      */ 
/*      */     public abstract void setContentDescription(Object paramObject, CharSequence paramCharSequence);
/*      */ 
/*      */     public abstract void recycle(Object paramObject);
/*      */ 
/*      */     public abstract String getViewIdResourceName(Object paramObject);
/*      */ 
/*      */     public abstract void setViewIdResourceName(Object paramObject, String paramString);
/*      */ 
/*      */     public abstract int getLiveRegion(Object paramObject);
/*      */ 
/*      */     public abstract void setLiveRegion(Object paramObject, int paramInt);
/*      */ 
/*      */     public abstract Object getCollectionInfo(Object paramObject);
/*      */ 
/*      */     public abstract void setCollectionInfo(Object paramObject1, Object paramObject2);
/*      */ 
/*      */     public abstract Object getCollectionItemInfo(Object paramObject);
/*      */ 
/*      */     public abstract void setCollectionItemInfo(Object paramObject1, Object paramObject2);
/*      */ 
/*      */     public abstract Object getRangeInfo(Object paramObject);
/*      */ 
/*      */     public abstract void setRangeInfo(Object paramObject1, Object paramObject2);
/*      */ 
/*      */     public abstract List<Object> getActionList(Object paramObject);
/*      */ 
/*      */     public abstract Object obtainCollectionInfo(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3);
/*      */ 
/*      */     public abstract int getCollectionInfoColumnCount(Object paramObject);
/*      */ 
/*      */     public abstract int getCollectionInfoRowCount(Object paramObject);
/*      */ 
/*      */     public abstract boolean isCollectionInfoHierarchical(Object paramObject);
/*      */ 
/*      */     public abstract Object obtainCollectionItemInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */     public abstract int getCollectionItemColumnIndex(Object paramObject);
/*      */ 
/*      */     public abstract int getCollectionItemColumnSpan(Object paramObject);
/*      */ 
/*      */     public abstract int getCollectionItemRowIndex(Object paramObject);
/*      */ 
/*      */     public abstract int getCollectionItemRowSpan(Object paramObject);
/*      */ 
/*      */     public abstract boolean isCollectionItemHeading(Object paramObject);
/*      */ 
/*      */     public abstract boolean isCollectionItemSelected(Object paramObject);
/*      */ 
/*      */     public abstract Object getTraversalBefore(Object paramObject);
/*      */ 
/*      */     public abstract void setTraversalBefore(Object paramObject, View paramView);
/*      */ 
/*      */     public abstract void setTraversalBefore(Object paramObject, View paramView, int paramInt);
/*      */ 
/*      */     public abstract Object getTraversalAfter(Object paramObject);
/*      */ 
/*      */     public abstract void setTraversalAfter(Object paramObject, View paramView);
/*      */ 
/*      */     public abstract void setTraversalAfter(Object paramObject, View paramView, int paramInt);
/*      */ 
/*      */     public abstract void setContentInvalid(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isContentInvalid(Object paramObject);
/*      */ 
/*      */     public abstract void setError(Object paramObject, CharSequence paramCharSequence);
/*      */ 
/*      */     public abstract CharSequence getError(Object paramObject);
/*      */ 
/*      */     public abstract void setLabelFor(Object paramObject, View paramView);
/*      */ 
/*      */     public abstract void setLabelFor(Object paramObject, View paramView, int paramInt);
/*      */ 
/*      */     public abstract Object getLabelFor(Object paramObject);
/*      */ 
/*      */     public abstract void setLabeledBy(Object paramObject, View paramView);
/*      */ 
/*      */     public abstract void setLabeledBy(Object paramObject, View paramView, int paramInt);
/*      */ 
/*      */     public abstract Object getLabeledBy(Object paramObject);
/*      */ 
/*      */     public abstract boolean canOpenPopup(Object paramObject);
/*      */ 
/*      */     public abstract void setCanOpenPopup(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract List<Object> findAccessibilityNodeInfosByViewId(Object paramObject, String paramString);
/*      */ 
/*      */     public abstract Bundle getExtras(Object paramObject);
/*      */ 
/*      */     public abstract int getInputType(Object paramObject);
/*      */ 
/*      */     public abstract void setInputType(Object paramObject, int paramInt);
/*      */ 
/*      */     public abstract void setMaxTextLength(Object paramObject, int paramInt);
/*      */ 
/*      */     public abstract int getMaxTextLength(Object paramObject);
/*      */ 
/*      */     public abstract void setTextSelection(Object paramObject, int paramInt1, int paramInt2);
/*      */ 
/*      */     public abstract int getTextSelectionStart(Object paramObject);
/*      */ 
/*      */     public abstract int getTextSelectionEnd(Object paramObject);
/*      */ 
/*      */     public abstract Object getWindow(Object paramObject);
/*      */ 
/*      */     public abstract boolean isDismissable(Object paramObject);
/*      */ 
/*      */     public abstract void setDismissable(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isEditable(Object paramObject);
/*      */ 
/*      */     public abstract void setEditable(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean isMultiLine(Object paramObject);
/*      */ 
/*      */     public abstract void setMultiLine(Object paramObject, boolean paramBoolean);
/*      */ 
/*      */     public abstract boolean refresh(Object paramObject);
/*      */   }
/*      */ 
/*      */   public static class RangeInfoCompat
/*      */   {
/*      */     public static final int RANGE_TYPE_INT = 0;
/*      */     public static final int RANGE_TYPE_FLOAT = 1;
/*      */     public static final int RANGE_TYPE_PERCENT = 2;
/*      */     private final Object mInfo;
/*      */ 
/*      */     private RangeInfoCompat(Object paramObject)
/*      */     {
/*  467 */       this.mInfo = paramObject;
/*      */     }
/*      */ 
/*      */     public float getCurrent() {
/*  471 */       return AccessibilityNodeInfoCompatKitKat.RangeInfo.getCurrent(this.mInfo);
/*      */     }
/*      */ 
/*      */     public float getMax() {
/*  475 */       return AccessibilityNodeInfoCompatKitKat.RangeInfo.getMax(this.mInfo);
/*      */     }
/*      */ 
/*      */     public float getMin() {
/*  479 */       return AccessibilityNodeInfoCompatKitKat.RangeInfo.getMin(this.mInfo);
/*      */     }
/*      */ 
/*      */     public int getType() {
/*  483 */       return AccessibilityNodeInfoCompatKitKat.RangeInfo.getType(this.mInfo);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static class CollectionItemInfoCompat
/*      */   {
/*      */     private final Object mInfo;
/*      */ 
/*      */     public static CollectionItemInfoCompat obtain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
/*      */     {
/*  423 */       return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1, paramBoolean2));
/*      */     }
/*      */ 
/*      */     private CollectionItemInfoCompat(Object paramObject)
/*      */     {
/*  428 */       this.mInfo = paramObject;
/*      */     }
/*      */ 
/*      */     public int getColumnIndex() {
/*  432 */       return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnIndex(this.mInfo);
/*      */     }
/*      */ 
/*      */     public int getColumnSpan() {
/*  436 */       return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnSpan(this.mInfo);
/*      */     }
/*      */ 
/*      */     public int getRowIndex() {
/*  440 */       return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowIndex(this.mInfo);
/*      */     }
/*      */ 
/*      */     public int getRowSpan() {
/*  444 */       return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowSpan(this.mInfo);
/*      */     }
/*      */ 
/*      */     public boolean isHeading() {
/*  448 */       return AccessibilityNodeInfoCompat.IMPL.isCollectionItemHeading(this.mInfo);
/*      */     }
/*      */ 
/*      */     public boolean isSelected() {
/*  452 */       return AccessibilityNodeInfoCompat.IMPL.isCollectionItemSelected(this.mInfo);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static class CollectionInfoCompat
/*      */   {
/*      */     public static final int SELECTION_MODE_NONE = 0;
/*      */     public static final int SELECTION_MODE_SINGLE = 1;
/*      */     public static final int SELECTION_MODE_MULTIPLE = 2;
/*      */     final Object mInfo;
/*      */ 
/*      */     public static CollectionInfoCompat obtain(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
/*      */     {
/*  391 */       return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(paramInt1, paramInt2, paramBoolean, paramInt3));
/*      */     }
/*      */ 
/*      */     private CollectionInfoCompat(Object paramObject)
/*      */     {
/*  396 */       this.mInfo = paramObject;
/*      */     }
/*      */ 
/*      */     public int getColumnCount() {
/*  400 */       return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoColumnCount(this.mInfo);
/*      */     }
/*      */ 
/*      */     public int getRowCount() {
/*  404 */       return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoRowCount(this.mInfo);
/*      */     }
/*      */ 
/*      */     public boolean isHierarchical() {
/*  408 */       return AccessibilityNodeInfoCompat.IMPL.isCollectionInfoHierarchical(this.mInfo);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static class AccessibilityActionCompat
/*      */   {
/*   42 */     public static final AccessibilityActionCompat ACTION_FOCUS = new AccessibilityActionCompat(1, null);
/*      */ 
/*   49 */     public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
/*      */ 
/*   56 */     public static final AccessibilityActionCompat ACTION_SELECT = new AccessibilityActionCompat(4, null);
/*      */ 
/*   63 */     public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
/*      */ 
/*   70 */     public static final AccessibilityActionCompat ACTION_CLICK = new AccessibilityActionCompat(16, null);
/*      */ 
/*   77 */     public static final AccessibilityActionCompat ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
/*      */ 
/*   84 */     public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
/*      */ 
/*   91 */     public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
/*      */ 
/*  139 */     public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, null);
/*      */ 
/*  187 */     public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, null);
/*      */ 
/*  208 */     public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, null);
/*      */ 
/*  229 */     public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, null);
/*      */ 
/*  236 */     public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
/*      */ 
/*  243 */     public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
/*      */ 
/*  250 */     public static final AccessibilityActionCompat ACTION_COPY = new AccessibilityActionCompat(16384, null);
/*      */ 
/*  257 */     public static final AccessibilityActionCompat ACTION_PASTE = new AccessibilityActionCompat(32768, null);
/*      */ 
/*  264 */     public static final AccessibilityActionCompat ACTION_CUT = new AccessibilityActionCompat(65536, null);
/*      */ 
/*  291 */     public static final AccessibilityActionCompat ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, null);
/*      */ 
/*  298 */     public static final AccessibilityActionCompat ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
/*      */ 
/*  305 */     public static final AccessibilityActionCompat ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
/*      */ 
/*  312 */     public static final AccessibilityActionCompat ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
/*      */ 
/*  332 */     public static final AccessibilityActionCompat ACTION_SET_TEXT = new AccessibilityActionCompat(2097152, null);
/*      */     private final Object mAction;
/*      */ 
/*      */     public AccessibilityActionCompat(int paramInt, CharSequence paramCharSequence)
/*      */     {
/*  345 */       this(AccessibilityNodeInfoCompat.IMPL.newAccessibilityAction(paramInt, paramCharSequence));
/*      */     }
/*      */ 
/*      */     private AccessibilityActionCompat(Object paramObject) {
/*  349 */       this.mAction = paramObject;
/*      */     }
/*      */ 
/*      */     public int getId()
/*      */     {
/*  358 */       return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionId(this.mAction);
/*      */     }
/*      */ 
/*      */     public CharSequence getLabel()
/*      */     {
/*  368 */       return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionLabel(this.mAction);
/*      */     }
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.accessibility.AccessibilityNodeInfoCompat
 * JD-Core Version:    0.6.2
 */