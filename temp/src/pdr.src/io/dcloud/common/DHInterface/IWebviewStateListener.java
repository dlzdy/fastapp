package io.dcloud.common.DHInterface;

public abstract interface IWebviewStateListener extends ICallBack
{
  public static final int ON_WEBVIEW_READY = -1;
  public static final int ON_PAGE_STARTED = 0;
  public static final int ON_PAGE_FINISHED = 1;
  public static final int ON_LOAD_RESOURCE = 2;
  public static final int ON_PROGRESS_CHANGED = 3;
  public static final int ON_RECEIVED_TITLE = 4;
  public static final int ON_RECEIVED_ERROR = 5;
  public static final int ON_WEBVIEW_RENDERING = 6;
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IWebviewStateListener
 * JD-Core Version:    0.6.2
 */