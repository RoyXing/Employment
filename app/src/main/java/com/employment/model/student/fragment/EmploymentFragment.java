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
import com.employment.app.Constants;
import com.employment.base.BaseFragment;
import com.employment.model.student.adapter.EmploymentAdapter;
import com.employment.model.student.bean.Recruit;
import com.employment.presenter.EmploymentPresenter;
import com.employment.presenter.contract.EmploymentContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by roy on 2017/3/28.
 */

public class EmploymentFragment extends BaseFragment<EmploymentPresenter> implements EmploymentContract.View, EmploymentAdapter.OnclickListener {

    @BindView(R.id.employment_recyclerView)
    RecyclerView employmentRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    boolean isLoadingMore = false;
    private EmploymentAdapter adapter;
    private String type;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_employment;
    }

    @Override
    protected void initEventAndData() {
        type = getArguments().getString(Constants.EMPLOYMENT);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        employmentRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new EmploymentAdapter(mActivity, new ArrayList<Recruit>());
        employmentRecyclerView.setAdapter(adapter);
        adapter.setOnclickListener(this);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadEmploymentInfo(type);
            }
        });
        employmentRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                int totalItemCount = linearLayoutManager.getItemCount();
                if (lastVisibleItem >= totalItemCount - 2 && dy > 0) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        mPresenter.loadEmployMoreInfo(type);
                    }
                }
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadEmploymentInfo(type);
    }

    @Override
    public void showContent(List<Recruit> recruits) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        adapter.setRecruits(recruits);
    }

    @Override
    public void showMoreContent() {
        isLoadingMore = false;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(mActivity, EmploymentDetailActivity.class);
        intent.putExtra(EmploymentDetailActivity.ARG_NUMBER, adapter.getRecruits().get(position));
        startActivity(intent);
    }

    @Override
    public void showError(String msg) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
    }
}
