package com.employment.di.component;

import android.app.Activity;

import com.employment.model.company.activity.CheckResumeDetail;
import com.employment.model.student.activity.EmploymentDetailActivity;
import com.employment.activity.LoginActivity;
import com.employment.activity.MainActivity;
import com.employment.model.student.activity.NoteActivity;
import com.employment.model.student.activity.PersonalActivity;
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

    void inject(PersonalActivity personalActivity);

    void inject(CheckResumeDetail checkResumeDetail);
}
