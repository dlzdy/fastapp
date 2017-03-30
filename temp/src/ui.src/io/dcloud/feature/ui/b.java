/*     */ package io.dcloud.feature.ui;
/*     */ 
/*     */ import android.content.Context;
/*     */ import io.dcloud.common.DHInterface.IFrameView;
/*     */ import io.dcloud.common.DHInterface.IWebview;
/*     */ import io.dcloud.common.adapter.ui.AdaFrameItem;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.ViewRect;
/*     */ import io.dcloud.common.util.JSONUtil;
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import io.dcloud.common.util.JsEventUtil;
/*     */ import io.dcloud.common.util.PdrUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public abstract class b
/*     */ {
/*  23 */   protected c a = null;
/*  24 */   protected HashMap<IWebview, String> b = null;
/*  25 */   protected a c = null;
/*     */   protected String d;
/*  27 */   protected String e = null;
/*     */ 
/*  29 */   protected String f = null;
/*  30 */   protected JSONObject g = null;
/*  31 */   protected IWebview h = null;
/*  32 */   private byte q = k;
/*  33 */   private byte r = o;
/*     */   private Context s;
/* 139 */   protected HashMap<String, ArrayList<String[]>> i = null;
/*     */ 
/* 266 */   public static byte j = ViewRect.POSITION_STATIC;
/* 267 */   public static byte k = ViewRect.POSITION_ABSOLUTE;
/* 268 */   public static byte l = ViewRect.POSITION_DOCK;
/* 269 */   public static byte m = ViewRect.DOCK_LEFT;
/* 270 */   public static byte n = ViewRect.DOCK_RIGHT;
/* 271 */   public static byte o = ViewRect.DOCK_TOP;
/* 272 */   public static byte p = ViewRect.DOCK_BOTTOM;
/*     */ 
/*     */   public b(String paramString)
/*     */   {
/*  36 */     this.d = paramString;
/*  37 */     this.b = new HashMap();
/*     */   }
/*     */   public abstract String a(IWebview paramIWebview, String paramString, JSONArray paramJSONArray);
/*     */ 
/*     */   protected void a(Context paramContext, a parama, IWebview paramIWebview, String paramString, JSONObject paramJSONObject) {
/*  42 */     this.c = parama;
/*  43 */     this.s = paramContext;
/*  44 */     this.h = paramIWebview;
/*  45 */     this.e = paramString;
/*  46 */     this.g = (paramJSONObject == null ? JSONUtil.createJSONObject("{}") : paramJSONObject);
/*  47 */     a();
/*     */   }
/*     */   protected void a() {
/*  50 */     JSONObject localJSONObject = this.g;
/*  51 */     if ((!JSONUtil.isNull(localJSONObject, "id")) && (!PdrUtil.isEmpty(this.f))) {
/*  52 */       this.f = JSONUtil.getString(localJSONObject, "id");
/*     */     }
/*  54 */     String str1 = JSONUtil.getString(localJSONObject, "position");
/*  55 */     if (!PdrUtil.isEmpty(str1)) {
/*  56 */       if ("absolute".equals(str1))
/*  57 */         this.q = k;
/*  58 */       else if ("dock".equals(str1))
/*  59 */         this.q = l;
/*  60 */       else if ("static".equals(str1)) {
/*  61 */         this.q = j;
/*     */       }
/*     */     }
/*  64 */     String str2 = JSONUtil.getString(localJSONObject, "dock");
/*  65 */     if (!PdrUtil.isEmpty(str2))
/*  66 */       if ("bottom".equals(str2))
/*  67 */         this.r = p;
/*  68 */       else if ("top".equals(str2))
/*  69 */         this.r = o;
/*  70 */       else if ("left".equals(str2))
/*  71 */         this.r = m;
/*  72 */       else if ("right".equals(str2))
/*  73 */         this.r = n;
/*     */   }
/*     */ 
/*     */   public final byte b()
/*     */   {
/*  92 */     return this.q;
/*     */   }
/*     */ 
/*     */   public final Context c()
/*     */   {
/*  99 */     return this.s;
/*     */   }
/*     */   public String d() {
/* 102 */     String str = "(function(){return {uuid:'%s',identity:'%s',option:%s}})()";
/* 103 */     return String.format(str, new Object[] { this.e, this.d, this.g });
/*     */   }
/*     */ 
/*     */   public abstract AdaFrameItem e();
/*     */ 
/*     */   public abstract void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
/*     */ 
/*     */   protected void a(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 142 */     if (this.i == null) {
/* 143 */       this.i = new HashMap(2);
/*     */     }
/* 145 */     ArrayList localArrayList = (ArrayList)this.i.get(paramString2);
/* 146 */     if (localArrayList == null) {
/* 147 */       localArrayList = new ArrayList(2);
/* 148 */       this.i.put(paramString2, localArrayList);
/*     */     }
/* 150 */     localArrayList.add(new String[] { paramString1, paramString3 });
/*     */   }
/*     */ 
/*     */   protected void a(String paramString1, String paramString2) {
/* 154 */     if (this.i != null) {
/* 155 */       ArrayList localArrayList = (ArrayList)this.i.get(paramString2);
/* 156 */       if (localArrayList != null) {
/* 157 */         for (String[] arrayOfString : localArrayList) {
/* 158 */           if (arrayOfString[0].equals(paramString1)) {
/* 159 */             localArrayList.remove(arrayOfString);
/*     */           }
/*     */         }
/* 162 */         if (localArrayList.size() == 0)
/* 163 */           this.i.remove(paramString2);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void f()
/*     */   {
/* 183 */     for (c localc : this.c.b)
/* 184 */       a(this, localc.i);
/*     */   }
/*     */ 
/*     */   private static void a(b paramb, HashMap<String, ArrayList<String[]>> paramHashMap)
/*     */   {
/* 189 */     if (paramHashMap != null)
/*     */     {
/* 191 */       for (ArrayList localArrayList : paramHashMap.values())
/* 192 */         if (localArrayList != null) {
/* 193 */           int i1 = localArrayList.size();
/* 194 */           for (int i2 = i1 - 1; i2 >= 0; i2--) {
/* 195 */             String[] arrayOfString = (String[])localArrayList.get(i2);
/* 196 */             String str = arrayOfString[0];
/* 197 */             c localc = paramb.c.a(str, str, null);
/* 198 */             if (localc == paramb)
/* 199 */               localArrayList.remove(i2);
/*     */           }
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected boolean a(String paramString)
/*     */   {
/* 213 */     boolean bool = false;
/* 214 */     if (this.i != null) {
/* 215 */       ArrayList localArrayList = (ArrayList)this.i.get(paramString);
/* 216 */       if (localArrayList != null) {
/* 217 */         bool = !localArrayList.isEmpty();
/*     */       }
/*     */     }
/* 220 */     return bool;
/*     */   }
/*     */ 
/*     */   public final boolean b(String paramString1, String paramString2)
/*     */   {
/* 229 */     return a(paramString1, paramString2, true);
/*     */   }
/*     */ 
/*     */   public boolean a(String paramString1, String paramString2, boolean paramBoolean)
/*     */   {
/* 240 */     Logger.d("execCallback pEventType=" + paramString1 + ";");
/* 241 */     boolean bool = false;
/* 242 */     if (this.i != null) {
/* 243 */       ArrayList localArrayList = (ArrayList)this.i.get(paramString1);
/* 244 */       if (localArrayList != null) {
/* 245 */         int i1 = localArrayList.size();
/* 246 */         String str1 = JsEventUtil.eventListener_format(paramString1, paramString2, paramBoolean);
/* 247 */         for (int i2 = i1 - 1; i2 >= 0; i2--) {
/* 248 */           String[] arrayOfString = (String[])localArrayList.get(i2);
/* 249 */           String str2 = arrayOfString[0];
/* 250 */           String str3 = arrayOfString[1];
/* 251 */           c localc = this.c.a(str2, str2, null);
/* 252 */           if ((localc != null) && (!localc.F)) {
/* 253 */             IWebview localIWebview = localc.u.obtainWebView();
/* 254 */             if (localIWebview != null) {
/* 255 */               JSUtil.execCallback(localIWebview, str3, str1, JSUtil.OK, true, true);
/* 256 */               bool = true;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 262 */     return bool;
/*     */   }
/*     */ 
/*     */   protected abstract void g();
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\ui.jar
 * Qualified Name:     io.dcloud.feature.ui.b
 * JD-Core Version:    0.6.2
 */