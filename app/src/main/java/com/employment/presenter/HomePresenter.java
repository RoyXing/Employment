package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.http.RetrofitHelper;
import com.employment.model.admin.bean.Department;
import com.employment.model.student.bean.StudentInfo;
import com.employment.model.student.bean.UnEmployment;
import com.employment.presenter.contract.HomeContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/9.
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public HomePresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getAllStudentInfo() {
        mRetrofitHelper.getAllStudentInfo()
                .compose(RxUtil.<List<StudentInfo>>rxSchedulerHelper())
                .subscribe(new Consumer<List<StudentInfo>>() {
                    @Override
                    public void accept(@NonNull List<StudentInfo> infoList) throws Exception {
                        mView.showAllStudent(infoList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError(throwable.toString());
                    }
                });
    }

    @Override
    public void getAllDepartmentInfo() {
        mRetrofitHelper.getAllDepartment()
                .compose(RxUtil.<List<Department>>rxSchedulerHelper())
                .subscribe(new Consumer<List<Department>>() {
                    @Override
                    public void accept(@NonNull List<Department> departments) throws Exception {
                        mView.showAllDepartmentInfo(departments);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError(throwable.toString());
                    }
                });
    }

    @Override
    public void getUnEmploymentByDepartment(String id, final String name) {
        mRetrofitHelper.getUnEmploymentByDepartment(id)
                .compose(RxUtil.<List<UnEmployment>>rxSchedulerHelper())
                .subscribe(new Consumer<List<UnEmployment>>() {
                    @Override
                    public void accept(@NonNull List<UnEmployment> unEmploymentList) throws Exception {
                        mView.showAllUnEmploymentByDepartment(unEmploymentList, name);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError(throwable.toString());
                    }
                });
    }

    @Override
    public void getAllUnEmploymentInfo() {
        mRetrofitHelper.getAllUnEmployment()
                .compose(RxUtil.<List<UnEmployment>>rxSchedulerHelper())
                .subscribe(new Consumer<List<UnEmployment>>() {
                               @Override
                               public void accept(@NonNull List<UnEmployment> unEmploymentList) throws Exception {
                                   mView.showUnEmploymentInfo(unEmploymentList);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                mView.showError(throwable.toString());
                            }
                        });
    }
}
