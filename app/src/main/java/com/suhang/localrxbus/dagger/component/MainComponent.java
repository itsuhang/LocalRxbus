package com.suhang.localrxbus.dagger.component;

import com.suhang.localrxbus.dagger.module.MainModule;
import com.suhang.ui.MainActivity;

import dagger.Subcomponent;

/**
 * Created by 苏杭 on 2017/5/26 11:19.
 */
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
