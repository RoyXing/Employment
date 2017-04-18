package com.employment.model.student.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.employment.R;
import com.employment.app.Constants;
import com.employment.base.SimpleBaseFragment;
import com.employment.model.student.adapter.EmploymentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by roy on 2017/4/15.
 */

public class EmploymentHome extends SimpleBaseFragment {

    @BindView(R.id.employment_main)
    TabLayout employmentMain;
    @BindView(R.id.vp_employment_main)
    ViewPager vpEmploymentMain;

    public static String[] type = {"校招", "直聘"};
    public static String[] typeApi = {"0", "1"};
    List<EmploymentFragment> fragments = new ArrayList<>();


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
            EmploymentFragment fragment = new EmploymentFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.EMPLOYMENT, typeApi[i]);
            fragment.setArguments(bundle);
            employmentMain.addTab(employmentMain.newTab());
            fragments.add(fragment);
        }
        vpEmploymentMain.setAdapter(new EmploymentPagerAdapter(getChildFragmentManager(), fragments));
    }

}
