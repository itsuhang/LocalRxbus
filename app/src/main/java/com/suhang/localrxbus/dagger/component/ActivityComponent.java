package com.suhang.localrxbus.dagger.component;

import com.suhang.localrxbus.MainActivity;
import com.suhang.localrxbus.annotation.ActivityScope;
import com.suhang.localrxbus.dagger.module.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by 苏杭 on 2017/5/24 21:01.
 */
//这里使用针对Activity的作用域
@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
	//提供注入方法
	void inject(MainActivity activity);
}
