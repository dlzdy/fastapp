var plus = function(){

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
        return window.nativeUI.confirm(message, callback, title, JSON.stringify(btnArray));
    },
    confirm: function (message, callback, title, btnArray){
        return window.nativeUI.confirm(message, callback, title, JSON.stringify(btnArray));
    },
    toast: function (message,options){
        return window.nativeUI.toast(message,JSON.stringify(options));
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
