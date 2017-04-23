package com.employment.presenter;

import com.employment.base.RxBus;
import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.http.RetrofitHelper;
import com.employment.http.bean.ResponseBean;
import com.employment.model.company.event.CompanyEvent;
import com.employment.presenter.contract.ResumeDetailContract;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/23.
 */

public class CheckResumeDetailPresenter extends RxPresenter<ResumeDetailContract.View>
        implements ResumeDetailContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CheckResumeDetailPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void commitResumeStatus(String id, final String type) {
        HashMap<String, String> map = new HashMap<>();
        map.put("interId", id);
        map.put("isuccess", type);
        mRetrofitHelper.commitInterviewInfo(map)
                .compose(RxUtil.<ResponseBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseBean>() {
                    @Override
                    public void accept(@NonNull ResponseBean responseBean) throws Exception {
                        if (responseBean.getCode() == 200) {
                            mView.commitSuccess(type);
                            RxBus.getInstance().post(new CompanyEvent("0"));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }
}
