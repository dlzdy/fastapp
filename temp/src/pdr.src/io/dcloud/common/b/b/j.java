/*     */ package io.dcloud.common.b.b;
/*     */ 
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.animation.DecelerateInterpolator;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.ITypeofAble;
/*     */ import io.dcloud.common.adapter.util.AnimOptions;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.nineoldandroids.animation.Animator;
/*     */ import io.dcloud.nineoldandroids.animation.Animator.AnimatorListener;
/*     */ import io.dcloud.nineoldandroids.animation.ObjectAnimator;
/*     */ import io.dcloud.nineoldandroids.view.ViewHelper;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class j
/*     */ {
/*     */   public static View a;
/*     */   public static e b;
/*  33 */   private static boolean c = false;
/*     */ 
/*     */   public static void a(d paramd, int paramInt)
/*     */   {
/*  43 */     String str1 = paramd.getAnimOptions().mAnimType;
/*  44 */     String str2 = paramd.getAnimOptions().mAnimType_close;
/*  45 */     d locald = (d)paramd.l.findFrameViewB(paramd);
/*  46 */     if (locald == null) {
/*  47 */       return;
/*     */     }
/*     */ 
/*  50 */     if (paramInt == 1) {
/*  51 */       str2 = AnimOptions.getCloseAnimType(str2);
/*     */ 
/*  53 */       if ((paramd.mAccelerationType.equals("auto")) && (!PdrUtil.isEquals(str2, "pop-out")) && (!BaseInfo.isDefaultAim) && (locald.mSnapshot == null)) {
/*  54 */         return;
/*     */       }
/*     */ 
/*  57 */       if ((paramd.mAccelerationType.equals("none")) && (!PdrUtil.isEquals(str2, "pop-out")) && (locald.mSnapshot == null)) {
/*  58 */         return;
/*     */       }
/*  60 */       if ((!paramd.mAccelerationType.equals("none")) && (locald.mSnapshot == null))
/*  61 */         BaseInfo.sOpenedCount -= 1;
/*     */     }
/*     */     else
/*     */     {
/*  65 */       if ((paramd.mAccelerationType.equals("auto")) && (!PdrUtil.isEquals(str1, "pop-in")) && (!BaseInfo.isDefaultAim) && (locald.mSnapshot == null)) {
/*  66 */         return;
/*     */       }
/*     */ 
/*  69 */       if ((paramd.mAccelerationType.equals("none")) && (!PdrUtil.isEquals(str2, "pop-in")) && (locald.mSnapshot == null)) {
/*  70 */         return;
/*     */       }
/*  72 */       if ((!paramd.mAccelerationType.equals("none")) && (locald.mSnapshot == null)) {
/*  73 */         BaseInfo.sOpenedCount += 1;
/*  74 */         c = BaseInfo.sOpenedCount > 1;
/*     */       }
/*     */     }
/*     */ 
/*  78 */     if (locald != null) {
/*  79 */       a = locald.obtainMainView();
/*  80 */       locald.l.a(locald, paramd);
/*  81 */       locald.chkUseCaptureAnimation(true, locald.hashCode(), locald.mSnapshot != null);
/*     */ 
/*  83 */       if ((locald.mAnimationCapture) && (BaseInfo.sAnimationCaptureB) && (!a(locald))) {
/*  84 */         Logger.e("mabo", "B页面是否启用截图动画方案:true | " + locald.getAnimOptions().mAnimType);
/*  85 */         a(paramInt, locald, paramd);
/*     */       } else {
/*  87 */         Logger.e("mabo", "B页面是否启用截图动画方案:false | " + locald.getAnimOptions().mAnimType);
/*  88 */         b(paramInt, locald, paramd);
/*     */       }
/*     */     }
/*  91 */     if (BaseInfo.sOpenedCount == 0)
/*  92 */       c = false;
/*     */   }
/*     */ 
/*     */   public static boolean a(d paramd)
/*     */   {
/* 101 */     ViewGroup localViewGroup = (ViewGroup)paramd.obtainMainView();
/*     */ 
/* 103 */     int i = localViewGroup.getChildCount();
/* 104 */     for (int j = 0; j < i; j++) {
/* 105 */       if ((localViewGroup.getChildAt(j) instanceof ITypeofAble))
/* 106 */         return true;
/*     */     }
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   private static void a(int paramInt, d paramd1, d paramd2)
/*     */   {
/* 118 */     int i = paramd2.obtainApp().getInt(0);
/*     */ 
/* 120 */     int j = paramd1.m.mChildArrayList.size();
/*     */     int k;
/*     */     d locald;
/* 121 */     if ((paramInt == 0) || (c))
/*     */     {
/* 123 */       paramd1.m.setScrollIndicator("none");
/* 124 */       if (j != 0) {
/* 125 */         for (k = 0; k < j; k++) {
/* 126 */           if ((paramd1.m.mChildArrayList.get(k) instanceof d)) {
/* 127 */             locald = (d)paramd1.m.mChildArrayList.get(k);
/* 128 */             locald.m.setScrollIndicator("none");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 134 */     b = paramd2.l.a(paramd1, paramInt, c);
/* 135 */     if (b == null) {
/* 136 */       b(paramInt, paramd1, paramd2);
/* 137 */       return;
/*     */     }
/* 139 */     if ((paramInt == 0) || (c))
/*     */     {
/* 141 */       if (paramd1.p != null)
/* 142 */         paramd1.m.setScrollIndicator(paramd1.p.getScrollIndicator());
/* 143 */       if (j != 0)
/* 144 */         for (k = 0; k < j; k++)
/* 145 */           if ((paramd1.m.mChildArrayList.get(k) instanceof d)) {
/* 146 */             locald = (d)paramd1.m.mChildArrayList.get(k);
/* 147 */             locald.m.setScrollIndicator(locald.p.getScrollIndicator());
/*     */           }
/*     */     }
/*     */     String str;
/*     */     ObjectAnimator localObjectAnimator;
/* 154 */     if (paramInt == 0)
/*     */     {
/* 158 */       b.setTag(Integer.valueOf(paramd1.hashCode()));
/* 159 */       str = paramd2.getAnimOptions().mAnimType;
/* 160 */       if (PdrUtil.isEquals(str, "pop-in")) {
/* 161 */         localObjectAnimator = ObjectAnimator.ofFloat(b == null ? a : b, "translationX", new float[] { paramd1.obtainFrameOptions().left, -i / 6 });
/*     */       }
/*     */       else
/*     */       {
/* 166 */         localObjectAnimator = ObjectAnimator.ofFloat(b == null ? a : b, "translationX", new float[] { 0.0F });
/*     */       }
/*     */ 
/* 169 */       localObjectAnimator.setDuration(paramd2.getAnimOptions().duration_show);
/* 170 */       localObjectAnimator.setInterpolator(new DecelerateInterpolator());
/*     */ 
/* 172 */       localObjectAnimator.addListener(new Animator.AnimatorListener()
/*     */       {
/*     */         public void onAnimationStart(Animator paramAnonymousAnimator)
/*     */         {
/* 176 */           if (j.b != null) {
/* 177 */             j.b.b(true);
/*     */           }
/* 179 */           BaseInfo.sDoingAnimation = true;
/*     */         }
/*     */ 
/*     */         public void onAnimationRepeat(Animator paramAnonymousAnimator)
/*     */         {
/*     */         }
/*     */ 
/*     */         public void onAnimationEnd(Animator paramAnonymousAnimator)
/*     */         {
/* 189 */           BaseInfo.sDoingAnimation = false;
/*     */ 
/* 193 */           if (j.b != null) {
/* 194 */             j.b.b(false);
/* 195 */             j.b.clearAnimation();
/* 196 */             j.b.setVisibility(4);
/* 197 */             j.b.setImageBitmap(null);
/*     */           } else {
/* 199 */             j.a.clearAnimation();
/*     */           }
/*     */ 
/* 203 */           this.a.removeListener(this);
/*     */         }
/*     */ 
/*     */         public void onAnimationCancel(Animator paramAnonymousAnimator)
/*     */         {
/*     */         }
/*     */       });
/*     */     }
/*     */     else
/*     */     {
/* 213 */       str = paramd2.getAnimOptions().mAnimType_close;
/* 214 */       str = AnimOptions.getCloseAnimType(str);
/* 215 */       if (PdrUtil.isEquals(str, "pop-out")) {
/* 216 */         localObjectAnimator = ObjectAnimator.ofFloat(b == null ? a : b, "translationX", new float[] { -i / 6, paramd2.obtainFrameOptions().left });
/*     */       }
/*     */       else
/*     */       {
/* 221 */         localObjectAnimator = ObjectAnimator.ofFloat(b == null ? a : b, "translationX", new float[] { 0.0F });
/*     */       }
/*     */ 
/* 224 */       localObjectAnimator.setDuration(paramd2.getAnimOptions().duration_close);
/* 225 */       localObjectAnimator.setInterpolator(new DecelerateInterpolator());
/*     */ 
/* 227 */       localObjectAnimator.addListener(new Animator.AnimatorListener()
/*     */       {
/*     */         public void onAnimationStart(Animator paramAnonymousAnimator)
/*     */         {
/* 231 */           if (j.b != null) {
/* 232 */             j.b.b(true);
/*     */           }
/* 234 */           BaseInfo.sDoingAnimation = true;
/*     */         }
/*     */ 
/*     */         public void onAnimationRepeat(Animator paramAnonymousAnimator)
/*     */         {
/*     */         }
/*     */ 
/*     */         public void onAnimationEnd(Animator paramAnonymousAnimator)
/*     */         {
/* 244 */           if (j.b != null) {
/* 245 */             j.b.b(false);
/*     */           }
/* 247 */           BaseInfo.sDoingAnimation = false;
/* 248 */           j.a.postDelayed(new Runnable()
/*     */           {
/*     */             public void run() {
/* 251 */               if (j.b != null) {
/* 252 */                 j.b.clearAnimation();
/* 253 */                 j.b.setVisibility(4);
/* 254 */                 j.b.setImageBitmap(null);
/*     */ 
/* 256 */                 j.b.setTag(Integer.valueOf(0));
/*     */               } else {
/* 258 */                 j.a.clearAnimation();
/*     */               }
/*     */             }
/*     */           }
/*     */           , 320L);
/*     */ 
/* 263 */           this.a.removeListener(this);
/*     */         }
/*     */ 
/*     */         public void onAnimationCancel(Animator paramAnonymousAnimator)
/*     */         {
/*     */         }
/*     */       });
/*     */     }
/* 271 */     localObjectAnimator.start();
/* 272 */     paramd1.l.h(paramd1);
/*     */   }
/*     */ 
/*     */   private static void b(int paramInt, d paramd1, d paramd2)
/*     */   {
/* 282 */     int i = paramd2.obtainApp().getInt(0);
/*     */     String str;
/*     */     final ObjectAnimator localObjectAnimator;
/* 284 */     if (paramInt == 0) {
/* 285 */       str = paramd2.getAnimOptions().mAnimType;
/* 286 */       if (PdrUtil.isEquals(str, "pop-in")) {
/* 287 */         localObjectAnimator = ObjectAnimator.ofFloat(b == null ? a : b, "translationX", new float[] { paramd1.obtainFrameOptions().left, -i / 6 });
/*     */       }
/*     */       else
/*     */       {
/* 292 */         localObjectAnimator = ObjectAnimator.ofFloat(b == null ? a : b, "translationX", new float[] { 0.0F });
/*     */       }
/*     */ 
/* 295 */       localObjectAnimator.setDuration(paramd2.getAnimOptions().duration_show);
/* 296 */       localObjectAnimator.setInterpolator(new DecelerateInterpolator());
/* 297 */       localObjectAnimator.addListener(new Animator.AnimatorListener()
/*     */       {
/*     */         public void onAnimationStart(Animator paramAnonymousAnimator)
/*     */         {
/* 301 */           BaseInfo.sDoingAnimation = true;
/*     */         }
/*     */ 
/*     */         public void onAnimationRepeat(Animator paramAnonymousAnimator)
/*     */         {
/*     */         }
/*     */ 
/*     */         public void onAnimationEnd(Animator paramAnonymousAnimator)
/*     */         {
/* 310 */           ViewHelper.setX(j.a, this.a.obtainFrameOptions().left);
/* 311 */           ViewHelper.setY(j.a, this.a.obtainFrameOptions().top);
/* 312 */           BaseInfo.sDoingAnimation = false;
/* 313 */           localObjectAnimator.removeListener(this);
/*     */         }
/*     */ 
/*     */         public void onAnimationCancel(Animator paramAnonymousAnimator) {
/*     */         }
/*     */       });
/*     */     }
/*     */     else {
/* 321 */       str = paramd2.getAnimOptions().mAnimType_close;
/* 322 */       str = AnimOptions.getCloseAnimType(str);
/* 323 */       if (PdrUtil.isEquals(str, "pop-out")) {
/* 324 */         localObjectAnimator = ObjectAnimator.ofFloat(b == null ? a : b, "translationX", new float[] { -i / 6, paramd2.obtainFrameOptions().left });
/*     */       }
/*     */       else
/*     */       {
/* 329 */         localObjectAnimator = ObjectAnimator.ofFloat(b == null ? a : b, "translationX", new float[] { 0.0F });
/*     */       }
/*     */ 
/* 332 */       localObjectAnimator.setDuration(paramd2.getAnimOptions().duration_close);
/* 333 */       localObjectAnimator.setInterpolator(new DecelerateInterpolator());
/* 334 */       localObjectAnimator.addListener(new Animator.AnimatorListener()
/*     */       {
/*     */         public void onAnimationStart(Animator paramAnonymousAnimator)
/*     */         {
/* 338 */           BaseInfo.sDoingAnimation = true;
/*     */         }
/*     */ 
/*     */         public void onAnimationRepeat(Animator paramAnonymousAnimator)
/*     */         {
/*     */         }
/*     */ 
/*     */         public void onAnimationEnd(Animator paramAnonymousAnimator)
/*     */         {
/* 347 */           ViewHelper.setX(j.a, this.a.obtainFrameOptions().left);
/* 348 */           ViewHelper.setY(j.a, this.a.obtainFrameOptions().top);
/* 349 */           BaseInfo.sDoingAnimation = false;
/* 350 */           localObjectAnimator.removeListener(this);
/*     */         }
/*     */ 
/*     */         public void onAnimationCancel(Animator paramAnonymousAnimator)
/*     */         {
/*     */         }
/*     */       });
/*     */     }
/* 358 */     localObjectAnimator.start();
/* 359 */     paramd1.l.h(paramd1);
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.j
 * JD-Core Version:    0.6.2
 */