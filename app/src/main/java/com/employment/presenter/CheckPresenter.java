package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.http.RetrofitHelper;
import com.employment.model.admin.bean.Department;
import com.employment.presenter.contract.CheckContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/9.
 */

public class CheckPresenter extends RxPresenter<CheckContract.View> implements CheckContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CheckPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }


    @Override
    public void getAllDepartment() {
        Disposable disposable = mRetrofitHelper.getAllDepartment()
                .compose(RxUtil.<List<Department>>rxSchedulerHelper())
                .subscribe(new Consumer<List<Department>>() {
                    @Override
                    public void accept(@NonNull List<Department> departments) throws Exception {
                        mView.showAllDepartment(departments);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError(throwable.toString());
                    }
                });
        addSubscribe(disposable);
    }
}
