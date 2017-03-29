var plus = function(){

};
plus.runtime = {
    test: function (){
        return window.runtime.test();
    },
    quit: function (){
        return window.runtime.quit();
    }
};
plus.nativeUI = {
    test: function (){
        return window.webview.test();
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

/**
* init  plus.runtime

(function(e) {
    var plus = e;
    var _runtime= {
        test: function (){
            return window.runtime.test();
        },
        quit: function (){
            return window.runtime.quit();
        }
    };
    plus.runtime = _runtime;
}) (window.plus),
*/
/**
* init  plus.runtime

(function(e) {
    var plus = e;
    var _webview= {
        test: function (){
            return window.webview.test();
        },
    };
    plus.webview = _webview;
}) (window.plus),
*/
/**
* init  plus.nativeUI

(function(e) {
    var plus = e;
    var _nativeUI= {
        test: function (){
            return window.webview.test();
        },
        confirm: function (message, callback, title, btnArray){
            return window.nativeUI.confirm(message, callback, title, JSON.stringify(btnArray));
        },
        toast: function (message,options){
            return window.nativeUI.toast(message,JSON.stringify(options));
        }
    };
    plus.nativeUI = _nativeUI;
}) (window.plus),
*/