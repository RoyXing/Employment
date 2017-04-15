package com.employment.http.api;

import com.employment.model.admin.bean.AdminInfo;
import com.employment.http.bean.WelcomeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by roy on 2017/4/7.
 */

public interface AdminApi {

    @GET("start-image/{res}")
    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    @POST()
    Observable<AdminInfo> adminLoginFetchInfo(@QueryMap HashMap<String, String> map);
}
