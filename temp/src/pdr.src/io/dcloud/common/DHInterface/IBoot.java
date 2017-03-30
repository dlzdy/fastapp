package io.dcloud.common.DHInterface;

import android.content.Context;
import android.os.Bundle;

public abstract interface IBoot extends ISysEventListener
{
  public abstract void onStart(Context paramContext, Bundle paramBundle, String[] paramArrayOfString);

  public abstract void onStop();

  public abstract void onPause();

  public abstract void onResume();
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IBoot
 * JD-Core Version:    0.6.2
 */