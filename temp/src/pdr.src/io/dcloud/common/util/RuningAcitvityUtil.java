/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.app.ActivityManager;
/*    */ import android.app.ActivityManager.RunningTaskInfo;
/*    */ import android.content.ComponentName;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class RuningAcitvityUtil
/*    */ {
/* 20 */   private static HashMap<String, Activity> RuningActivitys = new HashMap();
/*    */ 
/*    */   public static void putRuningActivity(Activity paramActivity)
/*    */   {
/* 27 */     if (!RuningActivitys.containsValue(paramActivity))
/* 28 */       RuningActivitys.put(paramActivity.getComponentName().getClassName(), paramActivity);
/*    */   }
/*    */ 
/*    */   public static void removeRuningActivity(String paramString)
/*    */   {
/* 37 */     if (RuningActivitys.containsKey(paramString))
/* 38 */       RuningActivitys.remove(paramString);
/*    */   }
/*    */ 
/*    */   public static Activity getTopRuningActivity(Activity paramActivity)
/*    */   {
/*    */     Object localObject1;
/*    */     Object localObject2;
/*    */     Object localObject3;
/* 48 */     if (BaseInfo.isForQihooHelper(paramActivity)) {
/* 49 */       localObject1 = RuningActivitys.entrySet().iterator();
/* 50 */       if (((Iterator)localObject1).hasNext()) {
/* 51 */         localObject2 = (Map.Entry)((Iterator)localObject1).next();
/* 52 */         localObject3 = (Activity)((Map.Entry)localObject2).getValue();
/*    */ 
/* 54 */         if ((localObject3 != null) && (!((Activity)localObject3).isFinishing()))
/* 55 */           return localObject3;
/*    */       }
/*    */     }
/*    */     else {
/* 59 */       localObject1 = (ActivityManager)paramActivity.getSystemService("activity");
/* 60 */       localObject2 = ((ActivityManager)localObject1).getRunningTasks(1);
/* 61 */       localObject3 = (ActivityManager.RunningTaskInfo)((List)localObject2).get(0);
/* 62 */       if (RuningActivitys.containsKey(((ActivityManager.RunningTaskInfo)localObject3).topActivity.getClassName())) {
/* 63 */         return (Activity)RuningActivitys.get(((ActivityManager.RunningTaskInfo)localObject3).topActivity.getClassName());
/*    */       }
/*    */     }
/* 66 */     return paramActivity;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.RuningAcitvityUtil
 * JD-Core Version:    0.6.2
 */