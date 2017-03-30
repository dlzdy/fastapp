package io.dcloud.invocation;

import android.os.Build.VERSION;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.Md5Utils;
import io.dcloud.common.util.ThreadPool;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

public class d
{
  static HashMap<String, Class> a = new HashMap(2);
  static HashMap<Class, String> b = new HashMap(2);
  static HashMap<Class, String> c = new HashMap(2);

  private static String d = "cache/invCache/";
  private static String e = "cache/invCache_temp/";

  public static Class a(String paramString)
  {
    Class localClass = (Class)a.get(paramString);
    if (localClass == null) {
      try {
        localClass = Class.forName(paramString);
        a.put(paramString, localClass);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
      }
    }
    return localClass;
  }

  public static String b(String paramString)
  {
    Class localClass = a(paramString);
    return a(localClass);
  }
  public static String a(Class paramClass) {
    String str = (String)b.get(paramClass);
    if (str != null) return str.toString();
    StringBuffer localStringBuffer = new StringBuffer("[");
    localStringBuffer.append("\"").append(paramClass.getName()).append("\"");
    localStringBuffer.append(",");
    for (Class localClass = paramClass.getSuperclass(); 
      localClass != null; localClass = localClass.getSuperclass())
    {
      localStringBuffer.append("\"").append(localClass.getName()).append("\"");
      localStringBuffer.append(",");
      if (localClass == Object.class) {
        break;
      }
    }
    a(localStringBuffer);
    localStringBuffer.append("]");
    str = localStringBuffer.toString();
    b.put(paramClass, str);
    return JSUtil.wrapJsVar(str, false);
  }

  public static void a(StringBuffer paramStringBuffer1, ArrayList paramArrayList1, StringBuffer paramStringBuffer2, ArrayList paramArrayList2, Class paramClass)
  {
    Method[] arrayOfMethod = paramClass.getMethods();
    for (int i = 0; i < arrayOfMethod.length; i++) {
      Method localMethod = arrayOfMethod[i];
      String str = localMethod.getName();
      int j = localMethod.getModifiers();
      if (Modifier.isPublic(j)) {
        if ((Modifier.isStatic(j)) && 
          (!paramArrayList2.contains(str)) && (!str.contains("-")) && (!"toString".equals(str)) && (!"valueOf".equals(str)) && (!"propertyIsEnumerable".equals(str)) && (!"hasOwnProperty".equals(str)) && (!"isPrototypeOf".equals(str)) && (!"constructor".equals(str)) && (!"toLocaleString".equals(str)))
        {
          paramArrayList2.add(str);
          paramStringBuffer2.append("\"" + str + "\"");
          paramStringBuffer2.append(",");
        }

        if ((!paramArrayList1.contains(str)) && (!str.contains("-")))
        {
          paramArrayList1.add(str);
          paramStringBuffer1.append("\"" + str + "\"");
          paramStringBuffer1.append(",");
        }
      }
    }
  }

  public static void a(IWebview paramIWebview, a parama, Object paramObject, Field paramField, boolean paramBoolean, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2)
  {
    String str = paramField.getName();
    int i = paramField.getModifiers();
    if (((paramBoolean) && (Modifier.isStatic(i)) && (Modifier.isFinal(i))) || ((!paramBoolean) && (!Modifier.isStatic(i)) && (Modifier.isPublic(i))))
    {
      try
      {
        Class localClass = paramField.getType();
        Object localObject = paramField.get(paramObject);
        a(paramIWebview, parama, localClass, localObject, str, paramBoolean, paramStringBuffer1, paramStringBuffer2);
      } catch (Exception localException) {
        localException.printStackTrace();
      }
    }
  }

  static void a(IWebview paramIWebview, a parama, Class paramClass, Object paramObject, String paramString, boolean paramBoolean, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2) {
    if (paramBoolean) {
      paramStringBuffer1.append("\"" + paramString + "\"");
      a(paramIWebview, parama, paramObject, paramClass, paramStringBuffer2);
      paramStringBuffer1.append(",");
      paramStringBuffer2.append(",");
    } else {
      paramStringBuffer1.append("this.").append(paramString).append("=");
      a(paramIWebview, parama, paramObject, paramClass, paramStringBuffer2);
      paramStringBuffer1.append(paramStringBuffer2.toString()).append(";");
      paramStringBuffer2.delete(0, paramStringBuffer2.length());
    }
  }

