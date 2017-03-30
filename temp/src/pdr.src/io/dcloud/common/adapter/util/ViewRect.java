/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.util.ArrayList;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class ViewRect
/*     */ {
/*  14 */   private ViewRect mParentViewRect = null;
/*     */ 
/*     */   /** @deprecated */
/*  21 */   private ViewRect mFrameParentViewRect = null;
/*     */ 
/*  23 */   private ArrayList<ViewRect> mRelViewRectDockSet = null;
/*  24 */   public float mWebviewScale = 1.0F;
/*     */   public int left;
/*     */   public int top;
/*     */   public int width;
/*     */   public int height;
/*     */   public static final int DEFAULT_MARGIN = 0;
/*  27 */   public String margin = String.valueOf(0);
/*     */   public int right;
/*     */   public int bottom;
/*  30 */   public static byte POSITION_STATIC = 0;
/*  31 */   public static byte POSITION_ABSOLUTE = 1;
/*  32 */   public static byte POSITION_DOCK = 2;
/*  33 */   public static byte DOCK_LEFT = 3;
/*  34 */   public static byte DOCK_RIGHT = 4;
/*  35 */   public static byte DOCK_TOP = 5;
/*  36 */   public static byte DOCK_BOTTOM = 6;
/*     */ 
/*  38 */   private byte mPosition = POSITION_STATIC;
/*  39 */   private byte mDock = DOCK_TOP;
/*     */ 
/*  41 */   public JSONObject mJsonViewOption = JSONUtil.createJSONObject("{}");
/*     */ 
/*  43 */   private boolean doUpdate = false;
/*     */ 
/*  45 */   public boolean allowUpdate = true;
/*     */ 
/*     */   public void setUpdateAction(boolean paramBoolean)
/*     */   {
/*  50 */     this.doUpdate = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean updateViewData(JSONObject paramJSONObject, int paramInt1, int paramInt2) {
/*  54 */     return updateViewData(paramJSONObject, paramInt1, paramInt2, this.mWebviewScale);
/*     */   }
/*     */ 
/*     */   public boolean updateViewData(JSONObject paramJSONObject, int paramInt1, int paramInt2, float paramFloat)
/*     */   {
/*  64 */     boolean bool1 = false;
/*  65 */     if (this.mJsonViewOption != null) {
/*  66 */       ViewRect localViewRect = this;
/*  67 */       if (localViewRect.mJsonViewOption != null) {
/*  68 */         JSONUtil.combinJSONObject(localViewRect.mJsonViewOption, paramJSONObject);
/*  69 */         paramJSONObject = localViewRect.mJsonViewOption;
/*     */       } else {
/*  71 */         localViewRect.mJsonViewOption = paramJSONObject;
/*     */       }
/*  73 */       int i = this.left;
/*  74 */       int j = this.top;
/*  75 */       int k = this.width;
/*  76 */       int m = this.height;
/*     */ 
/*  78 */       int n = (this.doUpdate) || (!paramJSONObject.isNull("left")) ? 1 : 0;
/*  79 */       int i1 = (this.doUpdate) || (!paramJSONObject.isNull("right")) ? 1 : 0;
/*  80 */       int i2 = (this.doUpdate) || (!paramJSONObject.isNull("top")) ? 1 : 0;
/*  81 */       int i3 = (this.doUpdate) || (!paramJSONObject.isNull("width")) ? 1 : 0;
/*  82 */       int i4 = (this.doUpdate) || (!paramJSONObject.isNull("height")) ? 1 : 0;
/*  83 */       int i5 = (this.doUpdate) || (!paramJSONObject.isNull("bottom")) ? 1 : 0;
/*     */ 
/*  85 */       localViewRect.left = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "left"), paramInt1, 0, paramFloat);
/*  86 */       localViewRect.top = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "top"), paramInt2, 0, paramFloat);
/*  87 */       localViewRect.width = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "width"), paramInt1, i3 != 0 ? localViewRect.width : paramInt1, paramFloat);
/*  88 */       localViewRect.height = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "height"), paramInt2, i4 != 0 ? localViewRect.height : paramInt2, paramFloat);
/*  89 */       localViewRect.right = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "right"), paramInt1, localViewRect.right, paramFloat);
/*  90 */       localViewRect.bottom = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "bottom"), paramInt2, localViewRect.bottom, paramFloat);
/*     */ 
/*  93 */       boolean bool2 = false;
/*  94 */       if (!paramJSONObject.isNull("margin")) {
/*  95 */         localViewRect.margin = JSONUtil.getString(paramJSONObject, "margin");
/*  96 */         bool2 = PdrUtil.isEquals("auto", localViewRect.margin);
/*     */       }
/*     */ 
/*  99 */       if (n == 0) {
/* 100 */         if ((i3 == 0) && (i1 != 0))
/*     */         {
/* 102 */           localViewRect.left = (-localViewRect.right);
/* 103 */         } else if ((i3 != 0) && (i1 == 0) && (bool2))
/* 104 */           localViewRect.left = ((paramInt1 - localViewRect.width) / 2);
/* 105 */         else if ((i3 != 0) && (i1 != 0)) {
/* 106 */           localViewRect.left = (paramInt1 - localViewRect.width - localViewRect.right);
/*     */         }
/*     */       }
/* 109 */       else if ((i3 == 0) && (i1 != 0)) {
/* 110 */         localViewRect.width = (paramInt1 - localViewRect.left - localViewRect.right);
/*     */       }
/*     */ 
/* 114 */       if (i2 == 0) {
/* 115 */         if ((i4 == 0) && (i5 != 0))
/*     */         {
/* 117 */           localViewRect.top = (-localViewRect.bottom);
/* 118 */         } else if ((i4 != 0) && (i5 == 0) && (bool2))
/* 119 */           localViewRect.top = ((paramInt2 - localViewRect.height) / 2);
/* 120 */         else if ((i4 != 0) && (i5 != 0)) {
/* 121 */           localViewRect.top = (paramInt2 - localViewRect.height - localViewRect.bottom);
/*     */         }
/*     */       }
/* 124 */       else if ((i4 == 0) && (i5 != 0)) {
/* 125 */         localViewRect.height = (paramInt2 - localViewRect.top - localViewRect.bottom);
/*     */       }
/*     */ 
/* 129 */       layoutWithRelViewRect();
/* 130 */       layoutDockViewRect(this.mParentViewRect, this, false);
/* 131 */       bool1 = (i != localViewRect.left) || (j != localViewRect.top) || (m != localViewRect.height) || (k != localViewRect.width);
/*     */     }
/* 133 */     return bool1;
/*     */   }
/*     */   public boolean hasRectChanged(ViewRect paramViewRect1, ViewRect paramViewRect2) {
/* 136 */     return (paramViewRect1.left != paramViewRect2.left) || (paramViewRect1.top != paramViewRect2.top) || (paramViewRect1.height != paramViewRect2.height) || (paramViewRect1.width != paramViewRect2.width);
/*     */   }
/*     */ 
/*     */   public void putRelViewRect(ViewRect paramViewRect) {
/* 140 */     if (this.mRelViewRectDockSet == null) this.mRelViewRectDockSet = new ArrayList();
/* 141 */     this.mRelViewRectDockSet.add(paramViewRect);
/*     */   }
/*     */ 
/*     */   public void delRelViewRect(ViewRect paramViewRect) {
/* 145 */     if (this.mRelViewRectDockSet != null) this.mRelViewRectDockSet.remove(paramViewRect); 
/*     */   }
/*     */ 
/*     */   private void layoutWithRelViewRect()
/*     */   {
/* 149 */     if (this.mRelViewRectDockSet == null) return;
/* 150 */     for (ViewRect localViewRect : this.mRelViewRectDockSet)
/* 151 */       layoutDockViewRect(this, localViewRect);
/*     */   }
/*     */ 
/*     */   public static void layoutDockViewRect(ViewRect paramViewRect1, ViewRect paramViewRect2, boolean paramBoolean)
/*     */   {
/* 161 */     int i = 0;
/* 162 */     ViewRect localViewRect = paramViewRect2.mParentViewRect;
/* 163 */     JSONObject localJSONObject = paramViewRect2.mJsonViewOption;
/* 164 */     String str1 = JSONUtil.getString(localJSONObject, "position");
/* 165 */     if (!PdrUtil.isEmpty(str1)) {
/* 166 */       if ("absolute".equals(str1)) {
/* 167 */         paramViewRect2.mPosition = POSITION_ABSOLUTE;
/* 168 */       } else if ("dock".equals(str1)) {
/* 169 */         paramViewRect2.mPosition = POSITION_DOCK;
/* 170 */         i = 1;
/* 171 */       } else if ("static".equals(str1)) {
/* 172 */         paramViewRect2.mPosition = POSITION_STATIC;
/*     */       }
/*     */     }
/* 175 */     if (i == 0) return;
/* 176 */     String str2 = JSONUtil.getString(localJSONObject, "dock");
/* 177 */     if (!PdrUtil.isEmpty(str2)) {
/* 178 */       if ("bottom".equals(str2))
/* 179 */         paramViewRect2.mDock = DOCK_BOTTOM;
/* 180 */       else if ("top".equals(str2))
/* 181 */         paramViewRect2.mDock = DOCK_TOP;
/* 182 */       else if ("left".equals(str2))
/* 183 */         paramViewRect2.mDock = DOCK_LEFT;
/* 184 */       else if ("right".equals(str2)) {
/* 185 */         paramViewRect2.mDock = DOCK_RIGHT;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 190 */     String str3 = JSONUtil.getString(localJSONObject, "left");
/* 191 */     String str4 = JSONUtil.getString(localJSONObject, "top");
/* 192 */     String str5 = JSONUtil.getString(localJSONObject, "width");
/* 193 */     String str6 = JSONUtil.getString(localJSONObject, "height");
/* 194 */     int j = !PdrUtil.isEmpty(str3) ? 1 : 0;
/* 195 */     int k = !PdrUtil.isEmpty(str4) ? 1 : 0;
/* 196 */     int m = !PdrUtil.isEmpty(str5) ? 1 : 0;
/* 197 */     int n = !PdrUtil.isEmpty(str6) ? 1 : 0;
/* 198 */     paramViewRect2.width = PdrUtil.convertToScreenInt(str5, localViewRect.width, paramViewRect1.width, localViewRect.mWebviewScale);
/* 199 */     paramViewRect2.height = PdrUtil.convertToScreenInt(str6, localViewRect.height, paramViewRect1.height, localViewRect.mWebviewScale);
/*     */ 
/* 201 */     paramViewRect2.width = (paramViewRect2.width < 0 ? paramViewRect2.mParentViewRect.width : paramViewRect2.width);
/* 202 */     paramViewRect2.height = (paramViewRect2.height < 0 ? paramViewRect2.mParentViewRect.height : paramViewRect2.height);
/*     */ 
/* 204 */     int i1 = paramViewRect1.top; int i2 = paramViewRect1.left; int i3 = paramViewRect1.width; int i4 = paramViewRect1.height;
/*     */ 
/* 207 */     int i5 = PdrUtil.convertToScreenInt(str3, localViewRect.width, paramViewRect1.left, localViewRect.mWebviewScale);
/* 208 */     int i6 = PdrUtil.convertToScreenInt(str4, localViewRect.height, paramViewRect1.top, localViewRect.mWebviewScale);
/* 209 */     if (paramViewRect2.mDock == DOCK_BOTTOM) {
/* 210 */       if ((k == 0) || (n != 0)) {
/* 211 */         paramViewRect1.height -= paramViewRect2.height;
/* 212 */         i6 = paramViewRect1.height + paramViewRect1.top;
/*     */       } else {
/* 214 */         paramViewRect1.height = (i6 - paramViewRect1.top);
/* 215 */         paramViewRect2.height -= i6;
/*     */       }
/* 217 */     } else if (paramViewRect2.mDock == DOCK_RIGHT) {
/* 218 */       if ((j == 0) || (m != 0)) {
/* 219 */         paramViewRect1.width -= paramViewRect2.width;
/* 220 */         i5 = paramViewRect1.width + paramViewRect1.left;
/*     */       } else {
/* 222 */         paramViewRect1.width = (i5 - paramViewRect1.left);
/* 223 */         paramViewRect2.width -= i5;
/*     */       }
/* 225 */     } else if (paramViewRect2.mDock == DOCK_LEFT) {
/* 226 */       i5 = 0;
/* 227 */       j = 0;
/*     */ 
/* 229 */       paramViewRect1.left = (i5 + paramViewRect2.width);
/* 230 */       paramViewRect1.width -= paramViewRect2.width;
/*     */     }
/* 236 */     else if (paramViewRect2.mDock == DOCK_TOP) {
/* 237 */       i6 = 0;
/* 238 */       k = 0;
/*     */ 
/* 240 */       paramViewRect1.top = (i6 + paramViewRect2.height);
/* 241 */       paramViewRect1.height -= paramViewRect2.height;
/*     */     }
/*     */ 
/* 248 */     if (!paramBoolean) {
/* 249 */       paramViewRect1.left = i2;
/* 250 */       paramViewRect1.top = i1;
/* 251 */       paramViewRect1.width = i3;
/* 252 */       paramViewRect1.height = i4;
/*     */     }
/* 254 */     paramViewRect2.left = i5;
/* 255 */     paramViewRect2.top = i6;
/*     */   }
/*     */ 
/*     */   public static void layoutDockViewRect(ViewRect paramViewRect1, ViewRect paramViewRect2)
/*     */   {
/* 264 */     layoutDockViewRect(paramViewRect1, paramViewRect2, true);
/*     */   }
/*     */ 
/*     */   public void updateViewData(ViewRect paramViewRect)
/*     */   {
/* 271 */     this.mWebviewScale = paramViewRect.mWebviewScale;
/* 272 */     this.left = paramViewRect.left;
/* 273 */     this.top = paramViewRect.top;
/* 274 */     this.width = paramViewRect.width;
/* 275 */     this.height = paramViewRect.height;
/* 276 */     this.right = paramViewRect.right;
/* 277 */     this.bottom = paramViewRect.bottom;
/* 278 */     updateViewData(paramViewRect.mJsonViewOption);
/*     */   }
/*     */ 
/*     */   public void onScreenChanged(int paramInt1, int paramInt2)
/*     */   {
/* 286 */     updateViewData(this.mJsonViewOption, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public boolean updateViewData(JSONObject paramJSONObject)
/*     */   {
/* 294 */     int i = this.mParentViewRect.width;
/* 295 */     int j = this.mParentViewRect.height;
/* 296 */     return updateViewData(paramJSONObject, i, j);
/*     */   }
/*     */ 
/*     */   public void onScreenChanged()
/*     */   {
/* 302 */     updateViewData(this.mJsonViewOption);
/*     */   }
/*     */ 
/*     */   public void setParentViewRect(ViewRect paramViewRect)
/*     */   {
/* 310 */     this.mParentViewRect = paramViewRect;
/*     */   }
/*     */ 
/*     */   public ViewRect getParentViewRect()
/*     */   {
/* 317 */     return this.mParentViewRect;
/*     */   }
/*     */ 
/*     */   public void setFrameParentViewRect(ViewRect paramViewRect)
/*     */   {
/* 322 */     this.mFrameParentViewRect = paramViewRect;
/*     */ 
/* 324 */     this.mFrameParentViewRect = null;
/*     */   }
/*     */ 
/*     */   public void commitUpdate2JSONObject(boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 333 */     int i = this.mFrameParentViewRect != null ? this.mFrameParentViewRect.width : this.mParentViewRect.width;
/* 334 */     int j = this.mFrameParentViewRect != null ? this.mFrameParentViewRect.height : this.mParentViewRect.height;
/* 335 */     checkValueIsPercentage("left", this.left, i, paramBoolean1, paramBoolean2);
/* 336 */     checkValueIsPercentage("top", this.top, j, paramBoolean1, paramBoolean2);
/* 337 */     checkValueIsPercentage("width", this.width, i, paramBoolean1, paramBoolean2);
/* 338 */     checkValueIsPercentage("height", this.height, j, paramBoolean1, paramBoolean2);
/* 339 */     checkValueIsPercentage("right", this.right, i, paramBoolean1, paramBoolean2);
/* 340 */     checkValueIsPercentage("bottom", this.bottom, j, paramBoolean1, paramBoolean2);
/*     */   }
/*     */ 
/*     */   public void commitUpdate2JSONObject()
/*     */   {
/* 346 */     commitUpdate2JSONObject(false, false);
/*     */   }
/*     */   public void checkValueIsPercentage(String paramString, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
/*     */     try {
/* 350 */       if ((!this.mJsonViewOption.isNull(paramString)) || (paramBoolean1))
/* 351 */         if (((!this.mJsonViewOption.isNull(paramString)) && (this.mJsonViewOption.getString(paramString).indexOf("%") >= 0)) || (paramBoolean2))
/*     */         {
/* 353 */           if (paramInt2 > 0) {
/* 354 */             this.mJsonViewOption.put(paramString, paramInt1 * 100 / paramInt2 + "%");
/*     */           }
/*     */         }
/*     */         else
/* 358 */           this.mJsonViewOption.put(paramString, paramInt1 / this.mWebviewScale);
/*     */     }
/*     */     catch (JSONException localJSONException)
/*     */     {
/* 362 */       localJSONException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.ViewRect
 * JD-Core Version:    0.6.2
 */