package io.dcloud.common.DHInterface;

public abstract interface IFrameViewStatus
{
  public static final byte STATUS_ON_INIT = 0;
  public static final byte STATUS_ON_PRE_LOADING = 1;
  public static final byte STATUS_ON_LOADING = 2;
  public static final byte STATUS_ON_PRESHOW = 3;
  public static final byte STATUS_ON_DESTROY = 4;
  public static final byte STATUS_ON_PAGE_CHANGED = 5;

  public abstract byte obtainStatus();

  public abstract void onInit();

  public abstract void onPreLoading();

  public abstract void onLoading();

  public abstract void onPreShow(IFrameView paramIFrameView);

  public abstract void onDestroy();

  public abstract void addFrameViewListener(IEventCallback paramIEventCallback);

  public abstract void removeFrameViewListener(IEventCallback paramIEventCallback);
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IFrameViewStatus
 * JD-Core Version:    0.6.2
 */