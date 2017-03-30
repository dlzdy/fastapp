/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.os.Build;
/*     */ import android.text.TextUtils;
/*     */ import android.view.View;
/*     */ import android.webkit.URLUtil;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class ViewOptions extends ViewRect
/*     */ {
/*  21 */   public boolean mUseHardwave = true;
/*     */   public Object mTag;
/*  23 */   public boolean scalable = false;
/*  24 */   public String mInjection = "true";
/*  25 */   public String mPlusrequire = "normal";
/*     */   public String name;
/*  27 */   private String mScrollIndicator = "all";
/*  28 */   public float opacity = -1.0F;
/*     */   public static final int BG_NONE = -1;
/*  30 */   public int background = -1;
/*  31 */   public int maskColor = -1;
/*  32 */   public String strBackground = null;
/*  33 */   public String errorPage = null;
/*  34 */   public boolean mBounce = false;
/*     */   public JSONObject transition;
/*     */   public JSONObject transform;
/*  38 */   public String mCacheMode = "default";
/*  39 */   public String mVideoFullscree = "auto";
/*     */ 
/*  44 */   public String popGesture = "none";
/*  45 */   public String mGeoInject = "none";
/*     */   public HashMap<String, DragBean> dragData;
/*  47 */   public boolean dragH5NeedTouchEvent = false;
/*     */   public JSONObject navigationbar;
/*     */ 
/*     */   public ViewOptions()
/*     */   {
/*  41 */     this.mUseHardwave = MobilePhoneModel.checkPhoneBanAcceleration(Build.BRAND);
/*     */   }
/*     */ 
/*     */   public boolean hasBackground()
/*     */   {
/*  56 */     return this.background != -1;
/*     */   }
/*     */ 
/*     */   public boolean hasMask() {
/*  60 */     return this.maskColor != -1;
/*     */   }
/*     */ 
/*     */   public boolean isTransparent()
/*     */   {
/*  68 */     return PdrUtil.isEquals("transparent", this.strBackground);
/*     */   }
/*     */ 
/*     */   public boolean hasTransparentValue()
/*     */   {
/*  75 */     return (isTransparent()) || (PdrUtil.checkAlphaTransparent(this.background)) || ((this.opacity >= 0.0F) && (this.opacity < 1.0F));
/*     */   }
/*     */ 
/*     */   public void updateViewData(ViewRect paramViewRect)
/*     */   {
/*  80 */     super.updateViewData(paramViewRect);
/*     */   }
/*     */ 
/*     */   public boolean updateViewData(JSONObject paramJSONObject)
/*     */   {
/*  90 */     boolean bool = super.updateViewData(paramJSONObject);
/*  91 */     if (paramJSONObject != null) {
/*  92 */       ViewOptions localViewOptions = this;
/*  93 */       if (!JSONUtil.isNull(paramJSONObject, "scrollIndicator")) {
/*  94 */         localViewOptions.mScrollIndicator = JSONUtil.getString(paramJSONObject, "scrollIndicator");
/*     */       }
/*     */ 
/*  97 */       if (!paramJSONObject.isNull("background")) {
/*     */         try {
/*  99 */           String str1 = JSONUtil.getString(paramJSONObject, "background").toLowerCase();
/* 100 */           localViewOptions.strBackground = str1;
/* 101 */           localViewOptions.background = PdrUtil.stringToColor(str1);
/*     */         } catch (Exception localException1) {
/* 103 */           localException1.printStackTrace();
/*     */         }
/*     */       }
/*     */ 
/* 107 */       if (!paramJSONObject.isNull("mask")) {
/*     */         try {
/* 109 */           String str2 = JSONUtil.getString(paramJSONObject, "mask").toLowerCase();
/* 110 */           localViewOptions.maskColor = PdrUtil.stringToColor(str2);
/*     */         } catch (Exception localException2) {
/* 112 */           localException2.printStackTrace();
/*     */         }
/*     */       }
/* 115 */       if (!paramJSONObject.isNull("cachemode")) {
/* 116 */         localViewOptions.mCacheMode = JSONUtil.getString(paramJSONObject, "cachemode");
/*     */       }
/* 118 */       localViewOptions.mUseHardwave = PdrUtil.parseBoolean(JSONUtil.getString(paramJSONObject, "hardwareAccelerated"), localViewOptions.mUseHardwave, false);
/* 119 */       localViewOptions.opacity = PdrUtil.parseFloat(JSONUtil.getString(paramJSONObject, "opacity"), localViewOptions.opacity);
/* 120 */       localViewOptions.scalable = PdrUtil.parseBoolean(JSONUtil.getString(paramJSONObject, "scalable"), localViewOptions.scalable, false);
/* 121 */       localViewOptions.transition = JSONUtil.getJSONObject(paramJSONObject, "transition");
/* 122 */       localViewOptions.transform = JSONUtil.getJSONObject(paramJSONObject, "transform");
/* 123 */       localViewOptions.errorPage = JSONUtil.getString(paramJSONObject, "errorPage");
/* 124 */       localViewOptions.mInjection = JSONUtil.getString(paramJSONObject, "injection");
/* 125 */       localViewOptions.mPlusrequire = paramJSONObject.optString("plusrequire", localViewOptions.mPlusrequire);
/* 126 */       localViewOptions.popGesture = paramJSONObject.optString("popGesture", localViewOptions.popGesture);
/* 127 */       localViewOptions.mGeoInject = paramJSONObject.optString("geolocation", localViewOptions.mGeoInject);
/* 128 */       String str3 = JSONUtil.getString(paramJSONObject, "bounce");
/* 129 */       if (("vertical".equalsIgnoreCase(str3)) || ("horizontal".equalsIgnoreCase(str3)) || ("all".equalsIgnoreCase(str3)))
/* 130 */         localViewOptions.mBounce = true;
/*     */       else {
/* 132 */         localViewOptions.mBounce = false;
/*     */       }
/* 134 */       localViewOptions.mVideoFullscree = JSONUtil.getString(paramJSONObject, "videoFullscreen");
/* 135 */       if (paramJSONObject.has("navigationbar")) {
/* 136 */         JSONUtil.combinJSONObject(localViewOptions.navigationbar, JSONUtil.getJSONObject(paramJSONObject, "navigationbar"));
/*     */       }
/*     */     }
/* 139 */     return bool;
/*     */   }
/*     */ 
/*     */   public String getScrollIndicator() {
/* 143 */     return this.mScrollIndicator;
/*     */   }
/*     */ 
/*     */   public static ViewOptions createViewOptionsData(ViewOptions paramViewOptions, ViewRect paramViewRect)
/*     */   {
/* 152 */     return createViewOptionsData(paramViewOptions, null, paramViewRect);
/*     */   }
/*     */   public static ViewOptions createViewOptionsData(ViewOptions paramViewOptions, ViewRect paramViewRect1, ViewRect paramViewRect2) {
/* 155 */     ViewOptions localViewOptions = null;
/* 156 */     if (paramViewOptions != null) {
/* 157 */       localViewOptions = new ViewOptions();
/* 158 */       if (paramViewRect1 != null) localViewOptions.setFrameParentViewRect(paramViewRect1);
/* 159 */       localViewOptions.mWebviewScale = paramViewOptions.mWebviewScale;
/* 160 */       localViewOptions.setParentViewRect(paramViewRect2);
/* 161 */       localViewOptions.updateViewData(paramViewOptions.mJsonViewOption);
/*     */     }
/* 163 */     return localViewOptions;
/*     */   }
/*     */ 
/*     */   public static String checkOptionsErrorPage(IWebview paramIWebview, String paramString)
/*     */   {
/*     */     String str1;
/* 173 */     if ((!TextUtils.isEmpty(paramString)) && (!URLUtil.isNetworkUrl(paramString))) {
/* 174 */       if (!"none".equals(paramString)) {
/* 175 */         str1 = paramIWebview.obtainApp().convert2AbsFullPath(paramIWebview.obtainFullUrl(), paramString);
/* 176 */         if (!new File(str1).exists()) {
/* 177 */           String str2 = paramIWebview.obtainApp().obtainConfigProperty("error");
/* 178 */           if (!"none".equals(str2))
/* 179 */             paramString = paramIWebview.obtainApp().convert2WebviewFullPath(null, str2);
/*     */           else
/* 181 */             paramString = "none";
/*     */         }
/*     */         else {
/* 184 */           paramString = paramIWebview.obtainApp().convert2WebviewFullPath(paramIWebview.obtainFullUrl(), paramString);
/*     */         }
/*     */       }
/*     */     } else {
/* 188 */       str1 = paramIWebview.obtainApp().obtainConfigProperty("error");
/* 189 */       if (!"none".equals(str1))
/* 190 */         paramString = paramIWebview.obtainApp().convert2WebviewFullPath(null, str1);
/*     */       else {
/* 192 */         paramString = "none";
/*     */       }
/*     */     }
/* 195 */     return paramString;
/*     */   }
/*     */ 
/*     */   public void setDragData(JSONObject paramJSONObject1, JSONObject paramJSONObject2, IFrameView paramIFrameView1, IFrameView paramIFrameView2, String paramString, View paramView)
/*     */   {
/*     */     try
/*     */     {
/* 202 */       if (null == this.dragData) {
/* 203 */         this.dragData = new HashMap();
/*     */       }
/* 205 */       DragBean localDragBean = new DragBean();
/* 206 */       localDragBean.dragCurrentViewOp = paramJSONObject1;
/* 207 */       localDragBean.dragBindViewOp = paramJSONObject2;
/* 208 */       localDragBean.dragBindWebView = paramIFrameView1;
/* 209 */       localDragBean.dragCallBackWebView = paramIFrameView2;
/* 210 */       localDragBean.dragCbId = paramString;
/* 211 */       localDragBean.nativeView = paramView;
/* 212 */       if (paramJSONObject1.has("direction")) {
/* 213 */         String str = paramJSONObject1.getString("direction");
/* 214 */         if (!TextUtils.isEmpty(str))
/* 215 */           this.dragData.put(str.toLowerCase(), localDragBean);
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {
/* 219 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.ViewOptions
 * JD-Core Version:    0.6.2
 */