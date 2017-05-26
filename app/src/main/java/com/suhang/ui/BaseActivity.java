package com.suhang.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.suhang.localrxbus.annotation.BaseScope;
import com.suhang.localrxbus.application.App;
import com.suhang.localrxbus.dagger.component.BaseComponent;
import com.suhang.localrxbus.dagger.module.BaseModule;
import com.suhang.localrxbus.function.rx.SubstribeManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sh on 2017/5/26 11:10.
 */
@BaseScope
public abstract class BaseActivity extends AppCompatActivity{
    //CompositeDisposable在BaseModule中被提供,并且作用域是BaseScope,所以在该域内该对象都只有一份
    @Inject
    CompositeDisposable mDisposable;
    @Inject
    SubstribeManager mManager;
    private BaseComponent mBaseComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过父组件AppComponent来注册BaseComponent
        mBaseComponent = ((App) getApplication()).getAppComponent().baseComponent(new BaseModule(this));
        inject();
    }

    /**
     * 得到BaseComponent
     * @return
     */
    protected BaseComponent getBaseComponent() {
        return mBaseComponent;
    }

    /**
     * 需要子类进行inject
     */
    protected abstract void  inject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //回收CompositeDisposable,因整个BaseScope域都用的同一个,所以能把所有产生的Disposable回收
        mDisposable.dispose();
    }
}
