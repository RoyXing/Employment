package com.employment.di.component;

import com.employment.app.App;
import com.employment.db.RealmHelper;
import com.employment.di.module.AppModule;
import com.employment.di.module.HttpModule;
import com.employment.di.module.PageModule;
import com.employment.http.RetrofitHelper;
import com.employment.model.admin.fragment.CheckHomeFragment;
import com.employment.model.admin.fragment.CheckPostFragment;
import com.employment.model.admin.fragment.HomeFragment;
import com.employment.model.company.fragment.CheckFragment;
import com.employment.model.company.fragment.CheckResumeFragment;
import com.employment.model.company.fragment.CompanyInfoFragment;
import com.employment.model.company.fragment.PublishFragment;
import com.employment.model.student.fragment.CommunityFragment;
import com.employment.model.student.fragment.CompanyFragment;
import com.employment.model.student.fragment.EmploymentHome;
import com.employment.model.student.fragment.NoteFragment;
import com.employment.model.student.fragment.PersonalFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by roy on 2017/4/7.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class, PageModule.class})
public interface AppComponent {

    App getContext();

    RetrofitHelper retrofitHelper();

    RealmHelper realmHelper();

    CheckPostFragment checkPostFragment();

    CheckHomeFragment checkRecruitmentFragment();

    HomeFragment homeFragment();

    CheckFragment checkFragment();

    CheckResumeFragment checkResumeFragment();

    CompanyInfoFragment companyInfoFragment();

    PublishFragment publishFragment();

    CommunityFragment communityFragment();

    CompanyFragment companyFragment();

    EmploymentHome employmentFragment();

    NoteFragment noteFragment();

    PersonalFragment personalFragment();
}
