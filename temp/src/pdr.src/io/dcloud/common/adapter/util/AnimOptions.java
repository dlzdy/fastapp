/*     */ package io.dcloud.common.adapter.util;
/*     */ 
/*     */ import android.view.animation.AccelerateDecelerateInterpolator;
/*     */ import android.view.animation.AccelerateInterpolator;
/*     */ import android.view.animation.AlphaAnimation;
/*     */ import android.view.animation.AnimationSet;
/*     */ import android.view.animation.DecelerateInterpolator;
/*     */ import android.view.animation.LinearInterpolator;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.nineoldandroids.animation.Animator;
/*     */ import io.dcloud.nineoldandroids.animation.AnimatorSet;
/*     */ import io.dcloud.nineoldandroids.animation.ObjectAnimator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class AnimOptions
/*     */ {
/*     */   static final String TAG = "AnimOptions";
/*     */   public static final byte OPTION_SHOW = 0;
/*     */   public static final byte OPTION_CLOSE = 1;
/*     */   public static final byte OPTION_UPDATE = 2;
/*     */   public static final byte OPTION_HIDE = 3;
/*     */   public static final byte OPTION_HIDE_SHOW = 4;
/*     */   public static final String TF_LINEAR = "linear";
/*     */   public static final String TF_EASE_IN = "ease-in";
/*     */   public static final String TF_EASE_OUT = "ease-out";
/*     */   public static final String TF_EASE_IN_OUT = "ease-in-out";
/*     */   public static final String ANIM_NONE = "none";
/*     */   public static final String ANIM_SLIDE_IN_RIGHT = "slide-in-right";
/*     */   public static final String ANIM_SLIDE_OUT_RIGHT = "slide-out-right";
/*     */   public static final String ANIM_SLIDE_IN_LEFT = "slide-in-left";
/*     */   public static final String ANIM_SLIDE_OUT_LEFT = "slide-out-left";
/*     */   public static final String ANIM_SLIDE_IN_TOP = "slide-in-top";
/*     */   public static final String ANIM_SLIDE_OUT_TOP = "slide-out-top";
/*     */   public static final String ANIM_SLIDE_OUT_BOTTOM = "slide-out-bottom";
/*     */   public static final String ANIM_SLIDE_IN_BOTTOM = "slide-in-bottom";
/*     */   public static final String ANIM_ZOOM_OUT = "zoom-out";
/*     */   public static final String ANIM_ZOOM_IN = "zoom-in";
/*     */   public static final String ANIM_ZOOM_FADE_OUT = "zoom-fade-out";
/*     */   public static final String ANIM_ZOOM_FADE_IN = "zoom-fade-in";
/*     */   public static final String ANIM_FADE_IN = "fade-in";
/*     */   public static final String ANIM_FADE_OUT = "fade-out";
/*     */   public static final String ANIM_FLIP_RX = "flip-rx";
/*     */   public static final String ANIM_FLIP_X = "flip-x";
/*     */   public static final String ANIM_FLIP_Y = "flip-y";
/*     */   public static final String ANIM_FLIP_RY = "flip-ry";
/*     */   public static final String ANIM_PAGE_FORWARD = "page-forward";
/*     */   public static final String ANIM_PAGE_BACKWARD = "page-backward";
/*     */   public static final String ANIM_POP_IN = "pop-in";
/*     */   public static final String ANIM_POP_OUT = "pop-out";
/*     */   public int sScreenWidth;
/*     */   public int sScreenHeight;
/*     */   public String timingfunction;
/*     */   public int duration;
/*     */   public int duration_show;
/*     */   public int duration_close;
/*     */   public String translate;
/*     */   public String scale;
/*     */   public String opacity;
/*     */   public String rotate;
/*     */   public String mAnimType;
/*     */   public String mAnimType_close;
/*     */   public AnimMode mAnimMode;
/*     */   public byte mOption;
/*     */   public ArrayList<String> mStartCallback;
/*     */   public ArrayList<String> mEndCallback;
/*     */   public AdaFrameItem mUserFrameItem;
/*     */   public AdaFrameItem mRelFrameItem;
/*     */   public IFrameView mHostFrame;
/*     */   public AnimationSet mAnimator;
/*     */   private static final int ANIM_TIME = 200;
/* 419 */   public static final HashMap<String, String> mAnimTypes = new HashMap(12);
/*     */ 
/*     */   public AnimOptions()
/*     */   {
/*  66 */     this.timingfunction = "linear";
/*  67 */     this.duration = 200;
/*  68 */     this.duration_show = 200;
/*  69 */     this.duration_close = 200;
/*  70 */     this.translate = "";
/*  71 */     this.scale = "";
/*  72 */     this.opacity = "";
/*  73 */     this.rotate = "";
/*     */ 
/*  75 */     this.mAnimType = "none";
/*     */ 
/*  81 */     this.mOption = 0;
/*  82 */     this.mStartCallback = null;
/*  83 */     this.mEndCallback = null;
/*     */ 
/*  91 */     this.mAnimator = null;
/*     */   }
/*  93 */   public Animator createAnimation() { Object localObject1 = null;
/*     */ 
/*  95 */     this.mAnimator = null;
/*     */     try {
/*  97 */       if ((this.mUserFrameItem.obtainFrameOptions_Animate() == null) && (this.mOption != 2)) {
/*  98 */         this.mUserFrameItem.makeViewOptions_animate();
/*     */       }
/* 100 */       if (this.mAnimMode != AnimMode.CUSTOM)
/*     */       {
/* 103 */         if (2 == this.mOption) {
/* 104 */           Object localObject2 = null;
/* 105 */           boolean bool = isUseBackground();
/* 106 */           if (bool)
/* 107 */             localObject2 = ((IFrameView)this.mUserFrameItem).obtainWebviewParent();
/*     */           else {
/* 109 */             localObject2 = this.mUserFrameItem;
/*     */           }
/* 111 */           localObject1 = setStyleOptionAnimator((AdaFrameItem)localObject2, ((AdaFrameItem)localObject2).obtainFrameOptions(), ((AdaFrameItem)localObject2).obtainFrameOptions_Animate(), ((AdaFrameItem)localObject2).obtainFrameOptions_Birth());
/* 112 */         } else if ((0 == this.mOption) || (this.mOption == 4)) {
/* 113 */           localObject1 = showOrHideShowAnimator(this.mUserFrameItem, this.mUserFrameItem.obtainFrameOptions(), this.mUserFrameItem.obtainFrameOptions_Animate(), this.mUserFrameItem.obtainFrameOptions_Birth());
/* 114 */         } else if ((1 == this.mOption) || (3 == this.mOption)) {
/* 115 */           localObject1 = closeOrHideAnimator(this.mUserFrameItem, this.mUserFrameItem.obtainFrameOptions(), this.mUserFrameItem.obtainFrameOptions_Animate(), this.mUserFrameItem.obtainFrameOptions_Birth());
/*     */         }
/*     */       }
/*     */     } catch (Exception localException) {
/* 119 */       localException.printStackTrace();
/*     */     }
/* 121 */     return localObject1; }
/*     */ 
/*     */   boolean isUseBackground()
/*     */   {
/* 125 */     return this.mUserFrameItem.obtainFrameOptions().background != -1;
/*     */   }
/*     */ 
/*     */   public void parseTransition(JSONObject paramJSONObject)
/*     */   {
/* 130 */     String str = JSONUtil.getString(paramJSONObject, "duration");
/* 131 */     if ((str != null) && (str.toLowerCase().endsWith("ms"))) {
/* 132 */       str = str.substring(0, str.length() - 2);
/*     */     }
/* 134 */     this.duration = PdrUtil.parseInt(str, this.duration);
/* 135 */     this.timingfunction = JSONUtil.getString(paramJSONObject, "timingfunction");
/*     */   }
/*     */ 
/*     */   private void setTimingFunction(Animator paramAnimator) {
/* 139 */     String str = (PdrUtil.isEmpty(this.timingfunction) ? "linear" : this.timingfunction).toLowerCase();
/*     */ 
/* 150 */     Logger.d("AnimOptions", "timingfunction = " + str);
/* 151 */     if (PdrUtil.isEquals("ease-in", str))
/*     */     {
/* 153 */       AccelerateInterpolator localAccelerateInterpolator = new AccelerateInterpolator(1.5F);
/* 154 */       paramAnimator.setInterpolator(localAccelerateInterpolator);
/* 155 */     } else if (PdrUtil.isEquals("ease-out", str)) {
/* 156 */       paramAnimator.setInterpolator(new DecelerateInterpolator(1.5F));
/* 157 */     } else if (PdrUtil.isEquals("linear", str)) {
/* 158 */       paramAnimator.setInterpolator(new LinearInterpolator());
/*     */     } else {
/* 160 */       paramAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void parseTransform(JSONObject paramJSONObject)
/*     */   {
/*     */   }
/*     */ 
/*     */   private final Animator setStyleOptionAnimator(AdaFrameItem paramAdaFrameItem, ViewOptions paramViewOptions1, ViewOptions paramViewOptions2, ViewOptions paramViewOptions3)
/*     */   {
/* 171 */     AnimatorSet localAnimatorSet = new AnimatorSet();
/* 172 */     int i = paramViewOptions1.left; int j = paramViewOptions1.top; int k = paramViewOptions1.width; int m = paramViewOptions1.height;
/* 173 */     int n = paramViewOptions2.left; int i1 = paramViewOptions2.top; int i2 = paramViewOptions2.width; int i3 = paramViewOptions2.height;
/*     */ 
/* 175 */     int i4 = (k != i2) || (m != i3) ? 1 : 0;
/* 176 */     Logger.d("Animation_Path", "createAnimSet_update _oldX=" + i + ";_oldY=" + j + ";_newX=" + n + ";_newY=" + i1);
/* 177 */     if ((i != n) || (j != i1)) {
/* 178 */       boolean bool = isUseBackground();
/* 179 */       if (!bool) {
/* 180 */         Logger.d("Animation_Path", "createAnimSet_update not webview mode fromXDelta=" + i + ";toXDelta=" + n + ";fromYDelta=" + j + ";toYDelta=" + i1);
/*     */ 
/* 182 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { i, n });
/* 183 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "y", new float[] { j, i1 });
/*     */       } else {
/* 185 */         Logger.d("Animation_Path", "createAnimSet_update not webview mode fromXDelta=" + i + ";toXDelta=" + n + ";fromYDelta=" + j + ";toYDelta=" + i1);
/* 186 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { i, n });
/* 187 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "y", new float[] { j, i1 });
/*     */       }
/*     */     }
/* 190 */     if (i4 != 0) {
/* 191 */       float f1 = k / this.sScreenWidth;
/* 192 */       float f2 = i2 / this.sScreenWidth;
/* 193 */       float f3 = m / this.sScreenHeight;
/* 194 */       float f4 = i3 / this.sScreenHeight;
/* 195 */       Logger.d("Animation_Path", "width (" + f1 + ";=" + f2 + ");height(" + f3 + "," + f4 + ")");
/* 196 */       ofInt(localAnimatorSet, paramAdaFrameItem, "width", new int[] { k, i2 });
/* 197 */       ofInt(localAnimatorSet, paramAdaFrameItem, "height", new int[] { m, i3 });
/*     */     }
/*     */ 
/* 205 */     setTimingFunction(localAnimatorSet);
/* 206 */     localAnimatorSet.setDuration(this.duration);
/* 207 */     return localAnimatorSet;
/*     */   }
/*     */ 
/*     */   private final AnimatorSet showOrHideShowAnimator(AdaFrameItem paramAdaFrameItem, ViewOptions paramViewOptions1, ViewOptions paramViewOptions2, ViewOptions paramViewOptions3)
/*     */   {
/* 212 */     String str = PdrUtil.isEmpty(this.mAnimType) ? "none" : this.mAnimType;
/* 213 */     AnimatorSet localAnimatorSet = new AnimatorSet();
/* 214 */     Logger.d("Animation_Path", "showOrHideShowAnimator _animType=" + str);
/* 215 */     if (PdrUtil.isEquals(str, "zoom-out"))
/*     */     {
/* 218 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "scaleX", new float[] { 0.0F, 1.0F });
/* 219 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "scaleY", new float[] { 0.0F, 1.0F });
/* 220 */     } else if ((!PdrUtil.isEquals(str, "page-forward")) && 
/* 221 */       (PdrUtil.isEquals(str, "zoom-fade-out")))
/*     */     {
/* 228 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "scaleX", new float[] { 0.8F, 1.0F });
/* 229 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "scaleY", new float[] { 0.8F, 1.0F });
/* 230 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "alpha", new float[] { 0.0F, 1.0F });
/* 231 */     }if (PdrUtil.isEquals(str, "fade-in"))
/*     */     {
/* 233 */       AnimationSet localAnimationSet = new AnimationSet(false);
/* 234 */       localAnimationSet.addAnimation(new AlphaAnimation(0.0F, 1.0F));
/* 235 */       this.mAnimator = localAnimationSet;
/* 236 */       this.mAnimator.setDuration(this.duration_show);
/*     */     }
/*     */     else {
/* 239 */       int i = paramViewOptions1.left; int j = paramViewOptions1.top;
/* 240 */       int k = paramViewOptions2.left; int m = paramViewOptions2.top;
/* 241 */       Logger.d("Animation_Path", "showOrHideShowAnimator _animType=" + str + ";fromXDelta=" + i + ";toXDelta=" + k + ";fromYDelta=" + j + ";toYDelta=" + m);
/*     */ 
/* 243 */       if (PdrUtil.isEquals(str, "flip-x"))
/*     */       {
/* 245 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "rotationX", new float[] { -90.0F, 0.0F });
/* 246 */       } else if (PdrUtil.isEquals(str, "flip-rx"))
/*     */       {
/* 248 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "rotationX", new float[] { 0.0F, 90.0F });
/* 249 */       } else if (PdrUtil.isEquals(str, "flip-y"))
/*     */       {
/* 251 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "rotationY", new float[] { -90.0F, 0.0F });
/* 252 */       } else if (PdrUtil.isEquals(str, "flip-ry"))
/*     */       {
/* 254 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "rotationY", new float[] { 0.0F, 90.0F });
/*     */       }
/* 256 */       else if ((PdrUtil.isEquals(str, "slide-in-right")) || (PdrUtil.isEquals(str, "pop-in")))
/*     */       {
/* 258 */         if (isUseBackground())
/*     */         {
/* 260 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { i, k });
/*     */         }
/*     */         else
/*     */         {
/* 268 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { i, k });
/*     */         }
/* 270 */       } else if (PdrUtil.isEquals(str, "slide-in-left"))
/*     */       {
/* 272 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { i, k });
/* 273 */       } else if (PdrUtil.isEquals(str, "slide-in-top"))
/*     */       {
/* 275 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "y", new float[] { j, m });
/* 276 */       } else if (PdrUtil.isEquals(str, "slide-in-bottom"))
/*     */       {
/* 278 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "y", new float[] { j, m });
/*     */       }
/*     */     }
/*     */ 
/* 282 */     setTimingFunction(localAnimatorSet);
/*     */ 
/* 288 */     localAnimatorSet.setDuration(this.duration_show);
/* 289 */     return localAnimatorSet;
/*     */   }
/*     */   ObjectAnimator ofFloat(AnimatorSet paramAnimatorSet, Object paramObject, String paramString, float[] paramArrayOfFloat) {
/* 292 */     if (paramArrayOfFloat[0] != paramArrayOfFloat[1]) {
/* 293 */       ObjectAnimator localObjectAnimator = new ObjectAnimator();
/* 294 */       localObjectAnimator.setPropertyName(paramString);
/* 295 */       localObjectAnimator.setFloatValues(paramArrayOfFloat);
/* 296 */       paramAnimatorSet.playTogether(new Animator[] { localObjectAnimator });
/* 297 */       return localObjectAnimator;
/*     */     }
/* 299 */     return null;
/*     */   }
/*     */   ObjectAnimator ofInt(AnimatorSet paramAnimatorSet, Object paramObject, String paramString, int[] paramArrayOfInt) {
/* 302 */     if (paramArrayOfInt[0] != paramArrayOfInt[1]) {
/* 303 */       ObjectAnimator localObjectAnimator = new ObjectAnimator();
/* 304 */       localObjectAnimator.setPropertyName(paramString);
/* 305 */       localObjectAnimator.setIntValues(paramArrayOfInt);
/* 306 */       paramAnimatorSet.playTogether(new Animator[] { localObjectAnimator });
/* 307 */       return localObjectAnimator;
/*     */     }
/* 309 */     return null;
/*     */   }
/*     */ 
/*     */   private final Animator closeOrHideAnimator(AdaFrameItem paramAdaFrameItem, ViewOptions paramViewOptions1, ViewOptions paramViewOptions2, ViewOptions paramViewOptions3) {
/* 313 */     String str = this.mAnimType_close;
/* 314 */     if (!mAnimTypes.containsValue(str)) {
/* 315 */       str = (String)mAnimTypes.get(this.mAnimType);
/*     */     }
/* 317 */     Logger.d("Animation_Path", "closeOrHideAnimator _animType=" + str);
/* 318 */     AnimatorSet localAnimatorSet = new AnimatorSet();
/* 319 */     if (PdrUtil.isEquals(str, "fade-out"))
/*     */     {
/* 321 */       AnimationSet localAnimationSet = new AnimationSet(false);
/* 322 */       localAnimationSet.addAnimation(new AlphaAnimation(1.0F, 0.0F));
/* 323 */       this.mAnimator = localAnimationSet;
/* 324 */       this.mAnimator.setDuration(this.duration_show);
/*     */     }
/* 326 */     else if (PdrUtil.isEquals(str, "zoom-in"))
/*     */     {
/* 328 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "scaleX", new float[] { 1.0F, 0.0F });
/* 329 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "scaleY", new float[] { 1.0F, 0.0F });
/* 330 */     } else if (PdrUtil.isEquals(str, "zoom-fade-in"))
/*     */     {
/* 335 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "scaleX", new float[] { 1.0F, 0.8F });
/* 336 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "scaleY", new float[] { 1.0F, 0.8F });
/* 337 */       ofFloat(localAnimatorSet, paramAdaFrameItem, "alpha", new float[] { 1.0F, 0.0F });
/* 338 */     } else if (!PdrUtil.isEquals(str, "page-backward"))
/*     */     {
/* 340 */       int i = paramViewOptions1.left; int j = paramViewOptions1.top;
/* 341 */       int k = paramViewOptions2.left; int m = paramViewOptions2.top;
/* 342 */       Logger.d("Animation_Path", "closeOrHideAnimator _animType=" + str + ";fromXDelta=" + i + ";toXDelta=" + k + ";fromYDelta=" + j + ";toYDelta=" + m);
/* 343 */       if (PdrUtil.isEquals(str, "flip-x"))
/*     */       {
/* 345 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "rotationX", new float[] { -90.0F, 0.0F });
/* 346 */       } else if (PdrUtil.isEquals(str, "flip-y"))
/*     */       {
/* 348 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "rotationY", new float[] { -90.0F, 0.0F });
/* 349 */       } else if (PdrUtil.isEquals(str, "flip-rx"))
/*     */       {
/* 351 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "rotationX", new float[] { 0.0F, 90.0F });
/* 352 */       } else if (PdrUtil.isEquals(str, "flip-ry"))
/*     */       {
/* 354 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "rotationY", new float[] { 0.0F, 90.0F });
/*     */       }
/* 356 */       else if ((PdrUtil.isEquals(str, "slide-out-right")) || (PdrUtil.isEquals(str, "pop-out")))
/*     */       {
/* 365 */         ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { i, k });
/* 366 */       } else if (PdrUtil.isEquals(str, "slide-out-left")) {
/* 367 */         if (isUseBackground())
/*     */         {
/* 369 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { 0.0F, -this.sScreenWidth });
/*     */         }
/*     */         else
/* 372 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { i, k });
/*     */       }
/* 374 */       else if (PdrUtil.isEquals(str, "slide-out-top")) {
/* 375 */         if (isUseBackground())
/*     */         {
/* 377 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "y", new float[] { this.sScreenHeight, 0.0F });
/*     */         }
/*     */         else {
/* 380 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "y", new float[] { j, m });
/*     */         }
/*     */ 
/*     */       }
/* 387 */       else if (PdrUtil.isEquals(str, "slide-out-bottom"))
/*     */       {
/* 393 */         if (isUseBackground())
/*     */         {
/* 395 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { i, i });
/* 396 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "y", new float[] { 0.0F, this.sScreenHeight });
/*     */         }
/*     */         else {
/* 399 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "x", new float[] { i, i });
/* 400 */           ofFloat(localAnimatorSet, paramAdaFrameItem, "y", new float[] { j, m });
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 405 */     setTimingFunction(localAnimatorSet);
/*     */ 
/* 411 */     localAnimatorSet.setDuration(this.duration_close);
/* 412 */     return localAnimatorSet;
/*     */   }
/*     */ 
/*     */   public static String getCloseAnimType(String paramString)
/*     */   {
/* 441 */     String str = paramString;
/* 442 */     if (mAnimTypes != null) {
/* 443 */       if (!mAnimTypes.containsValue(paramString)) {
/* 444 */         str = (String)mAnimTypes.get(paramString);
/*     */       }
/* 446 */       return str;
/*     */     }
/* 448 */     return null;
/*     */   }
/*     */ 
/*     */   public void setCloseAnimType(String paramString) {
/* 452 */     boolean bool = PdrUtil.isEquals("auto", paramString);
/* 453 */     if (bool) {
/* 454 */       this.mAnimType_close = ((String)mAnimTypes.get(this.mAnimType));
/*     */     } else {
/* 456 */       this.mAnimType_close = paramString;
/* 457 */       if (!mAnimTypes.containsValue(this.mAnimType_close))
/* 458 */         this.mAnimType_close = "none";
/*     */     }
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 421 */     mAnimTypes.put("slide-in-right", "slide-out-right");
/* 422 */     mAnimTypes.put("slide-in-left", "slide-out-left");
/* 423 */     mAnimTypes.put("slide-in-top", "slide-out-top");
/* 424 */     mAnimTypes.put("slide-in-bottom", "slide-out-bottom");
/* 425 */     mAnimTypes.put("zoom-out", "zoom-in");
/* 426 */     mAnimTypes.put("zoom-fade-out", "zoom-fade-in");
/* 427 */     mAnimTypes.put("fade-in", "fade-out");
/* 428 */     mAnimTypes.put("flip-x", "flip-rx");
/* 429 */     mAnimTypes.put("flip-rx", "flip-x");
/* 430 */     mAnimTypes.put("flip-y", "flip-ry");
/* 431 */     mAnimTypes.put("flip-ry", "flip-y");
/* 432 */     mAnimTypes.put("page-forward", "page-backward");
/* 433 */     mAnimTypes.put("none", "none");
/* 434 */     mAnimTypes.put("pop-in", "pop-out");
/*     */   }
/*     */ 
/*     */   static enum AnimMode
/*     */   {
/* 416 */     CUSTOM;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.AnimOptions
 * JD-Core Version:    0.6.2
 */