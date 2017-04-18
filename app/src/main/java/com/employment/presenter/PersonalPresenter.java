package com.employment.presenter;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.model.student.bean.Employment;
import com.employment.model.student.bean.UnEmployment;
import com.employment.presenter.contract.PersonalContract;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

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

    @Override
    public void getEmployInfo() {
        int state = realmHelper.getStudentInfoBean().getSstate();
        final HashMap<String, String> map = new HashMap<>();
        map.put("studentId", realmHelper.getStudentInfoBean().getSid() + "");
        map.put("state", state + "");
        Disposable disposable = null;
        if (state == 5) {
            disposable = mRetrofitHelper.getEmploymentInfo(map)
                    .compose(RxUtil.<Employment>rxSchedulerHelper())
                    .subscribe(new Consumer<Employment>() {
                                   @Override
                                   public void accept(@NonNull Employment employment) throws Exception {
                                       mView.showEmploymentInfo(employment);
                                   }
                               },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    mView.showError(throwable.toString());
                                }
                            });
        } else if (state == 4) {
            disposable = mRetrofitHelper.getUnEmploymentInfo(map)
                    .compose(RxUtil.<UnEmployment>rxSchedulerHelper())
                    .subscribe(new Consumer<UnEmployment>() {
                                   @Override
                                   public void accept(@NonNull UnEmployment employment) throws Exception {
                                       mView.showUnEmploymentInfo(employment);
                                   }
                               },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(@NonNull Throwable throwable) throws Exception {
                                    mView.showError(throwable.toString());
                                }
                            });
        }
        addSubscribe(disposable);
    }


}
