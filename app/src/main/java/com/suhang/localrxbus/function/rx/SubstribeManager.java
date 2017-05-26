package com.suhang.localrxbus.function.rx;

import com.suhang.localrxbus.annotation.BaseScope;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by 苏杭 on 2017/5/26 11:03.
 */
@BaseScope
public class SubstribeManager {
    @Inject
    RxBus mRxBus;
    //这里得到的CompositeDisposable与Activity中的是同一个对象
    @Inject
    CompositeDisposable mDisposable;
    @Inject
    public SubstribeManager() {
    }

    /**
     * 发送事件
     * @param o
     */
    public void post(Object o) {
        mRxBus.post(o);
    }

    /**
     * 订阅事件,返回包裹类,方便同一回收Disposable
     * @param aClass
     * @param <T>
     * @return
     */
    public <T> FlowableWrap<T> subscribeResult(Class<T> aClass) {
        return new FlowableWrap<>(mRxBus.toFlowable(aClass),mDisposable);
    }

}
