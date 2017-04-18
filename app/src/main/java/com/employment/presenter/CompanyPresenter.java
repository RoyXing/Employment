package com.employment.presenter;

import com.employment.base.RxBus;
import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.model.student.bean.Resume;
import com.employment.model.student.event.NoteEvent;
import com.employment.model.student.event.ResumeEvent;
import com.employment.presenter.contract.CompanyContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/9.
 */

public class CompanyPresenter extends RxPresenter<CompanyContract.View> implements CompanyContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;

    @Inject
    public CompanyPresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
        registerEvent();
    }

    private void registerEvent() {
        addRxBusSubscribe(ResumeEvent.class, new Consumer<ResumeEvent>() {
            @Override
            public void accept(@NonNull ResumeEvent resumeEvent) throws Exception {
                if (resumeEvent.getType() == 1)
                    getResumeInfo();
            }
        });
    }

    @Override
    public void getResumeInfo() {
        Disposable disposable = mRetrofitHelper.getResumeInfo(realmHelper.getStudentInfoBean().getSid() + "")
                .compose(RxUtil.<List<Resume>>rxSchedulerHelper())
                .subscribe(new Consumer<List<Resume>>() {
                    @Override
                    public void accept(@NonNull List<Resume> resumes) throws Exception {
                        mView.showContent(resumes);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError("");
                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void getMoreResumeInfo() {

    }
}
