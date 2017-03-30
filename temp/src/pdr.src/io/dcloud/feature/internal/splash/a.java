/*    */ package io.dcloud.feature.internal.splash;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class a
/*    */ {
/* 13 */   private static HashMap<String, Activity> b = new HashMap();
/*    */ 
/* 60 */   static ArrayList<String> a = new ArrayList();
/*    */ 
/*    */   public static void a(String paramString, Activity paramActivity)
/*    */   {
/* 15 */     b.put(paramString, paramActivity);
/*    */   }
/*    */ 
/*    */   public static void a(String paramString)
/*    */   {
/* 66 */     a.add(paramString);
/*    */   }
/*    */ 
/*    */   public static void b(String paramString) {
/* 70 */     a.remove(paramString);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.feature.internal.splash.a
 * JD-Core Version:    0.6.2
 */