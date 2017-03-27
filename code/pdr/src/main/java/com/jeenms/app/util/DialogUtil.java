package com.jeenms.app.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;

/**
 * Created by zhangdy on 2017/3/27.
 */

public class DialogUtil {

    public static AlertDialog.Builder initDialog(Context context) {
        AlertDialog.Builder builder = null;
        //TODO
        builder = new AlertDialog.Builder(context);
        return builder;
    }

}
