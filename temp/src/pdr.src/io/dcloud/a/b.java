/*    */ package io.dcloud.a;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class b
/*    */ {
/*    */   private HashMap<String, Map<String, String>> a;
/*    */ 
/*    */   private b()
/*    */   {
/* 14 */     this.a = new HashMap();
/*    */   }
/*    */ 
/*    */   public static b a()
/*    */   {
/* 22 */     return a.a;
/*    */   }
/*    */ 
/*    */   public void a(String paramString, Map<String, String> paramMap) {
/* 26 */     this.a.put(paramString, paramMap);
/*    */   }
/*    */ 
/*    */   public Map<String, String> a(String paramString) {
/* 30 */     return (Map)this.a.get(paramString);
/*    */   }
/*    */ 
/*    */   private static class a
/*    */   {
/* 18 */     public static final b a = new b(null);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.a.b
 * JD-Core Version:    0.6.2
 */