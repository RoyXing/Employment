package com.employment.base;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by roy on 2017/4/7.
 */

public class RxBus {

    private Subject<Object> bus;

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public static class RxBusHolder {
        private static RxBus BUS = new RxBus();
    }

    public static RxBus getInstance() {
        return RxBusHolder.BUS;
    }

    public void post(Object o) {
        bus.onNext(o);
    }

    public <T> Disposable toDefaultObservable(Class<T> eventType, Consumer<T> consumer) {
        return bus.ofType(eventType).compose(RxUtil.<T>rxSchedulerHelper()).subscribe(consumer);
    }

}
