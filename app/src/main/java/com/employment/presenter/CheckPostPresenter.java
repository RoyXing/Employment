package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.CheckPostContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class CheckPostPresenter extends RxPresenter<CheckPostContract.View> implements CheckPostContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CheckPostPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
