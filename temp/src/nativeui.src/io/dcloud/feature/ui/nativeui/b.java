/*     */ package io.dcloud.feature.ui.nativeui;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.BitmapFactory.Options;
/*     */ import android.graphics.drawable.AnimationDrawable;
/*     */ import android.graphics.drawable.BitmapDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.graphics.drawable.GradientDrawable;
/*     */ import android.os.Build.VERSION;
/*     */ import android.text.TextUtils;
/*     */ import android.view.KeyEvent;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.View;
/*     */ import android.view.View.OnClickListener;
/*     */ import android.view.View.OnKeyListener;
/*     */ import android.view.View.OnTouchListener;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.LinearLayout.LayoutParams;
/*     */ import android.widget.PopupWindow;
/*     */ import android.widget.PopupWindow.OnDismissListener;
/*     */ import android.widget.ProgressBar;
/*     */ import android.widget.TextView;
/*     */ import io.dcloud.RInformation;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ class b
/*     */   implements PopupWindow.OnDismissListener, ISysEventListener
/*     */ {
/*  59 */   public static final String a = b.class.getSimpleName();
/*     */   private Context d;
/*     */   private NativeUIFeatureImpl e;
/*     */   private IWebview f;
/*     */   private IApp g;
/*     */   private String h;
/*     */   public String b;
/*  83 */   private String i = "close";
/*     */   private PopupWindow j;
/*     */   private TextView k;
/*     */   private View l;
/*     */   private ProgressBar m;
/*     */   private ImageView n;
/*     */   private String o;
/*     */   private String p;
/*     */   private LinearLayout q;
/*     */   private String r;
/*     */   private String s;
/*  97 */   private boolean t = true;
/*     */   private String u;
/*     */   private int v;
/*     */   private int w;
/* 101 */   private int x = -872415232;
/*     */   private View y;
/*     */   private int z;
/*     */   private int A;
/*     */   private int B;
/* 106 */   private boolean C = false;
/*     */   private long D;
/*     */   private String E;
/* 109 */   private int F = -1;
/*     */   private String G;
/*     */   private AnimationDrawable H;
/*     */   private Bitmap I;
/*     */   private int J;
/* 115 */   private int K = -2;
/* 116 */   private int L = -2;
/*     */   LinearLayout c;
/*     */ 
/*     */   b(NativeUIFeatureImpl paramNativeUIFeatureImpl, IWebview paramIWebview, String paramString1, JSONObject paramJSONObject, String paramString2)
/*     */   {
/* 120 */     this.e = paramNativeUIFeatureImpl;
/* 121 */     this.f = paramIWebview;
/* 122 */     this.g = paramIWebview.obtainApp();
/* 123 */     this.h = paramString2;
/* 124 */     this.d = paramIWebview.getContext();
/* 125 */     this.y = ((AdaFrameItem)paramIWebview.obtainFrameView()).obtainMainView();
/*     */ 
/* 127 */     this.z = this.g.getInt(0);
/* 128 */     this.A = this.g.getInt(1);
/* 129 */     a(paramIWebview, paramJSONObject);
/* 130 */     c();
/* 131 */     a(this.q);
/* 132 */     b(paramString1);
/* 133 */     d();
/*     */   }
/*     */ 
/*     */   private void a(IWebview paramIWebview, JSONObject paramJSONObject) {
/* 137 */     if (!JSONUtil.isNull(paramJSONObject, "background")) {
/* 138 */       this.x = PdrUtil.stringToColor(JSONUtil.getString(paramJSONObject, "background"));
/*     */     }
/*     */ 
/* 142 */     String str1 = JSONUtil.getString(paramJSONObject, "modal");
/*     */ 
/* 144 */     if (!PdrUtil.isEmpty(str1)) {
/* 145 */       this.t = (!PdrUtil.isEquals("false", str1));
/*     */     }
/*     */ 
/* 148 */     float f1 = paramIWebview.getScale();
/* 149 */     this.B = ((int)(PdrUtil.parseInt(JSONUtil.getString(paramJSONObject, "round"), 10) * f1));
/*     */ 
/* 152 */     String str2 = JSONUtil.getString(paramJSONObject, "padlock");
/*     */ 
/* 154 */     if (!PdrUtil.isEmpty(str2)) {
/* 155 */       this.C |= Boolean.valueOf(str2).booleanValue();
/*     */     }
/*     */ 
/* 158 */     this.K = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "width"), this.z, this.K, f1);
/*     */ 
/* 161 */     this.L = PdrUtil.convertToScreenInt(JSONUtil.getString(paramJSONObject, "height"), this.A, this.L, f1);
/*     */ 
/* 165 */     String str3 = JSONUtil.getString(paramJSONObject, "back");
/*     */ 
/* 167 */     if (!TextUtils.isEmpty(str3)) {
/* 168 */       this.i = str3;
/*     */     }
/* 170 */     this.u = JSONUtil.getString(paramJSONObject, "style");
/*     */     String str4;
/* 173 */     if (!JSONUtil.isNull(paramJSONObject, "color")) {
/* 174 */       str4 = JSONUtil.getString(paramJSONObject, "color");
/*     */ 
/* 176 */       this.o = str4;
/*     */     }
/*     */ 
/* 179 */     this.r = JSONUtil.getString(paramJSONObject, "paddin");
/*     */ 
/* 181 */     if (!JSONUtil.isNull(paramJSONObject, "padding")) {
/* 182 */       str4 = JSONUtil.getString(paramJSONObject, "padding");
/*     */ 
/* 184 */       if (str4.indexOf('%') >= 0) {
/* 185 */         this.v = PdrUtil.convertToScreenInt(str4, this.z, this.v, f1);
/*     */ 
/* 187 */         this.w = PdrUtil.convertToScreenInt(str4, this.A, this.w, f1);
/*     */       }
/*     */       else {
/* 190 */         this.v = (this.w = PdrUtil.convertToScreenInt(str4, this.z, this.w, f1));
/*     */       }
/*     */     }
/*     */     else {
/* 194 */       int i1 = PdrUtil.parseInt("3%", this.z, 0);
/* 195 */       i1 = PdrUtil.parseInt(this.r, this.z, i1);
/* 196 */       this.v = (this.w = i1);
/*     */     }
/*     */ 
/* 199 */     if (!JSONUtil.isNull(paramJSONObject, "textalign")) {
/* 200 */       localObject = JSONUtil.getString(paramJSONObject, "textalign");
/*     */ 
/* 202 */       this.p = ((String)localObject);
/*     */     }
/*     */ 
/* 206 */     Object localObject = JSONUtil.getJSONObject(paramJSONObject, "loading");
/*     */ 
/* 208 */     if (localObject != null) {
/* 209 */       this.s = JSONUtil.getString((JSONObject)localObject, "display");
/*     */ 
/* 211 */       this.D = JSONUtil.getLong((JSONObject)localObject, "interval");
/*     */ 
/* 213 */       this.E = JSONUtil.getString((JSONObject)localObject, "icon");
/* 214 */       this.F = PdrUtil.convertToScreenInt(JSONUtil.getString((JSONObject)localObject, "height"), this.z, -1, f1);
/*     */     }
/*     */ 
/* 218 */     if (!TextUtils.isEmpty(this.E)) {
/* 219 */       this.G = this.g.convert2AbsFullPath(this.f.obtainFullUrl(), this.E);
/*     */     }
/*     */ 
/* 223 */     String str5 = JSONUtil.getString(paramJSONObject, "size");
/* 224 */     this.J = PdrUtil.convertToScreenInt(str5, this.z, 0, f1);
/*     */   }
/*     */ 
/*     */   private void c()
/*     */   {
/* 229 */     LayoutInflater localLayoutInflater = (LayoutInflater)this.d.getSystemService("layout_inflater");
/*     */ 
/* 231 */     this.c = ((LinearLayout)localLayoutInflater.inflate(RInformation.LAYOUT_DIALOG_LAYOUT_LOADING_DCLOUD, null, false));
/*     */ 
/* 233 */     this.q = ((LinearLayout)this.c.findViewById(RInformation.DCLOUD_LOADING_LAYOUT_ROOT));
/* 234 */     LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.q.getLayoutParams();
/* 235 */     localLayoutParams.width = this.K;
/* 236 */     localLayoutParams.height = this.L;
/* 237 */     this.q.setLayoutParams(localLayoutParams);
/*     */   }
/*     */ 
/*     */   private void a(View paramView) {
/* 241 */     this.k = ((TextView)paramView.findViewById(RInformation.ID_TEXT_LOADING_DCLOUD));
/*     */ 
/* 243 */     this.m = ((ProgressBar)paramView.findViewById(RInformation.ID_PROGRESSBAR_LOADING_DCLOUD));
/*     */ 
/* 245 */     this.n = ((ImageView)paramView.findViewById(RInformation.ID_IMAGE_LOADING_DCLOUD));
/*     */ 
/* 247 */     this.l = paramView.findViewById(RInformation.ID_WAITING_SEPARATOR_DCLOUD);
/*     */   }
/*     */ 
/*     */   private void b(String paramString) {
/* 251 */     f();
/* 252 */     e();
/* 253 */     g();
/* 254 */     j();
/* 255 */     a(paramString);
/* 256 */     h();
/* 257 */     i();
/*     */   }
/*     */ 
/*     */   private void d() {
/* 261 */     int i1 = -2;
/* 262 */     int i2 = -2;
/* 263 */     if ((Build.VERSION.SDK_INT >= 23) && (this.t == true)) {
/* 264 */       this.t = false;
/* 265 */       i1 = -1;
/* 266 */       i2 = -1;
/*     */     }
/* 268 */     if ((!this.t) || ((!TextUtils.isEmpty(this.i)) && (!"close".equalsIgnoreCase(this.i))))
/*     */     {
/* 271 */       this.g.registerSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
/*     */     }
/*     */ 
/* 275 */     this.j = new PopupWindow(this.c, i1, i2, this.t);
/* 276 */     this.j.showAtLocation(this.y, 17, 0, 0);
/* 277 */     this.j.setOnDismissListener(this);
/* 278 */     this.j.setBackgroundDrawable(new BitmapDrawable());
/* 279 */     this.j.setOutsideTouchable(true);
/* 280 */     this.j.setTouchInterceptor(new View.OnTouchListener()
/*     */     {
/*     */       public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
/*     */       {
/* 285 */         return b.a(b.this);
/*     */       }
/*     */     });
/* 288 */     if ((this.H != null) && (!this.H.isRunning()))
/* 289 */       this.H.start();
/*     */   }
/*     */ 
/*     */   private void e()
/*     */   {
/* 295 */     int i1 = -1;
/* 296 */     if (!PdrUtil.isEmpty(this.o)) {
/* 297 */       i1 = PdrUtil.stringToColor(this.o);
/*     */     }
/* 299 */     this.k.setTextColor(i1);
/*     */ 
/* 301 */     if (!PdrUtil.isEmpty(this.p)) {
/* 302 */       if ("left".equals(this.p))
/* 303 */         this.k.setGravity(3);
/* 304 */       else if ("right".equals(this.p))
/* 305 */         this.k.setGravity(5);
/*     */       else
/* 307 */         this.k.setGravity(17);
/*     */     }
/*     */     else {
/* 310 */       this.k.setGravity(17);
/*     */     }
/*     */ 
/* 313 */     if (this.J > 0)
/* 314 */       this.k.setTextSize(0, this.J);
/*     */   }
/*     */ 
/*     */   private void f()
/*     */   {
/* 319 */     if ("block".equalsIgnoreCase(this.s)) {
/* 320 */       this.q.setOrientation(1);
/* 321 */     } else if ("inline".equalsIgnoreCase(this.s)) {
/* 322 */       this.q.setOrientation(0);
/* 323 */     } else if ("none".equalsIgnoreCase(this.s)) {
/* 324 */       this.l.setVisibility(8);
/* 325 */       this.m.setVisibility(8);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void g()
/*     */   {
/*     */     Drawable localDrawable;
/* 331 */     if (PdrUtil.isEquals(this.u, "black")) {
/* 332 */       localDrawable = this.d.getResources().getDrawable(RInformation.DRAWBLE_PROGRESSBAR_BLACK_DCLOUD);
/*     */     }
/*     */     else {
/* 335 */       localDrawable = this.d.getResources().getDrawable(RInformation.DRAWBLE_PROGRESSBAR_WHITE_DCLOUD);
/*     */     }
/*     */ 
/* 338 */     if (this.F > 0) {
/* 339 */       this.m.setLayoutParams(new LinearLayout.LayoutParams(this.F, this.F));
/*     */     } else {
/* 341 */       int i1 = (int)(localDrawable.getIntrinsicHeight() * 0.3D);
/* 342 */       this.m.setLayoutParams(new LinearLayout.LayoutParams(i1, i1));
/*     */     }
/* 344 */     this.m.setIndeterminateDrawable(localDrawable);
/*     */   }
/*     */ 
/*     */   private void h() {
/* 348 */     this.q.setFocusable(true);
/* 349 */     this.q.setFocusableInTouchMode(true);
/* 350 */     this.q.setOnKeyListener(new View.OnKeyListener()
/*     */     {
/*     */       public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
/*     */       {
/* 354 */         if ((paramAnonymousKeyEvent.getAction() == 0) && (paramAnonymousInt == 4))
/*     */         {
/* 356 */           if ("close".equalsIgnoreCase(b.b(b.this))) {
/* 357 */             b.this.a();
/* 358 */             b.this.b();
/* 359 */             return true;
/* 360 */           }if ("transmit".equalsIgnoreCase(b.b(b.this)))
/*     */           {
/* 362 */             if (b.c(b.this).canGoBack())
/* 363 */               b.c(b.this).goBackOrForward(-1);
/*     */             else {
/* 365 */               b.c(b.this).getActivity().onBackPressed();
/*     */             }
/* 367 */             return false;
/* 368 */           }if ("none".equalsIgnoreCase(b.b(b.this)))
/*     */           {
/* 370 */             return true;
/*     */           }
/*     */         }
/* 373 */         return false;
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   private void i() {
/* 379 */     this.q.setPadding(this.v, this.w, this.v, this.w);
/* 380 */     GradientDrawable localGradientDrawable = (GradientDrawable)this.q.getBackground();
/*     */ 
/* 382 */     if (this.B > 0) {
/* 383 */       localGradientDrawable.setCornerRadius(this.B);
/*     */     }
/* 385 */     localGradientDrawable.setColor(this.x);
/*     */ 
/* 387 */     if (this.C)
/* 388 */       this.q.setOnClickListener(new View.OnClickListener()
/*     */       {
/*     */         public void onClick(View paramAnonymousView)
/*     */         {
/* 392 */           b.this.a();
/* 393 */           b.this.b();
/*     */         }
/*     */       });
/*     */   }
/*     */ 
/*     */   private int a(int paramInt)
/*     */   {
/* 400 */     int i1 = 1;
/* 401 */     int i2 = Math.min(this.z, this.A) - this.v - this.v;
/* 402 */     if ((i2 > 0) && (paramInt > i2))
/* 403 */       i1 = paramInt / i2;
/*     */     else {
/* 405 */       i1 = 1;
/*     */     }
/* 407 */     return i1;
/*     */   }
/*     */ 
/*     */   private void j()
/*     */   {
/* 414 */     if (TextUtils.isEmpty(this.G)) {
/* 415 */       return;
/*     */     }
/* 417 */     BitmapFactory.Options localOptions = new BitmapFactory.Options();
/* 418 */     localOptions.inJustDecodeBounds = true;
/*     */ 
/* 421 */     InputStream localInputStream = this.g.obtainResInStream(this.G);
/* 422 */     byte[] arrayOfByte = new byte[0];
/*     */     try {
/* 424 */       arrayOfByte = a(localInputStream);
/*     */     } catch (IOException localIOException) {
/* 426 */       localIOException.printStackTrace();
/*     */     }
/*     */ 
/* 429 */     BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length, localOptions);
/*     */ 
/* 431 */     int i1 = localOptions.outWidth;
/* 432 */     int i2 = localOptions.outHeight;
/* 433 */     if ((i1 == 0) || (i2 == 0)) {
/* 434 */       return;
/*     */     }
/* 436 */     if (i1 % i2 != 0) {
/* 437 */       return;
/*     */     }
/*     */ 
/* 440 */     localOptions.inSampleSize = a(i2);
/* 441 */     localOptions.inJustDecodeBounds = false;
/*     */ 
/* 443 */     this.I = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length, localOptions);
/* 444 */     if (null != this.I) {
/* 445 */       int i3 = this.I.getWidth();
/* 446 */       int i4 = this.I.getHeight();
/*     */ 
/* 448 */       int i5 = i3 / i4;
/*     */ 
/* 450 */       if (this.D <= 0L) {
/* 451 */         this.D = 100L;
/*     */       }
/*     */ 
/* 454 */       AnimationDrawable localAnimationDrawable = new AnimationDrawable();
/* 455 */       for (int i6 = 0; i6 < i5; i6++) {
/* 456 */         Bitmap localBitmap = Bitmap.createBitmap(this.I, i6 * i4, 0, i4, i4);
/*     */ 
/* 458 */         localAnimationDrawable.addFrame(new BitmapDrawable(localBitmap), (int)this.D);
/*     */       }
/*     */ 
/* 461 */       localAnimationDrawable.setOneShot(false);
/* 462 */       ViewGroup.LayoutParams localLayoutParams = this.n.getLayoutParams();
/* 463 */       if (localLayoutParams != null) {
/* 464 */         if (this.F > 0) {
/* 465 */           i4 = this.F;
/*     */         }
/* 467 */         localLayoutParams.width = i4;
/* 468 */         localLayoutParams.height = i4;
/* 469 */         this.n.setLayoutParams(localLayoutParams);
/*     */       }
/* 471 */       this.m.setVisibility(8);
/* 472 */       this.n.setVisibility(0);
/* 473 */       this.n.setBackground(localAnimationDrawable);
/* 474 */       this.H = ((AnimationDrawable)this.n.getBackground());
/*     */     }
/*     */   }
/*     */ 
/*     */   private byte[] a(InputStream paramInputStream) throws IOException {
/* 479 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/* 480 */     byte[] arrayOfByte = new byte[1024];
/* 481 */     int i1 = 0;
/* 482 */     while ((i1 = paramInputStream.read(arrayOfByte)) != -1) {
/* 483 */       localByteArrayOutputStream.write(arrayOfByte, 0, i1);
/*     */     }
/* 485 */     paramInputStream.close();
/* 486 */     localByteArrayOutputStream.close();
/* 487 */     return localByteArrayOutputStream.toByteArray();
/*     */   }
/*     */ 
/*     */   void a() {
/* 491 */     if ((this.j != null) && (this.j.isShowing()))
/* 492 */       this.j.dismiss();
/*     */   }
/*     */ 
/*     */   void a(String paramString)
/*     */   {
/* 497 */     String str = this.k.getText().toString().trim();
/* 498 */     if (!TextUtils.isEmpty(str))
/*     */     {
/*     */       Object localObject;
/* 499 */       if (str.length() == paramString.length()) {
/* 500 */         localObject = this.k.getLayoutParams();
/* 501 */         if (localObject != null) {
/* 502 */           LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.k.getWidth(), this.k.getHeight());
/*     */ 
/* 504 */           this.k.setLayoutParams(localLayoutParams);
/*     */         }
/*     */       } else {
/* 507 */         localObject = new LinearLayout.LayoutParams(-2, -2);
/*     */ 
/* 510 */         this.k.setLayoutParams((ViewGroup.LayoutParams)localObject);
/*     */       }
/*     */     }
/* 513 */     if (TextUtils.isEmpty(paramString)) {
/* 514 */       this.l.setVisibility(8);
/* 515 */       this.k.setVisibility(8);
/*     */     } else {
/* 517 */       this.k.setText(paramString);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onDismiss()
/*     */   {
/* 524 */     JSUtil.execCallback(this.f, this.h, null, JSUtil.OK, false, false);
/*     */ 
/* 526 */     this.j = null;
/* 527 */     if ((!this.t) || ((!TextUtils.isEmpty(this.i)) && (!"close".equalsIgnoreCase(this.i))))
/*     */     {
/* 531 */       this.g.unregisterSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
/*     */     }
/*     */ 
/* 534 */     if ((this.I != null) && (!this.I.isRecycled())) {
/* 535 */       this.I.recycle();
/* 536 */       System.gc();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean onExecute(ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*     */   {
/* 542 */     if (paramSysEventType == ISysEventListener.SysEventType.onKeyUp) {
/* 543 */       Object[] arrayOfObject = (Object[])paramObject;
/* 544 */       int i1 = ((Integer)arrayOfObject[0]).intValue();
/* 545 */       if (i1 == 4) {
/* 546 */         if ("none".equalsIgnoreCase(this.i))
/* 547 */           return true;
/* 548 */         if ("transmit".equalsIgnoreCase(this.i)) {
/* 549 */           return false;
/*     */         }
/* 551 */         a();
/* 552 */         b();
/* 553 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 557 */     return false;
/*     */   }
/*     */ 
/*     */   public void b() {
/* 561 */     this.e.a(this.b);
/* 562 */     this.g.unregisterSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
/*     */   }
/*     */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.doctor.hd\app\libs\nativeui.jar
 * Qualified Name:     io.dcloud.feature.ui.nativeui.b
 * JD-Core Version:    0.6.2
 */