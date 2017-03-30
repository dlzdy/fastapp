/*    */ package io.dcloud.common.adapter.ui.fresh;
/*    */ 
/*    */ public abstract interface ILoadingLayout
/*    */ {
/*    */   public abstract void setState(State paramState);
/*    */ 
/*    */   public abstract State getState();
/*    */ 
/*    */   public abstract int getContentSize();
/*    */ 
/*    */   public abstract void onPull(float paramFloat);
/*    */ 
/*    */   public static enum State
/*    */   {
/* 18 */     NONE, 
/*    */ 
/* 24 */     RESET, 
/*    */ 
/* 30 */     PULL_TO_REFRESH, 
/*    */ 
/* 36 */     RELEASE_TO_REFRESH, 
/*    */ 
/* 41 */     REFRESHING, 
/*    */ 
/* 46 */     LOADING, 
/*    */ 
/* 52 */     NO_MORE_DATA;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.fresh.ILoadingLayout
 * JD-Core Version:    0.6.2
 */