package com.employment.model.student.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.employment.model.student.fragment.CommunicationFragment;
import com.employment.model.student.fragment.CommunityFragment;
import com.employment.model.student.fragment.EmploymentFragment;
import com.employment.model.student.fragment.EmploymentHome;

import java.util.List;

/**
 * Created by roy on 2017/4/15.
 */
public class CommunicationPagerAdapter extends FragmentPagerAdapter {

    private List<CommunicationFragment> fragments;

    public CommunicationPagerAdapter(FragmentManager fm, List<CommunicationFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CommunityFragment.type[position];
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
