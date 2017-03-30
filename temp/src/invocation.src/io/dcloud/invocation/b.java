package io.dcloud.invocation;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import org.json.JSONArray;

public class b
  implements InvocationHandler
{
  String a = null;
  Object b = null;
  Class c = null;
  IWebview d = null;
  String e = null;
  ArrayList<String> f = null;

  public b(IWebview paramIWebview, String paramString1, JSONArray paramJSONArray, String paramString2) {
    try { this.d = paramIWebview;
      this.e = paramString2;
      this.f = new ArrayList();
      for (int i = 0; i < paramJSONArray.length(); i++) {
        this.f.add(JSONUtil.getString(paramJSONArray, i));
      }
      this.c = Class.forName(paramString1);
    } catch (IllegalArgumentException localIllegalArgumentException) {
      localIllegalArgumentException.printStackTrace();
    }
    catch (ClassNotFoundException localClassNotFoundException) {
      localClassNotFoundException.printStackTrace();
    }
  }

  public Object a(JSONArray paramJSONArray) {
    this.b = Proxy.newProxyInstance(this.c.getClassLoader(), new Class[] { this.c }, this);
    return this.b;
  }

  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    throws Throwable
  {
    StringBuffer localStringBuffer = new StringBuffer("[");
    String str1 = paramMethod.getName();
    if (this.f.contains(str1)) {
      if (paramArrayOfObject != null) {
        int i = paramArrayOfObject.length;
        for (int j = 0; j < i; j++) {
          if (paramArrayOfObject[j] == null)
            localStringBuffer.append("undefined");
          else {
            localStringBuffer.append(a.b(this.d, paramArrayOfObject[j]));
          }
          if (j != i - 1) {
            localStringBuffer.append(",");
          }
        }
      }
      localStringBuffer.append("]");
      String str2 = String.format("{method:'%s',arguments:%s}", new Object[] { paramMethod.getName(), localStringBuffer.toString() });
      JSUtil.execCallback(this.d, this.e, str2, JSUtil.OK, true, true);
      return null;
    }if ("hashCode".equals(str1))
      return Integer.valueOf(hashCode());
    if ("equals".equals(str1))
      return Boolean.valueOf(equals(paramArrayOfObject));
    if ("getClass".equals(str1))
      return this.c;
    if ("finalize".equals(str1)) {
      finalize();
    } else if ("notify".equals(str1)) {
      notify();
    } else if ("notifyAll".equals(str1)) {
      notifyAll(); } else {
      if ("toString".equals(str1))
        return this.a + this.c.toString();
      if ("wait".equals(str1)) {
        if (paramArrayOfObject.length == 0)
          wait();
        else if (paramArrayOfObject.length == 1)
          wait(Long.parseLong(String.valueOf(paramArrayOfObject[0])));
        else if (paramArrayOfObject.length == 2)
          wait(Long.parseLong(String.valueOf(paramArrayOfObject[0])), Integer.parseInt(String.valueOf(paramArrayOfObject[0])));
      }
      else
        try {
          return paramMethod.invoke(this.b, paramArrayOfObject);
        } catch (Exception localException) {
          localException.printStackTrace();
        }
    }
    return null;
  }
}