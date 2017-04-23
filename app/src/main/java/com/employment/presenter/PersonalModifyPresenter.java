package com.employment.presenter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.employment.base.RxBus;
import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.http.bean.ResponseBean;
import com.employment.model.student.event.EmploymentEvent;
import com.employment.presenter.contract.PersonalModifyContract;
import com.employment.utils.SystemUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/17.
 */

public class PersonalModifyPresenter extends RxPresenter<PersonalModifyContract.View> implements PersonalModifyContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private RealmHelper realmHelper;
    private Context mContext;
    private DatePickerDialog dialog;

    @Inject
    public PersonalModifyPresenter(RetrofitHelper mRetrofitHelper, RealmHelper realmHelper, Activity activity) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
        this.mContext = activity;
    }

    @Override
    public void setSelfComment(String comment) {
        realmHelper.setSelfComment(comment);
        RxBus.getInstance().post(new EmploymentEvent("0"));
        mView.setCommentSuccess();
    }

    @Override
    public void commitEmploymentInfo(String status, String position, String salary, String time) {
        HashMap<String, String> map = new HashMap<>();
        map.put("studentId", realmHelper.getStudentInfoBean().getSid() + "");
        map.put("ejobName", position);
        map.put("esalary", salary);
        map.put("etime", time);
        Disposable disposable = mRetrofitHelper.commitEmploymentInfo(map)
                .compose(RxUtil.<ResponseBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseBean>() {
                    @Override
                    public void accept(@NonNull ResponseBean responseBean) throws Exception {
                        mView.setCommentSuccess();
                        realmHelper.setEmploymentStatus(5);
                        RxBus.getInstance().post(new EmploymentEvent("1"));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError(throwable.toString());
                    }
                });
        addSubscribe(disposable);
    }

    @Override
    public void commitUnEmployment(String status, String position, String salary) {

    }

    @Override
    public void chooseStayTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar instance = Calendar.getInstance();
                instance.set(i, i1, i2);
                Date newDate = instance.getTime();
                mView.showStayDate(SystemUtils.formatTime(newDate));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        dialog.show();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
