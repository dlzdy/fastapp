package io.dcloud.common.DHInterface;

import android.view.ViewGroup.LayoutParams;
import io.dcloud.common.adapter.ui.AdaFrameItem;

public abstract interface IContainerView
{
  public abstract void removeAllFrameItem();

  public abstract void removeFrameItem(AdaFrameItem paramAdaFrameItem);

  public abstract void addFrameItem(AdaFrameItem paramAdaFrameItem);

  public abstract void addFrameItem(AdaFrameItem paramAdaFrameItem, ViewGroup.LayoutParams paramLayoutParams);
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IContainerView
 * JD-Core Version:    0.6.2
 */