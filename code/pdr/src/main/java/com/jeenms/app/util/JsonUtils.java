package com.jeenms.app.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangdy on 2017/3/27.
 */

public class JSONUtils {

    /**
     * "abc"-->[abc]
     * @param value
     * @return
     */
    public static JSONArray createJSONArray(String value) {
        if(!value.startsWith("[")) {
            value = "[" + value;
        }

        if(!value.endsWith("]")) {
            value = value + "]";
        }

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(value);
        } catch (JSONException e) {

        }
        return jsonArray;
    }

    /**
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static String getString(JSONObject jsonObject, String key) {
        try {
            return jsonObject == null?null:jsonObject.getString(key);
        } catch (Exception var3) {
            return null;
        }
    }

    /**
     * ["确定"]-->确定
     * @param jsonObject
     * @param index
     * @return
     */
    public static String getString(JSONArray jsonObject, int index) {
        try {
            Object obj = jsonObject.get(index);
            if(isNotEmpty(obj)) {
                return String.valueOf(obj);
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
    /**
     * [''] 也为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return obj == null || obj.equals("") || obj.toString().length() == 4 && obj.toString().toLowerCase().equals("null");
    }
    /**
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static long getLong(JSONObject jsonObject, String key) {
        try {
            return jsonObject != null && jsonObject.has(key)?jsonObject.getLong(key):0L;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }


    public static boolean getBoolean(JSONObject var0, String var1) {
        try {
            return var0 != null && var0.has(var1)?var0.getBoolean(var1):false;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

}
