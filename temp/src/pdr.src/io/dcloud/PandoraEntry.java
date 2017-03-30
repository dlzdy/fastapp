/*    */ package io.dcloud;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.Intent;
/*    */ import android.os.Bundle;
/*    */ import android.os.Handler;
/*    */ 
/*    */ public class PandoraEntry extends Activity
/*    */ {
/*    */   protected void onCreate(Bundle paramBundle)
/*    */   {
/* 17 */     super.onCreate(paramBundle);
/* 18 */     Intent localIntent = getIntent();
/*    */ 
/* 20 */     boolean bool = false;
/*    */     try {
/* 22 */       bool = localIntent.getBooleanExtra("is_stream_app", bool);
/*    */     } catch (Exception localException) {
/* 24 */       localException.printStackTrace();
/* 25 */       finish();
/* 26 */       return;
/*    */     }
/* 28 */     if (bool) {
/* 29 */       localIntent.setClass(this, WebAppActivity.class);
/* 30 */       localIntent.putExtra("is_stream_app", true);
/*    */     }
/*    */     else {
/* 33 */       localIntent.putExtra("short_cut_class_name", PandoraEntry.class.getName());
/*    */ 
/* 35 */       localIntent.setClass(this, PandoraEntryActivity.class);
/*    */     }
/* 37 */     startActivity(localIntent);
/*    */ 
/* 39 */     new Handler().postDelayed(new Runnable()
/*    */     {
/*    */       public void run()
/*    */       {
/* 44 */         PandoraEntry.this.finish();
/*    */       }
/*    */     }
/*    */     , 20L);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.PandoraEntry
 * JD-Core Version:    0.6.2
 */