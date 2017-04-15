package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.CheckContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class CheckPresenter extends RxPresenter<CheckContract.View> implements CheckContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CheckPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
