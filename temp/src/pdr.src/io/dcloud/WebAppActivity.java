/*     */ package io.dcloud;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.AlertDialog;
/*     */ import android.app.AlertDialog.Builder;
/*     */ import android.content.BroadcastReceiver;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.DialogInterface.OnClickListener;
/*     */ import android.content.DialogInterface.OnKeyListener;
/*     */ import android.content.Intent;
/*     */ import android.content.IntentFilter;
/*     */ import android.content.SharedPreferences;
/*     */ import android.content.res.AssetManager;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.Paint;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.Bundle;
/*     */ import android.os.Handler;
/*     */ import android.text.TextUtils;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.util.Log;
/*     */ import android.view.KeyEvent;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.widget.FrameLayout;
/*     */ import android.widget.FrameLayout.LayoutParams;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.LinearLayout.LayoutParams;
/*     */ import android.widget.Toast;
/*     */ import com.nostra13.dcloudimageloader.core.ImageLoader;
/*     */ import com.nostra13.dcloudimageloader.core.assist.FailReason;
/*     */ import com.nostra13.dcloudimageloader.core.assist.ImageLoadingListener;
/*     */ import io.dcloud.common.DHInterface.ICore;
/*     */ import io.dcloud.common.DHInterface.SplashView;
/*     */ import io.dcloud.common.adapter.io.DHFile;
/*     */ import io.dcloud.common.adapter.ui.FrameSwitchView;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.constant.DataInterface;
/*     */ import io.dcloud.common.constant.StringConst;
/*     */ import io.dcloud.common.util.AppStatus;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.DialogUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.TestUtil;
/*     */ import io.dcloud.common.util.TestUtil.PointTime;
/*     */ import io.dcloud.feature.internal.splash.c;
/*     */ import io.dcloud.feature.internal.splash.d;
/*     */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ 
/*     */ public class WebAppActivity extends a
/*     */ {
/*     */   public static final long ONE_SECOND = 1000L;
/*     */   public static final long SPLASH_SECOND = 5000L;
/*     */   private long y;
/*     */   private boolean z;
/*     */   private boolean A;
/*  71 */   private Handler B = new Handler();
/*     */ 
/*  73 */   private final String C = "remove-app_action";
/*     */   BroadcastReceiver p;
/*  75 */   static WebAppActivity q = null;
/*     */ 
/* 177 */   Bitmap r = null;
/* 178 */   View s = null;
/*     */ 
/* 180 */   long t = 0L;
/*     */ 
/* 526 */   boolean u = true;
/*     */ 
/* 532 */   FrameLayout v = null;
/* 533 */   LinearLayout w = null;
/*     */ 
/* 650 */   FrameLayout x = null;
/*     */ 
/*     */   public void onCreate(Bundle paramBundle)
/*     */   {
/*  77 */     Log.d("WebAppActivity", "onCreate");
/*     */ 
/*  79 */     super.onCreate(paramBundle);
/*     */ 
/*  81 */     if (!this.A) {
/*  82 */       TestUtil.record("run_5app_time_key");
/*     */     }
/*  84 */     q = this;
/*  85 */     b();
/*  86 */     IntentFilter localIntentFilter = new IntentFilter("remove-app_action");
/*  87 */     localIntentFilter.addAction("apk_download_end");
/*  88 */     localIntentFilter.addAction(getPackageName() + ".streamdownload.downloadfinish");
/*  89 */     this.p = new BroadcastReceiver()
/*     */     {
/*     */       public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
/*     */       {
/*  94 */         String str1 = paramAnonymousIntent.getAction();
/*     */         String str2;
/*  95 */         if (TextUtils.equals(str1, "remove-app_action")) {
/*  96 */           str2 = paramAnonymousIntent.getStringExtra("appid");
/*  97 */           WebAppActivity.this.c.getCoreHandler().removeStreamApp(str2);
/*  98 */         } else if (TextUtils.equals(str1, "apk_download_end"))
/*     */         {
/* 100 */           str2 = paramAnonymousIntent.getStringExtra("apkPath");
/* 101 */           String str3 = paramAnonymousIntent.getStringExtra("msg");
/* 102 */           DialogUtil.showInstallAPKDialog(WebAppActivity.q, str3, str2);
/* 103 */         } else if (TextUtils.equals(str1, WebAppActivity.this.getPackageName() + ".streamdownload.downloadfinish")) {
/* 104 */           str2 = paramAnonymousIntent.getStringExtra("flag");
/* 105 */           int i = paramAnonymousIntent.getIntExtra("status", 2);
/* 106 */           if ((str2.compareTo("wap2app_index") == 0) || (str2.compareTo("appstream") == 0) || (str2.compareTo("StreamAppWgt") == 0))
/*     */           {
/* 109 */             if (i == b.g) {
/* 110 */               String str4 = paramAnonymousIntent.getStringExtra("appid");
/* 111 */               int j = paramAnonymousIntent.getIntExtra("type", -1);
/* 112 */               if ((j == b.h) || (j == b.i) || (j == b.j)) {
/* 113 */                 InvokeExecutorHelper.QihooInnerStatisticUtil.invoke("doEvent", new Class[] { String.class, String.class }, new Object[] { str4, "event_add_shortcut" });
/*     */ 
/* 116 */                 Logger.d("syncStartApp", "download MAIN_PAGE nAppid=" + str4);
/* 117 */                 Intent localIntent = new Intent(WebAppActivity.this.getIntent());
/* 118 */                 localIntent.putExtra("just_download", true);
/* 119 */                 localIntent.putExtra("has_stream_splash", false);
/* 120 */                 localIntent.putExtra("is_stream_app", true);
/* 121 */                 localIntent.putExtra("appid", str4);
/* 122 */                 WebAppActivity.this.handleNewIntent(localIntent);
/* 123 */                 InvokeExecutorHelper.QHPushHelper.invoke("registerApp", str4, false);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     };
/* 130 */     registerReceiver(this.p, localIntentFilter);
/*     */ 
/* 132 */     FrameSwitchView localFrameSwitchView = FrameSwitchView.getInstance(this.that);
/* 133 */     if (!localFrameSwitchView.isInit()) {
/* 134 */       localFrameSwitchView.initView();
/*     */     }
/* 136 */     a();
/*     */   }
/*     */ 
/*     */   private void a() {
/* 140 */     Intent localIntent1 = getIntent();
/* 141 */     boolean bool = localIntent1 != null ? localIntent1.getBooleanExtra("__plugin_auto_hide__", false) : false;
/* 142 */     Log.d("WebAppActivity", "checkAutoHide " + bool);
/* 143 */     if (bool) {
/* 144 */       Intent localIntent2 = new Intent();
/* 145 */       String str1 = localIntent1.getStringExtra("__plugin_auto_hide_show_pname__");
/* 146 */       String str2 = localIntent1.getStringExtra("__plugin_auto_hide_show_activity__");
/* 147 */       localIntent2.putExtra("__plugin_auto_hide_show_pname__", true);
/* 148 */       localIntent2.setClassName(str1, str2);
/* 149 */       this.that.startActivity(localIntent2);
/* 150 */       this.that.overridePendingTransition(0, 0);
/* 151 */       Log.d("WebAppActivity", "checkAutoHide return mini package " + str2);
/*     */     }
/*     */   }
/*     */ 
/* 155 */   private void b() { Intent localIntent = getIntent();
/* 156 */     if (localIntent != null) {
/* 157 */       this.A = localIntent.getBooleanExtra("is_stream_app", false);
/* 158 */       if (!this.A)
/* 159 */         localIntent.removeExtra("appid");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onDestroy()
/*     */   {
/* 165 */     Log.d("WebAppActivity", "onDestroy");
/* 166 */     super.onDestroy();
/* 167 */     unregisterReceiver(this.p);
/* 168 */     q = null;
/* 169 */     io.dcloud.common.b.b.a.a();
/* 170 */     if (this.B != null) {
/* 171 */       this.B.removeCallbacksAndMessages(null);
/*     */     }
/*     */ 
/* 174 */     FrameSwitchView.getInstance(this.that).clearData();
/* 175 */     PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "WebAppDestroy");
/*     */   }
/*     */ 
/*     */   private Bitmap a(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 183 */     Bitmap localBitmap = null;
/*     */     try {
/* 185 */       if ((!TextUtils.isEmpty(paramString1)) && (new File(paramString1).exists())) {
/* 186 */         Logger.d("Main_Path", "use splashPath=" + paramString1);
/* 187 */         localBitmap = BitmapFactory.decodeFile(paramString1);
/* 188 */         if (localBitmap != null) {
/* 189 */           this.u = false;
/*     */           try {
/* 191 */             DHFile.deleteFile(paramString1);
/*     */           } catch (IOException localIOException) {
/* 193 */             localIOException.printStackTrace();
/*     */           }
/* 195 */           Logger.d("Main_Path", "use splashPath=" + paramString1);
/*     */         }
/*     */       }
/* 198 */       if ((localBitmap == null) && (!TextUtils.isEmpty(paramString2)) && (new File(paramString2).exists())) {
/* 199 */         Logger.d("Main_Path", "use splashPath=" + paramString2);
/* 200 */         localBitmap = BitmapFactory.decodeFile(paramString2);
/*     */       }
/* 202 */       if ((localBitmap == null) && (!TextUtils.isEmpty(paramString3)) && (new File(paramString3).exists())) {
/* 203 */         Logger.d("Main_Path", "use splashPath=" + paramString3);
/* 204 */         localBitmap = BitmapFactory.decodeFile(paramString3);
/*     */       }
/*     */     } catch (Exception localException) {
/* 207 */       localException.printStackTrace();
/*     */     }
/* 209 */     return localBitmap;
/*     */   }
/*     */ 
/*     */   public Object onCreateSplash(Context paramContext) {
/* 213 */     Intent localIntent = getIntent();
/* 214 */     String str1 = localIntent.getStringExtra("appid");
/*     */ 
/* 216 */     if ((this.s != null) && (this.s.getTag() != null)) {
/* 217 */       if (!this.s.getTag().equals(str1))
/* 218 */         closeAppStreamSplash(this.s.getTag().toString());
/*     */       else {
/* 220 */         return null;
/*     */       }
/*     */     }
/*     */ 
/* 224 */     this.u = true;
/*     */ 
/* 227 */     Logger.d("WebAppActivity", "onCreateSplash;intent=" + localIntent);
/* 228 */     boolean bool1 = localIntent.getBooleanExtra("__start_first_web__", false);
/* 229 */     if (bool1) {
/* 230 */       return null;
/*     */     }
/* 232 */     boolean bool2 = localIntent.getBooleanExtra("__splash_view__", true);
/* 233 */     if (!bool2) {
/* 234 */       return null;
/*     */     }
/* 236 */     Logger.d("WebAppActivity", "onCreateSplash hasSplash=" + bool2);
/* 237 */     boolean bool3 = localIntent.getBooleanExtra("__plugin_auto_hide__", false);
/* 238 */     if (bool3) {
/* 239 */       return null;
/*     */     }
/*     */ 
/* 247 */     String str2 = localIntent.getStringExtra("__splash_mode__");
/* 248 */     localIntent.removeExtra("__splash_mode__");
/* 249 */     if ((null == str2) || ("".equals(str2.trim())) || ((!"auto".equals(str2)) && (!"default".equals(str2))))
/*     */     {
/* 251 */       str2 = "auto";
/*     */     }
/* 253 */     Logger.d("WebAppActivity", "onCreateSplash __splash_mode__=" + str2);
/* 254 */     if ((localIntent != null) && (localIntent.getBooleanExtra("hide_stream_splash", false))) {
/* 255 */       setViewAsContentView(new View(paramContext));
/* 256 */       this.t = System.currentTimeMillis();
/* 257 */       this.z = true;
/* 258 */       return null;
/*     */     }
/*     */     String str3;
/*     */     String str4;
/*     */     String str5;
/* 259 */     if ((localIntent != null) && (localIntent.getBooleanExtra("has_stream_splash", false))) {
/* 260 */       if (this.s == null) {
/* 261 */         if ("auto".equals(str2)) {
/* 262 */           str3 = StringConst.STREAMAPP_KEY_ROOTPATH + "splash_temp/" + str1 + ".png";
/* 263 */           str4 = localIntent.getStringExtra("app_splash_path");
/* 264 */           str5 = StringConst.STREAMAPP_KEY_ROOTPATH + "splash/" + str1 + ".png";
/* 265 */           this.r = a(str3, str4, str5);
/* 266 */           if (this.r != null) {
/* 267 */             this.s = new SplashView(this.that, this.r);
/* 268 */             if (!this.u) {
/* 269 */               ((SplashView)this.s).showWaiting(SplashView.STYLE_BLACK);
/*     */             }
/*     */           }
/*     */         }
/* 273 */         if (this.s == null) {
/* 274 */           if (BaseInfo.isShowTitleBar(paramContext)) {
/* 275 */             this.s = new a(this.that);
/*     */           } else {
/* 277 */             str3 = localIntent.getStringExtra("app_icon");
/* 278 */             if ((!TextUtils.isEmpty(str3)) && (new File(str3).exists())) {
/* 279 */               this.r = BitmapFactory.decodeFile(str3);
/*     */             }
/* 281 */             if (this.r == null)
/*     */             {
/* 287 */               a(str1);
/*     */             }
/*     */ 
/* 290 */             this.s = new d(paramContext, this.r, localIntent.getStringExtra("__name__"));
/*     */           }
/*     */         }
/*     */       }
/* 294 */       this.s.setTag(str1);
/* 295 */       setViewAsContentView(this.s);
/* 296 */       this.t = System.currentTimeMillis();
/* 297 */       this.z = true;
/* 298 */       return null;
/* 299 */     }if (this.s == null)
/*     */     {
/*     */       try {
/* 302 */         if ("auto".equals(str2)) {
/* 303 */           str3 = StringConst.STREAMAPP_KEY_ROOTPATH + "splash_temp/" + str1 + ".png";
/* 304 */           str4 = localIntent.getStringExtra("app_splash_path");
/* 305 */           str5 = StringConst.STREAMAPP_KEY_ROOTPATH + "splash/" + str1 + ".png";
/* 306 */           this.r = a(str3, str4, str5);
/*     */ 
/* 308 */           if ((this.r == null) && (!BaseInfo.isForQihooHelper(paramContext)) && (!BaseInfo.isStreamApp(paramContext)) && (!BaseInfo.isForQihooBrowser(paramContext))) {
/* 309 */             SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/* 310 */             String str6 = localSharedPreferences.getString("update_splash_img_path", "");
/* 311 */             if (!TextUtils.isEmpty(str6)) {
/*     */               try {
/* 313 */                 if (PdrUtil.isDeviceRootDir(str6)) {
/* 314 */                   this.r = BitmapFactory.decodeFile(str6);
/*     */                 } else {
/* 316 */                   InputStream localInputStream = getResources().getAssets().open(str6);
/* 317 */                   this.r = BitmapFactory.decodeStream(localInputStream);
/* 318 */                   localInputStream.close();
/*     */                 }
/*     */               } catch (Exception localException2) {
/* 321 */                 this.r = null;
/*     */               }
/*     */             }
/* 324 */             if (this.r == null) {
/* 325 */               this.r = BitmapFactory.decodeResource(getResources(), RInformation.DRAWABLE_SPLASH);
/*     */             }
/*     */           }
/* 328 */           if (this.r != null) {
/* 329 */             this.s = new SplashView(this.that, this.r);
/* 330 */             if (!this.u) {
/* 331 */               ((SplashView)this.s).showWaiting(SplashView.STYLE_BLACK);
/*     */             }
/*     */           }
/*     */         }
/* 335 */         if (this.s == null) {
/* 336 */           if (BaseInfo.isShowTitleBar(paramContext)) {
/* 337 */             this.s = new a(this.that);
/*     */           } else {
/* 339 */             str3 = localIntent.getStringExtra("app_icon");
/* 340 */             if ((!TextUtils.isEmpty(str3)) && (new File(str3).exists())) {
/* 341 */               this.r = BitmapFactory.decodeFile(str3);
/*     */             }
/* 343 */             if (this.r == null)
/*     */             {
/* 349 */               a(str1);
/*     */             }
/* 351 */             Log.d("Main_Path", "use defaultSplash");
/*     */ 
/* 353 */             this.s = new d(paramContext, this.r, localIntent.getStringExtra("__name__"));
/*     */           }
/*     */         }
/* 356 */         this.s.setTag(str1);
/* 357 */         setViewAsContentView(this.s);
/* 358 */         this.t = System.currentTimeMillis();
/* 359 */         this.z = true;
/*     */       } catch (Exception localException1) {
/* 361 */         localException1.printStackTrace();
/*     */       }
/*     */ 
/* 364 */       return null;
/*     */     }
/* 366 */     return null;
/*     */   }
/*     */ 
/*     */   public void updateParam(String paramString, Object paramObject)
/*     */   {
/* 380 */     if ("progress".equals(paramString)) {
/* 381 */       if ((this.s instanceof a))
/* 382 */         ((a)this.s).a(((Integer)paramObject).intValue());
/*     */     }
/* 384 */     else if ("setProgressView".equals(paramString))
/* 385 */       setProgressView();
/*     */     else
/* 387 */       super.updateParam(paramString, paramObject);
/*     */   }
/*     */ 
/*     */   public void updateSplash(String paramString)
/*     */   {
/* 493 */     if ((this.s != null) && ((this.s instanceof c)))
/* 494 */       ((c)this.s).a(paramString);
/*     */   }
/*     */ 
/*     */   private void a(String paramString) {
/* 498 */     String str = DataInterface.getIconImageUrl(paramString, getResources().getDisplayMetrics().widthPixels + "");
/*     */ 
/* 502 */     ImageLoader.getInstance().loadImage(str, new ImageLoadingListener()
/*     */     {
/*     */       public void onLoadingStarted(String paramAnonymousString, View paramAnonymousView) {
/*     */       }
/*     */ 
/*     */       public void onLoadingFailed(String paramAnonymousString, View paramAnonymousView, FailReason paramAnonymousFailReason) {
/* 508 */         if (StringConst.canChangeHost(paramAnonymousString)) {
/* 509 */           paramAnonymousString = StringConst.changeHost(paramAnonymousString);
/* 510 */           ImageLoader.getInstance().loadImage(paramAnonymousString, this);
/*     */         }
/*     */       }
/*     */ 
/*     */       public void onLoadingComplete(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap)
/*     */       {
/* 517 */         if ((WebAppActivity.this.s != null) && ((WebAppActivity.this.s instanceof c)))
/* 518 */           ((c)WebAppActivity.this.s).a(paramAnonymousBitmap);
/*     */       }
/*     */ 
/*     */       public void onLoadingCancelled(String paramAnonymousString, View paramAnonymousView)
/*     */       {
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public void showSplashWaiting() {
/* 528 */     if ((this.u) && ((this.s instanceof SplashView)))
/* 529 */       ((SplashView)this.s).showWaiting();
/*     */   }
/*     */ 
/*     */   public void setViewAsContentView(View paramView)
/*     */   {
/* 536 */     if (this.v == null) {
/* 537 */       this.v = new FrameLayout(this.that);
/* 538 */       if (BaseInfo.isShowTitleBar(this.that)) {
/* 539 */         localObject = PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "getWebAppRootView", null, new Class[] { Activity.class }, new Object[] { this.that });
/* 540 */         if ((null != localObject) && ((localObject instanceof LinearLayout))) {
/* 541 */           this.w = ((LinearLayout)localObject);
/*     */         }
/*     */       }
/* 544 */       if (this.w != null) {
/* 545 */         localObject = new LinearLayout.LayoutParams(-1, -1);
/* 546 */         this.v.setLayoutParams((ViewGroup.LayoutParams)localObject);
/* 547 */         this.w.addView(this.v);
/* 548 */         setContentView(this.w);
/*     */       } else {
/* 550 */         setContentView(this.v);
/*     */       }
/*     */     }
/* 553 */     Object localObject = getIntent().getStringExtra("appid");
/*     */ 
/* 555 */     PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "checkNeedTitleView", null, new Class[] { Activity.class, String.class }, new Object[] { this.that, localObject });
/*     */ 
/* 558 */     if (((BaseInfo.sGlobalFullScreen) || (AndroidResources.checkImmersedStatusBar(this.that))) && (Build.VERSION.SDK_INT >= 23)) {
/* 559 */       FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
/* 560 */       localLayoutParams.topMargin = -1;
/* 561 */       this.v.setLayoutParams(localLayoutParams);
/*     */     }
/*     */ 
/* 568 */     int i = this.v.indexOfChild(this.s);
/* 569 */     int j = this.v.getChildCount();
/* 570 */     if (j > 0) {
/* 571 */       for (int k = j - 1; k >= 0; k--) {
/* 572 */         View localView = this.v.getChildAt(k);
/* 573 */         if (localView != paramView) {
/* 574 */           if ("AppRootView".equals(localView.getTag())) {
/* 575 */             this.v.addView(paramView, k);
/* 576 */             this.v.removeView(localView);
/* 577 */             break;
/* 578 */           }if (k == 0) {
/* 579 */             if (localView == this.s) {
/* 580 */               this.v.addView(paramView, 0);
/*     */             }
/* 582 */             else if (i > 0)
/* 583 */               this.v.addView(paramView, i - 1);
/*     */             else {
/* 585 */               this.v.addView(paramView);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 592 */       this.v.addView(paramView);
/*     */     }
/*     */ 
/* 595 */     TestUtil.print(TestUtil.START_APP_SET_ROOTVIEW, "启动" + paramView);
/* 596 */     if (AndroidResources.checkImmersedStatusBar(this.that))
/* 597 */       io.dcloud.common.b.b.a.a(this.v);
/*     */   }
/*     */ 
/*     */   public void setProgressView()
/*     */   {
/* 607 */     if (BaseInfo.isShowTitleBar(this.that)) {
/* 608 */       View localView = null;
/* 609 */       for (int i = 0; i < this.v.getChildCount(); i++) {
/* 610 */         localView = this.v.getChildAt(i);
/* 611 */         if ((null != localView) && (localView == this.s)) {
/* 612 */           this.v.removeViewAt(i);
/* 613 */           break;
/*     */         }
/*     */       }
/* 616 */       this.s = new a(this.that);
/* 617 */       this.v.addView(this.s);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isCanRefresh()
/*     */   {
/* 628 */     if (!BaseInfo.isShowTitleBar(this.that)) {
/* 629 */       return false;
/*     */     }
/* 631 */     if (null == this.v) {
/* 632 */       return false;
/*     */     }
/* 634 */     View localView = null;
/* 635 */     for (int i = 0; i < this.v.getChildCount(); i++) {
/* 636 */       localView = this.v.getChildAt(i);
/* 637 */       if ((null != localView) && ((localView instanceof SplashView))) {
/* 638 */         return false;
/*     */       }
/*     */     }
/* 641 */     return true;
/*     */   }
/*     */ 
/*     */   public void setWebViewIntoPreloadView(View paramView)
/*     */   {
/* 652 */     if (this.x == null) {
/* 653 */       this.x = new FrameLayout(this.that);
/* 654 */       this.v.addView(this.x, 0);
/*     */     }
/* 656 */     this.x.addView(paramView);
/*     */   }
/*     */   public void closeAppStreamSplash(String paramString) {
/* 659 */     Logger.d("webappActivity closeAppStreamSplash");
/*     */ 
/* 661 */     DCloudAdapterUtil.Plugin2Host_closeAppStreamSplash(paramString);
/* 662 */     if ((this.r != null) && (!this.r.isRecycled())) {
/*     */       try {
/* 664 */         this.r.recycle();
/* 665 */         this.r = null;
/*     */       } catch (Exception localException) {
/* 667 */         localException.printStackTrace();
/*     */       }
/*     */     }
/* 670 */     if (this.s != null) {
/* 671 */       Logger.d("webappActivity removeView mSplashView");
/* 672 */       if ((this.s instanceof a))
/*     */       {
/* 676 */         ((a)this.s).a();
/*     */       }
/*     */       else {
/* 679 */         this.v.removeView(this.s);
/*     */       }
/* 681 */       this.s = null;
/*     */     }
/* 683 */     this.z = false;
/* 684 */     this.t = 0L;
/*     */   }
/*     */ 
/*     */   public static AlertDialog showDownloadDialog(String paramString, DialogInterface.OnClickListener paramOnClickListener) {
/* 688 */     if ((q == null) || (!q.z)) return null;
/* 689 */     AlertDialog localAlertDialog = new AlertDialog.Builder(q.that).create();
/*     */ 
/* 691 */     localAlertDialog.setTitle("提示");
/* 692 */     if (TestUtil.PointTime.mEc == 20)
/* 693 */       localAlertDialog.setMessage("检测到设备无网络，请检查系统网络设置！");
/* 694 */     else if (paramString != null)
/* 695 */       localAlertDialog.setMessage("进入流应用" + paramString + "失败" + getErrorTipMsg());
/*     */     else {
/* 697 */       localAlertDialog.setMessage("进入流应用失败" + getErrorTipMsg());
/*     */     }
/* 699 */     localAlertDialog.setCanceledOnTouchOutside(false);
/* 700 */     localAlertDialog.setButton(-1, "重试", paramOnClickListener);
/* 701 */     localAlertDialog.setButton(-2, "关闭", new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*     */       {
/* 706 */         WebAppActivity.q.updateParam("closewebapp", WebAppActivity.q);
/* 707 */         this.a.onClick(paramAnonymousDialogInterface, paramAnonymousInt);
/*     */       }
/*     */     });
/* 711 */     localAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
/*     */     {
/*     */       public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent) {
/* 714 */         if ((paramAnonymousKeyEvent.getAction() == 1) && (paramAnonymousInt == 4))
/*     */         {
/* 716 */           WebAppActivity.q.updateParam("closewebapp", WebAppActivity.q);
/* 717 */           this.a.onClick(paramAnonymousDialogInterface, -2);
/* 718 */           return true;
/*     */         }
/* 720 */         return false;
/*     */       }
/*     */     });
/* 724 */     localAlertDialog.show();
/* 725 */     return localAlertDialog;
/*     */   }
/*     */ 
/*     */   public void onWindowFocusChanged(boolean paramBoolean) {
/* 729 */     super.onWindowFocusChanged(paramBoolean);
/*     */ 
/* 731 */     PlatformUtil.SCREEN_WIDTH(this.that);
/*     */ 
/* 733 */     PlatformUtil.SCREEN_HEIGHT(this.that);
/*     */ 
/* 735 */     PlatformUtil.MESURE_SCREEN_STATUSBAR_HEIGHT(this.that);
/*     */   }
/*     */ 
/*     */   private void c()
/*     */   {
/* 741 */     String str = "再按一次返回键关闭流应用";
/* 742 */     if ((getIntent() != null) && (getIntent().hasExtra("__name__"))) {
/* 743 */       str = "再按一次返回键关闭" + getIntent().getStringExtra("__name__");
/*     */     }
/* 745 */     Toast.makeText(this.that, str, 0).show();
/*     */   }
/*     */ 
/*     */   public void onBackPressed()
/*     */   {
/* 750 */     Logger.e("WebAppActivity", "onBackPressed");
/* 751 */     if ((this.A) && (this.z)) {
/* 752 */       if ("all".equalsIgnoreCase(BaseInfo.sSplashExitCondition)) {
/* 753 */         super.onBackPressed();
/*     */ 
/* 756 */         return;
/*     */       }
/*     */ 
/* 759 */       if ((this.t != 0L) && (System.currentTimeMillis() - this.t < 5000L)) {
/* 760 */         return;
/*     */       }
/* 762 */       if (this.y == 0L) {
/* 763 */         c();
/* 764 */         this.y = System.currentTimeMillis();
/* 765 */         this.B.postDelayed(new Runnable()
/*     */         {
/*     */           public void run()
/*     */           {
/* 769 */             WebAppActivity.a(WebAppActivity.this, 0L);
/*     */           }
/*     */         }
/*     */         , 1000L);
/*     */ 
/* 772 */         return;
/*     */       }
/*     */ 
/* 775 */       if (System.currentTimeMillis() - this.y > 1000L) {
/* 776 */         this.y = 0L;
/* 777 */         c();
/* 778 */         return;
/*     */       }
/* 780 */       String str = this.b;
/* 781 */       if ((str == null) && (getIntent() != null) && (getIntent().hasExtra("appid"))) {
/* 782 */         str = getIntent().getStringExtra("appid");
/*     */       }
/* 784 */       if (str != null) {
/* 785 */         if (TestUtil.PointTime.hasPointTime(TestUtil.STREAM_APP_POINT)) {
/* 786 */           TestUtil.PointTime.getPointTime(TestUtil.STREAM_APP_POINT).point();
/*     */         }
/* 788 */         TestUtil.PointTime.commit(this, str, 2, 2, "");
/* 789 */         AppStatus.setAppStatus(str, 0);
/*     */       } else {
/* 791 */         Logger.e("onBackPressed appid 不能为null的");
/*     */       }
/* 793 */       Logger.i("WebAppActivity.onBackPressed finish");
/* 794 */       finish();
/* 795 */       PlatformUtil.invokeMethod("io.dcloud.appstream.StreamAppMainActivity", "closeSplashPage", null, new Class[] { Boolean.TYPE }, new Object[] { Boolean.valueOf(false) });
/* 796 */       return;
/*     */     }
/*     */ 
/* 799 */     super.onBackPressed();
/*     */   }
/*     */ 
/*     */   public static String getErrorTipMsg() {
/* 803 */     String str = "";
/* 804 */     if (TestUtil.PointTime.mEc == 4)
/* 805 */       str = str + ", 无SD卡！";
/* 806 */     else if (TestUtil.PointTime.mEc == 9)
/* 807 */       str = str + ", SD卡空间不足！";
/* 808 */     else if (TestUtil.PointTime.mEt == 1)
/* 809 */       str = str + ", 配置文件下载失败！";
/* 810 */     else if ((TestUtil.PointTime.mEt == 3) || (TestUtil.PointTime.mEt == 2)) {
/* 811 */       str = str + ", 应用资源下载失败！";
/*     */     }
/* 813 */     return str;
/*     */   }
/*     */ 
/*     */   class a extends View
/*     */   {
/*     */     int a;
/*     */     float b;
/* 394 */     int c = 0;
/* 395 */     Paint d = new Paint();
/* 396 */     int e = 0;
/* 397 */     int f = 0;
/* 398 */     int g = 255;
/*     */ 
/* 400 */     a(Context arg2) { super();
/* 401 */       this.a = localContext.getResources().getDisplayMetrics().widthPixels;
/*     */ 
/* 403 */       int i = localContext.getResources().getDisplayMetrics().heightPixels;
/* 404 */       switch (i) {
/*     */       case 1920:
/* 406 */         this.b = 9.0F;
/* 407 */         break;
/*     */       case 1280:
/* 409 */         this.b = 6.0F;
/* 410 */         break;
/*     */       default:
/* 412 */         this.b = (localContext.getResources().getDisplayMetrics().heightPixels * 0.0045F);
/*     */       }
/*     */     }
/*     */ 
/*     */     protected void onMeasure(int paramInt1, int paramInt2)
/*     */     {
/* 421 */       super.onMeasure(paramInt1, paramInt2);
/* 422 */       setMeasuredDimension(this.a, this.c + (int)this.b);
/*     */     }
/*     */ 
/*     */     void a()
/*     */     {
/* 428 */       a(100);
/*     */     }
/*     */ 
/*     */     void b() {
/* 432 */       postDelayed(new Runnable()
/*     */       {
/*     */         public void run() {
/* 435 */           WebAppActivity.a.this.g -= 5;
/*     */ 
/* 439 */           if (WebAppActivity.a.this.g > 0) {
/* 440 */             WebAppActivity.a.this.postDelayed(this, 5L);
/*     */           } else {
/* 442 */             ViewGroup localViewGroup = (ViewGroup)WebAppActivity.a.this.getParent();
/*     */ 
/* 444 */             if (localViewGroup != null) {
/* 445 */               localViewGroup.removeView(WebAppActivity.a.this);
/*     */             }
/*     */           }
/* 448 */           WebAppActivity.a.this.invalidate();
/*     */         }
/*     */       }
/*     */       , 50L);
/*     */     }
/*     */ 
/*     */     void a(int paramInt)
/*     */     {
/* 455 */       paramInt = this.a * paramInt / 100;
/* 456 */       if (this.e >= this.f) {
/* 457 */         postDelayed(new Runnable()
/*     */         {
/*     */           public void run() {
/* 460 */             int i = (WebAppActivity.a.this.f - WebAppActivity.a.this.e) / 10;
/* 461 */             if (i > 10)
/* 462 */               i = 10;
/* 463 */             else if (i < 1) {
/* 464 */               i = 1;
/*     */             }
/* 466 */             WebAppActivity.a.this.e += i;
/*     */ 
/* 468 */             if (WebAppActivity.a.this.f > WebAppActivity.a.this.e)
/* 469 */               WebAppActivity.a.this.postDelayed(this, 5L);
/* 470 */             else if (WebAppActivity.a.this.f >= WebAppActivity.a.this.a) {
/* 471 */               WebAppActivity.a.this.b();
/*     */             }
/* 473 */             WebAppActivity.a.this.invalidate();
/*     */           }
/*     */         }
/*     */         , 5L);
/*     */       }
/*     */ 
/* 477 */       this.f = paramInt;
/*     */     }
/*     */ 
/*     */     protected void onDraw(Canvas paramCanvas)
/*     */     {
/* 484 */       super.onDraw(paramCanvas);
/*     */ 
/* 487 */       this.d.setColor(Color.argb(this.g, 26, 173, 25));
/* 488 */       paramCanvas.drawRect(0.0F, this.c, this.e, this.c + this.b, this.d);
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.WebAppActivity
 * JD-Core Version:    0.6.2
 */