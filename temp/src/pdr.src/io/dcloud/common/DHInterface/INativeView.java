package io.dcloud.common.DHInterface;

import android.view.View;
import org.json.JSONObject;

public abstract interface INativeView
{
  public abstract View obtanMainView();

  public abstract JSONObject toJSON();

  public abstract void setStyleLeft(int paramInt);

  public abstract int getStyleLeft();

  public abstract int getStyleWidth();

  public abstract String getViewId();

  public abstract String getViewUUId();
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.INativeView
 * JD-Core Version:    0.6.2
 */