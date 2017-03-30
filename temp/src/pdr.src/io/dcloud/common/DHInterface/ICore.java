package io.dcloud.common.DHInterface;

import android.app.Activity;
import android.content.Context;

public abstract interface ICore
{
  public abstract Object dispatchEvent(IMgr.MgrType paramMgrType, int paramInt, Object paramObject);

  public abstract Context obtainContext();

  public abstract void removeStreamApp(String paramString);

  public abstract boolean isStreamAppMode();

  public abstract void setIsStreamAppMode(boolean paramBoolean);

  public abstract boolean onActivityExecute(Activity paramActivity, ISysEventListener.SysEventType paramSysEventType, Object paramObject);

  public static abstract interface ICoreEvent
  {
    public static final int GET_SDK_MODE = -1;
    public static final int WEBAPP_QUIT = 0;
    public static final int CHECK_IS_IBOOT_SERVICES = 1;
    public static final int WEBAPP_START = 2;
  }

  public static abstract interface ICoreStatusListener
  {
    public abstract void onCoreReady(ICore paramICore);

    public abstract void onCoreInitEnd(ICore paramICore);

    public abstract boolean onCoreStop();
  }
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.ICore
 * JD-Core Version:    0.6.2
 */