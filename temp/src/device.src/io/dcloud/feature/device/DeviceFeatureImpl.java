/*     */ package io.dcloud.feature.device;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.hardware.Sensor;
/*     */ import android.hardware.SensorEvent;
/*     */ import android.hardware.SensorEventListener;
/*     */ import android.hardware.SensorManager;
/*     */ import android.media.AudioManager;
/*     */ import android.media.ToneGenerator;
/*     */ import android.net.Uri;
/*     */ import android.os.Build.VERSION;
/*     */ import android.os.PowerManager;
/*     */ import android.os.PowerManager.WakeLock;
/*     */ import android.os.SystemClock;
/*     */ import android.os.Vibrator;
/*     */ import android.provider.Settings.Global;
/*     */ import android.provider.Settings.System;
/*     */ import android.text.TextUtils;
/*     */ import android.view.Window;
/*     */ import android.view.WindowManager.LayoutParams;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFeature;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class DeviceFeatureImpl
/*     */   implements IFeature, ISysEventListener
/*     */ {
/*     */   private SensorManager b;
/*  52 */   private PowerManager.WakeLock c = null;
/*     */   private Sensor d;
/*  54 */   private boolean e = false;
/*     */   private Context f;
/* 192 */   int a = -1;
/*     */ 
/* 252 */   private final SensorEventListener g = new SensorEventListener() {
/* 254 */     private final float[] b = { 2.0F, 2.5F, 0.5F };
/* 255 */     private float[] c = new float[3];
/*     */     private long d;
/*     */ 
/* 259 */     public void onSensorChanged(SensorEvent paramAnonymousSensorEvent) { int i = 0;
/* 260 */       float[] arrayOfFloat = new float[3];
/*     */ 
/* 262 */       for (int j = 0; j < 3; j++) {
/* 263 */         arrayOfFloat[j] = Math.round(this.b[j] * (paramAnonymousSensorEvent.values[j] - this.c[j]) * 0.45F);
/*     */ 
/* 265 */         if (Math.abs(arrayOfFloat[j]) > 0.0F) {
/* 266 */           i = 1;
/*     */         }
/* 268 */         this.c[j] = paramAnonymousSensorEvent.values[j];
/*     */       }
/*     */ 
/* 271 */       if (i != 0)
/*     */       {
/* 274 */         Logger.i("sensorChanged " + paramAnonymousSensorEvent.sensor.getName() + " (" + paramAnonymousSensorEvent.values[0] + ", " + paramAnonymousSensorEvent.values[1] + ", " + paramAnonymousSensorEvent.values[2] + ")" + " diff(" + arrayOfFloat[0] + " " + arrayOfFloat[1] + " " + arrayOfFloat[2] + ")");
/*     */       }
/*     */ 
/* 280 */       long l = SystemClock.uptimeMillis();
/* 281 */       if (l - this.d > 1000L) {
/* 282 */         this.d = 0L;
/*     */ 
/* 284 */         float f1 = arrayOfFloat[0];
/* 285 */         float f2 = arrayOfFloat[1];
/* 286 */         int k = Math.abs(f1) > 3.0F ? 1 : 0;
/* 287 */         int m = Math.abs(f2) > 3.0F ? 1 : 0;
/*     */ 
/* 289 */         if (((k != 0) || (m != 0)) && ((k == 0) || (m == 0))) {
/* 290 */           if (k != 0) {
/* 291 */             if (f1 < 0.0F)
/* 292 */               Logger.i("test", "<<<<<<<< LEFT <<<<<<<<<<<<");
/*     */             else {
/* 294 */               Logger.i("test", ">>>>>>>>> RITE >>>>>>>>>>>");
/*     */             }
/*     */           }
/* 297 */           else if (f2 < -2.0F)
/* 298 */             Logger.i("test", "<<<<<<<< UP <<<<<<<<<<<<");
/*     */           else {
/* 300 */             Logger.i("test", ">>>>>>>>> DOWN >>>>>>>>>>>");
/*     */           }
/*     */ 
/* 303 */           this.d = l;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     public void onAccuracyChanged(Sensor paramAnonymousSensor, int paramAnonymousInt)
/*     */     {
/*     */     }
/* 252 */   };
/*     */ 
/*     */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*     */   {
/*  60 */     if (paramString.equals("getCurrentType"))
/*  61 */       return DeviceInfo.getNetWorkType();
/*  62 */     if ("unlockOrientation".equals(paramString)) {
/*  63 */       paramIWebview.obtainApp().setRequestedOrientation(null);
/*  64 */     } else if ("lockOrientation".equals(paramString)) {
/*  65 */       String str1 = paramArrayOfString[0];
/*  66 */       paramIWebview.obtainApp().setRequestedOrientation(str1);
/*  67 */     } else if ("dial".equals(paramString)) {
/*  68 */       boolean bool1 = PdrUtil.parseBoolean(paramArrayOfString[1], true, false);
/*  69 */       a(paramIWebview, paramArrayOfString[0], bool1);
/*  70 */     } else if ("beep".equals(paramString)) {
/*  71 */       ToneGenerator localToneGenerator = new ToneGenerator(5, 100);
/*  72 */       int i = 1;
/*     */       try {
/*  74 */         i = Integer.parseInt(paramArrayOfString[0]);
/*  75 */         if (i <= 0)
/*  76 */           i = 1;
/*     */       }
/*     */       catch (NumberFormatException localNumberFormatException1) {
/*  79 */         localNumberFormatException1.printStackTrace();
/*     */       }
/*  81 */       for (int j = 0; j < i; j++) {
/*  82 */         localToneGenerator.startTone(88);
/*     */         try {
/*  84 */           Thread.sleep(500L);
/*     */         } catch (InterruptedException localInterruptedException) {
/*  86 */           localInterruptedException.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       boolean bool2;
/*  89 */       if ("setWakelock".equals(paramString)) {
/*  90 */         bool2 = false;
/*  91 */         bool2 = PdrUtil.parseBoolean(paramArrayOfString[0], bool2, false);
/*  92 */         if (bool2)
/*  93 */           this.c.acquire();
/*     */         else
/*  95 */           this.c.release();
/*     */       } else {
/*  97 */         if ("isWakelock".equals(paramString)) {
/*  98 */           bool2 = this.c.isHeld();
/*  99 */           return JSUtil.wrapJsVar(String.valueOf(bool2), false);
/* 100 */         }if ("vibrate".equals(paramString)) {
/* 101 */           long l = 500L;
/*     */           try {
/* 103 */             l = Long.parseLong(paramArrayOfString[0]);
/* 104 */             if (l <= 0L)
/* 105 */               l = 500L;
/*     */           }
/*     */           catch (NumberFormatException localNumberFormatException2) {
/* 108 */             localNumberFormatException2.printStackTrace();
/*     */           }
/* 110 */           Vibrator localVibrator = (Vibrator)this.f.getSystemService("vibrator");
/* 111 */           localVibrator.vibrate(l);
/* 112 */         } else if ("setVolume".equals(paramString)) {
/* 113 */           float f1 = Float.parseFloat(paramArrayOfString[0]);
/* 114 */           AudioManager localAudioManager2 = (AudioManager)paramIWebview.getContext().getSystemService("audio");
/* 115 */           int k = a(f1);
/* 116 */           localAudioManager2.setStreamVolume(4, k, 8);
/* 117 */           localAudioManager2.setStreamVolume(8, k, 8);
/* 118 */           localAudioManager2.setStreamVolume(3, k, 8);
/* 119 */           localAudioManager2.setStreamVolume(5, k, 8);
/* 120 */           localAudioManager2.setStreamVolume(2, k, 8);
/* 121 */           localAudioManager2.setStreamVolume(1, k, 8);
/* 122 */           localAudioManager2.setStreamVolume(0, k, 8); } else {
/* 123 */           if ("getVolume".equals(paramString)) {
/* 124 */             AudioManager localAudioManager1 = (AudioManager)paramIWebview.getContext().getSystemService("audio");
/* 125 */             float f3 = localAudioManager1.getStreamVolume(3);
/* 126 */             return JSUtil.wrapJsVar(String.valueOf(f3 / this.a), false);
/*     */           }
/*     */           float f2;
/* 127 */           if ("setBrightness".equals(paramString)) {
/* 128 */             f2 = Float.parseFloat(paramArrayOfString[0]);
/* 129 */             a(paramIWebview, f2); } else {
/* 130 */             if ("getBrightness".equals(paramString)) {
/* 131 */               f2 = a(paramIWebview.getActivity());
/* 132 */               return JSUtil.wrapJsVar(String.valueOf(f2 / 255.0F), false);
/* 133 */             }if (paramString.equals("getCurrentAPN")) {
/* 134 */               String str2 = DeviceInfo.getCurrentAPN();
/* 135 */               if (TextUtils.isEmpty(str2)) {
/* 136 */                 return null;
/*     */               }
/* 138 */               String str3 = "{name:" + str2 + "}";
/*     */               try
/*     */               {
/* 141 */                 JSONObject localJSONObject = new JSONObject(str3);
/* 142 */                 return JSUtil.wrapJsVar(localJSONObject);
/*     */               }
/*     */               catch (JSONException localJSONException) {
/* 145 */                 localJSONException.printStackTrace();
/*     */ 
/* 147 */                 return null;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */ 
/*     */   private int a(Activity paramActivity)
/*     */   {
/* 157 */     float f1 = 125.0F;
/* 158 */     Window localWindow = paramActivity.getWindow();
/* 159 */     WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
/* 160 */     f1 = localLayoutParams.screenBrightness * 255.0F;
/* 161 */     if (f1 < 0.0F)
/*     */       try {
/* 163 */         if (Build.VERSION.SDK_INT > 17)
/* 164 */           f1 = Settings.Global.getInt(paramActivity.getContentResolver(), "screen_brightness");
/*     */         else
/* 166 */           f1 = Settings.System.getInt(paramActivity.getContentResolver(), "screen_brightness");
/*     */       }
/*     */       catch (Exception localException1)
/*     */       {
/*     */       }
/* 171 */     if (f1 < 0.0F)
/* 172 */       f1 = 125.0F;
/*     */     try
/*     */     {
/* 175 */       f1 = Settings.System.getInt(this.f.getContentResolver(), "screen_brightness");
/*     */     } catch (Exception localException2) {
/*     */     }
/* 178 */     return (int)f1;
/*     */   }
/*     */ 
/*     */   private void a(IWebview paramIWebview, float paramFloat)
/*     */   {
/* 185 */     Window localWindow = paramIWebview.getActivity().getWindow();
/* 186 */     WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
/* 187 */     localLayoutParams.screenBrightness = paramFloat;
/* 188 */     localWindow.setAttributes(localLayoutParams);
/* 189 */     Settings.System.putInt(this.f.getContentResolver(), "screen_brightness", (int)(paramFloat * 255.0F));
/*     */   }
/*     */ 
/*     */   private int a(float paramFloat)
/*     */   {
/* 199 */     if ((paramFloat > 1.0F) || (paramFloat < 0.0F)) return 0;
/* 200 */     int i = (int)(paramFloat * this.a);
/* 201 */     return i;
/*     */   }
/*     */ 
/*     */   protected void a(IWebview paramIWebview, String paramString, boolean paramBoolean)
/*     */   {
/* 216 */     Uri localUri = Uri.parse("tel:" + paramString);
/* 217 */     String str = "android.intent.action.DIAL";
/* 218 */     if (!paramBoolean) {
/* 219 */       str = "android.intent.action.CALL";
/*     */     }
/* 221 */     Intent localIntent = new Intent(str, localUri);
/* 222 */     paramIWebview.getActivity().startActivity(localIntent);
/*     */   }
/*     */ 
/*     */   public void init(AbsMgr paramAbsMgr, String paramString)
/*     */   {
/* 227 */     this.b = ((SensorManager)paramAbsMgr.getContext().getSystemService("sensor"));
/*     */ 
/* 229 */     this.d = this.b.getDefaultSensor(1);
/* 230 */     this.f = paramAbsMgr.getContext();
/* 231 */     PowerManager localPowerManager = (PowerManager)this.f.getSystemService("power");
/*     */ 
/* 233 */     this.c = localPowerManager.newWakeLock(10, "My Lock");
/*     */ 
/* 235 */     this.c.setReferenceCounted(false);
/*     */ 
/* 237 */     AudioManager localAudioManager = (AudioManager)paramAbsMgr.getContext().getSystemService("audio");
/* 238 */     this.a = localAudioManager.getStreamMaxVolume(3);
/*     */   }
/*     */ 
/*     */   public boolean onExecute(ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*     */   {
/* 243 */     if (paramSysEventType == ISysEventListener.SysEventType.onResume) {
/* 244 */       this.b.registerListener(this.g, this.d, 0);
/*     */     }
/* 246 */     else if (paramSysEventType == ISysEventListener.SysEventType.onStop) {
/* 247 */       this.b.unregisterListener(this.g);
/*     */     }
/* 249 */     return false;
/*     */   }
/*     */ 
/*     */   public void dispose(String paramString)
/*     */   {
/*     */   }
/*     */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.doctor.hd\app\libs\device.jar
 * Qualified Name:     io.dcloud.feature.device.DeviceFeatureImpl
 * JD-Core Version:    0.6.2
 */