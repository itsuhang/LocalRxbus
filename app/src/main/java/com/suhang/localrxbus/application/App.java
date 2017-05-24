package com.suhang.localrxbus.application;

import android.app.Application;

import com.suhang.localrxbus.dagger.component.AppComponent;
import com.suhang.localrxbus.dagger.component.DaggerAppComponent;
import com.suhang.localrxbus.dagger.module.AppModule;

/**
 * Created by 苏杭 on 2017/5/24 20:56.
 */

public class App extends Application {

	private AppComponent mAppComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
	}

	public AppComponent getAppComponent() {
		return mAppComponent;
	}
}
