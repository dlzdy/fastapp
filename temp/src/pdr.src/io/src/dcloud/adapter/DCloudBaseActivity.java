/*    */ package io.src.dcloud.adapter;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.Intent;
/*    */ import io.dcloud.common.DHInterface.IReflectAble;
/*    */ 
/*    */ public class DCloudBaseActivity extends Activity
/*    */   implements IReflectAble
/*    */ {
/*  7 */   public Activity that = this;
/*    */ 
/*    */   protected final void onNewIntent(Intent paramIntent)
/*    */   {
/* 18 */     super.onNewIntent(paramIntent);
/* 19 */     onNewIntentImpl(paramIntent);
/*    */   }
/*    */ 
/*    */   public void onNewIntentImpl(Intent paramIntent)
/*    */   {
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.src.dcloud.adapter.DCloudBaseActivity
 * JD-Core Version:    0.6.2
 */