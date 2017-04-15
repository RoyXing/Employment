package com.employment.di.component;

import android.app.Activity;

import com.employment.model.admin.fragment.CheckPostFragment;
import com.employment.model.admin.fragment.CheckRecruitmentFragment;
import com.employment.model.admin.fragment.HomeFragment;
import com.employment.model.company.fragment.CheckFragment;
import com.employment.model.company.fragment.CheckResumeFragment;
import com.employment.model.company.fragment.CompanyInfoFragment;
import com.employment.model.company.fragment.PublishFragment;
import com.employment.di.module.FragmentModule;
import com.employment.di.scope.FragmentScope;
import com.employment.model.student.fragment.CommunityFragment;
import com.employment.model.student.fragment.CompanyFragment;
import com.employment.model.student.fragment.EmploymentFragment;
import com.employment.model.student.fragment.NoteFragment;
import com.employment.model.student.fragment.PersonalFragment;

import dagger.Component;

/**
 * Created by roy on 2017/4/8.
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(CheckPostFragment checkPostFragment);

    void inject(CheckRecruitmentFragment checkRecruitmentFragment);

    void inject(HomeFragment homeFragment);

    void inject(CheckFragment checkFragment);

    void inject(CheckResumeFragment checkResumeFragment);

    void inject(CompanyInfoFragment companyInfoFragment);

    void inject(PublishFragment publishFragment);

    void inject(CommunityFragment communityFragment);

    void inject(CompanyFragment companyFragment);

    void inject(EmploymentFragment employmentFragment);

    void inject(NoteFragment noteFragment);

    void inject(PersonalFragment personalFragment);

}
