package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.CompanyContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class CompanyPresenter extends RxPresenter<CompanyContract.View> implements CompanyContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CompanyPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
