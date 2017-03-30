/*    */ package io.dcloud.common.adapter.util;
/*    */ 
/*    */ import android.os.AsyncTask;
/*    */ 
/*    */ public class AsyncTaskHandler
/*    */ {
/*    */   public static void executeAsyncTask(IAsyncTaskListener paramIAsyncTaskListener, String[] paramArrayOfString)
/*    */   {
/* 23 */     new MyAsyncTask(paramIAsyncTaskListener).execute(new String[][] { paramArrayOfString });
/*    */   }
/*    */ 
/*    */   public static abstract interface IAsyncTaskListener {
/*    */     public abstract void onExecuteBegin();
/*    */ 
/*    */     public abstract Object onExecuting();
/*    */ 
/*    */     public abstract void onExecuteEnd(Object paramObject);
/*    */ 
/*    */     public abstract void onCancel();
/*    */   }
/*    */ 
/*    */   static class MyAsyncTask extends AsyncTask<String[], Integer, Object> {
/* 37 */     AsyncTaskHandler.IAsyncTaskListener mListener = null;
/*    */ 
/* 39 */     MyAsyncTask(AsyncTaskHandler.IAsyncTaskListener paramIAsyncTaskListener) { this.mListener = paramIAsyncTaskListener; }
/*    */ 
/*    */ 
/*    */     protected void onPreExecute()
/*    */     {
/* 44 */       super.onPreExecute();
/* 45 */       this.mListener.onExecuteBegin();
/*    */     }
/*    */ 
/*    */     protected Object doInBackground(String[][] paramArrayOfString) {
/* 49 */       return this.mListener.onExecuting();
/*    */     }
/*    */ 
/*    */     protected void onPostExecute(Object paramObject)
/*    */     {
/* 54 */       super.onPostExecute(paramObject);
/* 55 */       this.mListener.onExecuteEnd(paramObject);
/*    */     }
/*    */ 
/*    */     protected void onCancelled()
/*    */     {
/* 60 */       super.onCancelled();
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.util.AsyncTaskHandler
 * JD-Core Version:    0.6.2
 */