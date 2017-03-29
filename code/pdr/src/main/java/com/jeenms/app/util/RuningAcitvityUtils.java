package com.jeenms.app.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.HashMap;
import java.util.List;


/**
 * Created by zhangdy on 2017/3/27.
 */

public class RuningAcitvityUtils {
    /**
     * <activity.getComponentName().getClassName(), activity>
     */
    private static HashMap<String, Activity> runingActivitys = new HashMap();


    /**
     * 缓存
     * @param activity
     */
    public static void putActivity(Activity activity) {
        String key = activity.getComponentName().getClassName();
        if(!runingActivitys.containsKey(key)) {
            runingActivitys.put(key, activity);
        }

    }

    /**
     * 删除activity
     * @param key
     */
    public static void removeActivity(String key) {
        if(runingActivitys.containsKey(key)) {
            runingActivitys.remove(key);
        }
    }

    /**
     * 得到Activities栈顶的Activity名称
     * @param activity
     * @return
     */
    public static Activity getTopActivity(Activity activity) {
        ActivityManager manager = (ActivityManager)activity.getSystemService(Context.ACTIVITY_SERVICE) ;
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1) ;
        if(runningTaskInfos != null) {
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTaskInfos.get(0);
            String key = runningTaskInfo.topActivity.getClassName();
            return runingActivitys.get(key);
        }else {
            return activity;
        }
    }


}
