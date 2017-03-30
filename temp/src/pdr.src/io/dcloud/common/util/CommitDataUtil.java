/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.Context;
/*    */ import android.content.SharedPreferences;
/*    */ import android.content.pm.PackageInfo;
/*    */ import android.content.pm.PackageManager;
/*    */ import android.text.TextUtils;
/*    */ import io.dcloud.common.DHInterface.IApp;
/*    */ import io.dcloud.common.adapter.util.AndroidResources;
/*    */ import io.dcloud.common.constant.DataInterface;
/*    */ import java.net.URLEncoder;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.json.JSONArray;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class CommitDataUtil
/*    */ {
/*    */   public static String getStartData(IApp paramIApp, SharedPreferences paramSharedPreferences)
/*    */   {
/* 28 */     StringBuffer localStringBuffer = new StringBuffer();
/*    */     try {
/* 30 */       localStringBuffer.append(DataInterface.getUrlBaseData(paramIApp.getActivity(), paramIApp.obtainAppId(), BaseInfo.getLaunchType(paramIApp.getActivity().getIntent()), null));
/* 31 */       localStringBuffer.append("&st=" + BaseInfo.run5appEndTime);
/* 32 */       localStringBuffer.append("&pn=" + paramIApp.getActivity().getPackageName());
/* 33 */       localStringBuffer.append("&v=" + paramIApp.obtainAppVersionName());
/* 34 */       localStringBuffer.append("&pv=" + AndroidResources.versionName);
/* 35 */       localStringBuffer.append("&name=" + URLEncoder.encode(paramIApp.obtainAppName(), "utf-8"));
/* 36 */       long l = paramSharedPreferences.getLong("commit_app_list_time", 0L);
/*    */ 
/* 38 */       if ((System.currentTimeMillis() - l) / 100000L >= 26000L) {
/* 39 */         localStringBuffer.append("&apps=" + getNoSystemAppData(paramIApp.getActivity()));
/* 40 */         localStringBuffer.append("&imsi=" + TelephonyUtil.getIMSI(paramIApp.getActivity()));
/* 41 */         JSONObject localJSONObject = new JSONObject();
/* 42 */         localJSONObject.put("pn", LauncherUtil.getLauncherPackageName(paramIApp.getActivity()));
/* 43 */         localStringBuffer.append("&launcher=" + localJSONObject.toString());
/* 44 */         if (!TextUtils.isEmpty(paramSharedPreferences.getString("geo_data", "")))
/* 45 */           localStringBuffer.append("&pos=" + paramSharedPreferences.getString("geo_data", ""));
/*    */       }
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 50 */       localException.printStackTrace();
/*    */     }
/* 52 */     return localStringBuffer.toString();
/*    */   }
/*    */ 
/*    */   public static String getNoSystemAppData(Context paramContext)
/*    */     throws Exception
/*    */   {
/* 62 */     PackageManager localPackageManager = paramContext.getPackageManager();
/* 63 */     List localList = ApkUtils.getInstalledNonSystemApps(paramContext, localPackageManager);
/* 64 */     JSONArray localJSONArray = new JSONArray();
/* 65 */     for (Object localObject = localList.iterator(); ((Iterator)localObject).hasNext(); ) { PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
/*    */ 
/* 67 */       if (!paramContext.getPackageName().equals(localPackageInfo.packageName)) {
/* 68 */         JSONObject localJSONObject = new JSONObject();
/* 69 */         String str = (String)localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo);
/* 70 */         localJSONObject.put("name", str);
/* 71 */         localJSONObject.put("pn", localPackageInfo.packageName);
/* 72 */         localJSONObject.put("ver", localPackageInfo.versionName);
/* 73 */         localJSONArray.put(localJSONObject);
/*    */       }
/*    */     }
/* 76 */     localObject = URLEncoder.encode(localJSONArray.toString(), "UTF-8");
/* 77 */     return localObject;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.CommitDataUtil
 * JD-Core Version:    0.6.2
 */