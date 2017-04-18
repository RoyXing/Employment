package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.presenter.contract.PersonalModifyContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/17.
 */

public class PersonalModifyPresenter extends RxPresenter<PersonalModifyContract.View> implements PersonalModifyContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;


    @Inject
    public PersonalModifyPresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
    }

    @Override
    public void setSelfComment(String comment) {
        realmHelper.setSelfComment(comment);
        mView.setCommentSuccess();
    }
}
