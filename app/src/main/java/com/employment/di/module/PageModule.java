package com.employment.di.module;

import com.employment.model.admin.fragment.CheckHomeFragment;
import com.employment.model.admin.fragment.CheckPostFragment;
import com.employment.model.admin.fragment.CheckRecruitmentFragment;
import com.employment.model.admin.fragment.HomeFragment;
import com.employment.model.company.fragment.CheckFragment;
import com.employment.model.company.fragment.CheckResumeFragment;
import com.employment.model.company.fragment.CompanyInfoFragment;
import com.employment.model.company.fragment.PublishFragment;
import com.employment.model.student.fragment.CommunityFragment;
import com.employment.model.student.fragment.CompanyFragment;
import com.employment.model.student.fragment.EmploymentFragment;
import com.employment.model.student.fragment.EmploymentHome;
import com.employment.model.student.fragment.NoteFragment;
import com.employment.model.student.fragment.PersonalFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by roy on 2017/4/8.
 */
@Module
public class PageModule {

    //admin
    @Singleton
    @Provides
    CheckPostFragment provideCheckPostFragment(){
        return new CheckPostFragment();
    }

    @Singleton
    @Provides
    CheckHomeFragment provideCheckRecruitmentFragment(){
        return new CheckHomeFragment();
    }

    @Singleton
    @Provides
    HomeFragment provideHomeFragment(){
        return new HomeFragment();
    }


    //company
    @Singleton
    @Provides
    CheckFragment provideCheckFragment(){
        return new CheckFragment();
    }

    @Singleton
    @Provides
    CheckResumeFragment provideCheckResumeFragment(){
        return new CheckResumeFragment();
    }

    @Singleton
    @Provides
    CompanyInfoFragment provideCompanyInfoFragment(){
        return new CompanyInfoFragment();
    }

    @Singleton
    @Provides
    PublishFragment providePublishFragment(){
        return new PublishFragment();
    }

    //student
    @Singleton
    @Provides
    CommunityFragment provideCommunityFragment(){
        return new CommunityFragment();
    }

    @Singleton
    @Provides
    CompanyFragment provideCompanyFragment(){
        return new CompanyFragment();
    }

    @Singleton
    @Provides
    EmploymentHome provideEmploymentFragment(){
        return new EmploymentHome();
    }

    @Singleton
    @Provides
    NoteFragment provideNoteFragment(){
        return new NoteFragment();
    }

    @Singleton
    @Provides
    PersonalFragment providePersonalFragment(){
        return new PersonalFragment();
    }
}
