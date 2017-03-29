package com.jeenms.app.util;

/**
 * Created by zhangdy on 2017/3/27.
 */

public class JsUtils {
    public static boolean isEmpty(final CharSequence cs) {
        return StringUtils.isEmpty(cs) || isUndefined(cs);
    }
    public static  boolean isUndefined(final CharSequence cs){
        return ("undefined".equals(cs));
    }
}
