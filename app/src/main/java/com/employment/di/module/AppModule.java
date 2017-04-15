package com.employment.di.module;

import com.employment.app.App;
import com.employment.db.RealmHelper;
import com.employment.http.RetrofitHelper;
import com.employment.http.api.AdminApi;
import com.employment.http.api.CompanyApi;
import com.employment.http.api.StudentApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roy on 2017/4/7.
 */
@Module
public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    RetrofitHelper provideRetrofitHelper(AdminApi adminApi, CompanyApi companyApi, StudentApi studentApi) {
        return new RetrofitHelper(adminApi, companyApi, studentApi);
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper(){
        return new RealmHelper();
    }
}
