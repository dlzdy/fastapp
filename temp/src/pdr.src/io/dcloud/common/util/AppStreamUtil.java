/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ 
/*    */ public class AppStreamUtil
/*    */ {
/*  7 */   public static HashMap<String, String> AppStreamGenuines = new HashMap();
/*    */ 
/*    */   public static boolean isAppStreamGenuine(String paramString)
/*    */   {
/* 42 */     return AppStreamGenuines.containsKey(paramString);
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 10 */     AppStreamGenuines.put("H5EC86117", "36Kr资讯");
/* 11 */     AppStreamGenuines.put("H592E7F63", "6人游旅行网");
/* 12 */     AppStreamGenuines.put("H5C4E96B4", "爱美丽");
/* 13 */     AppStreamGenuines.put("H5709B599", "宝贝租车");
/* 14 */     AppStreamGenuines.put("chaoyingyue", "超盈越");
/* 15 */     AppStreamGenuines.put("H5BCD03E4", "点评外卖");
/* 16 */     AppStreamGenuines.put("H510E2B40", "枫桥居花卉");
/* 17 */     AppStreamGenuines.put("H5E9801E3", "好记密码");
/* 18 */     AppStreamGenuines.put("HelloH5", "Hello H5+");
/* 19 */     AppStreamGenuines.put("HelloMUI", "Hello MUI");
/* 20 */     AppStreamGenuines.put("H5F563BDD", "HiMall");
/* 21 */     AppStreamGenuines.put("H51700FE4", "京东秒杀");
/* 22 */     AppStreamGenuines.put("H5336E745", "蓝昊数码");
/* 23 */     AppStreamGenuines.put("H5605AB01", "柳州1号");
/* 24 */     AppStreamGenuines.put("H5291D2691", "迷你课表");
/* 25 */     AppStreamGenuines.put("H54D773AC", "期待乐");
/* 26 */     AppStreamGenuines.put("H522034E0", "人脉返现");
/* 27 */     AppStreamGenuines.put("H532A4BFF", "挑食火锅");
/* 28 */     AppStreamGenuines.put("H5FCEFA0C", "我的冲印");
/* 29 */     AppStreamGenuines.put("H57D443FC", "养车屋");
/* 30 */     AppStreamGenuines.put("H52203BEA", "弈客围棋");
/* 31 */     AppStreamGenuines.put("H5EF8A9C1", "艺人捧场");
/* 32 */     AppStreamGenuines.put("H55BDF6BE", "甬派");
/* 33 */     AppStreamGenuines.put("H56022FE5", "有道词典");
/* 34 */     AppStreamGenuines.put("H559D7DDA", "智慧防汛");
/* 35 */     AppStreamGenuines.put("H5B680757", "中旋");
/* 36 */     AppStreamGenuines.put("H554D94D4", "装修云管家助手");
/* 37 */     AppStreamGenuines.put("H56F0FF05", "走着服务者");
/* 38 */     AppStreamGenuines.put("H50608789", "点点壁纸");
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.AppStreamUtil
 * JD-Core Version:    0.6.2
 */