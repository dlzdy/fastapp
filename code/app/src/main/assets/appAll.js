/**
* 判断是函数， true,false
*/
isFunc = function(obj){
    return typeof obj === 'function';
}
/**
* 判断是json格式, true ,false
*/
isJson = function(obj){
    return  (typeof obj == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length);
}

/**
* 方法转为字符串
*/
toString = function(obj){
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
//WebView
WebView = function(url, id, styles, extras){
    this.url = url;
    this.id = id;
    this.styles = styles;
    this.extras = extras;
    //
    this.addEventListener = function(event, listener, capture ) {
        plus.webview.addEventListener(event, listener, capture);
    };
    //
    this.removeEventListener = function(event, listener) {
         window.webview.removeEventListener(event, listener);
    };
    this.evalJS = function(js) {
         window.webview.evalJS(js);
    };
    this.show = function(aniShow, duration, showedCB, extras){
        window.webview.show(this.id, aniShow, duration, showedCB, extras);
    };
    this.parent = function() {
         window.webview.parent();
    };
};

plus = function(){

};


//Key管理设备按键事件
plus.key = {
    addEventListener: function(event, listener, capture){
        window.webview.addEventListener(event, listener, capture);
    },
    removeEventListener: function(event, listener){
        window.webview.removeEventListener(event, listener);
    },
};

//runtime
plus.runtime = {
    test: function (){
        return window.runtime.test();
    },
    quit: function (){
        return window.runtime.quit();
    },
    appid: function(){
        return window.runtime.appid();
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

plus.webview = {
    //
    test: function(){
        return window.webview.test();
    },
    create: function(url, id, style, extras){//return plus.webview
        var nativeResult = window.webview.create(url, id, style, extras);
        var jsonObj = JSON.parse(nativeResult);
        var webview = new WebView(jsonObj.url, jsonObj.id);
        return webview;
    },
    addEventListener: function(event, listener, capture){
        window.webview.addEventListener(event, listener, capture);
    },
    removeEventListener: function(event, listener){
        window.webview.removeEventListener(event, listener);
    },
    all: function(){
        var nativeResult = window.webview.all();
        return nativeRsult;
    },
    currentWebview: function(){ //return  plus.webview
        return window.webview.currentWebview();
    },
    getWebviewById: function(id){//return plus.webview
        return window.webview.getWebviewById(id);
    },
    evalJS: function(js){
        return window.webview.evalJS(js);
    },
    show: function(id, aniShow, duration, showedCB, extras){
        return window.webview.show(id, aniShow, duration, showedCB, extras);
    },
    parent: function(){
        return window.webview.parent();
    },

}
