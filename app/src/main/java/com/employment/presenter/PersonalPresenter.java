package com.employment.presenter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.employment.R;
import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.model.student.bean.Employment;
import com.employment.model.student.bean.UnEmployment;
import com.employment.presenter.contract.PersonalContract;

import java.util.Date;
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
    private Activity mContext;
    private DatePickerDialog dialog;
    private PopupWindow popupWindow;

    @Inject
    public PersonalPresenter(Activity mContext, RetrofitHelper mRetrofitHelper, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.realmHelper = realmHelper;
        this.mContext = mContext;
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

    @Override
    public void setBirthday(Date date) {
        dialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                realmHelper.setBirthday(new Date(i, i1, i2));
                mView.showBirthday(i + "-" + i1 + "-" + i2);
            }
        }, date.getYear(), date.getMonth(), date.getMonth());
        dialog.show();
    }

    @Override
    public void setPhone(LinearLayout layout, final String phone) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_layout, null);
        final EditText textView = (EditText) view.findViewById(R.id.input_text);
        Button cancel = (Button) view.findViewById(R.id.cancel);
        Button ok = (Button) view.findViewById(R.id.ok);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPhone = textView.getText().toString();
                if (!newPhone.equals(phone) && !newPhone.isEmpty()) {
                    mView.showNewPhone(newPhone);
                    realmHelper.setPhone(newPhone);
                }
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);// 非popwindow区域可触摸
        popupWindow.setFocusable(true);//可获取焦点
        popupWindow.setTouchable(true);// 设置可触摸
        popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void setEmail(LinearLayout layout, final String email) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_layout, null);
        final EditText textView = (EditText) view.findViewById(R.id.input_text);
        Button cancel = (Button) view.findViewById(R.id.cancel);
        Button ok = (Button) view.findViewById(R.id.ok);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmail = textView.getText().toString();
                if (!newEmail.equals(email) && !newEmail.isEmpty()) {
                    mView.showNewEmail(newEmail);
                    realmHelper.setEmail(newEmail);
                }
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);// 非popwindow区域可触摸
        popupWindow.setFocusable(true);//可获取焦点
        popupWindow.setTouchable(true);// 设置可触摸
        popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (dialog.isShowing() && dialog != null) {
            dialog.hide();
            dialog = null;
        }
        if (popupWindow.isShowing() && popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
