/*     */ package io.dcloud.common.DHInterface;
/*     */ 
/*     */ import io.dcloud.common.util.JSUtil;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.HashMap;
/*     */ import org.json.JSONArray;
/*     */ 
/*     */ public class StandardFeature extends BaseFeature
/*     */   implements IReflectAble
/*     */ {
/*  62 */   private HashMap<String, Method> mInnerClassMethod = null;
/*     */ 
/*     */   public final String execute(IWebview paramIWebview, String paramString, JSONArray paramJSONArray)
/*     */   {
/*  66 */     return executeAction(paramString, paramIWebview, paramJSONArray);
/*     */   }
/*     */ 
/*     */   private String executeAction(String paramString, IWebview paramIWebview, JSONArray paramJSONArray) {
/*  70 */     String str = null;
/*  71 */     Method localMethod = (Method)this.mInnerClassMethod.get(paramString);
/*  72 */     if (localMethod != null)
/*     */       try {
/*  74 */         Object localObject = localMethod.invoke(this, new Object[] { paramIWebview, paramJSONArray });
/*  75 */         if (localObject != null)
/*  76 */           str = localObject.toString();
/*     */       }
/*     */       catch (IllegalAccessException localIllegalAccessException) {
/*  79 */         str = JSUtil.wrapJsVar(localIllegalAccessException.getMessage());
/*  80 */         localIllegalAccessException.printStackTrace();
/*     */       } catch (IllegalArgumentException localIllegalArgumentException) {
/*  82 */         str = JSUtil.wrapJsVar(localIllegalArgumentException.getMessage());
/*  83 */         localIllegalArgumentException.printStackTrace();
/*     */       } catch (InvocationTargetException localInvocationTargetException) {
/*  85 */         str = JSUtil.wrapJsVar(localInvocationTargetException.getMessage());
/*  86 */         localInvocationTargetException.printStackTrace();
/*     */       }
/*     */     else {
/*  89 */       str = JSUtil.wrapJsVar("not found the " + paramString + " function");
/*     */     }
/*  91 */     return str;
/*     */   }
/*     */ 
/*     */   public void init(AbsMgr paramAbsMgr, String paramString) {
/*  95 */     super.init(paramAbsMgr, paramString);
/*  96 */     arrangeInnerMethod();
/*     */   }
/*     */ 
/*     */   private void arrangeInnerMethod() {
/* 100 */     this.mInnerClassMethod = new HashMap(1);
/*     */ 
/* 102 */     Method[] arrayOfMethod1 = getClass().getDeclaredMethods();
/* 103 */     for (Method localMethod : arrayOfMethod1) {
/* 104 */       int k = localMethod.getModifiers();
/* 105 */       if ((!Modifier.isStatic(k)) && (Modifier.isPublic(k)))
/*     */       {
/* 108 */         Class[] arrayOfClass = localMethod.getParameterTypes();
/* 109 */         if (isStandardFeatureMethod(arrayOfClass))
/* 110 */           this.mInnerClassMethod.put(localMethod.getName(), localMethod);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean isStandardFeatureMethod(Class[] paramArrayOfClass)
/*     */   {
/*     */     try {
/* 118 */       return (paramArrayOfClass != null) && (paramArrayOfClass.length == 2) && (paramArrayOfClass[0].equals(IWebview.class)) && (paramArrayOfClass[1].equals(JSONArray.class));
/*     */     } catch (Exception localException) {
/* 120 */       localException.printStackTrace();
/*     */     }
/* 122 */     return false;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.StandardFeature
 * JD-Core Version:    0.6.2
 */