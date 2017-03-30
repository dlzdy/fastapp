/*     */ package io.dcloud.common.adapter.ui;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.view.ViewGroup.LayoutParams;
/*     */ import android.view.ViewParent;
/*     */ import io.dcloud.common.DHInterface.IContainerView;
/*     */ import io.dcloud.common.DHInterface.INativeView;
/*     */ import io.dcloud.common.DHInterface.ITypeofAble;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public abstract class AdaContainerFrameItem extends AdaFrameItem
/*     */   implements IContainerView
/*     */ {
/*  28 */   private boolean isITypeofAble = false;
/*     */ 
/*  32 */   public ArrayList<AdaFrameItem> mChildArrayList = null;
/*  33 */   public ArrayList<INativeView> mChildNativeViewList = null;
/*     */ 
/*  35 */   protected AdaContainerFrameItem(Context paramContext) { super(paramContext);
/*  36 */     this.mChildArrayList = new ArrayList(1); }
/*     */ 
/*     */   public boolean checkITypeofAble()
/*     */   {
/*  40 */     if (this.isITypeofAble) {
/*  41 */       return this.isITypeofAble;
/*     */     }
/*  43 */     for (AdaFrameItem localAdaFrameItem : this.mChildArrayList) {
/*  44 */       if ((localAdaFrameItem instanceof AdaContainerFrameItem)) {
/*  45 */         AdaContainerFrameItem localAdaContainerFrameItem = (AdaContainerFrameItem)localAdaFrameItem;
/*  46 */         return localAdaContainerFrameItem.checkITypeofAble();
/*     */       }
/*     */     }
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   public void addNativeViewChild(INativeView paramINativeView) {
/*  53 */     if (this.mChildNativeViewList == null) {
/*  54 */       this.mChildNativeViewList = new ArrayList();
/*     */     }
/*  56 */     if (!this.mChildNativeViewList.contains(paramINativeView))
/*  57 */       this.mChildNativeViewList.add(paramINativeView);
/*     */   }
/*     */ 
/*     */   public void removeNativeViewChild(INativeView paramINativeView)
/*     */   {
/*  62 */     if ((this.mChildNativeViewList != null) && (this.mChildNativeViewList.contains(paramINativeView)))
/*  63 */       this.mChildNativeViewList.remove(paramINativeView);
/*     */   }
/*     */ 
/*     */   public ArrayList<INativeView> getChildNativeViewList()
/*     */   {
/*  68 */     return this.mChildNativeViewList;
/*     */   }
/*     */ 
/*     */   public ViewGroup obtainMainViewGroup()
/*     */   {
/*  76 */     return (ViewGroup)this.mViewImpl;
/*     */   }
/*     */ 
/*     */   public void addFrameItem(AdaFrameItem paramAdaFrameItem, ViewGroup.LayoutParams paramLayoutParams)
/*     */   {
/*  85 */     addFrameItem(paramAdaFrameItem, -1, paramLayoutParams);
/*     */   }
/*     */ 
/*     */   public void addFrameItem(AdaFrameItem paramAdaFrameItem)
/*     */   {
/*  93 */     addFrameItem(paramAdaFrameItem, -1);
/*     */   }
/*     */ 
/*     */   public void addFrameItem(AdaFrameItem paramAdaFrameItem, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
/*     */   {
/* 102 */     if ((paramAdaFrameItem instanceof ITypeofAble)) {
/* 103 */       this.isITypeofAble = true;
/*     */     }
/* 105 */     if ((this.mViewImpl instanceof ViewGroup)) {
/* 106 */       View localView = paramAdaFrameItem.obtainMainView();
/* 107 */       ViewParent localViewParent = localView.getParent();
/* 108 */       if (localViewParent != null) {
/* 109 */         ((ViewGroup)localViewParent).removeView(localView);
/* 110 */         this.mChildArrayList.remove(paramAdaFrameItem);
/*     */       }
/* 112 */       ((ViewGroup)this.mViewImpl).addView(localView, paramInt, paramLayoutParams);
/* 113 */       if ((paramInt < 0) || (paramInt > this.mChildArrayList.size())) {
/* 114 */         paramInt = this.mChildArrayList.size();
/*     */       }
/* 116 */       this.mChildArrayList.add(paramInt, paramAdaFrameItem);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addFrameItem(AdaFrameItem paramAdaFrameItem, int paramInt)
/*     */   {
/* 126 */     ViewGroup.LayoutParams localLayoutParams = paramAdaFrameItem.obtainMainView().getLayoutParams();
/* 127 */     addFrameItem(paramAdaFrameItem, paramInt, localLayoutParams == null ? new ViewGroup.LayoutParams(-1, -1) : localLayoutParams);
/*     */   }
/*     */ 
/*     */   protected void onResize()
/*     */   {
/* 132 */     super.onResize();
/* 133 */     for (AdaFrameItem localAdaFrameItem : this.mChildArrayList)
/* 134 */       localAdaFrameItem.onResize();
/*     */   }
/*     */ 
/*     */   public void removeFrameItem(AdaFrameItem paramAdaFrameItem)
/*     */   {
/* 143 */     if ((paramAdaFrameItem instanceof ITypeofAble)) {
/* 144 */       this.isITypeofAble = false;
/*     */     }
/* 146 */     if (this.mViewImpl != null) {
/* 147 */       ((ViewGroup)this.mViewImpl).removeView(paramAdaFrameItem.obtainMainView());
/* 148 */       this.mChildArrayList.remove(paramAdaFrameItem);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeAllFrameItem()
/*     */   {
/* 155 */     if (this.mViewImpl != null) {
/* 156 */       ((ViewGroup)this.mViewImpl).removeAllViews();
/* 157 */       clearView();
/*     */     }
/* 159 */     this.isITypeofAble = false;
/*     */   }
/*     */ 
/*     */   public ArrayList<AdaFrameItem> getChilds()
/*     */   {
/* 166 */     return this.mChildArrayList;
/*     */   }
/*     */ 
/*     */   public void onPopFromStack(boolean paramBoolean)
/*     */   {
/* 172 */     super.onPopFromStack(paramBoolean);
/* 173 */     if (this.mChildArrayList != null)
/* 174 */       for (AdaFrameItem localAdaFrameItem : this.mChildArrayList)
/* 175 */         localAdaFrameItem.onPopFromStack(paramBoolean);
/*     */   }
/*     */ 
/*     */   public void onPushToStack(boolean paramBoolean)
/*     */   {
/* 183 */     super.onPushToStack(paramBoolean);
/* 184 */     if (this.mChildArrayList != null)
/* 185 */       for (AdaFrameItem localAdaFrameItem : this.mChildArrayList)
/* 186 */         localAdaFrameItem.onPushToStack(paramBoolean);
/*     */   }
/*     */ 
/*     */   public boolean onDispose()
/*     */   {
/* 192 */     boolean bool = super.onDispose();
/* 193 */     if (this.mChildArrayList != null) {
/* 194 */       for (AdaFrameItem localAdaFrameItem : this.mChildArrayList) {
/* 195 */         bool |= localAdaFrameItem.onDispose();
/*     */       }
/*     */     }
/* 198 */     return bool;
/*     */   }
/*     */ 
/*     */   public void dispose() {
/* 202 */     super.dispose();
/* 203 */     clearView();
/*     */   }
/*     */   public void clearView() {
/* 206 */     if (((this.mViewImpl instanceof ViewGroup)) && 
/* 207 */       (this.mViewImpl != null)) {
/* 208 */       ((ViewGroup)this.mViewImpl).removeAllViews();
/*     */     }
/*     */ 
/* 211 */     if (this.mChildArrayList != null) {
/* 212 */       for (AdaFrameItem localAdaFrameItem : this.mChildArrayList) {
/* 213 */         localAdaFrameItem.dispose();
/*     */       }
/* 215 */       this.mChildArrayList.clear();
/*     */     }
/* 217 */     this.isITypeofAble = false;
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.adapter.ui.AdaContainerFrameItem
 * JD-Core Version:    0.6.2
 */