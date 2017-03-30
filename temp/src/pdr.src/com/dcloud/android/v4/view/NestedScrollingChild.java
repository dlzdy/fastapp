package com.dcloud.android.v4.view;

public abstract interface NestedScrollingChild
{
  public abstract void setNestedScrollingEnabled(boolean paramBoolean);

  public abstract boolean isNestedScrollingEnabled();

  public abstract boolean startNestedScroll(int paramInt);

  public abstract void stopNestedScroll();

  public abstract boolean hasNestedScrollingParent();

  public abstract boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt);

  public abstract boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2);

  public abstract boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean);

  public abstract boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2);
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.NestedScrollingChild
 * JD-Core Version:    0.6.2
 */