package com.employment.di.module;

import android.app.Activity;

import com.employment.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roy on 2017/4/7.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
