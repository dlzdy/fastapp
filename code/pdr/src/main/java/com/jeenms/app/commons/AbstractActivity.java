package com.jeenms.app.commons;

import android.app.Activity;
import android.os.Bundle;

public abstract class AbstractActivity extends Activity implements IActivity {
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
