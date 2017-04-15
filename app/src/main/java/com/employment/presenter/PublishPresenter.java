package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.CheckPostContract;
import com.employment.presenter.contract.PublishContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class PublishPresenter extends RxPresenter<PublishContract.View> implements PublishContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public PublishPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
