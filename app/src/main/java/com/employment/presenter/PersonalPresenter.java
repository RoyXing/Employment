package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.PersonalContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/9.
 */

public class PersonalPresenter extends RxPresenter<PersonalContract.View> implements PersonalContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;

    @Inject
    public PersonalPresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
    }

    @Override
    public void getStudentInfo() {
        mView.showContent(realmHelper.getStudentInfoBean());
    }
}
