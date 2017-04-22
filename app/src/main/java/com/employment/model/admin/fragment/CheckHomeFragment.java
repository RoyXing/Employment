package com.employment.model.admin.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.employment.R;
import com.employment.app.Constants;
import com.employment.base.SimpleBaseFragment;
import com.employment.model.admin.adapter.CheckFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by roy on 2017/4/22.
 */

public class CheckHomeFragment extends SimpleBaseFragment {

    @BindView(R.id.employment_main)
    TabLayout employmentMain;
    @BindView(R.id.vp_employment_main)
    ViewPager vpEmploymentMain;

    public static String[] type = {"已审核", "待审核"};
    public static String[] typeApi = {"0", "1"};
    List<CheckRecruitmentFragment> fragments = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_employment_home;
    }

    @Override
    protected void initEventAndData() {
        employmentMain.setTabMode(TabLayout.MODE_FIXED);
        employmentMain.setupWithViewPager(vpEmploymentMain);
        fragments.clear();
        for (int i = 0; i < type.length; i++) {
            CheckRecruitmentFragment fragment = new CheckRecruitmentFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.EMPLOYMENT, typeApi[i]);
            fragment.setArguments(bundle);
            employmentMain.addTab(employmentMain.newTab());
            fragments.add(fragment);
        }
        vpEmploymentMain.setAdapter(new CheckFragmentAdapter(getChildFragmentManager(), fragments));

    }
}
