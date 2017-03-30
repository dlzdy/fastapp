package io.dcloud.common.DHInterface;

import org.json.JSONArray;

public abstract interface IJsInterface
{
  @Deprecated
  public abstract String exec(String paramString1, String paramString2, String paramString3);

  public abstract String exec(String paramString1, String paramString2, JSONArray paramJSONArray);

  public abstract String prompt(String paramString1, String paramString2);

  public abstract void forceStop(String paramString);
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IJsInterface
 * JD-Core Version:    0.6.2
 */