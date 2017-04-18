package com.employment.model.student.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.employment.R;
import com.employment.model.student.activity.EmploymentDetailActivity;
import com.employment.base.BaseFragment;
import com.employment.model.student.adapter.CompanyAdapter;
import com.employment.model.student.bean.Resume;
import com.employment.presenter.CompanyPresenter;
import com.employment.presenter.contract.CompanyContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class CompanyFragment extends BaseFragment<CompanyPresenter> implements CompanyContract.View, CompanyAdapter.OnclickListener {

    @BindView(R.id.company_recyclerView)
    RecyclerView companyRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    boolean isLoadingMore = false;
    private CompanyAdapter adapter;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_company;
    }

    @Override
    protected void initEventAndData() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        companyRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CompanyAdapter(mActivity, new ArrayList<Resume>());
        companyRecyclerView.setAdapter(adapter);
        adapter.setOnclickListener(this);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getResumeInfo();
            }
        });
        companyRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = linearLayoutManager.getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        mPresenter.getMoreResumeInfo();
                    }
                }
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getResumeInfo();
    }

    @Override
    public void showContent(List<Resume> list) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        adapter.setResumes(list);
    }

    @Override
    public void showError(String msg) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(mActivity, EmploymentDetailActivity.class);
        intent.putExtra(EmploymentDetailActivity.CHECK_INFO, 1);
        intent.putExtra(EmploymentDetailActivity.ARG_NUMBER, adapter.getResumes().get(position).getCmRecruitByRid());
        startActivity(intent);
    }
}
