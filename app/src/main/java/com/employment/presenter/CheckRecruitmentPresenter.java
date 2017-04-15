package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.CheckPostContract;
import com.employment.presenter.contract.CheckRecruitmentContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class CheckRecruitmentPresenter extends RxPresenter<CheckRecruitmentContract.View> implements CheckRecruitmentContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CheckRecruitmentPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
