package com.suhang.localrxbus.function;

import com.suhang.localrxbus.annotation.ActivityScope;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subscribers.SerializedSubscriber;
//使Rxbus的作用域为Activity域,保证其在该域中,也就是Activity中保证单例(本例中作用域被限制在了Activity中,可根据情况更改)
@ActivityScope
public class RxBus {
    private final FlowableProcessor<Object> mBus;
    //将Rxbus注入
    @Inject
    public RxBus() {
        //调用toSerialized()方法，保证线程安全
        mBus = PublishProcessor.create().toSerialized();
    }

    /**
     * 发送消息
     * @param o
     */
    public void post(Object o) {
        new SerializedSubscriber<>(mBus).onNext(o);
    }

    /**
     * 确定接收消息的类型
     * @param aClass
     * @param <T>
     * @return
     */
    public <T> Flowable<T> toFlowable(Class<T> aClass) {
        return mBus.ofType(aClass);
    }

    /**
     * 判断是否有订阅者
     * @return
     */
    public boolean hasSubscribers() {
        return mBus.hasSubscribers();
    }

}