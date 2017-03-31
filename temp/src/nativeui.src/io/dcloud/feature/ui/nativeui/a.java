/*     */ package io.dcloud.feature.ui.nativeui;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.Dialog;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.DialogInterface.OnCancelListener;
/*     */ import android.content.res.Resources;
/*     */ import android.graphics.Color;
/*     */ import android.graphics.drawable.ColorDrawable;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.text.TextPaint;
/*     */ import android.util.TypedValue;
/*     */ import android.view.View;
/*     */ import android.view.View.OnClickListener;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.view.Window;
/*     */ import android.view.animation.AlphaAnimation;
/*     */ import android.view.animation.Animation;
/*     */ import android.view.animation.TranslateAnimation;
/*     */ import android.view.inputmethod.InputMethodManager;
/*     */ import android.widget.FrameLayout;
/*     */ import android.widget.FrameLayout.LayoutParams;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.LinearLayout.LayoutParams;
/*     */ import android.widget.ScrollView;
/*     */ import android.widget.TextView;
/*     */ import io.dcloud.RInformation;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class a extends Dialog
/*     */   implements View.OnClickListener
/*     */ {
/*     */   private Context a;
/*     */   private b b;
/*     */   private View c;
/*     */   private LinearLayout d;
/*     */   private View e;
/*     */   private List<a> f;
/*  56 */   private String g = "";
/*  57 */   private String h = "";
/*     */   private boolean i;
/*  59 */   private boolean j = true;
/*  60 */   private boolean k = true;
/*     */   private IWebview l;
/*     */ 
/*     */   public a(Context paramContext, IWebview paramIWebview, int paramInt)
/*     */   {
/*  64 */     super(paramContext, paramInt);
/*  65 */     this.a = paramContext;
/*  66 */     this.l = paramIWebview;
/*  67 */     a();
/*  68 */     getWindow().setGravity(80);
/*  69 */     ColorDrawable localColorDrawable = new ColorDrawable();
/*  70 */     localColorDrawable.setAlpha(0);
/*  71 */     getWindow().setBackgroundDrawable(localColorDrawable);
/*  72 */     setOnCancelListener(new DialogInterface.OnCancelListener()
/*     */     {
/*     */       public void onCancel(DialogInterface paramAnonymousDialogInterface)
/*     */       {
/*  77 */         a.this.d();
/*  78 */         if (a.a(a.this) != null)
/*  79 */           a.a(a.this).a(-1);
/*  80 */         a.a(a.this, false);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/*  87 */     InputMethodManager localInputMethodManager = (InputMethodManager)this.a.getSystemService("input_method");
/*  88 */     if (localInputMethodManager.isActive()) {
/*  89 */       View localView = ((Activity)this.a).getCurrentFocus();
/*  90 */       if (localView != null)
/*  91 */         localInputMethodManager.hideSoftInputFromWindow(localView.getWindowToken(), 0);
/*     */     }
/*  93 */     this.c = i();
/*  94 */     this.e.startAnimation(f());
/*  95 */     this.d.startAnimation(e());
/*     */   }
/*     */ 
/*     */   private Animation e() {
/*  99 */     int m = 1;
/* 100 */     TranslateAnimation localTranslateAnimation = new TranslateAnimation(m, 0.0F, m, 0.0F, m, 1.0F, m, 0.0F);
/* 101 */     localTranslateAnimation.setDuration(300L);
/* 102 */     return localTranslateAnimation;
/*     */   }
/*     */ 
/*     */   private Animation f() {
/* 106 */     AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
/* 107 */     localAlphaAnimation.setDuration(300L);
/* 108 */     return localAlphaAnimation;
/*     */   }
/*     */ 
/*     */   private Animation g() {
/* 112 */     int m = 1;
/* 113 */     TranslateAnimation localTranslateAnimation = new TranslateAnimation(m, 0.0F, m, 0.0F, m, 0.0F, m, 1.0F);
/* 114 */     localTranslateAnimation.setDuration(300L);
/* 115 */     localTranslateAnimation.setFillAfter(true);
/* 116 */     return localTranslateAnimation;
/*     */   }
/*     */ 
/*     */   private Animation h() {
/* 120 */     AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
/* 121 */     localAlphaAnimation.setDuration(300L);
/* 122 */     localAlphaAnimation.setFillAfter(true);
/* 123 */     return localAlphaAnimation;
/*     */   }
/*     */ 
/*     */   private View i()
/*     */   {
/* 130 */     FrameLayout localFrameLayout = new FrameLayout(this.a);
/* 131 */     FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-1, -1);
/* 132 */     localLayoutParams1.gravity = 80;
/* 133 */     localFrameLayout.setLayoutParams(localLayoutParams1);
/* 134 */     this.e = new View(this.a);
/* 135 */     this.e.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
/* 136 */     this.e.setBackgroundColor(Color.argb(136, 0, 0, 0));
/* 137 */     this.e.setId(10);
/* 138 */     this.e.setOnClickListener(this);
/*     */ 
/* 140 */     this.d = new LinearLayout(this.a);
/* 141 */     FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-1, -2);
/* 142 */     localLayoutParams2.gravity = 80;
/* 143 */     this.d.setLayoutParams(localLayoutParams2);
/* 144 */     this.d.setOrientation(1);
/* 145 */     localFrameLayout.addView(this.e);
/* 146 */     localFrameLayout.addView(this.d);
/* 147 */     return localFrameLayout;
/*     */   }
/*     */ 
/*     */   private void j()
/*     */   {
/* 155 */     int m = k().getIntrinsicHeight();
/*     */ 
/* 157 */     int n = m * this.f.size();
/* 158 */     if (this.g != null) {
/* 159 */       n += m;
/*     */     }
/*     */ 
/* 162 */     if (this.h != null) {
/* 163 */       n += m;
/*     */     }
/*     */ 
/* 168 */     boolean bool = false;
/* 169 */     if (this.g != null) {
/* 170 */       localObject1 = new TextView(this.a);
/* 171 */       ((TextView)localObject1).setGravity(17);
/* 172 */       ((TextView)localObject1).setId(200);
/* 173 */       ((TextView)localObject1).setOnClickListener(this);
/* 174 */       ((TextView)localObject1).setBackgroundDrawable(k());
/* 175 */       ((TextView)localObject1).setText(this.g);
/* 176 */       ((TextView)localObject1).setTextColor(Color.parseColor("#8C8C8C"));
/* 177 */       ((TextView)localObject1).setTextSize(2, 16.0F);
/* 178 */       localObject2 = b();
/* 179 */       ((LinearLayout.LayoutParams)localObject2).topMargin = 0;
/* 180 */       this.d.addView((View)localObject1, (ViewGroup.LayoutParams)localObject2);
/* 181 */       bool = true;
/*     */     }
/*     */ 
/* 184 */     Object localObject1 = null;
/* 185 */     Object localObject2 = null;
/*     */     Object localObject3;
/* 186 */     if (n > this.l.obtainApp().getInt(1)) {
/* 187 */       localObject1 = new ScrollView(this.a);
/* 188 */       LinearLayout localLinearLayout = new LinearLayout(this.a);
/* 189 */       localLinearLayout.setOrientation(1);
/* 190 */       localObject3 = new LinearLayout.LayoutParams(-1, -2);
/* 191 */       ((ScrollView)localObject1).addView(localLinearLayout, (ViewGroup.LayoutParams)localObject3);
/* 192 */       localObject2 = localLinearLayout;
/*     */     } else {
/* 194 */       localObject2 = this.d;
/*     */     }
/*     */     int i3;
/* 198 */     if ((this.f != null) && (this.f.size() > 0)) {
/* 199 */       for (int i1 = 0; i1 < this.f.size(); i1++) {
/* 200 */         localObject3 = new TextView(this.a);
/* 201 */         ((TextView)localObject3).setGravity(17);
/* 202 */         ((TextView)localObject3).setId(100 + i1 + 1);
/* 203 */         ((TextView)localObject3).setOnClickListener(this);
/* 204 */         i3 = i1;
/* 205 */         ((TextView)localObject3).setBackgroundDrawable(a(this.f.size(), i3, bool));
/* 206 */         ((TextView)localObject3).setText(((a)this.f.get(i1)).a);
/* 207 */         if (((a)this.f.get(i1)).b.equals("destructive"))
/* 208 */           ((TextView)localObject3).setTextColor(Color.parseColor("#E8484A"));
/*     */         else
/* 210 */           ((TextView)localObject3).setTextColor(Color.parseColor("#1E82FF"));
/* 211 */         ((TextView)localObject3).setTextSize(2, 16.0F);
/* 212 */         if (i1 > 0) {
/* 213 */           LinearLayout.LayoutParams localLayoutParams = b();
/* 214 */           localLayoutParams.topMargin = 0;
/* 215 */           ((LinearLayout)localObject2).addView((View)localObject3, localLayoutParams);
/*     */         } else {
/* 217 */           ((LinearLayout)localObject2).addView((View)localObject3);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 222 */     if (this.h != null)
/*     */     {
/* 224 */       TextView localTextView = new TextView(this.a);
/* 225 */       localTextView.setGravity(17);
/* 226 */       localTextView.getPaint().setFakeBoldText(true);
/* 227 */       localTextView.setTextSize(2, 16.0F);
/* 228 */       localTextView.setId(100);
/* 229 */       localTextView.setBackgroundDrawable(this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_CANCEL_BT));
/* 230 */       localTextView.setText(this.h);
/* 231 */       localTextView.setTextColor(Color.parseColor("#1E82FF"));
/* 232 */       localTextView.setOnClickListener(this);
/* 233 */       localObject3 = b();
/* 234 */       ((LinearLayout.LayoutParams)localObject3).topMargin = a(10);
/*     */ 
/* 236 */       if (n > this.l.obtainApp().getInt(1))
/*     */       {
/* 239 */         i3 = this.l.obtainApp().getInt(1);
/* 240 */         if (this.g != null)
/*     */         {
/* 242 */           i3 -= k().getIntrinsicHeight();
/*     */         }
/*     */ 
/* 245 */         if (this.h != null)
/*     */         {
/* 247 */           i3 -= this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_CANCEL_BT).getIntrinsicHeight() + a(10) * 3;
/*     */         }
/* 249 */         this.d.addView((View)localObject1, new LinearLayout.LayoutParams(-1, i3));
/*     */       }
/*     */ 
/* 252 */       this.d.addView(localTextView, (ViewGroup.LayoutParams)localObject3);
/*     */     }
/* 256 */     else if (n > this.l.obtainApp().getInt(1)) {
/* 257 */       this.d.addView((View)localObject1, new LinearLayout.LayoutParams(-1, -1));
/*     */     }
/*     */ 
/* 261 */     int i2 = a(10);
/* 262 */     this.d.setBackgroundDrawable(new ColorDrawable(0));
/* 263 */     this.d.setPadding(i2, i2, i2, i2);
/*     */   }
/*     */ 
/*     */   private int a(int paramInt)
/*     */   {
/* 268 */     return (int)TypedValue.applyDimension(1, paramInt, this.a.getResources().getDisplayMetrics());
/*     */   }
/*     */ 
/*     */   public LinearLayout.LayoutParams b() {
/* 272 */     LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
/* 273 */     return localLayoutParams;
/*     */   }
/*     */ 
/*     */   private Drawable k() {
/* 277 */     return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_TOP);
/*     */   }
/*     */ 
/*     */   private Drawable a(int paramInt1, int paramInt2, boolean paramBoolean)
/*     */   {
/* 289 */     if (paramInt1 == 1) {
/* 290 */       if (paramBoolean) {
/* 291 */         return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_BOTTOM);
/*     */       }
/* 293 */       return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_SINGLE);
/* 294 */     }if (paramInt1 == 2) {
/* 295 */       switch (paramInt2) {
/*     */       case 0:
/* 297 */         if (paramBoolean) {
/* 298 */           return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_MIDDLE);
/*     */         }
/* 300 */         return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_TOP);
/*     */       case 1:
/* 304 */         return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_BOTTOM);
/*     */       }
/* 306 */     } else if (paramInt1 > 2) {
/* 307 */       if (paramInt2 == 0) {
/* 308 */         if (paramBoolean) {
/* 309 */           return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_MIDDLE);
/*     */         }
/* 311 */         return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_TOP);
/*     */       }
/* 313 */       if (paramInt2 == paramInt1 - 1)
/* 314 */         return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_BOTTOM);
/* 315 */       return this.a.getResources().getDrawable(RInformation.SLT_AS_IOS7_OTHER_BT_MIDDLE);
/*     */     }
/* 317 */     return null;
/*     */   }
/*     */ 
/*     */   public void c() {
/* 321 */     if (!this.j)
/* 322 */       return;
/* 323 */     show();
/* 324 */     getWindow().setContentView(this.c);
/* 325 */     this.j = false;
/*     */   }
/*     */ 
/*     */   public void d()
/*     */   {
/* 332 */     if (this.j)
/* 333 */       return;
/* 334 */     dismiss();
/* 335 */     l();
/* 336 */     this.j = true;
/*     */   }
/*     */ 
/*     */   private void l()
/*     */   {
/* 343 */     this.d.startAnimation(g());
/* 344 */     this.e.startAnimation(h());
/*     */   }
/*     */ 
/*     */   public a a(String paramString)
/*     */   {
/* 354 */     this.h = paramString;
/* 355 */     return this;
/*     */   }
/*     */ 
/*     */   public a b(String paramString)
/*     */   {
/* 365 */     this.g = paramString;
/* 366 */     return this;
/*     */   }
/*     */ 
/*     */   public a a(boolean paramBoolean)
/*     */   {
/* 386 */     this.i = paramBoolean;
/* 387 */     return this;
/*     */   }
/*     */ 
/*     */   public a a(JSONArray paramJSONArray) {
/* 391 */     if ((paramJSONArray == null) || (paramJSONArray.length() == 0)) {
/* 392 */       return this;
/*     */     }
/* 394 */     this.f = new ArrayList();
/* 395 */     int m = paramJSONArray.length();
/* 396 */     for (int n = 0; n < m; n++) {
/* 397 */       JSONObject localJSONObject = null;
/* 398 */       a locala = null;
/*     */       try {
/* 400 */         localJSONObject = paramJSONArray.getJSONObject(n);
/* 401 */         if (localJSONObject != null) {
/* 402 */           String str1 = localJSONObject.optString("title");
/* 403 */           String str2 = localJSONObject.optString("style");
/* 404 */           locala = new a(str1, str2);
/* 405 */           if (locala != null)
/* 406 */             this.f.add(locala);
/*     */         }
/*     */       }
/*     */       catch (JSONException localJSONException)
/*     */       {
/* 411 */         localJSONException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 415 */     j();
/* 416 */     return this;
/*     */   }
/*     */ 
/*     */   public a a(b paramb) {
/* 420 */     this.b = paramb;
/* 421 */     return this;
/*     */   }
/*     */ 
/*     */   public void onClick(View paramView)
/*     */   {
/* 428 */     if (((paramView.getId() == 10) && (!this.i)) || (paramView.getId() == 200)) {
/* 429 */       return;
/*     */     }
/* 431 */     d();
/* 432 */     if (paramView.getId() != 10) {
/* 433 */       if (this.b != null)
/* 434 */         this.b.a(paramView.getId() - 100);
/* 435 */       this.k = false;
/*     */     } else {
/* 437 */       if (this.b != null)
/* 438 */         this.b.a(-1);
/* 439 */       this.k = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract interface b
/*     */   {
/*     */     public abstract void a(int paramInt);
/*     */   }
/*     */ 
/*     */   public class a
/*     */   {
/*     */     public String a;
/*     */     public String b;
/*     */ 
/*     */     public a(String paramString1, String arg3)
/*     */     {
/*     */       Object localObject;
/* 448 */       if (localObject == null)
/* 449 */         this.b = "normal";
/*     */       else
/* 451 */         this.b = localObject;
/* 452 */       this.a = paramString1;
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.doctor.hd\app\libs\nativeui.jar
 * Qualified Name:     io.dcloud.feature.ui.nativeui.a
 * JD-Core Version:    0.6.2
 */