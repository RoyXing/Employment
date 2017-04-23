package com.employment.presenter;

import android.widget.Toast;

import com.employment.app.App;
import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.http.bean.ResponseBean;
import com.employment.model.student.bean.Recruit;
import com.employment.presenter.contract.CheckEmploymentDetailContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by 陈学勤 on 2017/4/23.
 */

public class CheckEmploymentDetailPresenter extends RxPresenter<CheckEmploymentDetailContract.View> implements CheckEmploymentDetailContract.Presenter {
    private RetrofitHelper mRetrofitHelper;
    @Inject
    public CheckEmploymentDetailPresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }
    @Override
    public void pass(Map<String,String>map,final String state) {
        Disposable disposable = mRetrofitHelper.RecruitmentState(map).compose(RxUtil.<ResponseBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseBean>() {
                    @Override
                    public void accept(@NonNull ResponseBean responseBean) throws Exception {
                        if (responseBean.getCode() == 200) {
                            if (state.equals("0")) {
                                Toast.makeText(App.getInstance(), "通过成功", Toast.LENGTH_SHORT).show();
                            } else if (state.equals("3")) {
                                Toast.makeText(App.getInstance(), "已驳回", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (state.equals("0")) {
                                Toast.makeText(App.getInstance(), "审核失败", Toast.LENGTH_SHORT).show();
                            } else if (state.equals("3")) {
                                Toast.makeText(App.getInstance(), "驳回失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
        addSubscribe(disposable);
    }

}
