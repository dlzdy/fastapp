package com.jeenms.app.util;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangdy on 2017/3/27.
 */

public class JSONUtils {
    private static String TAG = "JSONUtils";

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
     *  {a:1} key=a-->1
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
     * 获取key对应的值
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

    /**
     * 获取key对应的值
     * @param jsonObject
     * @param key
     * @return
     */
    public static boolean getBoolean(JSONObject jsonObject, String key) {
        try {
            return jsonObject != null && jsonObject.has(key)?jsonObject.getBoolean(key):false;
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    /**
     * 对象序列化为json
     * 调用fastjson
     * @param obj
     * @return
     */
    public static String toJSON(Object obj) {
        return JSON.toJSON(obj) + "";
    }

    /**
     * json --> Object
     * 调用fastjson
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws JSONException
     */
    public static  <T> T toObject(String jsonStr, Class<T> clazz) throws JSONException {
        return JSON.parseObject(jsonStr, clazz);
    }
}
