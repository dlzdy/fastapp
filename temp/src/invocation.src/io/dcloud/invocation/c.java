package io.dcloud.invocation;

import android.util.Log;
import io.dcloud.common.DHInterface.IWebview;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class c
{
  a a = null;
  Class b = null;
  Object c = null;
  String d = null;

  public c(IWebview paramIWebview, a parama, Class paramClass, String paramString, JSONArray paramJSONArray) throws Exception
  {
    this.a = parama;
    this.b = paramClass;
    this.d = paramString;
    this.c = a(paramIWebview, parama, paramClass, paramJSONArray);
  }

  public c(a parama, Class paramClass, String paramString, Object paramObject)
  {
    this.a = parama;
    this.b = paramClass;
    this.d = paramString;
    this.c = paramObject;
  }

  public static Object a(IWebview paramIWebview, a parama, Class paramClass, JSONArray paramJSONArray) throws Exception {
    Object localObject = null;

    if (paramJSONArray != null) { Object[] arrayOfObject1 = a(paramIWebview, parama, paramJSONArray);
      Class[] arrayOfClass1 = (Class[])arrayOfObject1[0];
      Object[] arrayOfObject2 = (Object[])arrayOfObject1[1];
      Constructor localConstructor = null;
      Constructor[] arrayOfConstructor;
      int i;
      try { localConstructor = paramClass.getConstructor(arrayOfClass1);
      } catch (NoSuchMethodException localNoSuchMethodException) {
        arrayOfConstructor = paramClass.getConstructors();
        i = 0; } for (; i < arrayOfConstructor.length; i++) {
        Class[] arrayOfClass2 = arrayOfConstructor[i].getParameterTypes();
        if ((arrayOfClass2.length == arrayOfClass1.length) && 
          (a(arrayOfClass2, arrayOfClass1, arrayOfObject2))) {
          localConstructor = arrayOfConstructor[i];
          break;
        }

      }

      if (localConstructor != null)
        localObject = localConstructor.newInstance(arrayOfObject2);
    } else
    {
      localObject = paramClass.newInstance();
    }

    return localObject;
  }
  public static void a(IWebview paramIWebview, a parama, Class paramClass, Object paramObject, String paramString, JSONArray paramJSONArray) {
    Object[] arrayOfObject = a(paramIWebview, parama, paramJSONArray);
    try {
      Field localField = paramClass.getField(paramString);
      if (null != localField) {
        localField.setAccessible(true);
        localField.set(paramObject, ((Object[])(Object[])arrayOfObject[1])[0]);
      }
    } catch (Exception localException) {
      localException.printStackTrace();
    }
  }

  public static Object a(Class paramClass, Object paramObject, String paramString)
  {
    Object localObject = null;
    try {
      Field localField = paramClass.getField(paramString);
      if (null != localField) {
        localField.setAccessible(true);
        localObject = localField.get(paramObject);
      }
    } catch (Exception localException) {
      localException.printStackTrace();
    }
    return localObject;
  }

  Object a(IWebview paramIWebview, String paramString, JSONArray paramJSONArray)
    throws Exception
  {
    return b(paramIWebview, this.a, this.b, this.c, paramString, paramJSONArray);
  }

  public static final Object b(IWebview paramIWebview, a parama, Class paramClass, Object paramObject, String paramString, JSONArray paramJSONArray) throws Exception {
    Object[] arrayOfObject1 = a(paramIWebview, parama, paramJSONArray);
    Object localObject = null;
    Class[] arrayOfClass = (Class[])arrayOfObject1[0];
    Object[] arrayOfObject2 = (Object[])arrayOfObject1[1];
    Method localMethod = a(paramClass, paramString, arrayOfClass, arrayOfObject2);

    Class localClass = localMethod.getReturnType();
    if (localClass.equals(Void.class))
      localMethod.invoke(paramObject, arrayOfObject2);
    else {
      localObject = localMethod.invoke(paramObject, arrayOfObject2);
    }

    return localObject;
  }

  static Method a(Class paramClass, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    Method localMethod = null;
    boolean bool = "getClass".equals(paramString);
    for (Class localClass = paramClass; (bool) || (localClass != Object.class); localClass = localClass.getSuperclass())
      try {
        localMethod = paramClass.getMethod(paramString, paramArrayOfClass);
        if (localMethod != null) {
          localMethod.setAccessible(true);
          return localMethod;
        }
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
      }
    return b(paramClass, paramString, paramArrayOfClass, paramArrayOfObject);
  }

  private static Method b(Class paramClass, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      Method[] arrayOfMethod = paramClass.getDeclaredMethods();
      for (int i = 0; i < arrayOfMethod.length; i++) {
        Method localMethod = arrayOfMethod[i];
        if (localMethod.getName().equals(paramString)) {
          int j = localMethod.getParameterTypes().length;
          int k = paramArrayOfClass.length;
          if (j == k) {
            if (j == 0) {
              return localMethod;
            }
            Class[] arrayOfClass = localMethod.getParameterTypes();
            if (a(arrayOfClass, paramArrayOfClass, paramArrayOfObject)) {
              return localMethod;
            }
          }
        }
      }
      if (paramClass != Object.class)
        return b(paramClass.getSuperclass(), paramString, paramArrayOfClass, paramArrayOfObject);
    }
    catch (Exception localException) {
      localException.printStackTrace();
    }
    return null;
  }

  public static boolean a(Class[] paramArrayOfClass1, Class[] paramArrayOfClass2, Object[] paramArrayOfObject)
  {
    boolean bool = false;
    for (int i = 0; i < paramArrayOfClass1.length; i++) {
      bool = false;
      Class localClass1 = paramArrayOfClass2[i];
      Class localClass2 = paramArrayOfClass1[i];
      if (localClass1 != null) {
        if ((localClass2.isAssignableFrom(localClass1)) || (a(localClass2, paramArrayOfClass2, i)) || (c(localClass2, paramArrayOfClass2, i)) || (b(localClass2, paramArrayOfClass2, i)) || ((localClass2.isArray()) && (localClass2.isArray()) && (a(localClass2, paramArrayOfClass2, paramArrayOfObject, i))))
        {
          bool = true;
        }
      }
      else if ((!b(localClass2)) && (!Boolean.TYPE.equals(localClass2)) && (!Boolean.class.equals(localClass2)) && (!Character.TYPE.equals(localClass2)) && (!Character.class.equals(localClass2)))
      {
        bool = true;
      }

      if (!bool) return bool;
    }
    return bool;
  }

  static boolean a(Class paramClass, Class[] paramArrayOfClass, int paramInt)
  {
    boolean bool = a(paramClass, paramArrayOfClass[paramInt]);
    if (bool) paramArrayOfClass[paramInt] = paramClass;
    return bool;
  }
  static boolean a(Class paramClass1, Class paramClass2) {
    return ((Number.class.isAssignableFrom(paramClass1)) && (Number.class.isAssignableFrom(paramClass2))) || ((b(paramClass1)) && (b(paramClass2)));
  }

  static Object[] a(Class paramClass) {
    String str1 = paramClass.getName();
    Object[] arrayOfObject = new Object[2];
    int i = 0;
    Class localClass = null;
    for (int j = 0; j < str1.length(); j++) {
      int k = str1.charAt(j);
      if (k == 91) {
        i++;
      }
      else if (k == 66) {
        localClass = Byte.TYPE;
      } else if (k == 73) {
        localClass = Integer.TYPE;
      } else if (k == 70) {
        localClass = Float.TYPE;
      } else if (k == 68) {
        localClass = Double.TYPE;
      } else if (k == 90) {
        localClass = Boolean.TYPE;
      } else if (k == 74) {
        localClass = Long.TYPE;
      } else if (k == 83) {
        localClass = Short.TYPE; } else {
        if (k == 76) {
          String str2 = str1.substring(j + 1);
          try {
            localClass = Class.forName(str2);
          } catch (ClassNotFoundException localClassNotFoundException2) {
            localClassNotFoundException2.printStackTrace();
          }
        }
        try
        {
          localClass = Class.forName(str1);
        } catch (ClassNotFoundException localClassNotFoundException1) {
          localClassNotFoundException1.printStackTrace();
        }
      }

    }

    arrayOfObject[0] = Integer.valueOf(i);
    arrayOfObject[1] = localClass;
    return arrayOfObject;
  }

  static boolean a(Class paramClass, Class[] paramArrayOfClass, Object[] paramArrayOfObject, int paramInt) {
    if (paramClass == paramArrayOfClass[paramInt]) return true;
    Object[] arrayOfObject1 = a(paramClass);
    Object[] arrayOfObject2 = a(paramArrayOfClass[paramInt]);
    int i = Integer.parseInt(String.valueOf(arrayOfObject1[0]));
    Class localClass1 = (Class)arrayOfObject1[1];
    int j = Integer.parseInt(String.valueOf(arrayOfObject2[0]));
    Class localClass2 = (Class)arrayOfObject2[1];
    if ((i == j) && ((c(localClass1, localClass2)) || (b(localClass1, localClass2)) || (a(localClass1, localClass2)) || (localClass2.isAssignableFrom(localClass1))))
    {
      try
      {
        Object localObject = a(paramArrayOfObject[paramInt], localClass1, i, new a(i));
        if (localObject != null) {
          paramArrayOfClass[paramInt] = paramClass;
          paramArrayOfObject[paramInt] = localObject;
          return true;
        }
      } catch (Exception localException) {
        return false;
      }

    }

    return false;
  }

  static Object a(Object paramObject, Class paramClass, int paramInt, a parama)
    throws Exception
  {
    Object localObject1 = null;
    if (paramObject.getClass().isArray()) {
      int i = Array.getLength(paramObject);
      Object localObject2 = null;
      for (int j = 0; j < i; j++)
        try {
          Object localObject3 = Array.get(paramObject, j);
          localObject2 = a(localObject3, paramClass, paramInt - 1, parama);
          if (localObject1 == null) {
            parama.a(paramInt, i);
            if (paramInt == 1) {
              localObject1 = Array.newInstance(paramClass, i);
            } else {
              int[] arrayOfInt = parama.a(paramInt);
              localObject1 = Array.newInstance(paramClass, arrayOfInt);
            }
          }
          Array.set(localObject1, j, localObject2);
        } catch (Exception localException) {
          localException.printStackTrace();
          Log.w("test", "i=" + j + ";mAw=" + paramInt);
        }
    }
    else {
      localObject1 = a(paramObject, paramClass);
    }
    return localObject1;
  }

  static boolean b(Class paramClass)
  {
    return (paramClass.equals(Byte.TYPE)) || (paramClass.equals(Byte.class)) || (paramClass.equals(Integer.TYPE)) || (paramClass.equals(Integer.class)) || (paramClass.equals(Short.TYPE)) || (paramClass.equals(Short.class)) || (paramClass.equals(Float.TYPE)) || (paramClass.equals(Float.class)) || (paramClass.equals(Long.TYPE)) || (paramClass.equals(Long.class)) || (paramClass.equals(Double.TYPE)) || (paramClass.equals(Double.class));
  }

  static boolean b(Class paramClass1, Class paramClass2)
  {
    return (c(paramClass1)) && (c(paramClass2));
  }
  static boolean b(Class paramClass, Class[] paramArrayOfClass, int paramInt) {
    boolean bool = b(paramClass, paramArrayOfClass[paramInt]);
    if (bool) paramArrayOfClass[paramInt] = paramClass;
    return bool;
  }
  static boolean c(Class paramClass) {
    return (Character.TYPE.equals(paramClass)) || (Character.class.equals(paramClass));
  }

  static boolean c(Class paramClass1, Class paramClass2) {
    return (d(paramClass1)) && (d(paramClass2));
  }
  static boolean c(Class paramClass, Class[] paramArrayOfClass, int paramInt) {
    boolean bool = c(paramClass, paramArrayOfClass[paramInt]);
    if (bool) paramArrayOfClass[paramInt] = paramClass;
    return bool;
  }
  static boolean d(Class paramClass) {
    return (Boolean.TYPE.equals(paramClass)) || (Boolean.class.equals(paramClass));
  }
  static Object a(Object paramObject, Class paramClass) {
    if ((paramClass == paramObject.getClass()) || (paramObject == null)) return paramObject;
    if ((paramClass == Byte.TYPE) || (paramClass == Byte.class))
      return Byte.valueOf(Byte.parseByte(String.valueOf(paramObject)));
    if ((paramClass == Integer.TYPE) || (paramClass == Integer.class))
      return Integer.valueOf(Integer.parseInt(String.valueOf(paramObject)));
    if ((paramClass == Boolean.TYPE) || (paramClass == Boolean.class))
      return Boolean.valueOf(Boolean.parseBoolean(String.valueOf(paramObject)));
    if ((paramClass == Short.TYPE) || (paramClass == Short.class))
      return Short.valueOf(Short.parseShort(String.valueOf(paramObject)));
    if ((paramClass == Long.TYPE) || (paramClass == Long.class))
      return Long.valueOf(Long.parseLong(String.valueOf(paramObject)));
    if ((paramClass == Double.TYPE) || (paramClass == Double.class))
      return Double.valueOf(Double.parseDouble(String.valueOf(paramObject)));
    if ((paramClass == Float.TYPE) || (paramClass == Float.class))
      return Float.valueOf(Float.parseFloat(String.valueOf(paramObject)));
    if ((paramClass == Character.TYPE) || (paramClass == Character.class)) {
      return String.valueOf(paramObject);
    }
    return paramClass.cast(paramObject);
  }
  static Class e(Class paramClass) {
    if (paramClass == Integer.class)
      return Integer.TYPE;
    if (paramClass == Boolean.class) {
      return Boolean.TYPE;
    }
    return paramClass;
  }

  static Object[] a(IWebview paramIWebview, a parama, Object paramObject) {
    Object[] arrayOfObject = new Object[2];
    Object localObject1;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    if ((paramObject instanceof JSONArray)) {
      localObject1 = (JSONArray)paramObject;
      int i = ((JSONArray)localObject1).length();
      localObject2 = null;
      for (int j = 0; j < i; j++) {
        localObject3 = ((JSONArray)localObject1).opt(j);
        localObject4 = a(paramIWebview, parama, localObject3);
        Class localClass = localObject4[1].getClass();
        if (j == 0) {
          localObject2 = Array.newInstance(e(localObject4[1].getClass()), i);
        }
        if (localClass == Boolean.class)
          Array.setBoolean(localObject2, j, ((Boolean)localObject4[1]).booleanValue());
        else if (localClass == Byte.class)
          Array.setByte(localObject2, j, ((Byte)localObject4[1]).byteValue());
        else if (localClass == Double.class)
          Array.setDouble(localObject2, j, ((Double)localObject4[1]).doubleValue());
        else if (localClass == Float.class)
          Array.setFloat(localObject2, j, ((Float)localObject4[1]).floatValue());
        else if (localClass == Integer.class)
          Array.setInt(localObject2, j, ((Integer)localObject4[1]).intValue());
        else if (localClass == Long.class)
          Array.setLong(localObject2, j, ((Long)localObject4[1]).longValue());
        else if ((localClass instanceof Object)) {
          Array.set(localObject2, j, localObject4[1]);
        }
      }
      if (localObject2 != null) {
        arrayOfObject[0] = localObject2.getClass();
        arrayOfObject[1] = localObject2;
      }
    } else if ((paramObject instanceof JSONObject)) {
      localObject1 = (JSONObject)paramObject;
      String str1 = ((JSONObject)localObject1).optString("type");

      localObject2 = ((JSONObject)localObject1).opt("value");
      String str2;
      if (str1.equals("object")) {
        str2 = String.valueOf(localObject2);
        localObject3 = parama.a(paramIWebview, str2);
        arrayOfObject[0] = ((c)localObject3).b;
        arrayOfObject[1] = ((c)localObject3).c;
      } else if (str1.equals("string")) {
        arrayOfObject[0] = String.class;
        arrayOfObject[1] = localObject2;
      } else if (str1.equals("number")) {
        if ((localObject2 instanceof Integer)) {
          arrayOfObject[0] = Integer.TYPE;
          arrayOfObject[1] = Integer.valueOf(((Integer)localObject2).intValue());
        } else if ((localObject2 instanceof Double)) {
          arrayOfObject[0] = Double.class;
          arrayOfObject[1] = ((Double)(Double)localObject2);
        } else if ((localObject2 instanceof Float)) {
          arrayOfObject[0] = Float.class;
          arrayOfObject[1] = ((Float)(Float)localObject2);
        } else if ((localObject2 instanceof Long)) {
          arrayOfObject[0] = Long.class;
          arrayOfObject[1] = ((Long)(Long)localObject2);
        }
      } else if (str1.equals("boolean")) {
        arrayOfObject[0] = Boolean.class;
        arrayOfObject[1] = ((Boolean)(Boolean)localObject2);
      } else {
        str2 = ((JSONObject)localObject1).optString("__TYPE__");
        if ("JSBObject".equals(str2)) {
          localObject3 = ((JSONObject)localObject1).optString("__UUID__");
          localObject4 = parama.a(paramIWebview, (String)localObject3);
          arrayOfObject[0] = ((c)localObject4).b;
          arrayOfObject[1] = ((c)localObject4).c;
        }
      }
    } else {
      arrayOfObject[0] = paramObject.getClass();
      arrayOfObject[1] = paramObject;
    }
    return arrayOfObject;
  }
  protected static Object[] a(IWebview paramIWebview, a parama, JSONArray paramJSONArray) {
    Object[] arrayOfObject1 = new Object[2];
    try {
      int i = 0;
      if (paramJSONArray != null) {
        i = paramJSONArray.length();
      }
      Class[] arrayOfClass = new Class[i];
      Object[] arrayOfObject2 = new Object[i];
      for (int j = 0; j < i; j++) {
        Object localObject = paramJSONArray.get(j);
        Object[] arrayOfObject3 = a(paramIWebview, parama, localObject);
        arrayOfClass[j] = ((Class)arrayOfObject3[0]);
        arrayOfObject2[j] = arrayOfObject3[1];
      }
      arrayOfObject1[0] = arrayOfClass;
      arrayOfObject1[1] = arrayOfObject2;
    } catch (JSONException localJSONException) {
      localJSONException.printStackTrace();
    }
    return arrayOfObject1;
  }

  public void a()
  {
  }

  public boolean equals(Object paramObject)
  {
    return (super.equals(paramObject)) || (this.c.equals(paramObject));
  }

  static class a
  {
    private int[] a = null;

    a(int paramInt) { this.a = new int[paramInt]; }

    int[] a(int paramInt)
    {
      if (paramInt > this.a.length) return null;
      return Arrays.copyOfRange(this.a, this.a.length - paramInt, this.a.length);
    }

    void a(int paramInt1, int paramInt2) {
      if (paramInt1 > this.a.length) return;
      this.a[(this.a.length - paramInt1)] = paramInt2;
    }
  }
}