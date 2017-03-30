package com.dcloud.android.v4.widget;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.ViewGroup;
import org.json.JSONObject;

public abstract interface IRefreshAble
{
  public abstract void endRefresh();

  public abstract void beginRefresh();

  public abstract void parseData(JSONObject paramJSONObject, int paramInt1, int paramInt2, float paramFloat);

  public abstract void onResize(int paramInt1, int paramInt2, float paramFloat);

  public abstract void onInit(ViewGroup paramViewGroup, OnRefreshListener paramOnRefreshListener);

  public abstract void onSelfDraw(Canvas paramCanvas);

  public abstract boolean onSelfTouchEvent(MotionEvent paramMotionEvent);

  public abstract boolean isRefreshing();

  public abstract void setRefreshEnable(boolean paramBoolean);

  public abstract boolean isRefreshEnable();

  public static abstract interface OnRefreshListener
  {
    public static final int STATE_REFRESHING = 3;

    public abstract void onRefresh(int paramInt);
  }
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     com.dcloud.android.v4.widget.IRefreshAble
 * JD-Core Version:    0.6.2
 */