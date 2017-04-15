package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.PersonalContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class PersonalPresenter extends RxPresenter<PersonalContract.View> implements PersonalContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public PersonalPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
}
