package com.employment.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.employment.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roy on 2017/4/8.
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
