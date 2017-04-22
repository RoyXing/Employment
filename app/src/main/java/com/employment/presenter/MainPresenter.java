package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.db.RealmHelper;
import com.employment.presenter.contract.MainContract;

import javax.inject.Inject;

/**
 * Created by roy on 2017/4/7.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RealmHelper realmHelper;

    @Inject
    public MainPresenter(RealmHelper realmHelper) {
        this.realmHelper = realmHelper;
    }

    @Override
    public void getUserInfo(int type) {
        if (type == 2) {
            mView.setHeader(realmHelper.getStudentInfoBean(), type);
        } else if (type == 3) {
            mView.setHeader(realmHelper.getCompanyInfoBean(), type);
        } else if (type == 1) {
            mView.setHeader(realmHelper.getAdminInfoBean(), type);
        }
    }
}
