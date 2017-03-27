package com.jeenms.app.plugin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.Toast;

import com.jeenms.app.commons.AbstractFeature;
import com.jeenms.app.util.DialogUtil;
import com.jeenms.app.util.JSONUtil;
import com.jeenms.app.util.RuningAcitvityUtil;
import com.jeenms.app.util.StringUtils;

import org.json.JSONArray;

/**
 * 原生的UI  参考dcloud.NativeUIFeatureImpl
 * 1.alert
 * 2.confirm
 * 3.prompt
 * 4.toast
 *
 * WaitingView
 * closeWaiting
 * WaitingView_close
 * WaitingView_setTitle
 * showMenu
 * isTitlebarVisible
 * getTitlebarHeight
 * setTitlebarVisible
 *
 * pickDate
 * pickTime
 * actionSheet
 *
 * Created by zhangdy on 2017/3/27.
 */

public class NativeUIFeatureImpl extends AbstractFeature {

    public NativeUIFeatureImpl(WebView webView) {
        super(webView);
    }

//    public void alert() {
//        Toast.makeText(context, this.getClass().getSimpleName()  +  " test" , Toast.LENGTH_SHORT).show();
//    }

    /**
     * 警告消息框
     * void plus.nativeUI.alert( message, alertCB, title, buttonCapture );
     message: ( String ) 必选 提示对话框上显示的内容
     alertCB: ( AlertCallback ) 可选 提示对话框上关闭后的回调函数
     title: ( String ) 可选 提示对话框上显示的标题
     buttonCapture: ( String ) 必选 提示对话框上按钮显示的内容 ["确定"]
     */
    @JavascriptInterface
    public void alert(String message, String callback, String title, String buttonCapture) {
        //title
        if (StringUtils.isEmpty(title)) {
            title = "提示";
        }
        //button
        if (StringUtils.isEmpty(buttonCapture)) {
            buttonCapture = "确定";
        }

        aaa(message, callback,title,buttonCapture);
//        //title
//        builder.setTitle(title);
//
//        //button
//        JSONArray jsonButton;
//        if (StringUtils.isEmpty(buttonCapture)) {
//            jsonButton = JSONUtil.createJSONArray("确定");
//        }else{
//            jsonButton = JSONUtil.createJSONArray(buttonCapture);
//        }
//        String btnOk =  JSONUtil.getString(jsonButton, 0);
//
//        builder.setPositiveButton(btnOk, null);
//        AlertDialog dialog = builder.create();
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
    }
    @JavascriptInterface
    public boolean confirm(String message,String callback, String title,String buttonCapture) {
        //title
        if (StringUtils.isEmpty(title)) {
            title = "确认";
        }
        //button
        JSONArray jsonButton = null;
        if (StringUtils.isEmpty(buttonCapture)) {//左-是，右-否
            buttonCapture = "是,否";
        }
        return aaa(message, callback,title,buttonCapture);
    }


    private boolean aaa(String message,String callback, String title, String buttonCapture) {
        //TODO　topActivity
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setTitle(title);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        JSONArray jsonButton;
        if (StringUtils.isEmpty(buttonCapture)) {
            jsonButton = JSONUtil.createJSONArray("确定");
        }else{
            jsonButton = JSONUtil.createJSONArray(buttonCapture);
        }
        final String btnPostiveCaption =  JSONUtil.getString(jsonButton, 0);
        //第一个按钮
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, btnPostiveCaption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, btnPostiveCaption , Toast.LENGTH_SHORT).show();
            }
        });
        //第二个按钮
        final String btnNegativeCaption =  JSONUtil.getString(jsonButton, 1);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, btnNegativeCaption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, btnNegativeCaption , Toast.LENGTH_SHORT).show();
            }
        });
        //第三个按钮
        final String btnNeutralCaption =  JSONUtil.getString(jsonButton, 2);
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, btnNeutralCaption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, btnNeutralCaption , Toast.LENGTH_SHORT).show();
            }
        });

        //按取消键的处理
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    //JSUtil.execCallback(var7, var8, "{index:-1}", JSUtil.OK, true, false);
                    return true;
                } else {
                    return false;
                }
            }
        });
        //
        alertDialog.show();
        return true;
    }


}
