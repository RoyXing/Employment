package com.employment.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.employment.R;
import com.employment.app.App;
import com.employment.app.Constants;
import com.employment.base.BaseActivity;
import com.employment.model.admin.bean.AdminInfo;
import com.employment.model.admin.fragment.CheckHomeFragment;
import com.employment.model.admin.fragment.CheckPostFragment;
import com.employment.model.admin.fragment.HomeFragment;
import com.employment.model.company.bean.CompanyInfo;
import com.employment.model.company.fragment.CheckFragment;
import com.employment.model.company.fragment.CheckResumeFragment;
import com.employment.model.company.fragment.CompanyInfoFragment;
import com.employment.model.company.fragment.PublishFragment;
import com.employment.model.student.bean.StudentInfo;
import com.employment.model.student.fragment.CommunityFragment;
import com.employment.model.student.fragment.CompanyFragment;
import com.employment.model.student.fragment.EmploymentHome;
import com.employment.model.student.fragment.NoteFragment;
import com.employment.model.student.fragment.PersonalFragment;
import com.employment.presenter.MainPresenter;
import com.employment.presenter.contract.MainContract;
import com.employment.utils.SharedPreferenceUtil;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Inject
    CheckPostFragment checkPostFragment;
    @Inject
    CheckHomeFragment checkRecruitmentFragment;
    @Inject
    HomeFragment homeFragment;

    @Inject
    CheckFragment checkFragment;
    @Inject
    CheckResumeFragment checkResumeFragment;
    @Inject
    CompanyInfoFragment companyInfoFragment;
    @Inject
    PublishFragment publishFragment;

    @Inject
    CommunityFragment communityFragment;
    @Inject
    CompanyFragment companyFragment;
    @Inject
    EmploymentHome employmentFragment;
    @Inject
    NoteFragment noteFragment;
    @Inject
    PersonalFragment personalFragment;

    private MenuItem mLastMenuItem;

    private int hideFragment;
    private int showFragment;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        if (SharedPreferenceUtil.getUserStatud() == 2) {
            mFragments[FIRST] = employmentFragment;
            mFragments[SECOND] = companyFragment;
            mFragments[THIRD] = personalFragment;
            mFragments[FOURTH] = communityFragment;
            mFragments[FIFTH] = noteFragment;
            loadMultipleRootFragment(R.id.fl_main_content, FIRST, mFragments[FIRST], mFragments[SECOND], mFragments[THIRD], mFragments[FOURTH], mFragments[FIFTH]);
            setToolBar(toolbar, getString(R.string.employment_info));
        } else if (SharedPreferenceUtil.getUserStatud() == 3) {
            mFragments[FIRST] = checkFragment;
            mFragments[SECOND] = companyInfoFragment;
            mFragments[THIRD] = publishFragment;
            mFragments[FOURTH] = checkResumeFragment;
            loadMultipleRootFragment(R.id.fl_main_content, FIRST, mFragments[FIRST], mFragments[SECOND], mFragments[THIRD], mFragments[FOURTH]);
            setToolBar(toolbar, getString(R.string.check_info));
        } else if (SharedPreferenceUtil.getUserStatud() == 1) {
            mFragments[FIRST] = homeFragment;
            mFragments[SECOND] = checkRecruitmentFragment;
            mFragments[THIRD] = checkPostFragment;
            loadMultipleRootFragment(R.id.fl_main_content, FIRST, mFragments[FIRST], mFragments[SECOND], mFragments[THIRD]);
            setToolBar(toolbar, getString(R.string.admin_home));
        }
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        navigationView.setNavigationItemSelectedListener(this);
        if (SharedPreferenceUtil.getUserStatud() == 2) {
            navigationView.inflateMenu(R.menu.drawer_view_student);
            mLastMenuItem = navigationView.getMenu().findItem(R.id.nav_employment_info);
        } else if (SharedPreferenceUtil.getUserStatud() == 3) {
            navigationView.inflateMenu(R.menu.drawer_view_company);
            mLastMenuItem = navigationView.getMenu().findItem(R.id.nav_check_info);
        } else if (SharedPreferenceUtil.getUserStatud() == 1) {
            navigationView.inflateMenu(R.menu.drawer_view_admin);
            mLastMenuItem = navigationView.getMenu().findItem(R.id.nav_admin_home);
        }
        mLastMenuItem.setChecked(true);
        mPresenter.getUserInfo(SharedPreferenceUtil.getUserStatud());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setHeader(Object object, int type) {
        View headerView = navigationView.getHeaderView(0);
        CircleImageView imageView = (CircleImageView) headerView.findViewById(R.id.user_image);
        TextView textView = (TextView) headerView.findViewById(R.id.user_name);
        if (type == 2) {
            StudentInfo studentInfo = (StudentInfo) object;
            Glide.with(this).load(studentInfo.getSface()).into(imageView);
            textView.setText(studentInfo.getSname());
        } else if (type == 3) {
            CompanyInfo companyInfo = (CompanyInfo) object;
            Glide.with(this).load(companyInfo.getCface()).into(imageView);
            textView.setText(companyInfo.getCname());
        } else if (type == 1) {
            AdminInfo adminInfo = (AdminInfo) object;
            Glide.with(this).load(adminInfo.getUface()).into(imageView);
            textView.setText(adminInfo.getUrname());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_employment_info:
                showFragment = Constants.TYPE_RECRUITMENT;
                break;
            case R.id.nav_company_info:
                showFragment = Constants.TYPE_COMPANY;
                break;
            case R.id.nav_personal_info:
                showFragment = Constants.TYPE_PERSONAL_INFO;
                break;
            case R.id.nav_community:
                showFragment = Constants.TYPE_COMMUNITY;
                break;
            case R.id.nav_note:
                showFragment = Constants.TYPE_NOTE;
                break;
            case R.id.nav_check_info:
                showFragment = Constants.TYPE_CHECK_INFO;
                break;
            case R.id.nav_check_company_info:
                showFragment = Constants.TYPE_COMPANY_INFO;
                break;
            case R.id.nav_publish_info:
                showFragment = Constants.TYPE_PUBLISH;
                break;
            case R.id.nav_check_resume:
                showFragment = Constants.TYPE_CHECK_RESUME;
                break;
            case R.id.nav_admin_home:
                showFragment = Constants.TYPE_CHECK_RECRUITMENT_STATUS;
                break;
            case R.id.nav_admin_recruitment:
                showFragment = Constants.TYPE_CHECK_RECRUITMENT_INFO;
                break;
            case R.id.nav_admin_post:
                showFragment = Constants.TYPE_CHECK_POST;
                break;
            case R.id.logout:
                App.getInstance().finishAllActivity();
                SharedPreferenceUtil.setUserStatus(0);
                App.getAppComponent().realmHelper().clearUserInfo();
                SharedPreferenceUtil.setIsLogin(false);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }

        if (!item.getTitle().equals(getString(R.string.logout))) {
            if (mLastMenuItem != null) {
                mLastMenuItem.setChecked(false);
            }
            mLastMenuItem = item;
            item.setChecked(true);
            toolbar.setTitle(item.getTitle().toString());
            showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
            hideFragment = showFragment;
        }
        return true;
    }

    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_RECRUITMENT:
                return mFragments[FIRST];
            case Constants.TYPE_COMPANY:
                return mFragments[SECOND];
            case Constants.TYPE_PERSONAL_INFO:
                return mFragments[THIRD];
            case Constants.TYPE_COMMUNITY:
                return mFragments[FOURTH];
            case Constants.TYPE_NOTE:
                return mFragments[FIFTH];
            case Constants.TYPE_CHECK_INFO:
                return mFragments[FIRST];
            case Constants.TYPE_COMPANY_INFO:
                return mFragments[SECOND];
            case Constants.TYPE_PUBLISH:
                return mFragments[THIRD];
            case Constants.TYPE_CHECK_RESUME:
                return mFragments[FOURTH];
            case Constants.TYPE_CHECK_RECRUITMENT_STATUS:
                return mFragments[FIRST];
            case Constants.TYPE_CHECK_RECRUITMENT_INFO:
                return mFragments[SECOND];
            case Constants.TYPE_CHECK_POST:
                return mFragments[THIRD];
        }
        return mFragments[FIRST];
    }

    @Override
    public void onBackPressedSupport() {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出吗");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.getInstance().exitApp();
            }
        });
        builder.show();
    }
}
