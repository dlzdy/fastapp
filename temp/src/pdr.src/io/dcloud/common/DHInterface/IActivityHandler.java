package io.dcloud.common.DHInterface;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import io.dcloud.feature.internal.reflect.BroadcastReceiver;

public abstract interface IActivityHandler
{
  public abstract void resumeAppStreamTask(String paramString);

  public abstract void addAppStreamTask(String paramString1, String paramString2);

  public abstract boolean raiseFilePriority(String paramString1, String paramString2);

  public abstract boolean queryUrl(String paramString1, String paramString2);

  public abstract void bindDCloudServices();

  public abstract void unbindDCloudServices();

  public abstract Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter);

  public abstract void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver);

  public abstract boolean isStreamAppMode();

  public abstract void closeAppStreamSplash(String paramString);

  public abstract void showSplashWaiting();

  public abstract void updateSplash(String paramString);

  public abstract void setViewAsContentView(View paramView);

  public abstract void setWebViewIntoPreloadView(View paramView);

  public abstract void downloadSimpleFileTask(IApp paramIApp, String paramString1, String paramString2, String paramString3);

  public abstract Context getContext();

  public abstract void commitPointData0(String paramString1, int paramInt1, int paramInt2, String paramString2);

  public abstract void commitPointData(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5);

  public abstract void commitActivateData(String paramString1, String paramString2);

  public abstract String getUrlByFilePath(String paramString1, String paramString2);

  public abstract void updateParam(String paramString, Object paramObject);
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IActivityHandler
 * JD-Core Version:    0.6.2
 */