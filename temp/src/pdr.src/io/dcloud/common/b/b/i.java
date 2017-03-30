/*     */ package io.dcloud.common.b.b;
/*     */ 
/*     */ import android.app.Dialog;
/*     */ import android.content.Context;
/*     */ import android.text.TextUtils;
/*     */ import io.dcloud.common.DHInterface.AbsMgr;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IJsInterface;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.IReflectAble;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper;
/*     */ import io.dcloud.common.adapter.util.InvokeExecutorHelper.InvokeExecutor;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MessageHandler;
/*     */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*     */ import io.dcloud.common.b.a.a;
/*     */ import io.dcloud.common.util.ErrorDialogUtil;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import io.dcloud.common.util.TestUtil;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ 
/*     */ class i
/*     */   implements IJsInterface, IReflectAble
/*     */ {
/* 108 */   AbsMgr a = null;
/* 109 */   IWebview b = null;
/* 110 */   String c = null;
/*     */ 
/* 116 */   static boolean d = false;
/* 117 */   MessageHandler.IMessages e = new MessageHandler.IMessages()
/*     */   {
/*     */     public void execute(Object paramAnonymousObject) {
/* 120 */       Object[] arrayOfObject = (Object[])paramAnonymousObject;
/* 121 */       String str1 = String.valueOf(arrayOfObject[0]);
/* 122 */       String str2 = String.valueOf(arrayOfObject[1]);
/* 123 */       JSONArray localJSONArray = (JSONArray)arrayOfObject[2];
/* 124 */       i.this.exec(str1, str2, localJSONArray);
/*     */     }
/* 117 */   };
/*     */ 
/* 165 */   static final Class[] f = { String.class, String.class };
/*     */ 
/*     */   i(IWebview paramIWebview)
/*     */   {
/* 112 */     this.b = paramIWebview;
/* 113 */     this.c = this.b.obtainFrameView().obtainApp().obtainAppId();
/* 114 */     this.a = this.b.obtainFrameView().obtainWindowMgr();
/*     */   }
/*     */ 
/*     */   public void forceStop(String paramString)
/*     */   {
/* 130 */     if (!TextUtils.isEmpty(paramString))
/* 131 */       this.a.processEvent(IMgr.MgrType.WindowMgr, 20, paramString);
/*     */   }
/*     */ 
/*     */   public String prompt(String paramString1, String paramString2)
/*     */   {
/* 137 */     if (!d) {
/* 138 */       TestUtil.record("JsInterfaceImpl", Thread.currentThread());
/* 139 */       d = true;
/*     */     }
/* 141 */     String str1 = null;
/* 142 */     if ((paramString2 != null) && (paramString2.length() > 3) && (paramString2.substring(0, 4).equals("pdr:"))) {
/* 143 */       long l = System.currentTimeMillis();
/*     */       try
/*     */       {
/* 146 */         JSONArray localJSONArray1 = new JSONArray(paramString2.substring(4));
/* 147 */         String str2 = localJSONArray1.getString(0);
/* 148 */         String str3 = localJSONArray1.getString(1);
/* 149 */         boolean bool = localJSONArray1.getBoolean(2);
/* 150 */         JSONArray localJSONArray2 = JSONUtil.createJSONArray(paramString1);
/* 151 */         if (bool)
/* 152 */           MessageHandler.sendMessage(this.e, new Object[] { str2, str3, localJSONArray2 });
/*     */         else
/* 154 */           str1 = exec(str2, str3, localJSONArray2);
/*     */       }
/*     */       catch (JSONException localJSONException) {
/* 157 */         localJSONException.printStackTrace();
/*     */       }
/*     */     }
/* 160 */     return str1;
/*     */   }
/*     */   public String exec(String paramString1, String paramString2, String paramString3) {
/* 163 */     return exec(paramString1, paramString2, JSONUtil.createJSONArray(paramString3));
/*     */   }
/*     */ 
/*     */   public String exec(String paramString1, String paramString2, JSONArray paramJSONArray)
/*     */   {
/*     */     try
/*     */     {
/* 170 */       String str1 = this.c;
/* 171 */       paramString1 = paramString1.toLowerCase();
/* 172 */       InvokeExecutorHelper.QihooInnerStatisticUtil.invoke("statOnEvent", f, new Object[] { str1, paramString1 });
/* 173 */       boolean bool = "io.dcloud.HBuilder".equals(this.b.getContext().getPackageName());
/* 174 */       int i = bool ? 0 : (this.b.obtainFrameView().getFrameType() == 3) || (a.a(str1, paramString1)) ? 1 : 1;
/*     */       Object localObject;
/* 175 */       if (i != 0) {
/* 176 */         localObject = this.a.processEvent(IMgr.MgrType.FeatureMgr, 1, new Object[] { this.b, paramString1, paramString2, paramJSONArray });
/* 177 */         return String.valueOf(localObject);
/* 178 */       }if ((!this.b.obtainApp().isStreamApp()) && (!a.b(str1, paramString1)))
/*     */       {
/* 180 */         localObject = a.c(paramString1);
/* 181 */         if (PdrUtil.isEmpty(localObject)) {
/* 182 */           localObject = "应用未添加" + paramString1 + "权限，请在manifest.json文件中permissions节点进行配置，";
/*     */         }
/* 184 */         a(this.b, paramString1);
/* 185 */         Logger.e("dhwebview", "_appid=" + str1 + (String)localObject);
/* 186 */         String str2 = JSUtil.consoleTest(JSONUtil.toJSONableString((String)localObject));
/* 187 */         this.b.executeScript(str2);
/* 188 */         return "null";
/*     */       }
/*     */     } catch (Exception localException) {
/* 191 */       Logger.w("JsInterfaceImpl.exec pApiFeatureName=" + paramString1 + ";pActionName=" + paramString2 + ";pArgs=" + String.valueOf(paramJSONArray), localException);
/*     */     }
/* 193 */     return null;
/*     */   }
/*     */   public void a(IWebview paramIWebview, String paramString) {
/* 196 */     String str = String.format("manifest.json中未添加%s模块, 请参考 http://ask.dcloud.net.cn/article/283", new Object[] { paramString });
/* 197 */     Dialog localDialog = ErrorDialogUtil.getLossDialog(this.b, str, "http://ask.dcloud.net.cn/article/283", paramString);
/* 198 */     if (localDialog != null)
/* 199 */       localDialog.show();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.b.i
 * JD-Core Version:    0.6.2
 */