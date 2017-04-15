package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.CompanyInfoContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class CompanyInfoPresenter extends RxPresenter<CompanyInfoContract.View> implements CompanyInfoContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CompanyInfoPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
