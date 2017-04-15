package com.employment.di.module;

import android.util.Log;

import com.employment.BuildConfig;
import com.employment.app.Constants;
import com.employment.di.qualifier.AdminUrl;
import com.employment.di.qualifier.CompanyUrl;
import com.employment.di.qualifier.StudentUrl;
import com.employment.http.api.AdminApi;
import com.employment.http.api.CompanyApi;
import com.employment.http.api.StudentApi;
import com.employment.utils.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by roy on 2017/4/7.
 */

@Module
public class HttpModule {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @AdminUrl
    Retrofit provideAdminRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, Constants.HOST);
    }

    @Singleton
    @Provides
    @CompanyUrl
    Retrofit provideCompanyRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, Constants.HOST);
    }

    @Singleton
    @Provides
    @StudentUrl
    Retrofit provideStudentRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, Constants.HOST);
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("roy", message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtils.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtils.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }

    @Singleton
    @Provides
    AdminApi provideAdminService(@AdminUrl Retrofit retrofit) {
        return retrofit.create(AdminApi.class);
    }

    @Singleton
    @Provides
    CompanyApi provideCompanyService(@CompanyUrl Retrofit retrofit) {
        return retrofit.create(CompanyApi.class);
    }

    @Singleton
    @Provides
    StudentApi provideStudentService(@StudentUrl Retrofit retrofit) {
        return retrofit.create(StudentApi.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String host) {
        return builder.baseUrl(host).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
