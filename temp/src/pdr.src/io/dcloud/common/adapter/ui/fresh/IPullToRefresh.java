package io.dcloud.common.adapter.ui.fresh;

import android.view.View;

public abstract interface IPullToRefresh<T extends View>
{
  public abstract void setPullRefreshEnabled(boolean paramBoolean);

  public abstract void setPullLoadEnabled(boolean paramBoolean);

  public abstract void setScrollLoadEnabled(boolean paramBoolean);

  public abstract boolean isPullRefreshEnabled();

  public abstract boolean isPullLoadEnabled();

  public abstract boolean isScrollLoadEnabled();

  public abstract void setOnRefreshListener(PullToRefreshBase.OnRefreshListener<T> paramOnRefreshListener);

  public abstract void onPullDownRefreshComplete();

  public abstract void onPullUpRefreshComplete();

  public abstract T getRefreshableView();

  public abstract LoadingLayout getHeaderLoadingLayout();

  public abstract LoadingLayout getFooterLoadingLayout();

  public abstract void setLastUpdatedLabel(CharSequence paramCharSequence);
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.fresh.IPullToRefresh
 * JD-Core Version:    0.6.2
 */