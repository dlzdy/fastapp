/*     */ package io.dcloud.feature.ui.nativeui;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.AlertDialog;
/*     */ import android.app.AlertDialog.Builder;
/*     */ import android.app.DatePickerDialog;
/*     */ import android.app.DatePickerDialog.OnDateSetListener;
/*     */ import android.app.TimePickerDialog;
/*     */ import android.app.TimePickerDialog.OnTimeSetListener;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.DialogInterface.OnClickListener;
/*     */ import android.content.DialogInterface.OnDismissListener;
/*     */ import android.content.DialogInterface.OnKeyListener;
/*     */ import android.graphics.Bitmap;
/*     */ import android.graphics.BitmapFactory;
/*     */ import android.graphics.BitmapFactory.Options;
/*     */ import android.graphics.Color;
/*     */ import android.os.Build;
/*     */ import android.os.Bundle;
/*     */ import android.text.Editable;
/*     */ import android.text.Selection;
/*     */ import android.text.Spannable;
/*     */ import android.text.TextUtils;
/*     */ import android.util.Log;
/*     */ import android.view.KeyEvent;
/*     */ import android.view.View;
/*     */ import android.view.Window;
/*     */ import android.widget.DatePicker;
/*     */ import android.widget.EditText;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.LinearLayout.LayoutParams;
/*     */ import android.widget.TextView;
/*     */ import android.widget.TimePicker;
/*     */ import android.widget.Toast;
/*     */ import io.dcloud.RInformation;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFeature;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.adapter.util.CanvasHelper;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.constant.DOMException;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.DialogUtil;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.RuningAcitvityUtil;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.TimeZone;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class NativeUIFeatureImpl
/*     */   implements IFeature
/*     */ {
/*  79 */   HashMap<String, b> a = null;
/*     */ 
/* 480 */   DatePickerDialog b = null;
/*     */   int c;
/*     */   int d;
/*     */   int e;
/* 490 */   TimePickerDialog f = null;
/*     */   int g;
/*     */   int h;
/* 657 */   final byte i = 0;
/* 658 */   final byte j = 1;
/* 659 */   final byte k = 2;
/*     */ 
/*     */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*     */   {
/*  85 */     String str1 = null;
/*  86 */     IApp localIApp = paramIWebview.obtainApp();
/*  87 */     String str2 = null;
/*  88 */     if (paramArrayOfString.length >= 1) {
/*  89 */       str2 = paramArrayOfString[0];
/*     */     }
/*  91 */     JSONArray localJSONArray = null;
/*  92 */     if (paramArrayOfString.length >= 2) {
/*  93 */       localJSONArray = JSONUtil.createJSONArray(paramArrayOfString[1]);
/*     */     }
/*  95 */     if (paramString.equals("pickDate")) {
/*  96 */       a(paramIWebview, JSONUtil.getString(localJSONArray, 0), JSONUtil.getJSONObject(localJSONArray, 1));
/*     */     }
/*  99 */     else if (paramString.equals("pickTime")) {
/* 100 */       b(paramIWebview, JSONUtil.getString(localJSONArray, 0), JSONUtil.getJSONObject(localJSONArray, 1));
/*     */     }
/* 103 */     else if (paramString.equals("actionSheet")) {
/* 104 */       a(JSONUtil.getJSONObject(localJSONArray, 0), JSONUtil.getString(localJSONArray, 1), paramIWebview);
/*     */     }
/*     */     else
/*     */     {
/*     */       Object localObject1;
/*     */       Object localObject2;
/*     */       String str3;
/*     */       Object localObject3;
/* 107 */       if (paramString.equals("alert")) {
/* 108 */         localObject1 = JSONUtil.getString(localJSONArray, 0);
/* 109 */         localObject2 = JSONUtil.getString(localJSONArray, 1);
/* 110 */         str3 = JSONUtil.getString(localJSONArray, 2);
/* 111 */         localObject3 = JSONUtil.getString(localJSONArray, 3);
/* 112 */         a((byte)0, str3, null, (String)localObject1, new String[] { localObject3 }, null, paramIWebview, (String)localObject2);
/*     */       }
/*     */       else
/*     */       {
/*     */         Object localObject4;
/* 115 */         if (paramString.equals("confirm")) {
/* 116 */           localObject1 = JSONUtil.getString(localJSONArray, 0);
/* 117 */           localObject2 = JSONUtil.getString(localJSONArray, 1);
/* 118 */           str3 = JSONUtil.getString(localJSONArray, 2);
/* 119 */           localObject3 = JSONUtil.getJSONArray(localJSONArray, 3);
/* 120 */           localObject4 = null;
/* 121 */           if (localObject3 != null) {
/* 122 */             localObject4 = new String[((JSONArray)localObject3).length()];
/* 123 */             for (int n = 0; n < localObject4.length; n++) {
/* 124 */               localObject4[n] = JSONUtil.getString((JSONArray)localObject3, n);
/*     */             }
/*     */           }
/* 127 */           a((byte)1, str3, null, (String)localObject1, (String[])localObject4, null, paramIWebview, (String)localObject2);
/*     */         }
/* 129 */         else if (paramString.equals("prompt")) {
/* 130 */           localObject1 = JSONUtil.getString(localJSONArray, 0);
/* 131 */           localObject2 = JSONUtil.getString(localJSONArray, 1);
/* 132 */           str3 = JSONUtil.getString(localJSONArray, 2);
/* 133 */           localObject3 = JSONUtil.getString(localJSONArray, 3);
/* 134 */           localObject4 = JSONUtil.getJSONArray(localJSONArray, 4);
/* 135 */           String[] arrayOfString = null;
/* 136 */           if (localObject4 != null) {
/* 137 */             arrayOfString = new String[((JSONArray)localObject4).length()];
/* 138 */             for (int i1 = 0; i1 < arrayOfString.length; i1++) {
/* 139 */               arrayOfString[i1] = JSONUtil.getString((JSONArray)localObject4, i1);
/*     */             }
/*     */           }
/* 142 */           a((byte)2, str3, null, (String)localObject1, arrayOfString, (String)localObject3, paramIWebview, (String)localObject2);
/*     */         }
/* 145 */         else if (paramString.equals("toast")) {
/* 146 */           localObject1 = JSONUtil.getString(localJSONArray, 0);
/* 147 */           localObject2 = JSONUtil.getJSONObject(localJSONArray, 1);
/* 148 */           a(localIApp, paramIWebview, (String)localObject1, (JSONObject)localObject2);
/* 149 */         } else if (paramString.equals("WaitingView")) {
/* 150 */           localObject1 = JSONUtil.getString(localJSONArray, 0);
/* 151 */           localObject2 = JSONUtil.getJSONObject(localJSONArray, 1);
/* 152 */           str3 = JSONUtil.getString(localJSONArray, 2);
/* 153 */           localObject3 = new b(this, paramIWebview, (String)localObject1, (JSONObject)localObject2, str3);
/*     */ 
/* 155 */           if (this.a == null) {
/* 156 */             this.a = new HashMap();
/*     */           }
/* 158 */           ((b)localObject3).b = str2;
/* 159 */           this.a.put(str2, localObject3);
/*     */         }
/* 161 */         else if (paramString.equals("closeWaiting")) {
/* 162 */           if (this.a != null) {
/* 163 */             for (localObject1 = this.a.values().iterator(); ((Iterator)localObject1).hasNext(); ) { localObject2 = (b)((Iterator)localObject1).next();
/* 164 */               ((b)localObject2).a();
/*     */             }
/* 166 */             this.a.clear();
/*     */           }
/* 168 */         } else if (paramString.equals("WaitingView_close")) {
/* 169 */           if (this.a != null) {
/* 170 */             localObject1 = (b)this.a.remove(str2);
/* 171 */             if (localObject1 != null) {
/* 172 */               ((b)localObject1).a();
/*     */             }
/*     */           }
/*     */         }
/* 176 */         else if (paramString.equals("WaitingView_setTitle")) {
/* 177 */           if (this.a != null) {
/* 178 */             localObject1 = (b)this.a.get(str2);
/* 179 */             if (localObject1 != null) {
/* 180 */               localObject2 = JSONUtil.getString(localJSONArray, 0);
/* 181 */               ((b)localObject1).a((String)localObject2);
/*     */             }
/*     */           }
/* 184 */         } else if (paramString.equals("showMenu")) {
/* 185 */           PlatformUtil.invokeMethod("io.src.dcloud.adapter.DCloudAdapterUtil", "showMenu", null, new Class[] { Activity.class, String.class, String.class, String.class, IWebview.class, String.class }, new Object[] { paramIWebview.getActivity(), paramIWebview.obtainApp().obtainAppId(), JSONUtil.getString(localJSONArray, 0), JSONUtil.getString(localJSONArray, 1), paramIWebview, JSONUtil.getString(localJSONArray, 2) });
/*     */         }
/* 189 */         else if (paramString.equals("hideMenu")) {
/* 190 */           PlatformUtil.invokeMethod("io.src.dcloud.adapter.DCloudAdapterUtil", "hideMenu", null, new Class[] { Activity.class, String.class }, new Object[] { paramIWebview.getActivity(), paramIWebview.obtainApp().obtainAppId() });
/*     */         }
/*     */         else
/*     */         {
/*     */           boolean bool2;
/* 192 */           if (paramString.equals("isTitlebarVisible")) {
/* 193 */             localObject1 = PlatformUtil.invokeMethod("io.src.dcloud.adapter.DCloudAdapterUtil", "isTitlebarVisible", null, new Class[] { Activity.class, String.class }, new Object[] { paramIWebview.getActivity(), paramIWebview.obtainApp().obtainAppId() });
/*     */ 
/* 195 */             bool2 = false;
/* 196 */             if ((localObject1 instanceof Boolean)) {
/* 197 */               bool2 = Boolean.valueOf(localObject1.toString()).booleanValue();
/*     */             }
/* 199 */             return JSUtil.wrapJsVar(String.valueOf(bool2), false);
/* 200 */           }if (paramString.equals("getTitlebarHeight")) {
/* 201 */             localObject1 = PlatformUtil.invokeMethod("io.src.dcloud.adapter.DCloudAdapterUtil", "getTitlebarHeight", null, new Class[] { Activity.class }, new Object[] { paramIWebview.getActivity() });
/*     */ 
/* 203 */             bool2 = false;
/*     */             int m;
/* 204 */             if ((localObject1 instanceof Integer)) {
/* 205 */               m = Integer.valueOf(localObject1.toString()).intValue();
/*     */             }
/* 207 */             return JSUtil.wrapJsVar(String.valueOf(m), false);
/* 208 */           }if (paramString.equals("setTitlebarVisible")) {
/* 209 */             boolean bool1 = true;
/*     */             try {
/* 211 */               bool1 = localJSONArray.getBoolean(0);
/*     */             } catch (Exception localException) {
/* 213 */               localException.printStackTrace();
/*     */             }
/* 215 */             PlatformUtil.invokeMethod("io.src.dcloud.adapter.DCloudAdapterUtil", "setTitlebarVisible", null, new Class[] { Activity.class, String.class, Boolean.TYPE }, new Object[] { paramIWebview.getActivity(), paramIWebview.obtainApp().obtainAppId(), Boolean.valueOf(bool1) });
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 219 */     return str1;
/*     */   }
/*     */ 
/*     */   void a(String paramString) {
/* 223 */     this.a.remove(paramString);
/*     */   }
/*     */ 
/*     */   private void a(IApp paramIApp, IWebview paramIWebview, String paramString, JSONObject paramJSONObject)
/*     */   {
/* 228 */     int m = 0;
/* 229 */     int n = 1;
/* 230 */     int i1 = 80;
/* 231 */     String str = "block";
/* 232 */     Bitmap localBitmap = null;
/*     */     Object localObject3;
/* 233 */     if (paramJSONObject != null)
/*     */     {
/* 235 */       if (!paramJSONObject.isNull("style")) {
/* 236 */         localObject1 = JSONUtil.getString(paramJSONObject, "style").toLowerCase();
/*     */ 
/* 238 */         if ("block".equals(localObject1))
/* 239 */           str = "block";
/*     */         else {
/* 241 */           str = "inline";
/*     */         }
/*     */       }
/* 244 */       if (!paramJSONObject.isNull("icon")) {
/* 245 */         localObject1 = JSONUtil.getString(paramJSONObject, "icon");
/*     */ 
/* 247 */         localObject2 = paramIApp.convert2AbsFullPath(paramIWebview.obtainFullUrl(), (String)localObject1);
/*     */ 
/* 249 */         localObject3 = new BitmapFactory.Options();
/* 250 */         ((BitmapFactory.Options)localObject3).inScaled = false;
/* 251 */         localBitmap = BitmapFactory.decodeFile((String)localObject2, (BitmapFactory.Options)localObject3);
/*     */       }
/* 253 */       if (!paramJSONObject.isNull("duration")) {
/* 254 */         localObject1 = JSONUtil.getString(paramJSONObject, "duration").toLowerCase();
/*     */ 
/* 256 */         if ("long".equals(localObject1)) {
/* 257 */           m = 1;
/*     */         }
/*     */       }
/* 260 */       if (!paramJSONObject.isNull("align")) {
/* 261 */         localObject1 = JSONUtil.getString(paramJSONObject, "align");
/*     */ 
/* 263 */         if ("left".equals(localObject1))
/* 264 */           n = 3;
/* 265 */         else if ("right".equals(localObject1))
/* 266 */           n = 5;
/*     */         else {
/* 268 */           n = 1;
/*     */         }
/*     */       }
/* 271 */       if (!paramJSONObject.isNull("verticalAlign")) {
/* 272 */         localObject1 = JSONUtil.getString(paramJSONObject, "verticalAlign");
/*     */ 
/* 274 */         if ("top".equals(localObject1))
/* 275 */           i1 = 48;
/* 276 */         else if ("bottom".equals(localObject1))
/* 277 */           i1 = 80;
/*     */         else {
/* 279 */           i1 = 16;
/*     */         }
/*     */       }
/*     */     }
/* 283 */     Object localObject1 = null;
/* 284 */     Object localObject2 = RuningAcitvityUtil.getTopRuningActivity(paramIWebview.getActivity());
/*     */ 
/* 286 */     if (localBitmap != null) {
/* 287 */       localObject1 = new Toast((Context)localObject2);
/*     */ 
/* 289 */       localObject3 = new LinearLayout((Context)localObject2);
/* 290 */       ((LinearLayout)localObject3).setBackgroundResource(17301654);
/* 291 */       int i2 = CanvasHelper.dip2px((Context)localObject2, 16.0F);
/* 292 */       int i3 = CanvasHelper.dip2px((Context)localObject2, 8.0F);
/* 293 */       ((LinearLayout)localObject3).setPadding(i2, i3, i2, i3);
/* 294 */       ((LinearLayout)localObject3).setGravity(17);
/*     */ 
/* 298 */       ImageView localImageView = new ImageView((Context)localObject2);
/* 299 */       LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -2);
/* 300 */       localLayoutParams1.gravity = 17;
/*     */ 
/* 302 */       int i4 = CanvasHelper.dip2px((Context)localObject2, 7.0F);
/* 303 */       if (str.equals("inline")) {
/* 304 */         ((LinearLayout)localObject3).setOrientation(0);
/* 305 */         localLayoutParams1.setMargins(i4, i4, i4, i4);
/*     */       } else {
/* 307 */         int i5 = CanvasHelper.dip2px((Context)localObject2, 1.0F);
/* 308 */         ((LinearLayout)localObject3).setOrientation(1);
/* 309 */         localLayoutParams1.setMargins(i4, i4, i4, i5);
/*     */       }
/* 311 */       localImageView.setLayoutParams(localLayoutParams1);
/* 312 */       localImageView.setImageBitmap(localBitmap);
/* 313 */       ((LinearLayout)localObject3).addView(localImageView);
/*     */ 
/* 317 */       LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
/* 318 */       localLayoutParams2.gravity = 17;
/* 319 */       int i6 = CanvasHelper.dip2px((Context)localObject2, 5.0F);
/* 320 */       localLayoutParams2.setMargins(i6, i6, i6, i6);
/*     */ 
/* 323 */       TextView localTextView = new TextView((Context)localObject2);
/* 324 */       localTextView.setText(paramString);
/* 325 */       localTextView.setTextColor(Color.parseColor("#ffffffff"));
/* 326 */       localTextView.setLayoutParams(localLayoutParams2);
/* 327 */       ((LinearLayout)localObject3).addView(localTextView);
/*     */ 
/* 329 */       ((Toast)localObject1).setView((View)localObject3);
/* 330 */       ((Toast)localObject1).setGravity(n | i1, ((Toast)localObject1).getXOffset(), ((Toast)localObject1).getYOffset());
/*     */ 
/* 332 */       ((Toast)localObject1).setDuration(m);
/*     */     }
/*     */     else {
/* 335 */       localObject1 = Toast.makeText((Context)localObject2, paramString, m);
/* 336 */       ((Toast)localObject1).setGravity(n | i1, ((Toast)localObject1).getXOffset(), ((Toast)localObject1).getYOffset());
/*     */     }
/*     */ 
/* 339 */     ((Toast)localObject1).show();
/*     */   }
/*     */ 
/*     */   private void a(final IWebview paramIWebview, final String paramString, JSONObject paramJSONObject)
/*     */   {
/* 344 */     if (this.b != null) {
/* 345 */       JSUtil.execCallback(paramIWebview, paramString, DOMException.toJSON(5, ""), JSUtil.ERROR, true, false);
/*     */ 
/* 348 */       return;
/*     */     }
/* 350 */     Activity localActivity = RuningAcitvityUtil.getTopRuningActivity(paramIWebview.getActivity());
/* 351 */     int m = JSONUtil.getInt(paramJSONObject, "startYear");
/* 352 */     int n = JSONUtil.getInt(paramJSONObject, "startMonth");
/* 353 */     int i1 = JSONUtil.getInt(paramJSONObject, "startDay");
/*     */ 
/* 355 */     int i2 = JSONUtil.getInt(paramJSONObject, "endYear");
/* 356 */     int i3 = JSONUtil.getInt(paramJSONObject, "endMonth");
/* 357 */     int i4 = JSONUtil.getInt(paramJSONObject, "endDay");
/*     */ 
/* 359 */     GregorianCalendar localGregorianCalendar1 = JSONUtil.getInt(paramJSONObject, "setYear");
/* 360 */     int i5 = JSONUtil.getInt(paramJSONObject, "setMonth");
/* 361 */     int i6 = JSONUtil.getInt(paramJSONObject, "setDay");
/* 362 */     if (localGregorianCalendar1 == 0) {
/* 363 */       localGregorianCalendar2 = new GregorianCalendar();
/* 364 */       localGregorianCalendar2.setTime(new Date());
/* 365 */       localGregorianCalendar1 = localGregorianCalendar2.get(1);
/* 366 */       i5 = localGregorianCalendar2.get(2);
/* 367 */       i6 = localGregorianCalendar2.get(5);
/*     */     }
/* 369 */     final GregorianCalendar localGregorianCalendar2 = localGregorianCalendar1; final int i7 = i5; final int i8 = i6;
/* 370 */     final boolean bool = !paramJSONObject.isNull("title");
/*     */ 
/* 372 */     GregorianCalendar localGregorianCalendar3 = new GregorianCalendar();
/* 373 */     DatePickerDialog.OnDateSetListener local1 = new DatePickerDialog.OnDateSetListener()
/*     */     {
/*     */       public void onDateSet(DatePicker paramAnonymousDatePicker, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
/*     */       {
/* 377 */         NativeUIFeatureImpl.this.c = paramAnonymousInt1;
/* 378 */         NativeUIFeatureImpl.this.d = paramAnonymousInt2;
/* 379 */         NativeUIFeatureImpl.this.e = paramAnonymousInt3;
/*     */       }
/*     */     };
/* 383 */     int i9 = 0;
/*     */ 
/* 385 */     DatePickerDialog local3 = new DatePickerDialog(localActivity, i9, local1, localGregorianCalendar2, i7, i8)
/*     */     {
/* 397 */       int a = localGregorianCalendar2; int b = i7; int c = i8;
/*     */ 
/*     */       protected void onCreate(Bundle paramAnonymousBundle)
/*     */       {
/* 391 */         super.onCreate(paramAnonymousBundle);
/*     */ 
/* 393 */         getWindow().setSoftInputMode(2);
/*     */       }
/*     */ 
/*     */       public void onDateChanged(DatePicker paramAnonymousDatePicker, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
/*     */       {
/* 403 */         this.a = paramAnonymousInt1;
/* 404 */         this.b = paramAnonymousInt2;
/* 405 */         this.c = paramAnonymousInt3;
/* 406 */         if ((!bool) && 
/* 407 */           (!DeviceInfo.sVersion_release.equals("4.0.3")) && (!DeviceInfo.sVersion_release.equals("4.0.4")))
/*     */         {
/* 411 */           super.onDateChanged(paramAnonymousDatePicker, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
/*     */         }
/*     */       }
/*     */ 
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*     */       {
/* 418 */         super.onClick(paramAnonymousDialogInterface, paramAnonymousInt);
/* 419 */         switch (paramAnonymousInt) {
/*     */         case -1:
/* 421 */           GregorianCalendar localGregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/*     */ 
/* 423 */           if (DeviceInfo.sDeviceSdkVer >= 21)
/* 424 */             localGregorianCalendar.set(NativeUIFeatureImpl.this.c, localGregorianCalendar2, i7, 0, 0, 0);
/*     */           else {
/* 426 */             localGregorianCalendar.set(this.a, this.b, this.c, 0, 0, 0);
/*     */           }
/*     */ 
/* 429 */           long l = localGregorianCalendar.getTime().getTime();
/* 430 */           JSUtil.execCallback(paramIWebview, paramString, String.valueOf(l), JSUtil.OK, true, false);
/*     */ 
/* 432 */           break;
/*     */         case -2:
/* 434 */           JSUtil.execCallback(paramIWebview, paramString, DOMException.toJSON(-2, "用户取消"), JSUtil.ERROR, true, false);
/*     */         }
/*     */ 
/* 440 */         NativeUIFeatureImpl.a(NativeUIFeatureImpl.this);
/*     */       }
/*     */     };
/* 443 */     if (DeviceInfo.sDeviceSdkVer >= 11) {
/* 444 */       if (m > 1900)
/*     */       {
/* 446 */         localGregorianCalendar3.set(1, m);
/* 447 */         localGregorianCalendar3.set(2, n);
/* 448 */         localGregorianCalendar3.set(5, i1);
/* 449 */         local3.getDatePicker().setMinDate(localGregorianCalendar3.getTimeInMillis());
/*     */       }
/*     */ 
/* 452 */       if ((i2 > 1900) && (i2 >= m)) {
/* 453 */         localGregorianCalendar3.set(1, i2);
/* 454 */         localGregorianCalendar3.set(2, i3);
/* 455 */         localGregorianCalendar3.set(5, i4);
/* 456 */         local3.getDatePicker().setMaxDate(localGregorianCalendar3.getTimeInMillis());
/*     */       }
/*     */     }
/* 459 */     local3.setOnDismissListener(new DialogInterface.OnDismissListener()
/*     */     {
/*     */       public void onDismiss(DialogInterface paramAnonymousDialogInterface) {
/* 462 */         if (NativeUIFeatureImpl.this.b != null) {
/* 463 */           JSUtil.execCallback(paramIWebview, paramString, DOMException.toJSON(-2, "用户取消"), JSUtil.ERROR, true, false);
/*     */ 
/* 467 */           NativeUIFeatureImpl.a(NativeUIFeatureImpl.this);
/*     */         }
/*     */       }
/*     */     });
/* 471 */     if (bool) {
/* 472 */       String str = JSONUtil.getString(paramJSONObject, "title");
/*     */ 
/* 474 */       local3.setTitle(str);
/*     */     }
/* 476 */     local3.show();
/* 477 */     this.b = local3;
/*     */   }
/*     */ 
/*     */   private void a()
/*     */   {
/* 484 */     this.b = null;
/* 485 */     this.c = 0;
/* 486 */     this.d = 0;
/* 487 */     this.e = 0;
/*     */   }
/*     */ 
/*     */   private void b()
/*     */   {
/* 494 */     this.f = null;
/* 495 */     this.g = 0;
/* 496 */     this.h = 0;
/*     */   }
/*     */ 
/*     */   private void b(final IWebview paramIWebview, final String paramString, JSONObject paramJSONObject)
/*     */   {
/* 501 */     if (this.f != null) {
/* 502 */       JSUtil.execCallback(paramIWebview, paramString, DOMException.toJSON(5, ""), JSUtil.ERROR, true, false);
/*     */ 
/* 505 */       return;
/*     */     }
/* 507 */     Activity localActivity = RuningAcitvityUtil.getTopRuningActivity(paramIWebview.getActivity());
/* 508 */     final boolean bool1 = !paramJSONObject.isNull("title");
/*     */ 
/* 510 */     boolean bool2 = PdrUtil.parseBoolean(JSONUtil.getString(paramJSONObject, "is24Hour"), true, false);
/*     */ 
/* 513 */     String str1 = JSONUtil.getString(paramJSONObject, "time");
/* 514 */     String str2 = JSONUtil.getString(paramJSONObject, "__minutes");
/* 515 */     String str3 = JSONUtil.getString(paramJSONObject, "__hours");
/* 516 */     GregorianCalendar localGregorianCalendar1 = 0;
/* 517 */     int m = 0;
/* 518 */     if (PdrUtil.isEmpty(str3)) {
/* 519 */       localGregorianCalendar2 = new GregorianCalendar();
/* 520 */       if ((PdrUtil.isEmpty(str1)) || 
/* 523 */         (bool2))
/* 524 */         m = localGregorianCalendar2.get(11);
/*     */       else {
/* 526 */         m = localGregorianCalendar2.get(10);
/*     */       }
/* 528 */       localGregorianCalendar1 = localGregorianCalendar2.get(12);
/*     */     } else {
/* 530 */       m = Integer.parseInt(str3);
/* 531 */       localGregorianCalendar1 = Integer.parseInt(str2);
/*     */     }
/* 533 */     final GregorianCalendar localGregorianCalendar2 = localGregorianCalendar1;
/* 534 */     final int n = m;
/* 535 */     TimePickerDialog.OnTimeSetListener local5 = new TimePickerDialog.OnTimeSetListener()
/*     */     {
/*     */       public void onTimeSet(TimePicker paramAnonymousTimePicker, int paramAnonymousInt1, int paramAnonymousInt2) {
/* 538 */         NativeUIFeatureImpl.this.g = paramAnonymousInt1;
/* 539 */         NativeUIFeatureImpl.this.h = paramAnonymousInt2;
/*     */       }
/*     */     };
/* 542 */     TimePickerDialog local6 = new TimePickerDialog(localActivity, local5, m, localGregorianCalendar1, bool2)
/*     */     {
/* 544 */       int a = n; int b = localGregorianCalendar2;
/*     */ 
/*     */       protected void onCreate(Bundle paramAnonymousBundle)
/*     */       {
/* 549 */         super.onCreate(paramAnonymousBundle);
/*     */ 
/* 551 */         getWindow().setSoftInputMode(2);
/*     */       }
/*     */ 
/*     */       public void onTimeChanged(TimePicker paramAnonymousTimePicker, int paramAnonymousInt1, int paramAnonymousInt2)
/*     */       {
/* 557 */         this.a = paramAnonymousInt1;
/* 558 */         this.b = paramAnonymousInt2;
/* 559 */         if (!bool1)
/* 560 */           setTitle(paramAnonymousInt1 + ":" + paramAnonymousInt2);
/*     */       }
/*     */ 
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
/*     */       {
/* 566 */         super.onClick(paramAnonymousDialogInterface, paramAnonymousInt);
/* 567 */         switch (paramAnonymousInt)
/*     */         {
/*     */         case -1:
/* 570 */           Date localDate = new Date();
/* 571 */           if (DeviceInfo.sDeviceSdkVer >= 21) {
/* 572 */             localDate.setHours(NativeUIFeatureImpl.this.g);
/* 573 */             localDate.setMinutes(NativeUIFeatureImpl.this.h);
/*     */           } else {
/* 575 */             localDate.setHours(this.a);
/* 576 */             localDate.setMinutes(this.b);
/*     */           }
/* 578 */           long l = localDate.getTime();
/* 579 */           JSUtil.execCallback(paramIWebview, paramString, String.valueOf(l), JSUtil.OK, true, false);
/*     */ 
/* 581 */           break;
/*     */         case -2:
/* 583 */           JSUtil.execCallback(paramIWebview, paramString, DOMException.toJSON(-2, "用户取消"), JSUtil.ERROR, true, false);
/*     */         }
/*     */ 
/* 589 */         NativeUIFeatureImpl.b(NativeUIFeatureImpl.this);
/*     */       }
/*     */     };
/* 592 */     if (bool1) {
/* 593 */       String str4 = JSONUtil.getString(paramJSONObject, "title");
/*     */ 
/* 595 */       local6.setTitle(str4);
/*     */     }
/* 597 */     local6.setOnDismissListener(new DialogInterface.OnDismissListener()
/*     */     {
/*     */       public void onDismiss(DialogInterface paramAnonymousDialogInterface) {
/* 600 */         if (NativeUIFeatureImpl.this.f != null) {
/* 601 */           JSUtil.execCallback(paramIWebview, paramString, DOMException.toJSON(-2, "用户取消"), JSUtil.ERROR, true, false);
/*     */ 
/* 605 */           NativeUIFeatureImpl.b(NativeUIFeatureImpl.this);
/*     */         }
/*     */       }
/*     */     });
/* 609 */     local6.show();
/* 610 */     this.f = local6;
/*     */   }
/*     */ 
/*     */   private void a(JSONObject paramJSONObject, final String paramString, final IWebview paramIWebview)
/*     */   {
/* 625 */     String str1 = paramJSONObject.has("title") ? paramJSONObject.optString("title") : null;
/* 626 */     String str2 = paramJSONObject.has("cancel") ? paramJSONObject.optString("cancel") : null;
/* 627 */     JSONArray localJSONArray = paramJSONObject.optJSONArray("buttons");
/*     */ 
/* 629 */     if (localJSONArray == null) {
/* 630 */       return;
/*     */     }
/* 632 */     boolean bool = a(localJSONArray, paramIWebview.obtainApp(), paramString, paramIWebview);
/* 633 */     if (bool) {
/* 634 */       return;
/*     */     }
/* 636 */     a locala = new a(paramIWebview, paramString);
/* 637 */     Activity localActivity = RuningAcitvityUtil.getTopRuningActivity(paramIWebview.getActivity());
/* 638 */     localActivity.setTheme(RInformation.ACTS_STYLE_ActionSheetStyleIOS7);
/*     */ 
/* 641 */     int m = 16973837;
/* 642 */     if (paramIWebview.obtainApp().isFullScreen()) {
/* 643 */       m = 16973838;
/*     */     }
/* 645 */     a locala1 = new a(localActivity, paramIWebview, m);
/* 646 */     if (locala1 != null) {
/* 647 */       locala1.a(str2);
/* 648 */       locala1.b(str1);
/* 649 */       locala1.a(localJSONArray);
/* 650 */       locala1.a(locala);
/* 651 */       locala1.a(true);
/* 652 */       locala1.c();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(final byte paramByte, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, String paramString4, final IWebview paramIWebview, final String paramString5)
/*     */   {
/* 664 */     Activity localActivity = RuningAcitvityUtil.getTopRuningActivity(paramIWebview.getActivity());
/*     */     Object localObject1;
/*     */     Object localObject2;
/* 665 */     if (paramByte == 0) {
/* 666 */       boolean bool1 = a(paramString3, paramIWebview.obtainApp(), paramIWebview, paramString5);
/* 667 */       if (bool1) {
/* 668 */         return;
/*     */       }
/* 670 */       localObject1 = DialogUtil.initDialogTheme(localActivity, !BaseInfo.isForQihooHelper(localActivity));
/* 671 */       localObject2 = ((AlertDialog.Builder)localObject1).create();
/* 672 */       if ((paramArrayOfString != null) && (PdrUtil.isEmpty(paramArrayOfString[0]))) {
/* 673 */         paramArrayOfString[0] = AndroidResources.getString(17039370);
/*     */       }
/* 675 */       if (!PdrUtil.isEmpty(paramString1)) {
/* 676 */         ((AlertDialog)localObject2).setTitle(paramString1);
/*     */       }
/* 678 */       ((AlertDialog)localObject2).setCanceledOnTouchOutside(false);
/* 679 */       ((AlertDialog)localObject2).setMessage(paramString3);
/* 680 */       ((AlertDialog)localObject2).setButton(paramArrayOfString[0], new DialogInterface.OnClickListener()
/*     */       {
/*     */         public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/* 683 */           this.a.dismiss();
/* 684 */           JSUtil.execCallback(paramIWebview, paramString5, "{index:0}", JSUtil.OK, true, false);
/*     */         }
/*     */       });
/* 688 */       ((AlertDialog)localObject2).setOnKeyListener(new DialogInterface.OnKeyListener()
/*     */       {
/*     */         public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
/*     */         {
/* 693 */           if ((paramAnonymousKeyEvent.getAction() == 1) && 
/* 694 */             (paramAnonymousInt == 4)) {
/* 695 */             this.a.dismiss();
/* 696 */             JSUtil.execCallback(paramIWebview, paramString5, "{index:-1}", JSUtil.OK, true, false);
/*     */ 
/* 698 */             return true;
/*     */           }
/*     */ 
/* 701 */           return false;
/*     */         }
/*     */       });
/* 704 */       ((AlertDialog)localObject2).show();
/*     */     } else {
/* 706 */       AlertDialog.Builder localBuilder = DialogUtil.initDialogTheme(localActivity, !BaseInfo.isForQihooHelper(localActivity));
/* 707 */       localObject1 = localBuilder.create();
/* 708 */       ((AlertDialog)localObject1).setMessage(paramString3);
/* 709 */       localObject2 = null;
/* 710 */       if (paramByte == 2) {
/* 711 */         localObject2 = new EditText(localActivity);
/* 712 */         if (paramString4 != null) {
/* 713 */           ((EditText)localObject2).setHint(paramString4);
/*     */         }
/* 715 */         ((AlertDialog)localObject1).setView((View)localObject2);
/* 716 */         Editable localEditable = ((EditText)localObject2).getText();
/*     */ 
/* 718 */         if ((localEditable instanceof Spannable)) {
/* 719 */           localObject3 = (Spannable)localEditable;
/* 720 */           Selection.setSelection((Spannable)localObject3, localEditable.length());
/*     */         }
/*     */       }
/*     */ 
/* 724 */       if (!PdrUtil.isEmpty(paramString1)) {
/* 725 */         ((AlertDialog)localObject1).setTitle(paramString1);
/*     */       }
/* 727 */       ((AlertDialog)localObject1).setCanceledOnTouchOutside(false);
/* 728 */       ((AlertDialog)localObject1).setMessage(paramString3);
/* 729 */       if (paramArrayOfString == null) {
/* 730 */         paramArrayOfString = new String[] { AndroidResources.getString(17039370), AndroidResources.getString(17039360) };
/*     */       }
/*     */ 
/* 734 */       boolean bool2 = a(paramByte, paramString3, paramArrayOfString, paramIWebview.obtainApp(), paramIWebview, paramString5);
/* 735 */       if (bool2) {
/* 736 */         return;
/*     */       }
/* 738 */       Object localObject3 = localObject2;
/* 739 */       for (int m = 0; (m < paramArrayOfString.length) && (m < 3); m++) {
/* 740 */         final int n = m;
/* 741 */         DialogInterface.OnClickListener local10 = new DialogInterface.OnClickListener()
/*     */         {
/*     */           public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/* 744 */             String str = null;
/* 745 */             if (paramByte == 2) {
/* 746 */               str = this.b.getText().toString();
/*     */ 
/* 749 */               str = JSONUtil.toJSONableString(str);
/* 750 */               JSUtil.execCallback(paramIWebview, paramString5, "{index:" + n + ",message:" + str + "}", JSUtil.OK, true, false);
/*     */             }
/*     */ 
/* 754 */             if (paramByte == 1) {
/* 755 */               JSUtil.execCallback(paramIWebview, paramString5, String.valueOf(n), JSUtil.OK, true, false);
/*     */             }
/*     */ 
/* 759 */             this.f.dismiss();
/*     */           }
/*     */         };
/* 762 */         if (m == 0)
/* 763 */           ((AlertDialog)localObject1).setButton(paramArrayOfString[m], local10);
/* 764 */         else if (m == 1)
/* 765 */           ((AlertDialog)localObject1).setButton3(paramArrayOfString[m], local10);
/* 766 */         else if (m == 2) {
/* 767 */           ((AlertDialog)localObject1).setButton2(paramArrayOfString[m], local10);
/*     */         }
/*     */       }
/* 770 */       ((AlertDialog)localObject1).setOnKeyListener(new DialogInterface.OnKeyListener()
/*     */       {
/*     */         public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
/*     */         {
/* 775 */           if ((paramAnonymousKeyEvent.getAction() == 1) && 
/* 776 */             (paramAnonymousInt == 4)) {
/* 777 */             if (paramByte == 2) {
/* 778 */               String str = this.b.getText().toString();
/* 779 */               str = JSONUtil.toJSONableString(str);
/* 780 */               JSUtil.execCallback(paramIWebview, paramString5, "{index:-1,message:" + str + "}", JSUtil.OK, true, false);
/*     */             }
/*     */ 
/* 784 */             if (paramByte == 1) {
/* 785 */               JSUtil.execCallback(paramIWebview, paramString5, String.valueOf(-1), JSUtil.OK, true, false);
/*     */             }
/*     */ 
/* 789 */             this.e.dismiss();
/* 790 */             return true;
/*     */           }
/*     */ 
/* 793 */           return false;
/*     */         }
/*     */       });
/* 796 */       ((AlertDialog)localObject1).show();
/* 797 */       if (!Build.FINGERPRINT.toLowerCase().contains("flyme"))
/*     */       {
/* 799 */         DeviceInfo.showIME((View)localObject2);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void init(AbsMgr paramAbsMgr, String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void dispose(String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   private boolean a(String paramString1, IApp paramIApp, IWebview paramIWebview, String paramString2)
/*     */   {
/* 822 */     if ((!TextUtils.isEmpty(paramString1)) && (BaseInfo.ISAMU) && (
/* 823 */       (paramString1.contains("更新")) || (paramString1.contains("升级")) || (paramString1.contains("版本")))) {
/*     */       try {
/* 825 */         JSUtil.execCallback(paramIWebview, paramString2, "{index:0}", JSUtil.OK, true, false);
/* 826 */         a(paramIApp);
/* 827 */         return true;
/*     */       }
/*     */       catch (JSONException localJSONException) {
/* 830 */         localJSONException.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/* 834 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean a(byte paramByte, String paramString1, String[] paramArrayOfString, IApp paramIApp, IWebview paramIWebview, String paramString2) {
/* 838 */     if ((!TextUtils.isEmpty(paramString1)) && (paramArrayOfString != null) && (BaseInfo.ISAMU)) {
/* 839 */       for (int m = 0; (m < paramArrayOfString.length) && (m < 3); m++) {
/* 840 */         int n = m;
/* 841 */         String str = paramArrayOfString[m];
/* 842 */         if ((str.contains("更新")) || (str.contains("升级")) || (((paramString1.contains("更新")) || (paramString1.contains("升级")) || (paramString1.contains("版本"))) && ((str.equals("OK")) || (str.equals("是")) || (str.equals("确定"))))) {
/*     */           try {
/* 844 */             if (paramByte == 2) {
/* 845 */               paramString1 = "";
/* 846 */               paramString1 = JSONUtil.toJSONableString(paramString1);
/* 847 */               JSUtil.execCallback(paramIWebview, paramString2, "{index:" + n + ",message:" + paramString1 + "}", JSUtil.OK, true, false);
/*     */ 
/* 850 */               a(paramIApp);
/* 851 */               return true;
/* 852 */             }if (paramByte == 1) {
/* 853 */               JSUtil.execCallback(paramIWebview, paramString2, String.valueOf(n), JSUtil.OK, true, false);
/*     */ 
/* 856 */               a(paramIApp);
/* 857 */               return true;
/*     */             }
/*     */           }
/*     */           catch (JSONException localJSONException) {
/* 861 */             localJSONException.printStackTrace();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 866 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean a(JSONArray paramJSONArray, IApp paramIApp, String paramString, IWebview paramIWebview)
/*     */   {
/* 871 */     if ((paramJSONArray != null) && (BaseInfo.ISAMU)) {
/* 872 */       for (int m = 0; m < paramJSONArray.length(); m++) {
/*     */         try {
/* 874 */           String str = paramJSONArray.getJSONObject(m).getString("title");
/* 875 */           if ((str.contains("更新")) || (str.contains("升级")) || (str.contains("版本"))) {
/* 876 */             int n = m + 1;
/* 877 */             JSUtil.execCallback(paramIWebview, paramString, "" + n, JSUtil.OK, true, false);
/*     */ 
/* 879 */             a(paramIApp);
/* 880 */             return true;
/*     */           }
/*     */         }
/*     */         catch (JSONException localJSONException) {
/* 884 */           localJSONException.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 888 */     return false;
/*     */   }
/*     */ 
/*     */   private void a(IApp paramIApp) throws JSONException {
/* 892 */     JSONObject localJSONObject = new JSONObject();
/* 893 */     localJSONObject.put("type", "notify");
/* 894 */     localJSONObject.put("appid", paramIApp.obtainOriginalAppId());
/* 895 */     localJSONObject.put("version", paramIApp.obtainAppVersionName());
/* 896 */     Log.i(".stream_json", localJSONObject.toString());
/*     */   }
/*     */ 
/*     */   class a
/*     */     implements a.b
/*     */   {
/*     */     public void a(int paramInt)
/*     */     {
/* 620 */       JSUtil.execCallback(paramIWebview, paramString, "" + paramInt, JSUtil.OK, true, false);
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.doctor.hd\app\libs\nativeui.jar
 * Qualified Name:     io.dcloud.feature.ui.nativeui.NativeUIFeatureImpl
 * JD-Core Version:    0.6.2
 */