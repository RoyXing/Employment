package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.model.company.bean.Interview;
import com.employment.presenter.contract.CheckResumeContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/9.
 */

public class CheckResumePresenter extends RxPresenter<CheckResumeContract.View> implements CheckResumeContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;

    @Inject
    public CheckResumePresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
    }

    @Override
    public void getAllInterview() {
        mRetrofitHelper.getAllInterview(realmHelper.getCompanyInfoBean().getCid() + "")
                .compose(RxUtil.<List<Interview>>rxSchedulerHelper())
                .subscribe(new Consumer<List<Interview>>() {
                    @Override
                    public void accept(@NonNull List<Interview> interviews) throws Exception {
                        mView.showContent(interviews);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }
}
