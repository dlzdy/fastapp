package io.dcloud.common.DHInterface;

import android.view.View;

public abstract interface IWebAppRootView
{
  public abstract View obtainMainView();

  public abstract void onAppStart(IApp paramIApp);

  public abstract void onAppActive(IApp paramIApp);

  public abstract void onAppUnActive(IApp paramIApp);

  public abstract void onAppStop(IApp paramIApp);

  public abstract void onRootViewGlobalLayout(View paramView);

  public abstract boolean didCloseSplash();

  public abstract IFrameView findFrameViewB(IFrameView paramIFrameView);
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IWebAppRootView
 * JD-Core Version:    0.6.2
 */