/*     */ package io.dcloud.common.a;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.res.Configuration;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Rect;
/*     */ import android.os.Build.VERSION;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.view.View;
/*     */ import android.view.Window;
/*     */ import android.view.WindowManager.LayoutParams;
/*     */ import io.dcloud.common.DHInterface.IAppInfo;
/*     */ import io.dcloud.common.DHInterface.IOnCreateSplashView;
/*     */ import io.dcloud.common.DHInterface.IWebAppRootView;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.adapter.util.ViewRect;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ 
/*     */ public class e
/*     */   implements IAppInfo
/*     */ {
/*  29 */   Activity Z = null;
/*  30 */   protected IWebAppRootView aa = null;
/*  31 */   private IOnCreateSplashView a = null;
/*     */ 
/*  33 */   public int ab = 0;
/*     */ 
/*  35 */   public int ac = 0;
/*     */ 
/*  37 */   public int ad = 0;
/*     */ 
/*  39 */   public int ae = 0;
/*     */ 
/*  41 */   public int af = 0;
/*     */ 
/*  44 */   protected boolean ag = false;
/*     */ 
/*  46 */   private boolean b = false;
/*  47 */   private int c = 0;
/*     */ 
/*  49 */   ViewRect ah = new ViewRect();
/*     */ 
/*     */   void a(Activity paramActivity)
/*     */   {
/*  55 */     DisplayMetrics localDisplayMetrics = paramActivity.getResources().getDisplayMetrics();
/*  56 */     this.ac = localDisplayMetrics.heightPixels;
/*     */ 
/*  58 */     this.Z = paramActivity;
/*  59 */     this.ah.mJsonViewOption = JSONUtil.createJSONObject("{}");
/*  60 */     this.ad = PdrUtil.parseInt(PlatformUtil.getBundleData(BaseInfo.PDR, "StatusBarHeight"), 0);
/*  61 */     this.ab = localDisplayMetrics.widthPixels;
/*  62 */     this.af = (localDisplayMetrics.heightPixels - this.ad);
/*     */ 
/*  64 */     Logger.i("WebAppInfo", "init() get sStatusBarHeight=" + this.ad);
/*     */   }
/*     */ 
/*     */   public int checkSelfPermission(String paramString) {
/*  68 */     if ((Build.VERSION.SDK_INT >= 23) && (this.Z != null) && (paramString != null)) {
/*  69 */       return ((Integer)PlatformUtil.invokeMethod(this.Z.getClass().getName(), "checkSelfPermission", this.Z, new Class[] { paramString.getClass() }, new Object[] { paramString })).intValue();
/*     */     }
/*  71 */     return -100;
/*     */   }
/*     */   public void requestPermissions(String[] paramArrayOfString, int paramInt) {
/*  74 */     if ((Build.VERSION.SDK_INT >= 23) && (this.Z != null) && (paramArrayOfString != null))
/*  75 */       PlatformUtil.invokeMethod(this.Z.getClass().getName(), "requestPermissions", this.Z, new Class[] { paramArrayOfString.getClass(), Integer.TYPE }, new Object[] { paramArrayOfString, Integer.valueOf(paramInt) });
/*     */   }
/*     */ 
/*     */   public Activity getActivity()
/*     */   {
/*  80 */     return this.Z;
/*     */   }
/*     */ 
/*     */   public ViewRect getAppViewRect() {
/*  84 */     return this.ah;
/*     */   }
/*     */ 
/*     */   public IWebAppRootView obtainWebAppRootView() {
/*  88 */     return this.aa;
/*     */   }
/*     */ 
/*     */   public void setWebAppRootView(IWebAppRootView paramIWebAppRootView) {
/*  92 */     this.aa = paramIWebAppRootView;
/*     */   }
/*     */ 
/*     */   public void setFullScreen(boolean paramBoolean)
/*     */   {
/* 116 */     if (BaseInfo.sGlobalFullScreen != paramBoolean) {
/* 117 */       this.ag = paramBoolean;
/* 118 */       Window localWindow = getActivity().getWindow();
/*     */       WindowManager.LayoutParams localLayoutParams;
/* 119 */       if (paramBoolean) {
/* 120 */         localLayoutParams = localWindow.getAttributes();
/* 121 */         localLayoutParams.flags |= 1024;
/* 122 */         localWindow.setAttributes(localLayoutParams);
/*     */       }
/*     */       else {
/* 125 */         localLayoutParams = localWindow.getAttributes();
/* 126 */         localLayoutParams.flags &= -1025;
/* 127 */         localWindow.setAttributes(localLayoutParams);
/*     */       }
/*     */ 
/* 130 */       updateScreenInfo(this.ag ? 2 : 3);
/*     */     }
/* 132 */     BaseInfo.sGlobalFullScreen = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean isFullScreen()
/*     */   {
/* 137 */     return this.ag;
/*     */   }
/*     */ 
/*     */   public void updateScreenInfo(int paramInt)
/*     */   {
/* 145 */     boolean bool = this.ag;
/*     */     Object localObject;
/* 146 */     if ((!bool) && (this.ad == 0)) {
/* 147 */       localObject = new Rect();
/* 148 */       this.Z.getWindow().getDecorView().getWindowVisibleDisplayFrame((Rect)localObject);
/* 149 */       this.ad = ((Rect)localObject).top;
/* 150 */       if (this.ad > 0) {
/* 151 */         PlatformUtil.setBundleData(BaseInfo.PDR, "StatusBarHeight", String.valueOf(this.ad));
/*     */       }
/*     */     }
/*     */ 
/* 155 */     if (paramInt == 2) {
/* 156 */       localObject = this.Z.getResources().getDisplayMetrics();
/* 157 */       this.ab = ((DisplayMetrics)localObject).widthPixels;
/* 158 */       this.af = ((DisplayMetrics)localObject).heightPixels;
/*     */     } else {
/* 160 */       this.ab = this.aa.obtainMainView().getWidth();
/* 161 */       this.af = this.aa.obtainMainView().getHeight();
/*     */     }
/* 163 */     this.ah.onScreenChanged(this.ab, this.af);
/*     */   }
/*     */ 
/*     */   public void setRequestedOrientation(final String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 178 */       MessageHandler.sendMessage(new MessageHandler.IMessages()
/*     */       {
/*     */         public void execute(Object paramAnonymousObject) {
/* 181 */           if ("landscape".equals(paramString))
/* 182 */             e.this.Z.setRequestedOrientation(6);
/* 183 */           else if ("landscape-primary".equals(paramString))
/* 184 */             e.this.Z.setRequestedOrientation(0);
/* 185 */           else if ("landscape-secondary".equals(paramString))
/* 186 */             e.this.Z.setRequestedOrientation(8);
/* 187 */           else if ("portrait".equals(paramString))
/* 188 */             e.this.Z.setRequestedOrientation(7);
/* 189 */           else if ("portrait-primary".equals(paramString))
/* 190 */             e.this.Z.setRequestedOrientation(1);
/* 191 */           else if ("portrait-secondary".equals(paramString))
/* 192 */             e.this.Z.setRequestedOrientation(9);
/*     */           else
/* 194 */             e.this.Z.setRequestedOrientation(4);
/*     */         }
/*     */       }
/*     */       , 48L, paramString);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 199 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getRequestedOrientation()
/*     */   {
/* 208 */     return this.Z.getRequestedOrientation();
/*     */   }
/*     */ 
/*     */   public boolean isVerticalScreen()
/*     */   {
/* 216 */     return this.Z.getResources().getConfiguration().orientation == 1;
/*     */   }
/*     */ 
/*     */   public void setRequestedOrientation(int paramInt)
/*     */   {
/* 224 */     this.Z.setRequestedOrientation(paramInt);
/*     */   }
/*     */ 
/*     */   public int getInt(int paramInt) {
/* 228 */     int i = -1;
/* 229 */     switch (paramInt) {
/*     */     case 0:
/* 231 */       i = this.ab;
/* 232 */       break;
/*     */     case 1:
/* 234 */       i = this.af;
/* 235 */       break;
/*     */     case 2:
/* 237 */       i = this.ac;
/* 238 */       break;
/*     */     }
/*     */ 
/* 242 */     return i;
/*     */   }
/*     */ 
/*     */   public void setMaskLayer(boolean paramBoolean) {
/* 246 */     this.b = paramBoolean;
/* 247 */     if (paramBoolean) {
/* 248 */       this.c += 1;
/*     */     } else {
/* 250 */       this.c -= 1;
/* 251 */       if (this.c < 0) this.c = 0; 
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getMaskLayerCount()
/*     */   {
/* 256 */     return this.c;
/*     */   }
/*     */ 
/*     */   public void clearMaskLayerCount() {
/* 260 */     this.c = 0;
/*     */   }
/*     */   public void setOnCreateSplashView(IOnCreateSplashView paramIOnCreateSplashView) {
/* 263 */     this.a = paramIOnCreateSplashView;
/*     */   }
/*     */   public IOnCreateSplashView getOnCreateSplashView() {
/* 266 */     return this.a;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.a.e
 * JD-Core Version:    0.6.2
 */