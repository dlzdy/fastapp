/*    */ package io.dcloud.common.DHInterface;
/*    */ 
/*    */ import io.dcloud.common.adapter.util.MessageHandler;
/*    */ import io.dcloud.common.adapter.util.MessageHandler.IMessages;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class FeatureMessageDispatcher
/*    */ {
/* 14 */   public static ArrayList<MessageListener> sFeatureMessage = new ArrayList();
/*    */ 
/* 16 */   public static void registerListener(MessageListener paramMessageListener) { sFeatureMessage.add(paramMessageListener); }
/*    */ 
/*    */   public static void unregisterListener(MessageListener paramMessageListener) {
/* 19 */     sFeatureMessage.remove(paramMessageListener);
/*    */   }
/*    */ 
/*    */   public static void dispatchMessage(Object paramObject1, Object paramObject2)
/*    */   {
/*    */     try
/*    */     {
/* 29 */       for (MessageListener localMessageListener : sFeatureMessage) {
/* 30 */         if ((localMessageListener instanceof StrongMessageListener)) {
/* 31 */           if (paramObject1.equals(((StrongMessageListener)localMessageListener).mFlag)) {
/* 32 */             MessageHandler.sendMessage(new MessageHandler.IMessages()
/*    */             {
/*    */               public void execute(Object paramAnonymousObject) {
/* 35 */                 ((FeatureMessageDispatcher.MessageListener)paramAnonymousObject).onReceiver(this.val$msg);
/*    */               }
/*    */             }
/*    */             , localMessageListener);
/*    */           }
/*    */ 
/*    */         }
/* 39 */         else if (paramObject2 != null) {
/* 40 */           MessageHandler.sendMessage(new MessageHandler.IMessages()
/*    */           {
/*    */             public void execute(Object paramAnonymousObject) {
/* 43 */               ((FeatureMessageDispatcher.MessageListener)paramAnonymousObject).onReceiver(this.val$msg);
/*    */             }
/*    */           }
/*    */           , localMessageListener);
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 49 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/* 54 */   @Deprecated
/*    */   public static void dispatchMessage(Object paramObject) { dispatchMessage(null, paramObject); }
/*    */ 
/*    */ 
/*    */   public static abstract class StrongMessageListener
/*    */     implements FeatureMessageDispatcher.MessageListener
/*    */   {
/*    */     Object mFlag;
/*    */ 
/*    */     public StrongMessageListener(Object paramObject)
/*    */     {
/* 68 */       this.mFlag = paramObject;
/*    */     }
/*    */ 
/*    */     public abstract void onReceiver(Object paramObject);
/*    */   }
/*    */ 
/*    */   @Deprecated
/*    */   public static abstract interface MessageListener
/*    */   {
/*    */     public abstract void onReceiver(Object paramObject);
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.DHInterface.FeatureMessageDispatcher
 * JD-Core Version:    0.6.2
 */