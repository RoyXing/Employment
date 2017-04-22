package com.employment.presenter;

import android.app.Activity;
import android.util.Log;

import com.employment.base.RxPresenter;
import com.employment.base.RxUtil;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.model.admin.bean.AdminInfo;
import com.employment.model.company.bean.CompanyInfo;
import com.employment.model.student.bean.StudentInfo;
import com.employment.presenter.contract.LoginContract;
import com.employment.utils.SystemUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by roy on 2017/4/8.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private RetrofitHelper mRetrofitHelper;
    private Activity activity;
    private RealmHelper realmHelper;

    @Inject
    public LoginPresenter(RetrofitHelper mRetrofitHelper, Activity activity, RealmHelper realmHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
        this.activity = activity;
        this.realmHelper = realmHelper;
    }

    @Override
    public void login(String userName, String passWord, String type) {
        if (userName.isEmpty() || passWord.isEmpty()) {
            SystemUtils.showToast(activity, "账号或者密码不能为空");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", passWord);
        map.put("type", type);

        Disposable disposable;
        if (type.equals("1")) {
            disposable = mRetrofitHelper.getAdminInfo(map)
                    .compose(RxUtil.<AdminInfo>rxSchedulerHelper())
                    .subscribe(new Consumer<AdminInfo>() {
                        @Override
                        public void accept(@NonNull AdminInfo adminInfo) throws Exception {
                            realmHelper.insertAdminInfo(adminInfo);
                            mView.loginSuccess();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            mView.loginFailure();
                        }
                    });

        } else if (type.equals("3")) {
            disposable = mRetrofitHelper.getACompanyInfo(map)
                    .compose(RxUtil.<CompanyInfo>rxSchedulerHelper())
                    .subscribe(new Consumer<CompanyInfo>() {
                        @Override
                        public void accept(@NonNull CompanyInfo companyInfo) throws Exception {
                            realmHelper.insertCompanyInfo(companyInfo);
                            mView.loginSuccess();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            mView.loginFailure();
                        }
                    });

        } else {
            disposable = mRetrofitHelper.getStudentInfo(map)
                    .compose(RxUtil.<StudentInfo>rxSchedulerHelper())
                    .subscribe(new Consumer<StudentInfo>() {
                        @Override
                        public void accept(@NonNull StudentInfo studentInfo) throws Exception {
                            realmHelper.insertStudentInfo(studentInfo);
                            mView.loginSuccess();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            mView.loginFailure();
                        }
                    });
        }
        addSubscribe(disposable);
    }
}
