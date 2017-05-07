package com.employment.model.student.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.employment.R;
import com.employment.app.Constants;
import com.employment.base.BaseFragment;
import com.employment.model.student.adapter.CommunicationPagerAdapter;
import com.employment.presenter.CommunityPresenter;
import com.employment.presenter.contract.CommunityContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class CommunityFragment extends BaseFragment<CommunityPresenter> implements CommunityContract.View {
    @BindView(R.id.employment_main)
    TabLayout employmentMain;
    @BindView(R.id.vp_employment_main)
    ViewPager vpEmploymentMain;
    public static String[] type = {"话题列表", "我的发帖", "我的评论"};
    public static String[] typeApi = {"0", "1", "2"};
    List<CommunicationFragment> fragments = new ArrayList<>();

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initEventAndData() {
        employmentMain.setTabMode(TabLayout.MODE_FIXED);
        employmentMain.setupWithViewPager(vpEmploymentMain);
        fragments.clear();
        for (int i = 0; i < type.length; i++) {
            CommunicationFragment fragment = new CommunicationFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.COMMUNICATION, typeApi[i]);
            fragment.setArguments(bundle);
            employmentMain.addTab(employmentMain.newTab());
            fragments.add(fragment);
        }
        vpEmploymentMain.setAdapter(new CommunicationPagerAdapter(getChildFragmentManager(), fragments));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment f : getChildFragmentManager().getFragments()) {
            if (null != f) {
                f.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
