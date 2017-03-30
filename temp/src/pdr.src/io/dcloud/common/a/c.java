/*    */ package io.dcloud.common.a;
/*    */ 
/*    */ import io.dcloud.common.DHInterface.AbsMgr;
/*    */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*    */ import io.dcloud.common.adapter.util.MessageHandler;
/*    */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ class c
/*    */ {
/* 23 */   AbsMgr a = null;
/*    */ 
/* 25 */   c(AbsMgr paramAbsMgr) { this.a = paramAbsMgr; }
/*    */ 
/*    */   private ArrayList<a> b(String paramString)
/*    */   {
/* 29 */     ArrayList localArrayList1 = new ArrayList(1);
/* 30 */     if (paramString.startsWith("snc:")) {
/* 31 */       paramString = paramString.substring("snc:".length());
/*    */     }
/* 33 */     paramString = paramString.trim();
/* 34 */     int i = 0;
/* 35 */     int j = paramString.length();
/* 36 */     int k = 0;
/* 37 */     ArrayList localArrayList2 = new ArrayList();
/* 38 */     while (i < j) {
/* 39 */       char c = paramString.charAt(i);
/* 40 */       i++;
/* 41 */       if ((i == j) || ((b(c)) && (localArrayList2.size() % 2 == 0)) || (a(c)))
/*    */       {
/* 44 */         String str = paramString.substring(k, i).trim();
/* 45 */         if (!"".equals(str)) {
/* 46 */           k = i;
/* 47 */           localArrayList2.add(str);
/*    */         }
/*    */       }
/*    */     }
/* 51 */     int m = localArrayList2.size();
/* 52 */     for (int n = 0; n < m; ) {
/* 53 */       a locala = new a();
/* 54 */       locala.a = ((String)localArrayList2.get(n));
/* 55 */       locala.b = ((String)localArrayList2.get(n + 1));
/* 56 */       n += 2;
/* 57 */       localArrayList1.add(locala);
/*    */     }
/* 59 */     return localArrayList1;
/*    */   }
/*    */ 
/*    */   void a(final String paramString) {
/* 63 */     MessageHandler.sendMessage(new MessageHandler.IMessages()
/*    */     {
/*    */       public void execute(Object paramAnonymousObject) {
/* 66 */         ArrayList localArrayList = c.a(c.this, paramString);
/* 67 */         for (c.a locala : localArrayList)
/* 68 */           if ("update".equals(paramString)) {
/* 69 */             String str = locala.b;
/* 70 */             if ("all".equals(str))
/* 71 */               c.this.a.processEvent(IMgr.MgrType.WindowMgr, 13, null);
/* 72 */             else if ("current".equals(str))
/* 73 */               c.this.a.processEvent(IMgr.MgrType.WindowMgr, 12, null);
/*    */             else
/* 75 */               c.this.a.processEvent(IMgr.MgrType.WindowMgr, 14, str);
/*    */           }
/*    */       }
/*    */     }
/*    */     , null);
/*    */   }
/*    */ 
/*    */   boolean a(char paramChar)
/*    */   {
/* 87 */     return (paramChar == '\r') || (paramChar == '\n');
/*    */   }
/*    */ 
/*    */   boolean b(char paramChar) {
/* 91 */     return (paramChar == '\t') || (paramChar == '\013') || (paramChar == '\f') || (paramChar == ' ') || (paramChar == ' ') || (paramChar == '　');
/*    */   }
/*    */ 
/*    */   class a
/*    */   {
/*    */     String a;
/*    */     String b;
/*    */ 
/*    */     a()
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.a.c
 * JD-Core Version:    0.6.2
 */