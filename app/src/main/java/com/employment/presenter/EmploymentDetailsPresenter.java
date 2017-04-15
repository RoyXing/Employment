package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.http.bean.ResponseBean;
import com.employment.model.student.bean.Resume;
import com.employment.presenter.contract.EmploymentDetailContract;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/15.
 */

public class EmploymentDetailsPresenter extends RxPresenter<EmploymentDetailContract.View> implements EmploymentDetailContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;

    @Inject
    public EmploymentDetailsPresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
    }

    @Override
    public void applyResume(int recruitId, int areaId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("studentId", realmHelper.getStudentInfoBean().getSid() + "");
        map.put("recruitId", recruitId + "");
        Disposable subscribe = mRetrofitHelper.applyResume(map)
                .compose(RxUtil.<ResponseBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseBean>() {
                    @Override
                    public void accept(@NonNull ResponseBean responseBean) throws Exception {
                        if (responseBean.getCode() == 200) {
                            mView.applyOk();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
        addSubscribe(subscribe);
    }

    @Override
    public void isApply() {
        Disposable disposable = mRetrofitHelper.getResumeInfo(realmHelper.getStudentInfoBean().getSid() + "")
                .compose(RxUtil.<List<Resume>>rxSchedulerHelper())
                .subscribe(new Consumer<List<Resume>>() {
                    @Override
                    public void accept(@NonNull List<Resume> resumes) throws Exception {
                        mView.isCanApply(resumes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError("");
                    }
                });
        addSubscribe(disposable);
    }

}
