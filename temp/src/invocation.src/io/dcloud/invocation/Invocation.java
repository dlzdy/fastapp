package io.dcloud.invocation;

import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;

public class Invocation
  implements IFeature
{
  a a;

  public String execute(IWebview paramIWebview, String paramString, String[] paramArrayOfString)
  {
    return this.a.a(paramIWebview, paramString, paramArrayOfString);
  }

  public void init(AbsMgr paramAbsMgr, String paramString)
  {
    this.a = new a(paramAbsMgr);
  }

  public void dispose(String paramString)
  {
    this.a.a(paramString);
  }
}