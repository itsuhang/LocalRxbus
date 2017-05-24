package com.suhang.localrxbus.dagger.module;

import android.app.Application;

import com.suhang.localrxbus.application.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 苏杭 on 2017/5/24 21:01.
 */
@Singleton
@Module
public class AppModule {
	private App mApp;
	public AppModule(App app) {
		mApp = app;
	}

	/**
	 * 将App提供出去
	 * @return
	 */
	@Singleton
	@Provides
	App providerApp() {
		return mApp;
	}
}
