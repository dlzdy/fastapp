/*      */ package io.dcloud.feature.ui;
/*      */ 
/*      */ import android.graphics.Rect;
/*      */ import android.os.Build.VERSION;
/*      */ import android.text.TextUtils;
/*      */ import android.util.Log;
/*      */ import android.view.View;
/*      */ import android.view.ViewGroup.LayoutParams;
/*      */ import android.webkit.WebView;
/*      */ import android.widget.AbsoluteLayout.LayoutParams;
/*      */ import io.dcloud.common.DHInterface.AbsMgr;
/*      */ import io.dcloud.common.DHInterface.IApp;
/*      */ import io.dcloud.common.DHInterface.ICallBack;
/*      */ import io.dcloud.common.DHInterface.IContainerView;
/*      */ import io.dcloud.common.DHInterface.IEventCallback;
/*      */ import io.dcloud.common.DHInterface.IFrameView;
/*      */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*      */ import io.dcloud.common.DHInterface.INativeBitmap;
/*      */ import io.dcloud.common.DHInterface.INativeView;
/*      */ import io.dcloud.common.DHInterface.IWebview;
/*      */ import io.dcloud.common.adapter.ui.AdaContainerFrameItem;
/*      */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*      */ import io.dcloud.common.adapter.ui.AdaFrameItem.LayoutParamsUtil;
/*      */ import io.dcloud.common.adapter.ui.AdaFrameView;
/*      */ import io.dcloud.common.adapter.ui.AdaWebViewParent;
/*      */ import io.dcloud.common.adapter.ui.ReceiveJSValue;
/*      */ import io.dcloud.common.adapter.ui.ReceiveJSValue.ReceiveJSValueCallback;
/*      */ import io.dcloud.common.adapter.ui.WebLoadEvent;
/*      */ import io.dcloud.common.adapter.util.AnimOptions;
/*      */ import io.dcloud.common.adapter.util.Logger;
/*      */ import io.dcloud.common.adapter.util.MessageHandler;
/*      */ import io.dcloud.common.adapter.util.ViewOptions;
/*      */ import io.dcloud.common.adapter.util.ViewRect;
/*      */ import io.dcloud.common.util.BaseInfo;
/*      */ import io.dcloud.common.util.JSONUtil;
/*      */ import io.dcloud.common.util.JSUtil;
/*      */ import io.dcloud.common.util.PdrUtil;
/*      */ import io.dcloud.common.util.TestUtil;
/*      */ import io.dcloud.common.util.ThreadPool;
/*      */ import io.dcloud.nineoldandroids.view.ViewHelper;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ 
/*      */ public class c extends b
/*      */   implements IEventCallback
/*      */ {
/*   57 */   long q = System.currentTimeMillis();
/*   58 */   JSONArray r = null;
/*   59 */   IWebview s = null;
/*   60 */   JSONObject t = null;
/*      */   IFrameView u;
/*   65 */   String v = null;
/*      */ 
/*   71 */   int w = -1;
/*   72 */   Object x = null;
/*      */ 
/*   74 */   boolean y = false;
/*      */ 
/*   76 */   boolean z = false;
/*   77 */   int A = 0;
/*      */ 
/*   80 */   boolean B = false;
/*      */ 
/*   82 */   boolean C = false;
/*      */ 
/*   84 */   boolean D = true;
/*      */ 
/*   86 */   boolean E = false;
/*      */ 
/*   88 */   boolean F = false;
/*      */ 
/*   90 */   boolean G = false;
/*      */ 
/*   92 */   boolean H = false;
/*      */ 
/*   94 */   protected ArrayList<b> I = null;
/*      */ 
/*   96 */   String J = null;
/*      */ 
/*   98 */   String K = null;
/*      */ 
/*  100 */   IWebview L = null;
/*      */ 
/*  103 */   String M = null;
/*      */ 
/*  105 */   IWebview N = null;
/*      */ 
/*  108 */   String O = null;
/*      */ 
/*  110 */   IWebview P = null;
/*      */ 
/*  112 */   String Q = null;
/*      */ 
/*  114 */   private boolean T = true;
/*      */ 
/*  116 */   c R = null;
/*      */ 
/*  118 */   private ArrayList<c> U = null;
/*      */ 
/*  122 */   private boolean V = false;
/*  123 */   private String W = "auto";
/*  124 */   private int X = 150;
/*      */ 
/*  490 */   private static final HashMap<String, String> Y = new HashMap();
/*      */ 
/* 1557 */   Runnable S = null;
/*      */ 
/*      */   public void a(boolean paramBoolean)
/*      */   {
/*  131 */     this.V = paramBoolean;
/*      */   }
/*      */ 
/*      */   c(a parama, String paramString1, String paramString2, String paramString3, JSONObject paramJSONObject) {
/*  135 */     this(parama, null, paramString1, paramString2, paramString3, paramJSONObject);
/*      */   }
/*      */   private c(a parama, IFrameView paramIFrameView, String paramString1, String paramString2, String paramString3, JSONObject paramJSONObject) {
/*  138 */     super("NWindow");
/*  139 */     this.c = parama;
/*  140 */     this.v = paramString1;
/*  141 */     this.e = paramString3;
/*  142 */     this.g = paramJSONObject;
/*  143 */     a(paramIFrameView, paramString2);
/*      */   }
/*      */ 
/*      */   public void a(IFrameView paramIFrameView, String paramString) {
/*  147 */     if (paramIFrameView != null) {
/*  148 */       this.u = paramIFrameView;
/*  149 */       IWebview localIWebview = paramIFrameView.obtainWebView();
/*  150 */       if (localIWebview != null) {
/*  151 */         localIWebview.initWebviewUUID(this.e);
/*  152 */         localIWebview.setFrameId(paramString);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   protected b b(String paramString)
/*      */   {
/*  166 */     b localb = null;
/*  167 */     if ((this.I != null) && (!this.I.isEmpty())) {
/*  168 */       int i = this.I.size();
/*  169 */       for (int j = i - 1; j >= 0; j--) {
/*  170 */         localb = (b)this.I.get(j);
/*  171 */         if (PdrUtil.isEquals(paramString, localb.f)) {
/*      */           break;
/*      */         }
/*      */       }
/*      */     }
/*  176 */     return localb;
/*      */   }
/*      */ 
/*      */   public boolean a(b paramb) {
/*  180 */     if (this.I == null) {
/*  181 */       return false;
/*      */     }
/*  183 */     return this.I.contains(paramb);
/*      */   }
/*      */ 
/*      */   public boolean h()
/*      */   {
/*  192 */     return !this.u.isWebviewCovered();
/*      */   }
/*      */ 
/*      */   protected void a(c paramc)
/*      */   {
/*  199 */     if (this.U == null) this.U = new ArrayList();
/*  200 */     this.U.add(paramc);
/*  201 */     paramc.R = this;
/*      */   }
/*      */ 
/*      */   protected void b(b paramb)
/*      */   {
/*  210 */     if ((this.I == null) || (!this.I.contains(paramb))) {
/*  211 */       return;
/*      */     }
/*  213 */     this.I.remove(paramb);
/*  214 */     paramb.a = null;
/*  215 */     int i = paramb.b();
/*      */ 
/*  217 */     boolean bool = paramb instanceof c;
/*      */ 
/*  219 */     if (i == j) {
/*  220 */       IWebview localIWebview = this.u.obtainWebView();
/*  221 */       localIWebview.removeFrameItem(paramb.e());
/*  222 */     } else if (i == k) {
/*  223 */       this.u.obtainWebviewParent().removeFrameItem(paramb.e());
/*  224 */     } else if (i == l) {
/*  225 */       this.u.removeFrameItem(paramb.e());
/*  226 */       if (bool) {
/*  227 */         this.u.obtainWebviewParent().obtainFrameOptions().delRelViewRect(paramb.e().obtainFrameOptions());
/*      */       }
/*  229 */       e().resize();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void a(IWebview paramIWebview, String paramString)
/*      */   {
/*  238 */     this.c.c.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[] { paramIWebview, "nativeobj", "removeNativeView", { this.u, paramString } });
/*      */   }
/*      */   protected void b(IWebview paramIWebview, String paramString) {
/*  241 */     this.c.c.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[] { paramIWebview, "nativeobj", "addNativeView", { this.u, paramString } });
/*      */   }
/*      */ 
/*      */   protected void c(b paramb)
/*      */   {
/*  251 */     if (this.I == null) {
/*  252 */       this.I = new ArrayList(2);
/*      */     }
/*  254 */     int i = 0;
/*  255 */     AdaFrameItem localAdaFrameItem = paramb.e();
/*  256 */     int j = paramb.b();
/*  257 */     if (this.I.contains(paramb))
/*  258 */       return;
/*  259 */     if ((paramb instanceof c)) {
/*  260 */       localObject1 = (c)paramb;
/*      */ 
/*  263 */       a(paramb, (c)localObject1);
/*      */ 
/*  265 */       if ((this.c.g((c)localObject1)) && (this.B)) {
/*  266 */         ((c)localObject1).w = 4;
/*  267 */         this.c.e((c)localObject1);
/*      */       }
/*      */ 
/*  270 */       i = 1;
/*      */ 
/*  274 */       if (paramb.e().obtainMainView().getVisibility() == 4) {
/*  275 */         ((c)localObject1).B = true;
/*  276 */         ((c)localObject1).u.setVisible(true, true);
/*  277 */         Logger.d("View_Visible_Path", "NWindow.appendView childView set visible true");
/*      */       }
/*      */ 
/*  280 */       this.c.b(((c)localObject1).u);
/*  281 */       Logger.d("View_Visible_Path", "append " + ((c)localObject1).u);
/*      */ 
/*  283 */       ((c)localObject1).H = true;
/*      */ 
/*  285 */       ((c)localObject1).E = true;
/*      */     }
/*  291 */     else if ((paramb instanceof d)) {
/*  292 */       localObject1 = (d)paramb;
/*  293 */       ((d)localObject1).h();
/*  294 */       ((d)localObject1).a(true);
/*  295 */       i = 1;
/*      */     }
/*  297 */     Object localObject1 = paramb.g;
/*  298 */     ViewOptions localViewOptions1 = ((AdaFrameItem)this.u).obtainFrameOptions();
/*  299 */     AdaWebViewParent localAdaWebViewParent = this.u.obtainWebviewParent();
/*  300 */     ViewOptions localViewOptions2 = localAdaWebViewParent.obtainFrameOptions();
/*      */ 
/*  302 */     ViewOptions localViewOptions3 = localAdaFrameItem.obtainFrameOptions();
/*  303 */     Object localObject2 = null;
/*  304 */     if (j == l) {
/*  305 */       localObject2 = this.u;
/*  306 */       localViewOptions2.setParentViewRect(localViewOptions1);
/*  307 */       localViewOptions2.updateViewData(localViewOptions1);
/*      */     } else {
/*  309 */       if (j == k) {
/*  310 */         localObject2 = this.u;
/*  311 */       } else if (j == j) {
/*  312 */         localObject3 = this.u.obtainWebView();
/*  313 */         localObject2 = localObject3;
/*      */       }
/*      */ 
/*  317 */       Object localObject3 = ((AdaFrameView)this.u).obtainFrameOptions();
/*  318 */       localViewOptions3.setParentViewRect((ViewRect)localObject3);
/*      */     }
/*  320 */     localViewOptions3.updateViewData((JSONObject)localObject1, localViewOptions1.width, localViewOptions1.height, localViewOptions1.mWebviewScale);
/*  321 */     if (j == l) {
/*  322 */       localViewOptions3.setParentViewRect(((AdaFrameItem)localObject2).obtainFrameOptions());
/*  323 */       ViewRect.layoutDockViewRect(localViewOptions2, localViewOptions3);
/*      */ 
/*  325 */       localAdaWebViewParent.mNeedOrientationUpdate = true;
/*      */ 
/*  328 */       localViewOptions2.putRelViewRect(localViewOptions3);
/*      */ 
/*  330 */       k = localViewOptions2.left;
/*  331 */       m = localViewOptions2.top;
/*  332 */       n = localViewOptions2.width;
/*  333 */       i1 = localViewOptions2.height;
/*  334 */       Logger.d("View_Visible_Path", "NWindow.appendView ---> _webview left=" + k + ";top=" + m + ";width=" + n + ";height=" + i1);
/*      */ 
/*  336 */       localAdaWebViewParent.obtainMainView().setLayoutParams(AdaFrameItem.LayoutParamsUtil.createLayoutParams(k, m, n, i1));
/*      */     }
/*      */ 
/*  339 */     int k = localViewOptions3.width;
/*  340 */     int m = localViewOptions3.height;
/*  341 */     int n = localViewOptions3.left;
/*  342 */     int i1 = localViewOptions3.top;
/*      */ 
/*  344 */     paramb.a(localViewOptions2.left, localViewOptions2.top, localViewOptions2.width, localViewOptions2.height, k, m);
/*  345 */     Logger.d("View_Visible_Path", "NWindow.appendView childView=" + paramb.e());
/*      */ 
/*  348 */     localAdaFrameItem.setParentFrameItem((AdaContainerFrameItem)localObject2);
/*  349 */     if (i != 0)
/*      */     {
/*  351 */       a((IContainerView)localObject2, localAdaFrameItem, localAdaFrameItem.obtainMainView().getLayoutParams(), n, i1, k, m);
/*      */     } else {
/*  353 */       ViewGroup.LayoutParams localLayoutParams = AdaFrameItem.LayoutParamsUtil.createLayoutParams(n, i1, k, m);
/*  354 */       ((IContainerView)localObject2).addFrameItem(paramb.e(), localLayoutParams);
/*      */     }
/*  356 */     this.I.add(paramb);
/*  357 */     paramb.a = this;
/*      */   }
/*      */ 
/*      */   private void a(b paramb, c paramc)
/*      */   {
/*  365 */     if (BaseInfo.isBase(paramb.c()))
/*      */     {
/*  373 */       if ((this.v.startsWith("http://")) || (this.v.startsWith("https://")) || (paramc.v.startsWith("http://")) || (paramc.v.startsWith("https://")))
/*      */       {
/*  378 */         return;
/*      */       }
/*      */ 
/*  381 */       if ((!TextUtils.isEmpty(this.v)) && (!TextUtils.isEmpty(paramc.v)))
/*      */       {
/*  383 */         String str = paramc.k().obtainApp().convert2RelPath(k().obtainUrl());
/*      */ 
/*  388 */         Log.i(".stream_json", String.format("{\"filiation\": {\"parent\":\"%s\",\"child\":\"%s\"}}", new Object[] { e.c(WebLoadEvent.getHBuilderPrintUrl(str)), e.c(WebLoadEvent.getHBuilderPrintUrl(paramc.k().obtainUrl())) }));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void a(IContainerView paramIContainerView, AdaFrameItem paramAdaFrameItem, ViewGroup.LayoutParams paramLayoutParams, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  403 */     boolean bool = paramLayoutParams instanceof AbsoluteLayout.LayoutParams;
/*      */ 
/*  406 */     ViewOptions localViewOptions = paramAdaFrameItem.obtainFrameOptions();
/*  407 */     localViewOptions.left = paramInt1;
/*  408 */     localViewOptions.top = paramInt2;
/*  409 */     localViewOptions.width = paramInt3;
/*  410 */     localViewOptions.height = paramInt4;
/*  411 */     localViewOptions.commitUpdate2JSONObject();
/*  412 */     ((AdaFrameView)paramAdaFrameItem).isChildOfFrameView = true;
/*      */ 
/*  414 */     View localView = paramAdaFrameItem.obtainMainView();
/*      */ 
/*  416 */     if (Build.VERSION.SDK_INT > 11) {
/*  417 */       localView.setTop(0);
/*  418 */       localView.setLeft(0);
/*  419 */       localView.setBottom(0);
/*  420 */       localView.setRight(0);
/*      */     } else {
/*  422 */       localView.layout(0, 0, paramInt3, paramInt4);
/*      */     }
/*      */ 
/*  425 */     ViewHelper.setX(localView, 0.0F);
/*  426 */     ViewHelper.setY(localView, 0.0F);
/*  427 */     ViewGroup.LayoutParams localLayoutParams = AdaFrameItem.LayoutParamsUtil.createLayoutParams(paramInt1, paramInt2, paramInt3, paramInt4);
/*  428 */     paramIContainerView.addFrameItem(paramAdaFrameItem, localLayoutParams);
/*  429 */     Logger.d("View_Visible_Path", "appendNWindow Y=" + ViewHelper.getY(localView));
/*      */   }
/*      */ 
/*      */   protected void i()
/*      */   {
/*      */   }
/*      */ 
/*      */   public Object onCallBack(String paramString, Object paramObject)
/*      */   {
/*  502 */     Logger.d("yl", new Object[] { "NWindow.onCallBack pEventType=" + paramString, paramObject });
/*  503 */     if ((PdrUtil.isEquals(paramString, "dragBounce")) || (PdrUtil.isEquals(paramString, "slideBounce"))) {
/*  504 */       a(paramString, PdrUtil.isEmpty(paramObject) ? null : String.valueOf(paramObject), false);
/*  505 */     } else if (PdrUtil.isEquals("show_animation_end", paramString)) {
/*  506 */       if ((!PdrUtil.isEmpty(this.K)) && (this.L != null)) {
/*  507 */         JSUtil.execCallback(this.L, this.K, "", JSUtil.OK, false, false);
/*      */       }
/*  509 */       b("show", PdrUtil.isEmpty(paramObject) ? null : String.valueOf(paramObject));
/*  510 */     } else if (PdrUtil.isEquals("titleUpdate", paramString)) {
/*  511 */       a(paramString, String.format("{title:%s}", new Object[] { paramObject == null ? "''" : JSONObject.quote(paramObject.toString()) }), false);
/*  512 */       m();
/*  513 */     } else if (PdrUtil.isEquals("listenResourceLoading", paramString)) {
/*  514 */       JSUtil.execCallback(this.P, this.O, (String)paramObject, JSUtil.OK, true, true);
/*  515 */     } else if (PdrUtil.isEquals("overrideUrlLoading", paramString)) {
/*  516 */       JSUtil.execCallback(this.N, this.M, (String)paramObject, JSUtil.OK, true, true);
/*  517 */     } else if (PdrUtil.isEquals("hide_loading", paramString)) {
/*  518 */       this.c.f(this);
/*  519 */     } else if (PdrUtil.isEquals("show_loading", paramString)) {
/*  520 */       if (this.E)
/*  521 */         this.c.e(this);
/*      */     }
/*  523 */     else if (PdrUtil.isEquals("onresize", paramString))
/*      */     {
/*  525 */       i();
/*  526 */     } else if (PdrUtil.isEquals("slide_webview_hide", paramString))
/*      */     {
/*  528 */       n();
/*  529 */     } else if (PdrUtil.isEquals("slide_webview_close", paramString))
/*      */     {
/*  531 */       o();
/*      */     }
/*      */     else
/*      */     {
/*      */       Object localObject1;
/*  532 */       if (PdrUtil.isEquals("popGesture", paramString))
/*      */       {
/*  534 */         localObject1 = (Object[])paramObject;
/*  535 */         String str1 = (String)localObject1[0];
/*  536 */         Object localObject2 = localObject1[1];
/*  537 */         IFrameView localIFrameView = (IFrameView)localObject1[2];
/*  538 */         c localc = this.c.c(localIFrameView);
/*  539 */         String str2 = "{type:'%s', result:%s, private_args:{uuid:'%s',id:'%s',extras:'%s'}}";
/*  540 */         a(paramString, String.format(str2, new Object[] { str1, localObject2, localc.e, localc.f, localc.t }), false);
/*  541 */       } else if (PdrUtil.isEquals("touchstart", paramString)) {
/*  542 */         if ((this.i != null) && (this.i.containsKey("touchstart")))
/*  543 */           b(paramString, PdrUtil.isEmpty(paramObject) ? null : String.valueOf(paramObject));
/*      */       }
/*      */       else {
/*  546 */         localObject1 = (String)Y.get(paramString);
/*  547 */         if (!PdrUtil.isEmpty(localObject1)) {
/*  548 */           a((String)localObject1, paramObject, this.c.b, this);
/*      */         }
/*  550 */         if (PdrUtil.isEquals(paramString, "loaded")) {
/*  551 */           this.w = -1;
/*  552 */           if (this.r != null) {
/*  553 */             a(this.s, this.r, this, this.u.obtainApp().obtainAppId());
/*      */           }
/*  555 */           TestUtil.print(TestUtil.CREATE_WEBVIEW, this.v + " 从加载完成分发loaded事件到开始分发事件 " + paramString);
/*  556 */           Logger.d("Main_Path", "EVENTS_LOADED mUrl=" + this.v);
/*  557 */         } else if (PdrUtil.isEquals(paramString, "window_close")) {
/*  558 */           f();
/*      */         }
/*  560 */         b(paramString, PdrUtil.isEmpty(paramObject) ? null : String.valueOf(paramObject));
/*      */       }
/*      */     }
/*  562 */     if (PdrUtil.isEquals(paramString, "close")) {
/*  563 */       this.c.d(this);
/*      */     }
/*  565 */     return null;
/*      */   }
/*      */ 
/*      */   public static void a(String paramString, Object paramObject, ArrayList<c> paramArrayList, c paramc)
/*      */   {
/*  570 */     for (c localc : paramArrayList)
/*      */     {
/*  572 */       JSUtil.broadcastWebviewEvent(localc.k(), paramc.e, paramString, JSONUtil.toJSONableString(String.valueOf(paramObject)));
/*      */     }
/*  574 */     if (!paramArrayList.contains(paramc))
/*  575 */       JSUtil.broadcastWebviewEvent(paramc.k(), paramc.e, paramString, JSONUtil.toJSONableString(String.valueOf(paramObject)));
/*      */   }
/*      */ 
/*      */   protected String j()
/*      */   {
/*  580 */     ViewOptions localViewOptions = ((AdaFrameItem)this.u).obtainFrameOptions();
/*  581 */     String str = "{top:%d,left:%d,width:%d,height:%d}";
/*  582 */     str = String.format(str, new Object[] { Integer.valueOf((int)(localViewOptions.top / localViewOptions.mWebviewScale)), Integer.valueOf((int)(localViewOptions.left / localViewOptions.mWebviewScale)), Integer.valueOf((int)(localViewOptions.width / localViewOptions.mWebviewScale)), Integer.valueOf((int)(localViewOptions.height / localViewOptions.mWebviewScale)) });
/*  583 */     return str;
/*      */   }
/*      */ 
/*      */   public AdaFrameItem e() {
/*  587 */     return (AdaFrameItem)this.u;
/*      */   }
/*      */ 
/*      */   public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
/*      */   }
/*      */ 
/*      */   public String a(final IWebview paramIWebview, String paramString, JSONArray paramJSONArray) {
/*  594 */     String str1 = null;
/*      */     try {
/*  596 */       c localc1 = this;
/*  597 */       IApp localIApp = paramIWebview.obtainFrameView().obtainApp();
/*  598 */       String str2 = localIApp.obtainAppId();
/*      */       Object localObject1;
/*      */       Object localObject3;
/*  599 */       if ("setPullToRefresh".equals(paramString)) {
/*  600 */         Logger.d("View_Visible_Path", "refreshLoadingViewsSize setPullToRefresh args=" + paramJSONArray);
/*  601 */         localObject1 = JSONUtil.getJSONObject(paramJSONArray, 0);
/*  602 */         localObject3 = JSONUtil.getString(paramJSONArray, 1);
/*  603 */         if (!PdrUtil.isEmpty(localObject3)) {
/*  604 */           this.Q = ((String)localObject3);
/*      */         }
/*  606 */         this.u.obtainWebView().setWebViewEvent("pull_down_refresh", localObject1);
/*  607 */       } else if ("beginPullToRefresh".equals(paramString)) {
/*  608 */         this.u.obtainWebView().setWebViewEvent("pull_down_refresh_begin", null);
/*  609 */       } else if ("endPullToRefresh".equals(paramString)) {
/*  610 */         this.u.obtainWebView().endWebViewEvent("pull_down_refresh");
/*  611 */       } else if ("setBounce".equals(paramString)) {
/*  612 */         localObject1 = JSONUtil.getJSONObject(paramJSONArray, 0);
/*  613 */         this.u.obtainWebView().setWebViewEvent("bounce_register", localObject1);
/*  614 */       } else if ("resetBounce".equals(paramString)) {
/*  615 */         this.u.obtainWebView().endWebViewEvent("bounce_register");
/*  616 */       } else if ("setBlockNetworkImage".equals(paramString)) {
/*  617 */         localObject1 = JSONUtil.getString(paramJSONArray, 0);
/*  618 */         localc1.k().setWebviewProperty("blockNetworkImage", (String)localObject1);
/*  619 */       } else if ("getOption".equals(paramString)) {
/*  620 */         localObject1 = ((AdaFrameItem)localc1.u).obtainFrameOptions();
/*  621 */         if (((ViewOptions)localObject1).hasBackground()) {
/*  622 */           localObject1 = localc1.u.obtainWebviewParent().obtainFrameOptions();
/*      */         }
/*  624 */         str1 = JSUtil.wrapJsVar(((ViewOptions)localObject1).mJsonViewOption.toString(), false);
/*  625 */       } else if (("setOption".equals(paramString)) || ("setStyle".equals(paramString))) {
/*  626 */         b(paramIWebview, paramJSONArray, localc1);
/*  627 */       } else if ("getMetrics".equals(paramString)) {
/*  628 */         localObject1 = JSONUtil.getString(paramJSONArray, 0);
/*  629 */         localObject3 = j();
/*  630 */         JSUtil.execCallback(paramIWebview, (String)localObject1, (String)localObject3, JSUtil.OK, true, false);
/*      */       }
/*  632 */       else if ("getUrl".equals(paramString))
/*      */       {
/*  634 */         localObject1 = localc1.u.obtainWebView().obtainFullUrl();
/*  635 */         str1 = JSUtil.wrapJsVar((String)localObject1, true);
/*  636 */       } else if ("setPreloadJsFile".equals(paramString)) {
/*  637 */         localObject1 = JSONUtil.getString(paramJSONArray, 0);
/*  638 */         if (!PdrUtil.isEmpty(localObject1)) {
/*  639 */           localObject3 = localc1.u.obtainApp().convert2AbsFullPath(paramIWebview.obtainFullUrl(), (String)localObject1);
/*  640 */           localc1.u.obtainWebView().setPreloadJsFile((String)localObject3);
/*      */         }
/*  642 */       } else if ("appendPreloadJsFile".equals(paramString)) {
/*  643 */         localObject1 = JSONUtil.getString(paramJSONArray, 0);
/*  644 */         localObject3 = localc1.u.obtainApp().convert2AbsFullPath(paramIWebview.obtainFullUrl(), (String)localObject1);
/*  645 */         localc1.u.obtainWebView().appendPreloadJsFile((String)localObject3);
/*  646 */       } else if ("listenResourceLoading".equals(paramString)) {
/*  647 */         localObject1 = paramJSONArray.optJSONObject(0);
/*  648 */         this.O = paramJSONArray.optString(1);
/*  649 */         this.P = paramIWebview;
/*  650 */         localc1.u.obtainWebView().setListenResourceLoading((JSONObject)localObject1);
/*  651 */       } else if ("overrideResourceRequest".equals(paramString)) {
/*  652 */         localObject1 = paramJSONArray.optJSONArray(0);
/*  653 */         localc1.u.obtainWebView().setOverrideResourceRequest((JSONArray)localObject1);
/*  654 */       } else if ("overrideUrlLoading".equals(paramString)) {
/*  655 */         localObject1 = paramJSONArray.optJSONObject(0);
/*  656 */         this.M = paramJSONArray.optString(1);
/*  657 */         this.N = paramIWebview;
/*  658 */         localc1.u.obtainWebView().setOverrideUrlLoadingData((JSONObject)localObject1);
/*  659 */       } else if ("hide".equals(paramString)) {
/*  660 */         a(paramJSONArray, localc1);
/*  661 */       } else if ("show".equals(paramString)) {
/*  662 */         a(paramIWebview, paramJSONArray, localc1, str2);
/*  663 */       } else if ("close".equals(paramString)) {
/*  664 */         b(paramJSONArray, localc1);
/*      */       }
/*      */       else
/*      */       {
/*      */         Object localObject4;
/*  665 */         if ("evalJS".equals(paramString)) {
/*  666 */           localObject1 = JSONUtil.getString(paramJSONArray, 0);
/*  667 */           localObject3 = localc1.u.obtainWebView();
/*      */ 
/*  669 */           localObject4 = JSONUtil.getString(paramJSONArray, 1);
/*  670 */           if (!PdrUtil.isEmpty(localObject4)) {
/*  671 */             localObject1 = ReceiveJSValue.registerCallback((String)localObject1, new ReceiveJSValue.ReceiveJSValueCallback()
/*      */             {
/*      */               public String callback(JSONArray paramAnonymousJSONArray) {
/*  674 */                 String str = JSONUtil.getString(paramAnonymousJSONArray, 0);
/*  675 */                 Object localObject = null;
/*      */                 try {
/*  677 */                   localObject = paramAnonymousJSONArray.get(1);
/*      */                 } catch (JSONException localJSONException) {
/*      */                 }
/*  680 */                 if (((localObject instanceof String)) || ("string".equals(str)))
/*  681 */                   JSUtil.execCallback(paramIWebview, this.b, String.valueOf(localObject), JSUtil.OK, false, false);
/*  682 */                 else if ((localObject instanceof JSONArray))
/*  683 */                   JSUtil.execCallback(paramIWebview, this.b, localObject.toString(), JSUtil.OK, true, false);
/*  684 */                 else if (((localObject instanceof JSONObject)) || ("object".equals(str)))
/*  685 */                   JSUtil.execCallback(paramIWebview, this.b, localObject.toString(), JSUtil.OK, true, false);
/*  686 */                 else if ("undefined".equals(str))
/*  687 */                   JSUtil.execCallback(paramIWebview, this.b, "undefined", JSUtil.OK, true, false);
/*      */                 else {
/*  689 */                   JSUtil.execCallback(paramIWebview, this.b, localObject.toString(), JSUtil.OK, true, false);
/*      */                 }
/*  691 */                 return null;
/*      */               }
/*      */             });
/*      */           }
/*  695 */           ((IWebview)localObject3).evalJS((String)localObject1);
/*  696 */         } else if ("back".equals(paramString)) {
/*  697 */           localObject1 = localc1.u.obtainWebView();
/*  698 */           ((IWebview)localObject1).stopLoading();
/*  699 */           ((IWebview)localObject1).goBackOrForward(-1);
/*  700 */         } else if ("forward".equals(paramString)) {
/*  701 */           localObject1 = localc1.u.obtainWebView();
/*  702 */           ((IWebview)localObject1).stopLoading();
/*  703 */           ((IWebview)localObject1).goBackOrForward(1);
/*  704 */         } else if ("canBack".equals(paramString)) {
/*  705 */           localObject1 = JSONUtil.getString(paramJSONArray, 0);
/*  706 */           localObject3 = localc1.u.obtainWebView();
/*  707 */           JSUtil.execCallback(paramIWebview, (String)localObject1, String.valueOf(((IWebview)localObject3).canGoBack()), JSUtil.OK, true, false);
/*  708 */         } else if ("canForward".equals(paramString)) {
/*  709 */           localObject1 = JSONUtil.getString(paramJSONArray, 0);
/*  710 */           localObject3 = localc1.u.obtainWebView();
/*  711 */           JSUtil.execCallback(paramIWebview, (String)localObject1, String.valueOf(((IWebview)localObject3).canGoForward()), JSUtil.OK, true, false);
/*  712 */         } else if ("clear".equals(paramString)) {
/*  713 */           localObject1 = localc1.u.obtainWebView();
/*  714 */           ((IWebview)localObject1).clearHistory();
/*  715 */         } else if ("load".equals(paramString)) {
/*  716 */           localObject1 = paramIWebview.obtainUrl();
/*  717 */           localObject3 = JSONUtil.getString(paramJSONArray, 0);
/*  718 */           localObject4 = paramIWebview.obtainFrameView().obtainApp().convert2WebviewFullPath(paramIWebview.obtainFullUrl(), (String)localObject3);
/*  719 */           Logger.d("NWindow.load " + (String)localObject4);
/*  720 */           if ((!PdrUtil.isNetPath((String)localObject3)) && (this.c.b(str2)) && (this.c.d((String)localObject4))) {
/*  721 */             if (!this.c.a(this, (String)localObject4))
/*  722 */               this.c.e(this);
/*      */           }
/*      */           else {
/*  725 */             localc1.u.obtainWebView().setOriginalUrl((String)localObject3);
/*  726 */             localc1.u.obtainWebView().reload((String)localObject4);
/*      */           }
/*      */ 
/*  729 */           a(localc1, (String)localObject1);
/*  730 */         } else if ("stop".equals(paramString)) {
/*  731 */           localc1.u.obtainWebView().stopLoading();
/*  732 */         } else if ("reload".equals(paramString)) {
/*  733 */           boolean bool1 = PdrUtil.parseBoolean(JSONUtil.getString(paramJSONArray, 0), true, false);
/*  734 */           a(localc1, bool1);
/*      */         }
/*      */         else
/*      */         {
/*      */           JSONArray localJSONArray;
/*  735 */           if ("addEventListener".equals(paramString)) {
/*  736 */             localJSONArray = paramJSONArray;
/*  737 */             localObject3 = localJSONArray.getString(0);
/*  738 */             localObject4 = localJSONArray.getString(1);
/*  739 */             localc1.a((String)localObject4, (String)localObject3, (String)localc1.b.get(paramIWebview));
/*  740 */             if ((!localc1.u.obtainWebView().unReceiveTitle()) && ("titleUpdate".equals(localObject3)))
/*  741 */               onCallBack("titleUpdate", localc1.u.obtainWebView().obtainWebview().getTitle());
/*      */           }
/*  743 */           else if ("removeEventListener".equals(paramString)) {
/*  744 */             localJSONArray = paramJSONArray;
/*  745 */             localObject3 = localJSONArray.getString(0);
/*  746 */             localObject4 = localJSONArray.getString(1);
/*  747 */             localc1.a((String)localObject4, (String)localObject3);
/*  748 */           } else if ("isVisible".equals(paramString)) {
/*  749 */             str1 = JSUtil.wrapJsVar(String.valueOf(localc1.B), false);
/*      */           }
/*      */           else
/*      */           {
/*      */             boolean bool2;
/*  750 */             if ("setVisible".equals(paramString)) {
/*  751 */               bool2 = paramJSONArray.getBoolean(0);
/*  752 */               localc1.B = bool2;
/*  753 */               localc1.u.setVisible(bool2, true);
/*  754 */             } else if ("setContentVisible".equals(paramString)) {
/*  755 */               bool2 = paramJSONArray.getBoolean(0);
/*  756 */               localc1.D = bool2;
/*  757 */               ((AdaFrameItem)localc1.u.obtainWebView()).setVisibility(bool2 ? AdaFrameItem.VISIBLE : AdaFrameItem.GONE);
/*  758 */               localc1.u.obtainWebviewParent().setBgcolor(-1);
/*      */             }
/*      */             else
/*      */             {
/*      */               Object localObject2;
/*  759 */               if ("findViewById".equals(paramString)) {
/*  760 */                 localObject2 = JSONUtil.getString(paramJSONArray, 0);
/*  761 */                 str1 = b((String)localObject2).d();
/*  762 */               } else if ("getTitle".equals(paramString)) {
/*  763 */                 str1 = JSUtil.wrapJsVar(this.u.obtainWebView().obtainPageTitle(), true);
/*  764 */               } else if ("opener".equals(paramString)) {
/*  765 */                 if (this.R != null)
/*  766 */                   str1 = this.R.d();
/*      */                 else
/*  768 */                   str1 = JSUtil.wrapJsVar(String.format("{'uuid':%s,'id':%s}", new Object[] { "undefined", "undefined" }), false);
/*      */               }
/*  770 */               else if ("opened".equals(paramString)) {
/*  771 */                 str1 = a(this.U);
/*  772 */               } else if ("removeFromParent".equals(paramString)) {
/*  773 */                 localObject2 = this.a;
/*  774 */                 if ((localObject2 != null) && 
/*  775 */                   (((c)localObject2).a(localc1))) {
/*  776 */                   ((c)localObject2).b(localc1);
/*      */                 }
/*      */               }
/*  779 */               else if ("parent".equals(paramString)) {
/*  780 */                 if (this.a != null)
/*  781 */                   str1 = this.a.d();
/*      */                 else
/*  783 */                   str1 = JSUtil.wrapJsVar(String.format("{'uuid':%s,'id':%s}", new Object[] { "undefined", "undefined" }), false);
/*      */               }
/*  785 */               else if ("children".equals(paramString)) {
/*  786 */                 str1 = a(this.I);
/*  787 */               } else if ("loadData".equals(paramString)) {
/*  788 */                 localObject2 = JSONUtil.getString(paramJSONArray, 0);
/*  789 */                 localObject3 = PdrUtil.getNonString(JSONUtil.getString(paramJSONArray, 2), "text/html");
/*  790 */                 localObject4 = PdrUtil.getNonString(JSONUtil.getString(paramJSONArray, 3), "utf-8");
/*  791 */                 localc1.u.obtainWebView().loadContentData((String)localObject2, (String)localObject3, (String)localObject4);
/*  792 */               } else if ("removeNativeView".equals(paramString)) {
/*  793 */                 localObject2 = JSONUtil.getString(paramJSONArray, 1);
/*  794 */                 a(paramIWebview, (String)localObject2);
/*  795 */               } else if ("remove".equals(paramString)) {
/*  796 */                 localObject2 = JSONUtil.getString(paramJSONArray, 0);
/*  797 */                 localObject3 = this.c.a((String)localObject2);
/*  798 */                 if (localObject3 == null) {
/*  799 */                   localObject3 = this.c.a((String)localObject2, (String)localObject2, null);
/*      */                 }
/*  801 */                 if (a((b)localObject3))
/*  802 */                   localc1.b((b)localObject3);
/*      */               }
/*  804 */               else if ("append".equals(paramString)) {
/*  805 */                 localObject2 = JSONUtil.getString(paramJSONArray, 1);
/*  806 */                 localObject3 = this.c.a((String)localObject2);
/*  807 */                 if (localObject3 == null) {
/*  808 */                   localObject3 = this.c.a((String)localObject2, (String)localObject2, null);
/*      */                 }
/*  810 */                 if (!a((b)localObject3))
/*  811 */                   localc1.c((b)localObject3);
/*      */               }
/*  813 */               else if ("appendNativeView".equals(paramString)) {
/*  814 */                 localObject2 = JSONUtil.getString(paramJSONArray, 1);
/*  815 */                 b(paramIWebview, (String)localObject2);
/*  816 */               } else if ("captureSnapshot".equals(paramString)) {
/*  817 */                 a(paramIWebview, paramJSONArray, localc1);
/*  818 */               } else if ("clearSnapshot".equals(paramString)) {
/*  819 */                 localObject2 = paramJSONArray.getString(0);
/*  820 */                 localc1.u.clearSnapshot((String)localObject2);
/*  821 */               } else if ("draw".equals(paramString)) {
/*  822 */                 c(paramIWebview, paramJSONArray, localc1);
/*  823 */               } else if ("isHardwareAccelerated".equals(paramString)) {
/*  824 */                 localObject2 = ((AdaFrameItem)localc1.u).obtainFrameOptions();
/*  825 */                 str1 = JSUtil.wrapJsVar((((ViewOptions)localObject2).mUseHardwave) || ((localc1.u.getFrameType() == 2) && (localIApp.isStreamApp()) && (BaseInfo.isWap2AppAppid(str2))));
/*  826 */               } else if ("setCssFile".equals(paramString)) {
/*  827 */                 localObject2 = JSONUtil.getString(paramJSONArray, 0);
/*  828 */                 if (!PdrUtil.isEmpty(localObject2)) {
/*  829 */                   localObject2 = localIApp.convert2LocalFullPath(paramIWebview.obtainFullUrl(), (String)localObject2);
/*  830 */                   localc1.u.obtainWebView().setCssFile((String)localObject2, null);
/*      */                 }
/*  832 */               } else if ("setFixBottom".equals(paramString)) {
/*  833 */                 localObject2 = localc1.u.obtainWebView();
/*  834 */                 ((IWebview)localObject2).setFixBottom((int)(paramJSONArray.getInt(0) * ((IWebview)localObject2).getScale()));
/*  835 */               } else if ("getNavigationbar".equals(paramString)) {
/*  836 */                 localObject2 = String.valueOf(localc1.u.obtainWebView().obtainWebview().hashCode());
/*  837 */                 localObject3 = this.c.c.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[] { localc1.u.obtainWebView(), "nativeobj", "getNativeView", { localc1.u, localObject2 } });
/*      */ 
/*  840 */                 if ((null != localObject3) && ((localObject3 instanceof INativeView))) {
/*  841 */                   localObject4 = ((INativeView)localObject3).toJSON();
/*  842 */                   if (null != localObject4)
/*  843 */                     str1 = JSUtil.wrapJsVar(((JSONObject)localObject4).toString(), false);
/*      */                 }
/*      */               }
/*  846 */               else if ("drag".equals(paramString)) {
/*  847 */                 localObject2 = JSONUtil.getJSONObject(paramJSONArray, 0);
/*  848 */                 localObject3 = JSONUtil.getJSONObject(paramJSONArray, 1);
/*  849 */                 localObject4 = JSONUtil.getString(paramJSONArray, 2);
/*  850 */                 String str3 = JSONUtil.getString(paramJSONArray, 3);
/*      */ 
/*  852 */                 if ((null != localObject2) && 
/*  853 */                   (!TextUtils.isEmpty(JSONUtil.getString((JSONObject)localObject2, "direction"))) && (!TextUtils.isEmpty(JSONUtil.getString((JSONObject)localObject2, "moveMode")))) {
/*  854 */                   ViewOptions localViewOptions = localc1.e().obtainFrameOptions();
/*  855 */                   c localc2 = null;
/*  856 */                   String str4 = JSONUtil.getString((JSONObject)localObject3, "view");
/*  857 */                   if (!TextUtils.isEmpty(str4)) {
/*  858 */                     localc2 = this.c.a("", str4, str4);
/*      */                   }
/*  860 */                   View localView = null;
/*  861 */                   if (null == localc2) {
/*  862 */                     localObject5 = this.c.c.processEvent(IMgr.MgrType.FeatureMgr, 10, new Object[] { paramIWebview, "nativeobj", "getNativeView", { this.u, str4 } });
/*  863 */                     if ((null != localObject5) && ((localObject5 instanceof View))) {
/*  864 */                       localView = (View)localObject5;
/*      */                     }
/*      */                   }
/*  867 */                   Object localObject5 = this.c.a("", (String)localObject4, (String)localObject4);
/*  868 */                   localViewOptions.setDragData((JSONObject)localObject2, (JSONObject)localObject3, null == localc2 ? null : localc2.u, null == localObject5 ? null : ((c)localObject5).u, null != str3 ? str3 : null, localView);
/*      */                 }
/*      */ 
/*      */               }
/*  872 */               else if ("needTouchEvent".equals(paramString)) {
/*  873 */                 localObject2 = localc1.u.obtainWebView();
/*  874 */                 ((IWebview)localObject2).setWebviewProperty("needTouchEvent", "true");
/*  875 */                 str1 = "false";
/*  876 */               } else if ("setCssText".equals(paramString)) {
/*  877 */                 localObject2 = JSONUtil.getString(paramJSONArray, 0);
/*  878 */                 if (!PdrUtil.isEmpty(localObject2))
/*  879 */                   localc1.u.obtainWebView().setCssFile(null, (String)localObject2);
/*      */               }
/*  881 */               else if ("showBehind".equals(paramString)) {
/*  882 */                 localObject2 = JSONUtil.getString(paramJSONArray, 1);
/*  883 */                 localObject3 = this.c.a((String)localObject2);
/*  884 */                 if (localObject3 == null) {
/*  885 */                   localObject3 = this.c.a((String)localObject2, (String)localObject2, null);
/*      */ 
/*  887 */                   localc1.a((b)localObject3, this, str2);
/*      */                 }
/*      */               }
/*  890 */               else if ("checkRenderedContent".equals(paramString)) {
/*  891 */                 a(paramIWebview, paramJSONArray);
/*  892 */               } else if ("webview_animate".equals(paramString)) {
/*  893 */                 localObject2 = JSONUtil.getString(paramJSONArray, 0);
/*  894 */                 localObject3 = JSONUtil.getString(paramJSONArray, 1);
/*  895 */                 localc1.u.animate(paramIWebview, (String)localObject2, (String)localObject3);
/*  896 */               } else if ("webview_restore".equals(paramString)) {
/*  897 */                 localc1.u.restore();
/*  898 */               } else if ("setRenderedEventOptions".equals(paramString)) {
/*  899 */                 localObject2 = JSONUtil.getJSONObject(paramJSONArray, 0);
/*  900 */                 this.W = ((JSONObject)localObject2).optString("type", this.W);
/*  901 */                 this.X = ((JSONObject)localObject2).optInt("interval", this.X);
/*  902 */               } else if ("interceptTouchEvent".equals(paramString)) {
/*  903 */                 localObject2 = JSONUtil.getString(paramJSONArray, 0);
/*  904 */                 localc1.u.interceptTouchEvent(Boolean.valueOf((String)localObject2).booleanValue()); }  } 
/*      */           }
/*      */         }
/*      */       } } catch (Exception localException) { localException.printStackTrace(); }
/*      */ 
/*  909 */     return str1;
/*      */   }
/*      */ 
/*      */   private void a(final IWebview paramIWebview, JSONArray paramJSONArray) {
/*  913 */     final String str1 = JSONUtil.getString(paramJSONArray, 1);
/*  914 */     JSONObject localJSONObject = JSONUtil.getJSONObject(paramJSONArray, 2);
/*  915 */     String str2 = "auto";
/*  916 */     if ((localJSONObject != null) && (localJSONObject.has("type"))) {
/*  917 */       str2 = localJSONObject.optString("type", str2);
/*      */     }
/*  919 */     final String str3 = str2;
/*  920 */     ThreadPool.self().addThreadTask(new Runnable()
/*      */     {
/*      */       public void run() {
/*      */         try {
/*  924 */           boolean bool = paramIWebview.checkWhite(str3);
/*  925 */           JSUtil.execCallback(paramIWebview, str1, "{\"code\":100,\"rendered\":" + bool + "}", JSUtil.OK, true, false);
/*      */         } catch (Exception localException) {
/*  927 */           JSUtil.execCallback(paramIWebview, str1, "{\"code\":-100,\"message\":\"截图失败\"}", JSUtil.ERROR, true, false);
/*      */         }
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void a(b paramb, c paramc, String paramString) {
/*  934 */     c localc = (c)paramb;
/*  935 */     if (localc.B) {
/*  936 */       localc.q -= 1L;
/*  937 */       paramc.B = true;
/*  938 */       paramc.E = true;
/*  939 */       paramc.C = false;
/*      */ 
/*  941 */       int i = this.c.a(this);
/*      */ 
/*  944 */       this.c.a(paramString, paramc, i);
/*  945 */       this.c.c.processEvent(IMgr.MgrType.WindowMgr, 45, new Object[] { paramc.u, localc.u });
/*      */     }
/*      */   }
/*      */ 
/*      */   private void a(c paramc, String paramString)
/*      */   {
/*  953 */     if (paramc == null) {
/*  954 */       return;
/*      */     }
/*  956 */     IApp localIApp = paramc.k().obtainApp();
/*  957 */     if (localIApp == null) {
/*  958 */       return;
/*      */     }
/*      */ 
/*  961 */     c localc = paramc.R;
/*      */     String str1;
/*  963 */     if (localc == null)
/*  964 */       str1 = paramString;
/*      */     else {
/*  966 */       str1 = localc.k().obtainUrl();
/*      */     }
/*  968 */     String str2 = paramc.k().obtainUrl();
/*  969 */     if ((BaseInfo.isBase(paramc.c())) && (!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2)))
/*      */     {
/*  971 */       if ((str1.startsWith("http://")) || (str2.startsWith("http://")))
/*      */       {
/*  973 */         return;
/*      */       }
/*      */ 
/*  976 */       str1 = WebLoadEvent.getOriginalUrl(str1);
/*  977 */       str2 = WebLoadEvent.getOriginalUrl(str2);
/*  978 */       Log.i(".stream_json", String.format("{\"open\": {\"opener\":\"%s\",\"opened\":\"%s\"}}", new Object[] { WebLoadEvent.getHBuilderPrintUrl(localIApp.convert2RelPath(str1)), WebLoadEvent.getHBuilderPrintUrl(localIApp.convert2RelPath(str2)) }));
/*      */     }
/*      */   }
/*      */ 
/*      */   private void b(IWebview paramIWebview, JSONArray paramJSONArray, c paramc)
/*      */   {
/*  986 */     if (!paramc.F)
/*      */     {
/*  988 */       JSONObject localJSONObject = JSONUtil.getJSONObject(paramJSONArray, 0);
/*  989 */       boolean bool1 = a(localJSONObject, true);
/*      */ 
/*  991 */       Object localObject1 = (AdaFrameItem)paramc.u;
/*  992 */       if ((paramc.G) || (!localJSONObject.isNull("background"))) {
/*  993 */         localObject1 = paramc.u.obtainWebviewParent();
/*      */       }
/*  995 */       ((AdaFrameItem)localObject1).obtainFrameOptions().allowUpdate = true;
/*      */ 
/*  997 */       ViewOptions localViewOptions1 = ((AdaFrameItem)localObject1).obtainFrameOptions();
/*  998 */       Logger.d("View_Visible_Path", "setOption _old_win_options=" + localViewOptions1 + ";_new_json_option=" + localJSONObject);
/*      */ 
/* 1000 */       ViewOptions localViewOptions2 = ViewOptions.createViewOptionsData(localViewOptions1, localViewOptions1.getParentViewRect());
/* 1001 */       IWebview localIWebview = this.u.obtainWebView();
/* 1002 */       paramIWebview.setWebViewCacheMode(localViewOptions2.mCacheMode);
/*      */ 
/* 1004 */       JSONUtil.combinJSONObject(this.g, localJSONObject);
/*      */ 
/* 1006 */       a();
/*      */ 
/* 1008 */       if (!JSONUtil.isNull(localJSONObject, "navigationbar")) {
/* 1009 */         a(paramc, this.g.optJSONObject("navigationbar"));
/*      */       }
/* 1011 */       if (paramc.E) {
/* 1012 */         this.u.setFrameOptions_Animate(localViewOptions2);
/*      */ 
/* 1014 */         if (Build.VERSION.SDK_INT < 23) {
/* 1015 */           localViewOptions2.mWebviewScale = this.u.obtainWebView().getScale();
/*      */         }
/*      */ 
/* 1018 */         int i = localViewOptions2.background;
/*      */ 
/* 1020 */         float f = localViewOptions2.opacity;
/* 1021 */         boolean bool3 = localViewOptions2.updateViewData(localJSONObject);
/* 1022 */         boolean bool4 = PdrUtil.checkAlphaTransparent(i) != PdrUtil.checkAlphaTransparent(localViewOptions2.background);
/* 1023 */         bool4 |= f != localViewOptions2.opacity;
/* 1024 */         if ((localJSONObject != null) && 
/* 1025 */           (localJSONObject.has("render"))) {
/* 1026 */           localObject2 = localJSONObject.optString("render", "onscreen");
/* 1027 */           boolean bool5 = PdrUtil.isEquals((String)localObject2, "always");
/* 1028 */           paramc.u.setNeedRender(bool5);
/*      */         }
/*      */ 
/* 1032 */         ((AdaFrameItem)localObject1).setFrameOptions_Animate(localViewOptions2);
/*      */ 
/* 1034 */         paramc.G = localViewOptions2.hasBackground();
/*      */ 
/* 1036 */         if (!JSONUtil.isNull(localJSONObject, "scrollIndicator")) {
/* 1037 */           localIWebview.setScrollIndicator(localViewOptions2.getScrollIndicator());
/*      */         }
/*      */ 
/* 1040 */         if (!JSONUtil.isNull(localJSONObject, "scalable")) {
/* 1041 */           localIWebview.setWebviewProperty("scalable", String.valueOf(localViewOptions2.scalable));
/*      */         }
/*      */ 
/* 1044 */         if (!JSONUtil.isNull(localJSONObject, "videoFullscreen")) {
/* 1045 */           localIWebview.setWebviewProperty("videoFullscreen", String.valueOf(localViewOptions2.mVideoFullscree));
/*      */         }
/*      */ 
/* 1048 */         localIWebview.setWebviewProperty("injection", localViewOptions2.mInjection);
/*      */ 
/* 1050 */         localIWebview.setWebviewProperty("plusrequire", localViewOptions2.mPlusrequire);
/*      */ 
/* 1052 */         localIWebview.setWebviewProperty("geolocation", localViewOptions2.mGeoInject);
/* 1053 */         Object localObject2 = ((AdaFrameItem)localObject1).getAnimOptions();
/* 1054 */         if (!JSONUtil.isNull(localJSONObject, "transition")) {
/* 1055 */           ((AnimOptions)localObject2).parseTransition(localViewOptions2.transition);
/* 1056 */           if (localViewOptions2.transition.isNull("duration"))
/* 1057 */             ((AnimOptions)localObject2).duration = 0;
/*      */         }
/*      */         else {
/* 1060 */           ((AnimOptions)localObject2).duration = 0;
/*      */         }
/* 1062 */         if (!JSONUtil.isNull(localJSONObject, "transform")) {
/* 1063 */           ((AnimOptions)localObject2).parseTransform(localViewOptions2.transform);
/*      */         }
/*      */ 
/* 1066 */         if ((bool3) || (bool1) || (bool4))
/*      */         {
/* 1068 */           ((AdaFrameItem)paramc.u).getAnimOptions().mOption = 2;
/*      */         }
/* 1070 */         Object[] arrayOfObject = { paramc.u, Boolean.valueOf(bool3), Boolean.valueOf(bool3 ? 0 : bool1), Boolean.valueOf(bool4) };
/* 1071 */         this.c.c.processEvent(IMgr.MgrType.WindowMgr, 7, arrayOfObject);
/*      */       } else {
/* 1073 */         boolean bool2 = ((AdaFrameItem)localObject1).obtainFrameOptions().updateViewData(localJSONObject);
/* 1074 */         ((AdaFrameItem)localObject1).obtainFrameOptions_Birth().updateViewData(localJSONObject);
/* 1075 */         if (bool2) {
/* 1076 */           ViewOptions localViewOptions3 = ((AdaFrameItem)localObject1).obtainFrameOptions();
/* 1077 */           int j = localViewOptions3.left;
/* 1078 */           int k = localViewOptions3.top;
/* 1079 */           int m = localViewOptions3.left + localViewOptions3.width;
/* 1080 */           int n = localViewOptions3.top + localViewOptions3.height;
/*      */ 
/* 1082 */           ViewOptions localViewOptions4 = ((AdaFrameItem)localObject1).obtainFrameOptions();
/* 1083 */           localIWebview.setScrollIndicator(localViewOptions4.getScrollIndicator());
/*      */ 
/* 1085 */           localIWebview.setWebviewProperty("scalable", String.valueOf(localViewOptions4.scalable));
/*      */ 
/* 1087 */           localIWebview.setWebviewProperty("injection", localViewOptions2.mInjection);
/*      */ 
/* 1089 */           localIWebview.setWebviewProperty("plusrequire", localViewOptions2.mPlusrequire);
/*      */ 
/* 1091 */           localIWebview.setWebviewProperty("geolocation", localViewOptions2.mGeoInject);
/*      */ 
/* 1093 */           View localView = null;
/* 1094 */           localView = paramc.u.obtainMainView();
/*      */ 
/* 1097 */           AdaFrameItem.LayoutParamsUtil.setViewLayoutParams(localView, localViewOptions3.left, localViewOptions3.top, localViewOptions3.width, localViewOptions3.height);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/* 1103 */   private void a(JSONArray paramJSONArray, c paramc) { String str1 = JSONUtil.getString(paramJSONArray, 0);
/* 1104 */     String str2 = JSONUtil.getString(paramJSONArray, 1);
/* 1105 */     AnimOptions localAnimOptions = ((AdaFrameItem)paramc.u).getAnimOptions();
/* 1106 */     if (!PdrUtil.isEmpty(str2))
/* 1107 */       localAnimOptions.duration_close = PdrUtil.parseInt(str2, localAnimOptions.duration_close);
/*      */     else {
/* 1109 */       localAnimOptions.duration_close = localAnimOptions.duration_show;
/*      */     }
/*      */ 
/* 1113 */     localAnimOptions.setCloseAnimType(str1);
/* 1114 */     localAnimOptions.mOption = 3;
/* 1115 */     Logger.d("View_Visible_Path", "NWindow.hide view=" + paramc.e());
/*      */ 
/* 1117 */     if (paramc.B) {
/* 1118 */       boolean bool = paramc.p();
/* 1119 */       if (bool)
/*      */       {
/* 1121 */         JSONObject localJSONObject = JSONUtil.getJSONObject(paramJSONArray, 2);
/* 1122 */         a(localJSONObject, paramc, str1);
/* 1123 */         this.c.c.processEvent(IMgr.MgrType.WindowMgr, 23, paramc.u);
/*      */       } else {
/* 1125 */         onCallBack("hide", null);
/* 1126 */         paramc.u.setVisible(false, true);
/*      */       }
/* 1128 */       paramc.B = false;
/*      */     }
/*      */     else {
/* 1131 */       paramc.u.setVisible(false, true);
/*      */     }
/* 1133 */     paramc.C = true;
/*      */   }
/*      */ 
/*      */   private void n()
/*      */   {
/* 1140 */     AnimOptions localAnimOptions = ((AdaFrameItem)this.u).getAnimOptions();
/* 1141 */     localAnimOptions.mOption = 3;
/* 1142 */     this.B = false;
/* 1143 */     this.C = true;
/*      */   }
/*      */ 
/*      */   void a(IWebview paramIWebview, JSONArray paramJSONArray, c paramc, String paramString)
/*      */   {
/* 1149 */     if (this.c.g(paramc)) {
/* 1150 */       Logger.d("stream_manager", "showWebview url=" + paramc.v);
/* 1151 */       paramc.w = 1;
/* 1152 */       this.c.e(paramc);
/* 1153 */       paramc.x = new Object[] { paramIWebview, paramJSONArray, paramc, paramString };
/* 1154 */       return;
/*      */     }
/* 1156 */     paramc.q = System.currentTimeMillis();
/*      */ 
/* 1161 */     boolean bool1 = true;
/* 1162 */     paramc.B = bool1;
/*      */ 
/* 1164 */     String str1 = JSONUtil.getString(paramJSONArray, 0);
/*      */ 
/* 1166 */     String str2 = JSONUtil.getString(paramJSONArray, 1);
/*      */ 
/* 1168 */     this.K = JSONUtil.getString(paramJSONArray, 3);
/*      */ 
/* 1170 */     if (!PdrUtil.isEmpty(this.K)) {
/* 1171 */       this.L = paramIWebview;
/*      */     }
/* 1173 */     AnimOptions localAnimOptions = ((AdaFrameItem)paramc.u).getAnimOptions();
/* 1174 */     if (!PdrUtil.isEmpty(str2)) {
/* 1175 */       localAnimOptions.duration_show = PdrUtil.parseInt(str2, localAnimOptions.duration_show);
/*      */     }
/*      */ 
/* 1178 */     boolean bool2 = PdrUtil.isEquals("auto", str1);
/* 1179 */     localAnimOptions.mAnimType = (PdrUtil.isEmpty(str1) ? "none" : bool2 ? localAnimOptions.mAnimType : str1);
/* 1180 */     boolean bool3 = !PdrUtil.isEquals("none", localAnimOptions.mAnimType);
/*      */ 
/* 1183 */     bool3 = (!paramc.C) && (paramc.E) ? false : bool3;
/*      */ 
/* 1186 */     int i = this.c.a(this);
/*      */ 
/* 1190 */     this.c.a(paramString, paramc, i);
/*      */ 
/* 1194 */     JSONObject localJSONObject = JSONUtil.getJSONObject(paramJSONArray, 4);
/*      */ 
/* 1196 */     a(localJSONObject, paramc, str1);
/*      */ 
/* 1198 */     if (paramc.C) {
/* 1199 */       localAnimOptions.mOption = 4;
/* 1200 */       this.c.c.processEvent(IMgr.MgrType.WindowMgr, 24, paramc.u);
/*      */     } else {
/* 1202 */       localAnimOptions.mOption = 0;
/*      */ 
/* 1210 */       paramc.E = true;
/*      */ 
/* 1213 */       this.c.c.processEvent(IMgr.MgrType.WindowMgr, 1, new Object[] { paramc.u, Boolean.valueOf(bool3) });
/*      */     }
/*      */ 
/* 1216 */     paramc.C = false;
/* 1217 */     Logger.d("View_Visible_Path", "show " + paramc.u + ";webview_name=" + k().obtainFrameId());
/*      */   }
/*      */ 
/*      */   private void b(JSONArray paramJSONArray, c paramc) {
/* 1221 */     if (paramc.E) {
/* 1222 */       if (!paramc.F)
/*      */       {
/* 1224 */         this.c.d(paramc);
/* 1225 */         if (paramc.H) {
/* 1226 */           if (paramc.a != null) {
/* 1227 */             paramc.a.b(paramc);
/*      */           }
/*      */ 
/* 1230 */           paramc.e().onDispose();
/* 1231 */           paramc.e().dispose();
/*      */         } else {
/* 1233 */           String str1 = JSONUtil.getString(paramJSONArray, 0);
/*      */ 
/* 1235 */           String str2 = JSONUtil.getString(paramJSONArray, 1);
/* 1236 */           AnimOptions localAnimOptions = ((AdaFrameItem)paramc.u).getAnimOptions();
/*      */ 
/* 1238 */           if (!PdrUtil.isEmpty(str2))
/* 1239 */             localAnimOptions.duration_close = PdrUtil.parseInt(str2, localAnimOptions.duration_close);
/*      */           else {
/* 1241 */             localAnimOptions.duration_close = localAnimOptions.duration_show;
/*      */           }
/*      */ 
/* 1245 */           localAnimOptions.setCloseAnimType(PdrUtil.isEmpty(str1) ? "auto" : str1);
/* 1246 */           localAnimOptions.mOption = 1;
/*      */ 
/* 1248 */           JSONObject localJSONObject = JSONUtil.getJSONObject(paramJSONArray, 2);
/*      */ 
/* 1250 */           a(localJSONObject, paramc, str1);
/* 1251 */           this.c.c.processEvent(IMgr.MgrType.WindowMgr, 2, paramc.u);
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/* 1256 */       this.c.d(paramc);
/* 1257 */       paramc.e().onDispose();
/* 1258 */       paramc.e().dispose();
/*      */     }
/*      */ 
/* 1261 */     paramc.g();
/*      */   }
/*      */ 
/*      */   private void o()
/*      */   {
/* 1268 */     this.c.d(this);
/* 1269 */     if (this.E) {
/* 1270 */       if (!this.F)
/* 1271 */         if (this.H) {
/* 1272 */           if (this.a != null) {
/* 1273 */             this.a.b(this);
/*      */           }
/*      */ 
/* 1276 */           e().onDispose();
/* 1277 */           e().dispose();
/*      */         } else {
/* 1279 */           AnimOptions localAnimOptions = ((AdaFrameItem)this.u).getAnimOptions();
/* 1280 */           localAnimOptions.mOption = 1;
/*      */         }
/*      */     }
/*      */     else {
/* 1284 */       e().onDispose();
/* 1285 */       e().dispose();
/*      */     }
/*      */ 
/* 1288 */     g();
/*      */   }
/*      */ 
/*      */   private void a(JSONObject paramJSONObject, c paramc, String paramString)
/*      */   {
/*      */     Object localObject1;
/* 1298 */     if (paramJSONObject != null)
/*      */     {
/* 1302 */       localObject1 = paramJSONObject.optString("acceleration");
/* 1303 */       Object localObject2 = TextUtils.isEmpty((CharSequence)localObject1) ? "auto" : localObject1;
/* 1304 */       String str = "capture";
/*      */ 
/* 1306 */       paramc.u.setAccelerationType(localObject2);
/* 1307 */       IFrameView localIFrameView = paramc.u.findPageB();
/* 1308 */       if (localIFrameView == null) {
/* 1309 */         return;
/*      */       }
/*      */ 
/* 1312 */       localIFrameView.setAccelerationType(localObject2);
/*      */ 
/* 1315 */       if (paramJSONObject.has(str)) {
/* 1316 */         localObject3 = c(localIFrameView.obtainWebView(), paramJSONObject.optJSONObject(str).optString("__id__"));
/* 1317 */         paramc.u.setSnapshot(null != localObject3 ? ((INativeBitmap)localObject3).getBitmap() : null);
/*      */       }
/* 1319 */       Object localObject3 = "otherCapture";
/*      */ 
/* 1321 */       if (paramJSONObject.has((String)localObject3)) {
/* 1322 */         INativeBitmap localINativeBitmap = c(localIFrameView.obtainWebView(), paramJSONObject.optJSONObject((String)localObject3).optString("__id__"));
/* 1323 */         if (localIFrameView != null)
/* 1324 */           localIFrameView.setSnapshot(null != localINativeBitmap ? localINativeBitmap.getBitmap() : null);
/*      */       }
/*      */     }
/*      */     else {
/* 1328 */       paramc.u.setSnapshot(null);
/* 1329 */       paramc.u.setAccelerationType("auto");
/* 1330 */       localObject1 = paramc.u.findPageB();
/* 1331 */       if (localObject1 != null) {
/* 1332 */         ((IFrameView)localObject1).setSnapshot(null);
/* 1333 */         ((IFrameView)localObject1).setAccelerationType("auto");
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   /** @deprecated */
/*      */   void a(final IWebview paramIWebview, JSONArray paramJSONArray, c paramc)
/*      */   {
/* 1347 */     String str1 = JSONUtil.getString(paramJSONArray, 0);
/* 1348 */     final String str2 = JSONUtil.getString(paramJSONArray, 1);
/* 1349 */     paramc.u.captureSnapshot(str1, TextUtils.isEmpty(str2) ? null : new ICallBack()
/*      */     {
/*      */       public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject) {
/* 1352 */         JSUtil.execCallback(paramIWebview, str2, null, JSUtil.OK, false, false);
/* 1353 */         return null;
/*      */       }
/*      */     }
/*      */     , TextUtils.isEmpty(str2) ? null : new ICallBack()
/*      */     {
/*      */       public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject)
/*      */       {
/* 1358 */         JSUtil.execCallback(paramIWebview, str2, "{\"code\":-100,\"message\":\"截图失败\"}", JSUtil.ERROR, true, false);
/* 1359 */         return null;
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private void c(final IWebview paramIWebview, JSONArray paramJSONArray, c paramc)
/*      */   {
/* 1370 */     String str1 = JSONUtil.getString(paramJSONArray, 0);
/* 1371 */     String str2 = JSONUtil.getString(paramJSONArray, 1);
/*      */ 
/* 1373 */     View localView = this.c.a(str2, str2, null).e().obtainMainView();
/* 1374 */     final String str3 = JSONUtil.getString(paramJSONArray, 2);
/* 1375 */     boolean bool = false;
/* 1376 */     Rect localRect = null;
/* 1377 */     JSONObject localJSONObject1 = JSONUtil.getJSONObject(paramJSONArray, 3);
/* 1378 */     if (localJSONObject1 != null) {
/* 1379 */       bool = localJSONObject1.optBoolean("check", bool);
/* 1380 */       JSONObject localJSONObject2 = localJSONObject1.optJSONObject("clip");
/* 1381 */       if (localJSONObject2 != null) {
/* 1382 */         int i = localView.getWidth();
/* 1383 */         int j = localView.getHeight();
/* 1384 */         float f = paramIWebview.getScale();
/* 1385 */         int k = PdrUtil.convertToScreenInt(localJSONObject2.optString("left"), i, 0, f);
/* 1386 */         int m = PdrUtil.convertToScreenInt(localJSONObject2.optString("top"), j, 0, f);
/* 1387 */         int n = PdrUtil.convertToScreenInt(localJSONObject2.optString("width"), i, i, f);
/* 1388 */         int i1 = PdrUtil.convertToScreenInt(localJSONObject2.optString("height"), j, j, f);
/* 1389 */         localRect = new Rect(k, m, n, i1);
/*      */       }
/*      */     }
/* 1392 */     paramIWebview.obtainFrameView().draw(localView, c(paramIWebview, str1), bool, localRect, TextUtils.isEmpty(str3) ? null : new ICallBack()
/*      */     {
/*      */       public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject) {
/* 1395 */         JSUtil.execCallback(paramIWebview, str3, null, JSUtil.OK, false, false);
/* 1396 */         return null;
/*      */       }
/*      */     }
/*      */     , TextUtils.isEmpty(str3) ? null : new ICallBack()
/*      */     {
/*      */       public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject)
/*      */       {
/* 1401 */         JSUtil.execCallback(paramIWebview, str3, "{\"code\":" + paramAnonymousInt + ",\"message\":\"" + (paramAnonymousObject != null ? paramAnonymousObject.toString() : "截图失败") + "\"}", JSUtil.ERROR, true, false);
/* 1402 */         return null;
/*      */       }
/*      */     });
/*      */   }
/*      */ 
/*      */   private INativeBitmap c(IWebview paramIWebview, String paramString) {
/* 1408 */     INativeBitmap localINativeBitmap = (INativeBitmap)paramIWebview.obtainApp().obtainMgrData(IMgr.MgrType.FeatureMgr, 10, new Object[] { paramIWebview, "nativeobj", "getNativeBitmap", { paramIWebview.obtainApp().obtainAppId(), paramString } });
/*      */ 
/* 1410 */     return localINativeBitmap;
/*      */   }
/*      */ 
/*      */   boolean b(String paramString1, String paramString2, boolean paramBoolean)
/*      */   {
/* 1423 */     if (this.I != null) {
/* 1424 */       int i = this.I.size();
/* 1425 */       for (int j = i - 1; j >= 0; j--) {
/* 1426 */         b localb = (b)this.I.get(j);
/* 1427 */         if (((localb instanceof c)) && (((c)localb).B) && 
/* 1428 */           (((c)localb).b(paramString1, paramString2, paramBoolean))) {
/* 1429 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1434 */     return (a(paramString1)) && (a(paramString1, paramString2, paramBoolean));
/*      */   }
/*      */ 
/*      */   boolean c(String paramString) {
/* 1438 */     if (this.I != null) {
/* 1439 */       int i = this.I.size();
/* 1440 */       for (int j = i - 1; j >= 0; j--) {
/* 1441 */         b localb = (b)this.I.get(j);
/* 1442 */         if (((localb instanceof c)) && 
/* 1443 */           (((c)localb).a(paramString))) {
/* 1444 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 1449 */     return a(paramString);
/*      */   }
/*      */ 
/*      */   private boolean p()
/*      */   {
/* 1456 */     if (this.a != null) {
/* 1457 */       return (this.a.B) && (this.a.p());
/*      */     }
/* 1459 */     return true;
/*      */   }
/*      */   private void a(c paramc, boolean paramBoolean) {
/* 1462 */     paramc.u.obtainWebView().reload(paramBoolean);
/*      */   }
/*      */ 
/*      */   public IWebview k()
/*      */   {
/* 1474 */     return this.u.obtainWebView();
/*      */   }
/*      */ 
/*      */   public String l() {
/* 1478 */     IWebview localIWebview = this.u.obtainWebView();
/* 1479 */     if (localIWebview != null) {
/* 1480 */       return localIWebview.obtainFrameId();
/*      */     }
/* 1482 */     return null;
/*      */   }
/*      */ 
/*      */   boolean a(JSONObject paramJSONObject, boolean paramBoolean)
/*      */   {
/* 1487 */     boolean bool = false;
/* 1488 */     if (!paramJSONObject.isNull("zindex")) {
/* 1489 */       String str = JSONUtil.getString(paramJSONObject, "zindex");
/*      */       try {
/* 1491 */         int i = Integer.parseInt(str);
/* 1492 */         if (i != this.A) {
/* 1493 */           bool = true;
/* 1494 */           this.A = i;
/* 1495 */           ((AdaFrameView)this.u).mZIndex = this.A;
/* 1496 */           if (paramBoolean)
/* 1497 */             this.c.b(this);
/*      */         }
/*      */       }
/*      */       catch (Exception localException) {
/*      */       }
/*      */     }
/* 1503 */     return bool;
/*      */   }
/*      */ 
/*      */   public String d()
/*      */   {
/* 1510 */     if (PdrUtil.isEmpty(k().obtainFrameId())) {
/* 1511 */       return String.format("(function(){return {'uuid':'%s','id':%s,'identity':'%s','extras':%s}})()", new Object[] { this.e, "undefined", this.d, this.t == null ? "{}" : this.t.toString() });
/*      */     }
/* 1513 */     return String.format("(function(){return {'uuid':'%s','id':'%s','identity':'%s','extras':%s}})()", new Object[] { this.e, k().obtainFrameId(), this.d, this.t == null ? "{}" : this.t.toString() });
/*      */   }
/*      */ 
/*      */   private static String a(ArrayList paramArrayList)
/*      */   {
/* 1518 */     StringBuffer localStringBuffer = new StringBuffer("[");
/* 1519 */     if (paramArrayList != null) {
/* 1520 */       int i = paramArrayList.size();
/* 1521 */       for (int j = 0; j < i; j++) {
/* 1522 */         b localb = (b)paramArrayList.get(j);
/* 1523 */         if ((localb instanceof c))
/* 1524 */           localStringBuffer.append(((c)localb).d());
/*      */         else {
/* 1526 */           localStringBuffer.append("'" + localb.e + "'");
/*      */         }
/* 1528 */         if (j != i - 1) {
/* 1529 */           localStringBuffer.append(",");
/*      */         }
/*      */       }
/*      */     }
/* 1533 */     localStringBuffer.append("]");
/* 1534 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   protected void g() {
/* 1538 */     if ((this.R != null) && (this.R.U != null)) {
/* 1539 */       this.R.U.remove(this);
/*      */     }
/* 1541 */     this.R = null;
/* 1542 */     this.a = null;
/* 1543 */     if (this.I != null) {
/* 1544 */       for (b localb : this.I) {
/* 1545 */         localb.g();
/*      */       }
/* 1547 */       this.I.clear();
/* 1548 */       this.I = null;
/*      */     }
/* 1550 */     this.L = null;
/* 1551 */     this.K = null;
/* 1552 */     this.M = null;
/* 1553 */     this.N = null;
/* 1554 */     this.X = 150;
/*      */   }
/*      */ 
/*      */   public void m()
/*      */   {
/* 1559 */     if ((this.i != null) && (this.i.containsKey("rendered"))) {
/* 1560 */       if (this.S != null) {
/* 1561 */         MessageHandler.removeCallbacks(this.S);
/*      */       }
/* 1563 */       this.S = new Runnable()
/*      */       {
/*      */         public void run() {
/* 1566 */           boolean bool = c.this.u.obtainWebView().checkWhite(c.b(c.this));
/* 1567 */           if (bool)
/* 1568 */             c.this.m();
/*      */           else {
/* 1570 */             c.this.b("rendered", null);
/*      */           }
/* 1572 */           c.this.S = null;
/*      */         }
/*      */       };
/* 1575 */       MessageHandler.postDelayed(this.S, this.X);
/*      */     }
/*      */   }
/*      */ 
/*      */   private void a(c paramc, JSONObject paramJSONObject)
/*      */   {
/* 1584 */     String str1 = String.valueOf(paramc.k().obtainWebview().hashCode());
/* 1585 */     if (null != paramJSONObject) {
/* 1586 */       String str2 = paramJSONObject.optString("backgroundcolor");
/* 1587 */       String str3 = paramJSONObject.optString("titletext");
/* 1588 */       String str4 = paramJSONObject.optString("titlecolor");
/*      */ 
/* 1590 */       if (!TextUtils.isEmpty(str2)) {
/* 1591 */         this.c.c.processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[] { paramc.k(), "nativeobj", "setStyle", JSONUtil.createJSONArray("['" + str1 + "','" + str1 + "',{'backgroudColor':'" + str2 + "'}]") });
/*      */       }
/*      */ 
/* 1594 */       if ((!TextUtils.isEmpty(str3)) && (!TextUtils.isEmpty(str4)))
/* 1595 */         this.c.c.processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[] { paramc.k(), "nativeobj", "drawText", JSONUtil.createJSONArray("['" + str1 + "','" + str1 + "','" + str3 + "',{'top':'0px','left':'0px','width':'100%','height':'100%'},{'size':'16px','color':'" + str4 + "'},'" + str1 + "',null]") });
/*      */     }
/*      */   }
/*      */ 
/*      */   static
/*      */   {
/*  492 */     Y.put("close", "onclose");
/*  493 */     Y.put("loading", "onloading");
/*  494 */     Y.put("failed", "onerror");
/*  495 */     Y.put("loaded", "onloaded");
/*      */   }
/*      */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\ui.jar
 * Qualified Name:     io.dcloud.feature.ui.c
 * JD-Core Version:    0.6.2
 */