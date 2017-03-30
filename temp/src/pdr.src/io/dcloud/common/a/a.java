/*      */ package io.dcloud.common.a;
/*      */ 
/*      */ import android.app.Activity;
/*      */ import android.app.AlertDialog;
/*      */ import android.app.AlertDialog.Builder;
/*      */ import android.content.Context;
/*      */ import android.content.DialogInterface;
/*      */ import android.content.DialogInterface.OnClickListener;
/*      */ import android.content.Intent;
/*      */ import android.content.SharedPreferences;
/*      */ import android.content.SharedPreferences.Editor;
/*      */ import android.content.pm.PackageInfo;
/*      */ import android.content.pm.PackageManager;
/*      */ import android.content.res.Resources;
/*      */ import android.graphics.Bitmap;
/*      */ import android.graphics.BitmapFactory;
/*      */ import android.os.Build.VERSION;
/*      */ import android.text.TextUtils;
/*      */ import android.util.DisplayMetrics;
/*      */ import android.util.Log;
/*      */ import android.view.View;
/*      */ import android.widget.CheckBox;
/*      */ import com.nostra13.dcloudimageloader.cache.disc.DiscCacheAware;
/*      */ import com.nostra13.dcloudimageloader.core.ImageLoader;
/*      */ import com.nostra13.dcloudimageloader.core.assist.FailReason;
/*      */ import com.nostra13.dcloudimageloader.core.assist.ImageLoadingListener;
/*      */ import io.dcloud.RInformation;
/*      */ import io.dcloud.common.DHInterface.AbsMgr;
/*      */ import io.dcloud.common.DHInterface.IActivityHandler;
/*      */ import io.dcloud.common.DHInterface.IApp;
/*      */ import io.dcloud.common.DHInterface.ICallBack;
/*      */ import io.dcloud.common.DHInterface.ICore;
/*      */ import io.dcloud.common.DHInterface.IFrameView;
/*      */ import io.dcloud.common.DHInterface.IMgr.AppEvent;
/*      */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*      */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*      */ import io.dcloud.common.DHInterface.IWebview;
/*      */ import io.dcloud.common.DHInterface.IWebviewStateListener;
/*      */ import io.dcloud.common.adapter.io.DHFile;
/*      */ import io.dcloud.common.adapter.io.UnicodeInputStream;
/*      */ import io.dcloud.common.adapter.util.DeviceInfo;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*      */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.PlatformUtil;
/*      */ import io.dcloud.common.constant.DataInterface;
/*      */ import io.dcloud.common.constant.StringConst;
/*      */ import io.dcloud.common.util.BaseInfo;
/*      */ import io.dcloud.common.util.BaseInfo.BaseAppInfo;
/*      */ import io.dcloud.common.util.BaseInfo.CmtInfo;
/*      */ import io.dcloud.common.util.CheckSignatureUtil;
/*      */ import io.dcloud.common.util.DialogUtil;
/*      */ import io.dcloud.common.util.IOUtil;
/*      */ import io.dcloud.common.util.JSONUtil;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import io.dcloud.common.util.ReflectUtils;
/*      */ import io.dcloud.common.util.ShortCutUtil;
/*      */ import io.dcloud.common.util.ThreadPool;
/*      */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.nio.charset.Charset;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Set;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ public final class a extends AbsMgr
/*      */   implements IMgr.AppEvent
/*      */ {
/*   83 */   c a = null;
/*      */ 
/*   86 */   ArrayList<String> b = new ArrayList(1);
/*      */ 
/*   88 */   ArrayList<d> c = new ArrayList(1);
/*      */ 
/*   90 */   b d = null;
/*   91 */   Class[] e = new Class[0];
/*   92 */   private JSONObject f = null;
/*      */   private AlertDialog g;
/*      */ 
/*      */   public a(ICore paramICore)
/*      */   {
/*   95 */     super(paramICore, "appmgr", IMgr.MgrType.AppMgr);
/*      */ 
/*   97 */     b();
/*   98 */     a();
/*      */ 
/*  100 */     InputStream localInputStream = PlatformUtil.getResInputStream(BaseInfo.sApiConfigPath);
/*  101 */     if (localInputStream != null) {
/*      */       try {
/*  103 */         UnicodeInputStream localUnicodeInputStream = new UnicodeInputStream(localInputStream, Charset.defaultCharset().name());
/*  104 */         String str = new String(IOUtil.getBytes(localUnicodeInputStream));
/*  105 */         this.f = new JSONObject(str);
/*      */       } catch (IOException localIOException) {
/*  107 */         localIOException.printStackTrace();
/*      */       } catch (JSONException localJSONException) {
/*  109 */         localJSONException.printStackTrace();
/*      */       } catch (Exception localException) {
/*  111 */         localException.printStackTrace();
/*      */       }
/*      */     }
/*      */ 
/*  115 */     this.d = new b(this);
/*  116 */     if (BaseInfo.isQihooLifeHelper(getContext()))
/*  117 */       PlatformUtil.invokeMethod("io.dcloud.oauth.qihoosdk.QihooOAuthService", "autoLogin", null, this.e, new Object[0]);
/*      */   }
/*      */ 
/*      */   public Object processEvent(IMgr.MgrType paramMgrType, int paramInt, Object paramObject)
/*      */   {
/*  123 */     Object localObject1 = null;
/*      */     try {
/*  125 */       if (!checkMgrId(paramMgrType)) {
/*  126 */         localObject1 = this.mCore.dispatchEvent(paramMgrType, paramInt, paramObject);
/*      */       }
/*      */       else
/*      */       {
/*      */         Object localObject2;
/*      */         Object localObject4;
/*      */         Object localObject5;
/*      */         Object localObject6;
/*      */         Object localObject9;
/*      */         Object localObject10;
/*      */         Object localObject7;
/*      */         Object localObject8;
/*      */         Object localObject3;
/*      */         String str3;
/*  128 */         switch (paramInt) {
/*      */         case 15:
/*  130 */           localObject2 = (Object[])paramObject;
/*  131 */           localObject4 = (IApp)localObject2[0];
/*  132 */           localObject5 = (String)localObject2[1];
/*  133 */           localObject6 = (String)localObject2[2];
/*  134 */           String str1 = (String)localObject2[3];
/*  135 */           boolean bool2 = ((d)localObject4).a((String)localObject5, (String)localObject6);
/*  136 */           String str4 = null;
/*  137 */           localObject9 = null;
/*  138 */           if (this.f != null) {
/*  139 */             if (!TextUtils.isEmpty(str1)) {
/*  140 */               localObject5 = (String)localObject5 + "-" + str1;
/*      */             }
/*  142 */             JSONArray localJSONArray = this.f.optJSONArray((String)localObject5);
/*  143 */             if (localJSONArray != null)
/*  144 */               for (int k = 0; k < localJSONArray.length(); k++) {
/*  145 */                 localObject10 = localJSONArray.optJSONObject(k);
/*  146 */                 String str5 = ((JSONObject)localObject10).optString("action");
/*  147 */                 if (("*".equals(str5)) || (PdrUtil.isEquals((String)localObject6, str5))) {
/*  148 */                   str4 = ((JSONObject)localObject10).optString("desc");
/*  149 */                   localObject9 = (String)localObject5 + "_" + str5;
/*  150 */                   break;
/*      */                 }
/*      */               }
/*      */             else
/*  154 */               bool2 = true;
/*      */           }
/*      */           else {
/*  157 */             bool2 = true;
/*      */           }
/*  159 */           localObject1 = new String[] { String.valueOf(!bool2 ? 1 : false), String.format(str4, new Object[] { ((IApp)localObject4).obtainAppName() }), localObject9 };
/*  160 */           break;
/*      */         case 16:
/*  163 */           localObject2 = (Object[])paramObject;
/*  164 */           localObject4 = (IApp)localObject2[0];
/*  165 */           localObject5 = (String)localObject2[1];
/*  166 */           ((d)localObject4).a((String)localObject5, 1);
/*  167 */           break;
/*      */         case 11:
/*  170 */           if (this.d.a() != null)
/*  171 */             localObject1 = this.d.a().h; break;
/*      */         case 9:
/*  176 */           localObject2 = (Object[])paramObject;
/*  177 */           localObject4 = (IApp)localObject2[0];
/*  178 */           localObject5 = (IWebviewStateListener)localObject2[1];
/*  179 */           localObject1 = ((d)localObject4).a((IWebviewStateListener)localObject5);
/*  180 */           break;
/*      */         case 14:
/*  183 */           localObject2 = String.valueOf(paramObject);
/*  184 */           if (((String)localObject2).endsWith("/")) {
/*  185 */             localObject2 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
/*      */           }
/*  187 */           localObject4 = ((String)localObject2).substring(((String)localObject2).lastIndexOf("/") + 1);
/*  188 */           localObject5 = null;
/*  189 */           if (c((String)localObject4))
/*  190 */             localObject5 = a((String)localObject4);
/*      */           else {
/*  192 */             localObject5 = b((String)localObject2, (String)localObject4);
/*      */           }
/*  194 */           if (!((d)localObject5).b.a) {
/*  195 */             localObject1 = localObject5;
/*  196 */             a((d)localObject5); } break;
/*      */         case 8:
/*  201 */           localObject2 = (String[])paramObject;
/*  202 */           localObject4 = localObject2[0];
/*  203 */           localObject5 = localObject2[1];
/*  204 */           localObject6 = localObject2[2];
/*  205 */           byte b1 = Byte.parseByte(localObject2[3]);
/*  206 */           localObject1 = a((String)localObject4, (String)localObject5, (String)localObject6, b1);
/*  207 */           break;
/*      */         case 5:
/*  210 */           localObject2 = String.valueOf(paramObject);
/*  211 */           localObject4 = a((String)localObject2);
/*  212 */           if (localObject4 != null)
/*  213 */             localObject1 = ((d)localObject4).k(); break;
/*      */         case 6:
/*  218 */           localObject2 = String.valueOf(paramObject);
/*  219 */           localObject1 = a((String)localObject2);
/*  220 */           break;
/*      */         case 3:
/*  223 */           localObject2 = String.valueOf(paramObject);
/*  224 */           localObject4 = a((String)localObject2, true);
/*  225 */           if (localObject4 != null) {
/*  226 */             if (!((d)localObject4).d())
/*  227 */               Logger.e("appmgr", "reboot " + (String)localObject2 + " app failed !!!");
/*      */           }
/*      */           else {
/*  230 */             Logger.e("appmgr", "not found " + (String)localObject2 + " app!!!");
/*      */           }
/*  232 */           break;
/*      */         case 4:
/*  235 */           localObject2 = (Object[])paramObject;
/*  236 */           localObject4 = String.valueOf(localObject2[0]);
/*  237 */           localObject5 = (JSONObject)localObject2[1];
/*  238 */           localObject6 = (IWebview)localObject2[2];
/*  239 */           localObject7 = JSONUtil.getString((JSONObject)localObject5, "appid");
/*      */ 
/*  241 */           localObject8 = JSONUtil.getString((JSONObject)localObject5, "recognise");
/*  242 */           boolean bool4 = Boolean.parseBoolean((String)localObject8);
/*  243 */           if ((bool4) && (!TextUtils.isEmpty((CharSequence)localObject4)) && (!((String)localObject4).toLowerCase().endsWith(".wgtu")) && (!((String)localObject4).toLowerCase().endsWith(".wgt")))
/*      */           {
/*  247 */             if (!CheckSignatureUtil.check((String)localObject4)) {
/*  248 */               localObject9 = new Object[2];
/*  249 */               localObject9[0] = Boolean.valueOf(true);
/*  250 */               localObject9[1] = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(10), "校验不通过!" });
/*      */ 
/*  254 */               return localObject9;
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*  259 */           if (PdrUtil.isEmpty(localObject7)) {
/*  260 */             localObject7 = ((IWebview)localObject6).obtainFrameView().obtainApp().obtainAppId();
/*      */           }
/*  262 */           long l = System.currentTimeMillis();
/*  263 */           Logger.d("appmgr", "install begin _filePath = " + (String)localObject4 + ";_mayBeAppid = " + (String)localObject7);
/*  264 */           d locald4 = a((String)localObject4, (String)localObject7, (JSONObject)localObject5);
/*  265 */           Logger.d("appmgr", "install end useTime=" + (System.currentTimeMillis() - l));
/*  266 */           localObject10 = new Object[2];
/*  267 */           localObject10[0] = Boolean.valueOf(locald4.b.a);
/*  268 */           localObject10[1] = locald4.b.b;
/*  269 */           localObject1 = localObject10;
/*  270 */           break;
/*      */         case 10:
/*  273 */           localObject2 = null;
/*  274 */           if ((paramObject instanceof String))
/*  275 */             localObject2 = a(String.valueOf(paramObject), false);
/*  276 */           else if ((paramObject instanceof d)) {
/*  277 */             localObject2 = (d)paramObject;
/*      */           }
/*  279 */           if (localObject2 != null)
/*  280 */             ((d)localObject2).g(); break;
/*      */         case 13:
/*  285 */           localObject2 = (d)paramObject;
/*      */ 
/*  291 */           if (localObject2 != null)
/*  292 */             localObject1 = Boolean.valueOf(((d)localObject2).h());
/*      */           else {
/*  294 */             localObject1 = "false";
/*      */           }
/*  296 */           break;
/*      */         case 19:
/*  299 */           localObject1 = this.d.b();
/*  300 */           break;
/*      */         case 12:
/*  303 */           localObject2 = (String)paramObject;
/*  304 */           localObject4 = a((String)localObject2, false);
/*  305 */           if (localObject4 != null) {
/*  306 */             if (((d)localObject4).d == 3)
/*  307 */               localObject1 = Byte.valueOf(((d)localObject4).d = ((d)localObject4).n() ? ((d)localObject4).d : 2);
/*      */             else
/*  309 */               localObject1 = Byte.valueOf(((d)localObject4).d);
/*      */           }
/*      */           else {
/*  312 */             localObject1 = Byte.valueOf((byte)1);
/*      */           }
/*  314 */           break;
/*      */         case 20:
/*      */         case 21:
/*  318 */           int i = 1;
/*  319 */           localObject4 = null;
/*  320 */           localObject5 = null;
/*  321 */           localObject6 = null;
/*  322 */           if ((paramObject instanceof String)) {
/*  323 */             localObject4 = (String)paramObject;
/*      */           } else {
/*  325 */             localObject7 = (Object[])paramObject;
/*  326 */             localObject6 = (Activity)localObject7[0];
/*  327 */             localObject5 = (Intent)localObject7[1];
/*  328 */             localObject4 = (String)localObject7[2];
/*      */           }
/*  330 */           localObject7 = this.d.a((String)localObject4);
/*  331 */           localObject1 = Boolean.valueOf(true);
/*  332 */           if (localObject7 != null) {
/*  333 */             localObject8 = this.d.a();
/*  334 */             if (localObject8 != localObject7) {
/*  335 */               if (localObject8 != null) {
/*  336 */                 ((d)localObject8).f();
/*      */               }
/*  338 */               if (localObject5 != null)
/*  339 */                 ((d)localObject7).setWebAppIntent((Intent)localObject5);
/*      */               else {
/*  341 */                 localObject5 = ((d)localObject7).obtainWebAppIntent();
/*      */               }
/*  343 */               if (21 == paramInt) {
/*  344 */                 ((Intent)localObject5).putExtra("__webapp_reply__", true);
/*      */               }
/*  346 */               ((d)localObject7).getActivity().setIntent((Intent)localObject5);
/*  347 */               Logger.d("appmgr", (String)localObject4 + " will unactive change to active STREAM_START_APP");
/*  348 */               if (((d)localObject7).d == 2) {
/*  349 */                 ((d)localObject7).e();
/*      */               }
/*      */ 
/*      */             }
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*  358 */             localObject1 = Boolean.valueOf(false);
/*      */           }
/*  360 */           break;
/*      */         case 0:
/*  363 */           localObject3 = (Object[])paramObject;
/*  364 */           localObject4 = (Activity)localObject3[0];
/*  365 */           localObject5 = String.valueOf(localObject3[1]);
/*  366 */           localObject6 = String.valueOf(localObject3[2]);
/*  367 */           Log.i("ylyl", " AppMGr START_APP " + (String)localObject5);
/*  368 */           Logger.e("appMgr", "START_APP" + (String)localObject5);
/*  369 */           localObject7 = a((Activity)localObject4, (String)localObject5);
/*  370 */           final boolean bool3 = TextUtils.isEmpty(((d)localObject7).F);
/*  371 */           if (localObject7 != null) {
/*  372 */             final d locald2 = this.d.a();
/*  373 */             final d locald3 = this.d.a((Activity)localObject4, (d)localObject7);
/*      */ 
/*  375 */             if ((locald3 == null) || ((locald3 != null) && (locald3 != localObject7)) || (((d)localObject7).e))
/*      */             {
/*  377 */               boolean bool5 = ((d)localObject7).isStreamApp();
/*  378 */               if (bool5) {
/*  379 */                 int m = locald3 == null ? 1 : 0;
/*  380 */                 localObject10 = PlatformUtil.getBundleData("pdr", "test_runing" + (String)localObject5);
/*  381 */                 if (!TextUtils.isEmpty((CharSequence)localObject10)) {
/*  382 */                   m = 0;
/*      */ 
/*  384 */                   if (((String)localObject10).equals("popped")) {
/*  385 */                     PlatformUtil.removeBundleData("pdr", "test_runing" + (String)localObject5);
/*      */                   }
/*      */ 
/*      */                 }
/*      */ 
/*  392 */                 boolean bool6 = false;
/*  393 */                 if (!((d)localObject7).e) {
/*  394 */                   bool6 = ((Activity)localObject4).getIntent().getBooleanExtra("__start_first_web__", false);
/*      */                 }
/*  396 */                 if ((!((d)localObject7).isCompetentStreamApp()) && (m != 0) && (!bool6) && (((d)localObject7).d == 1)) {
/*  397 */                   if ((this.g != null) && (this.g.isShowing())) {
/*  398 */                     this.g.dismiss();
/*      */                   }
/*  400 */                   Object localObject11 = localObject4;
/*  401 */                   this.g = DialogUtil.initDialogTheme((Activity)localObject4, !BaseInfo.isForQihooHelper(localObject11)).create();
/*  402 */                   this.g.setCanceledOnTouchOutside(false);
/*  403 */                   String str6 = "当前应用为开发者测试版本，未经审核，可能存在安全隐患";
/*  404 */                   final CheckBox localCheckBox = new CheckBox((Context)localObject4);
/*  405 */                   localCheckBox.setText("信任此应用，不再提示");
/*  406 */                   localCheckBox.setTextColor(-65536);
/*  407 */                   this.g.setMessage(str6);
/*  408 */                   this.g.setView(localCheckBox, DeviceInfo.getDeivceSuitablePixel((Activity)localObject4, 20), 0, 0, 0);
/*  409 */                   DialogInterface.OnClickListener local1 = new DialogInterface.OnClickListener()
/*      */                   {
/*      */                     public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/*  412 */                       if (paramAnonymousInt == -2) {
/*  413 */                         a.a(a.this).dismiss();
/*  414 */                         IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(this.a);
/*  415 */                         if (localIActivityHandler != null) {
/*  416 */                           localIActivityHandler.closeAppStreamSplash(this.b);
/*  417 */                           BaseInfo.setLoadingLaunchePage(false, "closeSplashScreen0");
/*  418 */                           if (a.this.d.d() == 0) {
/*  419 */                             this.a.finish();
/*      */                           } else {
/*  421 */                             if (locald2 != null) {
/*  422 */                               locald2.f();
/*      */                             }
/*  424 */                             Intent localIntent = new Intent("android.intent.action.MAIN");
/*  425 */                             localIntent.addCategory("android.intent.category.HOME");
/*  426 */                             this.a.startActivity(localIntent);
/*      */                           }
/*      */                         }
/*  429 */                       } else if ((paramAnonymousInt != -3) && 
/*  430 */                         (paramAnonymousInt == -1)) {
/*  431 */                         if (localCheckBox.isChecked()) {
/*  432 */                           PlatformUtil.setBundleData("pdr", "test_runing" + this.b, "__am=t");
/*      */                         }
/*  434 */                         a.this.a(this.a, this.b, this.e, locald2, this.f, locald3, bool3);
/*  435 */                         a.a(a.this).dismiss();
/*      */                       }
/*      */                     }
/*      */                   };
/*  439 */                   this.g.setButton(-2, localObject11.getResources().getString(17039360), local1);
/*      */ 
/*  441 */                   this.g.setButton(-1, localObject11.getResources().getString(17039370), local1);
/*  442 */                   Logger.e("yl", "test show ");
/*  443 */                   this.g.show();
/*      */                 } else {
/*  445 */                   a((Activity)localObject4, (String)localObject5, (String)localObject6, locald2, (d)localObject7, locald3, bool3);
/*      */                 }
/*      */               } else {
/*  448 */                 a((Activity)localObject4, (String)localObject5, (String)localObject6, locald2, (d)localObject7, locald3, bool3);
/*      */               }
/*      */             }
/*      */           } else {
/*  452 */             Logger.e("appmgr", "not found " + (String)localObject5 + " app!!! 检测manifest.json里appid是否与control.xml中配置项符合");
/*      */           }
/*  454 */           localObject1 = localObject7;
/*  455 */           break;
/*      */         case 7:
/*  458 */           localObject3 = (String)paramObject;
/*  459 */           if (this.a == null) {
/*  460 */             this.a = new c(this);
/*      */           }
/*  462 */           this.a.a((String)localObject3);
/*  463 */           Logger.d("appmgr", "data=" + (String)localObject3);
/*  464 */           break;
/*      */         case 1:
/*  466 */           localObject4 = (Object[])paramObject;
/*  467 */           localObject5 = null;
/*  468 */           if ((localObject4[2] instanceof IApp))
/*  469 */             localObject5 = (d)localObject4[2];
/*      */           else {
/*  471 */             localObject5 = a(String.valueOf(localObject4[2]), false);
/*      */           }
/*  473 */           boolean bool1 = false;
/*  474 */           if ((BaseInfo.sRuntimeMode != null) || (localObject5 != null) || (d.a((ISysEventListener.SysEventType)localObject4[0]))) {
/*  475 */             bool1 = this.d.a((d)localObject5, (ISysEventListener.SysEventType)localObject4[0], localObject4[1]);
/*      */           }
/*  477 */           if ((!bool1) && (localObject5 != null) && (((ISysEventListener.SysEventType)localObject4[0]).equals(ISysEventListener.SysEventType.onKeyUp))) {
/*  478 */             int j = ((Integer)((Object[])(Object[])localObject4[1])[0]).intValue();
/*  479 */             str3 = (String)localObject4[2];
/*  480 */             if (j == 4) {
/*  481 */               processEvent(IMgr.MgrType.WindowMgr, 20, localObject5);
/*  482 */               bool1 = true;
/*      */             }
/*      */           }
/*  485 */           localObject1 = Boolean.valueOf(bool1);
/*  486 */           break;
/*      */         case 2:
/*  489 */           localObject4 = String.valueOf(paramObject);
/*  490 */           if (PdrUtil.isDeviceRootDir((String)localObject4)) {
/*  491 */             localObject1 = DHFile.getInputStream(DHFile.createFileHandler((String)localObject4));
/*      */           } else {
/*  493 */             localObject5 = this.d.a().h;
/*  494 */             d locald1 = a((String)localObject5);
/*  495 */             if (((String)localObject4).startsWith("_www/")) {
/*  496 */               if (locald1 != null)
/*  497 */                 localObject1 = locald1.obtainResInStream((String)localObject4);
/*      */             }
/*  499 */             else if (((String)localObject4).startsWith("_doc/")) {
/*  500 */               String str2 = ((String)localObject4).substring("_doc/".length());
/*  501 */               str3 = locald1.obtainAppDocPath() + str2;
/*  502 */               localObject1 = DHFile.getInputStream(DHFile.createFileHandler(str3));
/*      */             }
/*      */           }
/*  505 */           break;
/*      */         case 18:
/*  508 */           localObject4 = (IApp)paramObject;
/*  509 */           ((d)localObject4).a();
/*  510 */           break;
/*      */         case 17:
/*  514 */           localObject4 = String.valueOf(paramObject);
/*  515 */           b((String)localObject4);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */     catch (Throwable localThrowable)
/*      */     {
/*  523 */       Logger.w("AppMgr.processEvent", localThrowable);
/*      */     }
/*  525 */     return localObject1;
/*      */   }
/*      */ 
/*      */   private void d(final d paramd) {
/*  529 */     String str = DataInterface.getIconImageUrl(paramd.obtainAppId(), paramd.getActivity().getResources().getDisplayMetrics().widthPixels + "");
/*  530 */     ImageLoader.getInstance().loadImage(str, new ImageLoadingListener() {
/*      */       public void onLoadingStarted(String paramAnonymousString, View paramAnonymousView) {
/*      */       }
/*      */ 
/*      */       public void onLoadingFailed(String paramAnonymousString, View paramAnonymousView, FailReason paramAnonymousFailReason) {
/*  535 */         if (StringConst.canChangeHost(paramAnonymousString)) {
/*  536 */           paramAnonymousString = StringConst.changeHost(paramAnonymousString);
/*  537 */           ImageLoader.getInstance().loadImage(paramAnonymousString, this);
/*      */         }
/*      */       }
/*      */ 
/*      */       public void onLoadingComplete(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap) {
/*  542 */         String str = ImageLoader.getInstance().getDiscCache().get(paramAnonymousString).getPath();
/*  543 */         if (paramAnonymousBitmap == null) {
/*  544 */           if ((!TextUtils.isEmpty(str)) && (str.startsWith("file://"))) {
/*  545 */             str = str.substring("file://".length());
/*  546 */             paramAnonymousBitmap = BitmapFactory.decodeFile(str);
/*      */           }
/*  548 */           if (paramAnonymousBitmap == null) {
/*  549 */             paramAnonymousBitmap = BitmapFactory.decodeResource(paramd.getActivity().getResources(), RInformation.DRAWABLE_ICON);
/*      */           }
/*      */         }
/*  552 */         if (paramAnonymousBitmap != null) {
/*  553 */           int i = paramd.getActivity().getTitleColor();
/*      */           try {
/*  555 */             Constructor localConstructor = ReflectUtils.getObjectConstructor("android.app.ActivityManager$TaskDescription", new Class[] { String.class, Bitmap.class, Integer.TYPE });
/*  556 */             Object localObject = localConstructor.newInstance(new Object[] { paramd.obtainAppName(), paramAnonymousBitmap, Integer.valueOf(i) });
/*  557 */             ReflectUtils.invokeMethod(paramd.getActivity(), "setTaskDescription", new Class[] { localObject.getClass() }, new Object[] { localObject });
/*      */           } catch (Exception localException) {
/*  559 */             localException.printStackTrace();
/*      */           }
/*      */         } else {
/*  562 */           Logger.e("polishing overview", "obtain appstream icon failed.");
/*      */         }
/*      */       }
/*      */ 
/*      */       public void onLoadingCancelled(String paramAnonymousString, View paramAnonymousView)
/*      */       {
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void c() {
/*  573 */     ArrayList localArrayList = this.d.c();
/*  574 */     if ((localArrayList != null) && (localArrayList.size() >= BaseInfo.s_Runing_App_Count_Trim)) {
/*  575 */       int i = localArrayList.size();
/*  576 */       for (int j = i - BaseInfo.s_Runing_App_Count_Trim; 
/*  577 */         (j >= 0) && (j > i - BaseInfo.s_Runing_App_Count_Max); 
/*  578 */         j--) {
/*  579 */         d locald = (d)localArrayList.get(j);
/*  580 */         locald.a(ISysEventListener.SysEventType.onWebAppTrimMemory, locald);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void a(Activity paramActivity, final String paramString1, final String paramString2, d paramd1, final d paramd2, d paramd3, final boolean paramBoolean)
/*      */   {
/*  587 */     Log.i("ylyl", "startOneApp " + paramString1);
/*  588 */     BaseInfo.sLastRunApp = paramString1;
/*  589 */     BaseInfo.CmtInfo localCmtInfo = BaseInfo.getCmitInfo(paramString1);
/*  590 */     if (localCmtInfo.needUpdate) {
/*  591 */       localCmtInfo.templateVersion = paramd2.k;
/*  592 */       localCmtInfo.rptCrs = (paramd2.isStreamApp() ? true : paramd2.t);
/*  593 */       localCmtInfo.rptJse = (paramd2.isStreamApp() ? true : paramd2.u);
/*  594 */       localCmtInfo.plusLauncher = BaseInfo.getLaunchType(paramd2.obtainWebAppIntent());
/*  595 */       Intent localIntent = paramd2.obtainWebAppIntent();
/*  596 */       String str = DataInterface.getStreamappFrom(localIntent);
/*  597 */       localCmtInfo.sfd = str;
/*  598 */       localCmtInfo.needUpdate = false;
/*      */     }
/*  600 */     if (paramd2.d == 3) {
/*  601 */       paramd2.d = (paramd2.n() ? paramd2.d : 2);
/*      */     }
/*      */ 
/*  604 */     if ((paramd1 != null) && (paramd1 != paramd2) && (paramd1 != paramd3)) {
/*  605 */       paramd1.f();
/*      */     }
/*      */ 
/*  608 */     if ((paramd2.d == 1) || ((paramBoolean) && (!paramd2.f)) || (!paramBoolean))
/*      */     {
/*  614 */       Logger.d("appmgr", paramString1 + " will unrunning change to active");
/*  615 */       paramd2.a(paramActivity);
/*  616 */       processEvent(IMgr.MgrType.WindowMgr, 4, new Object[] { paramd2, paramString1 });
/*  617 */       paramd2.a(new ICallBack()
/*      */       {
/*      */         public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject) {
/*  620 */           if ((BaseInfo.s_Runing_App_Count_Trim <= a.this.d.d()) && (BaseInfo.s_Runing_App_Count_Trim > 0) && (BaseInfo.s_Runing_App_Count_Trim < BaseInfo.s_Runing_App_Count_Max))
/*      */           {
/*  625 */             a.b(a.this);
/*      */           }
/*  627 */           boolean bool = paramBoolean ? paramd2.b(paramString2) : paramd2.c(paramString2);
/*  628 */           if ((!paramd2.f) && (paramd2.e)) {
/*  629 */             paramd2.b(paramString2);
/*      */           }
/*  631 */           if (bool) {
/*  632 */             a.this.d.a(paramString1, paramd2);
/*      */           }
/*      */           else {
/*  635 */             Logger.e("appmgr", paramString1 + " run failed!!!");
/*      */           }
/*  637 */           return null;
/*      */         } } );
/*      */     }
/*  640 */     else if (paramd2.d == 2) {
/*  641 */       Logger.d("appmgr", paramString1 + " will unactive change to active");
/*  642 */       paramd2.e();
/*      */     }
/*      */     else
/*      */     {
/*  646 */       Logger.d("appmgr", paramString1 + " is active");
/*      */     }
/*  648 */     if ((paramd2.isStreamApp()) && (Build.VERSION.SDK_INT >= 21)) {
/*  649 */       d(paramd2);
/*      */     }
/*  651 */     if ((paramd3 != null) && (paramd3 != paramd2))
/*      */     {
/*  653 */       int i = 1;
/*  654 */       if (i != 0) {
/*  655 */         paramd3.i();
/*  656 */         paramd3.g();
/*      */       } else {
/*  658 */         paramd3.f();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   d a(String paramString) {
/*  664 */     return a(null, paramString);
/*      */   }
/*      */   d a(Activity paramActivity, String paramString) {
/*  667 */     return a(paramActivity, paramString, true);
/*      */   }
/*      */   private d a(String paramString, boolean paramBoolean) {
/*  670 */     return a(null, paramString, paramBoolean);
/*      */   }
/*      */   private d a(Activity paramActivity, String paramString, boolean paramBoolean) {
/*  673 */     Object localObject = null;
/*  674 */     if (this.b.contains(paramString)) {
/*  675 */       int i = -1;
/*  676 */       if ((i = this.b.indexOf(paramString)) >= 0) {
/*  677 */         localObject = (d)this.c.get(i);
/*      */       }
/*      */     }
/*  680 */     if ((localObject == null) && (paramBoolean))
/*      */     {
/*  682 */       d locald = new d(this, paramString, (byte)0);
/*  683 */       locald.setAppDataPath(BaseInfo.sBaseFsAppsPath + paramString + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
/*  684 */       localObject = locald;
/*  685 */       if (((d)localObject).Z == null) {
/*  686 */         ((d)localObject).Z = paramActivity;
/*      */       }
/*  688 */       if (paramActivity != null) {
/*  689 */         locald.setWebAppIntent(paramActivity.getIntent());
/*      */ 
/*  691 */         b(paramActivity, paramString);
/*      */       }
/*  693 */       locald.a(paramString, null);
/*  694 */       if (locald.b.a) {
/*  695 */         locald.h = paramString;
/*      */       }
/*  697 */       a(locald);
/*  698 */     } else if ((localObject != null) && (paramActivity != null))
/*      */     {
/*  700 */       if (((d)localObject).Z == null) {
/*  701 */         ((d)localObject).Z = paramActivity;
/*      */       }
/*  703 */       if (((d)localObject).Z.getIntent() != null)
/*  704 */         ((d)localObject).setWebAppIntent(((d)localObject).Z.getIntent());
/*      */       else {
/*  706 */         ((d)localObject).Z.setIntent(((d)localObject).obtainWebAppIntent());
/*      */       }
/*  708 */       if (!((d)localObject).e) {
/*  709 */         ((d)localObject).a(paramString, null);
/*      */       }
/*      */     }
/*      */ 
/*  713 */     return localObject;
/*      */   }
/*      */ 
/*      */   private void b(final Activity paramActivity, final String paramString)
/*      */   {
/*  722 */     IActivityHandler localIActivityHandler = DCloudAdapterUtil.getIActivityHandler(paramActivity);
/*  723 */     if ((localIActivityHandler != null) && 
/*  724 */       (localIActivityHandler.isStreamAppMode()) && 
/*  725 */       (InvokeExecutorHelper.StorageUtils.invoke("checkDirResourceComplete", InvokeExecutorHelper.AppidUtils.invoke("getAppFilePathByAppid", paramString), true))) {
/*  726 */       SharedPreferences localSharedPreferences = PlatformUtil.getOrCreateBundle("pdr");
/*  727 */       if (localSharedPreferences.getBoolean(paramString + "_smart_update_packge_success", false)) {
/*  728 */         Logger.d("AppMgr", paramString + " app 安装更新包");
/*      */ 
/*  730 */         InvokeExecutorHelper.AppStreamUpdateManager.invoke("implementUpdate", paramString);
/*  731 */         boolean bool1 = localSharedPreferences.getBoolean(paramString + "_created_shortcut", false);
/*  732 */         if (bool1) {
/*  733 */           final Intent localIntent = paramActivity.getIntent();
/*  734 */           if (localIntent != null) {
/*  735 */             final String str1 = localSharedPreferences.getString(paramString + "_create_shortcut_name", null);
/*  736 */             if (TextUtils.isEmpty(str1)) {
/*  737 */               boolean bool2 = localSharedPreferences.getBoolean(paramString + "_smart_update_need_update_icon", false);
/*  738 */               if (bool2) {
/*  739 */                 String str2 = DataInterface.getIconImageUrl(paramString, paramActivity.getResources().getDisplayMetrics().widthPixels + "");
/*      */ 
/*  743 */                 ImageLoader.getInstance().loadImage(str2, new ImageLoadingListener() {
/*      */                   public void onLoadingStarted(String paramAnonymousString, View paramAnonymousView) {
/*      */                   }
/*      */ 
/*      */                   public void onLoadingFailed(String paramAnonymousString, View paramAnonymousView, FailReason paramAnonymousFailReason) {
/*  748 */                     if (StringConst.canChangeHost(paramAnonymousString)) {
/*  749 */                       paramAnonymousString = StringConst.changeHost(paramAnonymousString);
/*  750 */                       ImageLoader.getInstance().loadImage(paramAnonymousString, this);
/*      */                     }
/*      */                   }
/*      */ 
/*      */                   public void onLoadingComplete(String paramAnonymousString, View paramAnonymousView, Bitmap paramAnonymousBitmap)
/*      */                   {
/*  756 */                     String str = localIntent.getStringExtra("short_cut_class_name");
/*  757 */                     ShortCutUtil.updateShortcutFromDeskTop(paramActivity, paramString, str1, paramAnonymousBitmap, str);
/*      */                   }
/*      */ 
/*      */                   public void onLoadingCancelled(String paramAnonymousString, View paramAnonymousView) {
/*      */                   }
/*      */                 });
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*  767 */         localSharedPreferences.edit().remove(paramString + "_smart_update_need_update").remove(paramString + "_smart_update_packge_success").remove(paramString + "_smart_update_need_update_icon").commit();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   void a()
/*      */   {
/*  781 */     if ((BaseInfo.mBaseAppInfoSet != null) && (!BaseInfo.mBaseAppInfoSet.isEmpty())) {
/*  782 */       Set localSet = BaseInfo.mBaseAppInfoSet.keySet();
/*  783 */       String[] arrayOfString1 = new String[localSet.size()];
/*  784 */       localSet.toArray(arrayOfString1);
/*  785 */       for (String str : arrayOfString1) {
/*  786 */         BaseInfo.BaseAppInfo localBaseAppInfo = (BaseInfo.BaseAppInfo)BaseInfo.mBaseAppInfoSet.get(str);
/*  787 */         if ((!BaseInfo.mUnInstalledAppInfoSet.containsKey(str)) && (!c(str))) {
/*  788 */           d locald = b(BaseInfo.sBaseResAppsPath + str, str);
/*  789 */           if ((locald != null) && (locald.b != null))
/*  790 */             if (!locald.b.a) {
/*  791 */               locald.c = localBaseAppInfo;
/*  792 */               a(locald);
/*      */             } else {
/*  794 */               Logger.e("AppMgr", str + "  app error," + locald.b);
/*      */             }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   void a(d paramd)
/*      */   {
/*  803 */     this.b.add(paramd.obtainAppId());
/*  804 */     this.c.add(paramd);
/*      */   }
/*      */   void b(d paramd) {
/*  807 */     this.b.remove(paramd.h);
/*  808 */     this.c.remove(paramd);
/*      */   }
/*      */   void b(String paramString) {
/*  811 */     d locald = this.d.b(paramString);
/*  812 */     if (locald != null) {
/*  813 */       locald.g();
/*      */ 
/*  816 */       BaseInfo.mUnInstalledAppInfoSet.put(paramString, locald.c);
/*      */ 
/*  818 */       b(locald);
/*      */     }
/*      */   }
/*      */ 
/*      */   d a(String paramString1, String paramString2, String paramString3, byte paramByte) {
/*  823 */     d locald = a(paramString1, false);
/*  824 */     if (locald == null) {
/*  825 */       locald = new d(this, paramString1, paramByte);
/*  826 */       locald.d = 3;
/*  827 */       locald.h = paramString1;
/*  828 */       if (!PdrUtil.isEmpty(paramString2)) {
/*  829 */         locald.setAppDataPath(paramString2);
/*      */       }
/*  831 */       locald.B = paramString3;
/*  832 */       a(locald);
/*  833 */       this.d.a(paramString1, locald);
/*      */     }
/*  835 */     return locald;
/*      */   }
/*      */ 
/*      */   void b()
/*      */   {
/*  843 */     if ((BaseInfo.mInstalledAppInfoSet != null) && (!BaseInfo.mInstalledAppInfoSet.isEmpty())) {
/*  844 */       Set localSet = BaseInfo.mInstalledAppInfoSet.keySet();
/*  845 */       String[] arrayOfString1 = new String[localSet.size()];
/*  846 */       localSet.toArray(arrayOfString1);
/*  847 */       int i = 0;
/*  848 */       for (String str : arrayOfString1) {
/*  849 */         if ((!BaseInfo.mUnInstalledAppInfoSet.containsKey(str)) && (!c(str))) {
/*  850 */           d locald = b(BaseInfo.sBaseFsAppsPath + str, str);
/*  851 */           if ((locald != null) && (locald.b != null) && (!locald.b.a)) {
/*  852 */             locald.A = false;
/*  853 */             a(locald);
/*      */           } else {
/*  855 */             ((BaseInfo.BaseAppInfo)BaseInfo.mInstalledAppInfoSet.get(str)).clearBundleData();
/*  856 */             BaseInfo.mInstalledAppInfoSet.remove(str);
/*  857 */             i = 1;
/*      */           }
/*      */         }
/*      */       }
/*  861 */       if (i != 0)
/*  862 */         BaseInfo.saveInstalledAppInfo();
/*      */     }
/*      */   }
/*      */ 
/*      */   private boolean c(String paramString)
/*      */   {
/*  868 */     return this.b.contains(paramString);
/*      */   }
/*      */   private d b(String paramString1, String paramString2) {
/*  871 */     return a(paramString1, paramString2);
/*      */   }
/*      */   d a(String paramString1, String paramString2) {
/*  874 */     return a(paramString1, paramString2, null);
/*      */   }
/*      */ 
/*      */   d a(String paramString1, String paramString2, JSONObject paramJSONObject)
/*      */   {
/*  889 */     d locald = a(paramString2, false);
/*      */     try {
/*  891 */       if (locald != null) {
/*  892 */         locald.b.a();
/*      */       }
/*  894 */       InputStream localInputStream = null;
/*      */       boolean bool1;
/*  895 */       if ((DHFile.isExist(paramString1)) || (PdrUtil.isDeviceRootDir(paramString1)))
/*      */       {
/*  898 */         bool1 = new File(paramString1).isFile();
/*  899 */         if (bool1)
/*      */         {
/*      */           int i;
/*  900 */           if ((bool1) && (paramString1.toLowerCase().endsWith(".wgtu"))) {
/*  901 */             i = locald == null ? 1 : 0;
/*  902 */             locald = i != 0 ? new d(this, paramString2, (byte)0) : locald;
/*  903 */             locald.b(paramString1, paramJSONObject);
/*  904 */             locald.b.c = false;
/*  905 */             locald.b.d = false;
/*  906 */           } else if ((bool1) && (paramString1.toLowerCase().endsWith(".wgt"))) {
/*  907 */             i = locald == null ? 1 : 0;
/*  908 */             locald.b.d = true;
/*  909 */             if (i != 0) {
/*  910 */               locald = new d(this, paramString2, (byte)0);
/*  911 */               locald.h = paramString2;
/*  912 */               locald.setAppDataPath(BaseInfo.sBaseFsAppsPath + paramString2 + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
/*      */             }
/*  914 */             boolean bool2 = locald.c(paramString1, paramJSONObject);
/*  915 */             locald.b.d = false;
/*  916 */             if ((bool2) && 
/*  917 */               (i != 0)) {
/*  918 */               a(locald);
/*      */             }
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*  928 */             PackageInfo localPackageInfo = null;
/*      */             try {
/*  930 */               localPackageInfo = PlatformUtil.parseApkInfo(getContext(), paramString1);
/*      */             } catch (Exception localException2) {
/*  932 */               localException2.printStackTrace();
/*  933 */               locald.b.b = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(10), localException2.getMessage() });
/*      */             }
/*      */ 
/*  936 */             if (localPackageInfo == null) {
/*  937 */               locald.b.a = true;
/*      */             } else {
/*  939 */               locald.b.a = false;
/*  940 */               String str1 = "{pname:'%s',version:'%s',name:'%s'}";
/*  941 */               String str2 = localPackageInfo.versionName;
/*  942 */               String str3 = localPackageInfo.packageName;
/*  943 */               String str4 = getContext().getPackageManager().getApplicationLabel(localPackageInfo.applicationInfo).toString();
/*  944 */               if (str4 == null) {
/*  945 */                 str4 = "";
/*      */               }
/*  947 */               locald.b.b = String.format(str1, new Object[] { str3, str2, str4 });
/*  948 */               PlatformUtil.openFileBySystem(getContext(), paramString1, null, null);
/*      */             }
/*      */           }
/*      */         } else {
/*  952 */           if (locald != null)
/*  953 */             locald.a((byte)0);
/*      */           else {
/*  955 */             locald = new d(this, paramString2, (byte)0);
/*      */           }
/*  957 */           locald.setAppDataPath(paramString1 + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
/*      */ 
/*  959 */           locald.a(paramString2, paramJSONObject);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  964 */         bool1 = paramString1.substring(paramString1.lastIndexOf('/')).contains(".wgt");
/*  965 */         if (bool1) {
/*  966 */           localInputStream = PlatformUtil.getResInputStream(paramString1);
/*      */         }
/*  968 */         locald = locald == null ? new d(this, paramString2, (byte)1) : locald;
/*  969 */         if ((bool1) || (localInputStream != null)) {
/*  970 */           locald.a(localInputStream);
/*      */         } else {
/*  972 */           locald.setAppDataPath(paramString1 + DeviceInfo.sSeparatorChar + BaseInfo.REAL_PRIVATE_WWW_DIR);
/*      */ 
/*  974 */           locald.a(paramString2, paramJSONObject);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  979 */       IOUtil.close(localInputStream);
/*      */     } catch (Exception localException1) {
/*  981 */       localException1.printStackTrace();
/*  982 */       Logger.e("appmgr", "installWebApp " + paramString1 + " is Illegal path");
/*      */     }
/*  984 */     return locald;
/*      */   }
/*      */ 
/*      */   public void dispose()
/*      */   {
/*  989 */     if (this.c != null) {
/*  990 */       for (d locald : this.c) {
/*  991 */         locald.m();
/*      */       }
/*      */     }
/*  994 */     this.c.clear();
/*  995 */     this.b.clear();
/*  996 */     if (this.d != null) {
/*  997 */       this.d.e();
/*      */     }
/*  999 */     this.d = null;
/* 1000 */     ThreadPool.self().addThreadTask(new Runnable()
/*      */     {
/*      */       public void run() {
/*      */         try {
/* 1004 */           DHFile.deleteFile(StringConst.STREAMAPP_KEY_ROOTPATH + "splash_temp/");
/*      */         } catch (Exception localException) {
/* 1006 */           localException.printStackTrace();
/*      */         }
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   void c(d paramd) {
/* 1013 */     this.d.b(paramd.h);
/* 1014 */     b(paramd);
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.a.a
 * JD-Core Version:    0.6.2
 */