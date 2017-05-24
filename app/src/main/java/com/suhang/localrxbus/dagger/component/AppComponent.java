package com.suhang.localrxbus.dagger.component;

import com.suhang.localrxbus.dagger.module.AppModule;
import com.suhang.localrxbus.dagger.module.ActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 苏杭 on 2017/5/24 21:01.
 */
//这里使用单例域
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
	//这里暴露子组件
	ActivityComponent baseComponent(ActivityModule activityModule);
}
