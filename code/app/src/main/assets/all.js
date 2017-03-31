/**
* 判断是函数， true,false
*/
var isFunc = function(obj){
    //alert("isFunc obj--> " + obj);
    //alert("isFunc typeof obj -->" + typeof obj );
    return typeof obj === 'function';
}
/**
* 判断是json格式, true ,false
*/
var isJson = function(obj){
    return  (typeof obj == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length);
}

/**
* 方法转为字符串
*/
var toString = function(obj){
    //alert("toString obj--> " + obj);
    if (obj === null || obj === undefined || obj === ''){//空
        return null;
    }
    if (isFunc(obj)){//函数
        return obj.toString();
    }
    if (isJson(obj)){//isJson
        return JSON.stringify(obj);
    }
    return obj;
};



var plus = function(){

};
//Key管理设备按键事件
plus.key = {
//    keyEvent = {},
//    keyEvent.backbutton = "back",
//    keyEvent.menubutton = "menu",
//    keyEvent.searchbutton = "search",
//    keyEvent.volumeupbutton = "volumeup",
//    keyEvent.volumedownbutton = "volumedown",
//    keyEvent.keyup = "keyup",
//    keyEvent.keydown = "keydown",
//    keyEvent.longpressed = "longpressed",
    addEventListener: function(o, r, a) {
        function s(e) {
            var t = {};
            t.keycode = e.keyType,
            t.keyCode = e.keyCode,
            t.keyType = e.keyType,
            r(t);
        }
    },
    removeEventListener: function(o, r) {

    },

};

//runtime
plus.runtime = {
    test: function (){
        return window.runtime.test();
    },
    quit: function (){
        return window.runtime.quit();
    }
};
//nativeUI
plus.nativeUI = {
    test: function (){
        return window.nativeUI.test();
    },
    alert: function (message, callback, title, btnArray){
        return window.nativeUI.alert(message, toString(callback), title, toString(btnArray));
    },
    confirm: function (message, callback, title, btnArray){
        return window.nativeUI.confirm(message, toString(callback), title, toString(btnArray));
    },
    toast: function (message,options){
        return window.nativeUI.toast(message, toString(options));
    }
}

//webview
plus.webview = {
    test: function (){
        return window.webview.test();
    },
    currentWebview: function (){
        return window.webview.currentWebview();
    },
}
