package com.dcloud.android.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import com.dcloud.android.annotation.Nullable;

public abstract interface TintableBackgroundView
{
  public abstract void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList);

  @Nullable
  public abstract ColorStateList getSupportBackgroundTintList();

  public abstract void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode);

  @Nullable
  public abstract PorterDuff.Mode getSupportBackgroundTintMode();
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.TintableBackgroundView
 * JD-Core Version:    0.6.2
 */