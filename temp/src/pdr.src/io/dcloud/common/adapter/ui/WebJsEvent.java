/*     */ package io.dcloud.common.adapter.ui;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.AlertDialog;
/*     */ import android.app.AlertDialog.Builder;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.DialogInterface.OnCancelListener;
/*     */ import android.content.DialogInterface.OnClickListener;
/*     */ import android.content.DialogInterface.OnKeyListener;
/*     */ import android.content.Intent;
/*     */ import android.net.Uri;
/*     */ import android.util.Log;
/*     */ import android.view.KeyEvent;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.view.Window;
/*     */ import android.webkit.ConsoleMessage;
/*     */ import android.webkit.ConsoleMessage.MessageLevel;
/*     */ import android.webkit.GeolocationPermissions.Callback;
/*     */ import android.webkit.JsPromptResult;
/*     */ import android.webkit.JsResult;
/*     */ import android.webkit.ValueCallback;
/*     */ import android.webkit.WebChromeClient;
/*     */ import android.webkit.WebChromeClient.CustomViewCallback;
/*     */ import android.webkit.WebChromeClient.FileChooserParams;
/*     */ import android.webkit.WebStorage.QuotaUpdater;
/*     */ import android.webkit.WebView;
/*     */ import android.widget.EditText;
/*     */ import android.widget.FrameLayout.LayoutParams;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.ICallBack;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.adapter.util.AndroidResources;
/*     */ import io.dcloud.common.adapter.util.CanvasHelper;
/*     */ import io.dcloud.common.adapter.util.DeviceInfo;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.constant.DOMException;
/*     */ import io.dcloud.common.constant.StringConst;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.BaseInfo.CmtInfo;
/*     */ import io.dcloud.common.util.DialogUtil;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ final class WebJsEvent extends WebChromeClient
/*     */ {
/*  55 */   private boolean mScreemOrientationChanged = false;
/*  56 */   private boolean mDefaultTitleBarVisibility = false;
/*  57 */   private int mDefaultScreemOrientation = -2;
/*     */ 
/*  59 */   DialogListener mListener = null;
/*     */ 
/*  61 */   AdaWebview mAdaWebview = null;
/*     */   static final String TAG = "webview";
/*  63 */   private boolean rptJsErr = true;
/*  64 */   private boolean isStreamApp = false;
/*     */   ValueCallback<Uri> mUploadMessage;
/*     */   ValueCallback<Uri[]> mUploadMessage21Level;
/*     */   public static final int FILECHOOSER_RESULTCODE = 1;
/* 498 */   static final FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(-1, -1, 17);
/*     */   View mCustomView;
/*     */   WebChromeClient.CustomViewCallback mCustomViewCallback;
/*     */ 
/*     */   WebJsEvent(AdaWebview paramAdaWebview)
/*     */   {
/*  66 */     this.mAdaWebview = paramAdaWebview;
/*  67 */     this.isStreamApp = paramAdaWebview.obtainApp().isStreamApp();
/*  68 */     this.rptJsErr = BaseInfo.getCmitInfo(BaseInfo.sLastRunApp).rptJse;
/*     */   }
/*     */ 
/*     */   private boolean isUrlWhiteListed(String paramString)
/*     */   {
/*  88 */     return true;
/*     */   }
/*     */   private boolean isCallbackId(String paramString) {
/*  91 */     return (paramString != null) && (paramString.startsWith("plus"));
/*     */   }
/*     */ 
/*     */   private void handleMessage(final JsPromptResult paramJsPromptResult, final AdaWebview paramAdaWebview, final String paramString1, final String paramString2, String paramString3, final boolean paramBoolean)
/*     */   {
/*  96 */     final JSONArray localJSONArray = JSONUtil.createJSONArray(paramString3);
/*  97 */     if (this.isStreamApp)
/*     */       try {
/*  99 */         final JSONObject localJSONObject = localJSONArray.optJSONObject(localJSONArray.length() - 1);
/* 100 */         if ((localJSONObject != null) && (isCallbackId(localJSONObject.optString("cbid")))) {
/* 101 */           final IApp localIApp = paramAdaWebview.obtainApp();
/* 102 */           final String[] arrayOfString = (String[])paramAdaWebview.obtainFrameView().obtainWindowMgr().processEvent(IMgr.MgrType.AppMgr, 15, new Object[] { localIApp, paramString1, paramString2, localJSONObject.optString("sid") });
/* 103 */           boolean bool = Boolean.parseBoolean(arrayOfString[0]);
/* 104 */           String str2 = arrayOfString[1];
/* 105 */           if (bool) {
/* 106 */             DialogUtil.showConfirm(paramAdaWebview.getActivity(), localIApp.obtainAppName(), str2, new String[] { "允许", "不允许" }, new ICallBack()
/*     */             {
/*     */               public Object onCallBack(int paramAnonymousInt, Object paramAnonymousObject)
/*     */               {
/*     */                 String str;
/* 109 */                 if (paramAnonymousInt == -1) {
/* 110 */                   paramAdaWebview.obtainFrameView().obtainWindowMgr().processEvent(IMgr.MgrType.AppMgr, 16, new Object[] { localIApp, arrayOfString[2] });
/* 111 */                   str = WebJsEvent.this.mAdaWebview.execScript(paramString1, paramString2, localJSONArray, paramBoolean);
/* 112 */                   paramJsPromptResult.confirm(str);
/* 113 */                 } else if (paramAnonymousInt == -2) {
/* 114 */                   str = DOMException.toJSON(-10, "用户拒绝该API访问");
/* 115 */                   JSUtil.execCallback(paramAdaWebview, localJSONObject.optString("cbid"), str, JSUtil.ERROR, true, false);
/* 116 */                   MessageHandler.sendMessage(new MessageHandler.IMessages()
/*     */                   {
/*     */                     public void execute(Object paramAnonymous2Object) {
/* 119 */                       WebJsEvent.1.this.val$result.confirm();
/*     */                     }
/*     */                   }
/*     */                   , null);
/*     */                 }
/*     */ 
/* 123 */                 return null;
/*     */               }
/*     */             });
/* 126 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/* 133 */     String str1 = this.mAdaWebview.execScript(paramString1, paramString2, localJSONArray, paramBoolean);
/* 134 */     paramJsPromptResult.confirm(str1);
/*     */   }
/*     */ 
/*     */   public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
/*     */   {
/* 140 */     int i = 0;
/* 141 */     if (isUrlWhiteListed(paramString1)) {
/* 142 */       i = 1;
/*     */     }
/* 144 */     if ((i != 0) && (paramString3 != null) && (paramString3.length() > 3) && (paramString3.substring(0, 4).equals("pdr:")))
/*     */     {
/*     */       try {
/* 147 */         localObject1 = new JSONArray(paramString3.substring(4));
/* 148 */         String str1 = ((JSONArray)localObject1).getString(0);
/* 149 */         localObject2 = ((JSONArray)localObject1).getString(1);
/* 150 */         boolean bool = ((JSONArray)localObject1).getBoolean(2);
/* 151 */         handleMessage(paramJsPromptResult, this.mAdaWebview, str1, (String)localObject2, paramString2, bool);
/*     */       } catch (JSONException localJSONException1) {
/* 153 */         if (!PdrUtil.isEquals(paramString3, paramString3.replace("\\\"", "\""))) {
/* 154 */           paramString2 = paramString2.replace("\\\"", "\"");
/* 155 */           paramString2 = paramString2.substring(1, paramString2.length() - 1);
/* 156 */           paramString3 = paramString3.replace("\\\"", "\"").substring(4);
/* 157 */           paramString3 = "pdr:" + paramString3.substring(1, paramString3.length() - 1);
/* 158 */           onJsPrompt(paramWebView, paramString1, paramString2, paramString3, paramJsPromptResult);
/*     */         } else {
/* 160 */           localJSONException1.printStackTrace();
/* 161 */           Logger.e("webview", "onJsPrompt js->native message=" + paramString2 + ";defaultValue=" + paramString3);
/*     */         }
/*     */       }
/* 164 */       return true;
/* 165 */     }if ((this.mAdaWebview.mWebViewImpl.mReceiveJSValue_android42 != null) && (i != 0) && (paramString3 != null) && (paramString3.length() > 5) && (paramString3.substring(0, 5).equals("sync:")))
/*     */     {
/*     */       try {
/* 168 */         localObject1 = new JSONArray(paramString3.substring(5));
/* 169 */         String str2 = ((JSONArray)localObject1).getString(0);
/* 170 */         localObject2 = ((JSONArray)localObject1).getString(1);
/* 171 */         str3 = this.mAdaWebview.mWebViewImpl.mReceiveJSValue_android42.__js__call__native__(str2, (String)localObject2);
/* 172 */         paramJsPromptResult.confirm(str3);
/*     */       } catch (JSONException localJSONException2) {
/* 174 */         if (!PdrUtil.isEquals(paramString3, paramString3.replace("\\\"", "\""))) {
/* 175 */           paramString2 = paramString2.replace("\\\"", "\"");
/* 176 */           paramString2 = paramString2.substring(1, paramString2.length() - 1);
/* 177 */           paramString3 = paramString3.replace("\\\"", "\"").substring(4);
/* 178 */           paramString3 = "pdr:" + paramString3.substring(1, paramString3.length() - 1);
/* 179 */           onJsPrompt(paramWebView, paramString1, paramString2, paramString3, paramJsPromptResult);
/*     */         } else {
/* 181 */           localJSONException2.printStackTrace();
/* 182 */           Logger.e("webview", "onJsPrompt js->native message=" + paramString2 + ";defaultValue=" + paramString3);
/*     */         }
/*     */       }
/* 185 */       return true;
/*     */     }
/* 187 */     Object localObject1 = paramJsPromptResult;
/* 188 */     final AlertDialog localAlertDialog = new AlertDialog.Builder(this.mAdaWebview.getActivity()).create();
/* 189 */     localAlertDialog.setMessage(paramString2);
/* 190 */     localAlertDialog.setTitle(this.mAdaWebview.getAppName());
/* 191 */     Object localObject2 = new EditText(this.mAdaWebview.getActivity());
/* 192 */     if (paramString3 != null) {
/* 193 */       ((EditText)localObject2).setText(paramString3);
/* 194 */       ((EditText)localObject2).setSelection(0, paramString3.length());
/* 195 */       DeviceInfo.showIME((View)localObject2);
/*     */     }
/* 197 */     localAlertDialog.setView((View)localObject2);
/* 198 */     String str3 = StringConst.getString(17039370);
/* 199 */     localAlertDialog.setButton(str3, new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/* 202 */         String str = this.val$input.getText().toString();
/* 203 */         this.val$res.confirm(str);
/*     */       }
/*     */     });
/* 206 */     String str4 = StringConst.getString(17039360);
/* 207 */     localAlertDialog.setButton2(str4, new DialogInterface.OnClickListener()
/*     */     {
/*     */       public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/* 210 */         this.val$res.cancel();
/*     */       }
/*     */     });
/* 213 */     localAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
/*     */     {
/*     */       public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
/*     */       {
/* 217 */         if ((!AndroidResources.sIMEAlive) && (paramAnonymousKeyEvent.getAction() == 0) && (paramAnonymousInt == 4))
/*     */         {
/* 219 */           localAlertDialog.dismiss();
/* 220 */           this.val$res.cancel();
/* 221 */           return true;
/*     */         }
/*     */ 
/* 224 */         return false;
/*     */       }
/*     */     });
/* 227 */     localAlertDialog.show();
/* 228 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
/*     */   {
/* 235 */     if (!PdrUtil.isEmpty(this.mAdaWebview.getAppName())) {
/* 236 */       final AlertDialog localAlertDialog = new AlertDialog.Builder(this.mAdaWebview.getActivity()).create();
/* 237 */       localAlertDialog.setTitle(this.mAdaWebview.getAppName());
/* 238 */       localAlertDialog.setMessage(paramString2);
/* 239 */       if (this.mListener == null) {
/* 240 */         this.mListener = new DialogListener();
/*     */       }
/* 242 */       this.mListener.mResult = paramJsResult;
/* 243 */       String str = StringConst.getString(17039370);
/* 244 */       localAlertDialog.setButton(str, this.mListener);
/* 245 */       localAlertDialog.setCanceledOnTouchOutside(false);
/* 246 */       localAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
/*     */       {
/*     */         public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
/*     */         {
/* 250 */           if ((paramAnonymousKeyEvent.getAction() == 1) && (paramAnonymousInt == 4))
/*     */           {
/* 252 */             paramJsResult.cancel();
/* 253 */             localAlertDialog.dismiss();
/* 254 */             return true;
/*     */           }
/*     */ 
/* 257 */           return false;
/*     */         }
/*     */       });
/* 260 */       localAlertDialog.show();
/* 261 */       return true;
/*     */     }
/* 263 */     return super.onJsAlert(paramWebView, paramString1, paramString2, paramJsResult);
/*     */   }
/*     */ 
/*     */   public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, final JsResult paramJsResult)
/*     */   {
/* 270 */     if (!PdrUtil.isEmpty(this.mAdaWebview.getAppName())) {
/*     */       try {
/* 272 */         final AlertDialog localAlertDialog = new AlertDialog.Builder(this.mAdaWebview.getActivity()).create();
/* 273 */         localAlertDialog.setMessage(paramString2);
/* 274 */         localAlertDialog.setTitle(this.mAdaWebview.getAppName());
/* 275 */         String str1 = StringConst.getString(17039370);
/* 276 */         localAlertDialog.setButton(str1, new DialogInterface.OnClickListener()
/*     */         {
/*     */           public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/* 279 */             paramJsResult.confirm();
/*     */           }
/*     */         });
/* 282 */         String str2 = StringConst.getString(17039360);
/* 283 */         localAlertDialog.setButton2(str2, new DialogInterface.OnClickListener()
/*     */         {
/*     */           public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
/* 286 */             paramJsResult.cancel();
/*     */           }
/*     */         });
/* 289 */         localAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
/*     */         {
/*     */           public void onCancel(DialogInterface paramAnonymousDialogInterface) {
/* 292 */             paramJsResult.cancel();
/*     */           }
/*     */         });
/* 295 */         localAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
/*     */         {
/*     */           public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent) {
/* 298 */             if ((paramAnonymousKeyEvent.getAction() == 0) && (paramAnonymousInt == 4))
/*     */             {
/* 300 */               paramJsResult.cancel();
/* 301 */               localAlertDialog.dismiss();
/* 302 */               return true;
/*     */             }
/*     */ 
/* 305 */             return false;
/*     */           }
/*     */         });
/* 308 */         localAlertDialog.show();
/* 309 */         return true;
/*     */       } catch (Exception localException) {
/* 311 */         localException.printStackTrace();
/* 312 */         return super.onJsConfirm(paramWebView, paramString1, paramString2, paramJsResult);
/*     */       }
/*     */     }
/* 315 */     return super.onJsConfirm(paramWebView, paramString1, paramString2, paramJsResult);
/*     */   }
/*     */ 
/*     */   public void openFileChooser(ValueCallback<Uri> paramValueCallback)
/*     */   {
/* 323 */     openFileChooserLogic(paramValueCallback, null, null);
/*     */   }
/*     */ 
/*     */   public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString) {
/* 327 */     openFileChooserLogic(paramValueCallback, paramString, null);
/*     */   }
/*     */ 
/*     */   public boolean onShowFileChooser(WebView paramWebView, ValueCallback<Uri[]> paramValueCallback, WebChromeClient.FileChooserParams paramFileChooserParams) {
/* 331 */     openFileChooserLogic(null, paramValueCallback, paramFileChooserParams.getAcceptTypes() != null ? paramFileChooserParams.getAcceptTypes()[0] : null, "");
/* 332 */     return true;
/*     */   }
/*     */ 
/*     */   public void openFileChooser(ValueCallback<Uri> paramValueCallback, String paramString1, String paramString2) {
/* 336 */     openFileChooserLogic(paramValueCallback, paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   private void openFileChooserLogic(ValueCallback<Uri> paramValueCallback, String paramString1, String paramString2) {
/* 340 */     openFileChooserLogic(paramValueCallback, null, paramString1, paramString2);
/*     */   }
/*     */   private void openFileChooserLogic(ValueCallback<Uri> paramValueCallback, ValueCallback<Uri[]> paramValueCallback1, String paramString1, String paramString2) {
/* 343 */     this.mUploadMessage = paramValueCallback;
/* 344 */     this.mUploadMessage21Level = paramValueCallback1;
/* 345 */     Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
/* 346 */     localIntent.addCategory("android.intent.category.OPENABLE");
/* 347 */     if (!PdrUtil.isEmpty(paramString1))
/* 348 */       localIntent.setType(paramString1);
/*     */     else {
/* 350 */       localIntent.setType("*/*");
/*     */     }
/* 352 */     this.mAdaWebview.obtainFrameView().obtainApp().registerSysEventListener(new ISysEventListener()
/*     */     {
/*     */       public boolean onExecute(ISysEventListener.SysEventType paramAnonymousSysEventType, Object paramAnonymousObject) {
/* 355 */         Object[] arrayOfObject = (Object[])paramAnonymousObject;
/* 356 */         int i = ((Integer)arrayOfObject[0]).intValue();
/* 357 */         int j = ((Integer)arrayOfObject[1]).intValue();
/* 358 */         WebJsEvent.this.mAdaWebview.obtainFrameView().obtainApp().unregisterSysEventListener(this, ISysEventListener.SysEventType.onActivityResult);
/* 359 */         if ((j != 0) && (paramAnonymousSysEventType == ISysEventListener.SysEventType.onActivityResult)) {
/* 360 */           Intent localIntent = (Intent)arrayOfObject[2];
/* 361 */           Uri localUri = localIntent.getData();
/* 362 */           Logger.i("webview", "openFileChooserLogic  OnActivityResult url=" + localUri);
/* 363 */           if (WebJsEvent.this.mUploadMessage21Level != null)
/* 364 */             WebJsEvent.this.mUploadMessage21Level.onReceiveValue(new Uri[] { localUri });
/* 365 */           else if (WebJsEvent.this.mUploadMessage != null) {
/* 366 */             WebJsEvent.this.mUploadMessage.onReceiveValue(localUri);
/*     */           }
/* 368 */           return true;
/*     */         }
/* 370 */         if (WebJsEvent.this.mUploadMessage21Level != null)
/* 371 */           WebJsEvent.this.mUploadMessage21Level.onReceiveValue(null);
/* 372 */         else if (WebJsEvent.this.mUploadMessage != null) {
/* 373 */           WebJsEvent.this.mUploadMessage.onReceiveValue(null);
/*     */         }
/* 375 */         return false;
/*     */       }
/*     */     }
/*     */     , ISysEventListener.SysEventType.onActivityResult);
/*     */     try
/*     */     {
/* 380 */       this.mAdaWebview.getActivity().startActivityForResult(localIntent, 1);
/*     */     }
/*     */     catch (Exception localException) {
/* 383 */       Logger.e("openFileChooserLogic Exception");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void onProgressChanged(WebView paramWebView, int paramInt) {
/* 388 */     if ((paramInt < 20) && (!this.mAdaWebview.unReceiveTitle)) {
/* 389 */       this.mAdaWebview.unReceiveTitle = true;
/*     */     }
/* 391 */     this.mAdaWebview.dispatchWebviewStateEvent(3, Integer.valueOf(paramInt));
/* 392 */     super.onProgressChanged(paramWebView, paramInt);
/*     */   }
/*     */ 
/*     */   public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
/*     */   {
/* 398 */     handleConsoleMessage(paramConsoleMessage);
/* 399 */     return true;
/*     */   }
/*     */   private void handleConsoleMessage(ConsoleMessage paramConsoleMessage) {
/* 402 */     String str1 = paramConsoleMessage.message();
/* 403 */     int i = paramConsoleMessage.lineNumber();
/* 404 */     String str2 = paramConsoleMessage.sourceId();
/* 405 */     String str3 = paramConsoleMessage.messageLevel().name();
/* 406 */     ConsoleMessage.MessageLevel localMessageLevel = paramConsoleMessage.messageLevel();
/*     */ 
/* 408 */     String str4 = str2;
/* 409 */     String str5 = "";
/* 410 */     String str6 = "";
/* 411 */     if (PdrUtil.isEmpty(str4)) {
/* 412 */       str6 = str1;
/* 413 */       str5 = "[" + str3 + "] " + str1;
/*     */     } else {
/*     */       try {
/* 416 */         str4 = this.mAdaWebview.mWebViewImpl.convertRelPath(str4);
/*     */       } catch (Exception localException) {
/*     */       }
/* 419 */       str6 = str1 + " at " + str4 + ":" + i;
/* 420 */       str5 = "[" + str3 + "] " + str1 + " at " + str4 + ":" + i;
/*     */     }
/*     */ 
/* 425 */     Logger.i("console", str5);
/*     */   }
/*     */ 
/*     */   public void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater)
/*     */   {
/* 440 */     Logger.i("webview", "onExceededDatabaseQuota url=" + paramString1);
/* 441 */     paramQuotaUpdater.updateQuota(paramLong2 * 2L);
/*     */   }
/*     */ 
/*     */   public void onReachedMaxAppCacheSize(long paramLong1, long paramLong2, WebStorage.QuotaUpdater paramQuotaUpdater)
/*     */   {
/* 447 */     Logger.i("webview", "onReachedMaxAppCacheSize");
/*     */ 
/* 449 */     super.onReachedMaxAppCacheSize(paramLong1, paramLong2, paramQuotaUpdater);
/*     */   }
/*     */ 
/*     */   public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback)
/*     */   {
/* 455 */     Logger.i("webview", "onGeolocationPermissionsShowPrompt origin=" + paramString);
/* 456 */     paramCallback.invoke(paramString, true, false);
/* 457 */     super.onGeolocationPermissionsShowPrompt(paramString, paramCallback);
/*     */   }
/*     */ 
/*     */   public void onGeolocationPermissionsHidePrompt()
/*     */   {
/* 462 */     Logger.i("webview", "onGeolocationPermissionsHidePrompt");
/* 463 */     super.onGeolocationPermissionsHidePrompt();
/*     */   }
/*     */ 
/*     */   public void onRequestFocus(WebView paramWebView)
/*     */   {
/* 468 */     Logger.i("webview", "onRequestFocus");
/* 469 */     super.onRequestFocus(paramWebView);
/*     */   }
/*     */ 
/*     */   public void onReceivedTouchIconUrl(WebView paramWebView, String paramString, boolean paramBoolean)
/*     */   {
/* 476 */     Logger.d("super.onReceivedTouchIconUrl(view, url, precomposed");
/* 477 */     super.onReceivedTouchIconUrl(paramWebView, paramString, paramBoolean);
/*     */   }
/*     */ 
/*     */   public boolean onJsBeforeUnload(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
/*     */   {
/* 484 */     return super.onJsBeforeUnload(paramWebView, paramString1, paramString2, paramJsResult);
/*     */   }
/*     */ 
/*     */   public void onReceivedTitle(WebView paramWebView, String paramString)
/*     */   {
/* 489 */     this.mAdaWebview.unReceiveTitle = false;
/* 490 */     this.mAdaWebview.dispatchWebviewStateEvent(4, paramString);
/* 491 */     this.mAdaWebview.mFrameView.dispatchFrameViewEvents("titleUpdate", paramString);
/* 492 */     this.mAdaWebview.mWebViewImpl.mPageTitle = paramString;
/* 493 */     Logger.i("webview", "onReceivedTitle title=" + paramString);
/* 494 */     this.mAdaWebview.mLoadCompleted = true;
/* 495 */     super.onReceivedTitle(paramWebView, paramString);
/*     */   }
/*     */ 
/*     */   public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
/*     */   {
/* 505 */     showCustomView(paramView, paramCustomViewCallback);
/*     */   }
/*     */ 
/*     */   public void onHideCustomView()
/*     */   {
/* 510 */     hideCustomView();
/*     */   }
/*     */ 
/*     */   public void showCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
/*     */   {
/* 516 */     Log.d("webview", "showing Custom View");
/*     */ 
/* 518 */     if (this.mCustomView != null) {
/* 519 */       paramCustomViewCallback.onCustomViewHidden();
/* 520 */       return;
/*     */     }
/*     */ 
/* 523 */     paramView.setBackgroundDrawable(CanvasHelper.getDrawable());
/* 524 */     if ((DeviceInfo.sModel.equals("HUAWEI MT1-U06")) || (DeviceInfo.sModel.equals("SM-T310")) || (DeviceInfo.sModel.equals("vivo Y51A"))) {
/* 525 */       this.mAdaWebview.obtainFrameView().obtainApp().registerSysEventListener(new ISysEventListener()
/*     */       {
/*     */         public boolean onExecute(ISysEventListener.SysEventType paramAnonymousSysEventType, Object paramAnonymousObject)
/*     */         {
/* 529 */           if (paramAnonymousSysEventType == ISysEventListener.SysEventType.onKeyUp) {
/* 530 */             Object[] arrayOfObject = (Object[])paramAnonymousObject;
/* 531 */             int i = ((Integer)arrayOfObject[0]).intValue();
/* 532 */             if (i == 4) {
/* 533 */               WebJsEvent.this.onHideCustomView();
/* 534 */               WebJsEvent.this.mAdaWebview.obtainFrameView().obtainApp().unregisterSysEventListener(this, ISysEventListener.SysEventType.onKeyUp);
/* 535 */               return true;
/*     */             }
/*     */           }
/* 538 */           return false;
/*     */         }
/*     */       }
/*     */       , ISysEventListener.SysEventType.onKeyUp);
/*     */     }
/*     */ 
/* 542 */     this.mCustomView = paramView;
/*     */ 
/* 545 */     View localView = null;
/*     */ 
/* 547 */     if (((this.mCustomView instanceof ViewGroup)) && (0 < ((ViewGroup)this.mCustomView).getChildCount()))
/*     */     {
/* 550 */       localView = ((ViewGroup)this.mCustomView).getChildAt(0);
/* 551 */       if (((localView instanceof ViewGroup)) && (0 < ((ViewGroup)localView).getChildCount()))
/*     */       {
/* 554 */         localView = ((ViewGroup)localView).getChildAt(0);
/*     */       }
/*     */     }
/* 557 */     if (null != localView) {
/* 558 */       localObject1 = new FrameLayout.LayoutParams(-1, -1);
/* 559 */       ((FrameLayout.LayoutParams)localObject1).setMargins(0, 0, 0, 0);
/* 560 */       ((FrameLayout.LayoutParams)localObject1).gravity = 17;
/* 561 */       localView.setPadding(0, 0, 0, 0);
/* 562 */       localView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
/* 563 */       localView.invalidate();
/*     */     }
/* 565 */     this.mCustomViewCallback = paramCustomViewCallback;
/*     */ 
/* 567 */     Object localObject1 = (ViewGroup)this.mAdaWebview.obtainMainView().getParent();
/* 568 */     ((ViewGroup)localObject1).addView(paramView, COVER_SCREEN_GRAVITY_CENTER);
/*     */ 
/* 571 */     ((ViewGroup)localObject1).setVisibility(0);
/* 572 */     ((ViewGroup)localObject1).bringToFront();
/*     */ 
/* 575 */     this.mAdaWebview.obtainMainView().setVisibility(8);
/*     */ 
/* 577 */     if ((this.mAdaWebview.obtainMainView().getContext() instanceof Activity)) {
/* 578 */       Activity localActivity = (Activity)this.mAdaWebview.obtainMainView().getContext();
/*     */ 
/* 580 */       if (BaseInfo.isShowTitleBar(localActivity.getBaseContext())) {
/* 581 */         Object localObject2 = PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "isTitlebarVisible", null, new Class[] { Activity.class, String.class }, new Object[] { localActivity, this.mAdaWebview.obtainApp().obtainAppId() });
/*     */ 
/* 583 */         boolean bool = false;
/* 584 */         if ((localObject2 instanceof Boolean)) {
/* 585 */           bool = Boolean.valueOf(localObject2.toString()).booleanValue();
/*     */         }
/* 587 */         this.mDefaultTitleBarVisibility = bool;
/*     */ 
/* 589 */         PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "setTitlebarVisibleNow", null, new Class[] { Activity.class, Boolean.TYPE, Boolean.TYPE }, new Object[] { localActivity, Boolean.valueOf(false), Boolean.valueOf(false) });
/*     */       }
/*     */ 
/* 594 */       setStatusBarVisibility(localActivity, false);
/*     */ 
/* 597 */       String str = this.mAdaWebview.getWebviewProperty("videoFullscreen");
/*     */       int i;
/* 598 */       if ("landscape".equals(str))
/* 599 */         i = 6;
/* 600 */       else if ("landscape-primary".equals(str))
/* 601 */         i = 0;
/* 602 */       else if ("landscape-secondary".equals(str))
/* 603 */         i = 8;
/* 604 */       else if ("portrait-primary".equals(str))
/* 605 */         i = 1;
/* 606 */       else if ("portrait-secondary".equals(str))
/* 607 */         i = 9;
/*     */       else {
/* 609 */         i = localActivity.getRequestedOrientation();
/*     */       }
/* 611 */       if (localActivity.getRequestedOrientation() != i) {
/* 612 */         if (-2 == this.mDefaultScreemOrientation) {
/* 613 */           this.mDefaultScreemOrientation = localActivity.getRequestedOrientation();
/*     */         }
/* 615 */         this.mScreemOrientationChanged = true;
/* 616 */         AdaWebview.ScreemOrientationChangedNeedLayout = true;
/* 617 */         localActivity.setRequestedOrientation(i);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void hideCustomView()
/*     */   {
/* 624 */     Log.d("webview", "Hidding Custom View");
/* 625 */     if (this.mCustomView == null) return;
/*     */ 
/* 628 */     this.mAdaWebview.obtainMainView().setVisibility(0);
/*     */ 
/* 631 */     this.mCustomView.setVisibility(8);
/*     */ 
/* 634 */     ViewGroup localViewGroup = (ViewGroup)this.mAdaWebview.obtainMainView().getParent();
/* 635 */     localViewGroup.removeView(this.mCustomView);
/* 636 */     this.mCustomView = null;
/* 637 */     this.mCustomViewCallback.onCustomViewHidden();
/*     */ 
/* 639 */     if ((this.mAdaWebview.obtainMainView().getContext() instanceof Activity)) {
/* 640 */       Activity localActivity = (Activity)this.mAdaWebview.obtainMainView().getContext();
/*     */ 
/* 642 */       if (BaseInfo.isShowTitleBar(localActivity.getBaseContext())) {
/* 643 */         PlatformUtil.invokeMethod("io.dcloud.appstream.actionbar.StreamAppActionBarUtil", "setTitlebarVisibleNow", null, new Class[] { Activity.class, Boolean.TYPE, Boolean.TYPE }, new Object[] { localActivity, Boolean.valueOf(this.mDefaultTitleBarVisibility), Boolean.valueOf(true) });
/*     */       }
/*     */ 
/* 648 */       setStatusBarVisibility(localActivity, true);
/*     */ 
/* 650 */       if (this.mScreemOrientationChanged) {
/* 651 */         this.mScreemOrientationChanged = false;
/* 652 */         AdaWebview.ScreemOrientationChangedNeedLayout = true;
/* 653 */         localActivity.setRequestedOrientation(this.mDefaultScreemOrientation);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setStatusBarVisibility(Activity paramActivity, boolean paramBoolean)
/*     */   {
/* 676 */     int i = paramBoolean ? 0 : 1024;
/* 677 */     if (null != paramActivity)
/* 678 */       paramActivity.getWindow().setFlags(i, 1024);
/*     */   }
/*     */ 
/*     */   class DialogListener
/*     */     implements DialogInterface.OnClickListener
/*     */   {
/* 659 */     JsResult mResult = null;
/*     */ 
/*     */     DialogListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void onClick(DialogInterface paramDialogInterface, int paramInt) {
/* 666 */       this.mResult.cancel();
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.WebJsEvent
 * JD-Core Version:    0.6.2
 */