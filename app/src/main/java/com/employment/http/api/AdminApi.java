package com.employment.http.api;

import com.employment.http.bean.ResponseBean;
import com.employment.model.admin.bean.AdminInfo;
import com.employment.http.bean.WelcomeBean;
import com.employment.model.student.bean.Recruit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by roy on 2017/4/7.
 */

public interface AdminApi {

    @GET("start-image/{res}")
    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

    @POST("login/login")
    Observable<AdminInfo> adminLoginFetchInfo(@QueryMap HashMap<String, String> map);

    @GET("recruit/findRecruitByState")
    Observable<List<Recruit>> adminSelectRecruitment(@Query("state") String state);

    @POST("recruit/verify")
    Observable<ResponseBean> RecruitmentState(@QueryMap Map<String, String> map);

}
