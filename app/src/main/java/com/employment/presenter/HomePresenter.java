package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.CheckPostContract;
import com.employment.presenter.contract.HomeContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public HomePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
