package com.employment.http;

import com.employment.http.bean.ResponseBean;
import com.employment.model.admin.bean.AdminInfo;
import com.employment.model.company.bean.CompanyInfo;
import com.employment.http.api.AdminApi;
import com.employment.http.api.CompanyApi;
import com.employment.http.api.StudentApi;
import com.employment.http.bean.WelcomeBean;
import com.employment.model.student.bean.Recruit;
import com.employment.model.student.bean.StudentInfo;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by roy on 2017/4/7.
 */

public class RetrofitHelper {

    private AdminApi adminApi;
    private CompanyApi companyApi;
    private StudentApi studentApi;

    public RetrofitHelper(AdminApi adminApi, CompanyApi companyApi, StudentApi studentApi) {
        this.adminApi = adminApi;
        this.companyApi = companyApi;
        this.studentApi = studentApi;
    }

    public Observable<WelcomeBean> getWelcomeInfo(String res) {
        return adminApi.getWelcomeInfo(res);
    }

    public Observable<AdminInfo> getAdminInfo(HashMap<String, String> map) {
        return adminApi.adminLoginFetchInfo(map);
    }


    public Observable<CompanyInfo> getACompanyInfo(HashMap<String, String> map) {
        return companyApi.companyLoginFetchInfo(map);
    }


    public Observable<StudentInfo> getStudentInfo(HashMap<String, String> map) {
        return studentApi.studentLoginFetchInfo(map);
    }

    public Observable<List<Recruit>> findRecruits(String type) {
        return studentApi.findRecruits(type);
    }

    public Observable<ResponseBean> applyResume(HashMap<String, String> map) {
        return studentApi.applyResume(map);
    }
}
