/*     */ package io.dcloud.common.DHInterface;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.res.Configuration;
/*     */ import android.os.Bundle;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class BaseFeature
/*     */   implements IBoot, IDPlugin, IFeature, IReflectAble, ISysEventListener
/*     */ {
/*     */   protected String mFeatureName;
/*     */   protected AbsMgr mFeatureMgr;
/*     */   protected Context mApplicationContext;
/*     */   private Context mDPluginContext;
/*     */   private Activity mDPluginActivity;
/*     */   protected ArrayList<BaseModule> mModules;
/*     */ 
/*     */   public BaseFeature()
/*     */   {
/*  30 */     this.mFeatureName = null;
/*  31 */     this.mFeatureMgr = null;
/*  32 */     this.mApplicationContext = null;
/*     */ 
/*  62 */     this.mDPluginContext = null;
/*  63 */     this.mDPluginActivity = null;
/*     */ 
/* 172 */     this.mModules = null;
/*     */   }
/*     */ 
/*     */   public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
/*     */   {
/*  40 */     return null;
/*     */   }
/*     */ 
/*     */   public String execute(IWebview paramIWebview, String paramString, JSONArray paramJSONArray)
/*     */   {
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */   public void init(AbsMgr paramAbsMgr, String paramString)
/*     */   {
/*  57 */     this.mFeatureMgr = paramAbsMgr;
/*  58 */     this.mApplicationContext = paramAbsMgr.getContext();
/*  59 */     this.mFeatureName = paramString;
/*     */   }
/*     */ 
/*     */   public void initDPlugin(Context paramContext, Activity paramActivity)
/*     */   {
/*  66 */     this.mDPluginContext = paramContext;
/*  67 */     this.mDPluginActivity = paramActivity;
/*     */   }
/*     */ 
/*     */   public Context getDPluginContext() {
/*  71 */     return this.mDPluginContext == null ? this.mApplicationContext : this.mDPluginContext;
/*     */   }
/*     */ 
/*     */   public Activity getDPluginActivity()
/*     */   {
/*  76 */     return this.mDPluginActivity;
/*     */   }
/*     */ 
/*     */   public boolean isOldMode() {
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   public void dispose(String paramString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final void registerSysEvent(IWebview paramIWebview, ISysEventListener.SysEventType paramSysEventType)
/*     */   {
/* 104 */     paramIWebview.obtainApp().registerSysEventListener(this, paramSysEventType);
/*     */   }
/*     */ 
/*     */   public final void unregisterSysEvent(IWebview paramIWebview, ISysEventListener.SysEventType paramSysEventType)
/*     */   {
/* 112 */     paramIWebview.obtainApp().unregisterSysEventListener(this, paramSysEventType);
/*     */   }
/*     */ 
/*     */   public final void registerSysEvent(IWebview paramIWebview)
/*     */   {
/* 119 */     registerSysEvent(paramIWebview, ISysEventListener.SysEventType.AllSystemEvent);
/*     */   }
/*     */ 
/*     */   public final void unregisterSysEvent(IWebview paramIWebview)
/*     */   {
/* 126 */     unregisterSysEvent(paramIWebview, ISysEventListener.SysEventType.AllSystemEvent);
/*     */   }
/*     */   protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
/*     */   }
/*     */   protected void onNewIntent(Intent paramIntent) {
/*     */   }
/*     */   protected void onConfigurationChanged(Configuration paramConfiguration) {  } 
/*     */   protected void onSaveInstanceState(Bundle paramBundle) {  } 
/*     */   protected void onLowMemory() {  } 
/* 135 */   public boolean doHandleAction(String paramString) { return false; }
/*     */ 
/*     */ 
/*     */   public void onReceiver(Intent paramIntent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onPause()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onResume()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onStop()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void onStart(Context paramContext, Bundle paramBundle, String[] paramArrayOfString)
/*     */   {
/*     */   }
/*     */ 
/*     */   public final boolean onExecute(ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*     */   {
/*     */     Object localObject1;
/*     */     Object localObject2;
/* 143 */     if (paramSysEventType == ISysEventListener.SysEventType.onActivityResult) {
/* 144 */       localObject1 = (Object[])paramObject;
/* 145 */       int i = ((Integer)localObject1[0]).intValue();
/* 146 */       int j = ((Integer)localObject1[1]).intValue();
/* 147 */       localObject2 = (Intent)localObject1[2];
/* 148 */       onActivityResult(i, j, (Intent)localObject2);
/* 149 */     } else if (paramSysEventType == ISysEventListener.SysEventType.onStart) {
/* 150 */       localObject1 = (Object[])paramObject;
/* 151 */       Context localContext = (Context)localObject1[0];
/* 152 */       Bundle localBundle = (Bundle)localObject1[1];
/* 153 */       localObject2 = (String[])localObject1[3];
/* 154 */       onStart(localContext, localBundle, (String[])localObject2);
/* 155 */     } else if (paramSysEventType == ISysEventListener.SysEventType.onPause) {
/* 156 */       onPause();
/* 157 */     } else if (paramSysEventType == ISysEventListener.SysEventType.onStop) {
/* 158 */       onStop();
/* 159 */     } else if (paramSysEventType == ISysEventListener.SysEventType.onResume) {
/* 160 */       onResume();
/* 161 */     } else if (paramSysEventType == ISysEventListener.SysEventType.onNewIntent) {
/* 162 */       localObject1 = (Intent)paramObject;
/* 163 */       onNewIntent((Intent)localObject1);
/* 164 */     } else if (paramSysEventType == ISysEventListener.SysEventType.onSaveInstanceState) {
/* 165 */       localObject1 = (Bundle)paramObject;
/* 166 */       onSaveInstanceState((Bundle)localObject1);
/*     */     }
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   public BaseModule getBaseModuleById(String paramString)
/*     */   {
/* 175 */     if (this.mModules != null) {
/* 176 */       for (BaseModule localBaseModule : this.mModules) {
/* 177 */         if (localBaseModule.id.equals(paramString)) {
/* 178 */           return localBaseModule;
/*     */         }
/*     */       }
/*     */     }
/* 182 */     return null;
/*     */   }
/*     */   public ArrayList<BaseModule> loadModules() {
/* 185 */     if (this.mModules != null) {
/* 186 */       return this.mModules;
/*     */     }
/* 188 */     this.mModules = new ArrayList();
/*     */ 
/* 190 */     HashMap localHashMap = (HashMap)this.mFeatureMgr.processEvent(IMgr.MgrType.FeatureMgr, 4, this.mFeatureName);
/* 191 */     if ((localHashMap != null) && (!localHashMap.isEmpty())) {
/* 192 */       Set localSet = localHashMap.keySet();
/* 193 */       for (String str1 : localSet) {
/* 194 */         String str2 = (String)localHashMap.get(str1);
/*     */         try
/*     */         {
/* 197 */           Object localObject = Class.forName(str2).newInstance();
/* 198 */           if ((localObject instanceof BaseModule)) {
/* 199 */             BaseModule localBaseModule = (BaseModule)localObject;
/* 200 */             localBaseModule.init(this.mApplicationContext);
/* 201 */             localBaseModule.name = str1;
/* 202 */             localBaseModule.featureName = this.mFeatureName;
/* 203 */             if (localBaseModule.id == null) {
/* 204 */               localBaseModule.id = str1;
/*     */             }
/* 206 */             this.mModules.add(localBaseModule);
/*     */           }
/*     */         } catch (InstantiationException localInstantiationException) {
/* 209 */           localInstantiationException.printStackTrace();
/*     */         } catch (IllegalAccessException localIllegalAccessException) {
/* 211 */           localIllegalAccessException.printStackTrace();
/*     */         } catch (ClassNotFoundException localClassNotFoundException) {
/* 213 */           Logger.e(localClassNotFoundException.getLocalizedMessage());
/*     */         }
/*     */       }
/*     */     }
/* 217 */     return this.mModules;
/*     */   }
/*     */   protected JSONArray toModuleJSONArray() throws JSONException {
/* 220 */     JSONArray localJSONArray = new JSONArray();
/* 221 */     if (this.mModules != null) {
/* 222 */       int i = this.mModules.size();
/* 223 */       for (int j = 0; j < i; j++) {
/* 224 */         BaseModule localBaseModule = (BaseModule)this.mModules.get(j);
/* 225 */         localJSONArray.put(localBaseModule.toJSONObject());
/*     */       }
/*     */     }
/* 228 */     return localJSONArray;
/*     */   }
/*     */ 
/*     */   public static abstract class BaseModule
/*     */   {
/*     */     public Context mApplicationContext;
/* 234 */     public String featureName = null;
/*     */ 
/* 238 */     public String name = null;
/*     */ 
/* 242 */     public String id = null;
/*     */ 
/* 246 */     public String description = null;
/*     */ 
/* 248 */     public void init(Context paramContext) { this.mApplicationContext = paramContext; }
/*     */ 
/*     */     public String getFullDescription() {
/* 251 */       return this.featureName + this.description;
/*     */     }
/*     */ 
/*     */     public abstract JSONObject toJSONObject()
/*     */       throws JSONException;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.BaseFeature
 * JD-Core Version:    0.6.2
 */