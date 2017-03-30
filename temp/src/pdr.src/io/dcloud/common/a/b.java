/*     */ package io.dcloud.common.a;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.text.TextUtils;
/*     */ import io.dcloud.common.DHInterface.IMgr.MgrType;
/*     */ import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import io.dcloud.common.adapter.util.MobilePhoneModel;
/*     */ import io.dcloud.common.util.BaseInfo;
/*     */ import io.dcloud.common.util.ShortCutUtil;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ class b
/*     */ {
/*  18 */   a a = null;
/*  19 */   private ArrayList<d> b = new ArrayList();
/*  20 */   private ArrayList<String> c = BaseInfo.sRunningApp = new ArrayList();
/*     */ 
/*     */   b(a parama)
/*     */   {
/*  27 */     this.a = parama;
/*     */   }
/*     */ 
/*     */   boolean a(d paramd, ISysEventListener.SysEventType paramSysEventType, Object paramObject)
/*     */   {
/*  39 */     if (paramSysEventType.equals(ISysEventListener.SysEventType.onResume))
/*     */     {
/*  41 */       for (int i = 0; i < this.b.size(); i++) {
/*  42 */         d locald1 = (d)this.b.get(i);
/*  43 */         if (locald1 != null) {
/*  44 */           boolean bool2 = ShortCutUtil.isOpsCreateShortcut(locald1.getActivity(), locald1.obtainAppId());
/*  45 */           if ((bool2) || (ShortCutUtil.isRunShortCut(locald1.obtainAppId())))
/*     */           {
/*  47 */             if (!MobilePhoneModel.isSmartisanLauncherPhone(locald1.getActivity())) {
/*  48 */               ShortCutUtil.onResumeCreateShortcut(locald1);
/*     */             }
/*     */             else
/*  51 */               ShortCutUtil.commitShortcut(locald1, 12, true, false, true, 0);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  56 */     else if ((paramd != null) && (paramSysEventType.equals(ISysEventListener.SysEventType.onKeyDown))) {
/*  57 */       Object[] arrayOfObject1 = (Object[])paramObject;
/*  58 */       j = ((Integer)arrayOfObject1[0]).intValue();
/*  59 */       if ((j == 4) && (BaseInfo.isPostChcekShortCut)) {
/*  60 */         ShortCutUtil.isHasShortCut(paramd, 500L, "back");
/*  61 */         return true;
/*     */       }
/*     */     }
/*  64 */     boolean bool1 = false;
/*  65 */     int j = paramd == null ? 1 : 0;
/*  66 */     int k = this.b.size();
/*  67 */     Object localObject = null;
/*  68 */     for (int m = k - 1; m >= 0; m--) {
/*  69 */       d locald2 = (d)this.b.get(m);
/*  70 */       int i1 = j;
/*  71 */       if (i1 == 0) {
/*  72 */         i1 = locald2 == paramd ? 1 : 0;
/*     */       }
/*  74 */       if (i1 != 0) {
/*  75 */         localObject = locald2;
/*  76 */         bool1 |= locald2.onExecute(paramSysEventType, paramObject);
/*  77 */         if ((bool1) && (!d.a(paramSysEventType))) {
/*     */           break;
/*     */         }
/*     */       }
/*     */     }
/*  82 */     if ((!bool1) && (paramSysEventType.equals(ISysEventListener.SysEventType.onKeyUp)) && (k > 1) && (localObject != null)) {
/*  83 */       Object[] arrayOfObject2 = (Object[])paramObject;
/*  84 */       int n = ((Integer)arrayOfObject2[0]).intValue();
/*  85 */       if (n == 4) {
/*  86 */         this.a.processEvent(IMgr.MgrType.WindowMgr, 20, localObject);
/*  87 */         bool1 = true;
/*     */       }
/*     */     }
/*  90 */     return bool1;
/*     */   }
/*     */ 
/*     */   protected d a(String paramString)
/*     */   {
/* 100 */     int i = this.c.indexOf(paramString);
/* 101 */     return i >= 0 ? (d)this.b.get(i) : null;
/*     */   }
/*     */ 
/*     */   void a(String paramString, d paramd) {
/* 105 */     this.c.add(paramString);
/* 106 */     this.b.add(paramd);
/* 107 */     BaseInfo.isStreamAppRuning = true;
/*     */   }
/*     */ 
/*     */   d b(String paramString) {
/* 111 */     Object localObject = null;
/* 112 */     for (d locald : this.b) {
/* 113 */       if (TextUtils.equals(locald.obtainAppId(), paramString)) {
/* 114 */         localObject = locald;
/* 115 */         break;
/*     */       }
/*     */     }
/* 118 */     Logger.d("AppCache", "removeWebApp " + localObject + ";mAppIdList=" + this.c);
/* 119 */     this.b.remove(localObject);
/* 120 */     this.c.remove(paramString);
/* 121 */     if (this.b.size() == 0) {
/* 122 */       BaseInfo.isStreamAppRuning = false;
/*     */     }
/* 124 */     return localObject;
/*     */   }
/*     */ 
/*     */   protected d a()
/*     */   {
/* 132 */     long l = 0L;
/* 133 */     Object localObject = null;
/* 134 */     for (int i = this.b.size() - 1; i >= 0; i--) {
/* 135 */       d locald = (d)this.b.get(i);
/* 136 */       if ((locald.d == 3) && (locald.T > l)) {
/* 137 */         localObject = locald;
/* 138 */         l = locald.T;
/*     */       }
/*     */     }
/* 141 */     return localObject;
/*     */   }
/*     */ 
/*     */   protected d a(Activity paramActivity, d paramd)
/*     */   {
/* 151 */     if (this.b.contains(paramd)) return null;
/* 152 */     long l = System.currentTimeMillis();
/* 153 */     int i = this.b.size();
/* 154 */     d locald = null;
/* 155 */     if (i >= BaseInfo.s_Runing_App_Count_Max) {
/* 156 */       locald = b();
/*     */     }
/* 158 */     return locald;
/*     */   }
/*     */ 
/*     */   public d b() {
/* 162 */     Object localObject = null;
/* 163 */     long l = System.currentTimeMillis();
/* 164 */     for (int i = 0; i < this.b.size(); i++) {
/* 165 */       d locald = (d)this.b.get(i);
/* 166 */       if (locald.T < l)
/*     */       {
/* 168 */         localObject = locald;
/* 169 */         l = locald.T;
/*     */       }
/*     */     }
/* 172 */     return localObject;
/*     */   }
/*     */ 
/*     */   protected ArrayList<d> c()
/*     */   {
/* 188 */     ArrayList localArrayList = new ArrayList(d());
/* 189 */     int i = d();
/* 190 */     for (int j = 0; j < i; j++) {
/* 191 */       d locald1 = (d)this.b.get(j);
/* 192 */       int k = 0;
/* 193 */       for (int m = 0; m < localArrayList.size(); m++) {
/* 194 */         d locald2 = (d)localArrayList.get(m);
/* 195 */         if (locald1.T <= locald2.T) break;
/* 196 */         k = m + 1;
/*     */       }
/*     */ 
/* 201 */       localArrayList.add(k, locald1);
/*     */     }
/* 203 */     return localArrayList;
/*     */   }
/*     */ 
/*     */   protected int d()
/*     */   {
/* 211 */     return this.b.size();
/*     */   }
/*     */ 
/*     */   void e() {
/* 215 */     this.b.clear();
/* 216 */     this.c.clear();
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.a.b
 * JD-Core Version:    0.6.2
 */