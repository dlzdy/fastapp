/*    */ package io.dcloud.feature.ui.nativeui;
/*    */ 
/*    */ import io.dcloud.RInformation;
/*    */ import io.dcloud.common.DHInterface.IReflectAble;
/*    */ 
/*    */ public abstract interface NativeUIR extends IReflectAble
/*    */ {
/* 11 */   public static final int[] ACTS_STYLE_ACTIONSHEET = RInformation.getIntArray("styleable", "ActionSheet");
/* 12 */   public static final int ACTS_STYLE_actionSheetBackground = RInformation.getInt("styleable", "ActionSheet_actionSheetBackground");
/* 13 */   public static final int ACTS_STYLE_cancelButtonBackground = RInformation.getInt("styleable", "ActionSheet_cancelButtonBackground");
/* 14 */   public static final int ACTS_STYLE_otherButtonTopBackground = RInformation.getInt("styleable", "ActionSheet_otherButtonTopBackground");
/* 15 */   public static final int ACTS_STYLE_otherButtonMiddleBackground = RInformation.getInt("styleable", "ActionSheet_otherButtonMiddleBackground");
/* 16 */   public static final int ACTS_STYLE_otherButtonBottomBackground = RInformation.getInt("styleable", "ActionSheet_otherButtonBottomBackground");
/* 17 */   public static final int ACTS_STYLE_ActionSheet_cancelButtonTextColor = RInformation.getInt("styleable", "ActionSheet_otherButtonSingleBackground");
/* 18 */   public static final int ACTS_STYLE_cancelButtonTextColor = RInformation.getInt("styleable", "ActionSheet_cancelButtonTextColor");
/* 19 */   public static final int ACTS_STYLE_otherButtonTextColor = RInformation.getInt("styleable", "ActionSheet_otherButtonTextColor");
/* 20 */   public static final int ACTS_STYLE_actionSheetPadding = RInformation.getInt("styleable", "ActionSheet_actionSheetPadding");
/* 21 */   public static final int ACTS_STYLE_otherButtonSpacing = RInformation.getInt("styleable", "ActionSheet_otherButtonSpacing");
/* 22 */   public static final int ACTS_STYLE_cancelButtonMarginTop = RInformation.getInt("styleable", "ActionSheet_cancelButtonMarginTop");
/* 23 */   public static final int ACTS_STYLE_actionSheetTextSize = RInformation.getInt("styleable", "ActionSheet_actionSheetTextSize");
/* 24 */   public static final int ACTS_STYLE_otherButtonSingleBackground = RInformation.getInt("styleable", "ActionSheet_otherButtonSingleBackground");
/* 25 */   public static final int ACTS_ATTR_SctionSheetSytle = RInformation.getInt("attr", "actionSheetStyle");
/* 26 */   public static final int ACTS_STYLE_ActionSheetStyleIOS7 = RInformation.getInt("style", "ActionSheetStyleIOS7");
/* 27 */   public static final int ACTS_STYLE_titleButtonTextColor = RInformation.getInt("styleable", "ActionSheet_titleButtonTextColor");
/* 28 */   public static final int ACTS_STYLE_destructiveButtonTextColor = RInformation.getInt("styleable", "ActionSheet_destructiveButtonTextColor");
/* 29 */   public static final int ACTS_STYLE_otherButtonTitleBackground = RInformation.getInt("styleable", "ActionSheet_otherButtonTitleBackground");
/* 30 */   public static final int SLT_AS_IOS7_CANCEL_BT = RInformation.getInt("drawable", "slt_as_ios7_cancel_bt");
/* 31 */   public static final int SLT_AS_IOS7_OTHER_BT_TOP = RInformation.getInt("drawable", "slt_as_ios7_other_bt_top");
/* 32 */   public static final int SLT_AS_IOS7_OTHER_BT_MIDDLE = RInformation.getInt("drawable", "slt_as_ios7_other_bt_middle");
/* 33 */   public static final int SLT_AS_IOS7_OTHER_BT_BOTTOM = RInformation.getInt("drawable", "slt_as_ios7_other_bt_bottom");
/* 34 */   public static final int SLT_AS_IOS7_OTHER_BT_SINGLE = RInformation.getInt("drawable", "slt_as_ios7_other_bt_single");
/*    */ 
/* 38 */   public static final int LAYOUT_DIALOG_LAYOUT_LOADING_DCLOUD = RInformation.getInt("layout", "dcloud_loadingview");
/* 39 */   public static final int ID_TEXT_LOADING_DCLOUD = RInformation.getInt("id", "dcloud_tv_loading");
/* 40 */   public static final int ID_PROGRESSBAR_LOADING_DCLOUD = RInformation.getInt("id", "dcloud_pb_loading");
/* 41 */   public static final int ID_IMAGE_LOADING_DCLOUD = RInformation.getInt("id", "dcloud_iv_loading");
/* 42 */   public static final int ID_WAITING_SEPARATOR_DCLOUD = RInformation.getInt("id", "dcloud_view_seaparator");
/* 43 */   public static final int DCLOUD_LOADING_LAYOUT_ROOT = RInformation.getInt("id", "dcloud_pd_root");
/*    */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.doctor.hd\app\libs\nativeui.jar
 * Qualified Name:     io.dcloud.feature.ui.nativeui.NativeUIR
 * JD-Core Version:    0.6.2
 */