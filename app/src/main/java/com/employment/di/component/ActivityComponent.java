package com.employment.di.component;

import android.app.Activity;

import com.employment.activity.EmploymentDetailActivity;
import com.employment.activity.LoginActivity;
import com.employment.activity.MainActivity;
import com.employment.activity.NoteActivity;
import com.employment.activity.WelcomeActivity;
import com.employment.di.module.ActivityModule;
import com.employment.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by roy on 2017/4/7.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(WelcomeActivity welcomeActivity);

    void inject(LoginActivity loginActivity);

    void inject(EmploymentDetailActivity employmentDetailActivity);

    void inject(NoteActivity noteActivity);
}