  public static void a(IWebview paramIWebview, a parama, Object paramObject, Class paramClass, StringBuffer paramStringBuffer) { if (paramObject == null) {
      paramStringBuffer.append("null");
    }
    else if (c(paramClass)) {
      paramStringBuffer.append(String.valueOf(paramObject));
    }
    else
    {
      Object localObject1;
      if ((paramClass.equals(String.class)) || (paramClass.equals(Character.class))) {
        String str = String.valueOf(paramObject);
        localObject1 = JSUtil.toJsResponseText("\"" + str + "\"");
        paramStringBuffer.append("\"" + (String)localObject1 + "\"");
      } else {
        int i = a(paramObject);
        if (i > -1) {
          localObject1 = new StringBuffer();
          ((StringBuffer)localObject1).append("[");
          for (int j = 0; j < i; j++) {
            Object localObject2 = a(Array.get(paramObject, j), paramObject.getClass());
            a(paramIWebview, parama, localObject2, localObject2.getClass(), (StringBuffer)localObject1);
            ((StringBuffer)localObject1).append(",");
          }
          a((StringBuffer)localObject1);
          ((StringBuffer)localObject1).append("]");

          paramStringBuffer.append((StringBuffer)localObject1);
        } else {
          paramStringBuffer.append("\"" + JSUtil.toJsResponseText(a.b(paramIWebview, paramObject)) + "\"");
        }
      }
    } }

  public static String b(Class paramClass) {
    return paramClass.getName();
  }

  public static int a(Object paramObject)
  {
    if ((paramObject instanceof Object[])) {
      return ((Object[])paramObject).length;
    }
    if ((paramObject instanceof boolean[])) {
      return ((boolean[])paramObject).length;
    }
    if ((paramObject instanceof byte[])) {
      return ((byte[])paramObject).length;
    }
    if ((paramObject instanceof char[])) {
      return ((char[])paramObject).length;
    }
    if ((paramObject instanceof short[])) {
      return ((short[])paramObject).length;
    }
    if ((paramObject instanceof int[])) {
      return ((int[])paramObject).length;
    }
    if ((paramObject instanceof long[])) {
      return ((long[])paramObject).length;
    }
    if ((paramObject instanceof float[])) {
      return ((float[])paramObject).length;
    }
    if ((paramObject instanceof double[])) {
      return ((double[])paramObject).length;
    }
    return -1;
  }

  public static String a(IWebview paramIWebview, a parama, String paramString)
  {
    Class localClass = a(paramString);
    long l = System.currentTimeMillis();
    String str1 = (String)c.get(localClass);
    if (str1 != null) return str1;
    str1 = c(paramString);
    if (str1 != null) {
      c.put(localClass, str1);
      return str1;
    }
    StringBuffer localStringBuffer1 = new StringBuffer();
    StringBuffer localStringBuffer2 = new StringBuffer();
    StringBuffer localStringBuffer3 = new StringBuffer();
    StringBuffer localStringBuffer4 = new StringBuffer();
    try {
      if (localClass != null) {
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        a(localStringBuffer1, localArrayList1, localStringBuffer2, localArrayList2, localClass);
        a(localStringBuffer1);
        a(localStringBuffer2);
        Field[] arrayOfField = localClass.getFields();
        for (int i = 0; i < arrayOfField.length; i++) {
          a(paramIWebview, parama, null, arrayOfField[i], true, localStringBuffer3, localStringBuffer4);
        }
        a(paramIWebview, parama, Class.class, localClass, "class", true, localStringBuffer3, localStringBuffer4);
        a(localClass, paramString, localStringBuffer3, localStringBuffer4);
        a(localStringBuffer3);
        a(localStringBuffer4);
      }
    } catch (Exception localException) {
      localException.printStackTrace();
    }
    String str2 = String.format("{InstanceMethod:[%s],ClassMethod:[%s],ClassConstKeys:[%s],ClassConstValues:[%s]}", new Object[] { localStringBuffer1.toString(), localStringBuffer2.toString(), localStringBuffer3.toString(), localStringBuffer4.toString() });

    c.put(localClass, str2);
    a(paramString, str2);
    return str2;
  }

