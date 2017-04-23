package com.employment.http.api;

import com.employment.http.bean.ResponseBean;
import com.employment.model.admin.bean.Department;
import com.employment.model.company.bean.CompanyInfo;
import com.employment.model.company.bean.Interview;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by roy on 2017/4/7.
 */

public interface CompanyApi {

    @POST("login/login")
    Observable<CompanyInfo> companyLoginFetchInfo(@QueryMap HashMap<String, String> map);

    @GET("department/findAll")
    Observable<List<Department>> getAllDepartment();

    @POST("company/update")
    Observable<ResponseBean> commitCompanyInfo(@QueryMap HashMap<String, String> map);

    @GET("inter/getInterviews")
    Observable<List<Interview>> getAllInterview(@Query("companyId") String companyId);

    @POST("recruit/add")
    Observable<ResponseBean> publishRecruit(@QueryMap HashMap<String, Object> map);
}
