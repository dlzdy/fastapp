package com.jeenms.app.plugin;

/**
 * Created by zhangdy on 2017/4/7.
 */
public class WebViewObject {

    private String url;

    private String id;

    private String styles;

    private String extra;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStyles() {
        return styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "WebViewObject{" +
                "url='" + url + '\'' +
                ", id='" + id + '\'' +
                ", styles='" + styles + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
