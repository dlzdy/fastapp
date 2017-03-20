package com.jeenms.app.commons;

import android.support.v4.app.Fragment;
import android.view.View;

import com.jeenms.app.commons.IView;

public abstract class AbstractFragment extends Fragment implements IView {

	protected View rootView;// 缓存Fragment view

}
