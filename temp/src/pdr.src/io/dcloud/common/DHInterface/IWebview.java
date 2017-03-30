package io.dcloud.common.DHInterface;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.webkit.WebView;
import io.dcloud.common.adapter.ui.ReceiveJSValue.ReceiveJSValueCallback;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract interface IWebview extends IContainerView
{
  public static final String USER_AGENT = "User-Agent";
  public static final String SET_COOKIE = "Set-Cookie";
  public static final String COOKIE = "Cookie";

  public abstract void setFlag(Object paramObject);

  public abstract Object getFlag();

  public abstract void show(Animation paramAnimation);

  public abstract void setFrameId(String paramString);

  public abstract String obtainFrameId();

  public abstract void setScrollIndicator(String paramString);

  public abstract float getScale();

  public abstract float getScaleOfOpenerWebview();

  public abstract void endWebViewEvent(String paramString);

  public abstract void setWebViewEvent(String paramString, Object paramObject);

  public abstract void initWebviewUUID(String paramString);

  public abstract String getWebviewUUID();

  public abstract void reload();

  public abstract void reload(String paramString);

  public abstract void reload(boolean paramBoolean);

  public abstract String obtainUrl();

  public abstract String obtainPageTitle();

  public abstract String obtainFullUrl();

  public abstract void loadUrl(String paramString);

  public abstract void loadContentData(String paramString1, String paramString2, String paramString3);

  public abstract IFrameView obtainFrameView();

  public abstract IApp obtainApp();

  public abstract void evalJS(String paramString);

  public abstract void evalJS(String paramString, ReceiveJSValue.ReceiveJSValueCallback paramReceiveJSValueCallback);

  public abstract void executeScript(String paramString);

  public abstract void addJsInterface(String paramString, IJsInterface paramIJsInterface);

  public abstract void addJsInterface(String paramString1, String paramString2);

  public abstract Context getContext();

  public abstract Activity getActivity();

  public abstract void setPreloadJsFile(String paramString);

  public abstract void appendPreloadJsFile(String paramString);

  public abstract void addStateListener(IWebviewStateListener paramIWebviewStateListener);

  public abstract void removeStateListener(IWebviewStateListener paramIWebviewStateListener);

  public abstract boolean canGoBack();

  public abstract void goBackOrForward(int paramInt);

  public abstract boolean canGoForward();

  public abstract void stopLoading();

  public abstract void clearHistory();

  public abstract WebView obtainWebview();

  public abstract String getWebviewProperty(String paramString);

  public abstract void setWebviewProperty(String paramString1, String paramString2);

  public abstract boolean isLoaded();

  public abstract void setOriginalUrl(String paramString);

  public abstract String getOriginalUrl();

  public abstract void onRootViewGlobalLayout(View paramView);

  public abstract void setOverrideUrlLoadingData(JSONObject paramJSONObject);

  public abstract void setOverrideResourceRequest(JSONArray paramJSONArray);

  public abstract void setListenResourceLoading(JSONObject paramJSONObject);

  public abstract boolean unReceiveTitle();

  public abstract void setWebViewCacheMode(String paramString);

  public abstract void setCssFile(String paramString1, String paramString2);

  public abstract void setFixBottom(int paramInt);

  public abstract int getFixBottom();

  public abstract boolean checkWhite(String paramString);
}

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.IWebview
 * JD-Core Version:    0.6.2
 */