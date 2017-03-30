package com.jeenms.app.plugin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.jeenms.app.commons.AbstractFeature;
import com.jeenms.app.util.JSONUtils;
import com.jeenms.app.util.JSUtils;
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
        if (JSUtils.isEmpty(title)) {
            title = "提示";
        }
        //button
        if (JSUtils.isEmpty(buttonCapture)) {
            buttonCapture = "确定";
        }
        commonDialog(message, callback,title,buttonCapture);
    }
    @JavascriptInterface
    public boolean confirm(String message,String callback, String title, String buttonCapture) {
        //title
        if (JSUtils.isEmpty(title)) {
            title = "确认";
        }
        //button
        if (JSUtils.isEmpty(buttonCapture)) {//左-是，右-否
            //buttonCapture = "否,是";
            buttonCapture = "取消,确定";
        }
        return commonDialog(message, callback,title,buttonCapture + "");
    }

    /**
     * 消息框
     * void plus.nativeUI.alert( message, alertCB, title, buttonCapture );
     message: ( String ) 必选 提示对话框上显示的内容
     alertCB: ( AlertCallback ) 可选 提示对话框上关闭后的回调函数
     title: ( String ) 可选 提示对话框上显示的标题
     buttonCapture: ( String ) 必选 提示对话框上按钮显示的内容 ["确定"]
     */
    private boolean commonDialog(String message, final String callback, String title, String buttonCapture) {
        //TODO　topActivity
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message);
        builder.setTitle(title);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        JSONArray jsonButton;
        if (JSUtils.isEmpty(buttonCapture)) {
            jsonButton = JSONUtils.createJSONArray("确定");
        }else{
            jsonButton = JSONUtils.createJSONArray(buttonCapture);
        }
        final String btnPostiveCaption =  JSONUtils.getString(jsonButton, 0);
        //第一个按钮
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, btnPostiveCaption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (JSUtils.isEmpty(callback)){
                    return;
                }
                String jsCallback =
                        "(function() { "
                                + "var callbackFun = " + callback + ";"
                                + "callbackFun({index:0});"
                                + "})()";
                JSUtils.exeJavaScript(getWebView(), jsCallback);
            }
        });
        //第二个按钮
        final String btnNegativeCaption =  JSONUtils.getString(jsonButton, 1);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, btnNegativeCaption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (JSUtils.isEmpty(callback)){
                    return;
                }
                String jsCallback =
                        "(function() { "
                                + "var callbackFun = " + callback + ";"
                                + "callbackFun({index:1});"
                                + "})()";
                JSUtils.exeJavaScript(getWebView(), jsCallback);
            }
        });
        //第三个按钮
        final String btnNeutralCaption =  JSONUtils.getString(jsonButton, 2);
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, btnNeutralCaption, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (JSUtils.isEmpty(callback)){
                    return;
                }
                String jsCallback =
                        "(function() { "
                                + "var callbackFun = " + callback + ";"
                                + "callbackFun({index:2});"
                                + "})()";
                JSUtils.exeJavaScript(getWebView(), jsCallback);
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

    /**
     * http://www.html5plus.org/doc/zh_cn/nativeui.html#plus.nativeUI.ToastOptions
     * @param toastOptions
     icon: (String 类型 )提示消息框上显示的图标
     style: (String 类型 )提示消息框上显示的样式
     可取值： "block"表示图标与文字分两行显示，上面显示图标，下面显示文字； "inline"表示图标与文字在同一行显示，左边显示图标，右边显示文字。 默认值为"block"。

     duration: (String 类型 )提示消息框显示的时间
     可选值为"long"、"short"，值为"long"时显示时间约为3.5s，值为"short"时显示时间约为2s，未设置时默认值为"short"。

     align: (String 类型 )提示消息框在屏幕中的水平位置
     可选值为"left"、"center"、"right"，分别为水平居左、居中、居右，未设置时默认值为"center"。

     verticalAlign: (String 类型 )提示消息在屏幕中的垂直位置
     可选值为"top"、"center"、"bottom"，分别为垂直居顶、居中、居底，未设置时默认值为"bottom"。
     */
    @JavascriptInterface
    public void toast(String message, String toastOptions){
        //TODO 解析toastOptions
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getContext(), message, duration);
        toast.show();
    }

    private class ToastOptions{
        String icon;
        String style;
        String duration;
        String align;
        String verticalAlign;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getAlign() {
            return align;
        }

        public void setAlign(String align) {
            this.align = align;
        }

        public String getVerticalAlign() {
            return verticalAlign;
        }

        public void setVerticalAlign(String verticalAlign) {
            this.verticalAlign = verticalAlign;
        }
    }
}
