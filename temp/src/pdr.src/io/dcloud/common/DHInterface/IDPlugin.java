package io.dcloud.common.DHInterface;

import android.app.Activity;
import android.content.Context;

public abstract interface IDPlugin extends IBoot, IFeature
{
  public abstract Context getDPluginContext();

  public abstract Activity getDPluginActivity();

  public abstract void initDPlugin(Context paramContext, Activity paramActivity);
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IDPlugin
 * JD-Core Version:    0.6.2
 */