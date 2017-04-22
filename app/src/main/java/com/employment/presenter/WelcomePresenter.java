package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.http.RetrofitHelper;
import com.employment.http.bean.WelcomeBean;
import com.employment.presenter.contract.WelcomeContract;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/7.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private static final int COUNT_DOWN_TIME = 2200;

    @Inject
    public WelcomePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getWelcomeImage() {
        mView.showContent(new WelcomeBean());
        startCountDown();
    }

    private void startCountDown() {
        Disposable disposable = Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.jumpWhichPage();
                    }
                });
        addSubscribe(disposable);
    }
}
