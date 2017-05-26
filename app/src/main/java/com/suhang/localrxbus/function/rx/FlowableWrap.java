package com.suhang.localrxbus.function.rx;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by 苏杭 on 2017/4/28 11:02.
 */

public class FlowableWrap<T> {
    private Flowable<T> mFlowable;
    private CompositeDisposable mDisposable;

    public FlowableWrap(Flowable<T> flowable, CompositeDisposable disposable) {
        mFlowable = flowable;
        mDisposable = disposable;
    }

    /**
     * 订阅事件,与Rxjava一致,自动添加事件到CompositeDisposable中,方便回收
     */
    public void subscribe(final Consumer<T> consumer) {
        mDisposable.add(mFlowable.subscribe(new Consumer<T>() {
            @Override
            public void accept(@NonNull T t) throws Exception {
                consumer.accept(t);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                //do something for error
            }
        }));
    }

    /**
     * 订阅事件,与Rxjava一致,自动添加事件到CompositeDisposable中,方便回收
     * 可自己处理异常
     */
    public void subscribe(final Consumer<T> consumer, final Consumer<? super Throwable> error) {
        mDisposable.add(mFlowable.subscribe(new Consumer<T>() {
            @Override
            public void accept(@NonNull T t) throws Exception {
                consumer.accept(t);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                error.accept(throwable);
            }
        }));
    }
}
