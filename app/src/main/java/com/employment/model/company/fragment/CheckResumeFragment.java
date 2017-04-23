package com.employment.model.company.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.model.company.bean.Interview;
import com.employment.model.student.adapter.EmploymentAdapter;
import com.employment.model.student.bean.Recruit;
import com.employment.presenter.CheckResumePresenter;
import com.employment.presenter.contract.CheckResumeContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class CheckResumeFragment extends BaseFragment<CheckResumePresenter> implements CheckResumeContract.View, EmploymentAdapter.OnclickListener {

    @BindView(R.id.checkResume_recyclerView)
    RecyclerView checkResumeRecyclerView;
    @BindView(R.id.check_resume_swipe)
    SwipeRefreshLayout checkResumeSwipe;
    private EmploymentAdapter adapter;

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
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        checkResumeRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new EmploymentAdapter(mActivity, new ArrayList<Recruit>());
        checkResumeRecyclerView.setAdapter(adapter);
        adapter.setOnclickListener(this);
        checkResumeSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getAllInterview();
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getAllInterview();
    }

    @Override
    public void showContent(List<Interview> interviews) {
        if (checkResumeSwipe.isRefreshing())
            checkResumeSwipe.setRefreshing(false);
        List<Recruit> list = new ArrayList<>();
        for (Interview interview : interviews) {
            list.add(interview.getCmRecruitByRid());
        }
        adapter.setRecruits(list);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
