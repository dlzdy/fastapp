package io.dcloud.common.DHInterface;

import android.app.Activity;
import io.dcloud.common.adapter.util.ViewRect;

public abstract interface IAppInfo extends IType_IntValue, IType_Layout_Changed
{
  public abstract boolean isVerticalScreen();

  public abstract void setRequestedOrientation(String paramString);

  public abstract void setRequestedOrientation(int paramInt);

  public abstract int getRequestedOrientation();

  public abstract void updateScreenInfo(int paramInt);

  public abstract Activity getActivity();

  public abstract ViewRect getAppViewRect();

  public abstract IWebAppRootView obtainWebAppRootView();

  public abstract void setOnCreateSplashView(IOnCreateSplashView paramIOnCreateSplashView);

  public abstract IOnCreateSplashView getOnCreateSplashView();

  public abstract void setWebAppRootView(IWebAppRootView paramIWebAppRootView);

  public abstract void setFullScreen(boolean paramBoolean);

  public abstract boolean isFullScreen();

  public abstract void setMaskLayer(boolean paramBoolean);

  public abstract int getMaskLayerCount();

  public abstract void clearMaskLayerCount();
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IAppInfo
 * JD-Core Version:    0.6.2
 */