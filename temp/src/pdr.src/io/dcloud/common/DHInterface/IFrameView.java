package io.dcloud.common.DHInterface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import io.dcloud.common.adapter.ui.AdaWebViewParent;
import io.dcloud.common.adapter.util.ViewOptions;

public abstract interface IFrameView extends IContainerView, IFrameViewStatus
{
  public static final byte TRANS_FIRST = 0;
  public static final byte TRANS_SECOND = 1;
  public static final byte TRANS_CUSTOM = 2;
  public static final int WIN_TYPE_COMMON = 0;
  public static final int WIN_TYPE_PAGE = 1;
  public static final int WIN_LAUNCH_PAGE = 2;
  public static final int WIN_WAP_PAGE = 3;
  public static final int WIN_SECOND_PAGE = 4;

  public abstract int getFrameType();

  public abstract void setNeedRender(boolean paramBoolean);

  public abstract void setVisible(boolean paramBoolean1, boolean paramBoolean2);

  public abstract void captureSnapshot(String paramString, ICallBack paramICallBack1, ICallBack paramICallBack2);

  public abstract void clearSnapshot(String paramString);

  public abstract void draw(View paramView, INativeBitmap paramINativeBitmap, boolean paramBoolean, Rect paramRect, ICallBack paramICallBack1, ICallBack paramICallBack2);

  public abstract void transition(byte paramByte);

  public abstract void setFrameOptions_Animate(ViewOptions paramViewOptions);

  public abstract IWebview obtainWebView();

  public abstract AdaWebViewParent obtainWebviewParent();

  public abstract IApp obtainApp();

  public abstract AbsMgr obtainWindowMgr();

  public abstract Context getContext();

  public abstract View obtainMainView();

  public abstract IWebAppRootView obtainWebAppRootView();

  public abstract void setSnapshot(Bitmap paramBitmap);

  public abstract void setAccelerationType(String paramString);

  public abstract IFrameView findPageB();

  public abstract void animate(IWebview paramIWebview, String paramString1, String paramString2);

  public abstract void restore();

  public abstract void interceptTouchEvent(boolean paramBoolean);

  public abstract boolean isWebviewCovered();

  public abstract void pushToViewStack();

  public abstract void popFromViewStack();
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IFrameView
 * JD-Core Version:    0.6.2
 */