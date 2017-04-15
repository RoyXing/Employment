package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.CommunityContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class CommunityPresenter extends RxPresenter<CommunityContract.View> implements CommunityContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CommunityPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
