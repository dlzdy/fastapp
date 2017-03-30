/*    */ package io.dcloud.common.adapter.util;
/*    */ 
/*    */ import android.view.View;
/*    */ import io.dcloud.common.DHInterface.IFrameView;
/*    */ import org.json.JSONObject;
/*    */ 
/*    */ public class DragBean
/*    */ {
/* 13 */   public JSONObject dragCurrentViewOp = null;
/* 14 */   public JSONObject dragBindViewOp = null;
/* 15 */   public IFrameView dragBindWebView = null;
/* 16 */   public IFrameView dragCallBackWebView = null;
/* 17 */   public String dragCbId = null;
/* 18 */   public View nativeView = null;
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.DragBean
 * JD-Core Version:    0.6.2
 */