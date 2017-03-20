package com.jeenms.app.commons;

/**
 *
 */
public interface IView {
    /**
     * 初始化视图
     *
     */
    public void initView();

    /**
     * 初始化数据
     */
    public void initData();

    /**
     * 初始化事件
     */
    public void initEvent();
}
