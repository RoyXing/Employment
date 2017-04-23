package com.employment.model.company.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.employment.R;
import com.employment.base.BaseFragment;
import com.employment.model.company.activity.CheckResumeDetail;
import com.employment.model.company.adapter.CheckResumeAdapter;
import com.employment.model.company.bean.Interview;
import com.employment.presenter.CheckResumePresenter;
import com.employment.presenter.contract.CheckResumeContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class CheckResumeFragment extends BaseFragment<CheckResumePresenter> implements CheckResumeContract.View, CheckResumeAdapter.OnItemClickListener {

    @BindView(R.id.checkResume_recyclerView)
    RecyclerView checkResumeRecyclerView;
    @BindView(R.id.check_resume_swipe)
    SwipeRefreshLayout checkResumeSwipe;
    private CheckResumeAdapter adapter;

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
        adapter = new CheckResumeAdapter(mActivity, new ArrayList<Interview>());
        checkResumeRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
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
        adapter.setInterviews(interviews);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(mContext, CheckResumeDetail.class);
        intent.putExtra("info", adapter.getInterviews().get(position).getCmStudentBySid());
        intent.putExtra("interviewId",adapter.getInterviews().get(position).getIid());
        intent.putExtra("status",adapter.getInterviews().get(position).getIsuccess());
        startActivity(intent);
    }
}
