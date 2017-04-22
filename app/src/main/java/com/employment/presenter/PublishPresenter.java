package com.employment.presenter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.http.RetrofitHelper;
import com.employment.http.bean.ResponseBean;
import com.employment.presenter.contract.CheckPostContract;
import com.employment.presenter.contract.PublishContract;
import com.employment.utils.SystemUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/9.
 */

public class PublishPresenter extends RxPresenter<PublishContract.View> implements PublishContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private DatePickerDialog dialog;
    private Context mContext;

    @Inject
    public PublishPresenter(RetrofitHelper mRetrofitHelper, Activity activity) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.mContext = activity;
    }

    @Override
    public void publishRecruit(HashMap<String, String> map) {
        mRetrofitHelper.publishRecruit(map)
                .compose(RxUtil.<ResponseBean>rxSchedulerHelper())
                .subscribe(new Consumer<ResponseBean>() {
                    @Override
                    public void accept(@NonNull ResponseBean responseBean) throws Exception {
                        if (responseBean.getCode() == 200) {
                            mView.publishSuccess();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void chooseTime(Date date, final int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar instance = Calendar.getInstance();
                instance.set(i, i1, i2);
                Date newDate = instance.getTime();
                mView.setTime(SystemUtils.formatTime(newDate), type);
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
