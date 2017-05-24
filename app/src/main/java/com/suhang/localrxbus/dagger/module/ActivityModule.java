package com.suhang.localrxbus.dagger.module;

import android.app.Activity;
import android.content.Context;


import com.suhang.localrxbus.annotation.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 苏杭 on 2017/5/24 21:01.
 */
@ActivityScope
@Module
public class ActivityModule {
	private Activity mActivity;

	public ActivityModule(Activity activity) {
		mActivity = activity;
	}

	/**
	 * 提供Activity,这里使用了ActivityScope域,则在此域中的mActivity对象只有一份(也就是局部单例)
	 * @return
	 */
	@ActivityScope
	@Provides
	Activity providerActivity() {
		return mActivity;
	}

	@ActivityScope
	@Provides
	Context providerContext() {
		return mActivity;
	}
}
