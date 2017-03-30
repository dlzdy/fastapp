/*    */ package io.dcloud.common.util;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.graphics.Bitmap.Config;
/*    */ import android.text.TextUtils;
/*    */ import com.nostra13.dcloudimageloader.cache.disc.DiscCacheAware;
/*    */ import com.nostra13.dcloudimageloader.cache.disc.impl.UnlimitedDiscCache;
/*    */ import com.nostra13.dcloudimageloader.cache.memory.impl.LruMemoryCache;
/*    */ import com.nostra13.dcloudimageloader.core.DisplayImageOptions;
/*    */ import com.nostra13.dcloudimageloader.core.DisplayImageOptions.Builder;
/*    */ import com.nostra13.dcloudimageloader.core.ImageLoader;
/*    */ import com.nostra13.dcloudimageloader.core.ImageLoaderConfiguration;
/*    */ import com.nostra13.dcloudimageloader.core.ImageLoaderConfiguration.Builder;
/*    */ import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;
/*    */ import com.nostra13.dcloudimageloader.core.assist.QueueProcessingType;
/*    */ import com.nostra13.dcloudimageloader.core.decode.BaseImageDecoder;
/*    */ import com.nostra13.dcloudimageloader.core.download.BaseImageDownloader;
/*    */ import io.dcloud.common.adapter.util.DeviceInfo;
/*    */ import io.src.dcloud.adapter.DCloudAdapterUtil;
/*    */ import java.io.File;
/*    */ 
/*    */ public class ImageLoaderUtil
/*    */ {
/*    */   public static void initImageLoader(Context paramContext)
/*    */   {
/* 29 */     if (ImageLoader.getInstance().isInited()) {
/* 30 */       return;
/*    */     }
/* 32 */     File localFile = new File(getIconLoaclfolder());
/* 33 */     if (!localFile.exists()) {
/* 34 */       localFile.mkdirs();
/*    */     }
/* 36 */     ImageLoaderConfiguration.Builder localBuilder = new ImageLoaderConfiguration.Builder(paramContext).memoryCacheExtraOptions(400, 400).tasksProcessingOrder(QueueProcessingType.LIFO).denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2097152)).memoryCacheSize(2097152).threadPriority(3).threadPoolSize(3).discCacheFileCount(100).denyCacheImageMultipleSizesInMemory().defaultDisplayImageOptions(getIconDisplayOptions(paramContext)).discCache(new UnlimitedDiscCache(localFile)).imageDownloader(new BaseImageDownloader(paramContext)).imageDecoder(new BaseImageDecoder(false));
/*    */ 
/* 50 */     ImageLoaderConfiguration localImageLoaderConfiguration = localBuilder.build();
/* 51 */     ImageLoader.getInstance().init(localImageLoaderConfiguration);
/*    */   }
/*    */ 
/*    */   public static String getIconLoaclfolder() {
/* 55 */     return DeviceInfo.sBaseFsRootPath + "icons/";
/*    */   }
/*    */ 
/*    */   public static void updateIcon(String paramString) {
/* 59 */     if (TextUtils.isEmpty(paramString)) {
/* 60 */       return;
/*    */     }
/* 62 */     File localFile = ImageLoader.getInstance().getDiscCache().get(paramString);
/* 63 */     if (localFile.exists()) {
/* 64 */       localFile.delete();
/*    */     }
/* 66 */     ImageLoader.getInstance().loadImage(paramString, null);
/*    */   }
/*    */ 
/*    */   public static DisplayImageOptions getIconDisplayOptions(Context paramContext) {
/* 70 */     DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().cacheOnDisc(true).cacheInMemory(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT).bitmapConfig(Bitmap.Config.RGB_565).showImageOnLoading(DCloudAdapterUtil.getImageOnLoadingId(paramContext)).build();
/*    */ 
/* 80 */     return localDisplayImageOptions;
/*    */   }
/*    */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.util.ImageLoaderUtil
 * JD-Core Version:    0.6.2
 */