package com.jeenms.app.commons;

import android.app.Activity;
import android.os.Bundle;

import com.jeenms.app.commons.IView;

public abstract class AbstractActivity extends Activity implements IView {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 视图
		initView();
		// 数据
		initData();
		// 事件
		initEvent();
	}


}
