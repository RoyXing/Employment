package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.http.RetrofitHelper;
import com.employment.model.student.bean.Recruit;
import com.employment.presenter.contract.EmploymentContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/9.
 */

public class EmploymentPresenter extends RxPresenter<EmploymentContract.View> implements EmploymentContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public EmploymentPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void loadEmploymentInfo(String type) {
        Disposable disposable = mRetrofitHelper.findRecruits(type)
                .compose(RxUtil.<List<Recruit>>rxSchedulerHelper())
                .subscribe(new Consumer<List<Recruit>>() {
                    @Override
                    public void accept(@NonNull List<Recruit> recruits) throws Exception {
                        mView.showContent(recruits);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void loadEmployMoreInfo(String type) {
        mView.showMoreContent();
    }
}
