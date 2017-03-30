package com.dcloud.android.v4.view;

import android.view.View;

public abstract interface NestedScrollingParent
{
  public abstract boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt);

  public abstract void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt);

  public abstract void onStopNestedScroll(View paramView);

  public abstract void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public abstract void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt);

  public abstract boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean);

  public abstract boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2);

  public abstract int getNestedScrollAxes();
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.view.NestedScrollingParent
 * JD-Core Version:    0.6.2
 */