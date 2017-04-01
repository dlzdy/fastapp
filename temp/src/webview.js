function(e) {
    function t(e) {
        U[e.__uuid__] = e
    }
    function n(e) {
        delete U[e.__uuid__]
    }
    function i(t, n, i, o) {
        i = i || {},
        i.name = n;
        var r = new e.webview.Webview(t, i, !1, o);
        return r
    }
    function o(t, n, i) {
        var o = null;
        if ("string" == typeof t) o = u(t);
        else {
            if (! (t instanceof e.webview.Webview)) return;
            o = t
        }
        o && o.close(n, i)
    }
    function r() {
        for (var t = b.execSync(I, S, [I, "enumWindow", [window.__HtMl_Id__]]), n = [], i = {},
        o = 0; o < t.length; o++) {
            var r = t[o],
            a = U[r.uuid];
            a || (a = new e.webview.Webview(null, null, !0, r.extras), a.__uuid__ = r.uuid, a.id = r.id, b.exec(I, x, [I, "setcallbackid", [a.__uuid__, [a.__callback_id__]]])),
            n.push(a),
            i[a.__uuid__] = a
        }
        return U = i,
        n
    }
    function a() {
        return s()
    }
    function s() {
        if (D.platform == D.IOS) return ! 1;
        var e = b.execSync(I, S, [I, "defaultHardwareAccelerated", []]);
        return e
    }
    function c(t, n, i) {
        if (t && "string" == typeof t) {
            var o = U[t];
            return o || (o = new e.webview.Webview(null, null, !0, i), o.__uuid__ = t, o.id__ = n, o.id = n, b.exec(I, x, [I, "setcallbackid", [o.__uuid__, [o.__callback_id__]]]), U[t] = o),
            o
        }
    }
    function u(t) {
        if (t && "string" == typeof t) {
            var n = b.execSync(I, S, [I, "findWindowByName", [window.__HtMl_Id__, [t]]]);
            if (n) {
                var i = U[n.uuid];
                return null == i && (i = new e.webview.Webview(null, null, !0, n.extras), i.__uuid__ = n.uuid, i.id = n.id, b.exec(I, x, [I, "setcallbackid", [i.__uuid__, [i.__callback_id__]]]), U[i.__uuid__] = i),
                i
            }
            for (var o in U) {
                var r = U[o];
                if (r && r.id === t) return r
            }
            return null
        }
    }
    function l() {
        var t = U[window.__HtMl_Id__];
        if (null == t || void 0 === t) {
            var n = b.execSync2(I, S, [I, "currentWebview", [window.__HtMl_Id__]]);
            n && (t = new e.webview.Webview(null, null, !0, n.extras), t.__uuid__ = window.__HtMl_Id__, t.id = n.id, U[t.__uuid__] = t, b.exec(I, x, [I, "setcallbackid", [t.__uuid__, [t.__callback_id__]]]))
        }
        return t
    }
    function _() {
        var t = b.execSync2(I, S, [I, "getLaunchWebview", []]);
        if (t) {
            var n = U[t.uuid];
            return null == n && (n = new e.webview.Webview(null, null, !0, t.extras), n.__uuid__ = t.uuid, n.id = t.id, b.exec(I, x, [I, "setcallbackid", [n.__uuid__, [n.__callback_id__]]]), U[n.__uuid__] = n),
            n
        }
    }
    function f(t, n, i, o) {
        var r = null;
        if ("string" == typeof t) r = u(t);
        else {
            if (! (t instanceof e.webview.Webview)) return;
            r = t
        }
        r && r.hide(n, i, o)
    }
    function d(e, t, n) {
        b.exec(I, x, [e.__IDENTITY__, t, [e.__uuid__, n]])
    }
    function h(e, t, n) {
        return b.execSync(I, S, [e.__IDENTITY__, t, [e.__uuid__, n]])
    }
    function p(t, n, i, o, r, a, s) {
        var c = e.webview.create(t, n, i, s);
        return c.show(o, r, a),
        c
    }
    function v(t, n, i, o, r) {
        var a = null;
        if ("string" == typeof t) a = u(t);
        else {
            if (! (t instanceof e.webview.Webview)) return;
            a = t
        }
        a && a.show(n, i, o, r)
    }
    function y(e) {
        var t = U[e.WebviewID];
        t && ("onloading" == e.Event ? null != t.onloading && t.onloading() : "onclose" == e.Event ? (null != t.onclose && t.onclose(), delete U[e.WebviewID]) : "onerror" == e.Event ? null != t.onerror && t.onerror() : "onloaded" == e.Event && null != t.onloaded && t.onloaded())
    }
    function g() {
        "save" == arguments[0] ? b.exec(I, x, [I, "debug", [location.href, arguments]]) : b.execSync(I, x, [I, "debug", [location.href, arguments]])
    }
    function w() {
        if (D.IOS == D.platform) return null;
        var t = b.execSync(I, S, [I, "getWapLaunchWebview", []]);
        if (t) {
            var n = U[t.uuid];
            return null == n && (n = new e.webview.Webview(null, null, !0, t.extras), n.__uuid__ = t.uuid, n.id = t.id, b.exec(I, x, [I, "setcallbackid", [n.__uuid__, [n.__callback_id__]]]), U[n.__uuid__] = n),
            n
        }
        return null
    }
    function m() {
        var t = b.execSync(I, S, [I, "getTopWebview", [window.__HtMl_Id__]]);
        if (t) {
            var n = U[t.uuid];
            return null == n && (n = new e.webview.Webview(null, null, !0, t.extras), n.__uuid__ = t.uuid, n.id = t.id, b.exec(I, x, [I, "setcallbackid", [n.__uuid__, [n.__callback_id__]]]), U[n.__uuid__] = n),
            n
        }
        return null
    }
    var e = e,
    b = e.bridge,
    I = "UI",
    x = "execMethod",
    S = "syncExecMethod",
    U = {},
    D = (e.bridge, e.tools);
    e.webview = {
        open: p,
        show: v,
        __test__: g,
        hide: f,
        createGroup: null,
        getWapLaunchWebview: w,
        getLaunchWebview: _,
        getWebviewById: u,
        getTopWebview: m,
        close: o,
        create: i,
        currentWebview: l,
        all: r,
        defauleHardwareAccelerated: a,
        defaultHardwareAccelerated: s,
        exec: d,
        execSync: h,
        _find__Window_By_UUID__: c,
        __pushWindow__: t,
        __popWindow__: n,
        __JSON_Window_Stack: U,
        __Webview_LoadEvent_CallBack_: y
    }
} (window.plus),