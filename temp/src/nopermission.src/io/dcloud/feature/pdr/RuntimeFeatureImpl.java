/*     */ package io.dcloud.feature.pdr;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Build;
/*     */ import android.text.TextUtils;
/*     */ import android.util.Log;
/*     */ import io.dcloud.PandoraEntry;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.ICallBack;
/*     */ import io.dcloud.common.DHInterface.IFeature;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class RuntimeFeatureImpl
/*     */   implements IFeature, MessageHandler.IMessages
/*     */ {
/*  36 */   final String a = PandoraEntry.class.getName();
/*     */ 
/*  43 */   AbsMgr b = null;
/*     */ 
/*     */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*     */   {
/*  40 */     MessageHandler.sendMessage(this, new Object[] { paramIWebview, paramString, paramArrayOfString });
/*  41 */     return null;
/*     */   }
/*     */ 
/*     */   public void init(AbsMgr paramAbsMgr, String paramString)
/*     */   {
/*  46 */     this.b = paramAbsMgr;
/*     */   }
/*     */ 
/*     */   public void dispose(String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void execute(Object paramObject)
/*     */   {
/*  55 */     Object[] arrayOfObject = (Object[])paramObject;
/*  56 */     final IWebview localIWebview = (IWebview)arrayOfObject[0];
/*  57 */     String str1 = String.valueOf(arrayOfObject[1]);
/*  58 */     final String[] arrayOfString = (String[])arrayOfObject[2];
/*  59 */     IApp localIApp = localIWebview.obtainFrameView().obtainApp();
/*     */     final String str2;
/*  60 */     if ("restart".equals(str1)) {
/*  61 */       str2 = localIApp.obtainAppId();
/*  62 */       this.b.processEvent(IMgr.MgrType.AppMgr, 3, str2);
/*  63 */     } else if ("install".equals(str1)) {
/*  64 */       str2 = localIApp.convert2AbsFullPath(localIWebview.obtainFullUrl(), arrayOfString[0]);
/*  65 */       a(str2, localIApp);
/*  66 */       new Thread() {
/*     */         public void run() {
/*  68 */           String str1 = arrayOfString[1];
/*  69 */           JSONObject localJSONObject = null;
/*  70 */           if (!PdrUtil.isEmpty(arrayOfString[2])) {
/*  71 */             localJSONObject = JSONUtil.createJSONObject(arrayOfString[2]);
/*     */           }
/*     */ 
/*  74 */           Object[] arrayOfObject = (Object[])RuntimeFeatureImpl.this.b.processEvent(IMgr.MgrType.AppMgr, 4, new Object[] { str2, localJSONObject, localIWebview });
/*     */ 
/*  76 */           boolean bool = Boolean.valueOf(String.valueOf(arrayOfObject[0])).booleanValue();
/*  77 */           String str2 = String.valueOf(arrayOfObject[1]);
/*  78 */           if (bool)
/*  79 */             JSUtil.execCallback(localIWebview, str1, str2, JSUtil.ERROR, true, false);
/*     */           else
/*  81 */             JSUtil.execCallback(localIWebview, str1, str2, JSUtil.OK, true, false);
/*     */         }
/*     */       }
/*  66 */       .start();
/*     */     }
/*     */     else
/*     */     {
/*     */       Object localObject1;
/*     */       String str4;
/*  85 */       if ("getProperty".equals(str1)) {
/*  86 */         str2 = arrayOfString[0];
/*  87 */         if (PdrUtil.isEmpty(str2)) {
/*  88 */           str2 = localIWebview.obtainFrameView().obtainApp().obtainAppId();
/*     */         }
/*  90 */         localObject1 = arrayOfString[1];
/*  91 */         str4 = String.valueOf(this.b.processEvent(IMgr.MgrType.AppMgr, 5, str2));
/*  92 */         if (!PdrUtil.isEmpty(str4))
/*  93 */           JSUtil.excCallbackSuccess(localIWebview, (String)localObject1, str4, true);
/*     */         else
/*  95 */           JSUtil.excCallbackError(localIWebview, (String)localObject1, null);
/*     */       }
/*  97 */       else if ("quit".equals(str1)) {
/*  98 */         this.b.processEvent(IMgr.MgrType.WindowMgr, 20, localIApp);
/*  99 */       } else if ("setBadgeNumber".equals(str1)) {
/* 100 */         a(localIWebview, arrayOfString[0]);
/* 101 */       } else if ("openURL".equals(str1)) {
/*     */         try {
/* 103 */           b(arrayOfString[0], localIApp);
/* 104 */           PlatformUtil.openURL(localIWebview.getActivity(), arrayOfString[0], String.valueOf(PdrUtil.getObject(arrayOfString, 2)));
/*     */         } catch (Exception localException1) {
/* 106 */           localException1.printStackTrace();
/* 107 */           JSUtil.excCallbackError(localIWebview, arrayOfString[1], localException1.getMessage());
/*     */         }
/* 109 */       } else if ("launchApplication".equals(str1)) {
/*     */         try {
/* 111 */           JSONObject localJSONObject1 = new JSONObject(arrayOfString[0]);
/* 112 */           localObject1 = localJSONObject1.names();
/* 113 */           str4 = null;
/* 114 */           String str5 = null;
/* 115 */           HashMap localHashMap = new HashMap();
/* 116 */           for (int i = 0; i < ((JSONArray)localObject1).length(); i++) {
/* 117 */             String str6 = ((JSONArray)localObject1).getString(i);
/* 118 */             if (str6.equals("pname")) {
/* 119 */               str4 = localJSONObject1.getString(str6);
/*     */             }
/* 121 */             else if (str6.equals("action")) {
/* 122 */               str5 = localJSONObject1.getString(str6);
/*     */             }
/* 124 */             else if (str6.equals("extra")) {
/* 125 */               JSONObject localJSONObject2 = localJSONObject1.getJSONObject(str6);
/* 126 */               Iterator localIterator = localJSONObject2.keys();
/* 127 */               while (localIterator.hasNext()) {
/* 128 */                 String str7 = (String)localIterator.next();
/* 129 */                 Object localObject2 = localJSONObject2.get(str7);
/* 130 */                 localHashMap.put(str7, localObject2);
/*     */               }
/*     */             }
/*     */           }
/* 134 */           PlatformUtil.launchApplication(localIWebview.getActivity(), str4, str5, localHashMap);
/*     */         } catch (Exception localException2) {
/* 136 */           localException2.printStackTrace();
/* 137 */           JSUtil.excCallbackError(localIWebview, arrayOfString[1], localException2.getMessage());
/*     */         }
/* 139 */       } else if ("openFile".equals(str1)) {
/* 140 */         String str3 = localIApp.checkPrivateDirAndCopy2Temp(arrayOfString[0]);
/* 141 */         localObject1 = arrayOfString[2];
/* 142 */         str4 = null;
/*     */         try {
/* 144 */           str4 = new JSONObject(arrayOfString[1]).optString("pname");
/*     */         } catch (Exception localException3) {
/* 146 */           localException3.printStackTrace();
/*     */         }
/* 148 */         str3 = localIApp.convert2AbsFullPath(localIWebview.obtainFullUrl(), str3);
/* 149 */         if (new File(str3).isFile())
/* 150 */           PlatformUtil.openFileBySystem(localIApp.getActivity(), str3, str4, new ICallBack()
/*     */           {
/*     */             public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject) {
/* 153 */               JSUtil.excCallbackError(localIWebview, this.b, String.valueOf(paramAnonymousObject), true);
/* 154 */               return null;
/*     */             }
/*     */           });
/*     */         else
/* 158 */           JSUtil.excCallbackError(localIWebview, (String)localObject1, String.valueOf(paramObject), true);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void a(IWebview paramIWebview, String paramString) {
/* 164 */     if ((TextUtils.isEmpty(paramString)) || (paramString.equals("0"))) {
/* 165 */       paramString = "";
/*     */     } else {
/* 167 */       int i = Integer.valueOf(paramString).intValue();
/* 168 */       paramString = String.valueOf(Math.max(0, Math.min(i, 99)));
/*     */     }
/*     */     try
/*     */     {
/* 172 */       if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi"))
/* 173 */         b(paramIWebview, paramString);
/* 174 */       else if (Build.MANUFACTURER.equalsIgnoreCase("samsung"))
/* 175 */         c(paramIWebview, paramString);
/* 176 */       else if (Build.MANUFACTURER.toLowerCase().contains("sony")) {
/* 177 */         d(paramIWebview, paramString);
/*     */       }
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 182 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void b(IWebview paramIWebview, String paramString) {
/*     */     try {
/* 188 */       Class localClass = Class.forName("android.app.MiuiNotification");
/* 189 */       localObject = localClass.newInstance();
/* 190 */       Field localField = localObject.getClass().getDeclaredField("messageCount");
/* 191 */       localField.setAccessible(true);
/* 192 */       localField.set(localObject, paramString);
/*     */     } catch (Exception localException) {
/* 194 */       localException.printStackTrace();
/*     */ 
/* 196 */       Object localObject = new Intent("android.intent.action.APPLICATION_MESSAGE_UPDATE");
/* 197 */       ((Intent)localObject).putExtra("android.intent.extra.update_application_component_name", paramIWebview.getContext().getPackageName() + "/" + this.a);
/* 198 */       ((Intent)localObject).putExtra("android.intent.extra.update_application_message_text", paramString);
/* 199 */       paramIWebview.getActivity().sendBroadcast((Intent)localObject);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void c(IWebview paramIWebview, String paramString)
/*     */   {
/* 205 */     boolean bool = true;
/* 206 */     if ("0".equals(paramString)) {
/* 207 */       bool = false;
/*     */     }
/* 209 */     Intent localIntent = new Intent();
/* 210 */     localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", bool);
/* 211 */     localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
/* 212 */     localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", this.a);
/* 213 */     localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", paramString);
/* 214 */     localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", paramIWebview.getContext().getPackageName());
/* 215 */     paramIWebview.getActivity().sendBroadcast(localIntent);
/*     */   }
/*     */ 
/*     */   private void d(IWebview paramIWebview, String paramString)
/*     */   {
/* 222 */     Intent localIntent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
/* 223 */     localIntent.putExtra("badge_count", paramString);
/* 224 */     localIntent.putExtra("badge_count_package_name", paramIWebview.getContext().getPackageName());
/* 225 */     localIntent.putExtra("badge_count_class_name", this.a);
/* 226 */     paramIWebview.getActivity().sendBroadcast(localIntent);
/*     */   }
/*     */ 
/*     */   private void a(String paramString, IApp paramIApp)
/*     */   {
/* 231 */     if ((!TextUtils.isEmpty(paramString)) && (BaseInfo.ISAMU)) {
/* 232 */       int i = paramString.length();
/* 233 */       String str1 = ".apk";
/* 234 */       String str2 = ".wgt";
/* 235 */       String str3 = ".wgtu";
/* 236 */       if ((i - paramString.indexOf(str1) - str1.length() == 0) || (i - paramString.indexOf(str2) - str2.length() == 0) || (i - paramString.indexOf(str3) - str3.length() == 0))
/*     */       {
/*     */         try
/*     */         {
/* 240 */           JSONObject localJSONObject = new JSONObject();
/* 241 */           localJSONObject.put("type", "install");
/* 242 */           localJSONObject.put("file", paramString);
/* 243 */           localJSONObject.put("appid", paramIApp.obtainOriginalAppId());
/* 244 */           localJSONObject.put("version", paramIApp.obtainAppVersionName());
/* 245 */           Log.i(".stream_json", localJSONObject.toString());
/*     */         }
/*     */         catch (JSONException localJSONException) {
/* 248 */           localJSONException.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void b(String paramString, IApp paramIApp)
/*     */   {
/*     */     try {
/* 257 */       JSONObject localJSONObject = new JSONObject();
/* 258 */       localJSONObject.put("type", "openurl");
/* 259 */       localJSONObject.put("url", paramString);
/* 260 */       localJSONObject.put("appid", paramIApp.obtainOriginalAppId());
/* 261 */       localJSONObject.put("version", paramIApp.obtainAppVersionName());
/* 262 */       Log.i(".stream_json", localJSONObject.toString());
/*     */     }
/*     */     catch (JSONException localJSONException) {
/* 265 */       localJSONException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           E:\work_app.android\hljk365-app-android\code\hljk365.iptv.tianjin\app\libs\nopermission.jar
 * Qualified Name:     io.dcloud.feature.pdr.RuntimeFeatureImpl
 * JD-Core Version:    0.6.2
 */