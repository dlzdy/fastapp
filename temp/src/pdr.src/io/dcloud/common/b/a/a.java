/*     */ package io.dcloud.common.b.a;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.os.Build;
/*     */ import io.dcloud.common.DHInterface.IApp;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*     */ import io.dcloud.common.adapter.util.PlatformUtil;
/*     */ import io.dcloud.common.util.AppPermissionUtil;
/*     */ import io.dcloud.common.util.ShortCutUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public final class a
/*     */ {
/*  27 */   static HashMap<String, ArrayList<String>> a = new HashMap(2);
/*  28 */   static ArrayList<String> b = new ArrayList();
/*     */ 
/* 102 */   static HashMap<String, String> c = new HashMap(2);
/*     */ 
/*     */   public static void a(String paramString, ArrayList<String> paramArrayList)
/*     */   {
/*  36 */     a.remove(paramString);
/*  37 */     a.put(paramString, paramArrayList);
/*     */   }
/*     */ 
/*     */   public static void a(String paramString)
/*     */   {
/*  44 */     b.add(paramString);
/*     */   }
/*     */ 
/*     */   public static void b(String paramString)
/*     */   {
/*  51 */     b.remove(paramString);
/*     */   }
/*     */ 
/*     */   public static boolean a(String paramString1, String paramString2)
/*     */   {
/*  70 */     ArrayList localArrayList = (ArrayList)a.get(paramString1);
/*  71 */     return (b.contains(paramString1)) || ((localArrayList != null) && (localArrayList.contains(paramString2.toLowerCase())));
/*     */   }
/*     */ 
/*     */   public static boolean b(String paramString1, String paramString2)
/*     */   {
/*  81 */     return ("console".equals(paramString2)) || ("events".equals(paramString2.toLowerCase()));
/*     */   }
/*     */ 
/*     */   public static String c(String paramString)
/*     */   {
/* 100 */     return (String)c.get(paramString);
/*     */   }
/*     */ 
/*     */   public static String a(IWebview paramIWebview, String[] paramArrayOfString)
/*     */   {
/* 131 */     String str1 = paramArrayOfString[0];
/* 132 */     String str2 = paramIWebview.obtainApp().obtainAppId();
/* 133 */     String str3 = paramIWebview.obtainApp().obtainAppName();
/* 134 */     Context localContext = paramIWebview.getContext();
/* 135 */     if (str1.equals("SHORTCUT")) {
/* 136 */       if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.MEIZU)) {
/* 137 */         if (!AppPermissionUtil.isFlymeShortcutallowAllow(localContext, ShortCutUtil.getHeadShortCutIntent(str3))) {
/* 138 */           return "denied";
/*     */         }
/* 140 */         return "notdeny";
/*     */       }
/* 142 */       if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.SMARTISAN)) {
/* 143 */         if (MobilePhoneModel.isSmartisanLauncherPhone(localContext)) {
/* 144 */           return "denied";
/*     */         }
/* 146 */         return "notdeny";
/*     */       }
/*     */ 
/* 149 */       if (AppPermissionUtil.getShotCutOpId() != -1) {
/* 150 */         int i = AppPermissionUtil.getShotCutOpId();
/* 151 */         if (Build.BRAND.equalsIgnoreCase(MobilePhoneModel.XIAOMI)) {
/* 152 */           int j = AppPermissionUtil.checkOp(localContext);
/* 153 */           switch (j) {
/*     */           case 3:
/*     */           case 4:
/* 156 */             return "undetermined";
/*     */           case 1:
/* 158 */             return "denied";
/*     */           case 0:
/* 160 */             return "authorized";
/*     */           case -1:
/* 162 */             return "unsupported";
/*     */           case 2:
/*     */           }
/* 165 */         } else if (Build.MANUFACTURER.equalsIgnoreCase(MobilePhoneModel.HUAWEI)) {
/* 166 */           if (!AppPermissionUtil.isEmuiShortcutallowAllow()) {
/* 167 */             return "denied";
/*     */           }
/* 169 */           return "notdeny";
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 174 */       String str4 = d(str1);
/* 175 */       String str5 = a(paramIWebview.obtainApp().checkSelfPermission(str4));
/* 176 */       return String.valueOf(str5);
/*     */     }
/* 178 */     return "unknown";
/*     */   }
/*     */ 
/*     */   public static String d(String paramString) {
/* 182 */     if ("CAMERA".equals(paramString))
/* 183 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "CAMERA", null));
/* 184 */     if ("RECORD".equals(paramString)) {
/* 185 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "RECORD_AUDIO", null));
/*     */     }
/* 187 */     if ("LOCATION".equals(paramString))
/* 188 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "ACCESS_FINE_LOCATION", null));
/* 189 */     if ("CONTACTS".equals(paramString))
/* 190 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "WRITE_CONTACTS", null));
/* 191 */     if ("SHORTCUT".equals(paramString)) {
/* 192 */       return String.valueOf(PlatformUtil.invokeFieldValue("android.Manifest$permission", "INSTALL_SHORTCUT", null));
/*     */     }
/* 194 */     return paramString;
/*     */   }
/*     */ 
/*     */   public static String a(int paramInt)
/*     */   {
/* 199 */     switch (paramInt) {
/*     */     case -1:
/* 201 */       return "undetermined";
/*     */     case 0:
/* 204 */       return "authorized";
/*     */     }
/*     */ 
/* 207 */     return "unknown";
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 104 */     c.put("invocation", "应用未添加invocation模块，无法使用Native.js相关API，请在manifest.json文件中permissions下添加Invocation节点。");
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.b.a.a
 * JD-Core Version:    0.6.2
 */