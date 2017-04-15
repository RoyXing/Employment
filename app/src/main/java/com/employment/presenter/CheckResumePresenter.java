package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.CheckResumeContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class CheckResumePresenter extends RxPresenter<CheckResumeContract.View> implements CheckResumeContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CheckResumePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
