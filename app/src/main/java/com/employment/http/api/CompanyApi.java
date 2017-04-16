package com.employment.http.api;

import com.employment.model.company.bean.CompanyInfo;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by roy on 2017/4/7.
 */

public interface CompanyApi {

    @POST("login/login")
    Observable<CompanyInfo> companyLoginFetchInfo(@QueryMap HashMap<String, String> map);

}
