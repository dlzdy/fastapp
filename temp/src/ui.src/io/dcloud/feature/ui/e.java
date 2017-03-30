/*     */ package io.dcloud.feature.ui;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.os.Build;
/*     */ import android.text.TextUtils;
/*     */ import android.util.DisplayMetrics;
/*     */ import android.util.Log;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.webkit.URLUtil;
/*     */ import android.webkit.WebView;
/*     */ import android.widget.AbsoluteLayout.LayoutParams;
/*     */ import android.widget.FrameLayout.LayoutParams;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IApp.ConfigProperty.ThridInfo;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameView;
/*     */ import io.dcloud.common.adapter.ui.AdaWebview;
/*     */ import io.dcloud.common.adapter.ui.WebLoadEvent;
/*     */ import io.dcloud.common.adapter.util.AnimOptions;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*     */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*     */ import io.dcloud.common.adapter.util.ViewOptions;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.nineoldandroids.animation.Animator;
/*     */ import io.dcloud.nineoldandroids.animation.Animator.AnimatorListener;
/*     */ import io.dcloud.nineoldandroids.animation.ValueAnimator;
/*     */ import io.dcloud.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
/*     */ import io.dcloud.nineoldandroids.view.ViewHelper;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class e
/*     */ {
/*  50 */   AbsMgr a = null;
/*     */ 
/*  52 */   HashMap<String, a> b = new HashMap(1);
/*     */ 
/*  54 */   final boolean c = false;
/*  55 */   String d = null;
/*     */ 
/* 724 */   private static HashMap<String, String> e = null;
/*     */ 
/*     */   e(AbsMgr paramAbsMgr, String paramString)
/*     */   {
/*  58 */     this.a = paramAbsMgr;
/*  59 */     this.d = paramString;
/*  60 */     a();
/*     */   }
/*     */ 
/*     */   public synchronized String a(IWebview paramIWebview, String paramString, JSONArray paramJSONArray)
/*     */   {
/*  70 */     String str1 = null;
/*     */     try {
/*  72 */       JSONArray localJSONArray1 = paramJSONArray;
/*     */ 
/*  74 */       String str2 = JSONUtil.getString(localJSONArray1, 0);
/*     */ 
/*  76 */       String str3 = JSONUtil.getString(localJSONArray1, 1);
/*  77 */       JSONArray localJSONArray2 = JSONUtil.getJSONArray(localJSONArray1, 2);
/*     */ 
/*  79 */       String str4 = JSONUtil.getString(localJSONArray2, 0);
/*  80 */       JSONArray localJSONArray3 = JSONUtil.getJSONArray(localJSONArray2, 1);
/*  81 */       IApp localIApp = paramIWebview.obtainFrameView().obtainApp();
/*  82 */       if (localIApp == null)
/*  83 */         return null;
/*  84 */       String str5 = localIApp.obtainAppId();
/*     */ 
/*  86 */       a locala = (a)this.b.get(str5);
/*  87 */       if ((locala == null) || (locala.e.obtainAppStatus() == 1))
/*     */       {
/*  89 */         if ((locala != null) && (locala.e.obtainAppStatus() == 1))
/*     */         {
/*  91 */           this.b.remove(str5);
/*     */         }
/*  93 */         Logger.d("Main_Path", "init AppWidgetMgr pAppid=" + str5);
/*  94 */         locala = new a(this.a, localIApp);
/*  95 */         locala.l = (paramIWebview.obtainFrameView().getFrameType() == 3);
/*  96 */         locala.k = (paramIWebview.obtainFrameView().getFrameType() == 2);
/*  97 */         this.b.put(str5, locala);
/*     */ 
/* 101 */         localObject1 = paramIWebview.obtainFrameView();
/* 102 */         a(str5, locala, (IFrameView)localObject1);
/*     */ 
/* 105 */         IFrameView localIFrameView = (IFrameView)this.a.processEvent(IMgr.MgrType.WindowMgr, 9, str5);
/* 106 */         if ((localIFrameView != null) && (localObject1 != localIFrameView)) {
/* 107 */           locala.k = true;
/* 108 */           a(str5, locala, localIFrameView);
/*     */         }
/*     */ 
/*     */       }
/* 112 */       else if (paramIWebview.obtainFrameView().getFrameType() == 3) {
/* 113 */         if (!locala.l) {
/* 114 */           a(str5, locala, paramIWebview.obtainFrameView());
/* 115 */           locala.l = true;
/*     */         }
/* 117 */       } else if ((paramIWebview.obtainFrameView().getFrameType() == 2) && 
/* 118 */         (!locala.k)) {
/* 119 */         a(str5, locala, paramIWebview.obtainFrameView());
/* 120 */         locala.k = true;
/*     */       }
/*     */ 
/* 124 */       Object localObject1 = null;
/*     */       try {
/* 126 */         localObject1 = a.valueOf(str3);
/*     */       } catch (Exception localException2) {
/* 128 */         localObject1 = null;
/*     */       }
/*     */       Object localObject2;
/*     */       Object localObject3;
/*     */       Object localObject4;
/*     */       Object localObject7;
/*     */       Object localObject8;
/*     */       Object localObject9;
/*     */       int k;
/* 131 */       if (PdrUtil.isEquals("UI", str2))
/*     */       {
/*     */         Object localObject5;
/* 132 */         switch (4.a[localObject1.ordinal()])
/*     */         {
/*     */         case 1:
/* 135 */           localObject2 = JSONUtil.getString(localJSONArray3, 0);
/* 136 */           localObject3 = locala.a(null, null, (String)localObject2);
/*     */ 
/* 138 */           if (localObject3 != null) {
/* 139 */             str1 = ((c)localObject3).d();
/*     */           }
/*     */ 
/* 142 */           break;
/*     */         case 2:
/* 144 */           localObject2 = (IFrameView)this.a.processEvent(IMgr.MgrType.WindowMgr, 44, paramIWebview.obtainApp());
/* 145 */           localObject3 = String.valueOf(localObject2.hashCode());
/* 146 */           localObject4 = locala.a((String)localObject3, (String)localObject3, null);
/*     */ 
/* 148 */           if (localObject4 != null) {
/* 149 */             str1 = ((c)localObject4).d();
/*     */           }
/*     */ 
/* 152 */           break;
/*     */         case 3:
/* 154 */           str1 = locala.d();
/*     */ 
/* 156 */           break;
/*     */         case 4:
/* 158 */           localObject2 = locala.c();
/* 159 */           if (localObject2 != null)
/* 160 */             str1 = ((c)localObject2).d(); break;
/*     */         case 5:
/* 164 */           localObject3 = locala.b();
/* 165 */           if (localObject3 != null)
/* 166 */             str1 = ((c)localObject3).d(); break;
/*     */         case 6:
/* 171 */           localObject3 = locala.a(4);
/* 172 */           if (localObject3 != null)
/* 173 */             str1 = ((c)localObject3).d(); break;
/*     */         case 7:
/* 178 */           localObject3 = paramIWebview.obtainFrameView();
/* 179 */           localObject4 = locala.c((IFrameView)localObject3);
/* 180 */           if (localObject4 != null)
/* 181 */             str1 = ((c)localObject4).d();
/*     */           else {
/* 183 */             Logger.e("Main_Path", "ui.execute " + str5 + " not found NWindow uuid=" + str4 + ";frameView=" + localObject3);
/*     */           }
/*     */ 
/* 189 */           break;
/*     */         case 8:
/* 191 */           localObject3 = JSONUtil.getString(localJSONArray3, 0);
/* 192 */           localObject4 = JSONUtil.getString(localJSONArray3, 1);
/* 193 */           localObject5 = a((String)localObject3);
/* 194 */           localObject7 = JSONUtil.getJSONObject(localJSONArray3, 2);
/*     */ 
/* 196 */           localObject8 = JSONUtil.getString(localJSONArray3, 3);
/*     */ 
/* 198 */           ((b)localObject5).b.put(paramIWebview, localObject8);
/*     */ 
/* 200 */           locala.a((String)localObject4, (b)localObject5);
/* 201 */           ((b)localObject5).a(paramIWebview.getActivity(), locala, paramIWebview, (String)localObject4, (JSONObject)localObject7);
/*     */ 
/* 204 */           break;
/*     */         case 9:
/* 206 */           localObject3 = JSONUtil.getString(localJSONArray3, 0);
/* 207 */           localObject4 = locala.a(str4, str4, null);
/*     */ 
/* 209 */           if (localObject4 != null) {
/* 210 */             ((c)localObject4).b.put(paramIWebview, localObject3);
/*     */           }
/*     */ 
/* 214 */           break;
/*     */         case 10:
/* 219 */           localObject3 = JSONUtil.getString(localJSONArray2, 0);
/* 220 */           localObject4 = JSONUtil.getJSONObject(localJSONArray2, 1);
/*     */           try {
/* 222 */             localObject5 = ((JSONObject)localObject4).optString("0");
/* 223 */             if ("save".equals(localObject5))
/* 224 */               ((AdaWebview)paramIWebview).saveWebViewData((String)localObject3);
/* 225 */             else if ("update".equals(localObject5))
/* 226 */               str1 = ((AdaWebview)paramIWebview).syncUpdateWebViewData((String)localObject3);
/*     */           }
/*     */           catch (Exception localException3) {
/* 229 */             localException3.printStackTrace();
/*     */           }
/*     */ 
/*     */         case 11:
/* 233 */           str1 = JSUtil.wrapJsVar((MobilePhoneModel.checkPhoneBanAcceleration(Build.BRAND)) || ((paramIWebview.obtainFrameView().getFrameType() == 2) && (BaseInfo.isWap2AppAppid(localIApp.obtainAppId())) && (localIApp.isStreamApp())));
/* 234 */           break;
/*     */         case 12:
/* 236 */           JSONObject localJSONObject = JSONUtil.getJSONObject(localJSONArray3, 0);
/* 237 */           localObject7 = JSONUtil.getJSONObject(localJSONArray3, 1);
/* 238 */           localObject8 = JSONUtil.getString(localJSONArray3, 2);
/* 239 */           localObject9 = JSONUtil.getString(localJSONArray3, 3);
/*     */ 
/* 241 */           k = paramIWebview.obtainWebview().getContext().getResources().getDisplayMetrics().widthPixels;
/* 242 */           IWebview localIWebview = null;
/* 243 */           if (!TextUtils.isEmpty((CharSequence)localObject8)) {
/* 244 */             localObject10 = locala.a(null, (String)localObject8, (String)localObject8);
/* 245 */             if (null != localObject10) {
/* 246 */               localIWebview = ((c)localObject10).k();
/*     */             }
/*     */           }
/* 249 */           Object localObject10 = null; String str6 = null;
/* 250 */           int m = 0; int n = 0; int i1 = 0; int i2 = 0;
/* 251 */           View localView1 = null; View localView2 = null;
/* 252 */           c localc1 = null; c localc2 = null;
/* 253 */           int i3 = 0; int i4 = 0;
/*     */           String str7;
/*     */           String str8;
/*     */           int i5;
/*     */           ViewGroup.LayoutParams localLayoutParams;
/* 254 */           if (null != localJSONObject) {
/* 255 */             localObject11 = JSONUtil.getString(localJSONObject, "view");
/* 256 */             localObject10 = JSONUtil.getString(localJSONObject, "action");
/* 257 */             localObject12 = JSONUtil.getJSONObject(localJSONObject, "styles");
/* 258 */             str7 = JSONUtil.getString((JSONObject)localObject12, "fromLeft");
/* 259 */             str8 = JSONUtil.getString((JSONObject)localObject12, "toLeft");
/* 260 */             i5 = PdrUtil.parseInt(str7, k, 2147483647);
/* 261 */             n = PdrUtil.parseInt(str8, k, 2147483647);
/* 262 */             localc1 = locala.a(null, (String)localObject11, (String)localObject11);
/* 263 */             if ((2147483647 != n) && 
/* 264 */               (null != localc1)) {
/* 265 */               localView1 = localc1.u.obtainMainView();
/* 266 */               if (0 == localView1.getVisibility()) {
/* 267 */                 m = 0;
/* 268 */                 if (2147483647 != i5) {
/* 269 */                   m = i5;
/*     */                 } else {
/* 271 */                   localLayoutParams = localView1.getLayoutParams();
/* 272 */                   if ((localLayoutParams instanceof AbsoluteLayout.LayoutParams))
/* 273 */                     m = ((AbsoluteLayout.LayoutParams)localLayoutParams).x;
/* 274 */                   else if ((localLayoutParams instanceof FrameLayout.LayoutParams)) {
/* 275 */                     m = (int)ViewHelper.getX(localView1);
/*     */                   }
/*     */                 }
/* 278 */                 localc1.u.pushToViewStack();
/* 279 */                 localc1.e().obtainFrameOptions().left = n;
/* 280 */                 localc1.e().obtainFrameOptions().checkValueIsPercentage("left", n, k, true, true);
/* 281 */                 i3 = 1;
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/* 286 */           if (null != localObject7) {
/* 287 */             localObject11 = JSONUtil.getString((JSONObject)localObject7, "view");
/* 288 */             str6 = JSONUtil.getString((JSONObject)localObject7, "action");
/* 289 */             localObject12 = JSONUtil.getJSONObject((JSONObject)localObject7, "styles");
/* 290 */             str7 = JSONUtil.getString((JSONObject)localObject12, "fromLeft");
/* 291 */             str8 = JSONUtil.getString((JSONObject)localObject12, "toLeft");
/* 292 */             i5 = PdrUtil.parseInt(str7, k, 2147483647);
/* 293 */             i2 = PdrUtil.parseInt(str8, k, 2147483647);
/* 294 */             localc2 = locala.a(null, (String)localObject11, (String)localObject11);
/* 295 */             if ((2147483647 != i2) && 
/* 296 */               (null != localc2)) {
/* 297 */               localView2 = localc2.u.obtainMainView();
/* 298 */               if (0 == localView2.getVisibility()) {
/* 299 */                 i1 = 0;
/* 300 */                 if (2147483647 != i5) {
/* 301 */                   i1 = i5;
/*     */                 } else {
/* 303 */                   localLayoutParams = localView2.getLayoutParams();
/* 304 */                   if ((localLayoutParams instanceof AbsoluteLayout.LayoutParams))
/* 305 */                     i1 = ((AbsoluteLayout.LayoutParams)localLayoutParams).x;
/* 306 */                   else if ((localLayoutParams instanceof FrameLayout.LayoutParams)) {
/* 307 */                     i1 = (int)ViewHelper.getX(localView2);
/*     */                   }
/*     */                 }
/* 310 */                 localc2.u.pushToViewStack();
/* 311 */                 localc2.e().obtainFrameOptions().left = i2;
/* 312 */                 localc2.e().obtainFrameOptions().checkValueIsPercentage("left", i2, k, true, true);
/* 313 */                 i4 = 1;
/*     */               }
/*     */             }
/*     */           }
/*     */ 
/* 318 */           Object localObject11 = localObject10;
/* 319 */           Object localObject12 = str6;
/* 320 */           if (i3 != 0) {
/* 321 */             a(localView1, m, n, (String)localObject11, localIWebview, (String)localObject9, localc1).start();
/*     */           }
/* 323 */           if (i4 != 0)
/* 324 */             a(localView2, i1, i2, (String)localObject12, localIWebview, (String)localObject9, localc2).start(); break;
/*     */         case 13:
/* 328 */           str1 = locala.e();
/*     */         }
/*     */       }
/* 331 */       else if (PdrUtil.isEquals("NWindow", str2)) {
/* 332 */         if ("NWindow".equals(str3))
/*     */         {
/* 334 */           a(locala, paramIWebview, localJSONArray3, localIApp, str4, false);
/*     */         }
/*     */         else {
/* 337 */           localObject2 = locala.a(str4, str4, null);
/*     */ 
/* 339 */           if (localObject2 != null) {
/* 340 */             str1 = ((c)localObject2).a(paramIWebview, str3, localJSONArray3);
/*     */           }
/*     */           else
/* 343 */             Logger.e("Main_Path", str5 + " App not found NWindow ;uuid=" + str4 + ";_action=" + str3 + ";at " + paramIWebview.obtainFullUrl());
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/*     */         Object localObject6;
/* 348 */         if (PdrUtil.isEquals("WebviewGroup", str2)) {
/* 349 */           if ("createGroup".equals(str3)) {
/* 350 */             localObject2 = new ArrayList();
/* 351 */             localObject3 = JSONUtil.getJSONArray(localJSONArray3, 0);
/* 352 */             localObject4 = JSONUtil.getJSONObject(localJSONArray3, 1);
/* 353 */             if (localObject4 == null) {
/* 354 */               localObject4 = new JSONObject("{}");
/*     */             }
/* 356 */             Logger.d("shutao", "stylesJS---" + ((JSONObject)localObject4).toString());
/* 357 */             for (int i = 0; i < ((JSONArray)localObject3).length(); i++) {
/* 358 */               localObject7 = JSONUtil.getJSONArray((JSONArray)localObject3, i);
/* 359 */               localObject8 = JSONUtil.getJSONArray((JSONArray)localObject7, 1);
/* 360 */               localObject9 = a(locala, paramIWebview, (JSONArray)localObject8, localIApp, str4, true);
/* 361 */               ((c)localObject9).a(true);
/* 362 */               ((ArrayList)localObject2).add(((c)localObject9).u);
/*     */             }
/* 364 */             localObject6 = new d("WebviewGroup", (ArrayList)localObject2, (JSONObject)localObject4);
/* 365 */             localObject7 = (IFrameView)this.a.processEvent(IMgr.MgrType.WindowMgr, 3, new Object[] { Integer.valueOf(1), localIApp, { localObject4 }, paramIWebview.obtainFrameView(), localObject6, localObject2 });
/*     */ 
/* 370 */             ((d)localObject6).a((IFrameView)localObject7);
/* 371 */             ((d)localObject6).a(paramIWebview.getActivity(), locala, paramIWebview, str4, (JSONObject)localObject4);
/*     */ 
/* 373 */             ((d)localObject6).b.put(paramIWebview, localJSONArray3.getString(2));
/* 374 */             locala.a(str4, (b)localObject6);
/* 375 */             if (DeviceInfo.sDeviceSdkVer >= 11)
/* 376 */               locala.a((IFrameView)localObject7);
/*     */           }
/*     */           else {
/* 379 */             localObject2 = locala.a(str4);
/* 380 */             str1 = ((b)localObject2).a(paramIWebview, str3, localJSONArray3);
/*     */           }
/* 382 */         } else if (TextUtils.equals(paramString, "n_createSecondWebview")) {
/* 383 */           localObject2 = paramIWebview;
/* 384 */           localObject3 = localIApp.obtainThridInfo(IApp.ConfigProperty.ThridInfo.SecondWebviewJsonData);
/* 385 */           if (localObject3 != null)
/*     */           {
/* 387 */             localObject4 = ((JSONObject)localObject3).optString("launch_path", null);
/* 388 */             localObject6 = ((JSONObject)localObject3).optString("mode", "front");
/* 389 */             localJSONArray3 = new JSONArray();
/* 390 */             localJSONArray3.put(localObject4);
/*     */ 
/* 392 */             localObject7 = localObject3;
/* 393 */             ((JSONObject)localObject7).put("name", ((JSONObject)localObject3).optString("id", localIApp.obtainAppId() + "__second"));
/* 394 */             if (("child".equals(localObject6)) && (!((JSONObject)localObject7).has("position"))) {
/* 395 */               ((JSONObject)localObject7).put("position", "absolute");
/*     */             }
/* 397 */             ((JSONObject)localObject7).put("winType", 4);
/* 398 */             localJSONArray3.put(localObject3);
/*     */ 
/* 400 */             localObject7 = a(locala, paramIWebview, localJSONArray3, localIApp, str4, false);
/* 401 */             if (str4 == null) {
/* 402 */               ((c)localObject7).e = String.valueOf(((c)localObject7).u.hashCode());
/*     */             }
/*     */ 
/* 405 */             ((c)localObject7).B = true;
/* 406 */             ((c)localObject7).E = true;
/* 407 */             ((AdaFrameView)((c)localObject7).u).getFrameType();
/* 408 */             localObject8 = locala.a(String.valueOf(paramIWebview.obtainFrameView().hashCode()), null, null);
/* 409 */             int j = locala.a.indexOf(localObject8);
/* 410 */             if ("behind".equals(localObject6)) {
/* 411 */               k = j - 1;
/* 412 */               ((c)localObject8).q -= 1L;
/* 413 */               locala.a(str5, (c)localObject7, k < 0 ? 0 : k);
/* 414 */             } else if ("child".equals(localObject6)) {
/* 415 */               ((AdaFrameView)paramIWebview.obtainFrameView()).obtainFrameOptions().updateViewData(new JSONObject("{\"width\":\"100%\",\"height\":\"100%\"}"));
/* 416 */               ((c)localObject8).c((b)localObject7);
/* 417 */               k = locala.a((c)localObject7);
/* 418 */               locala.a(str5, (c)localObject7, k);
/*     */             } else {
/* 420 */               ((c)localObject8).q += 1L;
/* 421 */               k = j + 1;
/* 422 */               locala.a(str5, (c)localObject7, k);
/* 423 */               this.a.processEvent(IMgr.MgrType.WindowMgr, 1, new Object[] { ((c)localObject7).u, Boolean.valueOf(false) });
/*     */             }
/*     */           }
/*     */         } else {
/* 427 */           localObject2 = locala.a(str4);
/* 428 */           str1 = ((b)localObject2).a(paramIWebview, str3, localJSONArray3);
/*     */         }
/*     */       }
/*     */     } catch (Exception localException1) {
/* 432 */       Logger.e("UIWidgetMgr", "pActionName=" + paramString + ";pJsArgs=" + paramJSONArray);
/*     */ 
/* 434 */       localException1.printStackTrace();
/*     */     }
/* 436 */     return str1;
/*     */   }
/*     */ 
/*     */   public void a(String paramString, final a parama, IFrameView paramIFrameView)
/*     */   {
/* 442 */     String str1 = String.valueOf(paramIFrameView.hashCode());
/* 443 */     IWebview localIWebview = paramIFrameView.obtainWebView();
/* 444 */     String str2 = localIWebview.obtainUrl();
/* 445 */     JSONObject localJSONObject = null;
/* 446 */     if ((paramIFrameView != null) && (((AdaFrameView)paramIFrameView).obtainFrameOptions() != null))
/*     */     {
/* 448 */       localJSONObject = ((AdaFrameView)paramIFrameView).obtainFrameOptions().mJsonViewOption;
/*     */     }
/*     */ 
/* 452 */     String str3 = localIWebview.obtainFrameId();
/* 453 */     String str4 = paramIFrameView.getFrameType() == 2 ? paramString : !PdrUtil.isEmpty(str3) ? str3 : str2;
/* 454 */     final c localc = new c(parama, str2, str4, str1, localJSONObject);
/*     */ 
/* 457 */     localc.a(paramIFrameView.getContext(), parama, paramIFrameView.obtainWebView(), str1, localJSONObject);
/*     */ 
/* 459 */     localc.B = (((AdaFrameView)paramIFrameView).obtainMainView().getVisibility() == 0);
/* 460 */     localc.E = true;
/* 461 */     ((AdaFrameView)paramIFrameView).addFrameViewListener(localc);
/* 462 */     localc.a(paramIFrameView, str4);
/* 463 */     parama.c(localc);
/* 464 */     parama.a(paramString, localc, 0);
/* 465 */     MessageHandler.sendMessage(new MessageHandler.IMessages()
/*     */     {
/*     */       public void execute(Object paramAnonymousObject) {
/* 468 */         parama.b(localc);
/*     */       }
/*     */     }
/*     */     , null);
/*     */   }
/*     */ 
/*     */   private c a(a parama, IWebview paramIWebview, JSONArray paramJSONArray, IApp paramIApp, String paramString, boolean paramBoolean)
/*     */     throws Exception
/*     */   {
/* 485 */     c localc1 = parama.c(paramIWebview.obtainFrameView());
/*     */ 
/* 487 */     String str1 = paramJSONArray.getString(0);
/* 488 */     JSONObject localJSONObject1 = paramJSONArray.optJSONObject(1);
/* 489 */     String str2 = paramJSONArray.optString(2);
/*     */ 
/* 491 */     JSONObject localJSONObject2 = paramJSONArray.optJSONObject(4);
/* 492 */     JSONArray localJSONArray = paramJSONArray.optJSONArray(5);
/* 493 */     c localc2 = null;
/* 494 */     String str3 = "";
/* 495 */     if (localJSONObject1 == null)
/* 496 */       localJSONObject1 = new JSONObject("{}");
/*     */     else {
/* 498 */       str3 = JSONUtil.getString(localJSONObject1, "name");
/*     */     }
/*     */ 
/* 501 */     localc2 = a(parama, paramIWebview, paramIApp, str1, str3, paramString, localJSONObject1, localJSONObject2, localJSONArray, paramBoolean);
/*     */ 
/* 504 */     localc1.a(localc2);
/* 505 */     if (str2 != null) {
/* 506 */       localc2.b.put(paramIWebview, str2);
/*     */     }
/*     */ 
/* 509 */     AnimOptions localAnimOptions = ((AdaFrameItem)localc2.u).getAnimOptions();
/*     */ 
/* 512 */     ViewOptions localViewOptions = ((AdaFrameItem)localc2.u).obtainFrameOptions();
/*     */ 
/* 514 */     localc2.G = localViewOptions.hasBackground();
/* 515 */     localAnimOptions.parseTransition(localViewOptions.transition);
/* 516 */     localAnimOptions.parseTransform(localViewOptions.transform);
/* 517 */     return localc2;
/*     */   }
/*     */ 
/*     */   private c a(a parama, IWebview paramIWebview, IApp paramIApp, String paramString1, String paramString2, String paramString3, JSONObject paramJSONObject1, JSONObject paramJSONObject2, JSONArray paramJSONArray, boolean paramBoolean)
/*     */   {
/* 544 */     String str1 = paramIApp.convert2WebviewFullPath(paramIWebview.obtainFullUrl(), paramString1);
/*     */ 
/* 546 */     String str2 = null;
/* 547 */     if (paramBoolean) {
/* 548 */       str2 = str1;
/* 549 */       str1 = null;
/*     */     }
/* 551 */     paramIApp.obtainWebviewBaseUrl();
/*     */ 
/* 553 */     a(paramIWebview, paramIApp, str1);
/*     */ 
/* 555 */     String str3 = paramIApp.obtainAppId();
/* 556 */     IFrameView localIFrameView = null;
/* 557 */     c localc = null;
/* 558 */     int i = !PdrUtil.isEmpty(paramString1) ? 1 : 0;
/* 559 */     localc = new c(parama, paramString1, paramString2, paramString3, paramJSONObject1);
/*     */ 
/* 561 */     localc.t = paramJSONObject2;
/* 562 */     int j = 0;
/* 563 */     j = paramJSONObject1.optInt("winType", j);
/* 564 */     localIFrameView = (IFrameView)this.a.processEvent(IMgr.MgrType.WindowMgr, 3, new Object[] { Integer.valueOf(j), paramIApp, { paramString1, paramJSONObject1 }, paramIWebview.obtainFrameView(), localc });
/*     */ 
/* 569 */     if (paramBoolean) {
/* 570 */       localIFrameView.obtainWebView().setOriginalUrl(str2);
/*     */     }
/* 572 */     if (paramJSONArray != null) {
/* 573 */       localc.r = paramJSONArray;
/* 574 */       localc.s = paramIWebview;
/*     */     }
/* 576 */     if ((paramJSONObject1 != null) && 
/* 577 */       (paramJSONObject1.has("render")))
/*     */     {
/* 579 */       localObject1 = paramJSONObject1.optString("render", "onscreen");
/*     */ 
/* 581 */       boolean bool2 = PdrUtil.isEquals((String)localObject1, "always");
/* 582 */       localIFrameView.setNeedRender(bool2);
/*     */     }
/*     */ 
/* 585 */     localc.a(localIFrameView, paramString2);
/*     */ 
/* 587 */     Object localObject1 = ((AdaFrameItem)localc.u).obtainFrameOptions();
/*     */     String str4;
/*     */     Object localObject2;
/* 588 */     if ((!TextUtils.isEmpty(((ViewOptions)localObject1).errorPage)) && (!URLUtil.isNetworkUrl(((ViewOptions)localObject1).errorPage)))
/*     */     {
/* 591 */       if (!"none".equals(((ViewOptions)localObject1).errorPage)) {
/* 592 */         str4 = paramIWebview.obtainApp().convert2AbsFullPath(paramIWebview.obtainFullUrl(), ((ViewOptions)localObject1).errorPage);
/* 593 */         if (!new File(str4).exists()) {
/* 594 */           localObject2 = paramIWebview.obtainApp().obtainConfigProperty("error");
/* 595 */           if (!"none".equals(localObject2))
/* 596 */             ((ViewOptions)localObject1).errorPage = paramIWebview.obtainApp().convert2WebviewFullPath(null, (String)localObject2);
/*     */           else
/* 598 */             ((ViewOptions)localObject1).errorPage = "none";
/*     */         }
/*     */         else {
/* 601 */           ((ViewOptions)localObject1).errorPage = paramIWebview.obtainApp().convert2WebviewFullPath(paramIWebview.obtainFullUrl(), ((ViewOptions)localObject1).errorPage);
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 607 */       str4 = paramIWebview.obtainApp().obtainConfigProperty("error");
/* 608 */       if (!"none".equals(str4))
/* 609 */         ((ViewOptions)localObject1).errorPage = paramIWebview.obtainApp().convert2WebviewFullPath(null, str4);
/*     */       else {
/* 611 */         ((ViewOptions)localObject1).errorPage = "none";
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 616 */     localObject1 = localc.u.obtainWebView();
/* 617 */     if (paramJSONObject1.has("plusrequire")) {
/* 618 */       ((IWebview)localObject1).setWebviewProperty("plusrequire", paramJSONObject1.optString("plusrequire"));
/*     */     }
/* 620 */     if (paramJSONObject1.has("geolocation")) {
/* 621 */       ((IWebview)localObject1).setWebviewProperty("geolocation", paramJSONObject1.optString("geolocation"));
/*     */     }
/* 623 */     if (paramJSONObject1.has("injection")) {
/* 624 */       str4 = String.valueOf(paramJSONObject1.optBoolean("injection"));
/* 625 */       ((IWebview)localObject1).setWebviewProperty("injection", str4);
/*     */     }
/* 627 */     if (paramJSONObject1.has("overrideresource")) {
/* 628 */       ((IWebview)localObject1).setOverrideResourceRequest(paramJSONObject1.optJSONArray("overrideresource"));
/*     */     }
/* 630 */     if (paramJSONObject1.has("overrideurl")) {
/* 631 */       ((IWebview)localObject1).setOverrideUrlLoadingData(paramJSONObject1.optJSONObject("overrideurl"));
/*     */     }
/*     */ 
/* 634 */     if ((BaseInfo.isWap2AppAppid(str3)) && (paramIApp.isStreamApp()) && (((IWebview)localObject1).obtainFrameView().getFrameType() == 4)) {
/* 635 */       if (!((IWebview)localObject1).getWebviewProperty("plusrequire").equals("none")) {
/* 636 */         ((IWebview)localObject1).appendPreloadJsFile(paramIApp.convert2LocalFullPath(null, "_www/__wap2app.js"));
/* 637 */         ((IWebview)localObject1).appendPreloadJsFile(paramIApp.convert2LocalFullPath(null, "_www/__wap2appconfig.js"));
/*     */       }
/* 639 */       str4 = paramIApp.convert2LocalFullPath(null, "_www/__wap2app.css");
/* 640 */       localObject2 = new File(str4);
/* 641 */       if (((File)localObject2).exists()) {
/* 642 */         ((IWebview)localObject1).setCssFile(str4, null);
/*     */       }
/*     */     }
/*     */ 
/* 646 */     if (i != 0) {
/* 647 */       bool1 = (!PdrUtil.isNetPath(str1)) && (parama.b(str3)) && (parama.d(str1));
/*     */ 
/* 649 */       Logger.d("willDownload=" + bool1 + ";" + str1);
/* 650 */       if (bool1)
/* 651 */         parama.a(localc, str1);
/*     */       else {
/* 653 */         localc.u.obtainWebView().loadUrl(str1);
/*     */       }
/*     */     }
/* 656 */     localc.a(paramIWebview.getContext(), parama, localIFrameView.obtainWebView(), paramString3, paramJSONObject1);
/*     */ 
/* 658 */     boolean bool1 = true;
/* 659 */     if (bool1)
/*     */     {
/* 662 */       localIFrameView.obtainMainView().setVisibility(4);
/*     */ 
/* 664 */       if (DeviceInfo.sDeviceSdkVer >= 11) {
/* 665 */         parama.a(localIFrameView);
/*     */       }
/*     */     }
/* 668 */     parama.c(localc);
/* 669 */     localc.a(paramJSONObject1, false);
/* 670 */     Logger.d("View_Visible_Path", str3 + " createNWindow webview_name=" + paramString2);
/*     */ 
/* 672 */     return localc;
/*     */   }
/*     */ 
/*     */   private void a(IWebview paramIWebview, IApp paramIApp, String paramString)
/*     */   {
/* 680 */     if ((BaseInfo.isBase(paramIWebview.getContext())) && (!TextUtils.isEmpty(paramString))) {
/* 681 */       String str1 = paramIWebview.obtainUrl();
/*     */ 
/* 683 */       if ((paramString.startsWith("http://")) || (str1.startsWith("http://")) || (paramString.startsWith("https://")) || (str1.startsWith("https://")))
/*     */       {
/* 687 */         return;
/*     */       }
/*     */ 
/* 690 */       str1 = WebLoadEvent.getOriginalUrl(str1);
/* 691 */       paramString = WebLoadEvent.getOriginalUrl(paramString);
/*     */ 
/* 693 */       String str2 = WebLoadEvent.getHBuilderPrintUrl(paramIApp.convert2RelPath(str1));
/*     */ 
/* 695 */       String str3 = WebLoadEvent.getHBuilderPrintUrl(paramIApp.convert2RelPath(paramString));
/*     */ 
/* 699 */       Log.i(".stream_json", String.format("{\"open\": {\"opener\":\"%s\",\"opened\":\"%s\"}}", new Object[] { c(str2), c(str3) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static b a(String paramString)
/*     */   {
/* 705 */     b localb = null;
/* 706 */     if (!PdrUtil.isEmpty(paramString)) {
/*     */       try {
/* 708 */         Object localObject = Class.forName((String)e.get(paramString.toLowerCase())).newInstance();
/*     */ 
/* 710 */         if ((localObject instanceof b))
/* 711 */           localb = (b)localObject;
/*     */       }
/*     */       catch (InstantiationException localInstantiationException) {
/* 714 */         localInstantiationException.printStackTrace();
/*     */       } catch (IllegalAccessException localIllegalAccessException) {
/* 716 */         localIllegalAccessException.printStackTrace();
/*     */       } catch (ClassNotFoundException localClassNotFoundException) {
/* 718 */         localClassNotFoundException.printStackTrace();
/*     */       }
/*     */     }
/* 721 */     return localb;
/*     */   }
/*     */ 
/*     */   private void a()
/*     */   {
/* 727 */     e = (HashMap)this.a.processEvent(IMgr.MgrType.FeatureMgr, 4, this.d);
/*     */   }
/*     */ 
/*     */   public void b(String paramString)
/*     */   {
/*     */     Object localObject;
/* 733 */     if (PdrUtil.isEmpty(paramString)) {
/* 734 */       localObject = this.b.values();
/*     */ 
/* 736 */       for (a locala : (Collection)localObject) {
/* 737 */         locala.a();
/*     */       }
/* 739 */       this.b.clear();
/*     */     } else {
/* 741 */       localObject = (a)this.b.get(paramString);
/* 742 */       if (localObject != null) {
/* 743 */         Logger.d("Main_Path", "UIWidgetMgr.dispose pAppid=" + paramString);
/*     */ 
/* 745 */         ((a)localObject).a();
/*     */       }
/* 747 */       this.b.remove(paramString);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String c(String paramString)
/*     */   {
/* 757 */     if (TextUtils.isEmpty(paramString)) {
/* 758 */       return "";
/*     */     }
/*     */ 
/* 761 */     if (paramString.startsWith("./")) {
/* 762 */       return paramString.substring("./".length());
/*     */     }
/*     */ 
/* 765 */     if (paramString.startsWith("../")) {
/* 766 */       return paramString.substring("../".length());
/*     */     }
/*     */ 
/* 769 */     if (paramString.startsWith(".../")) {
/* 770 */       return paramString.substring(".../".length());
/*     */     }
/* 772 */     return paramString;
/*     */   }
/*     */ 
/*     */   private ValueAnimator a(final View paramView, int paramInt1, int paramInt2, final String paramString1, final IWebview paramIWebview, final String paramString2, final c paramc)
/*     */   {
/* 779 */     ValueAnimator localValueAnimator = null;
/* 780 */     if ((paramView.getLayoutParams() instanceof AbsoluteLayout.LayoutParams))
/* 781 */       localValueAnimator = ValueAnimator.ofInt(new int[] { paramInt1, paramInt2 });
/* 782 */     else if ((paramView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
/* 783 */       localValueAnimator = ValueAnimator.ofFloat(new float[] { paramInt1, paramInt2 });
/*     */     }
/* 785 */     localValueAnimator.setDuration(200L);
/* 786 */     localValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
/*     */     {
/*     */       public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator) {
/* 789 */         if ((paramView.getLayoutParams() instanceof AbsoluteLayout.LayoutParams)) {
/* 790 */           AbsoluteLayout.LayoutParams localLayoutParams = (AbsoluteLayout.LayoutParams)paramView.getLayoutParams();
/* 791 */           localLayoutParams.height = paramView.getHeight();
/* 792 */           localLayoutParams.width = paramView.getWidth();
/* 793 */           localLayoutParams.x = ((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue();
/* 794 */           paramView.requestLayout();
/* 795 */         } else if ((paramView.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
/* 796 */           ViewHelper.setX(paramView, ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
/*     */         }
/*     */       }
/*     */     });
/* 800 */     localValueAnimator.addListener(new Animator.AnimatorListener() {
/*     */       public void onAnimationStart(Animator paramAnonymousAnimator) {
/*     */       }
/*     */ 
/*     */       public void onAnimationEnd(Animator paramAnonymousAnimator) {
/* 805 */         if ((null != paramIWebview) && (!TextUtils.isEmpty(paramString2))) {
/* 806 */           String str = paramc.l();
/* 807 */           if (TextUtils.isEmpty(str)) {
/* 808 */             str = "";
/*     */           }
/* 810 */           JSUtil.execCallback(paramIWebview, paramString2, String.format("{\"id\":\"%s\",\"target\":%s}", new Object[] { str, paramc.d() }), JSUtil.OK, true, true);
/*     */         }
/* 812 */         if (!TextUtils.isEmpty(paramString1))
/*     */         {
/* 816 */           if ("hide".equals(paramString1))
/* 817 */             paramc.a(paramc.k(), "hide", JSONUtil.createJSONArray("[null,null,null]"));
/* 818 */           else if ("close".equals(paramString1))
/* 819 */             paramc.a(paramc.k(), "close", JSONUtil.createJSONArray("[null,null,null]"));
/*     */         }
/*     */       }
/*     */ 
/*     */       public void onAnimationCancel(Animator paramAnonymousAnimator)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void onAnimationRepeat(Animator paramAnonymousAnimator)
/*     */       {
/*     */       }
/*     */     });
/* 828 */     return localValueAnimator;
/*     */   }
/*     */ 
/*     */   private static enum a
/*     */   {
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\ui.jar
 * Qualified Name:     io.dcloud.feature.ui.e
 * JD-Core Version:    0.6.2
 */