  public static void a(String paramString1, String paramString2)
  {
    if (paramString2.length() < 1024) return;
    File localFile1 = new File(DeviceInfo.sBaseFsRootPath + d + Md5Utils.md5(new StringBuilder().append(BaseInfo.sBaseVersion).append(Build.VERSION.SDK_INT).toString()) + "/" + Md5Utils.md5(paramString1));
    try {
      if (!localFile1.getParentFile().exists()) {
        localObject = new File(DeviceInfo.sBaseFsRootPath + d);
        if (((File)localObject).exists()) {
          File localFile2 = new File(DeviceInfo.sBaseFsRootPath + e);
          ((File)localObject).renameTo(localFile2);
          ThreadPool.self().addThreadTask(new Runnable()
          {
            public void run() {
              boolean bool = DHFile.delete(this.a);
            }
          });
        }

        localFile1.getParentFile().mkdirs();
      }
      if (!localFile1.exists()) {
        localFile1.createNewFile();
      }
      Object localObject = new FileOutputStream(localFile1);
      ((FileOutputStream)localObject).write(paramString2.getBytes("utf-8"));
    } catch (IOException localIOException) {
      localIOException.printStackTrace();
    }
  }

  public static String c(String paramString) {
    long l = System.currentTimeMillis();
    File localFile = new File(DeviceInfo.sBaseFsRootPath + d + Md5Utils.md5(new StringBuilder().append(BaseInfo.sBaseVersion).append(Build.VERSION.SDK_INT).toString()) + "/" + Md5Utils.md5(paramString));
    if (localFile.exists()) {
      try {
        byte[] arrayOfByte = DHFile.readAll(localFile);
        if (arrayOfByte != null) {
          return new String(arrayOfByte, "utf-8");
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
    return null;
  }

  private static void a(Class paramClass, String paramString, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2)
  {
    Class[] arrayOfClass = paramClass.getClasses();
    if (arrayOfClass != null) {
      int i = arrayOfClass.length;
      for (int j = 0; j < i; j++) {
        Class localClass = arrayOfClass[j];
        String str1 = localClass.getSimpleName();
        String str2 = localClass.getName();
        paramStringBuffer1.append("\"" + str1 + "\"");
        paramStringBuffer2.append("\"" + JSUtil.toJsResponseText(String.format("plus.android.importClass('%s')", new Object[] { str2 })) + "\"");
        paramStringBuffer1.append(",");
        paramStringBuffer2.append(",");
      }
    }
  }

  public static void a(StringBuffer paramStringBuffer)
  {
    if ((paramStringBuffer.length() > 0) && (paramStringBuffer.charAt(paramStringBuffer.length() - 1) == ','))
      paramStringBuffer.delete(paramStringBuffer.length() - 1, paramStringBuffer.length());
  }

  public static boolean c(Class paramClass)
  {
    return (paramClass == Boolean.class) || (paramClass == Boolean.TYPE) || (paramClass == Double.class) || (paramClass == Double.TYPE) || (paramClass == Integer.class) || (paramClass == Integer.TYPE) || (paramClass == Float.class) || (paramClass == Float.TYPE) || (paramClass == Long.class) || (paramClass == Long.TYPE) || (paramClass == Byte.class) || (paramClass == Byte.TYPE);
  }

  public static Object a(Object paramObject, Class paramClass)
  {
    if (paramObject != null) return paramObject;
    String str = paramClass.toString();
    if ((str.contains("java.lang.Byte")) || (str.contains("java.lang.Long")) || (str.contains("java.lang.Float")) || (str.contains("java.lang.Integer")) || (str.contains("java.lang.Double")))
    {
      return Integer.valueOf(0);
    }if (str.contains("java.lang.Boolean")) {
      return Boolean.valueOf(false);
    }
    return null;
  }
}