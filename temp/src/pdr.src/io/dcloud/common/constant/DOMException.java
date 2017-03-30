/*     */ package io.dcloud.common.constant;
/*     */ 
/*     */ import android.text.TextUtils;
/*     */ import io.dcloud.common.adapter.util.Logger;
/*     */ import java.util.IllegalFormatException;
/*     */ 
/*     */ public class DOMException
/*     */ {
/*     */   public static final String JSON_ERROR_INFO = "{code:%d,message:'%s'}";
/*     */   public static final String JSON_ERROR_INNE_INFO = "{code:%d,message:'%s',innerCode:%d}";
/*     */   public static final String STRING_ERROR_INFO = "[%s:%d]%s, %s";
/*     */   public static final String STRING_ERROR_INFO_THIRDSDK = "[%s%s:%d]%s";
/*     */   public static final String STRING_ERROR_INFO_THIRDSDK_NOCODE = "[%s%s]%s";
/*     */   public static final String STRING_ERROR_NOTLINK_INFO = "[%s:%d]%s";
/*     */   public static final String JSON_SHORTCUT_SUCCESS_INFO = "{sure:%s}";
/*     */   public static final String JSON_SHORTCUT_RESULT_INFO = "{result:%s}";
/*     */   public static final int CODE_NOT_FOUND_FILE = 0;
/*     */   public static final String MSG_NOT_FOUND_FILE = "not found file";
/*     */   public static final String ERROR_LINK = "http://ask.dcloud.net.cn/article/282";
/*     */   public static final String CODE = "code";
/*     */   public static final String MESSAGE = "message";
/*     */   public static final String INNERCODE = "innerCode";
/*     */   public static final int CODE_NOT_FOUND_3TH = 1;
/*     */   public static final String MSG_NOT_FOUND_3TH = "not found 3th activity";
/*     */   public static final int CODE_DECOMPRESS_ERROR = 2;
/*     */   public static final String MSG_DECOMPRESS_ERROR = "";
/*     */   public static final int CODE_RECORDER_ERROR = 3;
/*     */   public static final int CODE_PLAYER_ERROR = 4;
/*     */   public static final int CODE_PICK_DATE_ERROR = 5;
/*     */   public static final String MSG_PICK_DATE = "";
/*     */   public static final int CODE_PICK_TIME_ERROR = 6;
/*     */   public static final String MSG_PICK_TIME = "";
/*     */   public static final int CODE_RECOGNITION_ERROR = 7;
/*     */   public static final String MSG_RECOGNITION = "";
/*     */   public static final int CODE_BARCODE_ERROR = 8;
/*     */   public static final String MSG_BARCODE = "";
/*     */   public static final int CODE_OPERATE_DIR_ERROR = 9;
/*     */   public static final String MSG_OPERATE_DIR_ERROR = "operate_dir_error";
/*     */   public static final int CODE_INSTALL_WGT_ILLEGALITY_APPID_ERROR = 10;
/*     */   public static final String MSG_INSTALL_WGT_ILLEGALITY_APPID_ERROR = "非法的appid";
/*     */   public static final int CODE_CAMERA_ERROR = 11;
/*     */   public static final int CODE_GALLERY_ERROR = 12;
/*     */   public static final int CODE_MESSAGING_ERROR = 13;
/*     */   public static final int CODE_SHARE_AUTHORIZE_ERROR = 14;
/*     */   public static final String MSG_SHARE_AUTHORIZE_ERROR = "authorize";
/*     */   public static final int CODE_SHARE_SEND_ERROR = 15;
/*     */   public static final String MSG_SHARE_SEND_ERROR = "send";
/*     */   public static final String MSG_SHARE_SEND_PIC_ROUTE_ERROR = "分享图片仅支持本地路径";
/*     */   public static final String MSG_SHARE_SEND_CONTENT_EMPTY_ERROR = "分享文字不能为空";
/*     */   public static final int CODE_GEOLOCATION_HASNT_BAIDU_APPKEY = 16;
/*     */   public static final String MSG_GEOLOCATION_HASNT_BAIDU_APPKEY = "hasn't baidu appkey";
/*     */   public static final int CODE_GEOLOCATION_PROVIDER_ERROR = 17;
/*     */   public static final String MSG_GEOLOCATION_PROVIDER_ERROR = "指定的provider不存在或无效";
/*     */   public static final int CODE_GEOLOCATION_PERMISSION_ERROR = 22;
/*     */   public static final String MSG_GEOLOCATION_PERMISSION_ERROR = "获取定位权限失败";
/*     */   public static final int CODE_OAUTH_GET_SERVICES = 18;
/*     */   public static final String MSG_OAUTH_GET_SERVICES_ERROR = "获取授权登录认证服务操作失败";
/*     */   public static final int CODE_OAUTH_LOGIN = 19;
/*     */   public static final String MSG_OAUTH_LOGIN = "获取授权登录认证服务操作失败";
/*     */   public static final int CODE_OAUTH_LOGOUT = 20;
/*     */   public static final String MSG_OAUTH_LOGOUT = "获取授权登录认证服务操作失败";
/*     */   public static final int CODE_OAUTH_GET_USERINFO = 21;
/*     */   public static final String MSG_OAUTH_GET_USERINFO = "获取授权登录认证服务操作失败";
/*     */   public static final int CODE_PARAMETER_ERRORP = -1;
/*     */   public static final String MSG_PARAMETER_ERROR = "参数错误";
/*     */   public static final int CODE_USER_CANCEL = -2;
/*     */   public static final String MSG_USER_CANCEL = "用户取消";
/*     */   public static final int CODE_NOT_SUPPORT = -3;
/*     */   public static final String MSG_NOT_SUPPORT = "此功能不支持";
/*     */   public static final int CODE_FILE_NOT_EXIST = -4;
/*     */   public static final String MSG_FILE_NOT_EXIST = "文件不存在";
/*     */   public static final int CODE_IO_ERROR = -5;
/*     */   public static final String MSG_IO_ERROR = "IO 错误";
/*     */   public static final int CODE_NETWORK_ERROR = -6;
/*     */   public static final String MSG_NETWORK_ERROR = "网络错误";
/*     */   public static final int CODE_BUSINESS_PARAMETER_HAS_NOT = -7;
/*     */   public static final String MSG_BUSINESS_PARAMETER_HAS_NOT = "业务参数配置缺失";
/*     */   public static final int CODE_CLIENT_UNINSTALLED = -8;
/*     */   public static final String MSG_CLIENT_UNINSTALLED = "客户端未安装";
/*     */   public static final int CODE_SHORT_CUT_ALREADY_EXSIT = -9;
/*     */   public static final String MSG_SHORT_CUT_ALREADY_EXSIT = "快捷方式已存在";
/*     */   public static final int CODE_AUTHORIZE_FAILED = -10;
/*     */   public static final String MSG_AUTHORIZE_FAILED = "用户拒绝该API访问";
/*     */   public static final int CODE_UNKNOWN_ERROR = -99;
/*     */   public static final String MSG_UNKNOWN_ERROR = "未知错误";
/*     */   public static final int CODE_BUSINESS_INTERNAL_ERROR = -100;
/*     */   public static final String MSG_BUSINESS_INTERNAL_ERROR = "业务内部错误";
/*     */   public static final int CODE_OAUTH_FAIL = -1001;
/*     */   public static final String MSG_OAUTH_FAIL = "未登录或登录已注销";
/*     */   public static final int CODE_GET_TOKEN_ERROR = -1002;
/*     */   public static final String MSG_GET_TOKEN_ERROR = "获取token失败";
/*     */   public static final int CODE_UNOAUTH_ERROR = -1003;
/*     */   public static final String MSG_UNOAUTH_ERROR = "尚未获取oauth授权";
/*     */   public static final int CODE_RUNTIME_WGT_OR_WGTU_ERROR_MALFORMED = -1201;
/*     */   public static final String MSG_RUNTIME_WGT_OR_WGTU_ERROR_MALFORMED = "WGT/WGTU文件格式错误";
/*     */   public static final int CODE_RUNTIME_WGT_MANIFEST_NOT_EXIST = -1202;
/*     */   public static final String MSG_RUNTIME_WGT_MANIFEST_NOT_EXIST = "WGT安装包中manifest.json文件不存在";
/*     */   public static final int CODE_RUNTIME_WGT_MANIFEST_ERROR_MALFORMED = -1203;
/*     */   public static final String MSG_RUNTIME_WGT_MANIFEST_ERROR_MALFORMED = "WGT安装包中manifest.json文件格式错误";
/*     */   public static final int CODE_RUNTIME_WGT_MANIFEST_APPID_NOT_MATCH = -1204;
/*     */   public static final String MSG_RUNTIME_WGT_MANIFEST_APPID_NOT_MATCH = "WGT安装包中manifest.json文件的appid不匹配";
/*     */   public static final int CODE_RUNTIME_WGT_MANIFEST_VERSION_NOT_MATCH = -1205;
/*     */   public static final String MSG_RUNTIME_WGT_MANIFEST_VERSION_NOT_MATCH = "WGT安装包中manifest.json文件的version版本不匹配";
/*     */   public static final int CODE_RUNTIME_WGTU_UPDATE_NOT_EXIST = -1221;
/*     */   public static final String MSG_RUNTIME_WGTU_UPDATE_NOT_EXIST = "WGTU安装包中update.xml文件不存在";
/*     */   public static final int CODE_RUNTIME_WGTU_UPDATE_ERROR_MALFORMED = -1222;
/*     */   public static final String MSG_RUNTIME_WGTU_UPDATE_ERROR_MALFORMED = "WGTU安装包中update.xml文件格式错误";
/*     */   public static final int CODE_RUNTIME_WGTU_UPDATE_APPID_NOT_MATCH = -1223;
/*     */   public static final String MSG_RUNTIME_WGTU_UPDATE_APPID_NOT_MATCH = "WGTU安装包中update.xml文件的appid不匹配";
/*     */   public static final int CODE_RUNTIME_WGTU_UPDATE_VERSION_NOT_MATCH = -1224;
/*     */   public static final String MSG_RUNTIME_WGTU_UPDATE_VERSION_NOT_MATCH = "WGTU安装包中update.xml文件的version版本不匹配";
/*     */   public static final int CODE_RUNTIME_WGTU_WWW_MANIFEST_NOT_EXIST = -1225;
/*     */   public static final String MSG_RUNTIME_WGTU_WWW_MANIFEST_NOT_EXIST = "WGTU安装包中www目录下manifest.json不存在";
/*     */   public static final int CODE_RUNTIME_WGTU_WWW_MANIFEST_ERROR_MALFORMED = -1226;
/*     */   public static final String MSG_RUNTIME_WGTU_WWW_MANIFEST_ERROR_MALFORMED = "WGTU安装包中www目录下manifest.json文件格式错误";
/*     */   public static final int CODE_RUNTIME_WGTU_WWW_MANIFEST_APPID_NOT_MATCH = -1227;
/*     */   public static final String MSG_RUNTIME_WGTU_WWW_MANIFEST_APPID_NOT_MATCH = "WGTU安装包中www目录下manifest.json文件的appid不匹配";
/*     */   public static final int CODE_RUNTIME_WGTU_WWW_MANIFEST_VERSION_NOT_MATCH = -1228;
/*     */   public static final String MSG_RUNTIME_WGTU_WWW_MANIFEST_VERSION_NOT_MATCH = "WGTU安装包中www目录下manifest.json文件的version版本不匹配";
/*     */   public static final int CODE_RUNTIME_5PRUNTIME_LACK_MODULE = -1229;
/*     */   public static final String MSG_RUNTIME_5PRUNTIME_LACK_MODULE = "HTML5+ Runtime缺少升级包manifest.json中配置的模块";
/*     */   public static final int CODE_AUDIO_ERROR_MALFORMED = -1301;
/*     */   public static final String MSG_AUDIO_ERROR_MALFORMED = "文件播放格式错误";
/*     */   public static final int CODE_AUDIO_ERROR_TIMED_OUT = -1302;
/*     */   public static final String MSG_AUDIO_ERROR_TIMED_OUT = "文件请求超时";
/*     */ 
/*     */   public static String toJSON(int paramInt, String paramString)
/*     */   {
/* 167 */     String str = null;
/*     */     try {
/* 169 */       str = String.format("{code:%d,message:'%s'}", new Object[] { Integer.valueOf(paramInt), paramString });
/*     */     } catch (IllegalFormatException localIllegalFormatException) {
/* 171 */       Logger.e("DOMException is format error!!!");
/*     */     }
/* 173 */     return str;
/*     */   }
/*     */ 
/*     */   public static String toJSON(int paramInt1, String paramString, int paramInt2) {
/* 177 */     String str = null;
/*     */     try {
/* 179 */       str = String.format("{code:%d,message:'%s',innerCode:%d}", new Object[] { Integer.valueOf(paramInt1), paramString, Integer.valueOf(paramInt2) });
/*     */     } catch (IllegalFormatException localIllegalFormatException) {
/* 181 */       Logger.e("DOMException is format error!!!");
/*     */     }
/* 183 */     return str;
/*     */   }
/*     */ 
/*     */   public static String toStringForThirdSDK(String paramString1, String paramString2, int paramInt, String paramString3)
/*     */   {
/* 195 */     String str = null;
/*     */     try {
/* 197 */       str = String.format("[%s%s:%d]%s", new Object[] { paramString1, paramString2, Integer.valueOf(paramInt), paramString3 });
/*     */     } catch (IllegalFormatException localIllegalFormatException) {
/* 199 */       Logger.e("DOMException is format error!!!");
/*     */     }
/* 201 */     return str;
/*     */   }
/*     */ 
/*     */   public static String toStringForThirdSDK(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 212 */     String str = null;
/*     */     try {
/* 214 */       str = String.format("[%s%s]%s", new Object[] { paramString1, paramString2, paramString3 });
/*     */     } catch (IllegalFormatException localIllegalFormatException) {
/* 216 */       Logger.e("DOMException is format error!!!");
/*     */     }
/* 218 */     return str;
/*     */   }
/*     */   public static String toString(int paramInt, String paramString1, String paramString2, String paramString3) {
/* 221 */     String str = null;
/*     */     try {
/* 223 */       if (!TextUtils.isEmpty(paramString3))
/* 224 */         str = String.format("[%s:%d]%s, %s", new Object[] { paramString1, Integer.valueOf(paramInt), paramString2, paramString3 });
/*     */       else
/* 226 */         str = String.format("[%s:%d]%s", new Object[] { paramString1, Integer.valueOf(paramInt), paramString2 });
/*     */     }
/*     */     catch (IllegalFormatException localIllegalFormatException) {
/* 229 */       Logger.e("DOMException is format error!!!");
/*     */     }
/* 231 */     return str;
/*     */   }
/*     */ 
/*     */   public static String toString(String paramString1, String paramString2, String paramString3, String paramString4) {
/* 235 */     String str = null;
/*     */     try {
/* 237 */       if (!TextUtils.isEmpty(paramString4))
/* 238 */         str = "[" + paramString2 + ":" + paramString1 + "]" + " " + paramString3 + ",  " + paramString4;
/*     */       else
/* 240 */         str = "[" + paramString2 + ":" + paramString1 + "]" + " " + paramString3;
/*     */     }
/*     */     catch (IllegalFormatException localIllegalFormatException) {
/* 243 */       Logger.e("DOMException is format error!!!");
/*     */     }
/* 245 */     return str;
/*     */   }
/*     */ 
/*     */   public static String toString(String paramString) {
/* 249 */     return paramString + " " + "http://ask.dcloud.net.cn/article/282";
/*     */   }
/*     */ }

/* Location:           F:\xunlei\sdk\Android-SDK@1.9.9.29448_20170217\Android-SDK\SDK\libs\pdr.jar
 * Qualified Name:     io.dcloud.common.constant.DOMException
 * JD-Core Version:    0.6.2
 */