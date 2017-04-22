package com.employment.model.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.presenter.CheckResumePresenter;
import com.employment.presenter.contract.CheckResumeContract;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class CheckResumeFragment extends BaseFragment<CheckResumePresenter> implements CheckResumeContract.View {

    @BindView(R.id.checkResume_recyclerView)
    RecyclerView checkResumeRecyclerView;
    @BindView(R.id.check_resume_swipe)
    SwipeRefreshLayout checkResumeSwipe;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check_resume;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getAllInterview();
    }
}
