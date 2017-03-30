package io.dcloud.invocation;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.ISysEventListener.SysEventType;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.feature.internal.sdk.SDK;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class a
  implements IEventCallback
{
  static HashMap<IWebview, HashMap<String, c>> a = new HashMap(2);
  static a b = null;

  ArrayList<IWebview> c = new ArrayList();

  public a(AbsMgr paramAbsMgr)
  {
    b = this;
  }

  public String a(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
  {
    if (!this.c.contains(paramIWebview)) {
      this.c.add(paramIWebview);
      localObject1 = (AdaFrameView)paramIWebview.obtainFrameView();
      ((AdaFrameView)localObject1).addFrameViewListener(this);
    }
    Object localObject1 = null;
    String str;
    Object localObject2;
    Object localObject5;
    if ("__Instance".equals(paramString)) {
      str = paramArrayOfString[0];
      localObject2 = paramArrayOfString[1];
      boolean bool = true;
      localObject5 = null;
      Object localObject6;
      if ((paramArrayOfString.length > 2) && (!PdrUtil.isEmpty(paramArrayOfString[2]))) {
        localObject5 = JSONUtil.createJSONArray(paramArrayOfString[2]);
        localObject6 = JSONUtil.getJSONObject((JSONArray)localObject5, 0);
        if (localObject6 != null) {
          if ("boolean".equals(((JSONObject)localObject6).optString("type")))
            bool = ((JSONObject)localObject6).optBoolean("value");
          else
            bool = !PdrUtil.isEquals("__super__constructor__", JSONUtil.getString((JSONObject)localObject6, "value"));
        }
      }
      try
      {
        if (bool) {
          localObject6 = new c(paramIWebview, this, d.a((String)localObject2), str, (JSONArray)localObject5);
          a(paramIWebview, str, (c)localObject6);
        }
      } catch (Exception localException3) {
        localObject1 = a(localException3, "new " + (String)localObject2);
        Log.e("InvProxy", "NativeObject.execMethod __Instance " + (String)localObject2 + " method ; params=" + localObject5 + localException3);
      }
    } else if ("release".equals(paramString)) {
      str = paramArrayOfString[0];
      localObject2 = a(paramIWebview, str);
      if (localObject2 != null)
        ((c)localObject2).a();
    }
    else if ("getWebviewById".equals(paramString)) {
      str = paramIWebview.obtainFrameView().obtainApp().obtainAppId();
      localObject2 = paramArrayOfString[0];
      localObject1 = b(paramIWebview, SDK.obtainWebview(str, (String)localObject2).obtainWebview());
    } else if ("currentWebview".equals(paramString)) {
      localObject1 = b(paramIWebview, paramIWebview.obtainWebview());
    } else if ("getContext".equals(paramString)) {
      str = paramArrayOfString[0];
      localObject2 = paramIWebview.getActivity();
      localObject1 = b(paramIWebview, localObject2);
      a(paramIWebview, "onActivityResult", str);
    } else if ("import".equals(paramString)) {
      str = paramArrayOfString[0];

      localObject1 = JSUtil.wrapJsVar(d.a(paramIWebview, this, str), false);
    }
    else
    {
      Object localObject4;
      if ("__plusGetAttribute".equals(paramString)) {
        str = paramArrayOfString[0];
        localObject2 = paramArrayOfString[1];
        localObject4 = a(paramIWebview, str);
        if (localObject4 != null) {
          localObject5 = c.a(((c)localObject4).b, ((c)localObject4).c, (String)localObject2);
          if (localObject5 != null)
            localObject1 = b(paramIWebview, localObject5);
        }
      }
      else if ("__plusSetAttribute".equals(paramString)) {
        str = paramArrayOfString[0];
        localObject2 = paramArrayOfString[1];
        localObject4 = JSONUtil.createJSONArray(paramArrayOfString[2]);
        localObject5 = a(paramIWebview, str);
        if (localObject5 != null)
          c.a(paramIWebview, this, ((c)localObject5).b, ((c)localObject5).c, (String)localObject2, (JSONArray)localObject4);
      }
      else
      {
        Object localObject7;
        Object localObject8;
        if ("implements".equals(paramString)) {
          str = paramArrayOfString[0];
          localObject2 = paramArrayOfString[1];
          localObject4 = JSONUtil.createJSONArray(paramArrayOfString[2]);
          localObject5 = paramArrayOfString[3];
          localObject7 = new b(paramIWebview, (String)localObject2, (JSONArray)localObject4, (String)localObject5);
          ((b)localObject7).a = str;
          localObject8 = null;
          Object localObject9 = ((b)localObject7).a((JSONArray)localObject8);
          localObject1 = b(paramIWebview, localObject9);
        } else if ((!"__loadDylib".equals(paramString)) && 
          (!"__release".equals(paramString))) {
          if ("__inheritList".equals(paramString)) {
            str = paramArrayOfString[0];
            try {
              localObject2 = paramArrayOfString[1];
              if (!TextUtils.isEmpty((CharSequence)localObject2)) {
                localObject4 = a(paramIWebview, (String)localObject2);
                if (localObject4 != null)
                  localObject1 = d.a(((c)localObject4).b);
                else
                  localObject1 = d.b(str);
              }
              else {
                localObject1 = d.b(str);
              }
            } catch (Exception localException1) {
              localObject1 = a(localException1, "importClass " + str);
            }
          } else if (!"__execCFunction".equals(paramString))
          {
            Object localObject3;
            if ("__newObject".equals(paramString)) {
              str = paramArrayOfString[0];
              localObject3 = JSONUtil.createJSONArray(paramArrayOfString[1]);
              try {
                localObject4 = c.a(paramIWebview, this, d.a(str), (JSONArray)localObject3);
                localObject1 = b(paramIWebview, localObject4);
              } catch (Exception localException2) {
                localObject1 = a(localException2, "newObject " + str);
                Log.e("InvProxy", "NativeObject.execMethod __newObject " + str + " method ; params=" + localObject3 + localException2);
              }
            }
            else
            {
              JSONArray localJSONArray;
              if ("__execStatic".equals(paramString)) {
                str = paramArrayOfString[0];
                localObject3 = paramArrayOfString[1];
                if (a(str, (String)localObject3, paramIWebview)) {
                  return localObject1;
                }
                localJSONArray = null;
                if ((paramArrayOfString.length > 2) && (!PdrUtil.isEmpty(paramArrayOfString[2]))) {
                  localJSONArray = JSONUtil.createJSONArray(paramArrayOfString[2]);
                }
                localObject5 = d.a(str);
                localObject7 = null;
                if (localObject5 == null) {
                  localObject5 = String.class;
                  localObject7 = str;
                }
                try {
                  localObject8 = c.b(paramIWebview, this, (Class)localObject5, localObject7, (String)localObject3, localJSONArray);
                  if (localObject8 != null)
                    localObject1 = b(paramIWebview, localObject8);
                }
                catch (Exception localException5) {
                  localObject1 = a(localException5, "static " + ((Class)localObject5).getName() + "." + (String)localObject3);
                  Log.e("InvProxy", "NativeObject.execMethod " + (String)localObject3 + " method ; params=" + localObject5 + localException5);
                }
              } else if ("__exec".equals(paramString)) {
                str = paramArrayOfString[0];
                localObject3 = paramArrayOfString[1];
                if (a("", (String)localObject3, paramIWebview)) {
                  return localObject1;
                }

                localJSONArray = JSONUtil.createJSONArray(paramArrayOfString[2]);
                localObject5 = a(paramIWebview, str);
                if (localObject5 != null)
                  try {
                    localObject7 = ((c)localObject5).a(paramIWebview, (String)localObject3, localJSONArray);
                    if (localObject7 != null)
                      localObject1 = b(paramIWebview, localObject7);
                  }
                  catch (Exception localException4) {
                    localObject1 = a(localException4, ((c)localObject5).b.getName() + "." + (String)localObject3);
                    Log.e("InvProxy", "NativeObject.execMethod " + (String)localObject3 + " method ; params=" + localJSONArray + localException4);
                  }
              }
              else if ("__saveContent".equals(paramString)) {
                str = paramArrayOfString[0];
                localObject3 = paramArrayOfString[1];
                str = paramIWebview.obtainFrameView().obtainApp().convert2AbsFullPath(paramIWebview.obtainFullUrl(), str);
                DHFile.writeFile(((String)localObject3).toString().getBytes(), 0, str);
              }
            }
          }
        }
      }
    }
    return localObject1;
  }

  private static String a(Exception paramException, String paramString) {
    return String.format("throw \"%s\";", new Object[] { (TextUtils.isEmpty(paramException.getMessage()) ? paramException : paramException.getCause() != null ? paramException.getCause() : paramException.getMessage()) + ";at " + paramString });
  }

  private void a(final IWebview paramIWebview, String paramString1, final String paramString2)
  {
    ISysEventListener.SysEventType localSysEventType = ISysEventListener.SysEventType.valueOf(paramString1);
    switch (3.a[localSysEventType.ordinal()]) {
    case 1:
      final ISysEventListener local1 = new ISysEventListener()
      {
        public boolean onExecute(ISysEventListener.SysEventType paramAnonymousSysEventType, Object paramAnonymousObject) {
          if (paramAnonymousSysEventType == ISysEventListener.SysEventType.onActivityResult) {
            Object[] arrayOfObject = (Object[])paramAnonymousObject;
            int i = ((Integer)arrayOfObject[0]).intValue();
            int j = ((Integer)arrayOfObject[1]).intValue();
            Intent localIntent = (Intent)arrayOfObject[2];
            StringBuffer localStringBuffer = new StringBuffer("[");
            localStringBuffer.append(i);
            localStringBuffer.append(",").append(j);
            if (localIntent != null) {
              localStringBuffer.append(",").append(a.a(paramIWebview, localIntent));
            }
            localStringBuffer.append("]");
            JSUtil.execCallback(paramIWebview, paramString2, localStringBuffer.toString(), JSUtil.OK, true, true);
            return true;
          }
          return false;
        }
      };
      paramIWebview.obtainApp().registerSysEventListener(local1, ISysEventListener.SysEventType.onActivityResult);
      paramIWebview.obtainFrameView().addFrameViewListener(new IEventCallback()
      {
        public Object onCallBack(String paramAnonymousString, Object paramAnonymousObject)
        {
          if ((PdrUtil.isEquals(paramAnonymousString, "window_close")) || (PdrUtil.isEquals(paramAnonymousString, "close")))
          {
            paramIWebview.obtainApp().unregisterSysEventListener(local1, ISysEventListener.SysEventType.onActivityResult);
          }
          return null;
        }
      });
      break;
    }
  }

  static String a(IWebview paramIWebview, Object paramObject)
  {
    Class localClass = paramObject.getClass();
    String str1 = d.b(localClass);
    String str2 = null;
    String str3 = "basic";
    if ((localClass == String.class) || (localClass == CharSequence.class)) {
      str2 = "\"" + String.valueOf(paramObject) + "\"";
    } else if (d.c(localClass)) {
      str2 = String.valueOf(paramObject);
    } else {
      str3 = "object";
      String str4 = a(paramObject);
      a(paramIWebview, str4, paramObject);
      str2 = "\"" + str4 + "\"";

      String str5 = JSUtil.wrapJsVar(String.format("{\"type\":\"%s\", \"value\":%s, \"className\":\"%s\",\"superClassNames\":%s}", new Object[] { str3, str2, str1, a(localClass) }), false);
      return String.format("plus.ios.__Tool.New(%s, true)", new Object[] { str5 });
    }
    return JSUtil.wrapJsVar(String.format("{\"type\":\"%s\", \"value\":%s, \"className\":\"%s\",\"superClassNames\":%s}", new Object[] { str3, str2, str1, a(localClass) }), false);
  }

  static String a(Class paramClass) {
    JSONStringer localJSONStringer = new JSONStringer();
    ArrayList localArrayList = new ArrayList();
    try {
      localJSONStringer.array();
      a(paramClass, localJSONStringer, localArrayList);
      localJSONStringer.endArray();
    } catch (JSONException localJSONException) {
      localJSONException.printStackTrace();
    }
    String str = localJSONStringer.toString();
    return str == null ? "[]" : str;
  }
  static void a(Class paramClass, JSONStringer paramJSONStringer, ArrayList<String> paramArrayList) throws JSONException {
    for (Class localClass = paramClass.getSuperclass(); 
      localClass != null; localClass = localClass.getSuperclass())
    {
      String str = localClass.getName();
      if (!paramArrayList.contains(str)) {
        paramJSONStringer.value(str);
        paramArrayList.add(str);
        b(localClass, paramJSONStringer, paramArrayList);
      }
      if (localClass == Object.class)
        break;
    }
  }

  static void b(Class paramClass, JSONStringer paramJSONStringer, ArrayList<String> paramArrayList) throws JSONException {
    Class[] arrayOfClass = paramClass.getInterfaces();
    if (arrayOfClass != null)
      for (int i = 0; i < arrayOfClass.length; i++) {
        Class localClass = arrayOfClass[i];
        String str = localClass.getName();
        if (!paramArrayList.contains(str)) {
          paramJSONStringer.value(str);
          paramArrayList.add(str);

          b(localClass, paramJSONStringer, paramArrayList);
        }
      }
  }

  static String b(IWebview paramIWebview, Object paramObject)
  {
    Class localClass = paramObject.getClass();
    String str1 = d.b(localClass);
    String str2 = "basic";
    if ((!d.c(localClass)) && (localClass != String.class) && (localClass != CharSequence.class) && (!localClass.isArray()))
    {
      str2 = "object";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    a(paramIWebview, paramObject, localClass, localStringBuffer);
    return JSUtil.wrapJsVar(String.format("{\"type\":\"%s\", \"value\":%s, \"className\":\"%s\",\"superClassNames\":%s}", new Object[] { str2, localStringBuffer.toString(), str1, a(localClass) }), false);
  }

  static void a(IWebview paramIWebview, Object paramObject, Class paramClass, StringBuffer paramStringBuffer) {
    if ((paramClass == String.class) || (paramClass == CharSequence.class)) {
      paramStringBuffer.append(JSONObject.quote(String.valueOf(paramObject)));
    } else if (d.c(paramClass)) {
      paramStringBuffer.append(String.valueOf(paramObject));
    } else if (paramClass.isArray()) {
      int i = Array.getLength(paramObject);
      paramStringBuffer.append("[");
      for (int j = 0; j < i; j++) {
        Object localObject = d.a(Array.get(paramObject, j), paramClass);
        paramStringBuffer.append(b(paramIWebview, localObject));
        if (j != i - 1) {
          paramStringBuffer.append(",");
        }
      }
      paramStringBuffer.append("]");
    } else {
      String str = a(paramObject);
      a(paramIWebview, str, paramObject);
      paramStringBuffer.append("\"").append(str).append("\"");
    }
  }

  private static HashMap<String, c> a(IWebview paramIWebview)
  {
    HashMap localHashMap = (HashMap)a.get(paramIWebview);
    if (localHashMap == null) {
      localHashMap = new HashMap(2);
      a.put(paramIWebview, localHashMap);
    }
    return localHashMap;
  }

  c a(HashMap<String, c> paramHashMap, String paramString) {
    return (c)paramHashMap.get(paramString);
  }

  c a(IWebview paramIWebview, String paramString)
  {
    return a(a(paramIWebview), paramString);
  }

  private static void a(IWebview paramIWebview, String paramString, c paramc) {
    HashMap localHashMap = a(paramIWebview);
    localHashMap.put(paramString, paramc);
  }
  private static c a(IWebview paramIWebview, String paramString, Object paramObject) {
    Class localClass = paramObject.getClass();
    String str = localClass.getName();
    c localc = new c(b, localClass, paramString, paramObject);
    a(paramIWebview, paramString, localc);
    return localc;
  }

  static String a(Object paramObject) {
    String str = "Invocation" + paramObject.hashCode();
    return str;
  }

  public Object onCallBack(String paramString, Object paramObject)
  {
    if ((PdrUtil.isEquals(paramString, "close")) && ((paramObject instanceof IWebview)))
    {
      try
      {
        ((AdaFrameView)((IWebview)paramObject).obtainFrameView()).removeFrameViewListener(this);
        this.c.remove(paramObject);
        ((HashMap)a.remove((IWebview)paramObject)).clear();
      } catch (Exception localException) {
        localException.printStackTrace();
      }
    }
    return null;
  }

  private boolean a(String paramString1, String paramString2, IWebview paramIWebview)
  {
    if ((paramIWebview == null) || (TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)))
    {
      return false;
    }

    String str = paramIWebview.obtainApp().obtainConfigProperty("use_encryption");

    boolean bool = Boolean.parseBoolean(str);
    if ((bool) && ("setWebContentsDebuggingEnabled".equalsIgnoreCase(paramString2)) && ((TextUtils.isEmpty(paramString1)) || ("WebView".equalsIgnoreCase(paramString1)) || ("android.webkit.WebView".equalsIgnoreCase(paramString1))))
    {
      return true;
    }

    return false;
  }

  public void a(String paramString) {
    if (TextUtils.isEmpty(paramString))
      d.c.clear();
  }
}